
import java.util.*;
import java.io.*;

public class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = loadStudentsFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
        System.out.println(" Student added successfully.");
    }

    public void removeStudent(String rollNumber) {
        boolean removed = students.removeIf(s -> s.getRollNumber().equals(rollNumber));
        saveStudentsToFile();
        if (removed) {
            System.out.println(" Student removed.");
        } else {
            System.out.println(" Student not found.");
        }
    }

    public Student searchStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equals(rollNumber)) {
                return s;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public void editStudent(String rollNumber, String newName, String newGrade) {
        Student s = searchStudent(rollNumber);
        if (s != null) {
            s.setName(newName);
            s.setGrade(newGrade);
            saveStudentsToFile();
            System.out.println("✅ Student updated.");
        } else {
            System.out.println(" Student not found.");
        }
    }

    private void saveStudentsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println(" Error saving data.");
        }
    }

    private List<Student> loadStudentsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<?> list = (List<?>) obj;
                if (!list.isEmpty() && !(list.get(0) instanceof Student)) {
                    return new ArrayList<>();
                }
                @SuppressWarnings("unchecked")
                List<Student> studentsList = (List<Student>) list;
                return studentsList;
            } else {
                return new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
