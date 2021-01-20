/**
 * @(#)SuperLangostinoColorado.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa a un grupo de langostinos colorados que comparten
 * características fisiológicas y morfológicas, las cuales pertenecen a la misma
 * especie. Esta clase extiende de la clase SuperCrustaceo.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see SuperCrustaceo
 * @see Espacio
 * @see Recurso
 */
public final class SuperLangostinoColorado extends SuperCrustaceo
{
	/**
	 * Método constructor que permite crear un super-langostino colorado.
	 *
	 * @param cantidad El valor para la cantidad de langostinos que contiene el
	 *                 super-langostino colorado.
	 * @param espacio El espacio discreto en que nació el super-langostino
	 *                colorado.
	 */
	public SuperLangostinoColorado(long cantidad, Espacio espacio)
	{
		super(Recurso.LANGOSTINO_COLORADO, cantidad, espacio);
	}
	
	/**
	 * Método constructor que permite crear un super-langostino colorado.
	 *
	 * @param sexo El valor para el tipo de sexo que es el super-langostino
	 *             colorado.
	 * @param estado El valor para el tipo de estado que es el super-langostino
	 *               colorado.
 	 * @param talla El valor para la talla que tiene el super-langostino
 	 *              colorado.
	 * @param peso El valor para el peso que tiene el super-langostino colorado.
	 * @param cantidad El valor para la cantidad de langostinos que contiene el
	 *                 super-langostino colorado.
	 * @param espacio El espacio discreto en que nació el super-langostino
	 *                colorado.
	 */
	public SuperLangostinoColorado(int sexo, int estado, double talla,
								   double peso, long cantidad, Espacio espacio)
	{
		super(Recurso.LANGOSTINO_COLORADO, sexo, estado, talla, peso, cantidad,
			  espacio);
	}
}