import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomSummary extends JFrame {
    JLabel roomNumberLabel, roomTypeLabel, roomPriceLabel, electricityCostLabel, waterCostLabel, totalCostLabel,
            dateLabel, waterUnitLabel, electricityUnitLabel;

    public RoomSummary(String roomNumber, String roomType, String roomPrice, String electricityUnit, String waterUnit, double totalCost) {
        super("Room Summary");

        // สร้างหน้าต่าง JFrame ของคลาส RoomSummary
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // สร้างพาเนลสำหรับแสดงรายละเอียดห้อง
        JPanel detailsPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // สร้างป้ายชื่อและข้อมูลของห้อง
        roomNumberLabel = new JLabel("Room Number:");
        roomTypeLabel = new JLabel("Room Type:");
        roomPriceLabel = new JLabel("Room Price:");
        electricityCostLabel = new JLabel("Electricity Cost:");
        electricityUnitLabel = new JLabel("Electricity Unit:");
        waterCostLabel = new JLabel("Water Cost:");
        waterUnitLabel = new JLabel("Water Unit:");
        totalCostLabel = new JLabel("Total Cost:");
        dateLabel = new JLabel("Date:");

        // เพิ่มป้ายชื่อและข้อมูลของห้องลงในพาเนล
        detailsPanel.add(roomNumberLabel);
        detailsPanel.add(new JLabel(roomNumber));

        detailsPanel.add(roomTypeLabel);
        detailsPanel.add(new JLabel(roomType));

        detailsPanel.add(roomPriceLabel);
        detailsPanel.add(new JLabel(NumberFormat.getCurrencyInstance().format(Double.parseDouble(roomPrice))));

        detailsPanel.add(electricityUnitLabel);
        detailsPanel.add(new JLabel(electricityUnit));

        detailsPanel.add(electricityCostLabel);
        int electricityUnit2 = Integer.parseInt(electricityUnit) * 8; // คำนวณค่าไฟฟ้า
        detailsPanel.add(new JLabel(NumberFormat.getCurrencyInstance().format(electricityUnit2)));

        detailsPanel.add(waterUnitLabel);
        detailsPanel.add(new JLabel(waterUnit));

        double waterCost = calculateWaterCost(Integer.parseInt(waterUnit));
        detailsPanel.add(waterCostLabel);
        detailsPanel.add(new JLabel(NumberFormat.getCurrencyInstance().format(waterCost)));

        detailsPanel.add(totalCostLabel);
        detailsPanel.add(new JLabel(NumberFormat.getCurrencyInstance().format(totalCost)));

        detailsPanel.add(dateLabel);
        detailsPanel.add(new JLabel(getCurrentDate()));

        // เพิ่มพาเนลรายละเอียดห้องลงในพาเนลหลัก
        panel.add(detailsPanel, BorderLayout.CENTER);

        // สร้างปุ่มปิด
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // ปิดหน้าต่าง
            }
        });

        // เพิ่มปุ่มปิดลงในพาเนล
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // เพิ่มพาเนลลงในหน้าต่าง JFrame
        add(panel);

        // กำหนดขนาดของหน้าต่าง
        setSize(350, 300);
        setLocationRelativeTo(null); // แสดงหน้าต่างตรงกลาง
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ปิดหน้าต่างเมื่อปิด
        setVisible(true);
    }

    // คำนวณค่าน้ำ
    private double calculateWaterCost(int waterUnits) {
        double waterCost = 0.0;

        if (waterUnits <= 50) {
            waterCost = waterUnits * 6;
        } else if (waterUnits <= 100) {
            waterCost = waterUnits * 7;
        } else {
            waterCost = waterUnits * 10;
        }

        return waterCost;
    }

    // รับวันที่ปัจจุบัน
    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }
}
