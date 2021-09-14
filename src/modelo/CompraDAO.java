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
public class CompraDAO implements ConsultasCompraDAO{

    @Override
    public void insertar(CompraVO c) {
    Conector d = new Conector();
        try {
            d.conectar();
            String consulta = "INSERT INTO tbl_compra(fecha_compra,cantidad_producto,precio_producto,fk_id_producto)VALUES('"
                    + c.getFecha_compra()+"',"+c.getCantidad_producto()+","+c.getPrecio_producto()+","
                    + c.getFk_id_producto()+");";
            d.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Insertar " + e.getMessage());
        }
        d.desconectar();
    }

    @Override
    public void actualizar(CompraVO c) {
      Conector d = new Conector();
        try {
            d.conectar();
            String consulta = "UPDATE tbl_compra SET fecha_compra= '"
                    + c.getFecha_compra()+"',cantidad_producto= "+c.getCantidad_producto()
                    +",precio_producto="+c.getPrecio_producto()+",fk_id_producto= "+c.getFk_id_producto()
                    +" WHERE id_compra = "+ c.getId_compra()+ ";";
            d.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Actualizar " + e.getMessage());
        }
    }

    @Override
    public void eliminar(CompraVO c) {
         Conector d = new Conector();
        try {
            d.conectar();
            String consulta = "DELETE FROM tbl_compra WHERE id_compra =" + c.getId_compra()+";";
            d.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Eliminar " + e.getMessage());
        }
        d.desconectar();
    }

    @Override
    public ArrayList<CompraVO> consultarTabla() {
         Conector c = new Conector();
        ArrayList<CompraVO> info = new ArrayList<>();
        try {
            c.conectar();
            String consulta = "SELECT * FROM tbl_compra;";
            ResultSet rs = c.consulta_datos(consulta);
            while (rs.next()) {
                CompraVO pvo = new CompraVO();
                pvo.setId_compra(rs.getInt(1));
                pvo.setFecha_compra(rs.getString(2));
                pvo.setCantidad_producto(rs.getInt(3));
                pvo.setPrecio_producto(rs.getInt(4));
                pvo.setFk_id_producto(rs.getInt(5));
                info.add(pvo);
            }
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Mensaje Mostrar Datos " + e.getMessage());
        }
        return info;
   }
    
}
