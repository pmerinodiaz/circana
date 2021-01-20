/**
 * @(#)VectorVenta.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.util.Vector;

/**
 * Clase que representa una estructura de dato del tipo Vector y que se
 * especializa en almacenar ventas de la planificaci�n de ventas del modelo de
 * integraci�n.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Vector
 * @see Venta
 */
public class VectorVenta extends Vector
{
	/**
	 * M�todo constructor que inicializa el vector. Se llama al constructor por
	 * defecto de la clase padre.
	 */	
	public VectorVenta()
	{
		super();
	}
	
	/**
	 * M�todo que obtiene un elemento del vector con �ndice conocido. Se llama
	 * al m�todo de la clase padre que obtiene el objeto con �ndice conocido y
	 * luego se castea a Venta y se devuelve este objeto.
	 *
	 * @param indice Una posici�n del �ndice del vector.
	 *
	 * @return venta El nodo de venta que se encuentra ubicado en la posici�n
	 *               del vector con el �ndice conocido.
	 */
	public Venta obtenerElemento(int indice)
	{
		return (Venta) elementAt(indice);
	}
}