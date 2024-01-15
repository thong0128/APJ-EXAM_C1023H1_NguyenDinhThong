package view;


import controller.ProductController;
import model.Product;

import java.util.InputMismatchException;
import java.util.Scanner;

import static controller.ProductController.*;

public class Main {
    public static void main(String[] args) {
        mainMenu();
    }
    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("---- PRODUCT MANAGEMENT PROGRAM ----");
            System.out.println("Select function by number (to continue)");
            System.out.println("1. Show list");
            System.out.println("2. Add new");
            System.out.println("3. Update");
            System.out.println("4. Remove");
            System.out.println("5. Sort");
            System.out.println("6. Search for the most expensive product");
            System.out.println("6. Read from file");
            System.out.println("8. Write to file");
            System.out.println("9. Exit");
            System.out.print("Select function: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showList();
                    break;
                case 2:
                    Product product = productInput();
                    ProductController.addProduct(product);
                    System.out.println(product + "Add successfully");
                    break;
                case 3:
                    int id = checkId();
                    product = ProductController.get(id);
                    updateInput(product);
                    System.out.println(product + "Update successfully");
                    break;
                case 4:
                    id = checkId();
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Are you sure to delete? y/n?");
                    while (true) {
                        String ans = scanner1.nextLine();
                        if (ans.equalsIgnoreCase("y")) {
                            ProductController.removeProduct(id);
                            System.out.println("Remove successfully");
                            break;
                        } else if (ans.equalsIgnoreCase("n")) {
                            mainMenu();
                            break;
                        }else {
                            System.out.println("Sorry, I didn't catch that. Please answer y/n");
                        }
                    }
                    break;
                case 5:
                    sortMenu();
                    break;
                case 6:
                    System.out.println(highestPrice());
                    break;
                case 7:
                    readWriteFile.readDataFromFile();
                    break;
                case 8:
                    readWriteFile.writeDataToFile(productList);
                    break;
                case 9:
                    System.exit(0);
            }
        }
    }

    private static int checkId() {
        int id;
        while (true) {
            id = idInput();
            if (!ProductController.contain(id)) {
                System.out.println("Can't find this product");
            }
            else break;
        }
        return id;
    }

    public static int idInput() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter id: ");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Wrong data type, please input again");
        }
        return idInput();
    }
    public static String nameInput() {
        Scanner scanner = new Scanner(System.in);
        String name = "";
        while (name.isEmpty()) {
            System.out.print("Enter name: ");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Empty, please input again");
            }
        }
        return name;
    }
    public static long priceInput() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter price: ");
            return scanner.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("Wrong data type, please input again");
        }
        return priceInput();
    }

    public static int quantityInput() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter quantity: ");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Wrong data type, please input again");
        }
        return quantityInput();
    }
    public static String descriptionInput() {
        Scanner scanner = new Scanner(System.in);
        String description = "";
        while (description.isEmpty()) {
            System.out.print("Enter description: ");
            description = scanner.nextLine();
            if (description.isEmpty()) {
                System.out.println("Empty, please input again");
            }
        }
        return description;
    }
    public static Product productInput() {
        int  id = idInput();
        String name = nameInput();
        long price = priceInput();
        int quantity = quantityInput();
        String description = descriptionInput();
        return new Product(id, name, price, quantity, description);
    }
    public static void updateInput(Product product) {
        int  id = idInput();
        String name = nameInput();
        long price = priceInput();
        int quantity = quantityInput();
        String description = descriptionInput();
        updateProduct(product, id, name, price, quantity,description);
        System.out.println("Update completed");
    }
    public static void sortMenu() {
        int choice = -1;
        Scanner scanner = new Scanner(System.in);
        while(choice != 3) {
            System.out.println("---- Sort Menu ----");
            System.out.println("1. Sort by price in increase order");
            System.out.println("2. Sort by price in decrease order");
            System.out.println("3. Main menu");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    sortByPriceIncrease();
                    break;
                case 2:
                    sortByPriceDecrease();
                    break;
                case 3:
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
