/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.rubroDao;
import ec.com.sisapus.daoimpl.rubroDaoImpl;
import ec.com.sisapus.daoimpl.rubroDaoImplInterface;
import ec.com.sisapus.modelo.Rubro;
import ec.com.sisapus.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.mapping.Map;
import org.primefaces.component.inputtext.InputText;
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
    private List<SelectItem> listaRubross;
    //setear 
    private int codigorubro;
    private String unidadrubro;
    private String descripcrubro;
    private String categString;
    Transaction transaction;

    public rubroBeanVista() {
        this.rubro = new Rubro();

        this.codigorubro = 0;
        this.descripcrubro = "";
        this.unidadrubro = "";
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
    public void crearRubros() {
        this.session = null;
        this.transaccion = null;

        try {
            rubroDaoImplInterface daoRubro = new rubroDaoImplInterface();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();
            if (daoRubro.crearRubro(this.session, this.rubro)) {
                this.transaccion.commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Rubro creado correctamente"));
                this.rubro = new Rubro();
            } else {
                String msg = "No se modifico el Rubro";
                FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message2);
            }
        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //Modificar Rubro
    public void modificarRubros() {
        this.session = null;
        this.transaccion = null;

        try {
            rubroDaoImplInterface daoRubro = new rubroDaoImplInterface();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

            /*this.rubro.setNombreRubro(this.rubro.getNombreRubro());
             this.rubro.setDetalleRubro(this.rubro.getDetalleRubro());
             this.rubro.setUnidadRubro(this.rubro.getUnidadRubro());
             */
            daoRubro.modificarRubro(this.session, this.rubro);

            this.transaccion.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Los cambios fueron guardados correctamente"));
        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void cargarRubroEditar(String codigoRubro) {
        this.session = null;
        this.transaccion = null;

        try {
            rubroDaoImplInterface daoRubro = new rubroDaoImplInterface();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

            this.rubro = daoRubro.getByCodigoRubro(this.session, codigoRubro);

            RequestContext.getCurrentInstance().update("frmEditarRubro:panelEditarRubro");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarRubro').show()");

            this.transaccion.commit();
        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void eliminarRubros() {
        this.session = null;
        this.transaccion = null;

        try {
            rubroDaoImplInterface daoRubro = new rubroDaoImplInterface();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();
            if (daoRubro.eliminarRubro(this.session, this.rubro.getCodigoRubro())) {
                this.transaccion.commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Rubro eliminado correctamente"));
                //this.rubro=new Rubro();
            } else {
                String msg = "No se elimino el Rubro";
                FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message2);
            }
        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public List<Rubro> getAll() {
        this.session = null;
        this.transaccion = null;

        try {
            rubroDaoImplInterface daoRubro = new rubroDaoImplInterface();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            this.listaRubro = daoRubro.listarTodosRubros(this.session);
            this.transaccion.commit();
            return this.listaRubro;
        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //Lista de rubros para cargar en el combobox
    public List<SelectItem> getListaRubross() throws Exception {
        this.listaRubross = new ArrayList<SelectItem>();
        ///Crear Instancia de objeto para RolDaoImpl
        rubroDaoImpl rubrosdao = new rubroDaoImpl();
        List<Rubro> rubros = rubrosdao.BuscarRubro();
        for (Rubro rub : rubros) {
            SelectItem selectItem = new SelectItem(rub.getCodigoRubro(), rub.getNombreRubro());
            this.listaRubross.add(selectItem);
        }
        return listaRubross;
    }

    /*public void cargartextosRubros() throws Exception
           
     {
     this.listaRubross = new ArrayList<>();
     rubroDao rubrodao = new rubroDaoImpl();
     this.session=HibernateUtil.getSessionFactory().openSession();
     this.transaction=this.session.beginTransaction();
     rubro = rubrodao.getByIdRubro(session,this.rubro.getCodigoRubro());
     for (Rubro rub : listaRubro) {
     SelectItem selectItem = new SelectItem(rub.getCodigoRubro(),rub.getNombreRubro());
          
     this.codigorubro=rub.getCodigoRubro();
     this.descripcrubro=rub.getNombreRubro();
     this.unidadrubro=rub.getUnidadRubro();
          
    
       
     }
     this.transaction.commit();
     }*/
    public int getCodigorubro() {
        return codigorubro;
    }

    public void setCodigorubro(int codigorubro) {
        this.codigorubro = codigorubro;
    }

    public String getUnidadrubro() {
        return unidadrubro;
    }

    public void setUnidadrubro(String unidadrubro) {
        this.unidadrubro = unidadrubro;
    }

    public String getDescripcrubro() {
        return descripcrubro;
    }

    public void setDescripcrubro(String descripcrubro) {
        this.descripcrubro = descripcrubro;
    }

    public String getCategString() {
        return categString;
    }

    public void setCategString(String categString) {
        this.categString = categString;
    }

    public void setListaRubross(List<SelectItem> listaRubross) {

        this.listaRubross = listaRubross;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void valueChangeListener(ValueChangeEvent event) {
        //System.out.println("Cliente: " + event.getNewValue());  
        /*  PhaseId phaseId = event.getPhaseId();  
         //pega o novo valor  
         Integer newValue = (Integer) event.getNewValue();  
         if (phaseId.equals(PhaseId.ANY_PHASE)) {  
         //agenda o evento para a fase que nos interessa  
         event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);  
         event.queue();  
         } else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)) {  
         //aqui vai o método de antes (um pouco mudado)  
              
         this.rubro.setNombreRubro(newValue);  
         // this.rubro.setDetalleRubro(newValue);
         descripcrubro = String.valueOf(newValue);*/
    }
}
