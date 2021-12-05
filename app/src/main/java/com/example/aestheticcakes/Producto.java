package com.example.aestheticcakes;

import android.graphics.Bitmap;

public class Producto {

    private int productID;
    private String name;
    private Double price;
    private String image;
    private String imageSlider1;
    private String imageSlider2;
    private String productDescription;
    private int categoryID;
    private int productRate;

    public Producto(int productID, String name, Double price, String image, String imageSlider1, String imageSlider2, String productDescription, int categoryID, int productRate) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.image = image;
        this.imageSlider1 = imageSlider1;
        this.imageSlider2 = imageSlider2;
        this.productDescription = productDescription;
        this.categoryID = categoryID;
        this.productRate = productRate;
    }



    public String getImageSlider1() {
        return imageSlider1;
    }

    public void setImageSlider1(String imageSlider1) {
        this.imageSlider1 = imageSlider1;
    }

    public String getImageSlider2() {
        return imageSlider2;
    }

    public void setImageSlider2(String imageSlider2) {
        this.imageSlider2 = imageSlider2;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }


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


    public int getProductRate() { return productRate; }

    public void setProductRate(int productRate) { this.productRate = productRate; }




    public Producto(){

    }



}
