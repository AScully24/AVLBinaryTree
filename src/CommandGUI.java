
import AVLTree.Node;
import AVLTree.Tree;
import AVLTree.ItemNode;
import java.io.IOException;
import java.io.OutputStreamWriter;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandGUI {
    /* {src_lang=Java}*/

    private static Tree tree;
    private static final ArrayList<String> data = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //binaryTreeMethod();
        String a = "", b = "Hello1";
        int compareLength = a.length() + a.toLowerCase().compareTo(b.toLowerCase()) ;
        float perc = (float)((float)compareLength / (float)a.length());
        
        System.out.println("a length " + a.length());
        System.out.println("%" + perc + " match");
        System.out.println(compareLength);
        
    }

    private static void binaryTreeMethod() throws IOException {
        tree = new Tree(null);

        OutputStreamWriter output = new OutputStreamWriter(System.out);

        // Test Data
        Scanner sc = new Scanner(CommandGUI.class.getResourceAsStream("Test-Data.csv"));
        sc.nextLine();
        while (sc.hasNextLine()) {
            data.add(sc.nextLine());
        }

        for (int i = 0; i < data.size(); i++) {
            String lineData[] = data.get(i).split(",");
            tree.addNode(new ItemNode(Integer.parseInt(lineData[0]), lineData[1], Double.parseDouble(lineData[2])));
        }

        // Prints the tree in a tree format.
        tree.printTreeStructure();
        ArrayList<Node> arr = new ArrayList<>();
        tree.getNodesAsArrayList(arr, tree.getRoot());
        System.out.println(tree.getNodeCount());
        System.out.println(arr.size());
        

        int ref = 0;

        for (int i = 0; i < 15; i++) {

            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(CommandGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            ref = tree.getRoot().getLeftNode().getReference();
            
            tree.removeNode(ref);
            tree.printTreeStructure();

        }
    }

}
