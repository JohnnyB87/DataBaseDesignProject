package controllers;

import classes.Main;
import classes.PaneFrame;
import classes.SQLQuery;
import classes.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

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
    private String branchNo;
    private String tableName;

    @FXML
    private void initialize(){
        if(this.paneFrame != null) {
            this.confirmButton = paneFrame.getConfirmButton();
            this.tableName = "branch";
            confirmButton.setOnAction(e -> confirmButtonPressed());
        }
        con = Main.getCon();
    }

    private void confirmButtonPressed(){
        Validator validator = new Validator();
        validator.setContin(true);
        this.street = validator.validateTextFieldInputString(this.streetTxtFld.getText());
        this.city = validator.validateTextFieldInputString(this.cityTxtFld.getText());
        this.county = validator.validateTextFieldInputString(this.countyTxtFld.getText());
        this.contactNo = validator.validateTextFieldInputInt(this.contactTxtFld.getText());

        if(!validator.isContin())
            System.out.println("Enter correct input");
        else{
            Connection con = Main.getCon();
            this.branchNo = validator.getNumber(con, this.tableName);
            System.out.println("Database connection established1");
            SQLQuery sqlQuery = new SQLQuery();
            sqlQuery.insertQuery(con, this.tableName,
                    this.branchNo, this.street, this.city, this.county, Integer.toString(this.contactNo));
        }
        Stage s = (Stage)paneFrame.getScene().getWindow();
        s.close();
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

}
