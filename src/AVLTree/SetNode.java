package AVLTree;

import java.util.ArrayList;

public class SetNode extends ItemNode {

    private int itemCount;
    private ArrayList<ItemNode> items;

    public SetNode(int reference, String description, double price) {
        super(reference, description, price);
        itemCount = 0;
        items = new ArrayList<>();
    }

    /**
     * Searches a sets item list for a particular item.
     * @param ref The reference to be searched for
     * @return The desired item, otherwise null.
     */
    public ItemNode getItemByRef(int ref) {
        for (ItemNode i : items) {
            if (i.getReference() == ref)
                return i;
        }
        return null;
    }

    /**
     * Adds a new item to the Sets item list.
     * @param node The ItemNode to be added.
     */
    public boolean addToItemRefs(ItemNode node) {
        if (!items.contains(node)) {
            items.add(node);
            itemCount++;
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Object> getNodeData() {
        ArrayList<Object> arr = super.getNodeData(); //To change body of generated methods, choose Tools | Templates.
        arr.add(itemCount);
        arr.add(items);
        return arr;
    }

    @Override
    public void setNodeData(ArrayList<Object> arr) {
        super.setNodeData(arr);
        itemCount = (int) arr.get(4);
        items = (ArrayList<ItemNode>) arr.get(5);

    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public ArrayList<ItemNode> getItemRefs() {
        return items;
    }

    public void setItemRefs(ArrayList<ItemNode> itemRefs) {
        this.items = itemRefs;
    }

    /**
     * Deletes an item in the Set item list.
     * @param ref the desired item reference number
     * @return True if found and deleted, otherwise false.
     */
    public boolean removeItemRef(int ref) {
        int inc = 0;
        for (ItemNode i : items) {
            if (i.getReference() == ref) {
                items.remove(inc);
                return true;
            }
            inc++;
        }
        return false;
    }

    @Override
    public String toString() {
        return description;
    }

}
