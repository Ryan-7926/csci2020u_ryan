package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.xml.crypto.Data;

public class Main extends Application {
    TableView<StudentRecord> table = new TableView<StudentRecord>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane pane = new GridPane();

        TableColumn sIDCol = new TableColumn("SID");
        sIDCol.setMinWidth(120);
        sIDCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("SID"));

        TableColumn assignmentsCol = new TableColumn("Assignments");
        assignmentsCol.setMinWidth(120);
        assignmentsCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("Assignments"));

        TableColumn midtermCol = new TableColumn("Midterm");
        midtermCol.setMinWidth(120);
        midtermCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("Midterm"));

        TableColumn examCol = new TableColumn("Final Exam");
        examCol.setMinWidth(120);
        examCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("FinalExam"));

        TableColumn finalCol = new TableColumn("Final Mark");
        finalCol.setMinWidth(120);
        finalCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("FinalGrade"));

        TableColumn letterCol = new TableColumn("Letter Grade");
        letterCol.setMinWidth(120);
        letterCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("LetterGrade"));

        table.setItems(DataSource.getAllMarks());
        table.getColumns().addAll(sIDCol, assignmentsCol, midtermCol, examCol, finalCol, letterCol);

        pane.add(table,0,0);

        //TODO: add optional part of lab button and text fields etc

        primaryStage.setTitle("Student Records");
        primaryStage.setScene(new Scene(pane, 720, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
