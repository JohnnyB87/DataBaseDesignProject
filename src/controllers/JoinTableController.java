package controllers;

import classes.Main;
import classes.PaneFrame;
import classes.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.sql.*;
import java.util.ArrayList;

public class JoinTableController {

    @FXML
    private PaneFrame paneFrame;
    @FXML
    private ComboBox<String> branchNo;
    @FXML
    private ComboBox<String> staffCol;
    @FXML
    private Button joinBranchStaff;
    @FXML
    private ComboBox<String> branchNoCustomer;
    @FXML
    private ComboBox<String> customerCol;
    @FXML
    private Button joinBranchCustomer;
    @FXML
    private ComboBox<String> customerNo;
    @FXML
    private ComboBox<String> accCol;
    @FXML
    private Button customerAccount;

    private Connection con;
    private String bNoS;
    private String cNo;
    private String bNoC;
    private String cCol;
    private String aCol;
    private String sCol;
    @FXML
    private void initialize(){
        con = Main.getCon();
        fillComboBoxes();
    }

    private void fillComboBoxes() {
        this.branchNo.setItems(loopThroughTable("Branch","Bno"));
        this.branchNoCustomer.setItems(loopThroughTable("Branch","Bno"));
        this.customerNo.setItems(loopThroughTable("Customer","Cno"));
        this.accCol.setItems(loopThroughTableCol("Account"));
        this.staffCol.setItems(loopThroughTableCol("Staff"));
        this.customerCol.setItems(loopThroughTableCol("Customer"));
    }

    private ObservableList<String> loopThroughTable( String tableName, String columnName){
        ObservableList<String> array = FXCollections.observableArrayList();
        try {
            Statement s = con.createStatement();
            String select = String.format("SELECT %s FROM %s",columnName,tableName);
            ResultSet rs = s.executeQuery(select);
            while(rs.next()) {
                String bNo = rs.getString(columnName);
                array.add(bNo);
            }
        }catch(SQLException sql){
            System.out.println("Failed to Loop");
        }
        return array;
    }

    private ObservableList<String> loopThroughTableCol( String tableName){
        ObservableList<String> array = FXCollections.observableArrayList();
        try {
            Statement s = con.createStatement ();
            String query = String.format("SELECT * FROM %s",tableName);
            ResultSet rs = s.executeQuery (query);
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            for (int i=1; i<=count; i++)
                array.add(metaData.getColumnLabel(i));
            array.add("ALL");
        }catch(SQLException sql){
            System.out.println("Failed to Loop");
        }
        return array;
    }

    public void searchBranchStaffBtn() {
        SQLQuery sql = new SQLQuery();
        String table1 = "Branch";
        String table2 = "Staff";
        sql.joinQuery(con,table1,table2,this.bNoS,this.sCol);
    }

    public void searchBranchCustomerBtn() {
        SQLQuery sql = new SQLQuery();
        String table1 = "Branch";
        String table2 = "Customer";
        sql.joinQuery(con,table1,table2,this.bNoC,this.cCol);
    }

    public void searchCustomerAccBtn() {
        SQLQuery sql = new SQLQuery();
        String table1 = "Customer";
        String table2 = "Account";
        sql.joinQuery(con,table1,table2,this.cNo,this.aCol);
    }

    @FXML
    private void setBNoS() {
        if(!this.branchNo.getValue().isEmpty() && this.branchNo.getValue() != null)
            this.bNoS = this.branchNo.getValue();
    }

    @FXML
    private void setSCol() {
        if(!this.staffCol.getValue().isEmpty() && this.staffCol.getValue() != null)
            this.sCol =  this.staffCol.getValue();
    }

    @FXML
    private void setBNoC() {
        if(!this.branchNoCustomer.getValue().isEmpty() && this.branchNoCustomer.getValue() != null)
            this.bNoC =  this.branchNoCustomer.getValue();
    }

    @FXML
    private void setCCol() {
        if(!this.customerCol.getValue().isEmpty() && this.customerCol.getValue() != null)
            this.cCol =  this.customerCol.getValue();
    }

    @FXML
    private void setCNo() {
        if(!this.customerNo.getValue().isEmpty() && this.customerNo.getValue() != null)
            this.cNo =  this.customerNo.getValue();
    }

    @FXML
    private void setACol() {
        if(!this.accCol.getValue().isEmpty() && this.accCol.getValue() != null)
            this.aCol =  this.accCol.getValue();
    }
}
