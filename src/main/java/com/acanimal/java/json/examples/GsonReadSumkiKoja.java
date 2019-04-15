package com.acanimal.java.json.examples;

import com.acanimal.java.json.examples.model.Good;
import com.acanimal.java.json.examples.model.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import emoji4j.EmojiUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GsonReadSumkiKoja {

    private static final String FILENAME = "/examples/sumki_koja.json";
    private static final String BOOK_PATH = "/Users/AlxMan/projects/java-json-examples/src/main/resources/examples/book";
    private static final String ALBUMS_PATH = "catalog/"+ "sumki_koja/";
    private static final int PROFIT = 300;
    private static final int INVOICE_GOODS_ROW_INDEX = 1;
    private static final int INVOICE_ADD_IMG_ROW_INDEX = 1;
    private static final int GOODS_START_ID = 499;

    private static final File file = new File(GsonReadSumkiKoja.class.getClass().getResource(FILENAME).getFile());

    private static AtomicInteger position = new AtomicInteger(GOODS_START_ID);
    private static Map<String, Good> store = new LinkedHashMap<>();

    /**
     * With the object model read the whole JSON file is loaded on memory and the
     * application gets the desired element.
     */
    private static void readDom() {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Gson gson = new GsonBuilder().create();
            Item[] items = gson.fromJson(reader, Item[].class);
            int length = items.length;

            for (int i = 0; i < length; i++) {
                if (items[i].getSizes().size() > 0) {
                    int index = items[i].getSizes().size() - 1;
                    if (store.containsKey(items[i].getText())) {
                        store.get(items[i].getText())
                                .getPhotosUrls()
                                .add(items[i].getSizes().get(index).getUrl());
                    } else {
                        Good good = new Good();
                        int id = position.incrementAndGet();
                        good.setId(id);
                        //good.setModel(id + 25);
                        good.setDescription(items[i].getText().toLowerCase());
                        good.setModel(parseModel(good.getDescription()));
                        good.setMainPhotoPath(items[i].getSizes().get(index).getUrl());
                        store.put(items[i].getText(), good);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GsonReadSumkiKoja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String parseModel(String description) {
        if (description.contains("код1") & description.contains("код2")) {
            String modelStr = description
                    .substring(
                            description.indexOf("код1") + 4,
                            description.indexOf("код2"));
            return modelStr.trim();
        }
        return Integer.toString(position.get());

    }

    private static void setPrice() {
        for (Map.Entry<String, Good> pair: store.entrySet()) {
            Good good = pair.getValue();
            good.setPrice(parsePrice(good.getDescription()));
            good.setStorePrice((parsePrice(good.getDescription())) + PROFIT);
        }
    }

    private static int parsePrice(String description) {
        if (description.contains("цена:") & description.contains("грн")) {
            String priceStr = description
                    .substring(
                            description.indexOf("цена:") + 5,
                            description.indexOf("грн"));
            System.out.println(description);
            return Integer.parseInt(priceStr.trim());
        }
        return -100;
    }

    private static void capitalizeFirst() {
        for (Map.Entry<String, Good> pair : store.entrySet()) {
            Good good = pair.getValue();
            String desc = good.getPureDesc();
            String name = good.getName();
            String output = desc.substring(0, 1).toUpperCase() + desc.substring(1);
            good.setPureDesc(output);
            String capName = name.substring(0, 1).toUpperCase() + name.substring(1);
            good.setName(capName);
        }
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
        return ALBUMS_PATH + name;
    }

    private static void parseUrls() {
        for (Map.Entry<String, Good> pair: store.entrySet()) {
            Good good = pair.getValue();
            String photoUrl = good.getMainPhotoUrl();
            good.setMainPhotoPath(parsePhoto(photoUrl));
        }
    }

    private static void parseDesc() {
        for (Map.Entry<String, Good> pair: store.entrySet()) {
            Good good = pair.getValue();
            String description = good.getDescription();
            String desc = EmojiUtils.removeAllEmojis(
                    EmojiUtils.emojify(description))
                    .replace("new", "")
                    .replace("снова в наличии", "")
                    .replace("только у нас", "")
                    .replace("!", "")
                    .replace("!!!!", "")
                    .replace("в наличии", "")
                    .replace("все размеры", "")
                    .replace("распродажа", "")
                    .replace("количество ограничено", "")
                    .replace("любимая модель", "")
                    .replace("лучшая цена:", "цена:")
                    .replace("снова в наличии", "")
                    .replace("снова в наличии все размеры", "")
                    .replace("2017", "")
                    .replace("&#10083;", "")
                    .replace("&#128420;", "")
                    .replace("&#129392;", "")
                    .replace("&#128525;", "")
                    .replace("&#128293;", "")
                    .replace("&#128163;", "")
                    .replace("&#129381;", "").trim();
            //String priceString = desc.substring(desc.indexOf("опт"), desc.indexOf("грн")+3);
            //desc = desc.replace(priceString, "");
//            good.setDescription(desc);
            good.setPureDesc(desc);
        }
    }

    private static void parseName() {
        for (Map.Entry<String, Good> pair: store.entrySet()) {
            Good good = pair.getValue();
            good.setName(good.getPureDesc().substring(0, good.getPureDesc().length() / 3));
        }
    }

    private static void inspect() {
        for (Map.Entry<String, Good> pair: store.entrySet()) {
            Good good = pair.getValue();
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("main photo:         " + good.getMainPhotoUrl());
            System.out.println("position:           " + good.getId());
            System.out.println("model:              " + good.getModel());
            System.out.println("name:               " + good.getName());
            System.out.println("price:              " + good.getStorePrice());
            System.out.println("description:        " + good.getPureDesc());
//            System.out.println("description:        " + good.getDescription());
            System.out.println("additional photo:   " + Arrays.deepToString(good.getPhotosUrls().toArray()));
            System.out.println("=============================================================================");
        }
    }

    /**
     * Read file in object model and later in stream mode.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long ti, tf;

        ti = System.currentTimeMillis();
        System.out.println("Start reading ... ");

        GsonReadSumkiKoja.readDom();
        GsonReadSumkiKoja.parseDesc();
        GsonReadSumkiKoja.setPrice();
//        GsonReadSumkiKoja.parseName();
        GsonReadSumkiKoja.parseUrls();
        GsonReadSumkiKoja.parseAddUrls();
//        GsonReadSumkiKoja.capitalizeFirst();
//        ExcelWriter.write(store, BOOK_PATH + ".xls", INVOICE_GOODS_ROW_INDEX, INVOICE_ADD_IMG_ROW_INDEX);

//        inspect();

        tf = System.currentTimeMillis();
        System.out.println("Finish. Total time: " + (tf - ti));
    }
}
