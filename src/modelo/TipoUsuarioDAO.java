/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author John
 */
public class TipoUsuarioDAO implements ConsultasTipoUsuarioDAO {

    @Override
    public ArrayList<TipoUsuarioVO> consultarTabla() {
    Conector c = new Conector();
        ArrayList<TipoUsuarioVO> info = new ArrayList<>();
        try {
            c.conectar();
            String consulta = "SELECT * FROM tbl_tipo_usuario;";
            ResultSet rs = c.consulta_datos(consulta);
            while (rs.next()) {
                TipoUsuarioVO pvo = new TipoUsuarioVO();
                pvo.setId_tipo_usuario(rs.getInt(1));
                pvo.setNombre_tipo_usuario(rs.getString(2));
                pvo.setDescripcion_tipo_usuario(rs.getString(3));
                info.add(pvo);
            }
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Mensaje Mostrar Datos " + e.getMessage());
        }
        return info;
    }
    
}
