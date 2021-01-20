/**
 * @(#)TransformacionModelo.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Clase que realiza las transformaciones gr�ficas del mundo real de tres
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
	/** M�nimo valor en el eje I del sistema mundo modelo. */
	private int iMin;
	
	/** M�ximo valor en el eje I del sistema mundo modelo. */
	private int iMax;
	
	/**
	 * Diferencia entre el m�ximo y el m�nimo valor en el eje I del sistema
	 * mundo modelo.
	 */
	private int iDel;
	
	/** El factor de multiplicaci�n usado en la transformaci�n de I. */
	private double iFac;
	
	/** M�nimo valor en el eje J del sistema mundo modelo. */
	private int jMin;
	
	/** M�ximo valor en el eje J del sistema mundo modelo. */
	private int jMax;
	
	/**
	 * Diferencia entre el m�ximo y el m�nimo valor en el eje J del sistema
	 * mundo modelo.
	 */
	private int jDel;
	
	/** El factor de multiplicaci�n usado en la transformaci�n de J. */
	private double jFac;
	
	/** M�nimo valor en el eje K del sistema mundo modelo. */
	private int kMin;
	
	/** M�ximo valor en el eje K del sistema mundo modelo. */
	private int kMax;
	
	/**
	 * Diferencia entre el m�ximo y el m�nimo valor en el eje K del sistema
	 * mundo modelo.
	 */
	private int kDel;
	
	/** El factor de multiplicaci�n usado en la transformaci�n de K. */
	private double kFac;
	
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
	
	/** M�nimo valor en el eje Z del sistema mundo real. */
	private double zMin;
	
	/** M�ximo valor en el eje Z del sistema mundo real. */
	private double zMax;
	
	/**
	 * Diferencia entre el m�ximo y el m�nimo valor en el eje Z del sistema
	 * mundo real.
	 */
	private double zDel;
	
	/** El factor de multiplicaci�n usado en la transformaci�n de Z. */
	private double zFac;
	
	/**
     * M�todo constructor que establece el tama�o del mundo modelo y el mundo
     * real con los valores por defecto. Los valores por defecto corresponden
     * a el valor cero.
     */
    public TransformacionModelo()
	{
		this(0, 0, 0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
	}
	
	/**
     * M�todo constructor que establece el tama�o del mundo modelo y del mundo
     * real con los valores recibidos en los par�metros del m�todo.
     *
     * @param iMin El m�nimo valor en el eje I del sistema mundo modelo.
     * @param iMax El m�ximo valor en el eje I del sistema mundo modelo.
     * @param jMin El m�nimo valor en el eje J del sistema mundo modelo.
     * @param jMax El m�ximo valor en el eje J del sistema mundo modelo.
     * @param kMin El m�nimo valor en el eje K del sistema mundo modelo.
     * @param kMax El m�ximo valor en el eje K del sistema mundo modelo.
     * @param xMin El m�nimo valor en el eje X del sistema mundo real.
     * @param xMax El m�ximo valor en el eje X del sistema mundo real.
     * @param yMin El m�nimo valor en el eje Y del sistema mundo real.
     * @param yMax El m�ximo valor en el eje Y del sistema mundo real.
     * @param zMin El m�nimo valor en el eje Z del sistema mundo real.
     * @param zMax El m�ximo valor en el eje Z del sistema mundo real.
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
	 * M�todo que establece las coordenadas del sistema mundo modelo. Todas las
	 * variables en este m�todo son consideran en unidades de celdas, las cuales
	 * corresponden al sistema mundo modelo usado.
	 *
     * @param iMin El m�nimo valor en el eje I del sistema mundo modelo.
     * @param iMax El m�ximo valor en el eje I del sistema mundo modelo.
     * @param jMin El m�nimo valor en el eje J del sistema mundo modelo.
     * @param jMax El m�ximo valor en el eje J del sistema mundo modelo.
     * @param kMin El m�nimo valor en el eje K del sistema mundo modelo.
     * @param kMax El m�ximo valor en el eje K del sistema mundo modelo.
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
	 * M�todo que establece las coordenadas del sistema del mundo real. Todas
	 * las variables en este m�todo son consideradas en unidades del mundo real,
	 * las cuales pueden corresponder a metros, grados, milla n�uticas, etc.
	 *
	 * @param xMin El m�nimo valor en el eje X del sistema mundo real.
     * @param xMax El m�ximo valor en el eje X del sistema mundo real.
     * @param yMin El m�nimo valor en el eje Y del sistema mundo real.
     * @param yMax El m�ximo valor en el eje Y del sistema mundo real.
     * @param zMin El m�nimo valor en el eje Z del sistema mundo real.
     * @param zMax El m�ximo valor en el eje Z del sistema mundo real.
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
	 * M�todo que establece los factores multiplicativos usados en las
	 * ecuaciones de transformaci�n de coordenadas.
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
	 * M�todo que obtiene el valor del m�nimo valor en el eje I del sistema
	 * mundo modelo.
	 *
	 * @return iMin El m�nimo valor en el eje I del sistema mundo modelo.
	 */
	public int obtenerIMin()
	{
		return iMin;
	}
	
	/**
	 * M�todo que obtiene el valor del m�ximo valor en el eje I del sistema
	 * mundo modelo.
	 *
	 * @return iMax El m�ximo valor en el eje I del sistema mundo modelo.
	 */
	public int obtenerIMax()
	{
		return iMax;
	}
	
	/**
	 * M�todo que obtiene el valor de la diferencia entre iMax y iMin.
	 *
	 * @return iDel La diferencia entre el m�ximo y el m�nimo valor en el eje I
	 *              del sistema mundo modelo.
	 */
	public int obtenerIDel()
	{
		return iDel;
	}
	
	/**
	 * M�todo que obtiene el valor del m�nimo valor en el eje J del sistema
	 * mundo modelo.
	 *
	 * @return jMin El m�nimo valor en el eje J del sistema mundo modelo.
	 */
	public int obtenerJMin()
	{
		return jMin;
	}
	
	/**
	 * M�todo que obtiene el valor del m�ximo valor en el eje J del sistema
	 * mundo modelo.
	 *
	 * @return jMax El m�ximo valor en el eje J del sistema mundo modelo.
	 */
	public int obtenerJMax()
	{
		return jMax;
	}
	
	/**
	 * M�todo que obtiene el valor de la diferencia entre jMax y jMin.
	 *
	 * @return jDel La diferencia entre el m�ximo y el m�nimo valor en el eje J
	 *              del sistema mundo modelo.
	 */
	public int obtenerJDel()
	{
		return jDel;
	}
	
	/**
	 * M�todo que obtiene el valor del m�nimo valor en el eje K del sistema
	 * mundo modelo.
	 *
	 * @return kMin El m�nimo valor en el eje K del sistema mundo modelo.
	 */
	public int obtenerKMin()
	{
		return kMin;
	}
	
	/**
	 * M�todo que obtiene el valor del m�ximo valor en el eje K del sistema
	 * mundo modelo.
	 *
	 * @return kMax El m�ximo valor en el eje K del sistema mundo modelo.
	 */
	public int obtenerKMax()
	{
		return kMax;
	}
	
	/**
	 * M�todo que obtiene el valor de la diferencia entre kMax y kMin.
	 *
	 * @return kDel La diferencia entre el m�ximo y el m�nimo valor en el eje K
	 *              del sistema mundo modelo.
	 */
	public int obtenerKDel()
	{
		return kDel;
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
	 * M�todo que obtiene el valor del m�nimo valor en el eje Z del sistema
	 * mundo real.
	 *
	 * @return zMin El m�nimo valor en el eje Z del sistema mundo real.
	 */
	public double obtenerZMin()
	{
		return zMin;
	}
	
	/**
	 * M�todo que obtiene el valor del m�ximo valor en el eje Z del sistema
	 * mundo real.
	 *
	 * @return zMax El m�ximo valor en el eje Z del sistema mundo real.
	 */
	public double obtenerZMax()
	{
		return zMax;
	}
	
	/**
	 * M�todo que obtiene el valor de la diferencia entre zMax y zMin.
	 *
	 * @return zDel La diferencia entre el m�ximo y el m�nimo valor en el eje Z
	 *              del sistema mundo real.
	 */
	public double obtenerZDel()
	{
		return zDel;
	}
	
	/**
	 * M�todo que transforma las coordenadas del mundo real en X a coordenadas
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
	 * M�todo que transforma las coordenadas del mundo real en Y a coordenadas
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
	 * M�todo que transforma las coordenadas del mundo real en Z a coordenadas
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
	 * M�todo que transforma las coordenadas del sistema mundo modelo en I a
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
	 * M�todo que transforma las coordenadas del sistema mundo modelo en J a
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
	 * M�todo que transforma las coordenadas del sistema mundo modelo en K a
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
	 * M�todo que entrega un string con todos los atributos de la clase.
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