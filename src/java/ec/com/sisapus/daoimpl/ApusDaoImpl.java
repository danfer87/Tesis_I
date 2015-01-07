/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.daoimpl;

import ec.com.sisapus.dao.ApusDao;
import ec.com.sisapus.modelo.Analisispreciounitario;
import ec.com.sisapus.modelo.EquipherrApu;
import ec.com.sisapus.modelo.ManoobraApu;
import ec.com.sisapus.modelo.MaterialApu;
import ec.com.sisapus.modelo.TransporteApu;
import ec.com.sisapus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Edison
 */
public class ApusDaoImpl implements ApusDao {

    @Override
    public boolean insert(Session session, EquipherrApu equipherrapu) throws Exception {
        session.save(equipherrapu);
        
        return true;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarManobra(Session session, ManoobraApu manapu) throws Exception {
         session.save(manapu);
        
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarMaterial(Session session, MaterialApu mateapu) throws Exception {
       session.save(mateapu); //To change body of generated methods, choose Tools | Templates.
        return true;
    }

    @Override
    public boolean insertarTransporte(Session session, TransporteApu transapu) throws Exception {
        session.save(transapu); //To change body of generated methods, choose Tools | Templates.
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarAPU(Session session, Analisispreciounitario apu) throws Exception {
        session.save(apu); //To change body of generated methods, choose Tools | Templates.
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EquipherrApu getByIdEquipoAPU(Session session, Integer idequipoapu) throws Exception {
       return (EquipherrApu) session.load(EquipherrApu.class, idequipoapu); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EquipherrApu getUltimoRegistroEqApu(Session session) throws Exception {
     String hql="FROM EquipherrApu order by COD_EQHERR_APU desc";
        Query query=session.createQuery(hql).setMaxResults(1);
        
        return (EquipherrApu) query.uniqueResult(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override

    public Analisispreciounitario getUltimoRegistroApu(Session session) throws Exception {
        String hql="from Analisispreciounitario  order by  CODIGO_APU desc";
        Query query=session.createQuery(hql).setMaxResults(1);
        
        return (Analisispreciounitario) query.uniqueResult(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Analisispreciounitario getByIdAPUS(Session session, Integer ideapu) throws Exception {
        return (Analisispreciounitario) session.load(EquipherrApu.class, ideapu); //To change body of generated methods, choose Tools | Templates.


    /*@Override
    public Analisispreciounitario obtenerUltimoRegistroApu(Session session) throws Exception {
        String hql="FROM Analisispreciounitario order by codigoApu desc";
        Query query=session.createQuery(hql).setMaxResults(1);
        
        return (Analisispreciounitario) query.uniqueResult();
    }*/

    /*@Override
    public List<Analisispreciounitario> listarApus(Session session) throws Exception {
        String hql="from Analisispreciounitario";
        Query query=session.createQuery(hql);
        List<Analisispreciounitario> listaApu=(List<Analisispreciounitario>) query.list();
        return listaApu;*/

    }

    @Override
    public Analisispreciounitario obtenerApuPorId(Session session, Integer idapu) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Analisispreciounitario obtenerUltimoRegistroApu(Session session) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
