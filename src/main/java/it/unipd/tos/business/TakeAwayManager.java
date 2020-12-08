////////////////////////////////////////////////////////////////////
// [SIMONE] [DE RENZIS] [1187510]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;


import java.util.List;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.business.exception.TakeAwayBillException;



public class TakeAwayManager implements TakeAwayBill {


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
        
        return total;

    }

}
