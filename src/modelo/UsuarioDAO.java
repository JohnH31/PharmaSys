/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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

    @Override
        public void eliminar(UsuarioVO a) {
        Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "DELETE FROM tbl_usuario WHERE id_usuario =" + a.getId_usuario()+ ";";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Eliminar " + e.getMessage());
        }
        c.desconectar();
    }

    @Override
        public ArrayList<UsuarioVO> consultarTabla() {
            Conector c = new Conector();
        ArrayList<UsuarioVO> info = new ArrayList<>();
        try {
            c.conectar();
            String consulta = "SELECT * FROM tbl_usuario;";
            ResultSet rs = c.consulta_datos(consulta);
            while (rs.next()) {
                UsuarioVO pvo = new UsuarioVO();
                pvo.setId_usuario(rs.getInt(1));
                pvo.setUsuario(rs.getString(2));
                pvo.setClave_usuario(rs.getString(3));
                pvo.setFk_id_tipo_usuario(rs.getInt(4));
                info.add(pvo);
            }
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Mensaje Mostrar Datos " + e.getMessage());
        }
        return info;
   }

    @Override
        public ArrayList<UsuarioVO> validacion(UsuarioVO a) {
            Conector c = new Conector();
        ArrayList<UsuarioVO> info = new ArrayList<>();
        try {
            c.conectar();
            String consulta = "SELECT id_usuario, usuario, fk_id_tipo_usuario  FROM tbl_usuario WHERE usuario = '" + a.getUsuario() + "' AND clave_usuario = '" + a.getClave_usuario() + "';";
            //String consulta = "SELECT usuario,contraseña,id_tipo_usuario_fk  FROM tbl_usuario WHERE usuario = '"+user+"' AND contraseña = '"+contra+"';";
            ResultSet rs = c.consulta_datos(consulta);
            while (rs.next()) {
                UsuarioVO pvo = new UsuarioVO();
                pvo.setId_usuario(rs.getInt(1));
                pvo.setUsuario(rs.getString(2));
                pvo.setFk_id_tipo_usuario(rs.getInt(3));
                dato = pvo.getFk_id_tipo_usuario();
                info.add(pvo);
            }
            c.desconectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El Usuario no existe");
            System.out.println("Mensaje Mostrar Datos " + e.getMessage());
        }
        return info;
    }
    
}
