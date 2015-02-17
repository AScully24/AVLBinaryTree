package binarytree;

import java.util.ArrayList;
import java.util.Stack;

public class MyTree {
    /* {src_lang=Java}*/

    private int nodeCount;
    private MyNode root;

    public MyTree() {
    }

    public MyTree(MyNode root) {
        this.root = root;
    }

    /* Add a new node to the tree. */
    public void addNode(MyNode node) {

        if (root == null) {
            root = node;
            nodeCount++;
            return;
        }

        MyNode temp = root;
        while (true) {

            if (node.getReference() > temp.getReference()) {
                if (temp.getRightNode() == null) {
                    temp.setRightNode(node);
                    temp.getRightNode().setParentNode(temp);
                    nodeCount++;
                    return;
                } else temp = temp.getRightNode();
            } else {
                if (temp.getLeftNode() == null) {
                    temp.setLeftNode(node);
                    temp.getLeftNode().setParentNode(temp);
                    nodeCount++;
                    return;
                } else temp = temp.getLeftNode();
            }
        }
    }

    /* Remove a node from the tree. Allocates new parents and children accordingly*/
    public void removeNode(int reference) {
        MyNode temp = findNode(reference);

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
     * Searches the left side of the tree for the highest value.
     *
     * @param node.
     * @return Returns the highest value node.
     */
//    public MyNode findHighestValueNodeLeft(MyNode node) {
//        while (true) {
//            if (node.getLeftNode() == null) {
//                return node;
//            }
//        }
//        return node;
//    }

    /* Find a node based on it's reference. */
    public MyNode findNode(int reference) {
        MyNode node = root;
        while (true) {

            /* Reference found */
            if (node.getReference() == reference)
                return node;

            /* If we cannot go further down the tree, return null */
            if (node.getLeftNode() == null && node.getRightNode() == null)
                return null;

            /* Choose which path down the tree to go - Right node checked first */
            if (node.getRightNode() != null) {
                if (reference > node.getReference()) {
                    node = node.getRightNode();
                    continue;
                }
            }

            /* Then the left node */
            if (node.getLeftNode() != null) {
                if (reference < node.getReference()) {
                    node = node.getLeftNode();
                    continue;
                }
            }

            /* If either node does not complete the criteria, end the search */
            return null;
        }

    }

    /* 
     * Print the tree first based on right node first
     * This is dictated through the getChildren method in the MyNode class. 
     */
    public void printTree() {

        /* Recursive Method */
//        if (root == null) {
//            System.out.println("Tree is empty.");
//            return;
//        }
//        if (t.getLeftNode() != null) {
//            printTree(t.getLeftNode());
//        }
//        System.out.print(t.getReference() + " ");
//        
//        if (t.getRightNode() != null) {
//            printTree(t.getRightNode());
//        }
        Stack stack = new Stack();

        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        MyNode node = root;
        stack.push(node);
        while (!stack.isEmpty()) {
            node = (MyNode) stack.pop();
            /* Print in order */
            System.out.print(node.getReference() + " ");
            for (MyNode n : node.getChildren()) {
                if (n != null) {
                    stack.push(n);

                }
            }
            /*  Print in reverse order */
//            System.out.print(node.getReference() + " ");
        }
        System.out.println("");
    }

    /* Return the total number of nodes in a tree */
    public int getNodeCount() {
        return nodeCount;
    }

    public MyNode getRoot() {
        return root;
    }

}
