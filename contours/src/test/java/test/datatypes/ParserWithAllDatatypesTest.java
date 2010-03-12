package test.datatypes;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;

import test.datatypes.ds.*;

public class ParserWithAllDatatypesTest {

    private static final String RESOURCE_PATH = "src/test/resources/";
    private static final String testTypesFile = RESOURCE_PATH + "test/datatypes/data.types";

    @Test
    public void testParseDatatypesFileOk() throws Exception {
        InputStream is = new BufferedInputStream(new FileInputStream(new File(testTypesFile)));
        FileParser parser = new FileParser(is);

        start_comp parsedStruct = parser.parse();
        single_instances singles = parsedStruct.getSingle_instances();
        static_instances statics = parsedStruct.getStatic_instances();
        dynamic_instances dynamics = parsedStruct.getDynamic_instances();
        

        assertEquals("SINGLE", singles.getSingle_intro());
        assertEquals((byte) 0x02, singles.getSingle_byte());
        assertEquals((short) 0x41, singles.getSingle_hex());
        assertNotNull(singles.getSingle_component());
        assertEquals(-2004318072, singles.getSingle_integer().intValue());
        assertEquals(0x10L, singles.getSingle_long().longValue());
        assertEquals("00", singles.getSingle_bcd());
        
        assertEquals("STATIC", statics.getStatic_intro());
        assertArrayEquals(new String[]{"mz", "mz"}, statics.getStatic_string());
        assertEquals(0x42, statics.getStatic_hex()[0]);
        assertEquals(0x42, statics.getStatic_hex()[1]);
        assertArrayEquals(new byte[]{0x00, 0x11}, statics.getStatic_byte());        
        assertNotNull(statics.getStatic_component()[0]);
        assertNotNull(statics.getStatic_component()[1]);
        assertEquals(new Integer(0x55555555), statics.getStatic_integer()[0]);
        assertEquals(new Integer(0x66666666), statics.getStatic_integer()[1]);
        assertEquals(new Long(0x7777777777777777L), statics.getStatic_long()[0]);
        assertEquals(new Long(0x8888888888888888L), statics.getStatic_long()[1]);
        assertEquals("9900", statics.getStatic_bcd());
        
        assertEquals("DYNAMIC", dynamics.getDynamic_intro());
        assertEquals("mz", dynamics.getDynamic_string());
        assertEquals(0x53, dynamics.getDynamic_hex());
        assertEquals(2, dynamics.getDynamic_byte().length);
        assertEquals(0x33, dynamics.getDynamic_byte()[0]);
        assertEquals(0x44, dynamics.getDynamic_byte()[1]);
        assertEquals(2, dynamics.getDynamic_component().length);
        assertNotNull(dynamics.getDynamic_component()[0]);
        assertNotNull(dynamics.getDynamic_component()[1]);
        assertEquals(2, dynamics.getDynamic_integer().length);
        assertEquals(new Integer(0x77777777), dynamics.getDynamic_integer()[0]);
        assertEquals(new Integer(0x88888888), dynamics.getDynamic_integer()[1]);
        assertEquals(2, dynamics.getDynamic_long().length);
        assertEquals(new Long(0x9999999999999999L), dynamics.getDynamic_long()[0]);
        assertEquals(new Long(0xAAAAAAAAAAAAAAAAL), dynamics.getDynamic_long()[1]);
        assertEquals("00112233445566778899001122334455", dynamics.getDynamic_bcd());
    }
}
