/**
 * @(#)VectorVenta.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.util.Vector;

/**
 * Clase que representa una estructura de dato del tipo Vector y que se
 * especializa en almacenar ventas de la planificación de ventas del modelo de
 * integración.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Vector
 * @see Venta
 */
public class VectorVenta extends Vector
{
	/**
	 * Método constructor que inicializa el vector. Se llama al constructor por
	 * defecto de la clase padre.
	 */	
	public VectorVenta()
	{
		super();
	}
	
	/**
	 * Método que obtiene un elemento del vector con índice conocido. Se llama
	 * al método de la clase padre que obtiene el objeto con índice conocido y
	 * luego se castea a Venta y se devuelve este objeto.
	 *
	 * @param indice Una posición del índice del vector.
	 *
	 * @return venta El nodo de venta que se encuentra ubicado en la posición
	 *               del vector con el índice conocido.
	 */
	public Venta obtenerElemento(int indice)
	{
		return (Venta) elementAt(indice);
	}
}