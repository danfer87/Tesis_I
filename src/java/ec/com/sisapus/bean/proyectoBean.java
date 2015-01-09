package ec.com.sisapus.bean;

import ec.com.sisapus.dao.proyectoDao;
import ec.com.sisapus.dao.usuarioDao;
import ec.com.sisapus.daoimpl.proyectoDaoImpl;
import ec.com.sisapus.daoimpl.usuarioDaoImpl;
import ec.com.sisapus.modelo.Proyecto;
import ec.com.sisapus.modelo.Usuario;
import ec.com.sisapus.util.HibernateUtil;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Edison
 */
@Named(value = "proyectoBean")
@javax.faces.view.ViewScoped
//@ManagedBean
//@ViewScoped
public class proyectoBean implements Serializable {

    private Proyecto proyecto;
    private Usuario usuario;
    private List<Proyecto> listaProyectos;
    private List<Proyecto> liscaldimensional;
    private List<Proyecto> listaporUsuario;
    
    //////
    private Session session;
    private Transaction transaccion;
    private List<SelectItem> cargarProyectos;
    
    private Integer codigoProy;
    /////
    private Usuario idUssuu;

    public proyectoBean() {
        this.proyecto = new Proyecto();
        //probar
        
        this.usuario=new Usuario();
        //
    }

    public Proyecto getProyecto() {
        if (this.proyecto == null) {
            proyecto = new Proyecto();
        }
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public List<Proyecto> getListaProyectos() {
        proyectoDao proyecDao = new proyectoDaoImpl();
        listaProyectos = proyecDao.listarProyectos();
        return listaProyectos;
    }

    public void setListaProyectos(List<Proyecto> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }

    ///////////
    public Integer getCodigoProy() {
        return codigoProy;
    }

    public void setCodigoProy(Integer codigoProy) {
        this.codigoProy = codigoProy;
    }

    ///////////
    public List<Proyecto> getListaporUsuario() {
        //  proyectoDao proyecDao = new proyectoDaoImpl();
        //listaporUsuario = proyecDao.listarProyectosPorUsuario("kleper");
        this.session = null;
        this.transaccion = null;
        try {
            proyectoDaoImpl daoproyecto = new proyectoDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            this.listaporUsuario = daoproyecto.listarProyectosPorUsuario(this.proyecto.getUsuario().getSobrenombreUsu());
            this.transaccion.commit();
            return this.listaporUsuario;
        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }




    }

public void setListaporUsuario(List<Proyecto> listaporUsuario) {
        this.listaporUsuario = listaporUsuario;
    }

    public Session getSession() {
        return session;

    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaction transaccion) {
        this.transaccion = transaccion;
    }

    //////----Getter y Setter idUsss//////
    public Usuario getIdUssuu() {
        return idUssuu;

    }

    public void setIdUssuu(Usuario idUssuu) {
        this.idUssuu = idUssuu;
    }

    //////////
    //////////////////////////
    ////Crear Proyecto
    public void crearProyecto(ActionEvent actionEvent) throws Exception {
        proyectoDao proyectDao = new proyectoDaoImpl();
        usuarioDao usdao=new usuarioDaoImpl();
        String msg;
        this.usuario=usdao.getByIdUsuario(usuario);
        this.proyecto.setUsuario(usuario);
      /*  this.proyecto.setContratProy(this.proyecto.getContratProy());
        this.proyecto.setPropiepProy(this.proyecto.getPropiepProy());
        this.proyecto.setObraProy(this.proyecto.getObraProy());
        this.proyecto.setUbicProy(this.proyecto.getUbicProy());
        this.proyecto.setFechaProy(this.proyecto.getFechaProy());
        this.proyecto.setCostotProy(this.proyecto.getCostotProy());
        */
        if (proyectDao.crearProyecto(this.proyecto)) {
            msg = "Proyecto creado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se creo el Proyecto";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message2);
        }

    }

