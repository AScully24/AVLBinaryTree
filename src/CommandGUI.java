
import AVLTree.*;
import AVLTree.ItemNode;
import java.io.IOException;
import java.io.OutputStreamWriter;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandGUI {
    /* {src_lang=Java}*/

    private static Tree tree;
    private static final ArrayList<String> data = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //binaryTreeMethod();
        testData();
    }

    private static void testingStrings() {
        String a = "", b = "Hello1";
        int compareLength = a.length() + a.toLowerCase().compareTo(b.toLowerCase());
        float perc = (float) ((float) compareLength / (float) a.length());

        System.out.println("a length " + a.length());
        System.out.println("%" + perc + " match");
        System.out.println(compareLength);
    }
    
    private static void testData() {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(56);
        test.add(99);
        test.add(68);
        test.add(10);
        test.add(23);
        
        Random r = new Random();
        tree = new Tree();
        for (int i = 0; i < 30; i++) {
            
            int adding = r.nextInt(100);
//            int adding = test.get(i);
            System.out.println(adding  + " adding this number");
            
//            test.add(r.nextInt());
            tree.addNode(tree.getRoot(), new Node(adding));
            tree.printTreeStructure();
            try {
                sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(CommandGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        int check = 0;
//        for (Integer i : test) {
//            tree.addNode(tree.getRoot(), new Node(i));
//            //ree.printTreeStructure();
//        }
        
        tree.printTreeStructure();
        
    }
    
    
    private static void binaryTreeMethod() throws IOException {
        tree = new Tree(null);

        OutputStreamWriter output = new OutputStreamWriter(System.out);

        // Test Data
        Scanner sc = new Scanner(CommandGUI.class.getResourceAsStream("repo1//items.csv"));
        sc.nextLine();
        while (sc.hasNextLine()) {
            data.add(sc.nextLine());
        }

        for (int i = 0; i < data.size(); i++) {
            String lineData[] = data.get(i).split(",");
            tree.addNode(tree.getRoot(),new ItemNode(Integer.parseInt(lineData[0]), lineData[1], Double.parseDouble(lineData[2])));
        }
//        
//        int refs[] = {12806,12802,12810,12807,12805,12804};
//        
//        for (int r : refs) {
//            try {
//                sleep(1);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(CommandGUI.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            tree.removeNode(r);
//            tree.printTreeStructure();
//        }

        // Prints the tree in a tree format.
        //tree.printTreeStructure();
//        System.getProperties().list(System.out);
        tree.printTreeStructure();
        int ref = 12806;
//        Node n = tree.findNode(ref, tree.getRoot());
//        tree.removeNode(ref);

//        ref = 12807;
//        tree.removeNode(ref);
//        tree.printTreeStructure();
//
        for (int i = 0; i < 18; i++) {
            try {
                sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(CommandGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

               tree.printTreeStructure();
            ref = tree.getRoot().getLeftNode().getReference();
            //ref = tree.findHighestNode(tree.getRoot().getRightNode()).getReference();

            tree.removeNode(ref);

        }
        tree.printTreeStructure();

    }

}
