package at.fhj.swd07.contours;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import at.fhj.swd07.contours.enums.AttributeTypeEnum;
import at.fhj.swd07.contours.generators.TypeGeneratorFactory;


public class TypeGeneratorFactoryTest {

    @Test
    // Tests if all datatypes are correctly supported by the TypeGeneratorFactory
    public void testCreateGenerator() {
        for(AttributeTypeEnum val : AttributeTypeEnum.values()) {
            assertNotNull(TypeGeneratorFactory.createGenerator(val));
        }
    }

}
