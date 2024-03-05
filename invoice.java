public class invoice {
    private String firstName;
    private String lastName;
    private int tel;
    private String address;
    private Date date;
    
    public invoice(String firstName, String lastName, int tel, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tel = tel;
        this.address = address;
        this.date = new Date();
    }
}
