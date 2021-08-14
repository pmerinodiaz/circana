/**
 * @(#)FrameOperacion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.util.Vector;
import java.awt.Container;
import java.awt.event.KeyEvent;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Clase que extiende de la clase JInternalFrame. Contiene todos los componentes
 * GUI del módulo "Operación" que tiene la aplicación. Gracias a estos
 * componentes el usuario puede interactuar en forma simple con las opciones que
 * contiene el Módulo "Operación". Estos elementos son los siguientes:
 * "Transportar", "Evolución", "Reporte" y "Configuración".
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see Vector
 * @see Container
 * @see KeyEvent
 * @see JInternalFrame
 * @see JTabbedPane
 * @see ImageIcon
 * @see JOptionPane
 * @see PanelEscritorio
 * @see panelTransportarOperacion
 * @see panelEvolucionOperacion
 * @see panelReporteOperacion
 * @see panelConfiguracionOperacion
 * @see InterfaceInternalFrame
 */
public class FrameOperacion extends JInternalFrame
	implements InterfaceInternalFrame
{
	/** Indice que indica el valor actual de la barra de progreso. */
	public static int indiceBarraProgreso;
	
	/** Apuntador al creador del FrameOperacion. */
	public PanelEscritorio panelEscritorio;
	
	/** La paleta que contiene la lista de paneles. */
	private JTabbedPane paleta;
	
	/** Algoritmo Genético de Transporte del modelo operativo. */
	private AlgoritmoGeneticoTransporte agTransporte;
	
	/** Algoritmo Genético de Ruteo del modelo operativo. */
	private AlgoritmoGeneticoRuta[] rutaNave;
	
	/** El proceso de segundo plano para ejecutar en el frame. */
	private ProcesoSegundoPlano procesoEjecutarAG;
	
	/** El individuo del algoritmo que contiene la mejor solución encontrada. */
	private IndividuoTransporte solucionOptimaTransporte;
	
	/** El panel dedicado a mostrar "Transportar". */
	public PanelTransportarOperacion panelTransportar;
	
	/** El panel dedicado a mostrar "Evolución". */
	public PanelEvolucionOperacion panelEvolucion;
	
	/** El panel dedicado a mostrar "Reporte". */
	public PanelReporteOperacion panelReporte;
	
	/** El panel dedicado a mostrar "Configuración". */
	public PanelConfiguracionOperacion panelConfiguracion;
	
	/** Indica la cantidad total de la barra de progreso. */
	public int cantidadTotal;
	
	/**
	 * Método constructor que invoca a los métodos que configuran el frame, los
	 * paneles y los eventos asociados de los componentes GUI que contiene este
	 * frame.
	 *
	 * @param panelEscritorio El objeto que hace referencia al escritorio de la
	 *                        aplicación principal.
	 */
	public FrameOperacion(PanelEscritorio panelEscritorio)
	{
		// Inicializar el puntero.
		this.panelEscritorio = panelEscritorio;
		
		// Configurar.
		configurarElementosEspeciales();
		configurarFrame();
		configurarComponentes();
	}
	
	/**
     * Método que configura los elementos especiales de este frame. Se crea
     * instancias del algoritmo genético del modelo operativo y se crea
     * instancias del proceso en segundo plano.
     */
	public void configurarElementosEspeciales()
	{
		iniciarAlgoritmoGenetico();
		procesoEjecutarAG = new ProcesoSegundoPlano(this);
	}
	
	/**
     * Método que crea instancias del algoritmo genético del modelo operativo.
     */
	public void iniciarAlgoritmoGenetico()
	{
		agTransporte = new AlgoritmoGeneticoTransporte(this);
		solucionOptimaTransporte = new IndividuoTransporte();
	}
	
	/**
	 * Método que configura las propiedades del frame. Se cambia el tamaño
	 * (ancho y alto), el icono y la ubicación.
	 */
	public void configurarFrame()
	{
		setTitle("Operación");
		setResizable(true);
		setClosable(false);
		setMaximizable(true);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("../img/circana_pro.gif"));
		setSize(this.panelEscritorio.getSize());
	}
	
	/**
	 * Método que configura los paneles del frame. Este frame contiene
	 * principalmente cuatro paneles: "Transporte", "Ejecutar", "Reporte" y
	 * "Configuración". Los paneles son adjuntados al frame en forma de paleta
	 * de paneles.
	 */
	public void configurarComponentes()
	{
		// Capturar el panel contenedor del frame.
		Container contenedor = getContentPane();
		
		// Crear la paleta para los paneles.
		paleta = new JTabbedPane();
		
		// Crear los paneles.
		panelTransportar = new PanelTransportarOperacion(this);
		panelEvolucion = new PanelEvolucionOperacion(this);
		panelReporte = new PanelReporteOperacion(this);
		panelConfiguracion = new PanelConfiguracionOperacion(this);
		
		// Incorporar los paneles al contenedor.
		paleta.addTab("Transportar",
					  new ImageIcon("../img/operacion_transportar.gif"),
					  panelTransportar,
					  "Transportar de la Operación");
		paleta.addTab("Evolución",
					  new ImageIcon("../img/operacion_evolucion.gif"),
					  panelEvolucion,
					  "Evolución de la Operación");
		paleta.addTab("Reporte",
					  new ImageIcon("../img/operacion_reporte.gif"),
					  panelReporte,
					  "Reporte de la Operación");
		paleta.addTab("Configuración",
					  new ImageIcon("../img/operacion_configuracion.gif"),
					  panelConfiguracion,
					  "Configuración de la Operación");
		
		// Adjuntar un comando único a cada panel.
		paleta.setMnemonicAt(0, KeyEvent.VK_T);
		paleta.setMnemonicAt(1, KeyEvent.VK_E);
		paleta.setMnemonicAt(2, KeyEvent.VK_R);
		paleta.setMnemonicAt(3, KeyEvent.VK_C);
		
		// Incorporar la paleta de paneles al contendor del frame.
		contenedor.add(paleta);
	}
	
	/**
	 * Método para realizar la ejecución del Algoritmo Genético. Este método es
	 * llamado cuando el usuario pulsa el botón transportar. Este método realiza
	 * los camios en el escritorio y en todos los paneles de la operación que
	 * dependen de si un proceso está corriendo o no. Finalmente se invoca al
	 * método que ejecuta el proceso en segundo plano. Desde el proceso de
	 * segundo plano se llama al método ejecutarAlgoritmoGenetico(), en donde se
	 * realiza el ciclo de evolución del AG.
	 */	
	public void transportar()
	{
		// Notificar que hay un proceso corriendo.
		panelEscritorio.frameCircanaPro.establecerProcesoCorriendo(true);
		
		// Deshabilitar los botones.
		panelTransportar.habilitarBotones(false);
		panelConfiguracion.habilitarBotones(false);
		
		// Limpiar el reporte.
		panelReporte.limpiarReporte();
		
		// Aceptar el problema de transporte.
		panelTransportar.aceptarTransporte();
		
		// Inicializar el AG de transporte.
		agTransporte.inicializarAG(panelEvolucion);
		
		// Invocar al proceso de segundo plano.
		procesoEjecutarAG.ejecutar(0);
	}
	
	/**
	 * Método en donde se realiza el ciclo de evolución de los algoritmos
	 * genéticos que resuelven el problema de transporte y ruteo. Este método es
	 * llamado desde la clase ProcesoSegundoPlano, con el motivo de que no
	 * bloquee los demas procesos que pudieran encontrarse en ejecución al mismo
	 * momento.
	 */
	public void ejecutarAlgoritmoGenetico()
	{
		// Ejecutar algoritmos genéticos.
		ejecutarAlgoritmoGeneticoTransporte();
		ejecutarAlgoritmoGeneticoRuta();
		
		// Habilitar botones.
		panelTransportar.habilitarBotones(true);
		panelConfiguracion.habilitarBotones(true);
		
		// Notificar que no hay un proceso corriendo.
		panelEscritorio.frameCircanaPro.establecerProcesoCorriendo(false);
		
		// Mostrar el reporte.
		panelReporte.mostrarReporte();
	}
	
	/**
	 * Método para realiza la ejecución del Algoritmo Genético de Transporte.
	 */
	private void ejecutarAlgoritmoGeneticoTransporte()
	{
		indiceBarraProgreso = 0;
		cantidadTotal = ConfiguracionAGT.numeroGeneraciones +
						(ConfiguracionAGT.l - 1) *
						 ConfiguracionAGR.numeroGeneraciones;
		Vector evolucion = new Vector();
		evolucion = agTransporte.ejecutarAG();
		solucionOptimaTransporte = agTransporte.encontrarOptimo();
		panelEvolucion.asignarEvolucion(evolucion);
		panelReporte.generarReporteTransporte();
	}
	
	/**
	 * Método para realiza la ejecución del Algoritmo Genético de Ruteo.
	 */
	private void ejecutarAlgoritmoGeneticoRuta()
	{
		rutaNave = new AlgoritmoGeneticoRuta[ConfiguracionAGT.l - 1];
		VectorRuta tour;
		IndividuoRuta optimo;
		String reporte = "";
		double indice;
		
		for(int k = 0; k < (ConfiguracionAGT.l - 1);k++)
		{	
			tour = obtenerVectorRutaNave(k);
			
			if(tour.size() > 0)
			{
				rutaNave[k] = new AlgoritmoGeneticoRuta(this, tour);
				rutaNave[k].inicializarAG();
				rutaNave[k].ejecutarAG();
				optimo = rutaNave[k].encontrarOptimo();
				
				reporte = reporte + "Nave : "
							+ ConfiguracionAGT.capacidad.obtenerElemento(k).
							obtenerDescripcion() + "\n"
							+ optimo.obtenerRuta().
							obtenerReporteRuta(k,
							solucionOptimaTransporte.obtenerSolucion()) + "\n"
							+ "Distancia Total: "
							+ Servicio.obtenerNumeroFormateado(rutaNave[k].
							evaluacionRuta(optimo.obtenerRuta()), 10)
							+ " Kms.";
				
				/*reporte = reporte + "Nave : '"
							+ ConfiguracionAGT.capacidad.obtenerElemento(k).
							obtenerDescripcion() + "'\n"
							+ optimo.obtenerRuta().obtenerRuta() + "\n"
							+ "Distancia Total: " 
							+ Servicio.obtenerNumeroFormateado(rutaNave[k].
							evaluacionRuta(optimo.obtenerRuta()), 10)
							+ " Kms.";*/
			}
			else
			{
				rutaNave[k] = new AlgoritmoGeneticoRuta(this, new VectorRuta());
				this.indiceBarraProgreso = this.indiceBarraProgreso +
										   ConfiguracionAGR.numeroGeneraciones;
				indice = ((double) this.indiceBarraProgreso /
								   this.cantidadTotal)*100;
				this.panelEscritorio.frameCircanaPro.panelEstado.
						cambiarBarraOperacion((int) indice);
			}
			
			reporte = reporte + "\n\n";
		}
		
		panelReporte.generarReporteRuta(reporte);
	}
	
	/**
	 * Método que obtiene los puntos recorridos por una nave o embarcación. Los 
	 * puntos visitados son obtenidos de acuerdo a la matriz solución obtenida.
	 *
	 * @param k Indica la embarcación analizada.
	 *
	 * @return tour Lista de nodos visitados.
	 */
	private VectorRuta obtenerVectorRutaNave(int k)
	{
		double sumatoria = 0;
		double[][][] matrizSolucion = solucionOptimaTransporte.obtenerSolucion();
		int i;
		int j;
		int codigo;
		int indice;
		String descripcion;
		String tipo;
		Coordenada coordenada;
		ElementoRuta elemento = new ElementoRuta();
		VectorRuta tour = new VectorRuta();
		
		// Incluir los nodos destinos o puntos de demanda.
		tipo = "Demanda";
		for(j = 0;j < (ConfiguracionAGT.n - 1);j++)
		{
			for(i = 0;i < (ConfiguracionAGT.m - 1);i++)
			{
				sumatoria = sumatoria + matrizSolucion[i][j][k];
			}
			
			if(sumatoria > 0)
			{
				codigo = ConfiguracionAGT.demanda.obtenerElemento(j).
						 obtenerCodigo();
				descripcion = ConfiguracionAGT.demanda.obtenerElemento(j).
							  obtenerDescripcion();
				coordenada = ConfiguracionAGT.demanda.obtenerElemento(j).
							 obtenerCoordenada();
				elemento = new ElementoRuta(-1, codigo, descripcion, tipo,
											coordenada);
				indice = ConfiguracionAGR.grafoCompleto.buscarElemento(elemento);
				
				if(indice >= 0)
					tour.agregar(ConfiguracionAGR.grafoCompleto.
								 obtenerElemento(indice));
				//else
				//	System.out.println("Elemento:\n" +
				//					   elemento.obtenerDescripcion());
			}
			sumatoria = 0;
		}
		
		// Incluir los nodos origenes o caladeros
		tipo = "Oferta";
		for(i = 0;i < (ConfiguracionAGT.m - 1);i++)
		{
			for(j = 0;j < (ConfiguracionAGT.n - 1);j++)
			{
				sumatoria = sumatoria + matrizSolucion[i][j][k];
			}
			
			if(sumatoria > 0)
			{
				codigo = ConfiguracionAGT.oferta.obtenerElemento(i).
						 obtenerCodigo();
				descripcion = ConfiguracionAGT.oferta.obtenerElemento(i).
							  obtenerDescripcion();
				coordenada = ConfiguracionAGT.oferta.obtenerElemento(i).
							 obtenerCoordenada();
				elemento = new ElementoRuta(-1, codigo, descripcion, tipo,
											coordenada);
				indice = ConfiguracionAGR.grafoCompleto.buscarElemento(elemento);
				
				if(indice >= 0)
					tour.agregar(ConfiguracionAGR.grafoCompleto.
								 obtenerElemento(indice));
				//else
				//	System.out.println("Elemento:\n" +
				//					   elemento.obtenerDescripcion());
			}
			sumatoria = 0;
		}
		
		return tour;
	}
	
	/**
	 * Método que retorna el individuo mejor adaptado en la población al final
	 * del ciclo. Este contiene la mejor solución encontrada por el algoritmo
	 * genético.
	 *
	 * @return solucionOptimaTransporte Solución suboptimal generada por el AGT.
	 */
	public IndividuoTransporte obtenerSolucionTransporte()
	{
		return solucionOptimaTransporte;
	}
	
	/**
	 * Método que retorna un arreglo el algoritmo genético de cada nave.
	 *
	 * @return rutaNave Arreglo con las rutas seguidas por cada nave.
	 */
	public AlgoritmoGeneticoRuta[] obtenerAGR()
	{
		return rutaNave;
	}
	
	/**
     * Método que carga la información del proyecto en el frame interno del
     * proyecto. La información cargada se encuentra almacenada en clase
     * ConfiguracionAGT.
     */
	public void cargarConfiguracion()
	{
		panelReporte.cargarReporte();
		panelConfiguracion.subpanelConfiguracionAGT.cargarConfiguracion();
		panelConfiguracion.subpanelConfiguracionAGR.cargarConfiguracion();
		iniciarAlgoritmoGenetico();
	}
	
	/**
     * Método que carga la información con respecto al proyecto que se encuentra
     * en la clase estática Proyecto. De esta manera se actualizan las opciones
     * que se encuetran relacioandas con el proyecto.
     */
	public void actualizarInformacionProyecto()
	{
		ConfiguracionAGT.establecerDiaAnio();
		ConfiguracionAGT.configurarRestricciones();
		ConfiguracionAGR.establecerTablaDistancia();
		
		// volver a crear los paneles
		panelTransportar = new PanelTransportarOperacion(this);
		panelEvolucion = new PanelEvolucionOperacion(this);
		
		// volver a incorporar los paneles
		paleta.setComponentAt(0, panelTransportar);
		paleta.setComponentAt(1, panelEvolucion);
		
		// inicializar el ag
		iniciarAlgoritmoGenetico();
	}
	
	/**
	 * Método que permite modificar los resultados obtenidos del módulo 
	 * operativo producto de una nueva simulación.
	 */
	public void modificarResultados()
	{
		// Limpiar los resultados anteriores en la base de datos.
		Proyecto.limpiarResultados(Proyecto.OPERACION, -1);
		actualizarInformacionProyecto();
	}
	
	/**
	 * Método que muestra la ventanita "Transportar" de Operación en el panel
	 * de escritorio. Se setea el foco en el panel "Transportar".
	 */
	public void elegirTransportar()
	{
		paleta.setSelectedIndex(0);
	}
	
	/**
	 * Método que muestra la ventanita "Evolución" de Operación en el panel de
	 * escritorio. Se setea el foco en el panel "Evolución".
	 */
	public void elegirEvolucion()
	{
		paleta.setSelectedIndex(1);
	}
	
	/**
	 * Método que muestra la ventanita "Reporte" de Operación en el panel de
	 * escritorio. Se setea el foco en el panel "Reporte".
	 */
	public void elegirReporte()
	{
		paleta.setSelectedIndex(2);
	}
	
	/**
	 * Método que muestra la ventanita "Configuración" de Operación en el panel
	 * de escritorio. Se setea el foco en el panel "Configuración".
	 */
	public void elegirConfiguracion()
	{
		paleta.setSelectedIndex(3);
	}
}