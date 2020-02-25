package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    //bar chart
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    //pie chart
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane pane = new GridPane();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Housing Prices");

        for (int i = 0; i<avgHousingPricesByYear.length; i++) {
            series1.getData().add(new XYChart.Data(String.valueOf(i), avgHousingPricesByYear[i]));
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Commercial Prices");

        for (int i = 0; i<avgHousingPricesByYear.length; i++) {
            series2.getData().add(new XYChart.Data(String.valueOf(i), avgCommercialPricesByYear[i]));
        }

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data(ageGroups[0], purchasesByAgeGroup[0]),
                        new PieChart.Data(ageGroups[1], purchasesByAgeGroup[1]),
                        new PieChart.Data(ageGroups[2], purchasesByAgeGroup[2]),
                        new PieChart.Data(ageGroups[3], purchasesByAgeGroup[3]),
                        new PieChart.Data(ageGroups[4], purchasesByAgeGroup[4]),
                        new PieChart.Data(ageGroups[5], purchasesByAgeGroup[5]));

        final PieChart chart = new PieChart(pieChartData);
        bc.getData().addAll(series1, series2);

        pane.add(bc,0,0);
        pane.add(chart, 1, 0);

        primaryStage.setTitle("Lab 06");
        Scene scene = new Scene(pane);
        scene.getStylesheets().add("sample/styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
