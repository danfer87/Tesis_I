/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.perfilDao;
import ec.com.sisapus.modelo.Perfil;
import ec.com.sisapus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public class perfilDaoImpl implements perfilDao{

    @Override
    public void actualizarPermisoPerfil(Perfil perfil) {
            Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            //Perfil perfildb = (Perfil) session.load(Perfil.class, perfil.getCodigoPerf());
            session.merge(perfil);
            session.beginTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en la actualizacion del perfil" +e.getMessage());
            session.beginTransaction().rollback();
        }
    }

    @Override
    public Perfil buscarPerfilPorId(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Perfil) session.load(Perfil.class, id);
    }

    @Override
    public List<Perfil> buscarTodosPerfiles() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from  Perfil").list();  
    }

    @Override
    public List<Perfil> selectItems() {
        List<Perfil> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Perfil";
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
    public boolean crearPerfil(Perfil perfil) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(perfil);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean eliminarPerfil(Integer idPerfil) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Perfil perfil = (Perfil) sesion.load(Perfil.class, idPerfil);
            sesion.delete(perfil);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean actualizarPerfil(Perfil perfil) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Perfil perfildb = (Perfil) sesion.load(Perfil.class, perfil.getCodigoPerf());
            perfildb.setDescripPerf(perfil.getDescripPerf());
            sesion.merge(perfildb);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    
}
