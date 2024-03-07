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
        // this.roomNumber = roomNumber;
        // this.firstName = firstName;
        // this.lastName = lastName;
        // this.tel = tel;
        // this.address = address;
        this.date = new Date();

        try {
            SimpleDateFormat dateFormatTitle = new SimpleDateFormat("dd-MM-yyyy");
            String fileName = "Room_Receipt_" + dateFormatTitle.format(date) + "_" + roomNumber + ".txt";
            FileWriter writer = new FileWriter(fileName);
            writer.write("หมายเลขห้อง : " + roomNumber + "\n");
            writer.write("ชื่อ-นามสกุล : " + firstName + " " + lastName + "\n");
            writer.write("เบอร์โทรศัพท์ : " + tel + "\n");
            writer.write("ที่อยู่ : " + address + "\n");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            writer.write("วันที่เช่า : " + dateFormat.format(date));
            writer.close();
            System.out.println(fileName +" was saved");
        }
        catch (IOException e) {
            System.out.println("Error while writing file " + e.getMessage());
        }
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setFname(String firstName) {
        this.firstName = firstName;
    }

    public void setLname(String lastName) {
        this.lastName = lastName;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getFname() {
        return firstName;
    }

    public String getLname() {
        return lastName;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }
    
    

    // public static void main(String[] args) {
    //     room_receipt receipt = new room_receipt(1001, "ชยธร", "เติมพิพัฒน์พงศ์", "0957079861", "253/11 ม.รุ่งเรือง ถ.เทศบาล 4 ต.ปากเพรียว อ.เมือง จ.สระบุรี");
    // }
}
