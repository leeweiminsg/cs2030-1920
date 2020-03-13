class Roster extends KeyableMap<String, String, Student> {
    public Roster(String year) {
        super(year);
    }

    String getGrade(String studentId, String moduleCode, String assessmentName) throws NoSuchRecordException {
        try {
            return get(studentId).get(moduleCode).get(assessmentName).getGrade();
        } catch (NullPointerException e) {
            throw new NoSuchRecordException(studentId, moduleCode, assessmentName);
        }
    }

    @Override
    public Roster put(Student student) {
        map.put(student.getKey(), student);

        return this;
    }
}