/**
 * @(#)Impresora.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Frame;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.awt.print.PrinterException;
import java.awt.print.PageFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * Clase que imprime los reportes generados en toda la aplicación. La clase
 * implementa a la interface Printable para lograr su objetivo de impresión.
 * Imprime los reportes generados por cada modelo de la aplicación. Otorga
 * diferentes tipos de impresión, como para componentes JComponent o texto
 * normal.
 * 
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see Graphics
 * @see Graphics2D
 * @see Color
 * @see Frame
 * @see Printable
 * @see PrinterJob
 * @see PrinterException
 * @see PageFormat
 * @see JOptionPane
 * @see JTextArea
 */
/*
 * OBSERVACION: En texto normal no acepta codigos ASCII especiales, exceptuando
 * el caracter '\n' y '\t'. El tab no es muy bien representado.
 */
public class Impresora implements Printable
{
	/** Nombre del documento de impresión. */
	private static final String NOMBRE_IMPRESION = "CIRCANA Pro";
	
	/** Maneja la impresión de los documentos. */
	private PrinterJob trabajo;
	
	/** Contiene el texto del reporte que desea imprimir. */
	private JTextArea reporte;
	
	/** Contiene el texto del reporte que desea imprimir. */
	private String texto;
	
	/** Contiene la imagen que se imprime. */
	private Graphics2D imagenImpresa;
	
	/** Posicion X donde empieza el texto de la página. */
	private int margenX;
	
	/** Posicion Y donde empieza el texto de la página. */
	private int margenY;
	
	/** Cantidad de lineas por páginas. */
	private int lineaPagina; 
	
	/** Escala por linea de texto. */
	private int escalaLinea;
	
	/** Define el tipo de impresión (0: JTextArea , 1: Texto Normal *). */
	private int tipoImpresion;
	
	/**
     * Constructor  de la clase Impresora. Aquí se inicializan los elementos
     * principales para menajar las impresiones de la aplicación.
     */
	public Impresora()
	{
		trabajo = PrinterJob.getPrinterJob();
		trabajo.setPrintable(this);
		trabajo.setJobName(NOMBRE_IMPRESION);
		configurarFormatoTexto(30,50,45,15);
		establecerTipoImpresion(0);
	}
	
	/**
     * Método que modifica un texto para que acepte caracteres espaciales. Estos
     * caracteres especiales deben ser modificados para ser aceptables por la
     * clase de impresión.
     * 
     * @param textoTemporal Texto que se quiere modificar sus caracteres
     *						especiales.
     *
     * @return textoTemporal Texto modificado.
     */
	private String modificarTextoTemporal(String textoTemporal)
	{
		String tab = "         ";
		
		textoTemporal = textoTemporal.replaceAll("\t",tab);
		
		return textoTemporal;
	}
	
	/**
     * Método que imprime un texto normal. Este método es llamado por el método
     * "print" entregado por la interface Printable. Sus parámetros son los
     * mismos que fueron entregados al método de la interface.
     * 
     * @param g Gráfico en cual se imprime el reporte.
     * @param pageFormat El formato de la página ha imprimir.
     * @param pageIndex Página que se encuentra imprimiendo en el momento.
     * 
     * @return resultado Indica si finalizó de imprimir el trabajo.
     */
	private int imprimirTextoNormal(Graphics g, PageFormat pageFormat,
									int pageIndex)
	{
		int resultadoImpresora;
		int i;
		int linea;
		int indice;
		int indiceTemporal;
		int numeroPagina;
		int numeroLinea;
		int tamanoReporte;
		double anchoPagina;
		double altoPagina;
		String textoTemporal;
		
		imagenImpresa = (Graphics2D) g;
		linea = margenY * escalaLinea;
		indice = 0;
		numeroLinea = Servicio.contarCaracter(texto,'\n');
		numeroPagina = 1 + numeroLinea / lineaPagina;
		
		anchoPagina = pageFormat.getImageableWidth();
		altoPagina = pageFormat.getImageableHeight();
		tamanoReporte =	texto.length();
		imagenImpresa.translate(0f,-pageIndex * lineaPagina * escalaLinea);
		imagenImpresa.drawString("Página: " + (pageIndex + 1),50,50);
		
		do
		{
			indiceTemporal = texto.indexOf('\n',indice);
			
			if (indiceTemporal == -1)
				indiceTemporal = tamanoReporte;
			
			textoTemporal = texto.substring(indice,indiceTemporal);
			textoTemporal = modificarTextoTemporal(textoTemporal);
			imagenImpresa.drawString(textoTemporal, margenX,
									 linea * escalaLinea);
			
			indice = indiceTemporal + 1;
			linea++;
		}
		while (tamanoReporte >= indice);
		
		if (pageIndex >= numeroPagina)
			resultadoImpresora = Printable.NO_SUCH_PAGE;
		else
			resultadoImpresora = Printable.PAGE_EXISTS;
		
		return resultadoImpresora;
	}
	
