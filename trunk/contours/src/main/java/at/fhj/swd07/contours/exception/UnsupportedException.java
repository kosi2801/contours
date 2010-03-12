/* ***************************************************************************
 * DynamicFileParser
 * (c) 2009 by 
 * Andreas Hoefler (kosi2801 et gmail)
 * Part of a project for the class at the USoASc FH Joanneum - SWD07
 ****************************************************************************/

package at.fhj.swd07.contours.exception;

public class UnsupportedException extends SemanticException {

    /**
     * Generic exception for something which is possible grammar-side but not (yet?) supported.
     */
    private static final long serialVersionUID = -2170180014393747776L;

    public UnsupportedException(String identifier, int line, int pos) {
        super(identifier, line, pos);
    }
}
