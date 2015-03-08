package binarytree;

/**
 *
 * @author Anthony Scully
 */
public class MyLinkedList {

    private LinkedListNode root;
    private int nodeCount = 0;

    public MyLinkedList() {
    }
    
    public MyLinkedList(LinkedListNode root) {
        this.root = root;
        nodeCount = 1;
    }

    public void addNode(LinkedListNode newNode) {
        LinkedListNode tempNode = root;
        
        if (root == null) {
            root = newNode;
            return;
        }
        
        while (tempNode.getChild() != null) {
            tempNode = tempNode.getChild();
        }
        
        newNode.setParent(tempNode);
        tempNode.setChild(newNode);
        nodeCount++;
    }
    
    public LinkedListNode findNode(int reference) {
        LinkedListNode tempNode = root;
        
        while (tempNode != null && tempNode.getReference() != reference) {
            tempNode = tempNode.getChild();
        }
        
        return tempNode;
    }
    
    public void removeNode(int reference) {
        LinkedListNode tempNode = root;
        
        if (tempNode == null) {
            System.out.println("Removal: No root node.");
            return;
        }
        
        while (tempNode != null && tempNode.getReference() != reference) {
            tempNode = tempNode.getChild();
        }
        
        
        if (tempNode.getParent() == null){
            root = root.getChild();
            root.setParent(null);
        }
        else{
            tempNode.getParent().setChild(tempNode.getChild());
        }
        nodeCount--;
    }

    public void printAllNodes() {
        LinkedListNode tempNode = root;
        if (tempNode == null) {
            System.out.println("null");
            return;
        }
        while (tempNode!=null) {
            System.out.print(tempNode.getReference() + "->");
            tempNode = tempNode.getChild();
        }
        System.out.println("null");
    }
    
    public LinkedListNode getRoot() {
        return root;
    }

    public int getNodeCount() {
        return nodeCount;
    }
}
