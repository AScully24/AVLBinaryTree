package binarytree;

import java.util.ArrayList;

public class MyNode {
    /* {src_lang=Java}*/

    private int reference;
    private int data;
    private MyNode leftNode = null;
    private MyNode rightNode = null;
    private MyNode parentNode = null;

    public MyNode(int reference, int data) {
        this.reference = reference;
        this.data = data;
    }

    public MyNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(MyNode parentNode) {
        this.parentNode = parentNode;
    }

    public MyNode getLeftNode() {
        return leftNode;
    }

    public MyNode getRightNode() {
        return rightNode;
    }

    public void setLeftNode(MyNode leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(MyNode rightNode) {
        this.rightNode = rightNode;
    }

    public int getReference() {
        return reference;
    }

    public int getData() {
        return data;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ArrayList<MyNode> getChildren() {
        ArrayList<MyNode> ar = new ArrayList<>();
        ar.add(leftNode);
        ar.add(rightNode);
        return ar;
    }

    public void setChildren(ArrayList<MyNode> newChildren) {
        leftNode = newChildren.get(0);
        rightNode = newChildren.get(1);
    }

    public int getChildCount() {
        int ret = 0;
        if (leftNode != null)
            ret++;
        if (rightNode != null)
            ret++;

        return ret;
    }

    public String getChildrenString() {
        String left ="Empty" , right = "Empty";
        if (leftNode != null) {
            left = leftNode.toString();
        }
        if (rightNode != null) {
            right = rightNode.toString();
        }
        return "LeftNode: " + left+ " ---------- RightNode: " + right;
    }
    
    public MyNode getOnlyChild() {
        if (getChildCount() == 1) {
            if (leftNode != null) return leftNode;
            else return rightNode;
        } else return null;
    }

    @Override
    public String toString() {
        return "MyNode{" + "reference=" + reference + ", data=" + data + ", Child Count=" + getChildCount() + '}';
    }

}
