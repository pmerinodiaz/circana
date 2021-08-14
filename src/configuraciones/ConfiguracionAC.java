/**
 * @(#)ConfiguracionAC.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que contiene solamente atributos con valores est�ticos, que sirven para
 * mantener la informaci�n personalizada del usuario en el aut�mata celular
 * usado en la din�mica del ecosistema marino de estudio. Esta clase permite
 * guardar en memoria RAM los valores de la configuraci�n del aut�mata celular.
 * La configuraci�n es cargada y guardada en la base de datos. Esta clase se
 * comunica con la base de datos MySQL, por lo tanto, esta clase sirve como
 * interfaz entre el aut�mata celular del ecosistema y la base de datos.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see ResultSet
 * @see SQLException
 * @see BaseDatoTablaBasica
 * @see Proyecto
 */
public class ConfiguracionAC
{
	/**
	 * La tabla de donde se obtienen y almacenan los datos de la configuraci�n
	 * del aut�mata celular.
	 */
	private final static BaseDatoTablaBasica CONFIGURACION_AC =
		new BaseDatoTablaBasica(Proyecto.CONEXION, "configuracion_ac");
	
	/** El tipo de l�mite del espacio. */
	private static int limite;
	
	/** El tipo de vecindad del aut�mata celular. */
	private static int vecindad;
	
	/** La unidad de tiempo del aut�mata celular. */
	private static int tiempo;
	
	/**
	 * M�todo que carga los datos de una configuraci�n del aut�mata celular
	 * usado en la din�mica espacial con un c�digo conocido desde la base de
	 * datos. En esta clase se inicializan todos los atributos que tiene la
	 * clase.
	 *
	 * @param codigoProyecto El c�digo del proyecto del cual se obtienen los
	 *                       datos de la configuraci�n del aut�mata celular.
	 */
	public static void cargar(int codigoProyecto)
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Realizar la consulta.
		resultado = CONFIGURACION_AC.buscar("codigo_proyecto",
											""+codigoProyecto);
		
		// Setear los valores de los atributos.
		try
		{
			// Apuntar al primer registro.
			resultado.first();
			
			// Setear los valores.
			limite = resultado.getInt("codigo_limite");
			vecindad = resultado.getInt("codigo_vecindad");
			tiempo = resultado.getInt("codigo_tiempo");
		}
		
		// Capturar la excepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla configuracion_ac.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * M�todo que guarda los datos de una configuraci�n del aut�mata celuluar
	 * usado en la din�mica espacial con un c�digo conocido en la base de datos.
	 * En esta clase se guardan todos los valores de los atributos que tiene la
	 * clase.
	 *
	 * @param codigoProyecto El c�digo del proyecto al cual se modifican los
	 *                       datos de la configuraci�n del aut�mata celular.
	 */
	public static void guardar(int codigoProyecto)
	{
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Actualizar los campos con los nuevos valores.
		CONFIGURACION_AC.actualizarCampo("codigo_limite", ""+limite);
		CONFIGURACION_AC.actualizarCampo("codigo_vecindad", ""+vecindad);
		CONFIGURACION_AC.actualizarCampo("codigo_tiempo", ""+tiempo);
		
		// Ejecutar la actualizaci�n en la tabla.
		CONFIGURACION_AC.actualizar("codigo_proyecto", ""+codigoProyecto);
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * M�todo que agrega un registro de configuraci�n del aut�mata celular en la
     * base de datos. Este registro se agrega con el c�digo del proyecto que se
     * cre�.
	 *
	 * @param codigoProyecto El c�digo del proyecto del cual se agregan los
	 *                       datos de la configuraci�n del aut�mata celular.
     */
	public static void agregar(int codigoProyecto)
	{
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Agregar los campos con los nuevos valores.
		CONFIGURACION_AC.agregarCampo("codigo_proyecto", ""+codigoProyecto);
		CONFIGURACION_AC.agregarCampo("codigo_limite", ""+limite);
		CONFIGURACION_AC.agregarCampo("codigo_vecindad", ""+vecindad);
		CONFIGURACION_AC.agregarCampo("codigo_tiempo", ""+tiempo);
		
		// Ejecutar la agregaci�n en la tabla.
		CONFIGURACION_AC.agregar();
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * M�todo que establece un valor al atributo limite.
	 *
	 * @param limite El valor para el atributo limite.
	 */
	public static void establecerLimite(int limite)
	{
		ConfiguracionAC.limite = limite;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo limite.
	 *
	 * @return limite El valor del atributo limite.
	 */
	public static int obtenerLimite()
	{
		return limite;
	}
	
	/**
	 * M�todo que establece un valor al atributo vecindad.
	 *
	 * @param vecindad El valor para el atributo vecindad.
	 */
	public static void establecerVecindad(int vecindad)
	{
		ConfiguracionAC.vecindad = vecindad;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo vecindad.
	 *
	 * @return vecindad El valor del atributo vecindad.
	 */
	public static int obtenerVecindad()
	{
		return vecindad;
	}
	
	/**
	 * M�todo que establece un valor al atributo tiempo.
	 *
	 * @param tiempo El valor para el atributo tiempo.
	 */
	public static void establecerTiempo(int tiempo)
	{
		ConfiguracionAC.tiempo = tiempo;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tiempo.
	 *
	 * @return tiempo El valor del atributo tiempo.
	 */
	public static int obtenerTiempo()
	{
		return tiempo;
	}
}