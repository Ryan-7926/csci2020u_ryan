package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

import static java.lang.StrictMath.pow;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Lab 07");
        Pane pane = new Pane();


        Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE,Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};
        File file = new File("weatherwarnings-2015.csv");
        Scanner input = new Scanner(file);
        input.useDelimiter(",");

        String[] warningsArray = {"FLASH FLOOD","SEVERE THUNDERSTORM","SPECIAL MARINE","TORNADO"};
        int[] warningsAmount= new int[4];

        int total = 0;
        String warnings = null;

        while (input.hasNext()){
            warnings = input.next();

            for (int i=0;i<warningsAmount.length; i++) {
                if(warnings.equals(warningsArray[i])){
                    warningsAmount[i] += 1;
                    total += 1;
                }
            }
        }

        input.close();

        Canvas canvas = new Canvas(800,400);
        GraphicsContext g = canvas.getGraphicsContext2D();

        double startAngle = 0;
        double endAngle;
        double arc = pow(total,-1)*360;
        //pie chart
        for(int i=0; i < (warningsArray.length); i++) {
            endAngle = (warningsAmount[i]*arc);
            g.setFill(pieColours[i]);

            g.fillArc(350,50,300,300,startAngle,endAngle, ArcType.ROUND);

            startAngle += endAngle;
        }



        //legend
        for(int i=0; i < (warningsArray.length); i++) {
            g.setFill(pieColours[i]);
            g.fillRect(20,(3*i*20)+75,50,50);
            g.setFill(Color.BLACK);
            g.fillText(warningsArray[i] + " (" + warningsAmount[i] + ")" , 80,(3*i*20)+105);
        }

        pane.getChildren().add(canvas);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
