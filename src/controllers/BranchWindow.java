package controllers;

import classes.Main;
import classes.PaneFrame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;

public class BranchWindow {

    @FXML
    private PaneFrame paneFrame;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField streetTxtFld;
    @FXML
    private TextField countyTxtFld;
    @FXML
    private TextField cityTxtFld;
    @FXML
    private TextField contactTxtFld;
    private String street;
    private String city;
    private String county;
    private int contactNo;
    private Connection con;
    private Button confirmButton;
    private boolean contin = true;

    @FXML
    private void initialize(){
        if(this.paneFrame != null) {
            this.confirmButton = paneFrame.getConfirmButton();
            confirmButton.setOnAction(e -> confirmButtonPressed());
        }
        con = Main.getCon();
    }

    private void confirmButtonPressed(){
        this.contin = true;
        this.street = validateTextFieldInputString(this.streetTxtFld.getText());
        this.city = validateTextFieldInputString(this.cityTxtFld.getText());
        this.county = validateTextFieldInputString(this.countyTxtFld.getText());
        this.contactNo = validateTextFieldInputInt(this.contactTxtFld.getText());

        if(!this.contin)
            System.out.println("Enter correct input");
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public String validateTextFieldInputString(String s){
        if(s.matches("[a-zA-Z ]+"))
            return s;
        this.contin = false;
        return null;
    }
    public int validateTextFieldInputInt(String s){
        if(s.matches("[0-9]+"))
            return Integer.parseInt(s);
        this.contin = false;
        return 0;
    }
}
