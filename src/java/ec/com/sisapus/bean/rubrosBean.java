
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.rubroDao;
import ec.com.sisapus.daoimpl.rubroDaoImpl;
import ec.com.sisapus.modelo.Rubro;
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
public class rubrosBean {

    private Rubro rubro;
    private List<Rubro> listaRubros;
    
    
    public rubrosBean() {
    }

    public Rubro getRubro() {
        if(this.rubro == null){
        rubro = new Rubro();
        }
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public List<Rubro> getListaRubros() {
        rubroDao rubroDao = new rubroDaoImpl();
        listaRubros = rubroDao.buscarTodosRubros();
        return listaRubros;
    }

    public void setListaRubros(List<Rubro> listaRubros) {
        this.listaRubros = listaRubros;
    }
    
    
    ////Crear Rubro
    public void crearRubro(ActionEvent actionEvent) {
        rubroDao rubroDao = new rubroDaoImpl();
        String msg;
        this.rubro.setNombreRubro(this.rubro.getNombreRubro());
        this.rubro.setDetalleRubro(this.rubro.getDetalleRubro());
        this.rubro.setUnidadRubro(this.rubro.getUnidadRubro());
        
        if (rubroDao.crearRubro(this.rubro)) {
            msg = "Rubro creado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO,msg , null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se creo el Rubro";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    /*Actualizar Rubro*/
    public void actualizarRubro(ActionEvent actionEvent) {
        rubroDao rubroDao = new rubroDaoImpl();
        String msg;
        this.rubro.setNombreRubro(this.rubro.getNombreRubro());
        this.rubro.setDetalleRubro(this.rubro.getDetalleRubro());
        this.rubro.setUnidadRubro(this.rubro.getUnidadRubro());
        
        if (rubroDao.actualizarRubro(this.rubro)) {
            msg = "Rubro modificado correctamente";
             FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se modifico el Rubro";
             FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message2);
        }
      }
    
    /*Eliminar Rubro*/
    public void eliminarRubro(ActionEvent actionEvent) {
        rubroDao rubroDao = new rubroDaoImpl();
        String msg;
        if (rubroDao.eliminarRubro(this.rubro.getCodigoRubro())) {
            msg = "Rubro eliminado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se elimino el Rubro";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    
}
