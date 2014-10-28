/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.usuarioDao;
import ec.com.sisapus.daoimpl.usuarioDaoImpl;
import ec.com.sisapus.modelo.Usuario;
import java.awt.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Edison
 */
@Named(value = "usuarioBean")
@ApplicationScoped

public class usuarioBean {

    private Usuario usuario;
    private List<Usuario> listaUsuarios;
    private String contraseniaRepita;
    
    public usuarioBean() {
    }

    public Usuario getUsuario() {
        if(this.usuario==null){
        usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        usuarioDao usuDao = new usuarioDaoImpl();
        listaUsuarios = usuDao.buscarTodosUsu();
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getContraseniaRepita() {
        return contraseniaRepita;
    }

    public void setContraseniaRepita(String contraseniaRepita) {
        this.contraseniaRepita = contraseniaRepita;
    }
      
    public void crearUsuario(ActionEvent actionEvent) {
        usuarioDao usuarioDao = new usuarioDaoImpl();
        String msg;
        this.usuario.setNombreUsu(this.usuario.getNombreUsu());
        this.usuario.setApellidoUsu(this.usuario.getApellidoUsu());
        this.usuario.setSobrenombreUsu(this.usuario.getSobrenombreUsu());
        this.usuario.setContraseniaUsu(this.usuario.getContraseniaUsu());
        this.usuario.setCorreoUsu(this.usuario.getCorreoUsu());
        this.usuario.setPerfil(this.usuario.getPerfil());
        this.usuario.setEstadoUsu(this.usuario.getEstadoUsu());
        //Registro Fecha Creacion
        Date hoy = new Date();
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(hoy);
        this.usuario.setFechregUsu(java.sql.Date.valueOf(fecha));
        
        if (usuarioDao.crearUsu(this.usuario)) {
            msg = "Usuario creado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO,msg , null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se creo el usuario";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    /*Actualizar Usuario*/

    public void actualizarUsuario(ActionEvent actionEvent) {
        usuarioDao usuarioDao = new usuarioDaoImpl();
        String msg;
        this.usuario.setNombreUsu(this.usuario.getNombreUsu());
        this.usuario.setApellidoUsu(this.usuario.getApellidoUsu());
        this.usuario.setSobrenombreUsu(this.usuario.getSobrenombreUsu());
        this.usuario.setContraseniaUsu(this.usuario.getContraseniaUsu());
        this.usuario.setCorreoUsu(this.usuario.getCorreoUsu());
        this.usuario.setEstadoUsu(this.usuario.getEstadoUsu());
        this.usuario.setPerfil(this.usuario.getPerfil());
        //Fecha Modificacion
        Date hoy = new Date();
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(hoy);
        this.usuario.setFechmodUsu(java.sql.Date.valueOf(fecha));
        if (usuarioDao.actualizarUsu(this.usuario)) {
            msg = "Usuario modificado correctamente";
             FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se modifico el usuario";
             FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
             FacesContext.getCurrentInstance().addMessage(null, message2);
        }
      }
    /*Eliminar Usuario*/

    public void eliminarUsuario(ActionEvent actionEvent) {
        usuarioDao usuarioDao = new usuarioDaoImpl();
        String msg;
        if (usuarioDao.eliminarUsu(this.usuario.getCodigoUsu())) {
            msg = "Usuario eliminado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se elimino el usuario";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    public void registarUsuario(ActionEvent actionEvent) {
        usuarioDao usuarioDao = new usuarioDaoImpl();
        String msg;
        this.usuario.setNombreUsu(this.usuario.getNombreUsu());
        this.usuario.setApellidoUsu(this.usuario.getApellidoUsu());
        this.usuario.setSobrenombreUsu(this.usuario.getSobrenombreUsu());
        this.usuario.setContraseniaUsu(this.usuario.getContraseniaUsu());
        this.usuario.setCorreoUsu(this.usuario.getCorreoUsu());
        //this.usuario.setCorreoUsu(this.usuario.getCorreoUsu());
        this.usuario.setPerfil(this.usuario.getPerfil());
        //this.usuario.setEstadoUsu(this.usuario.getEstadoUsu());
        
        //Registro Fecha Registro
        Date hoy = new Date();
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(hoy);
        this.usuario.setFechregUsu(java.sql.Date.valueOf(fecha));
        
        if (usuarioDao.regisUsu(this.usuario)) {
            msg = "Usuario Regitrado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO,msg , null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se logro registar al usuario";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        
    }
    
    //public String getLoggedUserName() {
      //  return ((usuarioBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(USER_KEY)).toString();
    //}
}
