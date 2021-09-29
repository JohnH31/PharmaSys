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
import vista.FrmClientes;
import vista.FrmMenu;

/**
 *
 * @author John
 */
public class ControladorMenuClientes implements ActionListener, MouseListener, KeyListener,WindowListener {

    ClienteDAO cdao = new ClienteDAO();
    ClienteVO cvo = new ClienteVO();
    FrmMenu menu = new FrmMenu();
    FrmClientes vista = new FrmClientes();
    DefaultTableModel m; //= new DefaultTableModel(); metodo para el uso de la tabla
    TableRowSorter tr;//metodo para el filtro en la tabla

    public ControladorMenuClientes(ClienteDAO cdao,ClienteVO cvo,FrmMenu menu,FrmClientes vista) {
        this.cdao = cdao;
        this.cvo = cvo;
        this.menu = menu;
        this.vista = vista;
        
        vista.btnGuardar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.tblClientes.addMouseListener(this);
        vista.btnEditar.addActionListener(this);
        menu.MenusCliente.addActionListener(this);
        vista.addWindowListener(this);
        vista.txtFiltro.addKeyListener(this);
        menu.jmiClientesR.addActionListener(this);
    }
    //metodo para ingresar datos a la bd
    private void insertar() {
        try {
            cvo.setNombre_cliente(vista.txtNombre.getText());
            cvo.setApellido_cliente(vista.txtApellido.getText());
            cvo.setDireccion_cliente(vista.txtDireccion.getText());
            cdao.insertar(cvo);
            vista.txtNombre.setText("");
            vista.txtApellido.setText("");
            vista.txtDireccion.setText("");
            JOptionPane.showMessageDialog(null, "Registro Ingresado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para guardar registro!");
        }
    }

    //metodo para mostrar los datos de la bd en la tabla
    private void mostrar() {
        //DefaultTableModel m = new DefaultTableModel();
        m = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==4) {
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
        m.addColumn("Apellido");
        m.addColumn("Direccion");
        for (ClienteVO cvo : cdao.consultarTabla()) {
            m.addRow(new Object[]{cvo.getId_cliente(),cvo.getNombre_cliente(),cvo.getApellido_cliente(),cvo.getDireccion_cliente()});
        }
        vista.tblClientes.setModel(m);
        vista.tblClientes.setRowSorter(tr);
    }
    //metodo para eliminar datos en la tabla 
    private void eliminar() {

        int row = vista.tblClientes.getSelectedRow();
        cvo.setId_cliente(Integer.parseInt(vista.tblClientes.getValueAt(row, 0).toString()));
        int men = JOptionPane.showConfirmDialog(null, "Estas seguro que deceas eliminar el registro?", "pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (men == JOptionPane.YES_OPTION) {
            try {
                cdao.eliminar(cvo);
                cvo.setId_cliente(row);
            } catch (Exception e) {
                System.out.println("Mensaje eliminar" + e.getMessage());
            }
        }
    }
    //metodo para modificar datos en la bd
    private void modi() {      
          try {
              cvo.getId_cliente();
              cvo.setNombre_cliente(vista.txtNombre.getText());
              cvo.setApellido_cliente(vista.txtApellido.getText());
              cvo.setDireccion_cliente(vista.txtDireccion.getText());
              cdao.actualizar(cvo);
              vista.txtNombre.setText("");
              vista.txtApellido.setText("");
              vista.txtDireccion.setText("");
            JOptionPane.showMessageDialog(null, "Registro Modificado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para Modificar registro!");
        }
    }
    //metodo para cargar datos en la tabla de la bd
    private void datos() {
        int row;
        row = vista.tblClientes.getSelectedRow();
        cvo.setId_cliente(Integer.parseInt(vista.tblClientes.getValueAt(row, 0).toString()));
        vista.txtNombre.setText(String.valueOf(vista.tblClientes.getValueAt(row, 1)));
        vista.txtApellido.setText(String.valueOf(vista.tblClientes.getValueAt(row, 2)));
        vista.txtDireccion.setText(String.valueOf(vista.tblClientes.getValueAt(row, 3)));
        
    }
    //Reporte
    private void reporte() {
        try {
            cdao.reporte();
            cdao.jv.setDefaultCloseOperation(menu.DISPOSE_ON_CLOSE);
            cdao.jv.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Reporte No generado");
        }
    }
    
    //los botones a ultilizar y que metodos utilizaran
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.MenusCliente) {
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
        if (e.getSource() == menu.jmiClientesR) {
            this.reporte();
        }
    }
    //esto hace que al momento de dar un click o varios en las columnas y filas de las tablas hagan una funcion
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount()==1) {
            this.datos();
        }
        if (e.getClickCount()==2) {
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
