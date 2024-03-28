import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class History extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;

    public History() {
        setTitle("Payment History");
        setSize(800, 400); // เปลี่ยนขนาดหน้าต่างให้กว้างขึ้นเพื่อรองรับข้อมูลเพิ่มเติม
        setLocationRelativeTo(null);

        initializeComponents();
        loadPaymentHistory("payment_history.txt");
    }

    // กำหนดคอมโพเนนต์ต่าง ๆ ในหน้าต่าง
    private void initializeComponents() {
        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ทำให้เซลล์ในตารางไม่สามารถแก้ไขได้
            }
        };
        table.setFocusable(false); // ปิดการใช้งานคีย์บอร์ดเพื่อแก้ไข
        scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    // โหลดข้อมูลประวัติการชำระเงินจากไฟล์
    private void loadPaymentHistory(String fileName) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Room Number");
        model.addColumn("Payment Status");
        model.addColumn("Room Cost");
        model.addColumn("Electricity Unit");
        model.addColumn("Electricity Cost");
        model.addColumn("Water Unit");
        model.addColumn("Water Cost");
        model.addColumn("Total Cost");
        model.addColumn("Payment Date");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String roomNumber = data[0];
                String paymentStatus = data[1].equals("0") ? "Not Paid" : "Paid";
                String electricityUnit = data[3];
                String waterUnit = data[4];
                String totalCost = data[5];
                String paymentDate = data[6];
                Double Cost = Double.parseDouble(electricityUnit) * 8;
                Double waterCost;
                if (Double.parseDouble(waterUnit) <= 50) {
                    waterCost = Double.parseDouble(waterUnit) * 6;
                } else if (Double.parseDouble(waterUnit) <= 100) {
                    waterCost = Double.parseDouble(waterUnit) * 7;
                } else {
                    waterCost = Double.parseDouble(waterUnit) * 10;
                }

                // อ่านข้อมูลหมายเลขห้องจากไฟล์ room.txt
                String roomInfo = readRoomInfo(roomNumber);
                String[] roomData = roomInfo.split(" ");
                String roomCost = roomData[2]; // ข้อมูลค่าน้ำ

                model.addRow(new String[]{roomNumber, paymentStatus, roomCost, electricityUnit, String.valueOf(Cost), waterUnit,String.valueOf(waterCost), totalCost, paymentDate});
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        table.setModel(model);

        // กำหนดขนาดฟอนต์ให้ใหญ่ขึ้น
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setFont(new Font("Tahoma", Font.PLAIN, 14)); // เลือกฟอนต์และขนาดที่ต้องการ
        table.setDefaultRenderer(Object.class, renderer);
    }

    // อ่านข้อมูลหมายเลขห้องจากไฟล์ room.txt
    private String readRoomInfo(String roomNumber) throws IOException {
        String roomFileName = "room.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(roomFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                if (data[0].equals(roomNumber)) {
                    return line;
                }
            }
        }
        return "";
    }
}
