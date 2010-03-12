package windows.executable.parser;

import java.io.*;
import windows.executable.parser.ParseContext;
import windows.executable.parser.ParseUtil;
import windows.executable.exception.ParseException;
import windows.executable.ds.after_header_struct;

public class after_header_structParser { 

        // 'null' 'attr13' '1' 'STATIC_TEXT' 'lalelu' 
        private final static String attr13_exp = "lalelu"; 



    after_header_structParser() {
    }

    public static final after_header_struct parse(final InputStream is, final ParseContext context) throws IOException, ParseException {
        final after_header_struct comp = new after_header_struct();

        // ClassAttribute[name=<null>,syntheticName=attr13,type=STATIC_TEXT,type_identifier=lalelu,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr13 = ParseUtil.readBytes(is, context, 1 * 6); 
        if(!(new String(attr13)).equals(attr13_exp)) {
            throw new ParseException("Excpected content '" + attr13_exp + "' but got '" + new String(attr13) + "'.", context.position);
        }

        // ClassAttribute[name=<null>,syntheticName=attr14,type=BYTE,type_identifier=<null>,static_multiplier=2,context_multiplier=<null>,contextName=<null>]' 
        ParseUtil.skipBytes(is, context, 2); 


        return comp;
    }

};
