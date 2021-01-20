/**
 * @(#)EventoLink.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent;

/**
 * Clase que permite incorporar los eventos a los links URL que existen dentro
 * de la aplicaci�n. Esta clase implementa a la interface HyperlinkListener, con
 * el objetivo de poder realizar diversas acciones frente a los eventos
 * capturados de los links hacia p�ginas Webs.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see HyperlinkListener
 * @see HyperlinkEvent
 * @see FrameInternet
 */
public class EventoLink implements HyperlinkListener
{
	/** Para cuando es llamada por la clase FrameInternet. */
	private FrameInternet frameInternet;
	
	/**
	 * M�todo constructor que sirve para ser llamado desde el frame de internet
	 * que tiene la aplicaci�n. En este m�todo se inicializan los objetos que se
	 * usar�n posteriormente en la clase.
	 *
	 * @param frameInternet Es un puntero que hace referencia al objeto del tipo
	 *                      FrameInternet que est� llamando a este constructor.
	 */
	public EventoLink(FrameInternet frameInternet)
	{
		this.frameInternet = frameInternet;
	}
	
	/**
	 * M�todo en donde se captura el evento ocurrido en un link. Se captura el
	 * evento ocurrido en el link y se implementa la acci�n a seguir.
	 * Dependiendo de los objetos creados e inicializados en el constructor se
	 * determina desde donde fue llamado este m�todo.
	 *
	 * @param evento El evento capturado del link.
	 */
	public void hyperlinkUpdate(HyperlinkEvent evento)
	{
		// Cuando el evento est� activo.
		if (evento.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
		{
			// Evento ocurrido en el "frameInternet".
			if (frameInternet != null)
				frameInternet.irPaginaLink(evento.getURL());
		}
	}
}