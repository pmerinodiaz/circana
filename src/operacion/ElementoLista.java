/**
 * @(#)ElementoLista.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Clase que define la estructura que tendr� un elemento dentro de un Vector
 * que contiene elementos Xijk de una matriz soluci�n.
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
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
	 * M�todo constructor que establece el elemento perteneciente a una matriz
	 * soluci�n.
	 *
	 * @param i Elemento que indica el elemento de posici�n i.
	 * @param j Elemento que indica el elemento de posici�n j.
	 * @param k Elemento que indica el elemento de posici�n k.
	 *
	 * @return matriz Matriz con una soluci�n b�sica factible.
	 */
	public ElementoLista(int i, int j, int k)
	{
		this.i = i;
		this.j = j;
		this.k = k;
	}
}