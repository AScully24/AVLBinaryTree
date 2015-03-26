package AVLTree;

import java.io.OutputStreamWriter;
import static java.lang.StrictMath.max;
import java.util.ArrayList;

public class Tree {

    private int nodeCount;
    private Node root;

    public Tree() {
    }

    public Tree(Node root) {
        this.root = root;
    }

    /**
     * Used to increment the nodecount (not possible with the recursive method).
     *
     * @param node Node to begin search, usually root
     * @param newNode The new node to be added to the tree
     * @return The new node.
     */
    public Node addNode(Node node, Node newNode) {
        Node result = addNodeRecursion(node, newNode);

        if (result != null) {
            nodeCount++;
        }

        return result;
    }

    /**
     * Adds a new node to the AVL tree, and also re-balances the tree.
     *
     * @param node Node to begin search, usually root
     * @param newNode The new node to be added to the tree
     * @return The new node.
     */
    public Node addNodeRecursion(Node node, Node newNode) {

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
        nodeCount++;
        return node;
    }

    /**
     * Sets the height of a node depending on its children
     *
     * @param node The node to refresh.
     */
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

    /**
     * Rotates a node and its children right.
     *
     * @param x The node to be rotated
     * @return The new sub tree root
     */
    private Node rotateNodeLeft(Node x) {
        Node y = x.getRightNode();
        Node a = x.getLeftNode(), b = y.getLeftNode(), c = y.getRightNode();
        Node masterParent = x.getParentNode();

        if (x == root)
            root = y;

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

    /**
     * Rotates a node and its children right.
     *
     * @param y The node to be rotated
     * @return The new sub tree root
     */
    private Node rotateNodeRight(Node y) {
        Node x = y.getLeftNode();
        Node a = x.getLeftNode(), b = x.getRightNode(), c = y.getRightNode();
        Node masterParent = y.getParentNode();
        if (y == root)
            root = x;

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

    /**
     * Checks the children of a node to see if it is unbalanced.
     *
     * @param node The node to check the balances of
     * @return The balance of a node (negative if left, positive if right, 0 if
     * balanced)
     */
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
    }

    /**
     * Used to increment the nodecount (not possible with the recursive method).
     *
     * @param rootNode The node to start the search (Usually the root node)
     * @param reference The reference of the node to be deleted.
     * @return null
     */
    public Node removeNode(Node rootNode, int reference) {
        Node result = removeNodeRecursion(rootNode, reference);
        if (result == null) {
            nodeCount--;
        }
        return result;
    }

    /**
     * Removes a node from a tree, and re-balances the tree recursively.
     *
     * @param rootNode The node to start the search (Usually the root node)
     * @param reference The reference of the node to be deleted.
     * @return null
     */
    public Node removeNodeRecursion(Node rootNode, int reference) {
        if (root != null) {
            if (root.getReference() == reference) {
                root = null;
                return null;
            }
        }
        // STEP 1: PERFORM STANDARD BST DELETE
        if (rootNode == null)
            return rootNode;

        // If the reference to be deleted is smaller than the root's reference,
        // then it lies in left subtree
        else if (reference < rootNode.getReference())
            rootNode.setLeftNode(removeNode(rootNode.getLeftNode(), reference));

        // If the reference to be deleted is greater than the root's reference,
        // then it lies in right subtree
        else if (reference > rootNode.getReference())
            rootNode.setRightNode(removeNode(rootNode.getRightNode(), reference));

        // if reference is same as root's reference, then This is the node
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

    /**
     * Finds the highest node within a tree or subtree
     *
     * @param node The node to start the search in
     * @return The highest node in that tree.
     */
    public Node findHighestNode(Node node) {
        if (node.getRightNode() != null) {
            return findHighestNode(node.getRightNode());
        } else {
            return node;
        }
    }

    /**
     * Finds the lowest node within a tree or subtree
     *
     * @param node The node to start the search in
     * @return The lowest node in that tree.
     */
    public Node findLowestNode(Node node) {
        if (node.getLeftNode() != null) {
            return findLowestNode(node.getLeftNode());
        } else {
            return node;
        }
    }

    /**
     *
     * @param reference The reference to check
     * @return True if the node is found, false if it isn't.
     */
    public boolean nodeExists(int reference) {
        return findNode(reference, root) != null;
    }

    /**
     *
     * @param reference The reference of the desired node.
     * @param node The starting node to begin the search (usually the root)
     * @return The desired node, otherwise null
     */
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

    /**
     * Prints the node in left order.
     *
     * @param node The node to begin the print (Usually root)
     */
    protected void printTreeNodes(Node node) {
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

    /**
     * Prints the tree in a graphical format
     */
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

    /**
     *
     * @return Get the number of nodes within a tree.
     */
    public int getNodeCount() {
        return nodeCount;
    }

    /**
     *
     * @return The root node in the tree.
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Adds to arr every node in this tree.
     *
     * @param arr The ArrayList to put the tree in to.
     * @param node The node to start the search (Usually the root)
     */
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
