/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author John
 */
public class PedidoVO {
    private int id_pedido;
    private String fecha_pedido;
    private String descripcion_pedido;
    private boolean estado_pedido;
    private int fk_id_producto_pedido;

    public PedidoVO() {
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(String fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public String getDescripcion_pedido() {
        return descripcion_pedido;
    }

    public void setDescripcion_pedido(String descripcion_pedido) {
        this.descripcion_pedido = descripcion_pedido;
    }

    public boolean isEstado_pedido() {
        return estado_pedido;
    }

    public void setEstado_pedido(boolean estado_pedido) {
        this.estado_pedido = estado_pedido;
    }

    public int getFk_id_producto_pedido() {
        return fk_id_producto_pedido;
    }

    public void setFk_id_producto_pedido(int fk_id_producto_pedido) {
        this.fk_id_producto_pedido = fk_id_producto_pedido;
    }
    
    
}
