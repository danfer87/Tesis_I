package ec.com.sisapus.dao;

import ec.com.sisapus.modelo.Perfil;
import java.util.List;

/**
 *
 * @author Edison
 */
public interface perfilDao {
    public boolean crearPerfil(Perfil perfil);
    public boolean eliminarPerfil(Integer idPerf);
    public boolean actualizarPerfil(Perfil perfil);
    public void actualizarPermisoPerfil(Perfil perfil);
    public Perfil buscarPerfilPorId(Integer id);
    public List<Perfil> buscarTodosPerfiles();
    ///Listar Perfiles
    public List<Perfil> selectItems(); 
}
