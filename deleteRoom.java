import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class deleteRoom extends JFrame {
    JPanel headerPanel, menuPanel;
    Container container;
    JButton roomBtn;

    public deleteRoom() {
        super("Delete Room");
        container = getContentPane();

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Delete Room");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 2, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        headerPanel.add(menuPanel);

        readFile();
        
        setSize(1700, 400);
        setVisible(true);
    }

    public void writeToFile(String roomNumber, String roomType, String roomPrice, String roomStatus, String roomActive) {
        try {
            String fileName = "room.txt";
            String targetText = roomNumber + " " + roomType + " " + roomPrice + " " + roomStatus + " " + roomActive;

            // อ่านไฟล์ทีละบรรทัด
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                // ตรวจสอบว่าบรรทัดตรงกับข้อความที่ต้องการหรือไม่
                if (line.contains(targetText)) {
                    System.out.println("Found : " + targetText);

                    Scanner scanner = new Scanner(new File(fileName));
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

                    if(index != -1) {
                        lines.set(index, roomNumber + " " + roomType + " " + roomPrice + " " + roomStatus + " " + "0");
                    }

                    PrintWriter edit = new PrintWriter(new File(fileName));
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

    public void readFile() {
        try {
            String fileName = "room.txt";
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String roomNumber = data[0];
                String roomType = data[1];
                String roomPrice = data[2];
                String roomStatus = data[3];
                String roomActive = data[4];
                
                if(roomActive.equals("1")) {
                    roomBtn = new JButton(roomNumber);
                    roomBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    roomBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            int choice = JOptionPane.showConfirmDialog(null, "Room No. :       " + roomNumber + "\n" +
                                                                                            "Room Type :    " + roomType + "\n" +
                                                                                            "Room Price :   " + roomPrice + "\n" + 
                                                                                            "\nAre you sure to Delete?", "Confirmation", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                writeToFile(roomNumber, roomType, roomPrice, roomStatus, roomActive);
                                dispose();
                                JOptionPane.showMessageDialog(null, "Room Deleted.", "Successfully", 1);
                                return;
                            }
                        }
                    });
                }
                menuPanel.add(roomBtn);
            }
    
            reader.close();
        }
        catch(IOException e) {
            System.out.println("Error while writing file " + e.getMessage());
        }
    }
}