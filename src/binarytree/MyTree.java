package binarytree;

import java.util.ArrayList;

/**
 * Contains a lists of MyNodes that can tree traversed, removed, added to, and
 * viewed.
 *
 * @author Anthony Scully
 */
public class MyTree {
    /* {src_lang=Java}*/

    private int nodeCount;
    private MyNode root;

    /**
     * Default Constructor. Sets the root to null.
     */
    public MyTree() {
    }

    /**
     *
     * @param root Sets the root to the node that is passed when creating the
     * tree.
     */
    public MyTree(MyNode root) {
        this.root = root;
    }

    /**
     * Add a new node to the tree.
     *
     * @param newNode Node to be added to the tree. Must contain a unique
     * reference.
     */
    public void addNode(MyNode newNode) {

        if (root == null) {
            root = newNode;
            nodeCount++;
            return;
        }

        MyNode temp = root;
        while (true) {

            if (newNode.getReference() > temp.getReference()) {
                if (temp.getRightNode() == null) {
                    temp.setRightNode(newNode);
                    temp.getRightNode().setParentNode(temp);
                    nodeCount++;
                    return;
                } else temp = temp.getRightNode();
            } else {
                if (temp.getLeftNode() == null) {
                    temp.setLeftNode(newNode);
                    temp.getLeftNode().setParentNode(temp);
                    nodeCount++;
                    return;
                } else temp = temp.getLeftNode();
            }
        }
    }

    /**
     * Remove a node from the tree. Allocates new parents and children
     * accordingly
     *
     * @param reference The unique reference for the node to be deleted.
     */
    public void removeNode(int reference) {
        MyNode temp = findNode(reference, root);

        if (temp == null) {
            System.out.println("Node does not exist.");
            return;
        }

        /* Handle delete with the root.*/
        if (temp == root) {
            if (temp.getLeftNode() != null) {

                MyNode searchTemp = temp.getLeftNode();
                while (searchTemp.getRightNode() != null) {
                    searchTemp = searchTemp.getRightNode();
                }
                if (searchTemp.getChildCount() > 0) {
                    // FROM HERE
                    searchTemp.getOnlyChild().setParentNode(searchTemp.getParentNode());
                    searchTemp.getParentNode().setRightNode(searchTemp.getOnlyChild());

                    searchTemp.setChildren(root.getChildren());
                    root = searchTemp;
                    searchTemp.getParentNode().setRightNode(null);
                    root.setParentNode(null);
                } else {
                    searchTemp.setChildren(root.getChildren());
                    root = searchTemp;
                    searchTemp.getParentNode().setRightNode(null);
                    root.setParentNode(null);
                }
                return;
            } else if (temp.getRightNode() != null) {
                MyNode searchTemp = temp.getRightNode();
                while (searchTemp.getLeftNode() != null) {
                    searchTemp = searchTemp.getLeftNode();
                }
                searchTemp.setChildren(root.getChildren());
                root = searchTemp;
                searchTemp.getParentNode().setRightNode(null);
                root.setParentNode(null);
                return;
            }
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
            ArrayList<MyNode> toDeleteChildren = temp.getChildren();
            if (temp.getReference() > temp.getParentNode().getReference())
                temp.getParentNode().setRightNode(null);
            else
                temp.getParentNode().setLeftNode(null);

            for (MyNode n : toDeleteChildren) {
                if (n != null) {
                    addNode(n);
                    nodeCount--;
                }
            }
        }
        nodeCount--;
    }

    /**
     * Recursively searches to find a node based on a unique reference number.
     *
     * @param reference The unique reference number for the desired node.
     * @param node Starting node. This should be the root of the tree.
     * @return The MyNode with the matching reference number. Returns null if
     * not found.
     */
    public MyNode findNode(int reference, MyNode node) {

        /* Reference found */
        if (node.getReference() == reference)
            return node;

        /* If we cannot go further down the tree, return null */
        if (node.getLeftNode() == null && node.getRightNode() == null)
            return null;

        /* Choose which path down the tree to go - Right node checked first */
        if (node.getRightNode() != null) {
            if (reference > node.getReference()) {
                findNode(reference, node.getRightNode());
            }
        }

        if (node.getLeftNode() != null) {
            if (reference < node.getReference()) {
                findNode(reference, node.getLeftNode());
            }
        }

        /* If either node does not complete the criteria, end the search */
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
    public void printTree(MyNode node) {

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
    public MyNode getRoot() {
        return root;
    }

}
