import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

// ประกาศคลาส ExpensesForm ซึ่งเป็น JFrame
public class ExpensesForm extends JFrame {
    private JTextArea expenseDetailsArea;
    private JTextField amountField; // Field for entering the amount

    // Constructor ของคลาส ExpensesForm
    public ExpensesForm() {
        setTitle("Expenses Form"); // กำหนดชื่อหน้าต่าง JFrame
        setSize(400, 350); // กำหนดขนาดของหน้าต่าง

        JPanel panel = new JPanel(); // สร้าง JPanel เพื่อใส่คอมโพเนนต์ต่างๆ
        panel.setLayout(null); // กำหนด Layout ของ JPanel เป็น null เพื่อให้สามารถกำหนดตำแหน่งและขนาดของคอมโพเนนต์เองได้

        JLabel expenseDetailsLabel = new JLabel("Expense Details:"); // สร้าง JLabel สำหรับแสดงข้อความ "Expense Details:"
        expenseDetailsLabel.setBounds(20, 20, 120, 25); // กำหนดตำแหน่งและขนาดของ JLabel
        panel.add(expenseDetailsLabel); // เพิ่ม JLabel เข้าไปใน JPanel

        expenseDetailsArea = new JTextArea(); // สร้าง JTextArea สำหรับใส่รายละเอียดค่าใช้จ่าย
        JScrollPane scrollPane = new JScrollPane(expenseDetailsArea); // สร้าง JScrollPane เพื่อให้สามารถเลื่อนข้อมูลในกรณีที่ข้อมูลมีมากเกินจอ
        scrollPane.setBounds(150, 25, 200, 100); // กำหนดตำแหน่งและขนาดของ JScrollPane
        panel.add(scrollPane); // เพิ่ม JScrollPane เข้าไปใน JPanel

        JLabel amountLabel = new JLabel("Amount:"); // สร้าง JLabel สำหรับแสดงข้อความ "Amount:"
        amountLabel.setBounds(20, 150, 120, 25); // กำหนดตำแหน่งและขนาดของ JLabel
        panel.add(amountLabel); // เพิ่ม JLabel เข้าไปใน JPanel

        amountField = new JTextField(); // สร้าง JTextField สำหรับใส่จำนวนเงิน
        amountField.setBounds(150, 150, 200, 25); // กำหนดตำแหน่งและขนาดของ JTextField
        panel.add(amountField); // เพิ่ม JTextField เข้าไปใน JPanel

        JButton submitButton = new JButton("Submit"); // สร้าง JButton สำหรับกดเพื่อส่งข้อมูล
        submitButton.setBounds(150, 200, 100, 25); // กำหนดตำแหน่งและขนาดของ JButton
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitExpense(); // เรียกเมธอดเพื่อส่งข้อมูลค่าใช้จ่าย
                disposeForm(); // เรียกเมธอดเพื่อปิดหน้าต่าง
            }
        });
        panel.add(submitButton); // เพิ่ม JButton เข้าไปใน JPanel

        add(panel); // เพิ่ม JPanel เข้าไปใน JFrame
        setVisible(true); // ทำให้ JFrame ปรากฏบนหน้าจอ
    }

    // เมธอดสำหรับส่งข้อมูลค่าใช้จ่าย
    private void submitExpense() {
        String expenseDetails = expenseDetailsArea.getText(); // รับข้อความรายละเอียดค่าใช้จ่าย
        String amount = amountField.getText(); // รับจำนวนเงินที่ป้อน

        // บันทึกข้อมูลค่าใช้จ่ายลงในไฟล์
        saveToFile(expenseDetails, amount);
    }

    // เมธอดสำหรับบันทึกข้อมูลลงในไฟล์
    private void saveToFile(String expenseDetails, String amount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("expenses.txt", true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String timestamp = dateFormat.format(new Date());

            writer.write(expenseDetails + "`" + amount + "`" + timestamp); // เขียนข้อมูลลงในไฟล์ expenses.txt
            writer.newLine();  // เพิ่มบรรทัดใหม่เพื่อให้ข้อมูลชัดเจน

            JOptionPane.showMessageDialog(this, "Expense details saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving expense details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // เมธอดสำหรับปิดหน้าต่าง
    private void disposeForm() {
        this.dispose(); // ปิดหน้าต่าง
    }

    // เมธอด main สำหรับการทดสอบ
    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {
    //         public void run() {
    //             new ExpensesForm();
    //         }
    //     });
    // }
}
