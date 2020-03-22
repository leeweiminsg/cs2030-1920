import java.util.List;

class Cluster {
  private String name;
  private List<Case> cases;
}

class Case {
  private int id;
}

class ImportedCase extends Case {
  private String country;
}

enum contactNature {
  CASUAL, CLOSE, FAMILY;
}

class Contact {
  private Case case1, case2;
  private contactNature nature;
}