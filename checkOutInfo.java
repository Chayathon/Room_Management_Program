import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class checkOutInfo extends JFrame {
    Container container;
    JPanel headerPanel, menuPanel, buttoPanel;
    JLabel roomNumberLabel, roomTypeLabel, roomPriceLabel, roomStatusLabel, firstNameLabel, lastNameLabel, telLabel;
    JTextField roomNumberField, roomTypeField, roomPriceField, roomStatusField, firstNameField, lastNameField, telField, checkinDate;
    JButton confirmBtn, cancelBtn;

    public checkOutInfo(String roomNumber, String roomType, String roomPrice, String roomStatus) {
        super("Check Out");
        container = getContentPane();

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Check Out");
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

        readFile(roomNumber);

        confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int choice = JOptionPane.showConfirmDialog(null, "Confirm to Check Out " + roomNumber + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    writeToFile(roomNumber, roomType, roomPrice, roomStatus);
                    dispose();
                    JOptionPane.showMessageDialog(null, "Check Out Successfully!", "Successfully", 1);
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

    public void writeToFile(String roomNumber, String roomType, String roomPrice, String roomStatus) {
        try {
            String fileEdit = "room.txt";
            String targetText = roomNumber + " " + roomType + " " + roomPrice + " " + roomStatus + " " + "1";

            // อ่านไฟล์ทีละบรรทัด
            BufferedReader reader = new BufferedReader(new FileReader(fileEdit));
            String line;

            while ((line = reader.readLine()) != null) {
                // ตรวจสอบว่าบรรทัดตรงกับข้อความที่ต้องการหรือไม่
                
                String[] data = line.split(" ");
                String number = data[0];
                String type = data[1];
                String price = data[2];
                String status = "Avaliable";

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
                    String newData = number + " " + type + " " + price + " " + status + " " + "1";

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

        try {
            String fileEdit2 = "checkin_checkout.txt";
            String targetText = roomNumber + " " + roomType + " " + roomPrice + " " + firstNameField.getText() + " " + lastNameField.getText() + " " + telField.getText() + " " + checkinDate.getText() + " " + "-";

            // อ่านไฟล์ทีละบรรทัด
            BufferedReader reader = new BufferedReader(new FileReader(fileEdit2));
            String line;

            while ((line = reader.readLine()) != null) {
                // ตรวจสอบว่าบรรทัดตรงกับข้อความที่ต้องการหรือไม่
                
                String[] data = line.split(" ");
                String number = data[0];
                String type = data[1];
                String price = data[2];

                if (line.contains(targetText)) {
                    Scanner scanner = new Scanner(new File(fileEdit2));
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

                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                    String[] value = targetText.split(" ");
                    String newData = roomNumber + " " + roomType + " " + roomPrice + " " + firstNameField.getText() + " " + lastNameField.getText() + " " + telField.getText() + " " + checkinDate.getText() + " " + dateFormat.format(date);

                    if(index != -1) {
                        lines.set(index, newData);
                    }

                    PrintWriter edit = new PrintWriter(new File(fileEdit2));
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

    public void readFile(String roomNumber) {
        File fileName = new File("checkin_checkout.txt");
        try (Scanner scanner = new Scanner(fileName)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.toLowerCase().contains(roomNumber.toLowerCase())) { 
                    String []data;
                    data = line.split(" ");
                    String oldData = data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " " + data[4] + " " + data[5] + " " + data[6];
                    
                    if(line.toLowerCase().contains(oldData.toLowerCase() + " " + "-" )) {
                        if (data.length == 8) {
                            firstNameLabel = new JLabel("Firstname : ");
                            menuPanel.add(firstNameLabel);
                            firstNameField = new JTextField(20);
                            firstNameField.setText(data[3].trim());
                            firstNameField.setEditable(false);
                            menuPanel.add(firstNameField);
    
                            lastNameLabel = new JLabel("Lastname : ");
                            menuPanel.add(lastNameLabel);
                            lastNameField = new JTextField(20);
                            lastNameField.setText(data[4].trim());
                            lastNameField.setEditable(false);
                            menuPanel.add(lastNameField);
    
                            telLabel = new JLabel("Tel : ");
                            menuPanel.add(telLabel);
                            telField = new JTextField(20);
                            telField.setText(data[5].trim());
                            telField.setEditable(false);
                            menuPanel.add(telField);
    
                            checkinDate = new JTextField();
                            checkinDate.setText(data[6].trim());
                        }
                        else {
                            System.out.println("Warning: Invalid data format in line: " + line);
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + fileName.getName());
        }
    }
}