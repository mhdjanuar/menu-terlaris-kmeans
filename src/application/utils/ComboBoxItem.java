/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.utils;

/**
 *
 * @author mhdja
 */
public class ComboBoxItem {
     private int id;
    private String label;

    public ComboBoxItem(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return label; // ini yang tampil di JComboBox
    }
}
