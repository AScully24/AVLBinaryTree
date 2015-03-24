
import java.io.OutputStreamWriter;
import static java.lang.StrictMath.max;
import java.util.ArrayList;

public class Tree {

    private static final int UNBALANCED_RIGHT = 1;
    private static final int UNBALANCED_LEFT = -1;
    private int nodeCount;
    private Node root;

    public Tree() {
    }

    public Tree(Node root) {
        this.root = root;
    }

    public boolean addNode(Node newNode) {
        if (root == null) {
            root = newNode;
            nodeCount++;
            return true;
        }
        if (findNode(newNode.getReference(), root) != null) {
            return false;
        }
        Node node = root;
        while (true) {
            if (newNode.getReference() > node.getReference()) {
                if (node.getRightNode() == null) {
                    node.setRightNode(newNode);
                    node.getRightNode().setParentNode(node);
                    node = node.getRightNode();
                    nodeCount++;
                    break;
                } else
                    node = node.getRightNode();
            } else {
                if (node.getLeftNode() == null) {
                    node.setLeftNode(newNode);
                    node.getLeftNode().setParentNode(node);
                    node = node.getLeftNode();
                    nodeCount++;
                    break;
                } else
                    node = node.getLeftNode();
            }
        }
        rebalanceTree(node, node);
        return true;
    }

    public void refreshNodeHeight(Node node) {
        int childCount = node.getChildCount();
        if (childCount == 0)
            node.setHeight(0);
        if (childCount == 1)
            node.setHeight(node.getOnlyChild().getHeight() + 1);
        if (childCount == 2) {
            int maxHeight = max(node.getLeftNode().getHeight(), node.getRightNode().getHeight());
            node.setHeight(maxHeight + 1);
        }
    }

    private void rebalanceTree(Node node, Node leafNode) {
        if (leafNode == null) {
            leafNode = node;
        }
        while (node != null) {
            refreshNodeHeight(node);
            balanceHandler(node, leafNode);
            node = node.getParentNode();
        }
    }

    private void balanceHandler(Node node, Node leafNode) {
        int balance = checkBalance(node);
        int rotationCount = 0;
        if (balance != 0) {
            rotationCount = rotationCase(node, balance, leafNode);
            if (balance == UNBALANCED_RIGHT) {
                if (rotationCount == 1) {
                    rotateNodeLeft(node);
                } else if (rotationCount == 2) {
                    rotateNodeRight(node.getRightNode());
                    rotateNodeLeft(node);
                }
            } else if (balance == UNBALANCED_LEFT) {
                if (rotationCount == 1) {
                    rotateNodeRight(node);
                } else if (rotationCount == 2) {
                    rotateNodeLeft(node.getLeftNode());
                    rotateNodeRight(node);
                }
            }
        }
    }

    public int rotationCase(Node node, int balance, Node leafNode) {
        Node temp = node;
        ArrayList<Node> arr = new ArrayList<>();
//        System.out.print("Leaf: " + leafNode.getReference() + "\t");
        for (int i = 0; i < 2; i++) {
//            if (i == 0) {
//                if (leafNode.getReference() <= temp.getReference()) {
//                    temp = temp.getLeftNode();
//                } else {
//                    temp = temp.getRightNode();
//                }
//            } else {
//                System.out.print("Temp: " + i + "--" + temp.getReference() + "\t");
            if (leafNode.getReference() < temp.getReference()) {
                arr.add(temp.getLeftNode());
                temp = temp.getLeftNode();
            } else {
                arr.add(temp.getRightNode());
                temp = temp.getRightNode();
            }
            //}
        }

//        System.out.println("");
//        System.out.println("Bal= " + checkBalance(node));
//        OutputStreamWriter output = new OutputStreamWriter(System.out);
//        try {
//            node.printTree(output);
//            output.flush();
//            System.out.println("\n\n\n");
//        } catch (Exception e) {
//            System.out.println("Cannot print tree.");
//        }
        Node x = arr.get(0), y = arr.get(1);
        if (balance == UNBALANCED_LEFT) {
            if (y == x.getLeftNode())
                return 1;
            if (y == x.getRightNode())
                return 2;
        }
        if (balance == UNBALANCED_RIGHT) {
            if (y == x.getRightNode())
                return 1;
            if (y == x.getLeftNode())
                return 2;
        }
        return 0;
    }

