import java.awt.*;
import java.awt.event.*; 

import javax.swing.*;

public class Complaint extends JFrame implements ActionListener {
    JPanel headerPanel, menuPanel;
    Container container;
    JButton ComplaintFormBtn, ComplaintHistoryBtn;
    ComplaintForm ComplaintForm;
    ComplaintHistory ComplaintHistory;


    public Complaint() {
        super("Complaint");
        container = getContentPane();

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel);

        JLabel title = new JLabel("Complaint");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        headerPanel.add(title, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 10, 10, 10));
        headerPanel.add(menuPanel);

        ComplaintFormBtn = new JButton("Add Complaint");
        ComplaintFormBtn.addActionListener(this);
        menuPanel.add(ComplaintFormBtn);

        ComplaintHistoryBtn = new JButton("History");
        ComplaintHistoryBtn.addActionListener(this);
        menuPanel.add(ComplaintHistoryBtn);
    

        setSize(600, 600);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
     
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == ComplaintFormBtn) {
            ComplaintForm = new ComplaintForm();
        } else if(event.getSource() == ComplaintHistoryBtn) {
            ComplaintHistory = new ComplaintHistory();
        } 
    }
}