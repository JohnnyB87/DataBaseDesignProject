package classes;

import controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application{

    private static Stage primaryStage = new Stage();

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = getPrimaryStage();
        primaryStage.setTitle("Main Menu");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/MainWindow.fxml"));
        Pane mainWindow = loader.load();
        MainWindow mw = loader.getController();

        StackPane layout = new StackPane();

        layout.getChildren().add(mainWindow);
        Scene scene = new Scene(layout,400,350);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void runDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            //Connection con = DriverManager.getConnection(url, userName , password );

            // You Must replace YourName with your name and YourPassword with your password in the driver url

            Connection con = DriverManager.getConnection("jdbc:mysql://157.190.43.7:3306/estateagent5thed?user=YourName&password=YourPassword");
            System.out.println("Database connection established");
            Statement s = con.createStatement();

        }catch(Exception e){
            System.out.println("CONNECTION FAILED");
        }
    }
}
