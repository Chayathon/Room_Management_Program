import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Cashing extends JFrame {
    JPanel headerPanel, menuPanel; // ประกาศตัวแปรของ JPanel
    Container container; // ประกาศตัวแปรของ Container
    JButton roomBtn; // ประกาศตัวแปรของ JButton
    CardLayout cardLayout; // ประกาศตัวแปรของ CardLayout

    public Cashing() {
        super("Cashing Payment"); // กำหนดหัวข้อหน้าต่าง JFrame
        container = getContentPane(); // ดึง Container มาใช้งาน

        cardLayout = new CardLayout(10, 10); // สร้าง CardLayout ด้วยขอบเขต 10x10
        container.setLayout(cardLayout); // กำหนด Layout ของ Container เป็น CardLayout

        headerPanel = new JPanel(); // สร้าง JPanel เพื่อใช้เป็นส่วนหัว
        headerPanel.setLayout(new BorderLayout()); // กำหนด Layout ของ JPanel เป็น BorderLayout
        container.add(headerPanel); // เพิ่ม JPanel ส่วนหัวเข้าไปใน Container

        JLabel title = new JLabel("Cashing Payment"); // สร้าง JLabel สำหรับหัวข้อ
        title.setHorizontalAlignment(JLabel.CENTER); // กำหนดการจัดวางข้อความให้อยู่กึ่งกลาง
        title.setFont(new Font("Tahoma", Font.BOLD, 28)); // กำหนดรูปแบบและขนาดตัวอักษร
        headerPanel.add(title, BorderLayout.NORTH); // เพิ่ม JLabel หัวข้อเข้าไปในส่วนหัว

        menuPanel = new JPanel(); // สร้าง JPanel เพื่อใช้เป็นเมนูห้องพัก
        menuPanel.setLayout(new GridLayout(0, 6, 10, 10)); // กำหนด Layout ของ JPanel เป็น GridLayout 6 คอลัมน์
        JScrollPane scrollPane = new JScrollPane(menuPanel); // สร้าง JScrollPane เพื่อให้สามารถเลื่อนได้
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // กำหนดให้ไม่มีแถบเลื่อนแนวนอน
        headerPanel.add(scrollPane, BorderLayout.CENTER); // เพิ่ม JScrollPane เข้าไปในส่วนกลางของหัว

        try {
            String fileName = "payment_history.txt"; // กำหนดชื่อไฟล์ที่ใช้เก็บประวัติการชำระเงิน
            File file = new File(fileName); // สร้างอ็อบเจ็กต์ File
            BufferedReader reader = new BufferedReader(new FileReader(file)); // สร้าง BufferedReader เพื่ออ่านไฟล์

            String line;
            while ((line = reader.readLine()) != null) { // วนลูปอ่านข้อมูลในไฟล์ทีละบรรทัด
                String[] data = line.split(" "); // แยกข้อมูลด้วยช่องว่าง
                String roomNumber = data[0]; // รหัสห้องพัก
                String paymentStatus = data[1]; // สถานะการชำระเงิน

                if (paymentStatus.equals("0")) { // ถ้าสถานะการชำระเงินเป็น 0 (ยังไม่ชำระ)
                    roomBtn = new JButton(roomNumber); // สร้าง JButton ด้วยรหัสห้องพัก
                    roomBtn.setPreferredSize(new Dimension(120, 60)); // กำหนดขนาดปุ่ม
                    roomBtn.setFont(new Font("Tahoma", Font.PLAIN, 18)); // กำหนดรูปแบบและขนาดตัวอักษร

                    roomBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) { // เมื่อคลิกปุ่มห้องพัก
                            double totalCost = Double.parseDouble(data[3]); // ค่าใช้จ่ายรวม
                            showRoomDetailsDialog(roomNumber, data[2], totalCost); // แสดงข้อมูลห้องพักและชำระเงิน
                        }
                    });

                    menuPanel.add(roomBtn); // เพิ่มปุ่มห้องพักลงในเมนู
                }
            }

            reader.close(); // ปิดการอ่านไฟล์
        } catch (IOException e) { // จัดการข้อผิดพลาดในการอ่านไฟล์
            System.out.println("Error while reading file " + e.getMessage());
        }

        setSize(800, 600); // กำหนดขนาดของหน้าต่าง
        setLocationRelativeTo(null); // กำหนดให้ปรากฏในตำแหน่งกลางจอ
        setVisible(true); // ทำให้หน้าต่างแสดง
    }

    // เมื่อคลิกปุ่มห้องพักแต่ละห้อง
    private void showRoomDetailsDialog(String roomNumber, String electricityCost, double totalCost) {
        JDialog dialog = new JDialog(this, "Room Details and Payment", true); // สร้าง JDialog สำหรับแสดงรายละเอียดห้องพักและการชำระเงิน
        JPanel panel = new JPanel(new GridBagLayout()); // สร้าง JPanel เพื่อใส่องค์ประกอบที่ต้องการแสดง
        GridBagConstraints constraints = new GridBagConstraints(); // สร้างตัวแปร constraints เพื่อกำหนดเงื่อนไขการจัดวาง

        constraints.fill = GridBagConstraints.HORIZONTAL; // กำหนดให้องค์ประกอบเต็มพื้นที่ในแนวนอน
        constraints.insets = new Insets(5, 5, 5, 5); // กำหนดระยะห่างของข้อความด้านซ้ายขวาด้านบนล่าง
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Room Number:"), constraints); // เพิ่ม JLabel รหัสห้องพัก

        constraints.gridx = 1;
        panel.add(new JLabel(roomNumber), constraints); // เพิ่ม JLabel รหัสห้องพัก

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Electricity Cost:"), constraints); // เพิ่ม JLabel ค่าไฟ

        constraints.gridx = 1;
        panel.add(new JLabel(electricityCost), constraints); // เพิ่ม JLabel ค่าไฟ

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Total Cost:"), constraints); // เพิ่ม JLabel ราคารวม

        constraints.gridx = 1;
        panel.add(new JLabel(String.valueOf(totalCost)), constraints); // เพิ่ม JLabel ราคารวม

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Enter Payment:"), constraints); // เพิ่ม JLabel ระบุจำนวนเงินที่ชำระ

        constraints.gridx = 1;
        JTextField paymentField = new JTextField(10); // สร้าง JTextField สำหรับระบุจำนวนเงินที่ชำระ
        panel.add(paymentField, constraints); // เพิ่ม JTextField ลงใน panel

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        JButton confirmButton = new JButton("Confirm Payment"); // สร้าง JButton สำหรับยืนยันการชำระเงิน
        panel.add(confirmButton, constraints); // เพิ่ม JButton ลงใน panel

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) { // เมื่อคลิกปุ่มยืนยันการชำระเงิน
                String paymentAmountStr = paymentField.getText(); // รับข้อมูลจาก JTextField
                if (!paymentAmountStr.isEmpty()) { // ถ้ามีการป้อนข้อมูล
                    double paymentAmount = Double.parseDouble(paymentAmountStr); // แปลงข้อความเป็นตัวเลข
                    double change = paymentAmount - totalCost; // คำนวณเงินทอน

                    if (change >= 0) { // ถ้ามีเงินทอนเป็นบวกหรือเท่ากับศูนย์
                        updatePaymentStatus(roomNumber); // อัปเดตสถานะการชำระเงินในไฟล์
                        showChangeDialog(change); // แสดงข้อความเงินทอน
                        dialog.dispose(); // ปิดหน้าต่างแจ้งเตือน
                    } else { // ถ้ามีเงินทอนติดลบ
                        JOptionPane.showMessageDialog(dialog, "The payment amount is insufficient.", "Error",
                                JOptionPane.ERROR_MESSAGE); // แสดงข้อความแจ้งเตือนเงินทอนไม่เพียงพอ
                    }
                } else { // ถ้าไม่มีการป้อนข้อมูล
                    JOptionPane.showMessageDialog(dialog, "Please enter payment amount.", "Error",
                            JOptionPane.ERROR_MESSAGE); // แสดงข้อความแจ้งเตือนให้ป้อนจำนวนเงิน
                }
            }
        });

        dialog.add(panel); // เพิ่ม JPanel เข้าไปใน JDialog
        dialog.setSize(400, 250); // กำหนดขนาดของ JDialog
        dialog.setLocationRelativeTo(this); // กำหนดให้แสดงตรงกลางของหน้าต่างหลัก
        dialog.setVisible(true); // ทำให้ JDialog แสดง
    }

    // อัปเดตสถานะการชำระเงิน
    private void updatePaymentStatus(String roomNumber) {
        try {
            String fileName = "payment_history.txt"; // กำหนดชื่อไฟล์ที่ใช้เก็บประวัติการชำระเงิน
            File file = new File(fileName); // สร้างอ็อบเจ็กต์ File
            File tempFile = new File("temp.txt"); // สร้างอ็อบเจ็กต์ File ชั่วคราว
            BufferedReader reader = new BufferedReader(new FileReader(file)); // สร้าง BufferedReader เพื่ออ่านไฟล์
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile)); // สร้าง BufferedWriter เพื่อเขียนไฟล์

            String line;
            while ((line = reader.readLine()) != null) { // วนลูปอ่านข้อมูลในไฟล์
                String[] data = line.split(" "); // แยกข้อมูลด้วยช่องว่าง
                String currentRoomNumber = data[0]; // รหัสห้องพัก
                String paymentStatus = data[1]; // สถานะการชำระเงิน

                if (currentRoomNumber.equals(roomNumber) && paymentStatus.equals("0")) { // ถ้าเจอรหัสห้องที่ต้องการและยังไม่ชำระเงิน
                    writer.write(currentRoomNumber + " 1 " + data[2] + " " + data[3] + " " + data[4]); // เขียนข้อมูลใหม่โดยเปลี่ยนสถานะการชำระเงินเป็น 1 (ชำระแล้ว)
                } else {
                    writer.write(line); // เขียนข้อมูลเดิม
                }
                writer.newLine(); // เขียนข้อมูลใหม่ลงบรรทัดใหม่
            }

            reader.close(); // ปิดการอ่านไฟล์
            writer.close(); // ปิดการเขียนไฟล์

            if (!file.delete()) { // ลบไฟล์เดิม
                System.out.println("Error deleting file");
                return;
            }
            if (!tempFile.renameTo(file)) { // เปลี่ยนชื่อไฟล์ชั่วคราวเป็นชื่อไฟล์เดิม
                System.out.println("Error renaming file");
            }

        } catch (IOException e) { // จัดการข้อผิดพลาดในการอ่านหรือเขียนไฟล์
            e.printStackTrace();
            JOptionPane.showMessageDialog(container, "Error updating payment status. Please try again.", "Error",
                    JOptionPane.ERROR_MESSAGE); // แสดงข้อความแจ้งเตือนเกี่ยวกับข้อผิดพลาด
        }
    }

    // แสดงหน้าต่างแจ้งเตือนเงินทอน
    private void showChangeDialog(double change) {
        JOptionPane.showMessageDialog(container, "Change: " + change, "Change", JOptionPane.INFORMATION_MESSAGE); // แสดงจำนวนเงินทอน
        restartApplication(); // เริ่มแอปพลิเคชันใหม่
    }

    // เริ่มแอปพลิเคชันใหม่
    private void restartApplication() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                dispose(); // ปิดหน้าต่างปัจจุบัน
                new Cashing(); // เริ่มแอปพลิเคชันใหม่
            }
        });
    }

    // // เมธอด main สำหรับเรียกใช้งานโปรแกรม
    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {
    //         public void run() {
    //             new Cashing(); // เริ่มทำงานของโปรแกรม
    //         }
    //     });
    // }
}
