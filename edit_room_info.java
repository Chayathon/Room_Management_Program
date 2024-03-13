import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class edit_room_info extends JPanel implements ActionListener {
    JLabel roomNumberLabel, roomTypeLabel, roomStatusLabel;
    JTextField roomNumberField, roomTypeField, roomStatusField;
    JTextArea addressField;
    JButton confirmBtn, cancelBtn;
    private String roomNumber;
    private String roomType;
    private String roomStatus;

    public edit_room_info(CardLayout cardLayout, Container container, String roomNumber, String roomType, String roomStatus) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomStatus = roomStatus;

        roomNumberLabel = new JLabel("Room No. : ");
        add(roomNumberLabel);
        roomNumberField = new JTextField(14);
        add(roomNumberField);

        roomTypeLabel = new JLabel("Type : ");
        add(roomTypeLabel);
        roomTypeField = new JTextField(14);
        add(roomTypeField);

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

    public void editRoom(String oldRoomNumber, String oldRoomType, String oldRoomStatus, String newRoomNumber, String newRoomType, String newRoomStatus) {
        try {
            String fileEdit = "room.txt";
            String targetText = oldRoomNumber + " " + oldRoomType + " " + oldRoomStatus;

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
                    // String name = value[0];
                    // String type = value[1];
                    // String status = "1";
                    String newData = newRoomNumber + " " + newRoomType + " " + newRoomStatus;

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
            editRoom(roomNumber, roomType, roomStatus, roomNumberField.getText(), roomTypeField.getText(), roomStatusField.getText());
        }
    }
}