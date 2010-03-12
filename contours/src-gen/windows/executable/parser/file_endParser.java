package windows.executable.parser;

import java.io.*;
import windows.executable.parser.ParseContext;
import windows.executable.parser.ParseUtil;
import windows.executable.exception.ParseException;
import windows.executable.ds.file_end;
import windows.executable.parser.eof_componentParser;

public class file_endParser { 




    file_endParser() {
    }

    public static final file_end parse(final InputStream is, final ParseContext context) throws IOException, ParseException {
        final file_end comp = new file_end();

        // ClassAttribute[name=a_byte,syntheticName=attr15,type=BYTE,type_identifier=<null>,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final byte[] attr15 = ParseUtil.readBytes(is, context, 1); 
        comp.setA_byte( attr15[0]); 

        // ClassAttribute[name=<null>,syntheticName=attr16,type=COMPONENT,type_identifier=eof_component,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        eof_componentParser.parse(is, context); 


        return comp;
    }

};
