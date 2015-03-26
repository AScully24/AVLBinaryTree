package AVLTree;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class Node implements Comparable<Node> {

    protected int reference;
    private Node leftNode = null;
    private Node rightNode = null;
    private Node parentNode = null;
    private int height;

    public Node(int reference) {
        this.reference = reference;
        height = 1;
    }

    /**
     *
     * @return True if the node is the left child, false if it is not
     */
    public boolean isLeftNode() {
        if (parentNode == null) {
            return false;
        } else return this == parentNode.getLeftNode();
    }

    /**
     *
     * @return True if the node is the right child, false if it is not
     */
    public boolean isRightNode() {
        if (parentNode == null) {
            return false;
        } else return this == parentNode.getRightNode();
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    /**
     *
     * @return A nodes children as an ArrayList of Nodes.
     */
    public ArrayList<Node> getChildren() {
        ArrayList<Node> ar = new ArrayList<>();
        ar.add(leftNode);
        ar.add(rightNode);
        return ar;
    }

    /**
     * Sets the children of the node to the parameter. Any object after the
     * second object is ignored.
     *
     * @param newChildren An Arraylist of Nodes. The first record should be the
     * desired left child.
     */
    public void setChildren(ArrayList<Node> newChildren) {
        leftNode = newChildren.get(0);
        rightNode = newChildren.get(1);
    }

    /**
     *
     * @return Number of children that this node contains.
     */
    public int getChildCount() {
        int ret = 0;
        if (leftNode != null)
            ret++;
        if (rightNode != null)
            ret++;
        return ret;
    }

    /**
     *
     * @param arr ArrayList contains a nodes properties (Used getNodeData for simplicity)
     */
    public void setNodeData(ArrayList<Object> arr) {
        reference = (int) arr.get(0);
    }

    /**
     *
     * @return A nodes properties in an ArrayList
     */
    public ArrayList<Object> getNodeData() {
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(reference);
        return arr;
    }

    /**
     *
     * @return A node if the child count is 1, null for any other result.
     */
    public Node getOnlyChild() {
        if (getChildCount() == 1) {
            if (leftNode != null)
                return leftNode;
            else
                return rightNode;
        } else
            return null;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * Prints the nodes children values.
     * @param out
     * @throws IOException
     */
    public void printTree(OutputStreamWriter out) throws IOException {
        if (rightNode != null) {
            rightNode.printTree(out, true, "");
        }
        printNodeValue(out);
        if (leftNode != null) {
            leftNode.printTree(out, false, "");
        }
    }

    /**
     * Prints the nodes children values. Used by print tree.
     * @param out
     * @throws IOException
     */
    protected void printNodeValue(OutputStreamWriter out) throws IOException {
        out.write(Integer.toString(this.reference));
        out.write('\n');
    }
    
    /**
     * Used to print the nodes in a readable format.
     * @param out
     * @param isRight
     * @param indent
     * @throws IOException
     */
    public void printTree(OutputStreamWriter out, boolean isRight, String indent) throws IOException {
        if (rightNode != null) {
            rightNode.printTree(out, true, indent + (isRight ? "        " : " |      "));
        }
        out.write(indent);
        if (isRight) {
            out.write(" /");
        } else {
            out.write(" \\");
        }
        out.write("----- ");
        printNodeValue(out);
        if (leftNode != null) {
            leftNode.printTree(out, false, indent + (isRight ? " |      " : "        "));
        }
    }

    @Override
    public String toString() {
        return "MyNode{" + "reference=" + reference + '}';
    }

    /*
    *
    *
    * Used by Collections.sort to sort an arraylist by reference.
    */
    @Override
    public int compareTo(Node o) {
        return Comparators.REFERENCE.compare(this, o);
    }

    public static class Comparators {

        public static Comparator<Node> REFERENCE = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getReference() - o2.getReference();
            }
        };
    }

}
