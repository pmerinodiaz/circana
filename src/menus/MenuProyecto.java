/**
 * @(#)MenuProyecto.java 2.0 01/03/05
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
 * proyecto que tiene la aplicaci�n. Las opciones del men� proyecto son las
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
	/** Puntero al frame que contiene a este men�. */
	public FrameCircanaPro frameCircanaPro;
	
	/** La opci�n del men� nuevo. */
	private JMenuItem itemNuevo;
	
	/** La opci�n del men� abrir. */
	private JMenuItem itemAbrir;
	
	/** La opci�n del men� cerrar. */
	private JMenuItem itemCerrar;
	
	/** La opci�n del men� guardar. */
	private JMenuItem itemGuardar;
	
	/** La opci�n del men� guardar como. */
	private JMenuItem itemGuardarComo;
	
	/** La opci�n del men� salir. */
	private JMenuItem itemSalir;
	
	/**
	 * M�todo constructor que invoca a los m�todos que configuran el men�, sus
	 * opciones y los eventos asociados a los componentes del men�.
	 *
	 * @param frameCircanaPro El objeto que hace referencia al frame que
	 *                        contiene a este men�.
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
	 * M�todo en donde se configurar diversas propiedades que tiene este men�.
	 * Las opciones que se cambian son los atributos que se derivan de la clase
	 * JMenu.
	 */
	public void configurarMenu()
	{
		setText("Proyecto");
		setMnemonic('P');
	}
	
	/**
	 * M�todo que configura los componentes del men� proyecto. Primero se crean
	 * los objetos de la clase JMenuItem, luego se les adjunta a cada opci�n un
	 * comando �nico, se adjuntan las opciones al JMenu y finalmente se ve si
	 * las opciones deben estar habilitadas o no.
	 */
	public void configurarComponentes()
	{
		// Crear las opciones del men�.
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
		
		// Adjuntar un comando �nico a cada item.
		itemNuevo.setMnemonic(KeyEvent.VK_N);
		itemAbrir.setMnemonic(KeyEvent.VK_A);
		itemCerrar.setMnemonic(KeyEvent.VK_C);
		itemGuardar.setMnemonic(KeyEvent.VK_G);
		itemGuardarComo.setMnemonic(KeyEvent.VK_U);
		itemSalir.setMnemonic(KeyEvent.VK_S);
		
		// Incorporar las opciones al men�.
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
	 * M�todo que adjunta los escuchadores eventos a los elementos del men�. En
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
	 * M�todo que configura las opciones del men�. Espec�ficamente se establece
	 * la propiedad de habilitaci�n de los items del men�, dependiendo de si se
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
	 * M�todo que obtiene el objeto itemNuevo.
	 *
	 * @return itemNuevo La opci�n del men� nuevo.
	 */
	public JMenuItem obtenerItemNuevo()
	{
		return itemNuevo;
	}
	
	/**
	 * M�todo que obtiene el objeto itemAbrir.
	 *
	 * @return itemAbrir La opci�n del men� abrir.
	 */
	public JMenuItem obtenerItemAbrir()
	{
		return itemAbrir;
	}
	
	/**
	 * M�todo que obtiene el objeto itemCerrar.
	 *
	 * @return itemCerrar La opci�n del men� cerrar.
	 */
	public JMenuItem obtenerItemCerrar()
	{
		return itemCerrar;
	}
	
	/**
	 * M�todo que obtiene el objeto itemGuardar.
	 *
	 * @return itemGuardar La opci�n del men� guardar.
	 */
	public JMenuItem obtenerItemGuardar()
	{
		return itemGuardar;
	}
	
	/**
	 * M�todo que obtiene el objeto itemGuardarComo.
	 *
	 * @return itemGuardarComo La opci�n del men� guardar como.
	 */
	public JMenuItem obtenerItemGuardarComo()
	{
		return itemGuardarComo;
	}
	
	/**
	 * M�todo que obtiene el objeto itemSalir.
	 *
	 * @return itemSalir La opci�n del men� salir.
	 */
	public JMenuItem obtenerItemSalir()
	{
		return itemSalir;
	}
}