/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.catmatDao;
import ec.com.sisapus.daoimpl.catmatDaoImpl;
import ec.com.sisapus.modelo.Categoriamaterial;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;


/**
 *
 * @author Edison
 */
@ManagedBean
@ViewScoped
public class catmatBean {
    
    private List<SelectItem> listacatmater;

    
    public catmatBean() {
    }

    public List<SelectItem> getListacatmater() {
        this.listacatmater = new ArrayList<SelectItem>();
        catmatDao catmaterDao = new catmatDaoImpl();
        List<Categoriamaterial> catmater = catmaterDao.listarCatMaterial();
        for (Categoriamaterial catmaterial : catmater) {
            SelectItem selectItem = new SelectItem(catmaterial.getCodCatMat(),catmaterial.getNombCatMat());
            this.listacatmater.add(selectItem);
        }
        return listacatmater;
    }

    public void setListacatmater(List<SelectItem> listacatmater) {
        this.listacatmater = listacatmater;
    }
    
    

}
