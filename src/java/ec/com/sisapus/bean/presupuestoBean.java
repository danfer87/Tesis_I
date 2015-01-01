package ec.com.sisapus.bean;

import ec.com.sisapus.daoimpl.rubroDaoImplInterface;
import ec.com.sisapus.modelo.Presupuesto;
import ec.com.sisapus.modelo.Rubro;
import ec.com.sisapus.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
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
    private Rubro rubros;
    private List<Rubro> listaRubros;
    private Double costoPresupuesto;
    private Presupuesto presupuesto;
    private List<Presupuesto> listaPresupuestos;
    
    public presupuestoBean() {
        this.rubros = new Rubro();
        this.listaRubros = new ArrayList<>();
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
    
    ////
    
    public void agregarListaRubros(Integer idrubros) {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            rubroDaoImplInterface daorubro = new rubroDaoImplInterface();
            this.transaction = this.session.beginTransaction();
            this.rubros = daorubro.getByIdRubro(session, idrubros);
            this.listaRubros.add(new Rubro(null, this.rubros.getNombreRubro(), null, this.rubros.getUnidadRubro(), null, null));
            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Rubro agregado"));
            RequestContext.getCurrentInstance().update("frmPresupuesto:tablaDetallePresupuesto");
            RequestContext.getCurrentInstance().update("frmPresupuesto:mensajeGeneral");
            
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
