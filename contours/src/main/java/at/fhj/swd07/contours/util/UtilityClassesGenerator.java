package at.fhj.swd07.contours.util;

public class UtilityClassesGenerator {

    /**
     * This generates the required Exception-classes for the parsing logic.
     * @param parserPackageName The base package name of the parser. 
     * @param parserOutputPath The base output path for the parser classes.
     */
    public static void generateExceptionClasses(final String parserPackageName, final String parserOutputPath) {
        // create content of file
        final StringBuilder fileContent = new StringBuilder();
        final String mainParserPackage = parserPackageName.substring(0, parserPackageName.lastIndexOf('.'));
        fileContent.append("package " + mainParserPackage + ".exception;\n");
        fileContent.append("\n");
        fileContent.append("public class ParseException extends Exception { \n");
        fileContent.append("\n");
        fileContent.append("    private static final long serialVersionUID = 1L;\n");
        fileContent.append("    private Long position;\n");
        fileContent.append("\n");
        fileContent.append("    public ParseException(String message, Long position) {\n");
        fileContent.append("        super(message);\n");
        fileContent.append("        this.position = position;\n");
        fileContent.append("    }\n");
        fileContent.append("\n");
        fileContent.append("    @Override\n");
        fileContent.append("    public String getMessage() {\n");
        fileContent.append("        return super.getMessage() + \" [pos:\" + position.toString() + ']';\n");
        fileContent.append("    }\n");
        fileContent.append("\n");
        fileContent.append("    public String toString() {\n");
        fileContent.append("        return super.getMessage() + \" [pos:\" + position.toString() + ']';\n");
        fileContent.append("    }\n");
        fileContent.append("\n");
        fileContent.append("    public Long getPosition() {\n");
        fileContent.append("        return position;\n");
        fileContent.append("    }\n");
        fileContent.append("\n");
        fileContent.append("};\n");
    
        // create exception classes
        final String filename = parserOutputPath + "../exception/ParseException.java";
        FileHandlingUtils.createFile(filename, fileContent);
    }

    private static final String MAIN_PARSER_CLASS = "FileParser";

    /**
     * This generates the main entry class which kicks off the whole parsing
     * process.
     * @param parserPackageName The base package name of the parser. 
     * @param parserOutputPath The base output path for the parser classes.
     * @param startComponent The name of the start component for the parser.
     */
    public static void generateMainParser(final String parserPackageName, final String parserOutputPath, final String startComponent) {
        // create content of file
        final StringBuilder fileContent = new StringBuilder();
        final String mainParserPackage = parserPackageName.substring(0, parserPackageName.lastIndexOf('.'));
        fileContent.append("package " + mainParserPackage + ";\n");
        fileContent.append("\n");
        fileContent.append("import java.io.*;\n");
        fileContent.append("import " + mainParserPackage + ".exception.ParseException;\n");
        fileContent.append("import " + mainParserPackage + ".parser.ParseContext;\n");
        fileContent.append("import " + mainParserPackage + ".ds." + startComponent + ";\n");
        fileContent.append("import " + mainParserPackage + ".parser." + startComponent + "Parser;\n");
        fileContent.append("\n");
        fileContent.append("public class " + MAIN_PARSER_CLASS + " { \n");
        fileContent.append("\n");
        fileContent.append("    private InputStream is;\n");
        fileContent.append("\n");
        fileContent.append("    public " + MAIN_PARSER_CLASS + "(InputStream inputStream) {\n");
        fileContent.append("        this.is = inputStream;\n");
        fileContent.append("    }\n");
        fileContent.append("\n");
        fileContent.append("    public " + startComponent + " parse() throws ParseException, IOException {\n");
        fileContent.append("        ParseContext context = new ParseContext();\n");
        fileContent.append("        return " + startComponent + "Parser.parse(is, context);\n");
        fileContent.append("    }\n");
        fileContent.append("\n");
        fileContent.append("};\n");
    
        // create main parser class
        final String filename = parserOutputPath + "../" + MAIN_PARSER_CLASS + ".java";
        FileHandlingUtils.createFile(filename, fileContent);
    }

    /**
     * This generates the required Exception-classes for the parsing logic.
     * @param parserPackageName The base package name of the parser. 
     * @param parserOutputPath The base output path for the parser classes.
     */
    public static void generateParserContextClasses(final String parserPackageName, final String parserOutputPath) {
        // create content of file
        final StringBuilder fileContent = new StringBuilder();
        fileContent.append("package " + parserPackageName + ";\n");
        fileContent.append("\n");
        fileContent.append("import java.util.*;\n");
        fileContent.append("\n");
        fileContent.append("public class ParseContext { \n");
        fileContent.append("\n");
        fileContent.append("    public Long position = 0L;\n");
        fileContent.append("    public Map<String, Integer>  variables = new HashMap<String, Integer>();\n");
        fileContent.append("\n");
        fileContent.append("};\n");
    
        // create exception classes
        final String filename = parserOutputPath + "ParseContext.java";
        FileHandlingUtils.createFile(filename, fileContent);
    }

