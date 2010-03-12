/* ***************************************************************************
 * DynamicFileParser
 * (c) 2009 by 
 * Andreas Hoefler (kosi2801 et gmail)
 * Part of a project for the class at the USoASc FH Joanneum - SWD07
 ****************************************************************************/

package at.fhj.swd07.contours.exception;

public class UnsupportedAttributeTypeException extends GenerationException {

    /**
     */
    private static final long serialVersionUID = -2170180014393747776L;

    public UnsupportedAttributeTypeException(String type, String attribute, int line, int pos) {
        this(null, type, attribute, line, pos);
    }

    public UnsupportedAttributeTypeException(Exception ex, String type, String attribute, int line, int pos) {
        super(ex, "Generation of type '" + type + "' for attribute '" + attribute + "' not implemented.", line, pos);
    }
}
