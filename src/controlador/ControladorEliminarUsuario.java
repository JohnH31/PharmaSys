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
import modelo.UsuarioDAO;
import modelo.UsuarioVO;
import vista.FrmEliminarUsuario;
import vista.FrmMenu;

/**
 *
 * @author John
 */
public class ControladorEliminarUsuario implements ActionListener, MouseListener{
    UsuarioVO uvo = new UsuarioVO();
    UsuarioDAO udao = new UsuarioDAO();
    FrmMenu menu = new FrmMenu();
    FrmEliminarUsuario vista = new FrmEliminarUsuario();

    public ControladorEliminarUsuario(UsuarioVO uvo,UsuarioDAO udao,FrmMenu menu,FrmEliminarUsuario vista) {
        this.uvo = uvo;
        this.udao = udao;
        this.menu = menu;
        this.vista = vista;
        
        vista.tblEliminarUsuario.addMouseListener(this);
        menu.jmiEliminar.addActionListener(this);
    }
    
    private void mostrar() {
        DefaultTableModel m = new DefaultTableModel();
        m.setColumnCount(0);
        m.addColumn("Id");
        m.addColumn("Usuario");
        m.addColumn("Contraseña");
        m.addColumn("Id tipo de usuario");
        for (UsuarioVO uvo : udao.consultarTabla()) {
            m.addRow(new Object[]{uvo.getId_usuario(),uvo.getUsuario(), uvo.getClave_usuario(), uvo.getFk_id_tipo_usuario()});
        }
        vista.tblEliminarUsuario.setModel(m);
    }

    private void eliminar() {

        int row = vista.tblEliminarUsuario.getSelectedRow();
        uvo.setId_usuario(Integer.parseInt(vista.tblEliminarUsuario.getValueAt(row, 0).toString()));
        int men = JOptionPane.showConfirmDialog(null, "Estas seguro que deceas eliminar el registro?", "pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (men == JOptionPane.YES_OPTION) {
            try {
                udao.eliminar(uvo);
                uvo.setId_usuario(row);
            } catch (Exception e) {
                System.out.println("Mensaje eliminar" + e.getMessage());
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == menu.jmiEliminar) {
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
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
