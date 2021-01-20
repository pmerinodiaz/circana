/**
 * @(#)PanelVisor.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 * Clase que extiende de la clase JPanel. Este panel contiene el JTree que
 * permite visualizar el proyecto que está siendo usado por el usuario de la
 * aplicación y sus modelos asociados. Los modelos que contiene un proyecto son:
 * ecosistema, economía, operación e integración.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see GridLayout
 * @see JPanel
 * @see JTree
 * @see JScrollPane
 * @see DefaultMutableTreeNode
 * @see TreeSelectionModel
 * @see FrameCircanaPro
 * @see InterfacePanel
 */
public class PanelVisor extends JPanel implements InterfacePanel
{
	/** Puntero al frame que contiene a este panel. */
	public FrameCircanaPro frameCircanaPro;
	
	/** El árbol contenedor del proyecto. */
	private JTree archivos;
	
	/** El proyecto que contiene todos los modelos. */
	private DefaultMutableTreeNode proyecto;
	
	/** El modelo "Ecosistema" del proyecto. */
	private DefaultMutableTreeNode ecosistema;
	
	/** El modelo "Economía" del proyecto. */
	private DefaultMutableTreeNode economia;
	
	/** El modelo "Operación" del proyecto. */
	private DefaultMutableTreeNode operacion;
	
	/** El modelo "Integración" del proyecto. */
	private DefaultMutableTreeNode integracion;
	
	/** El item "Configuración de Proyecto". */
	private DefaultMutableTreeNode configuracionProyecto;
	
	/** El item "Información Opcional de Proyecto". */
	private DefaultMutableTreeNode opcionalProyecto;
	
	/** El item "Dinámica Espacial de Ecosistema". */
	private DefaultMutableTreeNode espacialEcosistema;
	
	/** El item "Dinámica Temporal de Ecosistema". */
	private DefaultMutableTreeNode temporalEcosistema;
	
	/** El item "Reporte de Ecosistema". */
	private DefaultMutableTreeNode reporteEcosistema;
	
	/** El item "Configuración de Ecosistema". */
	private DefaultMutableTreeNode configuracionEcosistema;
	
	/** El item "Entrenar de Economía". */
	private DefaultMutableTreeNode entrenarEconomia;
	
	/** El item "Graficar de Economía". */
	private DefaultMutableTreeNode graficarEconomia;
	
	/** El item "Reporte de Economía". */
	private DefaultMutableTreeNode reporteEconomia;
	
	/** El item "Configuración de Economía". */
	private DefaultMutableTreeNode configuracionEconomia;
	
	/** El item "Transportar de Operación". */
	private DefaultMutableTreeNode transportarOperacion;
	
	/** El item "Evolución de Costos de Operación". */
	private DefaultMutableTreeNode evolucionOperacion;
	
	/** El item "Reporte de Operación". */
	private DefaultMutableTreeNode reporteOperacion;
	
	/** El item "Configuración de Operación". */
	private DefaultMutableTreeNode configuracionOperacion;
	
	/** El item "Planificar de Integración". */
	private DefaultMutableTreeNode planificarIntegracion;
	
	/** El item "Graficar de Integración". */
	private DefaultMutableTreeNode graficarIntegracion;
		
	/** El item "Reporte de Integración". */
	private DefaultMutableTreeNode reporteIntegracion;
	
	/**
	 * Método constructor que invoca a los métodos que configuran el panel visor
	 * del proyecto, sus modelos y los eventos asociados.
	 *
	 * @param frameCircanaPro Objeto que hace referencia al frame que contiene
	 *                        a este panel.
	 */
	public PanelVisor(FrameCircanaPro frameCircanaPro)
	{
		// Inicializar el puntero.
		this.frameCircanaPro = frameCircanaPro;
		
		// Configurar.
		configurarPanel();
		configurarComponentes();
		configurarEventos();
	}
	
	/**
	 * Método en donde se configurar diversas propiedades que tiene este panel.
	 * Las opciones que se cambian son los atributos que se derivan de la
	 * clase JPanel.
	 */
	public void configurarPanel()
	{
		setLayout(new GridLayout(1, 1));
		setBounds(0,
			(int) (frameCircanaPro.getHeight() * frameCircanaPro.ALTO_BOTONERA),
			(int) (frameCircanaPro.getWidth() * frameCircanaPro.ANCHO_VISOR),
			(int) (frameCircanaPro.getHeight() * frameCircanaPro.ALTO_ESCRITORIO));
	}
	
