package com.techasoft.garmentstores.model;

/**
 * Created by JAYDIP PATEL on 12-07-2017.
 */

public class Product {


    private String name;
    private String price;
    private String quantity;
    private double amount;

    public Product() {
        super();
    }

    public Product( String name, String price,String quantity, double amount) {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + 4;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
       /* if (id != other.id)
            return false;*/
        return true;
    }

    @Override
    public String toString() {
        return "Product [name=" +name + ", price=" + price + ", quantity="
                + quantity + ", price=" + amount + "]";
    }
}

