/* ***************************************************************************
 * DynamicFileParser
 * (c) 2009 by 
 * Andreas Hoefler (kosi2801 et gmail)
 * Part of a project for the class at the USoASc FH Joanneum - SWD07
 ****************************************************************************/

package at.fhj.swd07.contours;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.*;
import org.grlea.log.SimpleLogger;

import at.fhj.swd07.contours.analysis.DepthFirstAdapter;
import at.fhj.swd07.contours.exception.UnsupportedAttributeTypeException;
import at.fhj.swd07.contours.generators.TypeGeneratorFactory;
import at.fhj.swd07.contours.node.*;
import at.fhj.swd07.contours.util.*;

import com.google.common.collect.Sets;

public class ParserGenerator extends DepthFirstAdapter {

    private static final String SUBPKG = "parser";

    private static final SimpleLogger log = new SimpleLogger(ParserGenerator.class);

    private String packageName;
    private String outputPath;
    private String startComponent;
    Map<String, List<ClassAttribute>> classes;

    public ParserGenerator(Map<String, List<ClassAttribute>> classes) {
        this.classes = classes;

        // correct multiplicity if not yet done
        for (Iterator<Map.Entry<String, List<ClassAttribute>>> iter = classes.entrySet().iterator(); iter.hasNext();) {
            List<ClassAttribute> componentAttributes = iter.next().getValue();
            for (ClassAttribute attr : componentAttributes) {
                if (attr.static_multiplier == null && attr.context_multiplier == null) {
                    attr.static_multiplier = 1;
                }
            }
        }
    }

    /**
     * This generates all the required .java files at the output-path for the
     * generated parser.
     */
    public void generateParser() {

        // set simple ToStringStyle for output of comments for ClassAttributes
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);

        // check if path already existing
        FileHandlingUtils.createPath(outputPath);
        FileHandlingUtils.createPath(outputPath + "../exception/");

        // generate exception classes
        UtilityClassesGenerator.generateExceptionClasses(packageName, outputPath);

        // generate the context classes
        UtilityClassesGenerator.generateParserContextClasses(packageName, outputPath);

        // generate the util
        UtilityClassesGenerator.generateParserUtilClasses(packageName, outputPath);

        // generate Main parsing class
        UtilityClassesGenerator.generateMainParser(packageName, outputPath, startComponent);

