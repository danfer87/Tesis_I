/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.dao;

import org.hibernate.Session;
import ec.com.sisapus.modelo.EquipherrApu;
import ec.com.sisapus.modelo.ManoobraApu;

/**
 *
 * @author kleber
 */
public interface ApusDao {
    //insertar equiposapus
     public boolean insert(Session session, EquipherrApu equipherrapu) throws Exception;
    //insertar nano de obra apus
      public boolean insertarManobra(Session session,ManoobraApu manapu) throws Exception;
     
}
