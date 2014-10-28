package ec.com.sisapus.dao;

import ec.com.sisapus.modelo.Usuario;
import java.util.List;

/**
 *
 * @author Edison
 */
public interface usuarioDao {
///Metodo Validacion Usuario
    public Usuario buscarPorUsuario(Usuario usuario);
///MetodosCrud Usuario
    public List<Usuario> buscarTodosUsu();
    public boolean crearUsu(Usuario usuario);
    public boolean actualizarUsu(Usuario usuario);
    public boolean eliminarUsu(Integer idUs);
    public boolean regisUsu(Usuario usuario);
    //public boolean buscarUsuario();
    
}
