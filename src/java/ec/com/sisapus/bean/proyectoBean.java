package ec.com.sisapus.bean;

import ec.com.sisapus.dao.proyectoDao;
import ec.com.sisapus.daoimpl.proyectoDaoImpl;
import ec.com.sisapus.modelo.Proyecto;
import ec.com.sisapus.util.HibernateUtil;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Edison
 */

@ManagedBean
@ViewScoped
public class proyectoBean {

    private Proyecto proyecto;
    private List<Proyecto> listaProyectos;
    
    //////
    private Session session;
    private Transaction transaccion;
    /////
    
    public proyectoBean() {
        this.proyecto = new Proyecto();
        }
    
    public Proyecto getProyecto() {
        if(this.proyecto == null){
        proyecto = new Proyecto();
        }
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public List<Proyecto> getListaProyectos() {
        proyectoDao proyecDao = new proyectoDaoImpl();
        listaProyectos = proyecDao.listarProyectos();
        return listaProyectos;
    }

    public void setListaProyectos(List<Proyecto> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }
    
    //////////////////////////
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
    
    //////////////////////////
    
    ////Crear Proyecto
    public void crearProyecto(ActionEvent actionEvent) {
        proyectoDao proyectDao = new proyectoDaoImpl();
        String msg;
        this.proyecto.setUsuario(this.proyecto.getUsuario());
        this.proyecto.setContratProy(this.proyecto.getContratProy());
        this.proyecto.setPropiepProy(this.proyecto.getPropiepProy());
        this.proyecto.setObraProy(this.proyecto.getObraProy());
        this.proyecto.setUbicProy(this.proyecto.getUbicProy());
        this.proyecto.setFechaProy(this.proyecto.getFechaProy());
        this.proyecto.setCostotProy(this.proyecto.getCostotProy());
            
        if (proyectDao.crearProyecto(this.proyecto)) {
            msg = "Proyecto creado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO,msg , null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se creo el Proyecto";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    /*Actualizar Proyecto*/
    public void actualizarProyecto(ActionEvent actionEvent) {
        proyectoDao proyectDao = new proyectoDaoImpl();
        String msg;
        this.proyecto.setUsuario(this.proyecto.getUsuario());
        this.proyecto.setContratProy(this.proyecto.getContratProy());
        this.proyecto.setPropiepProy(this.proyecto.getPropiepProy());
        this.proyecto.setObraProy(this.proyecto.getObraProy());
        this.proyecto.setUbicProy(this.proyecto.getUbicProy());
        this.proyecto.setFechaProy(this.proyecto.getFechaProy());
        this.proyecto.setCostotProy(this.proyecto.getCostotProy());
        
        if (proyectDao.actualizarProyecto(this.proyecto)) {
            msg = "Proyecto modificado correctamente";
             FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se modifico el Proyecto";
             FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message2);
        }
      }
    
    /*Eliminar Proyecto*/
    public void eliminarProyecto(ActionEvent actionEvent) {
        proyectoDao proyectDao = new proyectoDaoImpl();
        String msg;
        if (proyectDao.eliminarProyecto(this.proyecto.getCodigoProy())) {
            msg = "Proyecto eliminado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se elimino el Proyecto";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    //MODIFICAR PROYECTO OTRA FORMA///////
 
    public void modificarProyecto()
    {
        this.session=null;
        this.transaccion=null;
        
        try
        {
            proyectoDaoImpl daoProyecto = new proyectoDaoImpl();
            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaccion=session.beginTransaction();
            
            daoProyecto.modificarProyecto(this.session, this.proyecto);
            this.transaccion.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto Actualizado:", "Los cambios fueron realizados exitosamente"));
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
    
    public void cargarProyectoEditar(String codigoProyecto)
    {
        this.session=null;
        this.transaccion=null;
        
        try
        {
            proyectoDaoImpl daoProyecto = new proyectoDaoImpl();
            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaccion=session.beginTransaction();
            
            this.proyecto = daoProyecto.getByCodigoProyecto(this.session, codigoProyecto);
            
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
    /////////////////////////////////////
}
