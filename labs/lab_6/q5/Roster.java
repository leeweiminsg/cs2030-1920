import java.util.Map;
import java.util.Optional;

class Roster extends KeyableMap<String, String, Student> {
    public Roster(String year) {
        super(year);
    }

    String getGrade(String studentId, String moduleCode, String assessmentName) throws NoSuchRecordException {

        return get(studentId).flatMap(student -> student.get(moduleCode)).flatMap(module -> module.get(assessmentName))
                .map(assessment -> assessment.getGrade())
                .orElseThrow(() -> new NoSuchRecordException(studentId, moduleCode, assessmentName));
    }

    @Override
    public Roster put(Student student) {
        map.put(student.getKey(), student);

        return this;
    }
}