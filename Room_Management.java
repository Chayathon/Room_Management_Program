import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Room_Management extends JFrame implements ActionListener {
    JPanel headerPanel, menuPanel;
    Container container;
    JButton rentRoomBtn, testBtn;
    rent_room rentRoom;
    CardLayout cardLayout;
    // room_receipt roomReceipt;

    public Room_Management() {
        super("Room Management Program");
        container = getContentPane();
        
        cardLayout = new CardLayout(10, 10);
        container.setLayout(new FlowLayout());
        container.setLayout(cardLayout);

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Room Management Program");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 2, 10, 10));
        headerPanel.add(menuPanel);

        container.add(new rent_room(), "Rent Rooms");
        rentRoomBtn = new JButton("เช่าห้อง");
        rentRoomBtn.addActionListener(this);
        menuPanel.add(rentRoomBtn);

        testBtn = new JButton("test");
        testBtn.addActionListener(this);
        menuPanel.add(testBtn);

        testBtn = new JButton("test");
        testBtn.addActionListener(this);
        menuPanel.add(testBtn);

        testBtn = new JButton("test");
        testBtn.addActionListener(this);
        menuPanel.add(testBtn);

        // JButton test = new JButton("เช่าห้อง");
        // panel.add(test);

        // container.add(panel);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        Room_Management RM = new Room_Management();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == rentRoomBtn) {
            cardLayout.show(container, "Rent Rooms");
        }
    }
}