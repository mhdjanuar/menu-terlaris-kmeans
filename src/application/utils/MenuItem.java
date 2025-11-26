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
    public String nama;
    public double[] features; // fitur dinamis (banyak kolom numerik)
    public int cluster = -1;

    public MenuItem(String nama, double[] features) {
        this.nama = nama;
        this.features = features;
    }
}