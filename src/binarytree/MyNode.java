package binarytree;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Contains a reference and a piece of data that can be stored in a MyTree
 * class.
 *
 * @author Anthony Scully
 */
public class MyNode {
    /* {src_lang=Java}*/

    private int reference;
    private int data;
    private MyNode leftNode = null;
    private MyNode rightNode = null;
    private MyNode parentNode = null;

    /**
     *
     * @param reference A unique value for this node.
     * @param data The data to be stored in the node.
     */
    public MyNode(int reference, int data) {
        this.reference = reference;
        this.data = data;
    }

    /**
     *
     * @return The node previous to this node in the tree.
     */
    public MyNode getParentNode() {
        return parentNode;
    }

    /**
     * Sets a new parent for the current node.
     *
     * @param parentNode New parent node.
     */
    public void setParentNode(MyNode parentNode) {
        this.parentNode = parentNode;
    }

    /**
     *
     * @return The child node with the lower reference number than this node.
     * Returns null if one doesn't exist.
     */
    public MyNode getLeftNode() {
        return leftNode;
    }

    /**
     *
     * @return The child node with the higher reference number than this node.
     * Returns null if one doesn't exist.
     */
    public MyNode getRightNode() {
        return rightNode;
    }

    /**
     *
     * @param leftNode Set a new lower reference child node.
     */
    public void setLeftNode(MyNode leftNode) {
        this.leftNode = leftNode;
    }

    /**
     *
     * @param rightNode Set a new higher reference child node.
     */
    public void setRightNode(MyNode rightNode) {
        this.rightNode = rightNode;
    }

    /**
     *
     * @return The unique reference number for this node.
     */
    public int getReference() {
        return reference;
    }

    /**
     *
     * @return The data contained in this node.
     */
    public int getData() {
        return data;
    }

    /**
     * Sets a new unique reference for this node.
     *
     * @param reference The new unique value.
     */
    public void setReference(int reference) {
        this.reference = reference;
    }

    /**
     * Sets a new data value for the node.
     *
     * @param data The new data to be stored.
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     *
     * @return An ArrayList of MyNodes that are the children of this node, order
     * lowest reference to highest reference.
     */
    public ArrayList<MyNode> getChildren() {
        ArrayList<MyNode> ar = new ArrayList<>();
        ar.add(leftNode);
        ar.add(rightNode);
        return ar;
    }

    /**
     *
     * @param newChildren ArrayList of the new children for this node, ordered
     * lowest reference first.
     */
    public void setChildren(ArrayList<MyNode> newChildren) {
        leftNode = newChildren.get(0);
        rightNode = newChildren.get(1);
    }

    /**
     *
     * @return Number of children that are not set to null.
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
     * @return Value of children nodes as a string.
     */
    public String getChildrenString() {
        String left = "Empty", right = "Empty";
        if (leftNode != null) {
            left = leftNode.toString();
        }
        if (rightNode != null) {
            right = rightNode.toString();
        }
        return "LeftNode: " + left + " ---------- RightNode: " + right;
    }

    /**
     *
     * @return A MyNode that is the only child of this node. Returns null if
     * there are two children or there are no children.
     */
    public MyNode getOnlyChild() {
        if (getChildCount() == 1) {
            if (leftNode != null) return leftNode;
            else return rightNode;
        } else return null;
    }

    public void printTree(OutputStreamWriter out) throws IOException {
        if (rightNode != null) {
            rightNode.printTree(out, true, "");
        }
        printNodeValue(out);
        if (leftNode != null) {
            leftNode.printTree(out, false, "");
        }
    }

    private void printNodeValue(OutputStreamWriter out) throws IOException {
        out.write(Integer.toString(this.reference));
        out.write('\n');
    }

    private void printTree(OutputStreamWriter out, boolean isRight, String indent) throws IOException {
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
        return "MyNode{" + "reference=" + reference + ", data=" + data + ", Child Count=" + getChildCount() + '}';
    }

}
