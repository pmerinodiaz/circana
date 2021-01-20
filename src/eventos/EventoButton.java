/**
 * @(#)EventoButton.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 * Clase que permite incorporar los eventos a los botones del tipo JButton que
 * se tiene en toda la aplicación. Esta clase implementa la interface
 * ActionListener, con el objetivo de poder realizar diversas acciones frente a
 * los eventos capturados de los botones que se usan en la aplicación.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see ActionListener
 * @see ActionEvent
 * @see JButton
 * @see PanelBotonera
 * @see PanelConfiguracionProyecto
 * @see PanelOpcionalProyecto
 * @see SubPanelControlEcosistema
 * @see PanelTemporalEcosistema
 * @see PanelReporteEcosistema
 * @see PanelConfiguracionEcosistema
 * @see PanelEntrenarEconomia
 * @see PanelGraficarEconomia
 * @see PanelReporteEconomia
 * @see PanelConfiguracionEconomia
 * @see PanelTransportarOperacion
 * @see PanelEvolucionOperacion
 * @see PanelReporteOperacion
 * @see PanelConfiguracionOperacion
 * @see PanelPlanificarIntegracion
 * @see PanelGraficarIntegracion
 * @see PanelReporteIntegracion
 * @see FrameAbrirGuardarProyecto
 * @see FrameEvaluacionStock
 * @see FrameCaladero
 * @see FrameMercado
 * @see FrameMedioTransporte
 * @see FramePuntoDemanda
 * @see FrameConfigurarServidor
 * @see FrameAcerca
 */
public class EventoButton implements ActionListener
{
	/** Para cuando es llamada por la clase PanelBotonera. */
	private PanelBotonera panelBotonera;
	
	/** Para cuando es llamada por la clase PanelConfiguracionProyecto. */
	private PanelConfiguracionProyecto panelConfiguracionProyecto;
	
	/** Para cuando es llamada por la clase PanelOpcionalProyecto. */
	private PanelOpcionalProyecto panelOpcionalProyecto;
	
	/** Para cuando es llamada por la clase SubPanelControlEcosistema. */
	private SubPanelControlEcosistema subpanelControlEcosistema;
	
	/** Para cuando es llamada por la clase PanelTemporalEcosistema. */
	private PanelTemporalEcosistema panelTemporalEcosistema;
	
	/** Para cuando es llamada por la clase PanelReporteEcosistema. */
	private PanelReporteEcosistema panelReporteEcosistema;
	
	/** Para cuando es llamada por la clase PanelConfiguracionEcosistema. */
	private PanelConfiguracionEcosistema panelConfiguracionEcosistema;
	
	/** Para cuando es llamada por la clase PanelEntrenarEconomia. */
	private PanelEntrenarEconomia panelEntrenarEconomia;
	
	/** Para cuando es llamada por la clase PanelGraficarEconomia. */
	private PanelGraficarEconomia panelGraficarEconomia;
	
	/** Para cuando es llamada por la clase PanelReporteEconomia. */
	private PanelReporteEconomia panelReporteEconomia;
	
	/** Para cuando es llamada por la clase PanelConfiguracionEconomia. */
	private PanelConfiguracionEconomia panelConfiguracionEconomia;
	
	/** Para cuando es llamada por la clase PanelTransportarOperacion. */
	private PanelTransportarOperacion panelTransportarOperacion;	
	
	/** Para cuando es llamada por la clase PanelEvolucionOperacion. */
	private PanelEvolucionOperacion panelEvolucionOperacion;
	
	/** Para cuando es llamada por la clase PanelReporteOperacion. */
	private PanelReporteOperacion panelReporteOperacion;
	
	/** Para cuando es llamada por la clase PanelConfiguracionOperacion. */
	private PanelConfiguracionOperacion panelConfiguracionOperacion;
	
	/** Para cuando es llamada por la clase PanelPlanificarIntegracion. */
	private PanelPlanificarIntegracion panelPlanificarIntegracion;
	
	/** Para cuando es llamada por la clase PanelGraficarIntegracion. */
	private PanelGraficarIntegracion panelGraficarIntegracion;
	
	/** Para cuando es llamada por la clase PanelReporteIntegracion. */
	private PanelReporteIntegracion panelReporteIntegracion;
	
	/** Objeto para referenciar al frame de Abrir-Guardar Proyecto. */
	private FrameAbrirGuardarProyecto frameAbrirGuardarProyecto;
	
	/** Objeto para referenciar al frame de Evaluación de Stock. */
	private FrameEvaluacionStock frameEvaluacionStock;
	
	/** Objeto para referenciar al frame de Caladero. */
	private FrameCaladero frameCaladero;
	
	/** Objeto para referenciar al frame de Mercado. */
	private FrameMercado frameMercado;
	
