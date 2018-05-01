package classes;

import java.sql.*;

public class SQLQuery {

    private String getNumberOfValues(String[] array){
        String str = "";
        String car = "?,";
        int i=0;
        int size = array.length;
        while(i<size) {
            str = String.format("%s%s", str, car);
            i++;
        }
        return str.substring(0,str.length()-1);
    }

    public void deleteQuery(Connection con,String tableName, String accNo){
        try {
            //delete
            String id = tableName.charAt(0) + "No";
            Statement deleteStmt = con.createStatement();
            String delete = String.format("Delete from %s where %s ='%s'",tableName , id, accNo);
            deleteStmt.executeUpdate(delete);
            deleteStmt.close();
        }catch (Exception io) {
            System.out.println("error" + io);
        }
    }

    public void insertQuery(Connection con,String tableName, String... array){
        System.out.println("Database connection established1");
        String values = getNumberOfValues(array);
        try {
            String insert = String.format("INSERT INTO %s VALUES (%s)",tableName, values);
            PreparedStatement stmt = con.prepareStatement(insert);
            int i=1;
            for(String s : array){
                try{
                    stmt.setInt(i, Integer.parseInt(s));
                }catch(NumberFormatException nfe){
                    stmt.setString(i,s);
                }
                i++;
            }
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.printf("%s table updated",tableName);
    }

    public void updateQuery(Connection con,String tableName, String... array){

    }
}
