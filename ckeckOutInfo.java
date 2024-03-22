import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ckeckOutInfo extends JPanel {
    JLabel roomNumberLabel, roomTypeLabel, roomPriceLabel, roomStatusLabel, nameLabel, telLabel;
    JTextField roomNumberField, roomTypeField, roomPriceField, roomStatusField, nameField, telField;
    JButton confirmBtn, cancelBtn;

    public ckeckOutInfo(CardLayout cardLayout, Container container, String roomNumber, String roomType, String roomPrice, String roomStatus) {
        roomNumberLabel = new JLabel("Room No. : ");
        add(roomNumberLabel);
        roomNumberField = new JTextField(14);
        roomNumberField.setText(roomNumber);
        roomNumberField.setEditable(false);
        add(roomNumberField);

        roomTypeLabel = new JLabel("Type : ");
        add(roomTypeLabel);
        roomTypeField = new JTextField(14);
        roomTypeField.setText(roomType);
        roomTypeField.setEditable(false);
        add(roomTypeField);

        roomPriceLabel = new JLabel("Price : ");
        add(roomPriceLabel);
        roomPriceField = new JTextField(14);
        roomPriceField.setText(roomPrice);
        roomPriceField.setEditable(false);
        add(roomPriceField);

        readFile(roomNumber);

        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.first(container);
            } 
        });
        add(cancelBtn);

        confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int choice = JOptionPane.showConfirmDialog(null, "Confirm to Check Out " + roomNumber + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    writeToFile(roomNumber, roomType, roomPrice, roomStatus);
                    return;
                }
            }
        });
        add(confirmBtn);

        setSize(600, 600);
        setVisible(true);
    }

    public void writeToFile(String roomNumber, String roomType, String roomPrice, String roomStatus) {
        try {
            String fileEdit = "room.txt";
            String targetText = roomNumber + " " + roomType + " " + roomPrice + " " + roomStatus;

            // อ่านไฟล์ทีละบรรทัด
            BufferedReader reader = new BufferedReader(new FileReader(fileEdit));
            String line;

            while ((line = reader.readLine()) != null) {
                // ตรวจสอบว่าบรรทัดตรงกับข้อความที่ต้องการหรือไม่
                
                String[] data = line.split(" ");
                String number = data[0];
                String type = data[1];
                String price = data[2];

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
                    String newData = number + " " + type + " " + price + " " + "0";

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

        try (PrintWriter writer = new PrintWriter(new FileWriter("checkout.txt", true))) {
            File rentFile = new File("checkin.txt");
            Scanner scanner = new Scanner(rentFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.toLowerCase().contains(roomNumber.toLowerCase())) { 
                    String [] data;
                    data = line.split(" ");

                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    writer.println(data[0].trim() + " " + data[1].trim() + " " + data[2].trim() + " " + data[3].trim() + " " + data[4].trim() + " " + data[5].trim() + " " + dateFormat.format(date));
                }
            }
            
            
        }
        catch (IOException e) {
            System.out.println("Error while writing file " + e.getMessage());
        }
    }

    public void readFile(String roomNumber) {
        File rentFile = new File("checkin.txt");
        try (Scanner scanner = new Scanner(rentFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.toLowerCase().contains(roomNumber.toLowerCase())) { 
                    String [] data;
                    data = line.split(" ");

                    if (data.length == 8) {
                        nameLabel = new JLabel("Name : ");
                        add(nameLabel);
                        nameField = new JTextField(14);
                        nameField.setText(data[3].trim() + " " + data[4].trim());
                        nameField.setEditable(false);
                        add(nameField);

                        telLabel = new JLabel("Tel : ");
                        add(telLabel);
                        telField = new JTextField(14);
                        telField.setText(data[5].trim());
                        telField.setEditable(false);
                        add(telField);
                    }
                    else {
                        System.out.println("Warning: Invalid data format in line: " + line);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + rentFile.getName());
        }
    }
}