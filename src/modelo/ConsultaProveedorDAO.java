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
public interface ConsultaProveedorDAO {
    public void insertar(ProveedorVO p);
    public void actualizar(ProveedorVO p);
    public void eliminar(ProveedorVO p);
    public ArrayList<ProveedorVO> consultarTabla();
}
