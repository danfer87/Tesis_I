/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.dao;

import ec.com.sisapus.modelo.Material;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public interface materialDao {

    public Material buscarPorMaterial(Material material);
    public List<Material> buscarTodosMater();
    public boolean crearMater(Material material);
    public boolean actualizarMater(Material material);
    public boolean eliminarMater(Integer idMater);
    
      //agregado
      public Material getByIdMaterial(Session session, Integer idmaterial) throws Exception;
       public Material getUltimoRegistro(Session session) throws Exception; 
}
