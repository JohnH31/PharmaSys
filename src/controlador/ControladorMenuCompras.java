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
import modelo.CompraDAO;
import modelo.CompraVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import vista.FrmCompras;
import vista.FrmMenu;

/**
 *
 * @author John
 */
public class ControladorMenuCompras implements ActionListener, MouseListener {
    
    CompraDAO cdao = new CompraDAO();
    CompraVO cvo = new CompraVO();
    FrmMenu menu = new FrmMenu();
    FrmCompras vista = new FrmCompras();

    public ControladorMenuCompras(CompraDAO cdao,CompraVO cvo,FrmMenu menu,FrmCompras vista) {
        this.cdao = cdao;
        this.cvo = cvo;
        this.menu = menu;
        this.vista = vista;
        
        vista.jbtCompraG.addActionListener(this);
        vista.jbtnCompraD.addActionListener(this);
        vista.tblCompras.addMouseListener(this);
        menu.menuCompras.addActionListener(this);
    }   
    
    private void insertarCompra() {
        try {
            cvo.setFecha_compra(vista.txtFecha.getText());
            cvo.setCantidad_producto(Integer.parseInt(vista.txtCantidad.getText().toString()));
            cvo.setPrecio_producto(Integer.parseInt(vista.txtPresioCom.getText().toString()));
            cvo.setFk_id_producto(Integer.parseInt(vista.cbxIdProducto.getSelectedItem().toString()));
            cdao.insertar(cvo);
            vista.txtFecha.setText("");
            vista.txtCantidad.setText("");
            vista.txtPresioCom.setText("");
            vista.cbxIdProducto.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, "Registro Ingresado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para guardar registro!");
        }
    }

    public void cargarProducto(int buscar) {
        ProductoDAO pdao = new ProductoDAO();
        int index = 1;
        vista.cbxIdProducto.removeAllItems();
        vista.cbxIdProducto.addItem("Seleccione Estado");
        for (ProductoVO pvo : pdao.consultarTabla()) {
            vista.cbxIdProducto.addItem(String.valueOf(pvo.getId_producto()));
            //vista.cbxLibroAutor.addItem(lvo.getNombre_libro());
            if (pvo.getId_producto() == buscar) {
                vista.cbxIdProducto.setSelectedIndex(index);
            }
            index++;
        }
    }
    
    private void mostrar() {
        DefaultTableModel m = new DefaultTableModel();
        m.setColumnCount(0);
        m.addColumn("Id");
        m.addColumn("Fecha");
        m.addColumn("Cantidad");
        m.addColumn("Presio");
        m.addColumn("IdProducto");
        for (CompraVO cvo : cdao.consultarTabla()) {
            m.addRow(new Object[]{cvo.getId_compra(),cvo.getFecha_compra(),cvo.getCantidad_producto(),cvo.getPrecio_producto(),cvo.getFk_id_producto()});
        }
        vista.tblCompras.setModel(m);
    }

    private void eliminar() {

        int row = vista.tblCompras.getSelectedRow();
        cvo.setId_compra(Integer.parseInt(vista.tblCompras.getValueAt(row, 0).toString()));
        int men = JOptionPane.showConfirmDialog(null, "Estas seguro que deceas eliminar el registro?", "pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (men == JOptionPane.YES_OPTION) {
            try {
                cdao.eliminar(cvo);
                cvo.setId_compra(row);
            } catch (Exception e) {
                System.out.println("Mensaje eliminar" + e.getMessage());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.menuCompras) {
            vista.setVisible(true);
        }
        if (e.getSource() == vista.jbtCompraG) {
            this.insertarCompra();
        }
        if (e.getSource() == vista.jbtnCompraD) {
            this.mostrar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.eliminar();
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
