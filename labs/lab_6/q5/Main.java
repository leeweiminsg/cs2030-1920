import java.util.Scanner;
import java.util.Arrays;
import java.util.Optional;

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

            Assessment assessment = new Assessment(assessmentName, grade);
            Module module = new Module(moduleName);
            Student student = new Student(studentName);

            roster.get(studentName).ifPresentOrElse(
                    s -> s.get(moduleName).ifPresentOrElse(m -> m.put(assessment),
                            () -> roster.get(studentName).map(ss -> ss.put(module.put(assessment)))),
                    () -> roster.put(student.put(module.put(assessment))));
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