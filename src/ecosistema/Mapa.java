/*
 * @(#)Mapa.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import jp.go.ipa.jgcl.JgclPoint3D;
import jp.go.ipa.jgcl.JgclCartesianPoint3D;
import jp.go.ipa.jgcl.JgclBsplineSurface3D;
import jp.go.ipa.jgcl.JgclKnotType;

/**
 * Clase que representa a un mapa del oceano gen�rico de tres dimensiones (3D)
 * multivista. Esto incluye sus delimitaciones geogr�ficas y sus relaciones
 * oceanogr�ficas m�s importantes. Las latitudes y longitudes son medidas en
 * grados. La altitud es medida en metros. Los datos del mapa son obtenidos de
 * la base de datos. Todo lo que se modela en esta clase se considera dentro del
 * sistema del mundo real y mundo modelo.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see ResultSet
 * @see SQLException
 * @see JgclPoint3D
 * @see JgclCartesianPoint3D
 * @see JgclBsplineSurface3D
 * @see JgclKnotType
 * @see TransformacionModelo
 * @see Tiempo
 * @see Proyecto
 * @see Servicio
 */
public class Mapa
{
	/**
	 * Constante para el valor del peso de la muestra usada para la materia
	 * org�nica (gramos).
	 */
	private static final double PESO_MATERIA_ORGANICA = 10000;
	
	/** La longitud inicial del mapa. */
	private static double longitudInicial;
	
	/** El tipo de longitud inicial del mapa. */
	private static int tipoLongitudInicial;
	
	/** La longitud final del mapa. */
	private static double longitudFinal;
	
	/** El tipo de longitud final del mapa. */
	private static int tipoLongitudFinal;
	
	/** La latitud inicial del mapa. */
	private static double latitudInicial;
	
	/** El tipo de latitud inicial del mapa. */
	private static int tipoLatitudInicial;
	
	/** La latitud final del mapa. */
	private static double latitudFinal;
	
	/** El tipo de latitud final del mapa. */
	private static int tipoLatitudFinal;
	
	/** La altitud inicial del mapa. */
	private static double altitudInicial;
	
	/** La altitud final del mapa. */
	private static double altitudFinal;
	
	/** La matriz con los puntos de salida para las altitudes del mapa. */
	private static JgclPoint3D[][] altitud;
	
	/**
	 * La matriz con los puntos de salida para las materias org�nicas del mapa.
	 */
	private static JgclPoint3D[][] materiaOrganica;
	
	/** El n�mero de puntos de salida en longitud. */
	private static int outputsLongitud;
	
	/** El n�mero de puntos de salida en latitud. */
	private static int outputsLatitud;
	
	/**
	 * La transformaci�n de modelo usada para discretizar el mapa (continuo) a
	 * una grilla de puntos (discreto).
	 */
	private static TransformacionModelo transformacion;
	
	/**
	 * M�todo constructor en donde se llaman a los m�todos que permiten iniciar
	 * el mapa de estudio y sus componentes m�s importantes. Se cargan los datos
	 * del mapa desde la base de datos, despu�s se inicializa la transformaci�n
	 * de modelo del mapa y despu�s se generan los puntos de salida para las
	 * altitudes del mapa.
	 *
	 * @param tiempo El tiempo en el cual se debe iniciar el mapa de estudio.
	 */
	public static void iniciar(Tiempo tiempo)
	{
		// Cargar los datos desde la base de datos.
		cargar(Proyecto.obtenerCodigoRegion());
		
		// Inicializar la transformaci�n de modelo del mapa.
		iniciarTransformacion();
		
		// Generar las altitudes de salida.
		generarAltitudes();
		
		// Generar las materias org�nicas de salida.
		generarMateriasOrganicas();
	}
	
	/**
	 * M�todo que carga los datos de un mapa regional con c�digo conocido desde
	 * la base de datos. En esta clase se inicializan todos los atributos de la
	 * clase que tienen relaci�n con la base de datos.
	 *
	 * @param codigoRegion El c�digo de la regi�n de la cual se obtienen los
	 *                     datos del mapa.
	 */
	private static void cargar(int codigoRegion)
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Realizar la consulta.
		String consulta =
			"select * "+
			"from region "+
			"where codigo_region = "+codigoRegion;
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Setear los valores de los atributos.
		try
		{
			// Apuntar al primer registro.
			resultado.first();
			
			// Setear los valores.
			tipoLongitudInicial = resultado.getInt("codigo_longitud_inicial");
			tipoLongitudFinal = resultado.getInt("codigo_longitud_final");
			tipoLatitudInicial = resultado.getInt("codigo_latitud_inicial");
			tipoLatitudFinal = resultado.getInt("codigo_latitud_final");
			longitudInicial = resultado.getDouble("longitud_inicial_region");
			longitudFinal = resultado.getDouble("longitud_final_region");
			latitudInicial = resultado.getDouble("latitud_inicial_region");
			latitudFinal = resultado.getDouble("latitud_final_region");
			altitudInicial = resultado.getDouble("altitud_inicial_region");
			altitudFinal = resultado.getDouble("altitud_final_region");
		}
		
		// Cuando no se pudo setear los valores de los atributos.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla region.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * M�todo que crea e inicializa la transformaci�n de modelo utilizada en el
	 * mapa. La transformaci�n de modelos es en 3D y lo que hace es respresentar
	 * el mapa de estudio real (continuo) como un malla de puntos (discreto).
	 */
	private static void iniciarTransformacion()
	{
		// El tama�o del modelo en el eje I, J y K.
		int iInicial = 0;
		int jInicial = 0;
		int kInicial = 0;
		int iFinal = Servicio.obtenerMinutos(longitudInicial, longitudFinal);
		int jFinal = Servicio.obtenerMinutos(latitudInicial, latitudFinal);
		int kFinal = (int)
					 Servicio.obtenerDistancia(altitudInicial, altitudFinal);
		
		// Inicializar la transformaci�n de modelo.
		transformacion =
			new TransformacionModelo(iInicial, iFinal,
									 jInicial, jFinal,
									 kInicial, kFinal,
									 longitudFinal, longitudInicial,
									 latitudInicial, latitudFinal,
									 altitudInicial, altitudFinal);
	}
	
