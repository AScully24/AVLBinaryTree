
import AVLTree.Node;
import AVLTree.Tree;
import AVLTree.RepoNode;
import AVLTree.ItemNode;
import AVLTree.SetNode;
import com.sun.media.sound.ModelAbstractChannelMixer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anthony Scully
 */
public class GUI extends javax.swing.JFrame {

    private Tree repositries;
    private RepoNode currentRepo;
    private SetNode currentSet;

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();

        // Default values.
        loadDefaultRep();
        updateItemList();
        updateSetComboBox();
        updateSetList();
    }

    private void updateSetList() {
        currentSet = (SetNode) jcSets.getSelectedItem();

        if (currentSet == null) {
            ArrayList<String> setItems = new ArrayList<>();
            setItems.add("");
            JList newSetBox = new JList(setItems.toArray());
            listSetItems.setModel(newSetBox.getModel());
        } else {
            ArrayList<ItemNode> setItems = currentSet.getItemRefs();
            Collections.sort(setItems);
            JList newSetBox = new JList(setItems.toArray());
            listSetItems.setModel(newSetBox.getModel());
        }
    }

    private void updateSetComboBox() {
        ArrayList<Node> sets = new ArrayList<>();
        currentRepo.getSets().getNodesAsArrayList(sets, currentRepo.getSets().getRoot());
        Collections.sort(sets);
        JComboBox newSetBox = new JComboBox(sets.toArray());
        jcSets.setModel(newSetBox.getModel());
    }

    private void updateItemList() {
        ArrayList<Node> items = new ArrayList<>();
        currentRepo.getItems().getNodesAsArrayList(items, currentRepo.getItems().getRoot());
        Collections.sort(items);
        JList newItemList = new JList(items.toArray());
        listItems.setModel(newItemList.getModel());
    }

    private void loadDefaultRep() {
        repositries = new Tree();
        addRepository("repo1");
        currentRepo = (RepoNode) repositries.getRoot();
        updateRepositoryComboBox();

    }

    private void updateRepositoryComboBox() {
        ArrayList<Node> arr = new ArrayList<>();
        repositries.getNodesAsArrayList(arr, repositries.getRoot());
        Collections.sort(arr);
        JComboBox newComboBox = new JComboBox(arr.toArray());
        jcRepositories.setModel(newComboBox.getModel());
    }

    private void addItemsFromFile(String location, RepoNode newRepo) {
        /*
         * 
         * 
         * Adds the new items.
         */
        Scanner sc = null;
        ArrayList<String> items = new ArrayList<>();
        sc = new Scanner(GUI.class.getResourceAsStream(location + "items.csv"));

        sc.nextLine();
        while (sc.hasNextLine()) {
            items.add(sc.nextLine());
        }

        for (String i : items) {
            String lineData[] = i.split(",");
            ItemNode newNode = new ItemNode(Integer.parseInt(lineData[0]), lineData[1], Double.parseDouble(lineData[2]));
            newRepo.addItem(newNode, null);
        }
        sc.close();
    }

    public void addSetsFromFile(String location, RepoNode newRepo) {
        /*
         *
         *
         * Adds new sets 
         */
        Scanner sc = null;
        ArrayList<String> sets = new ArrayList<>();
        sc = new Scanner(GUI.class.getResourceAsStream(location + "sets.csv"));
        sc.nextLine();
        while (sc.hasNextLine()) {
            sets.add(sc.nextLine());
        }

        for (String s : sets) {

            // Creates the new set.
            String lineData[] = s.split(",");
            SetNode newSet = new SetNode(Integer.parseInt(lineData[0]),
                    lineData[1], Double.parseDouble(lineData[2]));

            // Adds the items to the set.
            for (int i = 4; i < lineData.length; i++) {
                int ref = Integer.parseInt(lineData[i]);
                ItemNode temp = newRepo.findItem(ref);
                if (temp != null) {
                    newSet.addToItemRefs(temp);
//                    temp.addToRelatedSet(newSet);
                }
            }
            newRepo.addSet(newSet);
        }
        sc.close();

    }

    private void addRepository(String location) {

        RepoNode newRepo = new RepoNode(repositries.getNodeCount() + 1, location);

        String newLocation = location;

        if (System.getProperty("os.name").toLowerCase().equals("linux")) {
            newLocation += "//";
        } else newLocation += "\\";

        addItemsFromFile(newLocation, newRepo);
        addSetsFromFile(newLocation, newRepo);

        currentSet = (SetNode) newRepo.getSets().getRoot();
        repositries.addNode(repositries.getRoot(), newRepo);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpRepositories = new javax.swing.JPanel();
        jcRepositories = new javax.swing.JComboBox();
        jlReposiroties = new javax.swing.JLabel();
        butAddItemToSet1 = new javax.swing.JButton();
        butAddItemToSet2 = new javax.swing.JButton();
        butAddItemToSet3 = new javax.swing.JButton();
        butAddItemToSet4 = new javax.swing.JButton();
        jpItems = new javax.swing.JPanel();
        jlItems = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listItems = new javax.swing.JList();
        jpSets = new javax.swing.JPanel();
        jlSets = new javax.swing.JLabel();
        jcSets = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        listSetItems = new javax.swing.JList();
        jpItemButtons = new javax.swing.JPanel();
        butAddItem = new javax.swing.JButton();
        butDeleteSelectedItem = new javax.swing.JButton();
        butFindItem = new javax.swing.JButton();
        butFindItem1 = new javax.swing.JButton();
        jpSetButtons = new javax.swing.JPanel();
        butAddItemToSet = new javax.swing.JButton();
        butRemoveItemFromSet = new javax.swing.JButton();
        butDeleteSet = new javax.swing.JButton();
        butDeleteSet1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("mainFrame"); // NOI18N

        jpRepositories.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpRepositories.setPreferredSize(new java.awt.Dimension(1152, 79));

        jcRepositories.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcRepositories.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcRepositories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcRepositoriesActionPerformed(evt);
            }
        });

        jlReposiroties.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jlReposiroties.setText("Repositories");

        butAddItemToSet1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        butAddItemToSet1.setText("Add a Repo");
        butAddItemToSet1.setToolTipText("");
        butAddItemToSet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAddItemToSet1ActionPerformed(evt);
            }
        });

        butAddItemToSet2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        butAddItemToSet2.setText("Print Items");
        butAddItemToSet2.setToolTipText("");
        butAddItemToSet2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAddItemToSet2ActionPerformed(evt);
            }
        });

        butAddItemToSet3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        butAddItemToSet3.setText("Print Sets");
        butAddItemToSet3.setToolTipText("");
        butAddItemToSet3.setActionCommand("Print Sets");
        butAddItemToSet3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAddItemToSet3ActionPerformed(evt);
            }
        });

        butAddItemToSet4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        butAddItemToSet4.setText("Print Rep");
        butAddItemToSet4.setToolTipText("");
        butAddItemToSet4.setActionCommand("Print Repo");
        butAddItemToSet4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAddItemToSet4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpRepositoriesLayout = new javax.swing.GroupLayout(jpRepositories);
        jpRepositories.setLayout(jpRepositoriesLayout);
        jpRepositoriesLayout.setHorizontalGroup(
            jpRepositoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRepositoriesLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jlReposiroties)
                .addGap(34, 34, 34)
                .addComponent(jcRepositories, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129)
                .addComponent(butAddItemToSet1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(butAddItemToSet2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(butAddItemToSet3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(butAddItemToSet4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpRepositoriesLayout.setVerticalGroup(
            jpRepositoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRepositoriesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jpRepositoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcRepositories, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlReposiroties)
                    .addComponent(butAddItemToSet1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butAddItemToSet2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butAddItemToSet3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butAddItemToSet4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpItems.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpItems.setMinimumSize(new java.awt.Dimension(0, 0));

        jlItems.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jlItems.setText("Items");

        listItems.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listItems);

        javax.swing.GroupLayout jpItemsLayout = new javax.swing.GroupLayout(jpItems);
        jpItems.setLayout(jpItemsLayout);
        jpItemsLayout.setHorizontalGroup(
            jpItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpItemsLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jpItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpItemsLayout.createSequentialGroup()
                        .addComponent(jlItems)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jpItemsLayout.setVerticalGroup(
            jpItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpItemsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlItems)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpSets.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpSets.setPreferredSize(new java.awt.Dimension(630, 480));

        jlSets.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jlSets.setText("Sets");

        jcSets.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcSets.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcSets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcSetsActionPerformed(evt);
            }
        });

        listSetItems.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listSetItems);

        javax.swing.GroupLayout jpSetsLayout = new javax.swing.GroupLayout(jpSets);
        jpSets.setLayout(jpSetsLayout);
        jpSetsLayout.setHorizontalGroup(
            jpSetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSetsLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jlSets)
                .addGap(34, 34, 34)
                .addComponent(jcSets, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(198, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSetsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jpSetsLayout.setVerticalGroup(
            jpSetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSetsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcSets, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlSets))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jpItemButtons.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpItemButtons.setPreferredSize(new java.awt.Dimension(630, 128));

        butAddItem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        butAddItem.setText("Add item");
        butAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAddItemActionPerformed(evt);
            }
        });

        butDeleteSelectedItem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        butDeleteSelectedItem.setText("Delete Selected item");
        butDeleteSelectedItem.setToolTipText("");
        butDeleteSelectedItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butDeleteSelectedItemActionPerformed(evt);
            }
        });

        butFindItem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        butFindItem.setText("Find Item");
        butFindItem.setToolTipText("");
        butFindItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butFindItemActionPerformed(evt);
            }
        });

        butFindItem1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        butFindItem1.setText("Add Items From File");
        butFindItem1.setToolTipText("");
        butFindItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butFindItem1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpItemButtonsLayout = new javax.swing.GroupLayout(jpItemButtons);
        jpItemButtons.setLayout(jpItemButtonsLayout);
        jpItemButtonsLayout.setHorizontalGroup(
            jpItemButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpItemButtonsLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jpItemButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpItemButtonsLayout.createSequentialGroup()
                        .addComponent(butDeleteSelectedItem)
                        .addGap(18, 18, 18)
                        .addComponent(butFindItem1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpItemButtonsLayout.createSequentialGroup()
                        .addComponent(butAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(butFindItem, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(289, Short.MAX_VALUE))
        );
        jpItemButtonsLayout.setVerticalGroup(
            jpItemButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpItemButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpItemButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butFindItem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpItemButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butDeleteSelectedItem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butFindItem1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jpSetButtons.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpSetButtons.setPreferredSize(new java.awt.Dimension(630, 128));

        butAddItemToSet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        butAddItemToSet.setText("Add to Set");
        butAddItemToSet.setToolTipText("");
        butAddItemToSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAddItemToSetActionPerformed(evt);
            }
        });

        butRemoveItemFromSet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        butRemoveItemFromSet.setText("Remove from set");
        butRemoveItemFromSet.setToolTipText("");
        butRemoveItemFromSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butRemoveItemFromSetActionPerformed(evt);
            }
        });

        butDeleteSet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        butDeleteSet.setText("Delete Set");
        butDeleteSet.setToolTipText("");
        butDeleteSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butDeleteSetActionPerformed(evt);
            }
        });

        butDeleteSet1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        butDeleteSet1.setText("Add Set From File");
        butDeleteSet1.setToolTipText("");
        butDeleteSet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butDeleteSet1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpSetButtonsLayout = new javax.swing.GroupLayout(jpSetButtons);
        jpSetButtons.setLayout(jpSetButtonsLayout);
        jpSetButtonsLayout.setHorizontalGroup(
            jpSetButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSetButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSetButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jpSetButtonsLayout.createSequentialGroup()
                        .addComponent(butDeleteSet1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(butAddItemToSet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpSetButtonsLayout.createSequentialGroup()
                        .addComponent(butDeleteSet, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(butRemoveItemFromSet, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpSetButtonsLayout.setVerticalGroup(
            jpSetButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSetButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSetButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butAddItemToSet, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butDeleteSet1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpSetButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butRemoveItemFromSet, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butDeleteSet, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpRepositories, javax.swing.GroupLayout.DEFAULT_SIZE, 1378, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jpItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpItemButtons, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpSets, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                            .addComponent(jpSetButtons, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpRepositories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpSets, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                    .addComponent(jpItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpSetButtons, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(jpItemButtons, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAddItemActionPerformed

        JPanel panel = new JPanel();
        MaskFormatter refMF = null;
        MaskFormatter priceMF = null;
        try {
            refMF = new MaskFormatter("#####");
            priceMF = new MaskFormatter("###########");
        } catch (ParseException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JFormattedTextField referenceField = new JFormattedTextField(refMF);
        referenceField.setValue("00000");
        JFormattedTextField priceField = new JFormattedTextField();
        JTextArea descriptionField = new JTextArea(10, 30);

        panel.add(new JLabel("Reference"));
        panel.add(referenceField);
        // panel.add(Box.createHorizontalStrut(15));

        panel.add(new JLabel("Price"));
        panel.add(priceField);
        //panel.add(Box.createVerticalStrut(5));

        panel.add(new JLabel("Description"));
        panel.add(descriptionField);
        //panel.add(Box.createHorizontalStrut(15));

        int result = JOptionPane.showConfirmDialog(null, panel, "Add a new item",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            int ref = Integer.parseInt(referenceField.getText());
            String description = descriptionField.getText();
            double price = Double.parseDouble(priceField.getText());

            ItemNode newItem = new ItemNode(ref, description, price);

            currentRepo.addItem(newItem, null);

            updateItemList();

        } else if (result == JOptionPane.CANCEL_OPTION) {

        }

    }//GEN-LAST:event_butAddItemActionPerformed

    private void butDeleteSelectedItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butDeleteSelectedItemActionPerformed
        ItemNode toRemove = (ItemNode) listItems.getSelectedValue();

        if (toRemove != null) {
            ArrayList<Node> arr = new ArrayList<>();
            repositries.getNodesAsArrayList(arr, repositries.getRoot());
            int reference = toRemove.getReference();
            for (Node r : arr) {
                RepoNode repo = (RepoNode) r;
                System.out.println("Deleted Item from " + repo.getName());
                repo.removeItem(reference);
            }

            updateItemList();
            updateSetList();
        } else {
            JOptionPane.showMessageDialog(null, "Please select an item from the item list on the left.");
        }

    }//GEN-LAST:event_butDeleteSelectedItemActionPerformed

    private void butFindItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butFindItemActionPerformed
        JPanel panel = new JPanel();
        MaskFormatter mf = null;
        try {
            mf = new MaskFormatter("#####");
        } catch (ParseException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        JFormattedTextField referenceField = new JFormattedTextField(mf);
        referenceField.setValue("");
        panel.add(new JLabel("Reference"));
        panel.add(referenceField);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        while (true) {

            int result = JOptionPane.showConfirmDialog(null, panel, "Find an item",
                    JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {

                String userInput = referenceField.getText();
                // If user doesn't put any info in, a message appears
                if (userInput.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "No text input.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    ArrayList<Node> allRepos = new ArrayList<>();
                    ItemNode item = null;
                    int ref = Integer.parseInt(referenceField.getText());

                    repositries.getNodesAsArrayList(allRepos, repositries.getRoot());

                    ItemNode i = currentRepo.findItem(ref);
                    String repoName = "";
                    for (Node na : allRepos) {
                        RepoNode n = (RepoNode) na;
                        ItemNode temp = n.findItem(ref);
                        if (temp != null) {
                            item = temp;
                            repoName = n.getName() + "\n";
                            temp = null;
                        }
                    }

                    if (item != null) {
                        JOptionPane.showMessageDialog(panel, "Item found in: \n" + repoName + "\n" + item.toString(), "Item found", JOptionPane.PLAIN_MESSAGE, null);
                    } else
                        JOptionPane.showMessageDialog(panel, "Item not found.", "Error", JOptionPane.ERROR_MESSAGE);

                    break;
                }
            } else if (result == JOptionPane.CANCEL_OPTION) {
                break;
            }
        }
    }//GEN-LAST:event_butFindItemActionPerformed

    private void butAddItemToSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAddItemToSetActionPerformed
        ItemNode item = (ItemNode) listItems.getSelectedValue();
        if (item != null) {
            if (currentSet.addToItemRefs(item)) {
                updateSetList();
            } else
                JOptionPane.showMessageDialog(null, "Item already exists in the set list.");

        } else {
            JOptionPane.showMessageDialog(null, "Please select an item from the item list on the left.");
        }
    }//GEN-LAST:event_butAddItemToSetActionPerformed

    private void removeItemFromSet(ItemNode toRemove, boolean getSimilarItems) {

        if (toRemove != null) {
            int reference = toRemove.getReference();
            String description = toRemove.getDescription();
            ArrayList<Node> allRepos = new ArrayList<>();
            ArrayList<ItemNode> similarItems = new ArrayList<>();
            String repoList = "";

            repositries.getNodesAsArrayList(allRepos, repositries.getRoot());

            for (Node rep_ : allRepos) {

                RepoNode rep = (RepoNode) rep_;
                if (rep.removeItem(toRemove.getReference())) {
                    repoList += rep.getName() + "\n";
                }
                if (getSimilarItems) {

                    rep.findSimilarItems(description, similarItems);
                }

            }

            if (getSimilarItems) {

                // Removes duplicates.
                for (int j = 0; j < similarItems.size(); j++) {
                    ItemNode s = similarItems.get(j);
                    for (int i = 0; i < listSetItems.getModel().getSize(); i++) {
                        ItemNode l = (ItemNode) listSetItems.getModel().getElementAt(i);
                        System.out.println(l.getReference());
                        if (s.getReference() == l.getReference() || reference == s.getReference()) {
                            similarItems.remove(s);
                            j--;
                        }
                    }
                }

                updateItemList();
                updateSetList();

                JOptionPane.showMessageDialog(null, "Item removed from: \n" + repoList, "Item Removed", JOptionPane.PLAIN_MESSAGE);
                // Lets the user choose a new item to add to the set.
                JPanel panel = new JPanel();
                JList listReplacements = new JList(similarItems.toArray());

                panel.add(new JLabel("Please choose a replacemant item."));
                panel.add(listReplacements);

                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                int result = JOptionPane.showConfirmDialog(null, panel, "Add a new item",
                        JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    ItemNode i = (ItemNode) listReplacements.getSelectedValue();
                    //i.addToRelatedSet(currentSet);
                    if (i != null) {
                        currentSet.addToItemRefs(i);
                    }

                } else if (result == JOptionPane.CANCEL_OPTION) {

                }
            }

//
//            // Update relevant locations
            updateItemList();
            updateSetList();
        } else {
            JOptionPane.showMessageDialog(null, "Please select an item from the set item list on the right.");
        }
    }


    private void butRemoveItemFromSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butRemoveItemFromSetActionPerformed
        ItemNode toRemove = (ItemNode) listSetItems.getSelectedValue();
        removeItemFromSet(toRemove, true);
    }//GEN-LAST:event_butRemoveItemFromSetActionPerformed

    private void butDeleteSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butDeleteSetActionPerformed

        for (int i = 0; i < listSetItems.getModel().getSize(); i++) {

            ItemNode l = (ItemNode) listSetItems.getModel().getElementAt(i);
            System.out.println(l.getReference());
            removeItemFromSet(l, false);
            i--;
        }

        currentRepo.removeSet(currentSet.getReference());
        updateSetComboBox();
        updateItemList();
        updateSetList();
    }//GEN-LAST:event_butDeleteSetActionPerformed

    private void jcSetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcSetsActionPerformed
        updateSetList();
    }//GEN-LAST:event_jcSetsActionPerformed

    private void jcRepositoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcRepositoriesActionPerformed
        currentRepo = (RepoNode) jcRepositories.getSelectedItem();
        updateItemList();
        updateSetComboBox();
        updateSetList();
    }//GEN-LAST:event_jcRepositoriesActionPerformed

    private void butAddItemToSet1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAddItemToSet1ActionPerformed
        JPanel panel = new JPanel();
        JTextField fileLoc = new JTextField(20);

        panel.add(fileLoc);
        int result = JOptionPane.showConfirmDialog(null, panel, "Add a new Repo.",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            addRepository(fileLoc.getText());
            updateRepositoryComboBox();
        } else if (result == JOptionPane.CANCEL_OPTION) {

        }

    }//GEN-LAST:event_butAddItemToSet1ActionPerformed

    private void butFindItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butFindItem1ActionPerformed
        JPanel panel = new JPanel();
        JTextField fileLoc = new JTextField(20);

        panel.add(fileLoc);
        int result = JOptionPane.showConfirmDialog(null, panel, "Add an item from file.",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            addItemsFromFile(fileLoc.getText(), currentRepo);
            updateItemList();
        } else if (result == JOptionPane.CANCEL_OPTION) {

        }
    }//GEN-LAST:event_butFindItem1ActionPerformed

    private void butDeleteSet1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butDeleteSet1ActionPerformed
        JPanel panel = new JPanel();
        JTextField fileLoc = new JTextField(20);

        panel.add(fileLoc);
        int result = JOptionPane.showConfirmDialog(null, panel, "Add a set from file.",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            addSetsFromFile(fileLoc.getText(), currentRepo);
            updateSetComboBox();
            updateSetList();
        } else if (result == JOptionPane.CANCEL_OPTION) {

        }
    }//GEN-LAST:event_butDeleteSet1ActionPerformed

    private void butAddItemToSet2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAddItemToSet2ActionPerformed
        currentRepo.printItemsTree();
    }//GEN-LAST:event_butAddItemToSet2ActionPerformed

    private void butAddItemToSet3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAddItemToSet3ActionPerformed
        currentRepo.printSetsTree();
    }//GEN-LAST:event_butAddItemToSet3ActionPerformed

    private void butAddItemToSet4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAddItemToSet4ActionPerformed
        repositries.printTreeStructure();
    }//GEN-LAST:event_butAddItemToSet4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAddItem;
    private javax.swing.JButton butAddItemToSet;
    private javax.swing.JButton butAddItemToSet1;
    private javax.swing.JButton butAddItemToSet2;
    private javax.swing.JButton butAddItemToSet3;
    private javax.swing.JButton butAddItemToSet4;
    private javax.swing.JButton butDeleteSelectedItem;
    private javax.swing.JButton butDeleteSet;
    private javax.swing.JButton butDeleteSet1;
    private javax.swing.JButton butFindItem;
    private javax.swing.JButton butFindItem1;
    private javax.swing.JButton butRemoveItemFromSet;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox jcRepositories;
    private javax.swing.JComboBox jcSets;
    private javax.swing.JLabel jlItems;
    private javax.swing.JLabel jlReposiroties;
    private javax.swing.JLabel jlSets;
    private javax.swing.JPanel jpItemButtons;
    private javax.swing.JPanel jpItems;
    private javax.swing.JPanel jpRepositories;
    private javax.swing.JPanel jpSetButtons;
    private javax.swing.JPanel jpSets;
    private javax.swing.JList listItems;
    private javax.swing.JList listSetItems;
    // End of variables declaration//GEN-END:variables
}
