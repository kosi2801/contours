package test.lob.parser;

import java.io.*;
import test.lob.parser.ParseContext;
import test.lob.parser.ParseUtil;
import test.lob.exception.ParseException;
import test.lob.ds.lob;
import test.lob.parser.headerParser;
import test.lob.ds.header;
import test.lob.ds.body;
import test.lob.parser.bodyParser;

public class lobParser { 




    lobParser() {
    }

    public static final lob parse(final InputStream is, final ParseContext context) throws IOException, ParseException {
        final lob comp = new lob();

        // ClassAttribute[name=header,syntheticName=attr0,type=COMPONENT,type_identifier=header,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final header attr0 = headerParser.parse(is, context); 
        comp.setHeader(attr0); 

        // ClassAttribute[name=body,syntheticName=attr1,type=COMPONENT,type_identifier=body,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final body attr1 = bodyParser.parse(is, context); 
        comp.setBody(attr1); 


        return comp;
    }

};
