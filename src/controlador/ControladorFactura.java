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
import modelo.ClienteDAO;
import modelo.ClienteVO;
import modelo.DetalleFacDAO;
import modelo.DetalleFacVO;
import modelo.FacturaDAO;
import modelo.FacturaVO;
import modelo.PedidoDAO;
import modelo.PedidoVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import vista.FrmClientes;
import vista.FrmFactura;
import vista.FrmMenu;
import vista.FrmVistaFYD;

/**
 *
 * @author John
 */
public class ControladorFactura implements ActionListener, MouseListener, KeyListener, WindowListener {

    FacturaDAO fdao = new FacturaDAO();
    FacturaVO fvo = new FacturaVO();
    DetalleFacDAO dfdao = new DetalleFacDAO();
    DetalleFacVO dfvo = new DetalleFacVO();
    PedidoDAO pdao = new PedidoDAO();
    PedidoVO pvo = new PedidoVO();
    ProductoDAO prdao = new ProductoDAO();
    ProductoVO prvo = new ProductoVO();
    FrmMenu menu = new FrmMenu();
    FrmFactura vista = new FrmFactura();
    FrmVistaFYD vista2 = new FrmVistaFYD();
    DefaultTableModel m; //= new DefaultTableModel(); metodo para el uso de la tabla
    TableRowSorter tr;//metodo para el filtro en la tabla
    TableRowSorter tr1;

