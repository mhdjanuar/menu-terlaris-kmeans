/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.daoimpl;

/**
 *
 * @author mhdja
 */
import application.dao.SalesKMeansDao;
import application.models.SalesKMeansModel;
import application.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesKMeansDaoImpl implements SalesKMeansDao {

    private Connection dbConnection;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private String query;

    public SalesKMeansDaoImpl() {
        dbConnection = DatabaseUtil.getInstance().getConnection();
    }

    @Override
    public List<SalesKMeansModel> findAll() throws Exception {
       List<SalesKMeansModel> list = new ArrayList<>();

       String query =
           "SELECT s.tahun, m.nama_menu, m.tipe_menu, " +
           "SUM(s.total_terjual) AS total_terjual_per_menu, " +
           "SUM(s.total_terjual * m.harga) AS omzet_per_menu " +
           "FROM sales s " +
           "INNER JOIN menus m ON s.id_menu = m.id_menu " +
           "GROUP BY s.tahun, m.id_menu, m.nama_menu, m.tipe_menu " +
           "ORDER BY s.tahun, m.nama_menu";

       try {
           pstmt = dbConnection.prepareStatement(query);
           resultSet = pstmt.executeQuery();

           while (resultSet.next()) {
               SalesKMeansModel data = new SalesKMeansModel();

               // Tidak ada bulan lagi: set 0 / remove kalau tidak dipakai
               data.setBulan(0);

               data.setTahun(resultSet.getInt("tahun"));
               data.setNamaMenu(resultSet.getString("nama_menu"));
               data.setTipeMenu(resultSet.getString("tipe_menu"));
               data.setTotalTerjualPerMenu(resultSet.getInt("total_terjual_per_menu"));
               data.setOmzetPerMenu(resultSet.getInt("omzet_per_menu"));

               list.add(data);
           }
       } finally {
           closeStatement();
       }

       return list;
   }



    private void closeStatement() {
        try {
            if (pstmt != null) {
                pstmt.close();
                pstmt = null;
            }
            if (resultSet != null) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
