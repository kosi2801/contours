/* ***************************************************************************
 * DynamicFileParser
 * (c) 2009 by 
 * Andreas Hoefler (kosi2801 et gmail)
 * Part of a project for the class at the USoASc FH Joanneum - SWD07
 ****************************************************************************/

package at.fhj.swd07.contours;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.grlea.log.SimpleLogger;

import at.fhj.swd07.contours.analysis.DepthFirstAdapter;
import at.fhj.swd07.contours.enums.AttributeTypeEnum;
import at.fhj.swd07.contours.exception.UnsupportedAttributeTypeException;
import at.fhj.swd07.contours.generators.TypeGeneratorFactory;
import at.fhj.swd07.contours.node.*;
import at.fhj.swd07.contours.util.*;

import com.google.common.collect.*;

public class DataStructureGenerator extends DepthFirstAdapter {

    private static final String SUBPKG = "ds";

    private static final SimpleLogger log = new SimpleLogger(DataStructureGenerator.class);

    // contains all stored identifiers for the current class
    private List<ClassAttribute> classAttributes = Lists.newLinkedList();

    // contains the attribute set for each class
    private Map<String, List<ClassAttribute>> classes = Maps.newHashMap();

    public Map<String, List<ClassAttribute>> getClasses() {
        return classes;
    }

    private String packageName;
    private String outputPath;
    private String currentComponentDefinition;
    private ClassAttribute currentClassAttribute;
    private Integer attrCount = 0;

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

    @Override
    public void outAIdComponentDefinition(AIdComponentDefinition node) {
        log.entry("outAIdComponentDefinition");
        // identifier to be stored in the symbol table
        final TIdentifier ident = node.getIdentifier();

        // name of the identifier to be stored in the table
        currentComponentDefinition = ident.getText();

        // check if path already existing
        FileHandlingUtils.createPath(outputPath);

        // does this component have any attributes
        // if (classAttributes.size() > 0) {
        // create content of file
        final StringBuilder fileContent = new StringBuilder();
        fileContent.append("package " + packageName + ";\n");
        fileContent.append("\n");
        fileContent.append("public class " + currentComponentDefinition + " { \n");
        for (ClassAttribute attr : classAttributes) {
            // only write out attributes with a name
            if (StringUtils.isNotBlank(attr.name)) {
                fileContent.append(generateClassAttributeCode(attr) + "\n");
                fileContent.append('\n');
            }
        }
        fileContent.append("};\n");

        // create data structure file
        final String filename = outputPath + currentComponentDefinition + ".java";
        FileHandlingUtils.createFile(filename, fileContent);
        // }

        // copy attributes to class-map
        classes.put(currentComponentDefinition, classAttributes);

        // clear up attributes-list for next class
        classAttributes = Lists.newLinkedList();
        log.exit("outAIdComponentDefinition");
    }

    @Override
    public void inAComponentComponentDefinitionElement(AComponentComponentDefinitionElement node) {
        log.entry("inAComponentComponentDefinitionElement");
        currentClassAttribute = new ClassAttribute();
        currentClassAttribute.syntheticName = "attr" + (attrCount++).toString();
        log.debugObject("currentClassAttribute", currentClassAttribute);
        log.exit("inAComponentComponentDefinitionElement");
    }

    @Override
    public void outAEofComponentDefinitionElement(AEofComponentDefinitionElement node) {
        log.entry("outAEofComponentDefinitionElement");
        currentClassAttribute = new ClassAttribute();
        currentClassAttribute.syntheticName = "attr" + (attrCount++).toString();

        currentClassAttribute.type = AttributeTypeEnum.EOF;

        log.debugObject("currentClassAttribute", currentClassAttribute);
        classAttributes.add(currentClassAttribute);
        currentClassAttribute = null;
        log.exit("outAEofComponentDefinitionElement");
    }

    @Override
    public void outAComponentElementName(AComponentElementName node) {
        log.entry("outAComponentElementName");
        currentClassAttribute.name = node.getIdentifier().getText();
        log.debugObject("currentClassAttribute.name", currentClassAttribute.name);
        log.exit("outAComponentElementName");
    }

    @Override
    public void outAComponentComponentElementType(AComponentComponentElementType node) {
        log.entry("outAComponentComponentElementType");
        currentClassAttribute.type = AttributeTypeEnum.COMPONENT;
        currentClassAttribute.type_identifier = node.getIdentifier().getText();
        log.debugObject("currentClassAttribute.type", currentClassAttribute.type);
        log.debugObject("currentClassAttribute.type.identifier", currentClassAttribute.type_identifier);
        log.exit("outAComponentComponentElementType");
    }

