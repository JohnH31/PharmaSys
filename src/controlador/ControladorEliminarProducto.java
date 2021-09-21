/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.UsuarioVO;
import vista.FrmMenu;
import vista.FrmProductos;

/**
 *
 * @author John
 */
public class ControladorEliminarProducto implements ActionListener, MouseListener{
    
    ProductoDAO pdao = new ProductoDAO();
    ProductoVO pvo = new ProductoVO();
    FrmMenu menu = new FrmMenu();
    FrmProductos vista = new FrmProductos();

    public ControladorEliminarProducto(ProductoDAO pdao,ProductoVO pvo,FrmProductos vista ) {
    this.pdao = pdao;
    this.pvo = pvo;
    this.vista = vista;
    
    vista.jbtnProductoD.addActionListener(this);
    vista.jbtnProductoE.addActionListener(this);
    vista.tblProducto.addMouseListener(this);
    
    }
    private void mostrar() {
        DefaultTableModel m = new DefaultTableModel();
        m.setColumnCount(0);
        m.addColumn("Id");
        m.addColumn("Nombre");
        m.addColumn("Tipo");
        m.addColumn("Descripcion");
        for (ProductoVO pvo : pdao.consultarTabla()) {
            m.addRow(new Object[]{pvo.getId_producto(),pvo.getNombre_producto(),pvo.getTipo_producto(),pvo.getDescripcion_producto()});
        }
        vista.tblProducto.setModel(m);
    }

    private void eliminar() {

        int row = vista.tblProducto.getSelectedRow();
        pvo.setId_producto(Integer.parseInt(vista.tblProducto.getValueAt(row, 0).toString()));
        int men = JOptionPane.showConfirmDialog(null, "Estas seguro que deceas eliminar el registro?", "pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (men == JOptionPane.YES_OPTION) {
            try {
                pdao.eliminar(pvo);
                pvo.setId_producto(row);
            } catch (Exception e) {
                System.out.println("Mensaje eliminar" + e.getMessage());
            }
        }
    }
    
        private void modi() {
        try {
            pvo.getId_producto();
            pvo.setNombre_producto(vista.jtxfNombrePt.getText());
            pvo.setTipo_producto(vista.jtxfTipoPt.getText());
            pvo.setDescripcion_producto(vista.jtxaDescripcionPt.getText());
            pdao.actualizar(pvo);
            vista.jtxfNombrePt.setText("");
            vista.jtxfTipoPt.setText("");
            vista.jtxaDescripcionPt.setText("");
            JOptionPane.showMessageDialog(null, "Registro Modificado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para Modificar registro!");
        }
    }

    private void datos() {
        int row;
        row = vista.tblProducto.getSelectedRow();
        pvo.setId_producto(Integer.parseInt(vista.tblProducto.getValueAt(row, 0).toString()));
        vista.jtxfNombrePt.setText(String.valueOf(vista.tblProducto.getValueAt(row, 1)));
        vista.jtxfTipoPt.setText(String.valueOf(vista.tblProducto.getValueAt(row, 2)));
        vista.jtxaDescripcionPt.setText(String.valueOf(vista.tblProducto.getValueAt(row, 3)));

    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == vista.jbtnProductoD) {
            this.mostrar();
        }
        if (e.getSource() == vista.jbtnProductoE) {
            this.modi();
            this.mostrar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            this.datos();
        }
        if (e.getClickCount() == 2) {
            this.eliminar();
        }
        this.mostrar();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
   
}
