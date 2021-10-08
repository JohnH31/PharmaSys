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
public class PedidoDAO implements ConsultasPedidoDAO{
        
    @Override
    public void insertar(PedidoVO p) {
    Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "INSERT INTO tbl_pedido (fecha_pedido, fk_id_cliente, fk_id_producto_pedido, descripcion_pedido, precio_pedido, cantidad_pedido, total_pedido, fk_id_estadop) VALUES('"
                    +p.getFecha_pedido()+"',"+p.getFk_id_cliente()+","+p.getFk_id_producto_pedido()+",'"+p.getDescripcion_pedido()+"',"+p.getPrecio_pedido()+","
                            +p.getCantidad_pedido()+","+p.getTotal_pedido()+","+p.getFk_id_estadop()+");";
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
                    + p.getFecha_pedido()+"',fk_id_cliente="+p.getFk_id_cliente()+",fk_id_producto_pedido="+p.getFk_id_producto_pedido()
                    +",descripcion_pedido= '"+p.getDescripcion_pedido()+"',precio_pedido="+p.getPrecio_pedido()
                    +",cantidad_pedido="+p.getCantidad_pedido()+",total_pedido="+p.getTotal_pedido()
                    +",fk_id_estadop="+p.getFk_id_estadop()
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
                pvo.setFk_id_cliente(rs.getInt(3));
                pvo.setFk_id_producto_pedido(rs.getInt(4));
                pvo.setDescripcion_pedido(rs.getString(5));
                pvo.setPrecio_pedido(rs.getDouble(6));
                pvo.setCantidad_pedido(rs.getInt(7));
                pvo.setTotal_pedido(rs.getDouble(8));
                pvo.setFk_id_estadop(rs.getInt(9));
                info.add(pvo);
            }
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Mensaje Mostrar Datos " + e.getMessage());
        }
        return info;
   }

    @Override
    public ArrayList<PedidoVO> consultarJoin() {
        Conector c = new Conector();
        ArrayList<PedidoVO> info = new ArrayList<>();
        ArrayList<ProductoVO> infos = new ArrayList<>();
        try {
            c.conectar();
            String consulta = "SELECT id_producto,nombre_producto,tipo_producto,precio_producto,fecha_pedido,fk_id_cliente,descripcion_pedido,fk_id_estadop,fk_id_producto_pedido FROM tbl_producto A RIGHT JOIN tbl_pedido L ON A.id_producto = L.fk_id_producto_pedido";
            ResultSet rs = c.consulta_datos(consulta);
            while(rs.next()){
                PedidoVO pvo = new PedidoVO();
                ProductoVO dvo = new ProductoVO();
                dvo.setId_producto(rs.getInt(1));
                dvo.setNombre_producto(rs.getString(2));
                dvo.setTipo_producto(rs.getString(3));
                dvo.setPrecio_producto(rs.getDouble(4));
                pvo.setFecha_pedido(rs.getString(5));
                pvo.setFk_id_cliente(rs.getInt(6));
                pvo.setDescripcion_pedido(rs.getString(7));
                pvo.setFk_id_estadop(rs.getInt(8));
                pvo.setFk_id_producto_pedido(rs.getInt(9));
                info.add(pvo);
                infos.add(dvo);
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
        
        String ruta = "C:\\Users\\John\\Documents\\NetBeansProjects\\FarmaciaPharmaSys\\src\\reportes\\ReportePedidos.jasper";
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
