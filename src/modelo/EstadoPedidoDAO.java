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
public class EstadoPedidoDAO implements ConsultaEstadoPedidoDAO {

    @Override
    public ArrayList<EstadoPedidoVO> consultarTabla() {
    Conector c = new Conector();
        ArrayList<EstadoPedidoVO> info = new ArrayList<>();
        try {
            c.conectar();
            String consulta = "SELECT * FROM tbl_estado_pedido;";
            ResultSet rs = c.consulta_datos(consulta);
            while (rs.next()) {
                EstadoPedidoVO pvo = new EstadoPedidoVO();
                pvo.setId_estado_pedido(rs.getInt(1));
                pvo.setEstado_pedido(rs.getString(2));
                info.add(pvo);
            }
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Mensaje Mostrar Datos " + e.getMessage());
        }
        return info;
    }
    
}
