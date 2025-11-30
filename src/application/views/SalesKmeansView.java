/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package application.views;

import application.dao.MenuMakananDao;
import application.dao.SalesKMeansDao;
import application.daoimpl.MenuMakananDaoImpl;
import application.daoimpl.SalesKMeansDaoImpl;
import application.models.MenuMakananModel;
import application.models.SalesKMeansModel;
import application.utils.DataNormalizer;
import application.utils.DatabaseUtil;
import application.utils.KmeansAlgoritma;
import application.utils.MenuItem;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mhdja
 */
public class SalesKmeansView extends javax.swing.JPanel {
    public final SalesKMeansDao salesKmeansDao;
     public final MenuMakananDao menuMakananDao;

    /**
     * Creates new form SalesKmeansView
     */
    public SalesKmeansView() {
        this.salesKmeansDao = new SalesKMeansDaoImpl();
        this.menuMakananDao = new MenuMakananDaoImpl();
        
        initComponents();
    }
    
    
     private void loadKMeansData() {
        try {
            // 1️⃣ Ambil data dari DAO
            List<SalesKMeansModel> listFromDB = salesKmeansDao.findAll();

            // 2️⃣ Konversi ke MenuItem (fitur KMeans)
            List<MenuItem> menuItems = new ArrayList<>();
            for (SalesKMeansModel sm : listFromDB) {

                // buat fitur
                double[] features = new double[]{
                    sm.getTotalTerjualPerMenu(),  // fitur 1
                    sm.getOmzetPerMenu()          // fitur 2
                };

                // log ke console
                System.out.println(
                    "[LOAD DATA] Menu: " + sm.getNamaMenu() +
                    " | Total Terjual: " + features[0] +
                    " | Omzet: " + features[1]
                );

                // buat objek MenuItem
                 menuItems.add(new MenuItem(
                    sm.getNamaMenu() + " - " + sm.getTipeMenu(),
                    sm.getNamaMenu(),
                    sm.getTipeMenu(),
                    features
                ));

            }


            // 3️⃣ Normalisasi
            DataNormalizer.normalize(menuItems);

            // 4️⃣ Jalankan KMeans
            int k = 3; // jumlah cluster
            KmeansAlgoritma km = new KmeansAlgoritma(k, menuItems);
            km.run();
            
            // ⭐ 5️⃣ Sort hasil cluster ASC
            menuItems.sort((a, b) -> Integer.compare(a.cluster, b.cluster));
           
            // 5️⃣ Tampilkan hasil di JTable
            String[] columns = {"Nama Menu - Tipe", "Total Terjual", "Omzet", "Cluster", "Label"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            for (MenuItem item : menuItems) {

                // tentukan label dari cluster (Java 11 compatible)
                String label;
                switch (item.cluster) {
                    case 0:
                        label = "Terlaris";
                        break;
                    case 1:
                        label = "Sedang";
                        break;
                    default:
                        label = "Kurang Laris";
                        break;
                }

                model.addRow(new Object[]{
                    item.nama,
                    item.features[0],
                    item.features[1],
                    item.cluster,
                    label
                });
            }


            jTable1.setModel(model);
            
            // Tampilkan hasil di JTextArea
            // Misal km.getSteps() mengembalikan List<String>
            List<String> steps = km.getSteps();

            // Gabungkan semua elemen menjadi satu string dengan newline (\n) sebagai pemisah
            String text = String.join("\n", steps);

            JTextArea txtHasil = new JTextArea();
            txtHasil.setEditable(false);
            txtHasil.setFont(new Font("Monospaced", Font.PLAIN, 12));
            txtHasil.setLineWrap(true);
            txtHasil.setWrapStyleWord(true);
            txtHasil.setText(text);
            txtHasil.setCaretPosition(0); // biar scroll tidak langsung ke bawah

            
             JScrollPane scroll = new JScrollPane(txtHasil);

             jPanel1.setPreferredSize(new Dimension(400, 300));
              jPanel1.setMaximumSize(new Dimension(400, 300));
               jPanel1.setMaximumSize(new Dimension(400, 300));

        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(scroll, BorderLayout.CENTER);
        jPanel1.revalidate();
        jPanel1.repaint();
        

        } catch (Exception e) {
            e.printStackTrace();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 432, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Perhitungan Kmeans");

        jButton2.setText("Hitung");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Simpan");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Cetak Laporan");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addGap(3, 3, 3)
                                .addComponent(jButton1))
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        loadKMeansData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       try {
            // 1️⃣ Hapus semua data dataset_kmeans
            salesKmeansDao.deleteAll();
            System.out.println("[INFO] Semua data dataset_kmeans dihapus!");

            // 2️⃣ Reset JTable (kosongkan)
            DefaultTableModel emptyModel = new DefaultTableModel(
                new Object[]{"Nama Menu - Tipe", "Total Terjual", "Omzet", "Cluster", "Label"},
                0
            );
            jTable1.setModel(emptyModel);

            // 3️⃣ Reset jPanel1 TEXT AREA TANPA RESIZE PANEL
            jPanel1.removeAll();          // hapus konten lama
            jPanel1.setLayout(new BorderLayout());  

            // placeholder "belum ada hasil"
            JTextArea placeholder = new JTextArea("Belum ada hasil perhitungan K-Means.");
            placeholder.setEditable(false);
            placeholder.setFont(new Font("Monospaced", Font.PLAIN, 12));
            placeholder.setLineWrap(true);
            placeholder.setWrapStyleWord(true);

            JScrollPane sp = new JScrollPane(placeholder);
            sp.setBorder(null); // supaya bersih

            jPanel1.add(sp, BorderLayout.CENTER);

            jPanel1.revalidate();
            jPanel1.repaint();

        } catch (Exception ex) {
            Logger.getLogger(SalesKmeansView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
                       // 1️⃣ Ambil data dari DAO
            List<SalesKMeansModel> listFromDB = salesKmeansDao.findAll();

            // 2️⃣ Konversi ke MenuItem (fitur KMeans)
            List<MenuItem> menuItems = new ArrayList<>();
            for (SalesKMeansModel sm : listFromDB) {

                // buat fitur
                double[] features = new double[]{
                    sm.getTotalTerjualPerMenu(),  // fitur 1
                    sm.getOmzetPerMenu()          // fitur 2
                };

                // log ke console
                System.out.println(
                    "[LOAD DATA] Menu: " + sm.getNamaMenu() +
                    " | Total Terjual: " + features[0] +
                    " | Omzet: " + features[1]
                );

                // buat objek MenuItem
                 menuItems.add(new MenuItem(
                    sm.getNamaMenu() + " - " + sm.getTipeMenu(),
                    sm.getNamaMenu(),
                    sm.getTipeMenu(),
                    features
                ));

            }


            // 3️⃣ Normalisasi
            DataNormalizer.normalize(menuItems);

            // 4️⃣ Jalankan KMeans
            int k = 3; // jumlah cluster
            KmeansAlgoritma km = new KmeansAlgoritma(k, menuItems);
            km.run();
            
            // ⭐ 5️⃣ Sort hasil cluster ASC
            menuItems.sort((a, b) -> Integer.compare(a.cluster, b.cluster));
            
            List<MenuMakananModel> allMenus = menuMakananDao.findAll();
            // map nama → id
            Map<String, Integer> mapNamaToId = new HashMap<>();
            for (MenuMakananModel m : allMenus) {
                mapNamaToId.put(m.getNamaMenu(), m.getIdMenu());
            }
            
            // ⭐⭐ 5️⃣ Siapkan list model untuk save bulk
            List<SalesKMeansModel> toSave = new ArrayList<>();

            for (MenuItem item : menuItems) {

                // Ambil nama saja sebelum " - tipe"
                String namaMenuOnly = item.nama.split(" - ")[0];

                // Cari ID dari map
                Integer idMenu = mapNamaToId.get(namaMenuOnly);

                if (idMenu == null) {
                    System.out.println("Menu tidak ditemukan: " + namaMenuOnly);
                    continue; // skip kalau tidak ketemu
                }

                SalesKMeansModel d = new SalesKMeansModel();
                d.setIdMenu(idMenu); // kalau tipenya string
                d.setCluster(item.cluster);

                toSave.add(d);
            }
            
            // 6️⃣ Save bulk ke database
            salesKmeansDao.saveBulk(toSave);
            System.out.println("[INFO] Save bulk dataset_kmeans berhasil!"); 
            JOptionPane.showMessageDialog(null, "Data K-Means berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            
        } catch (Exception ex) {
            Logger.getLogger(SalesKmeansView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            String templateName = "LaporanHasilKmeans.jrxml";
            InputStream reportStream = ReportView.class.getResourceAsStream("/resources/reports/" + templateName);
            JasperDesign jd = JRXmlLoader.load(reportStream);

            Connection dbConnection = DatabaseUtil.getInstance().getConnection();

            JasperReport jr = JasperCompileManager.compileReport(jd);

            HashMap parameter = new HashMap();
            parameter.put("PATH","src/resources/images/");
            
            JasperPrint jp = JasperFillManager.fillReport(jr,parameter, dbConnection);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
