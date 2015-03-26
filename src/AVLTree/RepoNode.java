package AVLTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

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
     * @param itemNode The new Item node
     * @param setNode A set that the node is going to be added to.
     * @return True when item is added, false if it is a duplicate.
     */
    public boolean addItem(ItemNode itemNode, SetNode setNode) {

        if (!items.nodeExists(itemNode.getReference())) {
            if (items.addNode(items.getRoot(), itemNode) == null) {
                return false;
            }

            if (setNode != null) {
                //node.addToRelatedSet(sNode);
                setNode.addToItemRefs(itemNode);
            }
            return true;
        }
        return false;

    }

    public boolean addSet(SetNode node) {
        if (!sets.nodeExists(node.getReference())) {
            sets.addNode(sets.getRoot(), node);
            return true;
        }

        return false;

    }

    @Override
    public ArrayList<Object> getNodeData() {
        ArrayList<Object> arr = super.getNodeData();
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
        //System.out.println("Searching for " + ref);
        ItemNode toRemove = (ItemNode) items.findNode(ref, items.getRoot());

        if (toRemove == null) {
            return false;
        }
        
        ArrayList<Node> allSets = new ArrayList<>();
        sets.getNodesAsArrayList(allSets, sets.getRoot());
        
        for (Node no : allSets) {
            SetNode n = (SetNode) no;
            n.removeItemRef(toRemove.getReference());
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

//        ArrayList<ItemNode> itemsToRemove = toRemove.getItemRefs();
//        for (ItemNode i : itemsToRemove) {
//            items.removeNode(items.getRoot(), i.getReference());
//        }
        
        sets.removeNode(sets.getRoot(), ref);
        return true;
    }

    /**
     * Finds similar items within the repository based upon the description
     *
     * @param description The item description to be searched for.
     * @param itemArr the ArrayList to be filled with the similar items.
     */
    public void findSimilarItems(final String description, ArrayList<ItemNode> itemArr) {
        ArrayList<Node> loopArr = new ArrayList<>();
        items.getNodesAsArrayList(loopArr, items.getRoot());
        String newDescription = "";
        if (description.length() <=12) {
            newDescription = description;
        }else newDescription = description.substring(4, description.length() - 4);
        

        Collections.sort(loopArr, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return ((ItemNode) o1).getDescription().compareTo(((ItemNode) o2).getDescription());
            }
        });
        
        boolean isFound = false;
        for (Node na : loopArr) {
            ItemNode n = (ItemNode) na;
            boolean difference = n.getDescription().contains(newDescription);
            if (difference) {
                // Checks if the node has already been found.
                for (Node nab : itemArr) {
                    if (nab.getReference() == n.getReference()) {
                        isFound = true;
                    }
                }
                
                // Does not add if it hasn't been found.
                if (!isFound) {
                    itemArr.add(n);
                }
                isFound = false;
                
            }
        }
    }

    /**
     * Searches for an ItemNode based upon a reference number.
     *
     * @param ref The item reference to be searched for
     * @return The desired ItemNode, otherwise null.
     */
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
