/**
 * @(#)SuperMerluzaComun.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa a un grupo de merluzas comunes que comparten
 * características fisiológicas y morfológicas, las cuales pertenecen a la misma
 * especie. Esta clase extiende de la clase SuperPez.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see SuperPez
 * @see Espacio
 * @see Recurso
 */
public final class SuperMerluzaComun extends SuperPez
{
	/**
	 * Método constructor que permite crear una super-merluza común.
	 *
	 * @param cantidad El valor para la cantidad de merluzas comunes que
	 *                 contiene la super-merluza común.
	 * @param espacio El espacio discreto en que nació la super-merluza común.
	 */
	public SuperMerluzaComun(long cantidad, Espacio espacio)
	{
		super(Recurso.MERLUZA_COMUN, cantidad, espacio);
	}
	
	/**
	 * Método constructor que permite crear una super-merluza común.
	 *
	 * @param sexo El valor para el tipo de sexo que es la super-merluza común.
	 * @param estado El valor para el tipo de estado que es la super-merluza
	 *               común.
 	 * @param talla El valor para la talla que tiene la super-merluza común.
	 * @param peso El valor para el peso que tiene la super-merluza común.
	 * @param cantidad El valor para la cantidad de merluzas comunes que
	 *                 contiene la super-merluza común.
	 * @param espacio El espacio discreto en que nació la super-merluza común.
	 */
	public SuperMerluzaComun(int sexo, int estado, double talla, double peso,
							 long cantidad, Espacio espacio)
	{
		super(Recurso.MERLUZA_COMUN, sexo, estado, talla, peso, cantidad,
			  espacio);
	}
}