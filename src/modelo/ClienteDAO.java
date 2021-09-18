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
public class ClienteDAO implements ConsultasClienteDAO{

    @Override
    public void insertar(ClienteVO c) {
    Conector d = new Conector();
        try {
            d.conectar();
            String consulta = "INSERT INTO tbl_cliente(nombre_cliente, apellido_cliente, direccion_cliente)VALUES ('"
                    +c.getNombre_cliente()+"','"+c.getApellido_cliente()+"','"
                    +c.getDireccion_cliente()+"');";
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
            String consulta = "UPDATE tbl_cliente SET nombre_cliente= '"
                    + c.getNombre_cliente()+"',apellido_cliente= '"+c.getApellido_cliente()
                    +"',direccion_cliente= '"+c.getDireccion_cliente()+"' WHERE id_cliente = "+ c.getId_cliente()+ ";";
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
            String consulta = "DELETE FROM tbl_cliente WHERE id_cliente =" + c.getId_cliente()+";";
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
            String consulta = "SELECT * FROM tbl_cliente;";
            ResultSet rs = c.consulta_datos(consulta);
            while (rs.next()) {
                ClienteVO cvo = new ClienteVO();
                cvo.setId_cliente(rs.getInt(1));
                cvo.setNombre_cliente(rs.getString(2));
                cvo.setApellido_cliente(rs.getString(3));
                cvo.setDireccion_cliente(rs.getString(4));
                info.add(cvo);
            }
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Mensaje Mostrar Datos " + e.getMessage());
        }
        return info;
   }
    
}
