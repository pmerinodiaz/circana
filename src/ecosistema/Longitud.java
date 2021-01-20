/**
 * @(#)Longitud.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa un grado de longitud que se usa en un mapa geográfico.
 * Esta clase deriva de la clase grado y considera que una longitud se compone
 * de grados, minutos y tipo con los cuales se representa una referencia a una
 * longitud. Esta clase implementa a la interface Comparable con el motivo de
 * proveer de un método que permita comparar dos longitudes.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Comparable
 */
public class Longitud extends Grado implements Comparable
{
	/** El valor que indica que la longitud es este. */
	public static final int ESTE = 1;
	
	/** El valor que indica que la latitud es oeste. */
	public static final int OESTE = 2;
	
	/** El tipo de longitud. */
	private int tipo;
	
	/**
	 * Método constructor en donde se inicializan los valores del grado, minuto
	 * y tipo con los parámetros recibidos.
	 *
	 * @param grado El valor de los grados.
	 * @param minuto El valor de los minutos.
	 * @param tipo El tipo de latitud.
	 */
	public Longitud(int grado, int minuto, int tipo)
	{
		super(grado, minuto);
		establecerTipo(tipo);
	}
	
	/**
	 * Método constructor en donde se inicializan los valores del grado, minuto
	 * y tipo con el parámetro recibido como un valor expresado en puros grados.
	 *
	 * @param grados El valor del grado expresado en puros grados.
	 * @param tipo El tipo de latitud.
	 */
	public Longitud(double grados, int tipo)
	{
		super(grados);
		establecerTipo(tipo);
	}
	
	/**
	 * Método constructor en donde se inicializan los valores del grado, minuto
	 * y tipo con los parámetros recibidos como un objeto de la clase Grado.
	 *
	 * @param grado El valor del grado expresado como objetos de la clase Grado.
	 * @param tipo El tipo de latitud.
	 */
	public Longitud(Grado grado, int tipo)
	{
		super(grado);
		establecerTipo(tipo);
	}
	
	/**
	 * Método que establece un valor al atributo tipo.
	 *
	 * @param tipo El valor para el atributo tipo.
	 */
	public void establecerTipo(int tipo)
	{
		this.tipo = tipo;
	}
	
	/**
	 * Método que obtiene el valor del atributo tipo.
	 *
	 * @return tipo El valor del atributo tipo.
	 */
	public int obtenerTipo()
	{
		return tipo;
	}
	
	/**
	 * Método que entrega un string con el grado, minuto y tipo de la longitud.
	 *
	 * @return string El grado compuesto como un string.
	 */
	public String toString()
	{
		return super.toString()+" "+nombreTipo();
	}
	
	/**
	 * Método que determina si dos longitudes son iguales o distintos. Cuando
	 * las longitudes son iguales se retorna el valor cero. Cuando este objeto
	 * es menor que el objeto especificado se retorna un valor negativo. Cuando
	 * este objeto es mayor que el objeto especificado se retorna un valor
	 * positivo.
	 *
	 * @param objeto El objeto con el cual se desea comparar.
	 * @return Es cero cuando los objetos son iguales y es uno cuando son
	 *         diferentes.
	 */
	public int compareTo(Object objeto)
	{
		// El valor retornado.
		int valor = 0;
		
		// Castear el objeto a una latitud.
		Longitud comparado = (Longitud) objeto;
		
		// Cuando este objeto es igual que el objeto especificado.
		if (grado == comparado.obtenerGrado() &&
			minuto == comparado.obtenerMinuto() &&
			tipo == comparado.obtenerTipo())
			valor = 0;
		
		// Cuando este objeto es menor que el objeto especificado.
		else
		if (Servicio.obtenerGrados(this) < Servicio.obtenerGrados(comparado))
			valor = -1;
		
		// Cuando este objeto es mayor que el objeto especificado.
		else
		if (Servicio.obtenerGrados(this) > Servicio.obtenerGrados(comparado))
			valor = 1;
		
		// Devolver el valor.
		return valor;
	}
	
	/**
	 * Método que obtiene un string con el nombre del tipo de longitud.
	 *
	 * @return nombre El nombre del tipo de longitud.
	 */
	public String nombreTipo()
	{
		String nombre = "";
		
		switch (tipo)
		{
			case ESTE: nombre = "Este"; break;
			case OESTE: nombre = "Oeste"; break;
		}
		
		return nombre;
	}
}