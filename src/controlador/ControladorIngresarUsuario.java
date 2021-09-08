/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.TipoUsuarioDAO;
import modelo.TipoUsuarioVO;
import modelo.UsuarioDAO;
import modelo.UsuarioVO;
import vista.FrmAgregarUsuario;
import vista.FrmMenu;

/**
 *
 * @author John
 */
public class ControladorIngresarUsuario implements ActionListener {

    UsuarioVO uvo = new UsuarioVO();
    UsuarioDAO udao = new UsuarioDAO();
    FrmMenu menu = new FrmMenu();
    FrmAgregarUsuario au = new FrmAgregarUsuario();

    public ControladorIngresarUsuario(UsuarioVO uvo, UsuarioDAO udao, FrmMenu menu, FrmAgregarUsuario au) {
        this.uvo = uvo;
        this.udao = udao;
        this.menu = menu;
        this.au = au;

        menu.jmiCrear.addActionListener(this);
        au.btnAgregarU.addActionListener(this);
        au.btnCancelarU.addActionListener(this);
    }

    private void insertarUsuario() {
        try {
            uvo.setUsuario(au.txtUsuarioU.getText());
            uvo.setClave_usuario(au.txtContraseñaU.getText());
            uvo.setFk_id_tipo_usuario(Integer.parseInt(au.cbxTipoUsuario.getSelectedItem().toString()));
            udao.insertar(uvo);
            au.txtUsuarioU.setText("");
            au.txtContraseñaU.setText("");
            au.cbxTipoUsuario.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, "Registro Ingresado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar Datos para guardar registro!");
        }
    }

    public void cargarTipoUsuario(int buscar) {
        TipoUsuarioDAO tudao = new TipoUsuarioDAO();
        int index = 1;
        au.cbxTipoUsuario.removeAllItems();
        au.cbxTipoUsuario.addItem("Seleccione Estado");
        for (TipoUsuarioVO tuvo : tudao.consultarTabla()) {
            au.cbxTipoUsuario.addItem(String.valueOf(tuvo.getId_tipo_usuario()));
            //vista.cbxLibroAutor.addItem(lvo.getNombre_libro());
            if (tuvo.getId_tipo_usuario() == buscar) {
                au.cbxTipoUsuario.setSelectedIndex(index);
            }
            index++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == au.btnAgregarU) {
            this.insertarUsuario();
        }
        if (e.getSource() == au.btnCancelarU) {
            au.dispose();
        }
        if (e.getSource() == menu.jmiCrear) {
            au.setVisible(true);
        }
    }
}
