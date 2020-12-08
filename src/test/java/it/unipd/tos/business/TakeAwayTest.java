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
    
    // Se l’importo totale è inferiore a 10 € viene aggiunta una
    // commissione di 0,50 €
    @Test
    public void lessThen10EurosShouldGet50centsFeeTest() 
            throws TakeAwayBillException {

        bill.add(new MenuItem(MenuItem.itemType.Gelato, "simpleGelato", 1.30));
        bill.add(new MenuItem(MenuItem.itemType.Budino, "simpleBudino", 2.50));
        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "simpleBevanda", 3.00));
        totalPrice = manager.getOrderPrice(bill, user, 0);
        assertEquals(7.3, totalPrice, 0);
    }
    
    // Regalare massimo 10 gelati gratis (uno per ogni utente) a minorenni tra
    // le 18 e le 19
    
    // tutte le condizioni verificate
    @Test
    public void freeGelatiAllConditionsVerifiedTest() throws TakeAwayBillException {
        int time = 64850;

        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "freeBevanda", 11.00));
        User user1 = new User(1,"a", "b", LocalDate.of(2005, 1, 1));

        boolean getTrue = false;
        boolean getFalse = false;
        double priceTrue = 0.0;
        double priceFalse = 0.0;

        while (getTrue == false || getFalse == false) {
            double price = manager.getOrderPrice(bill, user1, time);

            if (manager.randomBoolean == true) {
                getTrue = true;
                priceTrue = price;
                manager.underageCustomers.remove(user1);
            } else {
                getFalse = true;
                priceFalse = price;
            }
        }
        assertEquals(priceTrue, 0, 0);
        assertEquals(priceFalse, 11, 0);

    }
    
    // non sono ancora le 18
    @Test
    public void freeGelatiTimeIsLessTest() throws TakeAwayBillException {
        int time = 60000;

        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "freeBevanda", 11.00));
        User user1 = new User(1,"a", "b", LocalDate.of(2005, 1, 1));
        double price = manager.getOrderPrice(bill, user1, time);
        assertEquals(price, 11, 0);
    }
    
    // sono già passate le 19
    @Test
    public void freeGelatiTimeIsMoreTest() throws TakeAwayBillException {
        int time = 70000;

        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "freeBevanda", 11.00));
        User user1 = new User(1,"a", "b", LocalDate.of(2005, 1, 1));
        double price = manager.getOrderPrice(bill, user1, time);
        assertEquals(price, 11, 0);
    }
    
    // il cliente non è minorenne
    @Test
    public void freeGelatiUserIsNotUnderageTest() throws TakeAwayBillException {
        int time = 64850;

        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "freeBevanda", 11.00));
        User user1 = new User(1,"a", "b", LocalDate.of(1990, 1, 1));
        double price = manager.getOrderPrice(bill, user1, time);
        assertEquals(price, 11, 0);
    }
    
    // il cliente ha già avuto il suo alimento gratis
    @Test
    public void freeGelatiUserAlreadyGotDiscountTest() throws TakeAwayBillException {
        int time = 64850;

        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "freeBevanda", 11.00));
        User user1 = new User(1,"a", "b", LocalDate.of(2005, 1, 1));
        manager.underageCustomers.add(user1);
        double price = manager.getOrderPrice(bill, user1, time);
        assertEquals(price, 11, 0);
    }
    
    // sono già stati dati 10 alimenti gratis
    @Test
    public void freeGelatiAlreadyGiven10AwayTest() throws TakeAwayBillException {
        int time = 64850;

        bill.add(new MenuItem(MenuItem.itemType.Bevanda, "freeBevanda", 11.00));
        User user1 = new User(1,"a", "b", LocalDate.of(2005, 1, 1));
        manager.underageCustomers.add(user1);
        manager.underageCustomers.add(user1);
        manager.underageCustomers.add(user1);
        manager.underageCustomers.add(user1);
        manager.underageCustomers.add(user1);
        manager.underageCustomers.add(user1);
        manager.underageCustomers.add(user1);
        manager.underageCustomers.add(user1);
        manager.underageCustomers.add(user1);
        manager.underageCustomers.add(user1);

        User user2 = new User(2,"c", "d", LocalDate.of(2004, 1, 1));

        double price = manager.getOrderPrice(bill, user2, time);

        assertEquals(price, 11, 0);
    }
    

}