package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("TicTacToe");

        GridPane board = new GridPane();
        Random rand = new Random();

        ImageView x = new ImageView(new Image("image/x.gif"));
        ImageView x1 = new ImageView(new Image("image/x.gif"));
        ImageView x2 = new ImageView(new Image("image/x.gif"));
        ImageView x3 = new ImageView(new Image("image/x.gif"));



        ImageView o = new ImageView(new Image("image/o.gif"));
        ImageView o1 = new ImageView(new Image("image/o.gif"));
        ImageView o2 = new ImageView(new Image("image/o.gif"));
        ImageView o3 = new ImageView(new Image("image/o.gif"));

        board.add(x,0,0);
        board.add(x1,0,1);
        board.add(o,1,1);
        board.add(x2,1,0);
        board.add(o1,2,0);
        board.add(x3,0,2);
        board.add(o2,1,2);
        board.add(o3,2,1);




        primaryStage.setScene(new Scene(board, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
