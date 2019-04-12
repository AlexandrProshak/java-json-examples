package com.acanimal.java.json.examples;

import com.acanimal.java.json.examples.model.Good;
import com.acanimal.java.json.examples.model.RowColumnSet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExcelWriter {

    public static void write(Map<String, Good> store, String bookLocationPath, int indexRow, int addImRowIndex) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet products = workbook.createSheet("Products");
        HSSFSheet additionalImages = workbook.createSheet("AdditionalImages");
        Set<Map.Entry<String, Good>> entries = store.entrySet();
        Iterator<Map.Entry<String, Good>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Good> next = iterator.next();
            Good good = next.getValue();
            RowColumnSet columnSet = new RowColumnSet(products.createRow(indexRow++));

            HSSFCell productIdCell = columnSet.getProduct_id();
            HSSFCell nameCell = columnSet.getName();
            HSSFCell model = columnSet.getModel();
            HSSFCell image_name = columnSet.getImage_name();
            HSSFCell price = columnSet.getPrice();
            HSSFCell description = columnSet.getDescription();
            HSSFCell meta_title = columnSet.getMeta_title();
            HSSFCell meta_description = columnSet.getMeta_description();
            HSSFCell meta_keywords = columnSet.getMeta_keywords();
            HSSFCell tags = columnSet.getTags();

            nameCell.setCellValue(good.getName());
            productIdCell.setCellValue(good.getId());
            model.setCellValue(good.getModel());
            image_name.setCellValue(good.getMainPhotoUrl());
            price.setCellValue(good.getStorePrice());
            description.setCellValue(good.getPureDesc());
            meta_title.setCellValue(good.getName());
            meta_keywords.setCellValue(good.getName());
            meta_description.setCellValue(good.getPureDesc());
            tags.setCellValue(good.getName());

            List<String> photosUrls = good.getPhotosUrls();
            Iterator<String> iterator1 = photosUrls.iterator();
            int addImOrder = 0;

            while (iterator1.hasNext()) {
                String url = iterator1.next();

                HSSFRow addImRow = additionalImages.createRow(addImRowIndex++);

                HSSFCell product_id_im = addImRow.createCell(0);
                HSSFCell image = addImRow.createCell(1);
                HSSFCell sort_order_im = addImRow.createCell(2);

                product_id_im.setCellValue(good.getId());
                image.setCellValue(url);
                sort_order_im.setCellValue(addImOrder++);
            }
        }
        try (FileOutputStream out = new FileOutputStream(new File(bookLocationPath))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
