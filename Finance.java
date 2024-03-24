import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Finance extends JFrame implements ActionListener {
    JPanel headerPanel, menuPanel; // ประกาศพาเนลสำหรับส่วนหัวและเมนู
    Container container; // ประกาศคอนเทนเนอร์สำหรับเก็บองค์ประกอบ
    JButton PaymentRentBtn, HistoryBtn, CashingBtn, ExpensesBtn; // ประกาศปุ่มสำหรับการชำระค่าเช่า, ประวัติ, และการนำเข้าเงิน
    PaymentRent PaymentRent; // ประกาศอ็อบเจกต์ PaymentRent
    History History; // ประกาศอ็อบเจกต์ History
    Cashing Cashing; // ประกาศอ็อบเจกต์ Cashing
    Expenses Expenses; //ประกาศอ็อบเจกต์ Expenses

    // สร้างคอนสตรักเตอร์ของคลาส Finance
    public Finance() {
        super("Finance"); // กำหนดชื่อหัวของหน้าต่างเป็น "Finance"
        container = getContentPane(); // รับคอนเทนเนอร์ของหน้าต่าง

        // สร้างพาเนลสำหรับส่วนหัวและเพิ่มลงในคอนเทนเนอร์
        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        // สร้างป้ายชื่อ "Finance" และเพิ่มลงในส่วนหัว และกำหนดรูปแบบและขนาดตามที่ต้องการ
        JLabel title = new JLabel("Finance");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        // สร้างพาเนลสำหรับเมนูและเพิ่มลงในส่วนหัว
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 10, 10, 10));
        headerPanel.add(menuPanel);

        // สร้างปุ่ม "Payment Rent" และเพิ่มลงในเมนู
        PaymentRentBtn = new JButton("Payment Rent");
        PaymentRentBtn.addActionListener(this); // กำหนดให้ปุ่มตอบสนองการคลิก
        menuPanel.add(PaymentRentBtn);

        // สร้างปุ่ม "Cashing" และเพิ่มลงในเมนู
        CashingBtn = new JButton("Cashing");
        CashingBtn.addActionListener(this); // กำหนดให้ปุ่มตอบสนองการคลิก
        menuPanel.add(CashingBtn);

        // สร้างปุ่ม "Expenses" และเพิ่มลงในเมนู
        ExpensesBtn = new JButton("Expenses");
        ExpensesBtn.addActionListener(this); // กำหนดให้ปุ่มตอบสนองการคลิก
        menuPanel.add(ExpensesBtn);

        // สร้างปุ่ม "History" และเพิ่มลงในเมนู
        HistoryBtn = new JButton("History");
        HistoryBtn.addActionListener(this); // กำหนดให้ปุ่มตอบสนองการคลิก
        menuPanel.add(HistoryBtn);

        // กำหนดขนาดของหน้าต่าง
        setSize(600, 600);
        setVisible(true); // แสดงหน้าต่าง
    }

    // เมธอดที่เรียกเมื่อมีการคลิกปุ่ม
    @Override
    public void actionPerformed(ActionEvent event) {
        // ตรวจสอบว่าปุ่มที่ถูกคลิกเป็นปุ่มใด แล้วดำเนินการตามนั้น
        if(event.getSource() == PaymentRentBtn) {
            PaymentRent = new PaymentRent(); // สร้างอ็อบเจกต์ PaymentRent
        } else if(event.getSource() == HistoryBtn) {
            History gui = new History(); // สร้างอ็อบเจกต์ History
            gui.setVisible(true); // แสดงหน้าต่าง History
        }  else if(event.getSource() == CashingBtn) {
            Cashing = new Cashing(); // สร้างอ็อบเจกต์ Cashing
        }   else if(event.getSource() == ExpensesBtn) {
            Expenses = new Expenses(); // สร้างอ็อบเจกต์ Expenses
        } 
    }
}
