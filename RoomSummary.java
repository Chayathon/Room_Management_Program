import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomSummary extends JFrame {
    JLabel roomNumberLabel, roomTypeLabel, roomPriceLabel, electricityCostLabel, waterCostLabel, totalCostLabel, dateLabel;

    public RoomSummary(String roomNumber, String roomType, String roomPrice, String electricityCost, double totalCost) {
        super("Room Summary");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel detailsPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        roomNumberLabel = new JLabel("Room Number:");
        roomTypeLabel = new JLabel("Room Type:");
        roomPriceLabel = new JLabel("Room Price:");
        electricityCostLabel = new JLabel("Electricity Cost:");
        waterCostLabel = new JLabel("Water Cost:");
        totalCostLabel = new JLabel("Total Cost:");
        dateLabel = new JLabel("Date:");

        detailsPanel.add(roomNumberLabel);
        detailsPanel.add(new JLabel(roomNumber));
        detailsPanel.add(roomTypeLabel);
        detailsPanel.add(new JLabel(roomType));
        detailsPanel.add(roomPriceLabel);
        detailsPanel.add(new JLabel(roomPrice));
        detailsPanel.add(electricityCostLabel);
        detailsPanel.add(new JLabel(electricityCost));
        detailsPanel.add(waterCostLabel);
        detailsPanel.add(new JLabel("100"));
        detailsPanel.add(totalCostLabel);
        detailsPanel.add(new JLabel(String.valueOf(totalCost)));
        detailsPanel.add(dateLabel);
        detailsPanel.add(new JLabel(getCurrentDate()));

        panel.add(detailsPanel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        

        setSize(350, 300);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the window when closed
        setVisible(true);


        setLocation(450, 450);
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }
}
