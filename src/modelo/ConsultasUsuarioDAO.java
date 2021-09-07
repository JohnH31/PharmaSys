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
public interface ConsultasUsuarioDAO {
    public void insertar(UsuarioVO a);
    public void eliminar(UsuarioVO a);
    public ArrayList<UsuarioVO> consultarTabla();
    public ArrayList<UsuarioVO> validacion(UsuarioVO a);
}
