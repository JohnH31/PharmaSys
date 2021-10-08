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
import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.ProveedorDAO;
import modelo.ProveedorVO;
import modelo.UsuarioVO;
import vista.FrmMenu;
import vista.FrmProductos;

/**
 *
 * @author John
 */
public class ControladorEliminarProducto implements ActionListener, MouseListener, KeyListener, WindowListener {

    ProductoDAO pdao = new ProductoDAO();
    ProductoVO pvo = new ProductoVO();
    FrmMenu menu = new FrmMenu();
    FrmProductos vista = new FrmProductos();
    DefaultTableModel m; //= new DefaultTableModel(); metodo para el uso de la tabla
    TableRowSorter tr;//metodo para el filtro en la tabla

    public ControladorEliminarProducto(ProductoDAO pdao, ProductoVO pvo, FrmProductos vista) {
        this.pdao = pdao;
        this.pvo = pvo;
        this.vista = vista;

        vista.jbtnProductoD.addActionListener(this);
        vista.jbtnProductoE.addActionListener(this);
        vista.tblProducto.addMouseListener(this);
        vista.addWindowListener(this);
        vista.txtFiltro.addKeyListener(this);
    }
    
    //metodo para mostrar los datos de la bd a la tabla
    private void mostrar() {
        //DefaultTableModel m = new DefaultTableModel();
        m = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==6) {
                    return true;
                }else{
                    return false;
                }
            }
            
        };
        tr = new TableRowSorter(m);
        m.setColumnCount(0);
        m.addColumn("Id");
        m.addColumn("Nombre");
        m.addColumn("Tipo");
        m.addColumn("Descripcion");
        m.addColumn("idProveedor");
        m.addColumn("Presio");
        for (ProductoVO pvo : pdao.consultarTabla()) {
            m.addRow(new Object[]{pvo.getId_producto(), pvo.getNombre_producto(), pvo.getTipo_producto(), pvo.getDescripcion_producto(),pvo.getFk_id_proveedor(),pvo.getPrecio_producto()});
        }
        vista.tblProducto.setModel(m);
        vista.tblProducto.setRowSorter(tr);
    }
    //metodo para eliminar datos en la tabla
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
    //metodo para modificar datos en la bd
    private void modi() {
        try {
            pvo.getId_producto();
            pvo.setNombre_producto(vista.jtxfNombrePt.getText());
            pvo.setTipo_producto(vista.jtxfTipoPt.getText());
            pvo.setDescripcion_producto(vista.jtxaDescripcionPt.getText());
            pvo.setFk_id_proveedor(Integer.parseInt(vista.cbxIdProveedores.getSelectedItem().toString()));
            pvo.setPrecio_producto(Double.parseDouble(vista.txtPresio.getText()));
            pdao.actualizar(pvo);
            vista.jtxfNombrePt.setText("");
            vista.jtxfTipoPt.setText("");
            vista.jtxaDescripcionPt.setText("");
            vista.cbxIdProveedores.setSelectedIndex(0);
            vista.txtPresio.setText("");
            JOptionPane.showMessageDialog(null, "Registro Modificado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para Modificar registro!");
        }
    }
    //metodo para cargar datos en la tabla de la bd
    private void datos() {
        int row;
        row = vista.tblProducto.getSelectedRow();
        pvo.setId_producto(Integer.parseInt(vista.tblProducto.getValueAt(row, 0).toString()));
        vista.jtxfNombrePt.setText(String.valueOf(vista.tblProducto.getValueAt(row, 1)));
        vista.jtxfTipoPt.setText(String.valueOf(vista.tblProducto.getValueAt(row, 2)));
        vista.jtxaDescripcionPt.setText(String.valueOf(vista.tblProducto.getValueAt(row, 3)));
        pvo.setFk_id_proveedor((int) vista.tblProducto.getValueAt(row, 4));
        vista.txtPresio.setText(String.valueOf(vista.tblProducto.getValueAt(row, 5)));

    }
    //metodo para cargar los Proveedores en el combobox
    public void cargarProveedores(int buscar) {
        ProveedorDAO pdao = new ProveedorDAO();
        int index = 1;
        vista.cbxIdProveedores.removeAllItems();
        vista.cbxIdProveedores.addItem("Seleccione Producto");
        for (ProveedorVO pvo : pdao.consultarTabla()) {
            vista.cbxIdProveedores.addItem(String.valueOf(pvo.getId_proveedor()));
            //vista.cbxLibroAutor.addItem(lvo.getNombre_libro());
            if (pvo.getId_proveedor() == buscar) {
                vista.cbxIdProveedores.setSelectedIndex(index);
            }
            index++;
        }
    }
    //los botones a ultilizar y que metodos utilizaran
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jbtnProductoD) {
            JOptionPane.showMessageDialog(null, "Hacer doble clic en el registro que desea eliminar");
        }
        if (e.getSource() == vista.jbtnProductoE) {
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
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
    //con esta funcion hace que filre una columna en especifico
    @Override
    public void keyReleased(KeyEvent e) {
    tr.setRowFilter(RowFilter.regexFilter("(?i)"+vista.txtFiltro.getText(), 1));
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
