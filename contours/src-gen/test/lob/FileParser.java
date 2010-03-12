package test.lob;

import java.io.*;
import test.lob.exception.ParseException;
import test.lob.parser.ParseContext;
import test.lob.ds.lob;
import test.lob.parser.lobParser;

public class FileParser { 

    private InputStream is;

    public FileParser(InputStream inputStream) {
        this.is = inputStream;
    }

    public lob parse() throws ParseException, IOException {
        ParseContext context = new ParseContext();
        return lobParser.parse(is, context);
    }

};
