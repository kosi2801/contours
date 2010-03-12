package windows.executable;

import java.io.*;
import windows.executable.exception.ParseException;
import windows.executable.parser.ParseContext;
import windows.executable.ds.component_a;
import windows.executable.parser.component_aParser;

public class FileParser { 

    private InputStream is;

    public FileParser(InputStream inputStream) {
        this.is = inputStream;
    }

    public component_a parse() throws ParseException, IOException {
        ParseContext context = new ParseContext();
        return component_aParser.parse(is, context);
    }

};
