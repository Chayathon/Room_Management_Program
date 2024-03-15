import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ComplaintHistory {

    public ComplaintHistory() {
        JFrame frame = new JFrame("Complaint History");
        frame.setLayout(new BorderLayout()); // เปลี่ยน Layout ของ JFrame เป็น BorderLayout

        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea); // เพิ่มสกอบาร์ให้เลื่อนลงได้
        
        // เพิ่ม JLabel เพื่อแสดงว่าหน้านี้เป็นประวัติ
        JLabel titleLabel = new JLabel("Complaint History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // เพิ่มขนาดตัวอักษร
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // เมื่อปุ่มถูกคลิกให้ปิดหน้าต่าง
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // เปลี่ยน Layout ของ JPanel เป็น BorderLayout
        panel.add(titleLabel, BorderLayout.NORTH); // ตำแหน่งของ titleLabel เป็นทางบน
        panel.add(scrollPane, BorderLayout.CENTER); // ตำแหน่งของ scrollPane เป็นกลาง
        panel.add(closeButton, BorderLayout.SOUTH); // ตำแหน่งของ closeButton เป็นทางล่าง
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // เพิ่มระยะขอบของ JPanel

        frame.add(panel, BorderLayout.CENTER); // เพิ่ม JPanel เข้าไปใน JFrame ที่ตำแหน่งกลาง
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ให้ปิดเฉพาะหน้าต่างนี้
        frame.setVisible(true);

        // อ่านข้อมูลจากไฟล์ .txt
        try {
            BufferedReader reader = new BufferedReader(new FileReader("complaints.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {
    //         public void run() {
    //             new ComplaintHistory();
    //         }
    //     });
    // }
}
