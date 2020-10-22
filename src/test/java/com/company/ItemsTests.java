package com.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
public class ItemsTests {
    @Test
    void createItem() {
        Items item = new Items("Expense","food","october",12.50);
        assertTrue(true);
    }
    @Test
    void TestToString() {
        Items item = new Items("Income","salary","may",575.32);
        String expected = "Income**salary**may**575.32";
        assertEquals(expected, item.toString());
    }
    @Test
    void testgetType() {
        Items item = new Items("Expense","bank loan","december",175);
        String expected = "Expense";
        assertEquals(expected, item.getType());
    }
    @Test
    void testgetTitle() {
        Items item = new Items("Expense","bank loan","december",175);
        String expected = "bank loan";
        assertEquals(expected, item.getTitle());
    }
    @Test
    void testgetMonth() {
        Items item = new Items("Expense","bank loan","december",175);
        String expected = "december";
        assertEquals(expected, item.getMonth());
    }
    @Test
    void testgetAmount() {
        Items item = new Items("Expense","bank loan","december",175);
        double expected = 175.0;
        assertEquals(expected, item.getAmount());
    }
}
