/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.presupuestoDao;
import ec.com.sisapus.modelo.Presupuesto;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public class presupuestoDaoImpl implements presupuestoDao{

    @Override
    public boolean insert(Session session, Presupuesto presupuesto) throws Exception {
        session.save(presupuesto);
        
        return true;
    }
    
}