	/**
	 * M�todo en donde se carga la configuraci�n de las altitudes reales e
	 * interpoladas del mapa. Se inicializa y carga el vector de coordenadas
	 * reales del mapa geogr�fico de estudio (Puntos de control) entre los
	 * l�mites establecidos (Area de estudio) y se establecen las altitudes para
	 * cada zona geogr�fica. Posteriormente se utiliza el m�todo B-Spline para
	 * generar la superficie del mapa.
	 */
	private static void generarAltitudes()
	{
		// Variables temporales.
		double u;
		double v;
		JgclPoint3D punto;
		
		// N�mero de puntos de control en longitud = 8.
		int inputsLongitud = Servicio.obtenerMinutos(longitudInicial,
													 longitudFinal)/15+3;
		
		/// N�mero de puntos de control en latitud = 14.
		int inputsLatitud = Servicio.obtenerMinutos(latitudInicial,
													latitudFinal)/15+3;
		
		// Crear la matriz de puntos de control.
		JgclPoint3D[][] inputAltitud =
			new JgclCartesianPoint3D[inputsLongitud][inputsLatitud];
		
		// Puntos de control de la Longitud 72� 45'.
		inputAltitud[0][0] = new JgclCartesianPoint3D(72.75, 29.25, -7000.0);
		inputAltitud[0][1] = new JgclCartesianPoint3D(72.75, 29.50, -7000.0);
		inputAltitud[0][2] = new JgclCartesianPoint3D(72.75, 29.75, -7000.0);
		inputAltitud[0][3] = new JgclCartesianPoint3D(72.75, 30.00, -7000.0);
		inputAltitud[0][4] = new JgclCartesianPoint3D(72.75, 30.25, -7000.0);
		inputAltitud[0][5] = new JgclCartesianPoint3D(72.75, 30.50, -7000.0);
		inputAltitud[0][6] = new JgclCartesianPoint3D(72.75, 30.75, -7000.0);
		inputAltitud[0][7] = new JgclCartesianPoint3D(72.75, 31.00, -7000.0);
		inputAltitud[0][8] = new JgclCartesianPoint3D(72.75, 31.25, -7000.0);
		inputAltitud[0][9] = new JgclCartesianPoint3D(72.75, 31.50, -7000.0);
		inputAltitud[0][10] = new JgclCartesianPoint3D(72.75, 31.75, -7000.0);
		inputAltitud[0][11] = new JgclCartesianPoint3D(72.75, 32.00, -7000.0);
		inputAltitud[0][12] = new JgclCartesianPoint3D(72.75, 32.25, -7000.0);
		inputAltitud[0][13] = new JgclCartesianPoint3D(72.75, 32.50, -7000.0);
		
		// Puntos de control de la Longitud 72� 30'.
		inputAltitud[1][0] = new JgclCartesianPoint3D(72.50, 29.25, -6000.0);
		inputAltitud[1][1] = new JgclCartesianPoint3D(72.50, 29.50, -6000.0);
		inputAltitud[1][2] = new JgclCartesianPoint3D(72.50, 29.75, -6000.0);
		inputAltitud[1][3] = new JgclCartesianPoint3D(72.50, 30.00, -6000.0);
		inputAltitud[1][4] = new JgclCartesianPoint3D(72.50, 30.25, -6000.0);
		inputAltitud[1][5] = new JgclCartesianPoint3D(72.50, 30.50, -6000.0);
		inputAltitud[1][6] = new JgclCartesianPoint3D(72.50, 30.75, -6000.0);
		inputAltitud[1][7] = new JgclCartesianPoint3D(72.50, 31.00, -6000.0);
		inputAltitud[1][8] = new JgclCartesianPoint3D(72.50, 31.25, -6000.0);
		inputAltitud[1][9] = new JgclCartesianPoint3D(72.50, 31.50, -6000.0);
		inputAltitud[1][10] = new JgclCartesianPoint3D(72.50, 31.75, -6000.0);
		inputAltitud[1][11] = new JgclCartesianPoint3D(72.50, 32.00, -6000.0);
		inputAltitud[1][12] = new JgclCartesianPoint3D(72.50, 32.25, -6000.0);
		inputAltitud[1][13] = new JgclCartesianPoint3D(72.50, 32.50, -6000.0);
		
		// Puntos de control de la Longitud 72� 15'.
		inputAltitud[2][0] = new JgclCartesianPoint3D(72.25, 29.25, -5700.0);
		inputAltitud[2][1] = new JgclCartesianPoint3D(72.25, 29.50, -6000.0);
		inputAltitud[2][2] = new JgclCartesianPoint3D(72.25, 29.75, -6000.0);
		inputAltitud[2][3] = new JgclCartesianPoint3D(72.25, 30.00, -6000.0);
		inputAltitud[2][4] = new JgclCartesianPoint3D(72.25, 30.25, -5800.0);
		inputAltitud[2][5] = new JgclCartesianPoint3D(72.25, 30.50, -6000.0);
		inputAltitud[2][6] = new JgclCartesianPoint3D(72.25, 30.75, -6000.0);
		inputAltitud[2][7] = new JgclCartesianPoint3D(72.25, 31.00, -6000.0);
		inputAltitud[2][8] = new JgclCartesianPoint3D(72.25, 31.25, -6000.0);
		inputAltitud[2][9] = new JgclCartesianPoint3D(72.25, 31.50, -6000.0);
		inputAltitud[2][10] = new JgclCartesianPoint3D(72.25, 31.75, -6000.0);
		inputAltitud[2][11] = new JgclCartesianPoint3D(72.25, 32.00, -6000.0);
		inputAltitud[2][12] = new JgclCartesianPoint3D(72.25, 32.25, -6000.0);
		inputAltitud[2][13] = new JgclCartesianPoint3D(72.25, 32.50, -6000.0);
		
		// Puntos de control de la Longitud 72� 0'.
		inputAltitud[3][0] = new JgclCartesianPoint3D(72.00, 29.25, -6200.0);
		inputAltitud[3][1] = new JgclCartesianPoint3D(72.00, 29.50, -6000.0);
		inputAltitud[3][2] = new JgclCartesianPoint3D(72.00, 29.75, -5800.0);
		inputAltitud[3][3] = new JgclCartesianPoint3D(72.00, 30.00, -5100.0);
		inputAltitud[3][4] = new JgclCartesianPoint3D(72.00, 30.25, -5000.0);
		inputAltitud[3][5] = new JgclCartesianPoint3D(72.00, 30.50, -4900.0);
		inputAltitud[3][6] = new JgclCartesianPoint3D(72.00, 30.75, -4000.0);
		inputAltitud[3][7] = new JgclCartesianPoint3D(72.00, 31.00, -4000.0);
		inputAltitud[3][8] = new JgclCartesianPoint3D(72.00, 31.25, -3900.0);
		inputAltitud[3][9] = new JgclCartesianPoint3D(72.00, 31.50, -3800.0);
		inputAltitud[3][10] = new JgclCartesianPoint3D(72.00, 31.75, -2200.0);
		inputAltitud[3][11] = new JgclCartesianPoint3D(72.00, 32.00, -4200.0);
		inputAltitud[3][12] = new JgclCartesianPoint3D(72.00, 32.25, -3800.0);
		inputAltitud[3][13] = new JgclCartesianPoint3D(72.00, 32.50, -3000.0);
		
		// Puntos de control de la Longitud 71� 45'.
		inputAltitud[4][0] = new JgclCartesianPoint3D(71.75, 29.25, -1500.0);
		inputAltitud[4][1] = new JgclCartesianPoint3D(71.75, 29.50, -1800.0);
		inputAltitud[4][2] = new JgclCartesianPoint3D(71.75, 29.75, -1800.0);
		inputAltitud[4][3] = new JgclCartesianPoint3D(71.75, 30.00, -2900.0);
		inputAltitud[4][4] = new JgclCartesianPoint3D(71.75, 30.25, -1000.0);
		inputAltitud[4][5] = new JgclCartesianPoint3D(71.75, 30.50, -300.0);
		inputAltitud[4][6] = new JgclCartesianPoint3D(71.75, 30.75, -500.0);
		inputAltitud[4][7] = new JgclCartesianPoint3D(71.75, 31.00, -800.0);
		inputAltitud[4][8] = new JgclCartesianPoint3D(71.75, 31.25, -500.0);
		inputAltitud[4][9] = new JgclCartesianPoint3D(71.75, 31.50, -800.0);
		inputAltitud[4][10] = new JgclCartesianPoint3D(71.75, 31.75, -1400.0);
		inputAltitud[4][11] = new JgclCartesianPoint3D(71.75, 32.00, -1500.0);
		inputAltitud[4][12] = new JgclCartesianPoint3D(71.75, 32.25, -1400.0);
		inputAltitud[4][13] = new JgclCartesianPoint3D(71.75, 32.50, -1350.0);
		
		// Puntos de control de la Longitud 71� 30'.
		inputAltitud[5][0] = new JgclCartesianPoint3D(71.50, 29.25, -50.0);
		inputAltitud[5][1] = new JgclCartesianPoint3D(71.50, 29.50, -100.0);
		inputAltitud[5][2] = new JgclCartesianPoint3D(71.50, 29.75, -200.0);
		inputAltitud[5][3] = new JgclCartesianPoint3D(71.50, 30.00, -490.0);
		inputAltitud[5][4] = new JgclCartesianPoint3D(71.50, 30.25, -190.0);
		inputAltitud[5][5] = new JgclCartesianPoint3D(71.50, 30.50, -600.0);
		inputAltitud[5][6] = new JgclCartesianPoint3D(71.50, 30.75, 600.0);
		inputAltitud[5][7] = new JgclCartesianPoint3D(71.50, 31.00, 400.0);
		inputAltitud[5][8] = new JgclCartesianPoint3D(71.50, 31.25, 350.0);
		inputAltitud[5][9] = new JgclCartesianPoint3D(71.50, 31.50, 300.0);
		inputAltitud[5][10] = new JgclCartesianPoint3D(71.50, 31.75, 0.0);
		inputAltitud[5][11] = new JgclCartesianPoint3D(71.50, 32.00, -150.0);
		inputAltitud[5][12] = new JgclCartesianPoint3D(71.50, 32.25, 0.0);
		inputAltitud[5][13] = new JgclCartesianPoint3D(71.50, 32.50, 80.0);
		
		// Puntos de control de la Longitud 71� 15'.
		inputAltitud[6][0] = new JgclCartesianPoint3D(71.25, 29.25, 150.0);
		inputAltitud[6][1] = new JgclCartesianPoint3D(71.25, 29.50, 300.0);
		inputAltitud[6][2] = new JgclCartesianPoint3D(71.25, 29.75, 600.0);
		inputAltitud[6][3] = new JgclCartesianPoint3D(71.25, 30.00, 40.0);
		inputAltitud[6][4] = new JgclCartesianPoint3D(71.25, 30.25, 600.0);
		inputAltitud[6][5] = new JgclCartesianPoint3D(71.25, 30.50, 1000.0);
		inputAltitud[6][6] = new JgclCartesianPoint3D(71.25, 30.75, 1000.0);
		inputAltitud[6][7] = new JgclCartesianPoint3D(71.25, 31.00, 1000.0);
		inputAltitud[6][8] = new JgclCartesianPoint3D(71.25, 31.25, 1000.0);
		inputAltitud[6][9] = new JgclCartesianPoint3D(71.25, 31.50, 800.0);
		inputAltitud[6][10] = new JgclCartesianPoint3D(71.25, 31.75, 800.0);
		inputAltitud[6][11] = new JgclCartesianPoint3D(71.25, 32.00, 600.0);
		inputAltitud[6][12] = new JgclCartesianPoint3D(71.25, 32.25, 600.0);
		inputAltitud[6][13] = new JgclCartesianPoint3D(71.25, 32.50, 750.0);
		
		// Puntos de control de la Longitud 71� 0'.
		inputAltitud[7][0] = new JgclCartesianPoint3D(71.00, 29.25, 350.0);
		inputAltitud[7][1] = new JgclCartesianPoint3D(71.00, 29.50, 500.0);
		inputAltitud[7][2] = new JgclCartesianPoint3D(71.00, 29.75, 800.0);
		inputAltitud[7][3] = new JgclCartesianPoint3D(71.00, 30.00, 240.0);
		inputAltitud[7][4] = new JgclCartesianPoint3D(71.00, 30.25, 800.0);
		inputAltitud[7][5] = new JgclCartesianPoint3D(71.00, 30.50, 1200.0);
		inputAltitud[7][6] = new JgclCartesianPoint3D(71.00, 30.75, 1200.0);
		inputAltitud[7][7] = new JgclCartesianPoint3D(71.00, 31.00, 1200.0);
		inputAltitud[7][8] = new JgclCartesianPoint3D(71.00, 31.25, 1200.0);
		inputAltitud[7][9] = new JgclCartesianPoint3D(71.00, 31.50, 1000.0);
		inputAltitud[7][10] = new JgclCartesianPoint3D(71.00, 31.75, 1000.0);
		inputAltitud[7][11] = new JgclCartesianPoint3D(71.00, 32.00, 800.0);
		inputAltitud[7][12] = new JgclCartesianPoint3D(71.00, 32.25, 800.0);
		inputAltitud[7][13] = new JgclCartesianPoint3D(71.00, 32.50, 950.0);
		
		// Crer la superficie.
		// Se utiliza el m�todo B-Spline con:
		//	1.- Grado del polinomio en U = 3.
		//	2.- No periodicidad en U.
		//	3.- Knots uniformes en U.
		//	4.- Grado del polinomio en V = 3.
		//	5.- No periodicidad en V.
		//	6.- Knots uniformes en V.
		JgclBsplineSurface3D superficie =
			new JgclBsplineSurface3D(3, false, JgclKnotType.UNIFORM_KNOTS,
									 3, false, JgclKnotType.UNIFORM_KNOTS,
									 inputAltitud);
		
		// Establecer el n�mero de puntos de salida en longitud = 76.
		outputsLongitud = transformacion.obtenerIMax()+1;
		
		// Establecer el n�mero de puntos de salida en latitud = 166.
		outputsLatitud = transformacion.obtenerJMax()+1;
		
		// Crear la matriz de puntos de salida.
		altitud =
			new JgclCartesianPoint3D[outputsLongitud][outputsLatitud];
		
		// Inicializar la matriz de puntos de salida.
		for (int i=0; i<outputsLongitud; i++)
			for (int j=0; j<outputsLatitud; j++)
			{
				u = (double)i/15.0; // Rango en [0.0; 5.0].
				v = (double)j/15.0; // Rango en [0.0; 11.0].
				
				// Obtener el punto de salida.
				punto = superficie.coordinates(u, v);
				
				// Inicializar y asignar valor al punto de salida.
				altitud[i][j] = new JgclCartesianPoint3D(punto.x(),
														 punto.y(),
														 punto.z());
			}
	}
	
