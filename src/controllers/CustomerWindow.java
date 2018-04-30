package controllers;

import classes.Main;
import classes.PaneFrame;
import classes.SQLQuery;
import classes.Validator;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomerWindow {

    @FXML
    private PaneFrame paneFrame;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField nameTxtFld;
    @FXML
    private TextField addressTxtFld;
    @FXML
    private TextField contactTxtFld;
    @FXML
    private ComboBox bNoComboBox;
    private Button confirmButton;
    private String name;
    private String address;
    private int contactNo;
    private String bNo;
    private Connection con;
    Validator validator;
    private String cNo;
    private String tableName;

    @FXML
    private void initialize(){
        if(this.paneFrame != null) {
            this.confirmButton = paneFrame.getConfirmButton();
            this.tableName = "customer";
            this.con = Main.getCon();
            validator = new Validator();
            ObservableList<String> bNoList = validator.createObservableList(this.con,"branch");
            this.bNoComboBox.getItems().addAll(bNoList);
            this.confirmButton.setOnAction(e -> {
                confirmButtonPressed();
            });
            this.bNoComboBox.setOnAction(e-> checkComboBox());
        }
    }

    private void confirmButtonPressed(){
        validator.setContin(true);
        this.name = validator.validateTextFieldInputString(this.nameTxtFld.getText());
        this.address = validator.validateTextFieldInputString(this.addressTxtFld.getText());
        this.contactNo = validator.validateTextFieldInputInt(this.contactTxtFld.getText());

//        System.out.println(this.name + "    "+this.address+ "    "+this.contactNo+"    "+this.bNo );

        if(!validator.isContin())
            System.out.println("Enter correct input");
        else{
            Connection con = Main.getCon();
            this.cNo = validator.getNumber(con, this.tableName);
            System.out.println("Database connection established1");
            SQLQuery sqlQuery = new SQLQuery();
            sqlQuery.insertQuery(con, this.tableName,
                    this.cNo, this.bNo,this.name, this.address, Integer.toString(this.contactNo));
        }
        Stage s = (Stage)paneFrame.getScene().getWindow();
        s.close();
    }

    private void checkComboBox(){
        if(this.bNoComboBox.getValue() != null
                && !this.bNoComboBox.getValue().toString().isEmpty() )
            this.bNo = this.bNoComboBox.getValue().toString();
    }

    public Label getTitleLabel() {
        return titleLabel;
    }
}
