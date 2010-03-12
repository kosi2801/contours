package test.datatypes.parser;

import java.io.*;
import test.datatypes.parser.ParseContext;
import test.datatypes.parser.ParseUtil;
import test.datatypes.exception.ParseException;
import test.datatypes.ds.comp_b;

public class comp_bParser { 



    comp_bParser() {
    }

    public static final comp_b parse(final InputStream is, final ParseContext context) throws IOException, ParseException {
        final comp_b comp = new comp_b();

        // ClassAttribute[name=<null>,syntheticName=attr27,type=BYTE,type_identifier=<null>,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        ParseUtil.skipBytes(is, context, 1); 


        return comp;
    }

};
