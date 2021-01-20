/**
 * @(#)TransformacionGrafica.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Clase que realiza las transformaciones gr�ficas del mundo real de dos
 * dimensiones (2D) a mundo visi�n de dos dimensiones (2D) y visceversa. Esta
 * clase sirve como interface entre el sistema del mundo real y el sistema del
 * mundo visi�n. Los ejes del sistema mundo real (2D) son X e Y. Los ejes del
 * sistema mundo visi�n son U y V.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public class TransformacionGrafica
{
	/** M�nimo valor en el eje X del sistema mundo visi�n. */
	private int uMin;
	
	/** M�ximo valor en el eje X del sistema mundo visi�n. */
	private int uMax;
	
	/**
	 * Diferencia entre el m�ximo y el m�nimo valor en el eje X del sistema
	 * mundo visi�n.
	 */
	private int uDel;
	
	/** El factor de multiplicaci�n usado en la transformaci�n de U. */
	private double uFac;
	
	/** M�nimo valor en el eje Y del sistema mundo visi�n. */
	private int vMin;
	
	/** M�ximo valor en el eje Y del sistema mundo visi�n. */
	private int vMax;
	
	/**
	 * Diferencia entre el m�ximo y el m�nimo valor en el eje Y del sistema
	 * mundo visi�n.
	 */
	private int vDel;
	
	/** El factor de multiplicaci�n usado en la transformaci�n de V. */
	private double vFac;
	
	/** M�nimo valor en el eje X del sistema mundo real. */
	private double xMin;
	
	/** M�ximo valor en el eje X del sistema mundo real. */
	private double xMax;
	
	/**
	 * Diferencia entre el m�ximo y el m�nimo valor en el eje X del sistema
	 * mundo real.
	 */
	private double xDel;
	
	/** El factor de multiplicaci�n usado en la transformaci�n de X. */
	private double xFac;
	
	/** M�nimo valor en el eje Y del sistema mundo real. */
	private double yMin;
	
	/** M�ximo valor en el eje Y del sistema mundo real. */
	private double yMax;
	
	/**
	 * Diferencia entre el m�ximo y el m�nimo valor en el eje Y del sistema
	 * mundo real.
	 */
	private double yDel;
	
	/** El factor de multiplicaci�n usado en la transformaci�n de Y. */
	private double yFac;
	
	/**
     * M�todo constructor que establece el tama�o del mundo visi�n y el mundo
     * real con los valores por defecto. Los valores por defecto corresponden al
     * valor cero.
     */
    public TransformacionGrafica()
	{
		this(0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0);
	}
	
	/**
     * M�todo constructor que establece el tama�o del mundo visi�n y del mundo
     * real con los valores recibidos en los par�metros del m�todo.
     *
     * @param uMin El m�nimo valor en el eje X del sistema mundo visi�n.
     * @param uMax El m�ximo valor en el eje X del sistema mundo visi�n.
     * @param vMin El m�nimo valor en el eje Y del sistema mundo visi�n.
     * @param vMax El m�ximo valor en el eje Y del sistema mundo visi�n.
     * @param xMin El m�nimo valor en el eje X del sistema mundo real.
     * @param xMax El m�ximo valor en el eje X del sistema mundo real.
     * @param yMin El m�nimo valor en el eje Y del sistema mundo real.
     * @param yMax El m�ximo valor en el eje Y del sistema mundo real.
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
	 * M�todo que establece las coordenadas del sistema mundo visi�n. Todas las
	 * variables en este m�todo son consideran en unidades de pixeles, las
	 * cuales corresponden al sistema mundo visi�n usado.
	 *
	 * @param uMin El m�nimo valor en el eje X del sistema mundo visi�n.
     * @param uMax El m�ximo valor en el eje X del sistema mundo visi�n.
     * @param vMin El m�nimo valor en el eje Y del sistema mundo visi�n.
     * @param vMax El m�ximo valor en el eje Y del sistema mundo visi�n.
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
	 * M�todo que establece las coordenadas del sistema del mundo real. Todas
	 * las variables en este m�todo son consideradas en unidades del mundo real,
	 * las cuales pueden corresponder a metros, grados, milla n�uticas, etc.
	 *
	 * @param xMin El m�nimo valor en el eje X del sistema mundo real.
     * @param xMax El m�ximo valor en el eje X del sistema mundo real.
     * @param yMin El m�nimo valor en el eje Y del sistema mundo real.
     * @param yMax El m�ximo valor en el eje Y del sistema mundo real.
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
	 * M�todo que establece los factores multiplicativos usados en las
	 * ecuaciones de transformaci�n de coordenadas.
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
	 * M�todo que obtiene el valor del m�nimo valor en el eje X del sistema
	 * mundo visi�n.
	 *
	 * @return uMin El m�nimo valor en el eje X del sistema mundo visi�n.
	 */
	public int obtenerUMin()
	{
		return uMin;
	}
	
	/**
	 * M�todo que obtiene el valor del m�ximo valor en el eje X del sistema
	 * mundo visi�n.
	 *
	 * @return uMax El m�ximo valor en el eje X del sistema mundo visi�n.
	 */
	public int obtenerUMax()
	{
		return uMax;
	}
	
	/**
	 * M�todo que obtiene el valor de la diferencia entre uMax y uMin.
	 *
	 * @return uDel La diferencia entre el m�ximo y el m�nimo valor en el eje X
	 *              del sistema mundo visi�n.
	 */
	public int obtenerUDel()
	{
		return uDel;
	}
	
	/**
	 * M�todo que obtiene el valor del factor de multiplicaci�n usado en la
	 * transformaci�n de U.
	 *
	 * @return uFac El factor de multiplicaci�n usado en la transformaci�n de U.
	 */
	public double obtenerUFac()
	{
		return uFac;
	}
	
	/**
	 * M�todo que obtiene el valor del m�nimo valor en el eje Y en el sistema
	 * mundo visi�n.
	 *
	 * @return vMin El m�nimo valor en el eje Y en el sistema mundo visi�n.
	 */
	public int obtenerVMin()
	{
		return vMin;
	}
	
	/**
	 * M�todo que obtiene el valor del m�ximo valor en el eje Y del sistema
	 * mundo visi�n.
	 *
	 * @return vMax El m�ximo valor en el eje Y del sistema mundo visi�n.
	 */
	public int obtenerVMax()
	{
		return vMax;
	}
	
	/**
	 * M�todo que obtiene el valor de la diferencia entre vMax y vMin.
	 *
	 * @return vDel La diferencia entre el m�ximo y el m�nimo valor en el eje Y
	 *              del sistema mundo visi�n.
	 */
	public int obtenerVDel()
	{
		return vDel;
	}
	
	/**
	 * M�todo que obtiene el valor del factor de multiplicaci�n usado en la
	 * transformaci�n de V.
	 *
	 * @return vFac El factor de multiplicaci�n usado en la transformaci�n de V.
	 */
	public double obtenerVFac()
	{
		return vFac;
	}
	
	/**
	 * M�todo que obtiene el valor del m�nimo valor en el eje X del sistema
	 * mundo real.
	 *
	 * @return xMin El m�nimo valor en el eje X del sistema mundo real.
	 */
	public double obtenerXMin()
	{
		return xMin;
	}
	
	/**
	 * M�todo que obtiene el valor del m�ximo valor en el eje X del sistema
	 * mundo real.
	 *
	 * @return xMax El m�ximo valor en el eje X del sistema mundo real.
	 */
	public double obtenerXMax()
	{
		return xMax;
	}
	
	/**
	 * M�todo que obtiene el valor de la diferencia entre xMax y xMin.
	 *
	 * @return xDel La diferencia entre el m�ximo y el m�nimo valor en el eje X
	 *              del sistema mundo real.
	 */
	public double obtenerXDel()
	{
		return xDel;
	}
	
	/**
	 * M�todo que obtiene el valor del factor de multiplicaci�n usado en la
	 * transformaci�n de X.
	 *
	 * @return xFac El factor de multiplicaci�n usado en la transformaci�n de X.
	 */
	public double obtenerXFac()
	{
		return xFac;
	}
	
	/**
	 * M�todo que obtiene el valor del m�nimo valor en el eje Y del sistema
	 * mundo real.
	 *
	 * @return yMin El m�nimo valor en el eje Y del sistema mundo real.
	 */
	public double obtenerYMin()
	{
		return yMin;
	}
	
	/**
	 * M�todo que obtiene el valor del m�ximo valor en el eje Y del sistema
	 * mundo real.
	 *
	 * @return yMax El m�ximo valor en el eje Y del sistema mundo real.
	 */
	public double obtenerYMax()
	{
		return yMax;
	}
	
	/**
	 * M�todo que obtiene el valor de la diferencia entre yMax y yMin.
	 *
	 * @return yDel La diferencia entre el m�ximo y el m�nimo valor en el eje Y
	 *              del sistema mundo real.
	 */
	public double obtenerYDel()
	{
		return yDel;
	}
	
	/**
	 * M�todo que obtiene el valor del factor de multiplicaci�n usado en la
	 * transformaci�n de Y.
	 *
	 * @return yFac El factor de multiplicaci�n usado en la transformaci�n de Y.
	 */
	public double obtenerYFac()
	{
		return yFac;
	}
	
	/**
	 * M�todo que transforma las coordenadas del mundo real en X a coordenadas
	 * del mundo visi�n en U.
	 *
	 * @param x El valor en el eje X del sistema mundo real.
	 *
	 * @return u El valor en el eje U del sistema mundo visi�n.
	 */
	public int obtenerU(double x)
	{
		return (int) ((x - xMin) * uFac + uMin);
	}
	
	/**
	 * M�todo que transforma las coordenadas del mundo real en Y a coordenadas
	 * del mundo visi�n en V.
	 *
	 * @param y El valor en el eje Y del sistema mundo real.
	 *
	 * @return v El valor en el eje V del sistema mundo visi�n.
	 */
	public int obtenerV(double y)
	{
		return (int) ((y - yMin) * vFac + vMin);
	}
	
	/**
	 * M�todo que transforma las coordenadas del sistema mundo visi�n en U a
	 * coordenadas del mundo real en X.
	 *
	 * @param u El valor en el eje U del sistema mundo visi�n.
	 *
	 * @return x El valor en el eje X del sistema mundo real.
	 */
	public double obtenerX(int u)
	{
		return (double) ((u - uMin) * xFac + xMin);
	}
	
	/**
	 * M�todo que transforma las coordenadas del sistema mundo visi�n en V a
	 * coordenadas del mundo real en Y.
	 *
	 * @param v El valor en el eje V del sistema mundo visi�n.
	 *
	 * @return y El valor en el eje Y del sistema mundo real.
	 */
	public double obtenerY(int v)
	{
		return (double) ((v - vMin) * yFac + yMin);
	}
	
	/**
	 * M�todo que entrega un string con todos los atributos de la clase.
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