import java.util.Scanner;

public class Supermarket_Program {

    // 1. Global Variables
    private static String[][] user = new String[10][3];
    private static int userCount = 0;

    private static String[] productNames = { "Milk", "Eggs", "Bread" };
    private static double[] productPrices = { 45.0, 30.0, 55.0 };
    private static double[] productQuantities = { 100.0, 150.0, 200.0 };
    private static int productCount = 3;

    // 2. Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // User_Admin
        // addUser("admin01","12345","admin");
        // addUser("cashier01","12345","cashier");

        while (true) {
            loginSystem(sc);
        }
    }

    // 3. Core System Methods
    public static void loginSystem(Scanner sc) {
        System.out.println("========Login========");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password:  ");
        String password = sc.nextLine();
        System.out.println("=====================");

        String role = checkLogin(user, userCount, username, password);
        if (role.equals("admin")) {
            System.out.println("Login Successful");
            adminMenu(sc);

        } else if (role.equals("cashier")) {
            System.out.println("Login Successful");
            cashierMenu(sc);

        } else {
            System.out.println("!!!Login Failed!!!");
        }
    }

    public static String checkLogin(String[][] users, int userCount, String username, String password) {
        if (username == null || password == null) {
            return "Invalid input.";
        }
        for (int i = 0; i < userCount; i++) {
            if (user[i] != null && users[i][0].equals(username) && users[i][1].equals(password)) {
                return users[i][2];
            }
        }
        return "User not found.";
    }

    // 4. Menu Methods
    public static void adminMenu(Scanner sc) {
        while (true) {
            System.out.println("=====Supermarket Program for Admin=====");
            System.out.print("""
                    1. Sell Product
                    2. Product Management
                    3. Sales Report
                    4. User Management
                    5. Logout
                    """);
            System.out.println("=======================================");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    sellProductProcess(sc);
                    break;
                case "2":
                    managementProductMenu(sc);
                    break;
                case "3":
                    // Sales Report Method
                    break;
                case "4":
                    userManagement(sc);
                    break;
                case "5":
                    System.err.println("Logout Successful");
                    System.out.println("=====================");
                    return;
                default:
                    System.out.print("Unknown command Try again");
            }
        }
    }

    public static void cashierMenu(Scanner sc) {
        while (true) {
            System.out.println("=====Supermarket Program for Cashier=====");
            System.out.print("""
                    1. Sell Product
                    2. Sales Report
                    3. Logout
                    """);
            System.out.println("=========================================");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    sellProductProcess(sc);
                    break;
                case "2":
                    // Sales Report Method
                    break;
                case "3":
                    System.out.println("Logout Successful");
                    System.out.println("=====================");
                    return;
                default:
                    System.out.print("Unknown command Try again");
            }
        }
    }

    public static void userManagement(Scanner sc) {
        while (true) {
            System.out.println("=====Management User=====");
            System.out.println("""
                    1. Display all User
                    2. Add User
                    3. Edit User
                    4. Delete User
                    5. back to AdminMenu
                            """);
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    displayUser();
                    break;
                case "2":
                    register(sc);
                    break;
                case "3":
                    updateUser(sc);
                    break;
                case "4":
                    deleteUser(sc);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    public static void managementProductMenu(Scanner sc) {
        while (true) {
            System.out.println("=====Products Management=====");
            System.out.print("""
                    1. Display ALL Product in stock
                    2. Add Products
                    3. Edit Products
                    4. Remove Products
                    5. Back To Admin Menu
                    """);
            System.out.println("=============================");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    displayProducts();
                    break;
                case "2":
                    addProduct(sc);
                    break;
                case "3":
                    updateProduct(sc);
                    break;
                case "4":
                    removeProduct(sc);
                    break;
                case "5":
                    return;
                default:
                    throw new AssertionError();
            }
        }
    }

    // 5. User Management Methods
    public static void displayUser() {
        System.out.println("=====User List=====");
        for (int i = 0; i < userCount; i++) {
            System.out.println("No " + (i + 1) + " Username :" + user[i][0] + " Role :" + user[i][2]);
        }
    }

    public static int findIndexUser(String username) {
        for (int i = 0; i < userCount; i++) {
            if (user[i][0].equals(username)) {
                return i;
            }
        }
        return -1;
    }

    public static void deleteUser(Scanner sc) {
        System.out.print("Enter Username to remove : ");
        String username = sc.nextLine();
        int index = findIndexUser(username);

        if (index == -1) {
            System.out.println("User Not Found");
            return;
        }

        for (int i = index; i < userCount - 1; i++) {
            user[i] = user[i + 1];
        }

        user[userCount - 1] = new String[3];
        userCount--;

        System.out.println("Remove User Successful");
    }

    public static void updateUser(Scanner sc) {
        System.out.print("Enter Username to Edit : ");
        String username = sc.nextLine();
        int index = findIndexUser(username);

        if (index == -1) {
            System.out.println("User Not Found");
            return;
        }

        while (true) {
            System.out.println("Username :" + user[index][0] + " Role :" + user[index][2]);
            System.out.println("What do you to Edit?");
            System.out.println("1. Username");
            System.out.println("2. Password");
            System.out.println("3. Role");
            System.out.println("4. Cancel");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter new username : ");
                    String newUsername = sc.nextLine().trim();

                    int existingIndex = findIndexUser(newUsername);

                    if (newUsername.isEmpty()) {
                        System.out.println("Username cannot be empty.");
                    } else if (existingIndex != -1) {
                        System.out.println("Username : " + newUsername + " already exists.");
                    } else {
                        user[index][0] = newUsername;
                        System.out.println("Update Username Successful");
                    }
                    break;
                case "2":
                    System.out.print("Enter new password : ");
                    String newPassword = sc.nextLine().trim();

                    if (newPassword.isEmpty()) {
                        System.out.println("Password cannot be empty.");
                    } else {
                        user[index][1] = newPassword;
                        System.out.println("Update password Successful");
                    }
                    break;
                case "3":
                    System.out.print("Enter new role : ");
                    String newRole = sc.nextLine().trim();

                    if (newRole.equalsIgnoreCase("admin") || newRole.equalsIgnoreCase("cashier")) {
                        user[index][2] = newRole.toLowerCase();
                        System.out.println("Role updated successfully!");
                    } else {
                        System.out.println("Invalid role. Must be 'admin' or 'cashier'.");
                    }
                    break;
                case "4":
                    System.out.println("Cancelled");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    public static void register(Scanner sc) {
        System.out.print("Enter Username : ");
        String username = sc.nextLine();

        System.out.print("Enter Password : ");
        String password = sc.nextLine();

        System.out.print("Enter Role : ");
        String role = sc.nextLine();

        if (addUser(username, password, role)) {
            System.out.println("Register Successful");
        } else {
            System.out.println("Failed to add user. Username might already exist or input is invalid.");
        }
    }

    public static boolean addUser(String username, String password, String role) {
        username = username.trim();
        password = password.trim();
        role = role.trim();
        if (username == null || password == null || role == null)
            return false;

        for (int i = 0; i < userCount; i++) {
            if (user[i] != null && username.equals(user[i][0])) {
                return false;
            }
        }

        resizeUserArray();
        user[userCount][0] = username;
        user[userCount][1] = password;
        user[userCount][2] = role;
        userCount++;
        return true;
    }

    private static void resizeUserArray() {
        if (userCount < user.length) {
            return;
        }
        String[][] newdata = new String[user.length * 2][3];

        for (int i = 0; i < user.length; i++) {
            newdata[i] = user[i];
        }
        user = newdata;
    }

    // 6. Product Management Methods
    public static void displayProducts() {
        System.out.println("Product List:");
        System.out.println();
        for (int i = 0; i < productCount; i++) {
            System.out.println("  Product numbers: " + (i + 1));
            System.out.println("  Name: " + productNames[i]);
            System.out.println("  Price: " + productPrices[i] + " bath");
            System.out.println("  Quantity: " + productQuantities[i]);
            System.out.println();
        }
    }

   public static void addProduct(Scanner sc) {
        System.out.print("Enter Number of product types to add: ");
        int addCount = sc.nextInt(); 
        sc.nextLine(); 

        for (int i = 0; i < addCount; i++) {
            System.out.println("--- Adding Product " + (i + 1) + "/" + addCount + " ---");
            
          
            resizeProductArray(); 

            
            System.out.print("Enter new product name: ");
            productNames[productCount] = sc.nextLine();
            
            System.out.print("Enter new product price(bath): ");
            productPrices[productCount] = sc.nextDouble();
            
            System.out.print("Enter new product quantity: ");
            productQuantities[productCount] = sc.nextDouble();
            sc.nextLine(); 

         
            productCount++;
            System.out.println("Add Product Successful (" + productNames[productCount - 1] + ")");
        }
        System.out.println("Finished adding " + addCount + " products.");
    }

    public static void removeProduct(Scanner sc) {
        System.out.print("Enter the product number to remove: ");
        int index = sc.nextInt();
        sc.nextLine();
        index--;

        if (index < 0 || index >= productCount) {
            System.out.println("Invalid product number.");
            return;
        }

        for (int i = index; i < productCount - 1; i++) {
            productNames[i] = productNames[i + 1];
            productPrices[i] = productPrices[i + 1];
            productQuantities[i] = productQuantities[i + 1];
        }

        productCount--;
        System.out.println("Remove Product Successful");
    }

    public static void updateProduct(Scanner sc) {
        System.out.print("Enter the product number to change: ");
        int index = sc.nextInt();
        sc.nextLine();
        index--;
        if (index < 0 || index >= productCount) {
            System.out.println("Invalid product number.");
            return;
        }
        System.out.print("Enter new product name: ");
        productNames[index] = sc.nextLine();
        System.out.print("Enter new product price: ");
        productPrices[index] = sc.nextDouble();
        System.out.print("Enter new product quantity: ");
        productQuantities[index] = sc.nextDouble();
        sc.nextLine();

        System.out.println("Update Product Successful");
    }

    private static void resizeProductArray() {
        if (productCount < productNames.length) {
            return;
        }

        int newSize = productNames.length * 2;
        String[] newNames = new String[newSize];
        double[] newPrices = new double[newSize];
        double[] newQuantities = new double[newSize];

        for (int i = 0; i < productNames.length; i++) {
            newNames[i] = productNames[i];
            newPrices[i] = productPrices[i];
            newQuantities[i] = productQuantities[i];
        }

        productNames = newNames;
        productPrices = newPrices;
        productQuantities = newQuantities;
    }

    // 7. Sales/Checkout Methods
    public static void sellProductProcess(Scanner sc) {
        int[] quantities = selectItems(sc);

        double total = calculateTotal(quantities);

        double finalTotal = calculatePromotion(total);

        printReceipt(quantities, total, finalTotal);

        System.out.println("==========");
    }

    static int[] selectItems(Scanner sc) {
        int[] quantities = new int[productCount];

        System.out.println("สินค้าในร้าน:");
        for (int i = 0; i < productCount; i++) {
            System.out.println((i + 1) + ". " + productNames[i] + " - " + productPrices[i] + " บาท");
        }

        while (true) {
            System.out.print("เลือกสินค้าตามหมายเลข (0 เพื่อจบ): ");
            int choice = sc.nextInt();
            if (choice == 0)
                break;
            if (choice < 1 || choice > productCount) {
                System.out.println("หมายเลขไม่ถูกต้อง ลองใหม่ครับ");
                continue;
            }
            System.out.print("จำนวนที่ต้องการ: ");
            int qty = sc.nextInt();
            if (qty < 0) {
                System.out.println("จำนวนต้องไม่ติดลบ");
                continue;
            }
            quantities[choice - 1] += qty;
        }
        sc.nextLine();
        return quantities;
    }

    static double calculateTotal(int[] quantities) {
        double total = 0;
        System.out.println();
        System.out.println("รายการที่ซื้อ:");
        for (int i = 0; i < productCount; i++) {
            int qty = quantities[i];
            if (qty > 0) {
                double subtotal = productPrices[i] * qty;
                System.out.println(productNames[i] + " x " + qty + " = " + subtotal + " บาท");
                total += subtotal;
            }
        }
        System.out.println("ราคารวมทั้งหมด: " + total + " บาท");
        return total;
    }

    public static double calculatePromotion(double total) {
        double[] totalThreshold = { 1000, 750, 500, 250 };
        double[] totalDiscountPercent = { 0.20, 0.15, 0.10, 0.05 };

        double discount = 0;
        for (int i = 0; i < totalThreshold.length; i++) {
            if (total >= totalThreshold[i]) {
                discount = total * totalDiscountPercent[i];
                break;
            }
        }
        if (discount > 0) {
            System.out.println("You received a discount of " + discount + " Baht!");
        }
        return total - discount;
    }

    public static void printReceipt(int[] qty, double total, double finalTotal) {
        System.out.println("\n===== Receipt =====");
        System.out.println("Item\tPrice\tQty\tSubtotal");
        for (int i = 0; i < productCount; i++) {
            if (qty[i] > 0)
                System.out.println(
                        productNames[i] + "\t" + productPrices[i] + "\t" + qty[i] + "\t" + (productPrices[i] * qty[i]));
        }
        System.out.println("------------------------");
        System.out.printf("Total: %.2f\n", total);
        if (finalTotal < total) {
            System.out.printf("Discounted Total: %.2f\n", finalTotal);
        }
        System.out.println("========================");
        System.out.println("Thank you!");
    }

}
