package com.scrapper.model;

public class Product {

    String price;
    String name;
    String url;
    String catName;

    public Product(){

    }

    public Product(String price,String name,String url,String catName) {
        this.price = price;
        this.name = name;
        this.url = url;
        this.catName = catName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

}
