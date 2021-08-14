/**
 * @(#)EventoTree.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Clase que permite incorporar los eventos a los nodos del panel visor de
 * modelos que es de la clase JTree. Esta clase implementa a la interface
 * TreeSelectionListener, con el objetivo de poder realizar diversas acciones
 * frente a los eventos capturados de los nodos que tiene el panel visor del
 * proyecto.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see TreeSelectionListener
 * @see TreeSelectionEvent
 * @see DefaultMutableTreeNode
 * @see PanelVisorArchivos
 */
public class EventoTree implements TreeSelectionListener
{
	/** Para cuando es llamada por la clase PanelVisorArchivos. */
	private PanelVisor panelVisor;
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel del visor de
	 * archivos que tiene la aplicación. En este método se inicializan los
	 * objetos que se usarán posteriormente en la clase.
	 *
	 * @param panelVisor Es un objeto de la clase PanelVisor.
	 */
	public EventoTree(PanelVisor panelVisor)
	{
		this.panelVisor = panelVisor;
	}
	
	/**
	 * Método en donde se captura el evento ocurrido en un nodo del JTree,
	 * cuando éste a sido pulsado. Se captura el identificador del nodo pulsado
	 * y se implementa la acción a seguir. Dependiendo de los objetos creados e
	 * inicializados en el constructor se determina desde donde fue llamado este
	 * método.
	 *
	 * @param evento El evento capturado del nodo pulsado.
	 */
	public void valueChanged(TreeSelectionEvent evento)
	{
		// Evento ocurrido en el "PanelVisorArchivos".
		if (panelVisor != null)
		{
			DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)
			panelVisor.obtenerArchivos().getLastSelectedPathComponent();
			
			// Evento ocurrido en el nodo "Proyecto".
			if (nodo == panelVisor.obtenerProyecto())
				panelVisor.frameCircanaPro.panelEscritorio.elegirProyecto();
			
			// Evento ocurrido en el nodo "Ecosistema".
			else
			if (nodo == panelVisor.obtenerEcosistema())
				panelVisor.frameCircanaPro.panelEscritorio.elegirEcosistema();
			
			// Evento ocurrido en el nodo "Economía".
			else
			if (nodo == panelVisor.obtenerEconomia())
				panelVisor.frameCircanaPro.panelEscritorio.elegirEconomia();
			
			// Evento ocurrido en el nodo "Operación".
			else
			if (nodo == panelVisor.obtenerOperacion())
				panelVisor.frameCircanaPro.panelEscritorio.elegirOperacion();
			
			// Evento ocurrido en el nodo "Integración".
			else
			if (nodo == panelVisor.obtenerIntegracion())
				panelVisor.frameCircanaPro.panelEscritorio.elegirIntegracion();
			
			// Evento ocurrido en el botón "Configuración del Proyecto".
			else
			if (nodo == panelVisor.obtenerConfiguracionProyecto())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirConfiguracionProyecto();
			
			// Evento ocurrido en el botón "Información Opcional del Proyecto".
			else
			if (nodo == panelVisor.obtenerOpcionalProyecto())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirOpcionalProyecto();
			
			// Evento ocurrido en el botón "Dinámica Espacial del Ecosistema".
			else
			if (nodo == panelVisor.obtenerEspacialEcosistema())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirEspacialEcosistema();
			
			// Evento ocurrido en el botón "Dinámica Temporal del Ecosistema".
			else
			if (nodo == panelVisor.obtenerTemporalEcosistema())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirTemporalEcosistema();
			
			// Evento ocurrido en el botón "Reporte del Ecosistema".
			else
			if (nodo == panelVisor.obtenerReporteEcosistema())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirReporteEcosistema();
			
			// Evento ocurrido en el botón "Configuración del Ecosistema".
			else
			if (nodo == panelVisor.obtenerConfiguracionEcosistema())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirConfiguracionEcosistema();
			
			// Evento ocurrido en el botón "Entrenar de Economía".
			else
			if (nodo == panelVisor.obtenerEntrenarEconomia())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirEntrenarEconomia();
			
			// Evento ocurrido en el botón "Graficar de Economía".
			else
			if (nodo == panelVisor.obtenerGraficarEconomia())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirGraficarEconomia();
			
			// Evento ocurrido en el botón "Reporte de Economía".
			else
			if (nodo == panelVisor.obtenerReporteEconomia())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirReporteEconomia();
			
			// Evento ocurrido en el botón "Configuración de Economía".
			else
			if (nodo == panelVisor.obtenerConfiguracionEconomia())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirConfiguracionEconomia();
			
			// Evento ocurrido en el botón "Transportar de Operación".
			else
			if (nodo == panelVisor.obtenerTransportarOperacion())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirTransportarOperacion();
			
			// Evento ocurrido en el botón "Evolución de Operación".
			else
			if (nodo == panelVisor.obtenerEvolucionOperacion())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirEvolucionOperacion();
			
			// Evento ocurrido en el botón "Reporte de Operación".
			else
			if (nodo == panelVisor.obtenerReporteOperacion())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirReporteOperacion();
			
			// Evento ocurrido en el botón "Configuración de Operación".
			else
			if (nodo == panelVisor.obtenerConfiguracionOperacion())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirConfiguracionOperacion();
			
			// Evento ocurrido en el botón "Planificar de Integración".
			else
			if (nodo == panelVisor.obtenerPlanificarIntegracion())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirPlanificarIntegracion();
			
			// Evento ocurrido en el botón "Graficar de Integración".
			else
			if (nodo == panelVisor.obtenerGraficarIntegracion())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirGraficarIntegracion();	
			
			// Evento ocurrido en el botón "Reporte de Integración".
			else
			if (nodo == panelVisor.obtenerReporteIntegracion())
				panelVisor.frameCircanaPro.panelEscritorio.
				elegirReporteIntegracion();
		}
	}
}