package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Application {
    // IO streams
    private static DataOutputStream toServer = null;
    private static DataInputStream fromServer = null;

    private static TextField username, message;

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox pane = new VBox(10);
        pane.setPadding(new Insets(20,20,20,20));

        HBox userPane = new HBox(10);
        Label uName = new Label("Username");
        username = new TextField();
        userPane.getChildren().addAll(uName, username);

        HBox msgPane = new HBox(10);
        Label msg = new Label("Message");
        message = new TextField();
        msgPane.getChildren().addAll(msg, message);

        Button send = new Button("Send");
        send.setOnMouseReleased(e -> send());

        Button exit = new Button("Exit");
        exit.setOnMouseReleased(e -> System.exit(0));

        pane.getChildren().addAll(userPane, msgPane, send, exit);

        primaryStage.setTitle("Client");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();

        try {
            //Create a socket to connect to the server
            Socket socket = new Socket("localhost" , 8000);

            //Create an input stream to receive data to the server
            fromServer = new DataInputStream(socket.getInputStream());

            //Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static String message() {
        if(username.getText() != null && message.getText() != null) {
            return username.getText() + ": " + message.getText() + "\n";
        }
        else if(username.getText() == null && message.getText() == null) {
            username.setPromptText("Enter Username");
            message.setPromptText("Enter Message");
        }
        else if(username.getText() != null) {
            username.setPromptText("Enter Username");
        }
        else if(message.getText() != null) {
            message.setPromptText("Enter Message");
        }

        return "";
    }

    private static void send() {
        try {
            //Send the message to the Server
            toServer.writeUTF(message());
            toServer.flush();
            message.setText("");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}