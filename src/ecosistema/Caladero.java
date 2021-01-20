/**
 * @(#)Caladero.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa un caladero o foco de abundancia de un recurso pesquero.
 * Esta clase considera que un caladero tiene una posición geográfica en forma
 * de cuadrado, es decir, tiene una coordenda inicial y una coordenada final, y
 * una cantidad de biomasa del recurso del cual se va a extraer.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Coordenada
 * @see Comparable
 */
public class Caladero implements Comparable
{
	/** El tipo de caladero. */
	private int tipo;
	
	/** La coordenada inicial del caladero. */
	private Coordenada coordenadaInicial;
	
	/** La coordenada final del caladero. */
	private Coordenada coordenadaFinal;
	
	/** La biomasa (toneladas) de recurso en el caladero. */
	private double biomasa;
	
	/**
	 * Método constructor en donde se inicializan los valores de la coordenada
	 * inicial, coordenada final y biomasa con los parámetros recibidos.
	 *
	 * @param coordenadaInicial El valor para el atributo coordenadaInicial.
	 * @param coordenadaFinal El valor para el atributo coordenadaFinal.
	 * @param biomasa El valor para el atributo biomasa.
	 */
	public Caladero(Coordenada coordenadaInicial,
					Coordenada coordenadaFinal,
					double biomasa)
	{
		// Cuando la coordenada inicial está dentro de las 5 millas nauticas.
		if (Mapa.obtenerDistanciaContinente(coordenadaInicial) <=
			Servicio.transformarMillaNauticaKilometro(5))
			this.tipo = TipoCaladero.ARTESANAL;
		else this.tipo = TipoCaladero.INDUSTRIAL;
		
		// Establecer los valores de los atributos.
		this.coordenadaInicial = coordenadaInicial;
		this.coordenadaFinal = coordenadaFinal;
		this.biomasa = biomasa;
	}
	
	/**
	 * Método que obtiene el valor del atributo tipo.
	 *
	 * @return tipo El valor del atributo tipo.
	 */
	public int obtenerTipo()
	{
		return tipo;
	}
	
	/**
	 * Método que obtiene el valor al atributo coordenadaInicial.
	 *
	 * @return coordenadaInicial El valor del atributo coordenadaInicial.
	 */
	public Coordenada obtenerCoordenadaInicial()
	{
		return coordenadaInicial;
	}
	
	/**
	 * Método que obtiene el valor del atributo coordenadaFinal.
	 *
	 * @return coordenadaFinal El valor del atributo coordenadaFinal.
	 */
	public Coordenada obtenerCoordenadaFinal()
	{
		return coordenadaFinal;
	}
	
	/**
	 * Método que obtiene el valor del atributo biomasa.
	 *
	 * @return biomasa El valor del atributo biomasa.
	 */
	public double obtenerBiomasa()
	{
		return biomasa;
	}
	
	/**
	 * Método que entrega un string con la coordenada inicial, la coordenada
	 * final y la biomasa a extraer.
	 *
	 * @return string El caladero compuesto como un string.
	 */
	public String toString()
	{
		return coordenadaInicial+"-"+coordenadaFinal+": "+biomasa;
	}
	
	/**
	 * Método que determina si dos caladeros son iguales o distintos. Cuando la
	 * coordenada inicial, coordenada final y biomasa son iguales, entonces se
	 * retorna el valor cero. En caso contrario, se retorna el valor uno.
	 *
	 * @param objeto El objeto con el cual se desea comparar.
	 *
	 * @return cero Cuando los caladeros son iguales.
	 * @return uno Cuando los caladeros son diferentes.
	 */
	public int compareTo(Object objeto)
	{
		Caladero caladero = (Caladero) objeto;
		
		if ((tipo == caladero.obtenerTipo()) &&
			(coordenadaInicial.compareTo(caladero.obtenerCoordenadaInicial()) == 0) &&
			(coordenadaFinal.compareTo(caladero.obtenerCoordenadaFinal()) == 0) &&
			(biomasa == caladero.obtenerBiomasa()))
			return 0;
		
		return 1;
	}
}