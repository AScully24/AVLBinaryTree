package main;

import avlbinarytree.ItemNode;
import avlbinarytree.Tree;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandGUI {
    /* {src_lang=Java}*/

    private static Tree tree;
    private static final ArrayList<String> data = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        binaryTreeMethod();
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

//        // Prints the tree in a tree format.
        tree.printTreeStructure();
    }
}
