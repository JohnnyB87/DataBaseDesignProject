package classes;

public class Customer {

    //-------------------------
    //      ATTRIBUTES
    //-------------------------
    private String cNo;
    private String bNo;
    private String name;
    private String address;
    private String contactNo;

    //-------------------------
    //      CONSTRUCTORS
    //-------------------------
    public Customer(){}

    public Customer(String cNo, String bNo, String name, String address, String contactNo) {
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

    public String getContactNo() {
        return contactNo;
    }

    public String get(int n){
        String[] array = {cNo, bNo, name, address, contactNo};
        return array[n];
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

    public void setContactNo(String contactNo) {
        if(contactNo.matches("[0-9]+"))
            this.contactNo = contactNo;
        else
            System.out.println("Only numbers allowed");
    }

    public void editDetails(int n, String newValue){
        String[] array = {cNo, bNo, name, address, contactNo};
        array[n] = newValue;
        this.cNo = array[0];
        this.bNo = array[1];
        this.name = array[2];
        this.address = array[3];
        this.contactNo = array[4];
    }

    public void setNewValues(Customer c){
        this.cNo = c.cNo;
        this.bNo = c.bNo;
        this.name = c.name;
        this.address = c.address;
        this.contactNo = c.contactNo;
    }

    public String toString(){
        return String.format("%s : %s : %s : %s : %s%n",cNo,bNo,name,address,contactNo);
    }
}
