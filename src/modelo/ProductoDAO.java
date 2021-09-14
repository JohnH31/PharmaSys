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
public class ProductoDAO implements ConsultaProductoDAO{

    @Override
    public void insertar(ProductoVO p) {
    Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "INSERT INTO tbl_producto(nombre_producto,tipo_producto,descripcion_producto)VALUES('"
                    +p.getNombre_producto()+"','"+p.getTipo_producto()+"','"+p.getDescripcion_producto()+"');";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Insertar " + e.getMessage());
        }
        c.desconectar();
    }

    @Override
    public void actualizar(ProductoVO p) {
    Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "UPDATE tbl_producto SET nombre_producto= '"
                    + p.getNombre_producto()+"',tipo_producto= '"+p.getTipo_producto()
                    +"',descripcion_producto='"+p.getDescripcion_producto()
                    +"' WHERE id_producto = "+ p.getId_producto()+ ";";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Actualizar " + e.getMessage());
        }
    }

    @Override
    public void eliminar(ProductoVO p) {
    Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "DELETE FROM tbl_producto WHERE id_producto =" + p.getId_producto()+";";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Eliminar " + e.getMessage());
        }
        c.desconectar();
    }

    @Override
    public ArrayList<ProductoVO> consultarTabla() {
      Conector c = new Conector();
        ArrayList<ProductoVO> info = new ArrayList<>();
        try {
            c.conectar();
            String consulta = "SELECT * FROM tbl_producto;";
            ResultSet rs = c.consulta_datos(consulta);
            while (rs.next()) {
                ProductoVO pvo = new ProductoVO();
                pvo.setId_producto(rs.getInt(1));
                pvo.setNombre_producto(rs.getString(2));
                pvo.setTipo_producto(rs.getString(3));
                pvo.setDescripcion_producto(rs.getString(4));
                info.add(pvo);
            }
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Mensaje Mostrar Datos " + e.getMessage());
        }
        return info;
   }
    
}
