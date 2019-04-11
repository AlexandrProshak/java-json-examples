package com.acanimal.java.json.examples.model;

import java.util.List;

public class Item {
    String id;
    String album_id;
    String owner_id;
    String user_id;
    List<Sizes> sizes;
    String text;
    String date;

    public String getId() {
        return id;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public List<Sizes> getSizes() {
        return sizes;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Item{" +
                "sizes=" + sizes +
                ", text='" + text + '\'' +
                '}';
    }
}
