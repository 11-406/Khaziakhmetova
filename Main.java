//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static  void main(String[] args) {
        Node r = new Node(1);
        r.left = new Node(2);
        r.right = new Node(3);
        r.left.left = new Node(4);
        r.left.right = new Node(5);
        Traversal.order(r);
        System.out.println("Максимальное число: " );
        int max = Traversal.max(r);
        System.out.println(max);

    }
}