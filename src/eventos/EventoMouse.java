/**
 * @(#)EventoMouse.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase que permite incorporar los eventos a los movimientos y acciones que
 * hace el usuario con el mouse dentro de la aplicaci�n. Esta clase extiende de
 * la clase Mouseadapter, con el objetivo de poder realizar diversas acciones
 * frente a los eventos capturados de los movimientos y acciones del mouse
 * dentro de la aplicaci�n.
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
	 * M�todo constructor que sirve para ser llamado desde el panel del mapa del
	 * ecosistema que tiene la aplicaci�n. En este m�todo se inicializan los
	 * objetos que se usar�n posteriormente en la clase.
	 *
	 * @param subpanelMapaEcosistema Es un puntero que hace referencia al objeto
	 *                               del tipo SubPanelMapaEcosistema que est�
	 *                               llamando a este constructor.
	 */
	public EventoMouse(SubPanelMapaEcosistema subpanelMapaEcosistema)
	{
		this.subpanelMapaEcosistema = subpanelMapaEcosistema;
	}
	
	/**
	 * M�todo en donde se captura el evento ocurrido cuando se ha realizado una
	 * acci�n con el mouse. Se capturan las coordenadas en donde se hizo la
	 * acci�n y se implementa la acci�n a seguir. Dependiendo de los objetos
	 * creados e inicializados en el constructor se determina desde donde fue
	 * llamado este m�todo.
	 *
	 * @param evento El evento capturado del evento del mouse ocurrido.
	 */
	public void mouseMoved(MouseEvent evento)
	{
		// Obtener las coordenadas en la cual se gener� el evento.
		int x = evento.getX();
		int y = evento.getY();
		
		// Evento ocurrido en SubPanelMapaEcosistema.
		if (subpanelMapaEcosistema != null)
			subpanelMapaEcosistema.graficarPosicion(x, y);
	}
}