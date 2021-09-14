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
public interface ConsultasCompraDAO {
    public void insertar(CompraVO c);
    public void actualizar(CompraVO c);
    public void eliminar(CompraVO c);
    public ArrayList<CompraVO> consultarTabla();
    
}
