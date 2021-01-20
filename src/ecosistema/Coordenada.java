/**
 * @(#)Coordenada.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa una coordenada geogr�fica que tiene un mapa geogr�fico
 * en tres dimensiones (3D). Esta representaci�n considera que una coordenada es
 * representada por los grados de longitud, los grados de latitud y el nivel de
 * altitud del punto especificado.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Grado
 * @see Servicio
 * @see Comparable
 */
public class Coordenada implements Comparable
{
	/** Los grados en longitud. */
	private double longitud;
	
	/** Los grados en latitud. */
	private double latitud;
	
	/** El nivel de altitud. */
	private double altitud;
	
	/**
	 * M�todo constructor en donde se inicializan los grados de longitud, los
	 * grados de latitud y el nivel de altitud con los valores por utilizados
	 * por defecto. En este constructor los valores longitud y latitud son
	 * medidos en grados como valores num�ricos.
	 */
	public Coordenada()
	{
		establecerComponentes(0.0, 0.0, 0.0);
	}
	
	/**
	 * M�todo constructor en donde se inicializan los grados de longitud, los
	 * grados de latitud y el nivel de altitud con los par�metros recibidos. En
	 * este constructor los par�metros longitud y latitud son medidos en grados
	 * como valores num�ricos.
	 *
	 * @param longitud Los grados en longitud.
	 * @param latitud Los grados en latitud.
	 * @param altitud El nivel de altitud.
	 */
	public Coordenada(double longitud, double latitud, double altitud)
	{
		establecerComponentes(longitud, latitud, altitud);
	}
	
	/**
	 * M�todo constructor en donde se inicializan los grados de longitud, los
	 * grados de latitud y el nivel de altitud con los par�metros recibidos. En
	 * este constructor los par�metros longitud y latitud son objetos de la
	 * clase Grado.
	 *
	 * @param longitud Los grados en longitud.
	 * @param latitud Los grados en latitud.
	 * @param altitud El nivel de altitud.
	 */
	public Coordenada(Grado longitud, Grado latitud, double altitud)
	{
		establecerComponentes(longitud, latitud, altitud);
	}
	
	/**
	 * M�todo que establece un valor a los valores de longitud, latitud y
	 * altitud. En este m�todo los par�metros longitud y latitud son valores
	 * num�rico punto flotante.
	 *
	 * @param longitud El valor de los grados en longitud.
	 * @param latitud El valor de los grados en latitud.
	 * @param altitud El valor de los metros de altitud.
	 */
	public void establecerComponentes(double longitud,
									  double latitud,
									  double altitud)
	{
		establecerLongitud(longitud);
		establecerLatitud(latitud);
		establecerAltitud(altitud);
	}
	
	/**
	 * M�todo que establece un valor a los valores de longitud, latitud y
	 * altitud. En este m�todo los par�metros longitud y latitud son objetos de
	 * la clase grado.
	 *
	 * @param longitud Los grados en longitud.
	 * @param latitud Los grados en latitud.
	 * @param altitud El nivel de altitud.
	 */
	public void establecerComponentes(Grado longitud,
									  Grado latitud,
									  double altitud)
	{
		establecerLongitud(longitud);
		establecerLatitud(latitud);
		establecerAltitud(altitud);
	}
	
	/**
	 * M�todo que establece un valor a los grados en longitud. En este m�todo el
	 * par�metro longitud es un valor num�rico punto flotante.
	 *
	 * @param longitud El valor de los grados en longitud.
	 */
	public void establecerLongitud(double longitud)
	{
		this.longitud = longitud;
	}
	
	/**
	 * M�todo que establece un valor a los grados en longitud. En este m�todo el
	 * par�metro longitud es un objeto de la clase Grado.
	 *
	 * @param longitud El valor de los grados en longitud.
	 */
	public void establecerLongitud(Grado longitud)
	{
		this.longitud = Servicio.obtenerGrados(longitud);
	}
	
	/**
	 * M�todo que establece un valor a los grados en latitud. En este m�todo el
	 * par�metro latitud es medido en grados como valor num�rico punto flotante.
	 *
	 * @param latitud El valor de los grados en latitud.
	 */
	public void establecerLatitud(double latitud)
	{
		this.latitud = latitud;
	}
	
	/**
	 * M�todo que establece un valor a los grados en latitud. En este m�todo el
	 * par�metro latitud es un objeto de la clase Grado.
	 *
	 * @param latitud El valor de los grados en latitud.
	 */
	public void establecerLatitud(Grado latitud)
	{
		this.latitud = Servicio.obtenerGrados(latitud);
	}
	
	/**
	 * M�todo que establece un valor al nivel de altitud.
	 *
	 * @param altitud El valor del nivel de altitud.
	 */
	public void establecerAltitud(double altitud)
	{
		this.altitud = altitud;
	}
	
	/**
	 * M�todo que obtiene el valor de los grados en longitud.
	 *
	 * @return longitud El valor de los grados en longitud.
	 */
	public double obtenerLongitud()
	{
		return longitud;
	}
	
	/**
	 * M�todo que obtiene el valor de los grados en latitud.
	 *
	 * @return latitud El valor de los grados en latitud.
	 */
	public double obtenerLatitud()
	{
		return latitud;
	}
	
	/**
	 * M�todo que obtiene el valor del nivel de altitud.
	 *
	 * @return altitud El valor del nivel de altitud.
	 */
	public double obtenerAltitud()
	{
		return altitud;
	}
	
	/**
	 * Entrega un string con los grados de longitud, latitud y el nivel de
	 * altitud.
	 *
	 * @return string La coordenada compuesta como un string.
	 */
	public String toString()
	{
		return "("+longitud+", "+latitud+", "+altitud+")";
	}
	
	/**
	 * Entrega un string con los grados de longitud, latitud y el nivel de
	 * altitud.
	 *
	 * @return string La coordenada compuesta como un string.
	 */
	public String toStringGrado()
	{
		return "("+new Grado(longitud)+", "+new Grado(latitud)+", "+altitud+")";
	}
	
	/**
	 * M�todo que determina si dos coordenadas son iguales o distintas. Cuando
	 * las coordenadas son iguales se retorna el valor cero. En caso contrario,
	 * se retorna el valor uno.
	 *
	 * @param objeto El objeto con el cual se desea comparar.
	 * @return Es cero cuando los objetos son iguales y es uno cuando son
	 *         diferentes.
	 */
	public int compareTo(Object objeto)
	{
		// Castear el objeto a una coordenada.
		Coordenada coordenada = (Coordenada) objeto;
		
		// Cuando la longitud, latitud y altitud son iguales.
		if (longitud == coordenada.obtenerLongitud() &&
			latitud == coordenada.obtenerLatitud() &&
			altitud == coordenada.obtenerAltitud())
			return 0;
		
		// Cuando la longitud, latitud y altitud son distintos.
		return 1;
	}
}