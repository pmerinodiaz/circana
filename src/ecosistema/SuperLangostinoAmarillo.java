/**
 * @(#)SuperLangostinoAmarillo.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa a un grupo de langostinos amarillos que comparten
 * características fisiológicas y morfológicas, las cuales pertenecen a la misma
 * especie. Esta clase extiende de la clase SuperCrustaceo.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see SuperCrustaceo
 * @see Espacio
 * @see Recurso
 */
public final class SuperLangostinoAmarillo extends SuperCrustaceo
{
	/**
	 * Método constructor que permite crear un super-langostino amarillo.
	 *
	 * @param cantidad El valor para la cantidad de langostinos que contiene el
	 *                 super-langostino amarillo.
	 * @param espacio El espacio discreto en que nació el super-langostino
	 *                amarillo.
	 */
	public SuperLangostinoAmarillo(long cantidad, Espacio espacio)
	{
		super(Recurso.LANGOSTINO_AMARILLO, cantidad, espacio);
	}
	
	/**
	 * Método constructor que permite crear un super-langostino amarillo.
	 *
	 * @param sexo El valor para el tipo de sexo que es el super-langostino
	 *             amarillo.
	 * @param estado El valor para el tipo de estado que es el super-langostino
	 *               amarillo.
 	 * @param talla El valor para la talla que tiene el super-langostino
 	 *              amarillo.
	 * @param peso El valor para el peso que tiene el super-langostino amarillo.
	 * @param cantidad El valor para la cantidad de langostinos que contiene el
	 *                 super-langostino amarillo.
	 * @param espacio El espacio discreto en que nació el super-langostino
	 *                amarillo.
	 */
	public SuperLangostinoAmarillo(int sexo, int estado, double talla,
								   double peso, long cantidad, Espacio espacio)
	{
		super(Recurso.LANGOSTINO_AMARILLO, sexo, estado, talla, peso, cantidad,
			  espacio);
	}
}