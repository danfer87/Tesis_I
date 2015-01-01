/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.equipoherrDao;
import ec.com.sisapus.modelo.Equipoherramienta;
import ec.com.sisapus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public class equipoherrDaoImpl implements equipoherrDao{

    @Override
    public Equipoherramienta buscarPorEquipoHerr(Equipoherramienta equipoherr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Equipoherramienta> buscarTodosEquipHerr() {
        List<Equipoherramienta> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Equipoherramienta e left join fetch e.categoriaequipoherramienta";
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
    public boolean crearEquipoHerr(Equipoherramienta equipoherr) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(equipoherr);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean actualizarEquipoHerr(Equipoherramienta equipoherr) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Equipoherramienta equipoherrdb = (Equipoherramienta) sesion.load(Equipoherramienta.class, equipoherr.getCodigoEqherr());
            equipoherrdb.setNombreEqherr(equipoherr.getNombreEqherr());
            equipoherrdb.setCostohoraEqherr(equipoherr.getCostohoraEqherr());
            equipoherrdb.setCategoriaequipoherramienta(equipoherr.getCategoriaequipoherramienta());
            sesion.merge(equipoherrdb);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean eliminarEquipoHerr(Integer idEqHerr) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Equipoherramienta equipoherr = (Equipoherramienta) sesion.load(Equipoherramienta.class, idEqHerr);
            sesion.delete(equipoherr);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Equipoherramienta getByIdEquipo(Session session, Integer idequipo) throws Exception {
       return (Equipoherramienta) session.load(Equipoherramienta.class, idequipo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Equipoherramienta getUltimoRegistro(Session session) throws Exception {
        String hql="FROM Equipoherramienta e left join fetch e.categoriaequipoherramienta order by CODIGO_EQHERR desc";
        Query query=session.createQuery(hql).setMaxResults(1);
        
        return (Equipoherramienta) query.uniqueResult();//To change body of generated methods, choose Tools | Templates.
    }
    
}
