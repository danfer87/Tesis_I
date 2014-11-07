/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.equipoherrDao;
import ec.com.sisapus.daoimpl.equipoherrDaoImpl;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ec.com.sisapus.modelo.EquipherrApu;
import ec.com.sisapus.modelo.Equipoherramienta;
import ec.com.sisapus.util.HibernateUtil;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author kleber
 */
@Named(value = "ApuBeanVista")
@ViewScoped
public class ApuBeanVista implements Serializable {
    
     Session session;
    Transaction transaction;
    
     private Equipoherramienta equipherramientas;    
     private List<EquipherrApu> listaEquiposApus;
     
     
     public ApuBeanVista()
             
             {
                this.equipherramientas=new Equipoherramienta();
                this.listaEquiposApus=new ArrayList<>();
             }

    public Equipoherramienta getEquipherramientas() {
        return equipherramientas;
    }

    public void setEquipherramientas(Equipoherramienta equipherramientas) {
        this.equipherramientas = equipherramientas;
    }
             
     
     
     

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<EquipherrApu> getListaEquiposApus() {
        return listaEquiposApus;
    }

    public void setListaEquiposApus(List<EquipherrApu> listaEquiposApus) {
        this.listaEquiposApus = listaEquiposApus;
    }
     
 ///funcion para lista de elementos detalle)
    
    public void agregarListaEquiposApus(Integer idEquipos)
    {
        this.session=null;
        this.transaction=null;
        
        try
        {
            this.session=HibernateUtil.getSessionFactory().openSession();
            
          equipoherrDaoImpl daoequipo=new equipoherrDaoImpl();
         //   equipoherrDao daoequipo=new equipoherrDaoImpl();
            
            this.transaction=this.session.beginTransaction();
            
            this.equipherramientas=daoequipo.getByIdEquipo(session, idEquipos);
            
            this.listaEquiposApus.add(new EquipherrApu(null, null,this.equipherramientas.getNombreEqherr(), null,null,this.equipherramientas.getCostohoraEqherr(), null, null));
            
            this.transaction.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Equipo/Herramienta agregado"));
            
         
            RequestContext.getCurrentInstance().update("frmdeteequipos:tablaListaEquipos");
            RequestContext.getCurrentInstance().update("frmEquipos:msgs");
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                transaction.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }
     
   
}
