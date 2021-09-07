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
public class UsuarioDAO implements ConsultasUsuarioDAO {

    public int dato;

    @Override
    public void insertar(UsuarioVO a) {
        Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "INSERT INTO tbl_usuario(usuario, clave_usuario, fk_id_tipo_usuario)VALUES('"
                    + a.getUsuario() + "','" + a.getClave_usuario() + "'," + a.getFk_id_tipo_usuario() + ");";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Insertar " + e.getMessage());
        }
        c.desconectar();
    }
}

    @Override
        public void eliminar(UsuarioVO a) {
        Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "DELETE FROM tbl_usuario WHERE id_usuario =" + a.getId_usuario() + ";";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Eliminar " + e.getMessage());
        }
        c.desconectar();
    }

    @Override
        public ArrayList<UsuarioVO> consultarTabla() {
   }

    @Override
        public ArrayList<UsuarioVO> validacion(UsuarioVO a) {
    }
    
}
