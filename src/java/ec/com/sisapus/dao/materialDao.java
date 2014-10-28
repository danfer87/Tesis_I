/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.dao;

import ec.com.sisapus.modelo.Material;
import java.util.List;

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
    
    
}