	/**
	 * Método que configura el panel visor del proyecto y el panel donde se
	 * incorpora este visor del proyecto.
	 */
	public void configurarComponentes()
	{
		// Crear el proyecto y sus modelos.
		proyecto = new DefaultMutableTreeNode("Proyecto");
		ecosistema = new DefaultMutableTreeNode("Ecosistema");
		economia = new DefaultMutableTreeNode("Economía");
		operacion = new DefaultMutableTreeNode("Operación");
		integracion = new DefaultMutableTreeNode("Integración");
		
		// Crear los items del "Proyecto".
		configuracionProyecto = new DefaultMutableTreeNode("Configuración");
		opcionalProyecto = new DefaultMutableTreeNode("Información Opcional");
		
		// Crear los items del modelo "Ecosistema".
		espacialEcosistema = new DefaultMutableTreeNode("Dinámica Espacial");
		temporalEcosistema = new DefaultMutableTreeNode("Dinámica Temporal");
		reporteEcosistema = new DefaultMutableTreeNode("Reporte");
		configuracionEcosistema = new DefaultMutableTreeNode("Configuración");
		
		// Crear los items del modelo "Economía".
		entrenarEconomia = new DefaultMutableTreeNode("Entrenar");
		graficarEconomia = new DefaultMutableTreeNode("Graficar");
		reporteEconomia = new DefaultMutableTreeNode("Reporte");
		configuracionEconomia = new DefaultMutableTreeNode("Configuración");
		
		// Crear los items del modelo "Operación".
		transportarOperacion = new DefaultMutableTreeNode("Transportar");
		evolucionOperacion = new DefaultMutableTreeNode("Evolución");
		reporteOperacion = new DefaultMutableTreeNode("Reporte");
		configuracionOperacion = new DefaultMutableTreeNode("Configuración");
		
		// Crear los items del modelo "Integración".
		planificarIntegracion = new DefaultMutableTreeNode("Planificar");
		graficarIntegracion = new DefaultMutableTreeNode("Graficar");
		reporteIntegracion = new DefaultMutableTreeNode("Reporte");
		
		// Incorporar los items al "Proyecto".
		proyecto.add(configuracionProyecto);
		proyecto.add(opcionalProyecto);
		
		// Incorporar los items al modelo "Ecosistema".
		ecosistema.add(espacialEcosistema);
		ecosistema.add(temporalEcosistema);
		ecosistema.add(reporteEcosistema);
		ecosistema.add(configuracionEcosistema);
		
		// Incorporar los items al modelo "Economía".
		economia.add(entrenarEconomia);
		economia.add(graficarEconomia);
		economia.add(reporteEconomia);
		economia.add(configuracionEconomia);
		
		// Incorporar los items al modelo "Operación".		
		operacion.add(transportarOperacion);
		operacion.add(evolucionOperacion);
		operacion.add(reporteOperacion);
		operacion.add(configuracionOperacion);
		
		// Incorporar los items al modelo "Integración".
		integracion.add(planificarIntegracion);
		integracion.add(graficarIntegracion);
		integracion.add(reporteIntegracion);
		
		// Incorporar los modelos al proyecto.
		proyecto.add(ecosistema);
		proyecto.add(economia);
		proyecto.add(operacion);
		proyecto.add(integracion);
		
		// Crear el árbol del proyecto.
		archivos = new JTree(proyecto);
		
		// Crear el panel con el árbol del proyecto.
		JScrollPane panelArchivos = new JScrollPane(archivos);
		
		// Incorporar el panel.
		add(panelArchivos);
		
		// Habilitar el proyecto.
		habilitarProyecto();
	}
	
	/**
	 * Método que adjunta los escuchadores de eventos a los componentes que
	 * tiene este visor del proyecto. En particular, se incorpora el evento
	 * EventoTree a el árbol de archivos.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoTree evento = new EventoTree(this);
		
		// Incorporar el escuchador de eventos a los items.
		archivos.addTreeSelectionListener(evento);
	}
	
	/**
	 * Método que habilita o deshabilita el proyecto al panel visor de proyecto.
	 * Específicamente se establece la propiedad de visibilidad del proyecto
	 * en el panel visor, dependiendo de si se encuentra abierto o no un
	 * proyecto.
	 */
	public void habilitarProyecto()
	{
		// Cuando no hay un proyecto abierto.
		if (!frameCircanaPro.obtenerProyectoAbierto())
			archivos.setVisible(false);
		
		// Cuando hay un proyecto abierto.
		else archivos.setVisible(true);
	}
	
	/**
	 * Método que obtiene el objeto archivos.
	 *
	 * @return archivos El árbol de los archivos.
	 */
	public JTree obtenerArchivos()
	{
		return archivos;
	}
	
	/**
	 * Método que obtiene el objeto proyecto.
	 *
	 * @return proyecto El nodo proyecto.
	 */
	public DefaultMutableTreeNode obtenerProyecto()
	{
		return proyecto;
	}
	
	/**
	 * Método que obtiene el objeto ecosistema.
	 *
	 * @return ecosistema El nodo ecosistema.
	 */
	public DefaultMutableTreeNode obtenerEcosistema()
	{
		return ecosistema;
	}
	
	/**
	 * Método que obtiene El objeto economia.
	 *
	 * @return economia El nodo economía.
	 */
	public DefaultMutableTreeNode obtenerEconomia()
	{
		return economia;
	}
	
