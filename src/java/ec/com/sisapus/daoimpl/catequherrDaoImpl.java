/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.catequherrDao;
import ec.com.sisapus.modelo.Categoriaequipoherramienta;
import ec.com.sisapus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public class catequherrDaoImpl implements catequherrDao{

    @Override
    public List<Categoriaequipoherramienta> listarCatEquipHerram() {
        List<Categoriaequipoherramienta> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Categoriaequipoherramienta";
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
