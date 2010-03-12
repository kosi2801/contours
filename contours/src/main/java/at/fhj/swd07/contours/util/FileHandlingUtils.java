/* ***************************************************************************
 * DynamicFileParser
 * (c) 2009 by 
 * Andreas Hoefler (kosi2801 et gmail)
 * Part of a project for the class at the USoASc FH Joanneum - SWD07
 ****************************************************************************/

package at.fhj.swd07.contours.util;

import java.io.*;

import org.grlea.log.SimpleLogger;

import at.fhj.swd07.contours.exception.FileIOGenerationException;

public class FileHandlingUtils {

    private static final SimpleLogger log = new SimpleLogger(FileHandlingUtils.class);

    public static void createPath(String outputPath) {
        log.entry("createPath");
        final File outPath = new File(outputPath);
        if (!outPath.isDirectory()) {
            if (outPath.exists()) {
                throw new FileIOGenerationException("Output Path '" + outputPath + "' exists but is not a direcory.");
            }

            // Create one directory
            if (outPath.mkdirs()) {
                log.info("Directory: '" + outputPath + "' created");
            } else {
                String errorMsg = "Could not create directory '" + outputPath + "'.";
                log.error(errorMsg);
                throw new FileIOGenerationException(errorMsg);
            }
        }
        log.exit("createPath");
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value = { "RV_RETURN_VALUE_IGNORED_BAD_PRACTICE" }, justification="Fail of delete is OK if file not even present")
    public static void createFile(final String filename, final StringBuilder fileContent) {
        final File f = new File(filename);
        
        f.delete(); // delete possible already existing file
        try {
            if(f.createNewFile() != true) {
                throw new FileIOGenerationException("Error creating file '" + filename + "'. File already exists.");
            }
            final BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
    
            out.write(fileContent.toString().getBytes());
            out.close();
            log.info("File '" + filename + "' created successfully.");
        } catch (IOException e) {
            throw new FileIOGenerationException("IOException: " + e.toString());
        }
    }
}
