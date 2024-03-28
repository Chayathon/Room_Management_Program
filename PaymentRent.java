import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PaymentRent extends JFrame {
    JPanel headerPanel, menuPanel;
    Container container;
    JButton roomBtn;
    CardLayout cardLayout;

    private RoomSummary currentRoomSummary; // เก็บการสรุปห้องปัจจุบัน

    public PaymentRent() {
        super("Calculate Payment Rent");
        container = getContentPane();

        cardLayout = new CardLayout(10, 10); // กำหนด CardLayout สำหรับการนำเสนอหน้าต่าง
        container.setLayout(new FlowLayout()); // ใช้ FlowLayout สำหรับการจัดวางองค์ประกอบ
        container.setLayout(cardLayout); // ใช้ CardLayout สำหรับการเปลี่ยนแปลงหน้าต่าง

        headerPanel = new JPanel(); // สร้างพาเนลสำหรับหัวข้อ
        headerPanel.setLayout(new BorderLayout()); // กำหนดลักษณะการจัดวางเป็น BorderLayout
        container.add(headerPanel); // เพิ่มพาเนลหัวข้อไปยัง container

        JLabel title = new JLabel("Calculate Payment Rent"); // สร้างป้ายชื่อ
        title.setHorizontalAlignment(JLabel.CENTER); // จัดตำแหน่งตามแนวนอน
        title.setFont(new Font("Tahoma", Font.BOLD, 28)); // กำหนดแบบอักษรและขนาด
        headerPanel.add(title, BorderLayout.NORTH); // เพิ่มป้ายชื่อไปยังพาเนลหัวข้อ

        menuPanel = new JPanel(); // สร้างพาเนลสำหรับเมนูห้อง
        menuPanel.setLayout(new GridLayout(3, 10, 10, 10)); // กำหนดลักษณะการจัดวางเป็น GridLayout
        headerPanel.add(menuPanel); // เพิ่มพาเนลเมนูไปยังพาเนลหัวข้อ

        try {
            String fileName = "room.txt"; // กำหนดชื่อไฟล์ที่เก็บข้อมูลห้อง
            File file = new File(fileName); // สร้างอ็อบเจกต์ File
            BufferedReader reader = new BufferedReader(new FileReader(file)); // สร้าง BufferedReader เพื่ออ่านข้อมูลจากไฟล์

            String line;
            while ((line = reader.readLine()) != null) { // อ่านข้อมูลทีละบรรทัดจนกว่าจะเจอ null (สิ้นสุดไฟล์)
                String[] data = line.split(" "); // แยกข้อมูลด้วยช่องว่าง
                String roomNumber = data[0]; // เลขห้อง
                String roomType = data[1]; // ประเภทห้อง
                String roomPrice = data[2]; // ราคาห้อง
                String roomStatus = data[3]; // สถานะห้อง
                String roomActive = data[4]; // สถานะการใช้งานของห้อง

                boolean canEnableButton = checkPaymentHistory(roomNumber); // ตรวจสอบประวัติการชำระเงินของห้อง

                roomBtn = new JButton(roomNumber); // สร้างปุ่มห้อง
                roomBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // เปลี่ยนรูปแบบเคอร์เซอร์เป็นรูปแบบมือ
                roomBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) { // เมื่อคลิกปุ่มห้อง
                        JTextField electricityCostField = new JTextField();
                        JTextField waterCostField = new JTextField();
                        JPanel utilityPanel = new JPanel(new GridLayout(3, 2));
                        utilityPanel.add(new JLabel("Enter electricity unit:"));
                        utilityPanel.add(electricityCostField);
                        utilityPanel.add(new JLabel("Enter water unit:"));
                        utilityPanel.add(waterCostField);

                        int result = JOptionPane.showConfirmDialog(null, utilityPanel, "Enter Utility Costs", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) { // เมื่อผู้ใช้คลิก OK
                            String electricityCost = electricityCostField.getText();
                            String waterCost = waterCostField.getText();
                            double roomPriceDouble = Double.parseDouble(roomPrice); // แปลงราคาห้องเป็น double

                            // คำนวณค่าน้ำ
                            Double waterUnit = Double.parseDouble(waterCost);
                            Double water=0.0;
                            if (waterUnit <= 50) {
                                water = waterUnit * 6;
                            } else if (waterUnit <= 100) {
                                water = waterUnit * 7;
                            } else {
                                water = waterUnit * 10;
                            }

                            // คำนวณค่าใช้จ่ายทั้งหมด
                            double totalCost = roomPriceDouble + (Double.parseDouble(electricityCost) * 8) + water;

                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String currentDate = dateFormat.format(new Date());

                            try (BufferedWriter writer = new BufferedWriter(new FileWriter("payment_history.txt", true))) {
                                // เขียนข้อมูลการชำระเงินลงในไฟล์
                                writer.write(roomNumber + " " + "0 " + roomPrice + " " + electricityCost + " " + waterCost + " " + totalCost + " " + currentDate);
                                writer.newLine();
                                JOptionPane.showMessageDialog(container, "Payment details saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } catch (IOException e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(container, "Error saving payment details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            currentRoomSummary = new RoomSummary(roomNumber, roomType, roomPrice, electricityCost, waterCost, totalCost);

                            dispose(); // ปิดหน้าต่าง PaymentRent
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    new PaymentRent(); // เปิดหน้าต่าง PaymentRent ใหม่
                                }
                            });
                        }
                    }
                });

                if(roomActive.equals("1")) { // หากห้องมีการใช้งาน
                    roomBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            cardLayout.show(container, roomNumber); // แสดงหน้าต่างที่เกี่ยวข้องกับห้องนั้น
                        }
                    });

                    if(roomStatus.equals("Taken") && !checkPaymentHistory(roomNumber)) { // หากห้องถูกใช้งานและไม่มีประวัติการชำระเงิน
                        menuPanel.add(roomBtn); // เพิ่มปุ่มห้องลงในเมนู
                    } else {
                        roomBtn.setEnabled(false); // ปิดปุ่มห้อง
                        menuPanel.add(roomBtn); // เพิ่มปุ่มห้องลงในเมนู
                    }
                }
            }

            reader.close(); // ปิด BufferedReader
        } catch(IOException e) {
            System.out.println("Error while writing file " + e.getMessage()); // แสดงข้อผิดพลาดในการอ่านไฟล์
        }

        setSize(1700, 400); // กำหนดขนาดของหน้าต่าง PaymentRent
        setVisible(true); // ทำให้หน้าต่างแสดง
    }

    // ตรวจสอบประวัติการชำระเงินของห้อง
    private boolean checkPaymentHistory(String roomNumber) {
        try {
            String fileName = "payment_history.txt"; // ชื่อไฟล์ที่เก็บประวัติการชำระเงิน
            File file = new File(fileName); // สร้างอ็อบเจกต์ File
            BufferedReader reader = new BufferedReader(new FileReader(file)); // สร้าง BufferedReader เพื่ออ่านข้อมูลจากไฟล์
            String line;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");

            while ((line = reader.readLine()) != null) { // อ่านข้อมูลทีละบรรทัดจนกว่าจะเจอ null (สิ้นสุดไฟล์)
                String[] data = line.split(" "); // แยกข้อมูลด้วยช่องว่าง
                if (data[0].equals(roomNumber) && data.length >= 6 && data[6].startsWith(dateFormat.format(new Date()))) {
                    reader.close(); // ปิด BufferedReader
                    return true; // มีประวัติการชำระเงินสำหรับห้องนี้
                }
            }

            reader.close(); // ปิด BufferedReader
        } catch (IOException e) {
            e.printStackTrace(); // แสดงข้อผิดพลาดในการอ่านไฟล์
        }

        return false; // ไม่มีประวัติการชำระเงินสำหรับห้องนี้
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {
    //         public void run() {
    //             new PaymentRent();
    //         }
    //     });
    // }
}