	/**
	 * Método que obtiene el objeto operación.
	 *
	 * @return operacion El nodo operación.
	 */
	public DefaultMutableTreeNode obtenerOperacion()
	{
		return operacion;
	}
	
	/**
	 * Método que obtiene el objeto integracion.
	 *
	 * @return integracion El nodo integración.
	 */
	public DefaultMutableTreeNode obtenerIntegracion()
	{
		return integracion;
	}
	
	/**
	 * Método que obtiene el objeto configuracionProyecto.
	 *
	 * @return configuracionProyecto El nodo configración del proyecto.
	 */
	public DefaultMutableTreeNode obtenerConfiguracionProyecto()
	{
		return configuracionProyecto;
	}
	
	/**
	 * Método que obtiene el objeto opcionalProyecto.
	 *
	 * @return opcionalProyecto El nodo información opcional del proyecto.
	 */
	public DefaultMutableTreeNode obtenerOpcionalProyecto()
	{
		return opcionalProyecto;
	}
	
	/**
	 * Método que obtiene el objeto espacialEcosistema.
	 *
	 * @return espacialEcosistema El nodo dinámica espacial.
	 */
	public DefaultMutableTreeNode obtenerEspacialEcosistema()
	{
		return espacialEcosistema;
	}
	
	/**
	 * Método que obtiene el objeto temporalEcosistema.
	 *
	 * @return temporalEcosistema El nodo dinámica temporal.
	 */
	public DefaultMutableTreeNode obtenerTemporalEcosistema()
	{
		return temporalEcosistema;
	}
	
	/**
	 * Método que obtiene el objeto reporteEcosistema.
	 *
	 * @return reporteEcosistema El nodo reporte.
	 */
	public DefaultMutableTreeNode obtenerReporteEcosistema()
	{
		return reporteEcosistema;
	}
	
	/**
	 * Método que obtiene el objeto configuracionEcosistema.
	 *
	 * @return configuracionEcosistema El nodo configuración.
	 */
	public DefaultMutableTreeNode obtenerConfiguracionEcosistema()
	{
		return configuracionEcosistema;
	}
	
	/**
	 * Método que obtiene el objeto entrenarEconomia.
	 *
	 * @return entrenarEconomia El nodo entrenar.
	 */
	public DefaultMutableTreeNode obtenerEntrenarEconomia()
	{
		return entrenarEconomia;
	}
	
	/**
	 * Método que obtiene el objeto graficarEconomia.
	 *
	 * @return graficarEconomia El nodo graficar.
	 */
	public DefaultMutableTreeNode obtenerGraficarEconomia()
	{
		return graficarEconomia;
	}
	
	/**
	 * Método que obtiene el objeto reporteEconomia.
	 *
	 * @return reporteEconomia El nodo reporte.
	 */
	public DefaultMutableTreeNode obtenerReporteEconomia()
	{
		return reporteEconomia;
	}
	
	/**
	 * Método que obtiene el objeto configuracionEconomia.
	 *
	 * @return configuracionEconomia El nodo configuración.
	 */
	public DefaultMutableTreeNode obtenerConfiguracionEconomia()
	{
		return configuracionEconomia;
	}
	
	/**
	 * Método que obtiene el objeto transportarOperacion.
	 *
	 * @return transportarOperacion El nodo transportar.
	 */
	public DefaultMutableTreeNode obtenerTransportarOperacion()
	{
		return transportarOperacion;
	}
	
	/**
	 * Método que obtiene el objeto evolucionOperacion.
	 *
	 * @return evolucionOperacion El nodo evolución.
	 */
	public DefaultMutableTreeNode obtenerEvolucionOperacion()
	{
		return evolucionOperacion;
	}
	
	/**
	 * Método que obtiene el objeto reporteOperacion.
	 *
	 * @return reporteOperacion El nodo reporte.
	 */
	public DefaultMutableTreeNode obtenerReporteOperacion()
	{
		return reporteOperacion;
	}
	
	/**
	 * Método que obtiene el objeto configuracionOperacion.
	 *
	 * @return configuracionOperacion El nodo configuración.
	 */
	public DefaultMutableTreeNode obtenerConfiguracionOperacion()
	{
		return configuracionOperacion;
	}
	
	/**
	 * Método que obtiene el objeto planificarIntegracion.
	 *
	 * @return planificarIntegracion El nodo planificar.
	 */
	public DefaultMutableTreeNode obtenerPlanificarIntegracion()
	{
		return planificarIntegracion;
	}
	
	/**
	 * Método que obtiene el objeto graficar Integracion.
	 *
	 * @return graficarIntegracion El nodo Graficar.
	 */
	public DefaultMutableTreeNode obtenerGraficarIntegracion()
	{
		return graficarIntegracion;
	}
	
	/**
	 * Método que obtiene el objeto reporteIntegracion.
	 *
	 * @return reporteIntegracion El nodo reporte.
	 */
	public DefaultMutableTreeNode obtenerReporteIntegracion()
	{
		return reporteIntegracion;
	}
}