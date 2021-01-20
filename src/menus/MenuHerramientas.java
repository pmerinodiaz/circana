/**
 * @(#)MenuHerramientas.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

/**
 * Clase que extiende de la clase JMenu. Contiene todas las opciones del men�
 * herramientas que tiene la aplicaci�n. Las opciones del men� herramientas son
 * las siguientes: configuraci�n del servidor.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see KeyEvent
 * @see JMenu
 * @see JMenuItem
 * @see ImageIcon
 * @see FrameCircanaPro
 * @see EventoMenuItem
 * @see InterfaceMenu
 */
public class MenuHerramientas extends JMenu implements InterfaceMenu
{
	/** Puntero al frame que contiene a este men�. */
	public FrameCircanaPro frameCircanaPro;
	
	/** La opci�n del men� configuraci�n del servidor. */
	private JMenuItem itemConfiguracionServidor;
	
	/**
	 * M�todo constructor que invoca a los m�todos que configuran el men�, sus
	 * opciones y los eventos asociados.
	 *
	 * @param frameCircanaPro El objeto que hace referencia al frame que
	 *                        contiene a este men�.
	 */
	public MenuHerramientas(FrameCircanaPro frameCircanaPro)
	{
		// Inicializar el puntero.
		this.frameCircanaPro = frameCircanaPro;
		
		// Configurar.
		configurarMenu();
		configurarComponentes();
		configurarEventos();
	}
	
	/**
	 * M�todo en donde se configurar diversas propiedades que tiene este men�.
	 * Las opciones que se cambian son los atributos que se derivan de la clase
	 * JMenu.
	 */
	public void configurarMenu()
	{
		setText("Herramientas");
		setMnemonic('H');
	}
	
	/**
	 * M�todo que configura las opciones del men� herramientas. Primero se crean
	 * los objetos de la clase JMenuItem, luego se les adjunta a cada opci�n un
	 * comando �nico, se adjuntan las opciones al JMenu y finalmente se ve si
	 * las opciones deben estar habilitadas o no.
	 */
	public void configurarComponentes()
	{
		// Crear las opciones del men�.
		itemConfiguracionServidor = new JMenuItem("Configuraci�n del Servidor",
			new ImageIcon("../img/herramientas_configuracion_servidor.gif"));
		
		// Adjuntar un comando �nico a cada item.
		itemConfiguracionServidor.setMnemonic(KeyEvent.VK_C);
		
		// Incorporar las opciones al men�.
		add(itemConfiguracionServidor);
	}
	
	/**
	 * M�todo que adjunta los escuchadores eventos a los elementos del men�. En
	 * particular se incorpora el escuchador de eventos del tipo EventoMenuItem
	 * a los JMenuItem que tiene este JMenu.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoMenuItem eventoMenuItem = new EventoMenuItem(this);
		
		// Incorporar el escuchador de eventos a los componentes.
		itemConfiguracionServidor.addActionListener(eventoMenuItem);
	}
	
	/**
	 * M�todo que configura las opciones del men�. Espec�ficamente se establece
	 * la propiedad de habilitaci�n de los items del men�, dependiendo de si se
	 * encuentra abierto o no un proyecto.
	 */
	public void habilitarOpciones()
	{
	}
	
	/**
	 * M�todo que obtiene el objeto itemConfigurarServidor.
	 *
	 * @return itemConfigurarServidor El item del men� configurar servidor.
	 */
	public JMenuItem obtenerItemConfiguracionServidor()
	{
		return itemConfiguracionServidor;
	}
}