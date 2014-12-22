/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.ApusDao;
import ec.com.sisapus.modelo.EquipherrApu;
import ec.com.sisapus.modelo.ManoobraApu;
import ec.com.sisapus.modelo.MaterialApu;
import ec.com.sisapus.modelo.TransporteApu;
import ec.com.sisapus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public class ApusDaoImpl implements ApusDao {

    @Override
    public boolean insert(Session session, EquipherrApu equipherrapu) throws Exception {
        session.save(equipherrapu);
        
        return true;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarManobra(Session session, ManoobraApu manapu) throws Exception {
         session.save(manapu);
        
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarMaterial(Session session, MaterialApu mateapu) throws Exception {
       session.save(mateapu); //To change body of generated methods, choose Tools | Templates.
        return true;
    }

    @Override
    public boolean insertarTransporte(Session session, TransporteApu transapu) throws Exception {
        session.save(transapu); //To change body of generated methods, choose Tools | Templates.
        return true; //To change body of generated methods, choose Tools | Templates.
    }

   
}
