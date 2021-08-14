/**
 * @(#)VectorResultado.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.util.Vector;

/**
 * Clase que representa una estructura de dato del tipo Vector y que se
 * especializa en almacenar resultados de un modelo en particular.
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
 * @see Vector
 * @see Resultado
 */
 public class VectorResultado extends Vector
{
	/**
	 * M�todo constructor que inicializa el vector. Se llama al constructor por
	 * defecto de la clase padre.
	 */	
	public VectorResultado()
	{
		super();
	}
	
	/**
	 * M�todo que obtiene un elemento del vector con �ndice conocido. Se llama
	 * al m�todo de la clase padre que obtiene el objeto con �ndice conocido y
	 * luego se castea a Resultado y se devuelve este objeto.
	 *
	 * @param indice Una posici�n del �ndice del vector.
	 *
	 * @return resultado El nodo resultado que se encuentra ubicado en la
	 *                   posici�n del vector con el �ndice conocido.
	 */
	public Resultado obtenerElemento(int indice)
	{
		return (Resultado) elementAt(indice);
	}
}