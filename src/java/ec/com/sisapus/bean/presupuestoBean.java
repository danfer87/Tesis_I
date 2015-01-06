package ec.com.sisapus.bean;

import ec.com.sisapus.daoimpl.ApusDaoImpl;
import ec.com.sisapus.daoimpl.proyectoDaoImpl;
import ec.com.sisapus.daoimpl.rubroDaoImplInterface;
import ec.com.sisapus.modelo.Analisispreciounitario;
import ec.com.sisapus.modelo.Presupuesto;
import ec.com.sisapus.modelo.Proyecto;
import ec.com.sisapus.modelo.Rubro;
import ec.com.sisapus.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Edison
 */
public class presupuestoBean implements Serializable {
    Session session;
    Transaction transaction;
    //Variables del Rubro
    private Rubro rubros;
    private List<Rubro> listaRubros;
    //Variables del Presupuesto
    private Presupuesto presupuesto;
    private Double costoPresupuesto;
    private List<Presupuesto> listaPresupuestos;
    //Variables de Proyecto
    private Proyecto proyecto;
    private List<Proyecto> listaProyecto;
    private Integer codigoproyecto;
    private String propietarioproyecto;
    private String contratistaproyecto;
    private String obraproyecto;
    private String ubicacionproyecto;
    private Date fechaproyecto;
    private Double costoproyecto;
    //Variables del APU
    private Analisispreciounitario apu;
    private List<Analisispreciounitario> listaApus;
    
    
    public presupuestoBean() {
        this.rubros = new Rubro();
        this.listaRubros = new ArrayList<>();
        this.listaApus = new ArrayList<>();
        this.proyecto = new Proyecto();
        this.codigoproyecto = 0;
        this.propietarioproyecto = "";
        this.contratistaproyecto = "";
        this.obraproyecto = "";
        this.ubicacionproyecto = "";
        this.fechaproyecto = null;
        this.costoproyecto = 0.0;
    }
    
    ///// Getters and Setters
    
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

    public Rubro getRubros() {
        return rubros;
    }

    public void setRubros(Rubro rubros) {
        this.rubros = rubros;
    }

    public List<Rubro> getListaRubros() {
        return listaRubros;
    }

    public void setListaRubros(List<Rubro> listaRubros) {
        this.listaRubros = listaRubros;
    }

    public Double getCostoPresupuesto() {
        return costoPresupuesto;
    }

    public void setCostoPresupuesto(Double costoPresupuesto) {
        this.costoPresupuesto = costoPresupuesto;
    }
    
      
    //Getters y Setters de Proyecto
    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Integer getCodigoproyecto() {
        return codigoproyecto;
    }

    public void setCodigoproyecto(Integer codigoproyecto) {
        this.codigoproyecto = codigoproyecto;
    }

    public String getPropietarioproyecto() {
        return propietarioproyecto;
    }

    public void setPropietarioproyecto(String propietarioproyecto) {
        this.propietarioproyecto = propietarioproyecto;
    }

    public String getContratistaproyecto() {
        return contratistaproyecto;
    }

    public void setContratistaproyecto(String contratistaproyecto) {
        this.contratistaproyecto = contratistaproyecto;
    }

    public String getObraproyecto() {
        return obraproyecto;
    }

    public void setObraproyecto(String obraproyecto) {
        this.obraproyecto = obraproyecto;
    }

    public String getUbicacionproyecto() {
        return ubicacionproyecto;
    }

    public void setUbicacionproyecto(String ubicacionproyecto) {
        this.ubicacionproyecto = ubicacionproyecto;
    }

    public Date getFechaproyecto() {
        return fechaproyecto;
    }

    public void setFechaproyecto(Date fechaproyecto) {
        this.fechaproyecto = fechaproyecto;
    }

    public Double getCostoproyecto() {
        return costoproyecto;
    }

    public void setCostoproyecto(Double costoproyecto) {
        this.costoproyecto = costoproyecto;
    }
    
    
    ///// Getter y Setter APU
    public Analisispreciounitario getApu() {
        return apu;
    }

    public void setApu(Analisispreciounitario apu) {
        this.apu = apu;
    }

    public List<Analisispreciounitario> getListaApus() {
        return listaApus;
    }

    public void setListaApus(List<Analisispreciounitario> listaApus) {
        this.listaApus = listaApus;
    }
    
    
    
    
   
    ///Funcion para seleccionar un proyecto por su id
    
    public void seleccionarProyectoPorId(Integer idproyecto) {
        
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            proyectoDaoImpl daoproyecto = new proyectoDaoImpl();

            this.transaction = this.session.beginTransaction();

            this.proyecto = daoproyecto.obtenerProyectoPorId(session, idproyecto);

            this.setCodigoproyecto(this.proyecto.getCodigoProy());
            this.setContratistaproyecto(this.proyecto.getContratProy());
            this.setPropietarioproyecto(this.proyecto.getPropiepProy());
            this.setObraproyecto(this.proyecto.getObraProy());
            this.setUbicacionproyecto(this.proyecto.getUbicProy());
            this.setFechaproyecto(this.proyecto.getFechaProy());

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Proyecto Seleccionado"));

            RequestContext.getCurrentInstance().update("formPresupuesto:cabeceraPresupuesto");
            RequestContext.getCurrentInstance().update("formPresupuesto:mensajeGeneralPresupuesto");


        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }
    
    ///Funcion para agregar los apus al presupuesto
    public void agregarApus(Integer idApu) {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            ApusDaoImpl daoapu = new ApusDaoImpl();

            this.transaction = this.session.beginTransaction();
            this.apu = daoapu.obtenerApuPorId(session, idApu);
            this.listaApus.add(new Analisispreciounitario(null, null, null, null, null, null,this.apu.getDescApu(), this.apu.getUnidadApu(), this.apu.getCategoriaApu(), null, null, null, null, null, null, null, this.apu.getCostotApu(),null, null, null, null));
            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Apu del rubro agregado"));

            RequestContext.getCurrentInstance().update("formPresupuesto:tablaDetallePresupuesto");
            RequestContext.getCurrentInstance().update("formPresupuesto:mensajeGeneralPresupuesto");


        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }
    
    public void EliminarListaRubro(String nom) {
        try {
            int i = 0;

            for (Rubro item : this.listaRubros) {
                if (item.getNombreRubro().equals(nom)) {
                    this.listaRubros.remove(i);
                    break;
                }
                i++;
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Equipos y Herramientas retirado de la lista"));

            RequestContext.getCurrentInstance().update("frmPresupuesto:tablaDetallePresupuesto");
            RequestContext.getCurrentInstance().update("frmPresupuesto:panelFinalVenta");
            RequestContext.getCurrentInstance().update("frmPresupuesto:mensajeGeneral");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
    
    public void calcularCostosPresupuesto() {
        try {
            Double totalCostoPresupuesto = new Double("0.00");

            for (Presupuesto item : this.listaPresupuestos) {
                Double costototalrubro = item.getCantidadPres() * (new Double(item.getPunitPres()));
                item.setPtotPres(costototalrubro);
                
                totalCostoPresupuesto = totalCostoPresupuesto + costototalrubro;
            }

            this.setCostoPresupuesto(totalCostoPresupuesto);

            RequestContext.getCurrentInstance().update("frmPresupuesto:tablaDetallePresupuesto");
            RequestContext.getCurrentInstance().update("frmPresupuesto:panelFinalVenta");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
}
