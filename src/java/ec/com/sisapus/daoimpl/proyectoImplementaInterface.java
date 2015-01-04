/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.proyectoInterface;
import ec.com.sisapus.modelo.Proyecto;
import ec.com.sisapus.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Edison
 */
public class proyectoImplementaInterface implements proyectoInterface{

    @Override
    public List<Proyecto> encontrarTodoslosProyectos() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        Criteria crit = sesion.createCriteria(Proyecto.class);
        ArrayList<Proyecto>  list = (ArrayList<Proyecto>) crit.list();
        return list;
    }

    @Override
    public List<Proyecto> encontrarProyectos(Integer id) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        Criteria crit = sesion.createCriteria(Proyecto.class);
        crit.add(Restrictions.eq("codigoProy", id));
        return crit.list();
    }

    @Override
    public List<Proyecto> encontrarProyectos(Session sesion, Integer id) {
        String hql="from Proyecto where codigoProy=:codigopro";
        Query query=sesion.createQuery(hql);
        query.setParameter("codigopro", id);
        
        return (List<Proyecto>) query.list();
    }

    @Override
    public Proyecto proyetoPorId(Session sesion, Integer idproyecto) {
        String hql="from Proyecto where codigoProy=:codigopro";
        Query query=sesion.createQuery(hql);
        query.setParameter("codigopro", idproyecto);
        
        return (Proyecto) query.uniqueResult();
    }

    
    
}
