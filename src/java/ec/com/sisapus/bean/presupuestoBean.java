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
import java.math.BigDecimal;
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
    
    //Variables del Presupuesto
    private Presupuesto presupuesto;
    private Double precioTotApuRubro;
    private Double porcentajeiva;
    private Double precioTiva;
    private Double subtotalPres;
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
        
        this.listaApus = new ArrayList<>();
        this.listaPresupuestos = new ArrayList<>();
        this.presupuesto = new Presupuesto();
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
//////
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
    
    ///Getter y Setter de Presupuesto
    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Double getPrecioTotApuRubro() {
        return precioTotApuRubro;
    }

    public void setPrecioTotApuRubro(Double precioTotApuRubro) {
        this.precioTotApuRubro = precioTotApuRubro;
    }
    
    
    public List<Presupuesto> getListaPresupuestos() {
        return listaPresupuestos;
    }

    public void setListaPresupuestos(List<Presupuesto> listaPresupuestos) {
        this.listaPresupuestos = listaPresupuestos;
    }

    public Double getPorcentajeiva() {
        return porcentajeiva;
    }

    public void setPorcentajeiva(Double porcentajeiva) {
        this.porcentajeiva = porcentajeiva;
    }

    public Double getPrecioTiva() {
        return precioTiva;
    }

    public void setPrecioTiva(Double precioTiva) {
        this.precioTiva = precioTiva;
    }
    
    public Double getSubtotalPres() {
        return subtotalPres;
    }

    public void setSubtotalPres(Double subtotalPres) {
        this.subtotalPres = subtotalPres;
    }
    
   
    ///Funcion para agregar un proyecto al presupuesto
    
    public void agregarProyectoPresup(Integer idproyecto) {
        
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

            RequestContext.getCurrentInstance().update("frmPresupuesto:cabeceraPresupuesto");
            RequestContext.getCurrentInstance().update("frmPresupuesto:mensajeGeneralPresupuesto");

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
            
          //  this.listaPresupuestos.add(new Presupuesto(null, null, null, this.apu.getDescApu(), this.apu.getUnidadApu(),0, this.apu.getCostotApu(), new Double("0.00"), null, null, null));
            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Apu del rubro agregado"));

            RequestContext.getCurrentInstance().update("frmPresupuesto:tablaDetallePresupuesto");
            RequestContext.getCurrentInstance().update("frmPresupuesto:mensajeGeneralPresupuesto");


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
    
    public void EliminarApuLista(String nombrePres) {
        try {
            int i = 0;

            for (Presupuesto presup : this.listaPresupuestos) {
                if (presup.getDescripPres().equals(nombrePres)) {
                    this.listaPresupuestos.remove(i);
                    break;
                }
                i++;
            }
            Double subtotalPresup = new Double("0.00");
            Double ivaPres = new Double("0.00");
            Double valorTotalPres = new Double("0.00");
            
            //Presupuesto pres = new Presupuesto();

            for (Presupuesto presup : this.listaPresupuestos) 
            {
                Double costototalapurubro = (new Double(presup.getCantidadPres())) * (new Double(presup.getPunitPres()));
                presup.setPtotPres(costototalapurubro);
                subtotalPresup = subtotalPresup + costototalapurubro;
            }
            this.setPrecioTotApuRubro(subtotalPresup);
            ivaPres = (subtotalPresup * (this.porcentajeiva/100));
            this.setPrecioTiva(ivaPres);
            valorTotalPres=subtotalPresup+ivaPres;
            this.setCostoPresupuesto(valorTotalPres);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Rubro retirado de la lista"));

            RequestContext.getCurrentInstance().update("frmPresupuesto:tablaDetallePresupuesto");
            RequestContext.getCurrentInstance().update("frmPresupuesto:panelFinalPres");
            RequestContext.getCurrentInstance().update("frmPresupuesto:panelPresupuestar");
            RequestContext.getCurrentInstance().update("frmPresupuesto:mensajeGeneralPresupuesto");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
    
    public void calcularCostosPresupuesto() {
        
        try {
            
            Double subtotalPresup = new Double("0.00");
            Double ivaPres = new Double("0.00");
            Double valorTotalPres = new Double("0.00");
            
            Presupuesto pres = new Presupuesto();

            for (Presupuesto presup : this.listaPresupuestos) 
            {
                Double costototalapurubro = (new Double(presup.getCantidadPres())) * (new Double(presup.getPunitPres()));
                presup.setPtotPres(costototalapurubro);
                subtotalPresup = subtotalPresup + costototalapurubro;
            }
            this.setPrecioTotApuRubro(subtotalPresup);
            ivaPres = (subtotalPresup * (this.porcentajeiva/100));
            this.setPrecioTiva(ivaPres);
            valorTotalPres=subtotalPresup+ivaPres;
            this.setCostoPresupuesto(valorTotalPres);
            ////
            presupuesto.setSubtPres(subtotalPresup);
            presupuesto.setIvaPres(ivaPres);
            presupuesto.setGastotPres(valorTotalPres);
            //pres.setSubtPres(subtotalPresup);
            //pres.setIvaPres(ivaPres);
            //pres.setGastotPres(valorTotalPres);
            
            RequestContext.getCurrentInstance().update("frmPresupuesto:tablaDetallePresupuesto");
            RequestContext.getCurrentInstance().update("frmPresupuesto:panelFinalPres");
            RequestContext.getCurrentInstance().update("frmPresupuesto:panelPresupuestar");
            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
    
    /*public void guardarPresupuesto() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            ApusDaoImpl apusdao = new ApusDaoImpl();
            this.transaction = this.session.beginTransaction();
            this.apu = apusdao.obtenerUltimoRegistroApu(session);

            for (Analisispreciounitario item : this.listapus) {
                item.setEquipherrApu(this.equipapus);
                apusdao.insertarAPU(this.session, item);
            }

            this.transaction.commit();
            this.listapus = new ArrayList<>();
            this.equipapus = new EquipherrApu();
            // this.precioTotaltransporte=0.0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Precio Unitario guardado correctamente"));
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
    }*/
}
