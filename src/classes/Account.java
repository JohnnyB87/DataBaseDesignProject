package classes;

public class Account {

    //-------------------------
    //      ATTRIBUTES
    //-------------------------
    private String accNo;
    private String type;
    private String description;


    //-------------------------
    //      CONSTRUCTORS
    //-------------------------
    public Account(){}

    public  Account(String accNo, String type, String description){
        this.accNo = accNo;
        this.type = type;
        this.description = description;
    }

    //-------------------------
    //      GETTERS
    //-------------------------
    public String getAccNo() {
        return accNo;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    //-------------------------
    //      SETTERS
    //-------------------------
    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
