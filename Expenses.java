import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Expenses extends JFrame implements ActionListener {
    // คอมโพเนนต์สำหรับ GUI
    JPanel headerPanel, menuPanel;
    Container container;
    JButton expensesFormBtn, expensesHistoryBtn;
    ExpensesForm ExpensesForm;
    ExpensesHistory ExpensesHistory;

    // คอนสตรักเตอร์
    public Expenses() {
        super("Expenses"); // กำหนดชื่อของ JFrame
        container = getContentPane(); // สร้าง Container สำหรับคอมโพเนนต์ GUI

        // สร้าง Header Panel และเพิ่มป้ายชื่อไปยัง Header Panel
        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Expenses");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        // สร้าง Menu Panel และเพิ่มปุ่ม Add Expenses และปุ่ม History ลงใน Menu Panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 10, 10, 10));
        headerPanel.add(menuPanel);

        expensesFormBtn = new JButton("Add Expenses");
        expensesFormBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        expensesFormBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        expensesFormBtn.addActionListener(this);
        menuPanel.add(expensesFormBtn);

        expensesHistoryBtn = new JButton("History");
        expensesHistoryBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        expensesHistoryBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        expensesHistoryBtn.addActionListener(this);
        menuPanel.add(expensesHistoryBtn);

        setSize(600, 600); // กำหนดขนาดของ JFrame
        setVisible(true); // ทำให้ JFrame ปรากฏอยู่บนหน้าจอ
    }

    // เมธอดที่ถูกเรียกเมื่อมีการคลิกปุ่ม
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == expensesFormBtn) {
            // สร้างหน้าต่างเพิ่มค่าใช้จ่ายเมื่อคลิกที่ปุ่ม "เพิ่มค่าใช้จ่าย"
            ExpensesForm = new ExpensesForm();

        } else if (event.getSource() == expensesHistoryBtn) {
            // แสดงประวัติค่าใช้จ่ายเมื่อคลิกที่ปุ่ม "ประวัติ"
            ExpensesHistory = new ExpensesHistory();
            ExpensesHistory.setVisible(true);
        }
    }
}
