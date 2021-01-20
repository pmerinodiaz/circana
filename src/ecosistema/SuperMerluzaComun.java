/**
 * @(#)SuperMerluzaComun.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa a un grupo de merluzas comunes que comparten
 * caracter�sticas fisiol�gicas y morfol�gicas, las cuales pertenecen a la misma
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
	 * M�todo constructor que permite crear una super-merluza com�n.
	 *
	 * @param cantidad El valor para la cantidad de merluzas comunes que
	 *                 contiene la super-merluza com�n.
	 * @param espacio El espacio discreto en que naci� la super-merluza com�n.
	 */
	public SuperMerluzaComun(long cantidad, Espacio espacio)
	{
		super(Recurso.MERLUZA_COMUN, cantidad, espacio);
	}
	
	/**
	 * M�todo constructor que permite crear una super-merluza com�n.
	 *
	 * @param sexo El valor para el tipo de sexo que es la super-merluza com�n.
	 * @param estado El valor para el tipo de estado que es la super-merluza
	 *               com�n.
 	 * @param talla El valor para la talla que tiene la super-merluza com�n.
	 * @param peso El valor para el peso que tiene la super-merluza com�n.
	 * @param cantidad El valor para la cantidad de merluzas comunes que
	 *                 contiene la super-merluza com�n.
	 * @param espacio El espacio discreto en que naci� la super-merluza com�n.
	 */
	public SuperMerluzaComun(int sexo, int estado, double talla, double peso,
							 long cantidad, Espacio espacio)
	{
		super(Recurso.MERLUZA_COMUN, sexo, estado, talla, peso, cantidad,
			  espacio);
	}
}