/**
 * @(#)SuperCamaronNailon.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa a un grupo de camarones nailon que comparten
 * caracter�sticas fisiol�gicas y morfol�gicas, las cuales pertenecen a la misma
 * especie. Esta clase extiende de la clase SuperCrustaceo.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see SuperCrustaceo
 * @see Espacio
 * @see Recurso
 */
public final class SuperCamaronNailon extends SuperCrustaceo
{
	/**
	 * M�todo constructor que permite crear un super-camar�n nailon.
	 *
	 * @param cantidad El valor para la cantidad de camarones nailon que
	 *                 contiene el super-camar�n nailon.
	 * @param espacio El espacio discreto en que naci� el super-camar�n nailon.
	 */
	public SuperCamaronNailon(long cantidad, Espacio espacio)
	{
		super(Recurso.CAMARON_NAILON, cantidad, espacio);
	}
	
	/**
	 * M�todo constructor que permite crear un super-camar�n nailon.
	 *
	 * @param sexo El valor para el tipo de sexo que es el super-camar�n nailon.
	 * @param estado El valor para el tipo de estado que es el super-camar�n
	 *               nailon.
 	 * @param talla El valor para la talla que tiene el super-camar�n nailon.
	 * @param peso El valor para el peso que tiene el super-camar�n nailon.
	 * @param cantidad El valor para la cantidad de camarones nailon que
	 *                 contiene el super-camar�n nailon.
	 * @param espacio El espacio discreto en que naci� el super-camar�n nailon.
	 */
	public SuperCamaronNailon(int sexo, int estado, double talla, double peso,
							  long cantidad, Espacio espacio)
	{
		super(Recurso.CAMARON_NAILON, sexo, estado, talla, peso, cantidad,
			  espacio);
	}
}