/**
 * @(#)ConfiguracionHeuristica.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que contiene solamente atributos con valores est�ticos, que sirven para
 * mantener la informaci�n personalizada del usuario en la heur�stica usado en
 * la integraci�n de los modelos. Esta clase permite guardar en memoria RAM los
 * valores de la configuraci�n de la heur�stica. La configuraci�n es cargada y
 * guardada en la base de datos. Esta clase se comunica con la base de datos
 * MySQL, por lo tanto, esta clase sirve como interfaz entre la heur�stica de la
 * integraci�n y la base de datos.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see ResultSet
 * @see SQLException
 * @see BaseDatoTablaBasica
 * @see Proyecto
 */
public class ConfiguracionHeuristica
{
	/**
	 * La tabla de donde se obtienen y almacenan los datos de la configuraci�n
	 * de la heur�stica.
	 */
	private final static BaseDatoTablaBasica CONFIGURACION_HEURISTICA =
		new BaseDatoTablaBasica(Proyecto.CONEXION, "configuracion_heuristica");
	
	/** El tipo de heur�stica. */
	private static int heuristica;
	
	/** El tipo de red neuronal. */
	private static int redNeuronal;
	
	/** El stock acumulado. */
	private static double stockAcumulado;
	
	/**
	 * M�todo que carga los datos de una configuraci�n de la heur�stica usada en
	 * la heur�stica con un c�digo conocido desde la base de datos. En esta
	 * clase se inicializan todos los atributos que tiene la clase.
	 *
	 * @param codigoProyecto El c�digo del proyecto del cual se obtienen los
	 *                       datos de la configuraci�n de la heur�stica.
	 */
	public static void cargar(int codigoProyecto)
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Realizar la consulta.
		resultado = CONFIGURACION_HEURISTICA.buscar("codigo_proyecto",
													""+codigoProyecto);
		
		// Setear los valores de los atributos.
		try
		{
			// Apuntar al primer registro.
			resultado.first();
			
			// Setear los valores.
			heuristica = resultado.getInt("codigo_tipo_heuristica");
			redNeuronal = resultado.getInt("codigo_tipo_red_neuronal");
			stockAcumulado = resultado.getDouble(
				"stock_acumulado_configuracion_heuristica");
		}
		
		// Capturar la excepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla " +
							   "configuracion_heuristica.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * M�todo que guarda los datos de una configuraci�n de la heur�stica usada
	 * en la integraci�n con un c�digo conocido en la base de datos. En esta
	 * clase se guardan todos los valores de los atributos que tiene la clase.
	 *
	 * @param codigoProyecto El c�digo del proyecto al cual se modifican los
	 *                       datos de la configuraci�n de la heur�stica.
	 */
	public static void guardar(int codigoProyecto)
	{
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Actualizar los campos con los nuevos valores.
		CONFIGURACION_HEURISTICA.actualizarCampo(
			"codigo_tipo_heuristica", "" + heuristica);
		CONFIGURACION_HEURISTICA.actualizarCampo(
			"codigo_tipo_red_neuronal", "" + redNeuronal);
		CONFIGURACION_HEURISTICA.actualizarCampo(
			"stock_acumulado_configuracion_heuristica", "" + stockAcumulado);
		
		// Ejecutar la actualizaci�n en la tabla.
		CONFIGURACION_HEURISTICA.actualizar("codigo_proyecto", ""+codigoProyecto);
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * M�todo que agrega un registro de configuraci�n de la heur�stica en la
     * base de datos. Este registro se agrega con el c�digo del proyecto que se
     * cre�.
	 *
	 * @param codigoProyecto El c�digo del proyecto del cual se agregan los
	 *                       datos de la configuraci�n de la heur�stica.
     */
	public static void agregar(int codigoProyecto)
	{
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Agregar los campos con los nuevos valores.
		CONFIGURACION_HEURISTICA.agregarCampo(
			"codigo_proyecto", "" + codigoProyecto);
		CONFIGURACION_HEURISTICA.agregarCampo(
			"codigo_tipo_heuristica", "" + heuristica);
		CONFIGURACION_HEURISTICA.agregarCampo(
			"codigo_tipo_red_neuronal", "" + redNeuronal);
		CONFIGURACION_HEURISTICA.agregarCampo(
			"stock_acumulado_configuracion_heuristica", "" + stockAcumulado);
		
		// Ejecutar la agregaci�n en la tabla.
		CONFIGURACION_HEURISTICA.agregar();
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * M�todo que establece un valor al atributo heuristica.
	 *
	 * @param heuristica El valor para el atributo heuristica.
	 */
	public static void establecerHeuristica(int heuristica)
	{
		ConfiguracionHeuristica.heuristica = heuristica;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo heuristica.
	 *
	 * @return heuristica El valor del atributo heuristica.
	 */
	public static int obtenerHeuristica()
	{
		return heuristica;
	}
	
	/**
	 * M�todo que establece un valor al atributo redNeuronal.
	 *
	 * @param redNeuronal El valor para el atributo redNeuronal.
	 */
	public static void establecerRedNeuronal(int redNeuronal)
	{
		ConfiguracionHeuristica.redNeuronal = redNeuronal;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo redNeuronal.
	 *
	 * @return redNeuronal El valor del atributo redNeuronal.
	 */
	public static int obtenerRedNeuronal()
	{
		return redNeuronal;
	}
	
	/**
	 * M�todo que establece un valor al atributo stock acumulado.
	 *
	 * @param stockAcumulado El valor para el atributo stock acumulado.
	 */
	public static void establecerStockAcumulado(double stockAcumulado)
	{
		ConfiguracionHeuristica.stockAcumulado = stockAcumulado;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo stock acumulado.
	 *
	 * @return stockAcumulado El valor del atributo stockAcumulado.
	 */
	public static double obtenerStockAcumulado()
	{
		return stockAcumulado;
	}
}