    public ControladorFactura(FacturaDAO fdao, FacturaVO fvo, DetalleFacDAO dfdao, DetalleFacVO dfvo, PedidoDAO pdao, PedidoVO pvo, ProductoDAO prdao, ProductoVO prvo, FrmMenu menu, FrmFactura vista, FrmVistaFYD vista2) {
        this.fdao = fdao;
        this.fvo = fvo;
        this.dfdao = dfdao;
        this.dfvo = dfvo;
        this.pdao = pdao;
        this.pvo = pvo;
        this.prdao = prdao;
        this.prvo = prvo;
        this.menu = menu;
        this.vista = vista;
        this.vista2 = vista2;

        menu.btnFactura.addActionListener(this);
        vista.btnEditar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.addWindowListener(this);
        vista2.addWindowListener(this);
        vista2.tblFacturas.addMouseListener(this);
        vista2.tblFacturasDetalle.addMouseListener(this);
        vista.txtFiltro.addKeyListener(this);
        vista.btntablasfyd.addActionListener(this);
        menu.jmiFacturaR.addActionListener(this);
    }
    //metodo para ingresar datos a la bd
    private void insertar() {
        try {
            fvo.setFecha_factura(vista.txtFecha.getText());
            fvo.setFk_id_cliente(Integer.parseInt(vista.cbxCliente.getSelectedItem().toString()));
            dfvo.setCantidad_producto(Integer.parseInt(vista.txtCantidad.getText()));
            double total = Integer.parseInt(vista.txtCantidad.getText()) * Double.parseDouble(vista.txtPresio.getText());
            dfvo.setTotal_factura(total);
            dfvo.setFk_id_producto(Integer.parseInt(vista.cbxIdProducto.getSelectedItem().toString()));
            fdao.insertar(fvo);
            dfdao.insertar(dfvo);
            vista.txtFecha.setText("");
            vista.cbxCliente.setSelectedIndex(0);
            vista.txtCantidad.setText("");
            vista.txtPresio.setText("");
            vista.txtTotal.setText("");
            vista.cbxIdProducto.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, "Registro Ingresado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para guardar registro!");
        }
    }
    
    //metodo para cargar los productos en el combobox
    public void cargarProducto(int buscar) {
        ProductoDAO pdao = new ProductoDAO();
        int index = 1;
        vista.cbxIdProducto.removeAllItems();
        vista.cbxIdProducto.addItem("Seleccione Producto");
        for (ProductoVO pvo : pdao.consultarTabla()) {
            vista.cbxIdProducto.addItem(String.valueOf(pvo.getId_producto()));
            //vista.cbxLibroAutor.addItem(lvo.getNombre_libro());
            if (pvo.getId_producto() == buscar) {
                vista.cbxIdProducto.setSelectedIndex(index);
            }
            index++;
        }
    }
    //metodo para cargar los clientes en el combobox
    public void cargarCliente(int buscar) {
        ClienteDAO cdao = new ClienteDAO();
        int index = 1;
        vista.cbxCliente.removeAllItems();
        vista.cbxCliente.addItem("Seleccione Producto");
        for (ClienteVO cvo : cdao.consultarTabla()) {
            vista.cbxCliente.addItem(String.valueOf(cvo.getId_cliente()));
            //vista.cbxLibroAutor.addItem(lvo.getNombre_libro());
            if (cvo.getId_cliente() == buscar) {
                vista.cbxCliente.setSelectedIndex(index);
            }
            index++;
        }
    }
    
    //metodos para mostrar el contenido de la bd a las tablas
    private void mostrar() {
        //DefaultTableModel m = new DefaultTableModel();
        m = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 4) {
                    return true;
                } else {
                    return false;
                }
            }

        };
        tr = new TableRowSorter(m);
        m.setColumnCount(0);
        m.addColumn("id Producto");
        m.addColumn("Producto");
        m.addColumn("Tipo");
        m.addColumn("Presio");
        for (ProductoVO prvo : prdao.consultarJoin()) {
            m.addRow(new Object[]{prvo.getId_producto(), prvo.getNombre_producto(), prvo.getTipo_producto(),prvo.getPrecio_producto()});
        }
        vista.tblFactura.setModel(m);
        vista.tblFactura.setRowSorter(tr);
    }

    private void mostrar1() {
        //DefaultTableModel me = new DefaultTableModel();
        m = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 5) {
                    return true;
                } else {
                    return false;
                }
            }

        };
        tr1 = new TableRowSorter(m);
        m.setColumnCount(0);
        m.addColumn("Fecha Pedido");
        m.addColumn("id cliente");
        m.addColumn("Descripcion");
        m.addColumn("Estado Pedido");
        m.addColumn("id Producto");
        for (PedidoVO pvo : pdao.consultarJoin()) {
            m.addRow(new Object[]{pvo.getFecha_pedido(),pvo.getFk_id_cliente(), pvo.getDescripcion_pedido(), pvo.getFk_id_estadop(), pvo.getFk_id_producto_pedido()});
        }
        vista.tblFactura1.setModel(m);
        vista.tblFactura1.setRowSorter(tr1);
    }

    private void mostrar2() {
        //DefaultTableModel m = new DefaultTableModel();
        m = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return true;
                } else {
                    return false;
                }
            }

        };
        m.setColumnCount(0);
        m.addColumn("id Factura");
        m.addColumn("Fecha");
        m.addColumn("id cliente");
        for (FacturaVO fvo : fdao.consultarTabla()) {
            m.addRow(new Object[]{fvo.getId_factura(), fvo.getFecha_factura(), fvo.getFk_id_cliente()});
        }
        vista2.tblFacturas.setModel(m);
    }

    private void mostrar3() {
        //DefaultTableModel m = new DefaultTableModel();
        m = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 4) {
                    return true;
                } else {
                    return false;
                }
            }

        };
        m.setColumnCount(0);
        m.addColumn("id detalle factura");
        m.addColumn("cantidad");
        m.addColumn("total");
        m.addColumn("id producto");
        for (DetalleFacVO dfvo : dfdao.consultarTabla()) {
            m.addRow(new Object[]{dfvo.getId_detalle_factura(), dfvo.getCantidad_producto(), dfvo.getTotal_factura(), dfvo.getFk_id_producto()});
        }
        vista2.tblFacturasDetalle.setModel(m);
    }
    //metodos para eliminar datos en la tabla de la bd 
    private void eliminar() {

        int row = vista2.tblFacturas.getSelectedRow();
        fvo.setId_factura(Integer.parseInt(vista2.tblFacturas.getValueAt(row, 0).toString()));
        int men = JOptionPane.showConfirmDialog(null, "Estas seguro que deceas eliminar el registro?", "pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (men == JOptionPane.YES_OPTION) {
            try {
                fdao.eliminar(fvo);
                fvo.setId_factura(row);
            } catch (Exception e) {
                System.out.println("Mensaje eliminar" + e.getMessage());
            }
        }
    }

    private void eliminar2() {

        int row = vista2.tblFacturasDetalle.getSelectedRow();
        dfvo.setId_detalle_factura(Integer.parseInt(vista2.tblFacturasDetalle.getValueAt(row, 0).toString()));
        int men = JOptionPane.showConfirmDialog(null, "Estas seguro que deceas eliminar el registro?", "pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (men == JOptionPane.YES_OPTION) {
            try {
                dfdao.eliminar(dfvo);
                dfvo.setId_detalle_factura(row);
            } catch (Exception e) {
                System.out.println("Mensaje eliminar" + e.getMessage());
            }
        }
    }
    //metodo para modificar datos en la bd
    private void modi() {
        try {
            fvo.setId_factura(Integer.parseInt(vista.txtIDFactura.getText()));
            fvo.setFecha_factura(vista.txtFecha.getText());
            fvo.setFk_id_cliente(Integer.parseInt(vista.cbxCliente.getSelectedItem().toString()));
            dfvo.setCantidad_producto(Integer.parseInt(vista.txtCantidad.getText()));
            double total = Integer.parseInt(vista.txtCantidad.getText()) * Double.parseDouble(vista.txtPresio.getText());
            dfvo.setTotal_factura(total);
            //dfvo.setTotal_factura(Integer.parseInt(vista.txtTotal.getText()));

            fdao.actualizar(fvo);
            dfdao.actualizar(dfvo);
            vista.txtFecha.setText("");
            vista.cbxCliente.setSelectedIndex(0);
            vista.txtCantidad.setText("");
            vista.txtPresio.setText("");
            vista.txtTotal.setText("");
            vista.cbxIdProducto.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, "Registro Modificado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para Modificar registro!");
        }
    }
    //metodo para cargar datos en la tabla de la bd
    private void datos() {
        try {
            int row;
            row = vista2.tblFacturas.getSelectedRow();
            vista.txtIDFactura.setText(String.valueOf(vista2.tblFacturas.getValueAt(row, 0).toString()));
            vista.txtFecha.setText(String.valueOf(vista2.tblFacturas.getValueAt(row, 1).toString()));
            fvo.setFk_id_cliente((int) vista2.tblFacturas.getValueAt(row, 2));
        } catch (Exception e) {
        }

    }

    private void datos1() {
        try {
            int row;
            row = vista2.tblFacturasDetalle.getSelectedRow();
            dfvo.setId_detalle_factura(Integer.parseInt(vista2.tblFacturasDetalle.getValueAt(row, 0).toString()));
            vista.txtCantidad.setText(String.valueOf(vista2.tblFacturasDetalle.getValueAt(row, 1).toString()));
            vista.txtTotal.setText(String.valueOf(vista2.tblFacturasDetalle.getValueAt(row, 2).toString()));
            dfvo.setFk_id_producto((int) vista2.tblFacturasDetalle.getValueAt(row, 3));
            vista.txtPresio.setText(String.valueOf(vista2.tblFacturasDetalle.getValueAt(row, 4).toString()));

        } catch (Exception e) {
        }
    }
    //Reporte
    private void reporte() {
        try {
            fdao.reporte();
            fdao.jv.setDefaultCloseOperation(menu.DISPOSE_ON_CLOSE);
            fdao.jv.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Reporte No generado");
        }
    }
    //los botones a ultilizar y que metodos utilizaran
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.btnFactura) {
            vista.setVisible(true);
        }
        if (e.getSource() == vista.btnGuardar) {
            this.insertar();
        }
        if (e.getSource() == vista.btnEliminar) {
            JOptionPane.showMessageDialog(null, "Hacer doble clic en el registro que desea eliminar");
        }
        if (e.getSource() == vista.btnEditar) {
            this.modi();

        }
        if (e.getSource() == vista.btntablasfyd) {
            vista2.setVisible(true);
        }
        if (e.getSource() == menu.jmiFacturaR) {
            this.reporte();
        }
    }
    //esto hace que al momento de dar un click o varios en las columnas y filas de las tablas hagan una funcion
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            this.datos();
            this.datos1();
        }
        if (e.getClickCount() == 2) {
            this.eliminar();
            this.eliminar2();
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
        this.mostrar1();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
    //con esta funcion hace que filre una columna en especifico
    @Override
    public void keyReleased(KeyEvent e) {
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + vista.txtFiltro.getText(), 0));
        tr1.setRowFilter(RowFilter.regexFilter("(?i)" + vista.txtFiltro.getText(), 3));
    }
    //esta funicon hace que al momento que se habra la vista me muestre el contenido de mi tabla 
    @Override
    public void windowOpened(WindowEvent e) {
        this.mostrar();
        this.mostrar1();
        this.mostrar2();
        this.mostrar3();
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
        this.mostrar1();
        this.mostrar2();
        this.mostrar3();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
