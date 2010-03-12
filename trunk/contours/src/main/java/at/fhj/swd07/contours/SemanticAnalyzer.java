/* ***************************************************************************
 * DynamicFileParser
 * (c) 2009 by 
 * Andreas Hoefler (kosi2801 et gmail)
 * Part of a project for the class at the USoASc FH Joanneum - SWD07
 ****************************************************************************/

package at.fhj.swd07.contours;

import java.util.HashSet;

import at.fhj.swd07.contours.analysis.DepthFirstAdapter;
import at.fhj.swd07.contours.enums.AttributeTypeEnum;
import at.fhj.swd07.contours.exception.*;
import at.fhj.swd07.contours.node.*;

import com.google.common.collect.Sets;

public class SemanticAnalyzer extends DepthFirstAdapter {
    // contains all defined identifiers
    HashSet<String> definedSymbolsTable = Sets.newHashSet();

    public HashSet<String> getDefinedSymbolsTable() {
        return definedSymbolsTable;
    }

    // contains all used identifiers
    HashSet<String> usedSymbolsTable = Sets.newHashSet();

    public HashSet<String> getUsedSymbolsTable() {
        return usedSymbolsTable;
    }

    String currentComponentDefinition;
    AttributeTypeEnum attrType;

    // check that components are only defined once
    @Override
    public void inAIdComponentDefinition(AIdComponentDefinition node) {
        // identifier to be stored in the symbol table
        final TIdentifier ident = node.getIdentifier();
        // name of the identifier to be stored in the table
        currentComponentDefinition = ident.getText();

        // is the identifier in the table?
        if (definedSymbolsTable.contains(currentComponentDefinition)) { // report an error
            throw new DuplicateComponentDefinitionException(currentComponentDefinition, ident.getLine(), ident.getPos());
        } else {
            definedSymbolsTable.add(currentComponentDefinition);
        }
    }

    @Override
    public void inAComponentElementName(AComponentElementName node) {
        final TIdentifier ident = node.getIdentifier();
        final String componentElementName = currentComponentDefinition + '.' + ident.getText();

        // is the identifier in the table?
        if (definedSymbolsTable.contains(componentElementName)) { // report an error
            throw new DuplicateComponentElementException(componentElementName, currentComponentDefinition, ident.getLine(), ident.getPos());
        } else {
            definedSymbolsTable.add(componentElementName);
        }
    }

    // check that used components are defined somewhere
    @Override
    public void outAComponentComponentElementType(AComponentComponentElementType node) {
        usedSymbolsTable.add(node.getIdentifier().getText());
        attrType = AttributeTypeEnum.COMPONENT;
    }

    @Override
    public void outAComponentDefinitions(AComponentDefinitions node) {
        // check that all used components are defined somewhere
        if (Sets.intersection(definedSymbolsTable, usedSymbolsTable).size() != usedSymbolsTable.size()) {
            usedSymbolsTable.removeAll(definedSymbolsTable);
            throw new UndefinedComponentException(usedSymbolsTable.iterator().toString());
        }
    }

    @Override
    public void outAStaticMultiplierComponentElementMultiplier(AStaticMultiplierComponentElementMultiplier node) {
        TIntegerLiteral ident = node.getIntegerLiteral();
        if (Integer.valueOf(ident.getText()) <= 0) {
            throw new InvalidMultiplierException(ident.getText(), currentComponentDefinition, ident.getLine(), ident.getPos());
        }
    }

    @Override
    public void outAStaticHexComponentElementType(AStaticHexComponentElementType node) {
        THexLiteral ident = node.getHexLiteral();
        final String hexVal = ident.getText();
        if (hexVal.length() != 4) { // also count '0x'
            throw new UnsupportedException("Hex value length > 1 byte not yet supported", ident.getLine(), ident.getPos());
        }
        attrType = AttributeTypeEnum.STATIC_HEX;
    }

    // check, that context multipliers are only defined where it makes sense
    @Override
    public void inAComponentComponentDefinitionElement(AComponentComponentDefinitionElement node) {
        attrType = null;
    }

    @Override
    public void outAStaticTextComponentElementType(AStaticTextComponentElementType node) {
        attrType = AttributeTypeEnum.STATIC_TEXT;
    }

    @Override
    public void outAByteElementType(AByteElementType node) {
        attrType = AttributeTypeEnum.BYTE;
    }

    @Override
    public void outAComponentElementContextName(AComponentElementContextName node) {
        TIdentifier ident = node.getIdentifier();
        if (attrType == null)
            return;

        // throw exception, where the value would not make sense for a context multiplier
        switch (attrType) {
        case STATIC_TEXT:
        case COMPONENT:
            throw new UnsupportedException("Assigning context multiplier '" + ident.getText() + "'does not make sense for attribute of type " + attrType.toString(), ident.getLine(), ident.getPos());
        default:
            break;
        }
    }

}
