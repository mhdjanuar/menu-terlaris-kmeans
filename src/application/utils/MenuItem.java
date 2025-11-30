/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.utils;

/**
 *
 * @author mhdja
 */
public class MenuItem {
    public String nama;        // nama yang ditampilkan (gabungan)
    public String namaMenu;    // nama asli
    public String tipeMenu;    // opsional
    public double[] features;
    public int cluster;

    public MenuItem(String nama, String namaMenu, String tipeMenu, double[] features) {
        this.nama = nama;
        this.namaMenu = namaMenu;
        this.tipeMenu = tipeMenu;
        this.features = features;
    }
}
