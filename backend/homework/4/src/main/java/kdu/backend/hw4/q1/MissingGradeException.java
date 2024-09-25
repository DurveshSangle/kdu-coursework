package kdu.backend.hw4.q1;

public class MissingGradeException extends Exception{
    final private int studentId;
    public MissingGradeException(int studentId){
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }
}
