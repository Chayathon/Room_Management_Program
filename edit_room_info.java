import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class edit_room_info extends JPanel implements ActionListener {
    JLabel roomNumberLabel, roomTypeLabel, roomPriceLabel, roomStatusLabel;
    JTextField roomNumberField, roomTypeField, roomPriceField, roomStatusField;
    JTextArea addressField;
    JButton confirmBtn, cancelBtn;
    private String roomNumber;
    private String roomType;
    private String roomPrice;
    private String roomStatus;

    public edit_room_info(CardLayout cardLayout, Container container, String roomNumber, String roomType, String roomPrice, String roomStatus) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomStatus = roomStatus;

        roomNumberLabel = new JLabel("Room No. : ");
        add(roomNumberLabel);
        roomNumberField = new JTextField(14);
        add(roomNumberField);

        roomTypeLabel = new JLabel("Type : ");
        add(roomTypeLabel);
        roomTypeField = new JTextField(14);
        add(roomTypeField);

        roomPriceLabel = new JLabel("Price : ");
        add(roomPriceLabel);
        roomPriceField = new JTextField(14);
        add(roomPriceField);

        roomStatusLabel = new JLabel("Status : ");
        add(roomStatusLabel);
        roomStatusField = new JTextField(14);
        add(roomStatusField);

        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.first(container);
            }
        });
        add(cancelBtn);

        confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(this);
        add(confirmBtn);

        setSize(600, 600);
        setVisible(true);
    }

    public void editRoom(String oldRoomNumber, String oldRoomType, String oldRoomPrice, String oldRoomStatus, String newRoomNumber, String newRoomType, String newRoomPrice, String newRoomStatus) {
        try {
            String fileEdit = "room.txt";
            String targetText = oldRoomNumber + " " + oldRoomType + " " + oldRoomPrice + " " + oldRoomStatus;

            // อ่านไฟล์ทีละบรรทัด
            BufferedReader reader = new BufferedReader(new FileReader(fileEdit));
            String line;

            while ((line = reader.readLine()) != null) {
                // ตรวจสอบว่าบรรทัดตรงกับข้อความที่ต้องการหรือไม่
                
                String[] data = line.split(" ");
                String roomNumber = data[0];
                String roomType = data[1];
                String roomPrice = data[2];
                String roomStatus = data[3];

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

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == confirmBtn) {
            editRoom(roomNumber, roomType, roomPrice, roomStatus, roomNumberField.getText(), roomTypeField.getText(), roomPriceField.getText(), roomStatusField.getText());
        }
    }
}