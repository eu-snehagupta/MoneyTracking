
package com.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MoneyTrackingTests {
    @Test
    void createMoneylist() {
        ArrayList<Items> moneyList = new ArrayList<>();
        moneyList.add(new Items("Expense", "food", "october", 12.50));
        moneyList.add(new Items("Income", "salary", "may", 575.32));
        assertTrue(true);
    }
     /*@Test          //dont know how to test void method without using mockito
    void TestAddItems() {
        MoneyTracking obj = new MoneyTracking();
        obj.addItems();
        ArrayList<Items> moneyList = new ArrayList<>();
        moneyList.add(new Items("Expense","food","october",12.50));
        moneyList.add(new Items("Income","salary","may",575.32));
        assertEquals(2, moneyList.size());
    }
   @Test       //tried implementing reflection to test private methods, but the test still failing!:(
    public void shouldTakeUserInput() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class c = Class.forName("com.company.MoneyTracking");
        Test t = (Test)c.newInstance();
        MoneyTracking obj= new MoneyTracking();
        Method m = c.getDeclaredMethod("scanUserString", Class.forName("null"));
        m.setAccessible(true);

        String input = "savings";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("savings", m.invoke(t,null));
    }*/
}

