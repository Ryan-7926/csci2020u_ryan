package sample;

public class StudentRecord {

    // instance variables
    private String SID;
    private float assignments;
    private float midterm;
    private float exam;
    private float overall;
    private char grade;

    // constructors
    public StudentRecord() {
        this.SID = "";
        this.assignments = 0.0F;
        this.midterm = 0.0F;
        this.exam = 0.0F;
        this.overall = 0.0F;
        this.grade = 'X';
    }

    public StudentRecord(String SID, float assignments, float midterm, float exam) {
        this.SID = SID;
        this.assignments = assignments;
        this.midterm = midterm;
        this.exam = exam;
        this.overall = calculateMark();
        this.grade = calculateGrade();
    }

    // accessor methods
    public String getSID() { return this.SID; }
    public float getAssignments() { return this.assignments; }
    public float getMidterm() { return this.midterm; }
    public float getExam() { return this.exam; }
    public float getOverall() { return this.overall; }
    public char getGrade() { return this.grade; }

    // mutator methods
    public void setSID(String SID) { this.SID = SID; }
    public void setAssignments(float assignments) { this.assignments = assignments; }
    public void setMidterm(float midterm) { this.midterm = midterm; }
    public void setExam(float exam) { this.exam = exam; }
    public void setOverall(float overall) { this.overall = overall; }
    public void setGrade(char grade) { this.grade = grade; }

    // calculations
    public float calculateMark() {
        return (this.assignments * 0.2F) + (this.midterm * 0.3F) + (this.exam * 0.5F);
    }

    public void setOverall(StudentRecord record) {
        record.setOverall((record.getAssignments() * 0.2F) + (record.getMidterm() * 0.3F) + (record.getExam() * 0.5F));
    }

    public char calculateGrade() {
        if(this.overall>=80.0) {
            this.grade = 'A';

        }
        else if(this.overall>=70.0) {
            this.grade = 'B';

        }
        else if(this.overall>=60.0) {
            this.grade = 'C';

        }
        else if(this.overall>=50.0) {
            this.grade = 'D';

        }
        else {
            this.grade = 'F';

        }

        return grade;
    }

    public void setGrade(StudentRecord record) {
        if(record.overall < 50) record.setGrade('F');
        else if(record.overall < 60) record.setGrade('D');
        else if(record.overall < 70) record.setGrade('C');
        else if(record.overall < 80) record.setGrade('B');
        else record.setGrade('A');
    }
}