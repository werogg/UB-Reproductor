package edu.ub.prog2.VinagreJorgeOteroJoel.vista;

import edu.ub.prog2.VinagreJorgeOteroJoel.controlador.Controlador;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.FitxerReproduible;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.Video;
import edu.ub.prog2.utils.AplicacioException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author wero
 */
public class AplicacioUB4Remastered extends javax.swing.JFrame {
    Controlador controlador;
    boolean playing;
    String selection, selectedAlbumPopup;
    DefaultListModel<String> dlm;
    
    /**
     * Creates new form AplicacioUB4Remastered
     */
    public AplicacioUB4Remastered() {
        // Init controller and try to load the saved data
        this.controlador = new Controlador();
        loadData();
        
        // Init the model of the main list (biblioteca + albums)
        dlm = new DefaultListModel<>();
        dlm.insertElementAt("Biblioteca", 0); // Insert biblioteca item
        
        // Init the rest of components
        initComponents();
        
        // Init the frame status and load the lists and tables
        this.setSize(1280, 720);
        this.refreshTableBiblioteca();
        this.refreshListAlbums();
        
        // Init the rest of variables
        playing = false;
        selection = "";
        jList1.setSelectedIndex(0); // Set the selected index to biblioteca on start
        
        
    }
    
    /**
     * Method to check if there's old data and ask to load it
     */
    private void loadData() {
        File file = new File("data.dat");
        if (file.exists()) {
            if (JOptionPane.showConfirmDialog(this, 
            "S'ha trobat un arxiu de dades guardat, vols carregar-lo?", "Carregar dades?", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                try {
                    controlador.carregarDadesDisc("data.dat");
                } catch (AplicacioException ex) {
                    Logger.getLogger(AplicacioUB4Remastered.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Method to refresh the main table with the library files
     */
    public void refreshTableBiblioteca() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        
        for (FitxerReproduible fr : controlador.getBibliotecaFiles()) {
            if (fr instanceof Video) { 
                model.addRow(new Object[]{fr.getDescripcio(), fr.getCamiAbsolut(), "Video", fr.getDurada(), fr.getUltimaModificacio()});
            } else {
                model.addRow(new Object[]{fr.getDescripcio(), fr.getCamiAbsolut(), "Audio", fr.getDurada(), fr.getUltimaModificacio()});
            }
        }
    }
    
    /**
     * Method to refresh the main table with a selected album files
     * @param string Name of the album to be loaded to the main table
     */
    public void refreshTableAlbum(String string) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        
        try {
            for (FitxerReproduible fr : controlador.mostrarAlbumFitxers(selection)) {
                if (fr instanceof Video) { 
                    model.addRow(new Object[]{fr.getDescripcio(), fr.getCamiAbsolut(), "Video", fr.getDurada(), fr.getUltimaModificacio()});
                } else {
                    model.addRow(new Object[]{fr.getDescripcio(), fr.getCamiAbsolut(), "Audio", fr.getDurada(), fr.getUltimaModificacio()});
                }
            }  
        } catch (AplicacioException ex) {
            Logger.getLogger(AplicacioUB4Remastered.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Refresh the main list with all the albums
     */
    public void refreshListAlbums() {
        dlm.clear();
        dlm.insertElementAt("Biblioteca", 0);
        
        controlador.mostrarLlistatAlbums().forEach((str) -> {
            dlm.addElement(str);
        });
    }
    
    /**
     * Refresh the items of the popup with all the albums
     */
    public void refreshPopupAlbums() {
        JMenuItem jmi; 
        jMenu2.removeAll();
        
        for (String str : controlador.mostrarLlistatAlbums()) {
            jmi = new JMenuItem(str);
            jmi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JMenuItem menuItem = (JMenuItem)e.getSource();
                        controlador.afegirFitxer(menuItem.getText(), jTable1.getSelectedRow());
                    } catch (AplicacioException ex) {
                        Logger.getLogger(AplicacioUB4Remastered.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            jMenu2.add(jmi);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu2.setText("Afegir a album...");
        jMenu2.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu2MenuSelected(evt);
            }
        });
        jPopupMenu1.add(jMenu2);

        jMenuItem3.setText("Eliminar arxiu");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem3);

        jMenuItem6.setText("Reproduir arxiu");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem6);

        jMenuItem4.setText("Eliminar arxiu del album");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem4);

        jMenuItem7.setText("Reproduir arxiu");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem7);

        jMenuItem5.setText("Eliminar album");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jPopupMenu3.add(jMenuItem5);

        jMenuItem8.setText("Reproduir album");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jPopupMenu3.add(jMenuItem8);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Vinotero Media Player");
        setBackground(new java.awt.Color(51, 51, 51));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setHorizontalScrollBar(null);

        jList1.setBackground(new java.awt.Color(51, 51, 51));
        jList1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jList1.setForeground(new java.awt.Color(159, 159, 159));
        jList1.setModel(dlm);
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setFocusable(false);
        jList1.setRequestFocusEnabled(false);
        jList1.setSelectionBackground(null);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jList1MouseReleased(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton10.setBackground(new java.awt.Color(51, 51, 51));
        jButton10.setForeground(new java.awt.Color(51, 51, 51));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-shuffle-24.png"))); // NOI18N
        jButton10.setBorder(null);
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setFocusable(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10);

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setForeground(new java.awt.Color(51, 51, 51));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-stop-squared-filled-24.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton9.setBackground(new java.awt.Color(51, 51, 51));
        jButton9.setForeground(new java.awt.Color(51, 51, 51));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-play-24.png"))); // NOI18N
        jButton9.setBorder(null);
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setFocusable(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9);

        jButton8.setBackground(new java.awt.Color(51, 51, 51));
        jButton8.setForeground(new java.awt.Color(51, 51, 51));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-pause-squared-filled-24.png"))); // NOI18N
        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8);

        jButton5.setBackground(new java.awt.Color(51, 51, 51));
        jButton5.setForeground(new java.awt.Color(51, 51, 51));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-end-filled-24.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusable(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5);

        jButton7.setBackground(new java.awt.Color(51, 51, 51));
        jButton7.setForeground(new java.awt.Color(51, 51, 51));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-repeat-24.png"))); // NOI18N
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setForeground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.setPreferredSize(getSize());

        jTable1.setBackground(new java.awt.Color(51, 51, 51));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Path", "Codec", "Duracio", "Afegit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setAutoscrolls(false);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setFillsViewportHeight(true);
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(102, 102, 102));
        jTable1.setMinimumSize(new java.awt.Dimension(100, 0));
        jTable1.setPreferredSize(new java.awt.Dimension(500, 500));
        jTable1.setRowHeight(30);
        jTable1.setRowMargin(5);
        jTable1.setSelectionBackground(new java.awt.Color(102, 102, 102));
        jTable1.setShowVerticalLines(false);
        jTable1.setSurrendersFocusOnKeystroke(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel3.add(jScrollPane1);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));
        jMenuBar1.setBorder(null);
        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenu1.setBackground(new java.awt.Color(51, 51, 51));
        jMenu1.setText("Media");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add-new-file.png"))); // NOI18N
        jMenuItem1.setText("Afegir fitxer");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new-folder.png"))); // NOI18N
        jMenuItem2.setText("Crear album");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        FrmAfegirFitxerMultimedia afegirFitxerMultimedia = new FrmAfegirFitxerMultimedia(this, controlador);
        afegirFitxerMultimedia.setVisible(true);
        
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        if (jList1.getSelectedIndex() > -1 && !jList1.getSelectedValue().equals(selection)) {
            selection = jList1.getSelectedValue();
            if (jList1.getSelectedIndex() == 0) {
                this.refreshTableBiblioteca();
            } else {
                this.refreshTableAlbum(selection);
            }
        }
        
        if (jList1.getSelectedIndex() == -1) jList1.setSelectedIndex(0);
    }//GEN-LAST:event_jList1ValueChanged

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        String input = JOptionPane.showInputDialog("Nom del album:");
        try {
            controlador.afegirAlbum(input);
        } catch (AplicacioException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
        }
        
