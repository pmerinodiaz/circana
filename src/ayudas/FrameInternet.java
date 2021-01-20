/**
 * @(#)FrameInternet.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.awt.Container;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

/**
 * Clase que deriva de JDialog y que contiene el frame que se muestra al
 * usuario cuando se pulsa el bot�n "Cotenido" o "Ejemplos de Inteligencia
 * Artificial" o "CIRCANA Pro en Internet". Este dialog tiene por objetivo
 * mostrar una p�gina Web del software CIRCANA Pro.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see IOException
 * @see LinkedList
 * @see URL
 * @see MalformedURLException
 * @see Container
 * @see Color
 * @see BorderLayout
 * @see Cursor
 * @see KeyEvent
 * @see ImageIcon
 * @see JDialog
 * @see JOptionPane
 * @see JEditorPane
 * @see JScrollPane
 * @see KeyEvent
 * @see JScrollPane
 * @see SwingUtilities
 * @see JFrame
 * @see FrameCircanaPro
 * @see CargadorPaginas
 * @see EventoLink
 * @see InterfaceFrame
 */
class FrameInternet extends JDialog implements InterfaceFrame
{
	/** URL de la p�gina donde est� ubicado el contenido. */
	public static final String CONTENIDO =
		"http://www.circanapro.cl.tc/software/manual20";
	
	/** URL de la p�gina donde est�n ubicados los ejemplos de IA. */
	public static final String EJEMPLOS =
		"http://www.pmerinodiaz.cl.tc";
	
	/** URL de la p�gina donde est� ubicado el sitio CIRCANA Pro. */
	public static final String CIRCANA_PRO =
		"http://www.circanapro.cl.tc";
	
	/** Puntero al frame que contiene a este frame. */
	public FrameCircanaPro frameCircanaPro;
	
	/** La direcci�n URL donde se encuentra la p�gina Web. */
	private String textoURL;
	
	/** Panel en donde se muestra la p�gina Web. */
	private	JEditorPane	html;
	
	/**
	 * M�todo constructor del frame en donde se inicializan los atributos de la
	 * clase y se carga la p�gina Web del CIRCANA Pro.
	 *
	 * @param frameCircanaPro Un puntero al frame que contiene este frame.
	 * @param textoURL El texto de la URL que se desea visualizar.
	 * @param textoTitulo El texto de t�tulo que se desea visualizar.
	 */
	public FrameInternet(FrameCircanaPro frameCircanaPro, String textoURL,
						 String textoTitulo)
	{
		super(frameCircanaPro, textoTitulo, true);
		
		// Inicializar las variables.
		this.frameCircanaPro = frameCircanaPro;
		this.textoURL = textoURL;
		
		// Configurar.
		configurarElementosEspeciales();
		configurarFrame();
		configurarComponentes();
		configurarEventos();
	}
	
	/**
	 * M�todo en donde se inicializan los objetos que se usan en todo este
	 * frame. En particular este m�to no es implementado porque no contiene
	 * elementos especiales.
	 */
	public void configurarElementosEspeciales()
	{
	}
	
	/**
	 * M�todo en donde se configuran algunas propiedades de este frame. Las
	 * propiedades que se configuran corresponden a los atributos derivados
	 * desde la clase JFrame.
	 */
	public void configurarFrame()
	{
		setSize(900, 600);
		setLocation(70,50);
		setBackground(Color.gray);
		setResizable(false);
	}
	
	/**
	 * M�todo en donde se configuran los componentes GUI de este frame.
	 */
	public void configurarComponentes()
	{
		// Crear el panel editor.
		html = new JEditorPane();
		html.setEditable(false);
		
		// Capturar el container de este frame.
		Container contenedor = getContentPane();
				
		// Crear un panel.
		JPanel panelPagina = new JPanel();
		
		panelPagina.setLayout(new BorderLayout());
		contenedor.setLayout(null);
		
		// Crear un scroll para el panel.
		JScrollPane scrollPane = new JScrollPane();
		
		// Configurar el panel.
		scrollPane.getViewport().add(html, BorderLayout.CENTER);
		panelPagina.add(scrollPane, BorderLayout.CENTER);
		
		// Posicionando el panel.
		panelPagina.setBounds(0,0,getWidth(),getHeight());
		
		// Iniciar el panel de la p�gina.
		mostrarPanelSinConexion();				
		
		// Aderiendo al Jdialog.
		contenedor.add(panelPagina);
	}
	
