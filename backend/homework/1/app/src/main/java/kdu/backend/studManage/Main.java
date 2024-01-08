package kdu.backend.studManage;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        StudentRepository sr = new StudentRepository();

        System.out.println("Choose Options: ");
        System.out.println("1. Add Students");
        System.out.println("2. Update Students");
        System.out.println("3. Retrieve Student by Id");
        System.out.println("4. Retrieve Student by Name");
//        System.out.println("5. Exit");

        int op = 1;

        while(op==1){

            int choice = sc.nextInt();
            switch(choice) {
                case 1:
                    option1(sr);
                    break;
                case 2:
                    option2(sr);
                    break;
                case 3:
                    option3(sr);
                    break;
                case 4:
                    option4(sr);
                    break;
                case 5:
                    op = 0;
                    break;
                default: break;
            }
        }
    }

    public static void option1(StudentRepository sr){

        Student st = new Student();
        System.out.println("Enter the id: ");
        st.setId(sc.nextInt());
        System.out.println("Enter the name: ");
        st.setName(sc.next());
        System.out.println("Enter the age: ");
        st.setAge(sc.nextInt());
        System.out.println("Enter the grade: ");
        st.setGrade(sc.next().charAt(0));
        sr.addStudent(st);
    }

    public static void option2(StudentRepository sr) {
        System.out.println("Enter the id of student to update: ");
        int id = sc.nextInt();
        Student st = new Student();
        st.setId(id);
        System.out.println("Enter the new name: ");
        st.setName(sc.next());
        System.out.println("Enter the new age: ");
        st.setAge(sc.nextInt());
        System.out.println("Enter the new grade: ");
        st.setGrade(sc.next().charAt(0));
        sr.updateStudent(st,id);
    }

    public static void option3(StudentRepository sr){
        System.out.println("Enter the id of student to be retrieved: ");
        int id = sc.nextInt();
        Student st = sr.getStudent(id);
        sr.printStudent(st);
    }

    public static void option4(StudentRepository sr){
        System.out.println("Enter the name of student to be retrieved: ");
        String name = sc.next().toLowerCase();
        Student st = sr.getStudent(name);
        sr.printStudent(st);
    }

}