	/**
	 * M�todo en donde se carga la configuraci�n de las materias org�nicas
	 * reales e interpolados del mapa. Se inicializa y carga el vector de
	 * materias org�nicas reales del mapa geogr�fico de estudio (Puntos de
	 * control) entre los l�mites establecidos (Area de estudio) y se establecen
	 * las materias org�nicas para cada zona geogr�fica. Posteriormente se
	 * utiliza el m�todo B-Spline para generar las materias org�nicas del mapa.
	 */
	private static void generarMateriasOrganicas()
	{
		// Variables temporales.
		double u;
		double v;
		JgclPoint3D punto;
		
		// N�mero de puntos de control en longitud = 8.
		int inputsLongitud = Servicio.obtenerMinutos(longitudInicial,
													 longitudFinal)/15+3;
		
		/// N�mero de puntos de control en latitud = 14.
		int inputsLatitud = Servicio.obtenerMinutos(latitudInicial,
													latitudFinal)/15+3;
		
		// Crear la matriz de puntos de control.
		JgclPoint3D[][] inputMateriaOrganica =
			new JgclCartesianPoint3D[inputsLongitud][inputsLatitud];
		
		// Puntos de control de la Longitud 72� 45'.
		inputMateriaOrganica[0][0] = new JgclCartesianPoint3D(72.75, 29.25, 0.01);
		inputMateriaOrganica[0][1] = new JgclCartesianPoint3D(72.75, 29.50, 0.01);
		inputMateriaOrganica[0][2] = new JgclCartesianPoint3D(72.75, 29.75, 0.01);
		inputMateriaOrganica[0][3] = new JgclCartesianPoint3D(72.75, 30.00, 0.01);
		inputMateriaOrganica[0][4] = new JgclCartesianPoint3D(72.75, 30.25, 0.01);
		inputMateriaOrganica[0][5] = new JgclCartesianPoint3D(72.75, 30.50, 0.01);
		inputMateriaOrganica[0][6] = new JgclCartesianPoint3D(72.75, 30.75, 0.01);
		inputMateriaOrganica[0][7] = new JgclCartesianPoint3D(72.75, 31.00, 0.01);
		inputMateriaOrganica[0][8] = new JgclCartesianPoint3D(72.75, 31.25, 0.01);
		inputMateriaOrganica[0][9] = new JgclCartesianPoint3D(72.75, 31.50, 0.01);
		inputMateriaOrganica[0][10] = new JgclCartesianPoint3D(72.75, 31.75, 0.01);
		inputMateriaOrganica[0][11] = new JgclCartesianPoint3D(72.75, 32.00, 0.01);
		inputMateriaOrganica[0][12] = new JgclCartesianPoint3D(72.75, 32.25, 0.01);
		inputMateriaOrganica[0][13] = new JgclCartesianPoint3D(72.75, 32.50, 0.01);
		
		// Puntos de control de la Longitud 72� 30'.
		inputMateriaOrganica[1][0] = new JgclCartesianPoint3D(72.50, 29.25, 0.01);
		inputMateriaOrganica[1][1] = new JgclCartesianPoint3D(72.50, 29.50, 0.01);
		inputMateriaOrganica[1][2] = new JgclCartesianPoint3D(72.50, 29.75, 0.009);
		inputMateriaOrganica[1][3] = new JgclCartesianPoint3D(72.50, 30.00, 0.0085);
		inputMateriaOrganica[1][4] = new JgclCartesianPoint3D(72.50, 30.25, 0.0083);
		inputMateriaOrganica[1][5] = new JgclCartesianPoint3D(72.50, 30.50, 0.0082);
		inputMateriaOrganica[1][6] = new JgclCartesianPoint3D(72.50, 30.75, 0.0081);
		inputMateriaOrganica[1][7] = new JgclCartesianPoint3D(72.50, 31.00, 0.0082);
		inputMateriaOrganica[1][8] = new JgclCartesianPoint3D(72.50, 31.25, 0.0081);
		inputMateriaOrganica[1][9] = new JgclCartesianPoint3D(72.50, 31.50, 0.0078);
		inputMateriaOrganica[1][10] = new JgclCartesianPoint3D(72.50, 31.75, 0.078);
		inputMateriaOrganica[1][11] = new JgclCartesianPoint3D(72.50, 32.00, 0.082);
		inputMateriaOrganica[1][12] = new JgclCartesianPoint3D(72.50, 32.25, 0.083);
		inputMateriaOrganica[1][13] = new JgclCartesianPoint3D(72.50, 32.50, 0.084);
		
		// Puntos de control de la Longitud 72� 15'.
		inputMateriaOrganica[2][0] = new JgclCartesianPoint3D(72.25, 29.25, 0.0082);
		inputMateriaOrganica[2][1] = new JgclCartesianPoint3D(72.25, 29.50, 0.008);
		inputMateriaOrganica[2][2] = new JgclCartesianPoint3D(72.25, 29.75, 0.0078);
		inputMateriaOrganica[2][3] = new JgclCartesianPoint3D(72.25, 30.00, 0.0071);
		inputMateriaOrganica[2][4] = new JgclCartesianPoint3D(72.25, 30.25, 0.0068);
		inputMateriaOrganica[2][5] = new JgclCartesianPoint3D(72.25, 30.50, 0.0066);
		inputMateriaOrganica[2][6] = new JgclCartesianPoint3D(72.25, 30.75, 0.0065);
		inputMateriaOrganica[2][7] = new JgclCartesianPoint3D(72.25, 31.00, 0.0064);
		inputMateriaOrganica[2][8] = new JgclCartesianPoint3D(72.25, 31.25, 0.0062);
		inputMateriaOrganica[2][9] = new JgclCartesianPoint3D(72.25, 31.50, 0.006);
		inputMateriaOrganica[2][10] = new JgclCartesianPoint3D(72.25, 31.75, 0.006);
		inputMateriaOrganica[2][11] = new JgclCartesianPoint3D(72.25, 32.00, 0.0062);
		inputMateriaOrganica[2][12] = new JgclCartesianPoint3D(72.25, 32.25, 0.0061);
		inputMateriaOrganica[2][13] = new JgclCartesianPoint3D(72.25, 32.50, 0.0059);
		
		// Puntos de control de la Longitud 72� 0'.
		inputMateriaOrganica[3][0] = new JgclCartesianPoint3D(72.00, 29.25, 0.0073);
		inputMateriaOrganica[3][1] = new JgclCartesianPoint3D(72.00, 29.50, 0.007);
		inputMateriaOrganica[3][2] = new JgclCartesianPoint3D(72.00, 29.75, 0.0058);
		inputMateriaOrganica[3][3] = new JgclCartesianPoint3D(72.00, 30.00, 0.0056);
		inputMateriaOrganica[3][4] = new JgclCartesianPoint3D(72.00, 30.25, 0.0055);
		inputMateriaOrganica[3][5] = new JgclCartesianPoint3D(72.00, 30.50, 0.0054);
		inputMateriaOrganica[3][6] = new JgclCartesianPoint3D(72.00, 30.75, 0.005);
		inputMateriaOrganica[3][7] = new JgclCartesianPoint3D(72.00, 31.00, 0.005);
		inputMateriaOrganica[3][8] = new JgclCartesianPoint3D(72.00, 31.25, 0.0048);
		inputMateriaOrganica[3][9] = new JgclCartesianPoint3D(72.00, 31.50, 0.0048);
		inputMateriaOrganica[3][10] = new JgclCartesianPoint3D(72.00, 31.75, 0.0052);
		inputMateriaOrganica[3][11] = new JgclCartesianPoint3D(72.00, 32.00, 0.0053);
		inputMateriaOrganica[3][12] = new JgclCartesianPoint3D(72.00, 32.25, 0.005);
		inputMateriaOrganica[3][13] = new JgclCartesianPoint3D(72.00, 32.50, 0.0048);
		
		// Puntos de control de la Longitud 71� 45'.
		inputMateriaOrganica[4][0] = new JgclCartesianPoint3D(71.75, 29.25, 0.0045);
		inputMateriaOrganica[4][1] = new JgclCartesianPoint3D(71.75, 29.50, 0.0039);
		inputMateriaOrganica[4][2] = new JgclCartesianPoint3D(71.75, 29.75, 0.0038);
		inputMateriaOrganica[4][3] = new JgclCartesianPoint3D(71.75, 30.00, 0.0044);
		inputMateriaOrganica[4][4] = new JgclCartesianPoint3D(71.75, 30.25, 0.0031);
		inputMateriaOrganica[4][5] = new JgclCartesianPoint3D(71.75, 30.50, 0.0022);
		inputMateriaOrganica[4][6] = new JgclCartesianPoint3D(71.75, 30.75, 0.0028);
		inputMateriaOrganica[4][7] = new JgclCartesianPoint3D(71.75, 31.00, 0.0027);
		inputMateriaOrganica[4][8] = new JgclCartesianPoint3D(71.75, 31.25, 0.002);
		inputMateriaOrganica[4][9] = new JgclCartesianPoint3D(71.75, 31.50, 0.0025);
		inputMateriaOrganica[4][10] = new JgclCartesianPoint3D(71.75, 31.75, 0.0033);
		inputMateriaOrganica[4][11] = new JgclCartesianPoint3D(71.75, 32.00, 0.0035);
		inputMateriaOrganica[4][12] = new JgclCartesianPoint3D(71.75, 32.25, 0.0034);
		inputMateriaOrganica[4][13] = new JgclCartesianPoint3D(71.75, 32.50, 0.0033);
		
		// Puntos de control de la Longitud 71� 30'.
		inputMateriaOrganica[5][0] = new JgclCartesianPoint3D(71.50, 29.25, 0.0005);
		inputMateriaOrganica[5][1] = new JgclCartesianPoint3D(71.50, 29.50, 0.0005);
		inputMateriaOrganica[5][2] = new JgclCartesianPoint3D(71.50, 29.75, 0.001);
		inputMateriaOrganica[5][3] = new JgclCartesianPoint3D(71.50, 30.00, 0.0025);
		inputMateriaOrganica[5][4] = new JgclCartesianPoint3D(71.50, 30.25, 0.001);
		inputMateriaOrganica[5][5] = new JgclCartesianPoint3D(71.50, 30.50, -0.001);
		inputMateriaOrganica[5][6] = new JgclCartesianPoint3D(71.50, 30.75, -0.001);
		inputMateriaOrganica[5][7] = new JgclCartesianPoint3D(71.50, 31.00, -0.002);
		inputMateriaOrganica[5][8] = new JgclCartesianPoint3D(71.50, 31.25, -0.003);
		inputMateriaOrganica[5][9] = new JgclCartesianPoint3D(71.50, 31.50, -0.001);
		inputMateriaOrganica[5][10] = new JgclCartesianPoint3D(71.50, 31.75, 0.0);
		inputMateriaOrganica[5][11] = new JgclCartesianPoint3D(71.50, 32.00, 0.0005);
		inputMateriaOrganica[5][12] = new JgclCartesianPoint3D(71.50, 32.25, 0.0);
		inputMateriaOrganica[5][13] = new JgclCartesianPoint3D(71.50, 32.50, 0.0005);
		
		// Puntos de control de la Longitud 71� 15'.
		inputMateriaOrganica[6][0] = new JgclCartesianPoint3D(71.25, 29.25, -0.0015);
		inputMateriaOrganica[6][1] = new JgclCartesianPoint3D(71.25, 29.50, -0.003);
		inputMateriaOrganica[6][2] = new JgclCartesianPoint3D(71.25, 29.75, -0.006);
		inputMateriaOrganica[6][3] = new JgclCartesianPoint3D(71.25, 30.00, -0.0004);
		inputMateriaOrganica[6][4] = new JgclCartesianPoint3D(71.25, 30.25, -0.006);
		inputMateriaOrganica[6][5] = new JgclCartesianPoint3D(71.25, 30.50, -0.001);
		inputMateriaOrganica[6][6] = new JgclCartesianPoint3D(71.25, 30.75, -0.001);
		inputMateriaOrganica[6][7] = new JgclCartesianPoint3D(71.25, 31.00, -0.001);
		inputMateriaOrganica[6][8] = new JgclCartesianPoint3D(71.25, 31.25, -0.001);
		inputMateriaOrganica[6][9] = new JgclCartesianPoint3D(71.25, 31.50, -0.008);
		inputMateriaOrganica[6][10] = new JgclCartesianPoint3D(71.25, 31.75, -0.0008);
		inputMateriaOrganica[6][11] = new JgclCartesianPoint3D(71.25, 32.00, -0.0006);
		inputMateriaOrganica[6][12] = new JgclCartesianPoint3D(71.25, 32.25, -0.0006);
		inputMateriaOrganica[6][13] = new JgclCartesianPoint3D(71.25, 32.50, -0.00075);
		
		// Puntos de control de la Longitud 71� 0'.
		inputMateriaOrganica[7][0] = new JgclCartesianPoint3D(71.00, 29.25, -0.0003);
		inputMateriaOrganica[7][1] = new JgclCartesianPoint3D(71.00, 29.50, -0.0005);
		inputMateriaOrganica[7][2] = new JgclCartesianPoint3D(71.00, 29.75, -0.0008);
		inputMateriaOrganica[7][3] = new JgclCartesianPoint3D(71.00, 30.00, -0.0002);
		inputMateriaOrganica[7][4] = new JgclCartesianPoint3D(71.00, 30.25, -0.0008);
		inputMateriaOrganica[7][5] = new JgclCartesianPoint3D(71.00, 30.50, -0.0012);
		inputMateriaOrganica[7][6] = new JgclCartesianPoint3D(71.00, 30.75, -0.0012);
		inputMateriaOrganica[7][7] = new JgclCartesianPoint3D(71.00, 31.00, -0.0012);
		inputMateriaOrganica[7][8] = new JgclCartesianPoint3D(71.00, 31.25, -0.0012);
		inputMateriaOrganica[7][9] = new JgclCartesianPoint3D(71.00, 31.50, -0.001);
		inputMateriaOrganica[7][10] = new JgclCartesianPoint3D(71.00, 31.75, -0.001);
		inputMateriaOrganica[7][11] = new JgclCartesianPoint3D(71.00, 32.00, -0.0008);
		inputMateriaOrganica[7][12] = new JgclCartesianPoint3D(71.00, 32.25, -0.0008);
		inputMateriaOrganica[7][13] = new JgclCartesianPoint3D(71.00, 32.50, -0.00095);
		
		// Crer la superficie.
		// Se utiliza el m�todo B-Spline con:
		//	1.- Grado del polinomio en U = 3.
		//	2.- No periodicidad en U.
		//	3.- Knots uniformes en U.
		//	4.- Grado del polinomio en V = 3.
		//	5.- No periodicidad en V.
		//	6.- Knots uniformes en V.
		JgclBsplineSurface3D superficie =
			new JgclBsplineSurface3D(3, false, JgclKnotType.UNIFORM_KNOTS,
									 3, false, JgclKnotType.UNIFORM_KNOTS,
									 inputMateriaOrganica);
		
		// Establecer el n�mero de puntos de salida en longitud = 76.
		outputsLongitud = transformacion.obtenerIMax()+1;
		
		// Establecer el n�mero de puntos de salida en latitud = 166.
		outputsLatitud = transformacion.obtenerJMax()+1;
		
		// Crear la matriz de puntos de salida.
		materiaOrganica =
			new JgclCartesianPoint3D[outputsLongitud][outputsLatitud];
		
		// Inicializar la matriz de puntos de salida.
		for (int i=0; i<outputsLongitud; i++)
			for (int j=0; j<outputsLatitud; j++)
			{
				u = (double)i/15.0; // Rango en [0.0; 5.0].
				v = (double)j/15.0; // Rango en [0.0; 11.0].
				
				// Obtener el punto de salida.
				punto = superficie.coordinates(u, v);
				
				// Inicializar y asignar valor al punto de salida.
				materiaOrganica[i][j] = new JgclCartesianPoint3D(punto.x(),
																 punto.y(),
																 punto.z());
			}
	}
	
