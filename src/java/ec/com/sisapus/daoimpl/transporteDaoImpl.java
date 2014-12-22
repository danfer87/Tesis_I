/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.transporteDao;
import ec.com.sisapus.modelo.Transporte;
import ec.com.sisapus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public class transporteDaoImpl implements transporteDao{

    @Override
    public Transporte buscarPorTransporte(Transporte transporte) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transporte> buscarTodosTransportes() {
        List<Transporte> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Transporte t left join fetch t.categoriatransporte";
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
    public boolean crearTransporte(Transporte transporte) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(transporte);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean actualizarTransporte(Transporte transporte) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Transporte transportedb = (Transporte) sesion.load(Transporte.class, transporte.getCodigoTransp());
            transportedb.setNombreTransp(transporte.getNombreTransp());
            transportedb.setTarifaTransp(transporte.getTarifaTransp());
            transportedb.setCategoriatransporte(transporte.getCategoriatransporte());
            sesion.merge(transportedb);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean eliminarTransporte(Integer idTransporte) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Transporte transporte = (Transporte) sesion.load(Transporte.class, idTransporte);
            sesion.delete(transporte);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Transporte getByIdTransporte(Session session, Integer idtransp) throws Exception {
        return (Transporte) session.load(Transporte.class, idtransp);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transporte getUltimoRegistro(Session session) throws Exception {
         String hql="FROM Transporte t left join fetch t.categoriatransporte order by CODIGO_TRANSP desc";
        Query query=session.createQuery(hql).setMaxResults(1);
        
        return (Transporte) query.uniqueResult();//To change body of generated methods, choose Tools | Templates.
    }
    
}
