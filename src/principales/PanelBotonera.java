/**
 * @(#)PanelBotonera.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Dimension;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * Clase que extiende de la clase JToolBar. Este tool bar corresponde al panel
 * que contiene todos los botones de acceso rápido a los módulos de la
 * aplicación. Los botones que contiene la botonera son los siguientes: nuevo
 * proyecto, abrir proyecto, guardar proyecto, configuración del proyecto,
 * información opcional del proyecto, dinámica espacial del ecosistema, dinámica
 * temporal del ecosistema, reporte del ecosistema, configuración del
 * ecosistema, entrenar de economía, graficar de economía, reporte de economía,
 * configuración de economía, transporte de operación, ejecución de operación,
 * reporte de operación, configuración de operación, planificar de integración,
 * graficar de integración y reporte de integración.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Dimension
 * @see JToolBar
 * @see JButton
 * @see ImageIcon
 * @see FrameCircanaPro
 * @see EventoButton
 * @see InterfacePanel
 */
public class PanelBotonera extends JToolBar implements InterfacePanel
{
	/** Puntero al frame que contiene a este menú. */
	public FrameCircanaPro frameCircanaPro;
	
	/** El botón de acceso rápido nuevo proyecto. */
	private JButton botonNuevo;
	
	/** El botón de acceso rápido abrir proyecto. */
	private JButton botonAbrir;
	
	/** El botón de acceso rápido guardar proyecto. */
	private JButton botonGuardar;
	
	/** El botón de acceso rápido configuración del proyecto. */
	private JButton botonConfiguracionProyecto;
	
	/** El botón de acceso rápido información opcional del proyecto. */
	private JButton botonOpcionalProyecto;
	
	/** El botón de acceso rápido dinámica espacial del ecosistema. */
	private JButton botonEspacialEcosistema;
	
	/** El botón de acceso rápido dinámica temporal del ecosistema. */
	private JButton botonTemporalEcosistema;
	
	/** El botón de acceso rápido reporte del ecosistema. */
	private JButton botonReporteEcosistema;
	
	/** El botón de acceso rápido configuración del ecosistema. */
	private JButton botonConfiguracionEcosistema;
	
	/** El botón de acceso rápido entrenar de economía. */
	private JButton botonEntrenarEconomia;
	
	/** El botón de acceso rápido graficar de economía. */
	private JButton botonGraficarEconomia;
	
	/** El botón de acceso rápido reporte de economía. */
	private JButton botonReporteEconomia;
	
	/** El botón de acceso rápido configuración de economía. */
	private JButton botonConfiguracionEconomia;
	
	/** El botón de acceso rápido transportar de operación. */
	private JButton botonTransportarOperacion;
	
	/** El botón de acceso rápido evolución de operación. */
	private JButton botonEvolucionOperacion;
	
	/** El botón de acceso rápido reporte de operación. */
	private JButton botonReporteOperacion;
	
	/** El botón de acceso rápido configuración de operación. */
	private JButton botonConfiguracionOperacion;
	
	/** El botón de acceso rápido planificar de integración. */
	private JButton botonPlanificarIntegracion;
	
	/** El botón de acceso rápido graficar de integración. */
	private JButton botonGraficarIntegracion;
		
	/** El botón de acceso rápido reporte de integración. */
	private JButton botonReporteIntegracion;
	
	/**
	 * Método constructor que invoca a los métodos que configuran la botonera,
	 * sus botones y los eventos asociados.
	 *
	 * @param frameCircanaPro Objeto que hace referencia al frame que contiene
	 *                        a este panel.
	 */
	public PanelBotonera(FrameCircanaPro frameCircanaPro)
	{
		// Inicializar el puntero.
		this.frameCircanaPro = frameCircanaPro;
		
		// Configurar.
		configurarPanel();
		configurarComponentes();
		configurarEventos();
	}
	
