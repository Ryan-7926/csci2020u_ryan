package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.MenuBar;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.stage.FileChooser;

public class Main extends Application {

    TableView<StudentRecord> table;
    TableColumn<StudentRecord, String> sidColumn;
    TableColumn<StudentRecord, Float> assignmentColumn;
    TableColumn<StudentRecord, Float> midtermColumn;
    TableColumn<StudentRecord, Float> examColumn;
    BorderPane pane;
    GridPane input;
    MenuBar menuBar;
    Menu menu;
    MenuItem newMenu, openMenu, saveMenu, saveAsMenu, exitMenu;
    TextField sidText, assignmentText, midtermText, examText;
    Button add;
    File file;
    String filename;
    FileChooser fileChooser;

    @Override
    public void start(Stage primaryStage) throws Exception{
        file = new File("records.csv");
        filename = "records.csv";
        VBox layout = new VBox();
        pane = new BorderPane();
        input = new GridPane();
        menuBar = new MenuBar();
        menu = new Menu("File");
        buildMenu();
        menuBar.getMenus().addAll(menu);
        buildTable();
        input();
        layout.getChildren().addAll(table, input);
        primaryStage.setTitle("Lab 08");
        pane.setTop(menuBar);
        pane.setBottom(layout);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    public void buildMenu() {
        newMenu =  new MenuItem("New");
        newMenu.setOnAction(e -> { table.getItems().clear(); });
        openMenu = new MenuItem("Open");
        openMenu.setOnAction(e -> { open(); });
        saveMenu = new MenuItem("Save");
        saveMenu.setOnAction(e -> save());
        saveAsMenu = new MenuItem("Save As");
        saveAsMenu.setOnAction(e -> saveAs());
        exitMenu = new MenuItem("Exit");
        exitMenu.setOnAction(e -> { System.exit(0); });
        menu.getItems().addAll(newMenu, openMenu, saveMenu, saveAsMenu, exitMenu);
    }

    public void buildTable() {
        // columns
        sidColumn = new TableColumn<>("SID");
        sidColumn.setMinWidth(100);
        sidColumn.setCellValueFactory(new PropertyValueFactory<>("SID"));

        assignmentColumn = new TableColumn<>("Assignments");
        assignmentColumn.setMinWidth(100);
        assignmentColumn.setCellValueFactory(new PropertyValueFactory<>("assignments"));

        midtermColumn = new TableColumn<>("Midterm");
        midtermColumn.setMinWidth(100);
        midtermColumn.setCellValueFactory(new PropertyValueFactory<>("midterm"));

        examColumn = new TableColumn<>("Exam");
        examColumn.setMinWidth(100);
        examColumn.setCellValueFactory(new PropertyValueFactory<>("exam"));

        TableColumn<StudentRecord, Float> overallColumn = new TableColumn<>("Final Mark");
        overallColumn.setMinWidth(100);
        overallColumn.setCellValueFactory(new PropertyValueFactory<>("overall"));

        TableColumn<StudentRecord, Character> gradeColumn = new TableColumn<>("Letter Grade");
        gradeColumn.setMinWidth(100);
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        // construct table
        table = new TableView<>();
        table.setItems(DataSource.getAllMarks());
        table.getColumns().addAll(sidColumn, assignmentColumn, midtermColumn, examColumn, overallColumn, gradeColumn);
    }

    public void input() {

        // settings
        input.setPadding(new Insets(10,10,10,10));
        input.setVgap(20);
        input.setHgap(10);

        // labels
        input.add(new Label("SID:"), 0, 0);
        input.add(new Label("Assignments:"), 2, 0);
        input.add(new Label("Midterm:"), 0, 1);
        input.add(new Label("Final Exam:"), 2, 1);

        // textfields

        sidText =  new TextField();
        sidText.setPromptText("SID");
        input.add(sidText,1,0);

        assignmentText =  new TextField();
        assignmentText.setPromptText("Assignments/100");
        input.add(assignmentText,4,0);

        midtermText =  new TextField();
        midtermText.setPromptText("Midterm/100");
        input.add(midtermText,1,1);

        examText =  new TextField();
        examText.setPromptText("Final Exam/100");
        input.add(examText,4,1);

        // button
        add = new Button("Add");
        add.setOnAction(e -> addRow());
        input.add(add,1,3);
    }

    public void addRow() {
        StudentRecord record = new StudentRecord();
        try {
            record.setSID(sidText.getText());
            record.setAssignments(Float.parseFloat(assignmentText.getText()));
            record.setMidterm(Float.parseFloat(midtermText.getText()));
            record.setExam(Float.parseFloat(examText.getText()));
            record.setOverall(record);
            record.setGrade(record);
            table.getItems().add(record);
        } catch (Exception e) {

        }
    }

    public void saveAs() {
        Stage fileWindow = new Stage();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Spreadsheet", "*.csv"));
        file = fileChooser.showSaveDialog(fileWindow);
        save();
    }

    public void save() {
        try {
            PrintWriter pw = new PrintWriter(file);
            String data;
            pw.write("SID,Assignments,Midterm,Final Exam\n");
            for (int i=0; i < table.getItems().size(); i++) {
                data = "";
                data += sidColumn.getCellData(i) + ",";
                data += assignmentColumn.getCellData(i) + ",";
                data += midtermColumn.getCellData(i) + ",";
                data += examColumn.getCellData(i) + "\n";
                pw.write(data);
            }
            pw.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void open() {
        Stage fileWindow = new Stage();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Spreadsheet", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(fileWindow);
        filename = selectedFile.getName();
        System.out.println(filename);
        load(selectedFile);
    }

    public void load(File selectedFile) {
        table.getItems().clear();
        try {
            Scanner scanner = new Scanner(selectedFile);
            String line = scanner.nextLine();
            String[] values;
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                values = line.split(",");
                StudentRecord record = new StudentRecord();
                record.setSID(values[0]);
                record.setAssignments(Float.parseFloat(values[1]));
                record.setMidterm(Float.parseFloat(values[2]));
                record.setExam(Float.parseFloat(values[3]));
                record.setOverall(record);
                record.setGrade(record);
                table.getItems().add(record);
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println("File Not Found");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}