/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.dao;

import ec.com.sisapus.modelo.Transporte;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public interface transporteDao {
  public Transporte buscarPorTransporte(Transporte transporte);
    public List<Transporte> buscarTodosTransportes();
    public boolean crearTransporte(Transporte transporte);
    public boolean actualizarTransporte(Transporte transporte);
    public boolean eliminarTransporte(Integer idTransporte);  
    
    //agregado
      public Transporte getByIdTransporte(Session session, Integer idtransp) throws Exception;
       public Transporte getUltimoRegistro(Session session) throws Exception; 
}
