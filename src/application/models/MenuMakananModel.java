/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.models;

/**
 *
 * @author mhdja
 */
public class MenuMakananModel {
    private int idMenu;
    private String namaMenu;
    private String kategori;
    private String subkategori;
    private String tipeMenu;
    private int harga;
    private boolean status;

    // constructors
    public MenuMakananModel() {}

    public MenuMakananModel(String namaMenu, String kategori, String subkategori, String tipeMenu, int harga) {
        this.namaMenu = namaMenu;
        this.kategori = kategori;
        this.subkategori = subkategori;
        this.tipeMenu = tipeMenu;
        this.harga = harga;
        this.status = true;
    }

    // getters & setters
    public int getIdMenu() { return idMenu; }
    public void setIdMenu(int idMenu) { this.idMenu = idMenu; }
    public String getNamaMenu() { return namaMenu; }
    public void setNamaMenu(String namaMenu) { this.namaMenu = namaMenu; }
    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    public String getSubkategori() { return subkategori; }
    public void setSubkategori(String subkategori) { this.subkategori = subkategori; }
    public String getTipeMenu() { return tipeMenu; }
    public void setTipeMenu(String tipeMenu) { this.tipeMenu = tipeMenu; }
    public int getHarga() { return harga; }
    public void setHarga(int harga) { this.harga = harga; }
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}