package ec.com.sisapus.modelo;
// Generated 06/01/2015 02:51:14 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Categoriamanoobra generated by hbm2java
 */
public class Categoriamanoobra  implements java.io.Serializable {


     private Integer codCatManob;
     private String nombCatManob;
     private Set manoobras = new HashSet(0);

    public Categoriamanoobra() {
         this.codCatManob = 0;

    }

    public Categoriamanoobra(String nombCatManob, Set manoobras) {
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
    public Set getManoobras() {
        return this.manoobras;
    }
    
    public void setManoobras(Set manoobras) {
        this.manoobras = manoobras;
    }




}


