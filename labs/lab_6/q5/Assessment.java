import java.util.Optional;

class Assessment implements Keyable<String> {
    private final String name, grade;

    public Assessment(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getKey() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return String.format("{%s: %s}", name, grade);
    }
}
