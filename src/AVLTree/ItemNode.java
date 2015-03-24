package AVLTree;


import java.util.ArrayList;


public class ItemNode extends Node {

    protected String description;
    protected double price;
    private final ArrayList<SetNode> relatedSets;

    public ItemNode(int reference, String description, double price) {
        super(reference);
        this.description = description;
        this.price = price;
        relatedSets = new ArrayList<>();
    }

    public void addToRelatedSet(SetNode node) {
        relatedSets.add(node);
    }

    public void removeFromRelatedSet(SetNode node) {
        relatedSets.remove(node);
    }

    public ArrayList<SetNode> getRelatedSets() {
        return relatedSets;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desciption) {
        this.description = desciption;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return reference + " - " + description + " - £" + price;
    }

}
