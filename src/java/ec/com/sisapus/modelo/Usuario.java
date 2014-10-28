package ec.com.sisapus.modelo;
// Generated 24/03/2014 10:48:04 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private Integer codigoUsu;
     private Perfil perfil;
     private String nombreUsu;
     private String apellidoUsu;
     private String sobrenombreUsu;
     private String contraseniaUsu;
     private String correoUsu;
     private Boolean estadoUsu;
     private Date fechregUsu;
     private Date fechmodUsu;
     private Set<Proyecto> proyectos = new HashSet<Proyecto>(0);

    public Usuario() {
        this.codigoUsu = 0;
        this.perfil = new Perfil();
    }

    public Usuario(Perfil perfil, String nombreUsu, String apellidoUsu, String sobrenombreUsu, String contraseniaUsu, String correoUsu, Boolean estadoUsu, Date fechregUsu, Date fechmodUsu, Set<Proyecto> proyectos) {
       this.perfil = perfil;
       this.nombreUsu = nombreUsu;
       this.apellidoUsu = apellidoUsu;
       this.sobrenombreUsu = sobrenombreUsu;
       this.contraseniaUsu = contraseniaUsu;
       this.correoUsu = correoUsu;
       this.estadoUsu = estadoUsu;
       this.fechregUsu = fechregUsu;
       this.fechmodUsu = fechmodUsu;
       this.proyectos = proyectos;
    }
   
    public Integer getCodigoUsu() {
        return this.codigoUsu;
    }
    
    public void setCodigoUsu(Integer codigoUsu) {
        this.codigoUsu = codigoUsu;
    }
    public Perfil getPerfil() {
        return this.perfil;
    }
    
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    public String getNombreUsu() {
        return this.nombreUsu;
    }
    
    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }
    public String getApellidoUsu() {
        return this.apellidoUsu;
    }
    
    public void setApellidoUsu(String apellidoUsu) {
        this.apellidoUsu = apellidoUsu;
    }
    public String getSobrenombreUsu() {
        return this.sobrenombreUsu;
    }
    
    public void setSobrenombreUsu(String sobrenombreUsu) {
        this.sobrenombreUsu = sobrenombreUsu;
    }
    public String getContraseniaUsu() {
        return this.contraseniaUsu;
    }
    
    public void setContraseniaUsu(String contraseniaUsu) {
        this.contraseniaUsu = contraseniaUsu;
    }
    public String getCorreoUsu() {
        return this.correoUsu;
    }
    
    public void setCorreoUsu(String correoUsu) {
        this.correoUsu = correoUsu;
    }
    public Boolean getEstadoUsu() {
        return this.estadoUsu;
    }
    
    public void setEstadoUsu(Boolean estadoUsu) {
        this.estadoUsu = estadoUsu;
    }
    public Date getFechregUsu() {
        return this.fechregUsu;
    }
    
    public void setFechregUsu(Date fechregUsu) {
        this.fechregUsu = fechregUsu;
    }
    public Date getFechmodUsu() {
        return this.fechmodUsu;
    }
    
    public void setFechmodUsu(Date fechmodUsu) {
        this.fechmodUsu = fechmodUsu;
    }
    public Set<Proyecto> getProyectos() {
        return this.proyectos;
    }
    
    public void setProyectos(Set<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }




}

