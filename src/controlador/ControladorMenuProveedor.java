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
import modelo.ProveedorDAO;
import modelo.ProveedorVO;
import vista.FrmMenu;
import vista.FrmProveedor;

/**
 *
 * @author John
 */
public class ControladorMenuProveedor implements ActionListener, MouseListener{
    
    ProveedorDAO pdao = new ProveedorDAO();
    ProveedorVO pvo = new ProveedorVO();
    FrmMenu menu = new FrmMenu();
    FrmProveedor vista = new FrmProveedor();

    public ControladorMenuProveedor(ProveedorDAO pdao,ProveedorVO pvo,FrmMenu menu,FrmProveedor vista) {
        this.pdao = pdao;
        this.pvo = pvo;
        this.menu = menu;
        this.vista = vista;
        
        vista.jtbnGuardarP.addActionListener(this);
        vista.jtbEliminarP.addActionListener(this);
        vista.tblProveedores.addMouseListener(this);
        menu.menuProveedores.addActionListener(this);
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
        DefaultTableModel m = new DefaultTableModel();
        m.setColumnCount(0);
        m.addColumn("Id");
        m.addColumn("Nombre");
        m.addColumn("Telefono");
        m.addColumn("Direccion");
        m.addColumn("Correo");
        for (ProveedorVO pvo : pdao.consultarTabla()) {
            m.addRow(new Object[]{pvo.getId_proveedor(),pvo.getNombre_proveedor(),pvo.getTelefono_proveedor(),pvo.getDireccion_proveedor(),pvo.getCorreo_proveedor()});
        }
        vista.tblProveedores.setModel(m);
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
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.menuProveedores) {
            vista.setVisible(true);
        }
        if (e.getSource() == vista.jtbnGuardarP) {
            this.insertar();
        }
        if (e.getSource() == vista.jtbEliminarP) {
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
