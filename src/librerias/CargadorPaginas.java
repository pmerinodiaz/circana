/**
 * @(#)CargadorPaginas.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
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
 * Clase que permite cargar una p�gina Web en un JEditorPane. Esta clase
 * implementa a la interface Runnable, con el objetivo de utilizar el m�todo run
 * para poder cargar un p�gina Web como un archivo de texto.
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
	/** La direcci�n URL de la p�gina Web. */
	private URL url;
	
	/** El panel en donde se carga la p�gina Web. */
	private JEditorPane html;
	
	/** El cursor del panel en donde se carga la p�gina Web. */
	private Cursor cursor;
	
	/**
	 * M�todo constructor en donde se inicializan todos los atributos de esta
	 * clase con los valores recibidos por par�metros.
	 *
	 * @param url La direcci�n URL de la p�gina Web.
	 * @param html El panel en donde se carga la p�gina Web.
	 * @param cursor El cursor del panel en donde se carga la p�gina Web.
	 */
	public CargadorPaginas(URL url, JEditorPane html, Cursor cursor)
	{
		this.html = html;
		this.url = url;
		this.cursor = cursor;
	}
	
	/**
     * M�todo que finaliza de cargar una p�gina.
     */	
	private void finalizarCargarPagina()
	{
		url = null;
		SwingUtilities.invokeLater(this);
	}
	
	/**
	 * M�todo en donde se lee una direcci�n URL como si fuera un archivo de
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
			String tituloError = "No se pudo cargar la p�gina.";
			
			// Obtener el documento del panel.
			Document doc = html.getDocument();
			
			// Tratar de establecer la p�gina.
			try
			{
				html.setPage(url);
			}
			
			// Establecer el documento.
			catch (IOException ioe)
			{
				html.setDocument(doc);
			}
			
			// Si no hay memoria suficiente para mostrar la p�gina.
			catch (java.lang.OutOfMemoryError excepcion)
			{
				JOptionPane.showMessageDialog(new JFrame(),
				"No hay memoria suficiente para cargar la p�gina.",
				tituloError,JOptionPane.ERROR_MESSAGE);
				
				url = null;
				finalizarCargarPagina();
			}
			
			// Si no hay memoria suficiente para mostrar la p�gina.
			catch (java.lang.ArrayIndexOutOfBoundsException excepcion)
			{
				JOptionPane.showMessageDialog(new JFrame(),
				"No se puede visualizar esta p�gina porque tiene etiquetas " +
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