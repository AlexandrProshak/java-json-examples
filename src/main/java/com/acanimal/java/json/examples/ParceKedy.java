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

public class ParceKedy {

    private static final String FILENAME = "/examples/kedy_2.json";
    private static final String BOOK_PATH = "/Users/AlxMan/projects/java-json-examples/src/main/resources/examples/book";
    private static final String ALBUMS_PATH = "catalog/"+ "kedy/";
    private static final int PROFIT = 200;
    private static final int INVOICE_GOODS_ROW_INDEX = 1;
    private static final int INVOICE_ADD_IMG_ROW_INDEX = 1;
    private static final int GOODS_START_ID = 499;

    private static final File file = new File(ParceKedy.class.getClass().getResource(FILENAME).getFile());

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
            Logger.getLogger(ParceKedy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String parseModel(String description) {
        if (description.contains("код")) {
            String modelStr = description
                    .substring(
                            description.indexOf("код") + 4,
                            description.indexOf("код") + 9);
            modelStr = modelStr.replace(".", "");
            return modelStr.trim();
        }
        return "invalid";

    }

    private static void setPrice() {
        for (Map.Entry<String, Good> pair: store.entrySet()) {
            Good good = pair.getValue();
            String priceStr = parsePrice(good.getPureDesc());
            good.setPrice(ParceKedy.getPrice(priceStr));
            good.setStorePrice(ParceKedy.getPrice(priceStr) + PROFIT);
        }
    }

    private static int getPrice(String str) {
        int price = - 1000;
        String tmp;
        if (!"invalid".equals(str)) {
            tmp = str.substring(str.indexOf("опт") + 3, str.indexOf("грн)"));
            price = Integer.parseInt(tmp.trim());
        }
        return price;
    }

    private static String parsePrice(String description) {
        if (description.contains("цена") & description.contains("грн)")) {
            String priceStr = description
                    .substring(
                            description.indexOf("цена"),
                            (description.indexOf("грн)") + 4));
            return priceStr.trim();
        }
        return "invalid";
    }

    private static void capitalizeFirst() {
        for (Map.Entry<String, Good> pair : store.entrySet()) {
            Good good = pair.getValue();
            String desc = good.getPureDesc().trim();
            String name = good.getName().trim();
            if (null != desc & desc.length() > 1) {
                String tmpDesc = desc.substring(0, 1);
                if (tmpDesc.equals(".")) {
                    desc = desc.replaceFirst(".", "");
                }
                String output = desc.substring(0, 1).toUpperCase() + desc.substring(1);
                good.setPureDesc(output);
            }
            if (null != name & name.length() > 1) {
                String tmpName = name.substring(0, 1);
                if (tmpName.equals(".")) {
                    name = name.replaceFirst(".", "");
                }
                String capName = name.substring(0, 1).toUpperCase() + name.substring(1);
                good.setName(capName);
            }
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
                    .replace("розница", "")
                    .replace("( опт", "(опт")
                    .replace("опт -", "опт")
                    .replace("грн(", "грн (")
                    .replace("грн )", "грн)")
                    .replace("грн ", " грн ")
                    .replace("  грн ", " грн ")
                    .replace("грн,", "грн")
//                    .replace("\r", " ")
//                    .replace("\n", " ")
//                    .replace("\r\n", " ")
//                    .replace("размеры:", "размеры:" + System.lineSeparator())
//                    .replace("размерная сетка:", "размерная сетка:" + System.lineSeparator())
//                    .replace("среднюю ножку", "среднюю ножку" + System.lineSeparator())
//                    .replace("размер в размер", "размер в размер" + System.lineSeparator())
                    .replace("материал:", "Материал:")
                    .replace("матераил:", "Материал:")
                    .replace("количество ограничено", "")
                    .replace("любимая модель", "")
                    .replace("новая модель", "")
                    .replace("новая", "")
                    .replace("лучшая цена", "цена")
                    .replace("цена:", "цена")
                    .replace("( опт", "(опт")
                    .replace(" ( ", " (")
                    .replace("2017", "")
                    .replace("на узкую стопу не подходят \n" +
                            "полномерные размеры:", "На узкую стопу не подходят, полномерные размеры:")
                    .replace(". Материал:", ". \n Материал:")
                    .replace("&#10083;", "")
                    .replace("&#128420;", "")
                    .replace("&#129392;", "")
                    .replace("&#128525;", "")
                    .replace("&#128293;", "")
                    .replace("&#128163;", "")
                    .replace("&#127995;", "")
                    .replace("&#128077;", "")
                    .replace("&#9989;", "")
                    .replace("&#127481;", "")
                    .replace("&#127477;", "")
                    .replace("&#127473;", "")
                    .replace("&#127469;", "")
                    .replace(" .", ".")
                    .replace(". .", ". ")
                    .replace(" -", " - ")
                    .replace("- ", " - ")
                    .replace("  ", " ")
                    .replace("   ", " ")
                    .replace("см", " см")
                    .replace("&#129381;", "").trim();
            desc = desc.replaceFirst("\n", "");
            good.setPureDesc(desc);
        }
    }

    private static void parseName() {
        for (Map.Entry<String, Good> pair: store.entrySet()) {
            Good good = pair.getValue();
            String pureDesc = good.getPureDesc();
            if (pureDesc.contains("код") & pureDesc.contains("цена")) {
                good.setName(pureDesc.substring(pureDesc.indexOf("код") + 8,
                                pureDesc.indexOf("цена")).trim());
            } else {
                good.setName("invalid");
            }
        }
    }

    private static void inspect() {
        for (Map.Entry<String, Good> pair: store.entrySet()) {
            Good good = pair.getValue();
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("main photo: " + good.getMainPhotoUrl());
            System.out.println("position: " + good.getId());
            System.out.println("model: " + good.getModel());
            System.out.println("name: " + good.getName());
            System.out.println("price: " + good.getStorePrice());
            System.out.println("pure desc: " + good.getPureDesc());
            System.out.println("+_+_+_+_+_+_");
            System.out.println("description:        " + good.getDescription());
            System.out.println("additional photo: " + Arrays.deepToString(good.getPhotosUrls().toArray()));
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

        ParceKedy.readDom();
        ParceKedy.parseDesc();
        ParceKedy.setPrice();
        ParceKedy.parseName();
        ParceKedy.parseUrls();
        ParceKedy.parseAddUrls();
        ParceKedy.setDescription();
        ParceKedy.capitalizeFirst();
        ExcelWriter.write(store, BOOK_PATH + ".xls", INVOICE_GOODS_ROW_INDEX, INVOICE_ADD_IMG_ROW_INDEX);

        System.out.println();
        inspect();
        tf = System.currentTimeMillis();
        System.out.println("Finish. Total time: " + (tf - ti));
    }

    private static void setDescription() {
        for (Map.Entry<String, Good> pair : store.entrySet()) {
            Good good = pair.getValue();
            String description = good.getPureDesc();
            if (description.length() > 10) {
                String tmp = description.substring(description.indexOf("код") + 8, description.length()).trim();
                String priseStr = ParceKedy.parsePrice(description);
                tmp = tmp
                        .replace(priseStr, "")
                        .replace("грн)", "")
                        .replace("  ", " ")
                        .replace(". .", ". ")
                        .replaceFirst("\n", "")
                        .replace("   ", " ");
                good.setPureDesc(tmp);
            }
        }
    }
}
