
package ec.com.sisapus.bean;

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
import ec.com.sisapus.modelo.Rubro;
import ec.com.sisapus.util.HibernateUtil;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;


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
    private Double CostoHora;
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
    //precios unitarios
    private Analisispreciounitario analisisapus;
    private List<Analisispreciounitario> listapus;
    private Double auxiliarPorcenjate;
    private Double auxiliarotroscostos;
    private Double totaldirAPU;
    private Double costoinAPu;
    private Double costoaputotal;
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

    public ApuBeanVista() {
        this.equipherramientas = new Equipoherramienta();
        this.listaEquiposApus = new ArrayList<>();
        this.manoobras = new Manoobra();
        this.listaManoBra = new ArrayList<>();
        this.materiales = new Material();
        this.listaMaterialApus = new ArrayList<>();
        this.transportes = new Transporte();
        this.listaTransporteApus = new ArrayList<>();
        this.rubro = new Rubro();
        this.analisisapus = new Analisispreciounitario();


        this.auxdesrubro = "";
        this.auxunidrubro = "";
        //inicializar precios totales
        //escenarios



    }
    
        public List<Analisispreciounitario> getlistarApus() {
        this.session = null;
        this.transaction = null;

        try {
            ApusDaoImpl daoapu = new ApusDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.listapus = daoapu.listarApus(this.session);
            this.transaction.commit();
            return this.listapus;
        } catch (Exception ex) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    ///funcion para agregar rubro
    public void agregarRubroApus(Integer idRubros) {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            rubroDaoImpl daorubro = new rubroDaoImpl();


            this.transaction = this.session.beginTransaction();

            this.rubro = daorubro.getByIdRubro(session, idRubros);

            this.setAuxocidigo(this.rubro.getCodigoRubro());
            this.setAuxdesrubro(this.rubro.getDetalleRubro());
            this.setAuxunidrubro(this.rubro.getUnidadRubro());
            this.setAuxcategoria(this.rubro.getCategoriarubro().getDescripcionCatRubro());


            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Rubro agregado"));

            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta4");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");


        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //  
    public void agregarListaEquiposApus(Integer idEquipos) {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            equipoherrDaoImpl daoequipo = new equipoherrDaoImpl();

            this.transaction = this.session.beginTransaction();
            this.equipherramientas = daoequipo.getByIdEquipo(session, idEquipos);
            //this.listaEquiposApus.add(new EquipherrApu( null,this.equipherramientas.getNombreEqherr(), null,this.equipherramientas.getCostohoraEqherr(),null, null, null, null));
            this.listaEquiposApus.add(new EquipherrApu(null, this.equipherramientas.getNombreEqherr(), null, this.equipherramientas.getCostohoraEqherr(), null, null, null, null));
            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Equipo/Herramienta agregado"));

            //el qu estaba
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");


        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }
    //funcion para retirar

    public void EliminarListaEquipo(String nom) {
        try {
            int i = 0;

            for (EquipherrApu item : this.listaEquiposApus) {
                if (item.getDescEqherrApu().equals(nom)) {
                    this.listaEquiposApus.remove(i);

                    break;
                }

                i++;
            }

            Double totalVenta = new Double("0");

            for (EquipherrApu item : this.listaEquiposApus) {
                Double costohora = item.getCantEqherrApu() * (new Double(item.getTarifaEqherrApu()));

                Double totalVentaPorProducto = (costohora * (new Double(item.getRendimEqherrApu())));
                item.setCostohoraEqherrApu(costohora);
                item.setCostotEqherrApu(totalVentaPorProducto);

                totalVenta = totalVenta + totalVentaPorProducto;
            }

            this.setPrecioTotalEquipo(totalVenta);
            this.analisisapus.setAnalApuEqherr(totalVenta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Equipos y Herramientas retirado de la lista"));

            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
    //calculo subtotal equipos

    public void calcularCostos() {



        try {
            Double totalVenta = new Double("0.00");

            for (EquipherrApu item : this.listaEquiposApus) {
                Double costohora = item.getCantEqherrApu() * (new Double(item.getTarifaEqherrApu()));

                Double totalVentaPorProducto = (costohora * (new Double(item.getRendimEqherrApu())));
                item.setCostohoraEqherrApu(costohora);
                item.setCostotEqherrApu(totalVentaPorProducto);

                totalVenta = totalVenta + totalVentaPorProducto;
            }

            this.setPrecioTotalEquipo(totalVenta);
            //    this.analisisapus.setAnalApuEqherr(totalVenta);
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    public void guardarequiposApus() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            equipoherrDaoImpl daoequipo = new equipoherrDaoImpl();
            ApusDaoImpl apusequip = new ApusDaoImpl();
            this.transaction = this.session.beginTransaction();
            this.equipherramientas = daoequipo.getUltimoRegistro(session);

            for (EquipherrApu item : this.listaEquiposApus) {
                this.equipherramientas = daoequipo.getByIdEquipo(session, this.equipherramientas.getCodigoEqherr());
                item.setEquipoherramienta(this.equipherramientas);

                apusequip.insert(this.session, item);
            }

            this.transaction.commit();
            // this.listaEquiposApus=new ArrayList<>();
            // this.equipherramientas=new Equipoherramienta();
            //this.precioTotalEquipo=0.0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Equipo y Herramientas guardado correctamente"));
        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.session != null) {
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
//material
    public void agregarListaManobraApu(Integer idmanobra) {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            manoobraDaoImpl daomano = new manoobraDaoImpl();


            this.transaction = this.session.beginTransaction();

            this.manoobras = daomano.getByIdManobra(session, idmanobra);

            this.listaManoBra.add(new ManoobraApu(null, this.manoobras.getNombreManob(), null, this.manoobras.getCostojrhManob(), null, null, null, null));
            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Mano de Obra agregado"));


            //el qu estaba
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta1");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
            //

        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

//
    //funcion para retirar
    public void EliminarListaManObra(String nom) {
        try {
            int i = 0;

            for (ManoobraApu item : this.listaManoBra) {
                if (item.getDescMoApu().equals(nom)) {
                    this.listaManoBra.remove(i);

                    break;
                }

                i++;
            }

            Double totalVenta1 = new Double("0.00");

            for (ManoobraApu item : this.listaManoBra) {
                Double costohora1 = item.getCantMoApu() * (new Double(item.getCostojrhMoApu()));

                Double totalVentaPorProducto1 = (costohora1 * (new Double(item.getRendimMoApu())));
                item.setCostohoraMoApu(costohora1);
                item.setCostotMoApu(totalVentaPorProducto1);

                totalVenta1 = totalVenta1 + totalVentaPorProducto1;
            }

            this.setPrecioTotalmanoobra(totalVenta1);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Mano de Obra retirado de la lista"));

            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta1");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta1");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    //calculo subtotal equipos
    public void calcularCostosManobra() {
        try {
            Double totalVenta1 = new Double("0.00");

            for (ManoobraApu item : this.listaManoBra) {
                Double costohora1 = item.getCantMoApu() * (new Double(item.getCostojrhMoApu()));

                Double totalVentaPorProducto1 = (costohora1 * (new Double(item.getRendimMoApu())));
                item.setCostohoraMoApu(costohora1);
                item.setCostotMoApu(totalVentaPorProducto1);

                totalVenta1 = totalVenta1 + totalVentaPorProducto1;
            }

            this.setPrecioTotalmanoobra(totalVenta1);

            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta1");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta1");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    //
    public void guardaremmt() {
        try {
            guardarequiposApus();
            guardarmanObraApus();
            guardarmaterialApus();
            guardarTransporteApus();
        } catch (Exception ex) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }

    }

    // 
    public void guardarmanObraApus() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            manoobraDaoImpl manoobradao = new manoobraDaoImpl();
            ApusDaoImpl apusmano = new ApusDaoImpl();


            this.transaction = this.session.beginTransaction();
            this.manoobras = manoobradao.getUltimoRegistro(session);

            for (ManoobraApu item : this.listaManoBra) {
                this.manoobras = manoobradao.getByIdManobra(session, this.manoobras.getCodigoManob());
                item.setManoobra(this.manoobras);
                apusmano.insertarManobra(this.session, item);
            }

            this.transaction.commit();
            // this.listaManoBra=new ArrayList<>();
            // this.manoobras=new Manoobra();
            // this.precioTotalmanoobra=0.0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Mano de Obra guardado correctamente"));
        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

//materiales    
    public void agregarListaMaterialApu(Integer idmaterial) {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            materialDaoImpl materialdao = new materialDaoImpl();



            this.transaction = this.session.beginTransaction();

            this.materiales = materialdao.getByIdMaterial(session, idmaterial);

            //this.listaManoBra.add(new ManoobraApu(null,this.manoobras.getNombreManob(),null,null,this.manoobras.getCostojrhManob(),null, null, null,null));
            this.listaMaterialApus.add(new MaterialApu(null, this.materiales.getNombreMat(), this.materiales.getUnidMat(), null, this.materiales.getPrecunitMat(), null, null));
            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Material agregado"));


            //el qu estaba
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta2");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
            //

        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

//
    //funcion para retirar
    public void EliminarListaMateriales(String nom) {
        try {
            int i = 0;

            for (MaterialApu item : this.listaMaterialApus) {
                if (item.getDescMatApu().equals(nom)) {
                    this.listaMaterialApus.remove(i);

                    break;
                }

                i++;
            }

            Double totalVenta1 = new Double("0.00");

            for (MaterialApu item : this.listaMaterialApus) {


                Double totalVentaPorProducto1 = (new Double(item.getCantMatApu())) * (new Double(item.getPreunitMatApu()));

                item.setCostotMatApu(totalVentaPorProducto1);

                totalVenta1 = totalVenta1 + totalVentaPorProducto1;
            }

            this.setPrecioTotalmaterial(totalVenta1);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Material retirado de la lista"));

            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta2");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta2");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    //calculo subtotal equipos
    public void calcularCostosMateriales() {
        try {
            Double totalVenta1 = new Double("0.00");

            for (MaterialApu item : this.listaMaterialApus) {


                Double totalVentaPorProducto1 = (new Double(item.getCantMatApu())) * (new Double(item.getPreunitMatApu()));

                item.setCostotMatApu(totalVentaPorProducto1);

                totalVenta1 = totalVenta1 + totalVentaPorProducto1;
            }

            this.setPrecioTotalmaterial(totalVenta1);

            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta2");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta2");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    // 
    public void guardarmaterialApus() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            materialDaoImpl materialdao = new materialDaoImpl();
            ApusDaoImpl apusmaterial = new ApusDaoImpl();


            this.transaction = this.session.beginTransaction();
            this.materiales = materialdao.getUltimoRegistro(session);

            for (MaterialApu item : this.listaMaterialApus) {
                this.materiales = materialdao.getByIdMaterial(session, this.materiales.getCodigoMat());
                item.setMaterial(this.materiales);
                apusmaterial.insertarMaterial(this.session, item);
            }

            this.transaction.commit();
            //    this.listaMaterialApus=new ArrayList<>();
            //   this.materiales=new Material();
            //  this.precioTotalmaterial=0.0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Material guardado correctamente"));
        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

//fin materiales
//transporte    
    public void agregarListaTransporteApu(Integer idtrans) {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            transporteDaoImpl transpdao = new transporteDaoImpl();


            this.transaction = this.session.beginTransaction();

            this.transportes = transpdao.getByIdTransporte(session, idtrans);

            //this.listaManoBra.add(new ManoobraApu(null,this.manoobras.getNombreManob(),null,null,this.manoobras.getCostojrhManob(),null, null, null,null));
            this.listaTransporteApus.add(new TransporteApu(null, this.transportes.getNombreTransp(), "GLB", null, this.transportes.getTarifaTransp(), null, null));
            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Transporte agregado"));

            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta3");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
            //

        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

//
    //funcion para retirar
    public void EliminarListaTransporte(String nom) {
        try {
            int i = 0;

            for (TransporteApu item : this.listaTransporteApus) {
                if (item.getDescTranApu().equals(nom)) {
                    this.listaTransporteApus.remove(i);

                    break;
                }

                i++;
            }

            Double totalVenta1 = new Double("0.00");

            for (TransporteApu item : this.listaTransporteApus) {


                Double totalVentaPorProducto1 = (new Double(item.getCantTranApu())) * (new Double(item.getTarifaTranApu()));

                item.setCostotTranApu(totalVentaPorProducto1);

                totalVenta1 = totalVenta1 + totalVentaPorProducto1;
            }

            this.setPrecioTotaltransporte(totalVenta1);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Transporte retirado de la lista"));

            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta3");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta3");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    //calculo subtotal equipos
    public void calcularCostosTransporte() {
        try {
            Double totalVenta1 = new Double("0.00");

            for (TransporteApu item : this.listaTransporteApus) {


                Double totalVentaPorProducto1 = (new Double(item.getCantTranApu())) * (new Double(item.getTarifaTranApu()));

                item.setCostotTranApu(totalVentaPorProducto1);

                totalVenta1 = totalVenta1 + totalVentaPorProducto1;
            }

            this.setPrecioTotaltransporte(totalVenta1);




            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta3");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta3");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    // 
    public void guardarTransporteApus() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            transporteDaoImpl transpodao = new transporteDaoImpl();
            ApusDaoImpl apustraanporte = new ApusDaoImpl();


            this.transaction = this.session.beginTransaction();
            this.transportes = transpodao.getUltimoRegistro(session);

            for (TransporteApu item : this.listaTransporteApus) {
                this.transportes = transpodao.getByIdTransporte(session, this.transportes.getCodigoTransp());
                item.setTransporte(this.transportes);
                apustraanporte.insertarTransporte(this.session, item);
            }

            this.transaction.commit();
            //this.listaTransporteApus=new ArrayList<>();
            //this.transportes=new Transporte();
            //this.precioTotaltransporte=0.0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Transporte guardado correctamente"));
        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

//fin transporte
//Analisis Precios Unitarios
    public void guardarAPU() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            ApusDaoImpl apusdao = new ApusDaoImpl();
            this.transaction = this.session.beginTransaction();
            this.equipapus = apusdao.getUltimoRegistroEqApu(session);

            for (Analisispreciounitario item : this.listapus) {
                item.setEquipherrApu(this.equipapus);
                apusdao.insertarAPU(this.session, item);
            }

            this.transaction.commit();
            this.listapus = new ArrayList<>();
            this.equipapus = new EquipherrApu();
            // this.precioTotaltransporte=0.0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Precio Unitario guardado correctamente"));
        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //costos totales apus
    public void calcularCostosTotalesAPU() {
        try {
            Double costodir1APu = new Double("0.00");
            Double costoinAPu = new Double("0.00");
            Double costosotrosindAPu = new Double("0.00");
            Double costoaputotal = new Double("0.00");
            Analisispreciounitario apus = new Analisispreciounitario();

            costodir1APu = this.precioTotaltransporte + this.precioTotalEquipo + this.precioTotalmaterial + this.precioTotalmanoobra;
            costoinAPu = (costodir1APu * (this.auxiliarPorcenjate / 100));
            costosotrosindAPu = this.auxiliarotroscostos;
            costoaputotal = costodir1APu + costoinAPu + costosotrosindAPu;
            //setear los totales del apu     
            apus.setCostDirApu(costodir1APu);
            apus.setCostIndApu(costoinAPu);
            apus.setCostOtrosIndApu(costosotrosindAPu);
            apus.setCostotApu(costoinAPu);
            //setear los totales de la pantalla
            this.setTotaldirAPU(costodir1APu);
            this.setCostoinAPu(costoinAPu);
            this.setCostoaputotal(costoaputotal);

            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta5");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
    //fin de costos totales apus   

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

        if (newValue != null && !newValue.equals(oldValue)) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);


        }
    }
}
