/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.dao;

import ec.com.sisapus.modelo.Proyecto;
import ec.com.sisapus.modelo.Usuario;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public interface proyectoDao {
    
    public List<Proyecto> listarProyectos();
    public boolean crearProyecto(Proyecto proyecto);
    public boolean actualizarProyecto(Proyecto proyecto);
    public boolean eliminarProyecto(Integer idProyecto);
    public List<Proyecto> listarProyectosPorUsuario(String sobre);
    public boolean modificarProyecto(Session session, Proyecto tProyecto)throws Exception;
    public Proyecto getByCodigoProyecto(Session session, String codigoProyecto)throws Exception;
    
}
