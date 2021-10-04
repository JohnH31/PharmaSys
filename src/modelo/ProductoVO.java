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
public class ProductoVO {
    private int id_producto;
    private String nombre_producto;
    private String tipo_producto;
    private String descripcion_producto;
    private int fk_id_proveedor;
    private double precio_producto;

    public ProductoVO() {
    }

    public int getFk_id_proveedor() {
        return fk_id_proveedor;
    }

    public void setFk_id_proveedor(int fk_id_proveedor) {
        this.fk_id_proveedor = fk_id_proveedor;
    }

    public double getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(double precio_producto) {
        this.precio_producto = precio_producto;
    }
    

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }
    
    
}
