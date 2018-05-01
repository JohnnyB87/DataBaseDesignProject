package classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Validator {

    private boolean cont;

    public boolean isCont() {
        return cont;
    }

    public void setCont(boolean cont) {
        this.cont = cont;
    }

    public String validateTextFieldInputString(String s){
        if(s.matches("[a-zA-Z ]+"))
            return s;
        this.cont = false;
        return null;
    }
    public String validateTextFieldInputInt(String s){
        if(s.matches("[0-9]+"))
            return s;
        this.cont = false;
        return null;
    }

    public String getNumber(Connection con, String table){
        String str;
        int start = 0;
        Statement s;
        String car = table.substring(0,1).toUpperCase();
        String col = String.format("%sno",car);
        try {
            s = con.createStatement();
            ResultSet rs = s.executeQuery (String.format("SELECT %s FROM %s",col,table));
            while (rs.next ())
            {
                str = rs.getString (col);
                int n = Integer.parseInt(str.substring(1));
                System.out.println(n);
                if(start <= n)
                    start = n + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s%03d",car,start));
        return String.format("%s%03d",car,start);
    }

    public ObservableList<String> createObservableList(Connection con, String tableName){
        String colName = tableName.substring(0,1).toUpperCase() + "no";
        ObservableList<String> ol = FXCollections.observableArrayList();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery (String.format("SELECT %s FROM %s",colName ,tableName));
            while(rs.next()){
                ol.add(rs.getString(colName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ol;
    }

}
