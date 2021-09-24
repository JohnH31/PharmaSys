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
public interface ConsultasFacturaDAO {
    public void insertar(FacturaVO f);
    public void actualizar(FacturaVO f);
    public void eliminar(FacturaVO f);
    public ArrayList<FacturaVO> consultarTabla();
}
