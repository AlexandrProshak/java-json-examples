package com.acanimal.java.json.examples.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Good {

    private int id;
    private String name;
    private String description;
    private String pureDesc;
    private int price;
    private int storePrice;

    private String mainPhotoUrl;
    private List<String> photosUrls = new ArrayList<>();

    public int getStorePrice() {
        return storePrice;
    }

    public void setStorePrice(int storePrice) {
        this.storePrice = storePrice;
    }

    public String getPureDesc() {
        return pureDesc;
    }

    public void setPureDesc(String pureDesc) {
        this.pureDesc = pureDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMainPhotoUrl() {
        return mainPhotoUrl;
    }

    public void setMainPhotoUrl(String mainPhotoUrl) {
        this.mainPhotoUrl = mainPhotoUrl;
    }

    public List<String> getPhotosUrls() {
        return photosUrls;
    }

    public void setPhotosUrls(List<String> photosUrls) {
        this.photosUrls = photosUrls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return id == good.id &&
                price == good.price &&
                storePrice == good.storePrice &&
                Objects.equals(name, good.name) &&
                Objects.equals(description, good.description) &&
                Objects.equals(pureDesc, good.pureDesc) &&
                Objects.equals(mainPhotoUrl, good.mainPhotoUrl) &&
                Objects.equals(photosUrls, good.photosUrls);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, pureDesc, price, storePrice, mainPhotoUrl, photosUrls);
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pureDesc='" + pureDesc + '\'' +
                ", storePrice=" + storePrice +
                ", mainPhotoUrl='" + mainPhotoUrl + '\'' +
                ", photosUrls=" + photosUrls +
                '}';
    }
}
