package ec.com.sisapus.modelo;
// Generated 24/03/2014 10:48:04 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Categoriamanoobra generated by hbm2java
 */
public class Categoriamanoobra  implements java.io.Serializable {


     private Integer codCatManob;
     private String nombCatManob;
     private Set<Manoobra> manoobras = new HashSet<Manoobra>(0);

    public Categoriamanoobra() {
        this.codCatManob = 0;
    }

    public Categoriamanoobra(String nombCatManob, Set<Manoobra> manoobras) {
       this.nombCatManob = nombCatManob;
       this.manoobras = manoobras;
    }
   
    public Integer getCodCatManob() {
        return this.codCatManob;
    }
    
    public void setCodCatManob(Integer codCatManob) {
        this.codCatManob = codCatManob;
    }
    public String getNombCatManob() {
        return this.nombCatManob;
    }
    
    public void setNombCatManob(String nombCatManob) {
        this.nombCatManob = nombCatManob;
    }
    public Set<Manoobra> getManoobras() {
        return this.manoobras;
    }
    
    public void setManoobras(Set<Manoobra> manoobras) {
        this.manoobras = manoobras;
    }




}

