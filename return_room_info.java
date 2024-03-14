import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class return_room_info extends JPanel {
    JLabel roomNumberLabel, roomTypeLabel, roomPriceLabel, roomStatusLabel, nameLabel, telLabel, addressLabel;
    JButton confirmBtn, cancelBtn;
    private String roomNumber;
    private String roomType;
    private String roomPrice;
    private String roomStatus;

    public return_room_info(CardLayout cardLayout, Container container, String roomNumber, String roomType, String roomPrice, String roomStatus) {
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

        try {
            String fileName = "rent.txt";
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String firstName = data[3];
                String lastName = data[4];
                String tel = data[5];
                String address = data[6];
                
                nameLabel = new JLabel("Name : " + firstName + " " + lastName);
                add(nameLabel);

                telLabel = new JLabel("Tel : " + tel);
                add(telLabel);

                addressLabel = new JLabel("Address : " + address);
                add(addressLabel);
            }
    
            reader.close();
        }
        catch(IOException e) {
            System.out.println("Error while writing file " + e.getMessage());
        }

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
    }
}