/**
 * @(#)MenuProyecto.java 2.0 01/03/05
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
 * proyecto que tiene la aplicación. Las opciones del menú proyecto son las
 * siguientes: nuevo, abrir, cerrar, guardar, guardar como y salir.
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
public class MenuProyecto extends JMenu implements InterfaceMenu
{
	/** Puntero al frame que contiene a este menú. */
	public FrameCircanaPro frameCircanaPro;
	
	/** La opción del menú nuevo. */
	private JMenuItem itemNuevo;
	
	/** La opción del menú abrir. */
	private JMenuItem itemAbrir;
	
	/** La opción del menú cerrar. */
	private JMenuItem itemCerrar;
	
	/** La opción del menú guardar. */
	private JMenuItem itemGuardar;
	
	/** La opción del menú guardar como. */
	private JMenuItem itemGuardarComo;
	
	/** La opción del menú salir. */
	private JMenuItem itemSalir;
	
	/**
	 * Método constructor que invoca a los métodos que configuran el menú, sus
	 * opciones y los eventos asociados a los componentes del menú.
	 *
	 * @param frameCircanaPro El objeto que hace referencia al frame que
	 *                        contiene a este menú.
	 */
	public MenuProyecto(FrameCircanaPro frameCircanaPro)
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
		setText("Proyecto");
		setMnemonic('P');
	}
	
	/**
	 * Método que configura los componentes del menú proyecto. Primero se crean
	 * los objetos de la clase JMenuItem, luego se les adjunta a cada opción un
	 * comando único, se adjuntan las opciones al JMenu y finalmente se ve si
	 * las opciones deben estar habilitadas o no.
	 */
	public void configurarComponentes()
	{
		// Crear las opciones del menú.
		itemNuevo = new JMenuItem("Nuevo",
			new ImageIcon("../img/proyecto_nuevo.gif"));
		itemAbrir = new JMenuItem("Abrir...",
			new ImageIcon("../img/proyecto_abrir.gif"));
		itemCerrar = new JMenuItem("Cerrar",
			new ImageIcon("../img/proyecto_cerrar.gif"));
		itemGuardar = new JMenuItem("Guardar",
			new ImageIcon("../img/proyecto_guardar.gif"));
		itemGuardarComo = new JMenuItem("Guardar Como...",
			new ImageIcon("../img/proyecto_guardar_como.gif"));
		itemSalir = new JMenuItem("Salir",
			new ImageIcon("../img/proyecto_salir.gif"));
		
		// Adjuntar un comando único a cada item.
		itemNuevo.setMnemonic(KeyEvent.VK_N);
		itemAbrir.setMnemonic(KeyEvent.VK_A);
		itemCerrar.setMnemonic(KeyEvent.VK_C);
		itemGuardar.setMnemonic(KeyEvent.VK_G);
		itemGuardarComo.setMnemonic(KeyEvent.VK_U);
		itemSalir.setMnemonic(KeyEvent.VK_S);
		
		// Incorporar las opciones al menú.
		add(itemNuevo);
		add(itemAbrir);
		add(itemCerrar);
		addSeparator();
		add(itemGuardar);
		add(itemGuardarComo);
		addSeparator();
		add(itemSalir);
		
		// Habilitar las opciones.
		habilitarOpciones();
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
		
		// Incorporar el escuchador de eventos a los componentes.
		itemNuevo.addActionListener(evento);
		itemAbrir.addActionListener(evento);
		itemCerrar.addActionListener(evento);
		itemGuardar.addActionListener(evento);
		itemGuardarComo.addActionListener(evento);
		itemSalir.addActionListener(evento);
	}
	
	/**
	 * Método que configura las opciones del menú. Específicamente se establece
	 * la propiedad de habilitación de los items del menú, dependiendo de si se
	 * encuentra abierto o no un proyecto.
	 */
	public void habilitarOpciones()
	{
		// Cuando no hay un proyecto abierto.
		if (!frameCircanaPro.obtenerProyectoAbierto())
		{
			itemNuevo.setEnabled(true);
			itemAbrir.setEnabled(true);
			itemCerrar.setEnabled(false);
			itemGuardar.setEnabled(false);
			itemGuardarComo.setEnabled(false);
			itemSalir.setEnabled(true);
		}
		// Cuando hay un proyecto abierto.
		else
		{
			itemNuevo.setEnabled(true);
			itemAbrir.setEnabled(true);
			itemCerrar.setEnabled(true);
			itemGuardar.setEnabled(true);
			itemGuardarComo.setEnabled(true);
			itemSalir.setEnabled(true);
		}
	}
	
	/**
	 * Método que obtiene el objeto itemNuevo.
	 *
	 * @return itemNuevo La opción del menú nuevo.
	 */
	public JMenuItem obtenerItemNuevo()
	{
		return itemNuevo;
	}
	
	/**
	 * Método que obtiene el objeto itemAbrir.
	 *
	 * @return itemAbrir La opción del menú abrir.
	 */
	public JMenuItem obtenerItemAbrir()
	{
		return itemAbrir;
	}
	
	/**
	 * Método que obtiene el objeto itemCerrar.
	 *
	 * @return itemCerrar La opción del menú cerrar.
	 */
	public JMenuItem obtenerItemCerrar()
	{
		return itemCerrar;
	}
	
	/**
	 * Método que obtiene el objeto itemGuardar.
	 *
	 * @return itemGuardar La opción del menú guardar.
	 */
	public JMenuItem obtenerItemGuardar()
	{
		return itemGuardar;
	}
	
	/**
	 * Método que obtiene el objeto itemGuardarComo.
	 *
	 * @return itemGuardarComo La opción del menú guardar como.
	 */
	public JMenuItem obtenerItemGuardarComo()
	{
		return itemGuardarComo;
	}
	
	/**
	 * Método que obtiene el objeto itemSalir.
	 *
	 * @return itemSalir La opción del menú salir.
	 */
	public JMenuItem obtenerItemSalir()
	{
		return itemSalir;
	}
}