/**
 * @(#)PanelBotonera.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.Dimension;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * Clase que extiende de la clase JToolBar. Este tool bar corresponde al panel
 * que contiene todos los botones de acceso r�pido a los m�dulos de la
 * aplicaci�n. Los botones que contiene la botonera son los siguientes: nuevo
 * proyecto, abrir proyecto, guardar proyecto, configuraci�n del proyecto,
 * informaci�n opcional del proyecto, din�mica espacial del ecosistema, din�mica
 * temporal del ecosistema, reporte del ecosistema, configuraci�n del
 * ecosistema, entrenar de econom�a, graficar de econom�a, reporte de econom�a,
 * configuraci�n de econom�a, transporte de operaci�n, ejecuci�n de operaci�n,
 * reporte de operaci�n, configuraci�n de operaci�n, planificar de integraci�n,
 * graficar de integraci�n y reporte de integraci�n.
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
	/** Puntero al frame que contiene a este men�. */
	public FrameCircanaPro frameCircanaPro;
	
	/** El bot�n de acceso r�pido nuevo proyecto. */
	private JButton botonNuevo;
	
	/** El bot�n de acceso r�pido abrir proyecto. */
	private JButton botonAbrir;
	
	/** El bot�n de acceso r�pido guardar proyecto. */
	private JButton botonGuardar;
	
	/** El bot�n de acceso r�pido configuraci�n del proyecto. */
	private JButton botonConfiguracionProyecto;
	
	/** El bot�n de acceso r�pido informaci�n opcional del proyecto. */
	private JButton botonOpcionalProyecto;
	
	/** El bot�n de acceso r�pido din�mica espacial del ecosistema. */
	private JButton botonEspacialEcosistema;
	
	/** El bot�n de acceso r�pido din�mica temporal del ecosistema. */
	private JButton botonTemporalEcosistema;
	
	/** El bot�n de acceso r�pido reporte del ecosistema. */
	private JButton botonReporteEcosistema;
	
	/** El bot�n de acceso r�pido configuraci�n del ecosistema. */
	private JButton botonConfiguracionEcosistema;
	
	/** El bot�n de acceso r�pido entrenar de econom�a. */
	private JButton botonEntrenarEconomia;
	
	/** El bot�n de acceso r�pido graficar de econom�a. */
	private JButton botonGraficarEconomia;
	
	/** El bot�n de acceso r�pido reporte de econom�a. */
	private JButton botonReporteEconomia;
	
	/** El bot�n de acceso r�pido configuraci�n de econom�a. */
	private JButton botonConfiguracionEconomia;
	
	/** El bot�n de acceso r�pido transportar de operaci�n. */
	private JButton botonTransportarOperacion;
	
	/** El bot�n de acceso r�pido evoluci�n de operaci�n. */
	private JButton botonEvolucionOperacion;
	
	/** El bot�n de acceso r�pido reporte de operaci�n. */
	private JButton botonReporteOperacion;
	
	/** El bot�n de acceso r�pido configuraci�n de operaci�n. */
	private JButton botonConfiguracionOperacion;
	
	/** El bot�n de acceso r�pido planificar de integraci�n. */
	private JButton botonPlanificarIntegracion;
	
	/** El bot�n de acceso r�pido graficar de integraci�n. */
	private JButton botonGraficarIntegracion;
		
	/** El bot�n de acceso r�pido reporte de integraci�n. */
	private JButton botonReporteIntegracion;
	
	/**
	 * M�todo constructor que invoca a los m�todos que configuran la botonera,
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
	 * M�todo en donde se configurar diversas propiedades que tiene este tool
	 * bar. Las opciones que se cambian son los atributos que se derivan de la
	 * clase JToolBar.
	 */
	public void configurarPanel()
	{
		setName("Acceso R�pido");
		setBounds(0, 0,
			frameCircanaPro.getWidth(),
			(int)(frameCircanaPro.getHeight() * frameCircanaPro.ALTO_BOTONERA));
	}
	
	/**
	 * M�todo que configura los botones del panel de la botonera. Primero se
	 * crean los objetos de la clase JButton, luego se les adjunta a cada opci�n
	 * un comando �nico y finalmente se adjuntan las opciones al JToolBar.
	 */
	public void configurarComponentes()
	{
		// Separaci�n entre items.
		Dimension separacion = new Dimension(2, 3);
						
		// Crear los botones de acceso r�pido.
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
			"Configuraci�n del Proyecto");
		botonOpcionalProyecto.setToolTipText(
			"Informaci�n Opcional del Proyecto");
		botonEspacialEcosistema.setToolTipText(
			"Din�mica Espacial del Ecosistema");
		botonTemporalEcosistema.setToolTipText(
			"Din�mica Temporal del Ecosistema");
		botonReporteEcosistema.setToolTipText(
			"Reporte del Ecosistema");
		botonConfiguracionEcosistema.setToolTipText(
			"Configuraci�n del Ecosistema");
		botonEntrenarEconomia.setToolTipText(
			"Entrenar de la Econom�a");
		botonGraficarEconomia.setToolTipText(
			"Graficar de la Econom�a");
		botonReporteEconomia.setToolTipText(
			"Reporte de la Econom�a");
		botonConfiguracionEconomia.setToolTipText(
			"Configuraci�n de la Econom�a");
		botonTransportarOperacion.setToolTipText(
			"Transportar de la Operaci�n");
		botonEvolucionOperacion.setToolTipText(
			"Evoluci�n de la Operaci�n");
		botonReporteOperacion.setToolTipText(
			"Reporte de la Operaci�n");
		botonConfiguracionOperacion.setToolTipText(
			"Configuraci�n de la Operaci�n");
		botonPlanificarIntegracion.setToolTipText(
			"Planificar de la Integraci�n");
		botonGraficarIntegracion.setToolTipText(
			"Graficar de la Integraci�n");
		botonReporteIntegracion.setToolTipText(
			"Reporte de la Integraci�n");
		
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
	 * M�todo que adjunta los escuchadores eventos a los botones de acceso
	 * r�pido que tiene la botonera. En particular se incorpora el escuchador
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
	 * M�todo que configura los botones de la botonera. Espec�ficamente se 
	 * establece la propiedad de habilitaci�n de los botones del tool bar,
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
	 * M�todo que obtiene el objeto botonNuevo.
	 *
	 * @return botonNuevo El bot�n de la botonera "Nuevo Proyecto".
	 */
	public JButton obtenerBotonNuevo()
	{
		return botonNuevo;
	}
	
	/**
	 * M�todo que obtiene el objeto botonAbrir.
	 *
	 * @return botonAbrir El bot�n de la botonera "Abrir Proyecto".
	 */
	public JButton obtenerBotonAbrir()
	{
		return botonAbrir;
	}
	
	/**
	 * M�todo que obtiene el objeto botonGuardar.
	 *
	 * @return botonGuardar El bot�n de la botonera "Guardar Proyecto".
	 */
	public JButton obtenerBotonGuardar()
	{
		return botonGuardar;
	}
	
	/**
	 * M�todo que obtiene el objeto botonConfiguracionProyecto
	 *
	 * @return botonConfiguracionProyecto El bot�n de la botonera "Configuraci�n
	 *                                    del Proyecto".
	 */
	public JButton obtenerBotonConfiguracionProyecto()
	{
		return botonConfiguracionProyecto;
	}
	
	/**
	 * M�todo que obtiene el objeto botonOpcionalProyecto
	 *
	 * @return botonOpcionalProyecto El bot�n de la botonera "Informaci�n
	 *                               Opcional del Proyecto".
	 */
	public JButton obtenerBotonOpcionalProyecto()
	{
		return botonOpcionalProyecto;
	}
	
	/**
	 * M�todo que obtiene el objeto botonEspacialEcosistema.
	 *
	 * @return botonEspacialEcosistema El bot�n de la botonera "Din�mica
	 *                                 Espacial del Ecosistema".
	 */
	public JButton obtenerBotonEspacialEcosistema()
	{
		return botonEspacialEcosistema;
	}
	
	/**
	 * M�todo que obtiene el objeto botonTemporalEcosistema.
	 *
	 * @return botonTemporalEcosistema El bot�n de la botonera "Din�mica
	 *                                 Temporal del Ecosistema".
	 */
	public JButton obtenerBotonTemporalEcosistema()
	{
		return botonTemporalEcosistema;
	}
	
	/**
	 * M�todo que obtiene el objeto botonReporteEcosistema.
	 *
	 * @return botonReporteEcosistema El bot�n de la botonera "Reporte de
	 *                                Ecosistema".
	 */
	public JButton obtenerBotonReporteEcosistema()
	{
		return botonReporteEcosistema;
	}
	
	/**
	 * M�todo que obtiene el objeto botonConfiguracionEcosistema.
	 *
	 * @return botonConfiguracionEcosistema El bot�n de la botonera
	 *                                      "Configuraci�n del Ecosistema".
	 */
	public JButton obtenerBotonConfiguracionEcosistema()
	{
		return botonConfiguracionEcosistema;
	}
	
	/**
	 * M�todo que obtiene el objeto botonEntrenarEconomia.
	 *
	 * @return botonEntrenarEconomia El bot�n de la botonera "Entrenar de
	 *                               Econom�a".
	 */
	public JButton obtenerBotonEntrenarEconomia()
	{
		return botonEntrenarEconomia;
	}
	
	/**
	 * M�todo que obtiene el objeto botonGraficarEconomia.
	 *
	 * @return botonGraficarEconomia El bot�n de la botonera "Graficar de
	 *                               Econom�a".
	 */
	public JButton obtenerBotonGraficarEconomia()
	{
		return botonGraficarEconomia;
	}
	
	/**
	 * M�todo que obtiene el objeto botonReporteEconomia.
	 *
	 * @return botonReporteEconomia El bot�n de la botonera "Reporte de
	 *                              Econom�a".
	 */
	public JButton obtenerBotonReporteEconomia()
	{
		return botonReporteEconomia;
	}
	
	/**
	 * M�todo que obtiene el objeto botonConfiguracionEconomia.
	 *
	 * @return botonConfiguracionEconomia El bot�n de la botonera "Configuraci�n
	 *                                    de Econom�a".
	 */
	public JButton obtenerBotonConfiguracionEconomia()
	{
		return botonConfiguracionEconomia;
	}
	
	/**
	 * M�todo que obtiene el objeto botonTransportarOperacion.
	 *
	 * @return botonTransportarOperacion El bot�n de la botonera "Transportar
	 *                                   de Operaci�n".
	 */
	public JButton obtenerBotonTransportarOperacion()
	{
		return botonTransportarOperacion;
	}
	
	/**
	 * M�todo que obtiene el objeto botonEvolucionOperacion.
	 *
	 * @return botonEvolucionOperacion El bot�n de la botonera "Evoluci�n de la
	 *                                 Operaci�n".
	 */
	public JButton obtenerBotonEvolucionOperacion()
	{
		return botonEvolucionOperacion;
	}
	
	/**
	 * M�todo que obtiene el objeto botonReporteOperacion.
	 *
	 * @return botonReporteOperacion El bot�n de la botonera "Reporte de
	 *                               Operaci�n".
	 */
	public JButton obtenerBotonReporteOperacion()
	{
		return botonReporteOperacion;
	}
	
	/**
	 * M�todo que obtiene el objeto botonConfiguracionOperacion.
	 *
	 * @return botonConfiguracionOperacion El bot�n de la botonera
	 *                                    "Configuraci�n de Operaci�n".
	 */
	public JButton obtenerBotonConfiguracionOperacion()
	{
		return botonConfiguracionOperacion;
	}
	
	/**
	 * M�todo que obtiene el objeto botonPlanificarIntegracion
	 *
	 * @return botonPlanificarIntegracion El bot�n de la botonera "Planificar de
	 *                                    Integraci�n".
	 */
	public JButton obtenerBotonPlanficarIntegracion()
	{
		return botonPlanificarIntegracion;
	}
	
	/**
	 * M�todo que obtiene el objeto botonGraficaIntegracion
	 *
	 * @return botonGraficarIntegracion El bot�n de la botonera "Graficar de
	 *                                  Integraci�n".
	 */
	public JButton obtenerBotonGraficarIntegracion()
	{
		return botonGraficarIntegracion;
	}
	
	/**
	 * M�todo que obtiene el objeto botonReporteIntegracion
	 *
	 * @return botonReporteIntegracion El bot�n de la botonera "Reporte de
	 *                                 Integraci�n".
	 */
	public JButton obtenerBotonReporteIntegracion()
	{
		return botonReporteIntegracion;
	}
}