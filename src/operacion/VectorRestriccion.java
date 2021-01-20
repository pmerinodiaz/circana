/**
 * @(#)VectorRestriccion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.util.Vector;

/**
 * Clase que representa una estructura de dato del tipo Vector que almacena
 * elementos de tipo restricción. Estas son de tipo oferta, demanda o medios
 * de transporte.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see Vector
 * @see ElementoRestriccion
 */
public class VectorRestriccion extends Vector
{
	/**
	 * Método constructor que inicializa el vector.
	 */
	public VectorRestriccion()
	{
		super();
	}
	
	/**
	 * Método que permite agregar un elemento al final del vector.
	 *
	 * @param elemento Elemento perteneciente a una restricción.
	 */
	public void agregar(ElementoRestriccion elemento)
	{
		super.add(elemento);
	}
	
	/**
	 * Método que permite insertar un elemento en la posición indicada.
	 *
	 * @param indice Posición dentro del vector.
	 * @param elemento Elemento perteneciente a una restricción.
	 */
	public void insertar(int indice, ElementoRestriccion elemento)
	{
		super.insertElementAt(elemento, indice);
	}
	
	/**
	 * Método que permite remover un elemento de la posición indicada.
	 *
	 * @param indice Posición dentro del vector.
	 */
	public void remover(int indice)
	{
		super.removeElementAt(indice);
	}
	
	/**
	 * Método que permite modificar el valor de un elemento en la posición
	 * indicada.
	 *
	 * @param indice Posición dentro del vector.
	 * @param elemento Elemento perteneciente a una restricción.
	 */
	public void cambiarValor(int indice, ElementoRestriccion elemento)
	{
		super.setElementAt(elemento, indice);
	}
	
	/**
	 * Método que entrega el elemento de la posición indicada.
	 *
	 * @param indice Posición dentro del vector.
	 * 
	 * @return ElementoRestriccion Elemento de la lista de restricción.
	 */
	public ElementoRestriccion obtenerElemento(int indice)
	{
		return (ElementoRestriccion) super.get(indice);
	}
	
	/**
	 * Método que entrega las cantidades de los elementos como en arreglo.
	 *
	 * @return arreglo Vector de números reales que contiene las cantidades.
	 */	
	public Vector obtenerArrayCantidad()
	{
		ElementoRestriccion elemento;
		
		int tamanio = super.size();
		int i;
		
		Vector arreglo = new Vector();
		
		for(i = 0;i < tamanio;i++)
		{
			elemento = (ElementoRestriccion) super.elementAt(i);
			arreglo.add("" + elemento.obtenerCantidad());
		}
		
		return arreglo;
	}
	
	/**
	 * Método que modifica las cantidades de los elementos en el arreglo.
	 *
	 * @param array El arreglo de valores que corresponden a las cantidades
	 *              de elementos.
	 */
	public void modificarArrayCantidad(double[] array)
	{
		ElementoRestriccion elemento;
		
		int tamanio = array.length;
		int i;
		
		for(i = 0;i < tamanio;i++)
		{
			elemento = (ElementoRestriccion) super.elementAt(i);
			elemento.establecerCantidad(array[i]);
		}
	}
	
	/**
	 * Método que entrega las suma total de las cantidades de los elementos 
	 * incluidos en el arreglo.
	 *
	 * @return suma Sumatoria total de los elementos en el arreglo.
	 */	
	public double obtenerSumatoriaCantidad()
	{
		ElementoRestriccion elemento;
		
		int tamanio = super.size();
		int i;
		double suma = 0;
		
		for(i = 0;i < tamanio;i++)
		{
			elemento = (ElementoRestriccion) super.elementAt(i);
			suma = suma + elemento.obtenerCantidad();
		}
		
		return suma;
	}
	
	/**
	 * Método que busca un elemento en el vector por el parámetro del código.
	 *
	 * @param codigo Código del elemento en la base de datos.
	 *
	 * @return pos Ubicación del elemento dentro del vector.
	 */
	public int buscarElemento(int codigo)
	{
		ElementoRestriccion elemento;
		
		int tamanio = super.size();
		int pos = -1;
		
		for(int i = 0;i < tamanio;i++)
		{
			elemento = obtenerElemento(i);
			if(elemento.obtenerCodigo() == codigo)
			{
				pos = i;
				break;
			}
		}
		
		return pos;
	}
}