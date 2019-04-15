package com.acanimal.java.json.examples.model;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class RowColumnSet {

    private HSSFCell product_id;
    private HSSFCell name;
    private HSSFCell categories;
    private HSSFCell sku;
    private HSSFCell upc;
    private HSSFCell ean;
    private HSSFCell jan;
    private HSSFCell isbn;
    private HSSFCell mpn;
    private HSSFCell location;
    private HSSFCell quantity;
    private HSSFCell model;
    private HSSFCell manufacturer;
    private HSSFCell image_name;
    private HSSFCell shipping;
    private HSSFCell price;
    private HSSFCell points;
    private HSSFCell date_added;
    private HSSFCell date_modified;
    private HSSFCell date_available;
    private HSSFCell weight;
    private HSSFCell weight_unit;
    private HSSFCell length;
    private HSSFCell width;
    private HSSFCell height;
    private HSSFCell length_unit;
    private HSSFCell status;
    private HSSFCell tax_class_id;
    private HSSFCell seo_keyword;
    private HSSFCell description;
    private HSSFCell meta_title;
    private HSSFCell meta_description;
    private HSSFCell meta_keywords;
    private HSSFCell layout;
    private HSSFCell related_ids;
    private HSSFCell tags;
    private HSSFCell stock_status_id;
    private HSSFCell store_ids;
    private HSSFCell sort_order;
    private HSSFCell subtract;
    private HSSFCell minimum;

    public RowColumnSet(HSSFRow row) {
        this.product_id = row.createCell(0);
        this.name = row.createCell(1);
        this.categories = row.createCell(2);
        this.sku = row.createCell(3);
        this.upc = row.createCell(4);
        this.ean = row.createCell(5);
        this.jan = row.createCell(6);
        this.isbn = row.createCell(7);
        this.mpn = row.createCell(8);
        this.location = row.createCell(9);
        this.quantity = row.createCell(10);
        this.model = row.createCell(11);
        this.manufacturer = row.createCell(12);
        this.image_name = row.createCell(13);
        this.shipping = row.createCell(14);
        this.price = row.createCell(15);
        this.points = row.createCell(16);
        this.date_added = row.createCell(17);
        this.date_modified = row.createCell(18);
        this.date_available = row.createCell(19);
        this.weight = row.createCell(20);
        this.weight_unit = row.createCell(21);
        this.length = row.createCell(22);
        this.width = row.createCell(23);
        this.height = row.createCell(24);
        this.length_unit = row.createCell(25);
        this.status = row.createCell(26);
        this.tax_class_id = row.createCell(27);
        this.seo_keyword = row.createCell(28);
        this.description = row.createCell(29);
        this.meta_title = row.createCell(30);
        this.meta_description = row.createCell(31);
        this.meta_keywords = row.createCell(32);
        this.layout = row.createCell(35);
        this.related_ids = row.createCell(36);
        this.tags = row.createCell(37);
        this.stock_status_id = row.createCell(33);
        this.store_ids = row.createCell(34);
        this.sort_order = row.createCell(38);
        this.subtract = row.createCell(39);
        this.minimum = row.createCell(40);
        this.setDefault();
    }

    private void setDefault() {
        this.categories.setCellValue("cat");
        this.quantity.setCellValue(100);
        this.shipping.setCellValue("no");
        this.points.setCellValue(0);
        this.weight.setCellValue(0);
        this.weight_unit.setCellValue("кг");
        this.length.setCellValue(0);
        this.width.setCellValue(1);
        this.height.setCellValue(1);
        this.length_unit.setCellValue("см");
        this.status.setCellValue("true");
        this.tax_class_id.setCellValue(0);
        this.stock_status_id.setCellValue(7);
        this.store_ids.setCellValue(0);
        this.sort_order.setCellValue(1);
        this.subtract.setCellValue("true");
        this.minimum.setCellValue(1);
    }

    public HSSFCell getProduct_id() {
        return product_id;
    }

    public void setProduct_id(HSSFCell product_id) {
        this.product_id = product_id;
    }

    public HSSFCell getName() {
        return name;
    }

    public void setName(HSSFCell name) {
        this.name = name;
    }

    public HSSFCell getCategories() {
        return categories;
    }

    public void setCategories(HSSFCell categories) {
        this.categories = categories;
    }

    public HSSFCell getSku() {
        return sku;
    }

    public void setSku(HSSFCell sku) {
        this.sku = sku;
    }

    public HSSFCell getUpc() {
        return upc;
    }

    public void setUpc(HSSFCell upc) {
        this.upc = upc;
    }

    public HSSFCell getEan() {
        return ean;
    }

    public void setEan(HSSFCell ean) {
        this.ean = ean;
    }

    public HSSFCell getJan() {
        return jan;
    }

    public void setJan(HSSFCell jan) {
        this.jan = jan;
    }

    public HSSFCell getIsbn() {
        return isbn;
    }

    public void setIsbn(HSSFCell isbn) {
        this.isbn = isbn;
    }

    public HSSFCell getMpn() {
        return mpn;
    }

    public void setMpn(HSSFCell mpn) {
        this.mpn = mpn;
    }

    public HSSFCell getLocation() {
        return location;
    }

    public void setLocation(HSSFCell location) {
        this.location = location;
    }

    public HSSFCell getQuantity() {
        return quantity;
    }

    public void setQuantity(HSSFCell quantity) {
        this.quantity = quantity;
    }

    public HSSFCell getModel() {
        return model;
    }

    public void setModel(HSSFCell model) {
        this.model = model;
    }

    public HSSFCell getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(HSSFCell manufacturer) {
        this.manufacturer = manufacturer;
    }

    public HSSFCell getImage_name() {
        return image_name;
    }

    public void setImage_name(HSSFCell image_name) {
        this.image_name = image_name;
    }

    public HSSFCell getShipping() {
        return shipping;
    }

    public void setShipping(HSSFCell shipping) {
        this.shipping = shipping;
    }

    public HSSFCell getPrice() {
        return price;
    }

    public void setPrice(HSSFCell price) {
        this.price = price;
    }

    public HSSFCell getPoints() {
        return points;
    }

    public void setPoints(HSSFCell points) {
        this.points = points;
    }

    public HSSFCell getDate_added() {
        return date_added;
    }

    public void setDate_added(HSSFCell date_added) {
        this.date_added = date_added;
    }

    public HSSFCell getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(HSSFCell date_modified) {
        this.date_modified = date_modified;
    }

    public HSSFCell getDate_available() {
        return date_available;
    }

    public void setDate_available(HSSFCell date_available) {
        this.date_available = date_available;
    }

    public HSSFCell getWeight() {
        return weight;
    }

    public void setWeight(HSSFCell weight) {
        this.weight = weight;
    }

    public HSSFCell getWeight_unit() {
        return weight_unit;
    }

    public void setWeight_unit(HSSFCell weight_unit) {
        this.weight_unit = weight_unit;
    }

    public HSSFCell getLength() {
        return length;
    }

    public void setLength(HSSFCell length) {
        this.length = length;
    }

    public HSSFCell getWidth() {
        return width;
    }

    public void setWidth(HSSFCell width) {
        this.width = width;
    }

    public HSSFCell getHeight() {
        return height;
    }

    public void setHeight(HSSFCell height) {
        this.height = height;
    }

    public HSSFCell getLength_unit() {
        return length_unit;
    }

    public void setLength_unit(HSSFCell length_unit) {
        this.length_unit = length_unit;
    }

    public HSSFCell getStatus() {
        return status;
    }

    public void setStatus(HSSFCell status) {
        this.status = status;
    }

    public HSSFCell getTax_class_id() {
        return tax_class_id;
    }

    public void setTax_class_id(HSSFCell tax_class_id) {
        this.tax_class_id = tax_class_id;
    }

    public HSSFCell getSeo_keyword() {
        return seo_keyword;
    }

    public void setSeo_keyword(HSSFCell seo_keyword) {
        this.seo_keyword = seo_keyword;
    }

    public HSSFCell getDescription() {
        return description;
    }

    public void setDescription(HSSFCell description) {
        this.description = description;
    }

    public HSSFCell getMeta_title() {
        return meta_title;
    }

    public void setMeta_title(HSSFCell meta_title) {
        this.meta_title = meta_title;
    }

    public HSSFCell getMeta_description() {
        return meta_description;
    }

    public void setMeta_description(HSSFCell meta_description) {
        this.meta_description = meta_description;
    }

    public HSSFCell getMeta_keywords() {
        return meta_keywords;
    }

    public void setMeta_keywords(HSSFCell meta_keywords) {
        this.meta_keywords = meta_keywords;
    }

    public HSSFCell getLayout() {
        return layout;
    }

    public void setLayout(HSSFCell layout) {
        this.layout = layout;
    }

    public HSSFCell getTags() {
        return tags;
    }

    public void setTags(HSSFCell tags) {
        this.tags = tags;
    }

    public HSSFCell getStock_status_id() {
        return stock_status_id;
    }

    public void setStock_status_id(HSSFCell stock_status_id) {
        this.stock_status_id = stock_status_id;
    }

    public HSSFCell getStore_ids() {
        return store_ids;
    }

    public void setStore_ids(HSSFCell store_ids) {
        this.store_ids = store_ids;
    }

    public HSSFCell getSort_order() {
        return sort_order;
    }

    public void setSort_order(HSSFCell sort_order) {
        this.sort_order = sort_order;
    }

    public HSSFCell getSubtract() {
        return subtract;
    }

    public void setSubtract(HSSFCell subtract) {
        this.subtract = subtract;
    }

    public HSSFCell getMinimum() {
        return minimum;
    }

    public void setMinimum(HSSFCell minimum) {
        this.minimum = minimum;
    }
}
