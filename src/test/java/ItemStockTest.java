import org.junit.Test;
import shop.Item;
import storage.ItemStock;

import static org.junit.Assert.*;


public class ItemStockTest {
    @Test
    public void testOfConstructor() {
        Item item = new Item(1, "First Item" , 5, "Fifth Category");
        ItemStock itemStock = new ItemStock(item);
        assertEquals(item, itemStock.getItem());
        assertEquals(0, itemStock.getCount());
    }

    @Test
    public void testInDecreasingItemCount() {
        Item item = new Item(1, "First Item" , 5, "Fifth Category");
        ItemStock itemStock = new ItemStock(item);
        itemStock.IncreaseItemCount(10);
        itemStock.decreaseItemCount(5);
        assertEquals(5, itemStock.getCount());
        itemStock.decreaseItemCount(3);
        assertEquals(2, itemStock.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCountWithInvalidItem() {
        Item item = new Item(1, "First Item" , 5, "Fifth Category");
        ItemStock itemStock = new ItemStock(item);
        itemStock.setCount(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInIncreasingItemCountWithInvalidItem() {
        Item item = new Item(1, "First Item" , 5, "Fifth Category");
        ItemStock itemStock = new ItemStock(item);
        itemStock.IncreaseItemCount(0);
    }

    @Test
    public void testSetCount() {
        Item item = new Item(1, "First Item" , 5, "Fifth Category");
        ItemStock itemStock = new ItemStock(item);
        itemStock.setCount(5);
        assertEquals(5, itemStock.getCount());
        itemStock.setCount(0);
        assertEquals(0, itemStock.getCount());
    }

    @Test
    public void testInDecreasingItemCountWithZeroItems() {
        Item item = new Item(1, "First Item" , 5, "Fifth Category");
        ItemStock itemStock = new ItemStock(item);
        itemStock.decreaseItemCount(5);
        assertEquals(0, itemStock.getCount());
    }

    @Test
    public void testInIncreasingItemCount() {
        Item item = new Item(1, "First Item" , 5, "Fifth Category");
        ItemStock itemStock = new ItemStock(item);
        itemStock.IncreaseItemCount(5);
        assertEquals(5, itemStock.getCount());
        itemStock.IncreaseItemCount(3);
        assertEquals(8, itemStock.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInDecreasingItemCountWithInvalidItem() {
        Item item = new Item(1, "First Item" , 5, "Fifth Category");
        ItemStock itemStock = new ItemStock(item);
        itemStock.decreaseItemCount(0);
    }

}


