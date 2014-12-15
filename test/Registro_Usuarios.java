/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.com.sisapus.bean.usuarioBean;
import ec.com.sisapus.dao.usuarioDao;
import ec.com.sisapus.daoimpl.usuarioDaoImpl;
import ec.com.sisapus.modelo.Perfil;
import ec.com.sisapus.modelo.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kleber
 */
public class Registro_Usuarios {
    
    public Registro_Usuarios() {
    }
     @Resource  
    List<Usuario> listausuarios;  
    Usuario usuario;
    Perfil perfil;
    usuarioDao usuarioDao= new usuarioDaoImpl();
    usuarioBean usuariobean=new usuarioBean();
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
public void TestRegistrarUsuario() {
         
try {
    
usuarioDao.registrarUsuario("kleber","Mendoza","jueee","nic0kl3p3r","klepermixmaster@gmail.com");   
if(usuarioDao !=null) {
 
Assert.assertTrue("Usuario Registrado Exitosamente", true);

} else {

fail("Error Usuario no se registro" );
}

} catch (Exception e) {

fail("Usuario no registrado");
} 
   
     }
     
  @Test
 public void envio_Correo_registro()
         {
    try {
             usuariobean.enviar_correo("klepermixmaster@gmail.com");
if((usuariobean!=null))  
     {
        Assert.assertTrue("Envio de mail Exitosamente", true); 
     }
 
else {
//("Usuario no Encontrado ["+sobre+"] ["+password+"]",false);
fail("No se puede enviar el correo electronico" );
} 
         
   } catch (Exception e) {

fail("Usuario no registrado");
}   
   } 
}