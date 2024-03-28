import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cashing extends JFrame {
    JPanel headerPanel, menuPanel; 
    Container container; 
    JButton roomBtn; 
    CardLayout cardLayout; 

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
        menuPanel.setLayout(new GridLayout(0, 6, 10, 10)); 
        JScrollPane scrollPane = new JScrollPane(menuPanel); 
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        headerPanel.add(scrollPane, BorderLayout.CENTER); 
        
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
                    roomBtn.setPreferredSize(new Dimension(120, 60)); 
                    roomBtn.setFont(new Font("Tahoma", Font.PLAIN, 18)); 
 

                    roomBtn.setText(roomNumber);
                    roomBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) { 
                            double totalCost, waterCo, electCo,roomPri;
                            roomPri = Double.parseDouble(data[2]);
                            electCo = Double.parseDouble(data[3]) * 8;
                            waterCo = Double.parseDouble(data[4]);
                            if (waterCo <= 50) {
                                waterCo = waterCo * 6;
                            } else if (waterCo <= 100) {
                                waterCo = waterCo * 7;
                            } else {
                                waterCo = waterCo * 10;
                            }
                            totalCost = Double.parseDouble(data[5]); 
                            showRoomDetailsDialog(roomNumber, roomPri, electCo, waterCo, totalCost); 
                        }
                    });
                    
                    menuPanel.add(roomBtn); 
                }
            }

            reader.close(); 
        } catch (IOException e) { 
            System.out.println("Error while reading file " + e.getMessage());
        }

        setSize(800, 600); 
        setLocationRelativeTo(null); 
        setVisible(true); 
    }

    private void showRoomDetailsDialog(String roomNumber, Double price, Double electricityCost, Double waterCost, double totalCost) {
        JDialog dialog = new JDialog(this, "Room Details and Payment", true); 
        JPanel panel = new JPanel(new GridBagLayout()); 
        GridBagConstraints constraints = new GridBagConstraints(); 

        constraints.fill = GridBagConstraints.HORIZONTAL; 
        constraints.insets = new Insets(5, 5, 5, 5); 
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Room Number:"), constraints); 

        constraints.gridx = 1;
        panel.add(new JLabel(roomNumber), constraints); 

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Room Price:"), constraints); 

        constraints.gridx = 1;
        panel.add(new JLabel(NumberFormat.getCurrencyInstance().format(price)), constraints); 

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Electricity Cost:"), constraints); 

        constraints.gridx = 1;
        panel.add(new JLabel(NumberFormat.getCurrencyInstance().format(electricityCost)), constraints); 

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Water Cost:"), constraints); 

        constraints.gridx = 1;
        panel.add(new JLabel(NumberFormat.getCurrencyInstance().format(waterCost)), constraints); 

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(new JLabel("Total Cost:"), constraints); 

        constraints.gridx = 1;
        panel.add(new JLabel(NumberFormat.getCurrencyInstance().format(totalCost)), constraints); 

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(new JLabel("Enter Payment:"), constraints); 

        constraints.gridx = 1;
        JTextField paymentField = new JTextField(10); 
        panel.add(paymentField, constraints); 

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        JButton confirmButton = new JButton("Confirm Payment"); 
        panel.add(confirmButton, constraints); 

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) { 
                String paymentAmountStr = paymentField.getText(); 
                if (!paymentAmountStr.isEmpty()) { 
                    double paymentAmount = Double.parseDouble(paymentAmountStr); 
                    double change = paymentAmount - totalCost; 

                    if (change >= 0) { 
                        updatePaymentStatus(roomNumber); 
                        showChangeDialog(change); 
                        dialog.dispose(); 
                    } else { 
                        JOptionPane.showMessageDialog(dialog, "The payment amount is insufficient.", "Error",
                                JOptionPane.ERROR_MESSAGE); 
                    }
                } else { 
                    JOptionPane.showMessageDialog(dialog, "Please enter payment amount.", "Error",
                            JOptionPane.ERROR_MESSAGE); 
                }
            }
        });

        dialog.add(panel); 
        dialog.setSize(400, 300); 
       
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
    
                if (currentRoomNumber.equals(roomNumber)) {
                    // อัปเดตสถานะการชำระเงินเป็น 1 หากห้องตรงกับห้องที่ต้องการอัปเดต
                    writer.write(currentRoomNumber + " 1 " + data[2] + " " + data[3] + " " + data[4] + " " + data[5] + " " + data[6]);
                } else {
                    // คัดลอกข้อมูลเดิมไปยังไฟล์ชั่วคราว
                    writer.write(line);
                }
                writer.newLine();
            }
    
            reader.close();
            writer.close();
    
            if (!file.delete()) {
                System.out.println("Error deleting file");
                return;
            }
            if (!tempFile.renameTo(file)) {
                System.out.println("Error renaming file");
            }
    
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(container, "Error updating payment status. Please try again.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
      
    private void showChangeDialog(double change) {
        JOptionPane.showMessageDialog(container, "Change: " + NumberFormat.getCurrencyInstance().format(change), "Change", JOptionPane.INFORMATION_MESSAGE); 
        restartApplication(); 
    }
        
    private void restartApplication() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                dispose(); 
                new Cashing(); 
            }
        });
    }
        
    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {
    //         public void run() {
    //             new Cashing(); 
    //         }
    //     });
    // }
}
