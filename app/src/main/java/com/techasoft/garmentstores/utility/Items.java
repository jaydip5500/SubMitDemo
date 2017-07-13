package com.techasoft.garmentstores.utility;

/**
 * Created by JAYDIP PATEL on 12-07-2017.
 */

public class Items {


    private String name;
    private String price;
    private String quantity;
    private String amount;

    public Items() {
        super();
    }

    public Items(String name, String price, String quantity, String amount) {
        super();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return String.valueOf(amount);
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
