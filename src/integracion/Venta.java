/**
 * @(#)Venta.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */
 
/**
 * Clase que define la estructura que tendrá un elemento correspondiente a un
 * resultado de la planificación de ventas de la integración. Una venta
 * corresponde a un valor que genera el modelo de integración sobre un recurso,
 * en un tiempo específico. Este valor incluye: oferta, demanda, precio y venta.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Comparable
 */
public class Venta implements Comparable
{
	/** Día del año de la venta. */
	private int dia;
	
	/** Año de la venta. */
	private int anio;
	
	/** Cantidad ofertada de la venta. */
	private double oferta;
	
	/** Cantidad demandada de la venta. */
	private double demanda;
	
	/** El precio de la venta. */
	private double precio;
	
	/** Cantidad de la venta. */
	private double venta;
	
	/**
	 * Método constructor en donde se inicializan los valores del día, año,
	 * oferta, demanda, precio y venta.
	 *
	 * @param dia El valor para el atributo dia.
	 * @param anio El valor para el atributo anio.
	 * @param oferta El valor para el atributo oferta.
	 * @param demanda El valor para el atributo demanda.
	 * @param precio El valor para el atributo precio.
	 * @param venta El valor para el atributo venta.
	 */
	public Venta(int dia, int anio, double oferta, double demanda,
				 double precio, double venta)
	{
		this.dia = dia;
		this.anio = anio;
		this.oferta = oferta;
		this.demanda = demanda;
		this.precio = precio;
		this.venta = venta;
	}
	
	/**
	 * Método que devuelve el día del año de la venta.
	 *
	 * @return dia El día del año de la venta.
	 */
	public int obtenerDia()
	{
		return dia;
	}
	
	/**
	 * Método que devuelve el año de la venta.
	 *
	 * @return anio El año de la venta.
	 */
	public int obtenerAnio()
	{
		return anio;
	}
	
	/**
	 * Método que devuelve la oferta de la venta.
	 *
	 * @return oferta La oferta de la venta.
	 */
	public double obtenerOferta()
	{
		return oferta;
	}
	
	/**
	 * Método que devuelve la demanda de la venta.
	 *
	 * @return demanda La demanda de la venta.
	 */
	public double obtenerDemanda()
	{
		return demanda;
	}
	
	/**
	 * Método que devuelve el precio de la venta.
	 *
	 * @return precio El precio de la venta.
	 */
	public double obtenerPrecio()
	{
		return precio;
	}
	
	/**
	 * Método que devuelve la cantidad vendida.
	 *
	 * @return venta La cantidad vendida.
	 */
	public double obtenerVenta()
	{
		return venta;
	}
	
	/**
	 * Método que entrega un string con el día, año, oferta, demanda, precio y
	 * cantidad de la venta.
	 *
	 * @return string La venta compuesta como un string.
	 */
	public String toString()
	{
		return dia + "/" + anio + ": [" +
			   oferta + "; " + demanda + "; " + precio + "; " + venta + "]";
	}
	
	/**
	 * Método que determina si dos ventas son iguales o distintas. Cuando las
	 * fechas son menores se retorna el valor menos uno y cuando las fechas son
	 * mayores se retorna el valor mas uno. Cuando las fechas son iguales,
	 * entonces se retorna el valor cero.
	 *
	 * @param objeto El objeto con el cual se desea comparar.
	 *
	 * @return menos uno Cuando la fecha de la venta es menor.
	 * @return uno Cuando la fecha de la venta es mayor.
	 * @return cero Cuando la fecha de la venta es igual.
	 */
	public int compareTo(Object objeto)
	{
		Venta v = (Venta) objeto;
		
		if (((dia < v.obtenerDia()) && (anio <= v.obtenerAnio())) ||
			((dia >= v.obtenerDia()) && (anio < v.obtenerAnio())))
			return -1;
		
		if (((dia > v.obtenerDia()) && (anio >= v.obtenerAnio())) ||
			((dia <= v.obtenerDia()) && (anio > v.obtenerAnio())))
			return 1;
		
		return 0;
	}
}