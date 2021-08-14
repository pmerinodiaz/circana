/**
 * @(#)Resultado.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */
 
/**
 * Clase que define la estructura que tendr� un elemento correspondiente a un 
 * resultado obtenido por alg�n modelo en espec�fico. Un resultado corresponde
 * a un valor que genera un modelo de un recurso y en un tiempo espec�fico. Este
 * valor puede ser: oferta, demanda, precio o venta.
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
 * @see Comparable
 */
public class Resultado implements Comparable
{
	/** D�a del a�o del resultado. */
	private int dia;
	
	/** A�o del resultado. */
	private int anio;
	
	/** Cantidad obtenida por el resultado. */
	private double cantidad;
	
	/**
	 * M�todo constructor en donde se inicializan los valores del d�a, a�o y
	 * cantidad del resultado.
	 *
	 * @param dia El valor para el atributo dia.
	 * @param anio El valor para el atributo anio.
	 * @param cantidad El valor para el atributo cantidad.
	 */
	public Resultado(int dia, int anio, double cantidad)
	{
		this.dia = dia;
		this.anio = anio;
		this.cantidad = cantidad;
	}
	
	/**
	 * M�todo que devuelve el d�a del a�o del resultado.
	 *
	 * @return dia El d�a del a�o del resultado.
	 */
	public int obtenerDia()
	{
		return dia;
	}
	
	/**
	 * M�todo que devuelve el a�o del resultado.
	 *
	 * @return anio El a�o del resultado.
	 */
	public int obtenerAnio()
	{
		return anio;
	}
	
	/**
	 * M�todo que devuelve la cantidad del resultado.
	 *
	 * @return cantidad La cantidad del resultado.
	 */
	public double obtenerCantidad()
	{
		return cantidad;
	}
	
	/**
	 * M�todo que entrega un string con el d�a, a�o y cantidad del resultado.
	 *
	 * @return string El resultado compuesto como un string.
	 */
	public String toString()
	{
		return dia+"/"+anio+": "+cantidad;
	}
	
	/**
	 * M�todo que determina si dos resultados son iguales o distintos. Cuando el
	 * d�a, a�o y cantidad son iguales, entonces se retorna el valor cero. En
	 * caso contrario, se retorna el valor uno.
	 *
	 * @param objeto El objeto con el cual se desea comparar.
	 *
	 * @return cero Cuando los resultados son iguales.
	 * @return uno Cuando los resultados son diferentes.
	 */
	public int compareTo(Object objeto)
	{
		Resultado resultado = (Resultado) objeto;
		
		if ((dia == resultado.obtenerDia()) &&
			(anio == resultado.obtenerAnio()) &&
			(cantidad == resultado.obtenerCantidad()))
			return 0;
		
		return 1;
	}
}