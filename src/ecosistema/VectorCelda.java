/**
 * @(#)VectorCelda.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.util.Vector;

/**
 * Clase que representa una estructura de dato del tipo Vector que almacena
 * de celdas de ubicación de super-individuos. Esta clase extiende de la clase
 * Vector.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Vector
 * @see Celda
 */
public class VectorCelda extends Vector
{
	/**
	 * Método constructor que inicializa el vector. Se llama al constructor por
	 * defecto de la clase padre.
	 */
	public VectorCelda()
	{
		super();
	}
	
	/**
	 * Método que obtiene un elemento del vector con índice conocido. Se llama
	 * al método de la clase padre que obtiene el objeto con índice conocido y
	 * luego se castea a Celda y se devuelve este objeto.
	 *
	 * @param indice Una posición del índice del vector.
	 *
	 * @return celda El nodo origen-destino que se encuentra ubicado en la
	 *               posición del vector con el índice conocido.
	 */
	public Celda obtenerElemento(int indice)
	{
		return (Celda) elementAt(indice);
	}
	
	/**
	 * Método que remueve una celda del vector. Se hace un recorrido del vector
	 * hasta encontrar la celda ha eliminar. Cuando se encuentra, se elimina del
	 * vector.
	 *
	 * @param celdaEliminar La celda que se quiere eliminar.
	 */
	public void remove(Celda celdaEliminar)
	{
		// Obtener el tamaño del vector.
		int tamanio = size();
		
		// Ciclo para recorrer el vector.
		for (int i=0; i<tamanio; i++)
		{
			// Obtener la celda.
			Celda celda = obtenerElemento(i);
			
			// Cuando se encuentra la celda.
			if (celda.compareTo(celdaEliminar) == 0)
			{
				removeElementAt(i);
				break;
			}
		}
	}
}