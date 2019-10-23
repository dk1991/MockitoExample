import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorTest {
    private static Calculator calculator;

    @BeforeClass
    public static void creatCalculator() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        int result = calculator.add(2,2);

        Assert.assertEquals(4,result);
    }

    @Test
    public void testSubtract() {
        int result = calculator.subtract(3,-2);

        Assert.assertEquals(5,result);
    }
}