	/**
	 * M�todo que genera todos los cambios en el mapa que ocurren por
	 * consecuencia del cambio en el tiempo del ambiente.
	 *
	 */
	/*
	 * OBSERVACION: Falta implementar este m�todo.
	 */
	public static void evolucionar()
	{
	}
	
	/**
	 * M�todo que obtiene una longitud en funci�n de la latitud y la altitud.
	 * Para obtener la longitud se deja fija la latitud y se comienza a buscar
	 * la altura recibida como par�metro. Cuando es encontrada, entonces se sale
	 * del ciclo de b�squeda. Finalmente, se retorna la longitud en donde par�
	 * el ciclo de b�squeda.
	 *
	 * @param latitud La latitud de la coordenada.
	 * @param altitud La altitud de la coordenada.
	 *
	 * @return longitud La longitud correspondiente a la latitud y altitud de la
	 *                  coordenada.
	 */
	public static double obtenerLongitud(double latitudCoordenada,
										 double altitudCoordenada)
	{
		// El error aceptable.
		double error = -50;
		
		// Variables temporales.
		int i;
		int j = transformacion.obtenerJ(latitudCoordenada);
		
		// Ciclo para recorrer las longitudes con una latitud fija.
		for (i=0; i<outputsLongitud; i++)
			if ((altitud[i][j].z() - altitudCoordenada) >= error)
				break;
		
		// Devolver la longitud.
		return transformacion.obtenerX(i);
	}
	
