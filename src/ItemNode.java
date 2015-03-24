public class ItemNode extends Node {

    private String desciption;
    private double price;

    public ItemNode(int reference, String description, double price) {
        super(reference);
        this.desciption = description;
        this.price = price;
    }

    public String getDescription() {
        return desciption;
    }

    public void setDescription(String desciption) {
        this.desciption = desciption;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "ItemNode{" + "desciption=" + desciption + ", price=" + price + '}';
    }
    
    
}
