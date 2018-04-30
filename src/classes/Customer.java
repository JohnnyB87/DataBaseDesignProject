package classes;

public class Customer {

    //-------------------------
    //      ATTRIBUTES
    //-------------------------
    private String cNo;
    private String bNo;
    private String name;
    private String address;
    private int contactNo;

    //-------------------------
    //      CONSTRUCTORS
    //-------------------------
    public Customer(){}

    public Customer(String cNo, String bNo, String name, String address, int contactNo) {
        this.cNo = cNo;
        this.bNo = bNo;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
    }

    //-------------------------
    //      GETTERS
    //-------------------------

    public String getCNo() {
        return cNo;
    }

    public String getBNo() {
        return bNo;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getContactNo() {
        return contactNo;
    }


    //-------------------------
    //      SETTERS
    //-------------------------

    public void setCNo(String cNo) {
        this.cNo = cNo;
    }

    public void setBNo(String bNo) {
        this.bNo = bNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }
}