	/**
	 * M�todo que obtiene una latitud en funci�n de la longitud y la altitud.
	 * Para obtener la latitud se deja fija la longitud y se comienza a buscar
	 * la altura recibida como par�metro. Cuando es encontrada, entonces se sale
	 * del ciclo de b�squeda. Finalmente, se retorna la latitud en donde par�
	 * el ciclo de b�squeda.
	 *
	 * @param longitud La longitud de la coordenada.
	 * @param altitud La altitud de la coordenada.
	 *
	 * @return latitud La latitud correspondiente a la longitud y altitud de la
	 *                 coordenada.
	 */
	public static double obtenerLatitud(double longitudCoordenada,
										double altitudCoordenada)
	{
		// El error aceptable.
		double error = -50;
		
		// Variables temporales.
		int i = transformacion.obtenerI(longitudCoordenada);
		int j;
		
		// Ciclo para recorrer las latitudes con una longitud fija.
		for (j=0; j<outputsLatitud; j++)
			if ((altitud[i][j].z() - altitudCoordenada) >= error)
				break;
		
		// Devolver la latitud.
		return transformacion.obtenerY(j);
	}
	
	/**
	 * M�todo que obtiene una altitud en funci�n de la longitud y la latitud.
	 *
	 * @param longitud La longitud de la coordenada.
	 * @param latitud La latitud de la coordenada.
	 *
	 * @return altitud[i][j] La altitud correspondiente a la longitud y latitud
	 *                       de la coordenada.
	 */
	public static double obtenerAltitud(double longitudCoordenada,
										double latitudCoordenada)
	{
		int i = transformacion.obtenerI(longitudCoordenada);
		int j = transformacion.obtenerJ(latitudCoordenada);
		
		return altitud[i][j].z();
	}
	
