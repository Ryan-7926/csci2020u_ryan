package sample;

import javafx.beans.property.SimpleStringProperty;

public class StudentRecord {
    private float midterm;
    private float assignments;
    private float finalExam;
    private float finalGrade;
    private String sID;
    private String letterGrade;

    StudentRecord(String sID, float assignments, float midterm, float finalExam) {
        this.sID = sID;
        this.assignments = assignments;
        this.midterm = midterm;
        this.finalExam =   finalExam;
    }

    public float getMidterm() {
        return midterm;
    }
    public String getLetterGrade() {

        if(getFinalGrade()>=80.0) {
            this.letterGrade = "A";

        }
        else if(getFinalGrade()>=70.0) {
            this.letterGrade = "B";

        }
        else if(getFinalGrade()>=60.0) {
            this.letterGrade = "C";

        }
        else if(getFinalGrade()>=50.0) {
            this.letterGrade = "D";

        }
        else {
            this.letterGrade = "F";

        }

        return letterGrade;
    }

    public String getSID() {
        return sID;
    }

    public float getAssignments() {
        return assignments;
    }

    public float getFinalExam() {
        return finalExam;
    }

    public float getFinalGrade() {
        //calculate final grade
        this.finalGrade = (float)(0.2 * getAssignments()) + (float)(0.3 * getMidterm()) + (float)(0.5 * getFinalExam());

        return finalGrade;
    }
}
