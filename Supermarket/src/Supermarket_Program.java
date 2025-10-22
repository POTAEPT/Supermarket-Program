import java.util.Scanner;
public class Supermarket_Program {
    // ประกาศแสกนเนอร์แล้ว สามารถใช้ได้เลยไม่ต้องประกาศใหม่
    public static Scanner sc = new Scanner(System.in);

    //สร้างอาเรย์สำหรับเก็บข้อมูลของผู้ใช้งาน(171)
    private static String[][] user = new String[10][3];
    //นับจำนวนผู้ใช้งานที่มีอยู่ใน อาเรย์(171)
    private static int userCount = 0;

    private static void resizeArray(){
        if(userCount < user.length) {return;}
        String[][] newdata = new String[user.length*2][3];
        for (int i = 0; i < user.length; i++) {newdata[i] = user[i];}
        user = newdata;
    }

    //เมดธอน สำหรับเพิ่มข้อมูลผู้ใช้งาน (171)
    public static boolean  addUser(String username, String password, String role){
        username = username.trim();
        password = password.trim();
        role = role.trim();
        if(username == null || password == null || role == null) return false;

        for (int i = 0; i<userCount; i++){
            if(user[i] != null && username.equals(user[i][0])){
                return false;
            }
        }

        resizeArray();
        user[userCount][0] = username;
        user[userCount][1] = password;
        user[userCount][2] = role;
        userCount++;
        return true;
    }

    public static void  loginSystem(){
        System.out.println("========Login========");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password:  ");
        String password = sc.nextLine();
        System.out.println("=====================");

        String role = checkLogin(user,userCount,username,password);
        if(role.equals("admin")){
            System.out.println("Login Successful");
            adminMenu();

        }else if (role.equals("cashier")){
            cashierMenu();
            System.out.println("Login Successful");
        }else{System.out.println("!!!Login Failed!!!");}
    }


    //เมดธอน สำหรับเช็คว่ามีข้อมูลผู้ใช้งานในอาเรย์แล้วหรือไม่(171)
    public static String checkLogin(String[][] users, int userCount, String username, String password) {
        if(username == null || password == null){return "Invalid input.";}
        for (int i = 0; i < userCount; i++) {
            if (users[i][0].equals(username) && users[i][1].equals(password)) {
                return users[i][2]; // ถ้าเจอ user ตรงกับ username และ password ส่งคืน role (admin/cashier)
            }
        }
        return "User not found.";
    }

    //เมดธอน สำหรับเมนูของผู้ใช้งานที่เป็น แอดมิน(171)
    public static void  adminMenu(){

        while (true){
            System.out.println("=====Supermarket Program for Admin=====");
            System.out.print("1. Sell Product\n" +
                    "2. Product Management\n" +
                    "3. Stock Management\n" +
                    "4. Sales Report\n" +
                    "5. User Management\n"+
                    "6. Logout\n");
            System.out.println("=======================================");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            switch (choice){
                case "1":
                    //Sell Product Method
                    break;
                case "2":
                    // Product Management Method
                    break;
                case "3":
                    // Stock Management Method
                    break;
                case "4":
                    //Sales Report Method
                    break;
                case "5":
                    //User Management Method
                    break;
                case "6":
                    //Logout
                    System.err.println("Exit Program");
                    System.out.println("=====================");
                    return ;

                default:System.out.print("Unknown command Try again");


            }
        }
    }

