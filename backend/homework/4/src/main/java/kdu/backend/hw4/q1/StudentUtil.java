package kdu.backend.hw4.q1;

import kdu.backend.hw4.Logging;

import java.util.Arrays;

public class StudentUtil {


    public static final Logging log = new Logging();

    /**
     * Returns the calculated GPA of students
     *
     * @param studentIdList The integer array containing students id's
     * @param studentsGrades The character 2d array containing grades of every student in his/her course
     * @return double array of GPA's
     *
     * */
    public static double[] calculateGPA(int[] studentIdList,char[][] studentsGrades) throws MissingGradeException {
        if(studentIdList.length!=studentsGrades.length){
            String errorMsg = "studentIdList & studentsGrades are out-of-sync. studentIdList.length: " + studentIdList.length + ", studentsGrades.length: " + studentsGrades.length;
            throw new IllegalArgumentException(errorMsg);
        }
        int noOfStud = studentIdList.length;
        double[] gpaList = new double[noOfStud];
        for(int i=0;i<noOfStud;i++){
            int noOfCourses = studentsGrades[i].length;
            double gpa = 0;
            for(char grade:studentsGrades[i]){
                try{ gpa+=gradePoints(grade,studentIdList[i]); }
                catch(MissingGradeException e){ throw e; }
            }
            gpaList[i] = gpa/noOfCourses;
        }
        return gpaList;
    }

    public static int gradePoints(char grade,int studentId) throws MissingGradeException{
        if(grade=='A') return 4;
        else if(grade=='B') return 3;
        else if(grade=='C') return 2;
        else if(grade==' '){
            throw new MissingGradeException(studentId);
        }
        return 0;
    }

    /**
     * Returns all students whose GPA lies in given range
     *
     * @param higher the upper bound on GPA range
     * @param lower the lower bound on GPA range
     * @param studentIdList The integer array containing students id's
     * @param studentGrades The character 2d array containing grades of every student in his/her course
     *
     * @return integer array containing id of all students in given GPA range
     * */
    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentGrades){
        if (lower < 0 || higher < 0 || lower > higher) {
            int[] output = {};
            return output;
        }
        double[] gpas;
        try{
            gpas = calculateGPA(studentIdList,studentGrades);
        }
        catch(MissingGradeException e){
            String errorMsg = "Missing Grade of student "+e.getStudentId();
            throw new InvalidDataException(errorMsg,e);
        }

        int noOfStud = studentIdList.length;
        int count = 0;
        for(int i=0;i<noOfStud;i++) {
            if (gpas[i] >= lower && gpas[i] <= higher) {
                count++;
            }
        }
        int[] output = new int[count];
        int j=0;
        for(int i=0;i<noOfStud;i++){
            if (gpas[i] >= lower && gpas[i] <= higher) {
                output[j] = studentIdList[i];
                j++;
            }
        }
        return output;
    }

    public static void main(String[] args){
        double lower = 3.2;
        double higher = 3.5;
        int[] studentIdList = {1001, 1002};
        char[][] studentsGrades = { { 'A', 'A', 'A', ' ' }, { 'A', 'B', 'B' } };

        try{
            int[] studentBetweenLowerHigher = getStudentsByGPA(lower,higher,studentIdList,studentsGrades);
            String str2 = "Students whose GPA lies between "+ lower +" and "+ higher +" are: " + Arrays.toString(studentBetweenLowerHigher);
            log.logInfo(str2);
        }
        catch(InvalidDataException e){
            String errorMsg = e.getCause() + " " + e.getMessage();
            log.logError(errorMsg);
        }

    }
}

