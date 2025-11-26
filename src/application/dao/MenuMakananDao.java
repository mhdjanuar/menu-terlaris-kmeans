/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package application.dao;

import application.models.MenuMakananModel;
import java.util.List;

/**
 *
 * @author mhdja
 */


public interface MenuMakananDao {
    List<MenuMakananModel> findAll();
    MenuMakananModel findById(int id);
    int create(MenuMakananModel menu);
    int update(MenuMakananModel menu);
    int delete(int id);
}
