/**
 * @(#)EventoMenuItem.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

/**
 * Clase que permite incorporar los eventos a las opciones del men� que tiene la
 * aplicaci�n. En esta clase se implementa a la interface ActionListener, con el
 * objetivo de poder realizar acciones frente a los eventos capturados de las
 * opciones del men� de la aplicaci�n.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see ActionListener
 * @see ActionEvent
 * @see JMenuItem
 * @see MenuProyecto
 * @see MenuAdministracionDatos
 * @see MenuHerramientas
 * @see MenuVentana
 * @see MenuAyuda
 */
public class EventoMenuItem implements ActionListener
{
	/** Para cuando es llamada por la clase MenuProyecto. */
	private MenuProyecto menuProyecto;
	
	/** Para cuando es llamada por la clase MenuAdministracionDatos. */
	private MenuAdministracionDatos menuAdministracionDatos;
	
	/** Para cuando es llamada por la clase MenuHerramientas. */
	private MenuHerramientas menuHerramientas;
	
	/** Para cuando es llamada por la clase MenuVentana. */
	private MenuVentana menuVentana;
	
	/** Para cuando es llamada por la clase MenuAyuda. */
	private MenuAyuda menuAyuda;
	
	/**
	 * M�todo constructor que sirve para ser llamado desde el men� proyecto que
	 * tiene la aplicaci�n.
	 *
	 * @param menuProyecto Es un objeto de la clase MenuProyecto.
	 */
	public EventoMenuItem(MenuProyecto menuProyecto)
	{
		this.menuProyecto = menuProyecto;
	}
	
	/**
	 * M�todo constructor que sirve para ser llamado desde el men�
	 * administraci�n de datos que tiene la aplicaci�n.
	 *
	 * @param menuAdministracionDatos Es un objeto de la clase
	 *                                MenuAdministracionDatos.
	 */
	public EventoMenuItem(MenuAdministracionDatos menuAdministracionDatos)
	{
		this.menuAdministracionDatos = menuAdministracionDatos;
	}
	
	/**
	 * M�todo constructor que sirve para ser llamado desde el men� herramientas
	 * que tiene la aplicaci�n.
	 *
	 * @param menuHerramientas Es un objeto de la clase MenuHerramientas.
	 */
	public EventoMenuItem(MenuHerramientas menuHerramientas)
	{
		this.menuHerramientas = menuHerramientas;
	}
	
	/**
	 * M�todo constructor que sirve para ser llamado desde el men� ventana que
	 * tiene la aplicaci�n.
	 *
	 * @param menuVentana Es un objeto de la clase MenuVentana.
	 */
	public EventoMenuItem(MenuVentana menuVentana)
	{
		this.menuVentana = menuVentana;
	}
	
	/**
	 * M�todo constructor que sirve para ser llamado desde el men� ayuda que
	 * tiene la aplicaci�n.
	 *
	 * @param menuAyuda Es un objeto de la clase MenuAyuda.
	 */
	public EventoMenuItem(MenuAyuda menuAyuda)
	{
		this.menuAyuda = menuAyuda;
	}
	
