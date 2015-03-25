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
    private int height = 0;
    
    public boolean isLeftNode() {
        if (parentNode == null) {
            return false;
        }else return this == parentNode.getLeftNode();
    }
    
    public boolean isRightNode() {
        if (parentNode == null) {
            return false;
        }else return this == parentNode.getRightNode();
    }
    
    public Node(int reference) {
        this.reference = reference;
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

    public ArrayList<Node> getChildren() {
        ArrayList<Node> ar = new ArrayList<>();
        ar.add(leftNode);
        ar.add(rightNode);
        return ar;
    }

    public void setChildren(ArrayList<Node> newChildren) {
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
        String left = "Empty", right = "Empty";
        if (leftNode != null) {
            left = leftNode.toString();
        }
        if (rightNode != null) {
            right = rightNode.toString();
        }
        return "LeftNode: " + left + " ---------- RightNode: " + right;
    }
    
    public void setNodeData(ArrayList<Object> arr) {
        reference = (int) arr.get(0);
    }
    
    public ArrayList<Object> getNodeData(){
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(reference);
        return arr;
    }
    
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

    public boolean isChild(Node node) {
        if (leftNode == node) {
            return true;
        } else if (rightNode == node) {
            return true;
        } else
            return false;
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

    protected void printNodeValue(OutputStreamWriter out) throws IOException {
        out.write(Integer.toString(this.reference));
        out.write('\n');
    }

    protected void printTree(OutputStreamWriter out, boolean isRight, String indent) throws IOException {
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
