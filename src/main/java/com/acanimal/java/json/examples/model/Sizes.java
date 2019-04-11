package com.acanimal.java.json.examples.model;

public class Sizes {
    String type;
    String url;
    String width;
    String height;

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "type='" + type + '\'' +
               "url ='" + url + '\'' +
               System.lineSeparator();
    }
}
