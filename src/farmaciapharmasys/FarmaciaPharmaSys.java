/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmaciapharmasys;

import controlador.ControladorCaja;
import controlador.ControladorEliminarProducto;
import controlador.ControladorEliminarUsuario;
import controlador.ControladorFactura;
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
import modelo.DetalleFacDAO;
import modelo.DetalleFacVO;
import modelo.FacturaDAO;
import modelo.FacturaVO;
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
import vista.FrmFactura;
import vista.FrmLogin;
import vista.FrmMenu;
import vista.FrmPedido;
import vista.FrmProductos;
import vista.FrmProveedor;
import vista.FrmVistaFYD;

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
        
        //Clases en las que se controla la union de la bd con la aplicacion de java
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
        FacturaDAO fdao = new FacturaDAO();
        FacturaVO fvo = new FacturaVO();
        DetalleFacDAO dfdao = new DetalleFacDAO();
        DetalleFacVO dfvo = new DetalleFacVO();
        FrmFactura vista = new FrmFactura();
        FrmPedido fpedi = new FrmPedido();
        FrmClientes fcle = new FrmClientes();
        FrmProveedor fpro = new FrmProveedor();
        FrmCompras fcp = new FrmCompras();
        FrmProductos frp = new FrmProductos();
        FrmLogin lo = new FrmLogin();
        FrmMenu me = new FrmMenu();
        FrmCaja caja = new FrmCaja();
        FrmVistaFYD ffyd = new FrmVistaFYD();
        FrmAgregarUsuario au = new FrmAgregarUsuario();
        FrmEliminarUsuario eu = new FrmEliminarUsuario();
        
        //Controladores para las vistas y las funciones, metodos con la aplicacion java y la bd        
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
        ControladorFactura cfac = new ControladorFactura(fdao, fvo, dfdao, dfvo, pedao, pevo, pdao, pvo, me, vista,ffyd);
        
        //levanar la primera vista de la aplicacion 
        lo.setVisible(true);
        lo.setLocationRelativeTo(null);
        
        //carga los datos de cada combobox
        ciu.cargarTipoUsuario(0);
        cmc.cargarProducto(0);
        cmped.cargarProducto(0);
        cmped.cargarCliente(0);
        cmped.cargarEstado(0);
        ccaja.cargarEstado(0);
        cfac.cargarCliente(0);
        cfac.cargarProducto(0);
        cep.cargarProveedores(0);
    }
    
}