    //เมดธอน แสดงเมนูสำหรับผู้ใช้งานที่เป็นแคชเชียร์(171)
    public static void  cashierMenu() {
        while (true){
            System.out.println("=====Supermarket Program for Cashier=====");
            System.out.print("1. Sell Product\n" +
                    "2. Sales Report\n" +
                    "3. Logout\n");
            System.out.println("=========================================");
            System.out.println("Enter your choice: ");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    //Sell Product Method
                    break;
                case "2":
                    // Sales Report Method
                    break;
                case "3":
                    //Logout
                    System.err.println("Exit Program");
                    System.out.println("=====================");
                    return ;
                default:
                    System.out.print("Unknown command Try again");
            }
        }
    }



    // Method การคิดส่วนลดตามราคาที่กำหนดไว้ 189
    public static double calculatePromotion(double total) {
        double[] totalThreshold = {1000, 750, 500, 250};        // ขั้นต่ำแต่ละระดับ
        double[] totalDiscountPercent = {0.20, 0.15, 0.10, 0.05}; // ส่วนลดแต่ละระดับ

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

    // Method แสดงใบเสร็จ รับชื่อสินค้า, ราคาต่อหน่วย, จำนวนที่ซื้อ, subtotal/total ก่อนลด, หลังลด 189
    public static void printReceipt(String[] items, double[] prices, int[] qty, int itemCount, double total, double finalTotal) {
        System.out.println("\n===== Receipt =====");
        System.out.println("Item\tPrice\tQty\tSubtotal");
        for (int i = 0; i < itemCount; i++) {
            if(qty[i]>0)
                System.out.println(items[i] + "\t" + prices[i] + "\t" + qty[i] + "\t" + (prices[i]*qty[i]));
        }
        System.out.println("------------------------");
        System.out.printf("Total: %.2f\n", total);
        if (finalTotal < total) {
            System.out.printf("Discounted Total: %.2f\n", finalTotal);
        }
        System.out.println("========================");
        System.out.println("Thank you!");
    }

    //เเมดธอน แสดงรายการสินค้า
    public static void displayProducts(String[] names, double[] prices, double[] quantitiy) {
        System.out.println("Product List:");
        System.out.println();
        for (int i = 0; i < names.length; i++) {
            System.out.println("  Product numbers: " + (i+1));
            System.out.println("  Name: " + names[i]);
            System.out.println("  Price: " + prices[i] + " bath");
            System.out.println("  Quantity: " + quantitiy[i]);
            System.out.println();
        }
    }
    //เมดธอน เพิ่มสินค้า
    public static Object[] addProduct(String[] names, double[] prices, double[] quantity) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Number of product types: ");
        int addCount = input.nextInt();
        int newSize = names.length + addCount;
        String[] updatedNames = new String[newSize];
        double[] updatedPrices = new double[newSize];
        double[] updatedQuantity = new double[newSize];
        input.nextLine();
        for (int i = 0; i < names.length; i++) {
            updatedNames[i] = names[i];
            updatedPrices[i] = prices[i];
            updatedQuantity[i] = quantity[i];
        }
        for (int i = 0; i < addCount; i++) {
            System.out.print("Enter product name: ");
            updatedNames[names.length + i] = input.nextLine();
            System.out.print("Enter product price: ");
            updatedPrices[prices.length + i] = input.nextDouble();
            System.out.print("Enter product quantity: ");
            updatedQuantity[quantity.length + i] = input.nextDouble();
            input.nextLine(); 
        }
        return new Object[]{updatedNames, updatedPrices, updatedQuantity};
    }
    //เมดธอน ลบสินค้า
    public static Object[] removeProduct(String[] names, double[] prices, double[] quantity) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the product number to remove: ");
        int index = input.nextInt();
        index--;
        if (index < 0 || index >= names.length) {
            System.out.println("Invalid product number.");
            return new Object[]{names, prices, quantity};
        }
        String[] updatedNames = new String[names.length - 1];
        double[] updatedPrices = new double[prices.length - 1];
        double[] updatedQuantity = new double[quantity.length - 1];
        for (int i = 0, j = 0; i < names.length;i++){
            if(i == index){
                continue;
            }
            updatedNames[j] = names[i];
            updatedPrices[j] = prices[i];
            updatedQuantity[j] = quantity[i];
            j++;
        }
        return new Object[]{updatedNames, updatedPrices, updatedQuantity};
    }
    //เมดธอน แก้ไขสินค้า
    public static Object[] UpdateProduct(String[] names, double[] prices, double[] quantity) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the product number to change: ");
        int index = input.nextInt();
        input.nextLine(); 
        index--;
        if (index < 0 || index >= names.length) {
            System.out.println("Invalid product number.");
            return new Object[]{names, prices, quantity};
        }
        System.out.print("Enter new product name: ");
        names[index] = input.nextLine();
        System.out.print("Enter new product price: ");
        prices[index] = input.nextDouble();
        System.out.print("Enter new product quantity: ");
        quantity[index] = input.nextDouble();
        return new Object[]{names, prices, quantity};
    }



    public static void main(String[] args) {
        // User_Admin
        addUser("admin01","12345","admin");
        addUser("cashier01","12345","cashier");

        while (true){
            loginSystem();
        }
    }
}
