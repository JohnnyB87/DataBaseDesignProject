package controllers;

import classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StaffWindow {

    @FXML
    private TableView<Staff> tableView;
    @FXML
    private TableColumn<Staff,String> sNoCol;
    @FXML
    private TableColumn<Staff,String> bNoCol;
    @FXML
    private TableColumn<Staff,String> nameCol;
    @FXML
    private TableColumn<Staff,String> posCol;
    @FXML
    private TableColumn<Staff,String> contactNoCol;
    @FXML
    private PaneFrame paneFrame;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField nameTxtFld;
    @FXML
    private TextField contactTxtFld;
    @FXML
    private ComboBox<String> bNoComboBox;
    @FXML
    private ComboBox<String> posComboBox;
    private Staff staff;
    private Validator validator;
    private String tableName;
    private Connection con;

    @FXML
    private void initialize(){
        if(this.paneFrame != null) {
            Button confirmButton = paneFrame.getConfirmButton();
            this.con = Main.getCon();
            this.tableName = "staff";
            this.validator = new Validator();
            this.staff = new Staff();
            if(this.titleLabel.getText().contains("Add")) {
                ObservableList<String> bNoList = validator.createObservableList(this.con, "branch");
                this.bNoComboBox.getItems().addAll(bNoList);
                ObservableList<String> options =
                        FXCollections.observableArrayList(
                                "Manager",
                                "Clerk",
                                "Supervisor"
                        );
                this.posComboBox.getItems().addAll(options);
                confirmButton.setOnAction(e -> confirmButtonPressed());
                this.bNoComboBox.setOnAction(e -> this.staff.setBNo(checkComboBox(this.bNoComboBox)));
                this.posComboBox.setOnAction(e -> this.staff.setPosition(checkComboBox(this.posComboBox)));
            }
            else{
                setColumns();
                fillTable();
            }
        }
    }

    private void confirmButtonPressed(){
        validator.setContin(true);
        this.staff.setName(validator.validateTextFieldInputString(this.nameTxtFld.getText()));
        this.staff.setContactNo(validator.validateTextFieldInputInt(this.contactTxtFld.getText()));
        if(this.staff.getPosition() == null || this.staff.getBNo() == null)
            validator.setContin(false);

//        System.out.println(this.name + "    "+this.address+ "    "+this.contactNo+"    "+this.bNo );

        if(!validator.isContin())
            System.out.println("Enter correct input");
        else{
            Connection con = Main.getCon();
            this.staff.setSNo(validator.getNumber(con, this.tableName));
            System.out.println("Database connection established1");
            SQLQuery sqlQuery = new SQLQuery();
            sqlQuery.insertQuery(con, this.tableName,
                    this.staff.getSNo(), this.staff.getBNo(), this.staff.getName(),
                    this.staff.getPosition(), Integer.toString(this.staff.getContactNo())
            );
        }
        Stage s = (Stage)paneFrame.getScene().getWindow();
        s.close();
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    private String checkComboBox(ComboBox cb){
        if(cb.getValue() != null
                && !cb.getValue().toString().isEmpty() )
            return cb.getValue().toString();
        return null;
    }

    private void setColumns() {
        this.sNoCol.setCellValueFactory(new PropertyValueFactory<>("SNo"));
        this.bNoCol.setCellValueFactory(new PropertyValueFactory<>("BNo"));
        this.nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.posCol.setCellValueFactory(new PropertyValueFactory<>("Position"));
        this.contactNoCol.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
    }

    private void fillTable(){
        ObservableList<Staff> ol = FXCollections.observableArrayList();
        Statement s = null;
        try {
            s = con.createStatement();
            //Simple Query
            ResultSet rs = s.executeQuery ("SELECT * FROM staff");
            while (rs.next ())
            {
                Staff a = new Staff(
                        rs.getString (1),rs.getString(2),
                        rs.getString(3),rs.getString (4),
                        rs.getInt (5));
                ol.add(a);
            }
            this.tableView.setItems(ol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
