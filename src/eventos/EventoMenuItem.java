/**
 * @(#)EventoMenuItem.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

/**
 * Clase que permite incorporar los eventos a las opciones del menú que tiene la
 * aplicación. En esta clase se implementa a la interface ActionListener, con el
 * objetivo de poder realizar acciones frente a los eventos capturados de las
 * opciones del menú de la aplicación.
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
	 * Método constructor que sirve para ser llamado desde el menú proyecto que
	 * tiene la aplicación.
	 *
	 * @param menuProyecto Es un objeto de la clase MenuProyecto.
	 */
	public EventoMenuItem(MenuProyecto menuProyecto)
	{
		this.menuProyecto = menuProyecto;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el menú
	 * administración de datos que tiene la aplicación.
	 *
	 * @param menuAdministracionDatos Es un objeto de la clase
	 *                                MenuAdministracionDatos.
	 */
	public EventoMenuItem(MenuAdministracionDatos menuAdministracionDatos)
	{
		this.menuAdministracionDatos = menuAdministracionDatos;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el menú herramientas
	 * que tiene la aplicación.
	 *
	 * @param menuHerramientas Es un objeto de la clase MenuHerramientas.
	 */
	public EventoMenuItem(MenuHerramientas menuHerramientas)
	{
		this.menuHerramientas = menuHerramientas;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el menú ventana que
	 * tiene la aplicación.
	 *
	 * @param menuVentana Es un objeto de la clase MenuVentana.
	 */
	public EventoMenuItem(MenuVentana menuVentana)
	{
		this.menuVentana = menuVentana;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el menú ayuda que
	 * tiene la aplicación.
	 *
	 * @param menuAyuda Es un objeto de la clase MenuAyuda.
	 */
	public EventoMenuItem(MenuAyuda menuAyuda)
	{
		this.menuAyuda = menuAyuda;
	}
	
	/**
	 * Método en donde se captura el evento sobre una opción del menú, cuando
	 * ésta a sido elegida. Se captura el identificador de la opción del menú
	 * elegida y se implementa la acción a seguir.
	 *
	 * @param evento El evento capturado de la opción del menú elegida.
	 */
	public void actionPerformed(ActionEvent evento)
	{
		// Obtener la opción del menú en la cual se generó el evento.
		JMenuItem opcion = (JMenuItem) evento.getSource();
		
		// Evento ocurrido en el menú proyecto.
		if (menuProyecto != null)
		{
			// Evento ocurrido en la opción del menú nuevo.
			if (opcion == menuProyecto.obtenerItemNuevo())
				menuProyecto.frameCircanaPro.nuevoProyecto();
			
			// Evento ocurrido en la opción del menú abrir.
			else
			if (opcion == menuProyecto.obtenerItemAbrir())
				menuProyecto.frameCircanaPro.abrir();
			
			// Evento ocurrido en la opción del menú cerrar.
			else
			if (opcion == menuProyecto.obtenerItemCerrar())
				menuProyecto.frameCircanaPro.cerrar();
			
			// Evento ocurrido en la opción del menú guardar.
			else
			if (opcion == menuProyecto.obtenerItemGuardar())
				menuProyecto.frameCircanaPro.guardar();
			
			// Evento ocurrido en la opción del menú guardar como.
			else
			if (opcion == menuProyecto.obtenerItemGuardarComo())
				menuProyecto.frameCircanaPro.guardarComo();
			
			// Evento ocurrido en la opción del menú salir.
			else
			if (opcion == menuProyecto.obtenerItemSalir())
				menuProyecto.frameCircanaPro.salir();
		}
		
		// Evento ocurrido en el menú administración de datos.
		else
		if (menuAdministracionDatos != null)
		{
			// Evento ocurrido en la opción del menú evaluación de stock.
			if (opcion == menuAdministracionDatos.obtenerItemEvaluacionStock())
				menuAdministracionDatos.frameCircanaPro.evaluacionStock();
			
			// Evento ocurrido en la opción del menú caladero.
			else
			if (opcion == menuAdministracionDatos.obtenerItemCaladero())
				menuAdministracionDatos.frameCircanaPro.caladero();
			
			// Evento ocurrido en la opción del menú mercado.
			else
			if (opcion == menuAdministracionDatos.obtenerItemMercado())
				menuAdministracionDatos.frameCircanaPro.mercado();
			
			// Evento ocurrido en la opción del menú medio de transporte.
			else
			if (opcion == menuAdministracionDatos.obtenerItemMedioTransporte())
				menuAdministracionDatos.frameCircanaPro.medioTransporte();
			
			// Evento ocurrido en la opción del menú punto de demanda.
			else
			if (opcion == menuAdministracionDatos.obtenerItemPuntoDemanda())
				menuAdministracionDatos.frameCircanaPro.puntoDemanda();
		}
		
		// Evento ocurrido en el menú herramientas.
		else
		if (menuHerramientas != null)
		{
			// Evento ocurrido en la opción del menú configuración del servidor.
			if (opcion == menuHerramientas.obtenerItemConfiguracionServidor())
				menuHerramientas.frameCircanaPro.configuracionServidor();
		}
		
		// Evento ocurrido en el menú ventana.
		else
		if (menuVentana != null)
		{
			// Evento ocurrido en la opción del menú cascada.
			if (opcion == menuVentana.obtenerItemCascada())
				menuVentana.frameCircanaPro.panelEscritorio.posicionarFrames(1);
			
			// Evento ocurrido en la opción del menú mosaico horizontal.
			else	
			if (opcion == menuVentana.obtenerItemHorizontal())
				menuVentana.frameCircanaPro.panelEscritorio.posicionarFrames(2);
			
			// Evento ocurrido en la opción del menú mosaico vertical.
			else	
			if (opcion == menuVentana.obtenerItemVertical())
				menuVentana.frameCircanaPro.panelEscritorio.posicionarFrames(3);
			
			// Evento ocurrido en la opción del menú minimizar todo.	
			else	
			if (opcion == menuVentana.obtenerItemMinimizarTodo())
				menuVentana.frameCircanaPro.panelEscritorio.minimizarFrames();	
		}
		
		// Evento ocurrido en el menú ayuda.
		else
		if (menuAyuda != null)
		{
			// Evento ocurrido en la opción del menú contenido.
			if (opcion == menuAyuda.obtenerItemContenido())
				menuAyuda.frameCircanaPro.contenido();
			
			// Evento ocurrido en la opción del menú ejemplos de IA.
			else
			if (opcion == menuAyuda.obtenerItemEjemplos())
				menuAyuda.frameCircanaPro.ejemplos();
			
			// Evento ocurrido en la opción del menú acerca de CIRCANA Pro en
			// Internet.
			else
			if (opcion == menuAyuda.obtenerItemInternet())
				menuAyuda.frameCircanaPro.internet();
			
			// Evento ocurrido en la opción del menú acerca de CIRCANA Pro.
			else
			if (opcion == menuAyuda.obtenerItemAcerca())
				menuAyuda.frameCircanaPro.acerca();
		}
	}
}