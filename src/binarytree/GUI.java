package binarytree;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

public class GUI {
    /* {src_lang=Java}*/

    private static MyTree tree;
    private static final int[] references = new int[10];

    public static void main(String[] args) throws IOException {
        tree = new MyTree(null);
        addReferences();
        Random r = new Random();
        int[] test = {200, 180, 220, 192, 189, 190, 185};

        OutputStreamWriter output = new OutputStreamWriter(System.out);

//        for (int i : references) {
//            tree.addNode(new MyNode(i, r.nextInt(100)));
//        }
        for (int u : test) {
            tree.addNode(new MyNode(u, r.nextInt(100)));
        }

        // Prints the tree in a tree format.
        tree.getRoot().printTree(output);
        output.flush();
        System.out.println("\n\n\n");
        tree.removeNode(200);
        tree.getRoot().printTree(output);
        output.flush();
    }

    private static void addReferences() {
        int i = -1;
        references[++i] = 10;
        references[++i] = 9;
        references[++i] = 12;
        references[++i] = 5;
        references[++i] = 3;
        references[++i] = 4;
        references[++i] = 16;
        references[++i] = 13;
        references[++i] = 11;
        references[++i] = 20;

    }

}
