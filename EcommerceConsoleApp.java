package shopping;

import java.util.*;

class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

public class EcommerceConsoleApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Product> products = new ArrayList<>();
    private static final Map<Integer, Integer> cart = new HashMap<>();
    
    public static void main(String[] args) {
        initializeProducts();
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> displayProducts();
                case 2 -> addToCart();
                case 3 -> removeFromCart();
                case 4 -> viewCart();
                case 5 -> checkout();
                case 6 -> exitApp();
                default -> System.out.println("\u001B[31mInvalid choice! Try again.\u001B[0m");
            }
        }
    }

    private static void initializeProducts() {
        products.add(new Product(1, "Laptop", 800.00));
        products.add(new Product(2, "Smartphone", 500.00));
        products.add(new Product(3, "Headphones", 100.00));
        products.add(new Product(4, "Charger", 150.00));
        products.add(new Product(5, "PS5", 850.00));
    }

    private static void displayMenu() {
        System.out.println("\u001B[34m\n=== E-Commerce Console App ===\u001B[0m");
        System.out.println("1. View Products");
        System.out.println("2. Add to Cart");
        System.out.println("3. Remove from Cart");
        System.out.println("4. View Cart");
        System.out.println("5. Checkout");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayProducts() {
        System.out.println("\u001B[32mAvailable Products:\u001B[0m");
        for (Product product : products) {
            System.out.println(product.id + ". " + product.name + " - $" + product.price);
        }
    }

    private static void addToCart() {
        System.out.print("Enter product ID to add to cart: ");
        int productId = scanner.nextInt();
        if (productId > 0 && productId <= products.size()) {
            cart.put(productId, cart.getOrDefault(productId, 0) + 1);
            System.out.println("\u001B[32mAdded to cart!\u001B[0m");
        } else {
            System.out.println("\u001B[31mInvalid product ID!\u001B[0m");
        }
    }

    private static void removeFromCart() {
        System.out.print("Enter product ID to remove from cart: ");
        int productId = scanner.nextInt();
        if (cart.containsKey(productId)) {
            if (cart.get(productId) > 1) {
                cart.put(productId, cart.get(productId) - 1);
            } else {
                cart.remove(productId);
            }
            System.out.println("\u001B[33mRemoved from cart!\u001B[0m");
        } else {
            System.out.println("\u001B[31mProduct not in cart!\u001B[0m");
        }
    }

    private static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("\u001B[31mYour cart is empty!\u001B[0m");
            return;
        }
        System.out.println("\u001B[36mYour Cart:\u001B[0m");
        double total = 0;
        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            Product product = products.get(entry.getKey() - 1);
            System.out.println(product.name + " x " + entry.getValue() + " - $" + (product.price * entry.getValue()));
            total += product.price * entry.getValue();
        }
        System.out.println("Total: $" + total);
    }

    private static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("\u001B[31mYour cart is empty!\u001B[0m");
            return;
        }
        viewCart();
        System.out.println("\u001B[32mThank you for your purchase!\u001B[0m");
        cart.clear();
    }

    private static void exitApp() {
        System.out.println("\u001B[35mThank you for using our E-Commerce Console App!\u001B[0m");
        System.exit(0);
    }
}
