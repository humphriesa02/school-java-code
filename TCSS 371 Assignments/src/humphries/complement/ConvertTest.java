package humphries.complement;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConvertTest {

    @Test
    public void testNegative2sComp() {
        char data[] = {'1', '0', '1', '0', '0'};
        assertEquals(-12, Convert.convert2sCompToDecimal(data));
    }

    @Test
    public void testPositive2sComp() {
        char data[] = {'0', '1', '1', '0', '1'};
        assertEquals(13, Convert.convert2sCompToDecimal(data));
    }
    @Test
    public void testNegative2sCompLargeNum(){
        char data[] = {'1','1','0','1','1','1','0','1','1','1','0','1'};
        assertEquals(-547, Convert.convert2sCompToDecimal(data));
    }

}
