/**
 * @(#)ConfiguracionRecurso.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que contiene solamente atributos con valores estáticos, que sirven para
 * mantener la información personalizada del usuario con los recursos del
 * ecosistema marino de estudio. Esta clase permite guardar en memoria RAM los
 * valores de la configuración de los recursos del ecosistema. La configuración
 * es cargada y guardada en la base de datos. Esta clase se comunica con la base
 * de datos MySQL, por lo tanto, esta clase sirve como interfaz entre la
 * configuración de los recursos del ecosistema y la base de datos.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see ResultSet
 * @see SQLException
 * @see BaseDatoTablaBasica
 * @see Proyecto
 */
public class ConfiguracionRecurso
{
	/**
	 * La tabla de donde se obtienen y almacenan los datos de la configuración
	 * de los recursos.
	 */
	private final static BaseDatoTablaBasica CONFIGURACION_RECURSO =
		new BaseDatoTablaBasica(Proyecto.CONEXION, "configuracion_recurso");
	
	/** Las calidades de los recursos. */
	private static int[] calidad;
	
	/** Los mostrar en la dinámica espacial de los recursos. */
	private static String[] espacial;
	
	/** Los mostrar en la dinámica temporal de los recursos. */
	private static String[] temporal;
	
	/** Los porcentajes de los recursos. */
	private static double[] porcentaje;
	
