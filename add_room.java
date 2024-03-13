import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class add_room extends JFrame implements ActionListener {
    JLabel roomNumberLabel, roomTypeLabel;
    JTextField roomNumberField, roomTypeField;
    JButton confirmBtn, cancelBtn;
    Container container;
    JPanel headerPanel, menuPanel;

    public add_room() {
        super("Add Room");
        container = getContentPane();

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Add Room");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 2, 10, 10));
        headerPanel.add(menuPanel);

        roomNumberLabel = new JLabel("Room Number : ");
        menuPanel.add(roomNumberLabel);
        roomNumberField = new JTextField(14);
        menuPanel.add(roomNumberField);

        roomTypeLabel = new JLabel("Room Type : ");
        menuPanel.add(roomTypeLabel);
        roomTypeField = new JTextField(14);
        menuPanel.add(roomTypeField);

        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(this);
        menuPanel.add(cancelBtn);

        confirmBtn = new JButton("Add");
        confirmBtn.addActionListener(this);
        menuPanel.add(confirmBtn);

        setSize(600, 600);
        setVisible(true);
    }

    public void saveComplteTicket(String roomNumber, String roomType) {
        File complteTicketFile = new File("room.txt");

        if (complteTicketFile.exists()) {
            int choice = JOptionPane.showConfirmDialog(null,
                    "File 'room.txt' already exists. Append data?", "Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (choice != JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "CompleteTicket information not saved.");
                return;
            }
        }

        try {
            saveComplteTicketToFile(roomNumber, roomType, complteTicketFile);
            JOptionPane.showMessageDialog(null, "CompleteTicket information saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving CompleteTicket information: " + e.getMessage());
        }
    }

        private static void saveComplteTicketToFile(String roomNumber, String roomType, File equipmentFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(equipmentFile, true))) {
            writer.println(roomNumber + " " + roomType + " " + "0");
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == cancelBtn) {
            dispose();
        }
        else if(event.getSource() == confirmBtn) {
            saveComplteTicket(roomNumberField.getText(), roomTypeField.getText());
        }
    }
    
}
