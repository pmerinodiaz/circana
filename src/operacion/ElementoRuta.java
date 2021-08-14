/**
 * @(#)ElementoRuta.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que define la estructura que tendrá un elemento correspondiente a un 
 * nodo o punto a visitar por el algoritmo del agente viajero.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 */
public class ElementoRuta
{
	/** Indice dentro de la tabla de distancias. */
	private int indice;
	
	/** Código del nodo en la base de datos. */
	private int codigo;
	
	/** Descripción del nodo. */
	private String descripcion;
	
	/** Indica el tipo de nodo (0: Nodo origen, - 1: Nodo destino). */
	private String tipo;
	
	/** Indica la coordenada geográfica del nodo. */
	private Coordenada coordenada;
	
	/**
	 * Método constructor que inicializa los atributos de la clase con los
	 * valores por defecto.
	 */
	public ElementoRuta()
	{
		this.indice = 0;
		this.codigo = 0;
		this.descripcion = "";
		this.tipo = "";
		this.coordenada = new Coordenada();
	}
	
	/**
	 * Método constructor que inicializa los atributos de la clase con los
	 * valores recibidos como parámetros.
	 *
	 * @param indice Indice dentro de la tabla de distancias.
	 * @param codigo Código del nodo en la base de datos.
	 * @param descripcion Descripción del nodo.
	 * @param tipo Indica el tipo de nodo.
	 * @param coordenada Indica la coordenada geográfica del nodo.
	 */
	public ElementoRuta(int indice, int codigo, String descripcion,
						String tipo, Coordenada coordenada)
	{
		this.indice = indice;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.coordenada = coordenada;
	}
	
	/**
	 * Método que devuelve el indice en la tabla de distancias del elemento.
	 *
	 * @return indice Posición en la tabla de distancias.
	 */
	public int obtenerIndice()
	{
		return indice;
	}
	
	/**
	 * Método que devuelve el código del elemento en la base de datos.
	 *
	 * @return codigo Valor de identificación del elemento en la base de datos.
	 */
	public int obtenerCodigo()
	{
		return codigo;
	}
	
	/**
	 * Método que devuelve la descripción del elemento.
	 *
	 * @return descripcion Valor descriptivo del elemento.
	 */
	public String obtenerDescripcion()
	{
		return descripcion;
	}
	
	/**
	 * Método que devuelve el tipo de elemento. Referencia si es un punto de
	 * oferta o demanda.
	 *
	 * @return tipo Valor del tipo de nodo.
	 */
	public String obtenerTipo()
	{
		return tipo;
	}
	
	/**
	 * Método que devuelve la coordenada geográfica del elemento.
	 *
	 * @return coordenada Coordenada geográfica.
	 */
	public Coordenada obtenerCoordenada()
	{
		return coordenada;
	}
}