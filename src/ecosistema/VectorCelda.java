/**
 * @(#)VectorCelda.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.util.Vector;

/**
 * Clase que representa una estructura de dato del tipo Vector que almacena
 * de celdas de ubicaci�n de super-individuos. Esta clase extiende de la clase
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
	 * M�todo constructor que inicializa el vector. Se llama al constructor por
	 * defecto de la clase padre.
	 */
	public VectorCelda()
	{
		super();
	}
	
	/**
	 * M�todo que obtiene un elemento del vector con �ndice conocido. Se llama
	 * al m�todo de la clase padre que obtiene el objeto con �ndice conocido y
	 * luego se castea a Celda y se devuelve este objeto.
	 *
	 * @param indice Una posici�n del �ndice del vector.
	 *
	 * @return celda El nodo origen-destino que se encuentra ubicado en la
	 *               posici�n del vector con el �ndice conocido.
	 */
	public Celda obtenerElemento(int indice)
	{
		return (Celda) elementAt(indice);
	}
	
	/**
	 * M�todo que remueve una celda del vector. Se hace un recorrido del vector
	 * hasta encontrar la celda ha eliminar. Cuando se encuentra, se elimina del
	 * vector.
	 *
	 * @param celdaEliminar La celda que se quiere eliminar.
	 */
	public void remove(Celda celdaEliminar)
	{
		// Obtener el tama�o del vector.
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