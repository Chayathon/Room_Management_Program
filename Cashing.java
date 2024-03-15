import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Cashing extends JFrame {
    JPanel headerPanel, menuPanel;
    Container container;
    JButton roomBtn;
    CardLayout cardLayout;

    private RoomSummary currentRoomSummary;

    public Cashing() {
        super("Cashing Payment");
        container = getContentPane();

        cardLayout = new CardLayout(10, 10);
        container.setLayout(cardLayout);

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Cashing Payment");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS)); // Set vertical layout
        JScrollPane scrollPane = new JScrollPane(menuPanel); // Add scrolling functionality
        headerPanel.add(scrollPane, BorderLayout.CENTER);

        try {
            String fileName = "payment_history.txt";
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            JPanel rowPanel = new JPanel(); // Initialize a new panel for each row
            rowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Set flow layout for horizontal arrangement
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String roomNumber = data[0];
                String paymentStatus = data[1];
            
                if (paymentStatus.equals("0")) {
                    roomBtn = new JButton(roomNumber);
            
                    roomBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            double totalCost = Double.parseDouble(data[3]);
                            showRoomDetailsDialog(roomNumber, data[2], totalCost);
                        }
                    });
                    
            
                    rowPanel.add(roomBtn);
                    if (rowPanel.getComponentCount() == 10) { // When 10 buttons are added, add the rowPanel to menuPanel
                        menuPanel.add(rowPanel);
                        menuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add vertical space between rows
                        rowPanel = new JPanel(); // Reset the rowPanel for the next row
                        rowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Set layout for the next row
                    }
                }
            }
            
            if (rowPanel.getComponentCount() > 0) { // Add the last row if there are remaining buttons
                menuPanel.add(rowPanel);
            }

            reader.close();
        } catch(IOException e) {
            System.out.println("Error while reading file " + e.getMessage());
        }

        setSize(800, 800);
        setLocationRelativeTo(null); // Set window location to center of screen
        setVisible(true);
    }

    private double calculateTotalCost(String electricityCost) {
        // Implement your total cost calculation logic here
        double roomPriceDouble = 0.0; // Get room price from room.txt or another source
        return roomPriceDouble + (Double.parseDouble(electricityCost) * 8) + 100;
    }

    private void showRoomSummaryDialog(String roomNumber, String electricityCost, double totalCost, double change) {
        JDialog dialog = new JDialog(this, "Room Summary", true);
        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Room Number:"));
        panel.add(new JLabel(roomNumber));

        panel.add(new JLabel("Electricity Cost:"));
        panel.add(new JLabel(electricityCost));

        panel.add(new JLabel("Total Cost:"));
        panel.add(new JLabel(String.valueOf(totalCost)));

        panel.add(new JLabel("Change:"));
        panel.add(new JLabel(String.valueOf(change)));

        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void savePaymentData(String roomNumber, String electricityCost, double totalCost) {
        // Get current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("payment_history.txt", true))) {
            writer.write(roomNumber + " 1 " + electricityCost + " " + totalCost + " " + currentDate);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(container, "Error saving payment details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showRoomDetailsDialog(String roomNumber, String electricityCost, double totalCost) {
        JDialog dialog = new JDialog(this, "Room Details and Payment", true);
        JPanel panel = new JPanel(new GridLayout(5, 2));
    
        panel.add(new JLabel("Room Number:"));
        panel.add(new JLabel(roomNumber));
    
        panel.add(new JLabel("Electricity Cost:"));
        panel.add(new JLabel(electricityCost));
    
        panel.add(new JLabel("Total Cost:"));
        panel.add(new JLabel(String.valueOf(totalCost)));
    
        JTextField paymentField = new JTextField(10);
        panel.add(new JLabel("Enter Payment:"));
        panel.add(paymentField);
    
        JButton confirmButton = new JButton("Confirm Payment");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String paymentAmountStr = paymentField.getText();
                if (!paymentAmountStr.isEmpty()) {
                    double paymentAmount = Double.parseDouble(paymentAmountStr);
                    double change = paymentAmount - totalCost;
                    if (change >= 0) {
                        // Update payment status in payment_history.txt
                        updatePaymentStatus(roomNumber);
                        // Show change dialog
                        showChangeDialog(change);
                        dialog.dispose(); // Close the dialog
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Insufficient payment amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please enter payment amount.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(confirmButton);
    
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void updatePaymentStatus(String roomNumber) {
        try {
            String fileName = "payment_history.txt";
            File file = new File(fileName);
            File tempFile = new File("temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String currentRoomNumber = data[0];
                String paymentStatus = data[1];
    
                if (currentRoomNumber.equals(roomNumber) && paymentStatus.equals("0")) {
                    writer.write(currentRoomNumber + " 1 " + data[2] + " " + data[3] + " " + data[4]);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
    
            reader.close();
            writer.close();
            
            // Replace the original file with the temporary file
            if (!file.delete()) {
                System.out.println("Error deleting file");
                return;
            }
            if (!tempFile.renameTo(file)) {
                System.out.println("Error renaming file");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(container, "Error updating payment status. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showChangeDialog(double change) {
        JOptionPane.showMessageDialog(container, "Change: " + change, "Change", JOptionPane.INFORMATION_MESSAGE);
    }
    

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Cashing();
            }
        });
    }
}
