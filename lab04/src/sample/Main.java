package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.util.converter.DateTimeStringConverter;

import java.text.SimpleDateFormat;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setVgap(5);
        pane.setHgap(5);
        //username
        pane.add(new Label("Username"),0,0);
        TextField username = new TextField();
        username.setPromptText("Enter Username");
        pane.add(username, 1,0);
        //password
        pane.add(new Label("Password:"), 0,1);
        PasswordField password = new PasswordField();
        password.setPromptText("Enter Password");
        pane.add(password, 1,1);
        //full name
        pane.add(new Label("Full Name:"), 0 , 2);
        TextField name = new TextField();
        name.setPromptText("Enter Full Name");
        pane.add(name, 1 ,2);
        //email
        pane.add(new Label("E-Mail Address:"), 0 , 3);
        TextField email = new TextField();
        email.setPromptText("Enter E-Mail Address");
        pane.add(email, 1 ,3);
        //phone number
        pane.add(new Label("Phone #:"), 0 , 4);
        TextField phoneNumber = new TextField();
        phoneNumber.setPromptText("Enter Phone #");
        pane.add(phoneNumber, 1 ,4);
        //date of birth
        pane.add(new Label("Date of Birth:"), 0 , 5);
        DatePicker dateOfBirth = new DatePicker();
        dateOfBirth.setPromptText("Enter Date of Birth");
        pane.add(dateOfBirth, 1 ,5);

        //register button
        Button btAdd = new Button("Register");
        pane.add(btAdd, 1,6);
        GridPane.setHalignment(btAdd, HPos.RIGHT);
        btAdd.setOnAction(action -> {
            System.out.println("Username: " + username.getText() + "\nPassword: " + password.getText()
                                + "\nFull Name: " + name.getText() + "\nE-mail Address: " + email.getText());
        });


        primaryStage.setScene(new Scene(pane,500, 400));
        primaryStage.setTitle("Lab04");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
