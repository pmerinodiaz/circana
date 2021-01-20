/**
 * @(#)CargadorPaginas.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.io.IOException;
import java.net.URL;
import java.awt.Cursor;
import java.awt.Container;
import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

/**
 * Clase que permite cargar una página Web en un JEditorPane. Esta clase
 * implementa a la interface Runnable, con el objetivo de utilizar el método run
 * para poder cargar un página Web como un archivo de texto.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see IOException
 * @see URL
 * @see Cursor
 * @see Container
 * @see JEditorPane
 * @see SwingUtilities
 * @see Document
 * @see JOptionPane
 * @see JFrame
 */
class CargadorPaginas implements Runnable
{
	/** La dirección URL de la página Web. */
	private URL url;
	
	/** El panel en donde se carga la página Web. */
	private JEditorPane html;
	
	/** El cursor del panel en donde se carga la página Web. */
	private Cursor cursor;
	
	/**
	 * Método constructor en donde se inicializan todos los atributos de esta
	 * clase con los valores recibidos por parámetros.
	 *
	 * @param url La dirección URL de la página Web.
	 * @param html El panel en donde se carga la página Web.
	 * @param cursor El cursor del panel en donde se carga la página Web.
	 */
	public CargadorPaginas(URL url, JEditorPane html, Cursor cursor)
	{
		this.html = html;
		this.url = url;
		this.cursor = cursor;
	}
	
	/**
     * Método que finaliza de cargar una página.
     */	
	private void finalizarCargarPagina()
	{
		url = null;
		SwingUtilities.invokeLater(this);
	}
	
	/**
	 * Método en donde se lee una dirección URL como si fuera un archivo de
	 * texto plano. Luego se traspasa este archivo a formato HTML y se carga en
	 * el JEditorPane.
	 */
	public void run()
	{
		// Cuando la URL no existe.
		if (url == null)
		{
			// Restaurar el cursor original.
			html.setCursor(cursor);
			
			// Obtener el contenedor del panel y repintarlo.
			Container contenedor = html.getParent();
			contenedor.repaint();
		}
		
		// Cuando la URL existe.
		else
		{
			String tituloError = "No se pudo cargar la página.";
			
			// Obtener el documento del panel.
			Document doc = html.getDocument();
			
			// Tratar de establecer la página.
			try
			{
				html.setPage(url);
			}
			
			// Establecer el documento.
			catch (IOException ioe)
			{
				html.setDocument(doc);
			}
			
			// Si no hay memoria suficiente para mostrar la página.
			catch (java.lang.OutOfMemoryError excepcion)
			{
				JOptionPane.showMessageDialog(new JFrame(),
				"No hay memoria suficiente para cargar la página.",
				tituloError,JOptionPane.ERROR_MESSAGE);
				
				url = null;
				finalizarCargarPagina();
			}
			
			// Si no hay memoria suficiente para mostrar la página.
			catch (java.lang.ArrayIndexOutOfBoundsException excepcion)
			{
				JOptionPane.showMessageDialog(new JFrame(),
				"No se puede visualizar esta página porque tiene etiquetas " +
				"HTML especiales.",
				tituloError,JOptionPane.ERROR_MESSAGE);
				
				url = null;
				finalizarCargarPagina();
			}
			
			// Finalizar.
			finally
			{
				finalizarCargarPagina();
			}
		}
	}
}