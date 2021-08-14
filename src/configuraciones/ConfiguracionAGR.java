/**
 * @(#)ConfiguracionAGR.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que contiene solamente atributos con valores est�ticos, que sirven para
 * mantener la informaci�n personalizada del usuario sobre los par�metros de 
 * entrada utilizados por el algoritmo gen�tico de ruteo. Esta clase se comunica
 * con la Base de Datos MySQL.
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
 * @see Vector
 * @see Resultset
 * @see SQLException
 * @see BaseDatoMotor
 * @see BaseDatoTablaBasica
 * @see VectorRuta
 */
public class ConfiguracionAGR
{
	/** Conexi�n a la base de datos. */
	private final static BaseDatoMotor CONEXIONBD = new BaseDatoMotor();	
	
	/**
	 * La tabla de donde se obtiene los datos de la configuraci�n algoritmo
	 * gen�tico.
	 */
	private final static BaseDatoTablaBasica CONFIGURACION_AGR =
		new BaseDatoTablaBasica(CONEXIONBD, "configuracion_agr");
	
	/** Constante con la probabilidad para selecci�n de torneo. */
	public final static double PROBABILIDAD_FLIP = 0.95;
	
	/** Tabla de distancias. */
	public static double[][] tablaDistancia;
	
	/** Indica el n�mero de individuos de una poblaci�n. */
	public static int tamanoPoblacion;
	
	/** Indica el n�mero generaciones que itera el algoritmo gen�tico de ruteo. */
	public static int numeroGeneraciones;
	
	/** Indica la t�cnica de selecci�n utilizada (0:proporcional 1:torneo). */
	public static int tecnicaSeleccion;
	
	/** Indica la probabilidad con que los individuos se cruzan. */
	public static double probabilidadCruce;
	
	/** Indica la probabilidad con que un individuo es mutado. */
	public static double probabilidadMutacion;
	
	/** Lista que contiene todos los nodos del grafo. */
	public static VectorRuta grafoCompleto;
	
	/** Texto con el cuadro de las distancias entre los puntos. */
	public static String cuadroDistancia;
	
	/**
	 * M�todo que carga una configuraci�n del algoritmo gen�tico de ruteo a un
	 * proyecto. Cuando el proyecto es nuevo, se cargan los valores por defecto.
	 * Es decir, en este m�todo se asigna el valor inicial a todos los atributos
	 * de esta clase.
	 *
	 * @param codigoProyecto El c�digo del proyecto que se encuentra en la Base
	 *                       de Datos.
	 */
	public static void cargar(int codigoProyecto) 
	{		
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		CONEXIONBD.conectar();
		
		// Realizar la consulta.
		resultado = CONFIGURACION_AGR.buscar("codigo_proyecto",
											 ""+codigoProyecto);
		
		// Setear los valores de los atributos.
		try
		{
			// Apuntar al primer registro.
			resultado.first();
			
			// Setear los valores.
			tecnicaSeleccion = resultado.getInt("codigo_tecnica_seleccion");
			tamanoPoblacion = 
				resultado.getInt("tamanio_poblacion_configuracion_agr");
			numeroGeneraciones = 
				resultado.getInt("numero_generaciones_configuracion_agr");
			probabilidadCruce = 
				resultado.getDouble("probabilidad_cruce_configuracion_agr");
			probabilidadMutacion = 
				resultado.getDouble("probabilidad_mutacion_configuracion_agr");
			establecerTablaDistancia();
		}
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla configuracion_agr.");
		}
		
