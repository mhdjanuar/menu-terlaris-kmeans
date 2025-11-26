/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.utils;

import java.util.List;

/**
 *
 * @author mhdja
 */
public class DataNormalizer {

    public static void normalize(List<MenuItem> data) {
        int featureCount = data.get(0).features.length;

        double[] min = new double[featureCount];
        double[] max = new double[featureCount];

        // inisialisasi min & max
        for (int f = 0; f < featureCount; f++) {
            min[f] = Double.MAX_VALUE;
            max[f] = Double.MIN_VALUE;
        }

        // cari nilai min & max dari tiap fitur
        for (MenuItem item : data) {
            for (int f = 0; f < featureCount; f++) {
                if (item.features[f] < min[f]) min[f] = item.features[f];
                if (item.features[f] > max[f]) max[f] = item.features[f];
            }
        }

        // normalisasi
        for (MenuItem item : data) {
            for (int f = 0; f < featureCount; f++) {
                if (max[f] - min[f] == 0) {
                    item.features[f] = 0;
                } else {
                    item.features[f] = (item.features[f] - min[f]) / (max[f] - min[f]);
                }
            }
        }
    }
}

