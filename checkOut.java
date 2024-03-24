import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class checkOut extends JFrame {
    JPanel headerPanel, menuPanel;
    Container container;
    JButton roomBtn;
    CardLayout cardLayout;

    public checkOut() {
        super("Return Room");
        container = getContentPane();
        
        cardLayout = new CardLayout(10, 10);
        container.setLayout(new FlowLayout());
        container.setLayout(cardLayout);

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Check Out");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 10, 10, 10));
        headerPanel.add(menuPanel);

        readFile();
        
        setSize(1700, 400);
        setVisible(true);
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
                    roomBtn = new JButton(roomNumber + " " + roomType);
                    roomBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    roomBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            checkOutInfo checkOutInfo = new checkOutInfo(roomNumber, roomType, roomPrice, roomStatus);
                            dispose();
                        }
                    });
                }
    
                if(roomStatus.equals("Taken")) {
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
    }
}