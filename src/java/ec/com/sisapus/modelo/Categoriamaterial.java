package ec.com.sisapus.modelo;
// Generated 06/01/2015 02:17:13 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Categoriamaterial generated by hbm2java
 */
public class Categoriamaterial  implements java.io.Serializable {


     private Integer codCatMat;
     private String nombCatMat;
     private Set materials = new HashSet(0);

    public Categoriamaterial() {

     this.codCatMat = 0;
    }

    public Categoriamaterial(String nombCatMat, Set materials) {
       this.nombCatMat = nombCatMat;
       this.materials = materials;
    }
   
    public Integer getCodCatMat() {
        return this.codCatMat;
    }
    
    public void setCodCatMat(Integer codCatMat) {
        this.codCatMat = codCatMat;
    }
    public String getNombCatMat() {
        return this.nombCatMat;
    }
    
    public void setNombCatMat(String nombCatMat) {
        this.nombCatMat = nombCatMat;
    }
    public Set getMaterials() {
        return this.materials;
    }
    
    public void setMaterials(Set materials) {
        this.materials = materials;
    }




}


