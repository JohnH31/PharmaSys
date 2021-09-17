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
import vista.jifrmProductos;

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
    
    }
    
     private void insertarUsuario() {
        try {
            pvo.setNombre_producto(vista.jtxfNombrePt.getText());
            pvo.setTipo_producto(vista.jtxfTipoPt.getText());
            pvo.setDescripcion_producto(vista.jtxaDescripcionPt.getText());
            
            pdao.insertar(pvo);
            vista.jtxfNombrePt.setText("");
            vista.jtxfTipoPt.setText("");
            vista.jtxaDescripcionPt.setText("");
            JOptionPane.showMessageDialog(null, "Registro Ingresado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para guardar registro!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vista.jbtProductoG) {
            this.insertarUsuario();
        }
        if (e.getSource() == menu.menuProductos) {
            vista.setVisible(true);
        }
    }
    
}