    private void rotateNodeLeft(Node node) {
        Node x = node, y = node.getRightNode();
        Node a = x.getLeftNode(), b = y.getLeftNode(), c = y.getRightNode();
        Node masterParent = x.getParentNode();
        if (x == root)
            root = y;
        else {
            if (masterParent.getLeftNode() == x) {
                masterParent.setLeftNode(y);
            } else
                masterParent.setRightNode(y);
        }
        y.setParentNode(x.getParentNode());
        y.setLeftNode(x);
        x.setParentNode(y);
        x.setLeftNode(a);
        x.setRightNode(b);
        if (a != null)
            a.setParentNode(x);
        if (b != null)
            b.setParentNode(x);
        y.setRightNode(c);
        if (c != null)
            c.setParentNode(y);
        refreshNodeHeight(x);
        refreshNodeHeight(y);
    }

    private void rotateNodeRight(Node node) {
        Node x = node.getLeftNode(), y = node;
        Node a = x.getLeftNode(), b = x.getRightNode(), c = y.getRightNode();
        Node masterParent = y.getParentNode();
        if (y == root)
            root = x;
        else {
            if (masterParent.getLeftNode() == y) {
                masterParent.setLeftNode(x);
            } else
                masterParent.setRightNode(x);
        }
        x.setParentNode(y.getParentNode());
        x.setRightNode(y);
        y.setParentNode(x);
        y.setLeftNode(b);
        y.setRightNode(c);
        if (b != null)
            b.setParentNode(y);
        if (c != null)
            c.setParentNode(y);
        x.setLeftNode(a);
        if (a != null)
            a.setParentNode(x);
        refreshNodeHeight(y);
        refreshNodeHeight(x);
    }

    private int checkBalance(Node node) {
        int leftHeight, rightHeight;
        if (node == null)
            return 0;
        if (node.getLeftNode() == null)
            leftHeight = -1;
        else
            leftHeight = node.getLeftNode().getHeight();
        if (node.getRightNode() == null)
            rightHeight = -1;
        else
            rightHeight = node.getRightNode().getHeight();
        int balance = leftHeight - rightHeight;
        if (balance > 1)
            return -1;
        else if (balance < -1)
            return 1;
        else
            return 0;
    }

    public void removeNode(int reference) {
        Node node = findNode(reference, root);
        if (node == null) {
            System.out.println("Node does not exist.");
            return;
        }
        if (node == root) {
            if (root.getLeftNode() != null) {
                node = findHighestNode(root.getLeftNode());
            } else if (root.getRightNode() != null) {
                node = findLowestNode(root.getRightNode());
            } else {
                root = null;
                return;
            }
            removeRootNode(node);
            return;
        }

        int childCount = node.getChildCount();
        if (childCount == 0) {
            if (node.getParentNode().getReference() < node.getReference()) {
                node.getParentNode().setRightNode(null);
            } else {
                node.getParentNode().setLeftNode(null);
            }
        }
        if (childCount == 1) {
            if (node.getParentNode().getReference() < node.getReference()) {
                node.getParentNode().setRightNode(node.getOnlyChild());
                node.getParentNode().getRightNode().setParentNode(node.getParentNode());
            } else {
                node.getParentNode().setLeftNode(node.getOnlyChild());
                node.getParentNode().getLeftNode().setParentNode(node.getParentNode());
            }

            node = node.getOnlyChild();
            refreshNodeHeight(node);
        }
        if (childCount == 2) {
            ArrayList<Node> toDeleteChildren = node.getChildren();
            Node replacementNode = null;
            Node replacementParent = null;

            if (node == node.getParentNode().getRightNode()) {
                replacementNode = findHighestNode(node.getLeftNode());
                replacementParent = replacementNode.getParentNode();
                if (replacementNode.getChildCount() > 0) {
                    replacementParent.setRightNode(replacementNode.getOnlyChild());
                    replacementNode.getOnlyChild().setParentNode(replacementParent);
                } else {
                    replacementParent.setRightNode(null);

                }

                replacementNode.setParentNode(node.getParentNode());
                replacementNode.setChildren(node.getChildren());
                node.getParentNode().setRightNode(replacementNode);
            } else {
                //node.getParentNode().setLeftNode(null);

                replacementNode = findLowestNode(node.getRightNode());
                replacementParent = replacementNode.getParentNode();
                if (replacementNode.getChildCount() > 0) {
                    replacementParent.setLeftNode(replacementNode.getOnlyChild());
                    replacementNode.getOnlyChild().setParentNode(replacementParent);
                } else {
                    replacementParent.setLeftNode(null);

                }

                replacementNode.setParentNode(node.getParentNode());
                replacementNode.setChildren(node.getChildren());
                node.getParentNode().setLeftNode(replacementNode);
                refreshNodeHeight(replacementNode);
            }

            node = null;
        }
        nodeCount--;
    }

