package classes;

public class Staff {

    //-------------------------
    //      ATTRIBUTES
    //-------------------------
    private String sNo;
    private String bNo;
    private String name;
    private String position;
    private int contactNo;

    //-------------------------
    //      CONSTRUCTORS
    //-------------------------
    public Staff() {
    }

    public Staff(String sNo, String bNo, String name, String position, int contactNo) {
        this.sNo = sNo;
        this.bNo = bNo;
        this.name = name;
        this.position = position;
        this.contactNo = contactNo;
    }

    //-------------------------
    //      GETTERS
    //-------------------------
    public String getSNo() {
        return sNo;
    }

    public String getBNo() {
        return bNo;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getContactNo() {
        return contactNo;
    }

    //-------------------------
    //      SETTERS
    //-------------------------
    public void setSNo(String sNo) {
        this.sNo = sNo;
    }

    public void setBNo(String bNo) {
        this.bNo = bNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }
}