    /*Actualizar Proyecto*/
    public void actualizarProyecto(ActionEvent actionEvent) {
        proyectoDao proyectDao = new proyectoDaoImpl();
        String msg;
        /*
        this.proyecto.setUsuario(this.proyecto.getUsuario());
        this.proyecto.setContratProy(this.proyecto.getContratProy());
        this.proyecto.setPropiepProy(this.proyecto.getPropiepProy());
        this.proyecto.setObraProy(this.proyecto.getObraProy());
        this.proyecto.setUbicProy(this.proyecto.getUbicProy());
        this.proyecto.setFechaProy(this.proyecto.getFechaProy());
        this.proyecto.setCostotProy(this.proyecto.getCostotProy());
        */
        if (proyectDao.actualizarProyecto(this.proyecto)) {
            msg = "Proyecto modificado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se modifico el Proyecto";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message2);
        }
    }

    /*Eliminar Proyecto*/
    public void eliminarProyecto(ActionEvent actionEvent) {
        proyectoDao proyectDao = new proyectoDaoImpl();
        String msg;
        if (proyectDao.eliminarProyecto(this.proyecto.getCodigoProy())) {
            msg = "Proyecto eliminado correctamente";
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message1);
        } else {
            msg = "No se elimino el Proyecto";
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message2);
        }

    }

    //MODIFICAR PROYECTO OTRA FORMA///////
    public void modificarProyecto() {
        this.session = null;
        this.transaccion = null;

        try {
            proyectoDaoImpl daoProyecto = new proyectoDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

            daoProyecto.modificarProyecto(this.session, this.proyecto);
            this.transaccion.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto Actualizado:", "Los cambios fueron realizados exitosamente"));
        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    ////////////////////////////////
    public List<Proyecto> listarProyectosUsuario() {
        this.session = null;
        this.transaccion = null;

        try {
            proyectoDao daoProy = new proyectoDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            HttpSession sessionUsuario = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            //this.listaProyectos = daoProy.listarPorUsuario(this.session, this.idUssuu.getSobrenombreUsu());
            this.listaProyectos = daoProy.listarPorUsuario(this.session, sessionUsuario.getAttribute("sobrenombreUsuario").toString());
            this.transaccion.commit();
            return this.listaProyectos;
        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));

            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //Cargar lista de proyectos en el combobox
    public List<SelectItem> getCargarProyectos() {
        this.cargarProyectos = new ArrayList<SelectItem>();
        proyectoDao proydao = new proyectoDaoImpl();
        List<Proyecto> proy = proydao.listarProyectos();
        for (Proyecto pro : proy) {
            SelectItem selectItem = new SelectItem(pro.getCodigoProy(), pro.getObraProy());
            this.cargarProyectos.add(selectItem);
        }
        return cargarProyectos;
    }

    
    
    ////////Pestañas
    private TabView tabView;

    public TabView getTabView() {
        FacesContext fc = FacesContext.getCurrentInstance();
        tabView = (TabView) fc.getApplication().createComponent("org.primefaces.component.TabView");

        // cargar la lista de objetos para tabview

        //Se crean dinamicamente las tabs y en su contenido, unas cajas de texto
        //for ( Proyecto sub : listaProyectos) {
        Tab tab = new Tab();
        tab.setTitle("Guayaquil");


        Random randomGenerator = new Random();
        int total = 4;//Math.max(1, randomGenerator.nextInt(6));
        for (int i = 0; i < total; i++) {

            InputText inputtext = new InputText();

            inputtext.setLabel("Label");
            inputtext.setValue("id:" + inputtext.getClientId());
            inputtext.setOnfocus("");
            tab.getChildren().add(inputtext);
        }
        tabView.getChildren().add(tab);
        return tabView;
    }

    // return tabView; 
    //}
    public void setTabView(TabView tabView) {
        this.tabView = tabView;
    }
    
    
}
