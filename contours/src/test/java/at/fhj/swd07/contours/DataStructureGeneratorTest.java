package at.fhj.swd07.contours;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;

import at.fhj.swd07.contours.DataStructureGenerator;
import at.fhj.swd07.contours.lexer.*;
import at.fhj.swd07.contours.node.Start;
import at.fhj.swd07.contours.parser.*;

public class DataStructureGeneratorTest {

    private static final String RESOURCE_PATH = "src/test/resources/";
    private static final String correctDef = RESOURCE_PATH + "correct_definition.def";

    @Test
    public void testParseOk() throws Exception {
        createDataStructures(correctDef);

        assertTrue((new File("src-gen/windows/executable/ds/component_a.java").exists()));
        assertTrue((new File("src-gen/windows/executable/ds/after_header_struct.java").exists()));
        assertTrue((new File("src-gen/windows/executable/ds/file_end.java").exists()));
    }

    @Test
    public void testClassComponent_aOk() throws Exception {
        createDataStructures(correctDef);
        File file = new File("src-gen/windows/executable/ds/component_a.java");
        assertTrue((file.exists()));

        BufferedReader fc = new BufferedReader(new FileReader(file));
        try {
            assertEquals("package windows.executable.ds;", fc.readLine());
            assertEquals("", fc.readLine());
            assertEquals("public class component_a { ", fc.readLine());
            assertEquals("  private final static String mz_1 = \"MZ\";", fc.readLine());
            assertEquals("  public final String getMz_1() { return mz_1;}", fc.readLine());
            assertEquals("", fc.readLine());
            assertEquals("  private final static String[] mz_2 = {\"MZ\",\"MZ\"};", fc.readLine());
            assertEquals("  public final String[] getMz_2() { return mz_2;}", fc.readLine());
            assertEquals("", fc.readLine());
            assertEquals("  private final static short hex_1 = 0x42;", fc.readLine());
            assertEquals("  public final short getHex_1() { return hex_1;}", fc.readLine());
            assertEquals("", fc.readLine());
            assertEquals("  private final static short[] hex_2 = {0x43,0x43,0x43};", fc.readLine());
            assertEquals("  public final short[] getHex_2() { return hex_2;}", fc.readLine());
            assertEquals("", fc.readLine());
            assertEquals("  private after_header_struct ahs_1;", fc.readLine());
            assertEquals("  public after_header_struct getAhs_1() { return ahs_1;}", fc.readLine());
            assertEquals("  public void setAhs_1(after_header_struct attr) { this.ahs_1 = attr;}", fc.readLine());
            assertEquals("", fc.readLine());
            assertEquals("  private after_header_struct[] ahs_2 = new after_header_struct[4];", fc.readLine());
            assertEquals("  public after_header_struct[] getAhs_2() { return ahs_2;}", fc.readLine());
            assertEquals("  public void setAhs_2(after_header_struct[] attr) { this.ahs_2 = attr;}", fc.readLine());
            assertEquals("", fc.readLine());
            assertEquals("  private byte byte_1;", fc.readLine());
            assertEquals("  public byte getByte_1() { return byte_1;}", fc.readLine());
            assertEquals("  public void setByte_1(byte attr) { this.byte_1 = attr;}", fc.readLine());
            assertEquals("", fc.readLine());
            assertEquals("  private byte[] byte_2 = new byte[5];", fc.readLine());
            assertEquals("  public byte[] getByte_2() { return byte_2;}", fc.readLine());
            assertEquals("  public void setByte_2(byte[] attr) { this.byte_2 = attr;}", fc.readLine());
            assertEquals("", fc.readLine());
            assertEquals("};", fc.readLine());
            assertEquals(null, fc.readLine());
        } finally {
            fc.close();
        }
    }

    @Test
    public void testClassAfter_header_structOk() throws Exception {
        createDataStructures(correctDef);
        File file = new File("src-gen/windows/executable/ds/after_header_struct.java");
        assertTrue((file.exists()));

        BufferedReader fc = new BufferedReader(new FileReader(file));
        try {
            assertEquals("package windows.executable.ds;", fc.readLine());
            assertEquals("", fc.readLine());
            assertEquals("public class after_header_struct { ", fc.readLine());
            assertEquals("};", fc.readLine());
            assertEquals(null, fc.readLine());
        } finally {
            fc.close();
        }

    }

    @Test
    public void testClassFile_endOk() throws Exception {
        createDataStructures(correctDef);
        File file = new File("src-gen/windows/executable/ds/file_end.java");
        assertTrue((file.exists()));

        BufferedReader fc = new BufferedReader(new FileReader(file));
        try {
            assertEquals("package windows.executable.ds;", fc.readLine());
            assertEquals("", fc.readLine());
            assertEquals("public class file_end { ", fc.readLine());
            assertEquals("  private byte a_byte;", fc.readLine());
            assertEquals("  public byte getA_byte() { return a_byte;}", fc.readLine());
            assertEquals("  public void setA_byte(byte attr) { this.a_byte = attr;}", fc.readLine());
            assertEquals("", fc.readLine());
            assertEquals("};", fc.readLine());
            assertEquals(null, fc.readLine());
        } finally {
            fc.close();
        }
    }

    private void createDataStructures(String correctDef) throws FileNotFoundException, ParserException, LexerException, IOException {
        Start ast = createAST(correctDef);

        // check program semantics
        DataStructureGenerator dsGen = new DataStructureGenerator();
        ast.apply(dsGen);
    }

    private Start createAST(String correctDef) throws FileNotFoundException, ParserException, LexerException, IOException {
        // create lexer
        final Lexer lexer = createLexer(correctDef);

        // parser program
        final Parser parser = new Parser(lexer);

        return parser.parse();
    }

    private Lexer createLexer(final String filename) throws FileNotFoundException {
        final Lexer lexer = new Lexer(new PushbackReader(new BufferedReader(new FileReader(filename)), 1024));
        return lexer;
    }
}
