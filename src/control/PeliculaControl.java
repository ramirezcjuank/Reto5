/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import acceso.PeliculaDAO;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.PelliculaModel;
import vista.Main;

/**
 *
 * @author Juan Carlos
 */
public class PeliculaControl implements ActionListener{
    
    PeliculaDAO  dao= new PeliculaDAO();
    PelliculaModel pel= new PelliculaModel(); 
    Main main=new Main();
    DefaultTableModel modelo=new DefaultTableModel();

    public PeliculaControl(Main m) {
        this.main=m;
        this.main.btnMostrar.addActionListener(this);
        this.main.btnAgregar.addActionListener(this);
        this.main.btnModificar.addActionListener(this);
        this.main.btnActualizar.addActionListener(this);
        this.main.btnEliminar.addActionListener(this);
        listar(main.TablaPelicula);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==main.btnMostrar){
            limpiarTabla();
            listar(main.TablaPelicula);
        }
        if (e.getSource()==main.btnAgregar){
            agregar();
            limpiarTabla();
            listar(main.TablaPelicula);
        }
        if (e.getSource()==main.btnModificar){
            int fila=main.TablaPelicula.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(main, "Debe Seleccionar Una fila..!!");
            } 
            else {
                int id = Integer.parseInt((String) main.TablaPelicula.getValueAt(fila, 0).toString());
                String titulo = (String) main.TablaPelicula.getValueAt(fila, 1);
                String resumen = (String) main.TablaPelicula.getValueAt(fila, 2);
                int anio = Integer.parseInt((String) main.TablaPelicula.getValueAt(fila, 3).toString());
                int idDir = Integer.parseInt((String) main.TablaPelicula.getValueAt(fila, 4).toString());
                main.txtID.setText("" + id);
                main.txtTitulo.setText(titulo);
                main.txtResumen.setText(resumen);
                main.txtAnio.setText(""+anio);
                main.txtIDirector.setText(""+idDir);  
            }
        }
        if (e.getSource()==main.btnActualizar){
            actualizar();
            limpiarTabla();
            listar(main.TablaPelicula);
        }
        if (e.getSource()==main.btnEliminar){
            eliminar();
        }
        
    }
    
    public void listar(JTable tabla){
        modelo =(DefaultTableModel)tabla.getModel();
        ArrayList<PelliculaModel>peliculas=dao.obtenerPeliculas();
        Object[]object=new Object[5];
        for (int i = 0; i < peliculas.size(); i++) {
            object[0]=peliculas.get(i).getId();
            object[1]=peliculas.get(i).getTitulo();
            object[2]=peliculas.get(i).getResumen();
            object[3]=peliculas.get(i).getAnio();
            object[4]=peliculas.get(i).getId_director();
            modelo.addRow(object);                       
        }        
    }
    
    public void agregar(){
        String titulo=main.txtTitulo.getText();
        String resumen=main.txtResumen.getText();
        int anio=Integer.parseInt(main.txtAnio.getText());
        int idirector=Integer.parseInt(main.txtIDirector.getText());
        
        pel.setTitulo(titulo);
        pel.setResumen(resumen);
        pel.setAnio(anio);
        pel.setId_director(idirector);
        dao.agregarPelicula(pel);
    
    }
    
    public void actualizar(){
        int id=Integer.parseInt(main.txtID.getText());
        String titulo=main.txtTitulo.getText();
        String resumen=main.txtResumen.getText();
        int anio=Integer.parseInt(main.txtAnio.getText());
        int idirector=Integer.parseInt(main.txtIDirector.getText());
        pel.setId(id);
        pel.setTitulo(titulo);
        pel.setResumen(resumen);
        pel.setAnio(anio);
        pel.setId_director(idirector);
        dao.actualizarPelicula(pel);
    
    }
    
    void limpiarTabla() {
        for (int i = 0; i < main.TablaPelicula.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    void eliminar(){
        int fila = main.TablaPelicula.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(main, "Debe Seleccionar una Fila...!!!");
        } else {
            int id = Integer.parseInt((String) main.TablaPelicula.getValueAt(fila, 0).toString());
            dao.borrarPelicula(id);
        }
    }
    
}
