/**
 * @(#)Grado.java 2.0 01/01/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa un grados que se usa en un mapa geográfico. Esta
 * representación considera que un grado se compone de grados y minutos con los
 * cuales se representa una referencia a un grado. Esta clase implementa a la
 * interface Comparable con el motivo de proveer de un método que permita
 * comparar dos grados.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Comparable
 */
public class Grado implements Comparable
{
	/** El valor del grado. */
	protected int grado;
	
	/** El valor del minuto. */
	protected int minuto;
	
	/**
	 * Método constructor en donde se inicializan los valores del grado y
	 * minuto con los parámetros recibidos.
	 *
	 * @param grado El valor de los grados.
	 * @param minuto El valor de los minutos.
	 */
	public Grado(int grado, int minuto)
	{
		establecerGrado(grado);
		establecerMinuto(minuto);
	}
	
	/**
	 * Método constructor en donde se inicializan los valores del grado y
	 * minuto con el parámetro recibido como un valor expresado en puros grados.
	 *
	 * @param grados El valor del grado expresado en puros grados.
	 */
	public Grado(double grados)
	{
		this(Servicio.obtenerGrado(grados), Servicio.obtenerMinuto(grados));
	}
	
	/**
	 * Método constructor en donde se inicializan los valores del grado y
	 * minuto con parámetros recibidos como un objeto de la clase Grado.
	 *
	 * @param grado El valor del grado expresado como objetos de la clase Grado.
	 */
	public Grado(Grado grado)
	{
		this(grado.obtenerGrado(), grado.obtenerMinuto());
	}
	
	/**
	 * Método que establece un valor al atributo grado.
	 *
	 * @param grado El valor para el atributo grado.
	 */
	public void establecerGrado(int grado)
	{
		this.grado = grado;
	}
	
	/**
	 * Método que establece un valor al atributo minuto.
	 *
	 * @param minuto El valor para el atributo minuto.
	 */
	public void establecerMinuto(int minuto)
	{
		this.minuto = minuto;
	}
	
	/**
	 * Método que obtiene el valor del atributo grado.
	 *
	 * @return grado El valor del atributo grado.
	 */
	public int obtenerGrado()
	{
		return grado;
	}
	
	/**
	 * Método que obtiene el valor del atributo minuto.
	 *
	 * @return minuto El valor del atributo minuto.
	 */
	public int obtenerMinuto()
	{
		return minuto;
	}
	
	/**
	 * Método que entrega un string con el grado y minuto del grado.
	 *
	 * @return string El grado compuesto como un string.
	 */
	public String toString()
	{
		return ""+grado+"º "+minuto+"'";
	}
	
	/**
	 * Método que determina si dos grados son iguales o distintos. Cuando los
	 * grados son iguales se retorna el valor cero. Cuando este objeto es menor
	 * que el objeto especificado se retorna un valor negativo. Cuando este
	 * objeto es mayor que el objeto especificado se retorna un valor positivo.
	 *
	 * @param objeto El objeto con el cual se desea comparar.
	 * @return Es cero cuando los objetos son iguales y es uno cuando son
	 *         diferentes.
	 */
	public int compareTo(Object objeto)
	{
		// El valor retornado.
		int valor = 0;
		
		// Castear el objeto a un grado.
		Grado comparado = (Grado) objeto;
		
		// Cuando este objeto es igual que el objeto especificado.
		if (grado == comparado.obtenerGrado() &&
			minuto == comparado.obtenerMinuto())
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
}