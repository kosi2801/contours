package at.fhj.swd07.contours.generators;

import at.fhj.swd07.contours.util.ClassAttribute;

public interface TypeGenerator {

    public void generateAttributeReaderCode(StringBuilder fileContent, ClassAttribute attr);
    public void generateDataStructureCode(StringBuilder fileContent, ClassAttribute attr);
    public void generateAttributeStaticExpectedCode(StringBuilder fileContent, ClassAttribute attr);
    
}
