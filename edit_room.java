import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class edit_room extends JFrame {
    JPanel headerPanel, menuPanel;
    Container container;
    CardLayout cardLayout;
    JButton roomBtn;

    public edit_room() {
        super("Edit Room");
        container = getContentPane();

        cardLayout = new CardLayout(10, 10);
        container.setLayout(new FlowLayout());
        container.setLayout(cardLayout);

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Edit Room");
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
                container.add(new edit_room_info(cardLayout, container, roomNumber, roomType, roomPrice,  roomStatus), roomNumber);
                roomBtn = new JButton(roomNumber + " " + roomType + " " + roomPrice);
                roomBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        cardLayout.show(container, roomNumber);
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

    
}