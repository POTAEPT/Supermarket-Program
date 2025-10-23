# Supermarket-Program
Java-Projectgroup

รายการฟังก์ชัน (Methods) ทั้งหมด
1. Main Method
   main(String[] args)

3. Core System (ระบบหลัก)
loginSystem(Scanner sc): แสดงหน้า Login และรับ Username/Password

checkLogin(...): ตรวจสอบว่า Username/Password ถูกต้องหรือไม่ และคืนค่า Role (admin/cashier)

3. Menus (เมนู)
adminMenu(Scanner sc): แสดงเมนูหลักสำหรับ Admin

cashierMenu(Scanner sc): แสดงเมนูหลักสำหรับ Cashier

userManagement(Scanner sc): แสดงเมนูย่อยสำหรับจัดการผู้ใช้งาน

managementProductMenu(Scanner sc): แสดงเมนูย่อยสำหรับจัดการสินค้า

4. User Management (จัดการผู้ใช้ - โปเต้)
displayUser(): แสดงรายชื่อผู้ใช้ทั้งหมด

findIndexUser(String username): ช่วยค้นหา Index ของผู้ใช้จาก Username 

deleteUser(Scanner sc): ลบผู้ใช้ออกจากระบบ

updateUser(Scanner sc): แก้ไขข้อมูลผู้ใช้ (Username, Password, Role)

register(Scanner sc): หน้าสำหรับรับค่าเพื่อสร้าง User ใหม่

addUser(...): เพิ่ม User ใหม่ลงใน Array user

resizeUserArray(): ขยายขนาด Array user อัตโนมัติเมื่อเต็ม

5. Product Management (จัดการสินค้า - บอม)
displayProducts(): แสดงรายการสินค้าทั้งหมดในสต็อก

addProduct(Scanner sc): เพิ่มสินค้าใหม่เข้าระบบ (สามารถเพิ่มทีละหลายรายการได้)

removeProduct(Scanner sc): ลบสินค้าออกจากระบบ

updateProduct(Scanner sc): แก้ไขข้อมูลสินค้า (ชื่อ, ราคา, จำนวน)

resizeProductArray(): ขยายขนาด Array สินค้า (productNames, productPrices ฯลฯ) อัตโนมัติเมื่อเต็ม

6. Sales/Checkout (ระบบขาย - สกาย, ไบโอ)
sellProductProcess(Scanner sc): กระบวนการขายหลัก (เรียก selectItems -> calculateTotal -> calculatePromotion -> printReceipt -> recordSale)

selectItems(Scanner sc): (สกาย) แสดงรายการสินค้าและรับการสั่งซื้อจากลูกค้า

calculateTotal(int[] quantities): (สกาย) คำนวณราคารวม (ก่อนหักส่วนลด)

calculatePromotion(double total): (ไบโอ) คำนวณส่วนลดตามโปรโมชั่น

printReceipt(int[] qty, ...): (ไบโอ) พิมพ์ใบเสร็จสรุปรายการ

7. Sales Report (รายงานยอดขาย - ออม)
recordSale(int[] quantities): บันทึกยอดขายสะสมและตัดสต็อกสินค้า (เรียกใช้โดย sellProductProcess)

salesReport(): แสดงผลสรุปยอดขายทั้งหมด (เรียกใช้โดย adminMenu และ cashierMenu)

===================================
