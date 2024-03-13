import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class rent_room extends JFrame {
    JPanel headerPanel, menuPanel;
    Container container;
    JButton roomBtn;
    CardLayout cardLayout;

    public rent_room() {
        super("Room Management Program");
        container = getContentPane();
        
        cardLayout = new CardLayout(10, 10);
        container.setLayout(new FlowLayout());
        container.setLayout(cardLayout);

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 10, 10, 10));
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
                String roomStatus = data[2];
                
                roomBtn = new JButton(roomNumber);
                // ประมวลผลข้อมูลต่อได้
                container.add(new rent_room_info(cardLayout, container, roomNumber, roomType), roomNumber);
                roomBtn = new JButton(roomNumber + " " + roomType + " " + roomStatus);
                roomBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        cardLayout.show(container, roomNumber);
                    }
                });
    
                if(roomStatus.equals("0")) {
                    menuPanel.add(roomBtn);
                }
                else {
                    roomBtn.setEnabled(false);
                    menuPanel.add(roomBtn);
                }
            }
    
            reader.close();
        }
        catch(IOException e) {
            System.out.println("Error while writing file " + e.getMessage());
        }
        
        setSize(1700, 400);
        setVisible(true);
    }
}