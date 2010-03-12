/* ***************************************************************************
 * DynamicFileParser
 * (c) 2009 by 
 * Andreas Hoefler (kosi2801 et gmail)
 * Part of a project for the class at the USoASc FH Joanneum - SWD07
 ****************************************************************************/

package at.fhj.swd07.contours.exception;

public class FileIOGenerationException extends GenerationException {

    private static final long serialVersionUID = 2614639509511723986L;

    /**
     * @param error 
     */
    public FileIOGenerationException(String error) {
        super("File IO error. " + error, -1, -1);
    }
}
