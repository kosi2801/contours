package test.lob;

import static org.junit.Assert.assertTrue;

import java.io.*;

import org.junit.Test;

import at.fhj.swd07.contours.*;
import at.fhj.swd07.contours.lexer.*;
import at.fhj.swd07.contours.node.Start;
import at.fhj.swd07.contours.parser.*;


public class GenerateParserWithContextGeneratorTest {

    private static final String RESOURCE_PATH = "src/test/resources/";
    private static final String correctDef = RESOURCE_PATH + "correct_context_definition.def";

    @Test
    public void testParseOk() throws Exception {
        ParserGenerator pGen = createParserGenerator(correctDef);
        
        pGen.generateParser();
        
        assertTrue((new File("src-gen/test/lob/FileParser.java").exists()));
        assertTrue((new File("src-gen/test/lob/exception/ParseException.java").exists()));
        assertTrue((new File("src-gen/test/lob/parser/ParseContext.java").exists()));
        assertTrue((new File("src-gen/test/lob/parser/lobParser.java").exists()));
        assertTrue((new File("src-gen/test/lob/parser/headerParser.java").exists()));
        assertTrue((new File("src-gen/test/lob/parser/bodyParser.java").exists()));
        assertTrue((new File("src-gen/test/lob/parser/lineParser.java").exists()));
    }
    
    private ParserGenerator createParserGenerator(String correctDef) throws FileNotFoundException, ParserException, LexerException, IOException {
        Start ast = createAST(correctDef);

        // check program semantics
        ast.apply(new SemanticAnalyzer());
        
        // generate data structures
        DataStructureGenerator dsGen = new DataStructureGenerator();
        ast.apply(dsGen);
        
        assertTrue((new File("src-gen/test/lob/ds/lob.java").exists()));
        assertTrue((new File("src-gen/test/lob/ds/header.java").exists()));
        assertTrue((new File("src-gen/test/lob/ds/body.java").exists()));
        assertTrue((new File("src-gen/test/lob/ds/line.java").exists()));
        
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
