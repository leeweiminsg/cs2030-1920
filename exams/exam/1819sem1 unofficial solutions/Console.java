class Console {
    private String id;
    private Logic logic;

    Console(String id, Logic logic) {
        this.id = id;
        this.logic = logic;
    }

    void start(Console secondaryConsole) {
        Scanner sc = new Scanner(System.in);
        String command;
        do {
            System.out.print("Enter the command: ");
            command = sc.next();
            String logicStr = logic.invoke(command);
            this.feedback(logicStr + " executed");
            secondaryConsole.feedback(logicStr + " executed");
        } while (!command.equals("exit"));
    }

    void feedback(String mesg) {
        System.out.println(id + ": " + mesg);
    }
}

class Logic {
    String invoke(String command) {
        // do something based on the command

        return command;
    }
}