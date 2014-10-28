/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.bean;

import ec.com.sisapus.daoimpl.rubroDaoImplInterface;
import ec.com.sisapus.modelo.Rubro;
import ec.com.sisapus.util.HibernateUtil;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Edison
 */
@ManagedBean
@ViewScoped
public class rubroBeanVista {

    /**
     * Creates a new instance of rubroBeanVista
     */
    private Session session;
    private Transaction transaccion;
    
    private Rubro rubro;
    private List<Rubro> listaRubro;
    
    public rubroBeanVista() {
        this.rubro=new Rubro();
        //this.rubro.setCodigoRubro();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaction transaccion) {
        this.transaccion = transaccion;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public List<Rubro> getListaRubro() {
        return listaRubro;
    }

    public void setListaRubro(List<Rubro> listaRubro) {
        this.listaRubro = listaRubro;
    }
    
    //Crear Rubro
    public void crearRubros()
    {
        this.session=null;
        this.transaccion=null;
        
        try 
        {
            rubroDaoImplInterface daoRubro=new rubroDaoImplInterface();
            this.session= HibernateUtil.getSessionFactory().openSession();
            this.transaccion=session.beginTransaction();
            if (daoRubro.crearRubro(this.session, this.rubro))
            {
                this.transaccion.commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Rubro creado correctamente"));
                this.rubro=new Rubro();
            } else {
                String msg = "No se modifico el Rubro";
                FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message2);
                }
        }
        catch (Exception ex) {
            if(this.transaccion!=null)
            {
                this.transaccion.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }

    //Modificar Rubro
    public void modificarRubros()
    {
        this.session=null;
        this.transaccion=null;
        
        try
        {
            rubroDaoImplInterface daoRubro=new rubroDaoImplInterface();
            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaccion=session.beginTransaction();
            
            /*this.rubro.setNombreRubro(this.rubro.getNombreRubro());
            this.rubro.setDetalleRubro(this.rubro.getDetalleRubro());
            this.rubro.setUnidadRubro(this.rubro.getUnidadRubro());
            */
            daoRubro.modificarRubro(this.session, this.rubro);
            
            this.transaccion.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Los cambios fueron guardados correctamente"));
        }
        catch(Exception ex)
        {
            if(this.transaccion!=null)
            {
                this.transaccion.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }
    
    public void cargarRubroEditar(String codigoRubro)
    {
        this.session=null;
        this.transaccion=null;
        
        try
        {
            rubroDaoImplInterface daoRubro=new rubroDaoImplInterface();
            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaccion=session.beginTransaction();
            
            this.rubro=daoRubro.getByCodigoRubro(this.session, codigoRubro);
            
            RequestContext.getCurrentInstance().update("frmEditarRubro:panelEditarRubro");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarRubro').show()");
            
            this.transaccion.commit();
        }
        catch(Exception ex)
        {
            if(this.transaccion!=null)
            {
                this.transaccion.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }
    
    public void eliminarRubros()
    {
        this.session=null;
        this.transaccion=null;
        
        try 
        {
            rubroDaoImplInterface daoRubro=new rubroDaoImplInterface();
            this.session= HibernateUtil.getSessionFactory().openSession();
            this.transaccion=session.beginTransaction();
            if (daoRubro.eliminarRubro(this.session, this.rubro.getCodigoRubro()))
            {
                this.transaccion.commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Rubro eliminado correctamente"));
                //this.rubro=new Rubro();
            } else {
                String msg = "No se elimino el Rubro";
                FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message2);
                }
        }
        catch (Exception ex) {
            if(this.transaccion!=null)
            {
                this.transaccion.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }
    
     public List<Rubro> getAll()
    {
        this.session=null;
        this.transaccion=null;
        
        try
        {
            rubroDaoImplInterface daoRubro=new rubroDaoImplInterface();
            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaccion=this.session.beginTransaction();
            this.listaRubro=daoRubro.listarTodosRubros(this.session);
            this.transaccion.commit();
            return this.listaRubro;
        }
        catch(Exception ex)
        {
            if(this.transaccion!=null)
            {
                this.transaccion.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
            return null;
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
