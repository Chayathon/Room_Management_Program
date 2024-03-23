import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class checkInInfo extends JFrame {
    Container container;
    JPanel headerPanel, menuPanel, buttonPanel;
    JLabel roomNumberLabel, roomTypeLabel, roomPriceLabel, FnameLabel, LnameLabel, telLabel;
    JTextField roomNumberField, roomTypeField, roomPriceField, FnameField, LnameField, telField;
    JButton confirmBtn, backBtn;

    public checkInInfo(String roomNumber, String roomType, String roomPrice) {
        super("Check In");
        container = getContentPane();

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Check In");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 2, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        headerPanel.add(menuPanel);
        
        roomNumberLabel = new JLabel("Room No. : ");
        menuPanel.add(roomNumberLabel);
        roomNumberField = new JTextField(20);
        roomNumberField.setText(roomNumber);
        roomNumberField.setEditable(false);
        menuPanel.add(roomNumberField);
        
        roomTypeLabel = new JLabel("Room Type : ");
        menuPanel.add(roomTypeLabel);
        roomTypeField = new JTextField(20);
        roomTypeField.setText(roomType);
        roomTypeField.setEditable(false);
        menuPanel.add(roomTypeField);
        
        roomPriceLabel = new JLabel("Room Price : ");
        menuPanel.add(roomPriceLabel);
        roomPriceField = new JTextField(20);
        roomPriceField.setText(roomPrice);
        roomPriceField.setEditable(false);
        menuPanel.add(roomPriceField);
        
        FnameLabel = new JLabel("Firstname : ");
        menuPanel.add(FnameLabel);
        FnameField = new JTextField(20);
        menuPanel.add(FnameField);
        
        LnameLabel = new JLabel("Lastname : ");
        menuPanel.add(LnameLabel);
        LnameField = new JTextField(20);
        menuPanel.add(LnameField);
        
        telLabel = new JLabel("Tel : ");
        menuPanel.add(telLabel);
        telField = new JTextField(20);
        menuPanel.add(telField);

        confirmBtn = new JButton("    Confirm    ");
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int choice = JOptionPane.showConfirmDialog(null, "Confirm to Check In " + roomNumber + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    writeToFile(roomNumber, roomType, roomPrice, FnameField.getText(), LnameField.getText(), telField.getText());
                    dispose();
                    JOptionPane.showMessageDialog(null, "Check In Successfully!", "Successfully", 1);
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
    
    public void writeToFile(String roomNumber, String roomType, String roomPrice, String firstName, String lastName, String tel) {
        Date date = new Date();
        try {
            SimpleDateFormat dateFormatTitle = new SimpleDateFormat("dd-MM-yyyy");
            String fileName = "Room_Receipt_" + dateFormatTitle.format(date) + "_" + roomNumber + ".txt";
            FileWriter writer = new FileWriter(fileName);
            writer.write("Room No. : " + roomNumber + "\n");
            writer.write("Type : " + roomType + "\n");
            writer.write("Name : " + firstName + " " + lastName + "\n");
            writer.write("Tel : " + tel + "\n");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            writer.write("Check in date : " + dateFormat.format(date));
            writer.close();
            System.out.println(fileName + " was saved");

            String fileEdit = "room.txt";
            String targetText = roomNumber + " " + roomType + " " + roomPrice + " " + "0" + " " + "1";

            // อ่านไฟล์ทีละบรรทัด
            BufferedReader reader = new BufferedReader(new FileReader(fileEdit));
            String line;

            while ((line = reader.readLine()) != null) {
                // ตรวจสอบว่าบรรทัดตรงกับข้อความที่ต้องการหรือไม่
                if (line.contains(targetText)) {
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

                    String[] value = targetText.split(" ");
                    String name = value[0];
                    String type = value[1];
                    String price = value[2];
                    String status = "1";
                    String newData = name + " " + type + " " + price + " " + status + " " + "1";

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

        try (PrintWriter writer = new PrintWriter(new FileWriter("checkin_checkout.txt", true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            writer.println(roomNumber + " " + roomType + " " + roomPrice + " " + firstName + " " + lastName + " " + tel + " " + dateFormat.format(date) + " " + "-");
        }
        catch (IOException e) {
            System.out.println("Error while writing file " + e.getMessage());
        }
    }
}