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
import ec.com.sisapus.daoimpl.rubroDaoImpl;
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
import ec.com.sisapus.modelo.Categoriarubro;
import ec.com.sisapus.modelo.Escenarioapu;
import ec.com.sisapus.modelo.Rubro;
import ec.com.sisapus.util.HibernateUtil;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
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
     // equipos   
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
     //escenarios
     
     private Transporte transportes;    
     private List<TransporteApu> listaTransporteApus;
      private TransporteApu transportapus;
     private Double precioTotaltransporte; 
     
     //precios unitarios
     private Analisispreciounitario analisisapus;
     private List<Analisispreciounitario>  listapus;
     private Double auxiliarPorcenjate; 
     private Double auxiliarotroscostos;
    private   Double totaldirAPU;
  private  Double costoinAPu;
  private  Double costoaputotal;
     //rubros
     private Rubro rubro;
      private List<Rubro> listaRubro;
     private String auxdesrubro;
     private String auxunidrubro;
     private int auxocidigo;
    private String auxcategoria;
     ///para la categoria rubro
    private Categoriarubro catrubro;
   /// 
 //
    private Escenarioapu escenariosapu;
    
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
                this.rubro=new Rubro();
                this.analisisapus=new Analisispreciounitario();
                this.escenariosapu=new Escenarioapu();
               
             }
     
 ///funcion para agregar rubro
    
  public void agregarRubroApus(Integer idRubros)
    {
        this.session=null;
        this.transaction=null;
        
        try
        {
            this.session=HibernateUtil.getSessionFactory().openSession();
            
            rubroDaoImpl daorubro=new rubroDaoImpl();
       
            
            this.transaction=this.session.beginTransaction();
            
             this.rubro=daorubro.getByIdRubro(session, idRubros);
            
            this.setAuxocidigo(this.rubro.getCodigoRubro());
            this.setAuxdesrubro(this.rubro.getNombreRubro());
            this.setAuxunidrubro(this.rubro.getUnidadRubro());
            this.setAuxcategoria(this.rubro.getCategoriarubro().getDescripcionCatRubro());
       
      //      
       
            this.transaction.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Rubro agregado"));
          
             RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta4");
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
    }   
     
     
  //  
    public void agregarListaEquiposApus(Integer idEquipos)
    {
        this.session=null;
        this.transaction=null;
        
        try
        {
            this.session=HibernateUtil.getSessionFactory().openSession();
            
          equipoherrDaoImpl daoequipo=new equipoherrDaoImpl();
                   
            this.transaction=this.session.beginTransaction();    
             this.equipherramientas=daoequipo.getByIdEquipo(session, idEquipos);              
            //this.listaEquiposApus.add(new EquipherrApu( null,this.equipherramientas.getNombreEqherr(), null,this.equipherramientas.getCostohoraEqherr(),null, null, null, null));
            this.listaEquiposApus.add(new EquipherrApu( null,null,this.equipherramientas.getNombreEqherr(), null,this.equipherramientas.getCostohoraEqherr(),null, null, null));
            this.transaction.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Equipo/Herramienta agregado"));
     
            //el qu estaba
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
            
           Double totalVenta1=new Double("0");
            
            for(EquipherrApu item : this.listaEquiposApus)
            {
                Double costohora=item.getCantEqherrApu()*(new Double(item.getTarifaEqherrApu()));
               
                Double totalVentaPorProducto=(costohora*(new Double(item.getRendimEqherrApu())));
                item.setCostohoraEqherrApu(costohora);
                item.setCostotEqherrApu(totalVentaPorProducto);
                
                totalVenta1=totalVenta1+totalVentaPorProducto;
            }
            
          this.setPrecioTotalEquipo(totalVenta1);
             this.analisisapus.setAnalApuEqherr(totalVenta1);
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
           Double totalVenta1=new Double("0.00");
            
            for(EquipherrApu item : this.listaEquiposApus)
            {
                 Double costohora=item.getCantEqherrApu()*(new Double(item.getTarifaEqherrApu()));
               
                Double totalVentaPorProducto=(costohora*(new Double(item.getRendimEqherrApu())));
                item.setCostohoraEqherrApu(costohora);
                item.setCostotEqherrApu(totalVentaPorProducto);
                
                totalVenta1=totalVenta1+totalVentaPorProducto;
            }
            
            this.setPrecioTotalEquipo(totalVenta1);
           this.analisisapus.setAnalApuEqherr(totalVenta1);
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta");
        }
        catch(Exception ex)
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
       
  
  
  private TabView tabView;

    public TabView getTabView() {
        return tabView;
    }

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
    

//material

    
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
             
        this.listaManoBra.add(new ManoobraApu(null,null,this.manoobras.getNombreManob(),null,null,this.manoobras.getCostojrhManob(),null, null, null));
            this.transaction.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Mano de Obra agregado"));
            
 
            //el qu estaba
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta1");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
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
            
           Double totalVenta2=new Double("0.00");
            
            for(ManoobraApu item : this.listaManoBra)
            {
                Double costohora1=item.getCantMoApu()*(new Double(item.getCostojrhMoApu()));
               
                Double totalVentaPorProducto1=(costohora1*(new Double(item.getRendimMoApu())));
                item.setCostohoraMoApu(costohora1);
                item.setCostotMoApu(totalVentaPorProducto1);
                
                totalVenta2=totalVenta2+totalVentaPorProducto1;
            }
            
          this.setPrecioTotalmanoobra(totalVenta2);
         this.analisisapus.setAnalApuMob(totalVenta2);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Mano de Obra retirado de la lista"));
            
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta1");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta1");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
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
           Double totalVenta2=new Double("0.00");
            
           for(ManoobraApu item : this.listaManoBra)
            {
                Double costohora1=item.getCantMoApu()*(new Double(item.getCostojrhMoApu()));
               
                Double totalVentaPorProducto1=(costohora1*(new Double(item.getRendimMoApu())));
                item.setCostohoraMoApu(costohora1);
                item.setCostotMoApu(totalVentaPorProducto1);
                
                totalVenta2=totalVenta2+totalVentaPorProducto1;
            }
            
          this.setPrecioTotalmanoobra(totalVenta2);
          this.analisisapus.setAnalApuMob(totalVenta2);
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta1");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta1");
        }
        catch(Exception ex)
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
     
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
          this.listaMaterialApus.add(new MaterialApu(null,null, this.materiales.getNombreMat(),this.materiales.getUnidMat(), null,this.materiales.getPrecunitMat(),null));
        this.transaction.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Material agregado"));
            
   
            //el qu estaba
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta2");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
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
            
           Double totalVenta3=new Double("0.00");
            
            for(MaterialApu item : this.listaMaterialApus)
            {
                
               
                Double totalVentaPorProducto1=(new Double(item.getCantMatApu()))*(new Double(item.getPreunitMatApu()));
                
                item.setCostotMatApu(totalVentaPorProducto1);
                
                totalVenta3=totalVenta3+totalVentaPorProducto1;
            }
            
          this.setPrecioTotalmaterial(totalVenta3);
          this.analisisapus.setAnalApuEqherr(totalVenta3);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Material retirado de la lista"));
            
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta2");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta2");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
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
             Double totalVenta3=new Double("0.00");
            
            for(MaterialApu item : this.listaMaterialApus)
            {
                
               
                Double totalVentaPorProducto1=(new Double(item.getCantMatApu()))*(new Double(item.getPreunitMatApu()));
                
                item.setCostotMatApu(totalVentaPorProducto1);
                
                totalVenta3=totalVenta3+totalVentaPorProducto1;
            }
            
          this.setPrecioTotalmaterial(totalVenta3);
           this.analisisapus.setAnalApuMat(totalVenta3);
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta2");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta2");
        }
        catch(Exception ex)
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
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
          this.listaTransporteApus.add(new TransporteApu(null,null, this.transportes.getNombreTransp(), "GLB",null, this.transportes.getTarifaTransp(), null));
        this.transaction.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Transporte agregado"));
            
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta3");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
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
            
           Double totalVenta4=new Double("0.00");
            
            for(TransporteApu item : this.listaTransporteApus)
            {
                
               
                Double totalVentaPorProducto1=(new Double(item.getCantTranApu()))*(new Double(item.getTarifaTranApu()));
                
                item.setCostotTranApu(totalVentaPorProducto1);
                
                totalVenta4=totalVenta4+totalVentaPorProducto1;
            }
            
          this.setPrecioTotaltransporte(totalVenta4);
          this.analisisapus.setAnalApuTran(totalVenta4);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Transporte retirado de la lista"));
            
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta3");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta3");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
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
            Double totalVenta4=new Double("0.00");
            
            for(TransporteApu item : this.listaTransporteApus)
            {
                
               
                Double totalVentaPorProducto1=(new Double(item.getCantTranApu()))*(new Double(item.getTarifaTranApu()));
                
                item.setCostotTranApu(totalVentaPorProducto1);
                
                totalVenta4=totalVenta4+totalVentaPorProducto1;
            }
            
          this.setPrecioTotaltransporte(totalVenta4);
          this.analisisapus.setAnalApuTran(totalVenta4);
   
           
            
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta3");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta3");
        }
        catch(Exception ex)
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
       
  
     
  
     
