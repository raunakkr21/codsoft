import java.util.Scanner;

public class StudentGradeCalculator {

    private static final int MAX_MARKS_PER_SUBJECT = 100;
    private static final int MIN_MARKS_PER_SUBJECT = 0;
    private static final int MIN_SUBJECTS = 1;
    private static final int MAX_SUBJECTS = 20;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n Welcome to Student Grade Calculator 🎓");
        System.out.println("----------------------------------------");

        int numSubjects = getNumberOfSubjects(scanner);
        int[] marks = getSubjectMarks(scanner, numSubjects);
        displayResults(marks);

        scanner.close();
    }

    private static int getNumberOfSubjects(Scanner scanner) {
        System.out.print(" Enter number of subjects (1-20): ");
        while (!scanner.hasNextInt()) {
            System.out.print(" Please enter a valid number between 1 and 20: ");
            scanner.next();
        }
        int num = scanner.nextInt();

        while (num < MIN_SUBJECTS || num > MAX_SUBJECTS) {
            System.out.print(" Number of subjects must be between 1 and 20. Please re-enter: ");
            num = scanner.nextInt();
        }
        return num;
    }

    private static int[] getSubjectMarks(Scanner scanner, int numSubjects) {
        int[] marks = new int[numSubjects];
        System.out.println("\nEnter marks for each subject (out of 100):");

        for (int i = 0; i < numSubjects; i++) {
            boolean validInput = false;
            while (!validInput) {
                System.out.printf(" Subject %d marks: ", i + 1);
                if (scanner.hasNextInt()) {
                    marks[i] = scanner.nextInt();
                    if (marks[i] >= MIN_MARKS_PER_SUBJECT && marks[i] <= MAX_MARKS_PER_SUBJECT) {
                        validInput = true;
                    } else {
                        System.out.printf(" Marks must be between %d and %d. Please try again.\n",
                                MIN_MARKS_PER_SUBJECT, MAX_MARKS_PER_SUBJECT);
                    }
                } else {
                    System.out.print(" Please enter a valid number: ");
                    scanner.next();
                }
            }
        }
        return marks;
    }

    private static void displayResults(int[] marks) {
        int totalMarks = calculateTotalMarks(marks);
        double averagePercentage = calculateAveragePercentage(totalMarks, marks.length);
        String grade = determineGrade(averagePercentage);

        System.out.println("\n\n Result Summary:");
        System.out.println("+--------------------------------+");
        System.out.printf("| %-15s | %14d |\n", "Total Subjects", marks.length);
        System.out.printf("| %-15s | %6d/%-6d |\n", "Total Marks", totalMarks,
                (marks.length * MAX_MARKS_PER_SUBJECT));
        System.out.printf("| %-15s | %14.2f%% |\n", "Average", averagePercentage);
        System.out.printf("| %-15s | %14s |\n", "Grade", grade);
        System.out.println("+--------------------------------+");

        System.out.println("\n Grade Breakdown:");
        System.out.println(" 90-100% : A+ (Outstanding)");
        System.out.println(" 80-89%  : A (Excellent)");
        System.out.println(" 70-79%  : B (Good)");
        System.out.println(" 60-69%  : C (Average)");
        System.out.println(" 50-59%  : D (Below Average)");
        System.out.println(" Below 50% : F (Fail)");

        System.out.println("\n Performance Remarks:");
        if (grade.startsWith("A+")) {
            System.out.println("  Outstanding performance! Keep up the excellent work!");
        } else if (grade.startsWith("A")) {
            System.out.println("  Excellent work! You're doing great!");
        } else if (grade.startsWith("B")) {
            System.out.println("  Good performance! With a little more effort, you can reach the top!");
        } else if (grade.startsWith("C")) {
            System.out.println("  Average performance. Consider spending more time on your studies.");
        } else if (grade.startsWith("D")) {
            System.out.println(" ⚠ Needs improvement. Please focus more on your studies.");
        } else {
            System.out.println("  Unfortunately you've failed. Don't give up! Seek help and try harder next time.");
        }
    }

    private static int calculateTotalMarks(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    private static double calculateAveragePercentage(int totalMarks, int numSubjects) {
        return (double) totalMarks / numSubjects;
    }

    private static String determineGrade(double averagePercentage) {
        if (averagePercentage >= 90)
            return "A+ (Outstanding)";
        if (averagePercentage >= 80)
            return "A (Excellent)";
        if (averagePercentage >= 70)
            return "B (Good)";
        if (averagePercentage >= 60)
            return "C (Average)";
        if (averagePercentage >= 50)
            return "D (Below Avg)";
        return "F (Fail)";
    }
}