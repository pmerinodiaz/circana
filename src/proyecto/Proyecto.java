/**
 * @(#)Proyecto.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;

/**
 * Clase que contiene solo m�todos y atributos est�ticos que permiten mantener
 * actualizada la informaci�n del proyecto que se est� usando. La informaci�n es
 * grupal o com�n entre todos los proyectos, pero no guarda informaci�n de
 * resultados sobre este proyecto. Adem�s, se agregan constantes propias que
 * definen ciertos estados de un proyecto.
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see ResultSet
 * @see SQLException
 * @see Calendar
 * @see Date 
 * @see DateFormat
 * @see BaseDatoMotor
 * @see BaseDatoTablaBasica
 */
public class Proyecto 
{
	/** Constante que indica el valor del c�digo de un nuevo proyecto. */
	public final static int CODIGO_NUEVO = 1;
	
	/** Constante que indica que el modelo es ecosistema. */
	public final static int ECOSISTEMA = 1;
	
	/** Constante que indica que el modelo es econom�a. */
	public final static int ECONOMIA = 2;
	
	/** Constante que indica que el modelo es operaci�n. */
	public final static int OPERACION = 3;
	
	/** Constante que indica que el modelo es integraci�n. */
	public final static int INTEGRACION = 4;
	
	/** Conexi�n a la base de datos. */
	public final static BaseDatoMotor CONEXION = new BaseDatoMotor();
	
	/** Tabla que representa a la tabla proyecto de la base de datos. */
	private final static BaseDatoTablaBasica TABLA =
		new BaseDatoTablaBasica(CONEXION, "proyecto");
	
	/** Contiene el c�digo del proyecto. */
	private static int codigo;
	
	/** Contiene el c�digo del recurso del proyecto. */
	private static int codigoRecurso;
	
	/** Contiene el c�digo de la regi�n del proyecto. */
	private static int codigoRegion;
	
	/** Contiene el c�digo de la empresa del proyecto. */
	private static int codigoEmpresa;
	
	/** Nombre del proyecto. */
	private static String nombre;
	
	/**
	 * Fecha inicial en que ocurre el an�lisis del proyecto. El formato del
	 * arreglo es (0:d�a, 1:mes, 2:a�o).
	 */
	private static String[] fechaInicial;
	
	/**
	 * Fecha final en que ocurre el an�lisis del proyecto. El formato del
	 * arreglo es (0:d�a, 1:mes, 2:a�o).
	 */
	private	static String[] fechaFinal;
	
	/** Contiene el nombre del autor del proyecto. */
	private static String autor;
	
	/** Contiene la versi�n del proyecto. */
	private static String version;
	
	/** Contiene la observaci�n del proyecto. */
	private static String observacion;
	
	/** Contiene el reporte ecosist�mico del proyecto. */
	private static String reporteEcosistema;
	
	/** Contiene el reporte econ�mico del proyecto. */
	private static String reporteEconomia;
	
	/** Contiene el reporte operativo del proyecto. */
	private static String reporteOperacion;
	
	/** Contiene el reporte integrado del proyecto. */
	private static String reporteIntegracion;
	
	/** Indica si el proyecto ha sido cambiado. */
	private static boolean modificado;
	
	/**
     * M�todo que inicializa las fechas de simulaci�n de sistema. Las fechas de
     * simulaci�n se inicializa como fecha inicial la fecha actual y la fecha
     * final se inicializa como la actual mes un a�o.
     */
	private static void inicializarFechasSimulacion()
	{
		// Variables locales.
		Calendar fecha =  Calendar.getInstance();
		
		// Crear los arreglos.
		fechaInicial = new String[3];
		fechaFinal = new String[3];
		
		// La fecha inicial es la fecha actual.
		fechaInicial[0] = "" + fecha.get(Calendar.DAY_OF_MONTH);
		fechaInicial[1] = "" + (fecha.get(Calendar.MONTH) + 1);
		fechaInicial[2] = "" + fecha.get(Calendar.YEAR);
		
		// La fecha final es la fecha actual m�s un a�o.
		fechaFinal[0] = "" + fecha.get(Calendar.DAY_OF_MONTH);
		fechaFinal[1] = "" + (fecha.get(Calendar.MONTH) + 1);
		fechaFinal[2] = "" + (fecha.get(Calendar.YEAR) + 1);
	}
	
	/**
     * M�todo que inicializa las fechas de simulaci�n de sistema. Las fechas de
     * simulaci�n se inicializa como fecha que se encuentra guardada en el
     * proyecto, tanto como fecha inicial y fecha final.
     *
     * @param fechaInicial Fecha inicial cargada de la base de datos. Esta se 
     *                     encuentra en formato String.
     * @param fechaFinal Fecha Final cargada de la base de datos. Esta se
     *                   encuentra en formato String.
     */
	private static void inicializarFechasSimulacion(String fechaInicial,
													String fechaFinal)
	{
		// Crear los arreglos.
		Proyecto.fechaInicial = new String[3];
		Proyecto.fechaFinal = new String[3];
		
		// Formatear la fecha inicial.
		Proyecto.fechaInicial[2] = fechaInicial.substring(0,4);
		Proyecto.fechaInicial[1] = fechaInicial.substring(5,7);
		Proyecto.fechaInicial[0] = fechaInicial.substring(8,10);
		
		// Formatear la fecha final.
		Proyecto.fechaFinal[2] = fechaFinal.substring(0,4);
		Proyecto.fechaFinal[1] = fechaFinal.substring(5,7);
		Proyecto.fechaFinal[0] = fechaFinal.substring(8,10);
	}
	
	/**
     * M�todo que carga la configuraci�n de un proyecto, dado un c�digo de
     * proyecto conocido.
     * 
     * @param codigo El valor del c�digo del proyecto.
     */
	public static void cargar(int codigo)
	{
		// Variables temporales.
		DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.LONG);
		ResultSet resultado;
		
		// Conectar a la base de datos.
		CONEXION.conectar();
		
		// Realizar la consulta.
		resultado = TABLA.buscar("codigo_proyecto", "" + codigo);
		
		try
		{
			// Situando en el primer elemento.
			resultado.first();
			
			// Obteniendo campos.
			codigoRecurso = resultado.getInt("codigo_recurso");
			codigoRegion = resultado.getInt("codigo_region");
			codigoEmpresa = resultado.getInt("codigo_empresa");
			nombre = resultado.getString("nombre_proyecto");
			autor = resultado.getString("autor_proyecto");
			version = resultado.getString("version_proyecto");
			observacion = resultado.getString("observacion_proyecto");
			reporteEcosistema = resultado.getString("reporte_ecosistema_proyecto");
			reporteEconomia = resultado.getString("reporte_economia_proyecto");
			reporteOperacion = resultado.getString("reporte_operacion_proyecto");
			reporteIntegracion = resultado.getString("reporte_integracion_proyecto");
			
			// Cuando es un nuevo proyecto.
			if (codigo == Proyecto.CODIGO_NUEVO)
				inicializarFechasSimulacion();
			
			// Cuando no es un nuevo proyecto.
			else inicializarFechasSimulacion(
				 resultado.getString("fecha_inicial_proyecto"),
				 resultado.getString("fecha_final_proyecto"));
		}
		catch (SQLException ex)
		{
			// Si no se pudo cargar proyecto.
			System.err.println("No se pudo cargar el proyecto.");
		}
		
		// Desconectar a la base de datos.
		CONEXION.desconectar();
		
		// Modificar los atributos de la clase.
		Proyecto.codigo = codigo;
		Proyecto.modificado = false;
		
