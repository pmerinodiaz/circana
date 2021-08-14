/**
 * @(#)SuperCrustaceo.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa a un grupo de curst�ceos que comparten caracter�sticas
 * fisiol�gicas y morfol�gicas, las cuales pertenecen a la misma especie. Esta
 * clase extiende de la clase SuperIndividuo.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see SuperIndividuo
 * @see TipoAnimal
 * @see Espacio
 */
public abstract class SuperCrustaceo extends SuperIndividuo
{
	/**
	 * M�todo constructor que permite crear un super-crust�ceo.
	 *
	 * @param recurso El valor para el tipo de recurso que es el
	 *                super-crust�ceo.
	 * @param cantidad El valor para la cantidad de individuos que contiene el
	 *                 super-crust�ceo.
	 * @param espacio El espacio discreto en que naci� el super-individuo.
	 */
	public SuperCrustaceo(int recurso, long cantidad, Espacio espacio)
	{
		super(recurso, TipoAnimal.CRUSTACEO, cantidad, espacio);
	}
	
	/**
	 * M�todo constructor que permite crear un super-crust�ceo.
	 *
	 * @param recurso El valor para el tipo de recurso que es el
	 *                super-crust�ceo.
	 * @param sexo El valor para el tipo de sexo que es el super-crust�ceo.
	 * @param estado El valor para el tipo de estado que es el super-crust�ceo.
	 * @param talla El valor para la talla que tiene el super-crust�ceo.
	 * @param peso El valor para el peso que tiene el super-crust�ceo.
	 * @param cantidad El valor para la cantidad de individuos que contiene el
	 *                 super-crust�ceo.
	 * @param espacio El espacio discreto en que naci� el super-individuo.
	 */
	public SuperCrustaceo(int recurso, int sexo, int estado, double talla,
						  double peso, long cantidad, Espacio espacio)
	{
		super(recurso, TipoAnimal.CRUSTACEO, sexo, estado, talla, peso,
			  cantidad, espacio);
	}
}