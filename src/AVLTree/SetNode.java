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

    public ItemNode getItemByRef(ItemNode node) {
        for (ItemNode i : items) {
            if (i == node)
                return node;
        }
        return null;
    }

    public void addToItemRefs(ItemNode node) {
        items.add(node);
        itemCount++;
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
        super.setNodeData(arr); //To change body of generated methods, choose Tools | Templates.
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

    public boolean removeItemRef(ItemNode node) {
        int inc = 0;
        for (ItemNode i : items) {
            if (i.getReference() == node.getReference()) {
                items.get(inc).removeFromRelatedSet(this);
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