	/** Objeto para referenciar al frame de Medio de Transporte. */
	private FrameMedioTransporte frameMedioTransporte;
	
	/** Objeto para referenciar al frame de Punto de Demanda. */
	private FramePuntoDemanda framePuntoDemanda;
	
	/** Objeto para referenciar al frame de Configuración Servidor. */
	private FrameConfiguracionServidor frameConfiguracionServidor;
	
	/** Objeto para referenciar al frame de Acerca de CIRCANA Pro. */
	private FrameAcerca frameAcerca;
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel de la
	 * botonera que tiene la aplicación. En este método se inicializan los
	 * objetos que se usarán posteriormente en la clase.
	 *
	 * @param padre Es un objeto de la clase PanelBotonera.
	 */
	public EventoButton(PanelBotonera panelBotonera)
	{
		this.panelBotonera = panelBotonera;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel de la
	 * configuración del proyecto. En este método se inicializan los objetos que
	 * se usarán posteriormente en la clase.
	 *
	 * @param panelConfiguracionProyecto Es un objeto de la clase
	 *                                   PanelConfiguracionProyecto.
	 */
	public EventoButton(PanelConfiguracionProyecto panelConfiguracionProyecto)
	{
		this.panelConfiguracionProyecto = panelConfiguracionProyecto;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel de
	 * información opcional que tiene el panel del proyecto. En este método se
	 * inicializan los objetos que se usarán posteriormente en la clase.
	 *
	 * @param panelOpcionalProyecto Es un objeto de la clase
	 *                              PanelOpcionalProyecto.
	 */
	public EventoButton(PanelOpcionalProyecto panelOpcionalProyecto)
	{
		this.panelOpcionalProyecto = panelOpcionalProyecto;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel de control 
	 * que tiene el panel espacial del ecosistema. En este método se inicializan
	 * los objetos que se usarán posteriormente en la clase.
	 *
	 * @param subpanelControlEcosistema Es un objeto de la clase
	 *                                  SubPanelControlEcosistema.
	 */
	public EventoButton(SubPanelControlEcosistema subpanelControlEcosistema)
	{
		this.subpanelControlEcosistema = subpanelControlEcosistema;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel temporal que
	 * tiene el frame ecosistema. En este método se inicializan los objetos que
	 * se usarán posteriormente en la clase.
	 *
	 * @param panelTemporalEcosistema Es un objeto de la clase
	 *                                PanelTemporalEcosistema.
	 */
	public EventoButton(PanelTemporalEcosistema panelTemporalEcosistema)
	{
		this.panelTemporalEcosistema = panelTemporalEcosistema;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel reporte que
	 * tiene el frame ecosistema. En este método se inicializan los objetos que
	 * se usarán posteriormente en la clase.
	 *
	 * @param panelReporteEcosistema Es un objeto de la clase
	 *                               PanelReporteEcosistema.
	 */
	public EventoButton(PanelReporteEcosistema panelReporteEcosistema)
	{
		this.panelReporteEcosistema = panelReporteEcosistema;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel
	 * configuración que tiene el frame ecosistema. En este método se
	 * inicializan los objetos que se usarán posteriormente en la clase.
	 *
	 * @param panelConfiguracionEcosistema Es un objeto de la clase
	 *                                     PanelConfiguracionEcosistema.
	 */
	public EventoButton(PanelConfiguracionEcosistema
						panelConfiguracionEcosistema)
	{
		this.panelConfiguracionEcosistema = panelConfiguracionEcosistema;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel entrenar 
	 * que tiene el frame economía. En este método se inicializan los objetos
	 * que se usarán posteriormente en la clase.
	 *
	 * @param padre Es un objeto de la clase PanelEntrenarEconomia.
	 */
	public EventoButton(PanelEntrenarEconomia panelEntrenarEconomia)
	{
		this.panelEntrenarEconomia = panelEntrenarEconomia;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel graficar 
	 * que tiene el frame economía. En este método se inicializan los objetos
	 * que se usarán posteriormente en la clase.
	 *
	 * @param padre Es un objeto de la clase PanelGraficarEconomia.
	 */
	public EventoButton(PanelGraficarEconomia panelGraficarEconomia)
	{
		this.panelGraficarEconomia = panelGraficarEconomia;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel reporte 
	 * que tiene el frame economía. En este método se inicializan los objetos
	 * que se usarán posteriormente en la clase.
	 *
	 * @param padre Es un objeto de la clase PanelReporteEconomia.
	 
	 */
	public EventoButton(PanelReporteEconomia panelReporteEconomia)
	{
		this.panelReporteEconomia = panelReporteEconomia;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel
	 * configuración que tiene el frame economía. En este método se inicializan
	 * los objetos que se usarán posteriormente en la clase.
	 *
	 * @param padre Es un objeto de la clase PanelConfiguracionEconomia.
	 */
	public EventoButton(PanelConfiguracionEconomia panelConfiguracionEconomia)
	{
		this.panelConfiguracionEconomia = panelConfiguracionEconomia;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel transportar
	 * que tiene el frame operación. En este método se inicializan los objetos
	 * que se usarán posteriormente en la clase.
	 *
	 * @param panelTransportarOperacion Es un objeto de la clase
	 * 								    PanelTransportarOperacion.
	 */
	public EventoButton(PanelTransportarOperacion panelTransportarOperacion)
	{
		this.panelTransportarOperacion = panelTransportarOperacion;
	}	
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel evolución
	 * que tiene el frame operación. En este método se inicializan los objetos
	 * que se usarán posteriormente en la clase.
	 *
	 * @param panelEvolucionOperacion Es un objeto de la clase
	 *								  PanelEvolucionOperacion.
	 */
	public EventoButton(PanelEvolucionOperacion panelEvolucionOperacion)
	{
		this.panelEvolucionOperacion = panelEvolucionOperacion;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel reporte que
	 * tiene el frame operación. En este método se inicializan los objetos que
	 * se usarán posteriormente en la clase.
	 *
	 * @param panelReporteOperacion Es un objeto de la clase 
	 *								PanelReporteOperacion.
	 */
	public EventoButton(PanelReporteOperacion panelReporteOperacion)
	{
		this.panelReporteOperacion = panelReporteOperacion;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel
	 * configuración que tiene el frame operación. En este método se inicializan
	 * los objetos que se usarán posteriormente en la clase.
	 *
	 * @param panelConfiguracionOperacion Es un objeto de la clase 
	 *									  PanelConfiguracionOperacion.
	 */
	public EventoButton(PanelConfiguracionOperacion panelConfiguracionOperacion)
	{		
		this.panelConfiguracionOperacion = panelConfiguracionOperacion;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel planificar
	 * que tiene el frame integración. En este método se inicializan los objetos
	 * que se usarán posteriormente en la clase.
	 *
	 * @param panelPlanificarIntegracion Es un objeto de la clase
	 *                                   PanelPlanificarIntegracion.
	 */
	public EventoButton(PanelPlanificarIntegracion panelPlanificarIntegracion)
	{
		this.panelPlanificarIntegracion = panelPlanificarIntegracion;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel graficar que
	 * tiene el frame integración. En este método se inicializan los objetos que
	 * se usarán posteriormente en la clase.
	 *
	 * @param panelGraficarIntegracion Es un objeto de la clase
	 *                                 PanelGraficarIntegracion.
	 */
	public EventoButton(PanelGraficarIntegracion panelGraficarIntegracion)
	{
		this.panelGraficarIntegracion = panelGraficarIntegracion;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel reporte
	 * que tiene el frame integración. En este método se inicializan los objetos
	 * que se usarán posteriormente en la clase.
	 *
	 * @param panelReporteIntegracion Es un objeto de la clase
	 *                                PanelReporteIntegracion.
	 */
	public EventoButton(PanelReporteIntegracion panelReporteIntegracion)
	{
		this.panelReporteIntegracion = panelReporteIntegracion;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el frame abrir
	 * guardar proyecto que tiene la aplicación. En este método se inicializan
	 * los objetos que se usarán posteriormente en la clase.
	 *
	 * @param frameAbrirGuardarProyecto Es un objeto de la clase
	 *                                  FrameAbrirGuardarProyecto.
	 */
	public EventoButton(FrameAbrirGuardarProyecto frameAbrirGuardarProyecto)
	{
		this.frameAbrirGuardarProyecto = frameAbrirGuardarProyecto;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el frame evaluación
	 * de stock que tiene la aplicación. En este método se inicializan los
	 * objetos que se usarán posteriormente en la clase.
	 *
	 * @param frameEvaluacionStock Es un objeto de la clase
	 *                             FrameEvaluacionStock.
	 */
	public EventoButton(FrameEvaluacionStock frameEvaluacionStock)
	{
		this.frameEvaluacionStock = frameEvaluacionStock;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el frame caladero que
	 * tiene la aplicación. En este método se inicializan los objetos que se
	 * usarán posteriormente en la clase.
	 *
	 * @param frameCaladero Es un objeto de la clase FrameCaladero.
	 */
	public EventoButton(FrameCaladero frameCaladero)
	{
		this.frameCaladero = frameCaladero;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el frame mercado que
	 * tiene la aplicación. En este método se inicializan los objetos que se
	 * usarán posteriormente en la clase.
	 *
	 * @param frameMercado Es un objeto de la clase FrameMercado.
	 */
	public EventoButton(FrameMercado frameMercado)
	{
		this.frameMercado = frameMercado;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el frame medio de
	 * transporte que tiene la aplicación. En este método se inicializan los
	 * objetos que se usarán posteriormente en la clase.
	 *
	 * @param frameMedioTransporte Es un objeto de la clase
	 *                             FrameMedioTransporte.
	 */
	public EventoButton(FrameMedioTransporte frameMedioTransporte)
	{
		this.frameMedioTransporte = frameMedioTransporte;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el frame punto de
	 * demanda que tiene la aplicación. En este método se inicializan los
	 * objetos que se usarán posteriormente en la clase.
	 *
	 * @param framePuntoDemanda Es un objeto de la clase FramePuntoDemanda.
	 */
	public EventoButton(FramePuntoDemanda framePuntoDemanda)
	{
		this.framePuntoDemanda = framePuntoDemanda;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el frame
	 * configuración del servidor que tiene la aplicación. En este método se
	 * inicializan los objetos que se usarán posteriormente en la clase.
	 *
	 * @param frameConfiguracionServidor Es un objeto de la clase
	 *                                   FrameConfiguracionServidor.
	 */
	public EventoButton(FrameConfiguracionServidor frameConfiguracionServidor)
	{
		this.frameConfiguracionServidor = frameConfiguracionServidor;
	}
			
	/**
	 * Método constructor que sirve para ser llamado desde el frame acerca de
	 * CIRCANA Pro que tiene la aplicación. En este método se inicializan los
	 * objetos que se usarán posteriormente en la clase.
	 *
	 * @param frameAcerca Es un objeto de la clase FrameAcerca.
	 */
	public EventoButton(FrameAcerca frameAcerca)
	{
		this.frameAcerca = frameAcerca;
	}
	
	/**
	 * Método en donde se captura el evento sobre un botón cuando éste ha sido
	 * pulsado. Se captura el identificador del botón pulsado y se implementa la
	 * acción a seguir. Dependiendo de los objetos creados e inicializados en
	 * el constructor se determina desde donde fue llamado este método.
	 *
	 * @param evento El evento capturado del botón pulsado.
	 */
	public void actionPerformed(ActionEvent evento)
	{
		// Obtener el botón en el cual se generó el evento.
		JButton boton = (JButton) evento.getSource();
		
		// Evento ocurrido en el "PanelBotonera".
		if (panelBotonera != null)
		{
			// Evento ocurrido en el botón "Nuevo Proyecto".
			if (boton == panelBotonera.obtenerBotonNuevo())
				panelBotonera.frameCircanaPro.nuevoProyecto();
			
			// Evento ocurrido en el botón "Abrir Proyecto".
			else
			if (boton == panelBotonera.obtenerBotonAbrir())
				panelBotonera.frameCircanaPro.abrir();
			
			// Evento ocurrido en el botón "Guardar Proyecto".
			else
			if (boton == panelBotonera.obtenerBotonGuardar())
				panelBotonera.frameCircanaPro.guardar();
			
			// Evento ocurrido en el botón "Configuración de Proyecto".
			else
			if (boton == panelBotonera.obtenerBotonConfiguracionProyecto())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirConfiguracionProyecto();
			
			// Evento ocurrido en el botón "Información Opcional de Proyecto".
			else
			if (boton == panelBotonera.obtenerBotonOpcionalProyecto())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirOpcionalProyecto();
			
			// Evento ocurrido en el botón "Dinámica Espacial del Ecosistema".
			else
			if (boton == panelBotonera.obtenerBotonEspacialEcosistema())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirEspacialEcosistema();
			
			// Evento ocurrido en el botón "Dinámica Temporal del Ecosistema".
			else
			if (boton == panelBotonera.obtenerBotonTemporalEcosistema())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirTemporalEcosistema();
			
			// Evento ocurrido en el botón "Reporte del Ecosistema".
			else
			if (boton == panelBotonera.obtenerBotonReporteEcosistema())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirReporteEcosistema();
			
			// Evento ocurrido en el botón "Configuración del Ecosistema".
			else
			if (boton == panelBotonera.obtenerBotonConfiguracionEcosistema())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirConfiguracionEcosistema();
			
			// Evento ocurrido en el botón "Entrenar de Economía".
			else
			if (boton == panelBotonera.obtenerBotonEntrenarEconomia())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirEntrenarEconomia();
			
			// Evento ocurrido en el botón "Graficar de Economía".
			else
			if (boton == panelBotonera.obtenerBotonGraficarEconomia())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirGraficarEconomia();
			
			// Evento ocurrido en el botón "Reporte de Economía".
			else
			if (boton == panelBotonera.obtenerBotonReporteEconomia())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirReporteEconomia();
			
			// Evento ocurrido en el botón "Configuración de Economía".
			else
			if (boton == panelBotonera.obtenerBotonConfiguracionEconomia())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirConfiguracionEconomia();
			
			// Evento ocurrido en el botón "Transportar de Operación".
			else
			if (boton == panelBotonera.obtenerBotonTransportarOperacion())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirTransportarOperacion();
			
			// Evento ocurrido en el botón "Evolución de Operación".
			else
			if (boton == panelBotonera.obtenerBotonEvolucionOperacion())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirEvolucionOperacion();
			
			// Evento ocurrido en el botón "Reporte de Operación".
			else
			if (boton == panelBotonera.obtenerBotonReporteOperacion())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirReporteOperacion();
			
			// Evento ocurrido en el botón "Configuración de Operación".
			else
			if (boton == panelBotonera.obtenerBotonConfiguracionOperacion())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirConfiguracionOperacion();
			
			// Evento ocurrido en el botón "Planificar de Integración".
			else
			if (boton == panelBotonera.obtenerBotonPlanficarIntegracion())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirPlanificarIntegracion();
			
			// Evento ocurrido en el botón "Graficar de Integración".
			else
			if (boton == panelBotonera.obtenerBotonGraficarIntegracion())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirGraficarIntegracion();
			
			// Evento ocurrido en el botón "Reporte de Integración".
			else
			if (boton == panelBotonera.obtenerBotonReporteIntegracion())
				panelBotonera.frameCircanaPro.panelEscritorio.
				elegirReporteIntegracion();
		}
		
	 	// Evento ocurrido en el "PanelConfiguracionProyecto".
		else
		if (panelConfiguracionProyecto != null)
		{
			// Evento ocurrido en el botón "Establecer".
			if (boton == panelConfiguracionProyecto.obtenerBotonEstablecer())
				panelConfiguracionProyecto.establecerConfiguracion();
	 	}
		
	 	// Evento ocurrido en el "PanelOpcionalProyecto".
		else
		if (panelOpcionalProyecto != null)
		{
			// Evento ocurrido en el botón "Opcional".
			if (boton == panelOpcionalProyecto.obtenerBotonEstablecer())
				panelOpcionalProyecto.establecerConfiguracion();
	 	}
		
		// Evento ocurrido en el "SubPanelControlEcosistema".
		else
		if (subpanelControlEcosistema != null)
		{
			// Evento ocurrido en el botón "Simular".
			if (boton == subpanelControlEcosistema.obtenerBotonSimular())
				subpanelControlEcosistema.panelEspacialEcosistema.
				frameEcosistema.simular();
			
			// Evento ocurrido en el botón "Pausar".
			else
			if (boton == subpanelControlEcosistema.obtenerBotonPausar())
				subpanelControlEcosistema.panelEspacialEcosistema.
				frameEcosistema.pausar();
			
			// Evento ocurrido en el botón "Detener".
			else
			if (boton == subpanelControlEcosistema.obtenerBotonDetener())
				subpanelControlEcosistema.panelEspacialEcosistema.
				frameEcosistema.detener();
			
			// Evento ocurrido en el botón "Noroeste".
			else
			if (boton == subpanelControlEcosistema.obtenerBotonNoroeste())
				subpanelControlEcosistema.panelEspacialEcosistema.
				subpanelMapaEcosistema.moverNoroeste();
			
			// Evento ocurrido en el botón "Norte".
			else
			if (boton == subpanelControlEcosistema.obtenerBotonNorte())
				subpanelControlEcosistema.panelEspacialEcosistema.
				subpanelMapaEcosistema.moverNorte();
			
			// Evento ocurrido en el botón "Noreste".
			else
			if (boton == subpanelControlEcosistema.obtenerBotonNoreste())
				subpanelControlEcosistema.panelEspacialEcosistema.
				subpanelMapaEcosistema.moverNoreste();
			
			// Evento ocurrido en el botón "Oeste".
			else
			if (boton == subpanelControlEcosistema.obtenerBotonOeste())
				subpanelControlEcosistema.panelEspacialEcosistema.
				subpanelMapaEcosistema.moverOeste();
			
			// Evento ocurrido en el botón "Centrar".
			else
			if (boton == subpanelControlEcosistema.obtenerBotonCentrar())
				subpanelControlEcosistema.panelEspacialEcosistema.
				subpanelMapaEcosistema.moverCentro();
				
			// Evento ocurrido en el botón "Este".
			else
			if (boton == subpanelControlEcosistema.obtenerBotonEste())
				subpanelControlEcosistema.panelEspacialEcosistema.
				subpanelMapaEcosistema.moverEste();
			
			// Evento ocurrido en el botón "Suroeste".
			else
			if (boton == subpanelControlEcosistema.obtenerBotonSuroeste())
				subpanelControlEcosistema.panelEspacialEcosistema.
				subpanelMapaEcosistema.moverSuroeste();
			
			// Evento ocurrido en el botón "Sur".
			else
			if (boton == subpanelControlEcosistema.obtenerBotonSur())
				subpanelControlEcosistema.panelEspacialEcosistema.
				subpanelMapaEcosistema.moverSur();
			
			// Evento ocurrido en el botón "Sureste".
			else
			if (boton == subpanelControlEcosistema.obtenerBotonSureste())
				subpanelControlEcosistema.panelEspacialEcosistema.
				subpanelMapaEcosistema.moverSureste();
			
			// Evento ocurrido en el botón "Acercar".
			if (boton == subpanelControlEcosistema.obtenerBotonAcercar())
				subpanelControlEcosistema.panelEspacialEcosistema.
				subpanelMapaEcosistema.acercar();
			
			// Evento ocurrido en el botón "Alejar".
			else
			if (boton == subpanelControlEcosistema.obtenerBotonAlejar())
				subpanelControlEcosistema.panelEspacialEcosistema.
				subpanelMapaEcosistema.alejar();
			
			// Evento ocurrido en el botón "Restablecer".
			else
			if (boton == subpanelControlEcosistema.obtenerBotonRestablecer())
				subpanelControlEcosistema.panelEspacialEcosistema.
				subpanelMapaEcosistema.restablecer();
		}
		
		// Evento ocurrido en el "PanelTemporalEcosistema".
		else
		if (panelTemporalEcosistema != null)
		{
			// Evento ocurrido en el botón "Anterior".
			if (boton == panelTemporalEcosistema.obtenerBotonAnterior())
				panelTemporalEcosistema.cambiarAnioAnterior();
			
			// Evento ocurrido en el botón "Siguiente".
			else
			if (boton == panelTemporalEcosistema.obtenerBotonSiguiente())
				panelTemporalEcosistema.cambiarAnioSiguiente();
		}
		
		// Evento ocurrido en el "PanelReporteEcosistema".
		else
		if (panelReporteEcosistema != null)
		{
			// Evento ocurrido en el botón "Guardar".
			if (boton == panelReporteEcosistema.obtenerBotonGuardar())
			{
				panelReporteEcosistema.guardarReporte();
				panelReporteEcosistema.frameEcosistema.panelEscritorio.
				frameOperacion.modificarResultados();
				panelReporteEcosistema.frameEcosistema.panelEscritorio.
				frameIntegracion.actualizarInformacionProyecto();
			}
			
			// Evento ocurrido en el botón "Imprimir".
			else
			if (boton == panelReporteEcosistema.obtenerBotonImprimir())
				panelReporteEcosistema.imprimirReporte();
		}
		
		// Evento ocurrido en el panel "PanelConfiguracionEcosistema".
		else
		if (panelConfiguracionEcosistema != null)
		{
			// Evento ocurrido en el botón "Restaurar".
			if (boton == panelConfiguracionEcosistema.obtenerBotonRestaurar())
				panelConfiguracionEcosistema.restaurarConfiguracion();
			
			// Evento ocurrido en el botón "Establecer".
			else
			if (boton == panelConfiguracionEcosistema.obtenerBotonEstablecer())
				panelConfiguracionEcosistema.establecerConfiguracion();
		}
		
		// Evento ocurrido en el "PanelEntrenarEconomia".
		else
		if (panelEntrenarEconomia != null)
		{
			// Evento ocurrido en el botón "Entrenar".
			if (boton == panelEntrenarEconomia.obtenerBotonEntrenar())
				panelEntrenarEconomia.frameEconomia.entrenar();
			
			// Evento ocurrido en el botón "Detener".
			else
			if (boton == panelEntrenarEconomia.obtenerBotonDetener())
				panelEntrenarEconomia.frameEconomia.detener();
			
			// Evento ocurrido en el botón "Agregar".
			else
			if (boton == panelEntrenarEconomia.obtenerBotonAgregar())
				panelEntrenarEconomia.frameEconomia.
				agregarDatosTablaEntrenada();
			
			// Evento ocurrido en el botón "Quitar".
			else
			if (boton == panelEntrenarEconomia.obtenerBotonQuitar())
				panelEntrenarEconomia.frameEconomia.
				eliminarDatosTablaEntrenada();
			
			// Evento ocurrido en el botón "Refrescar".
			else
			if (boton == panelEntrenarEconomia.obtenerBotonRefrescar())
				panelEntrenarEconomia.refrescarTablaAnosDisponible();
		}
		
		// Evento ocurrido en el "PanelGraficarEconomia".
		else
		if (panelGraficarEconomia != null)
		{
			// Evento ocurrido en el botón "Anterior".
			if (boton == panelGraficarEconomia.obtenerBotonAnterior())
				panelGraficarEconomia.frameEconomia.cambiarTiempoPrediccion(1);	
			
			// Evento ocurrido en el botón "Siguiente".
			else
			if (boton == panelGraficarEconomia.obtenerBotonSiguiente())
				panelGraficarEconomia.frameEconomia.cambiarTiempoPrediccion(2);
		}
		
		// Evento ocurrido en el "PanelReporteEconomia".
		else
		if (panelReporteEconomia != null)
		{
			// Evento ocurrido en el botón "Guardar".
			if (boton == panelReporteEconomia.obtenerBotonGuardar())
			{
				panelReporteEconomia.guardarReporte();
				panelReporteEconomia.frameEconomia.panelEscritorio.
				frameIntegracion.actualizarInformacionProyecto();
			}
			
			// Evento ocurrido en el botón "Imprimir".
			else
			if (boton == panelReporteEconomia.obtenerBotonImprimir())
				panelReporteEconomia.imprimirReporte();
		}
		
		// Evento ocurrido en el "PanelConfiguracionEconomia".
		else
		if (panelConfiguracionEconomia != null)
		{
			// Evento ocurrido en el botón "Restaurar".
			if (boton == panelConfiguracionEconomia.obtenerBotonRestaurar())
				panelConfiguracionEconomia.frameEconomia.
					restaurarConfiguracion();
			
			// Evento ocurrido en el botón "Establecer".
			else
			if (boton == panelConfiguracionEconomia.obtenerbotonEstablecer())
				panelConfiguracionEconomia.frameEconomia.
					establecerConfiguracion();
	 	}
	 	
		// Evento ocurrido en el "PanelTransportarOperacion".
		else
		if (panelTransportarOperacion != null)
		{
			// Evento ocurrido en el botón "Transportar".
			if (boton == panelTransportarOperacion.obtenerBotonTransportar())
				panelTransportarOperacion.frameOperacion.transportar();
		}
		
		// Evento ocurrido en el "PanelEvolucionOperacion".
		else
		if (panelEvolucionOperacion != null)
		{
			// Evento ocurrido en el botón "Anterior".
			if (boton == panelEvolucionOperacion.obtenerBotonAnterior())
				panelEvolucionOperacion.retrocederEvolucion();
			
			// Evento ocurrido en el botón "Siguiente".
			else
			if (boton == panelEvolucionOperacion.obtenerBotonSiguiente())
				panelEvolucionOperacion.continuarEvolucion();
		}
		
		// Evento ocurrido en el "PanelReporteOperacion".
		else
		if (panelReporteOperacion != null)
		{
			// Evento ocurrido en el botón "Guardar".
			if (boton == panelReporteOperacion.obtenerBotonGuardar())
			{
				panelReporteOperacion.guardarReporte();
				panelReporteOperacion.frameOperacion.panelEscritorio.
				frameIntegracion.actualizarInformacionProyecto();
			}
			
			// Evento ocurrido en el botón "Imprimir".
			else
			if (boton == panelReporteOperacion.obtenerBotonImprimir())
				panelReporteOperacion.imprimirReporte();
		}
		
		// Evento ocurrido en el "PanelConfiguracionOperacion".
		else
		if (panelConfiguracionOperacion != null)
		{			
			// Evento ocurrido en el botón "Restaurar".
			if (boton == panelConfiguracionOperacion.obtenerBotonRestaurar())
				panelConfiguracionOperacion.restaurarConfiguracion();
			
			// Evento ocurrido en el botón "Establecer".
			else
			if (boton == panelConfiguracionOperacion.obtenerBotonEstablecer())
				panelConfiguracionOperacion.establecerConfiguracion();
	 	}
	 	
		// Evento ocurrido en el "PanelPlanificarIntegracion".
		else
		if (panelPlanificarIntegracion != null)
		{
			// Evento ocurrido en el botón "Planificar".
			if (boton == panelPlanificarIntegracion.obtenerBotonPlanificar())
				panelPlanificarIntegracion.frameIntegracion.planificar();
	 	}
	 	
	 	// Evento ocurrido en el "PanelGraficarIntegracion".
		else
		if (panelGraficarIntegracion != null)
		{
			// Evento ocurrido en el botón "Anterior".
			if (boton == panelGraficarIntegracion.obtenerBotonAnterior())
				panelGraficarIntegracion.frameIntegracion.
				cambiarTiempoPlanificacion(1);
			
			// Evento ocurrido en el botón "Siguiente".
			else
			if (boton == panelGraficarIntegracion.obtenerBotonSiguiente())
				panelGraficarIntegracion.frameIntegracion.
				cambiarTiempoPlanificacion(2);
		}
		
		// Evento ocurrido en el "PanelReporteIntegracion".
		else
		if (panelReporteIntegracion != null)
		{
			// Evento ocurrido en el botón "Guardar".
			if (boton == panelReporteIntegracion.obtenerBotonGuardar())
				panelReporteIntegracion.guardarReporte();
			
			// Evento ocurrido en el botón "Imprimir".
			else
			if (boton == panelReporteIntegracion.obtenerBotonImprimir())
				panelReporteIntegracion.imprimirReporte();
		}
		
		// Evento ocurrido en el frame "FrameAbrirGuardarProyecto".
		else
		if (frameAbrirGuardarProyecto != null)
		{
			// Evento ocurrido en el botón "Aceptar" para Abrir Proyecto.
			if (boton == frameAbrirGuardarProyecto.obtenerBotonAceptar() &&
				frameAbrirGuardarProyecto.obtenerOpcionProyecto() == false)
				frameAbrirGuardarProyecto.frameCircanaPro.abrirProyecto();
			
			// Evento ocurrido en el botón "Aceptar" para Guardar Proyecto.
			else
			if (boton == frameAbrirGuardarProyecto.obtenerBotonAceptar() &&
				frameAbrirGuardarProyecto.obtenerOpcionProyecto() == true)
				frameAbrirGuardarProyecto.frameCircanaPro.guardarProyecto();
			
			// Evento ocurrido en el botón "Cancelar".
			else
			if (boton == frameAbrirGuardarProyecto.obtenerBotonCancelar())
				frameAbrirGuardarProyecto.setVisible(false);
		}
		
		// Evento ocurrido en el frame "FrameEvaluacionStock".
		else
		if (frameEvaluacionStock != null)
		{
			// Evento ocurrido en el botón "Guardar".
			if (boton == frameEvaluacionStock.obtenerBotonGuardar())
				frameEvaluacionStock.guardar();
			
			// Evento ocurrido en el botón "Cancelar".
			else
			if (boton == frameEvaluacionStock.obtenerBotonCancelar())
				frameEvaluacionStock.cancelar();
		}
		
		// Evento ocurrido en el frame "FrameCaladero".
		else
		if (frameCaladero != null)
		{
			// Evento ocurrido en el botón "Guardar".
			if (boton == frameCaladero.obtenerBotonGuardar())
				frameCaladero.guardar();
			
			// Evento ocurrido en el botón "Cancelar".
			else
			if (boton == frameCaladero.obtenerBotonCancelar())
				frameCaladero.cancelar();
		}
		
		// Evento ocurrido en el frame "FrameMercado".
		else
		if (frameMercado != null)
		{
			// Evento ocurrido en el botón "Guardar".
			if (boton == frameMercado.obtenerBotonGuardar())
				frameMercado.guardar();
			
			// Evento ocurrido en el botón "Cancelar".
			else
			if (boton == frameMercado.obtenerBotonCancelar())
				frameMercado.cancelar();
		}
		
		// Evento ocurrido en el frame "FrameMedioTransporte".
		else
		if (frameMedioTransporte != null)
		{
			// Evento ocurrido en el botón "Guardar".
			if (boton == frameMedioTransporte.obtenerBotonGuardar())
				frameMedioTransporte.guardar();
			
			// Evento ocurrido en el botón "Cancelar".
			else
			if (boton == frameMedioTransporte.obtenerBotonCancelar())
				frameMedioTransporte.cancelar();
		}
		
		// Evento ocurrido en el frame "FramePuntoDemanda".
		else
		if (framePuntoDemanda != null)
		{
			// Evento ocurrido en el botón "Guardar".
			if (boton == framePuntoDemanda.obtenerBotonGuardar())
				framePuntoDemanda.guardar();
			
			// Evento ocurrido en el botón "Cancelar".
			else
			if (boton == framePuntoDemanda.obtenerBotonCancelar())
				framePuntoDemanda.cancelar();
		}
		
		// Evento ocurrido en el frame "FrameConfiguracionServidor".
		else
		if (frameConfiguracionServidor != null)
		{
			// Evento ocurrido en el botón "ProbarConexion".
			if (boton ==
				frameConfiguracionServidor.obtenerBotonProbarConexion())
				frameConfiguracionServidor.probarConexion();
			
			// Evento ocurrido en el botón "Instalar Base Dato".
			else
			if (boton ==
				frameConfiguracionServidor.obtenerBotonInstalarBaseDatos())
				frameConfiguracionServidor.instalarBaseDatos();	
			
			// Evento ocurrido en el botón "Establecer".
			else
			if (boton == frameConfiguracionServidor.obtenerBotonEstablecer())
				frameConfiguracionServidor.establecer();
			
			// Evento ocurrido en el botón "Cancelar".
			else
			if (boton == frameConfiguracionServidor.obtenerBotonCancelar())
				frameConfiguracionServidor.cancelar();
		}
		
		// Evento ocurrido en el frame "FrameAcerca".
		else
		if (frameAcerca != null)
		{
			// Evento ocurrido en el botón "Aceptar".
			if (boton == frameAcerca.obtenerBotonAceptar())
				frameAcerca.setVisible(false);
		}
	}
}