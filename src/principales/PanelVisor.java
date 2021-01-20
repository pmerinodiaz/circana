/**
 * @(#)PanelVisor.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 * Clase que extiende de la clase JPanel. Este panel contiene el JTree que
 * permite visualizar el proyecto que est� siendo usado por el usuario de la
 * aplicaci�n y sus modelos asociados. Los modelos que contiene un proyecto son:
 * ecosistema, econom�a, operaci�n e integraci�n.
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
	
	/** El �rbol contenedor del proyecto. */
	private JTree archivos;
	
	/** El proyecto que contiene todos los modelos. */
	private DefaultMutableTreeNode proyecto;
	
	/** El modelo "Ecosistema" del proyecto. */
	private DefaultMutableTreeNode ecosistema;
	
	/** El modelo "Econom�a" del proyecto. */
	private DefaultMutableTreeNode economia;
	
	/** El modelo "Operaci�n" del proyecto. */
	private DefaultMutableTreeNode operacion;
	
	/** El modelo "Integraci�n" del proyecto. */
	private DefaultMutableTreeNode integracion;
	
	/** El item "Configuraci�n de Proyecto". */
	private DefaultMutableTreeNode configuracionProyecto;
	
	/** El item "Informaci�n Opcional de Proyecto". */
	private DefaultMutableTreeNode opcionalProyecto;
	
	/** El item "Din�mica Espacial de Ecosistema". */
	private DefaultMutableTreeNode espacialEcosistema;
	
	/** El item "Din�mica Temporal de Ecosistema". */
	private DefaultMutableTreeNode temporalEcosistema;
	
	/** El item "Reporte de Ecosistema". */
	private DefaultMutableTreeNode reporteEcosistema;
	
	/** El item "Configuraci�n de Ecosistema". */
	private DefaultMutableTreeNode configuracionEcosistema;
	
	/** El item "Entrenar de Econom�a". */
	private DefaultMutableTreeNode entrenarEconomia;
	
	/** El item "Graficar de Econom�a". */
	private DefaultMutableTreeNode graficarEconomia;
	
	/** El item "Reporte de Econom�a". */
	private DefaultMutableTreeNode reporteEconomia;
	
	/** El item "Configuraci�n de Econom�a". */
	private DefaultMutableTreeNode configuracionEconomia;
	
	/** El item "Transportar de Operaci�n". */
	private DefaultMutableTreeNode transportarOperacion;
	
	/** El item "Evoluci�n de Costos de Operaci�n". */
	private DefaultMutableTreeNode evolucionOperacion;
	
	/** El item "Reporte de Operaci�n". */
	private DefaultMutableTreeNode reporteOperacion;
	
	/** El item "Configuraci�n de Operaci�n". */
	private DefaultMutableTreeNode configuracionOperacion;
	
	/** El item "Planificar de Integraci�n". */
	private DefaultMutableTreeNode planificarIntegracion;
	
	/** El item "Graficar de Integraci�n". */
	private DefaultMutableTreeNode graficarIntegracion;
		
	/** El item "Reporte de Integraci�n". */
	private DefaultMutableTreeNode reporteIntegracion;
	
	/**
	 * M�todo constructor que invoca a los m�todos que configuran el panel visor
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
	 * M�todo en donde se configurar diversas propiedades que tiene este panel.
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
	 * M�todo que configura el panel visor del proyecto y el panel donde se
	 * incorpora este visor del proyecto.
	 */
	public void configurarComponentes()
	{
		// Crear el proyecto y sus modelos.
		proyecto = new DefaultMutableTreeNode("Proyecto");
		ecosistema = new DefaultMutableTreeNode("Ecosistema");
		economia = new DefaultMutableTreeNode("Econom�a");
		operacion = new DefaultMutableTreeNode("Operaci�n");
		integracion = new DefaultMutableTreeNode("Integraci�n");
		
		// Crear los items del "Proyecto".
		configuracionProyecto = new DefaultMutableTreeNode("Configuraci�n");
		opcionalProyecto = new DefaultMutableTreeNode("Informaci�n Opcional");
		
		// Crear los items del modelo "Ecosistema".
		espacialEcosistema = new DefaultMutableTreeNode("Din�mica Espacial");
		temporalEcosistema = new DefaultMutableTreeNode("Din�mica Temporal");
		reporteEcosistema = new DefaultMutableTreeNode("Reporte");
		configuracionEcosistema = new DefaultMutableTreeNode("Configuraci�n");
		
		// Crear los items del modelo "Econom�a".
		entrenarEconomia = new DefaultMutableTreeNode("Entrenar");
		graficarEconomia = new DefaultMutableTreeNode("Graficar");
		reporteEconomia = new DefaultMutableTreeNode("Reporte");
		configuracionEconomia = new DefaultMutableTreeNode("Configuraci�n");
		
		// Crear los items del modelo "Operaci�n".
		transportarOperacion = new DefaultMutableTreeNode("Transportar");
		evolucionOperacion = new DefaultMutableTreeNode("Evoluci�n");
		reporteOperacion = new DefaultMutableTreeNode("Reporte");
		configuracionOperacion = new DefaultMutableTreeNode("Configuraci�n");
		
		// Crear los items del modelo "Integraci�n".
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
		
		// Incorporar los items al modelo "Econom�a".
		economia.add(entrenarEconomia);
		economia.add(graficarEconomia);
		economia.add(reporteEconomia);
		economia.add(configuracionEconomia);
		
		// Incorporar los items al modelo "Operaci�n".		
		operacion.add(transportarOperacion);
		operacion.add(evolucionOperacion);
		operacion.add(reporteOperacion);
		operacion.add(configuracionOperacion);
		
		// Incorporar los items al modelo "Integraci�n".
		integracion.add(planificarIntegracion);
		integracion.add(graficarIntegracion);
		integracion.add(reporteIntegracion);
		
		// Incorporar los modelos al proyecto.
		proyecto.add(ecosistema);
		proyecto.add(economia);
		proyecto.add(operacion);
		proyecto.add(integracion);
		
		// Crear el �rbol del proyecto.
		archivos = new JTree(proyecto);
		
		// Crear el panel con el �rbol del proyecto.
		JScrollPane panelArchivos = new JScrollPane(archivos);
		
		// Incorporar el panel.
		add(panelArchivos);
		
		// Habilitar el proyecto.
		habilitarProyecto();
	}
	
	/**
	 * M�todo que adjunta los escuchadores de eventos a los componentes que
	 * tiene este visor del proyecto. En particular, se incorpora el evento
	 * EventoTree a el �rbol de archivos.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoTree evento = new EventoTree(this);
		
		// Incorporar el escuchador de eventos a los items.
		archivos.addTreeSelectionListener(evento);
	}
	
	/**
	 * M�todo que habilita o deshabilita el proyecto al panel visor de proyecto.
	 * Espec�ficamente se establece la propiedad de visibilidad del proyecto
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
	 * M�todo que obtiene el objeto archivos.
	 *
	 * @return archivos El �rbol de los archivos.
	 */
	public JTree obtenerArchivos()
	{
		return archivos;
	}
	
	/**
	 * M�todo que obtiene el objeto proyecto.
	 *
	 * @return proyecto El nodo proyecto.
	 */
	public DefaultMutableTreeNode obtenerProyecto()
	{
		return proyecto;
	}
	
	/**
	 * M�todo que obtiene el objeto ecosistema.
	 *
	 * @return ecosistema El nodo ecosistema.
	 */
	public DefaultMutableTreeNode obtenerEcosistema()
	{
		return ecosistema;
	}
	
	/**
	 * M�todo que obtiene El objeto economia.
	 *
	 * @return economia El nodo econom�a.
	 */
	public DefaultMutableTreeNode obtenerEconomia()
	{
		return economia;
	}
	
	/**
	 * M�todo que obtiene el objeto operaci�n.
	 *
	 * @return operacion El nodo operaci�n.
	 */
	public DefaultMutableTreeNode obtenerOperacion()
	{
		return operacion;
	}
	
	/**
	 * M�todo que obtiene el objeto integracion.
	 *
	 * @return integracion El nodo integraci�n.
	 */
	public DefaultMutableTreeNode obtenerIntegracion()
	{
		return integracion;
	}
	
	/**
	 * M�todo que obtiene el objeto configuracionProyecto.
	 *
	 * @return configuracionProyecto El nodo configraci�n del proyecto.
	 */
	public DefaultMutableTreeNode obtenerConfiguracionProyecto()
	{
		return configuracionProyecto;
	}
	
	/**
	 * M�todo que obtiene el objeto opcionalProyecto.
	 *
	 * @return opcionalProyecto El nodo informaci�n opcional del proyecto.
	 */
	public DefaultMutableTreeNode obtenerOpcionalProyecto()
	{
		return opcionalProyecto;
	}
	
	/**
	 * M�todo que obtiene el objeto espacialEcosistema.
	 *
	 * @return espacialEcosistema El nodo din�mica espacial.
	 */
	public DefaultMutableTreeNode obtenerEspacialEcosistema()
	{
		return espacialEcosistema;
	}
	
	/**
	 * M�todo que obtiene el objeto temporalEcosistema.
	 *
	 * @return temporalEcosistema El nodo din�mica temporal.
	 */
	public DefaultMutableTreeNode obtenerTemporalEcosistema()
	{
		return temporalEcosistema;
	}
	
	/**
	 * M�todo que obtiene el objeto reporteEcosistema.
	 *
	 * @return reporteEcosistema El nodo reporte.
	 */
	public DefaultMutableTreeNode obtenerReporteEcosistema()
	{
		return reporteEcosistema;
	}
	
	/**
	 * M�todo que obtiene el objeto configuracionEcosistema.
	 *
	 * @return configuracionEcosistema El nodo configuraci�n.
	 */
	public DefaultMutableTreeNode obtenerConfiguracionEcosistema()
	{
		return configuracionEcosistema;
	}
	
	/**
	 * M�todo que obtiene el objeto entrenarEconomia.
	 *
	 * @return entrenarEconomia El nodo entrenar.
	 */
	public DefaultMutableTreeNode obtenerEntrenarEconomia()
	{
		return entrenarEconomia;
	}
	
	/**
	 * M�todo que obtiene el objeto graficarEconomia.
	 *
	 * @return graficarEconomia El nodo graficar.
	 */
	public DefaultMutableTreeNode obtenerGraficarEconomia()
	{
		return graficarEconomia;
	}
	
	/**
	 * M�todo que obtiene el objeto reporteEconomia.
	 *
	 * @return reporteEconomia El nodo reporte.
	 */
	public DefaultMutableTreeNode obtenerReporteEconomia()
	{
		return reporteEconomia;
	}
	
	/**
	 * M�todo que obtiene el objeto configuracionEconomia.
	 *
	 * @return configuracionEconomia El nodo configuraci�n.
	 */
	public DefaultMutableTreeNode obtenerConfiguracionEconomia()
	{
		return configuracionEconomia;
	}
	
	/**
	 * M�todo que obtiene el objeto transportarOperacion.
	 *
	 * @return transportarOperacion El nodo transportar.
	 */
	public DefaultMutableTreeNode obtenerTransportarOperacion()
	{
		return transportarOperacion;
	}
	
	/**
	 * M�todo que obtiene el objeto evolucionOperacion.
	 *
	 * @return evolucionOperacion El nodo evoluci�n.
	 */
	public DefaultMutableTreeNode obtenerEvolucionOperacion()
	{
		return evolucionOperacion;
	}
	
	/**
	 * M�todo que obtiene el objeto reporteOperacion.
	 *
	 * @return reporteOperacion El nodo reporte.
	 */
	public DefaultMutableTreeNode obtenerReporteOperacion()
	{
		return reporteOperacion;
	}
	
	/**
	 * M�todo que obtiene el objeto configuracionOperacion.
	 *
	 * @return configuracionOperacion El nodo configuraci�n.
	 */
	public DefaultMutableTreeNode obtenerConfiguracionOperacion()
	{
		return configuracionOperacion;
	}
	
	/**
	 * M�todo que obtiene el objeto planificarIntegracion.
	 *
	 * @return planificarIntegracion El nodo planificar.
	 */
	public DefaultMutableTreeNode obtenerPlanificarIntegracion()
	{
		return planificarIntegracion;
	}
	
	/**
	 * M�todo que obtiene el objeto graficar Integracion.
	 *
	 * @return graficarIntegracion El nodo Graficar.
	 */
	public DefaultMutableTreeNode obtenerGraficarIntegracion()
	{
		return graficarIntegracion;
	}
	
	/**
	 * M�todo que obtiene el objeto reporteIntegracion.
	 *
	 * @return reporteIntegracion El nodo reporte.
	 */
	public DefaultMutableTreeNode obtenerReporteIntegracion()
	{
		return reporteIntegracion;
	}
}