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
    Manoobra equipo;
     Categoriamanoobra catmanoobra;
    manoobraDao manodao= new manoobraDaoImpl();
    
    private static final int equipobd = 2; 
    
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