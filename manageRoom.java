import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class manageRoom extends JFrame implements ActionListener {
    Container container; // ประกาศคอนเทนเนอร์สำหรับเก็บองค์ประกอบ
    JPanel headerPanel, menuPanel; // ประกาศพาเนลสำหรับส่วนหัวและเมนู
    JButton addRoomBtn, editRoomBtn, deleteRoomBtn;
    addRoom addRoom;
    editRoom editRoom;
    deleteRoom deleteRoom;

    public manageRoom() {
        super("Manage Rooms"); // กำหนดชื่อหัวของหน้าต่างเป็น "Finance"
        container = getContentPane(); // รับคอนเทนเนอร์ของหน้าต่าง

        // สร้างพาเนลสำหรับส่วนหัวและเพิ่มลงในคอนเทนเนอร์
        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        // สร้างป้ายชื่อ "Finance" และเพิ่มลงในส่วนหัว และกำหนดรูปแบบและขนาดตามที่ต้องการ
        JLabel title = new JLabel("Manage Rooms");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        // สร้างพาเนลสำหรับเมนูและเพิ่มลงในส่วนหัว
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.add(menuPanel);
        
        // สร้างปุ่ม "Add Room" และเพิ่มลงในเมนู
        addRoomBtn = new JButton("Add Room");
        addRoomBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        addRoomBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addRoomBtn.addActionListener(this); // กำหนดให้ปุ่มตอบสนองการคลิก
        menuPanel.add(addRoomBtn);
        
        // สร้างปุ่ม "Edit Room" และเพิ่มลงในเมนู
        editRoomBtn = new JButton("Edit Room");
        editRoomBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        editRoomBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editRoomBtn.addActionListener(this); // กำหนดให้ปุ่มตอบสนองการคลิก
        menuPanel.add(editRoomBtn);
        
        // สร้างปุ่ม "Delete Room" และเพิ่มลงในเมนู
        deleteRoomBtn = new JButton("Delete Room");
        deleteRoomBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        deleteRoomBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteRoomBtn.addActionListener(this); // กำหนดให้ปุ่มตอบสนองการคลิก
        menuPanel.add(deleteRoomBtn);

        // กำหนดขนาดของหน้าต่าง
        setSize(600, 600);
        setVisible(true); // แสดงหน้าต่าง
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == addRoomBtn) {
            addRoom = new addRoom();
        }
        else if(event.getSource() == editRoomBtn) {
            editRoom = new editRoom();
        }
        else if(event.getSource() == deleteRoomBtn){
            deleteRoom = new deleteRoom();
        }
    }
}