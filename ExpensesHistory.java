import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

// ประกาศคลาส ExpensesHistory ซึ่งเป็น JFrame
public class ExpensesHistory extends JFrame {

    private JTable table;

    // Constructor ของคลาส ExpensesHistory
    public ExpensesHistory() {
        setTitle("Expenses History"); // กำหนดชื่อหน้าต่าง JFrame
        setSize(800, 400); // กำหนดขนาดของหน้าต่าง
        setLocationRelativeTo(null); // กำหนดให้หน้าต่างปรากฏตรงกลางของหน้าจอ

        initializeComponents(); // เรียกเมธอดเพื่อกำหนดคอมโพเนนต์ต่างๆ
        loadPaymentHistory("expenses.txt"); // โหลดประวัติการจ่ายเงินจากไฟล์ expenses.txt
    }

    // เมธอดสำหรับกำหนดคอมโพเนนต์ต่างๆในหน้าต่าง
    private void initializeComponents() {
        table = new JTable(); // สร้าง JTable เพื่อแสดงข้อมูล
        JScrollPane scrollPane = new JScrollPane(table); // สร้าง JScrollPane เพื่อให้สามารถเลื่อนข้อมูลในกรณีที่ข้อมูลมีมากเกินจอ
        add(scrollPane); // เพิ่ม JScrollPane เข้าไปในหน้าต่าง

        // เพิ่มปุ่ม Delete และ Save
        JButton deleteButton = new JButton("Delete");
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JButton saveButton = new JButton("Save");
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // เพิ่ม Action Listener ให้กับปุ่ม Delete และ Save
        deleteButton.addActionListener(e -> deleteSelectedRows());
        saveButton.addActionListener(e -> saveChanges());

        // สร้าง JPanel เพื่อใส่ปุ่ม Delete และ Save
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);

        // เพิ่ม JPanel ที่มีปุ่มเข้าไปในโครงร่าง JFrame ด้านล่าง
        add(buttonPanel, BorderLayout.SOUTH);

        // อนุญาตให้เลือกทั้งแถวเพื่อลบข้อมูล
        table.setCellSelectionEnabled(false);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);

        // อนุญาตให้เลือกหลายแถวพร้อมกัน
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // เปิดใช้งานการเรียงลำดับข้อมูล
        table.setAutoCreateRowSorter(true);
    }

    // เมธอดสำหรับโหลดประวัติการจ่ายเงินจากไฟล์
    private void loadPaymentHistory(String fileName) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // ทำให้สามารถแก้ไขเซลล์ทั้งหมดได้
                return true;
            }
        };

        model.addColumn("Expenses Name"); // เพิ่มคอลัมน์ "ชื่อค่าใช้จ่าย"
        model.addColumn("Amount"); // เพิ่มคอลัมน์ "จำนวนเงิน"
        model.addColumn("Expenses Date"); // เพิ่มคอลัมน์ "วันที่ค่าใช้จ่าย"

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("`");
                model.addRow(data);
            }
        } catch (IOException e) {
            // แสดงข้อความแจ้งเตือนเมื่อเกิดข้อผิดพลาดในการอ่านไฟล์
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        table.setModel(model); // กำหนดโมเดลของ JTable

        // กำหนดรูปแบบตัวอักษรสำหรับความชัดเจนของข้อมูล
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setFont(new Font("Tahoma", Font.PLAIN, 14)); // กำหนดฟอนต์และขนาดตัวอักษร
        table.setDefaultRenderer(Object.class, renderer);
    }

    // เมธอดสำหรับลบแถวที่ถูกเลือก
    private void deleteSelectedRows() {
        // ดึงแถวที่ถูกเลือก
        int[] selectedRows = table.getSelectedRows();

        // วนลูปผ่านแถวที่ถูกเลือกในลำดับย้อนกลับเพื่อหลีกเลี่ยงปัญหาดัชนี
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            ((DefaultTableModel) table.getModel()).removeRow(selectedRows[i]);
        }
    }

    // เมธอดสำหรับบันทึกการเปลี่ยนแปลง
    private void saveChanges() {
        // ดึงโมเดลของตาราง
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // ระบุชื่อไฟล์
        String fileName = "expenses.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // เขียนแต่ละแถวของตารางลงในไฟล์
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    writer.write(model.getValueAt(i, j).toString());
                    if (j < model.getColumnCount() - 1) {
                        writer.write("`"); // คั่นคอลัมน์ด้วยเครื่องหมาย backtick
                    }
                }
                writer.newLine(); // ขึ้นบรรทัดใหม่
            }
        } catch (IOException e) {
            // แสดงข้อความแจ้งเตือนเมื่อเกิดข้อผิดพลาดในการเขียนไฟล์
            JOptionPane.showMessageDialog(this, "Error saving changes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return; // ออกจากเมธอดเมื่อเกิดข้อผิดพลาด
        }

        // แสดงข้อความเมื่อบันทึกการเปลี่ยนแปลงเรียบร้อย
        JOptionPane.showMessageDialog(this, "Changes saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // ส่วน main method ถูกปิดคอมเมนต์ไว้เพื่อป้องกันการเรียกใช้งานซ้ำ
    // สามารถเปิดคอมเมนต์เพื่อทดสอบการใช้งานได้
    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         ExpensesHistory expensesHistory = new ExpensesHistory();
    //         expensesHistory.setVisible(true);
    //     });
    // }
}

