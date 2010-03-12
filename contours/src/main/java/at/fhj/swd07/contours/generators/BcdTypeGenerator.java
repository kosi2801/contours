package at.fhj.swd07.contours.generators;

import org.apache.commons.lang.StringUtils;

import at.fhj.swd07.contours.util.ClassAttribute;

public class BcdTypeGenerator extends AbstractTypeGenerator {

    protected Integer sizeOfDatatypeInBytes() {
        return 1;
    }

    protected Boolean isStaticDatatype() {
        return false;
    }

    @Override
    public void generateDataStructureCode(StringBuilder fileContent, ClassAttribute attr) {
        fileContent.append(generateBasicTypeAttributeCode(MultiplicityEnum.ONCE, 0, "String", attr.name));
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
            fileContent.append("        final String " + attr.syntheticName + "; \n");
            if (attr.context_multiplier != null) {
                fileContent.append("        " + attr.syntheticName + " = ParseUtil.readBcd(is, context, context.variables.get(\""
                        + attr.context_multiplier + "\")); \n");
            } else { // get static number of elements
                fileContent
                        .append("        " + attr.syntheticName + " = ParseUtil.readBcd(is, context, " + attr.static_multiplier.toString() + "); \n");
            }
            if (attr.name != null) {
                fileContent.append("        comp.set" + StringUtils.capitalize(attr.name) + "(" + attr.syntheticName + "); \n");
            }
            if (attr.contextName != null) {
                fileContent.append("        context.variables.put(\"" + attr.contextName + "\", Integer.getInteger(" + attr.syntheticName + "));\n");
            }
        }
    }

}
