/**
 * @(#)VectorCaladero.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.util.Vector;

/**
 * Clase que representa una estructura de dato del tipo Vector que almacena
 * caladeros de captura. Esta clase extiende de la clase Vector.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Vector
 * @see Caladero
 */
public class VectorCaladero extends Vector
{
	/**
	 * Método constructor que inicializa el vector. Se llama al constructor por
	 * defecto de la clase padre.
	 */
	public VectorCaladero()
	{
		super();
	}
	
	/**
	 * Método que obtiene un elemento del vector con índice conocido. Se llama
	 * al método de la clase padre que obtiene el objeto con índice conocido y
	 * luego se castea a Caladero y se devuelve este objeto.
	 *
	 * @param indice Una posición del índice del vector.
	 *
	 * @return caladero El nodo caladero que se encuentra ubicado en la posición
	 *                  del vector con el índice conocido.
	 */
	public Caladero obtenerElemento(int indice)
	{
		return (Caladero) elementAt(indice);
	}
	
	/**
	 * Método que busca en el vector un caladero especificado en el parámetro.
	 * En caso de encontrarse el caladero en el vector se retorna true. En caso
	 * contrario, se retorna false. El procedimiento de buscar el caladero en el
	 * vector se realiza en un recorrido hacia adelante del vector hasta llegar
	 * al final.
	 *
	 * @param caladero El caladero que se quiere buscar.
	 *
	 * @return true Cuando el caladero está en el vector.
	 * @return false Cuando el caladero no está en el vector.
	 */
	public boolean estar(Caladero caladero)
	{
		// Obtener el tamaño del vector.
		int tamanio = size();
		
		// Ciclo para recorrer todos el vector.
		for (int i=0; i<tamanio; i++)
		{
			// Obtener el caladero i-ésimo.
			Caladero nodoCaladero = obtenerElemento(i);
			
			// Cuando se encuentra el caladero.
			if (nodoCaladero.compareTo(caladero) == 0)
				return true;
		}
		
		return false;
	}
}