	/**
     * Método que imprime el componente swing JTextArea. Este método es llamado
     * por el método "print" entregado por la interface Printable. Sus
     * parámetros son los mismos que fueron entregados al metodo de la
     * interface.
     *
     * @param g Gráfico en cual se imprime el reporte.
     * @param pageFormat El formato de la página ha imprimir.
     * @param pageIndex Página que se encuentra imprimiendo en el momento.
     * 
     * @return resultado Indica si finalizó de imprimir el trabajo.
     */
	private int imprimirTextArea(Graphics g, PageFormat pageFormat,
								 int pageIndex)
	{
		int resultadoImpresora;
		int factorLinea;
		int numeroPagina;
		int numeroLinea;
		
		imagenImpresa = (Graphics2D) g;
		numeroLinea = Servicio.contarCaracter(reporte.getText(),'\n');
		numeroPagina = 1 + numeroLinea / lineaPagina;
		int anchoPagina =(int) pageFormat.getImageableWidth();
		int altoPagina = (int) pageFormat.getImageableHeight();
		
		imagenImpresa.translate(0, pageFormat.getImageableY());
		imagenImpresa.translate(0f + margenX,
								-pageIndex * lineaPagina * escalaLinea);
		imagenImpresa.translate(pageFormat.getImageableX(),
								pageFormat.getImageableY());
		
		reporte.paintAll(imagenImpresa);
		
		if (pageIndex >= numeroPagina)
			resultadoImpresora = Printable.NO_SUCH_PAGE;
		else
			resultadoImpresora = Printable.PAGE_EXISTS;
		
		return resultadoImpresora;
	}
	
	/**
     * Método que imprime un reporte generado por un modelo. Lo imprime con las
     * mismas caracteristicas entragas el componente, es decir, se imprime de la
     * misma manera que se muestra en la pantalla.
     * 
     * @param reporte Componente que contiene el reporte que se quiere imprimir.
     */
	public void imprimirReporte(JTextArea reporte)
	{
		this.reporte = reporte;
		trabajo = PrinterJob.getPrinterJob();
		trabajo.setPrintable(this);
		trabajo.setJobName(NOMBRE_IMPRESION);
		trabajo.printDialog();
		establecerTipoImpresion(0);
		
        try
        {
        	trabajo.print();
        }
        catch (Exception PrintException)
       	{
       		JOptionPane.showMessageDialog(new Frame(),
       			"No se pudo usar la impresora",
       			"Error al imprimir el reporte",
       			JOptionPane.ERROR_MESSAGE);
        }
	}
	
	/**
     * Método que imprime un texto con formato string. El texto debe acepta
     * códigos espaciales como '\n' y '\t' (no representado muy bien estos).
     * Los imprime en la mitad de la página.
     *
     * @param texto String que contiene el reporte que se quiere imprimir
     */
	public void imprimirTexto(String texto)
	{
		this.texto = texto;
		trabajo = PrinterJob.getPrinterJob();
		trabajo.setPrintable(this);
		trabajo.setJobName(NOMBRE_IMPRESION);
		trabajo.printDialog();
		establecerTipoImpresion(1);
		
        try
        { 
        	trabajo.print();
        }
        catch (Exception PrintException)
       	{
       		JOptionPane.showMessageDialog(new Frame(),
       			"No se pudo usar la impresora",
       			"Error al imprimir el reporte",
       			JOptionPane.ERROR_MESSAGE);
        }
	}
	
	/**
     * Método entregado por la interface Printable. Este método es el que
     * permite usar la impresora. Todo documento debe pasar obligatoriamente por
     * este método.
     * 
     * @param g Gráfico en cual se imprime el reporte.
     * @param pageFormat El formato de la página ha imprimir.
     * @param pageIndex Página que se encuentra imprimiendo en el momento.
     * 
     * @return resultado Indica si finalizó de imprimir el trabajo.
     */
	public int print(Graphics g, PageFormat pageFormat, int pageIndex)
		throws PrinterException
	{
		int resultadoImpresion;
		
		switch (tipoImpresion)
		{
	    	case 0:
	    		resultadoImpresion = imprimirTextArea(g,pageFormat,pageIndex);
	    		break;
	    	
	    	case 1:
	    		resultadoImpresion = imprimirTextoNormal(g,pageFormat,pageIndex);
	    		break;
	    	
	    	default:
	    		resultadoImpresion = Printable.NO_SUCH_PAGE;
	    }
	    
	    return resultadoImpresion;
	}
	
	/**
     * Configura el formato del texto que se imprime en la página.
     *
     * @param margenX Comienzo de la posición X para imprimir.
     * @param margenY Comienzo de la posición Y para imprimir.
     * @param lineaPagina Número de lineas (de texto) que habra por páginas.
     * @param escalaLinea Indice cuantos espacios se debe saltar para imprimir.
     *		              la otra linea.
     */
	private void configurarFormatoTexto(int margenX, int margenY,
									   int lineaPagina, int escalaLinea)
	{
		if (margenX > 0)
			this.margenX = margenX;
		
		if (margenY > 0)
			this.margenY = margenY;
		
		if (lineaPagina	> 0)
			this.lineaPagina = lineaPagina;
		
		if (escalaLinea > 0)
			this.escalaLinea = escalaLinea;
	}
	
	/**
     * Método que establece el tipo de impresión. Puede ser para texto normal
     * o para un componente JTextArea (0:JTextArea, 1: Texto Normal).
     *
     * @param tipoImpresion Indica que tipo de impresión que se quiere.
     */
	private void establecerTipoImpresion(int tipoImpresion)
	{
		this.tipoImpresion = tipoImpresion;
	}
}