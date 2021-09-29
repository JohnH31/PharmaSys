/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.UsuarioDAO;
import modelo.UsuarioVO;
import vista.FrmLogin;
import vista.FrmMenu;

/**
 *
 * @author John
 */
public class ControladorLogin implements ActionListener {

    UsuarioVO uvo = new UsuarioVO();
    UsuarioDAO udao = new UsuarioDAO();
    FrmLogin vista = new FrmLogin();
    FrmMenu menu = new FrmMenu();

    public ControladorLogin(UsuarioVO uvo, UsuarioDAO udao, FrmLogin vista, FrmMenu menu) {
        this.uvo = uvo;
        this.udao = udao;
        this.vista = vista;
        this.menu = menu;

        vista.btnIngresar.addActionListener(this);
        vista.btnCancelar.addActionListener(this);
    }
    //metodo para autenticar el usuario y la contraseña (Validaciones)
    public void verificar() {
        try {
            //System.out.println(uvo.getId_tipo_usuario_fk());
            char p[] = vista.txtContraseña.getPassword();
            String contra = new String(p);
            uvo.setClave_usuario(contra);
            //uvo.setContraseña(contra);
            uvo.setUsuario(vista.txtUsuario.getText());
            udao.validacion(uvo);
            if (vista.txtUsuario.getText().isEmpty() || contra.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe de ingresar un usuario y una contraseña");
            } else {
                //uvo.getUsuario().equals(vista.txtUsuario.getText());
                //System.out.println(list);
                //System.out.println("+-+"+list.get(1));
                //int d = uvo.getId_tipo_usuario_fk();
                switch (udao.dato) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
                        vista.dispose();
                        menu.setVisible(true);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
                        vista.dispose();
                        menu.setVisible(true);
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
                        vista.dispose();
                        menu.setVisible(true);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }
    //los botones a ultilizar y que metodos utilizaran
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnIngresar) {
            //this.comprobar();
            this.verificar();
        }
        if (e.getSource() == vista.btnCancelar) {
            vista.dispose();
        }
    }

}
