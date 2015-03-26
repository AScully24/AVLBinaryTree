package AVLTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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

        if (!items.itemExists(node.getReference())) {
            if (items.addNode(items.getRoot(), node) == null) {
                return false;
            }

            if (sNode != null) {
                node.addToRelatedSet(sNode);
                sNode.addToItemRefs(node);
            }
            return true;
        }
        return false;

    }

    public boolean addSet(SetNode node) {
        if (!sets.itemExists(node.getReference())) {
            sets.addNode(sets.getRoot(), node);
            return true;
        } 
        
        return false;
        
    }

    @Override
    public ArrayList<Object> getNodeData() {
        ArrayList<Object> arr = super.getNodeData(); //To change body of generated methods, choose Tools | Templates.
        arr.add(name);
        arr.add(items);
        arr.add(sets);

        return arr;
    }

    @Override
    public void setNodeData(ArrayList<Object> arr) {
        super.setNodeData(arr); //To change body of generated methods, choose Tools | Templates.
        name = (String) arr.get(1);
        items = (Tree) arr.get(2);
        sets = (Tree) arr.get(3);
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

        ArrayList<SetNode> relatedSets = toRemove.getRelatedSets();

        for (SetNode n : relatedSets) {
            n.removeItemRef(toRemove);
        }
        
        items.removeNode(items.getRoot(), ref);

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
            items.removeNode(items.getRoot(), i.getReference());
        }

        sets.removeNode(sets.getRoot(), ref);
        return true;
    }

    /**
     * Finds similar items within the repository based upon the description
     *
     * @param description The item description to be searched for.
     * @param itemArr the arraylist to be filled with the similar items.
     */
    public void findSimilarItems(final String description, ArrayList<Node> itemArr) {
        ArrayList<Node> loopArr = new ArrayList<>();
        items.getNodesAsArrayList(loopArr, items.getRoot());
        String newDescription = description.substring(5, description.length() - 5);

        Collections.sort(loopArr, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return ((ItemNode) o1).getDescription().compareTo(((ItemNode) o2).getDescription());
            }
        });

        for (Node na : loopArr) {
            ItemNode n = (ItemNode) na;
            boolean difference = n.getDescription().contains(newDescription);
            if (difference) {
                itemArr.add(n);
            }
        }
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
