/**
 * @(#)ConfiguracionAGT.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que contiene solamente atributos con valores estáticos, que sirve para
 * mantener la información personalizada del usuario obre los parámetros de 
 * entrada utilizados por el algoritmo genético de transporte. Esta clase se
 * comunica con la Base de Datos MySQL.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see Vector
 * @see Resultset
 * @see SQLException
 * @see BaseDatoMotor
 * @see BaseDatoTablaBasica
 * @see VectorRestriccion
 */
public class ConfiguracionAGT
{
	/** Conexión a la base de datos. */
	private final static BaseDatoMotor CONEXIONBD = new BaseDatoMotor();	
	
	/**
	 * La tabla de donde se obtiene los datos de la configuración algoritmo
	 * genético de transporte.
	 */
	private final static BaseDatoTablaBasica CONFIGURACION_AGT =
		new BaseDatoTablaBasica(CONEXIONBD, "configuracion_agt");	
	
	/** Valores para cargar el vector de días. */
	private static int diaCaladero;
	
	/** Valores para cargar el vector de años. */
	private static int anioCaladero;
	
	/** Constante con la probabilidad para selección de torneo. */
	public final static double PROBABILIDAD_FLIP = 0.95;
	
	/** El tipo de funcion objetivo (0:lineal 1:no lineal). */
	public static int tipoFuncion;
	
	/** El número de puntos de oferta o almacenes. */
	public static int m;
	
	/** El número de puntos de demanda o destinos. */
	public static int n;
	
	/** El número de medios de transporte. */
	public static int l;
	
	/** Matriz de costos. */
	public static double[][][] matrizCostos;
	
	/** Indica el número de individuos de una población. */
	public static int tamanoPoblacion;
	
	/**
	 * Indica el número generaciones que itera el algoritmo genético de
	 * transporte.
	 */
	public static int numeroGeneraciones;
	
	/** Indica la técnica de selección utilizada (0:proporcional 1:torneo). */
	public static int tecnicaSeleccion;
	
	/** Indica la probabilidad con que los individuos se cruzan. */
	public static double probabilidadCruce;
	
	/** Indica la probabilidad con que un individuo es mutado. */
	public static double probabilidadMutacion;
	
	/** El vector con las restricciones de oferta. */
	public static VectorRestriccion oferta;
	
	/** El vector con las restricciones de demanda. */
	public static VectorRestriccion demanda;
	
	/** El vector con las restricciones de capacidad. */
	public static VectorRestriccion capacidad;
	
