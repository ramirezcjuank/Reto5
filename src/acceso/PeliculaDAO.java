/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.PelliculaModel;
import utils.ConnectionDB;

/**
 *
 * @author Juan Carlos
 */
public class PeliculaDAO {
  
    private Connection conn =null;
    
    public ArrayList<PelliculaModel> obtenerPeliculas(){
        ArrayList<PelliculaModel> peliculas = new ArrayList();
        try {
            if(conn == null)
                conn = ConnectionDB.getConnection();
            
            String sql = "SELECT pel_id, pel_titulo , pel_resumen, pel_anio, pel_id_director FROM peliculas;";
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery(sql);
            
            while (result.next()) {
                PelliculaModel pelicula = new PelliculaModel(result.getInt(1),result.getString(2), result.getString(3), result.getInt(4), result.getInt(5));
                peliculas.add( pelicula );
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        return peliculas;
    
    }
    
    public ArrayList<PelliculaModel> obtenerPelicula(String titulo){
        ArrayList<PelliculaModel> peliculas = new ArrayList();
        try {
            if(conn == null)
                conn = ConnectionDB.getConnection();
            
            String sql = "SELECT pel_id, pel_titulo , pel_resumen, pel_anio, pel_id_director FROM peliculas WHERE pel_titulo=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(2, titulo);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                PelliculaModel pelicula = new PelliculaModel(result.getInt(1),result.getString(2), result.getString(3), result.getInt(4), result.getInt(5));
                peliculas.add( pelicula );
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        return peliculas;
    
    }
    
    public void agregarPelicula(PelliculaModel pelicula){
        try{
             if(conn == null)
                conn = ConnectionDB.getConnection();
            
            String sql ="INSERT INTO Peliculas(pel_titulo,pel_resumen,pel_anio,pel_id_director)"
            + "VALUES(?,?,?,?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, pelicula.getTitulo());
            statement.setString(2, pelicula.getResumen());
            statement.setInt(3, pelicula.getAnio());
            statement.setInt(4, pelicula.getId_director());
            
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0) 
                JOptionPane.showMessageDialog(null, "El registro fue agregado exitosamente !");
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        
    }
    
    public void actualizarPelicula(PelliculaModel pelicula){
        try {
            if(conn == null)
                conn = ConnectionDB.getConnection();
            
            String sql = "UPDATE peliculas SET pel_titulo=?,pel_resumen=?,pel_anio=?,pel_id_director=? WHERE pel_id=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, pelicula.getTitulo());
            statement.setString(2, pelicula.getResumen());
            statement.setInt(3, pelicula.getAnio());
            statement.setInt(4, pelicula.getId_director());
            statement.setInt(5, pelicula.getId());           
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) 
                JOptionPane.showMessageDialog(null, "El registro fue actualizado exitosamente !");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
    }
    
    public void borrarPelicula(int id){
        try {
            if(conn == null)
                conn = ConnectionDB.getConnection();
            
            String sql = "DELETE FROM peliculas WHERE pel_id=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "El registro fue borrado exitosamente !");
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : "
                    + ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        
    }
    
}
