package controllers;

import classes.*;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ComboBox<String> bNoComboBox;
    @FXML
    private TableView<Customer> tableView;
    @FXML
    private TableColumn<Customer, String> cNoCol;
    @FXML
    private TableColumn<Customer, String> bNoCol;
    @FXML
    private TableColumn<Customer, String> nameCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, String> contactNoCol;

    private Connection con;
    private Validator validator;
    private String tableName;
    private Customer customer;

    @FXML
    private void initialize(){
        if(this.paneFrame != null) {
            Button confirmButton = paneFrame.getConfirmButton();
            this.tableName = "customer";
            this.con = Main.getCon();
            validator = new Validator();
            customer = new Customer();
            if(this.titleLabel.getText().contains("Add")) {
                ObservableList<String> bNoList = validator.createObservableList(this.con, "branch");
                this.bNoComboBox.getItems().addAll(bNoList);
                confirmButton.setOnAction(e -> {
                    confirmButtonPressed();
                });
                this.bNoComboBox.setOnAction(e -> checkComboBox());
            }
            else{
                setColumns();
                fillTable();
            }
        }
    }

    private void confirmButtonPressed(){
        validator.setContin(true);
        customer.setName(validator.validateTextFieldInputString(this.nameTxtFld.getText()));
        customer.setAddress(validator.validateTextFieldInputString(this.addressTxtFld.getText()));
        customer.setContactNo(validator.validateTextFieldInputInt(this.contactTxtFld.getText()));

//        System.out.println(this.name + "    "+this.address+ "    "+this.contactNo+"    "+this.bNo );

        if(!validator.isContin())
            System.out.println("Enter correct input");
        else{
            customer.setCNo(validator.getNumber(con, this.tableName));
            System.out.println("Database connection established1");
            SQLQuery sqlQuery = new SQLQuery();
            sqlQuery.insertQuery(con, this.tableName,
                    customer.getCNo(), customer.getBNo(), customer.getName(),
                    customer.getAddress(), Integer.toString(customer.getContactNo()));
        }
        Stage s = (Stage)paneFrame.getScene().getWindow();
        s.close();
    }


    private void setColumns() {
        this.cNoCol.setCellValueFactory(new PropertyValueFactory<>("CNo"));
        this.bNoCol.setCellValueFactory(new PropertyValueFactory<>("BNo"));
        this.nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        this.contactNoCol.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
    }

    private void fillTable(){
        ObservableList<Customer> ol = FXCollections.observableArrayList();
        Statement s;
        try {
            s = con.createStatement();
            //Simple Query
            ResultSet rs = s.executeQuery ("SELECT * FROM staff");
            while (rs.next ())
            {
                int index = 1;
                Customer c = new Customer(
                        rs.getString (index++),rs.getString(index++),
                        rs.getString(index++),rs.getString (index++),
                        rs.getInt (index));
                ol.add(c);
            }
            this.tableView.setItems(ol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkComboBox(){
        if(this.bNoComboBox.getValue() != null
                && !this.bNoComboBox.getValue().isEmpty() )
            customer.setBNo(this.bNoComboBox.getValue());
    }

    public Label getTitleLabel() {
        return titleLabel;
    }
}
