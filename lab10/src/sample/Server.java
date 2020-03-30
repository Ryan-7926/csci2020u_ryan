package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server extends Application {
    TextArea chatArea = new TextArea();
    private int clientNo = 0;

    @Override
    public void start(Stage primaryStage) {
        VBox pane = new VBox(10);
        pane.setPadding(new Insets(20,20,20,20));

        chatArea.setWrapText(true);
        chatArea.setMinWidth(300);
        chatArea.setMinHeight(100);

        Button exit = new Button("Exit");
        exit.setOnMouseReleased(e -> System.exit(0));

        pane.getChildren().addAll(chatArea, exit);

        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(pane, 400, 275));
        primaryStage.show();

        new Thread( () -> {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(8000);
                chatArea.appendText("Chat server started at "
                        + new Date() + '\n');

                while (true) {
                    // Listen for a new connection request
                    Socket socket = serverSocket.accept();

                    // Increment clientNo
                    clientNo++;

                    Platform.runLater( () -> {
                        // Display the client number
                        chatArea.appendText("User " + clientNo +
                                " joined at " + new Date() + '\n');

                    });

                    // Create and start a new thread for the connection
                    new Thread(new HandleAClient(socket)).start();
                }
            }
            catch(IOException ex) {
                System.err.println(ex);
            }
        }).start();
    }

    // Define the thread class for handling new connection
    class HandleAClient implements Runnable {
        private Socket socket; // A connected socket

        /** Construct a thread */
        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        /** Run a thread */
        public void run() {
            try {
                // Create data input and output streams
                DataInputStream inputFromClient = new DataInputStream(
                        socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(
                        socket.getOutputStream());

                // Continuously serve the client
                while (true) {
                    // Receive radius from the client
                    String message = inputFromClient.readUTF();

                    Platform.runLater(() -> {
                        chatArea.appendText(message);
                    });
                }
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}