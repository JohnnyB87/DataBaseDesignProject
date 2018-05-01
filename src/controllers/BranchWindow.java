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
    @FXML
    private TableView<Branch> tableView;
    @FXML
    private TableColumn<Branch, String> bNoCol;
    @FXML
    private TableColumn<Branch, String> streetCol;
    @FXML
    private TableColumn<Branch, String> cityCol;
    @FXML
    private TableColumn<Branch, String> countyCol;
    @FXML
    private TableColumn<Branch, String> contactNoCol;

    private Connection con;
    private String tableName;
    private Validator validator;
    private Branch branch;

    @FXML
    private void initialize(){
        String action = Main.getButtonPressed();
        if(this.paneFrame != null) {
            this.con = Main.getCon();
            Button confirmButton = paneFrame.getConfirmButton();
            this.tableName = "branch";
            this.validator = new Validator();
            branch = new Branch();
            if(action.equalsIgnoreCase("Add")) {
                confirmButton.setOnAction(e -> confirmButtonPressed());
            }
            else{
                setColumns();
                fillTable();
                if(action.equalsIgnoreCase("Update")) {
                    this.tableView.setEditable(true);
                }
                else if(action.equalsIgnoreCase("Delete")){
                    this.tableView.setEditable(true);
                    confirmButton.setText("Delete");
                    confirmButton.setOnAction(e-> {
                        Branch selectedItem = tableView.getSelectionModel().getSelectedItem();
                        tableView.getItems().remove(selectedItem);
                        SQLQuery query = new SQLQuery();
                        query.deleteQuery(con, tableName, selectedItem.getBNo());
                    });
                }
            }
        }
        con = Main.getCon();
    }

    private void confirmButtonPressed(){
        this.validator = new Validator();
        this.validator.setCont(true);
        this.branch.setStreet(validator.validateTextFieldInputString(this.streetTxtFld.getText()));
        this.branch.setCity(validator.validateTextFieldInputString(this.cityTxtFld.getText()));
        this.branch.setCounty(validator.validateTextFieldInputString(this.countyTxtFld.getText()));
        this.branch.setContactNo(validator.validateTextFieldInputInt(this.contactTxtFld.getText()));

        if(!validator.isCont())
            System.out.println("Enter correct input");
        else{
            this.branch.setBNo(validator.getNumber(con, this.tableName));
            System.out.println("Database connection established1");
            SQLQuery sqlQuery = new SQLQuery();
            sqlQuery.insertQuery(con, this.tableName, branch.getBNo());
        }
        Stage s = (Stage)paneFrame.getScene().getWindow();
        s.close();
    }

    private void setColumns() {
        this.bNoCol.setCellValueFactory(new PropertyValueFactory<>("BNo"));
        this.streetCol.setCellValueFactory(new PropertyValueFactory<>("Street"));
        this.cityCol.setCellValueFactory(new PropertyValueFactory<>("City"));
        this.countyCol.setCellValueFactory(new PropertyValueFactory<>("County"));
        this.contactNoCol.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
    }

    private void fillTable(){
        ObservableList<Branch> ol = FXCollections.observableArrayList();
        Statement s;
        try {
            s = con.createStatement();
            //Simple Query
            ResultSet rs = s.executeQuery ("SELECT * FROM branch");
            while (rs.next ())
            {
                int index = 1;
                Branch b = new Branch(rs.getString (index++),rs.getString(index++),
                        rs.getString(index++), rs.getString(index++), rs.getInt(index));
                ol.add(b);
            }
            this.tableView.setItems(ol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

}
