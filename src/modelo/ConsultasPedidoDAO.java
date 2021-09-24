/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author John
 */
public interface ConsultasPedidoDAO {
    public void insertar(PedidoVO p);
    public void actualizar(PedidoVO p);
    public void eliminar(PedidoVO p);
    public ArrayList<PedidoVO> consultarTabla();
    public ArrayList<PedidoVO> consultarJoin();
}
