import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class room_receipt {
    private int roomNumber;
    private String firstName;
    private String lastName;
    private String tel;
    private String address;
    private Date date;

    public room_receipt() {
        
    }
    
    public room_receipt(int roomNumber, String firstName, String lastName, String tel, String address) {
        this.roomNumber = roomNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tel = tel;
        this.address = address;
        this.date = new Date();

        try {
            FileWriter writer = new FileWriter("Room_Receipt.txt");
            writer.write("หมายเลขห้อง : " + roomNumber + "\n");
            writer.write("ชื่อ-นามสกุล : " + firstName + " " + lastName + "\n");
            writer.write("เบอร์โทรศัพท์ : " + tel + "\n");
            writer.write("ที่อยู่ : " + address + "\n");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            writer.write("วันที่เช่า : " + dateFormat.format(date));
            writer.close();
            System.out.println("Room_Receipt.txt was saved");
        }
        catch (IOException e) {
            System.out.println("Error while writing file Room_Receipt.txt" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        room_receipt receipt = new room_receipt(1001, "ชยธร", "เติมพิพัฒน์พงศ์", "0957079861", "253/11 ม.รุ่งเรือง ถ.เทศบาล 4 ต.ปากเพรียว อ.เมือง จ.สระบุรี");
    }
}
