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
public interface rubroInterfaceDao {
    
    public boolean crearRubro(Session session, Rubro tRubro)throws Exception;
    public boolean modificarRubro(Session session, Rubro tRubro)throws Exception;
    public boolean eliminarRubro(Session session, Integer idRubro)throws Exception;
    public List<Rubro> listarTodosRubros(Session session)throws Exception;
    public Rubro getByCodigoRubro(Session session, String codigoRubro)throws Exception;
    
    //agregado
    public Rubro getByIdRubro(Session session, Integer idrubro) throws Exception;
    public Rubro getUltimoRegistro(Session session) throws Exception;
    
}