	/**
	 * M�todo que obtiene los gramos de materia org�nica que tiene una
	 * coordenada en funci�n de la longitud, la latitud y la altitud.
	 *
	 * @param coordenada La coordenada del mapa.
	 *
	 * @return materiaOrganica Cuando en la coordenada hay materia org�nica.
	 * @return cero Cuando en la coordenada no hay materia org�nica.
	 */
	public static double obtenerMateriaOrganica(Coordenada coordenada)
	{
		// El factor de sedimentaci�n. Utiliza la siguiente relaci�n: A m�s
		// altitud, entonces el factor tiene mayor valor; A menor altitud,
		// entonces el factor tiene menor valor.
		double factor;
		
		// Obtener las coordenadas en i y j.
		int i = transformacion.obtenerI(coordenada.obtenerLongitud());
		int j = transformacion.obtenerJ(coordenada.obtenerLatitud());
		
		// Obtener las altitudes.
		double altitudMapa = altitud[i][j].z();
		double altitudActual = coordenada.obtenerAltitud();
		
		// Calcular el factor de sedimentaci�n.
		if (altitudMapa >= 0)
			factor = 0;
		else factor = altitudActual/altitudMapa;
		
		return (materiaOrganica[i][j].z() > 0) ?
				factor * PESO_MATERIA_ORGANICA * materiaOrganica[i][j].z() : 0;
	}
	
	/**
	 * M�todo que determina cuanto tiene de fitoplancton una coordenada
	 * geogr�fica. El valor que se retorna en en gramos.
	 *
	 * @param coordenada La coordenada a la cual se le determina la cantidad de
	 *                   fitoplancton que contiene.
	 *
	 * @return fitoplancton La cantidad de fitoplancton que contiene la
	 *                      coordenada.
	 */
	/*
	 * OBSERVACION: Falta implementar este m�todo.
	 */
	public static double obtenerFitoplancton(Coordenada coordenada)
	{
		return 0;
	}
	
	/**
	 * M�todo que determina cuanto tiene de zooplancton una coordenada
	 * geogr�fica. El valor que se retorna en en gramos.
	 *
	 * @param coordenada La coordenada a la cual se le determina la cantidad de
	 *                   zooplancton que contiene.
	 *
	 * @return zooplancton La cantidad de zooplancton que contiene la
	 *                     coordenada.
	 */
	/*
	 * OBSERVACION: Falta implementar este m�todo.
	 */
	public static double obtenerZooplancton(Coordenada coordenada)
	{
		return 0;
	}
	
