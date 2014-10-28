/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.cattransporteDao;
import ec.com.sisapus.daoimpl.cattransporteDaoImpl;
import ec.com.sisapus.modelo.Categoriatransporte;
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
public class cattranspBean {
    
    private List<SelectItem> listacattransporte;
    
     public cattranspBean() {
    }

    public List<SelectItem> getListacattransporte() {
        this.listacattransporte = new ArrayList<SelectItem>();
        cattransporteDao cattransDao = new cattransporteDaoImpl();
        List<Categoriatransporte> catrans = cattransDao.listarCatTransporte();
        for (Categoriatransporte cattransp : catrans) {
            SelectItem selectItem = new SelectItem(cattransp.getCodCatTrans(),cattransp.getNombCatTrans());
            this.listacattransporte.add(selectItem);
        }
        return listacattransporte;
    }

    public void setListacattransporte(List<SelectItem> listacattransporte) {
        this.listacattransporte = listacattransporte;
    }
     
     
    
}
