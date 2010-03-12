package test.datatypes.parser;

import java.io.*;
import test.datatypes.parser.ParseContext;
import test.datatypes.parser.ParseUtil;
import test.datatypes.exception.ParseException;
import test.datatypes.ds.dynamic_instances;
import test.datatypes.ds.comp_b;
import java.util.Arrays;
import test.datatypes.parser.comp_bParser;

public class dynamic_instancesParser { 

        // 'dynamic_intro' 'attr19' '1' 'STATIC_TEXT' 'DYNAMIC' 
        private final static String attr19_exp = "DYNAMIC"; 

        // 'dynamic_string' 'attr20' '1' 'STATIC_TEXT' 'mz' 
        private final static String attr20_exp = "mz"; 

        // 'dynamic_hex' 'attr21' '1' 'STATIC_HEX' '53' 
        private final static byte[] attr21_exp = {0x53}; 







    dynamic_instancesParser() {
    }

    public static final dynamic_instances parse(final InputStream is, final ParseContext context) throws IOException, ParseException {
        final dynamic_instances comp = new dynamic_instances();

        // ClassAttribute[name=dynamic_intro,syntheticName=attr19,type=STATIC_TEXT,type_identifier=DYNAMIC,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr19 = ParseUtil.readBytes(is, context, 1 * 7); 
        if(!(new String(attr19)).equals(attr19_exp)) {
            throw new ParseException("Excpected content '" + attr19_exp + "' but got '" + new String(attr19) + "'.", context.position);
        }

        // ClassAttribute[name=dynamic_string,syntheticName=attr20,type=STATIC_TEXT,type_identifier=mz,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr20 = ParseUtil.readBytes(is, context, 1 * 2); 
        if(!(new String(attr20)).equals(attr20_exp)) {
            throw new ParseException("Excpected content '" + attr20_exp + "' but got '" + new String(attr20) + "'.", context.position);
        }

        // ClassAttribute[name=dynamic_hex,syntheticName=attr21,type=STATIC_HEX,type_identifier=53,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr21 = ParseUtil.readBytes(is, context, 1); 
        if (!Arrays.equals(attr21, attr21_exp)) {
            throw new ParseException("Excpected content '" + Arrays.toString(attr21_exp) + "' but got '" + Arrays.toString(attr21) + "'.", context.position);
        }

        // ClassAttribute[name=dynamic_byte,syntheticName=attr22,type=BYTE,type_identifier=<null>,static_multiplier=<null>,context_multiplier=number_two,contextName=<null>]' 
        final byte[] attr22 = ParseUtil.readBytes(is, context, context.variables.get("number_two")); 
        comp.setDynamic_byte( attr22); 

        // ClassAttribute[name=dynamic_component,syntheticName=attr23,type=COMPONENT,type_identifier=comp_b,static_multiplier=<null>,context_multiplier=number_two,contextName=<null>]' 
        final int attr23_count = context.variables.get("number_two"); 
        final comp_b[] attr23 = new comp_b[attr23_count]; 
        for(int i=0; i<attr23_count;i++) { 
            attr23[i] = comp_bParser.parse(is, context); 
        }; 
        comp.setDynamic_component( attr23); 

        // ClassAttribute[name=dynamic_integer,syntheticName=attr24,type=INTEGER,type_identifier=<null>,static_multiplier=<null>,context_multiplier=number_two,contextName=<null>]' 
        final Integer[] attr24 = new Integer[context.variables.get("number_two")]; 
        for(int i=0; i<attr24.length;i++){
            attr24[i] = ParseUtil.readInt(is, context); 
        }
        comp.setDynamic_integer( attr24); 

        // ClassAttribute[name=dynamic_long,syntheticName=attr25,type=LONG,type_identifier=<null>,static_multiplier=<null>,context_multiplier=number_two,contextName=<null>]' 
        final Long[] attr25 = new Long[context.variables.get("number_two")]; 
        for(int i=0; i<attr25.length;i++){
            attr25[i] = ParseUtil.readLong(is, context); 
        }
        comp.setDynamic_long( attr25); 

        // ClassAttribute[name=dynamic_bcd,syntheticName=attr26,type=BCD,type_identifier=<null>,static_multiplier=<null>,context_multiplier=number_bcds,contextName=<null>]' 
        final String attr26; 
        attr26 = ParseUtil.readBcd(is, context, context.variables.get("number_bcds")); 
        comp.setDynamic_bcd(attr26); 


        return comp;
    }

};
