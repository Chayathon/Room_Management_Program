import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ckeckOutInfo extends JPanel {
    JLabel roomNumberLabel, roomTypeLabel, roomPriceLabel, roomStatusLabel, nameLabel, telLabel, addressLabel;
    JButton confirmBtn, cancelBtn;
    private String roomNumber;
    private String roomType;
    private String roomPrice;
    private String roomStatus;
    
    private String firstName;
    private String lastName;
    private String tel;
    private String address;

    public ckeckOutInfo(CardLayout cardLayout, Container container, String roomNumber, String roomType, String roomPrice, String roomStatus) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomStatus = roomStatus;

        roomNumberLabel = new JLabel("Room No. : " + roomNumber);
        add(roomNumberLabel);

        roomTypeLabel = new JLabel("Type : " + roomType);
        add(roomTypeLabel);

        roomPriceLabel = new JLabel("Price : " + roomPrice);
        add(roomPriceLabel);

        read(roomNumber);

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
                    update(roomNumber, roomType, roomPrice, roomStatus);
                    return;
                }
            }
        });
        add(confirmBtn);

        setSize(600, 600);
        setVisible(true);
    }

    public void update(String roomNumber, String roomType, String roomPrice, String roomStatus) {
        
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
                    writer.println(data[0].trim() + " " + data[1].trim() + " " + data[2].trim() + " " + data[3].trim() + " " + data[4].trim() + " " + data[5].trim() + " " + data[6].trim() + " " + dateFormat.format(date));
                }
            }
            
            
        }
        catch (IOException e) {
            System.out.println("Error while writing file " + e.getMessage());
        }
    }

    public void read(String roomNumber) {
        File rentFile = new File("checkin.txt");
        try (Scanner scanner = new Scanner(rentFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.toLowerCase().contains(roomNumber.toLowerCase())) { 
                    String [] data;
                    data = line.split(" ");

                    if (data.length == 9) {
                        nameLabel = new JLabel("Name : " + data[3].trim() + " " + data[4].trim());
                        add(nameLabel);

                        telLabel = new JLabel("Tel : " + data[5].trim());
                        add(telLabel);

                        addressLabel = new JLabel("Address : " + data[6].trim());
                        add(addressLabel);
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