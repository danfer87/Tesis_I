/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.bean;

import ec.com.sisapus.dao.equipoherrDao;
import ec.com.sisapus.daoimpl.ApusDaoImpl;
import ec.com.sisapus.daoimpl.equipoherrDaoImpl;
import ec.com.sisapus.daoimpl.manoobraDaoImpl;
import ec.com.sisapus.daoimpl.materialDaoImpl;
import ec.com.sisapus.daoimpl.transporteDaoImpl;       
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ec.com.sisapus.modelo.EquipherrApu;
import ec.com.sisapus.modelo.Equipoherramienta;
import ec.com.sisapus.modelo.Manoobra;
import ec.com.sisapus.modelo.ManoobraApu;
import ec.com.sisapus.modelo.Material;
import ec.com.sisapus.modelo.MaterialApu;
import ec.com.sisapus.modelo.Transporte;
import ec.com.sisapus.modelo.TransporteApu;
import ec.com.sisapus.modelo.Analisispreciounitario;
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
     private Double  CostoHora;
   //mano de obra
      private Manoobra manoobras;    
     private List<ManoobraApu> listaManoBra;
      private ManoobraApu manopapus;
     private Double precioTotalmanoobra;
   //material
     
     private Material materiales;    
     private List<MaterialApu> listaMaterialApus;
      private MaterialApu materialapus;
     private Double precioTotalmaterial;
     
     //transporte
     
     private Transporte transportes;    
     private List<TransporteApu> listaTransporteApus;
      private TransporteApu transportapus;
     private Double precioTotaltransporte; 
     
     private Analisispreciounitario analisisapus;
     
     public ApuBeanVista()
             
             {
                this.equipherramientas=new Equipoherramienta();
                this.listaEquiposApus=new ArrayList<>();
                this.manoobras=new Manoobra();
                this.listaManoBra=new ArrayList<>();
                this.materiales=new Material();
                this.listaMaterialApus=new ArrayList<>();
                this.transportes=new Transporte();
                this.listaTransporteApus=new ArrayList<>();
                        
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
             
                 
            this.listaEquiposApus.add(new EquipherrApu( null,this.equipherramientas.getNombreEqherr(), null,this.equipherramientas.getCostohoraEqherr(),null, null, null, null));
       
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
                Double costohora=item.getCantEqherrApu()*(new Double(item.getTarifaEqherrApu()));
               
                Double totalVentaPorProducto=(costohora*(new Double(item.getRendimEqherrApu())));
                item.setCostohoraEqherrApu(costohora);
                item.setCostotEqherrApu(totalVentaPorProducto);
                
                totalVenta=totalVenta+totalVentaPorProducto;
            }
            
          this.setPrecioTotalEquipo(totalVenta);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Equipos y Herramientas retirado de la lista"));
            
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
                 Double costohora=item.getCantEqherrApu()*(new Double(item.getTarifaEqherrApu()));
               
                Double totalVentaPorProducto=(costohora*(new Double(item.getRendimEqherrApu())));
                item.setCostohoraEqherrApu(costohora);
                item.setCostotEqherrApu(totalVentaPorProducto);
                
                totalVenta=totalVenta+totalVentaPorProducto;
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
    

