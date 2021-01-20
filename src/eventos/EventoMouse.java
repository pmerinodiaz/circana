/**
 * @(#)EventoMouse.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase que permite incorporar los eventos a los movimientos y acciones que
 * hace el usuario con el mouse dentro de la aplicación. Esta clase extiende de
 * la clase Mouseadapter, con el objetivo de poder realizar diversas acciones
 * frente a los eventos capturados de los movimientos y acciones del mouse
 * dentro de la aplicación.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see MouseAdapter
 * @see MouseEvent
 * @see SubPanelMapaEcosistema
 */
public class EventoMouse extends MouseMotionAdapter
{
	/** Para cuando es llamada por la clase SubPanelMapaEcosistema. */
	private SubPanelMapaEcosistema subpanelMapaEcosistema;
	
	/**
	 * Método constructor que sirve para ser llamado desde el panel del mapa del
	 * ecosistema que tiene la aplicación. En este método se inicializan los
	 * objetos que se usarán posteriormente en la clase.
	 *
	 * @param subpanelMapaEcosistema Es un puntero que hace referencia al objeto
	 *                               del tipo SubPanelMapaEcosistema que está
	 *                               llamando a este constructor.
	 */
	public EventoMouse(SubPanelMapaEcosistema subpanelMapaEcosistema)
	{
		this.subpanelMapaEcosistema = subpanelMapaEcosistema;
	}
	
	/**
	 * Método en donde se captura el evento ocurrido cuando se ha realizado una
	 * acción con el mouse. Se capturan las coordenadas en donde se hizo la
	 * acción y se implementa la acción a seguir. Dependiendo de los objetos
	 * creados e inicializados en el constructor se determina desde donde fue
	 * llamado este método.
	 *
	 * @param evento El evento capturado del evento del mouse ocurrido.
	 */
	public void mouseMoved(MouseEvent evento)
	{
		// Obtener las coordenadas en la cual se generó el evento.
		int x = evento.getX();
		int y = evento.getY();
		
		// Evento ocurrido en SubPanelMapaEcosistema.
		if (subpanelMapaEcosistema != null)
			subpanelMapaEcosistema.graficarPosicion(x, y);
	}
}