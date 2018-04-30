package controllers;

import classes.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class AccountWindow {

    @FXML
    private PaneFrame paneFrame;
    @FXML
    private TableView tableView;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField accountType;
    @FXML
    private TextField accountDescription;
    private Button confirmButton;
    private String type;
    private String description;
    private String accNo;
    private String tableName;
    private Validator validator;

    @FXML
    private void initialize(){
        if(this.paneFrame != null) {
            this.confirmButton = paneFrame.getConfirmButton();
            this.tableName = "account";
            this.validator = new Validator();
            if(this.titleLabel.getText().contains("Add")) {
                this.confirmButton.setOnAction(e -> {
                    testConfirmButton();
                });
            }
        }
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public void testConfirmButton(){
        validator = new Validator();
        validator.setContin(true);
        this.type = validator.validateTextFieldInputString(this.accountType.getText());
        this.description = validator.validateTextFieldInputString(this.accountDescription.getText());
        if(!validator.isContin()){}
        else {
            Connection con = Main.getCon();
            this.accNo = validator.getNumber(con, this.tableName);
            System.out.println("Database connection established1");
            SQLQuery sqlQuery = new SQLQuery();
            sqlQuery.insertQuery(con, this.tableName,this.accNo,this.type,this.description);
        }
        Stage s = (Stage)paneFrame.getScene().getWindow();
        s.close();
    }


}
