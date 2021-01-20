/**
 * @(#)TransformacionGrafica.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que realiza las transformaciones gráficas del mundo real de dos
 * dimensiones (2D) a mundo visión de dos dimensiones (2D) y visceversa. Esta
 * clase sirve como interface entre el sistema del mundo real y el sistema del
 * mundo visión. Los ejes del sistema mundo real (2D) son X e Y. Los ejes del
 * sistema mundo visión son U y V.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public class TransformacionGrafica
{
	/** Mínimo valor en el eje X del sistema mundo visión. */
	private int uMin;
	
	/** Máximo valor en el eje X del sistema mundo visión. */
	private int uMax;
	
	/**
	 * Diferencia entre el máximo y el mínimo valor en el eje X del sistema
	 * mundo visión.
	 */
	private int uDel;
	
	/** El factor de multiplicación usado en la transformación de U. */
	private double uFac;
	
	/** Mínimo valor en el eje Y del sistema mundo visión. */
	private int vMin;
	
	/** Máximo valor en el eje Y del sistema mundo visión. */
	private int vMax;
	
	/**
	 * Diferencia entre el máximo y el mínimo valor en el eje Y del sistema
	 * mundo visión.
	 */
	private int vDel;
	
	/** El factor de multiplicación usado en la transformación de V. */
	private double vFac;
	
	/** Mínimo valor en el eje X del sistema mundo real. */
	private double xMin;
	
	/** Máximo valor en el eje X del sistema mundo real. */
	private double xMax;
	
	/**
	 * Diferencia entre el máximo y el mínimo valor en el eje X del sistema
	 * mundo real.
	 */
	private double xDel;
	
	/** El factor de multiplicación usado en la transformación de X. */
	private double xFac;
	
	/** Mínimo valor en el eje Y del sistema mundo real. */
	private double yMin;
	
	/** Máximo valor en el eje Y del sistema mundo real. */
	private double yMax;
	
	/**
	 * Diferencia entre el máximo y el mínimo valor en el eje Y del sistema
	 * mundo real.
	 */
	private double yDel;
	
	/** El factor de multiplicación usado en la transformación de Y. */
	private double yFac;
	
	/**
     * Método constructor que establece el tamaño del mundo visión y el mundo
     * real con los valores por defecto. Los valores por defecto corresponden al
     * valor cero.
     */
    public TransformacionGrafica()
	{
		this(0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0);
	}
	
	/**
     * Método constructor que establece el tamaño del mundo visión y del mundo
     * real con los valores recibidos en los parámetros del método.
     *
     * @param uMin El mínimo valor en el eje X del sistema mundo visión.
     * @param uMax El máximo valor en el eje X del sistema mundo visión.
     * @param vMin El mínimo valor en el eje Y del sistema mundo visión.
     * @param vMax El máximo valor en el eje Y del sistema mundo visión.
     * @param xMin El mínimo valor en el eje X del sistema mundo real.
     * @param xMax El máximo valor en el eje X del sistema mundo real.
     * @param yMin El mínimo valor en el eje Y del sistema mundo real.
     * @param yMax El máximo valor en el eje Y del sistema mundo real.
     */
    public TransformacionGrafica(int uMin, int uMax,
    							 int vMin, int vMax,
    							 double xMin, double xMax,
    							 double yMin, double yMax)
	{
		establecerMundoVision(uMin, uMax, vMin, vMax);
		establecerMundoReal(xMin, xMax, yMin, yMax);
		establecerFactores();
	}
	
	/**
	 * Método que establece las coordenadas del sistema mundo visión. Todas las
	 * variables en este método son consideran en unidades de pixeles, las
	 * cuales corresponden al sistema mundo visión usado.
	 *
	 * @param uMin El mínimo valor en el eje X del sistema mundo visión.
     * @param uMax El máximo valor en el eje X del sistema mundo visión.
     * @param vMin El mínimo valor en el eje Y del sistema mundo visión.
     * @param vMax El máximo valor en el eje Y del sistema mundo visión.
     */
	public void establecerMundoVision(int uMin, int uMax, int vMin, int vMax)
	{
		this.uMin = uMin;
		this.uMax = uMax;
		this.vMin = vMin;
		this.vMax = vMax;
		this.uDel = uMax - uMin;
		this.vDel = vMax - vMin;
	}
	
	/**
	 * Método que establece las coordenadas del sistema del mundo real. Todas
	 * las variables en este método son consideradas en unidades del mundo real,
	 * las cuales pueden corresponder a metros, grados, milla náuticas, etc.
	 *
	 * @param xMin El mínimo valor en el eje X del sistema mundo real.
     * @param xMax El máximo valor en el eje X del sistema mundo real.
     * @param yMin El mínimo valor en el eje Y del sistema mundo real.
     * @param yMax El máximo valor en el eje Y del sistema mundo real.
     */
	public void establecerMundoReal(double xMin, double xMax,
									double yMin, double yMax)
	{
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.xDel = xMax - xMin;
		this.yDel = yMax - yMin;
	}
	
	/**
	 * Método que establece los factores multiplicativos usados en las
	 * ecuaciones de transformación de coordenadas.
     */
	public void establecerFactores()
	{
		if (xDel != 0)
			uFac = uDel/xDel;
		else uFac = 0.0;
		
		if (yDel != 0)
			vFac = vDel/yDel;
		else vFac = 0.0;
		
		if (uDel != 0)
			xFac = xDel/uDel;
		else xFac = 0.0;
		
		if (vDel != 0)
			yFac = yDel/vDel;
		else yFac = 0.0;
	}
	
	/**
	 * Método que obtiene el valor del mínimo valor en el eje X del sistema
	 * mundo visión.
	 *
	 * @return uMin El mínimo valor en el eje X del sistema mundo visión.
	 */
	public int obtenerUMin()
	{
		return uMin;
	}
	
	/**
	 * Método que obtiene el valor del máximo valor en el eje X del sistema
	 * mundo visión.
	 *
	 * @return uMax El máximo valor en el eje X del sistema mundo visión.
	 */
	public int obtenerUMax()
	{
		return uMax;
	}
	
	/**
	 * Método que obtiene el valor de la diferencia entre uMax y uMin.
	 *
	 * @return uDel La diferencia entre el máximo y el mínimo valor en el eje X
	 *              del sistema mundo visión.
	 */
	public int obtenerUDel()
	{
		return uDel;
	}
	
	/**
	 * Método que obtiene el valor del factor de multiplicación usado en la
	 * transformación de U.
	 *
	 * @return uFac El factor de multiplicación usado en la transformación de U.
	 */
	public double obtenerUFac()
	{
		return uFac;
	}
	
	/**
	 * Método que obtiene el valor del mínimo valor en el eje Y en el sistema
	 * mundo visión.
	 *
	 * @return vMin El mínimo valor en el eje Y en el sistema mundo visión.
	 */
	public int obtenerVMin()
	{
		return vMin;
	}
	
	/**
	 * Método que obtiene el valor del máximo valor en el eje Y del sistema
	 * mundo visión.
	 *
	 * @return vMax El máximo valor en el eje Y del sistema mundo visión.
	 */
	public int obtenerVMax()
	{
		return vMax;
	}
	
	/**
	 * Método que obtiene el valor de la diferencia entre vMax y vMin.
	 *
	 * @return vDel La diferencia entre el máximo y el mínimo valor en el eje Y
	 *              del sistema mundo visión.
	 */
	public int obtenerVDel()
	{
		return vDel;
	}
	
	/**
	 * Método que obtiene el valor del factor de multiplicación usado en la
	 * transformación de V.
	 *
	 * @return vFac El factor de multiplicación usado en la transformación de V.
	 */
	public double obtenerVFac()
	{
		return vFac;
	}
	
	/**
	 * Método que obtiene el valor del mínimo valor en el eje X del sistema
	 * mundo real.
	 *
	 * @return xMin El mínimo valor en el eje X del sistema mundo real.
	 */
	public double obtenerXMin()
	{
		return xMin;
	}
	
	/**
	 * Método que obtiene el valor del máximo valor en el eje X del sistema
	 * mundo real.
	 *
	 * @return xMax El máximo valor en el eje X del sistema mundo real.
	 */
	public double obtenerXMax()
	{
		return xMax;
	}
	
	/**
	 * Método que obtiene el valor de la diferencia entre xMax y xMin.
	 *
	 * @return xDel La diferencia entre el máximo y el mínimo valor en el eje X
	 *              del sistema mundo real.
	 */
	public double obtenerXDel()
	{
		return xDel;
	}
	
	/**
	 * Método que obtiene el valor del factor de multiplicación usado en la
	 * transformación de X.
	 *
	 * @return xFac El factor de multiplicación usado en la transformación de X.
	 */
	public double obtenerXFac()
	{
		return xFac;
	}
	
	/**
	 * Método que obtiene el valor del mínimo valor en el eje Y del sistema
	 * mundo real.
	 *
	 * @return yMin El mínimo valor en el eje Y del sistema mundo real.
	 */
	public double obtenerYMin()
	{
		return yMin;
	}
	
	/**
	 * Método que obtiene el valor del máximo valor en el eje Y del sistema
	 * mundo real.
	 *
	 * @return yMax El máximo valor en el eje Y del sistema mundo real.
	 */
	public double obtenerYMax()
	{
		return yMax;
	}
	
	/**
	 * Método que obtiene el valor de la diferencia entre yMax y yMin.
	 *
	 * @return yDel La diferencia entre el máximo y el mínimo valor en el eje Y
	 *              del sistema mundo real.
	 */
	public double obtenerYDel()
	{
		return yDel;
	}
	
	/**
	 * Método que obtiene el valor del factor de multiplicación usado en la
	 * transformación de Y.
	 *
	 * @return yFac El factor de multiplicación usado en la transformación de Y.
	 */
	public double obtenerYFac()
	{
		return yFac;
	}
	
	/**
	 * Método que transforma las coordenadas del mundo real en X a coordenadas
	 * del mundo visión en U.
	 *
	 * @param x El valor en el eje X del sistema mundo real.
	 *
	 * @return u El valor en el eje U del sistema mundo visión.
	 */
	public int obtenerU(double x)
	{
		return (int) ((x - xMin) * uFac + uMin);
	}
	
	/**
	 * Método que transforma las coordenadas del mundo real en Y a coordenadas
	 * del mundo visión en V.
	 *
	 * @param y El valor en el eje Y del sistema mundo real.
	 *
	 * @return v El valor en el eje V del sistema mundo visión.
	 */
	public int obtenerV(double y)
	{
		return (int) ((y - yMin) * vFac + vMin);
	}
	
	/**
	 * Método que transforma las coordenadas del sistema mundo visión en U a
	 * coordenadas del mundo real en X.
	 *
	 * @param u El valor en el eje U del sistema mundo visión.
	 *
	 * @return x El valor en el eje X del sistema mundo real.
	 */
	public double obtenerX(int u)
	{
		return (double) ((u - uMin) * xFac + xMin);
	}
	
	/**
	 * Método que transforma las coordenadas del sistema mundo visión en V a
	 * coordenadas del mundo real en Y.
	 *
	 * @param v El valor en el eje V del sistema mundo visión.
	 *
	 * @return y El valor en el eje Y del sistema mundo real.
	 */
	public double obtenerY(int v)
	{
		return (double) ((v - vMin) * yFac + yMin);
	}
	
	/**
	 * Método que entrega un string con todos los atributos de la clase.
	 *
	 * @return string Los atributos de la clase compuestos como un string.
	 */
	public String toString()
	{
		String u = "uMin="+uMin+" uMax="+uMax+" uDel="+uDel+" uFac="+uFac+"\n";
		String v = "vMin="+vMin+" vMax="+vMax+" vDel="+vDel+" vFac="+vFac+"\n";
		String x = "xMin="+xMin+" xMax="+xMax+" xDel="+xDel+" xFac="+xFac+"\n";
		String y = "yMin="+yMin+" yMax="+yMax+" yDel="+yDel+" yFac="+yFac;
		
		return u + v + x + y;
	}
}