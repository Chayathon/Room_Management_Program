import javax.swing.*;
import java.awt.event.*;

public class Index_Page {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Index Page");
        JButton button = new JButton("Open Room Management");
        JButton button2 = new JButton("Open Complaint");


        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // เมื่อปุ่มถูกคลิก
                Room_Management RM = new Room_Management();
                // ทำสิ่งที่คุณต้องการเมื่อปุ่มถูกคลิก เช่น เปิดหน้าต่างใหม่
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // เมื่อปุ่มถูกคลิก
                Complaint Complaint = new Complaint();
                // ทำสิ่งที่คุณต้องการเมื่อปุ่มถูกคลิก เช่น เปิดหน้าต่างใหม่
            }
        });

        JPanel panel = new JPanel();
        panel.add(button);
        panel.add(button2);
        frame.add(panel);
        
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
