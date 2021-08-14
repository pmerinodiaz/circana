/**
 * @(#)FrameEcosistema.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Container;
import java.awt.event.KeyEvent;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;

/**
 * Clase que extiende de la clase JInternalFrame. Esta clase contiene todos los
 * componentes gráficos del módulo ecosistema. Estos componentes permiten al
 * usuario interactuar en forma simple con las opciones que contiene el módulo
 * ecosistema. Las opciones que contiene el módulo ecosistema son las
 * siguientes: la dinámica espacial, la dinámica temporal, el reporte y la
 * configuración. Cada una de ellas son incorporadas a este frame a través de un
 * panel, el cual se adjunta a este frame mediante una paleta de paneles.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Container
 * @see KeyEvent
 * @see JInternalFrame
 * @see JTabbedPane
 * @see ImageIcon
 * @see PanelEscritorio
 * @see PanelEspacialEcosistema
 * @see PanelTemporalEcosistema
 * @see PanelCuotasEcosistema
 * @see PanelReporteEcosistema
 * @see PanelConfiguracionEcosistema
 * @see Tiempo
 * @see Mapa
 * @see AutomataCelular
 * @see ProcesoSegundoPlano
 * @see InterfaceInternalFrame
 */
public class FrameEcosistema extends JInternalFrame
	implements InterfaceInternalFrame
{
	/** El puntero al panel contenedor de este frame. */
	public PanelEscritorio panelEscritorio;
	
	/** El panel dedicado a mostrar la dinámica espacial del ecosistema. */
	public PanelEspacialEcosistema panelEspacialEcosistema;
	
	/** El panel dedicado a mostrar la dinámica temporal del ecosistema. */
	public PanelTemporalEcosistema panelTemporalEcosistema;
	
	/** El panel dedicado a mostrar el reporte del ecosistema. */
	public PanelReporteEcosistema panelReporteEcosistema;
	
	/** El panel dedicado a mostrar la configuración del ecosistema. */
	public PanelConfiguracionEcosistema panelConfiguracionEcosistema;
	
	/** La paleta que contiene a todos los paneles del frame. */
	private JTabbedPane paleta;
	
	/** El tiempo usado en todo el módulo ecosistema. */
	private Tiempo tiempo;
	
	/** El autómata celular usado en todo el módulo ecosistema. */
	private AutomataCelular automataCelular;
	
	/** El estado actual de la simulación. */
	private int estado;
	
	/** El proceso de segundo plano que hace la simulación. */
	private ProcesoSegundoPlano procesoSimular;
	
	/**
	 * Método constructor que invoca a los métodos que inician todos los
	 * componentes de este frame.
	 *
	 * @param panelEscritorio El objeto que hace referencia al panelEscritorio
	 *                        que contiene a este frame.
	 */
	public FrameEcosistema(PanelEscritorio panelEscritorio)
	{
		// Apuntar al panel contenedor de este frame.
		this.panelEscritorio = panelEscritorio;
		
		// Configurar.
		configurarElementosEspeciales();
		configurarFrame();
		configurarComponentes();
	}
	
	/**
	 * Método en donde se inicializan los objetos que se usan en todo el módulo
	 * ecosistema. Estos objetos son los siguientes: el tiempo, el mapa, el
	 * autómata celular, el estado y el proceso en segundo plano.
	 */
	public void configurarElementosEspeciales()
	{
		// Inicializar el tiempo.
		tiempo = new Tiempo();
		
		// Iniciar el mapa.
		Mapa.iniciar(tiempo);
		
		// Inicializar el autómata celular.
		automataCelular = new AutomataCelular(tiempo);
		
		// Iniciar el estado.
		estado = ProcesoSegundoPlano.DETENIDO;
		
		// Iniciar el proceso de segundo plano.
		procesoSimular = new ProcesoSegundoPlano(this);
	}
	
	/**
	 * Método en donde se configuran algunas propiedades de este frame. Las
	 * propiedades que se configuran corresponden a los atributos derivados
	 * desde la clase JInternalFrame.
	 */
	public void configurarFrame()
	{
		setTitle("Ecosistema");
		setResizable(true);
		setClosable(false);
		setMaximizable(true);
		setIconifiable(true);
		setSize(panelEscritorio.getSize());
		setFrameIcon(new ImageIcon("../img/circana_pro.gif"));
	}
	
	/**
	 * Método en donde se configuran los componentes de este frame. Estos
	 * componentes son los siguientes: el panel de la dinámica espacial, el
	 * panel de la dinámica temporal,el panel del reporte y el panel de la
	 * configuración. Los paneles son adjuntados al frame através de una paleta
	 * de paneles.
	 */
	public void configurarComponentes()
	{
		// Capturar el panel contenedor del frame.
		Container contenedor = getContentPane();
		
		// Crear la paleta para los paneles.
		paleta = new JTabbedPane();
		
		// Crear los paneles.
		panelEspacialEcosistema = new PanelEspacialEcosistema(this);
		panelTemporalEcosistema = new PanelTemporalEcosistema(this);
		panelReporteEcosistema = new PanelReporteEcosistema(this);
		panelConfiguracionEcosistema = new PanelConfiguracionEcosistema(this);
		
		// Incorporar los paneles a la paleta.
		paleta.addTab("Dinámica Espacial",
			new ImageIcon("../img/ecosistema_espacial.gif"),
			panelEspacialEcosistema,
			"Dinámica Espacial del Ecosistema");
		paleta.addTab("Dinámica Temporal",
			new ImageIcon("../img/ecosistema_temporal.gif"),
			panelTemporalEcosistema,
			"Dinámica Temporal del Ecosistema");
		paleta.addTab("Reporte",
			new ImageIcon("../img/ecosistema_reporte.gif"),
			panelReporteEcosistema,
			"Reporte del Ecosistema");
		paleta.addTab("Configuración",
			new ImageIcon("../img/ecosistema_configuracion.gif"),
			panelConfiguracionEcosistema,
			"Configuración del Ecosistema");
		
		// Adjuntar un comando único a cada panel de la paleta.
		paleta.setMnemonicAt(0, KeyEvent.VK_E);
		paleta.setMnemonicAt(1, KeyEvent.VK_T);
		paleta.setMnemonicAt(2, KeyEvent.VK_R);
		paleta.setMnemonicAt(3, KeyEvent.VK_O);
		
		// Incorporar la paleta al contendor.
		contenedor.add(paleta);
	}
	
	/**
	 * Método que inicia el proceso de simulación de la dinámica espacial y
	 * dinámica temporal del recurso que se está estudiando. Si el estado actual
	 * de la simulación es detenida, entonces se cargan los recursos, se inicia
	 * el tiempo, se limpia el autómata celular y luego se carga el autómata
	 * celular. Se cambia el estado de la simulación a iniciada, se habilitan
	 * los botones de control, se habilitan los botones de la configuración y
	 * finalmente se invoca al método que ejecuta el proceso en segundo plano.
	 * Desde el proceso de segundo plano se llama al método simularDinamicas(),
	 * en donde se realiza el ciclo de simulación.
	 */
	public void simular()
	{
		// Cuando el estado de la simulación es detenida.
		if (estado == ProcesoSegundoPlano.DETENIDO)
		{
			// Cargar la información de todos los recursos, con respecto a la
			// fecha que tiene actualmente el proyecto.
			Recurso.cargarRecursos();
			
			// Iniciar el tiempo.
			tiempo.iniciar();
			
			// Limpiar el autómata celular.
			automataCelular.limpiar();
			
			// Cargar el autómata celular.
			automataCelular.cargar();
		}
		
		// Cambiar el estado de la simulación a iniciada.
		estado = ProcesoSegundoPlano.INICIADO;
		
		// Cambiar el estado del proyecto.
		panelEscritorio.frameCircanaPro.establecerProcesoCorriendo(true);
		
		// Habilitar los botones de control.
		panelEspacialEcosistema.subpanelControlEcosistema.habilitarControles();
		
		// Habilitar los botones de la configuración.
		panelConfiguracionEcosistema.habilitarBotones();
		
		// Invocar al proceso de segundo plano.
		procesoSimular.ejecutar(0);
	}
	
	/**
	 * Método que pausa el proceso de simulación de la dinámica espacial y
	 * dinámica temporal del recurso que se está estudiando. Se cambia el estado
	 * de la simulación a pausada y se deshabilitan los botones de control.
	 */
	public void pausar()
	{
		// Cambiar el estado de la simulación a pausada.
		estado = ProcesoSegundoPlano.PAUSADO;
		
		// Deshabilitar los botones de control.
		panelEspacialEcosistema.subpanelControlEcosistema.habilitarControles();
	}
	
	/**
	 * Método que detiene el proceso de simulación de la dinámica espacial y
	 * dinámica temporal del recurso que se está estudiando. Se cambia el estado
	 * de la simulación a detenida. Se habilitan los botones de control y se
	 * habilitan los botones de la configuración.
	 */
	public void detener()
	{
		// Cambiar el estado de la simulación a detenida.
		estado = ProcesoSegundoPlano.DETENIDO;
		
		// Cambiar el estado del proyecto.
		panelEscritorio.frameCircanaPro.establecerProcesoCorriendo(false);
		
		// Habilitar los botones de control.
		panelEspacialEcosistema.subpanelControlEcosistema.habilitarControles();
		
		// Habilitar los botones de la configuración.
		panelConfiguracionEcosistema.habilitarBotones();
	}
	
	/**
	 * Método en donde se realiza el ciclo de simulación de la dinámica espacial
	 * y dinámica temporal de los recursos que se están estudiando. Este método
	 * es llamado desde la clase ProcesoSegundoPlano, con el motivo de que no
	 * bloquee los demas procesos que pudieran encontrarse en ejecución al mismo
	 * momento. En el ciclo de simulación se repinta el panel del mapa, se
	 * repinta el panel del tiempo, se evoluciona el autómata celular, se
	 * evoluciona el mapa y se incrementa el tiempo.
	 */
	public void simularDinamicas()
	{
		// Calcular la cantidad de días de la simulación.
		double cantidadDias = (double) Servicio.obtenerDias(
							  tiempo.obtenerFechaInicial(),
							  tiempo.obtenerFechaFinal());
		
		// Ciclo en donde se realiza la simulación.
		while ((tiempo.esMenorIgualActualFinal()) &&
			   (estado == ProcesoSegundoPlano.INICIADO))
		{
			// Pintar la dinámica espacial.
			panelEspacialEcosistema.subpanelMapaEcosistema.repaint();
			
			// Pintar la dinámica temporal.
			panelTemporalEcosistema.repaint();
			
			// Pintar el avance en la barra de progreso.
			panelEscritorio.frameCircanaPro.panelEstado.
				cambiarBarraEcosistema(
				(int)(((double) tiempo.obtenerTicReloj()/cantidadDias)*100));
			
			// Cambiar la fecha.
			panelEspacialEcosistema.subpanelControlEcosistema.
				cambiarEtiquetaFecha(
				Servicio.obtenerFecha(tiempo.obtenerFechaActual()));
			
			// Cambiar la hora.
			panelEspacialEcosistema.subpanelControlEcosistema.
				cambiarEtiquetaHora(
				Servicio.obtenerHora(tiempo.obtenerHoraActual()));
			
			// Evolucionar el autómata celular.
			automataCelular.evolucionar();
			
			// Evolucionar el mapa.
			Mapa.evolucionar();
			
			// Avanzar en el tiempo.
			tiempo.incrementar();
			
			// Escuchar a otros procesos.
			ProcesoSegundoPlano.dormir();
		}
		
		// Cuando el estado de la simulación es iniciada.
		if (estado == ProcesoSegundoPlano.INICIADO)
		{
			// Detener la simulación.
			detener();
			
			// Mostrar el reporte.
			panelReporteEcosistema.mostrarReporte();
		}
	}
	
	/**
	 * Método que muestra la ventanita dinámica espacial del ecosistema en el
	 * panel de escritorio. Se setea el foco en el panel dinámica espacial.
	 */
	public void elegirEspacial()
	{
		paleta.setSelectedIndex(0);
	}
	
	/**
	 * Método que muestra la ventanita dinámica temporal del ecosistema en el
	 * panel de escritorio. Se setea el foco en el panel dinámica temporal.
	 */
	public void elegirTemporal()
	{
		paleta.setSelectedIndex(1);
	}
	
	/**
	 * Método que muestra la ventanita reporte del ecosistema en el panel de
	 * escritorio. Se setea el foco en el panel reporte.
	 */
	public void elegirReporte()
	{
		paleta.setSelectedIndex(2);
	}
	
	/**
	 * Método que muestra la ventanita configuración del ecosistema en el panel
	 * de escritorio. Se setea el foco en el panel configuración.
	 */
	public void elegirConfiguracion()
	{
		paleta.setSelectedIndex(3);
	}
	
	/**
	 * Método que obtiene el valor del atributo tiempo.
	 *
	 * return tiempo El valor del atributo tiempo.
	 */
	public Tiempo obtenerTiempo()
	{
		return tiempo;
	}
	
	/**
	 * Método que obtiene el valor del atributo automataCelular.
	 *
	 * return automataCelular El valor del atributo automataCelular.
	 */
	public AutomataCelular obtenerAutomataCelular()
	{
		return automataCelular;
	}
	
	/**
	 * Método que obtiene el valor del atributo estado.
	 *
	 * return estado El valor del atributo estado.
	 */
	public int obtenerEstado()
	{
		return estado;
	}
	
	/**
     * Método que carga la información que está disponible en las diversas
     * configuraciones, tanto del autómata celular y/o los recursos que se
     * encuentran en el proyecto. Lo importante en este método es que carga la
     * configuración del proyecto en el frame. Los datos que se cargan vienen
     * directamente de la base de datos, por lo que es conveniente no invocar
     * demasiado a este método.
     */
	public void cargarConfiguracion()
	{
		panelReporteEcosistema.cargarReporte();
		panelConfiguracionEcosistema.cargarConfiguracion();
	}
	
	/**
     * Método que carga la información con respecto al proyecto que se encuentra 
     * en la clase estática Proyecto. De esta manera, se actualizan las opciones
     * del ecosistema que se encuentran relacionadas con el proyecto. Los datos
     * que se cargan vienen
     */
	public void actualizarInformacionProyecto()
	{
		// Volver a crear los paneles.
		panelEspacialEcosistema = new PanelEspacialEcosistema(this);
		panelTemporalEcosistema = new PanelTemporalEcosistema(this);
		
		// Volver a incorporar los paneles.
		paleta.setComponentAt(0, panelEspacialEcosistema);
		paleta.setComponentAt(1, panelTemporalEcosistema);
	}
}