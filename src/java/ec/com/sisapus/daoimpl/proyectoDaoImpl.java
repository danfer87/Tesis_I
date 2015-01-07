/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.proyectoDao;
import ec.com.sisapus.modelo.Proyecto;
import ec.com.sisapus.modelo.Usuario;
import ec.com.sisapus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public class proyectoDaoImpl implements proyectoDao{

    @Override
    public List<Proyecto> listarProyectos() {
        List<Proyecto> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Proyecto p left join fetch p.usuario";
        //String sql = "from Proyecto p inner join p.usuario u WHERE u.sobrenombreUsu=";
     
        //Query query=sesion.createQuery(sql);
        try {
            sesion.beginTransaction();
          //  query.setString("sobre",sobre);
            listado = sesion.createQuery(sql).list();
            sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        }

        return listado;
    }

    @Override
    public boolean crearProyecto(Proyecto proyecto) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(proyecto);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean actualizarProyecto(Proyecto proyecto) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Proyecto proyectodb = (Proyecto) sesion.load(Proyecto.class, proyecto.getCodigoProy());
            proyectodb.setUsuario(proyecto.getUsuario());
            proyectodb.setContratProy(proyecto.getContratProy());
            proyectodb.setPropiepProy(proyecto.getPropiepProy());
            proyectodb.setObraProy(proyecto.getObraProy());
            proyectodb.setUbicProy(proyecto.getUbicProy());
            proyectodb.setFechaProy(proyecto.getFechaProy());
            proyectodb.setCostotProy(proyecto.getCostotProy());
            
            sesion.merge(proyectodb);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean eliminarProyecto(Integer idProyecto) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Proyecto proyecto = (Proyecto) sesion.load(Proyecto.class, idProyecto);
            sesion.delete(proyecto);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override

    public List<Proyecto> listarProyectosPorUsuario(String sobre) {
        List<Proyecto> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Proyecto p inner join p.usuario u WHERE u.sobrenombreUsu=usuario";
        try {
            Query query=sesion.createQuery(sql);
            sesion.beginTransaction();
       query.setParameter("usuario",sobre);
        query.executeUpdate();
            listado = sesion.createQuery(sql).list();
            sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        }

        return listado;
    }

    @Override

    public boolean modificarProyecto(Session session, Proyecto tProyecto) throws Exception {
        session.update(tProyecto);
        return true;
    }

    @Override
    public List<Proyecto> listarPorUsuario(Session session,String sobre1 ) throws Exception {
        String hql="from Proyecto p inner join p.usuario u WHERE u.sobrenombreUsu=:sobre1";
        Query query=session.createQuery(hql);
        try {
            query.setParameter("sobre1", sobre1);
        } catch (Exception e) {
            session.beginTransaction().rollback();
        }
                return (List<Proyecto>) query.list();
    }

    @Override
    public Proyecto obtenerProyectoPorId(Session session, Integer idProyecto) throws Exception {
        return (Proyecto) session.load(Proyecto.class, idProyecto);
    }

      
}
