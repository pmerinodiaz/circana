/**
 * @(#)MenuAdministracionDatos.java 2.0 01/03/05
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
 * administración de datos que tiene la aplicación. Las opciones de este menú
 * son las siguientes: evaluación de stock, caladero, mercado, medio de
 * transporte y punto de demanda.
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
public class MenuAdministracionDatos extends JMenu implements InterfaceMenu
{
	/** Puntero al frame que contiene a este menú. */
	public FrameCircanaPro frameCircanaPro;
	
	/** Opción del menú evaluación de stock. */
	private JMenuItem itemEvaluacionStock;
	
	/** Opción del menú caladero. */
	private JMenuItem itemCaladero;	
	
	/** Opción del menú mercado. */
	private JMenuItem itemMercado;
	
	/** Opción del menú medio de transporte. */
	private JMenuItem itemMedioTransporte;
	
	/** Opción del menú punto de demanda. */
	private JMenuItem itemPuntoDemanda;
	
	/**
	 * Método constructor que invoca a los métodos que configuran el menú, sus
	 * opciones y los eventos asociados.
	 *
	 * @param frameCircanaPro El objeto que hace referencia al frame que
	 *                        contiene a este menú.
	 */
	public MenuAdministracionDatos(FrameCircanaPro frameCircanaPro)
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
		setText("Administración de Datos");
		setMnemonic('A');
	}
	
	/**
	 * Método que configura las opciones del menú administración de datos.
	 * Primero se crean los objetos de la clase JMenuItem, luego se les adjunta
	 * a cada opción un comando único, se adjuntan las opciones al JMenu y
	 * finalmente se ve si las opciones deben estar habilitadas o no.
	 */
	public void configurarComponentes()
	{
		// Crear las opciones del menú.
		itemEvaluacionStock = new JMenuItem("Evaluación de Stock",
			new ImageIcon("../img/administracion_datos_evaluacion_stock.gif"));
		itemCaladero = new JMenuItem("Caladero",
			new ImageIcon("../img/administracion_datos_caladero.gif"));
		itemMercado = new JMenuItem("Mercado",
			new ImageIcon("../img/administracion_datos_mercado.gif"));
		itemMedioTransporte = new JMenuItem("Medio de Transporte",
			new ImageIcon("../img/administracion_datos_medio_transporte.gif"));
		itemPuntoDemanda = new JMenuItem("Punto de Demanda",
			new ImageIcon("../img/administracion_datos_punto_demanda.gif"));
		
		// Adjuntar un comando único a cada item.
		itemEvaluacionStock.setMnemonic(KeyEvent.VK_E);
		itemCaladero.setMnemonic(KeyEvent.VK_C);
		itemMercado.setMnemonic(KeyEvent.VK_M);
		itemMedioTransporte.setMnemonic(KeyEvent.VK_D);
		itemPuntoDemanda.setMnemonic(KeyEvent.VK_P);
		
		// Incorporar las opciones al menú.		
		add(itemEvaluacionStock);
		add(itemCaladero);
		addSeparator();
		add(itemMercado);
		addSeparator();
		add(itemMedioTransporte);
		add(itemPuntoDemanda);
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
		itemEvaluacionStock.addActionListener(evento);
		itemCaladero.addActionListener(evento);
		itemMercado.addActionListener(evento);
		itemMedioTransporte.addActionListener(evento);
		itemPuntoDemanda.addActionListener(evento);
	}
	
	/**
	 * Método que configura las opciones del menú. Específicamente se establece
	 * la propiedad de habilitación de los items del menú, dependiendo de si se
	 * encuentra abierto o no un proyecto. En particular, este menu no maneja
	 * la habilitación de opciones.
	 */
	public void habilitarOpciones()
	{
	}
	
	/**
	 * Método que obtiene el objeto itemEvaluacionStock.
	 *
	 * @return itemEvaluacionStock El item del menú evaluación de stock.
	 */
	public JMenuItem obtenerItemEvaluacionStock()
	{
		return itemEvaluacionStock;
	}
	
	/**
	 * Método que obtiene el objeto itemCaladero.
	 *
	 * @return itemCaladero El item del menú caladero.
	 */
	public JMenuItem obtenerItemCaladero()
	{
		return itemCaladero;
	}
	
	/**
	 * Método que obtiene el objeto itemMercado.
	 *
	 * @return itemMercado El item del menú mercado.
	 */
	public JMenuItem obtenerItemMercado()
	{
		return itemMercado;
	}
	
	/**
	 * Método que obtiene el objeto itemMedioTransporte.
	 *
	 * @return itemMedioTransporte El item del menú medio transporte.
	 */
	public JMenuItem obtenerItemMedioTransporte()
	{
		return itemMedioTransporte;
	}
	
	/**
	 * Método que obtiene el objeto itemPuntoDemanda.
	 *
	 * @return itemPuntoDemanda El item del menú punto demanda.
	 */
	public JMenuItem obtenerItemPuntoDemanda()
	{
		return itemPuntoDemanda;
	}
}