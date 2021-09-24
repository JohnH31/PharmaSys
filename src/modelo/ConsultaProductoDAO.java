/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author John
 */
public interface ConsultaProductoDAO {
     public void insertar(ProductoVO p);
     public void actualizar(ProductoVO p);
     public void eliminar(ProductoVO p);
     public ArrayList<ProductoVO> consultarTabla();
     public ArrayList<ProductoVO> consultarJoin();

}
