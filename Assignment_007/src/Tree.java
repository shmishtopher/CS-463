public class Tree {
  public final int data;
  public final Tree leftChild;
  public final Tree rightChild;
  public final Character ch;

  private Tree(int d, Character c,  Tree left, Tree right) {
    data = d;
    leftChild = left;
    rightChild = right;
    ch = c;
  }

  static Tree leaf(char c, int f) {
    return new Tree(f, c, null, null);
  }

  static Tree combine(Tree a, Tree b) {
    return new Tree(a.data + b.data, null, a, b);
  }

  public boolean isLeaf() {
    return leftChild == null && rightChild == null;
  }
}
