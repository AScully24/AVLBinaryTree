package avlbinarytree;

import java.io.OutputStreamWriter;
import static java.lang.StrictMath.max;
import java.util.ArrayList;

/**
 * Contains a lists of AVLNodes that can tree traversed, removed, added to, and
 * viewed. Keeps the tree balanced by checking the height of the left and right
 * side of the tree.
 *
 * @author Anthony Scully
 */
public class AVLTree {
    /* {src_lang=Java}*/

    private final static int UNBALANCED_RIGHT = 1;
    private final static int UNBALANCED_LEFT = -1;
    private final static int BALANCED = 0;
    private int nodeCount;
    private AVLNode root;

    /**
     * Default Constructor. Sets the root to null.
     */
    public AVLTree() {
    }

    /**
     *
     * @param root Sets the root to the node that is passed when creating the
     * tree.
     */
    public AVLTree(AVLNode root) {
        this.root = root;
    }

    /**
     * Add a new node to the tree.
     *
     * @param newNode Node to be added to the tree. Must contain a unique
     * reference.
     */
    public void addNode(AVLNode newNode) {

        if (root == null) {
            root = newNode;
            nodeCount++;
            return;
        }

        if (findNode(newNode.getReference(), root) != null) {
            return;
        }

        AVLNode node = root;
        while (true) {
            if (newNode.getReference() > node.getReference()) {
                if (node.getRightNode() == null) {
                    node.setRightNode(newNode);
                    node.getRightNode().setParentNode(node);
                    node = node.getRightNode();
                    nodeCount++;
                    break;
                } else node = node.getRightNode();
            } else {
                if (node.getLeftNode() == null) {
                    node.setLeftNode(newNode);
                    node.getLeftNode().setParentNode(node);
                    node = node.getLeftNode();
                    nodeCount++;
                    break;
                } else node = node.getLeftNode();
            }
        }

        rebalanceTree(node);
    }

    /**
     * Refreshes a single nodes height,
     *
     * @param node The node to refresh.
     */
    public void refreshNodeHeight(AVLNode node) {
        int childCount = node.getChildCount();

        if (childCount == 0) node.setHeight(0);

        if (childCount == 1)
            node.setHeight(node.getOnlyChild().getHeight() + 1);

        if (childCount == 2) {
            int maxHeight = max(node.getLeftNode().getHeight(), node.getRightNode().getHeight());
            node.setHeight(maxHeight + 1);
        }
    }

    /**
     * Recursively goes through the tree to refresh the height of the nodes
     * above a newly added node. Stops when the root is reached or the height
     * changes stop affecting the parent node. Also balances tree.
     *
     * @param node The node where the refresh begins.
     */
    private void rebalanceTree(AVLNode node) {

        AVLNode leafNode = node;

        if (node.getReference() == 12810) {
            node = node;
        }

        while (node != null) {
            refreshNodeHeight(node);
            int balance = balanceHandler(node, leafNode);
            node = node.getParentNode();
            OutputStreamWriter output = new OutputStreamWriter(System.out);
            try {
                getRoot().printTree(output);
                output.flush();
                System.out.println("\n\n\n");
            } catch (Exception e) {
            }
        }
    }

    public int balanceHandler(AVLNode node, AVLNode leafNode) {
        int balance = checkBalance(node);
        int rotationCase = 0;
        int rotationCount = 0;

//        if (balance != 0) {
//        rotationCase = rotationCase(node, balance);
//
//        switch (rotationCase) {
//            case 0:
//                break;
//            case 1: // Left Left
//                rotateNodeLeft(node);
//                break;
//            case 2: // Left Right
//                rotateNodeLeft(node.getLeftNode());
//                rotateNodeRight(node);
//                break;
//            case 3: // Right Right
//                rotateNodeRight(node);
//                break;
//            case 4: // Right Left
//                rotateNodeRight(node.getRightNode());
//                rotateNodeLeft(node);
//
//                break;
//        }
        rotationCount = rotationCase(node, balance, leafNode);
        if (balance == UNBALANCED_RIGHT) {
            if (rotationCount == 1) {
                rotateNodeLeft(node);
            } else if (rotationCount == 2) {
                rotateNodeRight(node.getRightNode());
                rotateNodeLeft(node);
            }
        } else if (balance == UNBALANCED_LEFT) {
            if (rotationCount == 1) {
                rotateNodeRight(node);
            } else if (rotationCount == 2) {
                rotateNodeLeft(node.getLeftNode());
                rotateNodeRight(node);
            }
        }

        return rotationCount;

//        if (balance != 0) {
//            int parentBalance = checkBalance(node.getParentNode());
//            if (parentBalance == UNBALANCED_RIGHT) {
//                rotateNodeLeft(node);
//            }else if (parentBalance == UNBALANCED_LEFT) {
//                rotateNodeRight(node);
//            }
//            
//        }
    }