//material
///funcion para lista de elementos detalle)
    
    public void agregarListaManobraApu(Integer idmanobra)
    {
        this.session=null;
        this.transaction=null;
        
        try
        {
            this.session=HibernateUtil.getSessionFactory().openSession();
            
         manoobraDaoImpl daomano=new manoobraDaoImpl();
        
            
            this.transaction=this.session.beginTransaction();
            
             this.manoobras=daomano.getByIdManobra(session, idmanobra);
             
        this.listaManoBra.add(new ManoobraApu(null,this.manoobras.getNombreManob(),null,this.manoobras.getCostojrhManob(),null, null, null,null));
            this.transaction.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Mano de Obra agregado"));
            
         
       
            //RequestContext.getCurrentInstance().update("frmApus:frmdetequipos:tablaListaEquipos");
           //RequestContext.getCurrentInstance().update("frmApus:msgs");
            //el qu estaba
            RequestContext.getCurrentInstance().update("frmRealizarVentas1:tablaListaProductosVenta1");
            RequestContext.getCurrentInstance().update("frmRealizarVentas1:mensajeGeneral1");
            //
       
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

//

    //funcion para retirar
       public void EliminarListaManObra(String nom)
    {        
        try
        {
            int i=0;
            
            for(ManoobraApu item : this.listaManoBra)
            {
                if(item.getDescMoApu().equals(nom))
                {
                    this.listaManoBra.remove(i);
                    
                    break;
                }
                
                i++;
            }
            
           Double totalVenta1=new Double("0.00");
            
            for(ManoobraApu item : this.listaManoBra)
            {
                Double costohora1=item.getCantMoApu()*(new Double(item.getCostojrhMoApu()));
               
                Double totalVentaPorProducto1=(costohora1*(new Double(item.getRendimMoApu())));
                item.setCostohoraMoApu(costohora1);
                item.setCostotMoApu(totalVentaPorProducto1);
                
                totalVenta1=totalVenta1+totalVentaPorProducto1;
            }
            
          this.setPrecioTotalmanoobra(totalVenta1);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Mano de Obra retirado de la lista"));
            
            RequestContext.getCurrentInstance().update("frmRealizarVentas1:tablaListaProductosVenta1");
            RequestContext.getCurrentInstance().update("frmRealizarVentas1:panelFinalVenta1");
            RequestContext.getCurrentInstance().update("frmRealizarVentas1:mensajeGeneral1");
        }
        catch(Exception ex)
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    //calculo subtotal equipos
       
       public void calcularCostosManobra()
    {
        try
        {   
           Double totalVenta1=new Double("0.00");
            
           for(ManoobraApu item : this.listaManoBra)
            {
                Double costohora1=item.getCantMoApu()*(new Double(item.getCostojrhMoApu()));
               
                Double totalVentaPorProducto1=(costohora1*(new Double(item.getRendimMoApu())));
                item.setCostohoraMoApu(costohora1);
                item.setCostotMoApu(totalVentaPorProducto1);
                
                totalVenta1=totalVenta1+totalVentaPorProducto1;
            }
            
          this.setPrecioTotalmanoobra(totalVenta1);
            
            RequestContext.getCurrentInstance().update("frmRealizarVentas1:tablaListaProductosVenta1");
            RequestContext.getCurrentInstance().update("frmRealizarVentas1:panelFinalVenta1");
        }
        catch(Exception ex)
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
       
   // 
    
     public void guardarmanObraApus()
        {
           this.session=null;
        this.transaction=null;
        
        try
        {
            this.session=HibernateUtil.getSessionFactory().openSession();
            
          manoobraDaoImpl manoobradao=new manoobraDaoImpl();
            ApusDaoImpl apusmano= new ApusDaoImpl();
     
            
            this.transaction=this.session.beginTransaction();
            this.manoobras=manoobradao.getUltimoRegistro(session);
            
            for(ManoobraApu item : this.listaManoBra)
            {
                this.manoobras=manoobradao.getByIdManobra(session,this.manoobras.getCodigoManob());
                item.setManoobra(this.manoobras);
                apusmano.insertarManobra(this.session, item);
            }
            
            this.transaction.commit();
            this.listaManoBra=new ArrayList<>();
            this.manoobras=new Manoobra();
            this.precioTotalmanoobra=0.0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Mano de Obra guardado correctamente"));
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
  
//materiales    
    
     public void agregarListaMaterialApu(Integer idmaterial)
    {
        this.session=null;
        this.transaction=null;
        
        try
        {
            this.session=HibernateUtil.getSessionFactory().openSession();
            
       materialDaoImpl materialdao= new materialDaoImpl();
               
        
            
            this.transaction=this.session.beginTransaction();
            
             this.materiales=materialdao.getByIdMaterial(session, idmaterial);
             
        //this.listaManoBra.add(new ManoobraApu(null,this.manoobras.getNombreManob(),null,null,this.manoobras.getCostojrhManob(),null, null, null,null));
          this.listaMaterialApus.add(new MaterialApu(null, this.materiales.getNombreMat(),this.materiales.getUnidMat(), null,this.materiales.getPrecunitMat(),null, null));
        this.transaction.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Material agregado"));
            
         
       
            //RequestContext.getCurrentInstance().update("frmApus:frmdetequipos:tablaListaEquipos");
           //RequestContext.getCurrentInstance().update("frmApus:msgs");
            //el qu estaba
            RequestContext.getCurrentInstance().update("frmRealizarVentas2:tablaListaProductosVenta2");
            RequestContext.getCurrentInstance().update("frmRealizarVentas2:mensajeGeneral2");
            //
       
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

//

    //funcion para retirar
       public void EliminarListaMateriales(String nom)
    {        
        try
        {
            int i=0;
            
            for(MaterialApu item : this.listaMaterialApus)
            {
                if(item.getDescMatApu().equals(nom))
                {
                    this.listaMaterialApus.remove(i);
                    
                    break;
                }
                
                i++;
            }
            
           Double totalVenta1=new Double("0.00");
            
            for(MaterialApu item : this.listaMaterialApus)
            {
                
               
                Double totalVentaPorProducto1=(new Double(item.getCantMatApu()))*(new Double(item.getPreunitMatApu()));
                
                item.setCostotMatApu(totalVentaPorProducto1);
                
                totalVenta1=totalVenta1+totalVentaPorProducto1;
            }
            
          this.setPrecioTotalmaterial(totalVenta1);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Material retirado de la lista"));
            
            RequestContext.getCurrentInstance().update("frmRealizarVentas2:tablaListaProductosVenta2");
            RequestContext.getCurrentInstance().update("frmRealizarVentas2:panelFinalVenta2");
            RequestContext.getCurrentInstance().update("frmRealizarVentas2:mensajeGeneral2");
        }
        catch(Exception ex)
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    //calculo subtotal equipos
       
       public void calcularCostosMateriales()
    {
        try
        {   
             Double totalVenta1=new Double("0.00");
            
            for(MaterialApu item : this.listaMaterialApus)
            {
                
               
                Double totalVentaPorProducto1=(new Double(item.getCantMatApu()))*(new Double(item.getPreunitMatApu()));
                
                item.setCostotMatApu(totalVentaPorProducto1);
                
                totalVenta1=totalVenta1+totalVentaPorProducto1;
            }
            
          this.setPrecioTotalmaterial(totalVenta1);
            
            RequestContext.getCurrentInstance().update("frmRealizarVentas2:tablaListaProductosVenta2");
            RequestContext.getCurrentInstance().update("frmRealizarVentas2:panelFinalVenta2");
        }
        catch(Exception ex)
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
       
   // 
    
     public void guardarmaterialApus()
        {
           this.session=null;
        this.transaction=null;
        
        try
        {
            this.session=HibernateUtil.getSessionFactory().openSession();
            
          materialDaoImpl materialdao=new materialDaoImpl();
            ApusDaoImpl apusmaterial= new ApusDaoImpl();
     
            
            this.transaction=this.session.beginTransaction();
            this.materiales=materialdao.getUltimoRegistro(session);
            
            for(MaterialApu item : this.listaMaterialApus)
            {
                this.materiales=materialdao.getByIdMaterial(session,this.materiales.getCodigoMat());
                item.setMaterial(this.materiales);
                apusmaterial.insertarMaterial(this.session, item);
            }
            
            this.transaction.commit();
            this.listaMaterialApus=new ArrayList<>();
            this.materiales=new Material();
            this.precioTotalmaterial=0.0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Material guardado correctamente"));
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
     
     
     
     
    
    
//fin materiales

//transporte    
    
     public void agregarListaTransporteApu(Integer idtrans)
    {
        this.session=null;
        this.transaction=null;
        
        try
        {
            this.session=HibernateUtil.getSessionFactory().openSession();
                 
       transporteDaoImpl transpdao=new transporteDaoImpl();
        
            
            this.transaction=this.session.beginTransaction();
            
             this.transportes=transpdao.getByIdTransporte(session, idtrans);
             
        //this.listaManoBra.add(new ManoobraApu(null,this.manoobras.getNombreManob(),null,null,this.manoobras.getCostojrhManob(),null, null, null,null));
          this.listaTransporteApus.add(new TransporteApu(null, this.transportes.getNombreTransp(), null,null, this.transportes.getTarifaTransp(), null, null));
        this.transaction.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Transporte agregado"));
            
            RequestContext.getCurrentInstance().update("frmRealizarVentas3:tablaListaProductosVenta3");
            RequestContext.getCurrentInstance().update("frmRealizarVentas3:mensajeGeneral3");
            //
       
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

//

    //funcion para retirar
       public void EliminarListaTransporte(String nom)
    {        
        try
        {
            int i=0;
            
            for(TransporteApu item : this.listaTransporteApus)
            {
                if(item.getDescTranApu().equals(nom))
                {
                    this.listaTransporteApus.remove(i);
                    
                    break;
                }
                
                i++;
            }
            
           Double totalVenta1=new Double("0.00");
            
            for(TransporteApu item : this.listaTransporteApus)
            {
                
               
                Double totalVentaPorProducto1=(new Double(item.getCantTranApu()))*(new Double(item.getTarifaTranApu()));
                
                item.setCostotTranApu(totalVentaPorProducto1);
                
                totalVenta1=totalVenta1+totalVentaPorProducto1;
            }
            
          this.setPrecioTotaltransporte(totalVenta1);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Transporte retirado de la lista"));
            
            RequestContext.getCurrentInstance().update("frmRealizarVentas3:tablaListaProductosVenta3");
            RequestContext.getCurrentInstance().update("frmRealizarVentas3:panelFinalVenta3");
            RequestContext.getCurrentInstance().update("frmRealizarVentas3:mensajeGeneral3");
        }
        catch(Exception ex)
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    //calculo subtotal equipos
       
       public void calcularCostosTransporte()
    {
        try
        {   
            Double totalVenta1=new Double("0.00");
            
            for(TransporteApu item : this.listaTransporteApus)
            {
                
               
                Double totalVentaPorProducto1=(new Double(item.getCantTranApu()))*(new Double(item.getTarifaTranApu()));
                
                item.setCostotTranApu(totalVentaPorProducto1);
                
                totalVenta1=totalVenta1+totalVentaPorProducto1;
            }
            
          this.setPrecioTotaltransporte(totalVenta1);
           
            
            RequestContext.getCurrentInstance().update("frmRealizarVentas3:tablaListaProductosVenta3");
            RequestContext.getCurrentInstance().update("frmRealizarVentas3:panelFinalVenta3");
        }
        catch(Exception ex)
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
       
   // 
    
     public void guardarTransporteApus()
        {
           this.session=null;
        this.transaction=null;
        
        try
        {
            this.session=HibernateUtil.getSessionFactory().openSession();
            
        transporteDaoImpl transpodao=new transporteDaoImpl();
            ApusDaoImpl apustraanporte= new ApusDaoImpl();
     
            
            this.transaction=this.session.beginTransaction();
            this.transportes=transpodao.getUltimoRegistro(session);
            
            for(TransporteApu item : this.listaTransporteApus)
            {
                this.transportes=transpodao.getByIdTransporte(session,this.transportes.getCodigoTransp());
                item.setTransporte(this.transportes);
                apustraanporte.insertarTransporte(this.session, item);
            }
            
            this.transaction.commit();
            this.listaTransporteApus=new ArrayList<>();
            this.transportes=new Transporte();
            this.precioTotaltransporte=0.0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Transporte guardado correctamente"));
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
     
     
     
     
    
    
//fin transporte






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

    public Manoobra getManoobras() {
        return manoobras;
    }

    public void setManoobras(Manoobra manoobras) {
        this.manoobras = manoobras;
    }

    public List<ManoobraApu> getListaManoBra() {
        return listaManoBra;
    }

    public void setListaManoBra(List<ManoobraApu> listaManoBra) {
        this.listaManoBra = listaManoBra;
    }

    

    public ManoobraApu getManopapus() {
        return manopapus;
    }

    public void setManopapus(ManoobraApu manopapus) {
        this.manopapus = manopapus;
    }

    public Double getPrecioTotalmanoobra() {
        return precioTotalmanoobra;
    }

    public void setPrecioTotalmanoobra(Double precioTotalmanoobra) {
        this.precioTotalmanoobra = precioTotalmanoobra;
    }

    public Double getCostoHora() {
        return CostoHora;
    }

    public void setCostoHora(Double CostoHora) {
        this.CostoHora = CostoHora;
    }

    //materiales
    public Material getMateriales() {
        return materiales;
    }

    public void setMateriales(Material materiales) {
        this.materiales = materiales;
    }

    public List<MaterialApu> getListaMaterialApus() {
        return listaMaterialApus;
    }

    public void setListaMaterialApus(List<MaterialApu> listaMaterialApus) {
        this.listaMaterialApus = listaMaterialApus;
    }

    public MaterialApu getMaterialapus() {
        return materialapus;
    }

    public void setMaterialapus(MaterialApu materialapus) {
        this.materialapus = materialapus;
    }

    public Double getPrecioTotalmaterial() {
        return precioTotalmaterial;
    }

    public void setPrecioTotalmaterial(Double precioTotalmaterial) {
        this.precioTotalmaterial = precioTotalmaterial;
    }
    
    //transporte
    public Transporte getTransportes() {
        return transportes;
    }

    public void setTransportes(Transporte transportes) {
        this.transportes = transportes;
    }

    public List<TransporteApu> getListaTransporteApus() {
        return listaTransporteApus;
    }

    public void setListaTransporteApus(List<TransporteApu> listaTransporteApus) {
        this.listaTransporteApus = listaTransporteApus;
    }

    public TransporteApu getTransportapus() {
        return transportapus;
    }

    public void setTransportapus(TransporteApu transportapus) {
        this.transportapus = transportapus;
    }

    public Double getPrecioTotaltransporte() {
        return precioTotaltransporte;
    }

    public void setPrecioTotaltransporte(Double precioTotaltransporte) {
        this.precioTotaltransporte = precioTotaltransporte;
    }

    public Analisispreciounitario getAnalisisapus() {
        return analisisapus;
    }

    public void setAnalisisapus(Analisispreciounitario analisisapus) {
        this.analisisapus = analisisapus;
    }
    
    
    
    
  
}
