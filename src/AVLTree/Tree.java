package AVLTree;

import java.io.OutputStreamWriter;
import static java.lang.StrictMath.max;
import java.util.ArrayList;

public class Tree {

    private static final int UNBALANCED_RIGHT = 1;
    private static final int UNBALANCED_LEFT = -1;
    private int nodeCount;
    private Node root;

    public Tree() {
    }

    public Tree(Node root) {
        this.root = root;
    }

    public Node addNode(Node node, Node newNode) {

        if (root == null) {
            root = newNode;
            return newNode;
        }

        /* 1.  Perform the normal BST rotation */
        if (node == null) {
            return newNode;
        }

        if (newNode.getReference() < node.getReference()) {
            node.setLeftNode(addNode(node.getLeftNode(), newNode));
            node.getLeftNode().setParentNode(node);
        } else {
            node.setRightNode(addNode(node.getRightNode(), newNode));
            node.getRightNode().setParentNode(node);
        }

        /* 2. Update height of this ancestor node */
        refreshNodeHeight(node);
        /* 3. Get the balance factor of this ancestor node to check whether
         this node became unbalanced */
        int balance = checkBalance(node);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && newNode.getReference() < node.getLeftNode().getReference())
            return rotateNodeRight(node);

        // Right Right Case
        if (balance < -1 && newNode.getReference() > node.getRightNode().getReference())
            return rotateNodeLeft(node);

        // Left Right Case
        if (balance > 1 && newNode.getReference() > node.getLeftNode().getReference()) {
            node.setLeftNode(rotateNodeLeft(node.getLeftNode()));
            return rotateNodeRight(node);
        }

        // Right Left Case
        if (balance < -1 && newNode.getReference() < node.getRightNode().getReference()) {
            node.setRightNode(rotateNodeRight(node.getRightNode()));
            return rotateNodeLeft(node);
        }


