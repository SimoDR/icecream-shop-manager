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


        for (MenuItem i : itemsOrdered) {
            total += i.getPrice();
        }
        
        return total;

    }

}