	/**
	 * M�todo que determina si una coordenada geogr�fica tiene materia org�nica
	 * o no. Si la coordenada tiene materia org�nica se retorna true. En caso
	 * contrario, se retorna false.
	 *
	 * @param coordenada La coordenada a la cual se le determina si tiene
	 *                   materia org�nica o no.
	 *
	 * @return true Cuando la coordenada si tiene materia org�nica.
	 * @return false Cuando la coordenada no tiene materia org�nica.
	 */
	public static boolean tieneMateriaOrganica(Coordenada coordenada)
	{
		return obtenerMateriaOrganica(coordenada) > 0;
	}
	
	/**
	 * M�todo que determina si una coordenada geogr�fica tiene fitoplancton o
	 * no. Si la coordenada tiene fitoplancton se retorna true. En caso
	 * contrario, se retorna false.
	 *
	 * @param coordenada La coordenada a la cual se le determina si tiene
	 *                   fitoplancton o no.
	 *
	 * @return true Cuando la coordenada si tiene fitoplancton.
	 * @return false Cuando la coordenada no tiene fitoplancton.
	 */
	public static boolean tieneFitoplancton(Coordenada coordenada)
	{
		return obtenerFitoplancton(coordenada) > 0;
	}
	
	/**
	 * M�todo que determina si una coordenada geogr�fica tiene zooplancton o no.
	 * Si la coordenada tiene zooplancton se retorna true. En caso contrario, se
	 * retorna false.
	 *
	 * @param coordenada La coordenada a la cual se le determina si tiene
	 *                   zooplancton o no.
	 *
	 * @return true Cuando la coordenada si tiene zooplancton.
	 * @return false Cuando la coordenada no tiene zooplancton.
	 */
	public static boolean tieneZooplancton(Coordenada coordenada)
	{
		return obtenerZooplancton(coordenada) > 0;
	}
	
	/**
	 * M�todo que determina si una coordenada geogr�fica est� en el agua o no.
	 * Si la coordenada est� en el agua se retorna true. En caso contrario, se
	 * retorna false.
	 *
	 * @param coordenada La coordenada a la cual se le determina si est� en el
	 *                   agua o no.
	 *
	 * @return true Cuando la coordenada si est� en el agua.
	 * @return false Cuando la coordenada no est� en el agua.
	 */
	public static boolean esAgua(Coordenada coordenada)
	{
		double altitudCoordenada = coordenada.obtenerAltitud();
		double altitudMapa = obtenerAltitud(coordenada.obtenerLongitud(),
											coordenada.obtenerLatitud());
		
		return (altitudMapa < altitudCoordenada) && (altitudCoordenada <= 0);
	}
	
	/**
	 * M�todo que determina si una coordenada geogr�fica est� en la tierra o no.
	 * Si la coordenada est� en la tierra se retorna true. En caso contrario, se
	 * retorna false.
	 *
	 * @param coordenada La coordenada a la cual se le determina si est� en la
	 *                   tierra o no.
	 *
	 * @return true Cuando la coordenada si est� en la tierra.
	 * @return false Cuando la coordenada no est� en la tierra.
	 */
	public static boolean esTierra(Coordenada coordenada)
	{
		double altitudCoordenada = coordenada.obtenerAltitud();
		double altitudMapa = obtenerAltitud(coordenada.obtenerLongitud(),
											coordenada.obtenerLatitud());
		
		return (0 < altitudCoordenada) || (altitudCoordenada <= altitudMapa);
	}
	
	/**
	 * M�todo que obtiene la longitud �ptima a un rango de distancias al fondo
	 * especificado en los par�metros. Se hace un recorrido en el eje de las
	 * longitudes hasta encontrar la distancia al fondo deseado. Se busca desde
	 * la longitud actual hasta la longitud final y desde la longitud actual
	 * hasta la longitud inicial. Luego se devuelve la longitud m�s cercana.
	 *
	 * @param longitudCoordenada La longitud desde donde se comienza la b�squeda.
	 * @param latitudCoordenada La latitud desde donde se comienza la b�squeda.
	 * @param altitudCoordenada La altitud desde donde se comienza la b�squeda.
	 * @param distanciaFondoMinima La distancia al fondo m�nima a buscar.
	 * @param distanciaFondoMaxima La distancia al fondo m�xima a buscar.
	 *
	 * @return longitudOptima La longitud �ptima a un rango de distancias al
	 *                        fondo.
	 */
	public static double obtenerLongitudOptimaDistanciaFondo(
													double longitudCoordenada,
													double latitudCoordenada,
													double altitudCoordenada,
													double distanciaFondoMinima,
													double distanciaFondoMaxima)
	{
		// Variables temporales.
		int i;
		int j = transformacion.obtenerJ(latitudCoordenada);
		double distanciaFondo;
		double longitudDecreciente = longitudCoordenada;
		double longitudCreciente = longitudCoordenada;
		
		// Ciclo para recorrer las longitudes en forma decreciente, con una
		// latitud fija.
		for (i=transformacion.obtenerI(longitudCoordenada);
			 i<outputsLongitud; i++)
		{
			// Obtener la distancia al fondo.
			distanciaFondo = Math.abs(altitud[i][j].z() - altitudCoordenada);
			
			// Cuando la distancia est� entre la m�nima y la m�xima.
			if (distanciaFondoMinima <= distanciaFondo &&
				distanciaFondo <= distanciaFondoMaxima)
			{
				longitudDecreciente = transformacion.obtenerX(i);
				break;
			}
		}
		
		// Ciclo para recorrer las longitudes en forma creciente, con una
		// latitud fija.
		for (i=transformacion.obtenerI(longitudCoordenada);
			 i>=0; i--)
		{
			// Obtener la distancia al fondo.
			distanciaFondo = Math.abs(altitud[i][j].z() - altitudCoordenada);
			
			// Cuando la distancia est� entre la m�nima y la m�xima.
			if (distanciaFondoMinima <= distanciaFondo &&
				distanciaFondo <= distanciaFondoMaxima)
			{
				longitudCreciente = transformacion.obtenerX(i);
				break;
			}
		}
		
		// Devolver la longitud m�s cercana.
		return Math.abs(longitudCoordenada - longitudDecreciente) <
			   Math.abs(longitudCreciente - longitudCoordenada) ?
			   longitudDecreciente : longitudCreciente;
	}
	
