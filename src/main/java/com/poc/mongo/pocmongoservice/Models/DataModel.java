package com.poc.mongo.pocmongoservice.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;

public class DataModel {

    @Id
    private ObjectId id;
    @JsonProperty("site")
    private String site;
    @JsonProperty("product")
    private String product;
    @JsonProperty("qty")
    private String qty;
    @JsonProperty("price")
    private String price;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }


    public void setSite(String site) {
        this.site = site;
    }


    public String getProduct() {
        return product;
    }


    public void setProduct(String product) {
        this.product = product;
    }


    public String getQty() {
        return qty;
    }


    public void setQty(String qty) {
        this.qty = qty;
    }


    public String getPrice() {
        return price;
    }


    public void setPrice(String price) {
        this.price = price;
    }

}
