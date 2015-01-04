/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.dao;

import ec.com.sisapus.modelo.Presupuesto;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public interface presupuestoDao {
    
    public boolean insert(Session session, Presupuesto presupuesto) throws Exception;
    
}
