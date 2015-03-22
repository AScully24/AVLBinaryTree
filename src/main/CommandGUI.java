package main;

import avlbinarytree.AVLNode;
import avlbinarytree.AVLTree;
import static com.sun.org.apache.regexp.internal.RETest.test;
import linkedlist.MyLinkedList;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CommandGUI {
    /* {src_lang=Java}*/

    private static AVLTree tree;
    private static final ArrayList<String> data = new ArrayList<>();
    private static MyLinkedList linkedList;

    public static void main(String[] args) throws IOException {
        binaryTreeMethod();

//        linkedList = new MyLinkedList();
//        Random r = new Random();
//        
//        int[] test = {200, 192, 180, 189, 220, 190, 185, 170, 160, 165, 193, 194, 196, 195};
//        for (int u : test) {
//            linkedList.addNode(new ItemNode(u,"Test" ,r.nextDouble()));
//        }
//        
//        linkedList.printAllNodes();
//        //linkedList.addNode(new ItemNode(38, "", 5d));
//        linkedList.removeNode(200);
//        linkedList.printAllNodes();
//        
    }

    private static void binaryTreeMethod() throws IOException {
        tree = new AVLTree(null);
        Random r = new Random();

        OutputStreamWriter output = new OutputStreamWriter(System.out);

        // Test Data
        //int[] test = {200, 192, 180, 189, 220, 190, 185, 170, 160, 165, 193, 194, 196, 195};
        //int[] test = {29, 26, 23};
        //int[] test = {100,90,95};
        //int[] test = {23,26,29};
        //int[] test = {100,90,110,85,95};
        // int[] test = {41, 20, 29, 26, 23, 65, 50, 11, 55};
//        for (int u : test) {
//            System.out.println(u);
//            tree.addNode(new AVLNode(u, "Test", r.nextDouble()));
//            tree.getRoot().printTree(output);
//            output.flush();
//            System.out.println("\n\n\n");
//        for (int i = -4; i < 11; i++) {
//            tree.addNode(new AVLNode(i, "Test", r.nextDouble()));
//            tree.getRoot().printTree(output);
//            output.flush();
//            System.out.println("\n\n\n");
//        }
        // Actual Data
        Scanner sc = new Scanner(CommandGUI.class.getResourceAsStream("Test-Data.csv"));

        sc.nextLine();

        while (sc.hasNextLine()) {
            data.add(sc.nextLine());
        }
        for (int i = 0; i < data.size(); i++) {
            String lineData[] = data.get(i).split(",");
            tree.addNode(new AVLNode(Integer.parseInt(lineData[0]), lineData[1], Double.parseDouble(lineData[2])));
        }
        tree.getRoot().printTree(output);
        output.flush();
        System.out.println("\n\n\n");
        //12727
        
        
        
//        // Prints the tree in a tree format.
//        tree.getRoot().printTree(output);
//        output.flush();
//        System.out.println("\n\n");

        AVLNode swap = tree.findNode(12727, tree.getRoot());
        tree.rotateNodeLeft(swap);
        tree.getRoot().printTree(output);
        output.flush();
        System.out.println("\n\n");
//        
//        
//        swap = tree.findNode(50, tree.getRoot());
//        tree.rotateNodeLeft(swap);
//        tree.getRoot().printTree(output);
//        output.flush();
//        System.out.println("\n\n");
//        
//        swap = tree.findNode(65, tree.getRoot());
//        tree.rotateNodeRight(swap);
//        tree.getRoot().printTree(output);
//        output.flush();
//        System.out.println("\n\n");
    }
}
