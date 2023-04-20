import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Order;
import shop.ShoppingCart;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTester {
    ShoppingCart shoppingCart;

    @BeforeEach
    public void initTest() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void constructorTestStateless() {
        int expectedState = 0;
        String expectedCustomerName = "Ivan";
        String expectedCustomerAddress = "Chaloupeckeho 1915";

        Order order = new Order(shoppingCart, expectedCustomerName, expectedCustomerAddress);

        assertEquals(expectedState, order.getState());
        assertEquals(expectedCustomerName, order.getCustomerName());
        assertEquals(expectedCustomerAddress, order.getCustomerAddress());
    }

    @Test
    public void constructorTestWithState() {
        int expectedState = 2;
        String expectedCustomerName = "Ivan";
        String expectedCustomerAddress = "Chaloupeckeho 1915";

        Order order = new Order(shoppingCart, expectedCustomerName, expectedCustomerAddress, expectedState);

        assertEquals(expectedState, order.getState());
        assertEquals(expectedCustomerName, order.getCustomerName());
        assertEquals(expectedCustomerAddress, order.getCustomerAddress());
    }

    @Test
    public void constructorTestWithEmptyCart() {
        assertThrows(NullPointerException.class, () -> {
            new Order(null, "Ivan", "Chaloupeckeho 1915");
        });
    }
}