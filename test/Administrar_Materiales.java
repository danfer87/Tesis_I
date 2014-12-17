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
    
    private static final int manobd = 3; 
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}