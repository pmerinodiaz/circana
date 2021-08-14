/**
 * @(#)Resultado.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */
 
/**
 * Clase que define la estructura que tendrá un elemento correspondiente a un 
 * resultado obtenido por algún modelo en específico. Un resultado corresponde
 * a un valor que genera un modelo de un recurso y en un tiempo específico. Este
 * valor puede ser: oferta, demanda, precio o venta.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see Comparable
 */
public class Resultado implements Comparable
{
	/** Día del año del resultado. */
	private int dia;
	
	/** Año del resultado. */
	private int anio;
	
	/** Cantidad obtenida por el resultado. */
	private double cantidad;
	
	/**
	 * Método constructor en donde se inicializan los valores del día, año y
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
	 * Método que devuelve el día del año del resultado.
	 *
	 * @return dia El día del año del resultado.
	 */
	public int obtenerDia()
	{
		return dia;
	}
	
	/**
	 * Método que devuelve el año del resultado.
	 *
	 * @return anio El año del resultado.
	 */
	public int obtenerAnio()
	{
		return anio;
	}
	
	/**
	 * Método que devuelve la cantidad del resultado.
	 *
	 * @return cantidad La cantidad del resultado.
	 */
	public double obtenerCantidad()
	{
		return cantidad;
	}
	
	/**
	 * Método que entrega un string con el día, año y cantidad del resultado.
	 *
	 * @return string El resultado compuesto como un string.
	 */
	public String toString()
	{
		return dia+"/"+anio+": "+cantidad;
	}
	
	/**
	 * Método que determina si dos resultados son iguales o distintos. Cuando el
	 * día, año y cantidad son iguales, entonces se retorna el valor cero. En
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