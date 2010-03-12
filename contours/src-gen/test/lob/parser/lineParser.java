package test.lob.parser;

import java.io.*;
import test.lob.parser.ParseContext;
import test.lob.parser.ParseUtil;
import test.lob.exception.ParseException;
import test.lob.ds.line;

public class lineParser { 



    lineParser() {
    }

    public static final line parse(final InputStream is, final ParseContext context) throws IOException, ParseException {
        final line comp = new line();

        // ClassAttribute[name=bytes,syntheticName=attr8,type=BYTE,type_identifier=<null>,static_multiplier=<null>,context_multiplier=width,contextName=<null>]' 
        final byte[] attr8 = ParseUtil.readBytes(is, context, context.variables.get("width")); 
        comp.setBytes( attr8); 


        return comp;
    }

};
