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
public class PedidoDAO implements ConsultasPedidoDAO{
        
    @Override
    public void insertar(PedidoVO p) {
    Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "INSERT INTO tbl_pedido(fecha_pedido,descripcion_pedido,estado_pedido,fk_id_producto_pedido)VALUES('"
                    +p.getFecha_pedido()+"','"+p.getDescripcion_pedido()+"',"+p.isEstado_pedido()+","+p.getFk_id_producto_pedido()+");";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Insertar " + e.getMessage());
        }
        c.desconectar();
    }

    @Override
    public void actualizar(PedidoVO p) {
    Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "UPDATE tbl_pedido SET fecha_pedido= '"
                    + p.getFecha_pedido()+"',descripcion_pedido= '"+p.getDescripcion_pedido()
                    +"',estado_pedido="+p.isEstado_pedido()+",fk_id_producto_pedido="+p.getFk_id_producto_pedido()
                    +" WHERE id_pedido = "+ p.getId_pedido()+ ";";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Actualizar " + e.getMessage());
        }
    }

    @Override
    public void eliminar(PedidoVO p) {
    Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "DELETE FROM tbl_pedido WHERE id_pedido =" + p.getId_pedido()+";";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Eliminar " + e.getMessage());
        }
        c.desconectar();
    }

    @Override
    public ArrayList<PedidoVO> consultarTabla() {
    Conector c = new Conector();
        ArrayList<PedidoVO> info = new ArrayList<>();
        try {
            c.conectar();
            String consulta = "SELECT * FROM tbl_pedido;";
            ResultSet rs = c.consulta_datos(consulta);
            while (rs.next()) {
                PedidoVO pvo = new PedidoVO();
                pvo.setId_pedido(rs.getInt(1));
                pvo.setFecha_pedido(rs.getString(2));
                pvo.setDescripcion_pedido(rs.getString(3));
                pvo.setEstado_pedido(rs.getBoolean(4));
                pvo.setFk_id_producto_pedido(rs.getInt(5));
                info.add(pvo);
            }
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Mensaje Mostrar Datos " + e.getMessage());
        }
        return info;
   }
    
}
