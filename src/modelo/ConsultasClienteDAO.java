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
public interface ConsultasClienteDAO {
    public void insertar(ClienteVO c);
    public void actualizar(ClienteVO c);
    public void eliminar(ClienteVO c);
    public ArrayList<ClienteVO> consultarTabla();
    
}
