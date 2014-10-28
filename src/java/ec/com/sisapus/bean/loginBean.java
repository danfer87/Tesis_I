/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.usuarioDao;
import ec.com.sisapus.daoimpl.usuarioDaoImpl;
import ec.com.sisapus.modelo.Usuario;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Edison
 */
@Named(value = "loginBean")
@SessionScoped

public class loginBean implements Serializable{
    
    private Usuario usuario;
    private String usuarioSesion;

    /**
     * Creates a new instance of loginBean
     */
    public loginBean() {
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

    public String getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(String usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }
    
    ///Login en el Sistema   
    public void login(ActionEvent actionEvent) {  
        RequestContext  context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean loggedIn = false;  
        
        usuarioDao usuarioDao= new usuarioDaoImpl();
        usuario = usuarioDao.buscarPorUsuario(usuario);
          
        if(usuario != null) {  
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getSobrenombreUsu());
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", usuario.getSobrenombreUsu());
            usuarioSesion=usuario.getSobrenombreUsu();
        } else {  
            loggedIn = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario o Contraseña Incorrectos");  
            if (this.usuario == null) {
                this.usuario = new Usuario();
            }
        }  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("loggedIn", loggedIn);  
    }  
    
    
    ///Cerrar Sesion en el Sistema
    public void cerrarSesion() {
        String ruta = "/sisapus/login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
        if(sesion != null){
        sesion.invalidate();
        }
        context.addCallbackParam("loggetOut", true);
        context.addCallbackParam("ruta", ruta);
    }
}
