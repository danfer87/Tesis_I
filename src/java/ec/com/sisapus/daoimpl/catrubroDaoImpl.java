/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.catrubroDao;
import ec.com.sisapus.modelo.Categoriarubro;
import ec.com.sisapus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public class catrubroDaoImpl implements catrubroDao{

    @Override
    public List<Categoriarubro> listarCatRubro() {
        List<Categoriarubro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Categoriarubro";
        try {
            sesion.beginTransaction();
            listado = sesion.createQuery(sql).list();
            sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        }

        return listado;
    }
    
}
