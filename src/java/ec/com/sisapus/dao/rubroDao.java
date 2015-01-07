/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.dao;

import ec.com.sisapus.modelo.Rubro;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public interface rubroDao {

    public Rubro buscarPorRubro(Rubro rubro);

    public List<Rubro> buscarTodosRubros();

    public boolean crearRubro(Rubro rubro);

    public boolean actualizarRubro(Rubro rubro);

    public boolean eliminarRubro(Integer idRubro);

    public List<Rubro> BuscarRubro();
   
    //agregado
    public Rubro getByIdRubro(Session session, Integer idrubro) throws Exception;

    public Rubro getUltimoRegistroRubro(Session session) throws Exception;
}
