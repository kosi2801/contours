package at.fhj.swd07.contours;

import static org.junit.Assert.assertTrue;

import java.io.*;

import org.junit.Test;

import at.fhj.swd07.contours.*;
import at.fhj.swd07.contours.lexer.*;
import at.fhj.swd07.contours.node.Start;
import at.fhj.swd07.contours.parser.*;


public class ParserGeneratorTest {

    private static final String RESOURCE_PATH = "src/test/resources/";
    private static final String correctDef = RESOURCE_PATH + "correct_definition.def";

    @Test
    public void testParseOk() throws Exception {
        ParserGenerator pGen = createParserGenerator(correctDef);
        
        pGen.generateParser();
        
        assertTrue((new File("src-gen/windows/executable/FileParser.java").exists()));
        assertTrue((new File("src-gen/windows/executable/parser/ParseContext.java").exists()));
        assertTrue((new File("src-gen/windows/executable/parser/after_header_structParser.java").exists()));
        assertTrue((new File("src-gen/windows/executable/parser/component_aParser.java").exists()));
        assertTrue((new File("src-gen/windows/executable/parser/eof_componentParser.java").exists()));
        assertTrue((new File("src-gen/windows/executable/parser/file_endParser.java").exists()));
        assertTrue((new File("src-gen/windows/executable/exception/ParseException.java").exists()));
    }
    
    private ParserGenerator createParserGenerator(String correctDef) throws FileNotFoundException, ParserException, LexerException, IOException {
        Start ast = createAST(correctDef);

        // check program semantics
        DataStructureGenerator dsGen = new DataStructureGenerator();
        ast.apply(dsGen);
        
        assertTrue((new File("src-gen/windows/executable/ds/after_header_struct.java").exists()));
        assertTrue((new File("src-gen/windows/executable/ds/component_a.java").exists()));
        assertTrue((new File("src-gen/windows/executable/ds/eof_component.java").exists()));
        assertTrue((new File("src-gen/windows/executable/ds/file_end.java").exists()));
        
        ParserGenerator pGen = new ParserGenerator(dsGen.getClasses());
        ast.apply(pGen);               
        
        return pGen;
    }
    
    private Start createAST(String correctDef) throws FileNotFoundException, ParserException, LexerException, IOException {
        // create lexer
        final Lexer lexer = createLexer(correctDef);

        // parser program
        final Parser parser = new Parser(lexer);

        return parser.parse();
    }

    private Lexer createLexer(final String filename) throws FileNotFoundException {
        final Lexer lexer = new Lexer (new PushbackReader(new BufferedReader(new FileReader(filename)), 1024));
        return lexer;
    }
}
