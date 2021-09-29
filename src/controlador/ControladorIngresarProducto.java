/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import vista.FrmMenu;
import vista.FrmProductos;

/**
 *
 * @author John
 */
public class ControladorIngresarProducto implements ActionListener{
    
    ProductoDAO pdao = new ProductoDAO();
    ProductoVO pvo = new ProductoVO();
    FrmMenu menu = new FrmMenu();
    FrmProductos vista = new FrmProductos();

    public ControladorIngresarProducto(ProductoDAO pdao,ProductoVO pvo,FrmMenu menu,FrmProductos vista ) {
    this.pdao = pdao;
    this.pvo = pvo;
    this.menu = menu;
    this.vista = vista;
    
    menu.menuProductos.addActionListener(this);
    vista.jbtProductoG.addActionListener(this);
    menu.btnProductosR.addActionListener(this);
    
    }
    //metodo para ingresar datos a la bd
     private void insertarUsuario() {
        try {
            pvo.setNombre_producto(vista.jtxfNombrePt.getText());
            pvo.setTipo_producto(vista.jtxfTipoPt.getText());
            pvo.setDescripcion_producto(vista.jtxaDescripcionPt.getText());
            pvo.setPresio_producto(Double.parseDouble(vista.txtPresio.getText()));
            pdao.insertar(pvo);
            vista.jtxfNombrePt.setText("");
            vista.jtxfTipoPt.setText("");
            vista.jtxaDescripcionPt.setText("");
            vista.txtPresio.setText("");
            JOptionPane.showMessageDialog(null, "Registro Ingresado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para guardar registro!");
        }
    }
     //Reporte
    private void reporte() {
        try {
            pdao.reporte();
            pdao.jv.setDefaultCloseOperation(menu.DISPOSE_ON_CLOSE);
            pdao.jv.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Reporte No generado");
        }
    }
     //los botones a ultilizar y que metodos utilizaran
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vista.jbtProductoG) {
            this.insertarUsuario();
        }
        if (e.getSource() == menu.menuProductos) {
            vista.setVisible(true);
        }
        if (e.getSource() == menu.btnProductosR) {
            this.reporte();
        }
    }
    
}