        this.refreshListAlbums();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jList1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseReleased
        // TODO add your handling code here:
        int i = jList1.locationToIndex(evt.getPoint());
        jList1.setSelectedIndex(i);
        if (jList1.getSelectedIndex() != 0) {
            // TODO add your handling code here:
            
            if (evt.isPopupTrigger() && evt.getComponent() instanceof JList ) {
                jPopupMenu3.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_jList1MouseReleased

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        if (selection.equals("Biblioteca")) {
            // TODO add your handling code here:
            int r = jTable1.rowAtPoint(evt.getPoint());
            if (r >= 0 && r < jTable1.getRowCount()) {
                jTable1.setRowSelectionInterval(r, r);
            } else {
                jTable1.clearSelection();
            }

            int rowindex = jTable1.getSelectedRow();
            if (rowindex < 0)
                return;
            
            this.refreshPopupAlbums();
            
            if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable ) {
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        } else {
            // TODO add your handling code here:
            int r = jTable1.rowAtPoint(evt.getPoint());
            if (r >= 0 && r < jTable1.getRowCount()) {
                jTable1.setRowSelectionInterval(r, r);
            } else {
                jTable1.clearSelection();
            }

            int rowindex = jTable1.getSelectedRow();
            if (rowindex < 0)
                return;
            
            this.refreshPopupAlbums();
            
            if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable ) {
                jPopupMenu2.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_jTable1MouseReleased

    private void jMenu2MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu2MenuSelected
        
    }//GEN-LAST:event_jMenu2MenuSelected

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            // TODO add your handling code here:
            controlador.esborrarFitxer(jTable1.getSelectedRow());
        } catch (AplicacioException ex) {
            Logger.getLogger(AplicacioUB4Remastered.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.refreshTableBiblioteca();
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            controlador.esborrarFitxer(selection, jTable1.getSelectedRow());
        } catch (AplicacioException ex) {
            Logger.getLogger(AplicacioUB4Remastered.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.refreshTableAlbum(selection);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try {
            // TODO add your handling code here:
            controlador.esborrarAlbum(selection);
            jList1.setSelectedIndex(0);
        } catch (AplicacioException ex) {
            Logger.getLogger(AplicacioUB4Remastered.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.refreshListAlbums();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        try {
            // TODO add your handling code here:
            controlador.obrirFinestraReproductor();
            controlador.reproduirFitxer(jTable1.getSelectedRow());
        } catch (AplicacioException ex) {
            Logger.getLogger(AplicacioUB4Remastered.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        try {
            controlador.obrirFinestraReproductor();
            // TODO add your handling code here:
            controlador.reproduirCarpeta(selection);
        } catch (AplicacioException ex) {
            Logger.getLogger(AplicacioUB4Remastered.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            // TODO add your handling code here:
            controlador.reemprenReproduccio();
        } catch (AplicacioException ex) {
            Logger.getLogger(AplicacioUB4Remastered.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            // TODO add your handling code here:
            controlador.pausaReproduccio();
        } catch (AplicacioException ex) {
            Logger.getLogger(AplicacioUB4Remastered.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            controlador.aturaReproduccio();
        } catch (AplicacioException ex) {
            Logger.getLogger(AplicacioUB4Remastered.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            // TODO add your handling code here:
            controlador.saltaReproduccio();
        } catch (AplicacioException ex) {
            Logger.getLogger(AplicacioUB4Remastered.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        controlador.setReproduccioCiclica();
        
        if (controlador.isReproduccioCiclica()) {
            JOptionPane.showMessageDialog(new JFrame(), "Reproducció ciclica ACTIVADA", "Informació", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Reproducció ciclica DESACTIVADA", "Informació", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        controlador.setReproduccioAleatoria();
        
        if (controlador.isReproduccioAleatoria()) {
            JOptionPane.showMessageDialog(new JFrame(), "Reproducció aleatoria ACTIVADA", "Informació", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Reproducció aleatoria DESACTIVADA", "Informació", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        String desc = (String) jTable1.getCellEditor(jTable1.getSelectedRow(), 0).getCellEditorValue();
        boolean found = false;
        Iterator it = controlador.getBibliotecaFiles().iterator();
        FitxerReproduible fr = (FitxerReproduible) it.next();
        int i = 0;
        
        while (it.hasNext() && !found) {
            fr = (FitxerReproduible) it.next();
            if (fr.getDescripcio().equals(desc)) {
                found = true;
            } else i++;
        }
        
        try {
            controlador.obrirFinestraReproductor();
            controlador.reproduirFitxer(i);
        } catch (AplicacioException ex) {
            Logger.getLogger(AplicacioUB4Remastered.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, 
            "Estas segur de que vols sortir de l'aplicació?", "Tancar aplicació?", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
        {
            try {
                controlador.guardarDadesDisc("data.dat");
            } catch (AplicacioException ex) {
                Logger.getLogger(AplicacioUB4Remastered.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        } 
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
