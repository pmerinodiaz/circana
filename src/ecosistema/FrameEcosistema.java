/**
 * @(#)FrameEcosistema.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.Container;
import java.awt.event.KeyEvent;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;

/**
 * Clase que extiende de la clase JInternalFrame. Esta clase contiene todos los
 * componentes gr�ficos del m�dulo ecosistema. Estos componentes permiten al
 * usuario interactuar en forma simple con las opciones que contiene el m�dulo
 * ecosistema. Las opciones que contiene el m�dulo ecosistema son las
 * siguientes: la din�mica espacial, la din�mica temporal, el reporte y la
 * configuraci�n. Cada una de ellas son incorporadas a este frame a trav�s de un
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
	
	/** El panel dedicado a mostrar la din�mica espacial del ecosistema. */
	public PanelEspacialEcosistema panelEspacialEcosistema;
	
	/** El panel dedicado a mostrar la din�mica temporal del ecosistema. */
	public PanelTemporalEcosistema panelTemporalEcosistema;
	
	/** El panel dedicado a mostrar el reporte del ecosistema. */
	public PanelReporteEcosistema panelReporteEcosistema;
	
	/** El panel dedicado a mostrar la configuraci�n del ecosistema. */
	public PanelConfiguracionEcosistema panelConfiguracionEcosistema;
	
	/** La paleta que contiene a todos los paneles del frame. */
	private JTabbedPane paleta;
	
	/** El tiempo usado en todo el m�dulo ecosistema. */
	private Tiempo tiempo;
	
	/** El aut�mata celular usado en todo el m�dulo ecosistema. */
	private AutomataCelular automataCelular;
	
	/** El estado actual de la simulaci�n. */
	private int estado;
	
	/** El proceso de segundo plano que hace la simulaci�n. */
	private ProcesoSegundoPlano procesoSimular;
	
	/**
	 * M�todo constructor que invoca a los m�todos que inician todos los
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
	 * M�todo en donde se inicializan los objetos que se usan en todo el m�dulo
	 * ecosistema. Estos objetos son los siguientes: el tiempo, el mapa, el
	 * aut�mata celular, el estado y el proceso en segundo plano.
	 */
	public void configurarElementosEspeciales()
	{
		// Inicializar el tiempo.
		tiempo = new Tiempo();
		
		// Iniciar el mapa.
		Mapa.iniciar(tiempo);
		
		// Inicializar el aut�mata celular.
		automataCelular = new AutomataCelular(tiempo);
		
		// Iniciar el estado.
		estado = ProcesoSegundoPlano.DETENIDO;
		
		// Iniciar el proceso de segundo plano.
		procesoSimular = new ProcesoSegundoPlano(this);
	}
	
	/**
	 * M�todo en donde se configuran algunas propiedades de este frame. Las
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
	 * M�todo en donde se configuran los componentes de este frame. Estos
	 * componentes son los siguientes: el panel de la din�mica espacial, el
	 * panel de la din�mica temporal,el panel del reporte y el panel de la
	 * configuraci�n. Los paneles son adjuntados al frame atrav�s de una paleta
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
		paleta.addTab("Din�mica Espacial",
			new ImageIcon("../img/ecosistema_espacial.gif"),
			panelEspacialEcosistema,
			"Din�mica Espacial del Ecosistema");
		paleta.addTab("Din�mica Temporal",
			new ImageIcon("../img/ecosistema_temporal.gif"),
			panelTemporalEcosistema,
			"Din�mica Temporal del Ecosistema");
		paleta.addTab("Reporte",
			new ImageIcon("../img/ecosistema_reporte.gif"),
			panelReporteEcosistema,
			"Reporte del Ecosistema");
		paleta.addTab("Configuraci�n",
			new ImageIcon("../img/ecosistema_configuracion.gif"),
			panelConfiguracionEcosistema,
			"Configuraci�n del Ecosistema");
		
		// Adjuntar un comando �nico a cada panel de la paleta.
		paleta.setMnemonicAt(0, KeyEvent.VK_E);
		paleta.setMnemonicAt(1, KeyEvent.VK_T);
		paleta.setMnemonicAt(2, KeyEvent.VK_R);
		paleta.setMnemonicAt(3, KeyEvent.VK_O);
		
		// Incorporar la paleta al contendor.
		contenedor.add(paleta);
	}
	
	/**
	 * M�todo que inicia el proceso de simulaci�n de la din�mica espacial y
	 * din�mica temporal del recurso que se est� estudiando. Si el estado actual
	 * de la simulaci�n es detenida, entonces se cargan los recursos, se inicia
	 * el tiempo, se limpia el aut�mata celular y luego se carga el aut�mata
	 * celular. Se cambia el estado de la simulaci�n a iniciada, se habilitan
	 * los botones de control, se habilitan los botones de la configuraci�n y
	 * finalmente se invoca al m�todo que ejecuta el proceso en segundo plano.
	 * Desde el proceso de segundo plano se llama al m�todo simularDinamicas(),
	 * en donde se realiza el ciclo de simulaci�n.
	 */
	public void simular()
	{
		// Cuando el estado de la simulaci�n es detenida.
		if (estado == ProcesoSegundoPlano.DETENIDO)
		{
			// Cargar la informaci�n de todos los recursos, con respecto a la
			// fecha que tiene actualmente el proyecto.
			Recurso.cargarRecursos();
			
			// Iniciar el tiempo.
			tiempo.iniciar();
			
			// Limpiar el aut�mata celular.
			automataCelular.limpiar();
			
			// Cargar el aut�mata celular.
			automataCelular.cargar();
		}
		
		// Cambiar el estado de la simulaci�n a iniciada.
		estado = ProcesoSegundoPlano.INICIADO;
		
		// Cambiar el estado del proyecto.
		panelEscritorio.frameCircanaPro.establecerProcesoCorriendo(true);
		
		// Habilitar los botones de control.
		panelEspacialEcosistema.subpanelControlEcosistema.habilitarControles();
		
		// Habilitar los botones de la configuraci�n.
		panelConfiguracionEcosistema.habilitarBotones();
		
		// Invocar al proceso de segundo plano.
		procesoSimular.ejecutar(0);
	}
	
	/**
	 * M�todo que pausa el proceso de simulaci�n de la din�mica espacial y
	 * din�mica temporal del recurso que se est� estudiando. Se cambia el estado
	 * de la simulaci�n a pausada y se deshabilitan los botones de control.
	 */
	public void pausar()
	{
		// Cambiar el estado de la simulaci�n a pausada.
		estado = ProcesoSegundoPlano.PAUSADO;
		
		// Deshabilitar los botones de control.
		panelEspacialEcosistema.subpanelControlEcosistema.habilitarControles();
	}
	
	/**
	 * M�todo que detiene el proceso de simulaci�n de la din�mica espacial y
	 * din�mica temporal del recurso que se est� estudiando. Se cambia el estado
	 * de la simulaci�n a detenida. Se habilitan los botones de control y se
	 * habilitan los botones de la configuraci�n.
	 */
	public void detener()
	{
		// Cambiar el estado de la simulaci�n a detenida.
		estado = ProcesoSegundoPlano.DETENIDO;
		
		// Cambiar el estado del proyecto.
		panelEscritorio.frameCircanaPro.establecerProcesoCorriendo(false);
		
		// Habilitar los botones de control.
		panelEspacialEcosistema.subpanelControlEcosistema.habilitarControles();
		
		// Habilitar los botones de la configuraci�n.
		panelConfiguracionEcosistema.habilitarBotones();
	}
	
	/**
	 * M�todo en donde se realiza el ciclo de simulaci�n de la din�mica espacial
	 * y din�mica temporal de los recursos que se est�n estudiando. Este m�todo
	 * es llamado desde la clase ProcesoSegundoPlano, con el motivo de que no
	 * bloquee los demas procesos que pudieran encontrarse en ejecuci�n al mismo
	 * momento. En el ciclo de simulaci�n se repinta el panel del mapa, se
	 * repinta el panel del tiempo, se evoluciona el aut�mata celular, se
	 * evoluciona el mapa y se incrementa el tiempo.
	 */
	public void simularDinamicas()
	{
		// Calcular la cantidad de d�as de la simulaci�n.
		double cantidadDias = (double) Servicio.obtenerDias(
							  tiempo.obtenerFechaInicial(),
							  tiempo.obtenerFechaFinal());
		
		// Ciclo en donde se realiza la simulaci�n.
		while ((tiempo.esMenorIgualActualFinal()) &&
			   (estado == ProcesoSegundoPlano.INICIADO))
		{
			// Pintar la din�mica espacial.
			panelEspacialEcosistema.subpanelMapaEcosistema.repaint();
			
			// Pintar la din�mica temporal.
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
			
			// Evolucionar el aut�mata celular.
			automataCelular.evolucionar();
			
			// Evolucionar el mapa.
			Mapa.evolucionar();
			
			// Avanzar en el tiempo.
			tiempo.incrementar();
			
			// Escuchar a otros procesos.
			ProcesoSegundoPlano.dormir();
		}
		
		// Cuando el estado de la simulaci�n es iniciada.
		if (estado == ProcesoSegundoPlano.INICIADO)
		{
			// Detener la simulaci�n.
			detener();
			
			// Mostrar el reporte.
			panelReporteEcosistema.mostrarReporte();
		}
	}
	
	/**
	 * M�todo que muestra la ventanita din�mica espacial del ecosistema en el
	 * panel de escritorio. Se setea el foco en el panel din�mica espacial.
	 */
	public void elegirEspacial()
	{
		paleta.setSelectedIndex(0);
	}
	
	/**
	 * M�todo que muestra la ventanita din�mica temporal del ecosistema en el
	 * panel de escritorio. Se setea el foco en el panel din�mica temporal.
	 */
	public void elegirTemporal()
	{
		paleta.setSelectedIndex(1);
	}
	
	/**
	 * M�todo que muestra la ventanita reporte del ecosistema en el panel de
	 * escritorio. Se setea el foco en el panel reporte.
	 */
	public void elegirReporte()
	{
		paleta.setSelectedIndex(2);
	}
	
	/**
	 * M�todo que muestra la ventanita configuraci�n del ecosistema en el panel
	 * de escritorio. Se setea el foco en el panel configuraci�n.
	 */
	public void elegirConfiguracion()
	{
		paleta.setSelectedIndex(3);
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tiempo.
	 *
	 * return tiempo El valor del atributo tiempo.
	 */
	public Tiempo obtenerTiempo()
	{
		return tiempo;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo automataCelular.
	 *
	 * return automataCelular El valor del atributo automataCelular.
	 */
	public AutomataCelular obtenerAutomataCelular()
	{
		return automataCelular;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo estado.
	 *
	 * return estado El valor del atributo estado.
	 */
	public int obtenerEstado()
	{
		return estado;
	}
	
	/**
     * M�todo que carga la informaci�n que est� disponible en las diversas
     * configuraciones, tanto del aut�mata celular y/o los recursos que se
     * encuentran en el proyecto. Lo importante en este m�todo es que carga la
     * configuraci�n del proyecto en el frame. Los datos que se cargan vienen
     * directamente de la base de datos, por lo que es conveniente no invocar
     * demasiado a este m�todo.
     */
	public void cargarConfiguracion()
	{
		panelReporteEcosistema.cargarReporte();
		panelConfiguracionEcosistema.cargarConfiguracion();
	}
	
	/**
     * M�todo que carga la informaci�n con respecto al proyecto que se encuentra 
     * en la clase est�tica Proyecto. De esta manera, se actualizan las opciones
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