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
        node.addToRelatedSet(this);
        itemCount++;
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
            if (i == node) {
                items.remove(inc);
                System.out.println("Item removed");
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
