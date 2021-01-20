/**
 * @(#)IndividuoRuta.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */
 
/**
 * Clase que describe la estructura de un individuo dentro de una población. La
 * estructura representa una solución al algoritmo del agente viajero.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 */
public class IndividuoRuta
{
	/** Solución con la ruta recorrida por el individuo. */
	private VectorRuta ruta;
	
	/** Valor de adaptación o evaluación del individuo. */
	private double valorEvaluacion;
	
	/**
	 * Método constructor que inicializa los valores por defecto de la clase.
	 */
	public IndividuoRuta()
	{
		ruta = new VectorRuta();
		valorEvaluacion = 0;
	}
	
	/**
	 * Método constructor que inicializa los valores de la clase con los
	 * parámetros recibidos.
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
	 * Método que establece la ruta seguida por un individuo.
	 *
	 * @param ruta La ruta seguida por un individuo.
	 */
	public void establecerRuta(VectorRuta ruta)
	{
		this.ruta = ruta;
	}
	
	/**
	 * Método que establece el grado de adaptación o adecuación del individuo.
	 *
	 * @param evaluacion Grado de adaptación del individuo.
	 */
	public void establecerValorEvaluacion(double evaluacion)
	{
		this.valorEvaluacion = evaluacion;
	}
	
	/**
	 * Método que devuelve la ruta seguida por un individuo.
	 *
	 * @return La ruta seguida por un individuo.
	 */
	public VectorRuta obtenerRuta()
	{
		return ruta;
	}
	
	/**
	 * Método que devuelve el valor de adaptación del individuo con respecto a
	 * la ruta seguida.
	 *
	 * @param evaluacion Grado de adaptación del individuo.
	 */
	public double obtenerValorEvaluacion()
	{
		return valorEvaluacion;
	}
}