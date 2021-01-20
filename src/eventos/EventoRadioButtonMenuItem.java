/**
 * @(#)EventoRadioButtonMenuItem.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;

/**
 * Clase que permite incorporar los eventos a las opciones del menú del tipo
 * JRadioButtonMenuItem que tiene la aplicación. Esta clase implementa a la
 * interface ActionListener, con el objetivo de poder realizar diversas acciones
 * frente a los eventos capturados de las opciones del menú de la aplicación.
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
	 * Método constructor que sirve para ser llamado desde el menú ventana que
	 * tiene la aplicación.
	 *
	 * @param menuVentana Es un objeto de la clase MenuVentana.
	 */
	public EventoRadioButtonMenuItem(MenuVentana menuVentana)
	{
		this.menuVentana = menuVentana;
	}
	
	/**
	 * Método en donde se captura el evento sobre una opción del menú, cuando
	 * ésta a sido elegida. Se captura el identificador de la opción del menú
	 * elegida y se implementa la acción a seguir.
	 *
	 * @param evento el evento capturado de la opción del menú elegida.
	 */
	public void actionPerformed(ActionEvent evento)
	{
		// Obtener la opción del menú en la cual se generó el evento.
		JRadioButtonMenuItem opcion = (JRadioButtonMenuItem) evento.getSource();
		
		// Evento ocurrido en el menú ventana.
		if (menuVentana != null)
		{
			// Evento ocurrido en la opción del menú moderno.
			if (opcion == menuVentana.obtenerItemModerno())
				menuVentana.frameCircanaPro.apariencia(1);
			
			// Evento ocurrido en la opción del menú basico.
			else
			if (opcion == menuVentana.obtenerItemBasico())
				menuVentana.frameCircanaPro.apariencia(2);
			
			// Evento ocurrido en la opción del menú windows xp.
			else
			if (opcion == menuVentana.obtenerItemWindowsXP())
				menuVentana.frameCircanaPro.apariencia(3);
			
			// Evento ocurrido en la opción del menú windows clásico.
			else
			if (opcion == menuVentana.obtenerItemWindowsClasico())
				menuVentana.frameCircanaPro.apariencia(4);
		}
	}
}