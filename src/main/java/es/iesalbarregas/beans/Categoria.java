package es.iesalbarregas.beans;


import java.io.Serializable;

/**
 *
 * @author Ana
 */
public class Categoria implements Serializable{
    
    private int idCategoria;
    private String nombre;
    private String imagen;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
}
