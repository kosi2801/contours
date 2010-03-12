package at.fhj.swd07.contours.generators;

import org.apache.commons.lang.StringUtils;

import at.fhj.swd07.contours.util.ClassAttribute;

public class StaticHexTypeGenerator extends AbstractTypeGenerator {
    
    protected Integer sizeOfDatatypeInBytes() {
        return 1;
    }
    
    protected Boolean isStaticDatatype() {
        return true;
    }

    @Override
    public void generateDataStructureCode(StringBuilder fileContent, ClassAttribute attr) {
        final MultiplicityEnum multiplicity = calculateMultiplicity(attr);
        fileContent.append(generateStaticTypeAttributeCode(multiplicity, attr.static_multiplier, "short", attr.name, "0x"+attr.type_identifier));
    }
    
    @Override
    public void generateAttributeStaticExpectedCode(StringBuilder fileContent, ClassAttribute attr) {
        fileContent.append("        // '" + attr.name + "' '" + attr.syntheticName + "' '" + attr.static_multiplier + "' '" + attr.type + "' '"
                + attr.type_identifier + "' \n");
        fileContent.append("        private final static byte[] " + attr.syntheticName + "_exp = {"
                + StringUtils.chop(StringUtils.repeat("0x" + attr.type_identifier + ",", attr.static_multiplier)) + "}; \n");
    }
    
    @Override
    public void generateAttributeReaderCode(StringBuilder fileContent, ClassAttribute attr) {
        fileContent.append("        final byte[] " + attr.syntheticName + " = ParseUtil.readBytes(is, context, " + attr.static_multiplier.toString()
                + "); \n");
        fileContent.append("        if (!Arrays.equals(" + attr.syntheticName + ", " + attr.syntheticName + "_exp)) {\n");
        fileContent.append("            throw new ParseException(\"Excpected content '\" + Arrays.toString(" + attr.syntheticName
                + "_exp) + \"' but got '\" + Arrays.toString(" + attr.syntheticName + ") + \"'.\", context.position);\n");
        fileContent.append("        }\n");
        if (attr.contextName != null) {
            fileContent.append("        context.variables.put(\"" + attr.contextName + "\", 0x" + attr.type_identifier + "L);\n");
        }
    }

}
