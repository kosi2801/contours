package at.fhj.swd07.contours.generators;

import org.apache.commons.lang.StringUtils;

import at.fhj.swd07.contours.util.ClassAttribute;

public class ByteTypeGenerator extends AbstractTypeGenerator {

    protected Integer sizeOfDatatypeInBytes() {
        return 1;
    }

    protected Boolean isStaticDatatype() {
        return false;
    }

    @Override
    public void generateDataStructureCode(StringBuilder fileContent, ClassAttribute attr) {
        final MultiplicityEnum multiplicity = calculateMultiplicity(attr);
        fileContent.append(generateBasicTypeAttributeCode(multiplicity, attr.static_multiplier, "byte", attr.name));
    }
    
    @Override
    public void generateAttributeReaderCode(StringBuilder fileContent, ClassAttribute attr) {
        // skip bytes when no name and no contextname
        if (StringUtils.isBlank(attr.name) && StringUtils.isBlank(attr.contextName)) {
            // skip number from context
            if (attr.context_multiplier != null) {
                fileContent.append("        ParseUtil.skipBytes(is, context, context.variables.get(\"" + attr.context_multiplier + "\")); \n");
            } else {
                fileContent.append("        ParseUtil.skipBytes(is, context, " + attr.static_multiplier.toString() + "); \n");
            }
        } else {
            // get dynamic number of elements from context
            if (attr.context_multiplier != null) {
                fileContent.append("        final byte[] " + attr.syntheticName + " = ParseUtil.readBytes(is, context, context.variables.get(\""
                        + attr.context_multiplier + "\")); \n");
            } else { // get static number of elements
                fileContent.append("        final byte[] " + attr.syntheticName + " = ParseUtil.readBytes(is, context, " + attr.static_multiplier.toString()
                        + "); \n");
            }
            if (attr.name != null) {
                if ((attr.static_multiplier != null) && (attr.static_multiplier == 1)) {
                    fileContent.append("        comp.set" + StringUtils.capitalize(attr.name) + "( " + attr.syntheticName + "[0]); \n");
                } else {
                    fileContent.append("        comp.set" + StringUtils.capitalize(attr.name) + "( " + attr.syntheticName + "); \n");
                }
            }
            if (attr.contextName != null) {
                fileContent.append("        context.variables.put(\"" + attr.contextName + "\", 0xFF & (int)" + attr.syntheticName + "[0]);\n");
            }
        }
    }

}
