class NoSuchRecordException extends Exception {
    private final String studentId, moduleCode, assessmentName;

    public NoSuchRecordException(String studentId, String moduleCode, String assessmentName) {
        this.studentId = studentId;
        this.moduleCode = moduleCode;
        this.assessmentName = assessmentName;
    }

    @Override
    public String getMessage() {
        return String.format("No such record: %s %s %s", studentId, moduleCode, assessmentName);
    }
}