/**
 * @(#)TransformacionModelo.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que realiza las transformaciones gráficas del mundo real de tres
 * dimensiones (3D) continuo al mundo modelo de tres dimensiones (3D) discreto
 * y visceversa. Esta clase sirve como interface entre el sistema del mundo real
 * y el sistema del mundo modelo. Los ejes del sistema mundo real (3D) son X, Y
 * y Z. Los ejes del sistema mundo modelo son I, J y K.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public class TransformacionModelo
{
	/** Mínimo valor en el eje I del sistema mundo modelo. */
	private int iMin;
	
	/** Máximo valor en el eje I del sistema mundo modelo. */
	private int iMax;
	
	/**
	 * Diferencia entre el máximo y el mínimo valor en el eje I del sistema
	 * mundo modelo.
	 */
	private int iDel;
	
	/** El factor de multiplicación usado en la transformación de I. */
	private double iFac;
	
	/** Mínimo valor en el eje J del sistema mundo modelo. */
	private int jMin;
	
	/** Máximo valor en el eje J del sistema mundo modelo. */
	private int jMax;
	
	/**
	 * Diferencia entre el máximo y el mínimo valor en el eje J del sistema
	 * mundo modelo.
	 */
	private int jDel;
	
	/** El factor de multiplicación usado en la transformación de J. */
	private double jFac;
	
	/** Mínimo valor en el eje K del sistema mundo modelo. */
	private int kMin;
	
	/** Máximo valor en el eje K del sistema mundo modelo. */
	private int kMax;
	
	/**
	 * Diferencia entre el máximo y el mínimo valor en el eje K del sistema
	 * mundo modelo.
	 */
	private int kDel;
	
	/** El factor de multiplicación usado en la transformación de K. */
	private double kFac;
	
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
	
	/** Mínimo valor en el eje Z del sistema mundo real. */
	private double zMin;
	
	/** Máximo valor en el eje Z del sistema mundo real. */
	private double zMax;
	
	/**
	 * Diferencia entre el máximo y el mínimo valor en el eje Z del sistema
	 * mundo real.
	 */
	private double zDel;
	
	/** El factor de multiplicación usado en la transformación de Z. */
	private double zFac;
	
	/**
     * Método constructor que establece el tamaño del mundo modelo y el mundo
     * real con los valores por defecto. Los valores por defecto corresponden
     * a el valor cero.
     */
    public TransformacionModelo()
	{
		this(0, 0, 0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
	}
	
	/**
     * Método constructor que establece el tamaño del mundo modelo y del mundo
     * real con los valores recibidos en los parámetros del método.
     *
     * @param iMin El mínimo valor en el eje I del sistema mundo modelo.
     * @param iMax El máximo valor en el eje I del sistema mundo modelo.
     * @param jMin El mínimo valor en el eje J del sistema mundo modelo.
     * @param jMax El máximo valor en el eje J del sistema mundo modelo.
     * @param kMin El mínimo valor en el eje K del sistema mundo modelo.
     * @param kMax El máximo valor en el eje K del sistema mundo modelo.
     * @param xMin El mínimo valor en el eje X del sistema mundo real.
     * @param xMax El máximo valor en el eje X del sistema mundo real.
     * @param yMin El mínimo valor en el eje Y del sistema mundo real.
     * @param yMax El máximo valor en el eje Y del sistema mundo real.
     * @param zMin El mínimo valor en el eje Z del sistema mundo real.
     * @param zMax El máximo valor en el eje Z del sistema mundo real.
     */
	public TransformacionModelo(int iMin, int iMax,
								int jMin, int jMax,
								int kMin, int kMax,
								double xMin, double xMax,
								double yMin, double yMax,
								double zMin, double zMax)
	{
		establecerMundoModelo(iMin, iMax, jMin, jMax, kMin, kMax);
		establecerMundoReal(xMin, xMax, yMin, yMax, zMin, zMax);
		establecerFactores();
	}
	
	/**
	 * Método que establece las coordenadas del sistema mundo modelo. Todas las
	 * variables en este método son consideran en unidades de celdas, las cuales
	 * corresponden al sistema mundo modelo usado.
	 *
     * @param iMin El mínimo valor en el eje I del sistema mundo modelo.
     * @param iMax El máximo valor en el eje I del sistema mundo modelo.
     * @param jMin El mínimo valor en el eje J del sistema mundo modelo.
     * @param jMax El máximo valor en el eje J del sistema mundo modelo.
     * @param kMin El mínimo valor en el eje K del sistema mundo modelo.
     * @param kMax El máximo valor en el eje K del sistema mundo modelo.
     */
	public void establecerMundoModelo(int iMin, int iMax,
									  int jMin, int jMax,
									  int kMin, int kMax)
	{
		this.iMin = iMin;
		this.iMax = iMax;
		this.jMin = jMin;
		this.jMax = jMax;
		this.kMin = kMin;
		this.kMax = kMax;
		this.iDel = iMax - iMin;
		this.jDel = jMax - jMin;
		this.kDel = kMax - kMin;
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
     * @param zMin El mínimo valor en el eje Z del sistema mundo real.
     * @param zMax El máximo valor en el eje Z del sistema mundo real.
     */
	public void establecerMundoReal(double xMin, double xMax,
									double yMin, double yMax,
									double zMin, double zMax)
	{
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.zMin = zMin;
		this.zMax = zMax;
		this.xDel = xMax - xMin;
		this.yDel = yMax - yMin;
		this.zDel = zMax - zMin;
	}
	
	/**
	 * Método que establece los factores multiplicativos usados en las
	 * ecuaciones de transformación de coordenadas.
     */
	public void establecerFactores()
	{
		if (xDel != 0)
			iFac = iDel/xDel;
		else iFac = 0.0;
		
		if (yDel != 0)
			jFac = jDel/yDel;
		else jFac = 0.0;
		
		if (zDel != 0)
			kFac = kDel/zDel;
		else kFac = 0.0;
		
		if (iDel != 0)
			xFac = xDel/iDel;
		else xFac = 0.0;
		
		if (jDel != 0)
			yFac = yDel/jDel;
		else yFac = 0.0;
		
		if (kDel != 0)
			zFac = zDel/kDel;
		else zFac = 0.0;
	}
	
	/**
	 * Método que obtiene el valor del mínimo valor en el eje I del sistema
	 * mundo modelo.
	 *
	 * @return iMin El mínimo valor en el eje I del sistema mundo modelo.
	 */
	public int obtenerIMin()
	{
		return iMin;
	}
	
	/**
	 * Método que obtiene el valor del máximo valor en el eje I del sistema
	 * mundo modelo.
	 *
	 * @return iMax El máximo valor en el eje I del sistema mundo modelo.
	 */
	public int obtenerIMax()
	{
		return iMax;
	}
	
	/**
	 * Método que obtiene el valor de la diferencia entre iMax y iMin.
	 *
	 * @return iDel La diferencia entre el máximo y el mínimo valor en el eje I
	 *              del sistema mundo modelo.
	 */
	public int obtenerIDel()
	{
		return iDel;
	}
	
	/**
	 * Método que obtiene el valor del mínimo valor en el eje J del sistema
	 * mundo modelo.
	 *
	 * @return jMin El mínimo valor en el eje J del sistema mundo modelo.
	 */
	public int obtenerJMin()
	{
		return jMin;
	}
	
	/**
	 * Método que obtiene el valor del máximo valor en el eje J del sistema
	 * mundo modelo.
	 *
	 * @return jMax El máximo valor en el eje J del sistema mundo modelo.
	 */
	public int obtenerJMax()
	{
		return jMax;
	}
	
	/**
	 * Método que obtiene el valor de la diferencia entre jMax y jMin.
	 *
	 * @return jDel La diferencia entre el máximo y el mínimo valor en el eje J
	 *              del sistema mundo modelo.
	 */
	public int obtenerJDel()
	{
		return jDel;
	}
	
	/**
	 * Método que obtiene el valor del mínimo valor en el eje K del sistema
	 * mundo modelo.
	 *
	 * @return kMin El mínimo valor en el eje K del sistema mundo modelo.
	 */
	public int obtenerKMin()
	{
		return kMin;
	}
	
	/**
	 * Método que obtiene el valor del máximo valor en el eje K del sistema
	 * mundo modelo.
	 *
	 * @return kMax El máximo valor en el eje K del sistema mundo modelo.
	 */
	public int obtenerKMax()
	{
		return kMax;
	}
	
	/**
	 * Método que obtiene el valor de la diferencia entre kMax y kMin.
	 *
	 * @return kDel La diferencia entre el máximo y el mínimo valor en el eje K
	 *              del sistema mundo modelo.
	 */
	public int obtenerKDel()
	{
		return kDel;
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
	 * Método que obtiene el valor del mínimo valor en el eje Z del sistema
	 * mundo real.
	 *
	 * @return zMin El mínimo valor en el eje Z del sistema mundo real.
	 */
	public double obtenerZMin()
	{
		return zMin;
	}
	
	/**
	 * Método que obtiene el valor del máximo valor en el eje Z del sistema
	 * mundo real.
	 *
	 * @return zMax El máximo valor en el eje Z del sistema mundo real.
	 */
	public double obtenerZMax()
	{
		return zMax;
	}
	
	/**
	 * Método que obtiene el valor de la diferencia entre zMax y zMin.
	 *
	 * @return zDel La diferencia entre el máximo y el mínimo valor en el eje Z
	 *              del sistema mundo real.
	 */
	public double obtenerZDel()
	{
		return zDel;
	}
	
	/**
	 * Método que transforma las coordenadas del mundo real en X a coordenadas
	 * del mundo modelo en I.
	 *
	 * @param x El valor en el eje X del sistema mundo real.
	 *
	 * @return i El valor en el eje I del sistema mundo modelo.
	 */
	public int obtenerI(double x)
	{
		return (int) ((x - xMin) * iFac + iMin);
	}
	
	/**
	 * Método que transforma las coordenadas del mundo real en Y a coordenadas
	 * del mundo modelo en J.
	 *
	 * @param y El valor en el eje Y del sistema mundo real.
	 *
	 * @return j El valor en el eje J del sistema mundo modelo.
	 */
	public int obtenerJ(double y)
	{
		return (int) ((y - yMin) * jFac + jMin);
	}
	
	/**
	 * Método que transforma las coordenadas del mundo real en Z a coordenadas
	 * del mundo modelo en K.
	 *
	 * @param z El valor en el eje Z del sistema mundo real.
	 *
	 * @return k El valor en el eje K del sistema mundo modelo.
	 */
	public int obtenerK(double z)
	{
		return (int) ((z - zMin) * kFac + kMin);
	}
	
	/**
	 * Método que transforma las coordenadas del sistema mundo modelo en I a
	 * coordenadas del mundo real en X.
	 *
	 * @param i El valor en el eje I del sistema mundo modelo.
	 *
	 * @return x El valor en el eje X del sistema mundo real.
	 */
	public double obtenerX(int i)
	{
		return (double) ((i - iMin) * xFac + xMin);
	}
	
	/**
	 * Método que transforma las coordenadas del sistema mundo modelo en J a
	 * coordenadas del mundo real en Y.
	 *
	 * @param j El valor en el eje J del sistema mundo modelo.
	 *
	 * @return y El valor en el eje Y del sistema mundo real.
	 */
	public double obtenerY(int j)
	{
		return (double) ((j - jMin) * yFac + yMin);
	}
	
	/**
	 * Método que transforma las coordenadas del sistema mundo modelo en K a
	 * coordenadas del mundo real en Z.
	 *
	 * @param k El valor en el eje K del sistema mundo modelo.
	 *
	 * @return z El valor en el eje Z del sistema mundo real.
	 */
	public double obtenerZ(int k)
	{
		return (double) ((k - kMin) * zFac + zMin);
	}
	
	/**
	 * Método que entrega un string con todos los atributos de la clase.
	 *
	 * @return string Los atributos de la clase compuestos como un string.
	 */
	public String toString()
	{
		String i = "iMin="+iMin+" iMax="+iMax+" iDel="+iDel+" iFac="+iFac+"\n";
		String j = "jMin="+jMin+" jMax="+jMax+" jDel="+jDel+" jFac="+jFac+"\n";
		String k = "kMin="+kMin+" kMax="+kMax+" kDel="+kDel+" kFac="+kFac+"\n";
		String x = "xMin="+xMin+" xMax="+xMax+" xDel="+xDel+" xFac="+xFac+"\n";
		String y = "yMin="+yMin+" yMax="+yMax+" yDel="+yDel+" yFac="+yFac+"\n";
		String z = "zMin="+zMin+" zMax="+zMax+" zDel="+zDel+" zFac="+zFac;
		
		return i + j + k + x + y + z;
	}
}