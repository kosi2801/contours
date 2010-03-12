package test.datatypes;

import static org.junit.Assert.assertTrue;

import java.io.*;

import org.junit.Test;

import at.fhj.swd07.contours.*;
import at.fhj.swd07.contours.lexer.*;
import at.fhj.swd07.contours.node.Start;
import at.fhj.swd07.contours.parser.*;


public class GenerateParserWithAllDatatypesTest {

    private static final String RESOURCE_PATH = "src/test/resources/";
    private static final String correctDef = RESOURCE_PATH + "all_datatypes_definition.def";

    @Test
    public void testParseOk() throws Exception {
        ParserGenerator pGen = createParserGenerator(correctDef);
        
        pGen.generateParser();
        
        assertTrue((new File("src-gen/test/datatypes/FileParser.java").exists()));
        assertTrue((new File("src-gen/test/datatypes/exception/ParseException.java").exists()));
        assertTrue((new File("src-gen/test/datatypes/parser/ParseContext.java").exists()));
        assertTrue((new File("src-gen/test/datatypes/parser/ParseUtil.java").exists()));
        assertTrue((new File("src-gen/test/datatypes/parser/start_compParser.java").exists()));
        assertTrue((new File("src-gen/test/datatypes/parser/single_instancesParser.java").exists()));
        assertTrue((new File("src-gen/test/datatypes/parser/static_instancesParser.java").exists()));
        assertTrue((new File("src-gen/test/datatypes/parser/dynamic_instancesParser.java").exists()));
        assertTrue((new File("src-gen/test/datatypes/parser/comp_bParser.java").exists()));
    }
    
    private ParserGenerator createParserGenerator(String correctDef) throws FileNotFoundException, ParserException, LexerException, IOException {
        Start ast = createAST(correctDef);

        // check program semantics
        DataStructureGenerator dsGen = new DataStructureGenerator();
        ast.apply(dsGen);
        
        assertTrue((new File("src-gen/test/datatypes/ds/start_comp.java").exists()));
        assertTrue((new File("src-gen/test/datatypes/ds/single_instances.java").exists()));
        assertTrue((new File("src-gen/test/datatypes/ds/static_instances.java").exists()));
        assertTrue((new File("src-gen/test/datatypes/ds/dynamic_instances.java").exists()));
        assertTrue((new File("src-gen/test/datatypes/ds/comp_b.java").exists()));
        
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