        // generate parser classes for single components
        generateComponentParsers();
    }

    /**
     * This generates the parser classes for each different component.
     */
    private void generateComponentParsers() {
        final String mainParserPackage = packageName.substring(0, packageName.lastIndexOf('.'));
        // iterate through all classes
        for (Iterator<Map.Entry<String, List<ClassAttribute>>> iter = classes.entrySet().iterator(); iter.hasNext();) {
            final Map.Entry<String, List<ClassAttribute>> entry = iter.next();
            final String componentName = entry.getKey();
            final List<ClassAttribute> componentAttributes = entry.getValue();

            // create content of file
            final StringBuilder fileContent = new StringBuilder();
            fileContent.append("package " + packageName + ";\n");
            fileContent.append("\n");
            fileContent.append("import java.io.*;\n");
            fileContent.append("import " + packageName + ".ParseContext;\n");
            fileContent.append("import " + packageName + ".ParseUtil;\n");
            fileContent.append("import " + mainParserPackage + ".exception.ParseException;\n");
            fileContent.append("import " + mainParserPackage + ".ds." + componentName + ";\n");

            // create imports for all component-attributes, but only once for
            // each
            final Set<String> importsSet = Sets.newHashSet();
            for (ClassAttribute attr : componentAttributes) {
                switch (attr.type) {
                case COMPONENT:
                    importsSet.add("import " + packageName + "." + attr.type_identifier + "Parser;\n");
                    // skip .ds import if component is never needed for an
                    // assignment in variable or context
                    if ((attr.name != null) || (attr.contextName != null)) {
                        importsSet.add("import " + mainParserPackage + ".ds." + attr.type_identifier + ";\n");
                    }
                    break;
                case STATIC_HEX:
                    importsSet.add("import java.util.Arrays;\n");
                    break;
                }
            }
            fileContent.append(StringUtils.join(importsSet.toArray()));
            fileContent.append("\n");
            fileContent.append("public class " + componentName + "Parser { \n");
            fileContent.append("\n");

            // generate static values for comparison later
            for (ClassAttribute attr : componentAttributes) {
                generateExpectedStaticValues(fileContent, attr);
            }
            fileContent.append("\n");
            fileContent.append("    " + componentName + "Parser() {\n");
            fileContent.append("    }\n");
            fileContent.append("\n");
            fileContent.append("    public static final " + componentName
                    + " parse(final InputStream is, final ParseContext context) throws IOException, ParseException {\n");
            fileContent.append("        final " + componentName + " comp = new " + componentName + "();\n");
            fileContent.append("\n");

            // generate attributes and their reading logic
            for (ClassAttribute attr : componentAttributes) {
                generateAttributeCode(fileContent, attr);
            }
            fileContent.append("\n");
            fileContent.append("        return comp;\n");
            fileContent.append("    }\n");
            fileContent.append("\n");
            fileContent.append("};\n");

            // create data structure file
            final String filename = outputPath + componentName + "Parser.java";
            FileHandlingUtils.createFile(filename, fileContent);
        }
    }

    /**
     * This generates the static variable which will be used to check for static
     * text or hex values within the parsed content.
     * 
     * @param fileContent
     *            The StringBuilder where the code-section for the static
     *            variables for the comparison will be added to.
     * @param attr
     *            The attribute for which the static comparison template should
     *            be generated if neccessary.
     */
    private void generateExpectedStaticValues(StringBuilder fileContent, ClassAttribute attr) {
        // write out statics for comparison
        // Call generator for this type
        try {
            TypeGeneratorFactory.createGenerator(attr.type).generateAttributeStaticExpectedCode(fileContent, attr);
        } catch (UnsupportedAttributeTypeException ex) {
            throw new UnsupportedAttributeTypeException(ex, attr.type.name(), attr.name, -1, -1);
        }

        fileContent.append('\n');
    }

    /**
     * This generates the code which reads in an attribute, checks static
     * content and stores the result in the appropiate data structures.
     * 
     * @param fileContent
     *            The StringBuilder where the code-section for the parsing of
     *            this attribute will be added to.
     * @param attr
     *            The attribute for which the parsing and checking code should
     *            be generated.
     */
    private void generateAttributeCode(StringBuilder fileContent, ClassAttribute attr) {
        fileContent.append("        // " + attr.toString() + "' \n");

        // call generator for this type
        try {
            TypeGeneratorFactory.createGenerator(attr.type).generateAttributeReaderCode(fileContent, attr);
        } catch (UnsupportedAttributeTypeException ex) {
            throw new UnsupportedAttributeTypeException(ex, attr.type.name(), attr.name, -1, -1);
        }
        fileContent.append('\n');
    }

    @Override
    public void outADefinitionPackageIdentifier(ADefinitionPackageIdentifier node) {
        log.entry("outADefinitionPackageIdentifier");
        packageName = node.getPackageIdentifier().getText() + '.' + SUBPKG;
        outputPath = packageName.replace('.', '/') + "/";

        log.info("Generated outputpath '" + outputPath + "'from package");
        log.exit("outADefinitionPackageIdentifier");
    }

    @Override
    public void outAOutputPath(AOutputPath node) {
        log.entry("outAOutputPath");
        String text = node.getCharacterLiteral().getText();
        outputPath = text.substring(1, text.length() - 1);
        if (!(outputPath.endsWith("/") || outputPath.endsWith("\\"))) {
            outputPath += "/";
        }
        outputPath += SUBPKG + '/';
        log.info("Overriding outputpath with '" + outputPath + "'.");
        log.exit("outAOutputPath");
    }

    public void outAStartComponent(AStartComponent node) {
        log.entry("outAStartComponent");
        startComponent = node.getIdentifier().getText();
        log.exit("outAStartComponent");
    }
}
