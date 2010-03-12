package at.fhj.swd07.contours.generators;


import at.fhj.swd07.contours.enums.AttributeTypeEnum;
import at.fhj.swd07.contours.exception.UnsupportedAttributeTypeException;

public class TypeGeneratorFactory {
    public static final TypeGenerator createGenerator(final AttributeTypeEnum attributeType) {
        switch(attributeType) {
        case COMPONENT:
            return new ComponentTypeGenerator();
        case BYTE:
            return new ByteTypeGenerator();
        case INTEGER:
            return new IntegerTypeGenerator();
        case LONG:
            return new LongTypeGenerator();
        case BCD:
            return new BcdTypeGenerator();
        case STATIC_TEXT:
            return new StaticTextTypeGenerator();
        case STATIC_HEX:
            return new StaticHexTypeGenerator();
        case EOF:
            return new EofTypeGenerator();
        default:
            throw new UnsupportedAttributeTypeException(attributeType.name(), null, -1, -1);
                
        }
    }
}
