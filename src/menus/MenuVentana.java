/**
 * @(#)MenuVentana.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;

/**
 * Clase que extiende de la clase JMenu. Contiene todas las opciones del men�
 * ventana de la aplicaci�n. Las opciones del men� ventana son las siguientes:
 * cascada, horizontal, vertical, minimizar todo y estilo.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see JMenu
 * @see JMenuItem
 * @see ImageIcon
 * @see JRadioButtonMenuItem
 * @see ButtonGroup
 * @see FrameCircanaPro
 * @see EventoMenuItem
 * @see EventoRadioButtonMenuItem
 * @see InterfaceMenu
 */
public class MenuVentana extends JMenu implements InterfaceMenu
{
	/** Puntero al frame que contiene a este men�. */
	public FrameCircanaPro frameCircanaPro;
	
	/** La opci�n del men� cascada. */
	private JMenuItem itemCascada;
	
	/** La opci�n del men� horizontal. */
	private JMenuItem itemHorizontal;
	
	/** La opci�n del men� vertical. */
	private JMenuItem itemVertical;
	
	/** La opci�n del men� minimizar todo. */
	private JMenuItem itemMinimizarTodo;
	
	/** La opci�n del men� estilo. */
	private JMenu itemEstilo;
	
	/** La opci�n del men� estilo moderno. */
	private JRadioButtonMenuItem itemModerno;
	
	/** La opci�n del men� estilo b�sico. */
	private JRadioButtonMenuItem itemBasico;
	
	/** La opci�n del men� estilo windows xp. */
	private JRadioButtonMenuItem itemWindowsXP;
	
	/** La opci�n del men� estilo windows cl�sico. */
	private JRadioButtonMenuItem itemWindowsClasico;
	
