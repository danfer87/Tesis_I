/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.com.sisapus.dao.materialDao;
import ec.com.sisapus.daoimpl.materialDaoImpl;
import ec.com.sisapus.modelo.Categoriamaterial;
import ec.com.sisapus.modelo.Material;
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
public class Administrar_Materiales {
    
    public Administrar_Materiales() {
    }
      @Resource  
    List<Material> listarmaterial;  
    Material material;
     Categoriamaterial catmaterial;
    materialDao materialdao= new materialDaoImpl();
    
    private static final int materialbd = 2; 
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        material=new Material();
        catmaterial=new Categoriamaterial();
        material.setNombreMat("cable tipo FTO cat6");
        material.setUnidMat("MT");
        material.setPrecunitMat(1.70);
        material.setCategoriamaterial(catmaterial);
        catmaterial.setCodCatMat(1);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void BuscarManoObra()
           {
       listarmaterial=materialdao.buscarTodosMater();
          Assert.assertTrue("Busca en la base de datos que exista 2 registros para Mantenimiento de Materiales",  
         listarmaterial.size() == materialbd);  
           }  
    @Test
       public void CrearMaterial() {
          try {
    //crear     
         materialdao.crearMater(material);
       if(materialdao !=null) {
 
         assertTrue("Registro para Material Creado Exitosamente", true);

         } else {

           fail("Error No se pudo Crear Registro para Material" );
    }

   } catch (Exception e) {

 fail("Error Registro para Material");
 } 
   }  
    
    @Test   
public void EliminarMaterial() {
 try {
    materialdao.eliminarMater(material.getCodigoMat());
if( materialdao !=null) {
 
assertTrue(" Registro Eliminado Exitosamente", true);

} else {

   fail("Error Registro no se puede eliminar" );
    }

} catch (Exception e) {

fail("Registro no eliminado");
} 
   } 
    
 @Test   
 public void ActualizarMaterial() {
   try {
    
materialdao.actualizarMater(material);
if(materialdao !=null) {
 
assertTrue(" Registro Actualizado Exitosamente", true);

} else {

fail("Error Registro no se pudo actualizar" );
}

} catch (Exception e) {

fail("Error Registro no se pudo Actualizar");
} 
   } 
    
}