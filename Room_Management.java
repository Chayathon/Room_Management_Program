import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Room_Management extends JFrame implements ActionListener {
    JPanel panel;
    Container container;
    JButton rentRoomBtn;
    rent_room rentRoom;
    // room_receipt roomReceipt;

    public Room_Management() {
        super("Room Management Program");
        container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JLabel title = new JLabel("Room Management Program");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        container.add(title, BorderLayout.NORTH);

        panel = new JPanel();
        rentRoomBtn = new JButton("เช่าห้อง");
        rentRoomBtn.addActionListener(this);
        panel.add(rentRoomBtn);

        JButton test = new JButton("เช่าห้อง");
        panel.add(test);

        container.add(panel);

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
            this.rentRoom = new rent_room();
        }
    }
}