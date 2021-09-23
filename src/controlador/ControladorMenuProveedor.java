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
import modelo.ProveedorDAO;
import modelo.ProveedorVO;
import vista.FrmMenu;
import vista.FrmProveedor;

/**
 *
 * @author John
 */
public class ControladorMenuProveedor implements ActionListener, MouseListener, KeyListener,WindowListener{

    ProveedorDAO pdao = new ProveedorDAO();
    ProveedorVO pvo = new ProveedorVO();
    FrmMenu menu = new FrmMenu();
    FrmProveedor vista = new FrmProveedor();
    DefaultTableModel m; //= new DefaultTableModel();
    TableRowSorter tr;

    public ControladorMenuProveedor(ProveedorDAO pdao, ProveedorVO pvo, FrmMenu menu, FrmProveedor vista) {
        this.pdao = pdao;
        this.pvo = pvo;
        this.menu = menu;
        this.vista = vista;

        vista.jtbnGuardarP.addActionListener(this);
        vista.jtbEliminarP.addActionListener(this);
        vista.tblProveedores.addMouseListener(this);
        vista.jtbnEditarP.addActionListener(this);
        menu.menuProveedores.addActionListener(this);
        vista.addWindowListener(this);
        vista.txtFiltro.addKeyListener(this);
    }

    private void insertar() {
        try {
            pvo.setNombre_proveedor(vista.txtNombre.getText());
            pvo.setTelefono_proveedor(Integer.parseInt(vista.txtTelefono.getText().toString()));
            pvo.setDireccion_proveedor(vista.txtDireccion.getText());
            pvo.setCorreo_proveedor(vista.txtCorreo.getText());
            pdao.insertar(pvo);
            vista.txtCorreo.setText("");
            vista.txtDireccion.setText("");
            vista.txtNombre.setText("");
            vista.txtTelefono.setText("");
            JOptionPane.showMessageDialog(null, "Registro Ingresado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para guardar registro!");
        }
    }

    private void mostrar() {
        //DefaultTableModel m = new DefaultTableModel();
        m = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==5) {
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
        m.addColumn("Telefono");
        m.addColumn("Direccion");
        m.addColumn("Correo");
        for (ProveedorVO pvo : pdao.consultarTabla()) {
            m.addRow(new Object[]{pvo.getId_proveedor(), pvo.getNombre_proveedor(), pvo.getTelefono_proveedor(), pvo.getDireccion_proveedor(), pvo.getCorreo_proveedor()});
        }
        vista.tblProveedores.setModel(m);
        vista.tblProveedores.setRowSorter(tr);
    }

    private void eliminar() {

        int row = vista.tblProveedores.getSelectedRow();
        pvo.setId_proveedor(Integer.parseInt(vista.tblProveedores.getValueAt(row, 0).toString()));
        int men = JOptionPane.showConfirmDialog(null, "Estas seguro que deceas eliminar el registro?", "pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (men == JOptionPane.YES_OPTION) {
            try {
                pdao.eliminar(pvo);
                pvo.setId_proveedor(row);
            } catch (Exception e) {
                System.out.println("Mensaje eliminar" + e.getMessage());
            }
        }
    }

    private void modi() {
        try {
            pvo.getId_proveedor();
            pvo.setNombre_proveedor(vista.txtNombre.getText());
            pvo.setTelefono_proveedor(Integer.parseInt(vista.txtTelefono.getText()));
            pvo.setDireccion_proveedor(vista.txtDireccion.getText());
            pvo.setCorreo_proveedor(vista.txtCorreo.getText());
            pdao.actualizar(pvo);
            vista.txtCorreo.setText("");
            vista.txtDireccion.setText("");
            vista.txtNombre.setText("");
            vista.txtTelefono.setText("");
            JOptionPane.showMessageDialog(null, "Registro Modificado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para Modificar registro!");
        }
    }

    private void datos() {
        int row;
        row = vista.tblProveedores.getSelectedRow();
        pvo.setId_proveedor(Integer.parseInt(vista.tblProveedores.getValueAt(row, 0).toString()));
        vista.txtNombre.setText(String.valueOf(vista.tblProveedores.getValueAt(row, 1)));
        vista.txtTelefono.setText(String.valueOf(vista.tblProveedores.getValueAt(row, 2)));
        vista.txtDireccion.setText(String.valueOf(vista.tblProveedores.getValueAt(row, 3)));
        vista.txtCorreo.setText(String.valueOf(vista.tblProveedores.getValueAt(row, 4)));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.menuProveedores) {
            vista.setVisible(true);
        }
        if (e.getSource() == vista.jtbnGuardarP) {
            this.insertar();
        }
        if (e.getSource() == vista.jtbEliminarP) {
            JOptionPane.showMessageDialog(null, "Hacer doble clic en el registro que desea eliminar");
        }
        if (e.getSource() == vista.jtbnEditarP) {
            this.modi();
   
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

    @Override
    public void keyTyped(KeyEvent e) {
        this.mostrar();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        tr.setRowFilter(RowFilter.regexFilter("(?i)"+vista.txtFiltro.getText(), 1));
    }

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
