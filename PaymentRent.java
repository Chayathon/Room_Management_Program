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

    private RoomSummary currentRoomSummary;

    public PaymentRent() {
        super("Calculate Payment Rent");
        container = getContentPane();

        cardLayout = new CardLayout(10, 10);
        container.setLayout(new FlowLayout());
        container.setLayout(cardLayout);

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Calculate Payment Rent");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 10, 10, 10));
        headerPanel.add(menuPanel);

        try {
            String fileName = "room.txt";
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String roomNumber = data[0];
                String roomType = data[1];
                String roomPrice = data[2];
                String roomStatus = data[3];
                String roomActive = data[4];

                // ตรวจสอบว่ามีประวัติการชำระเงินที่ตำแหน่ง 2 และสำหรับเดือนปัจจุบันหรือไม่
                boolean canEnableButton = checkPaymentHistory(roomNumber);

                roomBtn = new JButton(roomNumber);
                roomBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roomBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String electricityCost = JOptionPane.showInputDialog("Enter electricity unit:");
                        double roomPriceDouble = Double.parseDouble(roomPrice);
                        double totalCost = roomPriceDouble + (Double.parseDouble(electricityCost) * 8) + 100;

                        // รับวันที่ปัจจุบัน
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String currentDate = dateFormat.format(new Date());

                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("payment_history.txt", true))) {
                            // ปรับเปลี่ยนการเขียนข้อมูลในไฟล์ payment_history.txt
                            writer.write(roomNumber + " "+ "0 " + electricityCost + " " + totalCost + " " + currentDate);
                            writer.newLine();
                            // JOptionPane.showMessageDialog(container, "Payment details saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(container, "Error saving payment details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        // แสดงหน้าต่างสรุปห้อง
                        currentRoomSummary = new RoomSummary(roomNumber, roomType, roomPrice, electricityCost, totalCost);

                        dispose(); // ปิดหน้าต่างปัจจุบัน
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                new PaymentRent(); // เปิดหน้าต่าง PaymentRent ใหม่
                            }
                        });
                    }
                });
 

                if(roomActive.equals("1")) {
                    roomBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            cardLayout.show(container, roomNumber);
                        }
                    });

                    if(roomStatus.equals("Taken") && !checkPaymentHistory(roomNumber)) {
                        menuPanel.add(roomBtn);
                    } else {
                        roomBtn.setEnabled(false);
                        menuPanel.add(roomBtn);
                    }
                }



               
            }

            reader.close();
        } catch(IOException e) {
            System.out.println("Error while writing file " + e.getMessage());
        }

        setSize(1700, 400);
        setVisible(true);
    }

    private boolean checkPaymentHistory(String roomNumber) {
        try {
            String fileName = "payment_history.txt";
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                if (data[0].equals(roomNumber) && data.length >= 5 && data[4].startsWith(dateFormat.format(new Date()))) {
                    reader.close();
                    return true; // ค่าห้องได้ถูกชำระแล้วสำหรับเดือนปัจจุบัน
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // ยังไม่มีการชำระเงินสำหรับเดือนปัจจุบัน
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PaymentRent();
            }
        });
    }
}
