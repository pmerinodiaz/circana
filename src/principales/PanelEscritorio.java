/**
 * @(#)PanelEscritorio.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que extiende de la clase JDesktopPane. Este escritorio corresponde al
 * panel que contiene a todos los m�dulos o modelos de la aplicaci�n. Los m�dulos
 * se cargan como objetos de la clase JInternalFrame en este escritorio.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Color
 * @see JScrollPane
 * @see JDesktopPane
 * @see JInternalFrame
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see FrameCircanaPro
 * @see FrameProyecto
 * @see FrameEcosistema
 * @see FrameEconomia
 * @see FrameOperacion
 * @see FrameIntegracion
 * @see InterfacePanel
 */
public class PanelEscritorio extends JDesktopPane implements InterfacePanel
{
	/** Puntero al frame que contiene a este panel. */
	public FrameCircanaPro frameCircanaPro;
	
	/** Para incorporar el frame proyecto al panel escritorio. */
	public FrameProyecto frameProyecto;
	
	/** Para incorporar el frame ecosistema al panel escritorio. */
	public FrameEcosistema frameEcosistema;
	
	/** Para incorporar el frame econom�a al panel escritorio. */
	public FrameEconomia frameEconomia;
	
	/** Para incorporar el frame operaci�n al panel escritorio. */
	public FrameOperacion frameOperacion;
	
	/** Para incorporar el frame integraci�n al panel escritorio. */
	public FrameIntegracion frameIntegracion;
	
	/**
	 * M�todo constructor que invoca a los m�todos que configuran el escritorio,
	 * sus componentes y los eventos asociados.
	 *
	 * @param frameCircanaPro Objeto que hace referencia al frame que contiene
	 *                        a este panel.
	 */
	public PanelEscritorio(FrameCircanaPro frameCircanaPro)
	{
		// Inicializar el puntero.
		this.frameCircanaPro = frameCircanaPro;
		
		// Configurar.
		configurarPanel();
		configurarComponentes();
	}
	
	/**
	 * M�todo en donde se configurar diversas propiedades que tiene este panel.
	 * Las opciones que se cambian son los atributos que se derivan de la
	 * clase JDesktopPane.
	 */
	public void configurarPanel()
	{
		// El tipo de borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear los bordes.
		TitledBorder titulo = new TitledBorder(borde, "", TitledBorder.LEFT,
											   TitledBorder.TOP);
		
		// Setear el borde.
		setBorder(titulo);
		
		// Cambiar el color para la aparencia trasera.
		setBackground(Color.GRAY);
		
		// Cambiar el tama�o del panel.
		setBounds(
			(int) (frameCircanaPro.getWidth() * frameCircanaPro.ANCHO_VISOR),
			(int) (frameCircanaPro.getHeight() * frameCircanaPro.ALTO_BOTONERA),
			(int) (frameCircanaPro.getWidth() * frameCircanaPro.ANCHO_ESCRITORIO),
			(int) (frameCircanaPro.getHeight() * frameCircanaPro.ALTO_ESCRITORIO));
		
		// Cambiar el estilo de "arrastre" usado en el panel de escritorio.
		setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	}
	
	/**
	 * M�todo en donde se configuran los componentes de este frame. Se que crea
	 * un nuevo proyecto de trabajo. Cuando se crea un nuevo proyecto de
	 * trabajo, se crean los modelos "Ecosistema", "Econom�a", "Operaci�n" e
	 * "Integraci�n". Finalmente, se establece si los frames ser�n incorporados
	 * o no a este escritorio.
	 */
	public void configurarComponentes()
	{
		// Crear el proyecto y los modelos.
		frameProyecto = new FrameProyecto(this);
		frameEcosistema = new FrameEcosistema(this);
		frameEconomia = new FrameEconomia(this);
		frameOperacion = new FrameOperacion(this);
		frameIntegracion = new FrameIntegracion(this);
		
		// Incorporar los frames.
		add(frameProyecto);
		add(frameEcosistema);
		add(frameEconomia);
		add(frameOperacion);
		add(frameIntegracion);
		
		// Habilitar los frames.
		habilitarFrames();
	}
	
	/**
	 * M�todo que adjunta los escuchadores de eventos a los componentes que
	 * tiene este panel. En particular, este panel no maneja eventos.
	 */
	public void configurarEventos()
	{
	}
	
