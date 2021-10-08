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
public class FacturaDAO implements ConsultasFacturaDAO{

    @Override
    public void insertar(FacturaVO f) {
    Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "INSERT INTO tbl_factura(fecha_factura,fk_id_cliente)VALUES('"
                    +f.getFecha_factura()+"',"+f.getFk_id_cliente()+");";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Insertar " + e.getMessage());
        }
        c.desconectar();
    }

    @Override
    public void actualizar(FacturaVO f) {
        Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "UPDATE tbl_factura SET fecha_factura= '"
                    + f.getFecha_factura()+"',fk_id_cliente = "+f.getFk_id_cliente()
                    +" WHERE id_factura = "+ f.getId_factura()+ ";";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Actualizar " + e.getMessage());
        }
    }

    @Override
    public void eliminar(FacturaVO f) {
            Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "DELETE FROM tbl_factura WHERE id_factura =" + f.getId_factura()+";";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Eliminar " + e.getMessage());
        }
        c.desconectar();
    }

    @Override
    public ArrayList<FacturaVO> consultarTabla() {
    Conector c = new Conector();
        ArrayList<FacturaVO> info = new ArrayList<>();
        try {
            c.conectar();
            String consulta = "SELECT * FROM tbl_factura;";
            ResultSet rs = c.consulta_datos(consulta);
            while (rs.next()) {
                FacturaVO fvo = new FacturaVO();
                fvo.setId_factura(rs.getInt(1));
                fvo.setFecha_factura(rs.getString(2));
                fvo.setFk_id_cliente(rs.getInt(3));
                info.add(fvo);
            }
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Mensaje Mostrar Datos " + e.getMessage());
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
        
        String ruta = "C:\\Users\\John\\Documents\\NetBeansProjects\\FarmaciaPharmaSys\\src\\reportes\\ReporteFactura.jasper";
        //asignacion de ruta
        reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
        
        JasperPrint jp = JasperFillManager.fillReport(reporte, null,c.con);
        JasperViewer jv = new JasperViewer(jp,false);
        this.jv = jv;
                
        }catch (Exception e) {
            System.out.println("Mensaje reporte"+e.getMessage());
        }
    }
}
