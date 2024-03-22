import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class checkInInfo extends JPanel {
    JLabel FnameLabel, LnameLabel, telLabel;
    JTextField FnameField, LnameField, telField;
    JButton confirmBtn, backBtn;

    public checkInInfo(CardLayout cardLayout, Container container, String roomNumber, String roomType, String roomPrice) {
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

        confirmBtn = new JButton("    Confirm    ");
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int choice = JOptionPane.showConfirmDialog(null, "Confirm to Check In " + roomNumber + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    writeToFile(roomNumber, roomType, roomPrice, FnameField.getText(), LnameField.getText(), telField.getText());
                    return;
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
            writer.write("Rent date : " + dateFormat.format(date));
            writer.close();
            System.out.println(fileName + " was saved");

            String fileEdit = "room.txt";
            String targetText = roomNumber + " " + roomType + " " + roomPrice + " " + "0";

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
                    String newData = name + " " + type + " " + price + " " + status;

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

        try (PrintWriter writer = new PrintWriter(new FileWriter("checkin.txt", true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            writer.println(roomNumber + " " + roomType + " " + roomPrice + " " + firstName + " " + lastName + " " + tel + " " + dateFormat.format(date));
        }
        catch (IOException e) {
            System.out.println("Error while writing file " + e.getMessage());
        }
    }
}