	/**
	 * M�todo que habilita o deshabilita los frames al panel de escritorio.
	 * Espec�ficamente se establece la propiedad de visibilidad de los frames al
	 * panel escritorio, dependiendo de si se encuentra abierto o no un
	 * proyecto.
	 */
	public void habilitarFrames()
	{
		// Cuando no hay un proyecto abierto.
		if (!frameCircanaPro.obtenerProyectoAbierto())
		{
			// Hacer invisible los frames.
			frameProyecto.setVisible(false);
			frameEcosistema.setVisible(false);
			frameEconomia.setVisible(false);
			frameOperacion.setVisible(false);
			frameIntegracion.setVisible(false);
		}
		
		// Cuando hay un proyecto abierto.
		else
		{
			// Limpiar el a�tomata celular del frame ecosistema.
			frameEcosistema.obtenerAutomataCelular().limpiar();
			
			// Hacer visible los frames.
			frameProyecto.setVisible(true);
			frameEcosistema.setVisible(true);
			frameEconomia.setVisible(true);
			frameOperacion.setVisible(true);
			frameIntegracion.setVisible(true);
			
			// Posicionar en el primer frame.
			posicionarFrames(1);
		}
	}
	
	/**
     * M�todo que posiciona y ordena los frames de cierta manera elegida. Hay
     * tres tipos posibles para posicionar los frames que son: 1 (cascada),
     * 2 (horizontal) y 3 (vertical).
     *
     * @param tipoPosicion Indica la forma que se quere posicionar los frames.
     *					   Estas pueden ser: 1 (Cascada), 2 (Horizontal) y
     *					   3 (Vertical).
     */
	public void posicionarFrames(int tipoPosicion)
	{
		posicionarUnFrame(frameIntegracion,tipoPosicion, 1);
		posicionarUnFrame(frameOperacion, tipoPosicion, 2);
		posicionarUnFrame(frameEconomia, tipoPosicion, 3);
		posicionarUnFrame(frameEcosistema, tipoPosicion, 4);
		posicionarUnFrame(frameProyecto, tipoPosicion, 5);
	}
	
	/**
     * M�todo que posiciona y ordena en una forma determinda un frame
     * determinado dentro del panel escritorio. Hay tres tipos para posicionar
     * el frame. Estos tipos son los siguientes: 1 (cascada), 2 (horizontal) y
     * 3 (vertical).
     *
     * @param frame Referencia al frame que desea posicionar.
     * @param tipoPosicion Indica la forma que se quiere posicionar el frame.
     *					   Estas pueden ser: 1 (cascada), 2 (horizontal) y
     *                     3 (vertical).
     * @param orden Indica el orden en que debe ir posicionado este frame con
     *              respecto a los dem�s frames participantes.
     */
	private void posicionarUnFrame(JInternalFrame frame, int tipoPosicion,
								   int orden)
	{
		// Variables locales.
		int distancia;
		int factor;
		
		// Capturar el ancho y el alto del panel.
		int anchoPanel = getWidth(); 
		int altoPanel = getHeight();
		
		frame.setVisible(false);  
		
		// Seleccionando el frame.
		elegirFrame(frame);
		
		// Posionando el frame.
		switch (tipoPosicion) 
		{
	    	// Cascada.
	    	case 1:
	    	{	
	    		distancia = 10;
	    		factor = (orden - 1) * distancia;
	    		frame.setSize(anchoPanel - factor, altoPanel - factor);
	    		frame.setLocation(factor, factor);
	    		break;
	    	}
	    	
	    	// Mosaico horizontal.
	    	case 2:
	    	{
	    		distancia = altoPanel / 5;
	    		factor = (orden - 1) * distancia;
	    		frame.setSize(anchoPanel, distancia);
	    		frame.setLocation(0, factor);
	    		break;	
	    	}
	    	
	    	// Mosaico vertical.
	    	case 3:
	    	{
	   			distancia = anchoPanel / 5;
	   			factor = (orden - 1) * distancia;
	   			frame.setSize(distancia, altoPanel);
	   			frame.setLocation(factor, 0);
	   			break;
	     	}
	    }
	    
	    // Hacer visible el frame.
	    frame.setVisible(true);    
	}
	
	/**
     * M�todo en donde se selecciona un frame interno y se lo maximiza.
     * 
     * @param frame Frame interno del panel escritorio que se quiere elegir.
     */
	private void elegirFrame(JInternalFrame frame)
	{
		try
		{
			frame.setSelected(true);
			frame.setMaximum(true);
			frame.setMaximum(false);
		}
		catch (java.beans.PropertyVetoException e)
		{
			System.out.println ("Error al seleccionar el frame.");
		}
	}
	