    @Override
    public void outAStaticTextComponentElementType(AStaticTextComponentElementType node) {
        log.entry("outAStaticTextComponentElementType");
        currentClassAttribute.type = AttributeTypeEnum.STATIC_TEXT;
        String identifier = node.getCharacterLiteral().getText();
        // set static text without enclosing ""s
        currentClassAttribute.type_identifier = identifier.substring(1, identifier.length() - 1);
        log.debugObject("currentClassAttribute.type", currentClassAttribute.type);
        log.debugObject("currentClassAttribute.type.identifier", currentClassAttribute.type_identifier);
        log.exit("outAStaticTextComponentElementType");
    }

    @Override
    public void outAStaticHexComponentElementType(AStaticHexComponentElementType node) {
        log.entry("outAStaticHexComponentElementType");
        currentClassAttribute.type = AttributeTypeEnum.STATIC_HEX;
        String identifier = node.getHexLiteral().getText();
        // set hex value without prefix "0x"
        currentClassAttribute.type_identifier = identifier.substring(2, identifier.length());
        log.debugObject("currentClassAttribute.type", currentClassAttribute.type);
        log.debugObject("currentClassAttribute.type.identifier", currentClassAttribute.type_identifier);
        log.exit("outAStaticHexComponentElementType");
    }

    @Override
    public void outAByteElementType(AByteElementType node) {
        log.entry("outAByteElementType");
        currentClassAttribute.type = AttributeTypeEnum.BYTE;
        log.debugObject("currentClassAttribute.type", currentClassAttribute.type);
        log.exit("outAByteElementType");
    }

    @Override
    public void outAIntegerElementType(AIntegerElementType node) {
        log.entry("outAIntegerElementType");
        currentClassAttribute.type = AttributeTypeEnum.INTEGER;
        log.debugObject("currentClassAttribute.type", currentClassAttribute.type);
        log.exit("outAIntegerElementType");
    }

    @Override
    public void outALongElementType(ALongElementType node) {
        log.entry("outALongElementType");
        currentClassAttribute.type = AttributeTypeEnum.LONG;
        log.debugObject("currentClassAttribute.type", currentClassAttribute.type);
        log.exit("outALongElementType");
    }
    
    @Override
    public void outABcdElementType(ABcdElementType node) {
        log.entry("outABcdElementType");
        currentClassAttribute.type = AttributeTypeEnum.BCD;
        log.debugObject("currentClassAttribute.type", currentClassAttribute.type);
        log.exit("outABcdElementType");
    }
    
    @Override
    public void outAStaticMultiplierComponentElementMultiplier(AStaticMultiplierComponentElementMultiplier node) {
        log.entry("outAStaticMultiplierComponentElementMultiplier");
        currentClassAttribute.static_multiplier = Integer.valueOf(node.getIntegerLiteral().getText());
        log.debugObject("currentClassAttribute.static_multiplier", currentClassAttribute.static_multiplier);
        log.exit("outAStaticMultiplierComponentElementMultiplier");
    }

    @Override
    public void outAContextMultiplierComponentElementMultiplier(AContextMultiplierComponentElementMultiplier node) {
        log.entry("outAContextMultiplierComponentElementMultiplier");
        currentClassAttribute.context_multiplier = node.getIdentifier().getText();
        log.debugObject("currentClassAttribute.context_multiplier", currentClassAttribute.context_multiplier);
        log.exit("outAContextMultiplierComponentElementMultiplier");
    }

    @Override
    public void outAComponentElementContextName(AComponentElementContextName node) {
        log.entry("outAComponentElementContextName");
        currentClassAttribute.contextName = node.getIdentifier().getText();
        log.debugObject("currentClassAttribute.multiplicity", currentClassAttribute.static_multiplier);
        log.exit("outAComponentElementContextName");
    }

    @Override
    public void outAComponentComponentDefinitionElement(AComponentComponentDefinitionElement node) {
        log.entry("outAComponentComponentDefinitionElement");
        classAttributes.add(currentClassAttribute);
        log.debugObject("currentClassAttribute", currentClassAttribute);
        currentClassAttribute = null;
        log.exit("outAComponentComponentDefinitionElement");
    }

    private String generateClassAttributeCode(ClassAttribute attr) {
        log.entry("generateClassAttributeCode");
        log.debugObject("attr", attr);
        StringBuilder str = new StringBuilder();

        // Call generator for this type
        try {
            TypeGeneratorFactory.createGenerator(attr.type).generateDataStructureCode(str, attr);
        } catch (UnsupportedAttributeTypeException ex) {
            throw new UnsupportedAttributeTypeException(ex, attr.type.name(), attr.name, -1, -1);
        }

        log.exit("generateClassAttributeCode");
        return str.toString();
    }
}
