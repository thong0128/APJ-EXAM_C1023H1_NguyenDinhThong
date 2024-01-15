package controller;

import model.Product;
import storage.ReadWriteFile;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductController implements Serializable {
    public static ReadWriteFile readWriteFile = ReadWriteFile.getInstance();
    public static List<Product> productList = readWriteFile.readDataFromFile();
    public static void addProduct (Product product) {
        productList.add(product);
        readWriteFile.writeDataToFile(productList);
    }
    public static void removeProduct (int id) {
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            if (product.getId() == id) {
                productList.remove(product);
            }
        }
        readWriteFile.writeDataToFile(productList);
    }
    public static boolean contain (int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public static Product get(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    public static void showList () {
        for (Product product : productList) {
            System.out.println(product);
        }
    }
    public static void updateProduct(Product product, int id, String name, long price, int quantity, String description) {
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setDescription(description);
        readWriteFile.writeDataToFile(productList);
    }
    public static void sortByPriceIncrease() {
        Comparator<Product> comparator = Comparator.comparingLong(Product::getPrice);
        productList.sort(comparator);
    }
    public static void sortByPriceDecrease() {
        Comparator<Product> comparator = Comparator.comparingLong(Product::getPrice);
        productList.sort(comparator);
        Collections.reverse(productList);
    }
    public static Product highestPrice() {
        long max = 0;
        Product highestPrice = null;
        for (Product product : productList) {
            if (product.getPrice()>max) {
                highestPrice = product;
                max = product.getPrice();
            }
        }
        return highestPrice;
    }

}
