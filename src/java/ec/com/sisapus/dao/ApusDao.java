/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.dao;

import org.hibernate.Session;
import ec.com.sisapus.modelo.EquipherrApu;

/**
 *
 * @author kleber
 */
public interface ApusDao {
     public boolean insert(Session session, EquipherrApu equipherrapu) throws Exception;
}
