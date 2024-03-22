import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class addRoom extends JFrame {
    Container container;
    JPanel headerPanel, menuPanel, buttoPanel;
    JLabel roomNumberLabel, roomTypeLabel, roomPriceLabel;
    JTextField roomNumberField, roomTypeField, roomPriceField;
    JButton confirmBtn;

    public addRoom() {
        super("Add Room");
        container = getContentPane();

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Add Room");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 2, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        headerPanel.add(menuPanel);

        roomNumberLabel = new JLabel("Room Number : ");
        menuPanel.add(roomNumberLabel);
        roomNumberField = new JTextField(20);
        menuPanel.add(roomNumberField);

        roomTypeLabel = new JLabel("Room Type : ");
        menuPanel.add(roomTypeLabel);
        roomTypeField = new JTextField(20);
        menuPanel.add(roomTypeField);

        roomPriceLabel = new JLabel("Room Price : ");
        menuPanel.add(roomPriceLabel);
        roomPriceField = new JTextField(20);
        menuPanel.add(roomPriceField);

        confirmBtn = new JButton("Add");
        confirmBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                writeToFile(roomNumberField.getText(), roomTypeField.getText(), roomPriceField.getText());
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(500, 300);
        setVisible(true);
    }

    public void writeToFile(String roomNumber, String roomType, String roomPrice) {
        File fileName = new File("room.txt");

        if (fileName.exists()) {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure to Add room " + roomNumber + " ?", "Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Room Added.");

                try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
                    writer.println("R" + roomNumber + " " + roomType + " " + roomPrice + " " + "0");
                }
                catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error saving Room: " + e.getMessage());
                }
                return;
            }
        }

        // try {
        //     writeToFile(roomNumber, roomType, roomPrice, fileName);
        //     JOptionPane.showMessageDialog(null, "Room saved successfully!");
        // }
        // catch (IOException e) {
        //     JOptionPane.showMessageDialog(null, "Error saving Room: " + e.getMessage());
        // }
    }

    // private static void writeToFile(String roomNumber, String roomType, String roomPrice, File equipmentFile) {
    //     try (PrintWriter writer = new PrintWriter(new FileWriter(equipmentFile, true))) {
    //         writer.println("R" + roomNumber + " " + roomType + " " + roomPrice + " " + "0");
    //     }
    //     catch (IOException e) {
    //         JOptionPane.showMessageDialog(null, "Error saving Room: " + e.getMessage());
    //     }
    // }

}