		// Desconectar a la base de datos.
		CONEXIONBD.desconectar();
	}
	
	/**
	 * M�todo que guarda una configuraci�n del algoritmo gen�tico de ruteo en la
	 * Base de Datos, seg�n el identificador del proyecto.
	 *
	 * @param codigoProyecto Identificador o c�digo del proyecto que se encuentra
	 *						 en la Base de Datos.
	 */
	public static void guardar(int codigoProyecto)
	{
		// Conectar a la base de datos.
		CONEXIONBD.conectar();
		
		// Actualizar los campos con los nuevos valores.
		CONFIGURACION_AGR.actualizarCampo(
			"codigo_tecnica_seleccion", 
			"" + tecnicaSeleccion);
		CONFIGURACION_AGR.actualizarCampo(
			"tamanio_poblacion_configuracion_agr",
			"" + tamanoPoblacion);
		CONFIGURACION_AGR.actualizarCampo(
			"numero_generaciones_configuracion_agr",
			""+numeroGeneraciones);
		CONFIGURACION_AGR.actualizarCampo(
			"probabilidad_cruce_configuracion_agr",
			"" + probabilidadCruce);
		CONFIGURACION_AGR.actualizarCampo(
			"probabilidad_mutacion_configuracion_agr",
			"" + probabilidadMutacion);
		
		// Ejecutar la actualizaci�n en la tabla configuracion_agr.
		CONFIGURACION_AGR.actualizar("codigo_proyecto", "" + codigoProyecto);
		
		// Desconectar a la base de datos.
		CONEXIONBD.desconectar();
	}
	
	/**
     * M�todo que agrega un registro de configuraci�n del algoritmo gen�tico de
     * ruteo en la base de datos. Este registro se agrega seg�n el proyecto que
     * se cre�.
     * 
     * @param codigoProyecto Identificador del Proyecto
     */
	public static void agregar(int codigoProyecto)
	{
		// Conectar a la base de datos.
		CONEXIONBD.conectar();
		
		// Preparar un registro para agregar.
		CONFIGURACION_AGR.agregarCampo("codigo_proyecto",
									   "" + codigoProyecto);
		CONFIGURACION_AGR.agregarCampo("codigo_tecnica_seleccion", 
									   "" + tecnicaSeleccion);
		CONFIGURACION_AGR.agregarCampo("tamanio_poblacion_configuracion_agr",
									   "" + tamanoPoblacion);
		CONFIGURACION_AGR.agregarCampo("numero_generaciones_configuracion_agr",
									   ""+numeroGeneraciones);
		CONFIGURACION_AGR.agregarCampo("probabilidad_cruce_configuracion_agr",
									   "" + probabilidadCruce);
		CONFIGURACION_AGR.agregarCampo("probabilidad_mutacion_configuracion_agr",
									   "" + probabilidadMutacion);
		
		// Ejecutar el insert en la tabla configuracion_agr.
		CONFIGURACION_AGR.agregar();
		
		// Desconectar a la base de datos.
		CONEXIONBD.desconectar();
	}
	
	/**
	 * M�todo que crea una tabla con las distancias entre los distintos nodos
	 * del grafo.
	 */
	public static void establecerTablaDistancia()
	{
		int tamanio;
		double distancia;
		
		crearGrafo();
		tamanio = grafoCompleto.size();
		tablaDistancia = new double[tamanio][tamanio];
		cuadroDistancia = "";
		
		for (int i=0; i<tamanio; i++)
		{
			for (int j=0; j<tamanio; j++)
			{
				// Retorna la distancia entre dos nodos.
				distancia = Servicio.obtenerDistancia(
					grafoCompleto.obtenerElemento(i).obtenerCoordenada() ,
					grafoCompleto.obtenerElemento(j).obtenerCoordenada());	
				
				tablaDistancia[i][j] = distancia;
				cuadroDistancia = cuadroDistancia +
					grafoCompleto.obtenerElemento(i).obtenerDescripcion() +
					" -> " +
					grafoCompleto.obtenerElemento(j).obtenerDescripcion() +
					" = " +
					Servicio.obtenerNumeroFormateado(distancia,8) +
					"\n";
			}
		}
	}
	
	/**
	 * M�todo que crea una lista con los nodos contenidos en el grafo. Este es 
	 * un grafo completo con todos los puntos de oferta y demanda.
	 */
	public static void crearGrafo()
	{
		int i;
		int indice;
		int codigo;
		String descripcion;
		String tipo;
		Coordenada coordenada;
		ElementoRuta elementoRuta;
		
		indice = 0;
		tipo = "Oferta";
		grafoCompleto = new VectorRuta();
		
		// Incoporar al grafo los nodos de oferta.
		for (i=0; i<(ConfiguracionAGT.m-1); i++)
		{
			codigo = ConfiguracionAGT.oferta.obtenerElemento(i).obtenerCodigo();
			descripcion = ConfiguracionAGT.oferta.obtenerElemento(i).
							obtenerDescripcion();
			coordenada = ConfiguracionAGT.oferta.obtenerElemento(i).
							obtenerCoordenada();
			elementoRuta = new ElementoRuta(indice, codigo, descripcion, 
								tipo, coordenada);
			grafoCompleto.agregar(elementoRuta);
			indice ++;
		}
		
		tipo = "Demanda";
		
		// Incorporar al grafo los nodos de demanda.
		for (i=0; i<(ConfiguracionAGT.n-1); i++)
		{
			codigo = ConfiguracionAGT.demanda.obtenerElemento(i).obtenerCodigo();
			descripcion = ConfiguracionAGT.demanda.obtenerElemento(i).
							obtenerDescripcion();
			coordenada = ConfiguracionAGT.demanda.obtenerElemento(i).
							obtenerCoordenada();
			elementoRuta = new ElementoRuta(indice, codigo, descripcion, 
								tipo, coordenada);
			grafoCompleto.agregar(elementoRuta);
			indice ++;
		}
	}
	
	/**
	 * M�todo que establece los par�metros de entrada del algoritmo gen�tico de
	 * ruteo.
	 *
	 * @param parametros Arreglo con los parametros de entrada.
	 */	
	public static void establecerParametros(double[] parametros)
	{
		// Establecer los par�metros del algoritmo.
		tamanoPoblacion = (int) parametros[0];
		numeroGeneraciones = (int) parametros[1];
		probabilidadCruce = parametros[2];
		probabilidadMutacion = parametros[3];
		tecnicaSeleccion = (int) parametros[4];
	}
}