/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.dao;

import ec.com.sisapus.modelo.Manoobra;
import java.util.List;

/**
 *
 * @author Edison
 */
public interface manoobraDao {
    public Manoobra buscarPorManoObra(Manoobra manoObra);
    public List<Manoobra> buscarTodosManoObra();
    public boolean crearManoObra(Manoobra manoObra);
    public boolean actualizarManoObra(Manoobra manoObra);
    public boolean eliminarManoObra(Integer idManoObra);
}
