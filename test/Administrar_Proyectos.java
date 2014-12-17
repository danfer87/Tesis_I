/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.com.sisapus.dao.proyectoDao;
import ec.com.sisapus.dao.usuarioDao;
import ec.com.sisapus.daoimpl.proyectoDaoImpl;
import ec.com.sisapus.daoimpl.usuarioDaoImpl;
import ec.com.sisapus.modelo.Proyecto;
import ec.com.sisapus.modelo.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kleber
 */
public class Administrar_Proyectos {
    
    public Administrar_Proyectos() {
    }
    @Resource  
    List<Proyecto> listaproyectos;  
    Proyecto proyecto;
    Usuario usuario;
    usuarioDao usuarioDao= new usuarioDaoImpl();
    proyectoDao proyectoDao=new proyectoDaoImpl();
    private static final int proyectobd = 4;  
    @BeforeClass
   
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        usuario=new  Usuario ();
     proyecto=new Proyecto();
      proyecto.setUsuario(usuario);
      proyecto.setContratProy("Proyecto CONAFIPS");
       proyecto.setPropiepProy("julio villota");
        proyecto.setObraProy("quito sur");
       proyecto.setUbicProy("Quito");
       Date hoy = new Date();
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(hoy);
        proyecto.setFechaProy(java.sql.Date.valueOf(fecha));
       proyecto.setCostotProy(10000.00);
    // proyecto.setCodigoProy(7);
     usuario.setCodigoUsu(1);
     
    }
    
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
   @ Test
            
 public void ListarProyectos()
           {
         listaproyectos=proyectoDao.listarProyectos();
          Assert.assertTrue("Comprobar que en la base de datos hay 4 registros de proyectos",  
           listaproyectos.size() == proyectobd);  
           }  
   
   @ Test 
       public void CrearProyecto() {
          try {
    //crear usuario
         proyectoDao.crearProyecto(proyecto); 
       if(proyectoDao !=null) {
 
         assertTrue("Proyecto Creado Exitosamente", true);

            } else {

           fail("Error Proyecto no creado" );
}

} catch (Exception e) {

fail("Proyecto no creado");
} 
  }  
   
 @Test   
 public void EliminarProyecto() {
   try {
    
 proyectoDao.eliminarProyecto(proyecto.getCodigoProy());   
if(usuarioDao !=null) {
 
junit.framework.Assert.assertTrue(" Registro Eliminado Exitosamente", true);

} else {

fail("Error Proyecto no se puede eliminar" );
}

} catch (Exception e) {

fail("Proyecto no eliminado");
} 
   }   
   
     @Test   
 public void ActualizarProyecto() {
   try {
    
proyectoDao.actualizarProyecto(proyecto);   
if(usuarioDao !=null) {
 
assertTrue(" Registro Actualizado Exitosamente", true);

} else {

fail("Error Proyecto no se pudo actualizar" );
}

} catch (Exception e) {

fail("Error Proyecto no se pudo Actualizar");
} 
   }

   
 /*@ Test 
 public void CrearProyecto1() {
 proyectoDao.crearProyecto(proyecto);
 listaproyectos = proyectoDao.listarProyectos();
        
Assert.assertTrue("Comprobar que se ha registrado  un proyecto mas en el sistema ",  
                listaproyectos.size() == proyectobd+ 1);  
Proyecto proyectoAux = listaproyectos.get(proyectobd);  
  
Assert.assertTrue("Comprobar que coincide id.",  proyecto.getCodigoProy() == proyectoAux.getCodigoProy());  
Assert.assertTrue("Comprueba que coincide el contrato",  proyecto.getContratProy() == proyectoAux.getContratProy());  
Assert.assertTrue("Comprueba que coincide el costo.",proyecto.getCostotProy()== proyectoAux.getCostotProy());  
Assert.assertTrue("Comprueba que coincide el Propietario", proyecto.getPropiepProy()== proyectoAux.getPropiepProy());
Assert.assertTrue("Comprueba que coincide el Obra", proyecto.getObraProy()== proyectoAux.getObraProy());
Assert.assertTrue("Comprueba que coincide el Ubicacion.", proyecto.getUbicProy()== proyectoAux.getUbicProy());
Assert.assertTrue("Comprueba que coincide el Fecha", proyecto.getFechaProy()== proyectoAux.getFechaProy());

     }*/
}