	/**
	 * Método en donde se configurar diversas propiedades que tiene este tool
	 * bar. Las opciones que se cambian son los atributos que se derivan de la
	 * clase JToolBar.
	 */
	public void configurarPanel()
	{
		setName("Acceso Rápido");
		setBounds(0, 0,
			frameCircanaPro.getWidth(),
			(int)(frameCircanaPro.getHeight() * frameCircanaPro.ALTO_BOTONERA));
	}
	
	/**
	 * Método que configura los botones del panel de la botonera. Primero se
	 * crean los objetos de la clase JButton, luego se les adjunta a cada opción
	 * un comando único y finalmente se adjuntan las opciones al JToolBar.
	 */
	public void configurarComponentes()
	{
		// Separación entre items.
		Dimension separacion = new Dimension(2, 3);
						
		// Crear los botones de acceso rápido.
		botonNuevo = new JButton(
			new ImageIcon("../img/proyecto_nuevo.gif"));
		botonAbrir = new JButton(
			new ImageIcon("../img/proyecto_abrir.gif"));
		botonGuardar = new JButton(
			new ImageIcon("../img/proyecto_guardar.gif"));
		botonConfiguracionProyecto = new JButton(
			new ImageIcon("../img/proyecto_configuracion.gif"));
		botonOpcionalProyecto = new JButton(
			new ImageIcon("../img/proyecto_opcional.gif"));
		botonEspacialEcosistema = new JButton(
			new ImageIcon("../img/ecosistema_espacial.gif"));
		botonTemporalEcosistema = new JButton(
			new ImageIcon("../img/ecosistema_temporal.gif"));
		botonReporteEcosistema = new JButton(
			new ImageIcon("../img/ecosistema_reporte.gif"));
		botonConfiguracionEcosistema = new JButton(
			new ImageIcon("../img/ecosistema_configuracion.gif"));
		botonEntrenarEconomia = new JButton(
			new ImageIcon("../img/economia_entrenar.gif"));
		botonGraficarEconomia = new JButton(
			new ImageIcon("../img/economia_graficar.gif"));
		botonReporteEconomia = new JButton(
			new ImageIcon("../img/economia_reporte.gif"));
		botonConfiguracionEconomia = new JButton(
			new ImageIcon("../img/economia_configuracion.gif"));
		botonTransportarOperacion = new JButton(
			new ImageIcon("../img/operacion_transportar.gif"));
		botonEvolucionOperacion = new JButton(
			new ImageIcon("../img/operacion_evolucion.gif"));						
		botonReporteOperacion = new JButton(
			new ImageIcon("../img/operacion_reporte.gif"));
		botonConfiguracionOperacion = new JButton(
			new ImageIcon("../img/operacion_configuracion.gif"));
		botonPlanificarIntegracion = new JButton(
			new ImageIcon("../img/integracion_planificar.gif"));
		botonGraficarIntegracion = new JButton(
			new ImageIcon("../img/integracion_graficar.gif"));
		botonReporteIntegracion = new JButton(
			new ImageIcon("../img/integracion_reporte.gif"));
		
		// Setear el tool tips de los botones.
		botonNuevo.setToolTipText(
			"Nuevo Proyecto");
		botonAbrir.setToolTipText(
			"Abrir Proyecto");
		botonGuardar.setToolTipText(
			"Guardar Proyecto");
		botonConfiguracionProyecto.setToolTipText(
			"Configuración del Proyecto");
		botonOpcionalProyecto.setToolTipText(
			"Información Opcional del Proyecto");
		botonEspacialEcosistema.setToolTipText(
			"Dinámica Espacial del Ecosistema");
		botonTemporalEcosistema.setToolTipText(
			"Dinámica Temporal del Ecosistema");
		botonReporteEcosistema.setToolTipText(
			"Reporte del Ecosistema");
		botonConfiguracionEcosistema.setToolTipText(
			"Configuración del Ecosistema");
		botonEntrenarEconomia.setToolTipText(
			"Entrenar de la Economía");
		botonGraficarEconomia.setToolTipText(
			"Graficar de la Economía");
		botonReporteEconomia.setToolTipText(
			"Reporte de la Economía");
		botonConfiguracionEconomia.setToolTipText(
			"Configuración de la Economía");
		botonTransportarOperacion.setToolTipText(
			"Transportar de la Operación");
		botonEvolucionOperacion.setToolTipText(
			"Evolución de la Operación");
		botonReporteOperacion.setToolTipText(
			"Reporte de la Operación");
		botonConfiguracionOperacion.setToolTipText(
			"Configuración de la Operación");
		botonPlanificarIntegracion.setToolTipText(
			"Planificar de la Integración");
		botonGraficarIntegracion.setToolTipText(
			"Graficar de la Integración");
		botonReporteIntegracion.setToolTipText(
			"Reporte de la Integración");
		
		// Incorporar los botones al panel de la botonera.
		add(botonNuevo);
		add(botonAbrir);
		add(botonGuardar);
		addSeparator(separacion);
		add(botonConfiguracionProyecto);
		add(botonOpcionalProyecto);
		addSeparator(separacion);
		add(botonEspacialEcosistema);
		add(botonTemporalEcosistema);
		add(botonReporteEcosistema);
		add(botonConfiguracionEcosistema);
		addSeparator(separacion);
		add(botonEntrenarEconomia);
		add(botonGraficarEconomia);
		add(botonReporteEconomia);
		add(botonConfiguracionEconomia);
		addSeparator(separacion);		
		add(botonTransportarOperacion);
		add(botonEvolucionOperacion);
		add(botonReporteOperacion);
		add(botonConfiguracionOperacion);
		addSeparator(separacion);
		add(botonPlanificarIntegracion);
		add(botonGraficarIntegracion);
		add(botonReporteIntegracion);
		
		// Habilitar los botones.
		habilitarBotones();
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los botones de acceso
	 * rápido que tiene la botonera. En particular se incorpora el escuchador
	 * de eventos del tipo EventoButton a los JButton que tiene este JToolBar.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		
		// Incorporar el escuchador de eventos a los botones.
		botonNuevo.addActionListener(eventoButton);
		botonAbrir.addActionListener(eventoButton);
		botonGuardar.addActionListener(eventoButton);
		botonConfiguracionProyecto.addActionListener(eventoButton);
		botonOpcionalProyecto.addActionListener(eventoButton);
		botonEspacialEcosistema.addActionListener(eventoButton);
		botonTemporalEcosistema.addActionListener(eventoButton);
		botonReporteEcosistema.addActionListener(eventoButton);
		botonConfiguracionEcosistema.addActionListener(eventoButton);
		botonEntrenarEconomia.addActionListener(eventoButton);
		botonGraficarEconomia.addActionListener(eventoButton);
		botonReporteEconomia.addActionListener(eventoButton);
		botonConfiguracionEconomia.addActionListener(eventoButton);
		botonTransportarOperacion.addActionListener(eventoButton);
		botonEvolucionOperacion.addActionListener(eventoButton);
		botonReporteOperacion.addActionListener(eventoButton);
		botonConfiguracionOperacion.addActionListener(eventoButton);
		botonPlanificarIntegracion.addActionListener(eventoButton);
		botonGraficarIntegracion.addActionListener(eventoButton);
		botonReporteIntegracion.addActionListener(eventoButton);
	}
	
