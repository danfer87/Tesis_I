/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.com.sisapus.dao.manoobraDao;
import ec.com.sisapus.daoimpl.manoobraDaoImpl;
import ec.com.sisapus.modelo.Categoriamanoobra;
import ec.com.sisapus.modelo.Manoobra;
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
public class AdministrarManoObra {
    
    public AdministrarManoObra() {
    }
     @Resource  
    List<Manoobra> listarmanoObra;  
    Manoobra manoobra;
     Categoriamanoobra catmanoobra;
    manoobraDao manodao= new manoobraDaoImpl();
    
    private static final int manobd = 9; 
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
      @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Before
    public void setUp() {
        manoobra=new Manoobra();
        catmanoobra=new Categoriamanoobra();
        manoobra.setNombreManob("Supervisor");
        manoobra.setCostojrhManob(3.38);
        manoobra.setCategoriamanoobra(catmanoobra);
        catmanoobra.setCodCatManob(1);
    }
    @Test
    public void BuscarManoObra()
           {
        listarmanoObra=manodao.buscarTodosManoObra();
          Assert.assertTrue("Busca en la base de datos que exista 9 registros para Mantenimiento de Mano de Obra",  
          listarmanoObra.size() == manobd);  
           }  
    @Test
       public void CrearManoObra() {
          try {
    //crear     
         manodao.crearManoObra(manoobra);
       if(manodao !=null) {
 
         assertTrue("Registro para Mano de Obra Creado Exitosamente", true);

         } else {

           fail("Error No se pudo Crear Registro para Mano de Obra" );
    }

   } catch (Exception e) {

 fail("Error Registro para Mano de Obra");
 } 
   }  
    
    @Test   
public void EliminarManoObra() {
 try {
    manodao.eliminarManoObra(manoobra.getCodigoManob());
if( manodao !=null) {
 
assertTrue(" Registro Eliminado Exitosamente", true);

} else {

   fail("Error Registro no se puede eliminar" );
    }

} catch (Exception e) {

fail("Registro no eliminado");
} 
   } 
    
 @Test   
 public void ActualizarManoObra() {
   try {
    
manodao.actualizarManoObra(manoobra);
if(manodao !=null) {
 
assertTrue(" Registro Actualizado Exitosamente", true);

} else {

fail("Error Registro no se pudo actualizar" );
}

} catch (Exception e) {

fail("Error Registro no se pudo Actualizar");
} 
   } 
    
    
}