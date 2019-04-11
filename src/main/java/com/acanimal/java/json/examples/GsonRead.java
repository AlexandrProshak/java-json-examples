package com.acanimal.java.json.examples;

import com.acanimal.java.json.examples.model.Good;
import com.acanimal.java.json.examples.model.Item;
import com.acanimal.java.json.examples.model.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import emoji4j.EmojiUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GsonRead {

    private static final String FILENAME = "/examples/kupal.json";
    private static final int PROFIT = 100;

    private static final File file = new File(GsonRead.class.getClass().getResource(FILENAME).getFile());
    private static final InputStream stream = GsonRead.class.getClass().getResourceAsStream(FILENAME);
    private static AtomicInteger id = new AtomicInteger(0);
    private static Map<String, Good> store = new HashMap<>();


    /**
     * With the object model read the whole JSON file is loaded on memory and the
     * application gets the desired element.
     */
    public static void readDom() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            Gson gson = new GsonBuilder().create();
            Item[] items = gson.fromJson(reader, Item[].class);
            int length = items.length;

            for (int i = 0; i < length; i++) {
                int index = items[i].getSizes().size() - 1;
                if (store.containsKey(items[i].getText())) {
                    store.get(items[i].getText())
                            .getPhotosUrls()
                            .add(items[i].getSizes().get(index).getUrl());
                } else {
                    Good good = new Good();
                    good.setId(id.incrementAndGet());
                    good.setDescription(items[i].getText());
                    good.setMainPhotoUrl(items[i].getSizes().get(index).getUrl());
                    store.put(items[i].getText(), good);

                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GsonRead.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(GsonRead.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void setPrice() {
        for (Map.Entry<String, Good> pair: store.entrySet()) {
            Good good = pair.getValue();
            good.setPrice(parsePrice(good.getDescription()));
            good.setStorePrice((parsePrice(good.getDescription())) + PROFIT);
        }
    }

    public static int parsePrice(String description) {
        String priceStr = description
                .substring(
                        description.indexOf("ОПТ") + 3,
                        description.indexOf("ГРН"));
        return Integer.parseInt(priceStr.trim());
    }

    /**
     * This is a mixed implementation based on stream and object model. The JSON
     * file is read in stream mode and each object is parsed in object model.
     * With this approach we avoid to load all the object in memory and we are only
     * loading one at a time.
     */
    public static void readStream() {
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
            Gson gson = new GsonBuilder().create();

            // Read file in stream mode
            reader.beginArray();
            while (reader.hasNext()) {
                // Read data into object model
                Person person = gson.fromJson(reader, Person.class);
                if (person.getId() == 0 ) {
                    System.out.println("Stream mode: " + person);
                }
                break;
            }
            reader.close();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GsonRead.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GsonRead.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Read file in object model and later in stream mode.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        long ti, tf;
//
//        ti = System.currentTimeMillis();
//        System.out.println("Start reading in object mode: " + ti);

        GsonRead.readDom();
        GsonRead.setPrice();
        GsonRead.parseDesc();
        GsonRead.parseUrls();
        GsonRead.parseAddUrls();

        for (Map.Entry<String, Good> pair: store.entrySet()) {
            Good good = pair.getValue();
            System.out.println("------------------------------------------");
            System.out.println(good.getMainPhotoUrl());
            System.out.println("id " + good.getId());
            System.out.println("price " + good.getStorePrice());
            System.out.println(good.getPureDesc());
            System.out.println(good.getPhotosUrls().toString());
            System.out.println("------------------------------------------");
        }


//        tf = System.currentTimeMillis();
//        System.out.println("Finish. Total time: " + (tf - ti));
//
//        ti = System.currentTimeMillis();
//        System.out.println("Start reading in stream mode: " + ti);
//        GsonRead.readStream();
//        tf = System.currentTimeMillis();
//        System.out.println("Finish. Total time: " + (tf - ti));

    }

    private static void parseAddUrls() {
        for (Map.Entry<String, Good> pair: store.entrySet()) {
            Good good = pair.getValue();
            List<String> photosUrls = good.getPhotosUrls();
            if (photosUrls.size() != 0) {
                Iterator<String> iterator = photosUrls.iterator();
                List<String> urls = new ArrayList<>();
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    urls.add(parsePhoto(next));
                }
                good.setPhotosUrls(urls);
            }
        }

    }

    private static String parsePhoto(String photoUrl) {
        String name;
        name = photoUrl.substring(photoUrl.lastIndexOf("/"), photoUrl.length())
                .replace("/", "");
        return name;
    }

    private static void parseUrls() {
        for (Map.Entry<String, Good> pair: store.entrySet()) {
            Good good = pair.getValue();
            String photoUrl = good.getMainPhotoUrl();
            good.setMainPhotoUrl(parsePhoto(photoUrl));
        }
    }

    private static void parseDesc() {
        for (Map.Entry<String, Good> pair: store.entrySet()) {
            Good good = pair.getValue();
            String description = good.getDescription();
            String desc = EmojiUtils.removeAllEmojis(
                    EmojiUtils.emojify(description)).toLowerCase()
                    .replace("new", "")
                    .replace("снова в наличии", "")
                    .replace("в наличии", "")
                    .replace("все размеры", "")
                    .replace("любимая модель", "")
                    .replace("снова в наличии", "")
                    .replace("снова в наличии все размеры", "")
                    .replace("2017", "")
                    .replace("&#10083;", "")
                    .replace("&#128420;", "")
                    .replace("&#129381;", "").trim();
            String priceString = desc.substring(desc.indexOf("опт"), desc.indexOf("грн")+3);
            desc = desc.replace(priceString, "");
            good.setPureDesc(desc);
        }
    }
}
