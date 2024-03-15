import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ComplaintHistory {

    public ComplaintHistory() {
        JFrame frame = new JFrame("Complaint History");

        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // เมื่อปุ่มถูกคลิกให้ปิดหน้าต่าง
            }
        });

        JPanel panel = new JPanel();
        panel.add(textArea);
        panel.add(closeButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ให้ปิดเฉพาะหน้าต่างนี้
        frame.setVisible(true);

        // อ่านข้อมูลจากไฟล์ .txt
        try {
            BufferedReader reader = new BufferedReader(new FileReader("complaint_history.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ComplaintHistory();
            }
        });
    }
}
