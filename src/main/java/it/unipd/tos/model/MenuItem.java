////////////////////////////////////////////////////////////////////
// [SIMONE] [DE RENZIS] [1187510]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

public class MenuItem {

    public enum itemType {
        Gelato, Budino, Bevanda
    }

    private itemType item;
    private String name;
    private double price;

    public MenuItem(itemType item, String name, double price) {
        this.item = item;
        this.name = name;
        this.price = price;
    }

    public itemType getItemType() {
        return item;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}