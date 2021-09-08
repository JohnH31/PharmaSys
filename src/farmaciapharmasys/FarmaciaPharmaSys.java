/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmaciapharmasys;

import controlador.ControladorEliminarUsuario;
import controlador.ControladorIngresarUsuario;
import controlador.ControladorLogin;
import modelo.UsuarioDAO;
import modelo.UsuarioVO;
import vista.FrmAgregarUsuario;
import vista.FrmEliminarUsuario;
import vista.FrmLogin;
import vista.FrmMenu;

/**
 *
 * @author John
 */
public class FarmaciaPharmaSys {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UsuarioVO uvo = new UsuarioVO();
        UsuarioDAO udao = new UsuarioDAO();
        FrmLogin lo = new FrmLogin();
        FrmMenu me = new FrmMenu();
        FrmAgregarUsuario au = new FrmAgregarUsuario();
        FrmEliminarUsuario eu = new FrmEliminarUsuario();
        
        ControladorLogin clog = new ControladorLogin(uvo,udao,lo,me);
        ControladorIngresarUsuario ciu = new ControladorIngresarUsuario(uvo, udao, me, au);
        ControladorEliminarUsuario ceu = new ControladorEliminarUsuario(uvo, udao, me, eu);
        
        lo.setVisible(true);
        lo.setLocationRelativeTo(null);
        
        ciu.cargarTipoUsuario(0);
    }
    
}
