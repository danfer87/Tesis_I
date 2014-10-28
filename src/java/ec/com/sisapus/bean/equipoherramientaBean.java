
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.equipoherrDao;
import ec.com.sisapus.daoimpl.equipoherrDaoImpl;
import ec.com.sisapus.modelo.Equipoherramienta;
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
public class equipoherramientaBean {

    private Equipoherramienta equipoherramienta;
    private List<Equipoherramienta> listaEquipoHerramienta;
    
    
    public equipoherramientaBean() {
    }

    public Equipoherramienta getEquipoherramienta() {
        if(this.equipoherramienta == null){
        equipoherramienta = new Equipoherramienta();
        }
        return equipoherramienta;
    }

    public void setEquipoherramienta(Equipoherramienta equipoherramienta) {
        this.equipoherramienta = equipoherramienta;
    }

    public List<Equipoherramienta> getListaEquipoHerramienta() {
        equipoherrDao eqherrDao = new equipoherrDaoImpl();
        listaEquipoHerramienta = eqherrDao.buscarTodosEquipHerr();
        return listaEquipoHerramienta;
    }

    public void setListaEquipoHerramienta(List<Equipoherramienta> listaEquipoHerramienta) {
        this.listaEquipoHerramienta = listaEquipoHerramienta;
    }
    
    

    ////Crear Equipo/Herramienta
    public void crearEquipoHerram(ActionEvent actionEvent) {
        equipoherrDao eqherrDao = new equipoherrDaoImpl();
        String msg;
        this.equipoherramienta.setNombreEqherr(this.equipoherramienta.getNombreEqherr());
        this.equipoherramienta.setCostohoraEqherr(this.equipoherramienta.getCostohoraEqherr());
        
        if (eqherrDao.crearEquipoHerr(this.equipoherramienta)) {
            msg = "Equipo/Herramienta creado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO,msg , null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se creo el equipo Equipo/Herramienta";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    /*Actualizar Equipo/Herramienta*/
    public void actualizarEquipoHerram(ActionEvent actionEvent) {
        equipoherrDao eqherrDao = new equipoherrDaoImpl();
        String msg;
        this.equipoherramienta.setNombreEqherr(this.equipoherramienta.getNombreEqherr());
        this.equipoherramienta.setCostohoraEqherr(this.equipoherramienta.getCostohoraEqherr());
        this.equipoherramienta.setCategoriaequipoherramienta(this.equipoherramienta.getCategoriaequipoherramienta());
        if (eqherrDao.actualizarEquipoHerr(this.equipoherramienta)) {
            msg = "Equipo/Herramienta modificado correctamente";
             FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se modifico el Equipo/Herramienta";
             FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message2);
        }
      }
    
    /*Eliminar Equipo/Herramienta*/
    public void eliminarEquipoHerram(ActionEvent actionEvent) {
        equipoherrDao eqherrDao = new equipoherrDaoImpl();
        String msg;
        if (eqherrDao.eliminarEquipoHerr(this.equipoherramienta.getCodigoEqherr())) {
            msg = "Equipo/Herramienta eliminado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se elimino el Equipo/Herramienta";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    
}
