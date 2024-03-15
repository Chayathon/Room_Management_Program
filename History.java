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
        setSize(600, 400);
        setLocationRelativeTo(null);

        initializeComponents();
        loadPaymentHistory("payment_history.txt");
    }

    private void initializeComponents() {
        table = new JTable();
        scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private void loadPaymentHistory(String fileName) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Room Number");
        model.addColumn("Payment Status");
        model.addColumn("Electricity Unit");
        model.addColumn("Total Cost");
        model.addColumn("Payment Date");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String roomNumber = data[0];
                String paymentStatus = data[1].equals("0") ? "Not Paid" : "Paid";
                String electricityCost = data[2];
                String totalCost = data[3];
                String paymentDate = data[4];

                model.addRow(new String[]{roomNumber, paymentStatus, electricityCost, totalCost, paymentDate});
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        table.setModel(model);

        // กำหนดขนาดฟอนต์ให้ใหญ่ขึ้น
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setFont(new Font("Tahoma", Font.PLAIN, 44)); // เลือกฟอนต์และขนาดที่ต้องการ
        table.setDefaultRenderer(Object.class, renderer);
    }

 
}