        /* return the (unchanged) node  */
        return node;
    }

    public void refreshNodeHeight(Node node) {
        int childCount = node.getChildCount();
        if (childCount == 0)
            node.setHeight(1);
        if (childCount == 1)
            node.setHeight(node.getOnlyChild().getHeight() + 1);
        if (childCount == 2) {
            int maxHeight = max(node.getLeftNode().getHeight(), node.getRightNode().getHeight());
            node.setHeight(maxHeight + 1);
        }
    }

    private ArrayList<Node> getXandY(Node node) {
        Node temp = node;
        ArrayList<Node> arr = new ArrayList<>();
        int tempBalance = 0;
        for (int i = 0; i < 3; i++) {
            arr.add(temp);
            tempBalance = checkBalance(temp);
            if (tempBalance == UNBALANCED_LEFT) {
                temp = temp.getLeftNode();
            } else temp = temp.getRightNode();

        }
        return arr;
    }

    public int rotationCase(ArrayList<Node> arr, int balance) {
        Node x = (Node) arr.get(0), y = (Node) arr.get(1);
        if (balance == UNBALANCED_LEFT) {
            if (y == x.getLeftNode())
                return 1;

            if (y == x.getRightNode())
                return 2;
        }
        if (balance == UNBALANCED_RIGHT) {
            if (y == x.getRightNode())
                return 1;
            if (y == x.getLeftNode())
                return 2;
        }

        return 0;
    }

    private Node rotateNodeLeft(Node x) {
        Node y = x.getRightNode();
        Node a = x.getLeftNode(), b = y.getLeftNode(), c = y.getRightNode();
        Node masterParent = x.getParentNode();

        if (x == root)
            root = y;
//        else {
//            if (x.isLeftNode()) {
//                masterParent.setLeftNode(y);
//            } else
//                masterParent.setRightNode(y);
//        }

        y.setParentNode(x.getParentNode());
        y.setLeftNode(x);
        x.setParentNode(y);

        x.setLeftNode(a);
        x.setRightNode(b);
        if (a != null)
            a.setParentNode(x);
        if (b != null)
            b.setParentNode(x);

        y.setRightNode(c);
        if (c != null)
            c.setParentNode(y);

        refreshNodeHeight(x);
        refreshNodeHeight(y);

        return y;
    }

    private Node rotateNodeRight(Node y) {
        Node x = y.getLeftNode();
        Node a = x.getLeftNode(), b = x.getRightNode(), c = y.getRightNode();
        Node masterParent = y.getParentNode();
        if (y == root)
            root = x;
//        else {
//            if (masterParent.getLeftNode() == y) {
//                masterParent.setLeftNode(x);
//            } else
//                masterParent.setRightNode(x);
//        }

        x.setParentNode(y.getParentNode());
        x.setRightNode(y);
        y.setParentNode(x);

        y.setLeftNode(b);
        y.setRightNode(c);
        if (b != null)
            b.setParentNode(y);
        if (c != null)
            c.setParentNode(y);

        x.setLeftNode(a);
        if (a != null)
            a.setParentNode(x);

        refreshNodeHeight(y);
        refreshNodeHeight(x);

        return x;
    }

    private int checkBalance(Node node) {
        int leftHeight, rightHeight;
        if (node == null)
            return 0;

        if (node.getLeftNode() == null)
            leftHeight = 0;
        else
            leftHeight = node.getLeftNode().getHeight();

        if (node.getRightNode() == null)
            rightHeight = 0;
        else
            rightHeight = node.getRightNode().getHeight();

        return leftHeight - rightHeight;
//        int balance = leftHeight - rightHeight;
//        if (balance > 1)
//            return -1;
//        else if (balance < -1)
//            return 1;
//        else
//            return 0;
    }

    private void replaceNode(Node node, Node toSwap) {

        if (node == toSwap) {
            System.out.println("Cannot replace node");
            return;
        }

        if (node.isLeftNode()) {
            node.getParentNode().setLeftNode(toSwap);
        } else // if (node.isRightNode())// {
            node.getParentNode().setRightNode(toSwap);
//        }

        if (toSwap != null) {
            toSwap.setParentNode(node.getParentNode());
        }
//        node.setParentNode(null);
//        node.setLeftNode(null);
//        node.setRightNode(null);
    }

    public Node removeNode(Node rootNode, int reference) {

        // STEP 1: PERFORM STANDARD BST DELETE
        if (rootNode == null)
            return rootNode;

        // If the key to be deleted is smaller than the root's key,
        // then it lies in left subtree
        if (reference < rootNode.getReference())
            rootNode.setLeftNode(removeNode(rootNode.getLeftNode(), reference));

        // If the key to be deleted is greater than the root's key,
        // then it lies in right subtree
        else if (reference > rootNode.getReference())
            rootNode.setRightNode(removeNode(rootNode.getRightNode(), reference));

        // if key is same as root's key, then This is the node
        // to be deleted
        else {
            // node with only one child or no child
            int childCount = rootNode.getChildCount();
            if (childCount == 0 || childCount == 1) {

                Node temp = rootNode.getOnlyChild();
                // No child case

                if (temp == null) {
                    rootNode = null;
                } else { // One child case
                    rootNode.setNodeData(temp.getNodeData());// Copy the contents of the non-empty child
                    if (temp.isLeftNode()) {
                        rootNode.setLeftNode(null);
                    } else rootNode.setRightNode(null);
                }

            } else {
                // node with two children: Get the inorder successor (smallest
                // in the right subtree)

                Node temp = findLowestNode(rootNode.getRightNode());
                // Copy the inorder successor's data to this node
                rootNode.setNodeData(temp.getNodeData());
                // Delete the inorder successor
                rootNode.setRightNode(removeNode(rootNode.getRightNode(), temp.getReference()));
            }
        }

        // If the tree had only one node then return
        if (rootNode == null)
            return rootNode;
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        refreshNodeHeight(rootNode);

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = checkBalance(rootNode);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && checkBalance(rootNode.getLeftNode()) >= 0)
            return rotateNodeRight(rootNode);

        // Left Right Case
        if (balance > 1 && checkBalance(rootNode.getLeftNode()) < 0) {
            rootNode.setLeftNode(rotateNodeLeft(rootNode.getLeftNode()));
            return rotateNodeRight(rootNode);
        }

        // Right Right Case
        if (balance < -1 && checkBalance(rootNode.getRightNode()) <= 0)
            return rotateNodeLeft(rootNode);

        // Right Left Case
        if (balance < -1 && checkBalance(rootNode.getRightNode()) > 0) {
            rootNode.setRightNode(rotateNodeRight(rootNode.getRightNode()));
            return rotateNodeLeft(rootNode);
        }

        return rootNode;
    }

    public Node findHighestNode(Node node) {
        if (node.getRightNode() != null) {
            return findHighestNode(node.getRightNode());
        } else {
            return node;
        }
    }

    public Node findLowestNode(Node node) {
        if (node.getLeftNode() != null) {
            return findLowestNode(node.getLeftNode());
        } else {
            return node;
        }
    }

    public boolean itemExists(int reference) {
        return findNode(reference, root) != null;
    }

    public Node findNode(int reference, Node node) {
        if (root == null) {
            return null;
        }

        if (node.getReference() == reference)
            return node;
        if (node.getLeftNode() == null && node.getRightNode() == null)
            return null;
        if (node.getRightNode() != null) {
            if (reference > node.getReference()) {
                return findNode(reference, node.getRightNode());
            }
        }
        if (node.getLeftNode() != null) {
            if (reference < node.getReference()) {
                return findNode(reference, node.getLeftNode());
            }
        }
        return null;
    }

    public void printTreeNodes(Node node) {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        if (node.getLeftNode() != null) {
            printTreeNodes(node.getLeftNode());
        }
        System.out.print(node.getReference() + " ");
        if (node.getRightNode() != null) {
            printTreeNodes(node.getRightNode());
        }
    }

    public void printTreeStructure() {
        OutputStreamWriter output = new OutputStreamWriter(System.out);
        try {
            getRoot().printTree(output);
            output.flush();
            System.out.println("\n\n\n");
        } catch (Exception e) {
            System.out.println("Cannot print tree.");
        }
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public Node getRoot() {
        return root;
    }

    public void getNodesAsArrayList(ArrayList<Node> arr, Node node) {
        if (node != null) {
            arr.add(node);
            if (node.getLeftNode() != null || node.getRightNode() != null) {
                if (node.getRightNode() != null) {
                    getNodesAsArrayList(arr, node.getRightNode());
                }
                if (node.getLeftNode() != null) {
                    getNodesAsArrayList(arr, node.getLeftNode());
                }
            }
        } else {
        }

    }
}
