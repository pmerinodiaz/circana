/**
 * @(#)EventoRadioButtonMenuItem.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;

/**
 * Clase que permite incorporar los eventos a las opciones del men� del tipo
 * JRadioButtonMenuItem que tiene la aplicaci�n. Esta clase implementa a la
 * interface ActionListener, con el objetivo de poder realizar diversas acciones
 * frente a los eventos capturados de las opciones del men� de la aplicaci�n.
 *
 * @version 2.0 01/01/05
 * @author Patricio Merino
 * @see ActionListener
 * @see ActionEvent
 * @see JRadioButtonMenuItem
 * @see MenuVentana
 */
public class EventoRadioButtonMenuItem implements ActionListener
{
	/** Para cuando es llamada por la clase MenuVentana. */
	private MenuVentana menuVentana;
	
	/**
	 * M�todo constructor que sirve para ser llamado desde el men� ventana que
	 * tiene la aplicaci�n.
	 *
	 * @param menuVentana Es un objeto de la clase MenuVentana.
	 */
	public EventoRadioButtonMenuItem(MenuVentana menuVentana)
	{
		this.menuVentana = menuVentana;
	}
	
	/**
	 * M�todo en donde se captura el evento sobre una opci�n del men�, cuando
	 * �sta a sido elegida. Se captura el identificador de la opci�n del men�
	 * elegida y se implementa la acci�n a seguir.
	 *
	 * @param evento el evento capturado de la opci�n del men� elegida.
	 */
	public void actionPerformed(ActionEvent evento)
	{
		// Obtener la opci�n del men� en la cual se gener� el evento.
		JRadioButtonMenuItem opcion = (JRadioButtonMenuItem) evento.getSource();
		
		// Evento ocurrido en el men� ventana.
		if (menuVentana != null)
		{
			// Evento ocurrido en la opci�n del men� moderno.
			if (opcion == menuVentana.obtenerItemModerno())
				menuVentana.frameCircanaPro.apariencia(1);
			
			// Evento ocurrido en la opci�n del men� basico.
			else
			if (opcion == menuVentana.obtenerItemBasico())
				menuVentana.frameCircanaPro.apariencia(2);
			
			// Evento ocurrido en la opci�n del men� windows xp.
			else
			if (opcion == menuVentana.obtenerItemWindowsXP())
				menuVentana.frameCircanaPro.apariencia(3);
			
			// Evento ocurrido en la opci�n del men� windows cl�sico.
			else
			if (opcion == menuVentana.obtenerItemWindowsClasico())
				menuVentana.frameCircanaPro.apariencia(4);
		}
	}
}