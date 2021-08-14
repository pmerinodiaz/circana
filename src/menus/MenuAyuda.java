/**
 * @(#)MenuAyuda.java 2.0 01/03/05
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
 * ayuda que tiene la aplicaci�n. Las opciones del men� ayuda son las
 * siguientes: contenido, ejemplos de inteligencia artificial, CIRCANA Pro en
 * Internet y acerca de CIRCANA Pro.
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
public class MenuAyuda extends JMenu implements InterfaceMenu
{
	/** Puntero al frame que contiene a este men�. */
	public FrameCircanaPro frameCircanaPro;
	
	/** La opci�n del men� contenido. */
	private JMenuItem itemContenido;
	
	/** La opci�n del men� ejemplos de inteligencia artificial. */
	private JMenuItem itemEjemplos;
	
	/** La opci�n del men� CIRCANA Pro en Internet. */
	private JMenuItem itemInternet;
	
	/** La opci�n del men� acerca de CIRCANA Pro. */
	private JMenuItem itemAcerca;
	
	/**
	 * M�todo constructor que invoca a los m�todos que configuran el men�, sus
	 * opciones y los eventos asociados.
	 *
	 * @param frameCircanaPro El objeto que hace referencia al frame que
	 *                        contiene a este men�.
	 */
	public MenuAyuda(FrameCircanaPro frameCircanaPro)
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
		setText("Ayuda");
		setMnemonic('y');
	}
	
	/**
	 * M�todo que configura las opciones del men� ayuda. Primero se crean los
	 * objetos de la clase JMenuItem, luego se les adjunta a cada opci�n un
	 * comando �nico, se adjuntan las opciones al JMenu y finalmente se ve si
	 * las opciones deben estar habilitadas o no.
	 */
	public void configurarComponentes()
	{
		// Crear las opciones del men�.
		itemContenido = new JMenuItem("Contenido",
			new ImageIcon("../img/ayuda_contenido.gif"));
		itemEjemplos = new JMenuItem("Ejemplos de Inteligencia Artificial",
			new ImageIcon("../img/ayuda_ejemplos_inteligencia_artificial.gif"));
		itemInternet = new JMenuItem("CIRCANA Pro en Internet",
			new ImageIcon("../img/ayuda_circana_pro_internet.gif"));
		itemAcerca = new JMenuItem("Acerca de CIRCANA Pro",
			new ImageIcon("../img/ayuda_acerca_circana_pro.gif"));
		
		// Adjuntar un comando �nico a cada item.
		itemContenido.setMnemonic(KeyEvent.VK_C);
		itemEjemplos.setMnemonic(KeyEvent.VK_E);
		itemInternet.setMnemonic(KeyEvent.VK_I);
		itemAcerca.setMnemonic(KeyEvent.VK_A);
		
		// Incorporar las opciones al men�.
		add(itemContenido);
		add(itemEjemplos);
		addSeparator();
		add(itemInternet);
		add(itemAcerca);
	}
	
	/**
	 * M�todo que adjunta los escuchadores eventos a los elementos del men�. En
	 * particular se incorpora el escuchador de eventos del tipo EventoMenuItem
	 * a los JMenuItem que tiene este JMenu.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoMenuItem evento = new EventoMenuItem(this);
		
		// Incorporar el escuchador de eventos al elemento.
		itemContenido.addActionListener(evento);
		itemEjemplos.addActionListener(evento);
		itemInternet.addActionListener(evento);
		itemAcerca.addActionListener(evento);
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
	 * M�todo que obtiene el objeto itemContenido.
	 *
	 * @return itemContenido El item del men� contenido.
	 */
	public JMenuItem obtenerItemContenido()
	{
		return itemContenido;
	}
	
	/**
	 * M�todo que obtiene el objeto itemEjemplos.
	 *
	 * @return itemEjemplos El item del men� ejemplos de inteligencia
	 *                      artificial.
	 */
	public JMenuItem obtenerItemEjemplos()
	{
		return itemEjemplos;
	}
	
	/**
	 * M�todo que obtiene el objeto itemInternet.
	 *
	 * @return itemInternet El item del men� CIRCANA Pro en Internet.
	 */
	public JMenuItem obtenerItemInternet()
	{
		return itemInternet;
	}
	
	/**
	 * M�todo que obtiene el objeto itemAcerca.
	 *
	 * @return itemAcerca El item del men� acerca de CIRCANA Pro.
	 */
	public JMenuItem obtenerItemAcerca()
	{
		return itemAcerca;
	}
}