/**
 * @(#)VectorOrigenDestino.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.util.Vector;

/**
 * Clase que representa una estructura de dato del tipo Vector que almacena
 * pares ordenados de celdas origen-destino de super-individuos. Esta clase
 * extiende de la clase Vector.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Vector
 * @see OrigenDestino
 */
public class VectorOrigenDestino extends Vector
{
	/**
	 * Método constructor que inicializa el vector. Se llama al constructor por
	 * defecto de la clase padre.
	 */
	public VectorOrigenDestino()
	{
		super();
	}
	
	/**
	 * Método que obtiene un elemento del vector con índice conocido. Se llama
	 * al método de la clase padre que obtiene el objeto con índice conocido y
	 * luego se castea a OrigenDestino y se devuelve este objeto.
	 *
	 * @param indice Una posición del índice del vector.
	 *
	 * @return origenDestino El nodo origen-destino que se encuentra ubicado en
	 *                       la posición del vector con el índice conocido.
	 */
	public OrigenDestino obtenerElemento(int indice)
	{
		return (OrigenDestino) elementAt(indice);
	}
	
	/**
	 * Método que inserta un par ordenado de celdas origen-destino al vector,
	 * por el último nodo. El procedimiento de inserción se hace creando un
	 * nuevo par ordenado origen-destino y luego éste se inserta al final del
	 * vector.
	 *
	 * @param origen La celda de origen que se quiere insertar.
	 * @param destino La celda de destino que se quiere insertar.
	 */
	public void addElement(Celda origen, Celda destino)
	{
		// Crear el nuevo par ordenado.
		OrigenDestino nuevo = new OrigenDestino(origen, destino);
		
		// Insertar al final del vector.
		addElement(nuevo);
	}
	
	/**
	 * Método que busca en el vector un par ordenado origen-destino que contiene
	 * la celda origen del vector especificada en el parámetro. En caso de
	 * encontrarse la celda de origen en el vector se retorna el par ordenado
	 * que la contiene. En caso contrario, se retorna null. El procedimiento de
	 * buscar la celda origen en el vector se realiza en un recorrido hacia
	 * adelante del vector hasta llegar al final.
	 *
	 * @param origen La celda de origen que se quiere buscar.
	 *
	 * @return origenDestino El par ordenado origen-destino que contiene la
	 *                       celda origen especificada en el parámetro.
	 */
	public OrigenDestino buscarOrigen(Celda origen)
	{
		// Obtener el tamaño del vector.
		int tamanio = size();
		
		// Ciclo para recorrer el vector.
		for (int i=0; i<tamanio; i++)
		{
			// Obtener el par ordenado origen-destino.
			OrigenDestino origenDestino = obtenerElemento(i);
			
			// Cuando se encuentra el origen.
			if (origen.compareTo(origenDestino.obtenerOrigen()) == 0)
				return origenDestino;
		}
		
		return null;
	}
	
	/**
	 * Método que busca en el vector un par ordenado origen-destino que contiene
	 * la celda destino del vector especificada en el parámetro. En caso de
	 * encontrarse la celda de origen en el vector se retorna el par ordenado
	 * que la contiene. En caso contrario, se retorna null. El procedimiento de
	 * buscar la celda destino en el vector se realiza en un recorrido hacia
	 * adelante del vector hasta llegar al final.
	 *
	 * @param destino La celda de destino que se quiere buscar.
	 *
	 * @return origenDestino El par ordenado origen-destino que contiene la
	 *                       celda destino especificada en el parámetro.
	 */
	public OrigenDestino buscarDestino(Celda destino)
	{
		// Obtener el tamaño del vector.
		int tamanio = size();
		
		// Ciclo para recorrer el vector.
		for (int i=0; i<tamanio; i++)
		{
			// Obtener el par ordenado origen-destino.
			OrigenDestino origenDestino = obtenerElemento(i);
			
			// Cuando se encuentra el destino.
			if (destino.compareTo(origenDestino.obtenerDestino()) == 0)
				return origenDestino;
		}
		
		return null;
	}
	
	/**
	 * Método en donde se forma un string con todos los elementos que tiene este
	 * vector. Se hace un recorrido hacia adelante en el vector. Los elementos
	 * se van concadenando a un string, el cual después se retorna.
	 *
	 * @return texto Los elementos del vector concadenados como un string.
	 */
	public String toString()
	{
		String texto = "";
		
		// Obtener el tamaño del vector.
		int tamanio = size();
		
		// Ciclo para concadenar los elementos del vector.
		for (int i=0; i<tamanio; i++)
			texto+= obtenerElemento(i) + "\n";
		
		return texto;
	}
}