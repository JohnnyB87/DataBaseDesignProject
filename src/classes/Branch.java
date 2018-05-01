package classes;

public class Branch {

    //-------------------------
    //      ATTRIBUTES
    //-------------------------
    private String bNo;
    private String street;
    private String city;
    private String county;
    private String contactNo;

    //-------------------------
    //      CONSTRUCTORS
    //-------------------------
    public Branch() {
    }

    public Branch(String bNo, String street, String city, String county, String contactNo) {
        this.bNo = bNo;
        this.street = street;
        this.city = city;
        this.county = county;
        this.contactNo = contactNo;
    }

    //-------------------------
    //      GETTERS
    //-------------------------

    public String getBNo() {
        return bNo;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getContactNo() {
        return contactNo;
    }

    //-------------------------
    //      SETTERS
    //-------------------------

    public void setBNo(String bNo) {
        this.bNo = bNo;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void editDetails(int n, String newValue){
        String[] array = {bNo, street, city, county, contactNo};
        array[n] = newValue;
        this.bNo = array[0];
        this.street = array[1];
        this.city = array[2];
        this.county = array[3];
        this.contactNo = array[4];
    }
}
