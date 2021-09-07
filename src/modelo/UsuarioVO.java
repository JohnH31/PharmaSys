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
public class UsuarioVO {
   
    private int id_usuario;
    private String usuario;
    private String clave_usuario;
    private int fk_id_tipo_usuario;

    public UsuarioVO() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave_usuario() {
        return clave_usuario;
    }

    public void setClave_usuario(String clave_usuario) {
        this.clave_usuario = clave_usuario;
    }

    public int getFk_id_tipo_usuario() {
        return fk_id_tipo_usuario;
    }

    public void setFk_id_tipo_usuario(int fk_id_tipo_usuario) {
        this.fk_id_tipo_usuario = fk_id_tipo_usuario;
    }
    
    
    
}
