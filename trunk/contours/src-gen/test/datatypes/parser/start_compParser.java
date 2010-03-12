package test.datatypes.parser;

import java.io.*;
import test.datatypes.parser.ParseContext;
import test.datatypes.parser.ParseUtil;
import test.datatypes.exception.ParseException;
import test.datatypes.ds.start_comp;
import test.datatypes.ds.static_instances;
import test.datatypes.parser.single_instancesParser;
import test.datatypes.ds.dynamic_instances;
import test.datatypes.ds.single_instances;
import test.datatypes.parser.static_instancesParser;
import test.datatypes.parser.dynamic_instancesParser;

public class start_compParser { 






    start_compParser() {
    }

    public static final start_comp parse(final InputStream is, final ParseContext context) throws IOException, ParseException {
        final start_comp comp = new start_comp();

        // ClassAttribute[name=single_instances,syntheticName=attr0,type=COMPONENT,type_identifier=single_instances,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final single_instances attr0 = single_instancesParser.parse(is, context); 
        comp.setSingle_instances(attr0); 

        // ClassAttribute[name=static_instances,syntheticName=attr1,type=COMPONENT,type_identifier=static_instances,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final static_instances attr1 = static_instancesParser.parse(is, context); 
        comp.setStatic_instances(attr1); 

        // ClassAttribute[name=dynamic_instances,syntheticName=attr2,type=COMPONENT,type_identifier=dynamic_instances,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        final dynamic_instances attr2 = dynamic_instancesParser.parse(is, context); 
        comp.setDynamic_instances(attr2); 

        // ClassAttribute[name=<null>,syntheticName=attr3,type=EOF,type_identifier=<null>,static_multiplier=1,context_multiplier=<null>,contextName=<null>]' 
        if(is.available() > 0) {
            throw new ParseException("Excpected EOF but input stream is not empty.", context.position);
        }


        return comp;
    }

};
