package ec.com.sisapus.modelo;
// Generated 06/01/2015 02:51:14 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Escenarioapu generated by hbm2java
 */
public class Escenarioapu  implements java.io.Serializable {


     private Integer codigoEscenario;
     private Integer codigoApu;
     private String nombreEscenario;
     private Set analisispreciounitarios = new HashSet(0);

    public Escenarioapu() {
        this.codigoEscenario=0;
    }

    public Escenarioapu(Integer codigoApu, String nombreEscenario, Set analisispreciounitarios) {
       this.codigoApu = codigoApu;
       this.nombreEscenario = nombreEscenario;
       this.analisispreciounitarios = analisispreciounitarios;
    }
   
    public Integer getCodigoEscenario() {
        return this.codigoEscenario;
    }
    
    public void setCodigoEscenario(Integer codigoEscenario) {
        this.codigoEscenario = codigoEscenario;
    }
    public Integer getCodigoApu() {
        return this.codigoApu;
    }
    
    public void setCodigoApu(Integer codigoApu) {
        this.codigoApu = codigoApu;
    }
    public String getNombreEscenario() {
        return this.nombreEscenario;
    }
    
    public void setNombreEscenario(String nombreEscenario) {
        this.nombreEscenario = nombreEscenario;
    }
    public Set getAnalisispreciounitarios() {
        return this.analisispreciounitarios;
    }
    
    public void setAnalisispreciounitarios(Set analisispreciounitarios) {
        this.analisispreciounitarios = analisispreciounitarios;
    }




}


