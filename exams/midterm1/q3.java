import java.util.ArrayList;

class Cluster {
  private final String name;
  private final ArrayList<Case> cases;
}

abstract class Case {
  private final int id;

  private enum contactNature {
    CASUAL, CLOSE, FAMILY;
  }

  private final ArrayList<Case> contacts;
}

class localCase extends Case {
}

class importedCase extends Case {
  private final String country;
}