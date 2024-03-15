import java.awt.*;
import java.awt.event.*; 

import javax.swing.*;

public class Finance extends JFrame implements ActionListener {
    JPanel headerPanel, menuPanel;
    Container container;
    JButton PaymentReportBtn, ComplaintHistoryBtn;
    PaymentReport PaymentReport;
    ComplaintHistory ComplaintHistory;


    public Finance() {
        super("Report");
        container = getContentPane();

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Report");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 10, 10, 10));
        headerPanel.add(menuPanel);

        PaymentReportBtn = new JButton("Payment");
        PaymentReportBtn.addActionListener(this);
        menuPanel.add(PaymentReportBtn);

        ComplaintHistoryBtn = new JButton("Invoicing");
        ComplaintHistoryBtn.addActionListener(this);
        menuPanel.add(ComplaintHistoryBtn);
    

        setSize(600, 600);
        setVisible(true);
    }
     
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == PaymentReportBtn) {
            PaymentReport = new PaymentReport();
        } else if(event.getSource() == ComplaintHistoryBtn) {
            // ComplaintHistory = new ComplaintHistory();
        } 
    }
}
