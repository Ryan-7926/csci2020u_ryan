package question1;
/*
 * Ryan Christopher, 100702835
 * Mario Velazquez, 100702233
 *
 * Software Systems CSCI2020u
 * Assignment: question #1
 * Due: March 5, 2020
 *
 * Display a frame that contains three labels. Each label displays a card, as shown in
 * the figure below. The card image files are named 1.png, 2.png, ..., 54.png and
 * stored in the image/card directory. All three cards are distinct and selected
 * randomly.
 *
 */
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
        //generate random numbers to determine cards
        int[] cards = {rand.nextInt(54)+1,rand.nextInt(54)+1,rand.nextInt(54)+1};

        //check if any cards are the same
        for (int i = 0; i<3; i++) {
            for (int j=i+1; j<2; j++) {
                //if cards are the same, generate new number
                if (cards[i] == cards[j]) {cards[j] = rand.nextInt(54)+1; j--;}
            }
            //if cards are not the same add card to the HBox
            pane.getChildren().add(new ImageView(new Image(String.format("image/cards/%d.png",cards[i]))));
        }
        //display window
        primaryStage.setTitle("Three Cards");
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
