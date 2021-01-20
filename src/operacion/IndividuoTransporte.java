/**
 * @(#)IndividuoTransporte.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.text.DecimalFormat;
 
/**
 * Clase que describe la estructura de un individuo dentro de una población. La
 * estructura representa una solución factible al problema de transporte.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see DecimalFormat
 */
public class IndividuoTransporte
{
	/** 
	 * Estructura de un cromosoma en el AG de transporte. Matriz de valores
	 * reales que representa una posible solución al problema de transporte.
	 */
	private double[][][] matrizSolucion;
	
	/** Valor que indica la adecuación del individuo al entorno. */
	private double valorEvaluacion;
	
	/** Formato de salida para números decimales. */
	private DecimalFormat formatoDecimal;
	
	/** 
	 * Método constructor que inicializa los valores por defecto de la clase.
	 */
	public IndividuoTransporte()
	{
		// Matriz de valores reales de rango m*n*l
		matrizSolucion = new double[ConfiguracionAGT.m]
								   [ConfiguracionAGT.n]
								   [ConfiguracionAGT.l];
		
		// Valor de evaluación de la solución
		valorEvaluacion = 0.0;
	}
	
	/** 
	 * Método que fija el valor Xijk de la matriz solución.
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
	 * Método que fija el valor de la matriz solución.
	 *
	 * @param matrizSolucion Matriz solución establecida.
	 */
	public void establecerMatriz(double[][][] matrizSolucion)
	{
		this.matrizSolucion = matrizSolucion;
	}
	
	/**
	 * Método que fija el valor de evaluación o adaptación del individuo.
	 *
	 * @param valor Valor real que indica el nivel de adaptación.
	 */
	public void establecerValorEvaluacion(double valorEvaluacion)
	{
		this.valorEvaluacion = valorEvaluacion;
	}
	
	/** 
	 * Método que devuelve el valor de la función de evaluación sobre la 
	 * solución obtenida.
	 *
	 * @return valorEvaluacion Valor real de adaptación.
	 */
	public double obtenerEvaluacion()
	{
		return valorEvaluacion;
	}
	
	/** 
	 * Método que devuelve la matriz de solución de rango m*n*l.
	 *
	 * @return matrizSolucion Matriz de valores reales.
	 */
	public double[][][] obtenerSolucion()
	{
		return matrizSolucion;
	}
	
	/** 
	 * Método que devuelve la matriz solución encontrada de rango m*n*l.
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