/**
 * @(#)MenuHerramientas.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

/**
 * Clase que extiende de la clase JMenu. Contiene todas las opciones del menú
 * herramientas que tiene la aplicación. Las opciones del menú herramientas son
 * las siguientes: configuración del servidor.
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
	/** Puntero al frame que contiene a este menú. */
	public FrameCircanaPro frameCircanaPro;
	
	/** La opción del menú configuración del servidor. */
	private JMenuItem itemConfiguracionServidor;
	
	/**
	 * Método constructor que invoca a los métodos que configuran el menú, sus
	 * opciones y los eventos asociados.
	 *
	 * @param frameCircanaPro El objeto que hace referencia al frame que
	 *                        contiene a este menú.
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
	 * Método en donde se configurar diversas propiedades que tiene este menú.
	 * Las opciones que se cambian son los atributos que se derivan de la clase
	 * JMenu.
	 */
	public void configurarMenu()
	{
		setText("Herramientas");
		setMnemonic('H');
	}
	
	/**
	 * Método que configura las opciones del menú herramientas. Primero se crean
	 * los objetos de la clase JMenuItem, luego se les adjunta a cada opción un
	 * comando único, se adjuntan las opciones al JMenu y finalmente se ve si
	 * las opciones deben estar habilitadas o no.
	 */
	public void configurarComponentes()
	{
		// Crear las opciones del menú.
		itemConfiguracionServidor = new JMenuItem("Configuración del Servidor",
			new ImageIcon("../img/herramientas_configuracion_servidor.gif"));
		
		// Adjuntar un comando único a cada item.
		itemConfiguracionServidor.setMnemonic(KeyEvent.VK_C);
		
		// Incorporar las opciones al menú.
		add(itemConfiguracionServidor);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los elementos del menú. En
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
	 * Método que configura las opciones del menú. Específicamente se establece
	 * la propiedad de habilitación de los items del menú, dependiendo de si se
	 * encuentra abierto o no un proyecto.
	 */
	public void habilitarOpciones()
	{
	}
	
	/**
	 * Método que obtiene el objeto itemConfigurarServidor.
	 *
	 * @return itemConfigurarServidor El item del menú configurar servidor.
	 */
	public JMenuItem obtenerItemConfiguracionServidor()
	{
		return itemConfiguracionServidor;
	}
}