	/**
	 * Método que configura los botones de la botonera. Específicamente se 
	 * establece la propiedad de habilitación de los botones del tool bar,
	 * dependiendo de si se encuentra abierto o no un proyecto.
	 */
	public void habilitarBotones()
	{
		// Cuando no hay un proyecto abierto.
		if (!frameCircanaPro.obtenerProyectoAbierto())
		{
			botonNuevo.setEnabled(true);
			botonAbrir.setEnabled(true);
			botonGuardar.setEnabled(false);
			botonConfiguracionProyecto.setEnabled(false);
			botonOpcionalProyecto.setEnabled(false);
			botonEspacialEcosistema.setEnabled(false);
			botonTemporalEcosistema.setEnabled(false);
			botonReporteEcosistema.setEnabled(false);
			botonConfiguracionEcosistema.setEnabled(false);
			botonEntrenarEconomia.setEnabled(false);
			botonGraficarEconomia.setEnabled(false);
			botonReporteEconomia.setEnabled(false);
			botonConfiguracionEconomia.setEnabled(false);
			botonTransportarOperacion.setEnabled(false);
			botonEvolucionOperacion.setEnabled(false);
			botonReporteOperacion.setEnabled(false);
			botonConfiguracionOperacion.setEnabled(false);
			botonPlanificarIntegracion.setEnabled(false);
			botonGraficarIntegracion.setEnabled(false);
			botonReporteIntegracion.setEnabled(false);
		}
		// Cuando hay un proyecto abierto.
		else
		{
			botonNuevo.setEnabled(true);
			botonAbrir.setEnabled(true);
			botonGuardar.setEnabled(true);
			botonConfiguracionProyecto.setEnabled(true);
			botonOpcionalProyecto.setEnabled(true);
			botonEspacialEcosistema.setEnabled(true);
			botonTemporalEcosistema.setEnabled(true);
			botonReporteEcosistema.setEnabled(true);
			botonConfiguracionEcosistema.setEnabled(true);
			botonEntrenarEconomia.setEnabled(true);
			botonGraficarEconomia.setEnabled(true);
			botonReporteEconomia.setEnabled(true);
			botonConfiguracionEconomia.setEnabled(true);
			botonTransportarOperacion.setEnabled(true);
			botonEvolucionOperacion.setEnabled(true);
			botonReporteOperacion.setEnabled(true);
			botonConfiguracionOperacion.setEnabled(true);
			botonPlanificarIntegracion.setEnabled(true);
			botonGraficarIntegracion.setEnabled(true);
			botonReporteIntegracion.setEnabled(true);
		}
	}
	
