
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.catequherrDao;
import ec.com.sisapus.daoimpl.catequherrDaoImpl;
import ec.com.sisapus.modelo.Categoriaequipoherramienta;
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
public class catequipoherramientaBean {
    
    private List<SelectItem> listacatequipoherr;
    
    public catequipoherramientaBean() {
    }

    public List<SelectItem> getListacatequipoherr() {
        this.listacatequipoherr = new ArrayList<SelectItem>();
        catequherrDao cateqherrDao = new catequherrDaoImpl();
        List<Categoriaequipoherramienta> catequipoherr = cateqherrDao.listarCatEquipHerram();
        for (Categoriaequipoherramienta catequipherr : catequipoherr) {
            SelectItem selectItem = new SelectItem(catequipherr.getCodCatEqherr(),catequipherr.getNombCatEqherr());
            this.listacatequipoherr.add(selectItem);
        }
        return listacatequipoherr;
    }

    public void setListacatequipoherr(List<SelectItem> listacatequipoherr) {
        this.listacatequipoherr = listacatequipoherr;
    }
    
    
}
