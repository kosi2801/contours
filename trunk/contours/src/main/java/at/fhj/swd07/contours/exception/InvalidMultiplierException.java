/* ***************************************************************************
 * DynamicFileParser
 * (c) 2009 by 
 * Andreas Hoefler (kosi2801 et gmail)
 * Part of a project for the class at the USoASc FH Joanneum - SWD07
 ****************************************************************************/

package at.fhj.swd07.contours.exception;

public class InvalidMultiplierException extends SemanticException {

    /**
     */
    private static final long serialVersionUID = -2170180014393747776L;

    public InvalidMultiplierException(String identifier, String component,  int line, int pos) {
        super("The multiplier '" + identifier + "' in component '" + component + "' is not a valid multiplicity value", line, pos);
    }
}
