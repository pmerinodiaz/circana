/**
 * @(#)MenuAdministracionDatos.java 2.0 01/03/05
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
 * administraci�n de datos que tiene la aplicaci�n. Las opciones de este men�
 * son las siguientes: evaluaci�n de stock, caladero, mercado, medio de
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
	/** Puntero al frame que contiene a este men�. */
	public FrameCircanaPro frameCircanaPro;
	
	/** Opci�n del men� evaluaci�n de stock. */
	private JMenuItem itemEvaluacionStock;
	
	/** Opci�n del men� caladero. */
	private JMenuItem itemCaladero;	
	
	/** Opci�n del men� mercado. */
	private JMenuItem itemMercado;
	
	/** Opci�n del men� medio de transporte. */
	private JMenuItem itemMedioTransporte;
	
	/** Opci�n del men� punto de demanda. */
	private JMenuItem itemPuntoDemanda;
	
	/**
	 * M�todo constructor que invoca a los m�todos que configuran el men�, sus
	 * opciones y los eventos asociados.
	 *
	 * @param frameCircanaPro El objeto que hace referencia al frame que
	 *                        contiene a este men�.
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
	 * M�todo en donde se configurar diversas propiedades que tiene este men�.
	 * Las opciones que se cambian son los atributos que se derivan de la clase
	 * JMenu.
	 */
	public void configurarMenu()
	{
		setText("Administraci�n de Datos");
		setMnemonic('A');
	}
	
	/**
	 * M�todo que configura las opciones del men� administraci�n de datos.
	 * Primero se crean los objetos de la clase JMenuItem, luego se les adjunta
	 * a cada opci�n un comando �nico, se adjuntan las opciones al JMenu y
	 * finalmente se ve si las opciones deben estar habilitadas o no.
	 */
	public void configurarComponentes()
	{
		// Crear las opciones del men�.
		itemEvaluacionStock = new JMenuItem("Evaluaci�n de Stock",
			new ImageIcon("../img/administracion_datos_evaluacion_stock.gif"));
		itemCaladero = new JMenuItem("Caladero",
			new ImageIcon("../img/administracion_datos_caladero.gif"));
		itemMercado = new JMenuItem("Mercado",
			new ImageIcon("../img/administracion_datos_mercado.gif"));
		itemMedioTransporte = new JMenuItem("Medio de Transporte",
			new ImageIcon("../img/administracion_datos_medio_transporte.gif"));
		itemPuntoDemanda = new JMenuItem("Punto de Demanda",
			new ImageIcon("../img/administracion_datos_punto_demanda.gif"));
		
		// Adjuntar un comando �nico a cada item.
		itemEvaluacionStock.setMnemonic(KeyEvent.VK_E);
		itemCaladero.setMnemonic(KeyEvent.VK_C);
		itemMercado.setMnemonic(KeyEvent.VK_M);
		itemMedioTransporte.setMnemonic(KeyEvent.VK_D);
		itemPuntoDemanda.setMnemonic(KeyEvent.VK_P);
		
		// Incorporar las opciones al men�.		
		add(itemEvaluacionStock);
		add(itemCaladero);
		addSeparator();
		add(itemMercado);
		addSeparator();
		add(itemMedioTransporte);
		add(itemPuntoDemanda);
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
		itemEvaluacionStock.addActionListener(evento);
		itemCaladero.addActionListener(evento);
		itemMercado.addActionListener(evento);
		itemMedioTransporte.addActionListener(evento);
		itemPuntoDemanda.addActionListener(evento);
	}
	
	/**
	 * M�todo que configura las opciones del men�. Espec�ficamente se establece
	 * la propiedad de habilitaci�n de los items del men�, dependiendo de si se
	 * encuentra abierto o no un proyecto. En particular, este menu no maneja
	 * la habilitaci�n de opciones.
	 */
	public void habilitarOpciones()
	{
	}
	
	/**
	 * M�todo que obtiene el objeto itemEvaluacionStock.
	 *
	 * @return itemEvaluacionStock El item del men� evaluaci�n de stock.
	 */
	public JMenuItem obtenerItemEvaluacionStock()
	{
		return itemEvaluacionStock;
	}
	
	/**
	 * M�todo que obtiene el objeto itemCaladero.
	 *
	 * @return itemCaladero El item del men� caladero.
	 */
	public JMenuItem obtenerItemCaladero()
	{
		return itemCaladero;
	}
	
	/**
	 * M�todo que obtiene el objeto itemMercado.
	 *
	 * @return itemMercado El item del men� mercado.
	 */
	public JMenuItem obtenerItemMercado()
	{
		return itemMercado;
	}
	
	/**
	 * M�todo que obtiene el objeto itemMedioTransporte.
	 *
	 * @return itemMedioTransporte El item del men� medio transporte.
	 */
	public JMenuItem obtenerItemMedioTransporte()
	{
		return itemMedioTransporte;
	}
	
	/**
	 * M�todo que obtiene el objeto itemPuntoDemanda.
	 *
	 * @return itemPuntoDemanda El item del men� punto demanda.
	 */
	public JMenuItem obtenerItemPuntoDemanda()
	{
		return itemPuntoDemanda;
	}
}