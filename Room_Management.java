import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Room_Management extends JFrame implements ActionListener {
    JPanel headerPanel, menuPanel;
    Container container;
    JButton rentRoomBtn, returnRoomBtn, addRoomBtn, editRoomBtn, deleteRoomBtn, ComplaintBtn, ReportBtn, FinanceBtn, checkinHistoryBtn, checkoutHistoryBtn;
    checkIn checkIn;
    checkOut checkOut;
    addRoom addRoom;
    editRoom editRoom;
    deleteRoom deleteRoom;
    Complaint Complaint; //ร้องเรียน
    Finance Finance; // การเงิน
    checkinHistory checkinHistory; // ประวัติการเช่า
    checkoutHistory checkoutHistory; // ประวัติการคืน

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

        checkinHistoryBtn = new JButton("Checkin History");
        checkinHistoryBtn.addActionListener(this);
        menuPanel.add(checkinHistoryBtn);

        checkoutHistoryBtn = new JButton("Checkout History");
        checkoutHistoryBtn.addActionListener(this);
        menuPanel.add(checkoutHistoryBtn);


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
            checkIn = new checkIn();
        }
        else if(event.getSource() == returnRoomBtn) {
            checkOut = new checkOut();
        }
        else if(event.getSource() == addRoomBtn) {
            addRoom = new addRoom();
        }
        else if(event.getSource() == editRoomBtn) {
            editRoom = new editRoom();
        }
        else if(event.getSource() == deleteRoomBtn) {
            deleteRoom = new deleteRoom();
        }
        else if(event.getSource() == ComplaintBtn) {
            Complaint = new Complaint();
        }
        else if(event.getSource() == FinanceBtn){
            Finance = new Finance();
        }
        else if(event.getSource() == checkinHistoryBtn){
            checkinHistory = new checkinHistory();
        }
        else if(event.getSource() == checkoutHistoryBtn){
            checkoutHistory = new checkoutHistory();
        }
    }
}