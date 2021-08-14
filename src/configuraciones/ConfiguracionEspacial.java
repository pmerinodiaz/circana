/**
 * @(#)ConfiguracionEspacial.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que contiene solamente atributos con valores estáticos, que sirven para
 * mantener la información personalizada del usuario en la dinámica espacial del
 * ecosistema marino de estudio. Esta clase permite guardar en memoria RAM los
 * valores de la configuración de la dinámica espacial. La configuración es
 * cargada y guardada en la base de datos. Esta clase se comunica con la base de
 * datos MySQL, por lo tanto, esta clase sirve como interfaz entre la dinámica
 * espacial del ecosistema y la base de datos.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see ResultSet
 * @see SQLException
 * @see BaseDatoTablaBasica
 * @see Proyecto
 */
public class ConfiguracionEspacial
{
	/**
	 * La tabla de donde se obtienen y almacenan los datos de la configuración
	 * espacial.
	 */
	private final static BaseDatoTablaBasica CONFIGURACION_ESPACIAL =
		new BaseDatoTablaBasica(Proyecto.CONEXION, "configuracion_espacial");
	
	/** El código de la vista del mapa. */
	private static int vista;
	
	/** El código del tipo de longitud inicial del mapa. */
	private static int tipoLongitudInicial;
	
	/** El código del tipo de longitud final del mapa. */
	private static int tipoLongitudFinal;
	
	/** El código del tipo de latitud inicial del mapa. */
	private static int tipoLatitudInicial;
	
	/** El código del tipo de latitud final del mapa. */
	private static int tipoLatitudFinal;
	
	/** Longitud inicial del mapa. */
	private static double longitudInicial;
	
	/** Longitud final del mapa. */
	private static double longitudFinal;
	
	/** Latitud inicial del mapa. */
	private static double latitudInicial;
	
	/** Latitud final del mapa. */
	private static double latitudFinal;
	
	/** Altitud inicial del mapa. */
	private static double altitudInicial;
	
	/** Altitud final del mapa. */
	private static double altitudFinal;
	
	/**
	 * Método que carga los datos de una configuración de la dinámica espacial
	 * con un código conocido desde la base de datos. En esta clase se
	 * inicializan todos los atributos que tiene la clase.
	 *
	 * @param codigoProyecto El código del proyecto del cual se obtienen los
	 *                       datos de la configuración espacial.
	 */
	public static void cargar(int codigoProyecto)
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Realizar la consulta.
		resultado = CONFIGURACION_ESPACIAL.buscar("codigo_proyecto",
												  ""+codigoProyecto);
		
		// Setear los valores de los atributos.
		try
		{
			// Apuntar al primer registro.
			resultado.first();
			
			// Setear los valores.
			vista =
				resultado.getInt("codigo_vista");
			tipoLongitudInicial =
				resultado.getInt("codigo_longitud_inicial");
			tipoLongitudFinal =
				resultado.getInt("codigo_longitud_final");
			tipoLatitudInicial =
				resultado.getInt("codigo_latitud_inicial");
			tipoLatitudFinal =
				resultado.getInt("codigo_latitud_final");
			longitudInicial =
				resultado.getDouble("longitud_inicial_configuracion_espacial");
			longitudFinal =
				resultado.getDouble("longitud_final_configuracion_espacial");
			latitudInicial =
				resultado.getDouble("latitud_inicial_configuracion_espacial");
			latitudFinal =
				resultado.getDouble("latitud_final_configuracion_espacial");
			altitudInicial =
				resultado.getDouble("altitud_inicial_configuracion_espacial");
			altitudFinal =
				resultado.getDouble("altitud_final_configuracion_espacial");
		}
		
