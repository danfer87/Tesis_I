/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.com.sisapus.bean.loginBean;
import ec.com.sisapus.daoimpl.usuarioDaoImpl;
import javax.annotation.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ec.com.sisapus.dao.usuarioDao;
import ec.com.sisapus.modelo.Usuario;
import java.util.List;
import junit.framework.Assert;

/**
 *
 * @author kleber
 */
public class Autentificacion_Usuarios {
    
    public Autentificacion_Usuarios() {
    }
    @Resource  
    List<Usuario> listausuarios;  
  
    Usuario usuario;
    usuarioDao usuarioDao= new usuarioDaoImpl();
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        usuario=new Usuario();
      usuario.setSobrenombreUsu("kleper");
       usuario.setContraseniaUsu("nic0kl3p3r");
    }
    
    @After
    public void tearDown() {
        
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    

public void testautentificacion(){
      
try {

usuario = usuarioDao.buscarPorUsuario(usuario);
if(usuario !=null) {
Assert.assertTrue("Usuario encontrado ["+usuario.getSobrenombreUsu()+"] ["+usuario.getContraseniaUsu()+"]", true);

} else {
//("Usuario no Encontrado ["+sobre+"] ["+password+"]",false);
fail("Usuario no Encontrado ["+usuario.getSobrenombreUsu()+"] ["+usuario.getContraseniaUsu()+"]");
}
} catch (Exception e) {

fail("Usuario no encontrado");
} 
    
   } 
    
}