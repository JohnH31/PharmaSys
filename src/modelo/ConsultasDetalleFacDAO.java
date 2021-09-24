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
public interface ConsultasDetalleFacDAO {
    public void insertar(DetalleFacVO d);
    public void actualizar(DetalleFacVO d);
    public void eliminar(DetalleFacVO d);
    public ArrayList<DetalleFacVO> consultarTabla();
}
