package at.fhj.swd07.contours.generators;

import at.fhj.swd07.contours.util.ClassAttribute;

public class EofTypeGenerator extends AbstractTypeGenerator {

    protected Integer sizeOfDatatypeInBytes() {
        return 0;
    }

    protected Boolean isStaticDatatype() {
        return true;
    }

    @Override
    public void generateAttributeReaderCode(StringBuilder fileContent, ClassAttribute attr) {
        fileContent.append("        if(is.available() > 0) {\n");
        fileContent.append("            throw new ParseException(\"Excpected EOF but input stream is not empty.\", context.position);\n");
        fileContent.append("        }\n");
    }

}
