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
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    private String nombre,apellido,sobrenombre, contrasenia,correo;
    //private boolean estado;
    
    private String nombreperfil;
    //private Date fechaReg, fechaMod;
    
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
    
    ////
      
    public String getNombre() {
        return nombre;
        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSobrenombre() {
        return sobrenombre;
    }

    public void setSobrenombre(String sobrenombre) {
        this.sobrenombre = sobrenombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    ////
      
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
    
    //////////////
    
     public void enviar_correo(String destinatario) {
        Transport t;
        String msg;
        String clave = "caexvjjqjbvlkmkm";
        java.util.Date hoy = new java.util.Date();
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(hoy);

        //int i = 0;

        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "klepermix@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            //  for (i = 0; i < ia.size(); i++) {
            //    System.out.println(ia.get(i));
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("klepermix@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject("Bienvenidos a SISAPU");
            message.setText("Fecha:" + fecha + "\n\nGracias por Registrarse sus datos de registro son:" + "\n \nusuario:" + sobrenombre + "\ncontraseña:" + contrasenia + "\n\n Saludos" + "\n\n Atte. SisApu");
            t = session.getTransport("smtp");
            // Transport t = session.getTransport("smtp");
            t.connect("klepermix@gmail.com", clave);
            t.sendMessage(message, message.getAllRecipients());
            //}
            // Cierre.o
            t.close();
            msg = "correo enviado exitosamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } catch (Exception e) {
            msg = "no se pudo enviar correo";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message2);
        }
        //return mensaje;
    }
     
     /////////////////////
     
     public void registUsuario(ActionEvent actionEvent) {
       String msg;
        usuarioDao usuarioregistrodao = new usuarioDaoImpl();

        if (this.contrasenia.equals(this.contraseniaRepita)) {

            try {
                usuarioregistrodao.registrarUsuario(nombre, apellido, sobrenombre, contrasenia, correo);
                
                enviar_correo(correo);

                msg = "Usuario registrado correctamente";
                FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message1);
                limpiar_campos();

            } catch (Exception e) {
                msg = "No se pudo registrar el usuario";
                FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message2);

            }

        }
        else {
            msg = "Las contraseñas no coinciden intente nuevamente";
            FacesMessage message3 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message3);
        }

    }

    public void limpiar_campos() {
        this.nombre = "";
        this.apellido = "";
        this.sobrenombre = "";
        this.contrasenia = "";
        this.correo = "";
        this.contraseniaRepita="";
    }

     /////////////////////
    
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
        
        if (usuarioDao.regisUsu(usuario)) {
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

    public String getNombreperfil() {
        return nombreperfil;
    }

    public void setNombreperfil(String nombreperfil) {
        this.nombreperfil = nombreperfil;
    }
}
