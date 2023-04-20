import org.junit.jupiter.api.Test;
import shop.EShopController;
import shop.Item;
import shop.ShoppingCart;
import shop.StandardItem;
import storage.ItemStock;
import storage.NoItemInStorage;
import storage.Storage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EShopControllerTest {

    //  Steps for purchasing a product:
    //1. Update the inventory by adding one product to it
    //2. Add the product to the cart
    //3. Place an order for the product
    //4. Complete the purchase by making payment for the order
    //5. Verify the inventory to confirm that the product has been deducted from it
    @Test
    public void shoppingCartTestBuyingOnlyOneItem() throws NoItemInStorage {
        EShopController.startEShop();
        StandardItem item = new StandardItem(1, "Name", 200, "FOOD", 5);

        // Check if count 0
        assertThrows(IllegalArgumentException.class, () -> {
            EShopController.addItemToStorage(item, 0);
        });

        // Adding whit count 1
        EShopController.addItemToStorage(item, 1);
        ArrayList<ItemStock> itemsFromStorage = new ArrayList<>(EShopController.getItemsFromStorage());

        assertEquals(1, itemsFromStorage.size());
        assertEquals(1, itemsFromStorage.get(0).getCount());
        assertSame(item, itemsFromStorage.get(0).getItem());

        // Cart Creating
        ShoppingCart cart = EShopController.newCart();
        assertEquals(1, EShopController.getCarts().size());
        assertTrue(EShopController.getCarts().contains(cart));
        assertEquals(0, cart.getItemsCount());

        // Adding Item to cart
        cart.addItem(item);
        assertEquals(1, cart.getItemsCount());
        assertTrue(cart.getCartItems().contains(item));

        // Creating an order
        EShopController.purchaseShoppingCart(cart, "Ivan", "Chaloupeckeho 1915");

        // Count of item has to be 0
        itemsFromStorage = new ArrayList<>(EShopController.getItemsFromStorage()); // update
        assertEquals(1, itemsFromStorage.size());
        assertEquals(0, itemsFromStorage.get(0).getCount());
        assertEquals(1, EShopController.getArchive().getHowManyTimesHasBeenItemSold(item));
    }

    // Buying first product
    // 1. Fill the inventory (add some products to it)
    // 2. Add 3 of the products to cart
    // 3. Remove one product from cart and inventory (stock = 4)
    // 4. Try to place an order

    @Test
    public void shoppingCartTestNoItemInShoppingCart() throws NoItemInStorage {
        EShopController.startEShop();

        int[] itemCount = {1,1,1,1,1};

        Item[] storageItems = {
                new StandardItem(1, "One-Punch Man", 1200, "ANIME", 20),
                new StandardItem(2, "Lenovo Legion 5", 37000, "GADGETS", 401),
                new StandardItem(3, "Morchinska Water", 20, "FOOD", 5),
                new StandardItem(4, "Genshin Impact", 299, "VIDEOGAMES", 19),
                new StandardItem(5, "Clay", 550, "ITEMS", 17),
        };

        for (int i = 0; i < storageItems.length; i++) {
            EShopController.addItemToStorage(storageItems[i], itemCount[i]);
        }

        Storage storage = EShopController.getStorage();

        for (int i = 0; i < storageItems.length; i++) {
            assertEquals(1, storage.getItemCount(storageItems[i]));
        }

        // Cart Creating
        ShoppingCart cart = EShopController.newCart();
        assertEquals(1, EShopController.getCarts().size());
        assertTrue(EShopController.getCarts().contains(cart));
        assertEquals(0, cart.getItemsCount());

        // Adding Item to cart (3 items)
        cart.addItem(storageItems[0]);
        cart.addItem(storageItems[1]);
        cart.addItem(storageItems[2]);

        ArrayList<Item> cartItems = cart.getCartItems();

        assertEquals(3, cart.getItemsCount());

        assertTrue(cart.getCartItems().contains(storageItems[0]));
        assertTrue(cart.getCartItems().contains(storageItems[1]));
        assertTrue(cart.getCartItems().contains(storageItems[2]));

        // Removing 1 item of cart from storage
        storage.removeItems(storageItems[1], 1);
        assertEquals(0, storage.getItemCount(storageItems[1]));

        assertThrows(NoItemInStorage.class, () -> {
            EShopController.purchaseShoppingCart(cart, " some name", "Bla  29");
        });
    }
}