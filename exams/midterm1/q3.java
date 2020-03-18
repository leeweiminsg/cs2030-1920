import java.util.ArrayList;

class Cluster {
  private final String name;
  private final ArrayList<Case> cases;
}

class Case {
  private final int id;

  private enum contactNature {
    CASUAL, CLOSE, FAMILY;
  }

  private final ArrayList<Case> contacts;
}

class ImportedCase extends Case {
  private final String country;
}