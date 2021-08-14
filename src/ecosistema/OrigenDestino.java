/**
 * @(#)OrigenDestino.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa una estructura de dato del tipo par ordenado de celdas
 * origen-destino de un super-indiviuo. El nodo maneja basicamente dos datos:
 * la celda de origen del super-individuo y la celda de destino del
 * super-individuo.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public class OrigenDestino
{
	/** La celda de origen del super-individuo. */
	private Celda origen;
	
	/** La celda de destino del super-individuo. */
	private Celda destino;
	
	/**
	 * Método constructor que inicializa los atributos de la clase origen y
	 * destino con los valores recibidos como parámetros.
	 *
	 * @param origen La celda de origen del super-individuo.
	 * @param destino La celda de destino del super-individuo.
	 */
	public OrigenDestino(Celda origen, Celda destino)
	{
		establecerOrigen(origen);
		establecerDestino(destino);
	}
	
	/**
	 * Método que establece un valor al atributo origen.
	 *
	 * @param origen El valor para el atributo origen.
	 */
	public void establecerOrigen(Celda origen)
	{
		this.origen = origen;
	}
	
	/**
	 * Método que establece un valor al atributo destino.
	 *
	 * @param destino El valor para el atributo destino.
	 */
	public void establecerDestino(Celda destino)
	{
		this.destino = destino;
	}
	
	/**
	 * Método que retorna el valor del atributo origen.
	 *
	 * @return origen El valor del atributo origen.
	 */
	public Celda obtenerOrigen()
	{
		return origen;
	}
	
	/**
	 * Método que retorna el valor del atributo destino.
	 *
	 * @return destino El valor del atributo destino.
	 */
	public Celda obtenerDestino()
	{
		return destino;
	}
	
	/**
	 * Método que entrega un string con las celdas de origen y destino.
	 *
	 * @return string El nodo compuesto como un string.
	 */
	public String toString()
	{
		return "("+origen+"->"+destino+")";
	}
}