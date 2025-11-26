/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mhdja
 */
public class KmeansAlgoritma {

    private int k;
    private final List<MenuItem> data;
    private final double[][] centroids;

    private final List<String> formulas = new ArrayList<>();
    private final List<String> steps = new ArrayList<>();

    public KmeansAlgoritma(int k, List<MenuItem> data) {
        this.k = k;
        this.data = data;
        this.centroids = new double[k][data.get(0).features.length];
    }

    public void run() {
        // 🔥 gunakan K-Means++ untuk inisialisasi centroid
        initializeCentroidsPlusPlus();

        boolean changed;
        int iteration = 1;

        do {
            steps.add("\n=== Iterasi " + iteration + " ===");
            changed = false;

            // === ASSIGN CLUSTER ===
            for (MenuItem item : data) {
                int nearest = nearestCentroid(item);

                steps.add("Assign: " + item.nama + " → Cluster " + nearest);

                if (nearest != item.cluster) {
                    item.cluster = nearest;
                    changed = true;
                }
            }

            // === UPDATE CENTROID ===
            updateCentroids();

            iteration++;

        } while (changed);
    }

    // ================================================================
    //                    K-MEANS++ INITIALIZATION
    // ================================================================
    private void initializeCentroidsPlusPlus() {
        Random rand = new Random(1); // seed biar stabil

        steps.add("=== Inisialisasi Centroid dengan K-Means++ ===");

        // 1️⃣ Ambil centroid pertama secara acak
        int firstIdx = rand.nextInt(data.size());
        centroids[0] = Arrays.copyOf(data.get(firstIdx).features, data.get(0).features.length);

        steps.add("Centroid 0 diambil dari: " + data.get(firstIdx).nama +
                " → " + Arrays.toString(centroids[0]));

        // 2️⃣ Ambil centroid berikutnya berdasarkan probabilitas jarak
        for (int c = 1; c < k; c++) {

            double[] distSq = new double[data.size()];
            double totalDist = 0;

            // Hitung jarak minimum ke centroid yg sudah ada
            for (int i = 0; i < data.size(); i++) {
                MenuItem item = data.get(i);
                double minDist = Double.MAX_VALUE;

                for (int x = 0; x < c; x++) {
                    double sum = 0;
                    for (int f = 0; f < item.features.length; f++) {
                        double diff = item.features[f] - centroids[x][f];
                        sum += diff * diff;
                    }
                    minDist = Math.min(minDist, sum);
                }

                distSq[i] = minDist;
                totalDist += minDist;
            }

            // Pilih index berdasarkan probabilitas dist^2
            double r = rand.nextDouble() * totalDist;
            double running = 0;
            int chosenIdx = 0;

            for (int i = 0; i < distSq.length; i++) {
                running += distSq[i];
                if (running >= r) {
                    chosenIdx = i;
                    break;
                }
            }

            // Set centroid ke-c
            centroids[c] = Arrays.copyOf(data.get(chosenIdx).features, data.get(0).features.length);

            steps.add("Centroid " + c + " dipilih dari item: " + data.get(chosenIdx).nama +
                    " → " + Arrays.toString(centroids[c]) +
                    " (jarak besar → peluang lebih tinggi)");
        }
    }

    // ================================================================
    //                       HITUNG JARAK
    // ================================================================
    private int nearestCentroid(MenuItem item) {
        double minDist = Double.MAX_VALUE;
        int cluster = 0;

        steps.add("Menghitung jarak: " + item.nama);

        for (int i = 0; i < k; i++) {
            double sum = 0;

            for (int f = 0; f < item.features.length; f++) {
                double diff = item.features[f] - centroids[i][f];
                sum += diff * diff;
            }

            double dist = Math.sqrt(sum);

            formulas.add("DIST(" + item.nama + ", C" + i + ") = sqrt(" + sum + ") = " + dist);
            steps.add("  Jarak ke C" + i + ": " + dist);

            if (dist < minDist) {
                minDist = dist;
                cluster = i;
            }
        }

        return cluster;
    }

    // ================================================================
    //                     UPDATE CENTROID
    // ================================================================
    private void updateCentroids() {
        double[][] sum = new double[k][centroids[0].length];
        int[] count = new int[k];

        steps.add("=== Update Centroid ===");

        for (MenuItem item : data) {
            for (int f = 0; f < item.features.length; f++) {
                sum[item.cluster][f] += item.features[f];
            }
            count[item.cluster]++;
        }

        for (int i = 0; i < k; i++) {
            if (count[i] > 0) {
                for (int f = 0; f < centroids[i].length; f++) {
                    double oldValue = centroids[i][f];
                    centroids[i][f] = sum[i][f] / count[i];

                    formulas.add("C" + i + "[" + f + "] = " + sum[i][f] + " / " + count[i] +
                            " = " + centroids[i][f]);

                    steps.add("Centroid " + i + " fitur " + f + ": " +
                            oldValue + " → " + centroids[i][f]);
                }
            }
        }
    }

    // Getter untuk rumus & step
    public List<String> getFormulas() {
        return formulas;
    }

    public List<String> getSteps() {
        return steps;
    }
}