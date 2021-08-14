/**
 * @(#)ElementoRuta.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Clase que define la estructura que tendr� un elemento correspondiente a un 
 * nodo o punto a visitar por el algoritmo del agente viajero.
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
 */
public class ElementoRuta
{
	/** Indice dentro de la tabla de distancias. */
	private int indice;
	
	/** C�digo del nodo en la base de datos. */
	private int codigo;
	
	/** Descripci�n del nodo. */
	private String descripcion;
	
	/** Indica el tipo de nodo (0: Nodo origen, - 1: Nodo destino). */
	private String tipo;
	
	/** Indica la coordenada geogr�fica del nodo. */
	private Coordenada coordenada;
	
	/**
	 * M�todo constructor que inicializa los atributos de la clase con los
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
	 * M�todo constructor que inicializa los atributos de la clase con los
	 * valores recibidos como par�metros.
	 *
	 * @param indice Indice dentro de la tabla de distancias.
	 * @param codigo C�digo del nodo en la base de datos.
	 * @param descripcion Descripci�n del nodo.
	 * @param tipo Indica el tipo de nodo.
	 * @param coordenada Indica la coordenada geogr�fica del nodo.
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
	 * M�todo que devuelve el indice en la tabla de distancias del elemento.
	 *
	 * @return indice Posici�n en la tabla de distancias.
	 */
	public int obtenerIndice()
	{
		return indice;
	}
	
	/**
	 * M�todo que devuelve el c�digo del elemento en la base de datos.
	 *
	 * @return codigo Valor de identificaci�n del elemento en la base de datos.
	 */
	public int obtenerCodigo()
	{
		return codigo;
	}
	
	/**
	 * M�todo que devuelve la descripci�n del elemento.
	 *
	 * @return descripcion Valor descriptivo del elemento.
	 */
	public String obtenerDescripcion()
	{
		return descripcion;
	}
	
	/**
	 * M�todo que devuelve el tipo de elemento. Referencia si es un punto de
	 * oferta o demanda.
	 *
	 * @return tipo Valor del tipo de nodo.
	 */
	public String obtenerTipo()
	{
		return tipo;
	}
	
	/**
	 * M�todo que devuelve la coordenada geogr�fica del elemento.
	 *
	 * @return coordenada Coordenada geogr�fica.
	 */
	public Coordenada obtenerCoordenada()
	{
		return coordenada;
	}
}