	/**
	 * Método que carga los datos de una configuración de los recursos con
	 * código conocido desde la base de datos. En esta clase se inicializan
	 * todos los atributos que tiene la clase.
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
		resultado = CONFIGURACION_RECURSO.buscar("codigo_proyecto",
												 ""+codigoProyecto);
		
		// Setear los valores de los atributos.
		try
		{
			// Posicionar en el último registro.
			resultado.last();
			
			// Establecer el número de registros del resultado.
			int cantidadRecursos = resultado.getRow();
			
			// Asignar espacio en memoria a todos los arreglos.
			calidad = new int[cantidadRecursos];
			espacial = new String[cantidadRecursos];
			temporal = new String[cantidadRecursos];
			porcentaje = new double[cantidadRecursos];
			
			// Apuntar al primer registro.
			resultado.first();
			
			// Ciclo para recorrer los resultados.
			int indice = 0;
			do
			{
				// Setear los valores.
				calidad[indice] =
					resultado.getInt("codigo_tipo_calidad");
				espacial[indice] =
					resultado.getString("espacial_configuracion_recurso");
				temporal[indice] = 
					resultado.getString("temporal_configuracion_recurso");
				porcentaje[indice] =
					resultado.getDouble("porcentaje_configuracion_recurso");
				
				// Posicionar en el siguiente registro.
				resultado.next();
				
				// Incrementar el índice.
				indice++;
			}
			while (!resultado.isAfterLast());
		}
		
		// Capturar la excepción y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla "+
							   "configuracion_recurso.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * Método que guarda los datos de los recursos usados en el ecosistema en la
	 * base de datos. En esta clase se guardan todos los valores de los
	 * atributos que tiene la clase.
	 *
	 * @param codigoProyecto El código del proyecto al cual se modifican los
	 *                       datos de la configuración de los recursos.
	 */
	public static void guardar(int codigoProyecto)
	{
		// Obtener la cantidad de recursos.
		int cantidadRecursos = Recurso.obtenerCantidadRecursos();
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Ciclo para actualizar los registros.
		for (int recurso=0; recurso<cantidadRecursos; recurso++)
		{
			// Actualizar los campos con los nuevos valores.
			CONFIGURACION_RECURSO.actualizarCampo(
				"codigo_tipo_calidad", ""+calidad[recurso]);
			CONFIGURACION_RECURSO.actualizarCampo(
				"espacial_configuracion_recurso", "'"+espacial[recurso]+"'");
			CONFIGURACION_RECURSO.actualizarCampo(
				"temporal_configuracion_recurso", "'"+temporal[recurso]+"'");
			CONFIGURACION_RECURSO.actualizarCampo(
				"porcentaje_configuracion_recurso", ""+porcentaje[recurso]);
			
			// Ejecutar la actualización en la tabla.
			CONFIGURACION_RECURSO.actualizarCondicion(
				"codigo_proyecto = "+codigoProyecto+" and "+
				"codigo_recurso = "+recurso);
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Método que agrega los registros de configuración de los recursos del 
     * ecosistema en la la base de datos. Este registro se agrega según el
     * proyecto en donde se creó.
	 *
	 * @param codigoProyecto El código del proyecto del cual se agregan los
	 *                       datos de la configuración de los recursos.
     */
	public static void agregar(int codigoProyecto)
	{
		// Variables temporales.
		String consulta;
		
		// Obtener la cantidad de recursos.
		int cantidadRecursos = Recurso.obtenerCantidadRecursos();
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Ciclo para agregar los registros.
		for (int recurso=0; recurso<cantidadRecursos; recurso++)
		{
			// Agregar los campos con los nuevos valores.
			CONFIGURACION_RECURSO.agregarCampo(
				"codigo_proyecto", ""+codigoProyecto);
			CONFIGURACION_RECURSO.agregarCampo(
				"codigo_recurso", ""+recurso);
			CONFIGURACION_RECURSO.agregarCampo(
				"codigo_tipo_calidad", ""+calidad[recurso]);
			CONFIGURACION_RECURSO.agregarCampo(
				"espacial_configuracion_recurso", "'"+espacial[recurso]+"'");
			CONFIGURACION_RECURSO.agregarCampo(
				"temporal_configuracion_recurso", "'"+temporal[recurso]+"'");
			CONFIGURACION_RECURSO.agregarCampo(
				"porcentaje_configuracion_recurso", ""+porcentaje[recurso]);
			
			// Ejecutar la agregación en la tabla.
			CONFIGURACION_RECURSO.agregar();
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * Método que establece un valor al atributo calidad con un índice conocido.
	 *
	 * @param calidad El valor para el atributo calidad.
	 * @param indice El índice del arreglo calidad.
	 */
	public static void establecerCalidad(int calidad, int indice)
	{
		ConfiguracionRecurso.calidad[indice] = calidad;
	}
	
	/**
	 * Método que obtiene el valor del atributo calidad con un índice conocido.
	 *
	 * @param indice El índice del arreglo calidad.
	 *
	 * @return calidad[indice] El valor del atributo calidad con un índice
	 *                         conocido.
	 */
	public static int obtenerCalidad(int indice)
	{
		return calidad[indice];
	}
	
	/**
	 * Método que establece un valor al atributo espacial con un índice
	 * conocido.
	 *
	 * @param espacial El valor para el atributo espacial.
	 * @param indice El índice del arreglo espacial.
	 */
	public static void establecerEspacial(String espacial, int indice)
	{
		ConfiguracionRecurso.espacial[indice] = espacial;
	}
	
	/**
	 * Método que obtiene el valor del atributo espacial con un índice conocido.
	 *
	 * @param indice El índice del arreglo espacial.
	 *
	 * @return espacial[indice] El valor del atributo espacial con un índice
	 *                          conocido.
	 */
	public static String obtenerEspacial(int indice)
	{
		return espacial[indice];
	}
	
	/**
	 * Método que establece un valor al atributo temporal con un índice
	 * conocido.
	 *
	 * @param temporal El valor para el atributo temporal.
	 * @param indice El índice del arreglo temporal.
	 */
	public static void establecerTemporal(String temporal, int indice)
	{
		ConfiguracionRecurso.temporal[indice] = temporal;
	}
	
	/**
	 * Método que obtiene el valor del atributo temporal con un índice conocido.
	 *
	 * @param indice El índice del arreglo temporal.
	 *
	 * @return temporal[indice] El valor del atributo temporal con un índice
	 *                          conocido.
	 */
	public static String obtenerTemporal(int indice)
	{
		return temporal[indice];
	}
	
	/**
	 * Método que establece un valor al atributo porcentaje con un índice
	 * conocido.
	 *
	 * @param porcentaje El valor para el atributo porcentaje.
	 * @param indice El índice del arreglo porcentaje.
	 */
	public static void establecerPorcentaje(double porcentaje, int indice)
	{
		ConfiguracionRecurso.porcentaje[indice] = porcentaje;
	}
	
	/**
	 * Método que obtiene el valor del atributo porcentaje con un índice
	 * conocido.
	 *
	 * @param indice El índice del arreglo porcentaje.
	 *
	 * @return porcentaje[indice] El valor del atributo porcentaje con un índice
	 *                            conocido.
	 */
	public static double obtenerPorcentaje(int indice)
	{
		return porcentaje[indice];
	}
}