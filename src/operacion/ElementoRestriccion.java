/**
 * @(#)ElementoRestriccion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */
 
/**
 * Clase que define la estructura que tendrá un elemento de las condiciones de 
 * restricciones de oferta, demanda y medios de transporte dentro de un Vector.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 */
public class ElementoRestriccion
{
	/** Código de restricción en la base de datos. */
	private int codigoRestriccion;
	
	/** Descripción de la restricción. */
	private String descripcionRestriccion;
	
	/** Cantidad disponible. */
	private double cantidadRestriccion;
	
	/** Indica la coordenada geográfica. */
	private Coordenada coordenada;
	
	/**
	 * Método constructor que inicializa los elementos de la clase.
	 *
	 * @param codigo Código de restricción en la base de datos.
	 * @param descripcion Descripción de la restricción.
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
	 * Método que permite establecer el código de este elemento.
	 *
	 * @param codigo Nuevo valor de codigo.
	 */
	public void establecerCodigo(int codigo)
	{
		this.codigoRestriccion = codigo;
	}
	
	/**
	 * Método que permite establecer la descripción de este elemento.
	 * 
	 * @param descripcion Nuevo valor de descripción.
	 */
	public void establecerDescripcion(String descripcion)
	{
		this.descripcionRestriccion = descripcion;
	}
	
	/**
	 * Método que permite establecer la cantidad de este elemento.
	 *
	 * @param cantidad Nuevo valor de la cantidad.
	 */
	public void establecerCantidad(double cantidad)
	{
		this.cantidadRestriccion = cantidad;
	}
	
	/**
	 * Método que permite establecer la coordenada geográfica.
	 *
	 * @param coordenada Coordenada geográfica del elemento.
	 */
	public void establecerCoordenada(Coordenada coordenada)
	{
		this.coordenada = coordenada;
	}
	
	/**
	 * Método que devuelve el código de la restricción.
	 *
	 * @return codigoRestriccion Valor de identificación de la restricción.
	 */
	public int obtenerCodigo()
	{
		return codigoRestriccion;
	}
	
	/**
	 * Método que devuelve la descripción de la restricción.
	 *
	 * @return descripcionRestriccion Valor descriptivo de la restricción.
	 */
	public String obtenerDescripcion()
	{
		return descripcionRestriccion;
	}
	
	/**
	 * Método que devuelve la cantidad disponible de la restricción.
	 *
	 * @return cantidadRestriccion Valor disponible de la restricción.
	 */
	public double obtenerCantidad()
	{
		return cantidadRestriccion;
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