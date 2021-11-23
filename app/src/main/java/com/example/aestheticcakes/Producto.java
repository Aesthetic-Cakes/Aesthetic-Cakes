package com.example.aestheticcakes;

public class Producto {


    private int productID;
    private String name;
    private Double price;
    private String image;
    private int categoryID;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }



    public Producto(){

    }

    public Producto(int productID, String name, Double price, String image, int categoryID) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.image = image;
        this.categoryID = categoryID;
    }


}
