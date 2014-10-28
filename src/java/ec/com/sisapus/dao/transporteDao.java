/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.dao;

import ec.com.sisapus.modelo.Transporte;
import java.util.List;

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
}
