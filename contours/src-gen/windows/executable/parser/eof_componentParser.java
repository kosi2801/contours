package windows.executable.parser;

import java.io.*;
import windows.executable.parser.ParseContext;
import windows.executable.parser.ParseUtil;
import windows.executable.exception.ParseException;
import windows.executable.ds.eof_component;

public class eof_componentParser { 



    eof_componentParser() {
    }

    public static final eof_component parse(final InputStream is, final ParseContext context) throws IOException, ParseException {
        final eof_component comp = new eof_component();

        // ClassAttribute[name=<null>,syntheticName=attr17,type=EOF,type_identifier=<null>,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        if(is.available() > 0) {
            throw new ParseException("Excpected EOF but input stream is not empty.", context.position);
        }


        return comp;
    }

};
