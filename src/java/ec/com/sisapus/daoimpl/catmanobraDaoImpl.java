/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.catmanobraDao;
import ec.com.sisapus.modelo.Categoriamanoobra;
import ec.com.sisapus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public class catmanobraDaoImpl implements catmanobraDao{

    @Override
    public List<Categoriamanoobra> listarCatManoObra() {
        List<Categoriamanoobra> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Categoriamanoobra";
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
