package AVLTree;

import java.util.ArrayList;

public class RepoNode extends Node {

    Tree sets;
    Tree items;
    String name;

    public RepoNode(int reference, String name) {
        super(reference);
        this.name = name;
        items = new Tree();
        sets = new Tree();
    }

    /**
     * Add an item to the item list the set list (if required)
     *
     * @param node The new Item node
     * @param sNode A set that the node is going to be added to.
     * @return True when item is added, false if it is a duplicate.
     */
    public boolean addItem(ItemNode node, SetNode sNode) {
        if (!items.addNode(node)) {
            return false;
        }

        if (sNode != null) {
            node.addToRelatedSet(sNode);
            sNode.addToItemRefs(node);
        }
        return true;
    }

    public void addSet(SetNode node) {
        sets.addNode(node);
    }

    /**
     * Deletes an item and removes it from any sets within the repo.
     *
     * @param ref The reference for the node to be deleted.
     * @return False when the ItemNode doesn't exist, true when it has been
     * deleted.
     */
    public boolean removeItem(int ref) {
        System.out.println("Searching for " + ref);
        ItemNode toRemove = (ItemNode) items.findNode(ref, items.getRoot());
        
        if (toRemove == null) {
            return false;
        }
        System.out.println("Item Found " + toRemove.getReference());
        ArrayList<SetNode> relatedSets = toRemove.getRelatedSets();

        for (SetNode n : relatedSets) {
            n.removeItemRef(toRemove);
        }
        items.removeNode(ref);

        return true;
    }

    /**
     * Deletes a set and any items associated with that set within the repo.
     *
     * @param ref The reference for the node to be deleted.
     * @return False when the SetNode doesn't exist, true when it has been
     * deleted.
     */
    public boolean removeSet(int ref) {
        SetNode toRemove = (SetNode) sets.findNode(ref, sets.getRoot());

        if (toRemove == null) {
            return false;
        }

        ArrayList<ItemNode> itemsToRemove = toRemove.getItemRefs();
        for (ItemNode i : itemsToRemove) {
            items.removeNode(i.getReference());
        }

        sets.removeNode(ref);
        return true;
    }
    
    public ArrayList<Node> findSimilarItems(ItemNode node) {
        ArrayList<Node> arr = new ArrayList<>();
        items.getNodesAsArrayList(arr, items.getRoot());
        
        for (Node na : arr) {
//            if (node.getDescription().compareTo(((ItemNode)na).getDescription())) {
//                
//            }
        }
        
        return arr;
    }

    public ItemNode findItem(int ref) {
        return (ItemNode) items.findNode(ref, items.getRoot());
    }

    public void printSetsTree() {
        sets.printTreeStructure();
    }

    public void printItemsTree() {
        items.printTreeStructure();
    }

    @Override
    public String toString() {
        return reference + " - " + name;
    }

    public Tree getSets() {
        return sets;
    }

    public Tree getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

}
