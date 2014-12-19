/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.catrubroDao;
import ec.com.sisapus.daoimpl.catrubroDaoImpl;
import ec.com.sisapus.modelo.Categoriarubro;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Edison
 */
public class rubroBean {
private List<SelectItem> listacatrubro;

    
    public rubroBean() {
    }

    public List<SelectItem> getListacatrubro() {
        this.listacatrubro = new ArrayList<SelectItem>();
        catrubroDao catrubroDao = new catrubroDaoImpl();
        List<Categoriarubro> catrub = catrubroDao.listarCatRubro();
        for (Categoriarubro catrubro : catrub) {
            SelectItem selectItem = new SelectItem(catrubro.getCodigoCatRubro(),catrubro.getDescripcionCatRubro());
            this.listacatrubro.add(selectItem);
        }
        return listacatrubro;
    }

    public void setListacatrubro(List<SelectItem> listacatrubro) {
        this.listacatrubro = listacatrubro;
    }
    
}
