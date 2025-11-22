import java.util.Scanner;

class InvalidMarksException extends Exception {
    public InvalidMarksException(String msg) {
        super(msg);
    }
}

class Student {

    int roll;
    String name;
    int[] marks = new int[3];

    public Student(int roll, String name, int[] marks) {
        this.roll = roll;
        this.name = name;
        this.marks = marks;
    }

    public void checkMarks() throws InvalidMarksException {
        for (int i = 0; i < 3; i++) {
            if (marks[i] < 0 || marks[i] > 100) {
                throw new InvalidMarksException("Marks out of range for subject " + (i + 1));
            }
        }
    }

    public double average() {
        int sum = 0;
        for (int m : marks) sum += m;
        return sum / 3.0;
    }

    public void show() {
        System.out.println("Roll Number: " + roll);
        System.out.println("Name: " + name);

        System.out.print("Marks: ");
        for (int m : marks) System.out.print(m + " ");
        System.out.println();

        double avg = average();
        System.out.println("Average: " + avg);

        if (avg >= 40)
            System.out.println("Result: Pass");
        else
            System.out.println("Result: Fail");
    }
}

public class Exception_handling {

    Student[] list = new Student[50];
    int size = 0;

    Scanner sc = new Scanner(System.in);

    public void addStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int r = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String n = sc.nextLine();

            int[] m = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                m[i] = sc.nextInt();
            }

            Student s = new Student(r, n, m);
            s.checkMarks();

            list[size] = s;
            size++;

            System.out.println("Student added successfully.");

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
            sc.nextLine();
        } finally {
            System.out.println("Returning to menu...");
        }
    }

    public void searchStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int r = sc.nextInt();

            for (int i = 0; i < size; i++) {
                if (list[i].roll == r) {
                    list[i].show();
                    return;
                }
            }

            System.out.println("Student not found.");

        } catch (Exception e) {
            System.out.println("Invalid input.");
            sc.nextLine();
        }
    }

    public void menu() {
        while (true) {
            System.out.println("==== Student Result Menu ====");
            System.out.println("1. Add Student");
            System.out.println("2. Show Student Details");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int c = 0;
            try {
                c = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Enter valid choice.");
                sc.nextLine();
                continue;
            }

            if (c == 1) addStudent();
            else if (c == 2) searchStudent();
            else if (c == 3) {
                System.out.println("Goodbye.");
                sc.close();
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    public static void main(String[] args) {
        new Exception_handling().menu();
    }
}
