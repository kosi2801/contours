package windows.executable;

import static org.junit.Assert.*;

import java.io.*;
import java.util.Arrays;

import org.junit.Test;

import windows.executable.ds.component_a;
import windows.executable.exception.ParseException;

public class WindowsExecutableParserTest {

    private static final String RESOURCE_PATH = "src/test/resources/windows/executable/";
    private static final String winExeFile = RESOURCE_PATH + "windowsExecutable.tst";

    @Test
    public void testParseWindowExeFileOk() throws Exception {
        InputStream is = new BufferedInputStream(new FileInputStream(new File(winExeFile)));
        FileParser parser = new FileParser(is);

        component_a parsedStruct = parser.parse();
        assertEquals("MZ", parsedStruct.getMz_1());
        assertArrayEquals(new String[] { "MZ", "MZ" }, parsedStruct.getMz_2());
        assertEquals((short)0x42, parsedStruct.getHex_1());
        assertArrayEquals(new short[] { 0x43, 0x43, 0x43 }, parsedStruct.getHex_2());
        assertNotNull(parsedStruct.getAhs_1());
        assertNotNull(parsedStruct.getAhs_2());
        assertEquals(4, parsedStruct.getAhs_2().length);
        assertNotNull(parsedStruct.getAhs_2()[0]);
        assertNotNull(parsedStruct.getAhs_2()[2]);
        assertNotNull(parsedStruct.getAhs_2()[1]);
        assertNotNull(parsedStruct.getAhs_2()[3]);
        assertEquals((byte) 'b', parsedStruct.getByte_1());
        assertArrayEquals(new byte[] { 'c', 'd', 'e', 'f', 'g' }, parsedStruct.getByte_2());
    }

    @Test
    public void testParseWindowExeFileEOFExpected() throws Exception {
        byte[] fileBytesArray = getBytesFromFile(new File(winExeFile));
        
        // copy array and add one byte at the end
        byte[] oversizedArray = Arrays.copyOf(fileBytesArray, fileBytesArray.length + 1);
        oversizedArray[oversizedArray.length - 1] = 0x00;

        InputStream is = new ByteArrayInputStream(oversizedArray);
        FileParser parser = new FileParser(is);
        try {
            parser.parse();
            fail("Exception for missing EOF expected");
        } catch (ParseException e) {
            assertEquals("Excpected EOF but input stream is not empty. [pos:69]", e.getMessage());
        }

    }

    @Test
    public void testParseWindowExeFileWrongStaticString() throws Exception {
        byte[] fileBytesArray = getBytesFromFile(new File(winExeFile));
        
        // change some expected static string
        fileBytesArray[1] = 'Y'; // was 'Z' before
        
        InputStream is = new ByteArrayInputStream(fileBytesArray);
        FileParser parser = new FileParser(is);
        try {
            parser.parse();
            fail("Exception for missing EOF expected");
        } catch (ParseException e) {
            assertEquals("Excpected content 'MZ' but got 'MY'. [pos:2]", e.getMessage());
        }
        
    }
    
    @Test
    public void testParseWindowExeFileWrongHexValue() throws Exception {
        byte[] fileBytesArray = getBytesFromFile(new File(winExeFile));
        
        // change some expected hex value
        fileBytesArray[12] = 0x44; // was 0x43 before
        
        InputStream is = new ByteArrayInputStream(fileBytesArray);
        FileParser parser = new FileParser(is);
        try {
            parser.parse();
            fail("Exception for missing EOF expected");
        } catch (ParseException e) {
            assertEquals("Excpected content '[67, 67, 67]' but got '[67, 67, 68]'. [pos:13]", e.getMessage());
        }
        
    }
    
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(file));

        // Get the size of the file
        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
}