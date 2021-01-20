/**
 * @(#)SuperPez.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa a un grupo de peces que comparten características
 * fisiológicas y morfológicas, las cuales pertenecen a la misma especie. Esta
 * clase extiende de la clase SuperIndividuo.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see SuperIndividuo
 * @see TipoAnimal
 * @see Espacio
 */
public abstract class SuperPez extends SuperIndividuo
{
	/**
	 * Método constructor que permite crear un super-pez.
	 *
	 * @param recurso El valor para el tipo de recurso que es el super-pez.
	 * @param cantidad El valor para la cantidad de peces que contiene el
	 *                 super-pez.
	 * @param espacio El espacio discreto en que nació el super-individuo.
	 */
	public SuperPez(int recurso, long cantidad, Espacio espacio)
	{
		super(recurso, TipoAnimal.PEZ, cantidad, espacio);
	}
	
	/**
	 * Método constructor que permite crear un super-pez.
	 *
	 * @param recurso El valor para el tipo de recurso que es el super-pez.
	 * @param sexo El valor para el tipo de sexo que es el super-pez.
	 * @param estado El valor para el tipo de estado que es el super-pez.
	 * @param talla El valor para la talla que tiene el super-pez.
	 * @param peso El valor para el peso que tiene el super-pez.
	 * @param cantidad El valor para la cantidad de peces que contiene el
	 *                 super-pez.
	 * @param espacio El espacio discreto en que nació el super-individuo.
	 */
	public SuperPez(int recurso, int sexo, int estado, double talla,
					double peso, long cantidad, Espacio espacio)
	{
		super(recurso, TipoAnimal.PEZ, sexo, estado, talla, peso, cantidad,
			  espacio);
	}
}