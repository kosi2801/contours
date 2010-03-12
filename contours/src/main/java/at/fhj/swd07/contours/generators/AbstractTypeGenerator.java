package at.fhj.swd07.contours.generators;

import org.apache.commons.lang.StringUtils;

import at.fhj.swd07.contours.exception.UnsupportedException;
import at.fhj.swd07.contours.util.ClassAttribute;

public abstract class AbstractTypeGenerator implements TypeGenerator {

    /**
     * Tells, if this datatype contains a static value, which just has to be
     * checked for presence in the file. i.e. STATIC_TEXT or STATIC_HEX.
     * 
     * @return
     */
    protected abstract Boolean isStaticDatatype();

    protected abstract Integer sizeOfDatatypeInBytes();
    
    public void generateAttributeReaderCode(StringBuilder fileContent, ClassAttribute attr) {
    }

    public void generateDataStructureCode(StringBuilder fileContent, ClassAttribute attr) {
    }

    public void generateAttributeStaticExpectedCode(StringBuilder fileContent, ClassAttribute attr) {
    }
    
    protected enum MultiplicityEnum {
        ONCE, STATIC, CONTEXT;
    };
    
    protected MultiplicityEnum calculateMultiplicity(ClassAttribute attr) {
        MultiplicityEnum multiplicity = MultiplicityEnum.ONCE;
        if (attr.static_multiplier != null) {
            multiplicity = MultiplicityEnum.STATIC;
        } else if (attr.context_multiplier != null) {
            multiplicity = MultiplicityEnum.CONTEXT;
        }
        return multiplicity;
    }
    
    protected String generateStaticTypeAttributeCode(MultiplicityEnum multiplicity, Integer staticMultiplier, String datatype, String attributeName, String value) {
        final StringBuilder str = new StringBuilder();
        switch (multiplicity) {
        case ONCE:
            str.append("  private final static "+datatype+" " + attributeName + " = " + value + ";\n");
            str.append("  public final "+datatype+" get" + StringUtils.capitalize(attributeName) + "() { return " + attributeName + ";}");
            break;
        case STATIC:
            str.append("  private final static "+datatype+"[] " + attributeName + " = {");
            str.append(StringUtils.repeat(value+",", staticMultiplier));
            str.deleteCharAt(str.length() - 1);
            str.append("};\n");
            str.append("  public final "+datatype+"[] get" + StringUtils.capitalize(attributeName) + "() { return " + attributeName + ";}");
            break;
        case CONTEXT:
            throw new UnsupportedException("Context multiplier for this STATIC not yet supported.", -1, -1);
        }
        return str.toString();
    }
    
    protected String generateBasicTypeAttributeCode(MultiplicityEnum multiplicity, Integer staticMultiplier, String datatype, String attributeName) {
        final StringBuilder str = new StringBuilder();
        switch (multiplicity) {
        case ONCE:
            str.append("  private " + datatype + " " + attributeName + ";\n");
            str.append("  public " + datatype + " get" + StringUtils.capitalize(attributeName) + "() { return " + attributeName + ";}\n");
            str.append("  public void set" + StringUtils.capitalize(attributeName) + "(" + datatype + " attr) { this." + attributeName + " = attr;}");
            break;
        case STATIC:
            str.append("  private " + datatype + "[] " + attributeName + " = new " + datatype + "[" + staticMultiplier + "];\n");
            str.append("  public " + datatype + "[] get" + StringUtils.capitalize(attributeName) + "() { return " + attributeName + ";}\n");
            str.append("  public void set" + StringUtils.capitalize(attributeName) + "(" + datatype + "[] attr) { this." + attributeName + " = attr;}");
            break;
        case CONTEXT:
            str.append("  private " + datatype + "[] " + attributeName + ";\n");
            str.append("  public " + datatype + "[] get" + StringUtils.capitalize(attributeName) + "() { return " + attributeName + ";}\n");
            str.append("  public void set" + StringUtils.capitalize(attributeName) + "(" + datatype + "[] attr) { this." + attributeName + " = attr;}");
            break;
        }
        return str.toString();
    }
    
}
