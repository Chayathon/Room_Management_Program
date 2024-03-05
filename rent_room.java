import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class rent_room {
    JFrame frame = new JFrame("Rent Rooms");
    JRadioButton fanButton, airButton;
    Container container;

    public rent_room() {
        container = frame.getContentPane();
        container.setLayout(new FlowLayout(1, 10, 10));

        // JLabel title = new JLabel("เช่าห้อง");
        // title.setHorizontalAlignment(JLabel.CENTER);
        // title.setFont(new Font("Tahoma", Font.BOLD, 36));
        // container.add(title, BorderLayout.NORTH);

        JLabel FnameLabel = new JLabel("ชื่อ : ");
        container.add(FnameLabel);
        JTextField FnameField = new JTextField(20);
        container.add(FnameField);
        
        JLabel LnameLabel = new JLabel("นามสกุล : ");
        container.add(LnameLabel);
        JTextField LnameField = new JTextField(20);
        container.add(LnameField);
        
        JLabel telLabel = new JLabel("เบอร์โทร : ");
        container.add(telLabel);
        JTextField telField = new JTextField(20);
        container.add(telField);
        
        JLabel addressLabel = new JLabel("ที่อยู่ : ");
        container.add(addressLabel);
        JTextArea addressField = new JTextArea(3, 20);
        container.add(addressField);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(fanButton = new JRadioButton("ห้องพัดลม (1,800฿)"));
        container.add(fanButton);
        buttonGroup.add(airButton = new JRadioButton("ห้องแอร์ (2,500฿)"));
        container.add(airButton);

        JCheckBox checkBox = new JCheckBox("ยอมรับสัญญาการเช่าห้องพัก");
        container.add(checkBox);

        JButton confirmButton = new JButton("    ยืนยัน    ");
        container.add(confirmButton);

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        rent_room rent = new rent_room();
    }
}