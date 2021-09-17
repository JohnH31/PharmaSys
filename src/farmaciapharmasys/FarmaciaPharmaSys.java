/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmaciapharmasys;

import controlador.ControladorEliminarProducto;
import controlador.ControladorEliminarUsuario;
import controlador.ControladorIngresarProducto;
import controlador.ControladorIngresarUsuario;
import controlador.ControladorLogin;
import controlador.ControladorMenuCompras;
import modelo.CompraDAO;
import modelo.CompraVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.UsuarioDAO;
import modelo.UsuarioVO;
import vista.FrmAgregarUsuario;
import vista.FrmCompras;
import vista.FrmEliminarUsuario;
import vista.FrmLogin;
import vista.FrmMenu;
import vista.FrmProductos;
import vista.jifrmProductos;

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
        ProductoVO pvo = new ProductoVO();
        ProductoDAO pdao = new ProductoDAO();
        CompraDAO cdao = new CompraDAO();
        CompraVO cvo = new CompraVO();
        FrmCompras fcp = new FrmCompras();
        FrmProductos frp = new FrmProductos();
        FrmLogin lo = new FrmLogin();
        FrmMenu me = new FrmMenu();
        FrmAgregarUsuario au = new FrmAgregarUsuario();
        FrmEliminarUsuario eu = new FrmEliminarUsuario();
        
        ControladorLogin clog = new ControladorLogin(uvo,udao,lo,me);
        ControladorIngresarUsuario ciu = new ControladorIngresarUsuario(uvo, udao, me, au);
        ControladorEliminarUsuario ceu = new ControladorEliminarUsuario(uvo, udao, me, eu);
        ControladorIngresarProducto cip = new ControladorIngresarProducto(pdao, pvo, me, frp);
        ControladorEliminarProducto cep = new ControladorEliminarProducto(pdao, pvo, frp);
        ControladorMenuCompras cmc = new ControladorMenuCompras(cdao, cvo, me, fcp);
        
        me.setVisible(true);
        me.setLocationRelativeTo(null);
        
        ciu.cargarTipoUsuario(0);
        cmc.cargarProducto(0);
    }
    
}
