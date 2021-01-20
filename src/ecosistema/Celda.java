/**
 * @(#)Celda.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/** 
 * Clase que representa una celda del espacio discreto de tres dimensiones. La
 * celda se representa en forma de coordenada del tipo (i, j, k), la cual es
 * única. La celda puede ser de dos tipos: válida o no válida.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Comparable
 * @see Cloneable
 */
public class Celda implements Comparable, Cloneable
{
	/** El valor que indica que la celda es de tipo válida. */
	public static final int VALIDA = 1;
	
	/** El valor que indica que la celda es de tipo no válida. */
	public static final int NO_VALIDA = 2;
	
	/** La componente i de la celda. */
	private int i;
	
	/** La componente j de la celda. */
	private int j;
	
	/** La componente k de la celda. */
	private int k;
	
	/** El tipo de celda. */
	private int tipo;
	
	/**
	 * Método constructor en donde se inicializan los atributos de la clase con
	 * los valores por defecto. La celda que se crea por defecto es una celda
	 * no válida.
	 */
	public Celda()
	{
		this(-1, -1, -1, NO_VALIDA);
	}
	
	/**
	 * Método constructor en donde se inicializan los atributos i, j, k y tipo
	 * de la clase con los valores recibidos como parámetros.
	 *
	 * @param i El valor para la componente i de la celda.
	 * @param j El valor para la componente j de la celda.
	 * @param k El valor para la componente k de la celda.
	 * @param tipo El tipo de celda.
	 */
	public Celda(int i, int j, int k, int tipo)
	{
		// Establecer las componentes i, j y k.
		establecerComponentes(i, j, k);
		
		// Establecer el tipo.
		establecerTipo(tipo);
	}
	
	/**
	 * Método constructor en donde se inicializan los atributos i, j y k de la
	 * clase con los valores recibidos como parámetros. El tipo es obtenido
	 * viendo si los componentes i, j y k están dentro del rango del espacio.
	 *
	 * @param i El valor para la componente i de la celda.
	 * @param j El valor para la componente j de la celda.
	 * @param k El valor para la componente k de la celda.
	 * @param espacio El espacio discreto de la dinámica.
	 */
	public Celda(int i, int j, int k, Espacio espacio)
	{
		// Establecer las componentes i, j y k.
		establecerComponentes(i, j, k);
		
		// Cuando estamos dentro del rango del espacio.
		if (0<=i && i<espacio.obtenerTamanioI() &&
			0<=j && j<espacio.obtenerTamanioJ() &&
			0<=k && k<espacio.obtenerTamanioK())
			establecerTipo(VALIDA);
		
		// Cuando estamos fuera del rango del espacio.
		else establecerTipo(NO_VALIDA);
	}
	
	/**
	 * Método que establece un valor a los atributos i, j y k de la celda.
	 *
	 * @param i El valor para el atributo i de la celda.
	 * @param j El valor para el atributo j de la celda.
	 * @param k El valor para el atributo k de la celda.
	 */
	public void establecerComponentes(int i, int j, int k)
	{
		establecerI(i);
		establecerJ(j);
		establecerK(k);
	}
	
	/**
	 * Método que establece un valor al atributo i de la celda.
	 *
	 * @param i El valor para el atributo i de la celda.
	 */
	public void establecerI(int i)
	{
		this.i = i;
	}
	
	/**
	 * Método que establece un valor al atributo j de la celda.
	 *
	 * @param j El valor para el atributo j de la celda.
	 */
	public void establecerJ(int j)
	{
		this.j = j;
	}
	
	/**
	 * Método que establece un valor al atributo k de la celda.
	 *
	 * @param k El valor para el atributo k de la celda.
	 */
	public void establecerK(int k)
	{
		this.k = k;
	}
	
	/**
	 * Método que establece un valor al atributo tipo de la celda.
	 *
	 * @param tipo El valor para el atributo tipo de la celda.
	 */
	public void establecerTipo(int tipo)
	{
		this.tipo = tipo;
	}
	
	/**
	 * Método que obtiene el valor del atributo i de la celda.
	 *
	 * @return i El valor del atributo i de la celda.
	 */
	public int obtenerI()
	{
		return i;
	}
	
	/**
	 * Método que obtiene el valor del atributo j de la celda.
	 *
	 * @return j El valor del atributo j de la celda.
	 */
	public int obtenerJ()
	{
		return j;
	}
	
	/**
	 * Método que obtiene el valor del atributo k de la celda.
	 *
	 * @return k El valor del atributo k de la celda.
	 */
	public int obtenerK()
	{
		return k;
	}
	
	/**
	 * Método que obtiene el valor del atributo tipo de la celda.
	 *
	 * @return tipo El valor del atributo tipo.
	 */
	public int obtenerTipo()
	{
		return tipo;
	}
	
	/**
	 * Método que entrega un string con las componentes i, j y k de la celda.
	 *
	 * @return string La celda compuesta como un string.
	 */
	public String toString()
	{
		return "["+i+"]["+j+"]["+k+"]";
	}
	
	/**
	 * Método que determina si dos celdas son iguales o distintas. Cuando las
	 * componentes i, j, y k son iguales se retorna el valor cero. En caso
	 * contrario, se retorna el valor uno.
	 *
	 * @param objeto El objeto con el cual se desea comparar.
	 *
	 * @return cero Cuando las celdas son iguales.
	 * @return uno Cuando las celdas son diferentes.
	 */
	public int compareTo(Object objeto)
	{
		Celda celda = (Celda) objeto;
		
		if (i == celda.obtenerI() &&
			j == celda.obtenerJ() &&
			k == celda.obtenerK())
			return 0;
		
		return 1;
	}
	
	/**
	 * Método que permite copiar una celda sin la necesidad de compartir memoria
	 * con la celda desde donde ha sido copiada. Es decir, se crea una nueva
	 * celda con las mismas propiedades que la celda que ha sido clonada y se
	 * retorna esta nueva celda.
	 *
	 * @return nueva La nueva celda que tiene espacio en memoria distinto que la
	 *               celda de la cual fue clonada, pero tiene los mismos valores
	 *               en todos sus atributos.
	 */
	public Object clone()
	{
		return new Celda(i, j, k, tipo);
	}
}