public class Traversal {

    public static void order(Node root){
        if (root == null){
            return;
        }
        System.out.println(root.d);
        order(root.left);
        order(root.right);
    }

    public static int max(Node root){
        if (root == null){
            return -1000000;
        }
        int max = root.d;
        int leftMax = max(root.left);
        int rightMax = max(root.right);

        if (leftMax > max){
            max = leftMax;
        }

        if (rightMax > max){
            max = rightMax;
        }

        return max;

    }
}
