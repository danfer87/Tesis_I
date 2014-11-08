/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.ApusDao;
import ec.com.sisapus.modelo.EquipherrApu;
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

   
}