    /**
     * Calculates the number of rotations required to fix the tree traversal and
     * balance.
     *
     * @param node The Node to be rotated around.
     * @param balance Is the tree unbalanced left or right (See checkBalance
     * method)
     * @param leafNode
     * @return Number of rotations required to fix a tree.
     */
    public int rotationCase(AVLNode node, int balance, AVLNode leafNode) {
        AVLNode leftNode = node.getLeftNode();
        AVLNode rightNode = node.getRightNode();

        if (balance == UNBALANCED_LEFT) {
            if (leftNode != null) {
                if (leftNode.getLeftNode() == leafNode.getParentNode() || leftNode.getLeftNode() == leafNode) {
                    return 1;
                } else return 2;
            } else return 1;
        }
        
        if (balance == UNBALANCED_RIGHT) {
            if (rightNode != null) {
                if (rightNode.getRightNode() == leafNode.getParentNode() || rightNode.getRightNode() == leafNode) {
                    return 1;
                } else return 2;
            }else return 1;
        }
        return 0;
    }

    public void rotateNodeLeft(AVLNode node) {
        AVLNode x = node, y = node.getRightNode();
        AVLNode a = x.getLeftNode(), b = y.getLeftNode(), c = y.getRightNode();
        AVLNode masterParent = x.getParentNode();

        // Swaps the parent of the rotating node
        if (x == root) root = y;
        else {
            if (masterParent.getLeftNode() == x) {
                masterParent.setLeftNode(y);
            } else masterParent.setRightNode(y);
        }

        // x becomes y's left child.
        y.setParentNode(x.getParentNode());
        y.setLeftNode(x);
        x.setParentNode(y);

        //AB comes child of x
        x.setLeftNode(a);
        x.setRightNode(b);
        if (a != null) a.setParentNode(x);
        if (b != null) b.setParentNode(x);

        // C becomes the right child of y
        y.setRightNode(c);
        if (c != null) c.setParentNode(y);

        refreshNodeHeight(x);
        refreshNodeHeight(y);

        //refreshHeights(node);
    }

    public void rotateNodeRight(AVLNode node) {
        AVLNode x = node.getLeftNode(), y = node;
        AVLNode a = x.getLeftNode(), b = x.getRightNode(), c = y.getRightNode();
        AVLNode masterParent = y.getParentNode();

        // Swaps the parent of the rotating node
        if (y == root) root = x;
        else {
            if (masterParent.getLeftNode() == y) {
                masterParent.setLeftNode(x);
            } else masterParent.setRightNode(x);
        }

        // y becomes x's left child.
        x.setParentNode(y.getParentNode());
        x.setRightNode(y);
        y.setParentNode(x);

        //BC comes child of y
        y.setLeftNode(b);
        y.setRightNode(c);
        if (b != null) b.setParentNode(y);
        if (c != null) c.setParentNode(y);

        // A becomes the left child of x
        x.setLeftNode(a);
        if (a != null) a.setParentNode(x);

        refreshNodeHeight(y);
        refreshNodeHeight(x);

        //refreshHeights(node);
    }

    /**
     * Checks the children of a node to see if there is an AVL imbalance
     *
     * @param node The node containing the children to check.
     * @return 1 for right imbalance, -1 for left imbalance, 0 for balanced
     * tree.
     */
    public int checkBalance(AVLNode node) {
        int leftHeight, rightHeight;

        if (node == null) return 0;

        if (node.getLeftNode() == null) leftHeight = -1;
        else leftHeight = node.getLeftNode().getHeight();

        if (node.getRightNode() == null) rightHeight = -1;
        else rightHeight = node.getRightNode().getHeight();

        int balance = leftHeight - rightHeight;
        //int balance = leftHeight - rightHeight;

//        if (Math.abs(balance) == 1) return 0;
//        else return rightHeight - leftHeight;
        //System.out.println("Ref " + node.getReference() + " is balance " + balance);
        if (balance > 1) return -1; // Left unbalance
        else if (balance < -1) return 1; // Right unbalance
        else return 0; // Even
    }

