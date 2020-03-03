package question2;
/*
 * Ryan Christopher, 100702835
 * Mario Velazquez, 100702233
 *
 * Software Systems CSCI2020u
 * Assignment: question #1
 * Due: March 5, 2020
 *
 * Write a program that calculates the future value of an investment at a given
 * interest rate for a specified number of years. The formula for the calculation is as
 * follows:
 * futureValue = investmentAmount * (1 + monthlyInterestRate)^years*12
 *
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InvestmentCalculator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();

        Text[] leftText = {new Text("Investment Amount"), new Text("Years"),
                        new Text("Annual Interest Rate"), new Text("Future Value")};

        TextField[] rightFields = {new TextField(), new TextField(), new TextField(), new TextField()};

        for (TextField field : rightFields) {field.setAlignment(Pos.CENTER_RIGHT);}

        //restrict input for last field since it is used for displaying output only
        rightFields[3].setEditable(false);

        Button calculate = new Button("Calculate");
        //set button action to calculate the interest by sending the text of the input fields and the field for output
        calculate.setOnAction(action ->
                calculateInterest(rightFields[0].getText(), rightFields[1].getText(),
                                rightFields[2].getText(),rightFields[3])
        );
        HBox buttonPane = new HBox();
        buttonPane.getChildren().add(calculate);
        buttonPane.setAlignment(Pos.CENTER_RIGHT);

        //GridPane formatting
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(2);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);

        //add elements to the pane
        for (Text text : leftText) {pane.addColumn(0, text);}
        for (TextField fields : rightFields) {pane.addColumn(1, fields);}
        pane.addColumn(1, buttonPane);

        //final window setup and display
        primaryStage.setTitle("Investment Calculator");
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*
     * This method is sent the text in the TextFields and an instance of a TextField,
     * calculates interest based on the numbers entered,
     * then sets the text of the TextField instance.
     */
    public void calculateInterest(String aS, String yS, String rS, TextField v) {
        double aD, yD,rD;

        //convert Strings to double
        aD = Double.parseDouble(aS);
        yD = Double.parseDouble(yS);
        rD = Double.parseDouble(rS)/100;

        // calculate interest
        double valueCalc = aD * Math.pow((1 + rD/12),yD*12);

        // set text field
        v.setText(String.format("%.2f", valueCalc));
    }
}
