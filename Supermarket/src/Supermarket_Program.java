import java.util.Scanner;

public class Supermarket_Program {

    // 1. Global Variables
    private static String[][] user = new String[10][3];
    private static int userCount = 0;

    private static String[] productNames = { "Milk", "Eggs", "Bread" };
    private static double[] productPrices = { 45.0, 30.0, 55.0 };
    private static double[] productQuantities = { 100.0, 150.0, 200.0 };
    private static double[] productTotalRevenue = { 0.0, 0.0, 0.0 };
    // 1) ensure productTotalSoldQty is int[]
    private static int[] productTotalSoldQty = new int[10];
    private static int productCount = 3;

    // 2. Main Method
    //ณัฐวุฒิ 171
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         addUser("admin","12345","admin");
         addUser("cashier","12345","cashier");

        while (true) {
            loginSystem(sc);
        }
    }

    // 3. Core System Methods
    //ณัฐวุฒิ 171 ล๊อดอิน
    public static void loginSystem(Scanner sc) {
        System.out.println("======== Login ========");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password:  ");
        String password = sc.nextLine();
        System.out.println("=======================");

        String role = checkLogin(user, userCount, username, password);
        if (role.equals("admin")) {
            System.out.println("Login Successful");
            System.out.println("======================= \n");
            adminMenu(sc);

        } else if (role.equals("cashier")) {
            System.out.println("Login Successful");
            cashierMenu(sc);

        } else {
            System.out.println("!!!Login Failed!!!");
        }
    }
    //ณัฐวุฒิ 171 เช็คว่าลีอดอินถูกต้องไหม
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
    //ณัฐวุฒิ 171 เมนุAdmin
    public static void adminMenu(Scanner sc) {
        while (true) {
            System.out.println("===== Supermarket Program for Admin =====");
            System.out.print("""
                    1. Sell Product
                    2. Product Management
                    3. Sales Report
                    4. User Management
                    5. Logout
                    """);
            System.out.println("=========================================");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(" ");
                    sellProductProcess(sc);
                    break;
                case "2":
                    System.out.println(" ");
                    managementProductMenu(sc);
                    break;
                case "3":
                    System.out.println(" ");
                    salesReport();
                    break;
                case "4":
                    System.out.println(" ");
                    userManagement(sc);
                    break;
                case "5":
                    System.err.println("Logout Successful");
                    System.out.println("===================== \n");
                    return;
                default:
                    System.out.print("Unknown command Try again \n");
            }
        }
    }
    //ณัฐวุฒิ 171 Menu cashier
    public static void cashierMenu(Scanner sc) {
        while (true) {
            System.out.println("===== Supermarket Program for Cashier =====");
            System.out.print("""
                    1. Sell Product
                    2. Sales Report
                    3. Logout
                    """);
            System.out.println("===========================================");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(" ");
                    sellProductProcess(sc);
                    break;
                case "2":
                    System.out.println(" ");
                    salesReport();
                    break;
                case "3":
                    System.out.println("Logout Successful");
                    System.out.println("===================== \n");
                    return;
                default:
                    System.out.print("Unknown command Try again \n");
            }
        }
    }
    //ณัฐวุฒิ 171 เมนูจัดการ User
    public static void userManagement(Scanner sc) {
        while (true) {
            System.out.println("===== Management User =====");
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
                    System.out.println(" ");
                    displayUser();
                    break;
                case "2":
                    System.out.println(" ");
                    register(sc);
                    break;
                case "3":
                    System.out.println(" ");
                    updateUser(sc);
                    break;
                case "4":
                    System.out.println(" ");
                    deleteUser(sc);
                    break;
                case "5":
                    System.out.println(" ");
                    return;
                default:
                    System.out.println("Invalid choice. Try again. \n");
                    break;
            }
        }
    }
    //ณัฐวุฒิ 171
    public static void managementProductMenu(Scanner sc) {
        while (true) {
            System.out.println("===== Products Management =====");
            System.out.print("""
                    1. Display ALL Product in stock
                    2. Add Products
                    3. Edit Products
                    4. Remove Products
                    5. Back To Admin Menu
                    """);
            System.out.println("===============================");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(" ");
                    displayProducts();
                    break;
                case "2":
                    System.out.println(" ");
                    addProduct(sc);
                    break;
                case "3":
                    System.out.println(" ");
                    updateProduct(sc);
                    break;
                case "4":
                    System.out.println(" ");
                    removeProduct(sc);
                    break;
                case "5":
                    System.out.println(" ");
                    return;
                default:
                    System.out.println("Invalid choice. Try again. \n");
                    break;
            }
        }
    }

    // 5. User Management Methods
    //ณัฐวุฒิ 171
    public static void displayUser() {
        System.out.println("===== User List =====");
        for (int i = 0; i < userCount; i++) {
            System.out.println("No " + (i + 1) + " Username :" + user[i][0] + " Role :" + user[i][2]);
        }
    }
    //ณัฐวุฒิ 171
    public static int findIndexUser(String username) {
        for (int i = 0; i < userCount; i++) {
            if (user[i][0].equals(username)) {
                return i;
            }
        }
        return -1;
    }
    //ณัฐวุฒิ 171
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

    //ณัฐวุฒิ 171
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
    //ณัฐวุฒิ 171
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
    //ณัฐวุฒิ 171
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
    //ณัฐวุฒิ 171 ขยายไซส์อาเรย์
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
    //ณฐกร 167 แสดงสินค้าทั้งหมด
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
    //ณฐกร 167 เพิ่มสินค้า
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

    //ณฐกร 167 ลบสินค้า
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
            productTotalSoldQty[i] = productTotalSoldQty[i + 1];
            productTotalRevenue[i] = productTotalRevenue[i + 1];
        }

        productCount--;
        System.out.println("Remove Product Successful");
    }
    //ณฐกร 167 แก้ไขสินค้า
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
    // ณฐกร 167 ขยายอาเรย์สินค้า
    private static void resizeProductArray() {
        if (productCount < productNames.length) {
            return;
        }

        int newSize = productNames.length * 2;
        String[] newNames = new String[newSize];
        double[] newPrices = new double[newSize];
        double[] newQuantities = new double[newSize];
        double[] newTotalRevenue = new double[newSize];
        int[] newTotalSoldQty = new int[newSize]; // changed to int[]

        for (int i = 0; i < productNames.length; i++) {
            newNames[i] = productNames[i];
            newPrices[i] = productPrices[i];
            newQuantities[i] = productQuantities[i];
            newTotalSoldQty[i] = productTotalSoldQty[i];
            newTotalRevenue[i] = productTotalRevenue[i];
        }

        productNames = newNames;
        productPrices = newPrices;
        productQuantities = newQuantities;
        productTotalSoldQty = newTotalSoldQty;
        productTotalRevenue = newTotalRevenue;
    }

    // 7. Sales/Checkout Methods
    // ณัฐวุฒิ 171 กระบวนการขายเพื่อเตรียมเก็บข้อมูล
    public static void sellProductProcess(Scanner sc) {
        int[] quantities = selectItems(sc);

        double total = calculateTotal(quantities);

        double finalTotal = calculatePromotion(total);

        printReceipt(quantities, total, finalTotal);

        // Replace inline recording loop with call to recordSale
        recordSale(quantities);

        System.out.println("==========");
    }

    // ภานุวัฒน์ 185 บันทึกยอดขาย
    public static void recordSale(int[] quantities) {
        for (int i = 0; i < productCount; i++) {
            if (quantities[i] > 0) {
                // ลดสต็อกสินค้า
                productQuantities[i] -= quantities[i];
                // บันทึกข้อมูลการขาย
                productTotalSoldQty[i] += quantities[i];
                productTotalRevenue[i] += (productPrices[i] * quantities[i]); // บันทึกรายได้ (ก่อนหักส่วนลด)
            }
        }
    }
    //กรกฤช 159 เลือกสินค้า
    public  static int[] selectItems(Scanner sc) {
        int[] quantities = new int[productCount];

        System.out.println("===== Products in store =====");
        for (int i = 0; i < productCount; i++) {
            System.out.println((i + 1) + ". " + productNames[i] + " - " + productPrices[i] + " ฿");
        }

        while (true) {
            System.out.print("Select item by number (0 to finish): ");
            int choice = sc.nextInt();
            if (choice == 0)
                break;
            if (choice < 1 || choice > productCount) {
                System.out.println("Invalid number. Please try again.");
                continue;
            }
            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();
            if (qty < 0) {
                System.out.println("Quantity cannot be negative.");
                continue;
            }
            quantities[choice - 1] += qty;
        }
        sc.nextLine();
        return quantities;
    }

    //กรกฤช 159 คำนวนการขาย
    public static double calculateTotal(int[] quantities) {
        double total = 0;
        System.out.println();
        System.out.println("===== Items purchased =====");
        for (int i = 0; i < productCount; i++) {
            int qty = quantities[i];
            if (qty > 0) {
                double subtotal = productPrices[i] * qty;
                System.out.println(productNames[i] + " x " + qty + " = " + subtotal + " ฿");
                total += subtotal;
            }
        }System.out.println("--------------------------------");
        System.out.println("Total price: " + total + " ฿ ");
        return total;
    }
    //เมธาสิทธิ์ 189 คำนวนโปรโมชั่น
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
    //เมธาสิทธิ์ 189 แสดงใบเสร็จ
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


    //--- Sales Report --- 185
    //ภานุวัฒน์ 185 รายงานยอดขาย
    public static void salesReport() {
        System.out.println("===== Sales Report =====");
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-20s | %-10s | %-15s\n", "Product Name", "Qty Sold", "Total Revenue");
        System.out.println("-----------------------------------------------------");

        double grandTotalRevenue = 0.0;
        // 3) grandTotalItems should be int
        int grandTotalItems = 0;

        for (int i = 0; i < productCount; i++) {
            // แสดงเฉพาะสินค้าที่เคยขายได้
            if (productTotalSoldQty[i] > 0) {
                // 4) change %-10.0f to %-10d for integer qty
                System.out.printf("%-20s | %-10d | %-15.2f\n",
                        productNames[i],
                        productTotalSoldQty[i],
                        productTotalRevenue[i]);

                grandTotalRevenue += productTotalRevenue[i];
                grandTotalItems += productTotalSoldQty[i];
            }
        }

        System.out.println("-----------------------------------------------------");
        System.out.printf("%-20s | %-10d | %-15.2f\n",
                "GRAND TOTAL", grandTotalItems, grandTotalRevenue);
        System.out.println("==========================");
    }



}
