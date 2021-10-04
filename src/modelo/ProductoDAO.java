/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

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
            String consulta = "INSERT INTO tbl_producto(nombre_producto,tipo_producto,descripcion_producto,fk_id_proveedor,precio_producto)VALUES('"
                    +p.getNombre_producto()+"','"+p.getTipo_producto()+"','"+p.getDescripcion_producto()+"',"+p.getFk_id_proveedor()+","+p.getPrecio_producto()+");";
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
                    +"',descripcion_producto='"+p.getDescripcion_producto()+",fk_id_proveedor="+p.getFk_id_proveedor()+
                    ",precio_producto= "+p.getPrecio_producto()
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
                pvo.setFk_id_proveedor(rs.getInt(5));
                pvo.setPrecio_producto(rs.getDouble(6));
                info.add(pvo);
            }
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Mensaje Mostrar Datos " + e.getMessage());
        }
        return info;
   }

    @Override
    public ArrayList<ProductoVO> consultarJoin() {
     Conector c = new Conector();
        ArrayList<ProductoVO> info = new ArrayList<>();
        ArrayList<PedidoVO> infos = new ArrayList<>();
        try {
            c.conectar();
            String consulta = "SELECT id_producto,nombre_producto,tipo_producto,precio_producto,fecha_pedido,descripcion_pedido,fk_id_estadop,fk_id_producto_pedido FROM tbl_producto A RIGHT JOIN tbl_pedido L ON A.id_producto = L.fk_id_producto_pedido";
            ResultSet rs = c.consulta_datos(consulta);
            while(rs.next()){
                PedidoVO pvo = new PedidoVO();
                ProductoVO dvo = new ProductoVO();
                dvo.setId_producto(rs.getInt(1));
                dvo.setNombre_producto(rs.getString(2));
                dvo.setTipo_producto(rs.getString(3));
                dvo.setPrecio_producto(rs.getDouble(4));
                pvo.setFecha_pedido(rs.getString(5));
                pvo.setDescripcion_pedido(rs.getString(6));
                pvo.setFk_id_estadop(rs.getInt(7));
                pvo.setFk_id_producto_pedido(rs.getInt(8));
                info.add(dvo);
                infos.add(pvo);
            }
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Mensaje Mostrar Datos "+e.getMessage());
        }
        return info;    
    }
    public JasperViewer jv;
        //metodo para el reporte
    public void reporte(){
        Conector c = new Conector();
        try{
        c.conectar();
        //variable para encontrar el reporte
        JasperReport reporte;
        //Ruta del reporte
        
        String ruta = "C:\\Users\\John\\Documents\\NetBeansProjects\\FarmaciaPharmaSys\\src\\reportes\\ReporteProductos.jasper";
        //asignacion de ruta
        reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
        
        JasperPrint jp = JasperFillManager.fillReport(reporte, null,c.con);
        JasperViewer jv = new JasperViewer(jp,false);
        this.jv = jv;
                
        }catch (Exception e) {
            System.out.println("Mnesaje reporte"+e.getMessage());
        }
    }
    
}
