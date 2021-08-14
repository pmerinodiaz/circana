/**
 * @(#)ConfiguracionAC.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que contiene solamente atributos con valores estáticos, que sirven para
 * mantener la información personalizada del usuario en el autómata celular
 * usado en la dinámica del ecosistema marino de estudio. Esta clase permite
 * guardar en memoria RAM los valores de la configuración del autómata celular.
 * La configuración es cargada y guardada en la base de datos. Esta clase se
 * comunica con la base de datos MySQL, por lo tanto, esta clase sirve como
 * interfaz entre el autómata celular del ecosistema y la base de datos.
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
	 * La tabla de donde se obtienen y almacenan los datos de la configuración
	 * del autómata celular.
	 */
	private final static BaseDatoTablaBasica CONFIGURACION_AC =
		new BaseDatoTablaBasica(Proyecto.CONEXION, "configuracion_ac");
	
	/** El tipo de límite del espacio. */
	private static int limite;
	
	/** El tipo de vecindad del autómata celular. */
	private static int vecindad;
	
	/** La unidad de tiempo del autómata celular. */
	private static int tiempo;
	
	/**
	 * Método que carga los datos de una configuración del autómata celular
	 * usado en la dinámica espacial con un código conocido desde la base de
	 * datos. En esta clase se inicializan todos los atributos que tiene la
	 * clase.
	 *
	 * @param codigoProyecto El código del proyecto del cual se obtienen los
	 *                       datos de la configuración del autómata celular.
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
		
		// Capturar la excepción y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla configuracion_ac.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * Método que guarda los datos de una configuración del autómata celuluar
	 * usado en la dinámica espacial con un código conocido en la base de datos.
	 * En esta clase se guardan todos los valores de los atributos que tiene la
	 * clase.
	 *
	 * @param codigoProyecto El código del proyecto al cual se modifican los
	 *                       datos de la configuración del autómata celular.
	 */
	public static void guardar(int codigoProyecto)
	{
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Actualizar los campos con los nuevos valores.
		CONFIGURACION_AC.actualizarCampo("codigo_limite", ""+limite);
		CONFIGURACION_AC.actualizarCampo("codigo_vecindad", ""+vecindad);
		CONFIGURACION_AC.actualizarCampo("codigo_tiempo", ""+tiempo);
		
		// Ejecutar la actualización en la tabla.
		CONFIGURACION_AC.actualizar("codigo_proyecto", ""+codigoProyecto);
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Método que agrega un registro de configuración del autómata celular en la
     * base de datos. Este registro se agrega con el código del proyecto que se
     * creó.
	 *
	 * @param codigoProyecto El código del proyecto del cual se agregan los
	 *                       datos de la configuración del autómata celular.
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
		
		// Ejecutar la agregación en la tabla.
		CONFIGURACION_AC.agregar();
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * Método que establece un valor al atributo limite.
	 *
	 * @param limite El valor para el atributo limite.
	 */
	public static void establecerLimite(int limite)
	{
		ConfiguracionAC.limite = limite;
	}
	
	/**
	 * Método que obtiene el valor del atributo limite.
	 *
	 * @return limite El valor del atributo limite.
	 */
	public static int obtenerLimite()
	{
		return limite;
	}
	
	/**
	 * Método que establece un valor al atributo vecindad.
	 *
	 * @param vecindad El valor para el atributo vecindad.
	 */
	public static void establecerVecindad(int vecindad)
	{
		ConfiguracionAC.vecindad = vecindad;
	}
	
	/**
	 * Método que obtiene el valor del atributo vecindad.
	 *
	 * @return vecindad El valor del atributo vecindad.
	 */
	public static int obtenerVecindad()
	{
		return vecindad;
	}
	
	/**
	 * Método que establece un valor al atributo tiempo.
	 *
	 * @param tiempo El valor para el atributo tiempo.
	 */
	public static void establecerTiempo(int tiempo)
	{
		ConfiguracionAC.tiempo = tiempo;
	}
	
	/**
	 * Método que obtiene el valor del atributo tiempo.
	 *
	 * @return tiempo El valor del atributo tiempo.
	 */
	public static int obtenerTiempo()
	{
		return tiempo;
	}
}