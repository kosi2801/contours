package test.lob.parser;

import java.io.*;
import test.lob.parser.ParseContext;
import test.lob.parser.ParseUtil;
import test.lob.exception.ParseException;
import test.lob.ds.header;

public class headerParser { 

        // 'null' 'attr2' '1' 'STATIC_TEXT' 'LOB' 
        private final static String attr2_exp = "LOB"; 





    headerParser() {
    }

    public static final header parse(final InputStream is, final ParseContext context) throws IOException, ParseException {
        final header comp = new header();

        // ClassAttribute[name=<null>,syntheticName=attr2,type=STATIC_TEXT,type_identifier=LOB,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr2 = ParseUtil.readBytes(is, context, 1 * 3); 
        if(!(new String(attr2)).equals(attr2_exp)) {
            throw new ParseException("Excpected content '" + attr2_exp + "' but got '" + new String(attr2) + "'.", context.position);
        }

        // ClassAttribute[name=number,syntheticName=attr3,type=BYTE,type_identifier=<null>,static_multiplier=1,context_multiplier=<null>,contextName=number]' 
        final byte[] attr3 = ParseUtil.readBytes(is, context, 1); 
        comp.setNumber( attr3[0]); 
        context.variables.put("number", 0xFF & (int)attr3[0]);

        // ClassAttribute[name=width,syntheticName=attr4,type=BYTE,type_identifier=<null>,static_multiplier=1,context_multiplier=<null>,contextName=width]' 
        final byte[] attr4 = ParseUtil.readBytes(is, context, 1); 
        comp.setWidth( attr4[0]); 
        context.variables.put("width", 0xFF & (int)attr4[0]);

        // ClassAttribute[name=height,syntheticName=attr5,type=BYTE,type_identifier=<null>,static_multiplier=1,context_multiplier=<null>,contextName=height]' 
        final byte[] attr5 = ParseUtil.readBytes(is, context, 1); 
        comp.setHeight( attr5[0]); 
        context.variables.put("height", 0xFF & (int)attr5[0]);


        return comp;
    }

};
