/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.materialDao;
import ec.com.sisapus.modelo.Material;
import ec.com.sisapus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public class materialDaoImpl implements materialDao{

    @Override
    public Material buscarPorMaterial(Material material) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Material> buscarTodosMater() {
        List<Material> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Material m left join fetch m.categoriamaterial";
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
    public boolean crearMater(Material material) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(material);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean actualizarMater(Material material) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Material materialdb = (Material) sesion.load(Material.class, material.getCodigoMat());
            materialdb.setNombreMat(material.getNombreMat());
            materialdb.setUnidMat(material.getUnidMat());
            materialdb.setPrecunitMat(material.getPrecunitMat());
            materialdb.setCategoriamaterial(material.getCategoriamaterial());
            sesion.merge(materialdb);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean eliminarMater(Integer idMater) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Material material = (Material) sesion.load(Material.class, idMater);
            sesion.delete(material);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

  

    @Override
    public Material getUltimoRegistro(Session session) throws Exception {
        String hql="FROM Material m left join fetch m.categoriamaterial order by CODIGO_MAT desc";
        Query query=session.createQuery(hql).setMaxResults(1);
        
        return (Material) query.uniqueResult(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Material getByIdMaterial(Session session, Integer idmaterial) throws Exception {
           return (Material) session.load(Material.class, idmaterial);//To change body of generated methods, choose Tools | Templates.
    }
    
}
