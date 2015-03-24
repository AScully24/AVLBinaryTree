
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends javax.swing.JDialog {

    private static final ArrayList<String> itemData = new ArrayList<>();
    private static final ArrayList<String> setData = new ArrayList<>();
    private static Tree itemAVLTree;
    private static Tree setAVLTree;

    public GUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadDataToArrayList();
        loadLinkedList();
    }

    private void loadDataToArrayList() {
        Scanner sc = new Scanner(CommandGUI.class.getResourceAsStream("Test-Data.csv"));
        sc.nextLine();
        while (sc.hasNextLine()) {
            itemData.add(sc.nextLine());
        }
        sc.close();
        sc = new Scanner(CommandGUI.class.getResourceAsStream("Test-Data-Sets.csv"));
        sc.nextLine();
        while (sc.hasNextLine()) {
            setData.add(sc.nextLine());
        }
    }

    private void loadLinkedList() {
        itemAVLTree = new Tree();
        for (String s : itemData) {
            String lineData[] = s.split(",");
            itemAVLTree.addNode(new ItemNode(Integer.parseInt(lineData[0]), lineData[1], Double.parseDouble(lineData[2])));
        }
        setAVLTree = new Tree();
        for (String s : setData) {
            String lineData[] = s.split(",");
            SetNode newNode = new SetNode(Integer.parseInt(lineData[0]), lineData[1], Double.parseDouble(lineData[2]));
            for (int i = 4; i < lineData.length; i++) {
                newNode.addToItemRefs((ItemNode) itemAVLTree.findNode(Integer.parseInt(lineData[i]), itemAVLTree.getRoot()));
            }
            setAVLTree.addNode(newNode);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        buttonAdd = new javax.swing.JButton();
        buttonSearch = new javax.swing.JButton();
        buttonRemove = new javax.swing.JButton();
        buttonPrint = new javax.swing.JButton();
        buttonPrintSet = new javax.swing.JButton();
        buttonListItemSet = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("My Data Structures");
        buttonAdd.setText("Add Item");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });
        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });
        buttonRemove.setText("Remove Item");
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });
        buttonPrint.setText("Print Item");
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintActionPerformed(evt);
            }
        });
        buttonPrintSet.setText("Print Sets");
        buttonPrintSet.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintSetActionPerformed(evt);
            }
        });
        buttonListItemSet.setText("List Item set");
        buttonListItemSet.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonListItemSetActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(buttonAdd).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE).addComponent(buttonPrintSet)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(buttonPrint).addComponent(buttonRemove)).addGap(0, 0, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addComponent(buttonSearch).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(buttonListItemSet))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(23, 23, 23).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(buttonPrintSet).addComponent(buttonAdd)).addGap(18, 18, 18).addComponent(buttonRemove).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(buttonSearch).addComponent(buttonListItemSet)).addGap(18, 18, 18).addComponent(buttonPrint).addContainerGap(103, Short.MAX_VALUE)));
        pack();
    }

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {
        JTextField itemRef = new JTextField(20);
        JTextField itemDesc = new JTextField(20);
        JTextField itemPrice = new JTextField(20);
        JPanel p = new JPanel();
        p.add(new JLabel("Reference No: "));
        p.add(itemRef);
        p.add(Box.createHorizontalStrut(15));
        p.add(new JLabel("Description"));
        p.add(itemDesc);
        p.add(Box.createHorizontalStrut(15));
        p.add(new JLabel("Price"));
        p.add(itemPrice);
        int result = JOptionPane.showConfirmDialog(null, p, "Add an item", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int ref = Integer.parseInt(itemRef.getText());
            String description = itemDesc.getText();
            double price = Double.parseDouble(itemPrice.getText());
            itemAVLTree.addNode(new ItemNode(ref, description, price));
        } else if (result == JOptionPane.CANCEL_OPTION) {
        }
    }

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) {
        itemAVLTree.printTreeStructure();
    }

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {
        JTextField itemRef = new JTextField(20);
        JPanel p = new JPanel();
        p.add(new JLabel("Reference No: "));
        p.add(itemRef);
        int result = JOptionPane.showConfirmDialog(null, p, "Add an item", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int ref = Integer.parseInt(itemRef.getText());
            ItemNode toRemove = (ItemNode) itemAVLTree.findNode(ref, itemAVLTree.getRoot());
            
            itemAVLTree.removeNode(ref);
            
            ArrayList<Node> setSearch = new ArrayList<>();
            setAVLTree.getNodesAsArrayList(setSearch, setAVLTree.getRoot());
            for (Node sr : setSearch) {
                SetNode s = (SetNode) sr;
                if (s.removeItemRef(toRemove)) {
                    System.out.println("Item removed from set: " + s.getDescription());
                    break;
                }
            }
        } else if (result == JOptionPane.CANCEL_OPTION) {
        }
    }

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {
        JTextField itemRef = new JTextField(20);
        JPanel p = new JPanel();
        p.add(new JLabel("Reference No: "));
        p.add(itemRef);
        int result = JOptionPane.showConfirmDialog(null, p, "Add an item", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int ref = Integer.parseInt(itemRef.getText());
            System.out.println(itemAVLTree.findNode(ref, itemAVLTree.getRoot()).toString());
        } else if (result == JOptionPane.CANCEL_OPTION) {
        }
    }

    private void buttonPrintSetActionPerformed(java.awt.event.ActionEvent evt) {
        setAVLTree.printTreeStructure();
    }

    private void buttonListItemSetActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<Node> setSearch = new ArrayList<>();
        setAVLTree.getNodesAsArrayList(setSearch, setAVLTree.getRoot());
        JTextField itemRef = new JTextField(20);
        JPanel p = new JPanel();
        p.add(new JLabel("Reference No: "));
        p.add(itemRef);
        int result = JOptionPane.showConfirmDialog(null, p, "Find item sets", JOptionPane.OK_CANCEL_OPTION);
        boolean itemFound = false;
        String setDesc = "";
        if (result == JOptionPane.OK_OPTION) {
            int ref = Integer.parseInt(itemRef.getText());
            ItemNode toFind = (ItemNode) itemAVLTree.findNode(ref, itemAVLTree.getRoot());
            
            for (Node sr : setSearch) {
                SetNode s = (SetNode) sr;
                if (s.getItemByRef(toFind) != null) {
                    itemFound = true;
                    setDesc = s.getDescription();
                    break;
                }
            }
            if (itemFound) {
                System.out.printf("Item Reference %d found in Set %s\n", ref, setDesc);
            } else {
                System.out.printf("Item Reference %d not found\n", ref);
            }
        } else if (result == JOptionPane.CANCEL_OPTION) {
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                GUI dialog = new GUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonListItemSet;
    private javax.swing.JButton buttonPrint;
    private javax.swing.JButton buttonPrintSet;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JButton buttonSearch;
}
