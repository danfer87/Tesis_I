package ec.com.sisapus.bean;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import ec.com.sisapus.util.Util;

@Named(value = "appBean")
@ApplicationScoped
public class appBean {

    public appBean() {
    }
    
    public String getBaseUrl(){
        return Util.baseurl();
        }
    
    public String getBasePath()
    {
        return Util.basepath();
    }
}
