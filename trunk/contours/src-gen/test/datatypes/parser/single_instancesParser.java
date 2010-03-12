package test.datatypes.parser;

import java.io.*;
import test.datatypes.parser.ParseContext;
import test.datatypes.parser.ParseUtil;
import test.datatypes.exception.ParseException;
import test.datatypes.ds.single_instances;
import test.datatypes.ds.comp_b;
import java.util.Arrays;
import test.datatypes.parser.comp_bParser;

public class single_instancesParser { 

        // 'single_intro' 'attr4' '1' 'STATIC_TEXT' 'SINGLE' 
        private final static String attr4_exp = "SINGLE"; 


        // 'single_hex' 'attr6' '1' 'STATIC_HEX' '41' 
        private final static byte[] attr6_exp = {0x41}; 






    single_instancesParser() {
    }

    public static final single_instances parse(final InputStream is, final ParseContext context) throws IOException, ParseException {
        final single_instances comp = new single_instances();

        // ClassAttribute[name=single_intro,syntheticName=attr4,type=STATIC_TEXT,type_identifier=SINGLE,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr4 = ParseUtil.readBytes(is, context, 1 * 6); 
        if(!(new String(attr4)).equals(attr4_exp)) {
            throw new ParseException("Excpected content '" + attr4_exp + "' but got '" + new String(attr4) + "'.", context.position);
        }

        // ClassAttribute[name=single_byte,syntheticName=attr5,type=BYTE,type_identifier=<null>,static_multiplier=1,context_multiplier=<null>,contextName=number_two]' 
        final byte[] attr5 = ParseUtil.readBytes(is, context, 1); 
        comp.setSingle_byte( attr5[0]); 
        context.variables.put("number_two", 0xFF & (int)attr5[0]);

        // ClassAttribute[name=single_hex,syntheticName=attr6,type=STATIC_HEX,type_identifier=41,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr6 = ParseUtil.readBytes(is, context, 1); 
        if (!Arrays.equals(attr6, attr6_exp)) {
            throw new ParseException("Excpected content '" + Arrays.toString(attr6_exp) + "' but got '" + Arrays.toString(attr6) + "'.", context.position);
        }

        // ClassAttribute[name=single_component,syntheticName=attr7,type=COMPONENT,type_identifier=comp_b,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final comp_b attr7 = comp_bParser.parse(is, context); 
        comp.setSingle_component(attr7); 

        // ClassAttribute[name=single_integer,syntheticName=attr8,type=INTEGER,type_identifier=<null>,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final Integer[] attr8 = new Integer[1]; 
        for(int i=0; i<attr8.length;i++){
            attr8[i] = ParseUtil.readInt(is, context); 
        }
        comp.setSingle_integer( attr8[0]); 

        // ClassAttribute[name=single_long,syntheticName=attr9,type=LONG,type_identifier=<null>,static_multiplier=1,context_multiplier=<null>,contextName=number_bcds]' 
        final Long[] attr9 = new Long[1]; 
        for(int i=0; i<attr9.length;i++){
            attr9[i] = ParseUtil.readLong(is, context); 
        }
        comp.setSingle_long( attr9[0]); 
        context.variables.put("number_bcds", attr9[0].intValue());

        // ClassAttribute[name=single_bcd,syntheticName=attr10,type=BCD,type_identifier=<null>,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final String attr10; 
        attr10 = ParseUtil.readBcd(is, context, 1); 
        comp.setSingle_bcd(attr10); 


        return comp;
    }

};
