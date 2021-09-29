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
public class DetalleFacVO {
    private int id_detalle_factura;
    private int cantidad_producto;
    private double total_factura;
    private int fk_id_producto;
    private double presio_factura;

    public DetalleFacVO() {
    }

    public double getPresio_factura() {
        return presio_factura;
    }

    public void setPresio_factura(double presio_factura) {
        this.presio_factura = presio_factura;
    }

    public int getId_detalle_factura() {
        return id_detalle_factura;
    }

    public void setId_detalle_factura(int id_detalle_factura) {
        this.id_detalle_factura = id_detalle_factura;
    }

    public int getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(int cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public double getTotal_factura() {
        return total_factura;
    }

    public void setTotal_factura(double total_factura) {
        this.total_factura = total_factura;
    }

    public int getFk_id_producto() {
        return fk_id_producto;
    }

    public void setFk_id_producto(int fk_id_producto) {
        this.fk_id_producto = fk_id_producto;
    }
    
    
}
