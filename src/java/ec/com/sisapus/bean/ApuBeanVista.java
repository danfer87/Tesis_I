/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.equipoherrDao;
import ec.com.sisapus.daoimpl.ApusDaoImpl;
import ec.com.sisapus.daoimpl.equipoherrDaoImpl;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ec.com.sisapus.modelo.EquipherrApu;
import ec.com.sisapus.modelo.Equipoherramienta;
import ec.com.sisapus.util.HibernateUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kleber
 */
@Named(value = "ApuBeanVista")
@ViewScoped
public class ApuBeanVista implements Serializable {
    
     Session session;
    Transaction transaction;
    
     private Equipoherramienta equipherramientas;    
     private List<EquipherrApu> listaEquiposApus;
      private EquipherrApu equipapus;
     private Double precioTotalEquipo;
     
     public ApuBeanVista()
             
             {
                this.equipherramientas=new Equipoherramienta();
                this.listaEquiposApus=new ArrayList<>();
             }

    public Equipoherramienta getEquipherramientas() {
        return equipherramientas;
    }

    public void setEquipherramientas(Equipoherramienta equipherramientas) {
        this.equipherramientas = equipherramientas;
    }

    public Double getPrecioTotalEquipo() {
        return precioTotalEquipo;
    }

    public void setPrecioTotalEquipo(Double precioTotalEquipo) {
        this.precioTotalEquipo = precioTotalEquipo;
    }

    public EquipherrApu getEquipapus() {
        return equipapus;
    }

    public void setEquipapus(EquipherrApu equipapus) {
        this.equipapus = equipapus;
    }
             
     
     
     

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<EquipherrApu> getListaEquiposApus() {
        return listaEquiposApus;
    }

    public void setListaEquiposApus(List<EquipherrApu> listaEquiposApus) {
        this.listaEquiposApus = listaEquiposApus;
    }
     
 ///funcion para lista de elementos detalle)
    
