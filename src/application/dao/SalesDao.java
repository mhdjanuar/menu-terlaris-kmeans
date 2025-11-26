/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.dao;

import application.models.SalesModel;
import java.util.List;

/**
 *
 * @author mhdja
 */


public interface SalesDao {
    List<SalesModel> findAll();
    SalesModel findById(int id);
    int create(SalesModel sales);
    int update(SalesModel sales);
    int delete(int id);
}