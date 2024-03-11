import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Complaint extends JFrame {
    private JTextField nameField;
    private JTextArea complaintArea;

    public Complaint() {
        setTitle("Complaint Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        panel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(100, 20, 250, 25);
        panel.add(nameField);

        JLabel complaintLabel = new JLabel("Complaint:");
        complaintLabel.setBounds(20, 50, 80, 25);
        panel.add(complaintLabel);

        complaintArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(complaintArea);
        scrollPane.setBounds(100, 50, 250, 150);
        panel.add(scrollPane);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 220, 100, 25);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitComplaint();
            }
        });
        panel.add(submitButton);

        add(panel);
        setVisible(true);
    }

    private void submitComplaint() {
        String name = nameField.getText();
        String complaint = complaintArea.getText();

        // Save complaint details to a text file
        saveToFile(name, complaint);

        // Additional processing or actions related to the complaint submission can be added here

        System.out.println("Complaint submitted by: " + name);
        System.out.println("Complaint details: " + complaint);
    }

    private void saveToFile(String name, String complaint) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("complaints.txt", true))) {
            writer.write("Complaint submitted by: " + name);
            writer.newLine();
            writer.write("Complaint details: " + complaint);
            writer.newLine();
            writer.newLine();  // Add an extra line for better readability between entries

            System.out.println("Complaint details saved to complaints.txt");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving complaint details. Please try again.");
        }
    }

    public static void main(String[] args) {
        new Complaint();
    }
}
