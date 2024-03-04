import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class rent_room {
    JFrame window = new JFrame("Rent Rooms");
    JLabel textLabel;
    JTextField textField;
    JTextArea textArea;
    JButton confirmButton;
    JRadioButton radioButton;
    JCheckBox checkBox;
    ButtonGroup buttonGroup;
    Container container;

    public rent_room() {
        container = window.getContentPane();
        container.setLayout( new FlowLayout(FlowLayout.LEFT, 10, 10) );

        textLabel = new JLabel("ชื่อ : ");
        container.add(textLabel);
        textField = new JTextField(20);
        container.add(textField);
        
        textLabel = new JLabel("นามสกุล : ");
        container.add(textLabel);
        textField = new JTextField(20);
        container.add(textField);
        
        textLabel = new JLabel("เบอร์โทร : ");
        container.add(textLabel);
        textField = new JTextField(20);
        container.add(textField);
        
        textLabel = new JLabel("ที่อยู่ : ");
        container.add(textLabel);
        textArea = new JTextArea(3, 20);
        container.add(textArea);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton = new JRadioButton("ห้องพัดลม (1,800฿)"));
        container.add(radioButton);
        buttonGroup.add(radioButton = new JRadioButton("ห้องแอร์ (2,500฿)"));
        container.add(radioButton);

        checkBox = new JCheckBox("ยอมรับสัญญาการเช่าห้องพัก");
        container.add(checkBox);

        confirmButton = new JButton("    ยืนยัน    ");
        container.add(confirmButton);

        window.setSize( 600, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        rent_room rent = new rent_room();
    }
}