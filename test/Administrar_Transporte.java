/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.com.sisapus.dao.transporteDao;
import ec.com.sisapus.daoimpl.transporteDaoImpl;
import ec.com.sisapus.modelo.Categoriatransporte;
import ec.com.sisapus.modelo.Transporte;
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
public class Administrar_Transporte {
    
    public Administrar_Transporte() {
    }
     @Resource  
    List<Transporte> listartransporte;  
    Transporte transporte;
     Categoriatransporte cattrans;
    transporteDao transportedao= new transporteDaoImpl();
    
    private static final int transpbd = 1; 
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        transporte=new Transporte();
        cattrans=new Categoriatransporte();
         transporte.setNombreTransp("Camioneta Nissan");
        transporte.setTarifaTransp(0.00);
        cattrans.setCodCatTrans(1);
    }
    
    @After
    public void tearDown() {
    }
   @Test
    public void BuscarTransporte()
           {
       listartransporte=transportedao.buscarTodosTransportes();
          Assert.assertTrue("Busca en la base de datos que exista 1 registros para Mantenimiento de Transporte",  
         listartransporte.size() == transpbd);  
           }  
    @Test
       public void CrearTransporte() {
          try {
    //crear     
         transportedao.crearTransporte(transporte);
       if(transportedao!=null) {
 
         assertTrue("Registro para Transporte Creado Exitosamente", true);

         } else {

           fail("Error No se pudo Crear Registro para Transporte" );
    }

   } catch (Exception e) {

 fail("Error Registro para Transporte");
 } 
   }  
    
    @Test   
public void EliminarTransporte() {
 try {
    
    transportedao.eliminarTransporte(transporte.getCodigoTransp());
if( transportedao !=null) {
 
assertTrue(" Registro Eliminado Exitosamente", true);

} else {

   fail("Error Registro no se puede eliminar" );
    }

} catch (Exception e) {

fail("Registro no eliminado");
} 
   } 
    
 @Test   
 public void ActualizarTransporte() {
   try {
    

transportedao.actualizarTransporte(transporte);
if(transportedao !=null) {
 
assertTrue(" Registro Actualizado Exitosamente", true);

} else {

fail("Error Registro no se pudo actualizar" );
}

} catch (Exception e) {

fail("Error Registro no se pudo Actualizar");
} 
   } 
}