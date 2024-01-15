package storage;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ReadWriteFile {
    private static ReadWriteFile instance;

    public static ReadWriteFile getInstance() {
        if (instance==null) {
            instance = new ReadWriteFile();
        }
        return instance;
    }
    public void writeDataToFile(List<Product> productList) {
        File file = new File("data/products.csv");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(productList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Product> readDataFromFile() {
        List<Product> productList = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/products.csv"))) {
            productList = (List<Product>) ois.readObject();
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
