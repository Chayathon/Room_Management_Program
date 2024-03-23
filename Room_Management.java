import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Room_Management extends JFrame implements ActionListener {
    Container container;
    JPanel headerPanel, menuPanel;
    JButton checkInBtn, checkOutBtn, manageRoomBtn, addRoomBtn, editRoomBtn, deleteRoomBtn, ComplaintBtn, ReportBtn, FinanceBtn, checkincheckoutHistoryBtn;
    checkIn checkIn;
    checkOut checkOut;
    manageRoom manageRoom;
    addRoom addRoom;
    editRoom editRoom;
    deleteRoom deleteRoom;
    Complaint Complaint; //ร้องเรียน
    Finance Finance; // การเงิน
    checkincheckoutHistory checkincheckoutHistory; // ประวัติการเช่า การคืน

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
        menuPanel.setLayout(new GridLayout(3, 2, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.add(menuPanel);

        checkInBtn = new JButton("Check In");
        checkInBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        checkInBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkInBtn.addActionListener(this);
        menuPanel.add(checkInBtn);

        checkOutBtn = new JButton("Check Out");
        checkOutBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        checkOutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkOutBtn.addActionListener(this);
        menuPanel.add(checkOutBtn);

        FinanceBtn = new JButton("Finance");
        FinanceBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        FinanceBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        FinanceBtn.addActionListener(this);
        menuPanel.add(FinanceBtn);

        checkincheckoutHistoryBtn = new JButton("Rental History");
        checkincheckoutHistoryBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        checkincheckoutHistoryBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkincheckoutHistoryBtn.addActionListener(this);
        menuPanel.add(checkincheckoutHistoryBtn);

        manageRoomBtn = new JButton("Manage Rooms");
        manageRoomBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        manageRoomBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        manageRoomBtn.addActionListener(this);
        menuPanel.add(manageRoomBtn);

        ComplaintBtn = new JButton("Complaint");
        ComplaintBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        ComplaintBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
        if(event.getSource() == checkInBtn) {
            checkIn = new checkIn();
        }
        else if(event.getSource() == checkOutBtn) {
            checkOut = new checkOut();
        }
        else if(event.getSource() == FinanceBtn){
            Finance = new Finance();
        }
        else if(event.getSource() == checkincheckoutHistoryBtn){
            checkincheckoutHistory = new checkincheckoutHistory();
        }
        else if(event.getSource() == manageRoomBtn) {
            manageRoom = new manageRoom();
        }
        else if(event.getSource() == ComplaintBtn) {
            Complaint = new Complaint();
        }
    }
}