/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.dao;

import ec.com.sisapus.modelo.Proyecto;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public interface proyectoInterface {
    
    ///////// Metodos de Prueba //////////
    public Proyecto proyetoPorId (Session sesion, Integer idproyecto);
    public List<Proyecto> encontrarTodoslosProyectos();
    public List<Proyecto> encontrarProyectos(Integer id);
    public List<Proyecto> encontrarProyectos(Session sesion,Integer id);
    //public List<Proyecto> listarProyectosxUsuario(Integer idusu, Integer idproy);
    
}
