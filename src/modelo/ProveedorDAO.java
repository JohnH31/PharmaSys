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
public class ProveedorDAO implements ConsultaProveedorDAO{

    @Override
    public void insertar(ProveedorVO p) {
    Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "INSERT INTO tbl_proveedor(nombre_proveedor,telefono_proveedor,direccion_proveedor,correo_proveedor)VALUES('"
                    +p.getNombre_proveedor()+"',"+p.getTelefono_proveedor()+",'"+p.getDireccion_proveedor()+"','"+p.getCorreo_proveedor()+"');";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Insertar " + e.getMessage());
        }
        c.desconectar();
    }

    @Override
    public void actualizar(ProveedorVO p) {
    Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "UPDATE tbl_proveedor SET nombre_proveedor= '"
                    + p.getNombre_proveedor()+"',telefono_proveedor="+p.getTelefono_proveedor()
                    + ",direccion_proveedor= '"+p.getDireccion_proveedor()+ "',correo_proveedor= '"
                    + p.getCorreo_proveedor()+"' WHERE id_proveedor = "
                    + p.getId_proveedor()+ ";";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Actualizar " + e.getMessage());
        }
    }

    @Override
    public void eliminar(ProveedorVO p) {
    Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "DELETE FROM tbl_proveedor WHERE id_proveedor =" + p.getId_proveedor()+";";
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Eliminar " + e.getMessage());
        }
        c.desconectar();
    }

    @Override
    public ArrayList<ProveedorVO> consultarTabla() {
      Conector c = new Conector();
        ArrayList<ProveedorVO> info = new ArrayList<>();
        try {
            c.conectar();
            String consulta = "SELECT * FROM tbl_proveedor;";
            ResultSet rs = c.consulta_datos(consulta);
            while (rs.next()) {
                ProveedorVO pvo = new ProveedorVO();
                pvo.setId_proveedor(rs.getInt(1));
                pvo.setNombre_proveedor(rs.getString(2));
                pvo.setTelefono_proveedor(rs.getInt(3));
                pvo.setDireccion_proveedor(rs.getString(4));
                pvo.setCorreo_proveedor(rs.getString(5));
                info.add(pvo);
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
        
        String ruta = "C:\\Users\\John\\Documents\\NetBeansProjects\\FarmaciaPharmaSys\\src\\reportes\\ReporteProveedores.jasper";
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
