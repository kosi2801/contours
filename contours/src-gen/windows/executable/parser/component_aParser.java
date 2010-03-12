package windows.executable.parser;

import java.io.*;
import windows.executable.parser.ParseContext;
import windows.executable.parser.ParseUtil;
import windows.executable.exception.ParseException;
import windows.executable.ds.component_a;
import java.util.Arrays;
import windows.executable.ds.after_header_struct;
import windows.executable.parser.after_header_structParser;
import windows.executable.parser.file_endParser;

public class component_aParser { 

        // 'null' 'attr0' '1' 'STATIC_TEXT' 'MZ' 
        private final static String attr0_exp = "MZ"; 

        // 'mz_1' 'attr1' '1' 'STATIC_TEXT' 'MZ' 
        private final static String attr1_exp = "MZ"; 

        // 'mz_2' 'attr2' '2' 'STATIC_TEXT' 'MZ' 
        private final static String attr2_exp = "MZMZ"; 

        // 'null' 'attr3' '1' 'STATIC_HEX' '41' 
        private final static byte[] attr3_exp = {0x41}; 

        // 'hex_1' 'attr4' '1' 'STATIC_HEX' '42' 
        private final static byte[] attr4_exp = {0x42}; 

        // 'hex_2' 'attr5' '3' 'STATIC_HEX' '43' 
        private final static byte[] attr5_exp = {0x43,0x43,0x43}; 









    component_aParser() {
    }

    public static final component_a parse(final InputStream is, final ParseContext context) throws IOException, ParseException {
        final component_a comp = new component_a();

        // ClassAttribute[name=<null>,syntheticName=attr0,type=STATIC_TEXT,type_identifier=MZ,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr0 = ParseUtil.readBytes(is, context, 1 * 2); 
        if(!(new String(attr0)).equals(attr0_exp)) {
            throw new ParseException("Excpected content '" + attr0_exp + "' but got '" + new String(attr0) + "'.", context.position);
        }

        // ClassAttribute[name=mz_1,syntheticName=attr1,type=STATIC_TEXT,type_identifier=MZ,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr1 = ParseUtil.readBytes(is, context, 1 * 2); 
        if(!(new String(attr1)).equals(attr1_exp)) {
            throw new ParseException("Excpected content '" + attr1_exp + "' but got '" + new String(attr1) + "'.", context.position);
        }

        // ClassAttribute[name=mz_2,syntheticName=attr2,type=STATIC_TEXT,type_identifier=MZ,static_multiplier=2,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr2 = ParseUtil.readBytes(is, context, 2 * 2); 
        if(!(new String(attr2)).equals(attr2_exp)) {
            throw new ParseException("Excpected content '" + attr2_exp + "' but got '" + new String(attr2) + "'.", context.position);
        }

        // ClassAttribute[name=<null>,syntheticName=attr3,type=STATIC_HEX,type_identifier=41,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr3 = ParseUtil.readBytes(is, context, 1); 
        if (!Arrays.equals(attr3, attr3_exp)) {
            throw new ParseException("Excpected content '" + Arrays.toString(attr3_exp) + "' but got '" + Arrays.toString(attr3) + "'.", context.position);
        }

        // ClassAttribute[name=hex_1,syntheticName=attr4,type=STATIC_HEX,type_identifier=42,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr4 = ParseUtil.readBytes(is, context, 1); 
        if (!Arrays.equals(attr4, attr4_exp)) {
            throw new ParseException("Excpected content '" + Arrays.toString(attr4_exp) + "' but got '" + Arrays.toString(attr4) + "'.", context.position);
        }

        // ClassAttribute[name=hex_2,syntheticName=attr5,type=STATIC_HEX,type_identifier=43,static_multiplier=3,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr5 = ParseUtil.readBytes(is, context, 3); 
        if (!Arrays.equals(attr5, attr5_exp)) {
            throw new ParseException("Excpected content '" + Arrays.toString(attr5_exp) + "' but got '" + Arrays.toString(attr5) + "'.", context.position);
        }

        // ClassAttribute[name=<null>,syntheticName=attr6,type=COMPONENT,type_identifier=after_header_struct,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        after_header_structParser.parse(is, context); 

        // ClassAttribute[name=ahs_1,syntheticName=attr7,type=COMPONENT,type_identifier=after_header_struct,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final after_header_struct attr7 = after_header_structParser.parse(is, context); 
        comp.setAhs_1(attr7); 

        // ClassAttribute[name=ahs_2,syntheticName=attr8,type=COMPONENT,type_identifier=after_header_struct,static_multiplier=4,context_multiplier=<null>,contextName=<null>]' 
        for(int i=0; i<4;i++) { 
            after_header_struct attr8 = after_header_structParser.parse(is, context); 
            comp.getAhs_2()[i] = attr8; 
        }; 

        // ClassAttribute[name=<null>,syntheticName=attr9,type=BYTE,type_identifier=<null>,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        ParseUtil.skipBytes(is, context, 1); 

        // ClassAttribute[name=byte_1,syntheticName=attr10,type=BYTE,type_identifier=<null>,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr10 = ParseUtil.readBytes(is, context, 1); 
        comp.setByte_1( attr10[0]); 

        // ClassAttribute[name=byte_2,syntheticName=attr11,type=BYTE,type_identifier=<null>,static_multiplier=5,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr11 = ParseUtil.readBytes(is, context, 5); 
        comp.setByte_2( attr11); 

        // ClassAttribute[name=<null>,syntheticName=attr12,type=COMPONENT,type_identifier=file_end,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        file_endParser.parse(is, context); 


        return comp;
    }

};
