import java.awt.*;
import java.awt.event.*; 

import javax.swing.*;

public class Finance extends JFrame implements ActionListener {
    JPanel headerPanel, menuPanel;
    Container container;
    JButton PaymentRentBtn, HistoryBtn, CashingBtn;
    PaymentRent PaymentRent;
    History History;
    Cashing Cashing;


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

        PaymentRentBtn = new JButton("Payment Rent");
        PaymentRentBtn.addActionListener(this);
        menuPanel.add(PaymentRentBtn);

        CashingBtn = new JButton("Cashing");
        CashingBtn.addActionListener(this);
        menuPanel.add(CashingBtn);

        HistoryBtn = new JButton("History");
        HistoryBtn.addActionListener(this);
        menuPanel.add(HistoryBtn);
    

        setSize(600, 600);
        setVisible(true);
    }
     
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == PaymentRentBtn) {
            PaymentRent = new PaymentRent();
        } else if(event.getSource() == HistoryBtn) {
            History = new History();
        }  else if(event.getSource() == CashingBtn) {
            Cashing = new Cashing();
        } 
    }
}
