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
public class TipoUsuarioVO {
    private int id_tipo_usuario;
    private String nombre_tipo_usuario;
    private String descripcion_tipo_usuario;

    public TipoUsuarioVO() {
    }

    public int getId_tipo_usuario() {
        return id_tipo_usuario;
    }

    public void setId_tipo_usuario(int id_tipo_usuario) {
        this.id_tipo_usuario = id_tipo_usuario;
    }

    public String getNombre_tipo_usuario() {
        return nombre_tipo_usuario;
    }

    public void setNombre_tipo_usuario(String nombre_tipo_usuario) {
        this.nombre_tipo_usuario = nombre_tipo_usuario;
    }

    public String getDescripcion_tipo_usuario() {
        return descripcion_tipo_usuario;
    }

    public void setDescripcion_tipo_usuario(String descripcion_tipo_usuario) {
        this.descripcion_tipo_usuario = descripcion_tipo_usuario;
    }
    
    
    
}
