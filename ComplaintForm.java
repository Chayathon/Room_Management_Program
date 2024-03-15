import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ComplaintForm extends JFrame {
    // private JTextField nameField;
    private JTextArea complaintArea;

    public ComplaintForm() {
        setTitle("Complaint Form");
        setSize(400, 300);
 
        JPanel panel = new JPanel();
        panel.setLayout(null); 
        
        JLabel complaintLabel = new JLabel("Complaint:");
        complaintLabel.setBounds(20, 20, 80, 25);
        panel.add(complaintLabel);

        complaintArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(complaintArea);
        scrollPane.setBounds(100, 25, 250, 180);
        panel.add(scrollPane);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 210, 100, 25);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitComplaint();
                disposeForm(); // เมื่อกด submit ให้ปิดหน้าลง
            }
        });
        panel.add(submitButton);

        add(panel);
        setVisible(true);
    }

    private void submitComplaint() {
        // String name = nameField.getText();
        String complaint = complaintArea.getText();

        // Save complaint details to a text file
        saveToFile(complaint);
    }

    private void saveToFile(String complaint) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("complaints.txt", true))) {
            writer.write(complaint);
            writer.newLine();  // Add an extra line for better readability between entries

            JOptionPane.showMessageDialog(this, "Complaint details saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving complaint details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void disposeForm() {
        this.dispose(); // ปิดหน้าต่าง
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {
    //         public void run() {
    //             new ComplaintForm();
    //         }
    //     });
    // }
}
