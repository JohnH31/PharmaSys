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
import modelo.PedidoDAO;
import modelo.PedidoVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import vista.FrmMenu;
import vista.FrmPedido;

/**
 *
 * @author John
 */
public class ControladorMenuPedido implements ActionListener, MouseListener{
    
    PedidoDAO pdao = new PedidoDAO();
    PedidoVO pvo = new PedidoVO();
    FrmMenu menu = new FrmMenu();
    FrmPedido vista = new FrmPedido();

    public ControladorMenuPedido(PedidoDAO pdao,PedidoVO pvo,FrmMenu menu,FrmPedido vista) {
        this.pdao = pdao;
        this.pvo = pvo;
        this.menu = menu;
        this.vista = vista;
        
        vista.btnGuardar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnEditar.addActionListener(this);
        vista.tblPedido.addMouseListener(this);
        menu.menuPedidos.addActionListener(this);
    }
   
    private void insertar() {
        try {
            pvo.setFecha_pedido(vista.txtFecha.getText());
            pvo.setDescripcion_pedido(vista.txtDescripcion.getText());
            pvo.setEstado_pedido(Boolean.parseBoolean(vista.cbxEstadoPedido.getSelectedItem().toString()));
            pvo.setFk_id_producto_pedido(Integer.parseInt(vista.cbxIdProducto1.getSelectedItem().toString()));
            pdao.insertar(pvo);
            vista.txtFecha.setText("");
            vista.txtDescripcion.setText("");
            vista.cbxEstadoPedido.setSelectedIndex(0);
            vista.cbxIdProducto1.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, "Registro Ingresado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para guardar registro!");
        }
    }

    public void cargarProducto(int buscar) {
        ProductoDAO pdao = new ProductoDAO();
        int index = 1;
        vista.cbxIdProducto1.removeAllItems();
        vista.cbxEstadoPedido.removeAllItems();
        vista.cbxIdProducto1.addItem("Seleccione Producto");
        vista.cbxEstadoPedido.addItem("Seleccione Estado");
        vista.cbxEstadoPedido.addItem("TRUE");
        vista.cbxEstadoPedido.addItem("FALSE");
        for (ProductoVO pvo : pdao.consultarTabla()) {
            vista.cbxIdProducto1.addItem(String.valueOf(pvo.getId_producto()));
            //vista.cbxLibroAutor.addItem(lvo.getNombre_libro());
            if (pvo.getId_producto() == buscar) {
                vista.cbxIdProducto1.setSelectedIndex(index);
            }
            index++;
        }
    }
    
    private void mostrar() {
        DefaultTableModel m = new DefaultTableModel();
        m.setColumnCount(0);
        m.addColumn("Id");
        m.addColumn("Fecha");
        m.addColumn("Descripcion");
        m.addColumn("Estado Pedido");
        m.addColumn("IdProducto");
        for (PedidoVO pvo : pdao.consultarTabla()) {
            m.addRow(new Object[]{pvo.getId_pedido(),pvo.getFecha_pedido(),pvo.getDescripcion_pedido(),pvo.isEstado_pedido(),pvo.getFk_id_producto_pedido()});
        }
        vista.tblPedido.setModel(m);
    }

    private void eliminar() {

        int row = vista.tblPedido.getSelectedRow();
        pvo.setId_pedido(Integer.parseInt(vista.tblPedido.getValueAt(row, 0).toString()));
        int men = JOptionPane.showConfirmDialog(null, "Estas seguro que deceas eliminar el registro?", "pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (men == JOptionPane.YES_OPTION) {
            try {
                pdao.eliminar(pvo);
                pvo.setId_pedido(row);
            } catch (Exception e) {
                System.out.println("Mensaje eliminar" + e.getMessage());
            }
        }
    }
    
    private void modi() {
        try {
            pvo.getId_pedido();
            pvo.setFecha_pedido(vista.txtFecha.getText());
            pvo.setDescripcion_pedido(vista.txtDescripcion.getText());
            pvo.setEstado_pedido(Boolean.parseBoolean(vista.cbxEstadoPedido.getSelectedItem().toString()));
            pvo.setFk_id_producto_pedido(Integer.parseInt(vista.cbxIdProducto1.getSelectedItem().toString()));
            pdao.actualizar(pvo);
            vista.txtFecha.setText("");
            vista.txtDescripcion.setText("");
            vista.cbxEstadoPedido.setSelectedIndex(0);
            vista.cbxIdProducto1.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, "Registro Modificado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para Modificar registro!");
        }
    }

    private void datos() {
        int row;
        row = vista.tblPedido.getSelectedRow();
        pvo.setId_pedido(Integer.parseInt(vista.tblPedido.getValueAt(row, 0).toString()));
        vista.txtFecha.setText(String.valueOf(vista.tblPedido.getValueAt(row, 1)));
        vista.txtDescripcion.setText(String.valueOf(vista.tblPedido.getValueAt(row, 2)));
        pvo.setEstado_pedido((boolean) vista.tblPedido.getValueAt(row, 3));
        pvo.setFk_id_producto_pedido((int) vista.tblPedido.getValueAt(row, 4));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.menuPedidos) {
            vista.setVisible(true);
        }
        if (e.getSource() == vista.btnGuardar) {
            this.insertar();
        }
        if (e.getSource() == vista.btnEliminar) {
            this.mostrar();
        }
        if (e.getSource() == vista.btnEditar) {
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
