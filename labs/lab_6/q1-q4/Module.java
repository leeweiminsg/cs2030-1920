class Module extends KeyableMap<String, String, Assessment> {
    public Module(String code) {
        super(code);
    }

    @Override
    public Module put(Assessment assessment) {
        map.put(assessment.getKey(), assessment);

        return this;
    }
}
