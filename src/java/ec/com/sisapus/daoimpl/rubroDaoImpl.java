/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.rubroDao;
import ec.com.sisapus.modelo.Rubro;
import ec.com.sisapus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public class rubroDaoImpl implements rubroDao{

    @Override
    public Rubro buscarPorRubro(Rubro rubro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rubro> buscarTodosRubros() {
        List<Rubro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Rubro r ";
        try {
            sesion.beginTransaction();
            listado = sesion.createQuery(sql).list();
            sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        }

        return listado;
    }

    @Override
    public boolean crearRubro(Rubro rubro) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(rubro);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean actualizarRubro(Rubro rubro) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Rubro rubrodb = (Rubro) sesion.load(Rubro.class, rubro.getCodigoRubro());
            rubrodb.setNombreRubro(rubro.getNombreRubro());
            rubrodb.setDetalleRubro(rubro.getDetalleRubro());
            rubrodb.setUnidadRubro(rubro.getUnidadRubro());
            sesion.merge(rubrodb);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean eliminarRubro(Integer idRubro) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Rubro rubro = (Rubro) sesion.load(Rubro.class, idRubro);
            sesion.delete(rubro);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Rubro getByIdRubro(Session session, Integer idrubro) throws Exception {
        
         return (Rubro) session.load(Rubro.class, idrubro); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rubro getUltimoRegistroRubro(Session session) throws Exception {
        String hql="FROM Rubro r left join fetch r.categoriarubro order by CODIGO_RUBRO desc";
        Query query=session.createQuery(hql).setMaxResults(1);
         return (Rubro) query.uniqueResult();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rubro> BuscarRubro() {
        List<Rubro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Rubro";
        try {
            sesion.beginTransaction();
            listado = sesion.createQuery(sql).list();
            sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        }

        return listado; //To change body of generated methods, choose Tools | Templates.
    }

  
    
}
