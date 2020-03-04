package question3;
/*
 * Ryan Christopher, 100702835
 * Mario Velazquez, 100702233
 *
 * Software Systems CSCI2020u
 * Assignment: question #1
 * Due: March 5, 2020
 *
 * Draw a circle with three random points on the circle. Connect the points to form a
 * triangle. Display the angles in the triangle. Use the mouse to drag a point along
 * the perimeter of the circle. As you drag it, the triangle and angles are redisplayed
 * dynamically.
 *
 */
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

import static java.lang.Math.*;

public class DraggingPoints extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        int radius=100;
        //make circle
        Circle mainCircle = new Circle();
        mainCircle.setRadius(radius);
        mainCircle.setStroke(Color.BLACK);
        mainCircle.setFill(Color.WHITE);

        //initialize arrays for the coordinates of the points
        Random rand = new Random();
        float[] xPoints = new float[3];
        float[] yPoints = new float[3];

        //loop through the points and create them
        for (int i = 0; i<3; i++) {
            //generate random x-coordinate
            xPoints[i] = rand.nextFloat() * (radius);
            if (rand.nextBoolean()) {xPoints[i]*=-1;}

            //calculate y-coordinate based on x-coordinate
            yPoints[i] = (float) sqrt(radius*radius - xPoints[i]*xPoints[i]);

            //randomize y (+) or (-)
            if (rand.nextBoolean()) {
                yPoints[i]*=-1;

            }
            //put points in proper position on screen
            xPoints[i]+=250;
            yPoints[i]+=250;

        }
        //initialize and calculate array for angles
        float[] angles = calculateAngles(xPoints,yPoints);
        //make points
        Circle[] points = {new Circle(xPoints[0],yPoints[0],10,Color.RED),
                        new Circle(xPoints[1],yPoints[1],10,Color.YELLOW),
                        new Circle(xPoints[2],yPoints[2],10,Color.BLUE)
        };

        //make lines connecting the points
        //[0]= point 1 to 2, [1]= point 2 to 3, [2]= point 3 to 1
        Line[] lines = {new Line(xPoints[0],yPoints[0],xPoints[1],yPoints[1]),
                        new Line(xPoints[1],yPoints[1],xPoints[2],yPoints[2]),
                        new Line(xPoints[2],yPoints[2],xPoints[0],yPoints[0])
        };
        //make text to display the angles between the points
        Text[] anglesTxt = {new Text(xPoints[0],yPoints[0]-15,String.format("%.1f", toDegrees(angles[0]))),
                        new Text(xPoints[1],yPoints[1]-15,String.format("%.1f", toDegrees(angles[1]))),
                        new Text(xPoints[2],yPoints[2]-15,String.format("%.1f", toDegrees(angles[2])))
        };
        //loop to set up MouseDragged listeners for each point
        for (int i=0;i<3;i++) {
            int finalI = i;
            points[i].setOnMouseDragged(e -> {
                //calculate x and y based on angle from (250,250) to cursor
                //angle = arctan(x-250/y-250)
                float angle = (float) atan2(e.getY()-250,e.getX()-250);

                xPoints[finalI]= (float) (radius*cos(angle))+250;
                yPoints[finalI]= (float) (radius*sin(angle))+250;
                //set point positions
                points[finalI].setCenterX(xPoints[finalI]);
                points[finalI].setCenterY(yPoints[finalI]);

                //reposition lines
                lines[finalI].setStartX(xPoints[finalI]);
                lines[finalI].setStartY(yPoints[finalI]);
                lines[(finalI+2)%3].setEndX(xPoints[finalI]);
                lines[(finalI+2)%3].setEndY(yPoints[finalI]);

                //calculate angle of connecting lines
                float a = (float) hypot(xPoints[1]-xPoints[2],yPoints[1]-yPoints[2]);
                float b = (float) hypot(xPoints[0]-xPoints[2],yPoints[0]-yPoints[2]);
                float c = (float) hypot(xPoints[1]-xPoints[0],yPoints[1]-yPoints[0]);

                angles[0] = (float) acos((a*a -b*b - c*c)/(-2*b*c));
                angles[1] = (float) acos((b*b -a*a - c*c)/(-2*a*c));
                angles[2] = (float) acos((c*c - a*a -b*b)/(-2*a*b));
                //display updated angles
                for (int j=0;j<3;j++) {
                    anglesTxt[j].setText(String.format("%.1f", toDegrees(angles[j])));
                }
                //reposition angle text
                anglesTxt[finalI].setX(xPoints[finalI]);
                anglesTxt[finalI].setY(yPoints[finalI]-15);

            });
        }

        Pane pointsPane = new Pane();
        //add elements to the window
        for (int i=0; i<3; i++) {
            pointsPane.getChildren().add(anglesTxt[i]);
            pointsPane.getChildren().add(lines[i]);
            pointsPane.getChildren().add(points[i]);
        }
        StackPane mainPane = new StackPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.getChildren().addAll(mainCircle,pointsPane);
        mainPane.setPrefSize(500,500);

        //final window setup and display
        primaryStage.setTitle("Dragging Points");
        Scene scene = new Scene(mainPane);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /*
     * This method takes in two float arrays of x and y coordinates and calculates the angles between them,
     * then it returns an array of the calculated angles
     */
    public float[] calculateAngles(float[] x,float[] y) {
        //calculate hypotenuse of angles
        float a = (float) hypot(x[1]-x[2],y[1]-y[2]);
        float b = (float) hypot(x[0]-x[2],y[0]-y[2]);
        float c = (float) hypot(x[1]-x[0],y[1]-y[0]);

        float[] angle = new float[3];
        //formula given in assignment document to calculate angles
        angle[0] = (float) acos((a*a -b*b - c*c)/(-2*b*c));
        angle[1] = (float) acos((b*b -a*a - c*c)/(-2*a*c));
        angle[2] = (float) acos((c*c - a*a -b*b)/(-2*a*b));
        return angle;
    }
}
