package ec.com.sisapus.modelo;
// Generated 06/01/2015 02:51:14 PM by Hibernate Tools 3.2.1.GA



/**
 * ControlEjecutadoPresupuestado generated by hbm2java
 */
public class ControlEjecutadoPresupuestado  implements java.io.Serializable {


     private Integer codigoEjec;
     private Proyecto proyecto;
     private String descripcionEjec;
     private Double PUnitEjec;
     private Double PTotalEjecut;
     private Double PTotalEjec;

    public ControlEjecutadoPresupuestado() {
    }

    public ControlEjecutadoPresupuestado(Proyecto proyecto, String descripcionEjec, Double PUnitEjec, Double PTotalEjecut, Double PTotalEjec) {
       this.proyecto = proyecto;
       this.descripcionEjec = descripcionEjec;
       this.PUnitEjec = PUnitEjec;
       this.PTotalEjecut = PTotalEjecut;
       this.PTotalEjec = PTotalEjec;
    }
   
    public Integer getCodigoEjec() {
        return this.codigoEjec;
    }
    
    public void setCodigoEjec(Integer codigoEjec) {
        this.codigoEjec = codigoEjec;
    }
    public Proyecto getProyecto() {
        return this.proyecto;
    }
    
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
    public String getDescripcionEjec() {
        return this.descripcionEjec;
    }
    
    public void setDescripcionEjec(String descripcionEjec) {
        this.descripcionEjec = descripcionEjec;
    }
    public Double getPUnitEjec() {
        return this.PUnitEjec;
    }
    
    public void setPUnitEjec(Double PUnitEjec) {
        this.PUnitEjec = PUnitEjec;
    }
    public Double getPTotalEjecut() {
        return this.PTotalEjecut;
    }
    
    public void setPTotalEjecut(Double PTotalEjecut) {
        this.PTotalEjecut = PTotalEjecut;
    }
    public Double getPTotalEjec() {
        return this.PTotalEjec;
    }
    
    public void setPTotalEjec(Double PTotalEjec) {
        this.PTotalEjec = PTotalEjec;
    }




}


