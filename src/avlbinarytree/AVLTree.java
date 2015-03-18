package avlbinarytree;

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

    private int nodeCount;
    private AVLNode root;
    private int leftDepth = 0, rightDepth = 0;

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

        AVLNode temp = root;
        while (true) {

            if (newNode.getReference() > temp.getReference()) {
                if (temp.getRightNode() == null) {
                    temp.setRightNode(newNode);
                    temp.getRightNode().setParentNode(temp);
                    temp = temp.getRightNode();
                    nodeCount++;
                    break;
                } else temp = temp.getRightNode();
            } else {
                if (temp.getLeftNode() == null) {
                    temp.setLeftNode(newNode);
                    temp.getLeftNode().setParentNode(temp);
                    temp = temp.getLeftNode();
                    nodeCount++;
                    break;
                } else temp = temp.getLeftNode();
            }
        }

        calculateHeight(temp);
    }
    
    /**
     * Calculates the height of one side of the tree after a leaf node is added.
     * @param temp The leave node where the recursion begins.
     */
    private void calculateHeight(AVLNode temp) {
        int childCount = temp.getChildCount();
        switch (childCount) {
            case 0:
                temp.setHeight(0);
                break;
            case 1:
                temp.setHeight(temp.getOnlyChild().getHeight() + 1);
                break;
            case 2:
                temp.setHeight(max(temp.getLeftNode().getHeight(),temp.getRightNode().getHeight()) + 1);
                break;
        }
        
        if (temp==root) {
            // End of recursion.
        }else{
            calculateHeight(temp.getParentNode());
        }

    }

    /**
     * Remove a node from the tree. Allocates new parents and children
     * accordingly
     *
     * @param reference The unique reference for the node to be deleted.
     */
    public void removeNode(int reference) {
        AVLNode temp = findNode(reference, root);

        if (temp == null) {
            System.out.println("Node does not exist.");
            return;
        }

        /* Handle delete with the root.*/
        if (temp == root) {
            if (root.getLeftNode() != null) {
                temp = findHighestNode(root.getLeftNode());
            } else if (root.getRightNode() != null) {
                temp = findLowestNode(root.getRightNode());
            } else {
                root = null;
                return;
            }
            removeRootNode(temp);
            return;
        }

        int childCount = temp.getChildCount();

        /* When a node doesn't have any children*/
        if (childCount == 0) {
            if (temp.getParentNode().getReference() < temp.getReference()) {
                temp.getParentNode().setRightNode(null);
            } else {
                temp.getParentNode().setLeftNode(null);
            }
        }

        /* Only one child in node. */
        if (childCount == 1) {
            if (temp.getParentNode().getReference() < temp.getReference()) {
                temp.getParentNode().setRightNode(temp.getOnlyChild());
                temp.getParentNode().getRightNode().setParentNode(temp.getParentNode());
            } else {
                temp.getParentNode().setLeftNode(temp.getOnlyChild());
                temp.getParentNode().getLeftNode().setParentNode(temp.getParentNode());
            }
        }

        /* Two children in the node. */
        if (childCount == 2) {
            ArrayList<AVLNode> toDeleteChildren = temp.getChildren();
            if (temp.getReference() > temp.getParentNode().getReference())
                temp.getParentNode().setRightNode(null);
            else
                temp.getParentNode().setLeftNode(null);

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