	/**
	 * M�todo en donde se captura el evento sobre una opci�n del men�, cuando
	 * �sta a sido elegida. Se captura el identificador de la opci�n del men�
	 * elegida y se implementa la acci�n a seguir.
	 *
	 * @param evento El evento capturado de la opci�n del men� elegida.
	 */
	public void actionPerformed(ActionEvent evento)
	{
		// Obtener la opci�n del men� en la cual se gener� el evento.
		JMenuItem opcion = (JMenuItem) evento.getSource();
		
		// Evento ocurrido en el men� proyecto.
		if (menuProyecto != null)
		{
			// Evento ocurrido en la opci�n del men� nuevo.
			if (opcion == menuProyecto.obtenerItemNuevo())
				menuProyecto.frameCircanaPro.nuevoProyecto();
			
			// Evento ocurrido en la opci�n del men� abrir.
			else
			if (opcion == menuProyecto.obtenerItemAbrir())
				menuProyecto.frameCircanaPro.abrir();
			
			// Evento ocurrido en la opci�n del men� cerrar.
			else
			if (opcion == menuProyecto.obtenerItemCerrar())
				menuProyecto.frameCircanaPro.cerrar();
			
			// Evento ocurrido en la opci�n del men� guardar.
			else
			if (opcion == menuProyecto.obtenerItemGuardar())
				menuProyecto.frameCircanaPro.guardar();
			
			// Evento ocurrido en la opci�n del men� guardar como.
			else
			if (opcion == menuProyecto.obtenerItemGuardarComo())
				menuProyecto.frameCircanaPro.guardarComo();
			
			// Evento ocurrido en la opci�n del men� salir.
			else
			if (opcion == menuProyecto.obtenerItemSalir())
				menuProyecto.frameCircanaPro.salir();
		}
		
		// Evento ocurrido en el men� administraci�n de datos.
		else
		if (menuAdministracionDatos != null)
		{
			// Evento ocurrido en la opci�n del men� evaluaci�n de stock.
			if (opcion == menuAdministracionDatos.obtenerItemEvaluacionStock())
				menuAdministracionDatos.frameCircanaPro.evaluacionStock();
			
			// Evento ocurrido en la opci�n del men� caladero.
			else
			if (opcion == menuAdministracionDatos.obtenerItemCaladero())
				menuAdministracionDatos.frameCircanaPro.caladero();
			
			// Evento ocurrido en la opci�n del men� mercado.
			else
			if (opcion == menuAdministracionDatos.obtenerItemMercado())
				menuAdministracionDatos.frameCircanaPro.mercado();
			
			// Evento ocurrido en la opci�n del men� medio de transporte.
			else
			if (opcion == menuAdministracionDatos.obtenerItemMedioTransporte())
				menuAdministracionDatos.frameCircanaPro.medioTransporte();
			
			// Evento ocurrido en la opci�n del men� punto de demanda.
			else
			if (opcion == menuAdministracionDatos.obtenerItemPuntoDemanda())
				menuAdministracionDatos.frameCircanaPro.puntoDemanda();
		}
		
		// Evento ocurrido en el men� herramientas.
		else
		if (menuHerramientas != null)
		{
			// Evento ocurrido en la opci�n del men� configuraci�n del servidor.
			if (opcion == menuHerramientas.obtenerItemConfiguracionServidor())
				menuHerramientas.frameCircanaPro.configuracionServidor();
		}
		
		// Evento ocurrido en el men� ventana.
		else
		if (menuVentana != null)
		{
			// Evento ocurrido en la opci�n del men� cascada.
			if (opcion == menuVentana.obtenerItemCascada())
				menuVentana.frameCircanaPro.panelEscritorio.posicionarFrames(1);
			
			// Evento ocurrido en la opci�n del men� mosaico horizontal.
			else	
			if (opcion == menuVentana.obtenerItemHorizontal())
				menuVentana.frameCircanaPro.panelEscritorio.posicionarFrames(2);
			
			// Evento ocurrido en la opci�n del men� mosaico vertical.
			else	
			if (opcion == menuVentana.obtenerItemVertical())
				menuVentana.frameCircanaPro.panelEscritorio.posicionarFrames(3);
			
			// Evento ocurrido en la opci�n del men� minimizar todo.	
			else	
			if (opcion == menuVentana.obtenerItemMinimizarTodo())
				menuVentana.frameCircanaPro.panelEscritorio.minimizarFrames();	
		}
		
		// Evento ocurrido en el men� ayuda.
		else
		if (menuAyuda != null)
		{
			// Evento ocurrido en la opci�n del men� contenido.
			if (opcion == menuAyuda.obtenerItemContenido())
				menuAyuda.frameCircanaPro.contenido();
			
			// Evento ocurrido en la opci�n del men� ejemplos de IA.
			else
			if (opcion == menuAyuda.obtenerItemEjemplos())
				menuAyuda.frameCircanaPro.ejemplos();
			
			// Evento ocurrido en la opci�n del men� acerca de CIRCANA Pro en
			// Internet.
			else
			if (opcion == menuAyuda.obtenerItemInternet())
				menuAyuda.frameCircanaPro.internet();
			
			// Evento ocurrido en la opci�n del men� acerca de CIRCANA Pro.
			else
			if (opcion == menuAyuda.obtenerItemAcerca())
				menuAyuda.frameCircanaPro.acerca();
		}
	}
}