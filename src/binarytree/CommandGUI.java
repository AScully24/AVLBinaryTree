package binarytree;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CommandGUI {
    /* {src_lang=Java}*/

    private static MyTree tree;
    private static final ArrayList<String> data = new ArrayList<>();    
    private static MyLinkedList linkedList;
    
    public static void main(String[] args) throws IOException {
        linkedList = new MyLinkedList();
        Random r = new Random();
        
        int[] test = {200, 192, 180, 189, 220, 190, 185, 170, 160, 165, 193, 194, 196, 195};
        for (int u : test) {
            linkedList.addNode(new LinkedListNode(u,"Test" ,r.nextDouble()));
        }
        
        linkedList.printAllNodes();
        //linkedList.addNode(new LinkedListNode(38, "", 5d));
        linkedList.removeNode(200);
        linkedList.printAllNodes();
        
        
    }

    private static void binaryTreeMethod() throws IOException {
        tree = new MyTree(null);
        Random r = new Random();

        Scanner sc = new Scanner(CommandGUI.class.getResourceAsStream("Test-Data.csv"));
        sc.nextLine();
        while (sc.hasNextLine()) {
            data.add(sc.nextLine());
        }

        int startingPoint = data.size() / 2;
        int movement = 1;
        for (int i = 0; i < data.size(); i++) {

            String lineData[] = data.get(startingPoint).split(",");
            tree.addNode(new BinaryNode(Integer.parseInt(lineData[0]), lineData[1], Double.parseDouble(lineData[2])));

            if ((movement % 2) != 0) {
                startingPoint -= movement;
                System.out.println("--");
            } else {
                startingPoint += movement;
                System.out.println("++");
            }

            if (startingPoint > data.size() || startingPoint < 0) {
                System.out.println("Exiting Here: i=" + i + "\tstartPoint=" + startingPoint + "\tmovement=" + movement);
                break;
            }
            movement++;
//            System.out.print("movement = " + movement);
//            System.out.println("\ti = " + i);
        }

        System.out.println("data.size() = " + data.size());
        OutputStreamWriter output = new OutputStreamWriter(System.out);
        // Prints the tree in a tree format.
        tree.getRoot().printTree(output);
        output.flush();
        System.out.println("\n\n");
//        tree.removeNode(200);
//        tree.getRoot().printTree(output);
//        output.flush();  
    }

}
