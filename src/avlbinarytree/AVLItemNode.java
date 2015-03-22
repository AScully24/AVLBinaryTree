package avlbinarytree;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Contains a reference and a piece of data that can be stored in a MyTree
 * class.
 *
 * @author Anthony Scully
 */
public class AVLItemNode {
    /* {src_lang=Java}*/

    private int reference;
    private String description;
    private double price;
    private AVLItemNode leftNode = null;
    private AVLItemNode rightNode = null;
    private AVLItemNode parentNode = null;
    private int height = 0;

    /**
     *
     * @param reference A unique value for this node.
     * @param description The description for this node.
     * @param price The price to be stored in the node.
     */
    public AVLItemNode(int reference, String description, double price) {
        this.reference = reference;
        this.description = description;
        this.price = price;
    }

    /**
     *
     * @return The node previous to this node in the tree.
     */
    public AVLItemNode getParentNode() {
        return parentNode;
    }

    /**
     * Sets a new parent for the current node.
     *
     * @param parentNode New parent node.
     */
    public void setParentNode(AVLItemNode parentNode) {
        this.parentNode = parentNode;
    }

    /**
     *
     * @return The child node with the lower reference number than this node.
     * Returns null if one doesn't exist.
     */
    public AVLItemNode getLeftNode() {
        return leftNode;
    }

    /**
     *
     * @return The child node with the higher reference number than this node.
     * Returns null if one doesn't exist.
     */
    public AVLItemNode getRightNode() {
        return rightNode;
    }

    /**
     *
     * @param leftNode Set a new lower reference child node.
     */
    public void setLeftNode(AVLItemNode leftNode) {
        this.leftNode = leftNode;
    }

    /**
     *
     * @param rightNode Set a new higher reference child node.
     */
    public void setRightNode(AVLItemNode rightNode) {
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
     * @return The price contained in this node.
     */
    public double getPrice() {
        return price;
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
     * Sets a new price value for the node.
     *
     * @param price The new data to be stored.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 
     * @return The item description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set a new item description.
     * @param description The new description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     *
     * @return An ArrayList of MyNodes that are the children of this node, order
     * lowest reference to highest reference.
     */
    public ArrayList<AVLItemNode> getChildren() {
        ArrayList<AVLItemNode> ar = new ArrayList<>();
        ar.add(leftNode);
        ar.add(rightNode);
        return ar;
    }

    /**
     *
     * @param newChildren ArrayList of the new children for this node, ordered
     * lowest reference first.
     */
    public void setChildren(ArrayList<AVLItemNode> newChildren) {
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
     * @return A AVLItemNode that is the only child of this node. Returns null if
 there are two children or there are no children.
     */
    public AVLItemNode getOnlyChild() {
        if (getChildCount() == 1) {
            if (leftNode != null) return leftNode;
            else return rightNode;
        } else return null;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isChild(AVLItemNode node) {
        if (leftNode == node) {
            return true;
        }else if (rightNode == node) {
            return true;
        }else return false;
    }
    
    public void setData(NodeData data) {
        description = data.description;
        price = data.price;
        reference = data.reference;
        height = data.height;
        //parentNode = data.parent;
        //setChildren(data.children);
    }
    
    public NodeData getData() {
        NodeData data = new NodeData();
        data.description = description;
        data.price = price;
        data.reference = reference;
        data.height = height;
        //data.parent = parentNode;
        //data.children = getChildren();
        return data;
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
        return "MyNode{" + "reference=" + reference + ", price=" + price + ", Child Count=" + getChildCount() + '}';
    }

}
