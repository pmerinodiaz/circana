/**
 * @(#)MenuVentana.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;

/**
 * Clase que extiende de la clase JMenu. Contiene todas las opciones del menú
 * ventana de la aplicación. Las opciones del menú ventana son las siguientes:
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
	/** Puntero al frame que contiene a este menú. */
	public FrameCircanaPro frameCircanaPro;
	
	/** La opción del menú cascada. */
	private JMenuItem itemCascada;
	
	/** La opción del menú horizontal. */
	private JMenuItem itemHorizontal;
	
	/** La opción del menú vertical. */
	private JMenuItem itemVertical;
	
	/** La opción del menú minimizar todo. */
	private JMenuItem itemMinimizarTodo;
	
	/** La opción del menú estilo. */
	private JMenu itemEstilo;
	
	/** La opción del menú estilo moderno. */
	private JRadioButtonMenuItem itemModerno;
	
	/** La opción del menú estilo básico. */
	private JRadioButtonMenuItem itemBasico;
	
	/** La opción del menú estilo windows xp. */
	private JRadioButtonMenuItem itemWindowsXP;
	
	/** La opción del menú estilo windows clásico. */
	private JRadioButtonMenuItem itemWindowsClasico;
	
	/**
	 * Método constructor que invoca a los métodos que configuran el menú, sus
	 * opciones y los eventos asociados.
	 *
	 * @param frameCircanaPro El objeto que hace referencia al frame que
	 *                        contiene a este menú.
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
	 * Método en donde se configurar diversas propiedades que tiene este menú.
	 * Las opciones que se cambian son los atributos que se derivan de la clase
	 * JMenu.
	 */
	public void configurarMenu()
	{
		setText("Ventana");
		setMnemonic('V');
	}
	
	/**
	 * Método que configura las opciones del menú ventana. Primero se crean
	 * los objetos de la clase JMenuItem, luego se les adjunta a cada opción un
	 * comando único, se adjuntan las opciones al JMenu y finalmente se ve si
	 * las opciones deben estar habilitadas o no.
	 */
	public void configurarComponentes()
	{
		// Crear las opciones del menú.
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
		itemBasico = new JRadioButtonMenuItem("Básico");
		itemWindowsXP = new JRadioButtonMenuItem("Windows XP");
		itemWindowsClasico = new JRadioButtonMenuItem("Windows Clásico");
		
		// Adjuntar un comando único a cada item.
		itemCascada.setMnemonic(KeyEvent.VK_C);
		itemHorizontal.setMnemonic(KeyEvent.VK_H);
		itemVertical.setMnemonic(KeyEvent.VK_V);
		itemMinimizarTodo.setMnemonic(KeyEvent.VK_M);
		itemEstilo.setMnemonic(KeyEvent.VK_E);
		
		// Incorporar las opciones al menú.
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
	 * Método que adjunta los escuchadores eventos a los elementos del menú. En
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
	 * Método que configura las opciones del menú. Específicamente se establece
	 * la propiedad de habilitación de los items del menú, dependiendo de si se
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
	 * Método que obtiene el objeto itemCascada.
	 *
	 * @return itemCascada El item del menú cascada.
	 */
	public JMenuItem obtenerItemCascada()
	{
		return itemCascada;
	}
	
	/**
	 * Método que obtiene el objeto itemHorizontal.
	 *
	 * @return itemHorizontal El item del menú horizontal.
	 */
	public JMenuItem obtenerItemHorizontal()
	{
		return itemHorizontal;
	}
	
	/**
	 * Método que obtiene el objeto itemVertical.
	 *
	 * @return itemVertical La opción del menú vertical.
	 */
	public JMenuItem obtenerItemVertical()
	{
		return itemVertical;
	}
	
	/**
	 * Método que obtiene el objeto item MinimizarTodo
	 *
	 * @return itemMinimizarTodo La opción del menú minimizar todo.
	 */
	public JMenuItem obtenerItemMinimizarTodo()
	{
		return itemMinimizarTodo;
	}
	
	/**
	 * Método que obtiene el objeto itemModerno.
	 *
	 * @return itemModerno El item del menú moderno.
	 */
	public JRadioButtonMenuItem obtenerItemModerno()
	{
		return itemModerno;
	}
	
	/**
	 * Método que obtiene el objeto itemBasico.
	 *
	 * @return itemBasico El item del menú básico.
	 */
	public JRadioButtonMenuItem obtenerItemBasico()
	{
		return itemBasico;
	}
	
	/**
	 * Método que obtiene el objeto itemWindowsXP.
	 *
	 * @return itemWindowsXP La opción del menú windows xp.
	 */
	public JRadioButtonMenuItem obtenerItemWindowsXP()
	{
		return itemWindowsXP;
	}
	
	/**
	 * Método que obtiene el objeto itemWindowsClasico.
	 *
	 * @return itemWindowsClasico La opción del menú windows clásico.
	 */
	public JRadioButtonMenuItem obtenerItemWindowsClasico()
	{
		return itemWindowsClasico;
	}
}