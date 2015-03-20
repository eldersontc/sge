/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.MenuDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MenuDTO {
    
    MenuDAO menuDAO;
    
    public List<Object[]> ObtenerMenus(){
        List<Object[]> lista;
        try {
            menuDAO = new MenuDAO();
            menuDAO.AbrirSesision();
            lista = menuDAO.ObtenerMenus();
        } catch (Exception e) {
            throw  e;
        } finally {
            menuDAO.CerrarSesion();
        }
        return lista;
    }
}
