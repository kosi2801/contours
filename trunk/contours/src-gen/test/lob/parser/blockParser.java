package test.lob.parser;

import java.io.*;
import test.lob.parser.ParseContext;
import test.lob.parser.ParseUtil;
import test.lob.exception.ParseException;
import test.lob.ds.block;
import test.lob.parser.lineParser;
import test.lob.ds.line;

public class blockParser { 



    blockParser() {
    }

    public static final block parse(final InputStream is, final ParseContext context) throws IOException, ParseException {
        final block comp = new block();

        // ClassAttribute[name=lines,syntheticName=attr7,type=COMPONENT,type_identifier=line,static_multiplier=<null>,context_multiplier=height,contextName=<null>]' 
        final int attr7_count = context.variables.get("height"); 
        final line[] attr7 = new line[attr7_count]; 
        for(int i=0; i<attr7_count;i++) { 
            attr7[i] = lineParser.parse(is, context); 
        }; 
        comp.setLines( attr7); 


        return comp;
    }

};
