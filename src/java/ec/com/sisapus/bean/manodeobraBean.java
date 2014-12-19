
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.manoobraDao;
import ec.com.sisapus.daoimpl.manoobraDaoImpl;
import ec.com.sisapus.modelo.Manoobra;
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
public class manodeobraBean {

    private Manoobra manoobra;
    private List<Manoobra> listamanoobra;
    
    
    public manodeobraBean() {
    }
    
    

    public Manoobra getManoobra() {
        if(this.manoobra == null){
        manoobra = new Manoobra();
        }
        return manoobra;
    }

    public void setManoobra(Manoobra manoobra) {
        this.manoobra = manoobra;
    }

    public List<Manoobra> getListamanoobra() {
        manoobraDao manobraDao = new manoobraDaoImpl();
        listamanoobra = manobraDao.buscarTodosManoObra();
        return listamanoobra;
    }

    public void setListamanoobra(List<Manoobra> listamanoobra) {
        this.listamanoobra = listamanoobra;
    }
    
      
    ////Crear Mano de Obra
    public void crearManoObra(ActionEvent actionEvent) {
        manoobraDao manobraDao = new manoobraDaoImpl();
        String msg;
        this.manoobra.setNombreManob(this.manoobra.getNombreManob());
        this.manoobra.setCostojrhManob(this.manoobra.getCostojrhManob());
        
        if (manobraDao.crearManoObra(this.manoobra)) {
            msg = "Mano de Obra creada correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO,msg , null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se creo la Mano de Obra";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    /*Actualizar Mano de Obra*/
    public void actualizarManoObra(ActionEvent actionEvent) {
        manoobraDao manobraDao = new manoobraDaoImpl();
        String msg;
        this.manoobra.setNombreManob(this.manoobra.getNombreManob());
        this.manoobra.setCostojrhManob(this.manoobra.getCostojrhManob());
        this.manoobra.setCategoriamanoobra(this.manoobra.getCategoriamanoobra());
        
        if (manobraDao.actualizarManoObra(this.manoobra)) {
            msg = "Mano de Obra modificada correctamente";
             FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se modifico la Mano de Obra";
             FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message2);
        }
      }
    
    /*Eliminar Mano de Obra*/
    public void eliminarManoObra(ActionEvent actionEvent) {
        manoobraDao manobraDao = new manoobraDaoImpl();
        String msg;
        if (manobraDao.eliminarManoObra(this.manoobra.getCodigoManob())) {
            msg = "Mano de Obra eliminada correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se elimino la Mano de Obra";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    
}
