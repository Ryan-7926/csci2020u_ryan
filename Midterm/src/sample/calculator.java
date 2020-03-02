package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class calculator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        Text num1T = new Text("Number 1");
        Text num2T = new Text("Number 2");
        Text result = new Text("Result");

        TextField num1 = new TextField();
        TextField num2 = new TextField();
        TextField res = new TextField();

        Button add = new Button("Add");
        Button sub = new Button("Subtract");
        Button mult = new Button("Multiply");
        Button div = new Button("Divide");

        pane.add(num1T,0,0);
        pane.add(num2T,2,0);

        HBox box = new HBox();

        pane.add(num1,1,0);
        pane.add(num2,3,0);
        box.getChildren().add(add);
        pane.getChildren().add(sub);
        pane.getChildren().add(mult);
        pane.getChildren().add(div);


        add.setOnMouseClicked(e ->{
            //add numbers

        });
        sub.setOnMouseClicked(e ->{
            //subtract numbers

        });
        mult.setOnMouseClicked(e ->{
            //multiply numbers

        });
        div.setOnMouseClicked(e ->{
            //divide numbers

        });

        GridPane.setHalignment(pane, HPos.CENTER);

        primaryStage.setScene(new Scene(pane, 400, 100));
        primaryStage.show();
    }
}
