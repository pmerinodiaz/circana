/**
 * @(#)EventoFrame.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Clase que permite incorporar los eventos a los frames que tiene la
 * aplicaci�n. En esta clase se implementa a la clase WindowAdapter, con el
 * objetivo de poder realizar diversas acciones frente a los eventos capturados
 * de los frames de la aplicaci�n.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see WindowAdapter
 * @see WindowEvent
 * @see FrameCircanaPro
 * @see FrameConfigurarServidor
 */
public class EventoFrame extends WindowAdapter
{
	/** Apuntador al frame principal de la aplicaci�n. */
	private FrameCircanaPro frameCircanaPro;
		
	/** Apuntador al frame de configuraci�n del servidor. */
	private FrameConfiguracionServidor frameConfiguracionServidor;
	
	/**
	 * M�todo constructor que sirve para ser llamado desde el frame principal 
	 * que tiene la aplicaci�n. En este m�todo se inicializan los objetos que se
	 * usar�n posteriormente en la clase.
	 *
	 * @param frameCircanaPro Es un objeto que hace referencia al frame de la
	 *                        aplicaci�n principal.
	 */
	public EventoFrame(FrameCircanaPro frameCircanaPro)
	{
		this.frameCircanaPro = frameCircanaPro;
	}
	
	/**
	 * M�todo constructor que sirve para ser llamado desde el frame configurar 
	 * servidor que tiene la aplicaci�n. En este m�todo se inicializan los
	 * objetos que se usar�n posteriormente en la clase.
	 *
	 * @param frameConfiguracionServidor Es un objeto que hace referencia al
	 *                                   frame de configuraci�n del servidor
	 */
	public EventoFrame(FrameConfiguracionServidor frameConfiguracionServidor)
	{
		this.frameConfiguracionServidor = frameConfiguracionServidor;
	}
	
	/**
	 * M�todo en donde se captura el evento de cierre sobre el frame y luego 
	 * ejecuta una acci�n asociada a esta.
	 *
	 * @param evento El evento de cierre ocurrido en un frame.
	 */
	public void windowClosing(WindowEvent evento)
	{
		// Evento ocurrido en el frame "frameCircanaPro".
		if (frameCircanaPro != null)
			frameCircanaPro.salir();
		
		// Evento ocurrido en el frame "frameConfiguracionServidor".
		else
		if (frameConfiguracionServidor != null)
			frameConfiguracionServidor.cancelar();
	}
}