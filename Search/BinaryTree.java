public class BinaryTree {
/*   50
   /   \
 30     70
 / \    / \
20 40  60 80 */
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        tree.inorder(); // print inorder traversal of the BST
    }

    class Node {
        int key;
        Node left, right;
        private Node(int item) {
            key = item;
            left = right = null;
        }
    }
    Node root;

    BinaryTree() { root = null; }

    void insert(int key) { root = insertRec(root, key); }
    // A recursive function to insert a new key in BST
    Node insertRec(Node root, int key) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(key);
            return root;
        }
        // Otherwise, recur down the tree
        if (key < root.key) root.left = insertRec(root.left, key);
        else if (key > root.key) root.right = insertRec(root.right, key);
        return root; // return the (unchanged) node pointer
    }

    void inorder()  { inorderRec(root); }
    // A utility function to do inorder traversal of BST
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }
}