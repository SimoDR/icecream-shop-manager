////////////////////////////////////////////////////////////////////
// [SIMONE] [DE RENZIS] [1187510]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MenuItemTest {

    private MenuItem item;

    @Before
    public void setup() {
        item = new MenuItem(MenuItem.itemType.Gelato, "menuGelato", 1.30);
    }

    @Test
    public void getNameTest() {
        assertEquals("menuGelato", item.getName());
    }

    @Test
    public void getPriceTest() {
        assertEquals(1.30, item.getPrice(), 0);
    }

    @Test
    public void getItemTypeTest() {
        assertEquals(MenuItem.itemType.Gelato, item.getItemType());
    }
}