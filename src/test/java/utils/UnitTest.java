package utils;


import org.junit.Assert;
import org.junit.Test;


public class UnitTest {

    @Test
    public void deveGerarWarCorretamente(){
        Assert.assertEquals(Unit.HOSPEDAGEM, Unit.DIGITAL_OCEAN);
    }

}
