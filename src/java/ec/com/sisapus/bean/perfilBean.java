/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.perfilDao;
import ec.com.sisapus.daoimpl.perfilDaoImpl;
import ec.com.sisapus.modelo.Perfil;
import java.awt.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Edison
 */
@Named(value = "perfilBean")
@SessionScoped
public class perfilBean implements Serializable {

    /**
     * Creates a new instance of perfilBean
     */
    private Perfil perfil;
    private List<Perfil> perfiles;
    private List<SelectItem> listaPerfiles;

    public perfilBean() {
    }

    public Perfil getPerfil() {
        if (this.perfil == null) {
            this.perfil = new Perfil();
        }
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Perfil> getPerfiles() {
        perfilDao pDao = new perfilDaoImpl();
        perfiles = pDao.buscarTodosPerfiles();
        return perfiles;
    }

    public void prepararPerfil(Integer id) {
        perfilDao perfilDao = new perfilDaoImpl();
        perfil = perfilDao.buscarPerfilPorId(id);
    }

    public List<SelectItem> getListaPerfiles() {
        this.listaPerfiles = new ArrayList<SelectItem>();
        ///Crear Instancia de objeto para RolDaoImpl
        perfilDao perfDao = new perfilDaoImpl();
        List<Perfil> perfiles = perfDao.selectItems();
        for (Perfil perf : perfiles) {
            SelectItem selectItem = new SelectItem(perf.getCodigoPerf(),perf.getDescripPerf());
            this.listaPerfiles.add(selectItem);
        }
        return listaPerfiles;
    }

    public void setListaPerfiles(List<SelectItem> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }
    
    ////Crear Perfil
    public void crearPerfil(ActionEvent actionEvent) {
        perfilDao perfDao = new perfilDaoImpl();
        String msg;
        this.perfil.setDescripPerf(this.perfil.getDescripPerf());
        
        if (perfDao.crearPerfil(this.perfil)) {
            msg = "Perfil creado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO,msg , null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se creo el Perfil";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    /*Eliminar Material*/
    public void eliminarPerfil(ActionEvent actionEvent) {
        perfilDao perfDao = new perfilDaoImpl();
        String msg;
        if (perfDao.eliminarPerfil(this.perfil.getCodigoPerf())) {
            msg = "Perfil eliminado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se elimino el Perfil";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    /*Actualizar Perfil*/
    public void actualizarPerfil(ActionEvent actionEvent) {
        perfilDao perfDao = new perfilDaoImpl();
        String msg;
        //this.perfil.setDescripPerf(this.perfil.getDescripPerf());
        if (perfDao.actualizarPerfil(this.perfil)) {
            msg = "Perfil modificado correctamente";
             FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se modifico el Perfil";
             FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message2);
        }
      }
    

    ////////Actualizacion del perfil
    public void asignarPermisosPerfil() {
        ////Proyecto//////
        if (perfil.getModProyectos() == true) {
            perfil.setModProyecto(true);
        } else if (perfil.getModProyectos() == false) {
            perfil.setModProyecto(false);
        }
        //////Recursos//////
        if (perfil.getModRubros() == true || perfil.getModMateriales() == true || perfil.getModEquiherr() == true || perfil.getModManoobra() == true || perfil.getModTransporte() == true) {
            perfil.setModRecursos(true);
        } else if (perfil.getModRubros() == false && perfil.getModMateriales() == false && perfil.getModEquiherr() == false && perfil.getModManoobra() == false && perfil.getModTransporte() == false) {
            perfil.setModRecursos(false);
        }
        //////Analisis de Costos////////
        if (perfil.getModApu() == true || perfil.getModPresup() == true || perfil.getModCronograma() || perfil.getModReajprec()) {
            perfil.setModAnalcosto(true);
        } else if (perfil.getModApu() == false && perfil.getModPresup() == false && perfil.getModCronograma() == false && perfil.getModReajprec() == false) {
            perfil.setModAnalcosto(false);
        }
        ////////Seguridad//////
        if (perfil.getModUsuario() == true || perfil.getModPerfil() == true || perfil.getModRegusu() == true) {
            perfil.setModSeguridad(true);
        } else if (perfil.getModUsuario() == false && perfil.getModPerfil() == false && perfil.getModRegusu() == false) {
            perfil.setModSeguridad(false);
        }


        perfilDao perfilDao = new perfilDaoImpl();
        perfilDao.actualizarPermisoPerfil(perfil);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Permisos del Perfil Actualizados Correctamente", ""));
        this.perfil = new Perfil();
    }
}
