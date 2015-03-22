/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avlbinarytree;

import java.util.ArrayList;

/**
 *
 * @author Anthony Scully
 */
public class AVLSetNode extends AVLItemNode {
    private int itemCount;
    private ArrayList<Integer> itemRefs;

    public AVLSetNode(int reference, String description, double price) {
        super(reference, description, price);
        itemCount = 0;
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
    
}
