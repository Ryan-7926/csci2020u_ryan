package question4;
/*
 * Ryan Christopher, 100702835
 * Mario Velazquez, 100702233
 *
 * Software Systems CSCI2020u
 * Assignment: question #1
 * Due: March 5, 2020
 *
 * Develop a program that displays a histogram to show the occurrences of each
 * letter in a text area. The histogram should show the occurrences of each letter in a
 * text file, as shown in the following figure. Assume that the letters are not case
 * sensitive.
 *
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Histogram extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private int[] numOccurr = new int[26];

    @Override
    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        BarChart<String, Number> histogram = new BarChart<>(xAxis,yAxis) ;
        histogram.setAnimated(false);
        histogram.setCategoryGap(0);
        xAxis.setLabel("Letter");
        yAxis.setLabel("Occurrence");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Letters");
        String[] alphabet ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

        TextField fileInput = new TextField();
        String[] fileName = {""};
        //set on key action for input text field
        fileInput.setOnKeyPressed(e ->{
            //when the enter key is pressed on the text field the text is received
            if (e.getCode() == KeyCode.ENTER) {
                fileName[0] = fileInput.getText();

                //create file object and check if the file in the path exists
                File inputFile = new File(fileName[0]);
                if (!inputFile.exists()) {
                    //output error msg
                    System.out.println("File does not exist");
                    return;
                }
                else {
                    System.out.println("Reading file");
                }
                // create scanner to read the file
                Scanner input = null;
                try {
                    input = new Scanner(inputFile);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                //reset the number of occurrences each time the enter button is pressed
                for (int i=0; i<26; i++) {
                    numOccurr[i] = 0;
                }

                //run loop at least once ot receive input and if there is more that the scanner pick ups keep looping
                do {
                    //get input
                    assert input != null;
                    String characters = input.next();
                    //loop through the string to count each letter
                    for (int i = 0; i < characters.length(); i++) {
                        //for each letter in the input check if it is equal to any letter in the alphabet
                        for (int j = 0; j < 26; j++) {
                            String currentChar = String.valueOf(characters.charAt(i));
                            //if characters are equal add 1 to the corresponding array index and break the loop
                            if (currentChar.equalsIgnoreCase(alphabet[j])) {
                                numOccurr[j] += 1;
                                System.out.println(alphabet[j] + ": " + numOccurr[j]);
                                break;
                            }
                        }
                    }
                } while (input.hasNext());

                input.close();

                //clear the histogram each time a file is searched to fix adding same series multiple times error
                histogram.getData().clear();

                //add data to the series
                for (int x=0; x<26; x++) {
                    series1.getData().add(new XYChart.Data<>(alphabet[x], numOccurr[x]));
                }
                //add series to the chart
                histogram.getData().addAll(series1);
            }

        });

        //final window setup
        pane.setCenter(histogram);
        pane.setBottom(fileInput);
        Scene scene = new Scene(pane,800,800);
        primaryStage.setTitle("Histogram");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
