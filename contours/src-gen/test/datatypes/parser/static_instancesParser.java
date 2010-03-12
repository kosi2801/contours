package test.datatypes.parser;

import java.io.*;
import test.datatypes.parser.ParseContext;
import test.datatypes.parser.ParseUtil;
import test.datatypes.exception.ParseException;
import test.datatypes.ds.static_instances;
import test.datatypes.ds.comp_b;
import java.util.Arrays;
import test.datatypes.parser.comp_bParser;

public class static_instancesParser { 

        // 'static_intro' 'attr11' '1' 'STATIC_TEXT' 'STATIC' 
        private final static String attr11_exp = "STATIC"; 

        // 'static_string' 'attr12' '2' 'STATIC_TEXT' 'mz' 
        private final static String attr12_exp = "mzmz"; 

        // 'static_hex' 'attr13' '2' 'STATIC_HEX' '42' 
        private final static byte[] attr13_exp = {0x42,0x42}; 







    static_instancesParser() {
    }

    public static final static_instances parse(final InputStream is, final ParseContext context) throws IOException, ParseException {
        final static_instances comp = new static_instances();

        // ClassAttribute[name=static_intro,syntheticName=attr11,type=STATIC_TEXT,type_identifier=STATIC,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr11 = ParseUtil.readBytes(is, context, 1 * 6); 
        if(!(new String(attr11)).equals(attr11_exp)) {
            throw new ParseException("Excpected content '" + attr11_exp + "' but got '" + new String(attr11) + "'.", context.position);
        }

        // ClassAttribute[name=static_string,syntheticName=attr12,type=STATIC_TEXT,type_identifier=mz,static_multiplier=2,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr12 = ParseUtil.readBytes(is, context, 2 * 2); 
        if(!(new String(attr12)).equals(attr12_exp)) {
            throw new ParseException("Excpected content '" + attr12_exp + "' but got '" + new String(attr12) + "'.", context.position);
        }

        // ClassAttribute[name=static_hex,syntheticName=attr13,type=STATIC_HEX,type_identifier=42,static_multiplier=2,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr13 = ParseUtil.readBytes(is, context, 2); 
        if (!Arrays.equals(attr13, attr13_exp)) {
            throw new ParseException("Excpected content '" + Arrays.toString(attr13_exp) + "' but got '" + Arrays.toString(attr13) + "'.", context.position);
        }

        // ClassAttribute[name=static_byte,syntheticName=attr14,type=BYTE,type_identifier=<null>,static_multiplier=2,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr14 = ParseUtil.readBytes(is, context, 2); 
        comp.setStatic_byte( attr14); 

        // ClassAttribute[name=static_component,syntheticName=attr15,type=COMPONENT,type_identifier=comp_b,static_multiplier=2,context_multiplier=<null>,contextName=<null>]' 
        for(int i=0; i<2;i++) { 
            comp_b attr15 = comp_bParser.parse(is, context); 
            comp.getStatic_component()[i] = attr15; 
        }; 

        // ClassAttribute[name=static_integer,syntheticName=attr16,type=INTEGER,type_identifier=<null>,static_multiplier=2,context_multiplier=<null>,contextName=<null>]' 
        final Integer[] attr16 = new Integer[2]; 
        for(int i=0; i<attr16.length;i++){
            attr16[i] = ParseUtil.readInt(is, context); 
        }
        comp.setStatic_integer( attr16); 

        // ClassAttribute[name=static_long,syntheticName=attr17,type=LONG,type_identifier=<null>,static_multiplier=2,context_multiplier=<null>,contextName=<null>]' 
        final Long[] attr17 = new Long[2]; 
        for(int i=0; i<attr17.length;i++){
            attr17[i] = ParseUtil.readLong(is, context); 
        }
        comp.setStatic_long( attr17); 

        // ClassAttribute[name=static_bcd,syntheticName=attr18,type=BCD,type_identifier=<null>,static_multiplier=2,context_multiplier=<null>,contextName=<null>]' 
        final String attr18; 
        attr18 = ParseUtil.readBcd(is, context, 2); 
        comp.setStatic_bcd(attr18); 


        return comp;
    }

};
