import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class Room_Management extends JFrame implements ActionListener {
    JPanel headerPanel, menuPanel;
    Container container;
    JButton rentRoomBtn, addRoomBtn, editRoomBtn, deleteRoomBtn;
    rent_room rent_room;
    add_room add_room;
    edit_room edit_room;
    delete_room delete_room;
    // CardLayout cardLayout;

    public Room_Management() {
        super("Room Management Program");
        container = getContentPane();
        
    //     cardLayout = new CardLayout(10, 10);
    //     container.setLayout(new FlowLayout());
    //     container.setLayout(cardLayout);

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Room Management Program");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 10, 10, 10));
        headerPanel.add(menuPanel);

        rentRoomBtn = new JButton("Rent Room");
        rentRoomBtn.addActionListener(this);
        menuPanel.add(rentRoomBtn);

        addRoomBtn = new JButton("Add Room");
        addRoomBtn.addActionListener(this);
        menuPanel.add(addRoomBtn);

        editRoomBtn = new JButton("Edit Room");
        editRoomBtn.addActionListener(this);
        menuPanel.add(editRoomBtn);

        deleteRoomBtn = new JButton("Delete Room");
        deleteRoomBtn.addActionListener(this);
        menuPanel.add(deleteRoomBtn);

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        Room_Management RM = new Room_Management();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == rentRoomBtn) {
            rent_room = new rent_room();
        }
        else if(event.getSource() == addRoomBtn) {
            add_room = new add_room();
        }
        else if(event.getSource() == editRoomBtn) {
            edit_room = new edit_room();
        }
        else if(event.getSource() == deleteRoomBtn) {
            delete_room = new delete_room();
        }
    }
}