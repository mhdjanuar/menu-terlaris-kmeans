/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.daoimpl;

/**
 *
 * @author mhdja
 */

import application.dao.SalesDao;
import application.models.SalesModel;
import application.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDaoImpl implements SalesDao {

    private Connection dbConnection = null;
    private PreparedStatement pstmt = null;
    private ResultSet resultSet = null;
    private String query;

    public SalesDaoImpl() {
        dbConnection = DatabaseUtil.getInstance().getConnection();
    }

  @Override
    public List<SalesModel> findAll() {
        List<SalesModel> listDataAll = new ArrayList<>();

        try {
            // Query pakai nama bulan Indonesia dan urutkan berdasarkan update_at
            query = "SELECT s.id_sales, m.nama_menu, m.tipe_menu, "
                  + "CONCAT(ELT(s.bulan, 'Januari','Februari','Maret','April','Mei','Juni','Juli','Agustus','September','Oktober','November','Desember'), "
                  + "' ', s.tahun) AS bulan_tahun, "
                  + "s.total_terjual "
                  + "FROM sales AS s "
                  + "INNER JOIN menus AS m ON s.id_menu = m.id_menu "
                  + "ORDER BY s.id_sales DESC"; // urut berdasarkan update_at terbaru

            pstmt = dbConnection.prepareStatement(query);
            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                SalesModel data = new SalesModel();
                data.setIdSales(resultSet.getInt("id_sales"));
                data.setNamaMenu(resultSet.getString("nama_menu"));
                data.setTipeMenu(resultSet.getString("tipe_menu"));
                data.setBulanTahun(resultSet.getString("bulan_tahun"));
                data.setTotalTerjual(resultSet.getInt("total_terjual"));

                listDataAll.add(data);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement();
        }

        return listDataAll;
    }



    @Override
    public SalesModel findById(int id) {
        SalesModel data = null;

        try {
            query = "SELECT * FROM sales WHERE id_sales = ?";
            pstmt = dbConnection.prepareStatement(query);
            pstmt.setInt(1, id);

            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                data = new SalesModel();
                data.setIdSales(resultSet.getInt("id_sales"));
                data.setIdMenu(resultSet.getInt("id_menu"));
                data.setBulan(resultSet.getInt("bulan"));
                data.setTahun(resultSet.getInt("tahun"));
                data.setTotalTerjual(resultSet.getInt("total_terjual"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement();
        }

        return data;
    }

    @Override
    public int create(SalesModel sales) {
        try {
            query = "INSERT INTO sales (id_menu, bulan, tahun, total_terjual) VALUES (?, ?, ?, ?)";
            pstmt = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, sales.getIdMenu());
            pstmt.setInt(2, sales.getBulan());
            pstmt.setInt(3, sales.getTahun());
            pstmt.setInt(4, sales.getTotalTerjual());

            int result = pstmt.executeUpdate();

            resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement();
        }
    }

    @Override
    public int update(SalesModel sales) {
        int result = 0;

        try {
            query = "UPDATE sales SET id_menu = ?, bulan = ?, tahun = ?, total_terjual = ? WHERE id_sales = ?";
            pstmt = dbConnection.prepareStatement(query);

            pstmt.setInt(1, sales.getIdMenu());
            pstmt.setInt(2, sales.getBulan());
            pstmt.setInt(3, sales.getTahun());
            pstmt.setInt(4, sales.getTotalTerjual());
            pstmt.setInt(5, sales.getIdSales());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement();
        }

        return result;
    }
    
    

    @Override
    public int delete(int id) {
        int result = 0;

        try {
            query = "DELETE FROM sales WHERE id_sales = ?";
            pstmt = dbConnection.prepareStatement(query);
            pstmt.setInt(1, id);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement();
        }

        return result;
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