    /**
     * This generates the required utility for the parsing logic.
     * @param parserPackageName The base package name of the parser. 
     * @param parserOutputPath The base output path for the parser classes.
     */
    public static void generateParserUtilClasses(final String parserPackageName, final String parserOutputPath) {
        // create content of file
        final String mainParserPackage = parserPackageName.substring(0, parserPackageName.lastIndexOf('.'));
        final StringBuilder fileContent = new StringBuilder();
        fileContent.append("package " + parserPackageName + ";\n");
        fileContent.append("\n");
        fileContent.append("import java.io.*;\n");
        fileContent.append("import " + mainParserPackage + ".exception.ParseException;\n");
        fileContent.append("\n");
        fileContent.append("final class ParseUtil { \n");
        fileContent.append("\n");
        fileContent.append("        static final byte[] readBytes(final InputStream is, final ParseContext context, final int size) throws IOException, ParseException {\n");
        fileContent.append("            final byte[] attr0 = new byte[size]; \n");
        fileContent.append("            final long readBytes = is.read(attr0, 0, size); \n");
        fileContent.append("            if(readBytes < size) {\n");
        fileContent.append("                throw new ParseException(\"Tried to read \" + Integer.toString(size) + \" bytes but hit EOF instead.\", context.position);\n");
        fileContent.append("            }\n");
        fileContent.append("            context.position += size;\n");
        fileContent.append("            return attr0;\n");
        fileContent.append("        }\n");
        fileContent.append("\n");
        fileContent.append("        static final int readInt(final InputStream is, final ParseContext context) throws IOException, ParseException {\n");
        fileContent.append("            final byte[] intBytes = readBytes(is, context, 4); \n");
        fileContent.append("            return ((int)intBytes[0] << 24)\n");
        fileContent.append("                + (((int)intBytes[1] & 0xFF) << 16)\n");
        fileContent.append("                + (((int)intBytes[2] & 0xFF) << 8)\n");
        fileContent.append("                +  ((int)intBytes[3] & 0xFF);\n");
        fileContent.append("        }\n");
        fileContent.append("\n");
        fileContent.append("        static final long readLong(final InputStream is, final ParseContext context) throws IOException, ParseException {\n");
        fileContent.append("            final byte[] intBytes = readBytes(is, context, 8); \n");
        fileContent.append("            return ((long)intBytes[0] << 56)\n");
        fileContent.append("                + (((long)intBytes[1] & 0xFF) << 48)\n");
        fileContent.append("                + (((long)intBytes[2] & 0xFF) << 40)\n");
        fileContent.append("                + (((long)intBytes[3] & 0xFF) << 32)\n");
        fileContent.append("                + (((long)intBytes[4] & 0xFF) << 24)\n");
        fileContent.append("                + (((long)intBytes[5] & 0xFF) << 16)\n");
        fileContent.append("                + (((long)intBytes[6] & 0xFF) << 8)\n");
        fileContent.append("                +  ((long)intBytes[7] & 0xFF);\n");
        fileContent.append("        }\n");
        fileContent.append("\n");
        /**
         * Returns a string representation of the BCD encoded bytes. Values
         * other than 0-9 are converted to blanks, leading and trailing blanks
         * are removed.
         * 
         * @param is
         * @param context
         * @param size
         * @return
         * @throws IOException
         * @throws ParseException
         */
        fileContent.append("        static final String readBcd(final InputStream is, final ParseContext context, final int size) throws IOException, ParseException {\n");
        fileContent.append("            final byte[] bcdBytes = readBytes(is, context, size);\n");
        fileContent.append("\n");
        fileContent.append("            final StringBuilder buf = new StringBuilder(bcdBytes.length * 2);\n");
        // for (int i = 0; i < bcdBytes.length; ++i) {
        // buf.append((char) (((bcdBytes[i] & 0xf0) >> 4) + '0'));
        // if ((i != bcdBytes.length) && ((bcdBytes[i] & 0xf) != 0x0A)) // if
        // not pad char
        // buf.append((char) ((bcdBytes[i] & 0x0f) + '0'));
        // }
        fileContent.append("            for(final byte b : bcdBytes) {\n");
        fileContent.append("                // higher nibble\n");
        fileContent.append("                if((b & 0xf0) < 0xa0) {\n");
        fileContent.append("                    buf.append((char)(((b & 0xf0) >> 4) + '0'));\n");
        fileContent.append("                } else {\n");
        fileContent.append("                    buf.append(' ');\n");
        fileContent.append("                }\n");
        fileContent.append("                // lower nibble\n");
        fileContent.append("                if((b & 0x0f) < 0x0a) {\n");
        fileContent.append("                    buf.append((char)((b & 0x0f) + '0'));\n");
        fileContent.append("                } else {\n");
        fileContent.append("                    buf.append(' ');\n");
        fileContent.append("                }\n");
        fileContent.append("            }\n");
        fileContent.append("            return buf.toString().trim();\n");
        fileContent.append("        }\n");
        fileContent.append("\n");
        fileContent.append("        static final void skipBytes(final InputStream is, final ParseContext context, final int size) throws IOException, ParseException {\n");
        fileContent.append("            final long availableBytes = is.available(); \n");
        fileContent.append("            final long readBytes = is.skip(size); \n");
        fileContent.append("            /* also check available bytes before skip, as FileInputStream may skip beyond EOF without error.\n");
        fileContent.append("               http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6294974 */\n");
        fileContent.append("            if((readBytes < size) || (availableBytes < size)) {\n");
        fileContent.append("                throw new ParseException(\"Tried to skip \" + Integer.toString(size) + \" bytes but hit EOF instead.\", context.position);\n");
        fileContent.append("            }\n");
        fileContent.append("            context.position += size;\n");
        fileContent.append("        }\n");
        fileContent.append("\n");
        fileContent.append("};\n");
    
        // create util classes
        final String filename = parserOutputPath + "ParseUtil.java";
        FileHandlingUtils.createFile(filename, fileContent);
    }

}
