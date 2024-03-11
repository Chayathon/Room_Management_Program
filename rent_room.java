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

        FnameLabel = new JLabel("Firstname : ");
        add(FnameLabel);
        FnameField = new JTextField(14);
        add(FnameField);
        
        LnameLabel = new JLabel("Lastname : ");
        add(LnameLabel);
        LnameField = new JTextField(14);
        add(LnameField);
        
        telLabel = new JLabel("Tel : ");
        add(telLabel);
        telField = new JTextField(14);
        add(telField);
        
        addressLabel = new JLabel("Address : ");
        add(addressLabel);
        addressField = new JTextArea(3, 14);
        add(addressField);

        confirmBtn = new JButton("    Confirm    ");
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Date date = new Date();
                try {
                    SimpleDateFormat dateFormatTitle = new SimpleDateFormat("dd-MM-yyyy");
                    String fileName = "Room_Receipt_" + dateFormatTitle.format(date) + "_" + roomNumber + ".txt";
                    FileWriter writer = new FileWriter(fileName);
                    writer.write("Room No. : " + roomNumber + "\n");
                    writer.write("Type : " + roomType + "\n");
                    writer.write("Name : " + FnameField.getText() + " " + LnameField.getText() + "\n");
                    writer.write("Tel : " + telField.getText() + "\n");
                    writer.write("Address : " + addressField.getText() + "\n");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    writer.write("Rent date : " + dateFormat.format(date));
                    writer.close();
                    System.out.println(fileName + " was saved");
                }
                catch (IOException e) {
                    System.out.println("Error while writing file " + e.getMessage());
                }
            }
        });
        add(confirmBtn);

        backBtn = new JButton("    Back    ");
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.first(container);
            }
        });
        add(backBtn);
    }
}