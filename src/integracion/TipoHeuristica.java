/**
 * @(#)TipoHeuristica.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa los tipos de heurísticas de planificación de ventas de
 * la integración. Esta clase contiene solamente atributos estáticos.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public class TipoHeuristica
{
	/**
	 * El valor que indica que el tipo de heurística es vender a mayor oferta.
	 */
	public static final int MAYOR_OFERTA = 0;
	
	/**
	 * El valor que indica que el tipo de heurística es vender a mayor demanda.
	 */
	public static final int MAYOR_DEMANDA = 1;
	
	/**
	 * El valor que indica que el tipo de heurística es vender a mayor precio.
	 */
	public static final int MAYOR_PRECIO = 2;
}