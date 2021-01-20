/**
 * @(#)IndividuoTransporte.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.text.DecimalFormat;
 
/**
 * Clase que describe la estructura de un individuo dentro de una poblaci�n. La
 * estructura representa una soluci�n factible al problema de transporte.
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
 * @see DecimalFormat
 */
public class IndividuoTransporte
{
	/** 
	 * Estructura de un cromosoma en el AG de transporte. Matriz de valores
	 * reales que representa una posible soluci�n al problema de transporte.
	 */
	private double[][][] matrizSolucion;
	
	/** Valor que indica la adecuaci�n del individuo al entorno. */
	private double valorEvaluacion;
	
	/** Formato de salida para n�meros decimales. */
	private DecimalFormat formatoDecimal;
	
	/** 
	 * M�todo constructor que inicializa los valores por defecto de la clase.
	 */
	public IndividuoTransporte()
	{
		// Matriz de valores reales de rango m*n*l
		matrizSolucion = new double[ConfiguracionAGT.m]
								   [ConfiguracionAGT.n]
								   [ConfiguracionAGT.l];
		
		// Valor de evaluaci�n de la soluci�n
		valorEvaluacion = 0.0;
	}
	
	/** 
	 * M�todo que fija el valor Xijk de la matriz soluci�n.
	 *
	 * @param i Asigna valor a la iesima celda de oferta.
	 * @param j Asigna valor a la jesima celda de demanda.
	 * @param k Asigna valor a la kesima celda de capacidad de transporte.
	 */
	public void establecerValorX(int i, int j, int k, double valor)
	{
		matrizSolucion[i][j][k] = valor;
	}
	
	/** 
	 * M�todo que fija el valor de la matriz soluci�n.
	 *
	 * @param matrizSolucion Matriz soluci�n establecida.
	 */
	public void establecerMatriz(double[][][] matrizSolucion)
	{
		this.matrizSolucion = matrizSolucion;
	}
	
	/**
	 * M�todo que fija el valor de evaluaci�n o adaptaci�n del individuo.
	 *
	 * @param valor Valor real que indica el nivel de adaptaci�n.
	 */
	public void establecerValorEvaluacion(double valorEvaluacion)
	{
		this.valorEvaluacion = valorEvaluacion;
	}
	
	/** 
	 * M�todo que devuelve el valor de la funci�n de evaluaci�n sobre la 
	 * soluci�n obtenida.
	 *
	 * @return valorEvaluacion Valor real de adaptaci�n.
	 */
	public double obtenerEvaluacion()
	{
		return valorEvaluacion;
	}
	
	/** 
	 * M�todo que devuelve la matriz de soluci�n de rango m*n*l.
	 *
	 * @return matrizSolucion Matriz de valores reales.
	 */
	public double[][][] obtenerSolucion()
	{
		return matrizSolucion;
	}
	
	/** 
	 * M�todo que devuelve la matriz soluci�n encontrada de rango m*n*l.
	 *
	 * @return solucion Matriz de valores reales en formato string.
	 */
	public String obtenerSolucionString()
	{
		String solucion;
		formatoDecimal = new DecimalFormat("##0.##");
		int i;
		int j;
		int k;
		
		solucion = "{";
		for (i = 0;i < ConfiguracionAGT.m;i++)
		{
			solucion = solucion + "[";
			for (j = 0;j < ConfiguracionAGT.n;j++)
			{
				solucion = solucion + "(";
				for (k = 0;k < ConfiguracionAGT.l;k++)
				{
					solucion =
						solucion +
						formatoDecimal.format(matrizSolucion[i][j][k]) +
						" ";
				}
				solucion = solucion + ")";
			}
			solucion = solucion + "]";
		}
		solucion = solucion + "}";
		
		return solucion;
	}
}