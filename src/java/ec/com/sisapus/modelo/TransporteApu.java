package ec.com.sisapus.modelo;
// Generated 06/01/2015 02:17:13 PM by Hibernate Tools 3.2.1.GA



/**
 * TransporteApu generated by hbm2java
 */
public class TransporteApu  implements java.io.Serializable {


     private Integer codTranApu;
     private Transporte transporte;
     private String descTranApu;
     private String unidTranApu;
     private Integer cantTranApu;
     private Double tarifaTranApu;
     private Double costotTranApu;

    public TransporteApu() {
         this.codTranApu = 0;
        this.transporte = new Transporte();
    }

    public TransporteApu(Transporte transporte, String descTranApu, String unidTranApu, Integer cantTranApu, Double tarifaTranApu, Double costotTranApu) {
       this.transporte = transporte;
       this.descTranApu = descTranApu;
       this.unidTranApu = unidTranApu;
       this.cantTranApu = cantTranApu;
       this.tarifaTranApu = tarifaTranApu;
       this.costotTranApu = costotTranApu;
    }
   
    public Integer getCodTranApu() {
        return this.codTranApu;
    }
    
    public void setCodTranApu(Integer codTranApu) {
        this.codTranApu = codTranApu;
    }
    public Transporte getTransporte() {
        return this.transporte;
    }
    
    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }
    public String getDescTranApu() {
        return this.descTranApu;
    }
    
    public void setDescTranApu(String descTranApu) {
        this.descTranApu = descTranApu;
    }
    public String getUnidTranApu() {
        return this.unidTranApu;
    }
    
    public void setUnidTranApu(String unidTranApu) {
        this.unidTranApu = unidTranApu;
    }
    public Integer getCantTranApu() {
        return this.cantTranApu;
    }
    
    public void setCantTranApu(Integer cantTranApu) {
        this.cantTranApu = cantTranApu;
    }
    public Double getTarifaTranApu() {
        return this.tarifaTranApu;
    }
    
    public void setTarifaTranApu(Double tarifaTranApu) {
        this.tarifaTranApu = tarifaTranApu;
    }
    public Double getCostotTranApu() {
        return this.costotTranApu;
    }
    
    public void setCostotTranApu(Double costotTranApu) {
        this.costotTranApu = costotTranApu;
    }




}


