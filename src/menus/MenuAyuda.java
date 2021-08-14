/**
 * @(#)MenuAyuda.java 2.0 01/03/05
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
 * ayuda que tiene la aplicación. Las opciones del menú ayuda son las
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
	/** Puntero al frame que contiene a este menú. */
	public FrameCircanaPro frameCircanaPro;
	
	/** La opción del menú contenido. */
	private JMenuItem itemContenido;
	
	/** La opción del menú ejemplos de inteligencia artificial. */
	private JMenuItem itemEjemplos;
	
	/** La opción del menú CIRCANA Pro en Internet. */
	private JMenuItem itemInternet;
	
	/** La opción del menú acerca de CIRCANA Pro. */
	private JMenuItem itemAcerca;
	
	/**
	 * Método constructor que invoca a los métodos que configuran el menú, sus
	 * opciones y los eventos asociados.
	 *
	 * @param frameCircanaPro El objeto que hace referencia al frame que
	 *                        contiene a este menú.
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
	 * Método en donde se configurar diversas propiedades que tiene este menú.
	 * Las opciones que se cambian son los atributos que se derivan de la clase
	 * JMenu.
	 */
	public void configurarMenu()
	{
		setText("Ayuda");
		setMnemonic('y');
	}
	
	/**
	 * Método que configura las opciones del menú ayuda. Primero se crean los
	 * objetos de la clase JMenuItem, luego se les adjunta a cada opción un
	 * comando único, se adjuntan las opciones al JMenu y finalmente se ve si
	 * las opciones deben estar habilitadas o no.
	 */
	public void configurarComponentes()
	{
		// Crear las opciones del menú.
		itemContenido = new JMenuItem("Contenido",
			new ImageIcon("../img/ayuda_contenido.gif"));
		itemEjemplos = new JMenuItem("Ejemplos de Inteligencia Artificial",
			new ImageIcon("../img/ayuda_ejemplos_inteligencia_artificial.gif"));
		itemInternet = new JMenuItem("CIRCANA Pro en Internet",
			new ImageIcon("../img/ayuda_circana_pro_internet.gif"));
		itemAcerca = new JMenuItem("Acerca de CIRCANA Pro",
			new ImageIcon("../img/ayuda_acerca_circana_pro.gif"));
		
		// Adjuntar un comando único a cada item.
		itemContenido.setMnemonic(KeyEvent.VK_C);
		itemEjemplos.setMnemonic(KeyEvent.VK_E);
		itemInternet.setMnemonic(KeyEvent.VK_I);
		itemAcerca.setMnemonic(KeyEvent.VK_A);
		
		// Incorporar las opciones al menú.
		add(itemContenido);
		add(itemEjemplos);
		addSeparator();
		add(itemInternet);
		add(itemAcerca);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los elementos del menú. En
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
	 * Método que configura las opciones del menú. Específicamente se establece
	 * la propiedad de habilitación de los items del menú, dependiendo de si se
	 * encuentra abierto o no un proyecto.
	 */
	public void habilitarOpciones()
	{
	}
	
	/**
	 * Método que obtiene el objeto itemContenido.
	 *
	 * @return itemContenido El item del menú contenido.
	 */
	public JMenuItem obtenerItemContenido()
	{
		return itemContenido;
	}
	
	/**
	 * Método que obtiene el objeto itemEjemplos.
	 *
	 * @return itemEjemplos El item del menú ejemplos de inteligencia
	 *                      artificial.
	 */
	public JMenuItem obtenerItemEjemplos()
	{
		return itemEjemplos;
	}
	
	/**
	 * Método que obtiene el objeto itemInternet.
	 *
	 * @return itemInternet El item del menú CIRCANA Pro en Internet.
	 */
	public JMenuItem obtenerItemInternet()
	{
		return itemInternet;
	}
	
	/**
	 * Método que obtiene el objeto itemAcerca.
	 *
	 * @return itemAcerca El item del menú acerca de CIRCANA Pro.
	 */
	public JMenuItem obtenerItemAcerca()
	{
		return itemAcerca;
	}
}