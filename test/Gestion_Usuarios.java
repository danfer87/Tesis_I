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
public class Gestion_Usuarios {
    
    public Gestion_Usuarios() {
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
     usuario=new  Usuario ();
     perfil=new Perfil();
     usuario.setNombreUsu("jorge alberto");
      usuario.setApellidoUsu("perez diaz");
      usuario.setSobrenombreUsu("jorg");
        usuario.setContraseniaUsu("nic0kl3p3r");
        usuario.setCorreoUsu("alenicokleper@hotmail.com");
        usuario.setPerfil(perfil);
        usuario.setEstadoUsu(true);
        Date hoy = new Date();
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(hoy);
        usuario.setFechregUsu(java.sql.Date.valueOf(fecha));
     perfil.setCodigoPerf(1);
      
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
   

  
    @Test
     public void crearUsuario() {
   try {
    
usuarioDao.crearUsu(usuario);   
if(usuarioDao !=null) {
 
Assert.assertTrue("Usuario Creado Exitosamente", true);

} else {

fail("Error Usuario no creado" );
}

} catch (Exception e) {

fail("Usuario no creado");
} 
   }
 
    /**
     *
     */
    @Test   
 public void EliminarUsuario() {
   try {
    
usuarioDao.eliminarUsu(usuario.getCodigoUsu());   
if(usuarioDao !=null) {
 
Assert.assertTrue(" Registro Eliminado Exitosamente", true);

} else {

fail("Error Usuario no se puede eliminar" );
}

} catch (Exception e) {

fail("Usuario no eliminado");
} 
   }
       @Test   
 public void ActualizarUsuario() {
   try {
    
usuarioDao.actualizarUsu(usuario);   
if(usuarioDao !=null) {
 
Assert.assertTrue(" Registro Actualizado Exitosamente", true);

} else {

fail("Error Usuario no se pudo actualizar" );
}

} catch (Exception e) {

fail("Error Usuario no se pudo Actualizar");
} 
   }

   
   
   
}