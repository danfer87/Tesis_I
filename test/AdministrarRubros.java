/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.com.sisapus.dao.rubroDao;
import ec.com.sisapus.daoimpl.rubroDaoImpl;
import ec.com.sisapus.daoimpl.usuarioDaoImpl;
import ec.com.sisapus.modelo.Rubro;
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
public class AdministrarRubros {
    
    public AdministrarRubros() {
    }
     @Resource  
    List<Rubro> listarubros;  
   Rubro rubro;
    
    rubroDao rubrodao= new rubroDaoImpl();
    
    private static final int Rubrobd = 6;  
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        rubro=new Rubro();
        this.rubro.setNombreRubro("ESTRUCTURA TRAMO 1");
        this.rubro.setDetalleRubro("ESTRUCTURA TRAMO 1 CIUDAD DE QUITO");
        this.rubro.setUnidadRubro("METROS CUBICOS");
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
     @Test
    public void BuscarRubros()
           {
         listarubros=rubrodao.buscarTodosRubros();
          Assert.assertTrue("Busca en la base de datos que exista 6 registros de Rubros",  
          listarubros.size() == Rubrobd);  
           }  
    @Test
       public void CrearRubros() {
          try {
    //crear usuario
         rubrodao.crearRubro(rubro); 
       if(rubrodao !=null) {
 
         assertTrue("Rubro Creado Exitosamente", true);

         } else {

           fail("Error Rubro no creado" );
    }

   } catch (Exception e) {

 fail("Error Rubro no creado");
 } 
   }  
    
    @Test   
public void EliminarRubro() {
 try {
 rubrodao.eliminarRubro(rubro.getCodigoRubro());   
if(rubrodao !=null) {
 
assertTrue(" Registro Eliminado Exitosamente", true);

} else {

   fail("Error Rubro no se puede eliminar" );
    }

} catch (Exception e) {

fail("Rubro no eliminado");
} 
   } 
    
 @Test   
 public void ActualizarRubro() {
   try {
    
rubrodao.actualizarRubro(rubro); 
if(rubrodao !=null) {
 
assertTrue(" Registro Actualizado Exitosamente", true);

} else {

fail("Error Rubro no se pudo actualizar" );
}

} catch (Exception e) {

fail("Error Rubro no se pudo Actualizar");
} 
   }
    
    
    
}