package at.fhj.swd07.contours.generators;

import org.apache.commons.lang.StringUtils;

import at.fhj.swd07.contours.util.ClassAttribute;

public class StaticTextTypeGenerator extends AbstractTypeGenerator {
    
    protected Integer sizeOfDatatypeInBytes() {
        return 1;
    }
    
    protected Boolean isStaticDatatype() {
        return true;
    }

    @Override
    public void generateDataStructureCode(StringBuilder fileContent, ClassAttribute attr) {
        final MultiplicityEnum multiplicity = calculateMultiplicity(attr);
        fileContent.append(generateStaticTypeAttributeCode(multiplicity, attr.static_multiplier, "String", attr.name, "\""+attr.type_identifier+"\""));
    }
    
    @Override
    public void generateAttributeStaticExpectedCode(StringBuilder fileContent, ClassAttribute attr) {
        fileContent.append("        // '" + attr.name + "' '" + attr.syntheticName + "' '" + attr.static_multiplier + "' '" + attr.type + "' '"
                + attr.type_identifier + "' \n");
        fileContent.append("        private final static String " + attr.syntheticName + "_exp = \""
                + StringUtils.repeat(attr.type_identifier, attr.static_multiplier) + "\"; \n");
    }
    
    @Override
    public void generateAttributeReaderCode(StringBuilder fileContent, ClassAttribute attr) {
        fileContent.append("        final byte[] " + attr.syntheticName + " = ParseUtil.readBytes(is, context, " + attr.static_multiplier.toString()
                + " * " + Integer.toString(attr.type_identifier.length()) + "); \n");
        fileContent.append("        if(!(new String(" + attr.syntheticName + ")).equals(" + attr.syntheticName + "_exp)) {\n");
        fileContent.append("            throw new ParseException(\"Excpected content '\" + " + attr.syntheticName + "_exp + \"' but got '\" + new String("
                + attr.syntheticName + ") + \"'.\", context.position);\n");
        fileContent.append("        }\n");
    }

}
