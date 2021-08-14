/**
 * @(#)SuperCrustaceo.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa a un grupo de curstáceos que comparten características
 * fisiológicas y morfológicas, las cuales pertenecen a la misma especie. Esta
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
	 * Método constructor que permite crear un super-crustáceo.
	 *
	 * @param recurso El valor para el tipo de recurso que es el
	 *                super-crustáceo.
	 * @param cantidad El valor para la cantidad de individuos que contiene el
	 *                 super-crustáceo.
	 * @param espacio El espacio discreto en que nació el super-individuo.
	 */
	public SuperCrustaceo(int recurso, long cantidad, Espacio espacio)
	{
		super(recurso, TipoAnimal.CRUSTACEO, cantidad, espacio);
	}
	
	/**
	 * Método constructor que permite crear un super-crustáceo.
	 *
	 * @param recurso El valor para el tipo de recurso que es el
	 *                super-crustáceo.
	 * @param sexo El valor para el tipo de sexo que es el super-crustáceo.
	 * @param estado El valor para el tipo de estado que es el super-crustáceo.
	 * @param talla El valor para la talla que tiene el super-crustáceo.
	 * @param peso El valor para el peso que tiene el super-crustáceo.
	 * @param cantidad El valor para la cantidad de individuos que contiene el
	 *                 super-crustáceo.
	 * @param espacio El espacio discreto en que nació el super-individuo.
	 */
	public SuperCrustaceo(int recurso, int sexo, int estado, double talla,
						  double peso, long cantidad, Espacio espacio)
	{
		super(recurso, TipoAnimal.CRUSTACEO, sexo, estado, talla, peso,
			  cantidad, espacio);
	}
}