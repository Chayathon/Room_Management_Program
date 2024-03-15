import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class delete_room extends JFrame {
    JPanel headerPanel, menuPanel;
    Container container;
    JButton roomBtn;

    public delete_room() {
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
        headerPanel.add(menuPanel);

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
                
                roomBtn = new JButton(roomNumber);
                // ประมวลผลข้อมูลต่อได้
                roomBtn = new JButton(roomNumber + " " + roomType + " " + roomPrice + " " + roomStatus);
                roomBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        int choice = JOptionPane.showConfirmDialog(null, "Are you sure to delete " + roomNumber + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        deleteRoom(roomNumber, roomType, roomPrice, roomStatus);
                        return;
                    }
                    }
                });
                menuPanel.add(roomBtn);
            }
    
            reader.close();
        }
        catch(IOException e) {
            System.out.println("Error while writing file " + e.getMessage());
        }
        
        setSize(1700, 400);
        setVisible(true);
    }

    public void deleteRoom(String roomNumber, String roomType, String roomPrice, String roomStatus) {
        String fileName = "room.txt";
        String targetText = roomNumber + " " + roomType + " " + roomPrice + " " + roomStatus;

        try {
            // Read the content of the file
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            
            // Replace the text you want to remove with nothing
            content = content.replace(targetText, "");
            
            // Write the new content back to the file
            Files.write(Paths.get(fileName), content.getBytes());
            
            System.out.println("Room delete successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}