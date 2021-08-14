/**
 * @(#)ArchivoTextoPlano.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;

/**
 * Clase n�cleo para administrar cualquier archivo de texto plano. Permite leer
 * y guardar archivos de texto plano. En la especificaci�n de rutas se trabaja
 * solamente con rutas relativas. Adem�s permite administrar los archivos de
 * tipo configuraci�n de la aplicaci�n, es decir permite, leer una variable o
 * cambiar una variable. El formato de las variables son
 * "nombre_variable = valor_variable\n".
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see File
 * @see BufferedReader
 * @see BufferedWriter
 * @see FileWriter
 * @see FileReader
 */
public class ArchivoTextoPlano
{
	/** Constante que significa enter. */
	private final char ENTER = '\n';
	
	/** Administra el archivo. */
	private File archivo;
	
	/** Contiene el texto que participa con el archivo. */
	private String texto;
	
	/** Contiene el nombre del archivo. */
	private String nombreArchivo;
	
	/**
     * Constructor de la clase. Aqu� se inicializan los elementos ocupados por
     * la clase.
     */
	public ArchivoTextoPlano()
	{
		texto = "";
	}
	
	/**
     * Constructor de la clase. Aqu� se inicializa los elementos ocupados por la
     * clase.
     *
     * @param nombreArchivo String que contiene el nombre del archivo usado.
     */
	public ArchivoTextoPlano(String nombreArchivo)
	{
		this.nombreArchivo = nombreArchivo;
		texto = "";
	}
	
	/**
     * Carga desde un archivo de texto de plano la informaci�n que contiene en
     * una variable de tipo string.
     * 
     * @return texto Informaci�n almacenada en el archivo de texto.
     */
	public String leer()
	{
		String textoTemporal = "";
		texto = "";
		
		// Crear un archivo.
		archivo = new File(nombreArchivo);
		
		try
		{
			// Abrir una entrada de archivo.
			BufferedReader entrada = new BufferedReader(new FileReader(archivo));
			
			// Ciclo para leer el contenido del archivo.
			while ((textoTemporal = entrada.readLine()) != null)
			{
				texto += textoTemporal + ENTER;
			}
			
			// Cerrar la entrada.
			entrada.close();
		}
		catch (java.io.FileNotFoundException snsex)
		{
			// Capturar la excepci�n y mostrarla.
			System.out.println("No encontro el archivo " +
							   nombreArchivo + ".");
		}
		catch (java.io.IOException ioex)
		{
			// Capturar la excepci�n y mostrarla.
			System.out.println("No se pudo leer el archivo " +
							   nombreArchivo + ".");
		}
		
		return texto;	
	}
	
	/**
     * M�todo que guarda en un archivo de texto plano (si no existe el archivo
     * lo crea) la informaci�n enviada en el argumento del m�todo.
     * 
     * @param texto Informaci�n que se quiere guardar en el archivo de texto.
     */
	public void guardar(String texto)
	{
		this.texto = texto;
		
		try
	    {
	    	// Abrir una salida de archivo.
	    	BufferedWriter salida = new BufferedWriter(
                                new FileWriter(nombreArchivo));
		   	
		   	// Ciclo para guardar el contenido.
		   	for (int i = 0; i < texto.length(); i++)
		   	{
		   	   	if (texto.charAt(i) != ENTER)
		   	   	   	salida.write(texto.charAt(i));
		   	   	else
		   	   	   	salida.newLine();
		   	}
		   	
		   	// Cerrar la salida.
		   	salida.close();
		}
		catch(java.io.FileNotFoundException snsex)
		{
			// Capturar la excepci�n y mostrarla.
			System.out.println("No se encontro el archivo " +
							   nombreArchivo + ".");
		}
		catch(java.io.IOException ioex)
		{
			System.out.println("No se pudo leer el archivo " +
							   nombreArchivo + ".");
		}
	}
	
	/**
     * M�todo que agrega al final del archivo texto plano (si no existe el
     * archivo lo crea) la informaci�n enviada en el argumento del m�todo.
     * 
     * @param texto Informaci�n que se quiere agregar en el archivo de texto.
     */
	public void agregar(String texto)
	{
		this.texto = leer() + ENTER + texto;
		
		guardar(this.texto);
	}
	
	/**
     * M�todo que busca el valor de una variable que se encuentra en un archivo
     * de texto plano. No hay que olvidar que la variable debe estar escrita en
     * el formato indicado en la definici�n de la clase.
     * 
     * @param nombreVariable Nombre de la variable que se quiere buscar.
     *
     * @return valorVariable Valor de la variable que se buscaba.
     */
	public String buscarValorVariable(String nombreVariable)
	{
		int posicionInicial;
		int posicionFinal;
		String valorVariable = "";
		String texto = leer();
		
		if (texto.length() > 0)
		{
			nombreVariable += " = ";
			
			posicionInicial = texto.indexOf(nombreVariable);
			
			posicionInicial += nombreVariable.length();
			
			posicionFinal = texto.indexOf(ENTER,posicionInicial);
			
			valorVariable = texto.substring(posicionInicial,posicionFinal);
		}
		
		return valorVariable;
	}
	
	/**
     * M�todo que cambia el valor de una variable que se encuentra en un
     * archivo de texto plano. No hay que olvidar que la variable debe estar
     * escrita en el formato indicado en la definici�n de la clase.
     * 
     * @param nombreVariable Nombre de la variable que se quiere buscar.
     * @param valorVariable Valor de la variable que se quiere cambiar.
     */
	public void cambiarValorVariable(String nombreVariable, String valorVariable)
	{
		int posicionInicial;
		int posicionFinal;
		String texto = leer();
		String textoInicial;
		String textoFinal;
		
		if (texto.length() > 0)
		{
			nombreVariable += " = ";
			
			posicionInicial = texto.indexOf(nombreVariable);
			
			posicionInicial += nombreVariable.length();
			
			posicionFinal = texto.indexOf(ENTER,posicionInicial);
			
			textoInicial =  texto.substring(0,posicionInicial);
			textoFinal =  texto.substring(posicionFinal, texto.length());
			
			texto = textoInicial + valorVariable +  textoFinal;
			
			guardar(texto);
		}
	}
	
	/**
     * M�todo que establece el nombre del archivo que se va a ocupar.
     * 
     * @param nombreArchivo Nombre del archivo.
     */
	public void establecerNombreArchivo(String nombreArchivo)
	{
		this.nombreArchivo = nombreArchivo;
	}
	
	/**
     * M�todo que devuelve el nombre del archivo que se est� ocupando.
     * 
     * @return nombreArchivo Nombre del archivo.
     */
	public String obtenerNombreArchivo()
	{
		return nombreArchivo;
	}
	
	/**
     * M�todo sobrecargado del clase Object que devuelve la informaci�n sobre el
     * objeto instaciado de esta clase.
     *
     * @return informacion Informaci�n del objeto instanciado de esta clase.
     */
	public String toString()
	{
		return "Nombre del Archivo = " + nombreArchivo + "\n";
	}
}