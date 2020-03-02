package question2;

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

        Text amountT = new Text("Investment Amount");
        Text yearsT = new Text("Years");
        Text rateT = new Text("Annual Interest Rate");
        Text valueT = new Text("Future Value");

        TextField amount = new TextField();
        TextField years = new TextField();
        TextField rate = new TextField();
        TextField value = new TextField();

        amount.setAlignment(Pos.CENTER_RIGHT);
        years.setAlignment(Pos.CENTER_RIGHT);
        rate.setAlignment(Pos.CENTER_RIGHT);
        value.setAlignment(Pos.CENTER_RIGHT);
        value.setEditable(false);

        Button calculate = new Button("Calculate");
        calculate.setOnAction(action ->
                calculateInterest(amount.getText(), years.getText(),rate.getText(),value)
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
        pane.addColumn(0, amountT, yearsT, rateT, valueT);
        pane.addColumn(1, amount, years, rate, value, buttonPane);


        //final window setup and display
        primaryStage.setTitle("Investment Calculator");
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void calculateInterest(String aS, String yS, String rS, TextField v) {
        System.out.println("Calculating");
        double aD, yD,rD;

        aD = Double.parseDouble(aS);
        yD = Double.parseDouble(yS);
        rD = Double.parseDouble(rS)/100;

        // calculate interest
        double valueCalc = aD * Math.pow((1 + rD/12),yD*12);

        // set text field
        v.setText(String.format("%.2f", valueCalc));
    }
}