    protected void removeRootNode(Node toSwap) {

        int childCount = toSwap.getChildCount();
        Node parent = toSwap.getParentNode();
        Node leafNode = null;

        if (parent.getLeftNode() == toSwap) {
            if (parent.getRightNode() != null) {
                leafNode = findLowestNode(parent.getRightNode());
            }
        } else {
            if (parent.getLeftNode() != null) {
                leafNode = findHighestNode(parent.getLeftNode());
            }
        }

        if (childCount == 0) {
            if (parent.getLeftNode() == toSwap)
                parent.setLeftNode(null);
            else
                parent.setRightNode(null);

            toSwap.setChildren(root.getChildren());
            root = toSwap;

        } else if (childCount == 1) {

            toSwap.getOnlyChild().setParentNode(parent);

            if (parent.getLeftNode() == toSwap)
                parent.setLeftNode(toSwap.getOnlyChild());
            else
                parent.setRightNode(toSwap.getOnlyChild());

            toSwap.setChildren(root.getChildren());
            root = toSwap;
        }

        root.setParentNode(null);
        if (parent.getReference() == 12571) {
            int y = 0;
        }
        rebalanceTree(parent, leafNode);

    }

    protected Node findHighestNode(Node node) {
        if (node.getRightNode() != null) {
            return findHighestNode(node.getRightNode());
        } else {
            return node;
        }
    }

    protected Node findLowestNode(Node node) {
        if (node.getLeftNode() != null) {
            return findLowestNode(node.getLeftNode());
        } else {
            return node;
        }
    }

    public Node findNode(int reference, Node node) {
        if (node.getReference() == reference)
            return node;
        if (node.getLeftNode() == null && node.getRightNode() == null)
            return null;
        if (node.getRightNode() != null) {
            if (reference > node.getReference()) {
                return findNode(reference, node.getRightNode());
            }
        }
        if (node.getLeftNode() != null) {
            if (reference < node.getReference()) {
                return findNode(reference, node.getLeftNode());
            }
        }
        return null;
    }

    public void printTreeNodes(Node node) {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        if (node.getLeftNode() != null) {
            printTreeNodes(node.getLeftNode());
        }
        System.out.print(node.getReference() + " ");
        if (node.getRightNode() != null) {
            printTreeNodes(node.getRightNode());
        }
    }

    public void printTreeStructure() {
        OutputStreamWriter output = new OutputStreamWriter(System.out);
        try {
            getRoot().printTree(output);
            output.flush();
            System.out.println("\n\n\n");
        } catch (Exception e) {
            System.out.println("Cannot print tree.");
        }
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public Node getRoot() {
        return root;
    }

    public void getNodesAsArrayList(ArrayList<Node> arr, Node node) {
        if (node != null) {
            arr.add(node);
        }
        if (node.getLeftNode() != null || node.getRightNode() != null) {
            if (node.getRightNode() != null) {
                getNodesAsArrayList(arr, node.getRightNode());
            }
            if (node.getLeftNode() != null) {
                getNodesAsArrayList(arr, node.getLeftNode());
            }
        }
    }
}
