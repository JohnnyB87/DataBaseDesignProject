package classes;

public class Staff {

    //-------------------------
    //      ATTRIBUTES
    //-------------------------
    private String sNo;
    private String bNo;
    private String name;
    private String position;
    private String contactNo;

    //-------------------------
    //      CONSTRUCTORS
    //-------------------------
    public Staff() {
    }

    public Staff(String sNo, String bNo, String name, String position, String contactNo) {
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

    public String getContactNo() {
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

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void editDetails(int n, String newValue){
        String[] array = {sNo, bNo, name, position, contactNo};
        array[n] = newValue;
        this.sNo = array[0];
        this.bNo = array[1];
        this.name = array[2];
        this.position = array[3];
        this.contactNo = array[4];
    }
}