    /**
     * Remove a node from the tree. Allocates new parents and children
     * accordingly
     *
     * @param reference The unique reference for the node to be deleted.
     */
    public void removeNode(int reference) {
        AVLNode node = findNode(reference, root);

        if (node == null) {
            System.out.println("Node does not exist.");
            return;
        }

        /* Handle delete with the root.*/
        if (node == root) {
            if (root.getLeftNode() != null) {
                node = findHighestNode(root.getLeftNode());
            } else if (root.getRightNode() != null) {
                node = findLowestNode(root.getRightNode());
            } else {
                root = null;
                return;
            }
            removeRootNode(node);
            return;
        }

        int childCount = node.getChildCount();

        /* When a node doesn't have any children*/
        if (childCount == 0) {
            if (node.getParentNode().getReference() < node.getReference()) {
                node.getParentNode().setRightNode(null);
            } else {
                node.getParentNode().setLeftNode(null);
            }
        }

        /* Only one child in node. */
        if (childCount == 1) {
            if (node.getParentNode().getReference() < node.getReference()) {
                node.getParentNode().setRightNode(node.getOnlyChild());
                node.getParentNode().getRightNode().setParentNode(node.getParentNode());
            } else {
                node.getParentNode().setLeftNode(node.getOnlyChild());
                node.getParentNode().getLeftNode().setParentNode(node.getParentNode());
            }
        }

        /* Two children in the node. */
        if (childCount == 2) {
            ArrayList<AVLNode> toDeleteChildren = node.getChildren();
            if (node.getReference() > node.getParentNode().getReference())
                node.getParentNode().setRightNode(null);
            else
                node.getParentNode().setLeftNode(null);

            for (AVLNode n : toDeleteChildren) {
                if (n != null) {
                    addNode(n);
                    nodeCount--;
                }
            }
        }
        nodeCount--;
    }

    /**
     * Swaps the location of the root node and another node within the tree.
     *
     * @param toSwap Second node to be swapped.
     */
    protected void removeRootNode(AVLNode toSwap) {
        root.setReference(toSwap.getReference());
        root.setDescription(toSwap.getDescription());
        root.setPrice(toSwap.getPrice());

        int childCount = toSwap.getChildCount();

        if (childCount == 0) {
            System.out.println("toSwap is null");
            toSwap = null;
        } else if (childCount == 1) {
            System.out.println("toSwap becomes child");
            toSwap.setReference(toSwap.getOnlyChild().getReference());
            toSwap.setDescription(toSwap.getOnlyChild().getDescription());
            toSwap.setPrice(toSwap.getOnlyChild().getPrice());
            toSwap.setChildren(toSwap.getOnlyChild().getChildren());
        }

    }

    public void swapNodes(AVLNode first, AVLNode second) {
        AVLNode node = new AVLNode(0, null, 0);

        node.setData(first.getData());
        first.setData(second.getData());
        second.setData(node.getData());
    }

    /**
     * Recursively searches the tree for the highest value node (via reference).
     *
     * @param node The start node for where the search starts.
     * @return The highest node that is equal to or below the start node.
     */
    protected AVLNode findHighestNode(AVLNode node) {
        if (node.getRightNode() != null) {
            return findHighestNode(node.getRightNode());
        } else {
            return node;
        }
    }

    /**
     * Recursively searches the tree for the lowest value node (via reference).
     *
     * @param node The start node for where the search starts.
     * @return The lowest node that is equal to or below the start node.
     */
    protected AVLNode findLowestNode(AVLNode node) {
        if (node.getLeftNode() != null) {
            return findLowestNode(node.getLeftNode());
        } else {
            return node;
        }
    }

    /**
     * Recursively searches to find a node based on a unique reference number.
     *
     * @param reference The unique reference number for the desired node.
     * @param node Starting node. This should be the root of the tree.
     * @return The AVLNode with the matching reference number. Returns null if
     * not found.
     */
    public AVLNode findNode(int reference, AVLNode node) {

        /* Reference found */
        if (node.getReference() == reference)
            return node;

        /* If we cannot go further down the tree, return null */
        if (node.getLeftNode() == null && node.getRightNode() == null)
            return null;

        /* Choose which path down the tree to go - Right node checked first */
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
     *
     * Recursively prints the tree first based on left nodes first.
     *
     * If the root isn't chosen as the start point, the left side of the tree is
     * not printed.
     *
     * @param node Starting node for printing.
     */
    public void printTree(AVLNode node) {

        /* Recursive Method */
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        if (node.getLeftNode() != null) {
            printTree(node.getLeftNode());
        }
        System.out.print(node.getReference() + " ");

        if (node.getRightNode() != null) {
            printTree(node.getRightNode());
        }
    }

    /**
     *
     * @return Total number of nodes in a tree.
     */
    public int getNodeCount() {
        return nodeCount;
    }

    /**
     *
     * @return First node within the tree.
     */
    public AVLNode getRoot() {
        return root;
    }
}
