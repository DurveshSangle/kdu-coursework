package kdu.backend.hw2.q1;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StudentUtil {

    private static final Logger logger = LogManager.getLogger(StudentUtil.class);

    public static double[] calculateGPA(int[] studentIdList,char[][] studentsGrades){
        int noOfStud = studentIdList.length;
        double[] res = new double[noOfStud];
        for(int i=0;i<noOfStud;i++){
            int noOfCourses = studentsGrades[i].length;
            double gpa = 0;
            for(char grade:studentsGrades[i]){
                if(grade=='A') gpa+=4;
                else if(grade=='B') gpa+=3;
                else gpa+=2;
            }
            res[i] = gpa/noOfCourses;
        }
        return res;
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentGrades){
        double[] gpas = calculateGPA(studentIdList,studentGrades);
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
        char[][] studentsGrades = { { 'A', 'A', 'A', 'B' }, { 'A', 'B', 'B' } };

        double[] gpaOfStudents = calculateGPA(studentIdList,studentsGrades);

        logger.info("GPA's of students are: " + Arrays.toString(gpaOfStudents));

        int[] studentBetweenLowerHigher = getStudentsByGPA(lower,higher,studentIdList,studentsGrades);

        logger.info("Students whose GPA lies between "+ lower +" and "+ higher +" are: " + Arrays.toString(studentBetweenLowerHigher));
    }
}