	/**
     * M�todo que minimiza todos los frames internal de este panel.
     */
	public void minimizarFrames()
	{
		// Capturar el ancho del panel.
		int anchoPanel = getHeight();
		
		// Cambiar el tama�o de los frames.
		frameProyecto.setSize(160, 30);
		frameEcosistema.setSize(160, 30);
		frameEconomia.setSize(160, 30);
		frameOperacion.setSize(160, 30);
		frameIntegracion.setSize(160, 30);
		
		// Posicionar los frames.
		frameProyecto.setLocation(0, anchoPanel-30);
		frameEcosistema.setLocation(160, anchoPanel-30);
		frameEconomia.setLocation(320, anchoPanel-30);
		frameOperacion.setLocation(480, anchoPanel-30);
		frameIntegracion.setLocation(640, anchoPanel-30);
		
		// No seleccionar los frames.
		try
		{
			frameProyecto.setSelected(false);
			frameEcosistema.setSelected(false);
			frameEconomia.setSelected(false);
			frameOperacion.setSelected(false);
			frameIntegracion.setSelected(false);
		}
		catch (java.beans.PropertyVetoException e)
		{
			System.out.println ("Error al minimizar los frames.");
		}
	}
	
	/**
     * M�todo que carga las variables del proyecto en los frames de cada m�dulo.
     * Este m�todo es mucho m�s general y m�s costoso que el m�todo
     * actualizarInformacionProyecto, ya que el anterior m�todo nombrado carga 
     * simplemente las variables del proyecto en los frames. Este m�todo obtiene
     * los datos de las variables desde la base de datos.
     */
	public void cargarConfiguracion()
	{
		frameCircanaPro.cargarConfiguracion();
		frameProyecto.cargarConfiguracion();
		frameEcosistema.cargarConfiguracion();
		frameEconomia.cargarConfiguracion();
		frameOperacion.cargarConfiguracion();
		frameIntegracion.cargarConfiguracion();
	}
	
	/**
     * M�todo que actualiza las variables del proyecto en los frames de cada
     * m�dulo. Este m�todo es mucho m�s espec�fico y menos costoso que el
     * metodo cargarConfiguracion, ya que el anterior m�todo nombrado carga
     * adem�s las configuraciones del proyecto en los frames. Este m�todo solo
     * los actualiza.
     */
	public void actualizarInformacionProyecto()
	{
		frameProyecto.actualizarInformacionProyecto();
		frameEcosistema.actualizarInformacionProyecto();
		frameEconomia.actualizarInformacionProyecto();
		frameOperacion.actualizarInformacionProyecto();
		frameIntegracion.actualizarInformacionProyecto();
	}
	
	/**
	 * M�todo que elige el frame "Proyecto" dentro del panel de escritorio.
	 */
	public void elegirProyecto()
	{
		elegirFrame(frameProyecto);
	}
	
	/**
	 * M�todo que elige el frame "Ecosistema" dentro del panel de escritorio.
	 */
	public void elegirEcosistema()
	{
		elegirFrame(frameEcosistema);
		
	}
	
	/**
	 * M�todo que elige el frame "Econom�a" dentro del panel de escritorio.
	 */
	public void elegirEconomia()
	{
		elegirFrame(frameEconomia);
	}
	
	/**
	 * M�todo que elige el frame "Operaci�n" dentro del panel de escritorio.
	 */
	public void elegirOperacion()
	{
		elegirFrame(frameOperacion);
	}
	
	/**
	 * M�todo que elige el frame "Integraci�n" dentro del panel de escritorio.
	 */
	public void elegirIntegracion()
	{
		elegirFrame(frameIntegracion);
	}
	
	/**
	 * M�todo que muestra la ventanita "Configuraci�n" del Proyecto en el panel
	 * de escritorio. Se elige el frame proyecto y se setea el foco en el panel
	 * "Configuraci�n".
	 */
	public void elegirConfiguracionProyecto()
	{
		elegirProyecto();
		frameProyecto.elegirConfiguracion();
	}
	
	/**
	 * M�todo que muestra la ventanita "Informaci�n Opcional" del Proyecto en el
	 * panel de escritorio. Se elige el frame proyecto y se setea el foco en el
	 * panel "Informaci�n Opcional".
	 */
	public void elegirOpcionalProyecto()
	{
		elegirProyecto();
		frameProyecto.elegirOpcional();
	}
	
	/**
	 * M�todo que muestra la ventanita "Din�mica Espacial" del Ecosistema en el
	 * panel de escritorio. Se elige el frame ecosistema y se setea el foco en
	 * el panel "Din�mica Espacial".
	 */
	public void elegirEspacialEcosistema()
	{
		elegirEcosistema();
		frameEcosistema.elegirEspacial();
	}
	
	/**
	 * M�todo que muestra la ventanita "Din�mica Temporal" del Ecosistema en el
	 * panel de escritorio. Se elige el frame ecosistema y se setea el foco en
	 * el panel "Din�mica Temporal".
	 */
	public void elegirTemporalEcosistema()
	{
		elegirEcosistema();
		frameEcosistema.elegirTemporal();
	}
	