//fin transporte

//Analisis Precios Unitarios
     
   public void guardarAPU()
        {
         this.session=null;
        this.transaction=null;
        
        try
        {
        this.session=HibernateUtil.getSessionFactory().openSession();
        this.escenariosapu.setCodigoEscenario(1);
        this.analisisapus.setEscenarioapu(escenariosapu);
        this.analisisapus.setDescApu(getAuxdesrubro());
        this.analisisapus.setCategoriaApu(getAuxcategoria());
        this.analisisapus.setUnidadApu(getAuxunidrubro());
        this.analisisapus.setRubro(rubro);
          //los daos que necesito
          //daos para el analilsis ose la cabecera
         ApusDaoImpl apugenal=new ApusDaoImpl();
        equipoherrDaoImpl daoequipo=new equipoherrDaoImpl();
        manoobraDaoImpl manoobradao=new manoobraDaoImpl();
        materialDaoImpl materialdao=new materialDaoImpl();
       transporteDaoImpl transportedao=new transporteDaoImpl();
       
         this.transaction=this.session.beginTransaction();
         apugenal.insertarAPU(session, this.analisisapus);
         this.analisisapus=apugenal.getUltimoRegistroApu(session);
         this.equipherramientas=daoequipo.getUltimoRegistro(session);
         this.manoobras=manoobradao.getUltimoRegistro(session);
         this.materiales=materialdao.getUltimoRegistro(session);
         this.transportes=transportedao.getUltimoRegistro(session);
         
         //detalle equipos y herramientas
          for(EquipherrApu item : this.listaEquiposApus)
            {
                this.equipherramientas=daoequipo.getByIdEquipo(session,this.equipherramientas.getCodigoEqherr());
                item.setEquipoherramienta(this.equipherramientas);
               
                item.setAnalisispreciounitario(this.analisisapus);
                  apugenal.insert(this.session, item);
            }
         //detalle mano de obra
           for(ManoobraApu item : this.listaManoBra)
            {
                this.manoobras=manoobradao.getByIdManobra(session,this.manoobras.getCodigoManob());
                item.setManoobra(this.manoobras);
           
                 item.setAnalisispreciounitario(this.analisisapus);
                  apugenal.insertarManobra(this.session, item);
            }
         // detalle material
             for(MaterialApu item : this.listaMaterialApus)
            {
                this.materiales=materialdao.getByIdMaterial(session,this.materiales.getCodigoMat());
                item.setMaterial(this.materiales);
               
                 item.setAnalisispreciounitario(this.analisisapus);
                  apugenal.insertarMaterial(this.session, item);
            }
          //detalle transporte
            for(TransporteApu item : this.listaTransporteApus)
            {
                this.transportes=transportedao.getByIdTransporte(session,this.transportes.getCodigoTransp());
                item.setTransporte(this.transportes);
               item.setAnalisispreciounitario(this.analisisapus);
                apugenal.insertarTransporte(this.session, item);
                 
            }
           // informe();
            exportarPDF();
         this.listaEquiposApus=new ArrayList<>();
         this.listaManoBra= new ArrayList<>();
         this.listaMaterialApus=new ArrayList<>();
         this.listaTransporteApus=new ArrayList<>();
         this.analisisapus=new Analisispreciounitario();
         this.auxdesrubro="";
         this.auxcategoria="";
         this.auxiliarPorcenjate=null;
         this.auxiliarotroscostos=null;
         this.auxocidigo=0;
         this.auxunidrubro="";
         this.precioTotalEquipo=0.00;
         this.precioTotalmanoobra=0.00;
         this.precioTotalmaterial=0.00;
         this.precioTotaltransporte=0.00;
         this.costoaputotal=0.00;
         this.costoinAPu=0.00;
         this.totaldirAPU=0.00;
         this.rubro=new Rubro();
         this.escenariosapu=new Escenarioapu();
         
             
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Precio Unitario guardado correctamente"));
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                transaction.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error","No se puede guardar Precio Unitario ingrese correctamente los registros"));
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }  
        }
    
  //costos totales apus
 public void calcularCostosTotalesAPU()
    {
        try
        {   
           Double costodir1APu=new Double("0.00");
           Double costoinAPu=new Double("0.00");
           Double costosotrosindAPu=new Double("0.00");
           Double costoaputotal=new Double("0.00");
           // Analisispreciounitario apus=new Analisispreciounitario();
          
             costodir1APu=this.precioTotaltransporte+this.precioTotalEquipo+this.precioTotalmaterial+this.precioTotalmanoobra;
            costoinAPu= (costodir1APu*(this.auxiliarPorcenjate/100));
            costosotrosindAPu=this.auxiliarotroscostos;
            costoaputotal=costodir1APu+costoinAPu+costosotrosindAPu;
         //setear los totales del apu     
          this.analisisapus.setCostDirApu(costodir1APu);
          this.analisisapus.setCostIndApu(costoinAPu);
          this.analisisapus.setCostOtrosIndApu(costosotrosindAPu);
          this.analisisapus.setCostotApu(costoaputotal);
          this.analisisapus.setPorcenIndApu(auxiliarPorcenjate);
          //setear los totales de la pantalla
          this.setTotaldirAPU(costodir1APu);
          this.setCostoinAPu(costoinAPu);
          this.setCostoaputotal(costoaputotal);
     
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta5");
        }
        catch(Exception ex)
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
       //fin de costos totales apus   
 
 //reporte
 public void informe(){
  try {
        //Creamos una lista de los datos de la tabla "Proveedor" utilizando "List".
        //Iniciamos una transacción.
        this.transaction=this.session.beginTransaction();
        List<Analisispreciounitario> lista = (List<Analisispreciounitario>)session.createQuery("From Analisispreciounitario").list();
        Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("codigo_apu",this.analisisapus.getCodigoApu());
        //Utilizamos el método siguiente para cargar el reporte "ProveedorReport.jasper"
        //El "JRLoader.loadObject" es el cargador.
        JasperReport report  = (JasperReport)JRLoader.loadObject(ClassLoader.getSystemResource("Reportes/ReporteApu.jasper")); 
        //El método siguiente nos permite pasarle los datos al reporte utilizando JRBeanCollectionDataSource y como argumento la lista que creamos más arriba.
        //La lista posee todos los campos necesarios para pasarle datos al reporte.
        JasperPrint fillReport = JasperFillManager.fillReport(report, parametros,new JRBeanCollectionDataSource(lista));
        //El JasperViewer para visualizar, le pasamos como argumento nuestro "fillReport" de arriba.
        JasperViewer jviewer = new JasperViewer(fillReport,false);
 
  } catch (Exception e) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error","No se puede generar reporte"));
    }
 }
 
 
 
 //llamar reporte
 public void exportarPDF() throws JRException, IOException{
     
       this.transaction=this.session.beginTransaction();
        List<Analisispreciounitario> lista = (List<Analisispreciounitario>)session.createQuery("From Analisispreciounitario").list();
		Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("codigo_apu",this.analisisapus.getCodigoApu());
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/ReporteApu.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(lista));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=ReporteApu.pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
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

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

  

    public String getAuxdesrubro() {
        return auxdesrubro;
    }

    public void setAuxdesrubro(String auxdesrubro) {
        this.auxdesrubro = auxdesrubro;
    }

    public String getAuxunidrubro() {
        return auxunidrubro;
    }

    public void setAuxunidrubro(String auxunidrubro) {
        this.auxunidrubro = auxunidrubro;
    }

    public List<Rubro> getListaRubro() {
        return listaRubro;
    }

    public void setListaRubro(List<Rubro> listaRubro) {
        this.listaRubro = listaRubro;
    }

    public int getAuxocidigo() {
        return auxocidigo;
    }

    public void setAuxocidigo(int auxocidigo) {
        this.auxocidigo = auxocidigo;
    }

    public List<Analisispreciounitario> getListapus() {
        return listapus;
    }

    public void setListapus(List<Analisispreciounitario> listapus) {
        this.listapus = listapus;
    }

    public String getAuxcategoria() {
        return auxcategoria;
    }

    public void setAuxcategoria(String auxcategoria) {
        this.auxcategoria = auxcategoria;
    }

    public Categoriarubro getCatrubro() {
        return catrubro;
    }

    public void setCatrubro(Categoriarubro catrubro) {
        this.catrubro = catrubro;
    }

    
    public Double getTotaldirAPU() {
        return totaldirAPU;
    }

    public void setTotaldirAPU(Double totaldirAPU) {
        this.totaldirAPU = totaldirAPU;
    }

    public Double getAuxiliarPorcenjate() {
        return auxiliarPorcenjate;
    }

    public void setAuxiliarPorcenjate(Double auxiliarPorcenjate) {
        this.auxiliarPorcenjate = auxiliarPorcenjate;
    }

    public Double getCostoinAPu() {
        return costoinAPu;
    }

    public void setCostoinAPu(Double costoinAPu) {
        this.costoinAPu = costoinAPu;
    }

    public Double getCostoaputotal() {
        return costoaputotal;
    }

    public void setCostoaputotal(Double costoaputotal) {
        this.costoaputotal = costoaputotal;
    }

    public Double getAuxiliarotroscostos() {
        return auxiliarotroscostos;
    }

    public void setAuxiliarotroscostos(Double auxiliarotroscostos) {
        this.auxiliarotroscostos = auxiliarotroscostos;
    }

    ///probar cambio de datos
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        
            
          }        }

    public Escenarioapu getEscenariosapu() {
        return escenariosapu;
    }

    public void setEscenariosapu(Escenarioapu escenariosapu) {
        this.escenariosapu = escenariosapu;
    }
  

    
    
 
    
}
