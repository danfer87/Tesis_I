
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.catmanobraDao;
import ec.com.sisapus.daoimpl.catmanobraDaoImpl;
import ec.com.sisapus.modelo.Categoriamanoobra;
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
public class catmanoobraBean {
    
    private List<SelectItem> listacatmanoobra;
    
    public catmanoobraBean() {
    }

    public List<SelectItem> getListacatmanoobra() {
        this.listacatmanoobra = new ArrayList<SelectItem>();
        catmanobraDao catmanoobraDao = new catmanobraDaoImpl();
        List<Categoriamanoobra> catmanoobra = catmanoobraDao.listarCatManoObra();
        for (Categoriamanoobra catmanobra : catmanoobra) {
            SelectItem selectItem = new SelectItem(catmanobra.getCodCatManob(),catmanobra.getNombCatManob());
            this.listacatmanoobra.add(selectItem);
        }
        return listacatmanoobra;
    }

    public void setListacatmanoobra(List<SelectItem> listamanoobra) {
        this.listacatmanoobra = listamanoobra;
    }
    
    
}