	/**
	 * M�todo que muestra la ventanita "Reporte" del Ecosistema en el panel de
	 * escritorio. Se elige el frame ecosistema y se setea el foco en el panel
	 * "Reporte".
	 */
	public void elegirReporteEcosistema()
	{
		elegirEcosistema();
		frameEcosistema.elegirReporte();
	}
	
	/**
	 * M�todo que muestra la ventanita "Configuraci�n" del Ecosistema en el
	 * panel de escritorio. Se elige el frame ecosistema y se setea el foco en
	 * el panel "Configuraci�n".
	 */
	public void elegirConfiguracionEcosistema()
	{
		elegirEcosistema();
		frameEcosistema.elegirConfiguracion();
	}
	
	/**
	 * M�todo que muestra la ventanita "Entrenar" de Econom�a en el panel de
	 * escritorio. Se elige el frame economia y se setea el foco en el panel
	 * "Entrenar".
	 */
	public void elegirEntrenarEconomia()
	{
		elegirEconomia();
		frameEconomia.elegirEntrenar();
	}
	
	/**
	 * M�todo que muestra la ventanita "Graficar" de Econom�a en el panel de
	 * escritorio. Se elige el frame economia y se setea el foco en el panel
	 * "Graficar".
	 */
	public void elegirGraficarEconomia()
	{
		elegirEconomia();
		frameEconomia.elegirGraficar();
	}
	
	/**
	 * M�todo que muestra la ventanita "Reporte" de Econom�a en el panel de
	 * escritorio. Se elige el frame economia y se setea el foco en el panel
	 * "Reporte".
	 */
	public void elegirReporteEconomia()
	{
		elegirEconomia();
		frameEconomia.elegirReporte();
	}
	
	/**
	 * M�todo que muestra la ventanita "Configuraci�n" del Econom�a en el panel
	 * de escritorio. Se elige el frame economia y se setea el foco en el panel
	 * "Configuraci�n".
	 */
	public void elegirConfiguracionEconomia()
	{
		elegirEconomia();
		frameEconomia.elegirConfiguracion();
	}
	
	/**
	 * M�todo que muestra la ventanita "Transportar" de Operaci�n en el panel de
	 * escritorio. Se elige el frame operaci�n y se setea el foco en el panel
	 * "Transportar".
	 */
	public void elegirTransportarOperacion()
	{
		elegirOperacion();
		frameOperacion.elegirTransportar();
	}
	
	/**
	 * M�todo que muestra la ventanita "Evoluci�n" de Operaci�n en el panel de
	 * escritorio. Se elige el frame operaci�n y se setea el foco en el panel
	 * "Evoluci�n".
	 */
	public void elegirEvolucionOperacion()
	{
		elegirOperacion();
		frameOperacion.elegirEvolucion();
	}
		
	/**
	 * M�todo que muestra la ventanita "Reporte" de Operaci�n en el panel de
	 * escritorio. Se elige el frame operaci�n y se setea el foco en el panel
	 * "Reporte".
	 */
	public void elegirReporteOperacion()
	{
		elegirOperacion();
		frameOperacion.elegirReporte();
	}
	
	/**
	 * M�todo que muestra la ventanita "Configuraci�n" de Operaci�n en el panel
	 * de escritorio. Se elige el frame operaci�n y se setea el foco en el panel
	 * "Configuraci�n".
	 */
	public void elegirConfiguracionOperacion()
	{
		elegirOperacion();
		frameOperacion.elegirConfiguracion();
	}
	
	/**
	 * M�todo que muestra la ventanita "Planificar" de Integraci�n en el
	 * panel de escritorio. Se elige el frame integraci�n y se setea el foco en
	 * el panel "Planificacion".
	 */
	public void elegirPlanificarIntegracion()
	{
		elegirIntegracion();
		frameIntegracion.elegirPlanificar();
	}
	
	/**
	 * M�todo que muestra la ventanita "Graficar" de Integraci�n en el panel de
	 * escritorio. Se elige el frame integraci�n y se setea el foco en el panel
	 * "Graficar".
	 */
	public void elegirGraficarIntegracion()
	{
		elegirIntegracion();
		frameIntegracion.elegirGraficar();
	}
	
	/**
	 * M�todo que muestra la ventanita "Reporte" de Integraci�n en el panel de
	 * escritorio. Se elige el frame integraci�n y se setea el foco en el panel
	 * "Reporte".
	 */
	public void elegirReporteIntegracion()
	{
		elegirIntegracion();
		frameIntegracion.elegirReporte();
	}
}