	/**
	 * Método que carga una configuración del algoritmo genético de transporte
	 * a un proyecto. Cuando el proyecto es nuevo, se cargan los valores por
	 * defecto. Es decir, en este método se asigna el valor inicial a todos los
	 * atributos de esta clase.
	 *
	 * @param codigoProyecto El código del proyecto que se encuentra en la Base
	 *                       de Datos.
	 */
	public static void cargar(int codigoProyecto) 
	{		
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		CONEXIONBD.conectar();
		
		// Realizar la consulta.
		resultado = CONFIGURACION_AGT.buscar("codigo_proyecto",
												  ""+codigoProyecto);
		
		// Setear los valores de los atributos.
		try
		{
			// Apuntar al primer registro.
			resultado.first();
			
			// Setear los valores.
			tipoFuncion = resultado.getInt("codigo_funcion_evaluacion");
			tecnicaSeleccion = resultado.getInt("codigo_tecnica_seleccion");
			tamanoPoblacion = 
					resultado.getInt("tamanio_poblacion_configuracion_agt");
			numeroGeneraciones = 
					resultado.getInt("numero_generaciones_configuracion_agt");
			probabilidadCruce = 
					resultado.getDouble("probabilidad_cruce_configuracion_agt");
			probabilidadMutacion = 
					resultado.getDouble("probabilidad_mutacion_configuracion_agt");
			establecerDiaAnio();
			configurarRestricciones();			
		}
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla: "+
							   "configuracion_agt.");
		}
		
		// Desconectar a la base de datos.
		CONEXIONBD.desconectar();
	}
	
	/**
	 * Método que guarda una configuración del algoritmo genético de transporte
	 * en la Base de Datos, según el identificador del proyecto.
	 *
	 * @param codigoProyecto Identificador o código del proyecto que se encuentra 
	 *						 en la Base de Datos.
	 */
	public static void guardar(int codigoProyecto)
	{
		// Conectar a la base de datos.
		CONEXIONBD.conectar();
		
		// Actualizar los campos con los nuevos valores.
		CONFIGURACION_AGT.actualizarCampo("codigo_funcion_evaluacion", 
											"" + tipoFuncion);
		CONFIGURACION_AGT.actualizarCampo("codigo_tecnica_seleccion", 
											"" + tecnicaSeleccion);
		CONFIGURACION_AGT.actualizarCampo("tamanio_poblacion_configuracion_agt",
											"" + tamanoPoblacion);
		CONFIGURACION_AGT.actualizarCampo("numero_generaciones_configuracion_agt",
											""+numeroGeneraciones);
		CONFIGURACION_AGT.actualizarCampo("probabilidad_cruce_configuracion_agt",
											"" + probabilidadCruce);
		CONFIGURACION_AGT.actualizarCampo("probabilidad_mutacion_configuracion_agt",
											"" + probabilidadMutacion);
		
		// Ejecutar la actualización en la tabla configuracion_agt.
		CONFIGURACION_AGT.actualizar("codigo_proyecto", "" + codigoProyecto);
		
		// Desconectar a la base de datos.
		CONEXIONBD.desconectar();
	}
	
	/**
     * Método que agrega un registro de configuración del algoritmo genético de
     * transporte en la base de datos. Este registro se agrega según el proyecto
     * que se creó.
     * 
     * @param codigoProyecto Identificador del Proyecto
     */
	public static void agregar(int codigoProyecto)
	{
		// Conectar a la base de datos.
		CONEXIONBD.conectar();
		
		// Preparar un registro para agregar.
		CONFIGURACION_AGT.agregarCampo("codigo_proyecto",
											"" + codigoProyecto);
		CONFIGURACION_AGT.agregarCampo("codigo_funcion_evaluacion", 
											"" + tipoFuncion);
		CONFIGURACION_AGT.agregarCampo("codigo_tecnica_seleccion", 
											"" + tecnicaSeleccion);
		CONFIGURACION_AGT.agregarCampo("tamanio_poblacion_configuracion_agt",
											"" + tamanoPoblacion);
		CONFIGURACION_AGT.agregarCampo("numero_generaciones_configuracion_agt",
											""+numeroGeneraciones);
		CONFIGURACION_AGT.agregarCampo("probabilidad_cruce_configuracion_agt",
											"" + probabilidadCruce);
		CONFIGURACION_AGT.agregarCampo("probabilidad_mutacion_configuracion_agt",
											"" + probabilidadMutacion);
		
		// Ejecutar el insert en la tabla configuracion_agt.
		CONFIGURACION_AGT.agregar();
		
		// Desconectar a la base de datos.
		CONEXIONBD.desconectar();
	}
	
	/**
	 * Método que establece los valores del día y año para el vector de Oferta.
	 * Estos valores son por defecto el primer elemento del combo fecha en el
	 * panel de restricciones.
	 */
	public static void establecerDiaAnio()
	{
		int[] fechaInicial = Proyecto.obtenerFechaInicialFormatoInt();
		int[] fechaFinal = Proyecto.obtenerFechaFinalFormatoInt();
		int[] diaMes = new int[2];
		int[] fecha = new int[3];
		int diaInicial = Servicio.obtenerDia(fechaInicial[0], fechaInicial[1]);
		int diaFinal = Servicio.obtenerDia(fechaFinal[0], fechaFinal[1]);
		ResultSet resultado;
		
		String sql =
			"select codigo_dia, codigo_anio" +
			" from caladero_resultado" +
			" where ((codigo_dia >= " + diaInicial +
			" and codigo_anio = " + fechaInicial[2] + ")" +
			" or (codigo_dia <= " + diaFinal +
			" and codigo_anio = " + fechaFinal[2] + "))" +
			" and codigo_proyecto = " + Proyecto.obtenerCodigo() +
			" group by codigo_dia order by codigo_anio, codigo_dia";
		
		CONEXIONBD.conectar();
		resultado = CONEXIONBD.ejecutarConsulta(sql);
		
		try
		{
			resultado.first();
			if(resultado.getRow() != 0)
			{
				diaCaladero = resultado.getInt("codigo_dia");
				anioCaladero = resultado.getInt("codigo_anio");
			}
			else
			{
				diaCaladero = 0;
				anioCaladero = 0;
			}
		}
		catch(SQLException ex)
		{
			System.out.println ("Error al cargar la tabla caladero_resultado.");
		}
		
		CONEXIONBD.desconectar();
	}
	
	/**
	 * Método que permite establecer los valores de los atributos día y año.
	 * Estos valores son de utilidad al llenar el vector de caladeros para
	 * el día y año seleccionados.
	 *
	 * @param dia Indica el día del año.
	 * @param anio Indica el año.
	 */
	public static void establecerDiaAnio(int dia, int anio)
	{
		diaCaladero = dia;
		anioCaladero = anio;
		configurarRestricciones();
	}
	
	/**
	 * Método que carga los distintos puntos de oferta, demanda y medios de 
	 * transporte desde la base de datos.
	 */
	public static  void configurarRestricciones()
	{
		// Inicializar puntos de oferta, demanda y medios de transporte.
		oferta = cargarPuntosOferta();
		demanda = cargarPuntosDemanda();
		capacidad = cargarMediosTransporte();
		
		// Asignar cantidad puntos de oferta.
		m = oferta.size();
		
		// Asignar cantidad de puntos de demanda.
		n = demanda.size();
		
		// Asignar cantidad de medios de transporte.
		l = capacidad.size();
		
		// Inicializa la matriz de costos en cero.
		inicializarMatrizCostos();
	}
	
	/**
	 * Método que inicializa los valores de la matriz en costos en cero.
	 */
	private static void inicializarMatrizCostos()
	{
		int x;
		int y;
		int z;
		int i;
		int j;
		int k;
		matrizCostos = new double[m][n][l];
		ResultSet resultado;
		String consulta, condicion;
		
		condicion = "where codigo_proyecto = " + Proyecto.obtenerCodigo() +
				    " and codigo_dia = " + diaCaladero +
				    " and codigo_anio = " + anioCaladero + " ";
		
		CONEXIONBD.conectar();
		
		x = 0;
		y = 0;
		z = 0;
		
		// Obtener el número de caladeros para el resultado.
		consulta = "select codigo_caladero_resultado from unidad " + condicion +
				   "group by codigo_caladero_resultado";
		
		resultado = CONEXIONBD.ejecutarConsulta(consulta);
		
		try
		{
			resultado.last();
			x = resultado.getRow() + 1;
		}
		catch(SQLException ex)
		{
			System.out.println ("Error en sql " + consulta + "\n" +
				ex.getMessage());
		}
		
		// Obtener el número de puntos de demanda para el resultado.
		consulta = "select codigo_punto_demanda from unidad " + condicion +
				   "group by codigo_punto_demanda";
		
		resultado = CONEXIONBD.ejecutarConsulta(consulta);
		
		try
		{
			resultado.last();
			y = resultado.getRow() + 1;
		}
		catch(SQLException ex)
		{
			System.out.println ("Error en sql " + consulta + "\n" +
				ex.getMessage());
		}
		
		// Obtener el número de medios de transporte para el resultado.
		consulta = "select codigo_medio_transporte from unidad " + condicion +
				   "group by codigo_medio_transporte";
		
		resultado = CONEXIONBD.ejecutarConsulta(consulta);
		
		try
		{
			resultado.last();
			z = resultado.getRow() + 1;
		}
		catch(SQLException ex)
		{
			System.out.println ("Error en sql " + consulta + "\n" +
				ex.getMessage());
		}
		
		// Cargar la matriz de costos.
		if((x == m) && (y == n) && (z == l))
		{
			consulta = "select * from unidad " + condicion +
					   "order by codigo_caladero_resultado, " +
					   "codigo_punto_demanda, codigo_medio_transporte";
			
			resultado = CONEXIONBD.ejecutarConsulta(consulta);
			
			try
			{
				resultado.first();
				
				// Si existen resultados.
				if(resultado.getRow() != 0)
				{
					do
					{
						for(i = 0;i < (m-1);i++)
							for(j = 0;j < (n-1);j++)
								for(k = 0;k < (l-1);k++)
								{
									matrizCostos[i][j][k] =
										resultado.getDouble("costo_unidad");
									resultado.next();
								}
					}
					while(!resultado.isAfterLast());
				}
			}
			catch(SQLException ex)
			{
				System.out.println ("Error en sql " + consulta + "\n" +
					ex.getMessage());
			}
		}
		else
		{
			for(i = 0;i < m;i++)
				for(j = 0;j < n;j++)
					for(k = 0;k < l;k++)
						matrizCostos[i][j][k] = 0;
		}
		
		CONEXIONBD.desconectar();
	}
	
	/**
	 * Método que retorna un vector con los elementos principales de los puntos
	 * de oferta existentes en la base de datos.
	 *
	 * @return vector Vector con todos los caladeros disponibles.
	 */
	private static VectorRestriccion cargarPuntosOferta()
	{
		int codigo;
		int codigoRecurso;
		int codigoRegion;
		String descripcion;
		String sql;
		String[] longitud = new String[0];
		String[] latitud = new String[0];
		double cantidad;
		ResultSet resultado;		
		ElementoRestriccion elemento;
		VectorRestriccion vector = new VectorRestriccion();
		
		// Conectar a la base de datos.
		CONEXIONBD.conectar();
		
		// Obtener datos de la tabla longitud.
		sql = "select * from longitud order by codigo_longitud";
		resultado = CONEXIONBD.ejecutarConsulta(sql);
		
		try
		{
			resultado.last();
			longitud = new String[resultado.getRow()];
			resultado.first();
			
			if (resultado.getRow()!=0)
				while(!resultado.isAfterLast())
				{
					longitud[resultado.getRow() - 1] = "" +
								resultado.getString("descripcion_longitud");								
					resultado.next();
				}			
		}
		catch(SQLException ex)
		{
			System.out.println("Error en sql " + sql + "\n" + ex.getMessage());
		}
		
		// Obtener datos de la tabla latitud.
		sql = "select * from latitud order by codigo_latitud";
		resultado = CONEXIONBD.ejecutarConsulta(sql);
		
		try
		{
			resultado.last();
			latitud = new String[resultado.getRow()];
			resultado.first();
			
			if (resultado.getRow()!=0)
				while(!resultado.isAfterLast())
				{
					latitud[resultado.getRow() - 1] = "" +
								resultado.getString("descripcion_latitud");
					resultado.next();
				}
		}
		catch(SQLException ex)
		{
			System.out.println("Error en sql " + sql + "\n" + ex.getMessage());
		}
		
		codigoRecurso = Proyecto.obtenerCodigoRecurso();
		codigoRegion = Proyecto.obtenerCodigoRegion();
		
		sql = "select * "+
			  "from caladero_resultado "+
			  "where codigo_recurso = " + codigoRecurso + " " +
			  "and codigo_region =  " + codigoRegion + " " +
			  "and codigo_dia = " + diaCaladero + " " +
			  "and codigo_anio = " + anioCaladero + " " +
			  "and codigo_proyecto = " + Proyecto.obtenerCodigo() + " " +
			  "order by codigo_caladero_resultado";			  
		
		resultado = CONEXIONBD.ejecutarConsulta(sql);
		
		try
		{
			resultado.first();
						
			if (resultado.getRow() != 0)
			{
				while(!resultado.isAfterLast())
				{					
					// Obtener las consideraciones para identificar los caladeros.
					codigo = resultado.getInt("codigo_caladero_resultado");
					descripcion = "Longitud: " +
						Servicio.obtenerNumeroFormateado(
						resultado.getDouble("longitud_inicial_caladero_resultado") ,4)
						 + " " +
						longitud[resultado.getInt("codigo_longitud_inicial") - 1] +
						"/" +
						Servicio.obtenerNumeroFormateado(
						resultado.getDouble("longitud_final_caladero_resultado") ,4)
						 + " " +
						longitud[resultado.getInt("codigo_longitud_final") - 1] +
						" Latitud: " +
						Servicio.obtenerNumeroFormateado(
						resultado.getDouble("latitud_inicial_caladero_resultado") ,4)
						 + " " +
						latitud[resultado.getInt("codigo_latitud_inicial") - 1] +
						"/" +
						Servicio.obtenerNumeroFormateado(
						resultado.getDouble("latitud_final_caladero_resultado") ,4)
						 + " " +
						latitud[resultado.getInt("codigo_latitud_final") - 1];
					
					cantidad = resultado.getDouble("biomasa_caladero_resultado");
					
					// Agregar al vector.
					elemento = new ElementoRestriccion(codigo, descripcion, cantidad);
					elemento.establecerCoordenada(
						new Coordenada(
							resultado.getDouble("longitud_inicial_caladero_resultado"),
							resultado.getDouble("latitud_inicial_caladero_resultado"),0) );
					
					vector.agregar(elemento);
					
					resultado.next();			
				}
			}
			
			// Agregar un punto de oferta ficticio.
			vector.agregar(new ElementoRestriccion(-1, "Punto de Oferta Ficticio", 0));
		}	
		catch(SQLException ex)
		{
			System.out.println ("Error en sql Oferta " + sql + "\n" +
				ex.getMessage());
		}
		
		// Desconectar a la base de datos.
		CONEXIONBD.desconectar();
		
		return vector;
	}
	
	/**
	 * Método que retorna un vector con los elementos principales de los puntos
	 * de demanda existentes en la base de datos.
	 *
	 * @return vector Vector con todos los puntos de demanda disponibles.
	 */
	private static VectorRestriccion cargarPuntosDemanda()
	{
		int codigo;
		int codigoEmpresa;
		String descripcion;
		String sql;
		String select;
		String where;
		String order;
		double cantidad;
		ResultSet resultado;
		ElementoRestriccion elemento;
		VectorRestriccion vector = new VectorRestriccion();
		
		// Conectar a la base de datos.
		CONEXIONBD.conectar();		
		
		codigoEmpresa = Proyecto.obtenerCodigoEmpresa();
		
		// Ejecutar consulta a la base de datos.
		select = "select codigo_punto_demanda, descripcion_punto_demanda, longitud_coordenada, "
			+ "descripcion_longitud, latitud_coordenada, descripcion_latitud "
			+ "from punto_demanda, coordenada, latitud, longitud ";
		
		where = "where punto_demanda.codigo_coordenada = coordenada.codigo_coordenada "
			+ "and punto_demanda.codigo_empresa = " + codigoEmpresa + " "
			+ "and coordenada.codigo_longitud = longitud.codigo_longitud and "
			+ "coordenada.codigo_latitud = latitud.codigo_latitud ";
		
		order = "order by codigo_punto_demanda";
		
		sql = select + where + order;	
		resultado = CONEXIONBD.ejecutarConsulta(sql);
		
		try
		{
			resultado.first();
			if(resultado.getRow() != 0)
			{			
				while(!resultado.isAfterLast())
				{
					codigo = resultado.getInt("codigo_punto_demanda");
					descripcion = resultado.getString("descripcion_punto_demanda")
								+ " Long. "
								+ resultado.getString("longitud_coordenada") + " "
								+ resultado.getString("descripcion_longitud")
								+ " Lat. "
								+ resultado.getString("latitud_coordenada") + " "
								+ resultado.getString("descripcion_latitud");
					cantidad = 0;
					
					// Agregar al vector.
					elemento = new ElementoRestriccion(codigo, descripcion,
													   cantidad);
					elemento.establecerCoordenada(
						new Coordenada(
							resultado.getDouble("longitud_coordenada"),
							resultado.getDouble("latitud_coordenada"),0) );
					
					vector.agregar(elemento);
					
					resultado.next();					
				}
			}
			
			// Agregar un punto de demanda ficticio.
			vector.agregar(new ElementoRestriccion(-1,
				"Punto de Demanda Ficticio", 0));
		}
		catch(SQLException ex)
		{
			System.out.println ("Error en sql Demanda " + sql + "\n" +
				ex.getMessage());
		}
		
		// Desconectar a la base de datos.
		CONEXIONBD.desconectar();
		
		return vector;
	}
	
	/**
	 * Método que retorna un vector con los elementos principales de los medios
	 * de transporte existentes en la base de datos.
	 *
	 * @return vector Vector con todos los medios de transporte disponibles.
	 */
	private static VectorRestriccion cargarMediosTransporte()
	{
		int codigo;
		int codigoEmpresa;
		String descripcion;
		String sql;
		String select;
		String where;
		double cantidad;
		ResultSet resultado;
		VectorRestriccion vector = new VectorRestriccion();
		
		// Conectar a la base de datos.
		CONEXIONBD.conectar();		
		
		codigoEmpresa = Proyecto.obtenerCodigoEmpresa();
		
		// Ejecutar consulta a la base de datos.
		sql = "select * from medio_transporte " + 
				" where codigo_empresa = " + codigoEmpresa +
				" order by codigo_medio_transporte";
		
		resultado = CONEXIONBD.ejecutarConsulta(sql);
		
		try
		{
			resultado.first();
			if(resultado.getRow() != 0)
			{			
				while(!resultado.isAfterLast())
				{
					codigo = resultado.getInt("codigo_medio_transporte");
					descripcion = resultado.getString("descripcion_medio_transporte");
					cantidad = resultado.getDouble("capacidad_medio_transporte");
					
					// Agregar al vector.
					vector.agregar(new ElementoRestriccion(codigo, descripcion,
														   cantidad));
					
					resultado.next();
				}
			}
			
			// Agregar un medio de transporte ficticio.
			vector.agregar(new ElementoRestriccion(-1, "Transporte Ficticio", 0));
		}
		catch(SQLException ex)
		{
			System.out.println ("Error en sql Medio " + sql + "\n" +
				ex.getMessage());
		}
		
		// Desconectar a la base de datos.
		CONEXIONBD.desconectar();
		
		return vector;
	}
	
	/**
	 * Método que establece la matriz de costos del problema de transporte.
	 *
	 * @param matriz Matriz de costos de valores reales.
	 */	
	public static void establecerMatrizCostos(double[][][] matriz)
	{
		// Establecer las restricciones.
		matrizCostos = matriz;
	}
	
	/**
	 * Método que establece parámetros de entrada del algoritmo genético de
	 * transporte.
	 *
	 * @param parametros Arreglo con los parámetros de entrada.
	 */	
	public static void establecerParametros(double[] parametros)
	{
		// Establecer los parámetros del algoritmo genético de transporte.
		tamanoPoblacion = (int) parametros[0];
		numeroGeneraciones = (int) parametros[1];
		probabilidadCruce = parametros[2];
		probabilidadMutacion = parametros[3];
		tipoFuncion = (int) parametros[4];
		tecnicaSeleccion = (int) parametros[5];
	}
	
	/**
	 * Método que establece el conjunto de restricciones de oferta.
	 * 
	 * @param vector Vector de restricciones de oferta.
	 */
	public static void establecerOferta(double[] vector)
	{
		oferta.modificarArrayCantidad(vector);
	}
	
	/**
	 * Método que establece el conjunto de restricciones de demanda.
	 *
	 * @param vector Vector de restricciones de demanda.
	 */
	public static void establecerDemanda(double[] vector)
	{
		demanda.modificarArrayCantidad(vector);
	}

	/**
	 * Método que establece el conjunto de restricciones de capacidad de
	 * transporte.
	 *
	 * @param vector Vector de restricciones de capacidad de transporte.
	 */
	public static void establecerCapacidad(double[] vector)
	{
		capacidad.modificarArrayCantidad(vector);
	}
	
	/**
	 * Método que resetea la cantidad asiganda a los puntos ficticios.
	 */
	public static void resetPuntosFicticios()
	{
		oferta.obtenerElemento(oferta.size()-1).establecerCantidad(0);
		demanda.obtenerElemento(demanda.size()-1).establecerCantidad(0);
		capacidad.obtenerElemento(capacidad.size()-1).establecerCantidad(0);
	}
	
	/**
	 * Método que devuelve el conjunto de restricciones de oferta.
	 * 
	 * @return oferta Vector de restricciones de oferta.
	 */	
	public static Vector obtenerOferta()
	{
		return oferta.obtenerArrayCantidad();
	}
	
	/**
	 * Método que devuelve el conjunto de restricciones de demanda.
	 *
	 * @return demanda Vector de restricciones de demanda.
	 */	
	public static Vector obtenerDemanda()
	{
		return demanda.obtenerArrayCantidad();
	}
	
	/**
	 * Método que devuelve el conjunto de restricciones de capacidad de
	 * transporte.
	 *
	 * @return capacidad Vector de restricciones de capacidad de transporte.
	 */	
	public static Vector obtenerCapacidad()
	{
		return capacidad.obtenerArrayCantidad();
	}
	
	/**
	 * Método que comprueba si el problema de transporte se encuentra o no 
	 * balanceado.
	 *
	 * @return balance Valor booleano que indica el resultado de la
	 *                 comprobación.
	 */
	public static boolean comprobarBalance()
	{
		double sumaOferta;
		double sumaDemanda;
		double sumaCapacidad;
		boolean balance;
		
		sumaOferta = oferta.obtenerSumatoriaCantidad();
		sumaDemanda = demanda.obtenerSumatoriaCantidad();
		sumaCapacidad = capacidad.obtenerSumatoriaCantidad();
		
		if(sumaOferta == sumaDemanda && sumaDemanda == sumaCapacidad)
			balance = true;
		else
			balance = false;
		
		return balance;
	}
	
	/**
	 * Método que permite balancear el problema de transporte asignando el total 
	 * faltante a los puntos ficticios que se han creado.
	 */	
	public static void balancearProblema()
	{
		double sumaOferta;
		double sumaDemanda;
		double sumaCapacidad;
		double mayor;
		boolean balance;
		
		sumaOferta = oferta.obtenerSumatoriaCantidad();
		sumaDemanda = demanda.obtenerSumatoriaCantidad();
		sumaCapacidad = capacidad.obtenerSumatoriaCantidad();
		
		if(sumaOferta >= sumaDemanda && sumaOferta >= sumaCapacidad)
			mayor = sumaOferta;
		else
		if(sumaDemanda >= sumaOferta && sumaDemanda >= sumaCapacidad)
			mayor = sumaDemanda;
		else
			mayor = sumaCapacidad;
		
		oferta.obtenerElemento(oferta.size()-1).
			establecerCantidad(mayor - sumaOferta);
		demanda.obtenerElemento(demanda.size()-1).
			establecerCantidad(mayor - sumaDemanda);
		capacidad.obtenerElemento(capacidad.size()-1).
			establecerCantidad(mayor - sumaCapacidad);
	}
	
	/**
	 * Método que devuelve el día del año en el cual se están realizando las 
	 * distintas tareas del módulo operación.
	 *
	 * @return diaCaladero Día del año configurado.
	 */
	public static int obtenerDiaCaladero()
	{
		return diaCaladero;
	}
	
	/**
	 * Método que devuelve el año en el cual se están realizando las distintas
	 * tareas del módulo operación.
	 *
	 * @return anioCaladero Año configurado.
	 */
	public static int obtenerAnioCaladero()
	{
		return anioCaladero;
	}	
}