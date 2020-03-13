import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int recordNum = sc.nextInt();

        Roster roster = new Roster("AY1920");

        for (int i = 0; i < recordNum; i++) {
            String studentName = sc.next();
            String moduleName = sc.next();
            String assessmentName = sc.next();
            String grade = sc.next();

            Student student = roster.get(studentName);

            if (student == null) {
                student = new Student(studentName);
                roster.put(student);
            }

            Module module = student.get(moduleName);

            if (module == null) {
                module = new Module(moduleName);
                student.put(module);
            }

            Assessment assessment = new Assessment(assessmentName, grade);
            module.put(assessment);
        }

        while (sc.hasNextLine()) {
            try {
                String studentName = sc.next();
                String moduleName = sc.next();
                String assessmentName = sc.next();

                System.out.println(roster.getGrade(studentName, moduleName, assessmentName));

            } catch (NoSuchRecordException e) {
                System.out.println("NoSuchRecordException: " + e.getMessage());
            } catch (Exception NoSuchElementException) {
                break;
            }
        }

        sc.close();
    }
}