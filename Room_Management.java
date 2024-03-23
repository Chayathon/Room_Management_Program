import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Room_Management extends JFrame implements ActionListener {
    Container container;
    JPanel headerPanel, menuPanel;
    JButton checkInBtn, checkOutBtn, addRoomBtn, editRoomBtn, deleteRoomBtn, ComplaintBtn, ReportBtn, FinanceBtn, checkincheckoutHistoryBtn;
    checkIn checkIn;
    checkOut checkOut;
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
        menuPanel.setLayout(new GridLayout(3, 10, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.add(menuPanel);

        checkInBtn = new JButton("Check In");
        checkInBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        checkInBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkInBtn.addActionListener(this);
        menuPanel.add(checkInBtn);

        checkOutBtn = new JButton("Check Out");
        checkOutBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        checkOutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkOutBtn.addActionListener(this);
        menuPanel.add(checkOutBtn);

        FinanceBtn = new JButton("Finance");
        FinanceBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        FinanceBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        FinanceBtn.addActionListener(this);
        menuPanel.add(FinanceBtn);

        addRoomBtn = new JButton("Add Room");
        addRoomBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        addRoomBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addRoomBtn.addActionListener(this);
        menuPanel.add(addRoomBtn);

        editRoomBtn = new JButton("Edit Room");
        editRoomBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        editRoomBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editRoomBtn.addActionListener(this);
        menuPanel.add(editRoomBtn);

        deleteRoomBtn = new JButton("Delete Room");
        deleteRoomBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        deleteRoomBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteRoomBtn.addActionListener(this);
        menuPanel.add(deleteRoomBtn);

        checkincheckoutHistoryBtn = new JButton("Rental History");
        checkincheckoutHistoryBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        checkincheckoutHistoryBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkincheckoutHistoryBtn.addActionListener(this);
        menuPanel.add(checkincheckoutHistoryBtn);

        ComplaintBtn = new JButton("Complaint");
        ComplaintBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
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
        else if(event.getSource() == addRoomBtn) {
            addRoom = new addRoom();
        }
        else if(event.getSource() == editRoomBtn) {
            editRoom = new editRoom();
        }
        else if(event.getSource() == deleteRoomBtn) {
            deleteRoom = new deleteRoom();
        }
        else if(event.getSource() == checkincheckoutHistoryBtn){
            checkincheckoutHistory = new checkincheckoutHistory();
        }
        else if(event.getSource() == ComplaintBtn) {
            Complaint = new Complaint();
        }
    }
}