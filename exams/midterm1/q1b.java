import java.util.ArrayList;

class A {
  private ArrayList<Integer> intArray;

  A(int n) {
    intArray = new ArrayList<>();
    intArray.add(n);
  }

  A(ArrayList<Integer> intArray) {
    this.intArray = intArray;
  }

  public A next(int n) {
    ArrayList<Integer> intArrayCopy = new ArrayList<>(intArray);

    intArrayCopy.add(n);

    return new A(intArrayCopy);
  }

  @Override
  public String toString() {
    String s = "";

    for (int n : intArray) {
      s += String.format("[A:%d]", n);
    }

    return s;
  }
}