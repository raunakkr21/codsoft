
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Enter Roll Number: ");
                    String roll = sc.nextLine().trim();
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine().trim();

                    if (name.isEmpty() || roll.isEmpty() || grade.isEmpty()) {
                        System.out.println(" All fields are required!");
                        break;
                    }

                    sms.addStudent(new Student(name, roll, grade));
                    break;

                case "2":
                    System.out.print("Enter Roll Number to Edit: ");
                    String rollToEdit = sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine().trim();
                    System.out.print("Enter New Grade: ");
                    String newGrade = sc.nextLine().trim();

                    sms.editStudent(rollToEdit, newName, newGrade);
                    break;

                case "3":
                    System.out.print("Enter Roll Number to Remove: ");
                    String rollToRemove = sc.nextLine();
                    sms.removeStudent(rollToRemove);
                    break;

                case "4":
                    System.out.print("Enter Roll Number to Search: ");
                    String rollToSearch = sc.nextLine();
                    Student found = sms.searchStudent(rollToSearch);
                    System.out.println(found != null ? found : " Student not found.");
                    break;

                case "5":
                    sms.displayAllStudents();
                    break;

                case "6":
                    System.out.println(" Exiting... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println(" Invalid choice.");
            }
        }
    }
}
