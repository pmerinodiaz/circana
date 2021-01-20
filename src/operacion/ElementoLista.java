/**
 * @(#)ElementoLista.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que define la estructura que tendrá un elemento dentro de un Vector
 * que contiene elementos Xijk de una matriz solución.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 */
public class ElementoLista
{
	/** Identificador I del elemento Xijk. */
	public int i;
	
	/** Identificador J del elemento Xijk. */
	public int j;
	
	/** Identificador K del elemento Xijk. */
	public int k;
	
	/**
	 * Método constructor que establece el elemento perteneciente a una matriz
	 * solución.
	 *
	 * @param i Elemento que indica el elemento de posición i.
	 * @param j Elemento que indica el elemento de posición j.
	 * @param k Elemento que indica el elemento de posición k.
	 *
	 * @return matriz Matriz con una solución básica factible.
	 */
	public ElementoLista(int i, int j, int k)
	{
		this.i = i;
		this.j = j;
		this.k = k;
	}
}