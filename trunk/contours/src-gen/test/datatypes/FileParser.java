package test.datatypes;

import java.io.*;
import test.datatypes.exception.ParseException;
import test.datatypes.parser.ParseContext;
import test.datatypes.ds.start_comp;
import test.datatypes.parser.start_compParser;

public class FileParser { 

    private InputStream is;

    public FileParser(InputStream inputStream) {
        this.is = inputStream;
    }

    public start_comp parse() throws ParseException, IOException {
        ParseContext context = new ParseContext();
        return start_compParser.parse(is, context);
    }

};
