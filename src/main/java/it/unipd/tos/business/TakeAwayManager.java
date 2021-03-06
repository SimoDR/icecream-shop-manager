////////////////////////////////////////////////////////////////////
// [SIMONE] [DE RENZIS] [1187510]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.business.exception.TakeAwayBillException;



public class TakeAwayManager implements TakeAwayBill {
    
    List<User> underageCustomers = new ArrayList<User>();
    boolean randomBoolean = false;

    public double getOrderPrice(List<MenuItem> itemsOrdered, 
            User user, int time) 
            throws TakeAwayBillException {

        if (itemsOrdered == null) {
            throw new TakeAwayBillException("The bill doesn't exist");
        }

        if (itemsOrdered.contains(null)) {
            throw new TakeAwayBillException("The bill contains a null element");
        }

        double total = 0.0;
        double totalGelatiBudini = 0.0;
        int numGelati = 0;
        double minPriceGelato = Double.MAX_VALUE;

        for (MenuItem i : itemsOrdered) {
            total += i.getPrice();
            if (i.getItemType() == MenuItem.itemType.Gelato) {
                numGelati++;
                totalGelatiBudini += i.getPrice();
                if (i.getPrice() < minPriceGelato) {
                    minPriceGelato = i.getPrice();
                }
            }
            if (i.getItemType() == MenuItem.itemType.Budino) {
                totalGelatiBudini += i.getPrice();
            }
        }

        // se presi più di 5 gelati sconto sul gelato meno costoso
        if (numGelati > 5) {
            total -= 0.5 * minPriceGelato;
        }

        // Se l’importo totale delle ordinazioni (Gelati e Budini)
        // supera i 50 euro viene fatto il 10% disconto
        if (totalGelatiBudini > 50) {
            total -= 0.1 * total;
        }
        
        // Non è possibile avere un’ordinazione con più di 30 elementi
        if (itemsOrdered.size() > 30) {
            throw new TakeAwayBillException("Can't order more than 30 items");
        }
        
        // Se l’importo totale è inferiore a 10 € viene aggiunta una
        // commissione di 0,50 €
        if (total < 10) {
            total += 0.50;
        }
        
        // Regalare massimo 10 gelati gratis (uno per ogni utente) 
        // a minorenni tra le 18 e le 19
        if (time > 64800 && time < 68400 && user.getAge() < 18 &&
                !underageCustomers.contains(user) &&
                underageCustomers.size() < 10) {

            Random rand = new Random();
            randomBoolean = rand.nextBoolean();
            int randomInt = rand.nextInt(itemsOrdered.size());

            if (randomBoolean == true) {
                underageCustomers.add(user);
                double discount = itemsOrdered.get(randomInt).getPrice();
                total -= discount;
            }
        }
        
        return total;

    }

}