	/**
	 * Método que obtiene el objeto botonNuevo.
	 *
	 * @return botonNuevo El botón de la botonera "Nuevo Proyecto".
	 */
	public JButton obtenerBotonNuevo()
	{
		return botonNuevo;
	}
	
	/**
	 * Método que obtiene el objeto botonAbrir.
	 *
	 * @return botonAbrir El botón de la botonera "Abrir Proyecto".
	 */
	public JButton obtenerBotonAbrir()
	{
		return botonAbrir;
	}
	
	/**
	 * Método que obtiene el objeto botonGuardar.
	 *
	 * @return botonGuardar El botón de la botonera "Guardar Proyecto".
	 */
	public JButton obtenerBotonGuardar()
	{
		return botonGuardar;
	}
	
	/**
	 * Método que obtiene el objeto botonConfiguracionProyecto
	 *
	 * @return botonConfiguracionProyecto El botón de la botonera "Configuración
	 *                                    del Proyecto".
	 */
	public JButton obtenerBotonConfiguracionProyecto()
	{
		return botonConfiguracionProyecto;
	}
	
	/**
	 * Método que obtiene el objeto botonOpcionalProyecto
	 *
	 * @return botonOpcionalProyecto El botón de la botonera "Información
	 *                               Opcional del Proyecto".
	 */
	public JButton obtenerBotonOpcionalProyecto()
	{
		return botonOpcionalProyecto;
	}
	
	/**
	 * Método que obtiene el objeto botonEspacialEcosistema.
	 *
	 * @return botonEspacialEcosistema El botón de la botonera "Dinámica
	 *                                 Espacial del Ecosistema".
	 */
	public JButton obtenerBotonEspacialEcosistema()
	{
		return botonEspacialEcosistema;
	}
	
	/**
	 * Método que obtiene el objeto botonTemporalEcosistema.
	 *
	 * @return botonTemporalEcosistema El botón de la botonera "Dinámica
	 *                                 Temporal del Ecosistema".
	 */
	public JButton obtenerBotonTemporalEcosistema()
	{
		return botonTemporalEcosistema;
	}
	
