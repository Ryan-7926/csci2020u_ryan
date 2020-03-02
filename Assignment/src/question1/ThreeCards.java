package question1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;


public class ThreeCards extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox pane = new HBox();
        Random rand = new Random();

        int cards[] = {rand.nextInt(54)+1,rand.nextInt(54)+1,rand.nextInt(54)+1};

        //generate random numbers
        for (int i = 0; i<3; i++) {
            for (int j=i; j<3; j++) {
                if (cards[i] == cards[j]) {cards[j] = rand.nextInt(54)+1;}
            }
            System.out.println(cards[i]);

        }

        ImageView card1 = new ImageView(new Image(String.format("image/cards/%d.png",cards[0])));
        ImageView card2 = new ImageView(new Image(String.format("image/cards/%d.png",cards[1])));
        ImageView card3 = new ImageView(new Image(String.format("image/cards/%d.png",cards[2])));

        pane.getChildren().add(card1);
        pane.getChildren().add(card2);
        pane.getChildren().add(card3);

        primaryStage.setTitle("Three Cards");
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
