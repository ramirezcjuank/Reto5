/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Juan Carlos
 */
public class PelliculaModel {
    private int id;
    private String titulo;
    private String resumen;
    private int anio;
    private int id_director;
    
    
      public PelliculaModel() {
    }
    /**
     * Constructor para el manejo interno de la tabla Peliculas en Bd Jflix
     * @param id
     * @param titulo
     * @param resumen
     * @param anio
     * @param id_director 
     */

    public PelliculaModel(int id, String titulo, String resumen, int anio, int id_director) {
        this.id          = id;
        this.titulo      = titulo;
        this.resumen     = resumen;
        this.anio        = anio;
        this.id_director = id_director;
    }

  

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the resumen
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * @param resumen the resumen to set
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the id_director
     */
    public int getId_director() {
        return id_director;
    }

    /**
     * @param id_director the id_director to set
     */
    public void setId_director(int id_director) {
        this.id_director = id_director;
    }
        
    
}
