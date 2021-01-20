/**
 * @(#)SuperCamaronNailon.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa a un grupo de camarones nailon que comparten
 * características fisiológicas y morfológicas, las cuales pertenecen a la misma
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
	 * Método constructor que permite crear un super-camarón nailon.
	 *
	 * @param cantidad El valor para la cantidad de camarones nailon que
	 *                 contiene el super-camarón nailon.
	 * @param espacio El espacio discreto en que nació el super-camarón nailon.
	 */
	public SuperCamaronNailon(long cantidad, Espacio espacio)
	{
		super(Recurso.CAMARON_NAILON, cantidad, espacio);
	}
	
	/**
	 * Método constructor que permite crear un super-camarón nailon.
	 *
	 * @param sexo El valor para el tipo de sexo que es el super-camarón nailon.
	 * @param estado El valor para el tipo de estado que es el super-camarón
	 *               nailon.
 	 * @param talla El valor para la talla que tiene el super-camarón nailon.
	 * @param peso El valor para el peso que tiene el super-camarón nailon.
	 * @param cantidad El valor para la cantidad de camarones nailon que
	 *                 contiene el super-camarón nailon.
	 * @param espacio El espacio discreto en que nació el super-camarón nailon.
	 */
	public SuperCamaronNailon(int sexo, int estado, double talla, double peso,
							  long cantidad, Espacio espacio)
	{
		super(Recurso.CAMARON_NAILON, sexo, estado, talla, peso, cantidad,
			  espacio);
	}
}