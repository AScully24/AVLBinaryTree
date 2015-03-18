package linkedlist;

import java.util.ArrayList;

/**
 *
 * @author Anthony Scully
 */
public class MyLinkedList {

    private ItemNode root;
    private int nodeCount = 0;

    public MyLinkedList() {
    }
    
    public MyLinkedList(ItemNode root) {
        this.root = root;
        nodeCount = 1;
    }

    public void addNode(ItemNode newNode) {
        ItemNode tempNode = root;
        
        if (newNode == null) {
            System.out.println("Add Node: New node is null");
            return;
        }
        
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
    
    public ItemNode findNode(int reference) {
        ItemNode tempNode = root;
        
        while (tempNode != null && tempNode.getReference() != reference) {
            tempNode = tempNode.getChild();
        }
        
        return tempNode;
    }
    
    public void removeNode(int reference) {
        ItemNode tempNode = root;
        
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
        ItemNode tempNode = root;
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
 
    public void printNodeData(int ref) {
        ItemNode tempNode = findNode(ref);
        if (tempNode == null) {
            System.out.println("Print Node: Node not found.");
        }else{
            System.out.println(tempNode.toString());
        }
    }
    
    public void printAllNodeData() {
        ItemNode tempNode = root;
        if (tempNode == null) {
            System.out.println("null");
            return;
        }
        while (tempNode!=null) {
            System.out.println(tempNode.toString());
            tempNode = tempNode.getChild();
        }
        System.out.println("End");
    }
    
    public ArrayList<ItemNode> getNodesAsArrayList() {
        ArrayList<ItemNode> temp = new ArrayList<>();
        ItemNode tempNode = root;
        if (tempNode == null) {
            System.out.println("null");
            return null;
        }
        
        while (tempNode!=null) {
            temp.add(tempNode);
            tempNode = tempNode.getChild();
        }
        
        return temp;
    }
    
    public ItemNode getRoot() {
        return root;
    }
    
    public int getNodeCount() {
        return nodeCount;
    }
}