	/**
	 * M�todo constructor que invoca a los m�todos que configuran el men�, sus
	 * opciones y los eventos asociados.
	 *
	 * @param frameCircanaPro El objeto que hace referencia al frame que
	 *                        contiene a este men�.
	 */
	public MenuVentana(FrameCircanaPro frameCircanaPro)
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
		setText("Ventana");
		setMnemonic('V');
	}
	
	/**
	 * M�todo que configura las opciones del men� ventana. Primero se crean
	 * los objetos de la clase JMenuItem, luego se les adjunta a cada opci�n un
	 * comando �nico, se adjuntan las opciones al JMenu y finalmente se ve si
	 * las opciones deben estar habilitadas o no.
	 */
	public void configurarComponentes()
	{
		// Crear las opciones del men�.
		itemCascada = new JMenuItem("Cascada",
			new ImageIcon("../img/ventana_cascada.gif"));
		itemHorizontal = new JMenuItem("Mosaico Horizontal",
			new ImageIcon("../img/ventana_horizontal.gif"));
		itemVertical = new JMenuItem("Mosaico Vertical",
			new ImageIcon("../img/ventana_vertical.gif"));
		itemMinimizarTodo = new JMenuItem("Minimizar Todo",
			new ImageIcon("../img/ventana_minimizar_todo.gif"));
		itemEstilo = new JMenu("       Estilo");
		ButtonGroup grupoEstilo = new ButtonGroup();
		itemModerno = new JRadioButtonMenuItem("Moderno", true);
		itemBasico = new JRadioButtonMenuItem("B�sico");
		itemWindowsXP = new JRadioButtonMenuItem("Windows XP");
		itemWindowsClasico = new JRadioButtonMenuItem("Windows Cl�sico");
		
		// Adjuntar un comando �nico a cada item.
		itemCascada.setMnemonic(KeyEvent.VK_C);
		itemHorizontal.setMnemonic(KeyEvent.VK_H);
		itemVertical.setMnemonic(KeyEvent.VK_V);
		itemMinimizarTodo.setMnemonic(KeyEvent.VK_M);
		itemEstilo.setMnemonic(KeyEvent.VK_E);
		
		// Incorporar las opciones al men�.
		add(itemCascada);
		add(itemHorizontal);
		add(itemVertical);
		add(itemMinimizarTodo);
		addSeparator();
		add(itemEstilo);
		grupoEstilo.add(itemModerno);
		grupoEstilo.add(itemBasico);
		grupoEstilo.add(itemWindowsXP);
		grupoEstilo.add(itemWindowsClasico);
		itemEstilo.add(itemModerno);
		itemEstilo.add(itemBasico);
		itemEstilo.add(itemWindowsXP);
		itemEstilo.add(itemWindowsClasico);
		
		// Habilitar las opciones.
		habilitarOpciones();
	}
	
	/**
	 * M�todo que adjunta los escuchadores eventos a los elementos del men�. En
	 * particular se incorpora el escuchador de eventos de los tipos
	 * EventoMenuItem y EventoRadioButtonMenuItem a los JMenuItem y
	 * JRadioButtonMenuItem que tiene este JMenu.
	 */
	public void configurarEventos()
	{
		// Crear los escuchadores de eventos.
		EventoMenuItem eventoMenuItem = new EventoMenuItem(this);
		EventoRadioButtonMenuItem eventoRadioButtonItem =
			new EventoRadioButtonMenuItem(this);
		
		// Incorporar el escuchador de eventos a los elementos.
		itemCascada.addActionListener(eventoMenuItem);
		itemHorizontal.addActionListener(eventoMenuItem);
		itemVertical.addActionListener(eventoMenuItem);
		itemMinimizarTodo.addActionListener(eventoMenuItem);
		itemModerno.addActionListener(eventoRadioButtonItem);
		itemBasico.addActionListener(eventoRadioButtonItem);
		itemWindowsXP.addActionListener(eventoRadioButtonItem);
		itemWindowsClasico.addActionListener(eventoRadioButtonItem);
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
			itemCascada.setEnabled(false);
			itemHorizontal.setEnabled(false);
			itemVertical.setEnabled(false);
			itemMinimizarTodo.setEnabled(false);
			itemModerno.setEnabled(true);
			itemBasico.setEnabled(true);
			itemWindowsXP.setEnabled(true);
			itemWindowsClasico.setEnabled(true);
		}
		// Cuando hay un proyecto abierto.
		else
		{
			itemCascada.setEnabled(true);
			itemHorizontal.setEnabled(true);
			itemVertical.setEnabled(true);
			itemMinimizarTodo.setEnabled(true);
			itemModerno.setEnabled(true);
			itemBasico.setEnabled(true);
			itemWindowsXP.setEnabled(true);
			itemWindowsClasico.setEnabled(true);
		}
	}
	
	/**
	 * M�todo que obtiene el objeto itemCascada.
	 *
	 * @return itemCascada El item del men� cascada.
	 */
	public JMenuItem obtenerItemCascada()
	{
		return itemCascada;
	}
	
	/**
	 * M�todo que obtiene el objeto itemHorizontal.
	 *
	 * @return itemHorizontal El item del men� horizontal.
	 */
	public JMenuItem obtenerItemHorizontal()
	{
		return itemHorizontal;
	}
	
	/**
	 * M�todo que obtiene el objeto itemVertical.
	 *
	 * @return itemVertical La opci�n del men� vertical.
	 */
	public JMenuItem obtenerItemVertical()
	{
		return itemVertical;
	}
	
	/**
	 * M�todo que obtiene el objeto item MinimizarTodo
	 *
	 * @return itemMinimizarTodo La opci�n del men� minimizar todo.
	 */
	public JMenuItem obtenerItemMinimizarTodo()
	{
		return itemMinimizarTodo;
	}
	
	/**
	 * M�todo que obtiene el objeto itemModerno.
	 *
	 * @return itemModerno El item del men� moderno.
	 */
	public JRadioButtonMenuItem obtenerItemModerno()
	{
		return itemModerno;
	}
	
	/**
	 * M�todo que obtiene el objeto itemBasico.
	 *
	 * @return itemBasico El item del men� b�sico.
	 */
	public JRadioButtonMenuItem obtenerItemBasico()
	{
		return itemBasico;
	}
	
	/**
	 * M�todo que obtiene el objeto itemWindowsXP.
	 *
	 * @return itemWindowsXP La opci�n del men� windows xp.
	 */
	public JRadioButtonMenuItem obtenerItemWindowsXP()
	{
		return itemWindowsXP;
	}
	
	/**
	 * M�todo que obtiene el objeto itemWindowsClasico.
	 *
	 * @return itemWindowsClasico La opci�n del men� windows cl�sico.
	 */
	public JRadioButtonMenuItem obtenerItemWindowsClasico()
	{
		return itemWindowsClasico;
	}
}