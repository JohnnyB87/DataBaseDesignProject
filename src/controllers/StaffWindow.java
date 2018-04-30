package controllers;

import classes.Main;
import classes.PaneFrame;
import classes.SQLQuery;
import classes.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;

public class StaffWindow {

    @FXML
    private PaneFrame paneFrame;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField nameTxtFld;
    @FXML
    private TextField contactTxtFld;
    @FXML
    private ComboBox bNoComboBox;
    @FXML
    private ComboBox posComboBox;
    private Button confirmButton;
    private Validator validator;
    private String sNo;
    private String bNo;
    private String name;
    private String position;
    private int contactNo;
    private String tableName;
    private Connection con;

    @FXML
    private void initialize(){
        if(this.paneFrame != null) {
            this.confirmButton = paneFrame.getConfirmButton();
            this.con = Main.getCon();
            this.tableName = "staff";
            this.validator = new Validator();
            ObservableList<String> bNoList = validator.createObservableList(this.con,"branch");
            this.bNoComboBox.getItems().addAll(bNoList);
            ObservableList<String> options =
                    FXCollections.observableArrayList(
                            "Manager",
                            "Clerk",
                            "Supervisor"
                    );
            this.posComboBox.getItems().addAll(options);
            this.confirmButton.setOnAction(e -> confirmButtonPressed());
            this.bNoComboBox.setOnAction(e->this.bNo = checkComboBox(this.bNoComboBox));
            this.posComboBox.setOnAction(e->this.position = checkComboBox(this.posComboBox));
        }
    }

    private void confirmButtonPressed(){
        validator.setContin(true);
        this.name = validator.validateTextFieldInputString(this.nameTxtFld.getText());
        this.contactNo = validator.validateTextFieldInputInt(this.contactTxtFld.getText());
        if(this.position == null || this.bNo == null)
            validator.setContin(false);

//        System.out.println(this.name + "    "+this.address+ "    "+this.contactNo+"    "+this.bNo );

        if(!validator.isContin())
            System.out.println("Enter correct input");
        else{
            Connection con = Main.getCon();
            this.sNo = validator.getNumber(con, this.tableName);
            System.out.println("Database connection established1");
            SQLQuery sqlQuery = new SQLQuery();
            sqlQuery.insertQuery(con, this.tableName,
                    this.sNo, this.bNo,this.name, this.position, Integer.toString(this.contactNo));
        }
        Stage s = (Stage)paneFrame.getScene().getWindow();
        s.close();
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public void setName(String name){
        this.name = name;
    }

    private String checkComboBox(ComboBox cb){
        if(cb.getValue() != null
                && !cb.getValue().toString().isEmpty() )
            return cb.getValue().toString();
        return null;
    }
}
