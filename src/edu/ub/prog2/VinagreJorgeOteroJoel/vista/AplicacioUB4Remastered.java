package edu.ub.prog2.VinagreJorgeOteroJoel.vista;

import edu.ub.prog2.VinagreJorgeOteroJoel.controlador.Controlador;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.FitxerReproduible;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.Video;
import edu.ub.prog2.utils.AplicacioException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
        leftMenu.setSelectedIndex(0); // Set the selected index to biblioteca on start
        
        
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
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    /**
     * Method to refresh the main table with the library files
     */
    public void refreshTableBiblioteca() {
        DefaultTableModel model = (DefaultTableModel) fileDisplayTable.getModel();
        
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
        DefaultTableModel model = (DefaultTableModel) fileDisplayTable.getModel();

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
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
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
        btnAfegirImatgeAlbum.removeAll();
        
        for (String str : controlador.mostrarLlistatAlbums()) {
            jmi = new JMenuItem(str);
            jmi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JMenuItem menuItem = (JMenuItem)e.getSource();
                        controlador.afegirFitxer(menuItem.getText(), fileDisplayTable.getSelectedRow());
                    } catch (AplicacioException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            btnAfegirImatgeAlbum.add(jmi);
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

        libFileRightClickMenu = new javax.swing.JPopupMenu();
        btnAfegirImatgeAlbum = new javax.swing.JMenu();
        btnEliminarImatge = new javax.swing.JMenuItem();
        btnPlayImBiblioteca = new javax.swing.JMenuItem();
        albumFileRightClickMenu = new javax.swing.JPopupMenu();
        btnEliminarImatgeAlbum = new javax.swing.JMenuItem();
        btnPlayImAlbum = new javax.swing.JMenuItem();
        albumRightClickMenu = new javax.swing.JPopupMenu();
        btnEliminarAlbum = new javax.swing.JMenuItem();
        btnPlayAlbum = new javax.swing.JMenuItem();
        libRightClickMenu = new javax.swing.JPopupMenu();
        btnPlayBiblioteca = new javax.swing.JMenuItem();
        leftMenuPanel = new javax.swing.JPanel();
        leftMenuScroll = new javax.swing.JScrollPane();
        leftMenu = new javax.swing.JList<>();
        bottomBarPanel = new javax.swing.JPanel();
        btnAleatoryMode = new javax.swing.JButton();
        btnStopPlaying = new javax.swing.JButton();
        btnResumePlaying = new javax.swing.JButton();
        btnPausePlaying = new javax.swing.JButton();
        btnJumpPlaying = new javax.swing.JButton();
        btnCyclicMode = new javax.swing.JButton();
        fileDisplayTablePanel = new javax.swing.JPanel();
        fileDisplayTableScroll = new javax.swing.JScrollPane();
        fileDisplayTable = new javax.swing.JTable();
        topBarMenu = new javax.swing.JMenuBar();
        mediaMenu = new javax.swing.JMenu();
        btnAfegirFitxerBiblioteca = new javax.swing.JMenuItem();
        btnCrearAlbum = new javax.swing.JMenuItem();

        btnAfegirImatgeAlbum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-sum-16.png"))); // NOI18N
        btnAfegirImatgeAlbum.setText("Afegir a album...");
        btnAfegirImatgeAlbum.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                btnAfegirImatgeAlbumMenuSelected(evt);
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
        });
        libFileRightClickMenu.add(btnAfegirImatgeAlbum);

        btnEliminarImatge.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-basura-16.png"))); // NOI18N
        btnEliminarImatge.setText("Eliminar arxiu");
        btnEliminarImatge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarImatgeActionPerformed(evt);
            }
        });
        libFileRightClickMenu.add(btnEliminarImatge);

        btnPlayImBiblioteca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-reproducir-16.png"))); // NOI18N
        btnPlayImBiblioteca.setText("Reproduir arxiu");
        btnPlayImBiblioteca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayImBibliotecaActionPerformed(evt);
            }
        });
        libFileRightClickMenu.add(btnPlayImBiblioteca);

        btnEliminarImatgeAlbum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-basura-16.png"))); // NOI18N
        btnEliminarImatgeAlbum.setText("Eliminar arxiu del album");
        btnEliminarImatgeAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarImatgeAlbumActionPerformed(evt);
            }
        });
        albumFileRightClickMenu.add(btnEliminarImatgeAlbum);

        btnPlayImAlbum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-reproducir-16.png"))); // NOI18N
        btnPlayImAlbum.setText("Reproduir arxiu");
        btnPlayImAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayImAlbumActionPerformed(evt);
            }
        });
        albumFileRightClickMenu.add(btnPlayImAlbum);

        btnEliminarAlbum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-basura-16.png"))); // NOI18N
        btnEliminarAlbum.setText("Eliminar album");
        btnEliminarAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAlbumActionPerformed(evt);
            }
        });
        albumRightClickMenu.add(btnEliminarAlbum);

        btnPlayAlbum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-reproducir-16.png"))); // NOI18N
        btnPlayAlbum.setText("Reproduir album");
        btnPlayAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayAlbumActionPerformed(evt);
            }
        });
        albumRightClickMenu.add(btnPlayAlbum);

        btnPlayBiblioteca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-reproducir-16.png"))); // NOI18N
        btnPlayBiblioteca.setText("Reproduir biblioteca");
        btnPlayBiblioteca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayBibliotecaActionPerformed(evt);
            }
        });
        libRightClickMenu.add(btnPlayBiblioteca);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Vinotero Media Player");
        setBackground(new java.awt.Color(51, 51, 51));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        leftMenuPanel.setBackground(new java.awt.Color(51, 51, 51));

        leftMenuScroll.setBorder(null);
        leftMenuScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        leftMenuScroll.setHorizontalScrollBar(null);

        leftMenu.setBackground(new java.awt.Color(51, 51, 51));
        leftMenu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        leftMenu.setForeground(new java.awt.Color(159, 159, 159));
        leftMenu.setModel(dlm);
        leftMenu.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        leftMenu.setFocusable(false);
        leftMenu.setRequestFocusEnabled(false);
        leftMenu.setSelectionBackground(null);
        leftMenu.setSelectionForeground(new java.awt.Color(255, 255, 255));
        leftMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                leftMenuMouseReleased(evt);
            }
        });
        leftMenu.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                leftMenuValueChanged(evt);
            }
        });
        leftMenuScroll.setViewportView(leftMenu);

        javax.swing.GroupLayout leftMenuPanelLayout = new javax.swing.GroupLayout(leftMenuPanel);
        leftMenuPanel.setLayout(leftMenuPanelLayout);
        leftMenuPanelLayout.setHorizontalGroup(
            leftMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftMenuPanelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(leftMenuScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        leftMenuPanelLayout.setVerticalGroup(
            leftMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftMenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leftMenuScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(leftMenuPanel, java.awt.BorderLayout.LINE_START);

        bottomBarPanel.setBackground(new java.awt.Color(51, 51, 51));
        bottomBarPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAleatoryMode.setBackground(new java.awt.Color(51, 51, 51));
        btnAleatoryMode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-shuffle-24.png"))); // NOI18N
        btnAleatoryMode.setBorder(null);
        btnAleatoryMode.setBorderPainted(false);
        btnAleatoryMode.setContentAreaFilled(false);
        btnAleatoryMode.setFocusable(false);
        btnAleatoryMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAleatoryModeActionPerformed(evt);
            }
        });
        bottomBarPanel.add(btnAleatoryMode);

        btnStopPlaying.setBackground(new java.awt.Color(51, 51, 51));
        btnStopPlaying.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-stop-squared-filled-24.png"))); // NOI18N
        btnStopPlaying.setBorder(null);
        btnStopPlaying.setBorderPainted(false);
        btnStopPlaying.setContentAreaFilled(false);
        btnStopPlaying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopPlayingActionPerformed(evt);
            }
        });
        bottomBarPanel.add(btnStopPlaying);

        btnResumePlaying.setBackground(new java.awt.Color(51, 51, 51));
        btnResumePlaying.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-play-24.png"))); // NOI18N
        btnResumePlaying.setBorder(null);
        btnResumePlaying.setBorderPainted(false);
        btnResumePlaying.setContentAreaFilled(false);
        btnResumePlaying.setFocusable(false);
        btnResumePlaying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResumePlayingActionPerformed(evt);
            }
        });
        bottomBarPanel.add(btnResumePlaying);

        btnPausePlaying.setBackground(new java.awt.Color(51, 51, 51));
        btnPausePlaying.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-pause-squared-filled-24.png"))); // NOI18N
        btnPausePlaying.setBorder(null);
        btnPausePlaying.setBorderPainted(false);
        btnPausePlaying.setContentAreaFilled(false);
        btnPausePlaying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausePlayingActionPerformed(evt);
            }
        });
        bottomBarPanel.add(btnPausePlaying);

        btnJumpPlaying.setBackground(new java.awt.Color(51, 51, 51));
        btnJumpPlaying.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-end-filled-24.png"))); // NOI18N
        btnJumpPlaying.setBorder(null);
        btnJumpPlaying.setBorderPainted(false);
        btnJumpPlaying.setContentAreaFilled(false);
        btnJumpPlaying.setFocusable(false);
        btnJumpPlaying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJumpPlayingActionPerformed(evt);
            }
        });
        bottomBarPanel.add(btnJumpPlaying);

        btnCyclicMode.setBackground(new java.awt.Color(51, 51, 51));
        btnCyclicMode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-repeat-24.png"))); // NOI18N
        btnCyclicMode.setBorder(null);
        btnCyclicMode.setBorderPainted(false);
        btnCyclicMode.setContentAreaFilled(false);
        btnCyclicMode.setFocusable(false);
        btnCyclicMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCyclicModeActionPerformed(evt);
            }
        });
        bottomBarPanel.add(btnCyclicMode);

        getContentPane().add(bottomBarPanel, java.awt.BorderLayout.PAGE_END);

        fileDisplayTablePanel.setBackground(new java.awt.Color(51, 51, 51));
        fileDisplayTablePanel.setLayout(new java.awt.GridLayout(1, 0));

        fileDisplayTableScroll.setBackground(new java.awt.Color(51, 51, 51));
        fileDisplayTableScroll.setBorder(null);
        fileDisplayTableScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        fileDisplayTableScroll.setHorizontalScrollBar(null);
        fileDisplayTableScroll.setPreferredSize(getSize());

        fileDisplayTable.setBackground(new java.awt.Color(51, 51, 51));
        fileDisplayTable.setForeground(new java.awt.Color(255, 255, 255));
        fileDisplayTable.setModel(new javax.swing.table.DefaultTableModel(
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
        fileDisplayTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        fileDisplayTable.setAutoscrolls(false);
        fileDisplayTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        fileDisplayTable.setFillsViewportHeight(true);
        fileDisplayTable.setFocusable(false);
        fileDisplayTable.setGridColor(new java.awt.Color(102, 102, 102));
        fileDisplayTable.setMinimumSize(new java.awt.Dimension(100, 0));
        fileDisplayTable.setPreferredSize(new java.awt.Dimension(500, 500));
        fileDisplayTable.setRowHeight(30);
        fileDisplayTable.setRowMargin(5);
        fileDisplayTable.setSelectionBackground(new java.awt.Color(102, 102, 102));
        fileDisplayTable.setShowVerticalLines(false);
        fileDisplayTable.setSurrendersFocusOnKeystroke(true);
        fileDisplayTable.getTableHeader().setReorderingAllowed(false);
        fileDisplayTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                fileDisplayTableMouseReleased(evt);
            }
        });
        fileDisplayTableScroll.setViewportView(fileDisplayTable);
        if (fileDisplayTable.getColumnModel().getColumnCount() > 0) {
            fileDisplayTable.getColumnModel().getColumn(0).setResizable(false);
            fileDisplayTable.getColumnModel().getColumn(1).setResizable(false);
            fileDisplayTable.getColumnModel().getColumn(2).setResizable(false);
            fileDisplayTable.getColumnModel().getColumn(3).setResizable(false);
            fileDisplayTable.getColumnModel().getColumn(4).setResizable(false);
        }

        fileDisplayTablePanel.add(fileDisplayTableScroll);

        getContentPane().add(fileDisplayTablePanel, java.awt.BorderLayout.CENTER);

        topBarMenu.setBackground(new java.awt.Color(51, 51, 51));
        topBarMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        topBarMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mediaMenu.setBackground(new java.awt.Color(51, 51, 51));
        mediaMenu.setForeground(new java.awt.Color(255, 255, 255));
        mediaMenu.setText("Media");

        btnAfegirFitxerBiblioteca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add-new-file.png"))); // NOI18N
        btnAfegirFitxerBiblioteca.setText("Afegir fitxer");
        btnAfegirFitxerBiblioteca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAfegirFitxerBibliotecaActionPerformed(evt);
            }
        });
        mediaMenu.add(btnAfegirFitxerBiblioteca);

        btnCrearAlbum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new-folder.png"))); // NOI18N
        btnCrearAlbum.setText("Crear album");
        btnCrearAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearAlbumActionPerformed(evt);
            }
        });
        mediaMenu.add(btnCrearAlbum);

        topBarMenu.add(mediaMenu);

        setJMenuBar(topBarMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * JMenu (bar menu on top) add media option clicked
     * @param evt Action event
     */
    private void btnAfegirFitxerBibliotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAfegirFitxerBibliotecaActionPerformed
        FrmAfegirFitxerMultimedia afegirFitxerMultimedia = new FrmAfegirFitxerMultimedia(this, controlador);
        afegirFitxerMultimedia.setVisible(true);
    }//GEN-LAST:event_btnAfegirFitxerBibliotecaActionPerformed

    /**
     * Album/Library selected on the JList Menu (Left one)
     * @param evt ListSelectionEvent
     */
    private void leftMenuValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_leftMenuValueChanged
        if (leftMenu.getSelectedIndex() > -1 && !leftMenu.getSelectedValue().equals(selection)) {
            selection = leftMenu.getSelectedValue();
            if (leftMenu.getSelectedIndex() == 0) {
                this.refreshTableBiblioteca();
            } else {
                this.refreshTableAlbum(selection);
            }
        }
        
        if (leftMenu.getSelectedIndex() == -1) leftMenu.setSelectedIndex(0);
    }//GEN-LAST:event_leftMenuValueChanged

    /**
     * JMenu (bar menu on top) create album option clicked
     * @param evt ActionEvent
     */
    private void btnCrearAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearAlbumActionPerformed
        // TODO add your handling code here:
        String input = JOptionPane.showInputDialog("Nom del album:");
        try {
            controlador.afegirAlbum(input);
        } catch (AplicacioException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        this.refreshListAlbums();
    }//GEN-LAST:event_btnCrearAlbumActionPerformed

    /**
     * Check right click on the jList1 (Left one)
     * @param evt  MouseEvent
     */
    private void leftMenuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftMenuMouseReleased
        int i = leftMenu.locationToIndex(evt.getPoint());
        leftMenu.setSelectedIndex(i);
        if (leftMenu.getSelectedIndex() != 0) {
            if (evt.isPopupTrigger() && evt.getComponent() instanceof JList ) {
                albumRightClickMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        } else {
            if (evt.isPopupTrigger() && evt.getComponent() instanceof JList ) {
                libRightClickMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_leftMenuMouseReleased

    /**
     * Right-Click on main table (The file list one)
     * @param evt MouseEvent
     */
    private void fileDisplayTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileDisplayTableMouseReleased
        if (selection.equals("Biblioteca")) {
            int r = fileDisplayTable.rowAtPoint(evt.getPoint());
            if (r >= 0 && r < fileDisplayTable.getRowCount()) {
                fileDisplayTable.setRowSelectionInterval(r, r);
            } else {
                fileDisplayTable.clearSelection();
            }

            int rowindex = fileDisplayTable.getSelectedRow();
            if (rowindex < 0)
                return;
            
            this.refreshPopupAlbums();
            
            if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable ) {
                libFileRightClickMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        } else {
            int r = fileDisplayTable.rowAtPoint(evt.getPoint());
            if (r >= 0 && r < fileDisplayTable.getRowCount()) {
                fileDisplayTable.setRowSelectionInterval(r, r);
            } else {
                fileDisplayTable.clearSelection();
            }

            int rowindex = fileDisplayTable.getSelectedRow();
            if (rowindex < 0)
                return;
            
            this.refreshPopupAlbums();
            
            if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable ) {
                albumFileRightClickMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_fileDisplayTableMouseReleased

    // unused
    private void btnAfegirImatgeAlbumMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_btnAfegirImatgeAlbumMenuSelected
    }//GEN-LAST:event_btnAfegirImatgeAlbumMenuSelected

    /**
     * Remove file option from JPopupmenu on library
     * @param evt ActionEvent
     */
    private void btnEliminarImatgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarImatgeActionPerformed
        try {
            controlador.esborrarFitxer(fileDisplayTable.getSelectedRow());
        } catch (AplicacioException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
        this.refreshTableBiblioteca();
        
    }//GEN-LAST:event_btnEliminarImatgeActionPerformed

    /**
     * Remove file option from JPopupmenu on a selected album
     * @param evt ActionEvent
     */
    private void btnEliminarImatgeAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarImatgeAlbumActionPerformed
        try {
            controlador.esborrarFitxer(selection, fileDisplayTable.getSelectedRow());
        } catch (AplicacioException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        this.refreshTableAlbum(selection);
    }//GEN-LAST:event_btnEliminarImatgeAlbumActionPerformed

    /**
     * Remove album option from JPopupmenu on clicked album
     * @param evt ActionEvent
     */
    private void btnEliminarAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAlbumActionPerformed
        try {
            controlador.esborrarAlbum(selection);
            leftMenu.setSelectedIndex(0);
        } catch (AplicacioException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
        this.refreshListAlbums();
    }//GEN-LAST:event_btnEliminarAlbumActionPerformed

    /**
     * Play file option from JPopup menu clicked while library is selected on JList
     * @param evt ActionEvent
     */
    private void btnPlayImBibliotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayImBibliotecaActionPerformed
        try {
            controlador.obrirFinestraReproductor();
            controlador.reproduirFitxer(fileDisplayTable.getSelectedRow());
        } catch (AplicacioException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPlayImBibliotecaActionPerformed

    /**
     * Play file option from JPopup menu clicked while album is selected on JList
     * @param evt ActionEvent
     */
    private void btnPlayAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayAlbumActionPerformed
        try {
            controlador.obrirFinestraReproductor();
            controlador.reproduirCarpeta(selection);
        } catch (AplicacioException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPlayAlbumActionPerformed

    /**
     * Resmume playing button clicked
     * @param evt ActionEvent
     */
    private void btnResumePlayingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResumePlayingActionPerformed
        try {
            controlador.reemprenReproduccio();
        } catch (AplicacioException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnResumePlayingActionPerformed

    /**
     * Pause playing button clicked
     * @param evt ActionEvent
     */
    private void btnPausePlayingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausePlayingActionPerformed
        try {
            controlador.pausaReproduccio();
        } catch (AplicacioException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPausePlayingActionPerformed

    /**
     * Stop playing button clicked
     * @param evt 
     */
    private void btnStopPlayingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopPlayingActionPerformed
        try {
            controlador.aturaReproduccio();
        } catch (AplicacioException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnStopPlayingActionPerformed

    /**
     * Jump file button clicked
     * @param evt ActionEvent
     */
    private void btnJumpPlayingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJumpPlayingActionPerformed
        try {
            controlador.saltaReproduccio();
        } catch (AplicacioException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnJumpPlayingActionPerformed

    /**
     * Cyclic playing button clicked
     * @param evt ActionEvent
     */
    private void btnCyclicModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCyclicModeActionPerformed
        controlador.setReproduccioCiclica();
        
        if (controlador.isReproduccioCiclica()) {
            JOptionPane.showMessageDialog(new JFrame(), "Reproducció ciclica ACTIVADA", "Informació", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Reproducció ciclica DESACTIVADA", "Informació", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnCyclicModeActionPerformed

    /**
     * Aleatory playing button clicked
     * @param evt ActionEvent
     */
    private void btnAleatoryModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAleatoryModeActionPerformed
        // TODO add your handling code here:
        controlador.setReproduccioAleatoria();
        
        if (controlador.isReproduccioAleatoria()) {
            JOptionPane.showMessageDialog(new JFrame(), "Reproducció aleatoria ACTIVADA", "Informació", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Reproducció aleatoria DESACTIVADA", "Informació", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAleatoryModeActionPerformed

    /**
     * Play file from JPopupmenu on a selected album clicked
     * @param evt ActionEvent
     */
    private void btnPlayImAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayImAlbumActionPerformed
        String desc = (String) fileDisplayTable.getCellEditor(fileDisplayTable.getSelectedRow(), 0).getCellEditorValue();
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
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPlayImAlbumActionPerformed

    /**
     * Checkout before closing the app, handling data save here.
     * @param evt WindowEvent
     */
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
                JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        } 
    }//GEN-LAST:event_formWindowClosing

    /**
     * Play full library option from JPopupmenu while library is selected
     * @param evt 
     */
    private void btnPlayBibliotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayBibliotecaActionPerformed
        try {
            controlador.obrirFinestraReproductor();
            controlador.reproduirCarpeta();
        } catch (AplicacioException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPlayBibliotecaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu albumFileRightClickMenu;
    private javax.swing.JPopupMenu albumRightClickMenu;
    private javax.swing.JPanel bottomBarPanel;
    private javax.swing.JMenuItem btnAfegirFitxerBiblioteca;
    private javax.swing.JMenu btnAfegirImatgeAlbum;
    private javax.swing.JButton btnAleatoryMode;
    private javax.swing.JMenuItem btnCrearAlbum;
    private javax.swing.JButton btnCyclicMode;
    private javax.swing.JMenuItem btnEliminarAlbum;
    private javax.swing.JMenuItem btnEliminarImatge;
    private javax.swing.JMenuItem btnEliminarImatgeAlbum;
    private javax.swing.JButton btnJumpPlaying;
    private javax.swing.JButton btnPausePlaying;
    private javax.swing.JMenuItem btnPlayAlbum;
    private javax.swing.JMenuItem btnPlayBiblioteca;
    private javax.swing.JMenuItem btnPlayImAlbum;
    private javax.swing.JMenuItem btnPlayImBiblioteca;
    private javax.swing.JButton btnResumePlaying;
    private javax.swing.JButton btnStopPlaying;
    private javax.swing.JTable fileDisplayTable;
    private javax.swing.JPanel fileDisplayTablePanel;
    private javax.swing.JScrollPane fileDisplayTableScroll;
    private javax.swing.JList<String> leftMenu;
    private javax.swing.JPanel leftMenuPanel;
    private javax.swing.JScrollPane leftMenuScroll;
    private javax.swing.JPopupMenu libFileRightClickMenu;
    private javax.swing.JPopupMenu libRightClickMenu;
    private javax.swing.JMenu mediaMenu;
    private javax.swing.JMenuBar topBarMenu;
    // End of variables declaration//GEN-END:variables
}
