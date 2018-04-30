package classes;

import java.sql.*;

public class SQLQuery {

    public void insertQuery(Connection con,String tableName, String... array){
        System.out.println("Database connection established1");
        String str = "";
        String car = "?,";
        int i=0;
        int size = array.length;
        while(i<size) {
            str = String.format("%s%s", str, car);
            i++;
        }
        str = str.substring(0,str.length()-1);
        try {
            String insert = String.format("INSERT INTO %s VALUES (%s)",tableName, str);
            PreparedStatement stmt = con.prepareStatement(insert);
            i=1;
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
            // You May need to uncomment if Autocommit is not set
            //con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.printf("%s table updated",tableName);
    }
}
