import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class rent_room_info extends JPanel {
    JLabel FnameLabel, LnameLabel, telLabel, addressLabel;
    JTextField FnameField, LnameField, telField;
    JTextArea addressField;
    JButton confirmBtn, backBtn;
    private String roomNumber;
    private String roomType;
    private String roomPrice;

    public rent_room_info(CardLayout cardLayout, Container container, String roomNumber, String roomType, String roomPrice) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPrice = roomPrice;

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

                    String fileEdit = "room.txt";
                    String targetText = roomNumber + " " + roomType + " " + roomPrice + " " + "0";

                    // อ่านไฟล์ทีละบรรทัด
                    BufferedReader reader = new BufferedReader(new FileReader(fileEdit));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        // ตรวจสอบว่าบรรทัดตรงกับข้อความที่ต้องการหรือไม่
                        
                        String[] data = line.split(" ");
                        String roomNumber = data[0];
                        String roomType = data[1];
                        String roomStatus = data[2];

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

                try (PrintWriter writer = new PrintWriter(new FileWriter("rent.txt", true))) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    writer.println(roomNumber + " " + roomType + " " + roomPrice + " " + FnameField.getText() + " " + LnameField.getText() + " " + telField.getText() + " " + addressField.getText() + " " + dateFormat.format(date));
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

    public void write(String roomNumber, String roomType, String roomPrice) {
        File fileName = new File("rent.txt");

        if (fileName.exists()) {
            int choice = JOptionPane.showConfirmDialog(null,
                    "Confirm to Rent " + roomNumber + " ?", "Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (choice != JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Rent info not saved.");
                return;
            }
        }

        try {
            writeToFile(roomNumber, roomType, roomPrice, fileName);
            JOptionPane.showMessageDialog(null, "Rent info saved successfully!");
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving Rent info: " + e.getMessage());
        }
    }

    private static void writeToFile(String roomNumber, String roomType, String roomPrice, File equipmentFile) throws IOException {
        // try (PrintWriter writer = new PrintWriter(new FileWriter("rent.txt", true))) {
        //     writer.println("Room No. : " + roomNumber + "\n" +
        //                     "Room Type : " + roomType + "\n" +
        //                     "Room Price : " + roomPrice + "\n" +
        //                     "Name : " + FnameField.getText() + " " + LnameField.getText()
        //                     );
        // }
    }
}