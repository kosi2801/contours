/* ***************************************************************************
 * DynamicFileParser
 * (c) 2009 by 
 * Andreas Hoefler (kosi2801 et gmail)
 * Part of a project for the class at the USoASc FH Joanneum - SWD07
 ****************************************************************************/

package at.fhj.swd07.contours.exception;

public class SemanticException extends RuntimeException {
    /**
     */
    private static final long serialVersionUID = 8516410163885321856L;

    String errorMessage;
    int line=0;
    int pos=0;
    
    protected SemanticException(String errorMessage, int line, int pos) {
        this.errorMessage = errorMessage;
        this.line = line;
        this.pos = pos;
    }

    public String toString() {
        return "ERROR [" + line + "," + pos + "] " + errorMessage;
    }
    
}
