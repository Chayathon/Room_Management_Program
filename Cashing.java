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
        container.setLayout(new FlowLayout());
        container.setLayout(cardLayout);

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Cashing Payment");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 10, 10, 10));
        headerPanel.add(menuPanel);

        try {
            String fileName = "payment_history.txt";
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String roomNumber = data[0];
                String paymentStatus = data[1];

                if (paymentStatus.equals("0")) {
                    roomBtn = new JButton(roomNumber);

                    roomBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            String electricityCost = JOptionPane.showInputDialog("Enter payment :");
                            double totalCost = calculateTotalCost(electricityCost);
                            savePaymentData(roomNumber, electricityCost, totalCost);

                            // Show room summary window
                            currentRoomSummary = new RoomSummary(roomNumber, "Unknown", "Unknown", electricityCost, totalCost);

                            dispose(); // Close the current window
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    new Cashing(); // Open a new Cashing window
                                }
                            });
                        }
                    });

                    menuPanel.add(roomBtn);
                }
            }

            reader.close();
        } catch(IOException e) {
            System.out.println("Error while reading file " + e.getMessage());
        }

        setSize(1700, 400);
        setVisible(true);
    }

    private double calculateTotalCost(String electricityCost) {
        // Implement your total cost calculation logic here
        double roomPriceDouble = 0.0; // Get room price from room.txt or another source
        return roomPriceDouble + (Double.parseDouble(electricityCost) * 8) + 100;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Cashing();
            }
        });
    }
}
