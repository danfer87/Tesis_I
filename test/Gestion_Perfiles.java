/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.com.sisapus.bean.perfilBean;
import ec.com.sisapus.bean.usuarioBean;
import ec.com.sisapus.dao.perfilDao;
import ec.com.sisapus.dao.usuarioDao;
import ec.com.sisapus.daoimpl.perfilDaoImpl;
import ec.com.sisapus.daoimpl.usuarioDaoImpl;
import ec.com.sisapus.modelo.Perfil;
import ec.com.sisapus.modelo.Usuario;
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
public class Gestion_Perfiles {
    
    public Gestion_Perfiles() {
    }
    
    
     @Resource  
   
    Perfil perfil;
    perfilDao perfilDao= new perfilDaoImpl();
    perfilBean perfbean=new perfilBean();
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        perfil=new Perfil();
        perfil.setDescripPerf("cliente1");
        perfil.setModAnalcosto(true);
        perfil.setModApu(true);
        perfil.setModPresup(true);
        perfil.setModProyecto(true);
        perfil.setModProyectos(true);
        perfil.setModReajprec(true);
        perfil.setModCronograma(true);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
    public void crearPerfil() {
   try {
    
perfilDao.crearPerfil(perfil);   
if(perfilDao !=null) {
 
Assert.assertTrue("Perfil Creado Exitosamente", true);

} else {

fail("Error Perfil no creado" );
}

} catch (Exception e) {

fail("Error Perfil no creado");
} 
   }
     
     
      /*@Test
    public void AsignarPermiso() {
   try {
    
perfbean.asignarPermisosPerfil();   
if(perfbean !=null) {
 
Assert.assertTrue("Perfil Asignado Exitosamente", true);

} else {

fail("Error Perfil no Asignado" );
}

} catch (Exception e) {

fail("Error Perfil no Asignado");
} 
   }*/
      
     
}