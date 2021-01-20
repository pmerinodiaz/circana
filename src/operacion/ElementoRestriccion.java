/**
 * @(#)ElementoRestriccion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */
 
/**
 * Clase que define la estructura que tendr� un elemento de las condiciones de 
 * restricciones de oferta, demanda y medios de transporte dentro de un Vector.
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
 */
public class ElementoRestriccion
{
	/** C�digo de restricci�n en la base de datos. */
	private int codigoRestriccion;
	
	/** Descripci�n de la restricci�n. */
	private String descripcionRestriccion;
	
	/** Cantidad disponible. */
	private double cantidadRestriccion;
	
	/** Indica la coordenada geogr�fica. */
	private Coordenada coordenada;
	
	/**
	 * M�todo constructor que inicializa los elementos de la clase.
	 *
	 * @param codigo C�digo de restricci�n en la base de datos.
	 * @param descripcion Descripci�n de la restricci�n.
	 * @param cantidad Cantidad diponible.
	 */
	public ElementoRestriccion(int codigo, String descripcion, double cantidad)
	{
		this.codigoRestriccion = codigo;
		this.descripcionRestriccion = descripcion;
		this.cantidadRestriccion = cantidad;
		this.coordenada = new Coordenada();
	}
	
	/**
	 * M�todo que permite establecer el c�digo de este elemento.
	 *
	 * @param codigo Nuevo valor de codigo.
	 */
	public void establecerCodigo(int codigo)
	{
		this.codigoRestriccion = codigo;
	}
	
	/**
	 * M�todo que permite establecer la descripci�n de este elemento.
	 * 
	 * @param descripcion Nuevo valor de descripci�n.
	 */
	public void establecerDescripcion(String descripcion)
	{
		this.descripcionRestriccion = descripcion;
	}
	
	/**
	 * M�todo que permite establecer la cantidad de este elemento.
	 *
	 * @param cantidad Nuevo valor de la cantidad.
	 */
	public void establecerCantidad(double cantidad)
	{
		this.cantidadRestriccion = cantidad;
	}
	
	/**
	 * M�todo que permite establecer la coordenada geogr�fica.
	 *
	 * @param coordenada Coordenada geogr�fica del elemento.
	 */
	public void establecerCoordenada(Coordenada coordenada)
	{
		this.coordenada = coordenada;
	}
	
	/**
	 * M�todo que devuelve el c�digo de la restricci�n.
	 *
	 * @return codigoRestriccion Valor de identificaci�n de la restricci�n.
	 */
	public int obtenerCodigo()
	{
		return codigoRestriccion;
	}
	
	/**
	 * M�todo que devuelve la descripci�n de la restricci�n.
	 *
	 * @return descripcionRestriccion Valor descriptivo de la restricci�n.
	 */
	public String obtenerDescripcion()
	{
		return descripcionRestriccion;
	}
	
	/**
	 * M�todo que devuelve la cantidad disponible de la restricci�n.
	 *
	 * @return cantidadRestriccion Valor disponible de la restricci�n.
	 */
	public double obtenerCantidad()
	{
		return cantidadRestriccion;
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