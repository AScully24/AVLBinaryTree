/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import java.util.ArrayList;

/**
 *
 * @author Anthony Scully
 */
public class SetNode extends ItemNode {

    private int itemCount;
    private ArrayList<Integer> itemRefs;

    public SetNode(int reference, String description, double price, int itemCount) {
        super(reference, description, price);
        this.itemCount = itemCount;
        itemRefs = new ArrayList<>();
    }

    public int getItemByRef(int ref) {
        for (Integer i : itemRefs) {
            if (i == ref) return ref;
        }
        return -1;
    }

    public void addToItemRefs(int ref) {
        itemRefs.add(ref);
        itemCount++;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public ArrayList<Integer> getItemRefs() {
        return itemRefs;
    }

    public void setItemRefs(ArrayList<Integer> itemRefs) {
        this.itemRefs = itemRefs;
    }
    
    public boolean removeItemRef(int ref) {
        for (int i = 0; i < itemRefs.size(); i++) {
            if (itemRefs.get(i)  == ref) {
                itemRefs.remove(i);
                System.out.println("Item removed");
                return true;
            }
        }
        return false;
        
    }

    @Override
    public String toString() {
        String itemRefData = "| ";

        for (Integer i : itemRefs) {
            itemRefData += i.toString() + " | ";
        }

        return "SetNode{" + "itemCount=" + itemCount + "reference=" + reference
                + ", description=" + description + ", price=" + price + ", itemRefs=" + itemRefData + '}';
    }

}
