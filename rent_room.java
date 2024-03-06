import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class rent_room implements ActionListener {
    JFrame frame = new JFrame("Rent Rooms");
    Container container;
    JLabel FnameLabel, LnameLabel, telLabel, addressLabel;
    JTextField FnameField, LnameField, telField;
    JTextArea addressField;
    JRadioButton fanButton, airButton;
    JButton confirmBtn;
    room_receipt roomReceipt;

    public rent_room() {
        container = frame.getContentPane();
        container.setLayout(new FlowLayout(1, 10, 10));

        // JLabel title = new JLabel("เช่าห้อง");
        // title.setHorizontalAlignment(JLabel.CENTER);
        // title.setFont(new Font("Tahoma", Font.BOLD, 36));
        // container.add(title, BorderLayout.NORTH);

        FnameLabel = new JLabel("ชื่อ : ");
        container.add(FnameLabel);
        FnameField = new JTextField(14);
        container.add(FnameField);
        
        LnameLabel = new JLabel("นามสกุล : ");
        container.add(LnameLabel);
        LnameField = new JTextField(14);
        container.add(LnameField);
        
        telLabel = new JLabel("เบอร์โทร : ");
        container.add(telLabel);
        telField = new JTextField(14);
        container.add(telField);
        
        addressLabel = new JLabel("ที่อยู่ : ");
        container.add(addressLabel);
        addressField = new JTextArea(3, 14);
        container.add(addressField);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(fanButton = new JRadioButton("ห้องพัดลม (1,800฿)"));
        container.add(fanButton);
        buttonGroup.add(airButton = new JRadioButton("ห้องแอร์ (2,500฿)"));
        container.add(airButton);

        JCheckBox checkBox = new JCheckBox("ยอมรับสัญญาการเช่าห้องพัก");
        container.add(checkBox);

        confirmBtn = new JButton("    ยืนยัน    ");
        confirmBtn.addActionListener(this);
        container.add(confirmBtn);

        frame.setSize(500, 250);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Date date = new Date();
        int roomNumber = 1001;
        if(event.getSource() == confirmBtn) {
            try {
                SimpleDateFormat dateFormatTitle = new SimpleDateFormat("dd-MM-yyyy");
                String fileName = "Room_Receipt_" + dateFormatTitle.format(date) + "_" + roomNumber + ".txt";
                FileWriter writer = new FileWriter(fileName);
                writer.write("หมายเลขห้อง : " + roomNumber + "\n");
                writer.write("ชื่อ-นามสกุล : " + FnameField.getText() + " " + LnameField.getText() + "\n");
                writer.write("เบอร์โทรศัพท์ : " + telField.getText() + "\n");
                writer.write("ที่อยู่ : " + addressField.getText() + "\n");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                writer.write("วันที่เช่า : " + dateFormat.format(date));
                writer.close();
                System.out.println(fileName +" was saved");
            }
            catch (IOException e) {
                System.out.println("Error while writing file " + e.getMessage());
            }
            frame.dispose();
        }
    }

    // public static void main(String[] args) {
    //     rent_room rent = new rent_room();
    // }
}