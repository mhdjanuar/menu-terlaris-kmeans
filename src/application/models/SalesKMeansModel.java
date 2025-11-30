/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.models;

/**
 *
 * @author mhdja
 */
public class SalesKMeansModel {
    private int bulan;
    private int tahun;
    private String namaMenu;
    private String tipeMenu;
    private int totalTerjualPerMenu;
    private int omzetPerMenu;
    private int idMenu;
    private int cluster;

    public SalesKMeansModel() {}

    public SalesKMeansModel(int bulan, int tahun, String namaMenu, int totalTerjualPerMenu, int omzetPerMenu) {
        this.bulan = bulan;
        this.tahun = tahun;
        this.namaMenu = namaMenu;
        this.totalTerjualPerMenu = totalTerjualPerMenu;
        this.omzetPerMenu = omzetPerMenu;
    }

    // getters & setters
    public int getBulan() { return bulan; }
    public void setBulan(int bulan) { this.bulan = bulan; }

    public int getTahun() { return tahun; }
    public void setTahun(int tahun) { this.tahun = tahun; }

    public String getNamaMenu() { return namaMenu; }
    public void setNamaMenu(String namaMenu) { this.namaMenu = namaMenu; }

    public int getTotalTerjualPerMenu() { return totalTerjualPerMenu; }
    public void setTotalTerjualPerMenu(int totalTerjualPerMenu) { this.totalTerjualPerMenu = totalTerjualPerMenu; }

    public int getOmzetPerMenu() { return omzetPerMenu; }
    public void setOmzetPerMenu(int omzetPerMenu) { this.omzetPerMenu = omzetPerMenu; }
    
       /**
     * @return the tipeMenu
     */
    public String getTipeMenu() {
        return tipeMenu;
    }

    /**
     * @param tipeMenu the tipeMenu to set
     */
    public void setTipeMenu(String tipeMenu) {
        this.tipeMenu = tipeMenu;
    }
    
        /**
     * @return the idMenu
     */
    public int getIdMenu() {
        return idMenu;
    }

    /**
     * @param idMenu the idMenu to set
     */
    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    /**
     * @return the cluster
     */
    public int getCluster() {
        return cluster;
    }

    /**
     * @param cluster the cluster to set
     */
    public void setCluster(int cluster) {
        this.cluster = cluster;
    }
}
