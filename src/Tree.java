
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

    public void addNode(Node newNode) {
        if (root == null) {
            root = newNode;
            nodeCount++;
            return;
        }
        if (findNode(newNode.getReference(), root) != null) {
            return;
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
        printTreeStructure();
        rebalanceTree(node);
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

    private void rebalanceTree(Node node) {
        Node leafNode = node;
        if (node.getReference() == 12810) {
            node = node;
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
        Node leftNode = node.getLeftNode();
        Node rightNode = node.getRightNode();
        Node temp = node;
        ArrayList<Node> arr = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            if (leafNode.getReference() < temp.getReference()) {
                arr.add(temp.getLeftNode());
                temp = temp.getLeftNode();
            } else {
                arr.add(temp.getRightNode());
                temp = temp.getRightNode();
            }
        }
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
        }
        if (childCount == 2) {
            ArrayList<Node> toDeleteChildren = node.getChildren();
            if (node.getReference() > node.getParentNode().getReference())
                node.getParentNode().setRightNode(null);
            else
                node.getParentNode().setLeftNode(null);
            for (Node n : toDeleteChildren) {
                if (n != null) {
                    addNode(n);
                    nodeCount--;
                }
            }
        }
        nodeCount--;
    }

    protected void removeRootNode(Node toSwap) {
//        root.setReference(toSwap.getReference());
//        root.setDescription(toSwap.getDescription());
//        root.setPrice(toSwap.getPrice());
//        int childCount = toSwap.getChildCount();
//        if (childCount == 0) {
//            System.out.println("toSwap is null");
//            toSwap = null;
//        } else if (childCount == 1) {
//            toSwap.setReference(toSwap.getOnlyChild().getReference());
//            toSwap.setDescription(toSwap.getOnlyChild().getDescription());
//            toSwap.setPrice(toSwap.getOnlyChild().getPrice());
//            toSwap.setChildren(toSwap.getOnlyChild().getChildren());
//        }

        int childCount = toSwap.getChildCount();
        if (childCount == 0) {

            if (toSwap.getParentNode().getLeftNode() == toSwap)
                toSwap.getParentNode().setLeftNode(null);
            else
                toSwap.getParentNode().setRightNode(null);
            root = toSwap;

        } else if (childCount == 1) {

            if (toSwap.getParentNode().getLeftNode() == toSwap)
                toSwap.getParentNode().setLeftNode(toSwap.getOnlyChild());
            else
                toSwap.getParentNode().setRightNode(toSwap.getOnlyChild());

            toSwap.setChildren(root.getChildren());
            root = toSwap;
        }

        root.setParentNode(null);
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
