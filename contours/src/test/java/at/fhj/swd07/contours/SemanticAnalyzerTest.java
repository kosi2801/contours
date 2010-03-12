package at.fhj.swd07.contours;

import static org.junit.Assert.*;

import java.io.*;
import java.util.HashSet;

import org.junit.Test;

import at.fhj.swd07.contours.SemanticAnalyzer;
import at.fhj.swd07.contours.exception.*;
import at.fhj.swd07.contours.lexer.*;
import at.fhj.swd07.contours.node.Start;
import at.fhj.swd07.contours.parser.*;

import com.google.common.collect.Sets;

public class SemanticAnalyzerTest {

    private static final String RESOURCE_PATH = "src/test/resources/";

    @Test
    public void testParseOk() throws Exception {
        String correctDef = RESOURCE_PATH + "correct_definition.def";

        Start ast = createAST(correctDef);

        // check program semantics
        SemanticAnalyzer semAn = new SemanticAnalyzer();
        ast.apply(semAn);

        HashSet<String> symbolTable = semAn.getDefinedSymbolsTable();

        assertNotNull(symbolTable);

        assertEquals(Sets.newHashSet("component_a.ahs_1", "file_end.a_byte", "file_end", "component_a", "component_a.mz_1", "component_a.byte_2",
                "component_a.mz_2", "component_a.byte_1", "component_a.hex_2", "component_a.hex_1", "after_header_struct", "component_a.ahs_2", "eof_component"), symbolTable);
    }

    @Test
    public void testParseMinimalOk() throws Exception {
        String correctDef = RESOURCE_PATH + "minimal_correct_definition.def";

        Start ast = createAST(correctDef);

        // check program semantics
        SemanticAnalyzer semAn = new SemanticAnalyzer();
        ast.apply(semAn);

        HashSet<String> symbolTable = semAn.getDefinedSymbolsTable();

        assertNotNull(symbolTable);
        assertEquals(Sets.newHashSet("comp_a"), symbolTable);
    }

    @Test(expected = DuplicateComponentDefinitionException.class)
    public void testParseDuplicateCompDef() throws Exception {
        String errorDef = RESOURCE_PATH + "duplicate_component_definition.def";

        Start ast = createAST(errorDef);

        // check program semantics
        ast.apply(new SemanticAnalyzer());
    }

    @Test(expected = DuplicateComponentElementException.class)
    public void testParseDuplicateCompElement() throws Exception {
        String errorDef = RESOURCE_PATH + "duplicate_component_element.def";

        Start ast = createAST(errorDef);

        // check program semantics
        ast.apply(new SemanticAnalyzer());
    }

    @Test(expected = UndefinedComponentException.class)
    public void testParseMissingComponentDefinition() throws Exception {
        String errorDef = RESOURCE_PATH + "missing_component_definition.def";

        Start ast = createAST(errorDef);

        // check program semantics
        ast.apply(new SemanticAnalyzer());
    }

    @Test(expected = InvalidMultiplierException.class)
    public void testParseInvalidMultiplier() throws Exception {
        String errorDef = RESOURCE_PATH + "invalid_multiplier.def";
        
        Start ast = createAST(errorDef);
        
        // check program semantics
        ast.apply(new SemanticAnalyzer());
    }
    
    @Test(expected = UnsupportedException.class)
    public void testParseInvalidContextMultiplier() throws Exception {
        String errorDef = RESOURCE_PATH + "invalid_context_multiplier.def";
        
        Start ast = createAST(errorDef);
        
        // check program semantics
        ast.apply(new SemanticAnalyzer());
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
