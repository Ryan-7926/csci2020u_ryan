package question4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Flow;

public class Histogram extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private int[] numOccurr = new int[26];

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        BorderPane pane = new BorderPane();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        BarChart<String, Number> histogram = new BarChart<>(xAxis,yAxis) ;
        histogram.setAnimated(false);
        xAxis.setLabel("Letter");
        yAxis.setLabel("Occurrence");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Letters");
        String[] alphabet ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

        TextField fileInput = new TextField();
        String[] fileName = {""};
        fileInput.setOnKeyPressed(e ->{
            if (e.getCode() == KeyCode.ENTER) {
                fileName[0] = fileInput.getText();
            }
            System.out.println(fileName[0]);

            File inputFile = new File(fileName[0]);
            if (!inputFile.exists()) {
                //output error msg
                System.out.println("Does not exist");
                return;
            }
            else {
                System.out.println("Exists");

            }

            Scanner input = null;
            try {
                input = new Scanner(inputFile);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }


            for (int i=0; i<26; i++) {
                numOccurr[i] = 0;
            }


            do {
                //get inputs
                String characters = input.next();
                //loop through the string to count each letter
//                System.out.println("In file" + characters);

                for (int i = 0; i < characters.length(); i++) {
                    for (int j = 0; j < 26; j++) {
//                    System.out.println(characters.charAt(i));

                        String currentChar = String.valueOf(characters.charAt(i));
                        if (currentChar.equalsIgnoreCase(alphabet[j])) {
                            numOccurr[j] += 1;
                            System.out.println(alphabet[j] + ": " + numOccurr[j]);
                            break;
                        }
                    }


                }
                //create series for bar chart


            } while (input.hasNext());

            input.close();

            histogram.getData().clear();

            for (int x=0; x<26; x++) {
                series1.getData().add(new XYChart.Data(alphabet[x], numOccurr[x]));
            }


            histogram.getData().addAll(series1);

        });



        pane.setCenter(histogram);
        pane.setBottom(fileInput);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Histogram");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
