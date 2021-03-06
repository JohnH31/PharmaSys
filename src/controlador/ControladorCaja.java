/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.EstadoPedidoDAO;
import modelo.EstadoPedidoVO;
import modelo.PedidoDAO;
import modelo.PedidoVO;
import vista.FrmCaja;
import vista.FrmMenu;

/**
 *
 * @author John
 */
public class ControladorCaja implements ActionListener, MouseListener, KeyListener,WindowListener{
    
    PedidoDAO pdao = new PedidoDAO();
    PedidoVO pvo = new PedidoVO();
    FrmMenu menu = new FrmMenu();
    FrmCaja vista = new FrmCaja();
    DefaultTableModel m; //= new DefaultTableModel(); metodo para el uso de la tabla
    TableRowSorter tr;//metodo para el filtro en la tabla

    public ControladorCaja(PedidoDAO pdao,PedidoVO pvo,FrmMenu menu,FrmCaja vista) {
        this.pdao = pdao;
        this.pvo = pvo;
        this.menu = menu;
        this.vista = vista;
        
        vista.btnEliminar.addActionListener(this);
        vista.btnModificar.addActionListener(this);
        vista.tblPedido.addMouseListener(this);
        menu.btncaja.addActionListener(this);
        vista.addWindowListener(this);
        vista.txtFiltro.addKeyListener(this);
        
    }
    
     //metodo para cargar los estado en el combobox
    public void cargarEstado(int buscar) {
        EstadoPedidoDAO pdao = new EstadoPedidoDAO();
        int index = 1;
        vista.cbxEstadoPedido.removeAllItems();
        vista.cbxEstadoPedido.addItem("Seleccione Estado");
        for (EstadoPedidoVO pvo : pdao.consultarTabla()) {
            vista.cbxEstadoPedido.addItem(String.valueOf(pvo.getId_estado_pedido()));
            //vista.cbxLibroAutor.addItem(lvo.getNombre_libro());
            if (pvo.getId_estado_pedido() == buscar) {
                vista.cbxEstadoPedido.setSelectedIndex(index);
            }
            index++;
        }
    }
    
   //metodo para mostrar los datos de la bd en la tabla
    private void mostrar() {
        //DefaultTableModel m = new DefaultTableModel();
        m = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==9) {
                    return true;
                }else{
                    return false;
                }
            }
            
        };
        tr = new TableRowSorter(m);
        m.setColumnCount(0);
        m.addColumn("Id");
        m.addColumn("Fecha");
        m.addColumn("IdCliente");
        m.addColumn("IdProducto");
        m.addColumn("Descripcion");
        m.addColumn("Precio");
        m.addColumn("Cantidad");
        m.addColumn("Total");
        m.addColumn("Estado Pedido");
        for (PedidoVO pvo : pdao.consultarTabla()) {
            m.addRow(new Object[]{pvo.getId_pedido(),pvo.getFecha_pedido(),pvo.getFk_id_cliente(),pvo.getFk_id_producto_pedido(),pvo.getDescripcion_pedido(),pvo.getPrecio_pedido(),pvo.getCantidad_pedido(),pvo.getTotal_pedido(),pvo.getFk_id_estadop()});
        }
        vista.tblPedido.setModel(m);
        vista.tblPedido.setRowSorter(tr);
    }
    
    //metodo para eliminar datos en la tabla 
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
    
    //metodo para modificar datos en la bd
    private void modi() {
        try {
            pvo.getId_pedido();
            pvo.getFecha_pedido();
            pvo.getFk_id_cliente();
            pvo.getFk_id_producto_pedido();
            pvo.getDescripcion_pedido();
            pvo.getPrecio_pedido();
            pvo.getCantidad_pedido();
            pvo.getTotal_pedido();
            pvo.setFk_id_estadop(Integer.parseInt(vista.cbxEstadoPedido.getSelectedItem().toString()));
            pdao.actualizar(pvo);
            vista.cbxEstadoPedido.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, "Registro Modificado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para Modificar registro!");
        }
    }

    //metodo para cargar datos en la tabla de la bd
    private void datos() {
        int row;
        row = vista.tblPedido.getSelectedRow();
        pvo.setId_pedido(Integer.parseInt(vista.tblPedido.getValueAt(row, 0).toString()));
        pvo.setFecha_pedido(String.valueOf(vista.tblPedido.getValueAt(row, 1)));
        pvo.setFk_id_cliente(Integer.parseInt(vista.tblPedido.getValueAt(row, 2).toString()));
        pvo.setFk_id_producto_pedido(Integer.parseInt(vista.tblPedido.getValueAt(row, 3).toString()));
        pvo.setDescripcion_pedido(String.valueOf(vista.tblPedido.getValueAt(row, 4)));
        pvo.setPrecio_pedido(Double.parseDouble(vista.tblPedido.getValueAt(row, 5).toString()));
        pvo.setCantidad_pedido(Integer.parseInt(vista.tblPedido.getValueAt(row, 6).toString()));
        pvo.setTotal_pedido(Double.parseDouble(vista.tblPedido.getValueAt(row, 7).toString()));
        pvo.setFk_id_estadop(Integer.parseInt(vista.tblPedido.getValueAt(row, 8).toString()));

    }
    
    //los botones a ultilizar y que metodos utilizaran
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.btncaja) {
            vista.setVisible(true);
        }
        if (e.getSource() == vista.btnEliminar) {
            JOptionPane.showMessageDialog(null, "Hacer doble clic en el registro que desea eliminar");
        }
        if (e.getSource() == vista.btnModificar) {
            this.modi();
        }
    }
    
    //esto hace que al momento de dar un click o varios en las columnas y filas de las tablas hagan una funcion
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            this.datos();
        }
        if (e.getClickCount() == 2) {
            this.eliminar();
        }
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

    //me lee la funcion mostrar para poder saber que tabla es la que filtrara el contenido 
    @Override
    public void keyTyped(KeyEvent e) {
        this.mostrar();
//        m = new DefaultTableModel();
//        tr = new TableRowSorter(m);
//        vista.tblPedido.setRowSorter(tr);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
    
    //con esta funcion hace que filre una columna en especifico
    @Override
    public void keyReleased(KeyEvent e) {
        tr.setRowFilter(RowFilter.regexFilter("(?i)"+vista.txtFiltro.getText(), 3));
        
    }
    
    //esta funicon hace que al momento que se habra la vista me muestre el contenido de mi tabla 
    @Override
    public void windowOpened(WindowEvent e) {
        this.mostrar();
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
        this.mostrar();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
}
