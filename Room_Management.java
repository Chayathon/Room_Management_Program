import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class Room_Management extends JFrame implements ActionListener {
    JPanel headerPanel, menuPanel;
    Container container;
    JButton rentRoomBtn, returnRoomBtn, addRoomBtn, editRoomBtn, deleteRoomBtn, ComplaintBtn, ReportBtn, FinanceBtn;
    rent_room rent_room;
    return_room return_room;
    add_room add_room;
    edit_room edit_room;
    delete_room delete_room;
    Complaint Complaint; //ร้องเรียน
    Finance Finance; // การเงิน

    public Room_Management() {
        super("Room Management Program");
        container = getContentPane();

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Room Management Program");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 10, 10, 10));
        headerPanel.add(menuPanel);

        rentRoomBtn = new JButton("Check in");
        rentRoomBtn.addActionListener(this);
        menuPanel.add(rentRoomBtn);

        returnRoomBtn = new JButton("Check out");
        returnRoomBtn.addActionListener(this);
        menuPanel.add(returnRoomBtn);

        addRoomBtn = new JButton("Add Room");
        addRoomBtn.addActionListener(this);
        menuPanel.add(addRoomBtn);

        editRoomBtn = new JButton("Edit Room");
        editRoomBtn.addActionListener(this);
        menuPanel.add(editRoomBtn);

        deleteRoomBtn = new JButton("Delete Room");
        deleteRoomBtn.addActionListener(this);
        menuPanel.add(deleteRoomBtn);

        FinanceBtn = new JButton("Finance");
        FinanceBtn.addActionListener(this);
        menuPanel.add(FinanceBtn);

        ComplaintBtn = new JButton("Complaint");
        ComplaintBtn.addActionListener(this);
        menuPanel.add(ComplaintBtn);

 
        


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
        else if(event.getSource() == returnRoomBtn) {
            return_room = new return_room();
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
        else if(event.getSource() == ComplaintBtn) {
            Complaint = new Complaint();
        }
        else if(event.getSource() == FinanceBtn){
            Finance = new Finance();
        }
    }
}