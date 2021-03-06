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
public class ClienteDAO implements ConsultasClienteDAO{

    @Override
    public void insertar(ClienteVO c) {
    Conector d = new Conector();
        try {
            d.conectar();
            String consulta = "INSERT INTO tbl_cliente_android(nombre_cliente, apellido_cliente, direccion_cliente,correo_cliente,clave_cliente)VALUES ('"
                    +c.getNombre_cliente()+"','"+c.getApellido_cliente()+"','"
                    +c.getDireccion_cliente()+"','"+c.getCorreo_cliente()+"','"+c.getClave_cliente()+"');";
            d.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Insertar " + e.getMessage());
        }
        d.desconectar();
    }

    @Override
    public void actualizar(ClienteVO c) {
      Conector d = new Conector();
        try {
            d.conectar();
            String consulta = "UPDATE tbl_cliente_android SET nombre_cliente= '"
                    + c.getNombre_cliente()+"',apellido_cliente= '"+c.getApellido_cliente()
                    +"',direccion_cliente= '"+c.getDireccion_cliente()+"',correo_cliente='"+c.getCorreo_cliente()
                    +"',clave_cliente='"+c.getClave_cliente()+"' WHERE id_cliente = "+ c.getId_cliente()+ ";";
            d.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Actualizar " + e.getMessage());
        }
    }

    @Override
    public void eliminar(ClienteVO c) {
      Conector d = new Conector();
        try {
            d.conectar();
            String consulta = "DELETE FROM tbl_cliente_android WHERE id_cliente =" + c.getId_cliente()+";";
            d.consultas_multiples(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje Eliminar " + e.getMessage());
        }
        d.desconectar();
    }

    @Override
    public ArrayList<ClienteVO> consultarTabla() {
       Conector c = new Conector();
        ArrayList<ClienteVO> info = new ArrayList<>();
        try {
            c.conectar();
            String consulta = "SELECT * FROM tbl_cliente_android;";
            ResultSet rs = c.consulta_datos(consulta);
            while (rs.next()) {
                ClienteVO cvo = new ClienteVO();
                cvo.setId_cliente(rs.getInt(1));
                cvo.setNombre_cliente(rs.getString(2));
                cvo.setApellido_cliente(rs.getString(3));
                cvo.setDireccion_cliente(rs.getString(4));
                cvo.setCorreo_cliente(rs.getString(5));
                cvo.setClave_cliente(rs.getString(6));
                info.add(cvo);
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
        
        String ruta = "C:\\Users\\John\\Documents\\NetBeansProjects\\FarmaciaPharmaSys\\src\\reportes\\ReporteClientes.jasper";
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
