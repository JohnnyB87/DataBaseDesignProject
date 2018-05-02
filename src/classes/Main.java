package classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.*;

public class Main extends Application{

    private static Stage primaryStage = new Stage();
    private static Connection con;
    private static String buttonPressed;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Connection getCon() {
        return con;
    }

    public static void setButtonPressed(String text){
        buttonPressed = text;
    }

    public static String getButtonPressed(){
        return buttonPressed;
    }

    public static void main(String[] args) {
        runDB();
        launch(args);
//        con.close();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = getPrimaryStage();
        primaryStage.setTitle("Main Menu");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/MainMenuWindow.fxml"));
        Pane mainWindow = loader.load();

        StackPane layout = new StackPane();

        layout.getChildren().add(mainWindow);
        Scene scene = new Scene(layout,400,350);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void runDB(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bank?autoReconnect=true&useSSL=false&user=root&password=6980");
            System.out.println("Database connection established");

        }catch(Exception e){
            System.out.println("CONNECTION FAILED");
            e.printStackTrace();
        }
    }
}
