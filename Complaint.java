import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Complaint extends JFrame implements ActionListener {
    // ประกาศตัวแปรสำหรับ GUI Components
    JPanel headerPanel, menuPanel;
    Container container;
    JButton ComplaintFormBtn, ComplaintHistoryBtn;
    ComplaintForm ComplaintForm;
    ComplaintHistory ComplaintHistory;

    // คอนสตรักเตอร์ของคลาส
    public Complaint() {
        super("Complaint"); // กำหนดหัวข้อของหน้าต่าง GUI
        container = getContentPane(); // สร้าง Container สำหรับ GUI Components

        // สร้างและจัดวาง Header Panel และเพิ่มไปยัง Container
        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        // สร้างและจัดวางหัวข้อและเพิ่มไปยัง Header Panel
        JLabel title = new JLabel("Complaint");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        // สร้าง Menu Panel และเพิ่มไปยัง Header Panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 10, 10, 10)); // กำหนด Layout ของ Menu Panel เป็น GridLayout
        headerPanel.add(menuPanel);

        // สร้างปุ่ม "Add Complaint" และเพิ่ม ActionListener
        ComplaintFormBtn = new JButton("Add Complaint");
        ComplaintFormBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        ComplaintFormBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ComplaintFormBtn.addActionListener(this);
        menuPanel.add(ComplaintFormBtn);

        // สร้างปุ่ม "History" และเพิ่ม ActionListener
        ComplaintHistoryBtn = new JButton("History");
        ComplaintHistoryBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        ComplaintHistoryBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ComplaintHistoryBtn.addActionListener(this);
        menuPanel.add(ComplaintHistoryBtn);

        // กำหนดขนาดของหน้าต่าง GUI
        setSize(600, 600);
        setVisible(true); // กำหนดให้หน้าต่าง GUI แสดงผล
    }

    // เมทอด actionPerformed สำหรับจัดการเหตุการณ์เมื่อมีการคลิกปุ่ม
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == ComplaintFormBtn) { // ถ้าปุ่มที่ถูกคลิกคือ "Add Complaint"
            ComplaintForm = new ComplaintForm(); // สร้างหน้าต่าง Add Complaint
        } else if(event.getSource() == ComplaintHistoryBtn) { // ถ้าปุ่มที่ถูกคลิกคือ "History"
            ComplaintHistory = new ComplaintHistory(); // สร้างหน้าต่าง History
        } 
    }
}
