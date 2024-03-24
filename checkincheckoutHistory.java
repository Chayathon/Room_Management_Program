import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class checkincheckoutHistory extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;

    public checkincheckoutHistory() {
        setTitle("Rental History");
        setSize(800, 400); // เปลี่ยนขนาดหน้าต่างให้กว้างขึ้นเพื่อรองรับข้อมูลเพิ่มเติม
        setLocationRelativeTo(null);
        setVisible(true);

        initializeComponents();
        loadPaymentHistory("checkin_checkout.txt");
    }

    // กำหนดคอมโพเนนต์ต่าง ๆ ในหน้าต่าง
    private void initializeComponents() {
        table = new JTable();
        scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    // โหลดข้อมูลประวัติการเช่าจากไฟล์
    private void loadPaymentHistory(String fileName) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Room Number");
        model.addColumn("Room Type");
        model.addColumn("Room Price");
        model.addColumn("Firstname");
        model.addColumn("Lastname");
        model.addColumn("Tel");
        model.addColumn("Check in Date");
        model.addColumn("Check out Date");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String roomNumber = data[0];
                String roomType = data[1];
                String roomPrice = data[2];
                String firstName = data[3];
                String lastName = data[4];
                String tel = data[5];
                String checkinDate = data[6];
                String checkoutDate = data[7];

                // อ่านข้อมูลหมายเลขห้องจากไฟล์ room.txt
                String roomInfo = readRoomInfo(roomNumber);
                String[] roomData = roomInfo.split(" ");

                model.addRow(new String[]{roomNumber, roomType, roomPrice, firstName, lastName, tel, checkinDate, checkoutDate});
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
