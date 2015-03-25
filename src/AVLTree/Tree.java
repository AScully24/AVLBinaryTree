package AVLTree;

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
        rebalanceTree(node);
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

    private void rebalanceTree(Node node) {
        while (node != null) {
            refreshNodeHeight(node);
            balanceHandler(node);
            node = node.getParentNode();
        }
    }

    private ArrayList<Node> getXandY(Node node) {
        Node temp = node;
        ArrayList<Node> arr = new ArrayList<>();
        int tempBalance = 0;
        for (int i = 0; i < 3; i++) {
            tempBalance = checkBalance(temp);
            if (tempBalance == UNBALANCED_LEFT) {
                temp = temp.getLeftNode();
            } else temp = temp.getRightNode();
            
            arr.add(temp);
        }
        return arr;
    }
    
    
    private void balanceHandler(Node node) {
        int balance = checkBalance(node);
        int rotationCount = 0;
        if (balance != 0) {
            ArrayList<Node> nodes = getXandY(node);
            rotationCount = rotationCase(nodes, balance);
            
            Node z = nodes.get(0);
            Node y = nodes.get(1);
            Node x = nodes.get(2);
            
            if (balance == UNBALANCED_RIGHT) {
                if (rotationCount == 1) {
                    rotateNodeLeft(z,y);
                } else if (rotationCount == 2) {
                    rotateNodeRight(y,y.getLeftNode());
                    rotateNodeLeft(node,y);
                }
            } else if (balance == UNBALANCED_LEFT) {
                if (rotationCount == 1) {
                    rotateNodeRight(node, y);
                } else if (rotationCount == 2) {
                    rotateNodeLeft(x,y);
                    rotateNodeRight(node,x);
                }
            }
        }
    }

    public int rotationCase(ArrayList<Node> arr, int balance) {
        Node x = (Node) arr.get(0), y = (Node) arr.get(1);
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

    private void rotateNodeLeft(Node x, Node y) {
        //Node x = node, y = node.getRightNode();
        Node a = x.getLeftNode(), b = y.getLeftNode(), c = y.getRightNode();
        Node masterParent = x.getParentNode();

        if (x == root)
            root = y;
        else {
            if (x.isLeftNode()) {
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

    private void rotateNodeRight(Node x, Node y) {
    //    Node x = node.getLeftNode(), y = node;
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

    private void replaceNode(Node node, Node toSwap) {

        if (node == toSwap) {
            System.out.println("Cannot replace node");
            return;
        }

        if (node.isLeftNode()) {
            node.getParentNode().setLeftNode(toSwap);
        } else if (node.isRightNode()) {
            node.getParentNode().setRightNode(toSwap);
        } else if (node.getParentNode() == null) {
        }

        if (toSwap != null) {
            toSwap.setParentNode(node.getParentNode());
        }
        node.setParentNode(null);
        node.setLeftNode(null);
        node.setRightNode(null);
    }

    public void removeNode(int reference) {
        Node node = findNode(reference, root);

        if (node == null) {
            System.out.println("Node cannot be deleted: Does not exist.");
            return;
        }

        Node parent = node.getParentNode();
        int childCount = node.getChildCount();

        if (childCount == 0) {
            replaceNode(node, null);
            rebalanceTree(parent);
        }

        if (childCount == 1) {
            Node nodeChild = node.getOnlyChild();
            replaceNode(node, nodeChild);
            rebalanceTree(nodeChild);
        }

        if (childCount == 2) {
            Node toSwap = findHighestNode(node.getLeftNode());
            Node toSwapParent = toSwap.getParentNode();
            Node toSWapChild = toSwap.getOnlyChild();

            System.out.println("Node: " + node.getReference() + "\t toSwap: " + toSwap.getReference());

            if (toSwapParent.getLeftNode() != null) {
                System.out.println("\t Parent: " + toSwapParent.getHeight());
            }

            if (toSwap.getReference() == 12567) {
                int o = 0;
            }

            replaceNode(toSwap, toSWapChild);

            node.setNodeData(toSwap.getNodeData());
            refreshNodeHeight(toSwapParent);

            if (toSwapParent != null) {
                //System.out.println("\t Parent: " + toSwapParent.getHeight());
                if (toSwapParent.getReference() == 12564) {
                    int o = 2;
                }
            }

            balanceHandler(toSwapParent);

            if (toSWapChild == null) {
                rebalanceTree(node);
            } else rebalanceTree(toSWapChild);

//            rebalanceTree(toSwapParent);
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
        rebalanceTree(parent);

    }

    public Node findHighestNode(Node node) {
        if (node.getRightNode() != null) {
            return findHighestNode(node.getRightNode());
        } else {
            return node;
        }
    }

    public Node findLowestNode(Node node) {
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