	/**
	 * Método que obtiene el objeto botonReporteEcosistema.
	 *
	 * @return botonReporteEcosistema El botón de la botonera "Reporte de
	 *                                Ecosistema".
	 */
	public JButton obtenerBotonReporteEcosistema()
	{
		return botonReporteEcosistema;
	}
	
	/**
	 * Método que obtiene el objeto botonConfiguracionEcosistema.
	 *
	 * @return botonConfiguracionEcosistema El botón de la botonera
	 *                                      "Configuración del Ecosistema".
	 */
	public JButton obtenerBotonConfiguracionEcosistema()
	{
		return botonConfiguracionEcosistema;
	}
	
	/**
	 * Método que obtiene el objeto botonEntrenarEconomia.
	 *
	 * @return botonEntrenarEconomia El botón de la botonera "Entrenar de
	 *                               Economía".
	 */
	public JButton obtenerBotonEntrenarEconomia()
	{
		return botonEntrenarEconomia;
	}
	
	/**
	 * Método que obtiene el objeto botonGraficarEconomia.
	 *
	 * @return botonGraficarEconomia El botón de la botonera "Graficar de
	 *                               Economía".
	 */
	public JButton obtenerBotonGraficarEconomia()
	{
		return botonGraficarEconomia;
	}
	
	/**
	 * Método que obtiene el objeto botonReporteEconomia.
	 *
	 * @return botonReporteEconomia El botón de la botonera "Reporte de
	 *                              Economía".
	 */
	public JButton obtenerBotonReporteEconomia()
	{
		return botonReporteEconomia;
	}
	
	/**
	 * Método que obtiene el objeto botonConfiguracionEconomia.
	 *
	 * @return botonConfiguracionEconomia El botón de la botonera "Configuración
	 *                                    de Economía".
	 */
	public JButton obtenerBotonConfiguracionEconomia()
	{
		return botonConfiguracionEconomia;
	}
	
	/**
	 * Método que obtiene el objeto botonTransportarOperacion.
	 *
	 * @return botonTransportarOperacion El botón de la botonera "Transportar
	 *                                   de Operación".
	 */
	public JButton obtenerBotonTransportarOperacion()
	{
		return botonTransportarOperacion;
	}
	
	/**
	 * Método que obtiene el objeto botonEvolucionOperacion.
	 *
	 * @return botonEvolucionOperacion El botón de la botonera "Evolución de la
	 *                                 Operación".
	 */
	public JButton obtenerBotonEvolucionOperacion()
	{
		return botonEvolucionOperacion;
	}
	
	/**
	 * Método que obtiene el objeto botonReporteOperacion.
	 *
	 * @return botonReporteOperacion El botón de la botonera "Reporte de
	 *                               Operación".
	 */
	public JButton obtenerBotonReporteOperacion()
	{
		return botonReporteOperacion;
	}
	
	/**
	 * Método que obtiene el objeto botonConfiguracionOperacion.
	 *
	 * @return botonConfiguracionOperacion El botón de la botonera
	 *                                    "Configuración de Operación".
	 */
	public JButton obtenerBotonConfiguracionOperacion()
	{
		return botonConfiguracionOperacion;
	}
	
	/**
	 * Método que obtiene el objeto botonPlanificarIntegracion
	 *
	 * @return botonPlanificarIntegracion El botón de la botonera "Planificar de
	 *                                    Integración".
	 */
	public JButton obtenerBotonPlanficarIntegracion()
	{
		return botonPlanificarIntegracion;
	}
	
	/**
	 * Método que obtiene el objeto botonGraficaIntegracion
	 *
	 * @return botonGraficarIntegracion El botón de la botonera "Graficar de
	 *                                  Integración".
	 */
	public JButton obtenerBotonGraficarIntegracion()
	{
		return botonGraficarIntegracion;
	}
	
	/**
	 * Método que obtiene el objeto botonReporteIntegracion
	 *
	 * @return botonReporteIntegracion El botón de la botonera "Reporte de
	 *                                 Integración".
	 */
	public JButton obtenerBotonReporteIntegracion()
	{
		return botonReporteIntegracion;
	}
}