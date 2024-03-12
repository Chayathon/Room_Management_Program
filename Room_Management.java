import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class Room_Management extends JFrame {
    // JPanel headerPanel, menuPanel;
    // Container container;
    // JButton btn101, btn102, btn103, btn104, btn105, btn106, btn107, btn108, btn109, btn110, btn201, btn202, btn203, btn204, btn205, btn206, btn207, btn208, btn209, btn210, btn301, btn302, btn303, btn304, btn305, btn306, btn307, btn308, btn309, btn310;
    // CardLayout cardLayout;

    public Room_Management() {
        super("Room Management Program");
        // container = getContentPane();
        
    //     cardLayout = new CardLayout(10, 10);
    //     container.setLayout(new FlowLayout());
    //     container.setLayout(cardLayout);

    //     headerPanel = new JPanel();
    //     headerPanel.setLayout(new BorderLayout());
    //     container.add(headerPanel);

    //     // JLabel title = new JLabel("Room Management Program");
    //     // title.setHorizontalAlignment(JLabel.CENTER);
    //     // title.setFont(new Font("Tahoma", Font.BOLD, 28));
    //     // headerPanel.add(title, BorderLayout.NORTH);

    //     menuPanel = new JPanel();
    //     menuPanel.setLayout(new GridLayout(3, 10, 10, 10));
    //     headerPanel.add(menuPanel);
        
    //     container.add(new rent_room_info(cardLayout, container, 101, "Fan"), "Room101");
    //     btn101 = new JButton("101 (Fan)");
    //     btn101.addActionListener(this);
    //     menuPanel.add(btn101);

    //     container.add(new rent_room_info(cardLayout, container, 102, "Fan"), "Room102");
    //     btn102 = new JButton("102 (Fan)");
    //     btn102.addActionListener(this);
    //     menuPanel.add(btn102);

    //     container.add(new rent_room_info(cardLayout, container, 103, "Fan"), "Room103");
    //     btn103 = new JButton("103 (Fan)");
    //     btn103.addActionListener(this);
    //     menuPanel.add(btn103);

    //     container.add(new rent_room_info(cardLayout, container, 104, "Fan"), "Room104");
    //     btn104 = new JButton("104 (Fan)");
    //     btn104.addActionListener(this);
    //     menuPanel.add(btn104);

    //     container.add(new rent_room_info(cardLayout, container, 105, "Fan"), "Room105");
    //     btn105 = new JButton("105 (Fan)");
    //     btn105.addActionListener(this);
    //     menuPanel.add(btn105);

    //     container.add(new rent_room_info(cardLayout, container, 106, "Fan"), "Room106");
    //     btn106 = new JButton("106 (Fan)");
    //     btn106.addActionListener(this);
    //     menuPanel.add(btn106);

    //     container.add(new rent_room_info(cardLayout, container, 107, "Fan"), "Room107");
    //     btn107 = new JButton("107 (Fan)");
    //     btn107.addActionListener(this);
    //     menuPanel.add(btn107);

    //     container.add(new rent_room_info(cardLayout, container, 108, "Fan"), "Room108");
    //     btn108 = new JButton("108 (Fan)");
    //     btn108.addActionListener(this);
    //     menuPanel.add(btn108);

    //     container.add(new rent_room_info(cardLayout, container, 109, "Fan"), "Room109");
    //     btn109 = new JButton("109 (Fan)");
    //     btn109.addActionListener(this);
    //     menuPanel.add(btn109);

    //     container.add(new rent_room_info(cardLayout, container, 110, "Fan"), "Room110");
    //     btn110 = new JButton("110 (Fan)");
    //     btn110.addActionListener(this);
    //     menuPanel.add(btn110);

    //     container.add(new rent_room_info(cardLayout, container, 201, "Air Conditioner"), "Room201");
    //     btn201 = new JButton("201 (Air Conditioner)");
    //     btn201.addActionListener(this);
    //     menuPanel.add(btn201);

    //     container.add(new rent_room_info(cardLayout, container, 202, "Air Conditioner"), "Room202");
    //     btn202 = new JButton("202 (Air Conditioner)");
    //     btn202.addActionListener(this);
    //     menuPanel.add(btn202);

    //     container.add(new rent_room_info(cardLayout, container, 203, "Air Conditioner"), "Room203");
    //     btn203 = new JButton("203 (Air Conditioner)");
    //     btn203.addActionListener(this);
    //     menuPanel.add(btn203);

    //     container.add(new rent_room_info(cardLayout, container, 204, "Air Conditioner"), "Room204");
    //     btn204 = new JButton("204 (Air Conditioner)");
    //     btn204.addActionListener(this);
    //     menuPanel.add(btn204);

    //     container.add(new rent_room_info(cardLayout, container, 205, "Air Conditioner"), "Room205");
    //     btn205 = new JButton("205 (Air Conditioner)");
    //     btn205.addActionListener(this);
    //     menuPanel.add(btn205);

    //     container.add(new rent_room_info(cardLayout, container, 206, "Air Conditioner"), "Room206");
    //     btn206 = new JButton("206 (Air Conditioner)");
    //     btn206.addActionListener(this);
    //     menuPanel.add(btn206);

    //     container.add(new rent_room_info(cardLayout, container, 207, "Air Conditioner"), "Room207");
    //     btn207 = new JButton("207 (Air Conditioner)");
    //     btn207.addActionListener(this);
    //     menuPanel.add(btn207);

    //     container.add(new rent_room_info(cardLayout, container, 208, "Air Conditioner"), "Room208");
    //     btn208 = new JButton("208 (Air Conditioner)");
    //     btn208.addActionListener(this);
    //     menuPanel.add(btn208);

    //     container.add(new rent_room_info(cardLayout, container, 209, "Air Conditioner"), "Room209");
    //     btn209 = new JButton("209 (Air Conditioner)");
    //     btn209.addActionListener(this);
    //     menuPanel.add(btn209);

    //     container.add(new rent_room_info(cardLayout, container, 210, "Air Conditioner"), "Room210");
    //     btn210 = new JButton("210 (Air Conditioner)");
    //     btn210.addActionListener(this);
    //     menuPanel.add(btn210);

    //     container.add(new rent_room_info(cardLayout, container, 301, "Air Conditioner"), "Room301");
    //     btn301 = new JButton("301 (Air Conditioner)");
    //     btn301.addActionListener(this);
    //     menuPanel.add(btn301);

    //     container.add(new rent_room_info(cardLayout, container, 303, "Air Conditioner"), "Room302");
    //     btn302 = new JButton("302 (Air Conditioner)");
    //     btn302.addActionListener(this);
    //     menuPanel.add(btn302);

    //     container.add(new rent_room_info(cardLayout, container, 303, "Air Conditioner"), "Room303");
    //     btn303 = new JButton("303 (Air Conditioner)");
    //     btn303.addActionListener(this);
    //     menuPanel.add(btn303);

    //     container.add(new rent_room_info(cardLayout, container, 304, "Air Conditioner"), "Room304");
    //     btn304 = new JButton("304 (Air Conditioner)");
    //     btn304.addActionListener(this);
    //     menuPanel.add(btn304);

    //     container.add(new rent_room_info(cardLayout, container, 305, "Air Conditioner"), "Room305");
    //     btn305 = new JButton("305 (Air Conditioner)");
    //     btn305.addActionListener(this);
    //     menuPanel.add(btn305);

    //     container.add(new rent_room_info(cardLayout, container, 306, "Air Conditioner"), "Room306");
    //     btn306 = new JButton("306 (Air Conditioner)");
    //     btn306.addActionListener(this);
    //     menuPanel.add(btn306);

    //     container.add(new rent_room_info(cardLayout, container, 307, "Air Conditioner"), "Room307");
    //     btn307 = new JButton("307 (Air Conditioner)");
    //     btn307.addActionListener(this);
    //     menuPanel.add(btn307);

    //     container.add(new rent_room_info(cardLayout, container, 308, "Air Conditioner"), "Room308");
    //     btn308 = new JButton("308 (Air Conditioner)");
    //     btn308.addActionListener(this);
    //     menuPanel.add(btn308);

    //     container.add(new rent_room_info(cardLayout, container, 309, "Air Conditioner"), "Room309");
    //     btn309 = new JButton("309 (Air Conditioner)");
    //     btn309.addActionListener(this);
    //     menuPanel.add(btn309);

    //     container.add(new rent_room_info(cardLayout, container, 310, "Air Conditioner"), "Room310");
    //     btn310 = new JButton("310 (Air Conditioner)");
    //     btn310.addActionListener(this);
    //     menuPanel.add(btn310);

    //     setSize(1700, 400);
    //     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     setVisible(true);
    }
    public static void main(String[] args) throws IOException {
        Room_Management RM = new Room_Management();
        rent_room RR = new rent_room();
    }
    
    // @Override
    // public void actionPerformed(ActionEvent event) {
    //     if(event.getSource() == btn101) {
    //         cardLayout.show(container, "Room101");
    //     }
    //     else if(event.getSource() == btn102) {
    //         cardLayout.show(container, "Room102");
    //     }
    //     else if(event.getSource() == btn103) {
    //         cardLayout.show(container, "Room103");
    //     }
    //     else if(event.getSource() == btn104) {
    //         cardLayout.show(container, "Room104");
    //     }
    //     else if(event.getSource() == btn105) {
    //         cardLayout.show(container, "Room105");
    //     }
    //     else if(event.getSource() == btn106) {
    //         cardLayout.show(container, "Room106");
    //     }
    //     else if(event.getSource() == btn107) {
    //         cardLayout.show(container, "Room107");
    //     }
    //     else if(event.getSource() == btn108) {
    //         cardLayout.show(container, "Room108");
    //     }
    //     else if(event.getSource() == btn109) {
    //         cardLayout.show(container, "Room109");
    //     }
    //     else if(event.getSource() == btn110) {
    //         cardLayout.show(container, "Room110");
    //     }
    //     else if(event.getSource() == btn201) {
    //         cardLayout.show(container, "Room201");
    //     }
    //     else if(event.getSource() == btn202) {
    //         cardLayout.show(container, "Room202");
    //     }
    //     else if(event.getSource() == btn203) {
    //         cardLayout.show(container, "Room203");
    //     }
    //     else if(event.getSource() == btn204) {
    //         cardLayout.show(container, "Room204");
    //     }
    //     else if(event.getSource() == btn205) {
    //         cardLayout.show(container, "Room205");
    //     }
    //     else if(event.getSource() == btn206) {
    //         cardLayout.show(container, "Room206");
    //     }
    //     else if(event.getSource() == btn207) {
    //         cardLayout.show(container, "Room207");
    //     }
    //     else if(event.getSource() == btn208) {
    //         cardLayout.show(container, "Room208");
    //     }
    //     else if(event.getSource() == btn209) {
    //         cardLayout.show(container, "Room209");
    //     }
    //     else if(event.getSource() == btn210) {
    //         cardLayout.show(container, "Room210");
    //     }
    //     else if(event.getSource() == btn301) {
    //         cardLayout.show(container, "Room301");
    //     }
    //     else if(event.getSource() == btn302) {
    //         cardLayout.show(container, "Room302");
    //     }
    //     else if(event.getSource() == btn303) {
    //         cardLayout.show(container, "Room303");
    //     }
    //     else if(event.getSource() == btn304) {
    //         cardLayout.show(container, "Room304");
    //     }
    //     else if(event.getSource() == btn305) {
    //         cardLayout.show(container, "Room305");
    //     }
    //     else if(event.getSource() == btn306) {
    //         cardLayout.show(container, "Room306");
    //     }
    //     else if(event.getSource() == btn307) {
    //         cardLayout.show(container, "Room307");
    //     }
    //     else if(event.getSource() == btn308) {
    //         cardLayout.show(container, "Room308");
    //     }
    //     else if(event.getSource() == btn309) {
    //         cardLayout.show(container, "Room309");
    //     }
    //     else if(event.getSource() == btn310) {
    //         cardLayout.show(container, "Room310");
    //     }
    // }
}