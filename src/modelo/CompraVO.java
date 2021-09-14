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
public class CompraVO {
    private int id_compra;
    private String fecha_compra;
    private int cantidad_producto;
    private int precio_producto;
    private int fk_id_producto;

    public CompraVO() {
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public int getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(int cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public int getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(int precio_producto) {
        this.precio_producto = precio_producto;
    }

    public int getFk_id_producto() {
        return fk_id_producto;
    }

    public void setFk_id_producto(int fk_id_producto) {
        this.fk_id_producto = fk_id_producto;
    }
    
}
