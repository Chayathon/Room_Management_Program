import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Room_Management extends JFrame {
    CardLayout cardLayout;
    JPanel cardPanel;
    Container container;
    // rent_room rentRoom;
    // room_receipt roomReceipt;

    public Room_Management() {
        super("Room Management Program");
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        container = getContentPane();
        container.setLayout(new GridLayout(1, 2, 20, 20));

        // JLabel title = new JLabel("Room Management Program");
        // title.setHorizontalAlignment(JLabel.CENTER);
        // title.setFont(new Font("Tahoma", Font.BOLD, 28));
        // container.add(title, BorderLayout.NORTH);

        cardPanel.add(new rent_room(), "Page 2");

        container.add(cardPanel);

        JButton rentRoomBtn = new JButton("เช่าห้อง");
        rentRoomBtn.addActionListener(e -> cardLayout.next(cardPanel));
        container.add(rentRoomBtn);

        JButton test = new JButton("เช่าห้อง");
        container.add(test);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        // Room_Management RM = new Room_Management();
        SwingUtilities.invokeLater(() -> {
            Room_Management RM = new Room_Management();
            RM.setVisible(true);
        });
    }
    
    // @Override
    // public void actionPerformed(ActionEvent event) {
    //     if(event.getSource() == rentRoomBtn) {
    //         // this.rentRoom = new rent_room();
    //         cardLayout.next(cardPanel);
    //     }
    // }
}