	/**
	 * M�todo en donde se configuran los eventos de este frame. Este frame tiene
	 * evento asociado al link hacia la p�gina Web que se desea abrir. Este
	 * evento es manejado en la clase EventoLink.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoLink eventoLink = new EventoLink(this);
		
		// Incorporar el escuchador de eventos a los links.
		html.addHyperlinkListener(eventoLink);
	}
	
	/**
     * M�todo que carga una p�gina en el panel que muestra las p�ginas. Este
     * m�todo es llamado por los botones, links o al escribirse directamente en
     * el cuadro de texto de la direcci�n. Este m�todo cambia el cursor y se
     * invoca al m�todo que carga la p�gina Web desde Internet. 
     */
	public void cargarPagina()
	{
		try
		{
			// Crear la direcci�n URL.
			URL url = new URL(textoURL);
			
			// Cuando el browser puede visualizar el archivo.
			if (!esArchivoDescargar(url.getFile()))
			{
				// cambiando cursor
				Cursor cursor = html.getCursor();
				Cursor waitCursor = Cursor.getPredefinedCursor(
					Cursor.WAIT_CURSOR);
				html.setCursor(waitCursor);
				
				// Cambiar los links.
				SwingUtilities.invokeLater(
					new CargadorPaginas(url, html, cursor));
			}
			
			// Cuando el browser no puede visualizar el archivo.
			else
			{
				JOptionPane.showMessageDialog(this,
				"Este browser no permite descargar o visualizar algunos archivos",
				"Error de operaci�n de archivos",
				JOptionPane.ERROR_MESSAGE);
			}	
		}
		
		// Cuando la URL no es v�lida.
		catch (MalformedURLException excepcion)
		{
			JOptionPane.showMessageDialog(this,
			"La p�gina Web no fue encontrada.\n" +
			"Pruebe si la URL es v�lida.",
			"No se pudo cargar la p�gina", JOptionPane.ERROR_MESSAGE);
			mostrarPanelSinConexion();
		}
		
		// Cuando no puede conectarse a Internet.
		catch (IOException excepcion)
		{
			JOptionPane.showMessageDialog(this,
			"No se pudo conectarse a Internet.\n" +
			"Pruebe su conexi�n a Internet.",
			"No se pudo cargar la p�gina", JOptionPane.ERROR_MESSAGE);
			mostrarPanelSinConexion();
		}
	}
	
	/**
	 * M�todo en donde se carga la p�gina Web que tiene una direcci�n URL
	 * conocida. Esto lo logra a trav�s de un link de la p�gina anterior.
	 *
	 * @param url La p�gina que se obtuvo al ejecutar un v�nculo.
	 */
	public void irPaginaLink(URL url)
	{
		// Actualizar el texto de la URL.
		textoURL = url.toString();
		
		// Cargar la p�gina.
		cargarPagina();
	}
	
	/**
     * Metodo que valida si el archivo seleccionado es un archivo para tipo
     * de descaga y no HTML. 
     *
     * @param nombreArchivo Texto que contien el nombre del archivo. 
     *
     * @return esArchivo Indica si es archivo de descarga o no.
     */
	private boolean esArchivoDescargar(String nombreArchivo)
	{
		boolean esArchivo = false;
		String nombreExtension;
		String nombreExtensionNoPermitda;
		
		nombreExtensionNoPermitda = "zip " +
									"exe " +
									"rar " +
									"pdf " +
									"doc " +
									"ppt " +
									"xls " +
									"jpg " +
									"bmp " +
									"gif " +
									"jpg";
		
		if (nombreArchivo.length() > 4)
		{
			nombreExtension = nombreArchivo.substring(nombreArchivo.length() - 3,
													nombreArchivo.length());
			if (nombreExtensionNoPermitda.indexOf(nombreExtension) != -1)
				esArchivo = true;
			else
				esArchivo = false;
		}
		
		return esArchivo;	
	}
	
	/**
     * Metodo que indica en el panel cuando no hay p�gina cargada, ya sea por
     * error de URL, de conexi�n u otros.
     */
	private void mostrarPanelSinConexion()
	{
		html.setText("No hay p�gina cargada.");
		repaint();
	}
}