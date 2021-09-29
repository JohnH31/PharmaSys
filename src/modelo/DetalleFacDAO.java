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
public class DetalleFacDAO implements ConsultasDetalleFacDAO{

    @Override
    public void insertar(DetalleFacVO d) {
    Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "INSERT INTO tbl_detalle_factura(cantidad_producto,total_factura,fk_id_producto,presio_factura)VALUES("
                    +d.getCantidad_producto()+","+d.getTotal_factura()+","
                    +d.getFk_id_producto()+","+d.getPresio_factura()+");";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Insertar " + e.getMessage());
        }
        c.desconectar();
    }

    @Override
    public void actualizar(DetalleFacVO d) {
    Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "UPDATE tbl_detalle_factura SET cantidad_producto= "
                    + d.getCantidad_producto()+",total_factura="+d.getTotal_factura()
                    +",fk_id_producto="+d.getFk_id_producto()+",presio_factura="+d.getPresio_factura()
                    +" WHERE id_detalle_factura = "+ d.getId_detalle_factura()+ ";";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Actualizar " + e.getMessage());
        }
    }

    @Override
    public void eliminar(DetalleFacVO d) {
         Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "DELETE FROM tbl_detalle_factura WHERE id_detalle_factura =" + d.getId_detalle_factura()+";";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Eliminar " + e.getMessage());
        }
        c.desconectar();
    }

    @Override
    public ArrayList<DetalleFacVO> consultarTabla() {
    Conector c = new Conector();
        ArrayList<DetalleFacVO> info = new ArrayList<>();
        try {
            c.conectar();
            String consulta = "SELECT * FROM tbl_detalle_factura;";
            ResultSet rs = c.consulta_datos(consulta);
            while (rs.next()) {
                DetalleFacVO pvo = new DetalleFacVO();
                pvo.setId_detalle_factura(rs.getInt(1));
                pvo.setCantidad_producto(rs.getInt(2));
                pvo.setTotal_factura(rs.getDouble(3));
                pvo.setFk_id_producto(rs.getInt(4));
                pvo.setPresio_factura(rs.getDouble(5));
                info.add(pvo);
            }
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Mensaje Mostrar Datos " + e.getMessage());
        }
        return info;
   }
    
}
