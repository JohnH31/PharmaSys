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
    private int fk_id_cliente;
    private int fk_id_producto_pedido;
    private String descripcion_pedido;
    private double precio_pedido;
    private int cantidad_pedido;
    private double total_pedido;
    private int fk_id_estadop;

    public PedidoVO() {
    }

    public int getFk_id_cliente() {
        return fk_id_cliente;
    }

    public void setFk_id_cliente(int fk_id_cliente) {
        this.fk_id_cliente = fk_id_cliente;
    }

    public double getPrecio_pedido() {
        return precio_pedido;
    }

    public void setPrecio_pedido(double precio_pedido) {
        this.precio_pedido = precio_pedido;
    }

    public int getCantidad_pedido() {
        return cantidad_pedido;
    }

    public void setCantidad_pedido(int cantidad_pedido) {
        this.cantidad_pedido = cantidad_pedido;
    }

    public double getTotal_pedido() {
        return total_pedido;
    }

    public void setTotal_pedido(double total_pedido) {
        this.total_pedido = total_pedido;
    }

    public int getFk_id_estadop() {
        return fk_id_estadop;
    }

    public void setFk_id_estadop(int fk_id_estadop) {
        this.fk_id_estadop = fk_id_estadop;
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

    public int getFk_id_producto_pedido() {
        return fk_id_producto_pedido;
    }

    public void setFk_id_producto_pedido(int fk_id_producto_pedido) {
        this.fk_id_producto_pedido = fk_id_producto_pedido;
    }
    
    
}
