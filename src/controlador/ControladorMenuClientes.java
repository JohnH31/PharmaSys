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
import modelo.ClienteDAO;
import modelo.ClienteVO;
import vista.FrmClientes;
import vista.FrmMenu;

/**
 *
 * @author John
 */
public class ControladorMenuClientes implements ActionListener, MouseListener {

    ClienteDAO cdao = new ClienteDAO();
    ClienteVO cvo = new ClienteVO();
    FrmMenu menu = new FrmMenu();
    FrmClientes vista = new FrmClientes();

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
    }
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

    
    private void mostrar() {
        DefaultTableModel m = new DefaultTableModel();
        m.setColumnCount(0);
        m.addColumn("Id");
        m.addColumn("Nombre");
        m.addColumn("Apellido");
        m.addColumn("Direccion");
        for (ClienteVO cvo : cdao.consultarTabla()) {
            m.addRow(new Object[]{cvo.getId_cliente(),cvo.getNombre_cliente(),cvo.getApellido_cliente(),cvo.getDireccion_cliente()});
        }
        vista.tblClientes.setModel(m);
    }

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
    
    private void datos() {
        int row;
        row = vista.tblClientes.getSelectedRow();
        cvo.setId_cliente(Integer.parseInt(vista.tblClientes.getValueAt(row, 0).toString()));
        vista.txtNombre.setText(String.valueOf(vista.tblClientes.getValueAt(row, 1)));
        vista.txtApellido.setText(String.valueOf(vista.tblClientes.getValueAt(row, 2)));
        vista.txtDireccion.setText(String.valueOf(vista.tblClientes.getValueAt(row, 3)));
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.MenusCliente) {
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
        if (e.getClickCount()==1) {
            this.datos();
        }
        if (e.getClickCount()==2) {
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
