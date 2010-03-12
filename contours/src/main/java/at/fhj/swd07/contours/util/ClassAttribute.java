/* ***************************************************************************
 * DynamicFileParser
 * (c) 2009 by 
 * Andreas Hoefler (kosi2801 et gmail)
 * Part of a project for the class at the USoASc FH Joanneum - SWD07
 ****************************************************************************/

package at.fhj.swd07.contours.util;

import org.apache.commons.lang.builder.*;

import at.fhj.swd07.contours.enums.AttributeTypeEnum;

public class ClassAttribute {
    /**
     * The attribute name (=ComponentElementName) from the definition file.
     */
    public String name;
    
    /**
     * A synthetic and globally unique name for this variable.
     */
    public String syntheticName;
    
    /**
     * The type of this attribute. 
     */
    public AttributeTypeEnum type;
    
    /**
     * Holds different identifier values, depending on the type of the attribute.
     * COMPONENT: name of the referenced component
     * STATIC_TEXT: The value of the static text
     * HEX: The hex value, without '0x'
     */
    public String type_identifier;
    
    /**
     * How often this element repeats in sequence if it's a predetermined number.
     */
    public Integer static_multiplier;
    
    /**
     * The name of the context variable which holds the number of occourences of this attribute if it's a variable number.
     */
    public String context_multiplier;
    
    /**
     * The name of the context variable into which to store the value of this attribute.
     */
    public String contextName;

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this) //
                .append("name", this.name) //
                .append("syntheticName", this.syntheticName) //
                .append("type", this.type) //
                .append("type_identifier", this.type_identifier) //
                .append("static_multiplier", this.static_multiplier) //
                .append("context_multiplier", this.context_multiplier) //
                .append("contextName", this.contextName) //
                .toString();
    }
}
