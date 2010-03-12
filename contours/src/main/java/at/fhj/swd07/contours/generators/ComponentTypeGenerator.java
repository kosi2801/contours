package at.fhj.swd07.contours.generators;

import org.apache.commons.lang.StringUtils;

import at.fhj.swd07.contours.util.ClassAttribute;

public class ComponentTypeGenerator extends AbstractTypeGenerator {

    protected Integer sizeOfDatatypeInBytes() {
        return 1;
    }

    protected Boolean isStaticDatatype() {
        return false;
    }

    @Override
    public void generateDataStructureCode(StringBuilder fileContent, ClassAttribute attr) {
        final MultiplicityEnum multiplicity = calculateMultiplicity(attr);
        fileContent.append(generateBasicTypeAttributeCode(multiplicity, attr.static_multiplier, attr.type_identifier, attr.name));
    }
    
    @Override
    public void generateAttributeReaderCode(StringBuilder fileContent, ClassAttribute attr) {
        // get dynamic number of elements from context
        if (attr.context_multiplier != null) {
            fileContent.append("        final int " + attr.syntheticName + "_count = context.variables.get(\"" + attr.context_multiplier + "\"); \n");
            if (attr.name != null) {
                fileContent.append("        final " + attr.type_identifier + "[] " + attr.syntheticName + " = new " + attr.type_identifier + "["
                        + attr.syntheticName + "_count]; \n");
            }
            fileContent.append("        for(int i=0; i<" + attr.syntheticName + "_count;i++) { \n");
            if (attr.name != null) {
                fileContent.append("            " + attr.syntheticName + "[i] = " + attr.type_identifier + "Parser.parse(is, context); \n");
            } else {
                fileContent.append("            " + attr.type_identifier + "Parser.parse(is, context); \n");
            }
            fileContent.append("        }; \n");
            if (attr.name != null) {
                fileContent.append("        comp.set" + StringUtils.capitalize(attr.name) + "( " + attr.syntheticName + "); \n");
            }
        } else if ((attr.static_multiplier != null) && (attr.static_multiplier != 1)) {
            fileContent.append("        for(int i=0; i<" + attr.static_multiplier + ";i++) { \n");
            if (attr.name != null) {
                fileContent.append("            " + attr.type_identifier + " " + attr.syntheticName + " = " + attr.type_identifier
                        + "Parser.parse(is, context); \n");
                fileContent.append("            comp.get" + StringUtils.capitalize(attr.name) + "()[i] = " + attr.syntheticName + "; \n");
            } else {
                fileContent.append("            " + attr.type_identifier + "Parser.parse(is, context); \n");
            }
            fileContent.append("        }; \n");
        } else {
            if (attr.name != null) {
                fileContent.append("        final " + attr.type_identifier + " " + attr.syntheticName + " = " + attr.type_identifier
                        + "Parser.parse(is, context); \n");
                fileContent.append("        comp.set" + StringUtils.capitalize(attr.name) + "(" + attr.syntheticName + "); \n");
            } else {
                fileContent.append("        " + attr.type_identifier + "Parser.parse(is, context); \n");
            }
        }
    }

}
