/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

/**
 *
 * @author Anthony Scully
 */
public class ItemNode {

    protected ItemNode parent, child;
    protected int reference;
    protected String description;
    protected double price;
    
    public ItemNode(int reference, String description, double price) {
        this.reference = reference;
        this.description = description;
        this.price = price;
    }

    public ItemNode getParent() {
        return parent;
    }

    public void setParent(ItemNode parent) {
        this.parent = parent;
    }

    public ItemNode getChild() {
        return child;
    }

    public void setChild(ItemNode child) {
        this.child = child;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "LinkedListNode{" + "reference=" + reference + ", description=" + description + ", price=" + price + '}';
    }
    
    
}
