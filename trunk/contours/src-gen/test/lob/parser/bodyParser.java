package test.lob.parser;

import java.io.*;
import test.lob.parser.ParseContext;
import test.lob.parser.ParseUtil;
import test.lob.exception.ParseException;
import test.lob.ds.body;
import test.lob.ds.block;
import test.lob.parser.blockParser;

public class bodyParser { 



    bodyParser() {
    }

    public static final body parse(final InputStream is, final ParseContext context) throws IOException, ParseException {
        final body comp = new body();

        // ClassAttribute[name=blocks,syntheticName=attr6,type=COMPONENT,type_identifier=block,static_multiplier=<null>,context_multiplier=number,contextName=<null>]' 
        final int attr6_count = context.variables.get("number"); 
        final block[] attr6 = new block[attr6_count]; 
        for(int i=0; i<attr6_count;i++) { 
            attr6[i] = blockParser.parse(is, context); 
        }; 
        comp.setBlocks( attr6); 


        return comp;
    }

};
