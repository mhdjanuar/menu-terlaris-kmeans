/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.daoimpl;

import application.dao.MenuMakananDao;
import application.models.MenuMakananModel;
import application.utils.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuMakananDaoImpl implements MenuMakananDao {
    
    private Connection dbConnection = null;
    private PreparedStatement pstmt = null;
    private ResultSet resultSet = null;
    private String query;
    
    public MenuMakananDaoImpl() {
        dbConnection = DatabaseUtil.getInstance().getConnection();
    }

    @Override
    public List<MenuMakananModel> findAll() {
        List<MenuMakananModel> listDataAll = new ArrayList<>();

        try {
            query = "SELECT * FROM menus ORDER BY updated_at DESC";
            pstmt = dbConnection.prepareStatement(query);
            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                MenuMakananModel data = new MenuMakananModel();
                data.setIdMenu(resultSet.getInt("id_menu"));
                data.setNamaMenu(resultSet.getString("nama_menu"));
                data.setKategori(resultSet.getString("kategori"));
                data.setSubkategori(resultSet.getString("subkategori"));
                data.setTipeMenu(resultSet.getString("tipe_menu"));
                data.setHarga(resultSet.getInt("harga"));
                data.setStatus(resultSet.getInt("status") == 1);

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
    public int create(MenuMakananModel menu) {
        try {
            query = "INSERT INTO menus(nama_menu, kategori, subkategori, tipe_menu, harga, status) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, menu.getNamaMenu());
            pstmt.setString(2, menu.getKategori());
            pstmt.setString(3, menu.getSubkategori());
            pstmt.setString(4, menu.getTipeMenu());
            pstmt.setInt(5, menu.getHarga());
            pstmt.setInt(6, menu.isStatus() ? 1 : 0);

            // log data yang dikirim
            System.out.println("=== Insert Menu ===");
            System.out.println("Nama Menu: " + menu.getNamaMenu());
            System.out.println("Kategori: " + menu.getKategori());
            System.out.println("Harga: " + menu.getHarga());

            int result = pstmt.executeUpdate();
            resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()) {
                int insertedId = resultSet.getInt(1);
                System.out.println("Menu berhasil disimpan. ID: " + insertedId);
                return insertedId;
            }

            return result;
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan Menu: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            closeStatement();
        }
    }

    @Override
    public int update(MenuMakananModel menu) {
        int result = 0;

        try {
            query = "UPDATE menus SET nama_menu = ?, kategori = ?, subkategori = ?, tipe_menu = ?, harga = ?, status = ? WHERE id_menu = ?";
            pstmt = dbConnection.prepareStatement(query);

            pstmt.setString(1, menu.getNamaMenu());
            pstmt.setString(2, menu.getKategori());
            pstmt.setString(3, menu.getSubkategori());
            pstmt.setString(4, menu.getTipeMenu());
            pstmt.setInt(5, menu.getHarga());
            pstmt.setInt(6, menu.isStatus() ? 1 : 0);
            pstmt.setInt(7, menu.getIdMenu());

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
            query = "DELETE FROM menus WHERE id_menu = ?";
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

    @Override
    public MenuMakananModel findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
