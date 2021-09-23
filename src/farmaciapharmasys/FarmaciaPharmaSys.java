/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmaciapharmasys;

import controlador.ControladorCaja;
import controlador.ControladorEliminarProducto;
import controlador.ControladorEliminarUsuario;
import controlador.ControladorIngresarProducto;
import controlador.ControladorIngresarUsuario;
import controlador.ControladorLogin;
import controlador.ControladorMenuClientes;
import controlador.ControladorMenuCompras;
import controlador.ControladorMenuPedido;
import controlador.ControladorMenuProveedor;
import modelo.ClienteDAO;
import modelo.ClienteVO;
import modelo.CompraDAO;
import modelo.CompraVO;
import modelo.PedidoDAO;
import modelo.PedidoVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.ProveedorDAO;
import modelo.ProveedorVO;
import modelo.UsuarioDAO;
import modelo.UsuarioVO;
import vista.FrmAgregarUsuario;
import vista.FrmCaja;
import vista.FrmClientes;
import vista.FrmCompras;
import vista.FrmEliminarUsuario;
import vista.FrmLogin;
import vista.FrmMenu;
import vista.FrmPedido;
import vista.FrmProductos;
import vista.FrmProveedor;

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
        ProveedorDAO prdao = new ProveedorDAO();
        ProveedorVO prvo = new ProveedorVO();
        ClienteDAO cldao = new ClienteDAO();
        ClienteVO clvo = new ClienteVO();
        PedidoDAO pedao = new PedidoDAO();
        PedidoVO pevo = new PedidoVO();
        FrmPedido fpedi = new FrmPedido();
        FrmClientes fcle = new FrmClientes();
        FrmProveedor fpro = new FrmProveedor();
        FrmCompras fcp = new FrmCompras();
        FrmProductos frp = new FrmProductos();
        FrmLogin lo = new FrmLogin();
        FrmMenu me = new FrmMenu();
        FrmCaja caja = new FrmCaja();
        FrmAgregarUsuario au = new FrmAgregarUsuario();
        FrmEliminarUsuario eu = new FrmEliminarUsuario();
        
        ControladorLogin clog = new ControladorLogin(uvo,udao,lo,me);
        ControladorIngresarUsuario ciu = new ControladorIngresarUsuario(uvo, udao, me, au);
        ControladorEliminarUsuario ceu = new ControladorEliminarUsuario(uvo, udao, me, eu);
        ControladorIngresarProducto cip = new ControladorIngresarProducto(pdao, pvo, me, frp);
        ControladorEliminarProducto cep = new ControladorEliminarProducto(pdao, pvo, frp);
        ControladorMenuCompras cmc = new ControladorMenuCompras(cdao, cvo, me, fcp);
        ControladorMenuProveedor cmpp = new ControladorMenuProveedor(prdao, prvo, me, fpro);
        ControladorMenuClientes cmcl = new ControladorMenuClientes(cldao, clvo, me, fcle);
        ControladorMenuPedido cmped = new ControladorMenuPedido(pedao, pevo, me, fpedi);
        ControladorCaja ccaja = new ControladorCaja(pedao, pevo, me, caja);
        
        me.setVisible(true);
        me.setLocationRelativeTo(null);
        
        ciu.cargarTipoUsuario(0);
        cmc.cargarProducto(0);
        cmped.cargarProducto(0);
        ccaja.cargarProducto();
    }
    
}
