
import java.util.ArrayList;

public class RepoNode extends Node {

    Tree sets;
    Tree items;

    public RepoNode(int reference) {
        super(reference);
    }

    public void addItem(ItemNode node) {
        items.addNode(node);
    }

    public void addSet(SetNode node) {
        sets.addNode(node);
    }

    public void removeItem(int ref) {
        items.removeNode(ref);
        sets.removeNode(ref);
    }

    public ArrayList<SetNode> getSetsContaingItem(int ref) {
        ArrayList<SetNode> arr = new ArrayList<>();
        
        
        
        
        if (arr.size() > 0) return arr;
        else return null;
    }

    public void removeSet(int ref) {
        sets.removeNode(ref);
    }

    public void printSetsTree() {
        sets.printTreeStructure();
    }

    public void printItemsTree() {
        items.printTreeStructure();
    }
}