    public void agregarListaEquiposApus(Integer idEquipos)
    {
        this.session=null;
        this.transaction=null;
        
        try
        {
            this.session=HibernateUtil.getSessionFactory().openSession();
            
          equipoherrDaoImpl daoequipo=new equipoherrDaoImpl();
         //   equipoherrDao daoequipo=new equipoherrDaoImpl();
            
            this.transaction=this.session.beginTransaction();
            
             this.equipherramientas=daoequipo.getByIdEquipo(session, idEquipos);
             
                 
            this.listaEquiposApus.add(new EquipherrApu( null,this.equipherramientas.getNombreEqherr(), null,null,this.equipherramientas.getCostohoraEqherr(), null, null, null));
         
            this.transaction.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Equipo/Herramienta agregado"));
            
         
       
            //RequestContext.getCurrentInstance().update("frmApus:frmdetequipos:tablaListaEquipos");
           //RequestContext.getCurrentInstance().update("frmApus:msgs");
            //el qu estaba
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
            //
        //nuevas tablas    
       // RequestContext.getCurrentInstance().update("conequih,:tablaListaProductosVenta");
        //    RequestContext.getCurrentInstance().update("conequih,:mensajeGeneral");    
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                transaction.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }
   //funcion para retirar
       public void EliminarListaEquipo(String nom)
    {        
        try
        {
            int i=0;
            
            for(EquipherrApu item : this.listaEquiposApus)
            {
                if(item.getDescEqherrApu().equals(nom))
                {
                    this.listaEquiposApus.remove(i);
                    
                    break;
                }
                
                i++;
            }
            
           Double totalVenta=new Double("0");
            
            for(EquipherrApu item : this.listaEquiposApus)
            {
                Double totalVentaPorProducto=item.getCantEqherrApu()*(new Double(item.getTarifaEqherrApu())*(new Double(item.getCostohoraEqherrApu()))*(new Double(item.getRendimEqherrApu())));
                
                item.setCostotEqherrApu(totalVentaPorProducto);
                
                totalVenta=totalVenta+totalVentaPorProducto;
            }
            
          this.setPrecioTotalEquipo(totalVenta);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Producto retirado de la lista de venta"));
            
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
        }
        catch(Exception ex)
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
  //calculo subtotal equipos
       
       public void calcularCostos()
    {
        try
        {   
           Double totalVenta=new Double("0.00");
            
            for(EquipherrApu item : this.listaEquiposApus)
            {
                 Double totalVentaPorProducto=item.getCantEqherrApu()*(new Double(item.getTarifaEqherrApu())*(new Double(item.getCostohoraEqherrApu()))*(new Double(item.getRendimEqherrApu())));
                
                item.setCostotEqherrApu(totalVentaPorProducto);
                
                totalVenta=totalVentaPorProducto+totalVenta;
            }
            
            this.setPrecioTotalEquipo(totalVenta);
            
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta");
        }
        catch(Exception ex)
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
       
   
  public void guardarequiposApus()
        {
           this.session=null;
        this.transaction=null;
        
        try
        {
            this.session=HibernateUtil.getSessionFactory().openSession();
            
           equipoherrDaoImpl daoequipo=new equipoherrDaoImpl();
            ApusDaoImpl apusequip= new ApusDaoImpl();
     
            
            this.transaction=this.session.beginTransaction();
            this.equipherramientas=daoequipo.getUltimoRegistro(session);
            
            for(EquipherrApu item : this.listaEquiposApus)
            {
                this.equipherramientas=daoequipo.getByIdEquipo(session,this.equipherramientas.getCodigoEqherr());
                item.setEquipoherramienta(this.equipherramientas);
                apusequip.insert(this.session, item);
            }
            
            this.transaction.commit();
            this.listaEquiposApus=new ArrayList<>();
            this.equipherramientas=new Equipoherramienta();
            this.precioTotalEquipo=0.0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Equipo y Herramientas guardado correctamente"));
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                transaction.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }  
        }
  
  //probar creacion de Escenarios
  
  private TabView tabView;

    public void setTabView(TabView tabView) {
        this.tabView = tabView;
    }
  
  /* public TabView getTabView() {
        FacesContext fc = FacesContext.getCurrentInstance();
        tabView = (TabView) fc.getApplication().createComponent("org.primefaces.component.TabView");

        // cargar la lista de objetos para tabview
        List liscaldimensional = new ArrayList();
        liscaldimensional =a//ejbFacadeCalidadDimensional.findAll();

        //Se crean dinamicamente las tabs y en su contenido, unas cajas de texto
        for (CalidadDimensional sub : liscaldimensional) {
            Tab tab = new Tab();
            tab.setTitle(sub.getCalidadDimensional());
            Random randomGenerator = new Random();
            int total = 4;
            for (int i = 0; i < total; i++) {
                InputText inputtext = new InputText();
                inputtext.setLabel("Label");
                inputtext.setValue("id:" + inputtext.getClientId());
                inputtext.setOnfocus("");
                tab.getChildren().add(inputtext);
            }
            tabView.getChildren().add(tab);
        }
        return tabView;
    }*/
  //probar a lo que se selecciona la fila
    
public void SeleccionarFila(SelectEvent event) {  
    
    this.session=null;
        this.transaction=null;
        
        try
        {
            this.session=HibernateUtil.getSessionFactory().openSession();
            
          equipoherrDaoImpl daoequipo=new equipoherrDaoImpl();
         //   equipoherrDao daoequipo=new equipoherrDaoImpl();
            
            this.transaction=this.session.beginTransaction();
            
             this.equipherramientas=daoequipo.getByIdEquipo(session, equipherramientas.getCodigoEqherr());
             
                 
            this.listaEquiposApus.add(new EquipherrApu( null,this.equipherramientas.getNombreEqherr(), null,null,this.equipherramientas.getCostohoraEqherr(), null, null, null));
         
            this.transaction.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Equipo/Herramienta agregado"));
            
         
       
            //RequestContext.getCurrentInstance().update("frmApus:frmdetequipos:tablaListaEquipos");
           //RequestContext.getCurrentInstance().update("frmApus:msgs");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                transaction.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
        FacesMessage msg = new FacesMessage("Equipo y Herramienta Seleccionado", equipherramientas.getNombreEqherr());  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        
    }  
    
  
}
