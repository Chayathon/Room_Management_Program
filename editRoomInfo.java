import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class editRoomInfo extends JFrame {
    Container container;
    JPanel headerPanel, menuPanel, buttonPanel;
    JLabel roomNumberLabel, roomTypeLabel, roomPriceLabel, roomStatusLabel;
    JTextField roomNumberField, roomTypeField, roomPriceField, roomStatusField;
    JTextArea addressField;
    JButton confirmBtn, cancelBtn;

    public editRoomInfo(String roomNumber, String roomType, String roomPrice, String roomStatus) {
        super("Edit Room");
        container = getContentPane();

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Edit Room");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 2, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        headerPanel.add(menuPanel);
        roomNumberLabel = new JLabel("Room No. : ");
        menuPanel.add(roomNumberLabel);
        roomNumberField = new JTextField(14);
        roomNumberField.setText(roomNumber);
        menuPanel.add(roomNumberField);

        roomTypeLabel = new JLabel("Type : ");
        menuPanel.add(roomTypeLabel);
        roomTypeField = new JTextField(14);
        roomTypeField.setText(roomType);
        menuPanel.add(roomTypeField);

        roomPriceLabel = new JLabel("Price : ");
        menuPanel.add(roomPriceLabel);
        roomPriceField = new JTextField(14);
        roomPriceField.setText(roomPrice);
        menuPanel.add(roomPriceField);

        roomStatusLabel = new JLabel("Status : ");
        menuPanel.add(roomStatusLabel);
        roomStatusField = new JTextField(14);
        roomStatusField.setText(roomStatus);
        menuPanel.add(roomStatusField);

        confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int choice = JOptionPane.showConfirmDialog(null, "Confirm to Edit " + roomNumber + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    writeToFile(roomNumber, roomType, roomPrice, roomStatus, roomNumberField.getText(), roomTypeField.getText(), roomPriceField.getText(), roomStatusField.getText());
                    dispose();
                    JOptionPane.showMessageDialog(null, "Room Edited.", "Successfully", 1);
                    return;
                }
            }
        });
        JPanel buttonPanel = new JPanel();
        confirmBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(confirmBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(500, 400);
        setVisible(true);
    }

    public void writeToFile(String oldRoomNumber, String oldRoomType, String oldRoomPrice, String oldRoomStatus, String newRoomNumber, String newRoomType, String newRoomPrice, String newRoomStatus) {
        try {
            String fileEdit = "room.txt";
            String targetText = oldRoomNumber + " " + oldRoomType + " " + oldRoomPrice + " " + oldRoomStatus;

            // อ่านไฟล์ทีละบรรทัด
            BufferedReader reader = new BufferedReader(new FileReader(fileEdit));
            String line;

            while ((line = reader.readLine()) != null) {
                // ตรวจสอบว่าบรรทัดตรงกับข้อความที่ต้องการหรือไม่
                if (line.contains(targetText)) {
                    System.out.println("Found : " + targetText);

                    Scanner scanner = new Scanner(new File(fileEdit));
                    ArrayList<String> lines = new ArrayList<>();

                    while (scanner.hasNextLine()) {
                        lines.add(scanner.nextLine());
                    }
                    scanner.close();

                    int index = -1;
                    for (int i = 0; i < lines.size(); i++) {
                        if (lines.get(i).equals(targetText)) {
                            index = i;
                            break;
                        }
                    }
                    System.out.println("Line : " + index);

                    String newData = newRoomNumber + " " + newRoomType + " " + newRoomPrice + " " + newRoomStatus;
                    if(index != -1) {
                        lines.set(index, newData);
                    }

                    PrintWriter edit = new PrintWriter(new File(fileEdit));
                    for (String line1 : lines) {
                        edit.println(line1);
                    }
                    edit.close();
                    break;
                }
            }
            reader.close();
        }
        catch (IOException e) {
            System.out.println("Error while writing file " + e.getMessage());
        }
    }
}