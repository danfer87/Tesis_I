
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.materialDao;
import ec.com.sisapus.daoimpl.materialDaoImpl;
import ec.com.sisapus.modelo.Material;
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
public class materialBean {

    private Material material;
    private List<Material> listaMateriales;
    
    
    public materialBean() {
    }

    public Material getMaterial() {
        if(this.material == null){
        material = new Material();
        }
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<Material> getListaMateriales() {
        materialDao materDao = new materialDaoImpl();
        listaMateriales = materDao.buscarTodosMater();
        return listaMateriales;
    }

    public void setListaMateriales(List<Material> listaMateriales) {
        this.listaMateriales = listaMateriales;
    }
    
    ////Crear Material
    public void crearMaterial(ActionEvent actionEvent) {
        materialDao materDao = new materialDaoImpl();
        String msg;
        this.material.setNombreMat(this.material.getNombreMat());
        this.material.setUnidMat(this.material.getUnidMat());
        this.material.setPrecunitMat(this.material.getPrecunitMat());
        
        if (materDao.crearMater(this.material)) {
            msg = "Material creado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO,msg , null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se creo el Material";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    /*Actualizar Material*/
    public void actualizarMaterial(ActionEvent actionEvent) {
        materialDao materDao = new materialDaoImpl();
        String msg;
        this.material.setNombreMat(this.material.getNombreMat());
        this.material.setUnidMat(this.material.getUnidMat());
        this.material.setPrecunitMat(this.material.getPrecunitMat());
        this.material.setCategoriamaterial(this.material.getCategoriamaterial());
        if (materDao.actualizarMater(this.material)) {
            msg = "Material modificado correctamente";
             FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se modifico el Material";
             FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message2);
        }
      }
    
    /*Eliminar Material*/
    public void eliminarMaterial(ActionEvent actionEvent) {
        materialDao materDao = new materialDaoImpl();
        String msg;
        if (materDao.eliminarMater(this.material.getCodigoMat())) {
            msg = "Material eliminado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se elimino el Material";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    
}
