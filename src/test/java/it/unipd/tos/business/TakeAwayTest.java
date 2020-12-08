////////////////////////////////////////////////////////////////////
// [SIMONE] [DE RENZIS] [1187510]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayTest {

    TakeAwayManager manager;
    double totalPrice;
    List<MenuItem> bill;
    User user;

    @Before
    public void setup() {
        manager = new TakeAwayManager();
        totalPrice = 0.0;
        bill = new ArrayList<MenuItem>();
        user = new User(43,"Pippo", "Pluto", LocalDate.of(1999, 1, 24));
    }

    @Test(expected = TakeAwayBillException.class)
    public void nullBillTest() throws TakeAwayBillException {
        bill = null;
        totalPrice = manager.getOrderPrice(bill, user, 0);
    }

    @Test(expected = TakeAwayBillException.class)
    public void billContainsNullItemTest() throws TakeAwayBillException {
        bill.add(null);
        bill.add(new MenuItem(MenuItem.itemType.Budino, "nullBudino", 2.00));
        totalPrice = manager.getOrderPrice(bill, user, 0);
    }

    @Test
    public void simpleTotalSumOfItemsTest() throws TakeAwayBillException {

        bill.add(new MenuItem(MenuItem.itemType.Gelato, "simpleGelato", 1.30));
        bill.add(new MenuItem(MenuItem.itemType.Budino, "simpleBudino", 2.50));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "reach10Euros", 7.00));
        totalPrice = manager.getOrderPrice(bill, user, 0);
        assertEquals(13.8, totalPrice, 0);
    }
    
    // se presi più di 5 gelati sconto sul gelato meno costoso
    @Test
    public void moreThan5GelatiShouldGetDiscountOfHalfOfTheCheaperTest() 
            throws TakeAwayBillException  {
        bill.add(new MenuItem(MenuItem.itemType.Gelato, "Gelato1", 1.30));
        bill.add(new MenuItem(MenuItem.itemType.Gelato, "Gelato2", 1.50));
        bill.add(new MenuItem(MenuItem.itemType.Gelato, "Gelato3", 1.80));
        bill.add(new MenuItem(MenuItem.itemType.Gelato, "Gelato4", 0.70));
        bill.add(new MenuItem(MenuItem.itemType.Gelato, "Gelato5", 1.30));
        bill.add(new MenuItem(MenuItem.itemType.Gelato, "Gelato6", 7.00));

        totalPrice = manager.getOrderPrice(bill, user, 0);
        assertEquals(13.25, totalPrice, 0);
    }
    
    // Se l’importo totale delle ordinazioni (Gelati e Budini)
    // supera i 50 euro viene fatto il 10% disconto
    @Test
    public void gelatiAndBudiniMoreThan50EurosShouldGet10PercentDiscountTest()
            throws TakeAwayBillException {

        bill.add(new MenuItem(MenuItem.itemType.Gelato, "Gelato1", 10.0));
        bill.add(new MenuItem(MenuItem.itemType.Gelato, "Gelato2", 20.0));
        bill.add(new MenuItem(MenuItem.itemType.Gelato, "Budino1", 30.0));

        totalPrice = manager.getOrderPrice(bill, user, 0);
        assertEquals(54, totalPrice, 0);
    }
    
    // Non è possibile avere un’ordinazione con più di 30 elementi
    @Test(expected = TakeAwayBillException.class)
    public void moreThan30ItemsShouldThrowExceptionTest() throws TakeAwayBillException {

        bill.add(new MenuItem(MenuItem.itemType.Gelato, "simpleGelato", 1.30));
        bill.add(new MenuItem(MenuItem.itemType.Budino, "simpleBudino", 2.50));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));
        bill.add(new MenuItem(MenuItem.itemType.Budino, "simpleBudino", 2.50));
        bill.add(new MenuItem(MenuItem.itemType.Gelato, "simpleGelato", 1.30));
        bill.add(new MenuItem(MenuItem.itemType.Budino, "simpleBudino", 2.50));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));
        bill.add(new MenuItem(MenuItem.itemType.Budino, "simpleBudino", 2.50));
        bill.add(new MenuItem(MenuItem.itemType.Gelato, "simpleGelato", 1.30));
        bill.add(new MenuItem(MenuItem.itemType.Budino, "simpleBudino", 2.50));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));
        bill.add(new MenuItem(MenuItem.itemType.Budino, "simpleBudino", 2.50));
        bill.add(new MenuItem(MenuItem.itemType.Gelato, "simpleGelato", 1.30));
        bill.add(new MenuItem(MenuItem.itemType.Budino, "simpleBudino", 2.50));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));
        bill.add(new MenuItem(MenuItem.itemType.Budino, "simpleBudino", 2.50));
        bill.add(new MenuItem(MenuItem.itemType.Gelato, "simpleGelato", 1.30));
        bill.add(new MenuItem(MenuItem.itemType.Budino, "simpleBudino", 2.50));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));
        bill.add(new MenuItem(MenuItem.itemType.Budino, "simpleBudino", 2.50));
        bill.add(new MenuItem(MenuItem.itemType.Gelato, "simpleGelato", 1.30));
        bill.add(new MenuItem(MenuItem.itemType.Budino, "simpleBudino", 2.50));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));
        bill.add(new MenuItem(MenuItem.itemType.Budino, "simpleBudino", 2.50));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));

        totalPrice = manager.getOrderPrice(bill, user, 0);
    }

}