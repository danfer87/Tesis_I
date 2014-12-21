/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.manoobraDao;
import ec.com.sisapus.modelo.Manoobra;
import ec.com.sisapus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public class manoobraDaoImpl implements manoobraDao{

    @Override
    public Manoobra buscarPorManoObra(Manoobra manoObra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Manoobra> buscarTodosManoObra() {
    List<Manoobra> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Manoobra mo left join fetch mo.categoriamanoobra";
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
    public boolean crearManoObra(Manoobra manoObra) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(manoObra);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean actualizarManoObra(Manoobra manoObra) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Manoobra manoobradb = (Manoobra) sesion.load(Manoobra.class, manoObra.getCodigoManob());
            manoobradb.setNombreManob(manoObra.getNombreManob());
            manoobradb.setCostojrhManob(manoObra.getCostojrhManob());
            manoobradb.setCategoriamanoobra(manoObra.getCategoriamanoobra());
            sesion.merge(manoobradb);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean eliminarManoObra(Integer idManoObra) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Manoobra manoobra = (Manoobra) sesion.load(Manoobra.class, idManoObra);
            sesion.delete(manoobra);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Manoobra getByIdManobra(Session session, Integer idmanobra) throws Exception {
         return (Manoobra) session.load(Manoobra.class, idmanobra);
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Manoobra getUltimoRegistro(Session session) throws Exception {
         String hql="FROM Manoobra mo left join fetch mo.categoriamanoobra order by CODIGO_MANOB desc";
        Query query=session.createQuery(hql).setMaxResults(1);
        
        return (Manoobra) query.uniqueResult(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