		// Cargar las configuraciones de los modelos al proyecto cargado.
		Recurso.cargar();
		ConfiguracionEspacial.cargar(codigo);
		ConfiguracionAC.cargar(codigo);
		ConfiguracionRecurso.cargar(codigo);
		ConfiguracionNeuronalRBF.cargar(codigo);
		ConfiguracionNeuronalBP.cargar(codigo);
		ConfiguracionAGT.cargar(codigo);
		ConfiguracionAGR.cargar(codigo);
		ConfiguracionHeuristica.cargar(codigo);
	}
	
	/**
     * M�todo que carga la configuraci�n de un proyecto con el nombre del
     * proyecto conocido. Lo que hace es buscar el c�digo del proyecto seg�n el
     * nombre del proyecto. Cuando lo hace, se llama al metodo cargar(codigo).
     * 
     * @param nombre Contiene el nombre del proyecto.
     */
	public static void cargar(String nombre)
	{
		// Variables locales.
		int codigoTemporal = -2; // no iniciado
		ResultSet resultado;
		
		// Conectando a la base datos.
		CONEXION.conectar();
		
		// Ejecutando consulta.
		resultado = TABLA.buscar("nombre_proyecto","" + nombre);
		
		try
		{
			// Primer y �nico elemento.
			resultado.first();
			codigoTemporal = resultado.getInt("codigo_proyecto");
		}
		catch (SQLException ex)
		{
			// No se cargar proyecto.
			System.err.println ("No se pudo cargar el proyecto.");
		}
		
		// Desconectar a la base de datos.
		CONEXION.desconectar();
		
		// Ahora se llama al m�todo cargar con c�digo.
		cargar(codigoTemporal);
	}
	
	/**
     * Metodo que limpia los resultados de un modelo que est� asociado a un
     * proyecto. Esto porque un proyecto no debe tener dos conjuntos de
     * resultados almacenados. El proyecto que se usa es el que se encuentra
     * abierto actualmente.
     *
     * @param modelo El tipo de modelo del cual se quiere eliminar los datos
     *               asociados a al proyecto.
     * @param tipo El tipo adjuntado a la consulta de actualizaci�n en los
     *             resultados econ�micos. Este par�metro es usado para el caso
     *             de limpiar los resultados de uno de los dos tipos de redes
     *             neuronales. Cuando este par�metro es -1 no es usado. En caso
     *             contrario, es usado solo en el modelo econ�mico.
     */
	public static void limpiarResultados(int modelo, int tipo)
	{
		// Conectar a la base de datos.
		CONEXION.conectar();
		
		// Dependiendo del tipo de modelo, realizar las consultas de
		// actualizaci�n en el orden inverso (para los constraint de la BD).
		switch (modelo)
		{
			// Cuando es Ecosistema.
			case ECOSISTEMA:
			{
				// Eliminar en tabla caladero_resultado.
				CONEXION.ejecutarConsultaActualizacion(
					"DELETE FROM caladero_resultado " +
					"WHERE codigo_proyecto = " + codigo);
				
				// Eliminar en tabla dato_ecosistemico_resultado.
				CONEXION.ejecutarConsultaActualizacion(
					"DELETE FROM dato_ecosistemico_resultado " +
					"WHERE codigo_proyecto = " + codigo);
				
				// Actualizar en tabla proyecto.
				CONEXION.ejecutarConsultaActualizacion(
					"UPDATE proyecto " +
					"SET reporte_ecosistema_proyecto = NULL " +
					"WHERE codigo_proyecto = " + codigo);
				
				break;
			}
			
			// Cuando es Econom�a.
			case ECONOMIA:
			{
				// Eliminar en tabla dato_economico_resultado.
				CONEXION.ejecutarConsultaActualizacion(
					"DELETE FROM dato_economico_resultado " +
					"WHERE codigo_proyecto = " + codigo + " AND " +
					"codigo_tipo_red_neuronal = " + tipo);
				
				// Actualizar en tabla proyecto.
				CONEXION.ejecutarConsultaActualizacion(
					"UPDATE proyecto " +
					"SET reporte_economia_proyecto = NULL " +
					"WHERE codigo_proyecto = " + codigo);
				
				break;
			}
			
			// Cuando es Operaci�n.
			case OPERACION:
			{
				// Eliminar en tabla unidad.
				CONEXION.ejecutarConsultaActualizacion(
					"DELETE FROM unidad " +
					"WHERE codigo_proyecto = " + codigo);
				
				// Eliminar en tabla ruta.
				CONEXION.ejecutarConsultaActualizacion(
					"DELETE FROM ruta " +
					"WHERE codigo_proyecto = " + codigo);
				
				// Eliminar en tabla dato_operativo_resultado.
				CONEXION.ejecutarConsultaActualizacion(
					"DELETE FROM dato_operativo_resultado " +
					"WHERE codigo_proyecto = " + codigo);
				
				// Actualizar en tabla proyecto.
				CONEXION.ejecutarConsultaActualizacion(
					"UPDATE proyecto " +
					"SET reporte_operacion_proyecto = NULL " +
					"WHERE codigo_proyecto = " + codigo);
				
				break;
			}
			
			// Cuando es Integraci�n.
			case INTEGRACION:
			{
				// Eliminar en tabla dato_integrado_resultado.
				CONEXION.ejecutarConsultaActualizacion(
					"DELETE FROM dato_integrado_resultado " +
					"WHERE codigo_proyecto = " + codigo);
				
				// Actualizar en tabla proyecto.
				CONEXION.ejecutarConsultaActualizacion(
					"UPDATE proyecto " +
					"SET reporte_integracion_proyecto = NULL " +
					"WHERE codigo_proyecto = " + codigo);
				
				break;
			}
		}
		
		// Desconectar a la base de datos.
		CONEXION.desconectar();
	}
	
	/**
     * Metodo que limpia todos los resultados asociados al proyecto que se
     * encuentra abierto en este momento. Esto porque un nuevo proyecto no debe
     * tener dos conjuntos de resultados almacenados. Lsd consultas de
     * actualizaci�n de datos son realizadas en el orden inverso (para los
     * constraint de la BD).
     */
	public static void limpiarResultados()
	{
		limpiarResultados(INTEGRACION, -1);
		limpiarResultados(OPERACION, -1);
		limpiarResultados(ECONOMIA, 0);
		limpiarResultados(ECONOMIA, 1);
		limpiarResultados(ECOSISTEMA, -1);
	}
	
	/**
     * M�todo que guarda la configuraci�n de un proyecto ya cargado en la
     * aplicaci�n. Si no es un proyecto nuevo, entonces se actualizan sus datos.
     * En caso contrario, se llama al m�todo guardar como, para ponerle nombre a
     * el proyecto que se desea guardar en la base de datos.
     *
     * @return true Cuando es un proyecto nuevo.
     * @return false Cuando no es un proyecto nuevo.
     */
	public static boolean guardar()
	{
		boolean esNuevo = false;
		String autor;
		String version;
		String observacion;
		String reporteEcosistema;
		String reporteEconomia;
		String reporteOperacion;
		String reporteIntegracion;
		
		// Cuando el proyecto no es nuevo.
		if (codigo != Proyecto.CODIGO_NUEVO)
		{
			// Conectando a la base de datos.
			CONEXION.conectar();
			
			// Cuando hay autor.
			if ((Proyecto.autor != null) &&
				(Proyecto.autor.length() > 0))
				autor = "'" + Proyecto.autor + "'";
			else autor = "NULL";
			
			// Cuando hay versi�n.
			if ((Proyecto.version != null) &&
				(Proyecto.version.length() > 0))
				version = "'" + Proyecto.version + "'";
			else version = "NULL";
			
			// Cuando hay observaci�n.
			if ((Proyecto.observacion != null) &&
				(Proyecto.observacion.length() > 0))
				observacion = "'" + Proyecto.observacion + "'";
			else observacion = "NULL";
			
			// Cuando hay reporte ecosistema.
			if ((Proyecto.reporteEcosistema != null) &&
				(Proyecto.reporteEcosistema.length() > 0))
				reporteEcosistema = "'" + Proyecto.reporteEcosistema + "'";
			else reporteEcosistema = "NULL";
			
			// Cuando hay reporte econom�a.
			if ((Proyecto.reporteEconomia != null) &&
				(Proyecto.reporteEconomia.length() > 0))
				reporteEconomia = "'" + Proyecto.reporteEconomia + "'";
			else reporteEconomia = "NULL";
			
			// Cuando hay reporte operaci�n.
			if ((Proyecto.reporteOperacion != null) &&
				(Proyecto.reporteOperacion.length() > 0))
				reporteOperacion = "'" + Proyecto.reporteOperacion + "'";
			else reporteOperacion = "NULL";
			
			// Cuando hay reporte integraci�n.
			if ((Proyecto.reporteIntegracion != null) &&
				(Proyecto.reporteIntegracion.length() > 0))
				reporteIntegracion = "'" + Proyecto.reporteIntegracion + "'";
			else reporteIntegracion = "NULL";
			
			// Preparando los campos para actualizar.
			TABLA.actualizarCampo("codigo_recurso","" + codigoRecurso);
			TABLA.actualizarCampo("codigo_region","" + codigoRegion);
			TABLA.actualizarCampo("codigo_empresa","" + codigoEmpresa);
			TABLA.actualizarCampo("nombre_proyecto","'" + nombre + "'");
			TABLA.actualizarCampo("fecha_inicial_proyecto",
								  "'" + fechaInicial[2] +
								  "-" + fechaInicial[1] +
								  "-" + fechaInicial[0] + "'");
			TABLA.actualizarCampo("fecha_final_proyecto",
								  "'" + fechaFinal[2] +
								  "-" + fechaFinal[1] +
								  "-" + fechaFinal[0] + "'");
			TABLA.actualizarCampo("autor_proyecto", autor);
			TABLA.actualizarCampo("version_proyecto", version);
			TABLA.actualizarCampo("observacion_proyecto", observacion);
			TABLA.actualizarCampo("reporte_ecosistema_proyecto",
								  reporteEcosistema);
			TABLA.actualizarCampo("reporte_economia_proyecto",
								  reporteEconomia);
			TABLA.actualizarCampo("reporte_operacion_proyecto",
								  reporteOperacion);
			TABLA.actualizarCampo("reporte_integracion_proyecto",
								  reporteIntegracion);
			
			// Actualizando seg�n el campo indicado.
			TABLA.actualizar("codigo_proyecto","" + codigo);
			
			// Desconectando a la base de datos.
			CONEXION.desconectar();
			
			// Actualizando cada m�dulo.
			ConfiguracionEspacial.guardar(codigo);
			ConfiguracionAC.guardar(codigo);
			ConfiguracionRecurso.guardar(codigo);
			ConfiguracionNeuronalRBF.guardar(codigo);
			ConfiguracionNeuronalBP.guardar(codigo);
			ConfiguracionAGT.guardar(codigo);
			ConfiguracionAGR.guardar(codigo);
			ConfiguracionHeuristica.guardar(codigo);
		}
		
		// Cuando el proyecto es nuevo.
		else esNuevo = true;
		
		// Resetear el modificado.
		Proyecto.modificado = false;
		
		return esNuevo;
	}
	
	/**
     * M�todo que cambia y agrega los resultados generados por el modelo
     * ecosist�mico. Este m�todo es usado para el caso en que se tienen
     * resultados ecosist�micos en un nuevo proyecto y luego el proyecto ha sido
     * guardado. Por lo tanto, se deben exportar los resultados al proyecto
     * guardado.
     *
     * @param codigoViejo El c�digo del proyecto antiguo.
     * @param codigoNuevo El c�digo del proyecto nuevo.
     */
	private static void cambiarResultadosEcosistema(int codigoViejo,
													int codigoNuevo)
	{
		String insert;
		String valores;
		ResultSet resultado;
		BaseDatoTablaBasica datoEcosistemico =
			new BaseDatoTablaBasica(CONEXION, "dato_ecosistemico_resultado");
		
		resultado = datoEcosistemico.buscar("codigo_proyecto",
											"" + codigoViejo);
		
		// Insertar en dato_ecosistemico_resultado.
		insert = "INSERT INTO dato_ecosistemico_resultado " +
				 "(codigo_recurso, " +
				 "codigo_region, " +
				 "codigo_anio, " +
				 "codigo_dia, " + 
				 "codigo_proyecto, " +
				 "biomasa_dato_ecosistemico_resultado) ";
		
		try
		{
			resultado.first();
			if (resultado.getRow() != 0)
			{
				do
				{
					valores = "VALUES(" +
						resultado.getString("codigo_recurso") + "," +
						resultado.getString("codigo_region") + "," +
						resultado.getString("codigo_anio") + "," +
						resultado.getString("codigo_dia") + "," +
						codigoNuevo + "," +
						resultado.getString("biomasa_dato_ecosistemico_resultado") + ")";
					
					CONEXION.ejecutarConsultaActualizacion(insert + valores);
					
					resultado.next();
				}
				while (!resultado.isAfterLast());
			}
		}
		catch (SQLException ex)
		{
			// No es necesario mostrar la excepci�n.
		}
		
		// Insertar en caladero_resultado.
		insert = "INSERT INTO caladero_resultado " +
				 "(codigo_caladero_resultado, " +
				 "codigo_recurso, " +
				 "codigo_region, " +
				 "codigo_anio, " +
				 "codigo_dia, " +
				 "codigo_proyecto , " +
				 "codigo_longitud_inicial, " +
				 "codigo_longitud_final, " +
				 "codigo_latitud_inicial, " +
				 "codigo_latitud_final, " +
				 "codigo_tipo_caladero_resultado, " +
				 "longitud_inicial_caladero_resultado, " +
				 "longitud_final_caladero_resultado, " +
				 "latitud_inicial_caladero_resultado, " +
				 "latitud_final_caladero_resultado, " +
				 "biomasa_caladero_resultado) ";
		datoEcosistemico = new BaseDatoTablaBasica(CONEXION,
												   "caladero_resultado");
		
		int codigoCaladeroResultado =
			datoEcosistemico.buscarUltimoCodigo(
				"codigo_caladero_resultado") + 1;
		
		resultado = datoEcosistemico.buscar("codigo_proyecto",
											"" + codigoViejo);
		
		try
		{
			resultado.first();
			if (resultado.getRow() != 0)
			{
				do
				{
					valores = "VALUES(" +
						codigoCaladeroResultado + "," +
						resultado.getString("codigo_recurso") + "," +
						resultado.getString("codigo_region") + "," +
						resultado.getString("codigo_anio") + "," +
						resultado.getString("codigo_dia") + "," +
						resultado.getString("codigo_proyecto") + "," +
						resultado.getString("codigo_longitud_inicial") + "," +
						resultado.getString("codigo_longitud_final") + "," +
						resultado.getString("codigo_latitud_inicial") + "," +
						resultado.getString("codigo_latitud_final") + "," +
						resultado.getString("codigo_tipo_caladero_resultado") + "," +
						resultado.getString("longitud_inicial_caladero_resultado") + "," +
						resultado.getString("longitud_final_caladero_resultado") + "," +
						resultado.getString("latitud_inicial_caladero_resultado") + "," +
						resultado.getString("latitud_final_caladero_resultado") + "," +
						resultado.getString("biomasa_caladero_resultado") + ")";
					
					CONEXION.ejecutarConsultaActualizacion(insert + valores);
					
					resultado.next();
					
					codigoCaladeroResultado++;
				}
				while (!resultado.isAfterLast());
			}
		}
		catch (SQLException ex)
		{
			// No es necesario mostrar la excepci�n.
		}
	}
	
	/**
     * M�todo que cambia y agrega los resultados generados por el modelo 
     * econ�mico. Este m�todo es usado para el caso en que se tienen resultados
     * econ�micos en un nuevo proyecto y luego el proyecto ha sido guardado.
     * Por lo tanto, se deben exportar los resultados generados por el proyecto
     * guardado.
     *
     * @param codigoViejo El c�digo del proyecto antiguo.
     * @param codigoNuevo El c�digo del proyecto nuevo. 
     */
	private static void cambiarResultadosEconomia(int codigoViejo,
												  int codigoNuevo)
	{
		String insert;
		String valores;
		ResultSet resultado;
		BaseDatoTablaBasica datoEconomico = 
			new BaseDatoTablaBasica(CONEXION, "dato_economico_resultado");
		
		resultado = datoEconomico.buscar("codigo_proyecto",
										 "" + codigoViejo);
		
		insert = "INSERT INTO dato_economico_resultado (codigo_recurso, " +
				 "codigo_region, codigo_anio, codigo_dia, codigo_proyecto," +
				 "codigo_tipo_red_neuronal, precio_dato_economico_resultado," +
				 "demanda_dato_economico_resultado) ";
		
		try
		{
			resultado.first();
			if (resultado.getRow() != 0)
			{
				do
				{
					valores = "VALUES(" +
						resultado.getString("codigo_recurso") + "," +
						resultado.getString("codigo_region") + "," +
						resultado.getString("codigo_anio") + "," +
						resultado.getString("codigo_dia") + "," +
						codigoNuevo + "," +
						resultado.getString("codigo_tipo_red_neuronal") + "," +
						resultado.getString("precio_dato_economico_resultado") + "," +
						resultado.getString("demanda_dato_economico_resultado") + ")";
					
					CONEXION.ejecutarConsultaActualizacion(insert + valores);
					
					resultado.next();
				}
				while (!resultado.isAfterLast());
			}
		}
		catch (SQLException ex)
		{
			// No es necesario mostrar la excepci�n.
		}
	}
	
	/**
     * M�todo que cambia y agrega los resultados generados por el modelo 
     * operativo. Este m�todo es usado para el caso en que se tienen resultados
     * operativos en un nuevo proyecto y luego el proyecto ha sido guardado. Por
     * lo tanto, se debe exportar los resultados al proyecto guardado.
     *
     * @param codigoViejo El c�digo del proyecto antiguo.
     * @param codigoNuevo El c�digo del proyecto nuevo. 
     */
	private static void cambiarResultadosOperacion(int codigoViejo,
												   int codigoNuevo)
	{
		String insert;
		String valores;
		ResultSet resultado;
		BaseDatoTablaBasica datoOperativo =
			new BaseDatoTablaBasica(CONEXION, "dato_operativo_resultado");
		
		resultado = datoOperativo.buscar("codigo_proyecto",
										 "" + codigoViejo);
		
		// Tabla dato_operativo_resultado.
		insert = "INSERT INTO dato_operativo_resultado " +
				 "(codigo_proyecto, codigo_anio, codigo_dia, " +
				 "costo_total_dato_operativo_resultado, " +
				 "cantidad_total_dato_operativo_resultado) ";
		
		try
		{
			resultado.first();
			if (resultado.getRow() != 0)
			{
				do
				{
					valores = "VALUES(" +
						codigoNuevo + "," +
						resultado.getString("codigo_anio") + "," +
						resultado.getString("codigo_dia") + "," +
						resultado.getString("costo_total_dato_operativo_resultado") + "," +
						resultado.getString("cantidad_total_dato_operativo_resultado") + ")";
					
					CONEXION.ejecutarConsultaActualizacion(insert + valores);
					
					resultado.next();
				}
				while (!resultado.isAfterLast());
			}
		}
		catch (SQLException ex)
		{
			// No es necesario mostrar la excepci�n.
		}
		
		// Tabla unidad.
		insert = "INSERT INTO unidad " +
				 "(codigo_proyecto, codigo_anio, codigo_dia, " + 
				 "codigo_caladero_resultado, codigo_punto_demanda, " +
				 "codigo_medio_transporte , costo_unidad, " +
				 "cantidad_unidad) ";		
		datoOperativo = new BaseDatoTablaBasica(CONEXION, "unidad");
		resultado = datoOperativo.buscar("codigo_proyecto",
										 "" + codigoViejo);
		
		try
		{
			resultado.first();
			if (resultado.getRow() != 0)
			{
				do
				{
					valores = "VALUES(" +
						codigoNuevo + "," +
						resultado.getString("codigo_anio") + "," +
						resultado.getString("codigo_dia") + "," +
						resultado.getString("codigo_caladero_resultado") + "," +
						resultado.getString("codigo_punto_demanda") + "," +
						resultado.getString("codigo_medio_transporte") + "," +
						resultado.getString("costo_unidad") + "," +
						resultado.getString("cantidad_unidad") + ")";
					
					CONEXION.ejecutarConsultaActualizacion(insert + valores);
					
					resultado.next();
				}
				while (!resultado.isAfterLast());
			}
		}
		catch (SQLException ex)
		{
			// No es necesario mostrar la excepci�n.
		}
		
		// Tabla ruta.
		insert = "INSERT INTO ruta (codigo_proyecto, " + 
				 "codigo_anio, codigo_dia, codigo_medio_transporte , " + 
				 "tour_ruta, distancia_total_ruta) ";
		datoOperativo = new BaseDatoTablaBasica(CONEXION, "ruta");
		resultado = datoOperativo.buscar("codigo_proyecto",
										 "" + codigoViejo);
		
		try
		{
			resultado.first();
			if (resultado.getRow() != 0)
			{
				do
				{
					valores = "VALUES(" +
						codigoNuevo + "," +
						resultado.getString("codigo_anio") + "," +
						resultado.getString("codigo_dia") + "," +
						resultado.getString("codigo_medio_transporte") + "," +
						"'" + resultado.getString("tour_ruta") + "'," +
						resultado.getString("distancia_total_ruta") + ")";
					
					CONEXION.ejecutarConsultaActualizacion(insert + valores);
					
					resultado.next();
				}
				while (!resultado.isAfterLast());
			}
		}
		catch (SQLException ex)
		{
			// No es necesario mostrar la excepci�n.
		}
	}
	
	/**
     * M�todo que cambia y agrega los resultados generados por el modelo 
     * integraci�n. Este m�todo es usado para el caso en que se tienen
     * resultados integrados en un nuevo proyecto y luego el proyecto ha sido
     * guardado. Por lo tanto, se deben exportar los resultados generados por
     * el proyecto guardado.
     *
     * @param codigoViejo El c�digo del proyecto antiguo.
     * @param codigoNuevo El c�digo del proyecto nuevo. 
     */
	private static void cambiarResultadosIntegracion(int codigoViejo,
													 int codigoNuevo)
	{
		String insert;
		String valores;
		ResultSet resultado;
		BaseDatoTablaBasica datoIntegrado = 
			new BaseDatoTablaBasica(CONEXION, "dato_integrado_resultado");
		
		resultado = datoIntegrado.buscar("codigo_proyecto",
										 "" + codigoViejo);
		
		insert = "INSERT INTO dato_integrado_resultado " +
			"(codigo_recurso," +
			" codigo_region," +
			" codigo_anio," +
			" codigo_dia," +
			" codigo_proyecto," +
			" oferta_dato_integrado_resultado," +
			" demanda_dato_integrado_resultado," +
			" precio_dato_integrado_resultado," +
			" venta_dato_integrado_resultado) ";
		
		try
		{
			resultado.first();
			if (resultado.getRow() != 0)
			{
				do
				{
					valores = "VALUES(" +
						resultado.getString("codigo_recurso") + "," +
						resultado.getString("codigo_region") + "," +
						resultado.getString("codigo_anio") + "," +
						resultado.getString("codigo_dia") + "," +
						codigoNuevo + "," +
						resultado.getString("oferta_dato_integrado_resultado") + "," +
						resultado.getString("demanda_dato_integrado_resultado") + "," +
						resultado.getString("precio_dato_integrado_resultado") + "," +
						resultado.getString("venta_dato_integrado_resultado") + ")";
					
					CONEXION.ejecutarConsultaActualizacion(insert + valores);
					
					resultado.next();
				}
				while (!resultado.isAfterLast());
			}
		}
		catch (SQLException ex)
		{
			// No es necesario mostrar la excepci�n.
		}
	}
	
	/**
     * M�todo que cambia y agrega los resultados generados por el proyecto.
     * Los resultados son del tipo: ecosist�mico, econ�mico, operativo e
     * integrado. Este m�todo es usado para el caso en que se tienen resultados
     * en un nuevo proyecto y luego el proyecto ha sido guardado. Por lo tanto,
     * se deben exportar los resultados al proyecto guardado.
     *
     * @param codigoViejo El c�digo del viejo proyecto.
     * @param codigoNuevo El c�digo del nuevo proyecto.
     */
	private static void cambiarResultados(int codigoViejo,
										  int codigoNuevo)
	{
		// Conectar a la base de datos.
		CONEXION.conectar();
		
		// Realizar los cambios.
		cambiarResultadosEcosistema(codigoViejo, codigoNuevo);
		cambiarResultadosEconomia(codigoViejo, codigoNuevo);
		cambiarResultadosOperacion(codigoViejo, codigoNuevo);
		cambiarResultadosIntegracion(codigoViejo, codigoNuevo);
		
		// Desconectar a la base de datos.
		CONEXION.desconectar();
	}
	
	/**
     * M�todo que guarda la configuraci�n de un proyecto en la base de datos.
     * Puede ser un proyecto nuevo, o bien, un proyecto con otro nombre y
     * obviamente con otro c�digo.
     * 
     * @param nombre El nuevo nombre para el proyecto.
     */
	public static void guardarComo(String nombre)
	{
		int codigoAgregado;
		int codigoBuscado = buscarCodigoProyecto(nombre);
		ResultSet resultado;
		String autor;
		String version;
		String observacion;
		String reporteEcosistema;
		String reporteEconomia;
		String reporteOperacion;
		String reporteIntegracion;
		
		// Cuando hay autor.
		if ((Proyecto.autor != null) &&
			(Proyecto.autor.length() > 0))
			autor = "'" + Proyecto.autor + "'";
		else autor = "NULL";
		
		// Cuando hay versi�n.
		if ((Proyecto.version != null) &&
			(Proyecto.version.length() > 0))
			version = "'" + Proyecto.version + "'";
		else version = "NULL";
		
		// Cuando hay observaci�n.
		if ((Proyecto.observacion != null) &&
			(Proyecto.observacion.length() > 0))
			observacion = "'" + Proyecto.observacion + "'";
		else observacion = "NULL";
		
		// Cuando hay reporte ecosistema.
		if ((Proyecto.reporteEcosistema != null) &&
			(Proyecto.reporteEcosistema.length() > 0))
			reporteEcosistema = "'" + Proyecto.reporteEcosistema + "'";
		else reporteEcosistema = "NULL";
		
		// Cuando hay reporte econom�a.
		if ((Proyecto.reporteEconomia != null) &&
			(Proyecto.reporteEconomia.length() > 0))
			reporteEconomia = "'" + Proyecto.reporteEconomia + "'";
		else reporteEconomia = "NULL";
		
		// Cuando hay reporte operaci�n.
		if ((Proyecto.reporteOperacion != null) &&
			(Proyecto.reporteOperacion.length() > 0))
			reporteOperacion = "'" + Proyecto.reporteOperacion + "'";
		else reporteOperacion = "NULL";
		
		// Cuando hay reporte integraci�n.
		if ((Proyecto.reporteIntegracion != null) &&
			(Proyecto.reporteIntegracion.length() > 0))
			reporteIntegracion = "'" + Proyecto.reporteIntegracion + "'";
		else reporteIntegracion = "NULL";
		
		// Cuando el proyecto es nuevo.
		if (codigoBuscado == Proyecto.CODIGO_NUEVO)
		{
			// Conectar a la base de datos.
			CONEXION.conectar();
			
			// Preparando los campos para agregar a la base de datos.
			TABLA.agregarCampo("codigo_recurso","" + codigoRecurso);
			TABLA.agregarCampo("codigo_region","" + codigoRegion);
			TABLA.agregarCampo("codigo_empresa","" + codigoEmpresa);
			TABLA.agregarCampo("nombre_proyecto","'" + nombre + "'");
			TABLA.agregarCampo("fecha_inicial_proyecto",
							   "'" + fechaInicial[2] +
							   "-" + fechaInicial[1] +
							   "-" + fechaInicial[0] + "'");
			TABLA.agregarCampo("fecha_final_proyecto",
							   "'" + fechaFinal[2] +
							   "-" + fechaFinal[1] +
							   "-" + fechaFinal[0] + "'");
			TABLA.agregarCampo("autor_proyecto", autor);
			TABLA.agregarCampo("version_proyecto", version);
			TABLA.agregarCampo("observacion_proyecto", observacion);
			TABLA.agregarCampo("reporte_ecosistema_proyecto",
							   reporteEcosistema);
			TABLA.agregarCampo("reporte_economia_proyecto",
							   reporteEconomia);
			TABLA.agregarCampo("reporte_operacion_proyecto",
							   reporteOperacion);
			TABLA.agregarCampo("reporte_integracion_proyecto",
							   reporteIntegracion);
			
			// Agregando el registro.
			TABLA.agregar();
			
			// Buscando el proyecto reci�n agregado.
			resultado = TABLA.buscar("nombre_proyecto","'" + nombre + "'");
			
			try
			{
				// Posicionando en el primer registro.
				resultado.first();
				
				// Obteniendo el registro reci�n agregado.
				codigoAgregado = resultado.getInt("codigo_proyecto");
				
				// Cambiando resultados antes del actualizar el c�digo
				// del proyecto.
 				cambiarResultados(codigoBuscado, codigoAgregado);
 				
 				// Actualizando el c�digo del proyecto.
				Proyecto.codigo = codigoAgregado;
			}
			catch (SQLException ex)
			{
				// Capturar la excepci�n y mostrarla.
				System.err.println("No se pudo agregar en la tabla proyecto.");
			}
			
			// Desconectar la base de datos.
			CONEXION.desconectar();
			
			// Agregar configuraciones de cada m�dulo.
			ConfiguracionAC.agregar(codigo);
			ConfiguracionEspacial.agregar(codigo);
			ConfiguracionRecurso.agregar(codigo);
			ConfiguracionNeuronalBP.agregar(codigo);
			ConfiguracionNeuronalRBF.agregar(codigo);
			ConfiguracionAGT.agregar(codigo);
			ConfiguracionAGR.agregar(codigo);
			ConfiguracionHeuristica.agregar(codigo);
		}
		
		// Cuando el proyecto no es nuevo.
		else
		{
			// Actualizando el c�digo del proyecto.
			Proyecto.codigo = codigoBuscado;
			
			// Conectar a la base de datos.
			CONEXION.conectar();
			
			// Preparando los campos para actualizar.
			TABLA.actualizarCampo("codigo_recurso","" + codigoRecurso);
			TABLA.actualizarCampo("codigo_region","" + codigoRegion);
			TABLA.actualizarCampo("codigo_empresa","" + codigoEmpresa);
			TABLA.actualizarCampo("nombre_proyecto","'" + nombre + "'");
			TABLA.actualizarCampo("fecha_inicial_proyecto",
								  "'" + fechaInicial[2] +
								  "-" + fechaInicial[1] +
								  "-" + fechaInicial[0] + "'");
			TABLA.actualizarCampo("fecha_final_proyecto",
								  "'" + fechaFinal[2] +
								  "-" + fechaFinal[1] +
								  "-" + fechaFinal[0] + "'");
			TABLA.actualizarCampo("autor_proyecto", autor);
			TABLA.actualizarCampo("version_proyecto", version);
			TABLA.actualizarCampo("observacion_proyecto", observacion);
			TABLA.actualizarCampo("reporte_ecosistema_proyecto",
								  reporteEcosistema);
			TABLA.actualizarCampo("reporte_economia_proyecto",
								  reporteEconomia);
			TABLA.actualizarCampo("reporte_operacion_proyecto",
								  reporteOperacion);
			TABLA.actualizarCampo("reporte_integracion_proyecto",
								  reporteIntegracion);
			
			// Actualizando seg�n el campo indicado.
			TABLA.actualizar("codigo_proyecto", "" + codigo);
			
			// Desconectando a la base de datos.
			CONEXION.desconectar();
			
			// Actualizando las configuraciones de cada m�dulo.
			ConfiguracionAC.guardar(codigo);
			ConfiguracionEspacial.guardar(codigo);
			ConfiguracionRecurso.guardar(codigo);
			ConfiguracionNeuronalRBF.guardar(codigo);
			ConfiguracionNeuronalBP.guardar(codigo);
			ConfiguracionAGT.guardar(codigo);
			ConfiguracionAGR.guardar(codigo);
			ConfiguracionHeuristica.guardar(codigo);
		}
		
		// Cambiar los atributos de la clase.
		Proyecto.nombre = nombre;
		Proyecto.modificado = false;
	}
	
	/**
     * M�todo que indica si hay resultados asociados al proyecto que se
     * encuentra abierto en este momento. En caso de tener resultados se
     * devuelve true. En caso contrario se devuelve false. Este m�todo ve si
     * hay resultados
     *
     * @param tabla Nombre de la tabla que se quiere verificar los datos
     *              asociados a un nuevo proyecto.
     *
     * @return true Cuando la tabla tiene resultados asociados al proyecto.
     * @return false Cuando la tabla no tiene resultados asociados al proyecto.
     */
	public static boolean hayResultados(String tabla)
	{
		int registros = 0;
		BaseDatoTablaBasica tablaResultado =
			new BaseDatoTablaBasica(CONEXION, tabla);
		
		CONEXION.conectar();
		ResultSet resultado = tablaResultado.buscar("codigo_proyecto",
													"" + codigo);
		try
		{
			resultado.last();
			registros = resultado.getRow();
		}
		catch (SQLException ex)
		{
			System.out.println("Error al consultar la tabla " + tabla + ".");
		}
		
		CONEXION.desconectar();
		
		return (registros > 0) ? true:false;
	}
	
	/**
     * M�todo que indica si hay resultados asociados al proyecto que se
     * encuentra abierto en este momento. En caso de tener resultados se
     * devuelve true. En caso contrario se devuelve false. La revisi�n de
     * resultados se hace en las tablas de los modelos ecosistema, econom�a y
     * operaci�n.
     *
     * @return true Cuando hay resultados asociados al proyecto.
     * @return false Cuando no hay resultados asociados al proyecto.
     */
	public static boolean hayResultados()
	{
		return hayResultados("dato_ecosistemico_resultado") &&
			   hayResultados("caladero_resultado") &&
			   hayResultados("dato_economico_resultado") &&
			   hayResultados("dato_operativo_resultado") &&
			   hayResultados("ruta") &&
			   hayResultados("unidad");
	}
	
	/**
     * M�todo que indica si hay resultados econ�micos asociados al proyecto que
     * se encuentra abierto en este momento. En caso de tener resultados se
     * devuelve true. En caso contrario se devuelve false. Este m�todo ve si
     * hay resultados
     *
     * @param tipoRedNeuronal C�digo del tipo de red neuronal ha buscar en la
     *                        tabla de resultados econ�micos.
     *
     * @return true Cuando se tiene resultados econ�micos asociados al proyecto.
     * @return false Cuando se tiene resultados econ�micos asociados al
     *               proyecto.
     */
	public static boolean hayResultadosEconomicos(int codigoTipoRedNeuronal)
	{
		int registros = 0;
		BaseDatoTablaBasica tablaResultado =
			new BaseDatoTablaBasica(CONEXION, "dato_economico_resultado");
		
		CONEXION.conectar();
		ResultSet resultado = tablaResultado.buscarCondicion(
			"codigo_proyecto = " + codigo + " and " +
			"codigo_tipo_red_neuronal = " + codigoTipoRedNeuronal);
		
		try
		{
			resultado.last();
			registros = resultado.getRow();
		}
		catch (SQLException ex)
		{
			System.out.println("Error al consultar la tabla " +
							   "dato_economico_resultado.");
		}
		
		CONEXION.desconectar();
		
		return (registros > 0) ? true:false;
	}
	
	/**
     * M�todo que obtiene todos los registros de los proyectos en la base datos.
     *
     * @return resultado Resultado que contiene todos los registros de proyectos
     *                   desde la base de datos.
     */	
	public static ResultSet obtenerTodosProyectos()
	{
		// Creando elemento que almacena resultado.
		ResultSet resultado;		
		
		// Ejecutando consulta
		resultado = TABLA.buscarCondicion("codigo_proyecto <> " +
										  Proyecto.CODIGO_NUEVO + " " +
										  "order by nombre_proyecto");
		
		return resultado;
	}
	
	/**
     * Metodo que retorna verdadero si el proyecto es nuevo. En caso contrario,
     * se retorna falso.
     * 
     * @return true Si el proyecto es el nuevo.
     * @return false Si el proyecto no es el nuevo.
     */
	public static boolean esNuevo()
	{
		return (codigo == CODIGO_NUEVO);
	}
	
	/**
     * M�todo que verifica si el nombre del proyecto est� repetido o no en la
     * base de datos.
     * 
     * @param nombre String con el nombre que se quiere poner al proyecto.
     * 
     * @return repetido Indica si el nombre del proyecto est� o no repetido.
     */
	public static boolean estaRepetidoNombre(String nombre)
	{
		// Si est� repetido.
		boolean repetido;
		
		// Creando elemento que almacena resultado.
		ResultSet resultado;
		
		// Conectadno a la base de datos.
		CONEXION.conectar();
		
		// Ejecutadno consulta.
		resultado = TABLA.buscar("nombre_proyecto","'" + nombre + "'");
		
		// Si no hay elemento.
		if (resultado == null)
			repetido = true;
		else
			repetido = false;
		
		// Desconectando de la base datos.
		CONEXION.desconectar();
		
		return repetido;
	}
	
	/**
     * M�todo que verifica si el nombre del proyecto est� repetido o no en la
     * base de datos.
     * 
     * @param nombre String con el nombre que se quiere poner al proyecto
     * 
     * @return codigoBuscado Indica el c�digo del proyecto con el nombre
     *                       especificado como par�metro.
     */
	public static int buscarCodigoProyecto(String nombre)
	{
		// C�digo del proyecto buscado.
		int codigoBuscado = Proyecto.CODIGO_NUEVO;
		
		// Creando elemento que almacena resultado.
		ResultSet resultado;
		
		// Conectando a la base de datos.
		CONEXION.conectar();
		
		// Ejecutadno consulta.
		resultado = TABLA.buscar("nombre_proyecto","'" + nombre + "'");
		
		// Si se encuentra el elemento.
		try
		{
			resultado.first();
			
			// Si hay proyectos guardados.
			if (resultado.getRow() != 0)
				codigoBuscado = resultado.getInt("codigo_proyecto");
		}
		catch (SQLException ex)
		{
			System.out.println("Error al consultar la tabla proyecto.");
		}
			
		// Desconectando de la base de datos.
		CONEXION.desconectar();
		
		return codigoBuscado;
	}
	
	/**
     * M�todo que cambia el c�digo del recurso del proyecto.
     * 
     * @param codigoRecurso Entero que contiene el c�digo del recurso del
     *                      proyecto.
     */
	public static void establecerCodigoRecurso(int codigoRecurso)
	{
		Proyecto.codigoRecurso = codigoRecurso;
	}
	
	/**
     * M�todo que cambia el c�digo de la regi�n del proyecto.
     * 
     * @param codigoRegion Entero que contiene el c�digo de la regi�n donde se
     *                     ejecuta el proyecto.
     */
	public static void establecerCodigoRegion(int codigoRegion)
	{
		Proyecto.codigoRegion = codigoRegion;
	}
	
	/**
     * M�todo que cambia la empresa del proyecto.
     * 
     * @param codigoEmpresa Entero que contiene el c�digo de la empresa del
     *                      proyecto.
     */
	public static void establecerCodigoEmpresa(int codigoEmpresa)
	{
		Proyecto.codigoEmpresa = codigoEmpresa;
	}
	
	/**
     * M�todo que cambia la fecha inicial donde ocurre el an�lisis del proyecto.
     *
     * @param fechaInicial Vector que contiene la fecha inicial del analisis del
     *                     proyecto. El formato del arreglo es (0:d�a, 1:mes,
     *                     2:a�o).
     */
	public static void establecerFechaInicial(String[] fechaInicial)
	{
		Proyecto.fechaInicial =  fechaInicial;
	}
	
	/**
     * M�todo que cambia la fecha final donde ocurre el an�lisis del proyecto.
     *
     * @param fechaFinal Vector que contiene la fecha final del an�lisis del
     *                   proyecto. El formato del arreglo es (0:d�a, 1:mes,
     *                   2:a�o).
     */
	public static void establecerFechaFinal(String[] fechaFinal)
	{
		Proyecto.fechaFinal = fechaFinal;
	}
	
	/**
     * M�todo que cambia el autor del proyecto.
     * 
     * @param autor String que contiene el autor del proyecto.
     */
	public static void establecerAutor(String autor)
	{
		Proyecto.autor = autor;
	}
	
	/**
     * M�todo que cambia la versi�n del proyecto.
     * 
     * @param version String que contiene la versi�n del proyecto.
     */
	public static void establecerVersion(String version)
	{
		Proyecto.version = version;
	}
	
	/**
     * M�todo que cambia la observaci�n hecha al proyecto.
     * 
     * @param observacion String que contiene la observaci�n del proyecto.
     */
	public static void establecerObservacion(String observacion)
	{
		Proyecto.observacion = observacion;
	}
	
	/**
     * M�todo que cambia el reporte ecosist�mico del proyecto.
     * 
     * @param reporteEcosistema String que contiene el reporte ecosist�mico del
     *                          proyecto
     */
	public static void establecerReporteEcosistema(String reporteEcosistema)
	{
		Proyecto.reporteEcosistema = reporteEcosistema;
	}
	
	/**
     * M�todo que cambia el reporte econ�mico del proyecto.
     * 
     * @param reporteEconomia String que contiene el reporte econ�mico del
     *                        proyecto.
     */
	public static void establecerReporteEconomia(String reporteEconomia)
	{
		Proyecto.reporteEconomia = reporteEconomia;
	}
	
	/**
     * M�todo que cambia el reporte operativo del proyecto.
     * 
     * @param reporteOperacion String que contiene el reporte operativo del
     *                         proyecto.
     */
	public static void establecerReporteOperacion(String reporteOperacion)
	{
		Proyecto.reporteOperacion = reporteOperacion;
	}
	
	/**
     * M�todo que cambia el reporte integrado del proyecto.
     * 
     * @param reporteIntegracion String que contiene el reporte integrado del
     *                           proyecto.
     */
	public static void establecerReporteIntegracion(String reporteIntegracion)
	{
		Proyecto.reporteIntegracion = reporteIntegracion;
	}
	
	/**
     * M�todo que cambia el estado del proyecto cuando este ha cambiado desde su
     * �ltima grabaci�n.
     * 
     * @param modificado Un booleano que indica si cambi� o no el proyecto.
     */
	public static void establecerModificado(boolean modificado)
	{
		Proyecto.modificado = modificado;
	}
	
	/**
     * M�todo que devuelve el c�digo del proyecto.
     * 
     * @return codigo El c�digo que tiene el proyecto.
     */
	public static int obtenerCodigo()
	{
		return codigo;
	}
	
	/**
     * M�todo que devuelve el c�digo del recurso que se predice.
     * 
     * @return codigoRecurso C�digo del recurso que se predice.
     */
	public static int obtenerCodigoRecurso()
	{
		return codigoRecurso;
	}
	
	/**
     * M�todo que devuelve el nombre del recurso que se predice. Se busca en la
     * base de datos el recurso que contiene el c�digo del recurso que se est�
     * usando en el proyecto.
     * 
     * @return nombreRecurso Nombre del recurso que se predice.
     */
	public static String obtenerNombreRecurso()
	{
		// El nombre del recurso.
		String nombreRecurso = "";
		
		// Crear los resultados.
		ResultSet resultado;
		
		// Conectar a la base datos.
		CONEXION.conectar();
		
		// Ejecutar la consulta
		String consulta =
			"select nombre_comun_recurso " +
			"from recurso " +
			"where codigo_recurso = " + codigoRecurso;
		resultado = CONEXION.ejecutarConsulta(consulta);
		
		// Si encuentr� el elemento.
		try
		{
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Obtener el nombre del recurso.
			nombreRecurso = resultado.getString("nombre_comun_recurso");
		}
		
		// Si no encuentr� el elemento.
		catch(SQLException exception)
		{
			System.out.println("Error al buscar el nombre del recurso.");
		}
		
		// Desconectar la base datos.
		CONEXION.desconectar();
		
		return nombreRecurso;
	}
	
	/**
     * M�todo que devuelve c�digo de la regi�n del proyecto.
     * 
     * @return codigoRegion C�digo de la regi�n donde se ejecuta el proyecto.
     */
	public static int obtenerCodigoRegion()
	{
		return codigoRegion;
	}
	
	/**
     * M�todo que devuelve el nombre de la regi�n del proyecto. Se busca en la
     * base de datos la regi�n que contiene el c�digo de la regi�n que se est�
     * usando en el proyecto.
     * 
     * @return nombreRegion El nombre de la regi�n donde se ejecuta el proyecto.
     */
	public static String obtenerNombreRegion()
	{
		// El nombre de la regi�n.
		String nombreRegion = "";
		
		// Crear los resultados.
		ResultSet resultado;
		
		// Conectar a la base datos.
		CONEXION.conectar();
		
		// Ejecutar la consulta
		String consulta =
			"select descripcion_region " +
			"from region " +
			"where codigo_region = " + codigoRegion;
		resultado = CONEXION.ejecutarConsulta(consulta);
		
		// Si encuentr� el elemento.
		try
		{
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Obtener el nombre de la regi�n.
			nombreRegion = resultado.getString("descripcion_region");
		}
		
		// Si no encuentra el elemento.
		catch(SQLException exception)
		{
			System.out.println("Error al buscar el nombre de la region.");
		}
		
		// Desconectar la base datos.
		CONEXION.desconectar();
		
		return nombreRegion;
	}
	
	/**
     * M�todo que devuelve el c�digo de la empresa del proyecto.
     * 
     * @return codigoEmpresa C�digo de la empresa del proyecto.
     */
	public static int obtenerCodigoEmpresa()
	{
		return codigoEmpresa;
	}
	
	/**
     * M�todo que devuelve el nombre de la empresa del proyecto. Se busca en la
     * base de datos la empresa que contiene el c�digo de la empresa que se est�
     * usando en el proyecto.
     * 
     * @return nombreEmpresa El nombre de la empresa del proyecto.
     */
	public static String obtenerNombreEmpresa()
	{
		// El nombre de la empresa.
		String nombreEmpresa = "";
		
		// Crear los resultados.
		ResultSet resultado;
		
		// Conectar a la base datos.
		CONEXION.conectar();
		
		// Ejecutar la consulta
		String consulta =
			"select nombre_empresa " +
			"from empresa " +
			"where codigo_empresa = " + codigoEmpresa;
		resultado = CONEXION.ejecutarConsulta(consulta);
		
		// Si encuentra el elemento.
		try
		{
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Obtener el nombre de la empresa.
			nombreEmpresa = resultado.getString("nombre_empresa");
		}
		
		// Si no encuentra el elemento.
		catch(SQLException exception)
		{
			System.out.println("Error al buscar el nombre de la empresa.");
		}
		
		// Desconectar la base datos.
		CONEXION.desconectar();
		
		return nombreEmpresa;
	}
	
	/**
     * M�todo que obtiene el nombre del proyecto.
     * 
     * @return nombre Un string que indica el nombre del proyecto.
     */
	public static String obtenerNombre()
	{
		return nombre;
	}
	
	/**
     * M�todo que retorna la fecha incial donde ocurre el proyecto. La devuelve
     * en formato string.
     * 
     * @return fechaInicial Vector que contiene la fecha inicial del analisis 
     *						del proyecto. El formato del arreglo es (0:d�a,
     *                      1:mes, 2:a�o).
     */
	public static String[] obtenerFechaInicial()
	{
		return fechaInicial;
	}
	
	/**
     * M�todo que retorna la fecha inicial donde ocurre el proyecto. La devuelve
     * en formato entero.
     * 
     * @return fechaInicial Vector que contiene la fecha Inicial del an�lisis 
     *						del proyecto. El formato del arreglo es (0:d�a,
     *                      1:mes, 2:a�o).
     */
	public static int[] obtenerFechaInicialFormatoInt()
	{
		int[] fechaInicialFormatoEntero = new int[3];
		
		fechaInicialFormatoEntero[0] = Integer.parseInt(fechaInicial[0]);
		fechaInicialFormatoEntero[1] = Integer.parseInt(fechaInicial[1]);
		fechaInicialFormatoEntero[2] = Integer.parseInt(fechaInicial[2]);
		
		return fechaInicialFormatoEntero;
	}
	
	/**
     * M�todo que retorna la fecha final donde ocurre el proyecto. La devuelve
     * en formato string.
     * 
     * @return fechaFinal Vector que contiene la fecha final del an�lisis del
     *                    proyecto. El formato del arreglo es (0:d�a, 1:mes,
     *                    2:a�o).
     */
	public static String[] obtenerFechaFinal()
	{
		return fechaFinal;
	}
	
	/**
     * M�todo que retorna la fecha final donde ocurre el proyecto. La devuelve
     * en formato entero.
     * 
     * @return fechaFinal Vector que contiene la fecha final del an�lisis del
     *                    proyecto. El formato del arreglo es (0:d�a, 1:mes,
     *                    2:a�o).
     */
	public static int[] obtenerFechaFinalFormatoInt()
	{
		int[] fechaFinalFormatoEntero = new int[3];
		
		fechaFinalFormatoEntero[0] = Integer.parseInt(fechaFinal[0]);
		fechaFinalFormatoEntero[1] = Integer.parseInt(fechaFinal[1]);
		fechaFinalFormatoEntero[2] = Integer.parseInt(fechaFinal[2]);
		
		return fechaFinalFormatoEntero;
	}
	
	/**
     * M�todo que devuelve el autor del proyecto.
     * 
     * @return autor String que contiene el autor del proyecto.
     */
	public static String obtenerAutor()
	{
		return autor;
	}	
	
	/**
     * M�todo que devuelve la versi�n del proyecto.
     * 
     * @return version String que contiene la versi�n del proyecto.
     */
	public static String obtenerVersion()
	{
		return version;
	}
	
	/**
     * M�todo que obtiene la observaci�n del proyecto.
     * 
     * @return observacion Un string que indica la observaci�n del proyecto.
     */
	public static String obtenerObservacion()
	{
		return observacion;
	}
	
	/**
     * M�todo que obtiene el reporte ecosist�mico.
     * 
     * @return reporteEcosistema Un string que indica el reporte del ecosistema
     *                           del proyecto.
     */
	public static String obtenerReporteEcosistema()
	{
		return reporteEcosistema;
	}
	
	/**
     * M�todo que obtiene el reporte econ�mico.
     * 
     * @return reporteEconomia Un string que indica el reporte de la econom�a
     *                         del proyecto.
     */
	public static String obtenerReporteEconomia()
	{
		return reporteEconomia;
	}
	
	/**
     * M�todo que obtiene el reporte operativo.
     * 
     * @return reporteOperacion Un string que indica el reporte de la operaci�n
     *                          del proyecto.
     */
	public static String obtenerReporteOperacion()
	{
		return reporteOperacion;
	}
	
	/**
     * M�todo que obtiene el reporte integrado.
     * 
     * @return reporteIntegracion Un string que indica el reporte de la
     *                            integraci�n del proyecto.
     */
	public static String obtenerReporteIntegracion()
	{
		return reporteIntegracion;
	}
	
	/**
     * M�todo que obtiene el estado del proyecto desde su �ltima grabaci�n. Es
     * decir, dice si el proyecto fue modificado o no.
     * 
     * @return modificado Un booleano que indica si cambi� o no el proyecto.
     */
	public static boolean obtenerModificado()
	{
		return modificado;
	}
}