class Student extends KeyableMap<String, String, Module> {
    public Student(String id) {
        super(id);
    }

    @Override
    public Student put(Module module) {
        map.put(module.getKey(), module);

        return this;
    }
}