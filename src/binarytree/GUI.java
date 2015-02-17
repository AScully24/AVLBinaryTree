package binarytree;

import java.util.Random;

public class GUI {
    /* {src_lang=Java}*/

    private static MyTree tree;
    private static final int[] references = new int[10];

    public static void main(String[] args) {
        tree = new MyTree(null);
        addReferences();
        Random r = new Random();
        int[] test = {200,180,220,190,185};
        
//        for (int i : references) {
//            tree.addNode(new MyNode(i, r.nextInt(100)));
//        }
        
        
        for (int u : test) {
            tree.addNode(new MyNode(u, r.nextInt(100)));
        }
        
        tree.printTree(tree.getRoot());
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
