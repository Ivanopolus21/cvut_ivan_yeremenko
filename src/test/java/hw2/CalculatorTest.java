package hw2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    Calculator calc = new Calculator();
    @Test
    public void doAdd_MethodReturnAddedNum_additionAandB(){
        Assertions.assertEquals(15, calc.add(5, 10));
    }
    @Test
    public void doSubstract_MethodReturnSubstractedNum_substractionAandB(){
        Assertions.assertEquals(3, calc.subtract(7, 4));
    }
    @Test
    public void doMultiply_MethodReturnMultiplitedNum_multiplitionAandB(){
        Assertions.assertEquals(10, calc.multiply(5, 2));
    }
    @Test
    public void doDivide_MethodReturnDividedNum_divisionAandB(){
        Assertions.assertEquals(2, calc.divide(10, 5));
    }
    @Test
    public void doDivideByNull_MethodReturnError_divisionError(){
        Assertions.assertThrows(ArithmeticException.class, () -> calc.divide(-2, 0));
    }
}
