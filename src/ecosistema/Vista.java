/**
 * @(#)Vista.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa la vista del mapa geogr�fico de estudio. La vista
 * consiste en la elecci�n simult�nea de dos ejes del sistema mundo real. Esta
 * clase maneja solo atributos con valores est�ticos.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public class Vista
{
	/**
	 * El valor que indica que la vista del mapa corresponde a los ejes
	 * longitud-latitud.
	 */
	public static final int LONGITUD_LATITUD = 0;
	
	/**
	 * El valor que indica que la vista del mapa corresponde a los ejes
	 * longitud-altitud.
	 */
	public static final int LONGITUD_ALTITUD = 1;
	
	/**
	 * El valor que indica que la vista del mapa corresponde a los ejes
	 * latitud-altitud.
	 */
	public static final int LATITUD_ALTITUD = 2;
}