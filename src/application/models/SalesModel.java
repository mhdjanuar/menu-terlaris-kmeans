/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.models;

public class SalesModel {
    private int idSales;
    private int idMenu;
    private int bulan;
    private int tahun;
    private int totalTerjual;

    // tambahan untuk JOIN
    private String namaMenu;
    private String tipeMenu;
    private String bulanTahun;

    // constructors
    public SalesModel() {}

    public SalesModel(int idMenu, int bulan, int tahun, int totalTerjual) {
        this.idMenu = idMenu;
        this.bulan = bulan;
        this.tahun = tahun;
        this.totalTerjual = totalTerjual;
    }

    // getters & setters
    public int getIdSales() { return idSales; }
    public void setIdSales(int idSales) { this.idSales = idSales; }

    public int getIdMenu() { return idMenu; }
    public void setIdMenu(int idMenu) { this.idMenu = idMenu; }

    public int getBulan() { return bulan; }
    public void setBulan(int bulan) { this.bulan = bulan; }

    public int getTahun() { return tahun; }
    public void setTahun(int tahun) { this.tahun = tahun; }

    public int getTotalTerjual() { return totalTerjual; }
    public void setTotalTerjual(int totalTerjual) { this.totalTerjual = totalTerjual; }

    // tambahan dari JOIN
    public String getNamaMenu() { return namaMenu; }
    public void setNamaMenu(String namaMenu) { this.namaMenu = namaMenu; }

    public String getTipeMenu() { return tipeMenu; }
    public void setTipeMenu(String tipeMenu) { this.tipeMenu = tipeMenu; }

    public String getBulanTahun() { return bulanTahun; }
    public void setBulanTahun(String bulanTahun) { this.bulanTahun = bulanTahun; }
}

