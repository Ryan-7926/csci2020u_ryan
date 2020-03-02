package question3;

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
        Circle circle = new Circle();
        circle.setRadius(radius);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);

        //get random points
        Random rand = new Random();
        float[] xPoints = new float[3];
        float[] yPoints = new float[3];
        float[] angles;

        for (int i = 0; i<3; i++) {
            xPoints[i] = rand.nextFloat() * (radius);

            if (rand.nextBoolean()) {
                xPoints[i]*=-1;

            }
            yPoints[i] = (float) sqrt(radius*radius - xPoints[i]*xPoints[i]);

            //randomize y (+) or (-)
            if (rand.nextBoolean()) {
                yPoints[i]*=-1;

            }
            xPoints[i]+=250;
            yPoints[i]+=250;
//            System.out.println(xPoints[i]);
//            System.out.println(yPoints[i]);


        }
        angles = calculateAngles(xPoints,yPoints);
        //make points
        Circle point1 = new Circle(xPoints[0],yPoints[0],10,Color.RED);
        Circle point2 = new Circle(xPoints[1],yPoints[1],10,Color.YELLOW);
        Circle point3 = new Circle(xPoints[2],yPoints[2],10,Color.BLUE);

        //make lines
        Line L1to2 = new Line(xPoints[0],yPoints[0],xPoints[1],yPoints[1]);
        Line L2to3 = new Line(xPoints[1],yPoints[1],xPoints[2],yPoints[2]);
        Line L3to1 = new Line(xPoints[2],yPoints[2],xPoints[0],yPoints[0]);

        Text A = new Text(xPoints[0],yPoints[0]-15,String.format("%.1f", toDegrees(angles[0])));
        Text B = new Text(xPoints[1],yPoints[1]-15,String.format("%.1f", toDegrees(angles[1])));
        Text C = new Text(xPoints[2],yPoints[2]-15,String.format("%.1f", toDegrees(angles[2])));



        point1.setOnMouseDragged(e -> {
            //calculate x and y based on angle from (250,250) to cursor
            //angle = arctan(x-250/y-250)

            float tempX = (float) e.getX()-250;
            float tempY = (float) (e.getY()-250);
//            System.out.println(tempX + ", " + tempY);
            float angle = (float) atan2(tempY,tempX);
//            System.out.println(angle);

            xPoints[0]= (float) (radius*cos(angle));
            yPoints[0]= (float) (radius*sin(angle));

            xPoints[0]+=250;
            yPoints[0]+=250;

            point1.setCenterX(xPoints[0]);
            point1.setCenterY(yPoints[0]);
            L1to2.setStartX(xPoints[0]);
            L1to2.setStartY(yPoints[0]);
            L3to1.setEndX(xPoints[0]);
            L3to1.setEndY(yPoints[0]);
            //calculate angle of connecting lines
//            angles = calculateAngles(xPoints,yPoints);
            float a = (float) hypot(xPoints[1]-xPoints[2],yPoints[1]-yPoints[2]);
            float b = (float) hypot(xPoints[0]-xPoints[2],yPoints[0]-yPoints[2]);
            float c = (float) hypot(xPoints[1]-xPoints[0],yPoints[1]-yPoints[0]);

            angles[0] = (float) acos((a*a -b*b - c*c)/(-2*b*c));
            angles[1] = (float) acos((b*b -a*a - c*c)/(-2*a*c));
            angles[2] = (float) acos((c*c - a*a -b*b)/(-2*a*b));

            A.setText(String.format("%.1f", toDegrees(angles[0])));
            B.setText(String.format("%.1f", toDegrees(angles[1])));
            C.setText(String.format("%.1f", toDegrees(angles[2])));

            A.setX(xPoints[0]);
            A.setY(yPoints[0]-15);

        });

        point2.setOnMouseDragged(e -> {
            //calculate x and y based on angle from (250,250) to cursor
            //angle = arctan(x-250/y-250)
            float tempX = (float) e.getX()-250;
            float tempY = (float) (e.getY()-250);
//            System.out.println(tempX + ", " + tempY);
            float angle = (float) atan2(tempY,tempX);
            System.out.println(angle);

            xPoints[1]= (float) (radius*cos(angle));
            yPoints[1]= (float) (radius*sin(angle));

            xPoints[1]+=250;
            yPoints[1]+=250;

            point2.setCenterX(xPoints[1]);
            point2.setCenterY(yPoints[1]);
            L2to3.setStartX(xPoints[1]);
            L2to3.setStartY(yPoints[1]);
            L1to2.setEndX(xPoints[1]);
            L1to2.setEndY(yPoints[1]);

//            angles = calculateAngles(xPoints,yPoints);
            float a = (float) hypot(xPoints[1]-xPoints[2],yPoints[1]-yPoints[2]);
            float b = (float) hypot(xPoints[0]-xPoints[2],yPoints[0]-yPoints[2]);
            float c = (float) hypot(xPoints[1]-xPoints[0],yPoints[1]-yPoints[0]);

            angles[0] = (float) acos((a*a -b*b - c*c)/(-2*b*c));
            angles[1] = (float) acos((b*b -a*a - c*c)/(-2*a*c));
            angles[2] = (float) acos((c*c - a*a -b*b)/(-2*a*b));


            A.setText(String.format("%.1f", toDegrees(angles[0])));
            B.setText(String.format("%.1f", toDegrees(angles[1])));
            C.setText(String.format("%.1f", toDegrees(angles[2])));
            B.setX(xPoints[1]);
            B.setY(yPoints[1]-15);

        });

        point3.setOnMouseDragged(e -> {
            //calculate x and y based on angle from (250,250) to cursor
            //angle = arctan(x-250/y-250)
            float tempX = (float) e.getX()-250;
            float tempY = (float) (e.getY()-250);
//            System.out.println(tempX + ", " + tempY);
            float angle = (float) atan2(tempY,tempX);
//            System.out.println(angle);

            xPoints[2]= (float) (radius*cos(angle));
            yPoints[2]= (float) (radius*sin(angle));

            xPoints[2]+=250;
            yPoints[2]+=250;

            point3.setCenterX(xPoints[2]);
            point3.setCenterY(yPoints[2]);
            L3to1.setStartX(xPoints[2]);
            L3to1.setStartY(yPoints[2]);
            L2to3.setEndX(xPoints[2]);
            L2to3.setEndY(yPoints[2]);

//            angles = calculateAngles(xPoints,yPoints);
            float a = (float) hypot(xPoints[1]-xPoints[2],yPoints[1]-yPoints[2]);
            float b = (float) hypot(xPoints[0]-xPoints[2],yPoints[0]-yPoints[2]);
            float c = (float) hypot(xPoints[1]-xPoints[0],yPoints[1]-yPoints[0]);

            angles[0] = (float) acos((a*a -b*b - c*c)/(-2*b*c));
            angles[1] = (float) acos((b*b -a*a - c*c)/(-2*a*c));
            angles[2] = (float) acos((c*c - a*a -b*b)/(-2*a*b));


            A.setText(String.format("%.1f", toDegrees(angles[0])));
            B.setText(String.format("%.1f", toDegrees(angles[1])));
            C.setText(String.format("%.1f", toDegrees(angles[2])));
            C.setX(xPoints[2]);
            C.setY(yPoints[2]-15);
        });



        Pane pointsPane = new Pane();
        pointsPane.getChildren().addAll(point1,point2,point3,L1to2,L2to3,L3to1,A,B,C);

        StackPane mainPane = new StackPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.getChildren().addAll(circle,pointsPane);
        mainPane.setPrefSize(500,500);


        //final window setup and display
        primaryStage.setTitle("Dragging Points");
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public float[] calculateAngles(float[] x,float[] y) {
        float a = (float) hypot(x[1]-x[2],y[1]-y[2]);
        float b = (float) hypot(x[0]-x[2],y[0]-y[2]);
        float c = (float) hypot(x[1]-x[0],y[1]-y[0]);

        float[] angle = new float[3];
        angle[0] = (float) acos((a*a -b*b - c*c)/(-2*b*c));
        angle[1] = (float) acos((b*b -a*a - c*c)/(-2*a*c));
        angle[2] = (float) acos((c*c - a*a -b*b)/(-2*a*b));
        return angle;
    }
}