		// Capturar la excepción y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla "+
							   "configuracion_espacial.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * Método que guarda los datos de una configuración de la dinámica espacial
	 * con un código conocido en la base de datos. En esta clase se guardan
	 * todos los valores de los atributos que tiene la clase.
	 *
	 * @param codigoProyecto El código del proyecto al cual se modifican los
	 *                       datos de la configuración espacial.
	 */
	public static void guardar(int codigoProyecto)
	{
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Actualizar los campos con los nuevos valores.
		CONFIGURACION_ESPACIAL.actualizarCampo("codigo_vista", ""+vista);
		CONFIGURACION_ESPACIAL.actualizarCampo("codigo_longitud_inicial",
											   ""+tipoLongitudInicial);
		CONFIGURACION_ESPACIAL.actualizarCampo("codigo_longitud_final",
											   ""+tipoLongitudFinal);
		CONFIGURACION_ESPACIAL.actualizarCampo("codigo_latitud_inicial",
											   ""+tipoLatitudInicial);
		CONFIGURACION_ESPACIAL.actualizarCampo("codigo_latitud_final",
											   ""+tipoLatitudFinal);
		CONFIGURACION_ESPACIAL.actualizarCampo(
			"longitud_inicial_configuracion_espacial", ""+longitudInicial);
		CONFIGURACION_ESPACIAL.actualizarCampo(
			"longitud_final_configuracion_espacial", ""+longitudFinal);
		CONFIGURACION_ESPACIAL.actualizarCampo(
			"latitud_inicial_configuracion_espacial", ""+latitudInicial);
		CONFIGURACION_ESPACIAL.actualizarCampo(
			"latitud_final_configuracion_espacial", ""+latitudFinal);
		CONFIGURACION_ESPACIAL.actualizarCampo(
			"altitud_inicial_configuracion_espacial", ""+altitudInicial);
		CONFIGURACION_ESPACIAL.actualizarCampo(
			"altitud_final_configuracion_espacial", ""+altitudFinal);
		
		// Ejecutar la actualización en la tabla.
		CONFIGURACION_ESPACIAL.actualizar("codigo_proyecto", ""+codigoProyecto);
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Método que agrega un registro de configuración espacial en la base de
     * datos. Este registro se agrega con el código del proyecto que se creó.
	 *
	 * @param codigoProyecto El código del proyecto del cual se agregan los
	 *                       datos de la configuración espacial.
     */
	public static void agregar(int codigoProyecto)
	{
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Agregar los campos con los nuevos valores.
		CONFIGURACION_ESPACIAL.agregarCampo("codigo_proyecto",
											""+codigoProyecto);
		CONFIGURACION_ESPACIAL.agregarCampo("codigo_vista", ""+vista);
		CONFIGURACION_ESPACIAL.agregarCampo("codigo_longitud_inicial",
											""+tipoLongitudInicial);
		CONFIGURACION_ESPACIAL.agregarCampo("codigo_longitud_final",
											""+tipoLongitudFinal);
		CONFIGURACION_ESPACIAL.agregarCampo("codigo_latitud_inicial",
											""+tipoLatitudInicial);
		CONFIGURACION_ESPACIAL.agregarCampo("codigo_latitud_final",
											""+tipoLatitudFinal);
		CONFIGURACION_ESPACIAL.agregarCampo(
			"longitud_inicial_configuracion_espacial", ""+longitudInicial);
		CONFIGURACION_ESPACIAL.agregarCampo(
			"longitud_final_configuracion_espacial", ""+longitudFinal);
		CONFIGURACION_ESPACIAL.agregarCampo(
			"latitud_inicial_configuracion_espacial", ""+latitudInicial);
		CONFIGURACION_ESPACIAL.agregarCampo(
			"latitud_final_configuracion_espacial", ""+latitudFinal);
		CONFIGURACION_ESPACIAL.agregarCampo(
			"altitud_inicial_configuracion_espacial", ""+altitudInicial);
		CONFIGURACION_ESPACIAL.agregarCampo(
			"altitud_final_configuracion_espacial", ""+altitudFinal);
		
		// Ejecutar la agregación en la tabla.
		CONFIGURACION_ESPACIAL.agregar();
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * Método que establece un valor al atributo vista.
	 *
	 * @param vista El valor para el atributo vista.
	 */
	public static void establecerVista(int vista)
	{
		ConfiguracionEspacial.vista = vista;
	}
	
	/**
	 * Método que obtiene el valor del atributo vista.
	 *
	 * @return vista El valor del atributo vista.
	 */
	public static int obtenerVista()
	{
		return vista;
	}
	
	/**
	 * Método que establece un valor al atributo tipoLongitudInicial.
	 *
	 * @param tipoLongitudInicial El valor para el atributo tipoLongitudInicial.
	 */
	public static void establecerTipoLongitudInicial(int tipoLongitudInicial)
	{
		ConfiguracionEspacial.tipoLongitudInicial = tipoLongitudInicial;
	}
	
	/**
	 * Método que obtiene el valor del atributo tipoLongitudInicial.
	 *
	 * @return tipoLongitudInicial El valor del atributo tipoLongitudInicial.
	 */
	public static int obtenerTipoLongitudInicial()
	{
		return tipoLongitudInicial;
	}
	
	/**
	 * Método que establece un valor al atributo tipoLongitudFinal.
	 *
	 * @param tipoLongitudFinal El valor para el atributo tipoLongitudFinal.
	 */
	public static void establecerTipoLongitudFinal(int tipoLongitudFinal)
	{
		ConfiguracionEspacial.tipoLongitudFinal = tipoLongitudFinal;
	}
	
	/**
	 * Método que obtiene el valor del atributo tipoLongitudFinal.
	 *
	 * @return tipoLongitudFinal El valor del atributo tipoLongitudFinal.
	 */
	public static int obtenerTipoLongitudFinal()
	{
		return tipoLongitudFinal;
	}
	
	/**
	 * Método que establece un valor al atributo tipoLatitudInicial.
	 *
	 * @param tipoLatitudInicial El valor para el atributo tipoLatitudInicial.
	 */
	public static void establecerTipoLatitudInicial(int tipoLatitudInicial)
	{
		ConfiguracionEspacial.tipoLatitudInicial = tipoLatitudInicial;
	}
	
	/**
	 * Método que obtiene el valor del atributo tipoLatitudInicial.
	 *
	 * @return tipoLatitudInicial El valor del atributo tipoLatitudInicial.
	 */
	public static int obtenerTipoLatitudInicial()
	{
		return tipoLatitudInicial;
	}
	
	/**
	 * Método que establece un valor al atributo tipoLatitudFinal.
	 *
	 * @param tipoLatitudFinal El valor para el atributo tipoLatitudFinal.
	 */
	public static void establecerTipoLatitudFinal(int tipoLatitudFinal)
	{
		ConfiguracionEspacial.tipoLatitudFinal = tipoLatitudFinal;
	}
	
	/**
	 * Método que obtiene el valor del atributo tipoLatitudFinal.
	 *
	 * @return tipoLatitudFinal El valor del atributo tipoLatitudFinal.
	 */
	public static int obtenerTipoLatitudFinal()
	{
		return tipoLatitudFinal;
	}
	
	/**
	 * Método que establece un valor al atributo longitudInicial.
	 *
	 * @param longitudInicial El valor para el atributo longitudInicial.
	 */
	public static void establecerLongitudInicial(double longitudInicial)
	{
		ConfiguracionEspacial.longitudInicial = longitudInicial;
	}
	
	/**
	 * Método que obtiene el valor del atributo longitudInicial.
	 *
	 * @return longitudInicial El valor del atributo longitudInicial.
	 */
	public static double obtenerLongitudInicial()
	{
		return longitudInicial;
	}
	
	/**
	 * Método que establece un valor al atributo longitudFinal.
	 *
	 * @param longitudFinal El valor para el atributo longitudFinal.
	 */
	public static void establecerLongitudFinal(double longitudFinal)
	{
		ConfiguracionEspacial.longitudFinal = longitudFinal;
	}
	
	/**
	 * Método que establece el valor del atributo longitudFinal.
	 *
	 * @return longitudFinal El valor del atributo longitudFinal.
	 */
	public static double obtenerLongitudFinal()
	{
		return longitudFinal;
	}
	
	/**
	 * Método que establece un valor al atributo latitudInicial.
	 *
	 * @param latitudInicial El valor para el atributo latitudInicial.
	 */
	public static void establecerLatitudInicial(double latitudInicial)
	{
		ConfiguracionEspacial.latitudInicial = latitudInicial;
	}
	
	/**
	 * Método que obtiene el valor del atributo latitudInicial.
	 *
	 * @return latitudInicial El valor del atributo latitudInicial.
	 */
	public static double obtenerLatitudInicial()
	{
		return latitudInicial;
	}
	
	/**
	 * Método que establece un valor al atributo latitudFinal.
	 *
	 * @param latitudFinal El valor para el atributo latitudFinal.
	 */
	public static void establecerLatitudFinal(double latitudFinal)
	{
		ConfiguracionEspacial.latitudFinal = latitudFinal;
	}
	
	/**
	 * Método que obtiene el valor del atributo latitudFinal.
	 *
	 * @return latitudFinal El valor del atributo latitudFinal.
	 */
	public static double obtenerLatitudFinal()
	{
		return latitudFinal;
	}
	
	/**
	 * Método que establece un valor al atributo altitudInicial.
	 *
	 * @param altitudInicial El valor para el atributo altitudInicial.
	 */
	public static void establecerAltitudInicial(double altitudInicial)
	{
		ConfiguracionEspacial.altitudInicial = altitudInicial;
	}
	
	/**
	 * Método que obtiene el valor del atributo altitudInicial.
	 *
	 * @return altitudInicial El valor del atributo altitudInicial.
	 */
	public static double obtenerAltitudInicial()
	{
		return altitudInicial;
	}
	
	/**
	 * Método que establece un valor al atributo altitudFinal.
	 *
	 * @param altitudFinal El valor para el atributo altitudFinal.
	 */
	public static void establecerAltitudFinal(double altitudFinal)
	{
		ConfiguracionEspacial.altitudFinal = altitudFinal;
	}
	
	/**
	 * Método que obtiene el valor del atributo altitudFinal.
	 *
	 * @return altitudFinal El valor del atributo altitudFinal.
	 */
	public static double obtenerAltitudFinal()
	{
		return altitudFinal;
	}
}