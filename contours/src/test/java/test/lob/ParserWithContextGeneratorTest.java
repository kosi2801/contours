package test.lob;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;

import test.lob.FileParser;
import test.lob.ds.lob;


public class ParserWithContextGeneratorTest {

    private static final String RESOURCE_PATH = "src/test/resources/";
    private static final String testLobFile = RESOURCE_PATH + "test/lob/binary.lob";

    @Test
    public void testParseLobFileOk() throws Exception {
        InputStream is = new BufferedInputStream(new FileInputStream(new File(testLobFile)));
        FileParser parser = new FileParser(is);

        lob parsedStruct = parser.parse();
        assertEquals(0xFF, parsedStruct.getHeader().getNumber() &0xFF); // need to mask with 0xFF to convert it to integer and get unsigned value of byte
        assertEquals(0x04, parsedStruct.getHeader().getHeight() &0xFF);
        assertEquals(0x03, parsedStruct.getHeader().getWidth() &0xFF);
        assertArrayEquals(new byte[] { 0x11, 0x11, 0x11 }, parsedStruct.getBody().getBlocks()[0].getLines()[0].getBytes());
        assertArrayEquals(new byte[] { 0x22, 0x22, 0x22 }, parsedStruct.getBody().getBlocks()[0].getLines()[1].getBytes());
        assertArrayEquals(new byte[] { 0x33, 0x33, 0x33 }, parsedStruct.getBody().getBlocks()[0].getLines()[2].getBytes());
    }
}
