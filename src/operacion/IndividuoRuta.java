/**
 * @(#)IndividuoRuta.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */
 
/**
 * Clase que describe la estructura de un individuo dentro de una poblaci�n. La
 * estructura representa una soluci�n al algoritmo del agente viajero.
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
 */
public class IndividuoRuta
{
	/** Soluci�n con la ruta recorrida por el individuo. */
	private VectorRuta ruta;
	
	/** Valor de adaptaci�n o evaluaci�n del individuo. */
	private double valorEvaluacion;
	
	/**
	 * M�todo constructor que inicializa los valores por defecto de la clase.
	 */
	public IndividuoRuta()
	{
		ruta = new VectorRuta();
		valorEvaluacion = 0;
	}
	
	/**
	 * M�todo constructor que inicializa los valores de la clase con los
	 * par�metros recibidos.
	 *
	 * @param individuo El individuo de ruta.
	 */
	public IndividuoRuta(IndividuoRuta individuo)
	{
		this();
		establecerRuta(new VectorRuta(individuo.obtenerRuta()));
		establecerValorEvaluacion(individuo.obtenerValorEvaluacion());
	}
	
	/**
	 * M�todo que establece la ruta seguida por un individuo.
	 *
	 * @param ruta La ruta seguida por un individuo.
	 */
	public void establecerRuta(VectorRuta ruta)
	{
		this.ruta = ruta;
	}
	
	/**
	 * M�todo que establece el grado de adaptaci�n o adecuaci�n del individuo.
	 *
	 * @param evaluacion Grado de adaptaci�n del individuo.
	 */
	public void establecerValorEvaluacion(double evaluacion)
	{
		this.valorEvaluacion = evaluacion;
	}
	
	/**
	 * M�todo que devuelve la ruta seguida por un individuo.
	 *
	 * @return La ruta seguida por un individuo.
	 */
	public VectorRuta obtenerRuta()
	{
		return ruta;
	}
	
	/**
	 * M�todo que devuelve el valor de adaptaci�n del individuo con respecto a
	 * la ruta seguida.
	 *
	 * @param evaluacion Grado de adaptaci�n del individuo.
	 */
	public double obtenerValorEvaluacion()
	{
		return valorEvaluacion;
	}
}