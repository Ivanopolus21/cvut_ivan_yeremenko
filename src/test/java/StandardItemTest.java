import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import shop.StandardItem;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StandardItemTest {
    StandardItem standardItem;

    @BeforeEach
    public void setup() {
        standardItem = new StandardItem(1, "Makima", (float) 25.11, "Attack on Titan", 20);
    }

    @Test
    public void constructorTest() {
        // ARRANGE
        int expectedId = 1;
        String expectedName = "Makima";
        float expectedPrice = (float) 25.11;
        String expectedCategory = "Attack on Titan";
        int expectedLoyaltyPoints = 20;

        // ACT
        StandardItem standardItem = new StandardItem(expectedId, expectedName, expectedPrice, expectedCategory, expectedLoyaltyPoints);

        // ASSERT
        assertEquals(expectedId, standardItem.getID());
        assertEquals(expectedName, standardItem.getName());
        assertEquals(expectedPrice, standardItem.getPrice());
        assertEquals(expectedCategory, standardItem.getCategory());
        assertEquals(expectedLoyaltyPoints, standardItem.getLoyaltyPoints());
    }

    @Test
    public void copyTest() {
        StandardItem copiedStandardItem = standardItem.copy();

        assertEquals(standardItem, copiedStandardItem);
        // checks if two objects do not refer to the same object
        assertNotSame(standardItem, copiedStandardItem);
    }

    @ParameterizedTest()
    @MethodSource("generator")
    public void equalsWithSameObjetTest(StandardItem firstItem, StandardItem secondItem, boolean expected) {
        assertEquals(expected, firstItem.equals(secondItem));
    }

    @Test
    public void equalsTestWithAnotherItem() {
        Object notStandardItem = new Object();

        assertNotEquals(standardItem, notStandardItem);
    }

    private static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of(
                        new StandardItem(1, "Makima", (float) 25.11, "Attack on Titan", 20),
                        new StandardItem(1, "Makima", (float) 25.11, "Attack on Titan", 20),
                        true
                ),
//                bad id
                Arguments.of(
                        new StandardItem(1, "Makima", (float) 25.11, "Attack on Titan", 20),
                        new StandardItem(2, "Makima", (float) 25.11, "Attack on Titan", 20),
                        false
                ),
//                bad name
                Arguments.of(
                        new StandardItem(1, "Makima", (float) 25.11, "Attack on Titan", 20),
                        new StandardItem(1, "Armin", (float) 25.11, "Attack on Titan", 20),
                        false
                ),
//                bad float number
                Arguments.of(
                        new StandardItem(1, "Makima", (float) 25.11, "Attack on Titan", 20),
                        new StandardItem(1, "Makima", (float) 11, "Attack on Titan", 20),
                        false
                ),
//                bad category
                Arguments.of(
                        new StandardItem(1, "Makima", (float) 25.11, "Attack on Titan", 20),
                        new StandardItem(1, "Makima", (float) 25.11, "One-Punch Man", 20),
                        false
                ),
//                bad loyaltyPoints
                Arguments.of(
                        new StandardItem(1, "Makima", (float) 25.11, "Attack on Titan", 20),
                        new StandardItem(1, "Makima", (float) 25.11, "Attack on Titan", 1),
                        false
                )
        );
    }
}
