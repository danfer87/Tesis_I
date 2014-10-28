
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.transporteDao;
import ec.com.sisapus.daoimpl.transporteDaoImpl;
import ec.com.sisapus.modelo.Transporte;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Edison
 */
@ManagedBean
@ViewScoped
public class transportesBean {

    private Transporte transporte;
    private List<Transporte> listaTransporte;
    
    
    public transportesBean() {
    }

    public Transporte getTransporte() {
         if(this.transporte == null){
        transporte = new Transporte();
        }
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public List<Transporte> getListaTransporte() {
        transporteDao transDao = new transporteDaoImpl();
        listaTransporte = transDao.buscarTodosTransportes();
        return listaTransporte;
    }

    public void setListaTransporte(List<Transporte> listaTransporte) {
        this.listaTransporte = listaTransporte;
    }
    
    
    ////Crear Transporte
    public void crearTransporte(ActionEvent actionEvent) {
        transporteDao transDao = new transporteDaoImpl();
        String msg;
        this.transporte.setNombreTransp(this.transporte.getNombreTransp());
        this.transporte.setTarifaTransp(this.transporte.getTarifaTransp());
                
        if (transDao.crearTransporte(this.transporte)) {
            msg = "Transporte creado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO,msg , null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se creo el Transporte";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    /*Actualizar Transporte*/
    public void actualizarTransporte(ActionEvent actionEvent) {
        transporteDao transDao = new transporteDaoImpl();
        String msg;
        this.transporte.setNombreTransp(this.transporte.getNombreTransp());
        this.transporte.setTarifaTransp(this.transporte.getTarifaTransp());
        this.transporte.setCategoriatransporte(this.transporte.getCategoriatransporte());
        
        if (transDao.actualizarTransporte(this.transporte)) {
            msg = "Transporte modificado correctamente";
             FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se modifico el Transporte";
             FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message2);
        }
      }
    
    /*Eliminar Transporte*/
    public void eliminarTransporte(ActionEvent actionEvent) {
        transporteDao transDao = new transporteDaoImpl();
        String msg;
        if (transDao.eliminarTransporte(this.transporte.getCodigoTransp())) {
            msg = "Transporte eliminado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se elimino el Transporte";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    
}