	/**
	 * M�todo que obtiene la latitud �ptima a un rango de distancias al fondo
	 * especificado en los par�metros. Se hace un recorrido en el eje de las
	 * latitudes hasta encontrar la distancia al fondo deseado. Se busca desde
	 * la latitud actual hasta la latitud final y desde la latitud actual hasta
	 * la latitud inicial. Luego se devuelve la latitud m�s cercana.
	 *
	 * @param longitudCoordenada La longitud desde donde se comienza la b�squeda.
	 * @param latitudCoordenada La latitud desde donde se comienza la b�squeda.
	 * @param altitudCoordenada La altitud desde donde se comienza la b�squeda.
	 * @param distanciaFondoMinima La distancia al fondo m�nima a buscar.
	 * @param distanciaFondoMaxima La distancia al fondo m�xima a buscar.
	 *
	 * @return latitudOptima La latitud �ptima a un rango de distancias al
	 *                       fondo.
	 */
	public static double obtenerLatitudOptimaDistanciaFondo(
													double longitudCoordenada,
													double latitudCoordenada,
													double altitudCoordenada,
													double distanciaFondoMinima,
													double distanciaFondoMaxima)
	{
		// Variables temporales.
		int j;
		int i = transformacion.obtenerI(longitudCoordenada);
		double distanciaFondo;
		double latitudDecreciente = latitudCoordenada;
		double latitudCreciente = latitudCoordenada;
		
		// Ciclo para recorrer las latitudes en forma decreciente, con una
		// longitud fija.
		for (j=transformacion.obtenerJ(latitudCoordenada);
			 j>=0; j--)
		{
			// Obtener la distancia al fondo.
			distanciaFondo = Math.abs(altitud[i][j].z() - altitudCoordenada);
			
			// Cuando la distancia est� entre la m�nima y la m�xima.
			if (distanciaFondoMinima <= distanciaFondo &&
				distanciaFondo <= distanciaFondoMaxima)
			{
				latitudDecreciente = transformacion.obtenerY(j);
				break;
			}
		}
		
		// Ciclo para recorrer las latitudes en forma creciente, con una
		// longitud fija.
		for (j=transformacion.obtenerJ(latitudCoordenada);
			 j<outputsLatitud; j++)
		{
			// Obtener la distancia al fondo.
			distanciaFondo = Math.abs(altitud[i][j].z() - altitudCoordenada);
			
			// Cuando la distancia est� entre la m�nima y la m�xima.
			if (distanciaFondoMinima <= distanciaFondo &&
				distanciaFondo <= distanciaFondoMaxima)
			{
				latitudCreciente = transformacion.obtenerY(j);
				break;
			}
		}
		
		// Devolver la latitud m�s cercana.
		return Math.abs(latitudCoordenada - latitudDecreciente) <
			   Math.abs(latitudCreciente - latitudCoordenada) ?
			   latitudDecreciente : latitudCreciente;
	}
	
	/**
	 * M�todo que obtiene la altitud �ptima a un rango de distancias al fondo
	 * especificado en los par�metros. Se hace un recorrido en el eje de las
	 * altitudes hasta encontrar la distancia al fondo deseado. Se busca desde
	 * la altitud actual hasta la altitud final y desde la altitud actual hasta
	 * la altitud inicial. Luego se devuelve la altitud m�s cercana.
	 *
	 * @param longitudCoordenada La longitud desde donde se comienza la b�squeda.
	 * @param latitudCoordenada La latitud desde donde se comienza la b�squeda.
	 * @param altitudCoordenada La altitud desde donde se comienza la b�squeda.
	 * @param distanciaFondoMinima La distancia al fondo m�nima a buscar.
	 * @param distanciaFondoMaxima La distancia al fondo m�xima a buscar.
	 *
	 * @return altitudOptima La altitud �ptima a un rango de distancias al
	 *                       fondo.
	 */
	public static double obtenerAltitudOptimaDistanciaFondo(
													double longitudCoordenada,
													double latitudCoordenada,
													double altitudCoordenada,
													double distanciaFondoMinima,
													double distanciaFondoMaxima)
	{
		// Obtener la transformaci�n en I y J.
		int i = transformacion.obtenerI(longitudCoordenada);
		int j = transformacion.obtenerJ(latitudCoordenada);
		
		// Obtener la altitud del mapa.
		double altitudMapa = altitud[i][j].z();
		
		// Obtener las altitudes m�nimas y m�ximas.
		double altitudMinima = Math.abs(altitudMapa) - distanciaFondoMinima;
		double altitudMaxima = Math.abs(altitudMapa) - distanciaFondoMaxima;
		
		// Devolver la altitud m�s cercana.
		return Math.abs(altitudMinima - Math.abs(altitudCoordenada)) <
			   Math.abs(altitudMaxima - Math.abs(altitudCoordenada)) ?
			   -1*altitudMinima : -1*altitudMaxima;
	}
	
	/**
	 * M�todo que obtiene la distancia en kil�metros entre una coordenada y el
	 * continente. Se hace un recorrido en el eje de las longitudes hasta
	 * encontrar la altitud mayor o igual a cero. Luego se devuelve la distancia
	 * entre la coordenada y la longitud encontrada.
	 *
	 * @param coordenada La coordenada desde donde se realiza la distancia.
	 *
	 * @return distanciaContinente La distancia en kil�metros entre la
	 *                             coordenada y la longitud encontrada.
	 */
	public static double obtenerDistanciaContinente(Coordenada coordenada)
	{
		// Variables temporales.
		int i;
		int j = transformacion.obtenerJ(coordenada.obtenerLatitud());
		double longitud = coordenada.obtenerLongitud();
		
		// Ciclo para recorrer las longitudes en forma decreciente, con una
		// latitud fija.
		for (i=transformacion.obtenerI(coordenada.obtenerLongitud());
			 i<outputsLongitud; i++)
		{
			// Cuando la altitud es mayor o igual a cero.
			if (altitud[i][j].z() >= 0)
			{
				longitud = transformacion.obtenerX(i);
				break;
			}
		}
		
		// Devolver la distancia (kil�metros) entre las longitudes.
		return Servicio.transformarGradoLongitudKilometro(
			   Math.abs(coordenada.obtenerLongitud() - longitud));
	}
	
	/**
	 * M�todo que obtiene el valor del atributo longitudInicial.
	 *
	 * @return longitudInicial El valor del atributo longitudInicial.
	 */
	public static double obtenerLongitudInicial()
	{
		return longitudInicial;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo longitudFinal.
	 *
	 * @return longitudFinal El valor del atributo longitudFinal.
	 */
	public static double obtenerLongitudFinal()
	{
		return longitudFinal;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo latitudInicial.
	 *
	 * @return latitudInicial El valor del atributo latitudInicial.
	 */
	public static double obtenerLatitudInicial()
	{
		return latitudInicial;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo latitudFinal.
	 *
	 * @return latitudFinal El valor del atributo latitudFinal.
	 */
	public static double obtenerLatitudFinal()
	{
		return latitudFinal;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo altitudInicial.
	 *
	 * @return altitudInicial El valor del atributo altitudInicial.
	 */
	public static double obtenerAltitudInicial()
	{
		return altitudInicial;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo altitudFinal.
	 *
	 * @return altitudFinal El valor del atributo altitudFinal.
	 */
	public static double obtenerAltitudFinal()
	{
		return altitudFinal;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo transformacion.
	 *
	 * @return transformacion El valor del atributo transformacion.
	 */
	public static TransformacionModelo obtenerTransformacion()
	{
		return transformacion;
	}
	
	/**
	 * M�todo que establece el valor al atributo tiempo.
	 *
	 * @param tiempo El valor para el atributo tiempo.
	 */
	/*
	 * OBSERVACION: Falta implementar este m�todo.
	 */
	public static void establecerTiempo(Tiempo tiempo)
	{
	}
}