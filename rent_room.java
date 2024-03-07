import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class rent_room extends JPanel {
    JLabel FnameLabel, LnameLabel, telLabel, addressLabel;
    JTextField FnameField, LnameField, telField;
    JTextArea addressField;
    JButton confirmBtn, backBtn;
    private int roomNumber;
    private String roomType;

    public rent_room(CardLayout cardLayout, Container container, int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;

        FnameLabel = new JLabel("ชื่อ : ");
        add(FnameLabel);
        FnameField = new JTextField(14);
        add(FnameField);
        
        LnameLabel = new JLabel("นามสกุล : ");
        add(LnameLabel);
        LnameField = new JTextField(14);
        add(LnameField);
        
        telLabel = new JLabel("เบอร์โทร : ");
        add(telLabel);
        telField = new JTextField(14);
        add(telField);
        
        addressLabel = new JLabel("ที่อยู่ : ");
        add(addressLabel);
        addressField = new JTextArea(3, 14);
        add(addressField);

        JCheckBox checkBox = new JCheckBox("ยอมรับสัญญาการเช่าห้องพัก");
        add(checkBox);

        confirmBtn = new JButton("    ยืนยัน    ");
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Date date = new Date();
                try {
                    SimpleDateFormat dateFormatTitle = new SimpleDateFormat("dd-MM-yyyy");
                    String fileName = "Room_Receipt_" + dateFormatTitle.format(date) + "_" + roomNumber + ".txt";
                    FileWriter writer = new FileWriter(fileName);
                    writer.write("หมายเลขห้อง : " + roomNumber + "\n");
                    writer.write("ประเภท : " + roomType + "\n");
                    writer.write("ชื่อ-นามสกุล : " + FnameField.getText() + " " + LnameField.getText() + "\n");
                    writer.write("เบอร์โทรศัพท์ : " + telField.getText() + "\n");
                    writer.write("ที่อยู่ : " + addressField.getText() + "\n");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    writer.write("วันที่เช่า : " + dateFormat.format(date));
                    writer.close();
                    System.out.println(fileName + " was saved");
                }
                catch (IOException e) {
                    System.out.println("Error while writing file " + e.getMessage());
                }
            }
        });
        add(confirmBtn);

        backBtn = new JButton("    ย้อนกลับ    ");
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.first(container);
            }
        });
        add(backBtn);
    }

    // @Override
    // public void actionPerformed(ActionEvent event) {
    //     Date date = new Date();
    //     if(event.getSource() == confirmBtn) {
    //         try {
    //             SimpleDateFormat dateFormatTitle = new SimpleDateFormat("dd-MM-yyyy");
    //             String fileName = "Room_Receipt_" + dateFormatTitle.format(date) + "_" + roomNumber + ".txt";
    //             FileWriter writer = new FileWriter(fileName);
    //             writer.write("หมายเลขห้อง : " + roomNumber + "\n");
    //             writer.write("ชื่อ-นามสกุล : " + FnameField.getText() + " " + LnameField.getText() + "\n");
    //             writer.write("เบอร์โทรศัพท์ : " + telField.getText() + "\n");
    //             writer.write("ที่อยู่ : " + addressField.getText() + "\n");
    //             SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    //             writer.write("วันที่เช่า : " + dateFormat.format(date));
    //             writer.close();
    //             System.out.println(fileName + " was saved");
    //         }
    //         catch (IOException e) {
    //             System.out.println("Error while writing file " + e.getMessage());
    //         }
    //         // frame.dispose();
    //     }
    //     else if(event.getSource() == backBtn) {
    //         cardLayout.first(container);
    //     }
    // }

    // public static void main(String[] args) {
    //     rent_room rent = new rent_room();
    // }
}