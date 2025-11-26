/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.dao;

/**
 *
 * @author mhdja
 */

import application.models.SalesKMeansModel;
import java.util.List;

public interface SalesKMeansDao {
    List<SalesKMeansModel> findAll() throws Exception;
}
