/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.com.sisapus.dao.equipoherrDao;
import ec.com.sisapus.daoimpl.equipoherrDaoImpl;
import ec.com.sisapus.modelo.Categoriaequipoherramienta;
import ec.com.sisapus.modelo.Equipoherramienta;
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
public class Administrar_EquiposHerramientas {
    
    public Administrar_EquiposHerramientas() {
    }
    
    @Resource  
    List<Equipoherramienta> listarequiposher;  
    Equipoherramienta equipo;
     Categoriaequipoherramienta categoriaequipoherramienta1;
    equipoherrDao equiposdao= new equipoherrDaoImpl();
    
    private static final int equipobd = 5;  
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        equipo=new Equipoherramienta();
        categoriaequipoherramienta1= new Categoriaequipoherramienta();
         equipo.setNombreEqherr("Herramienta Menor");
        equipo.setCostohoraEqherr(2.00);
        equipo.setCategoriaequipoherramienta(categoriaequipoherramienta1);
        categoriaequipoherramienta1.setCodCatEqherr(1);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    
     @Test
    public void BuscarEquiposH()
           {
        listarequiposher=equiposdao.buscarTodosEquipHerr();
          Assert.assertTrue("Busca en la base de datos que exista 6 registros de Equipos y Herramientas",  
          listarequiposher.size() == equipobd);  
           }  
    @Test
       public void CrearEquiposH() {
          try {
    //crear     equipos
         equiposdao.crearEquipoHerr(equipo);
       if(equiposdao !=null) {
 
         assertTrue("Registro Creado Exitosamente", true);

         } else {

           fail("Error No se pudo Crear Registros" );
    }

   } catch (Exception e) {

 fail("Error Registros no creado");
 } 
   }  
    
    @Test   
public void EliminarEquiposH() {
 try {
  equiposdao.eliminarEquipoHerr(equipo.getCodigoEqherr());
if(equiposdao !=null) {
 
assertTrue(" Registro Eliminado Exitosamente", true);

} else {

   fail("Error Registro no se puede eliminar" );
    }

} catch (Exception e) {

fail("Registro no eliminado");
} 
   } 
    
 @Test   
 public void ActualizarEquipoH() {
   try {
    
equiposdao.actualizarEquipoHerr(equipo);
if(equiposdao !=null) {
 
assertTrue(" Registro Actualizado Exitosamente", true);

} else {

fail("Error Registro no se pudo actualizar" );
}

} catch (Exception e) {

fail("Error Registro no se pudo Actualizar");
} 
   }
    
}