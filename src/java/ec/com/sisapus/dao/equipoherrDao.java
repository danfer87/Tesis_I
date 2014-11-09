/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.dao;

import ec.com.sisapus.modelo.Equipoherramienta;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public interface equipoherrDao {
    public Equipoherramienta buscarPorEquipoHerr(Equipoherramienta equipoherr);
    public List<Equipoherramienta> buscarTodosEquipHerr();
    public boolean crearEquipoHerr(Equipoherramienta equipoherr);
    public boolean actualizarEquipoHerr(Equipoherramienta equipoherr);
    public boolean eliminarEquipoHerr(Integer idEqHerr);
    //agregado
      public Equipoherramienta getByIdEquipo(Session session, Integer idequipo) throws Exception;
       public Equipoherramienta getUltimoRegistro(Session session) throws Exception;
}
