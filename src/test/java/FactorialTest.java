import cz.fel.cvut.ts1.Factorial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FactorialTest {
    @Test
    public void factorialTest(){
        Assertions.assertEquals(2, Factorial.factorialRecursive(2));
        Assertions.assertEquals(120, Factorial.factorialIterative(5));
    }
}
