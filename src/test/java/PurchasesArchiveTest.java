
import archive.PurchasesArchive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import shop.Item;
import shop.Order;
import shop.StandardItem;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import archive.ItemPurchaseArchiveEntry;


public class PurchasesArchiveTest {
    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorContent = new ByteArrayOutputStream();
    private final PrintStream originalOutput = System.out;
    private final PrintStream originalError = System.err;
    private StandardItem mockedItem;
    private Order mockedOrder;
    private ItemPurchaseArchiveEntry mockedItemPurchaseArchiveEntry;
    private HashMap<Integer, ItemPurchaseArchiveEntry> itemArchive;
    private ArrayList<Order> orderArchive;

    @BeforeEach
    public void initTest() {
        mockedItem = Mockito.mock(StandardItem.class);
        mockedItemPurchaseArchiveEntry = Mockito.mock(ItemPurchaseArchiveEntry.class);
        mockedOrder = Mockito.mock(Order.class);
        itemArchive = new HashMap<>();
        orderArchive = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        items.add(mockedItem);

        Mockito.when(mockedItem.getID()).thenReturn(1);

        Mockito.when(mockedOrder.getItems()).thenReturn(items);

        Mockito.when(mockedItemPurchaseArchiveEntry.getCountHowManyTimesHasBeenSold()).thenReturn(1);
        itemArchive.put(1, mockedItemPurchaseArchiveEntry);

        orderArchive.add(mockedOrder);
    }

    @Test
    public void putTestOrderToArchiveForNonExistingProduct() {
        StandardItem newMockedItem = Mockito.mock(StandardItem.class);

        // New Order
        ArrayList<Item> items = new ArrayList<>();
        items.add(newMockedItem);
        Order newMockedOrder = Mockito.mock(Order.class);

        Mockito.when(newMockedItem.getID()).thenReturn(2);
        Mockito.when(newMockedOrder.getItems()).thenReturn(items);

        PurchasesArchive purchasesArchive = new PurchasesArchive(itemArchive, orderArchive);

        purchasesArchive.putOrderToPurchasesArchive(newMockedOrder);

        assertTrue(itemArchive.containsKey(2));
        assertEquals(1, itemArchive.get(2).getCountHowManyTimesHasBeenSold());
        assertEquals(2, itemArchive.get(2).getRefItem().getID());
    }

    @Test
    public void printEmptyTestOneItemPurchaseStatistics() {
        System.setOut(new PrintStream(outputContent));
        System.setErr(new PrintStream(errorContent));

        // test only printTestEmptyPurchaseStatistic
        Mockito.when(mockedItemPurchaseArchiveEntry.toString()).thenReturn("THING");
        itemArchive.put(1, mockedItemPurchaseArchiveEntry);
        PurchasesArchive purchasesArchive = new PurchasesArchive(itemArchive, null);
        String expected = "Statistic for item purchase: " + System.lineSeparator() + "THING" + System.lineSeparator();

        purchasesArchive.printItemPurchaseStatistics();

        assertEquals(expected, outputContent.toString());

        System.setErr(originalError);
    }

    @Test
    public void getEmptyArchiveTestNumberOfSoldItem() {
        Mockito.when(mockedItem.getID()).thenReturn(1);
        PurchasesArchive emptyPurchasesArchive = new PurchasesArchive();

        assertEquals(0, emptyPurchasesArchive.getHowManyTimesHasBeenItemSold(mockedItem));
    }

    @Test
    public void printTestEmptyPurchaseStatistic() {
        System.setOut(new PrintStream(outputContent));
        System.setErr(new PrintStream(errorContent));

        PurchasesArchive emptyPurchasesArchive = new PurchasesArchive();
        String expected = "Statistic for item purchase: " + System.lineSeparator();

        emptyPurchasesArchive.printItemPurchaseStatistics();

        assertEquals(expected, outputContent.toString());

        System.setOut(originalOutput);
        System.setErr(originalError);
    }

    @Test
    public void putTestOrderToPurchasesArchiveExistingItem() {
        PurchasesArchive purchasesArchive = new PurchasesArchive(itemArchive, orderArchive);

        purchasesArchive.putOrderToPurchasesArchive(mockedOrder);

        Mockito.verify(mockedItemPurchaseArchiveEntry).increaseCountHowManyTimesHasBeenSold(1);
    }

    @Test
    public void getTestHowManyTimesItemWasSold() {
        Mockito.when(mockedItem.getID()).thenReturn(10);
        Mockito.when(mockedItemPurchaseArchiveEntry.getCountHowManyTimesHasBeenSold()).thenReturn(30);
        itemArchive.put(10, mockedItemPurchaseArchiveEntry);
        PurchasesArchive purchasesArchive = new PurchasesArchive(itemArchive, null);

        assertEquals(30, purchasesArchive.getHowManyTimesHasBeenItemSold(mockedItem));
    }
}