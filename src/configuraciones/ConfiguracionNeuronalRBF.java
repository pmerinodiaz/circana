/**
 * @(#)ConfiguracionRedNeuronalRBF.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que contiene solamente valores estáticos, que sirven para mantener o
 * proveer información especial la red neuronal Radial Base Fase(RBF). Esta
 * clase se comunica con la base de datos en MySql. Así, la red a través de esta
 * clase interfiere con la base de datos.
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see ResultSet
 * @see SQLException
 * @see RedNeuronalRBF
 * @see BaseDatoMotor
 * @see BaseDatoTablaBasica
 */
public class ConfiguracionNeuronalRBF
{
	/**
	 * Indica cual es la distancia máxima que puede haber entre los centroides y
	 * el patrón.
	 */
	public final static double FACTOR_DISTANCIA = 0.1;
	
	/** Indica la cuota inferior del intervalo  en que se maneja la red. */
	public final static double INTERVALO_MENOR = -0.4;
	
	/** Indica la cuota superior del intervalo  en que se maneja la red. */
	public final static double INTERVALO_MAYOR = 0.4;
	
	/** Indica que uno de los errores de la red no esta iniciado. */
	public final static int ERROR_NO_INICIADO = -1;
	
	/**
	 * El número total de elementos en la configuración excluyendo este y los
	 * anteriores.
	 */
	public final static int TOTAL_ELEMENTOS_CONFIGURACION = 17;
	
	/** El número máximo de patrones. */
	public final static int NUMERO_MAXIMO_PATRONES = 200;
	
	/** El número máximo de entradas que puede tener la red neuronal. */
	public final static int NUMERO_MAXIMO_ENTRADAS = 30;
	
	/** El número máximo de centroides que puede tener la red. */ 
	public final static int NUMERO_MAXIMO_CENTROIDES = 50;
	
	/** El número máximo de salidas que puede tener la red. */
	public final static int NUMERO_MAXIMO_SALIDAS = 30;
	
	/** Tabla conectada a la tabla de la base de datos. */
	private final static BaseDatoTablaBasica TABLA =
		new BaseDatoTablaBasica(Proyecto.CONEXION,"configuracion_rbf");
	
	/** Número de patrones. */
	private static int numeroPatrones;
	
	/** Número de entradas que tiene la red. */
	private static int numeroEntradas;
	
	/** Número de centroides que tiene la red. */
	private static int numeroCentroides;
	
	/** Número de salidas que tiene la red. */
	private static int numeroSalidas;
	
	/** Cantidad de ciclos del aprendizaje no supervisado. */
  	private static double cicloNoSupervisado;
  	
  	/** Cantidad de ciclos del aprendizaje supervisado. */
  	private static double cicloSupervisado;
	
  	/** Porcentaje de validación cruzada. */
  	private static double porcentajeCruzada;
  	
  	/** Error mínimo aceptable para la red. */
  	private static double errorMinimo;
  	
  	/** Error máximo desbordable por la red. */
  	private static double errorMaximo;
  	
  	/** Valor de la rata de aprendizaje supervisada. */
  	private static double rataSupervisada;
	
	/** Valor de la variación de la rata de aprendizaje supervisada. */
  	private static double variacionRataSupervisada;
  	
  	/** Valor de la rata inicial del aprendizaje no supervisada. */
  	private static double rataINoSupervisada;
  	
  	/** Valor de la rata final del aprendizaje no supervisada. */
  	private static double rataFNoSupervisada;
  	
  	/** Indica si los centroides crecen en forma dinámica. */
	private static boolean habilitarCentroidesDinamicos;
	
  	/**
     * Método que carga una configuración de la base de datos. Al ser cargada
     * todas las variables tomarán los valores según la configuración. Si el
     * código del proyecto es NUEVO_PROYECTO se carga la configuración por
     * defecto.
     *
     * @param codigoProyecto Código del proyecto que se encuentra en la base de
     *                       datos.
     */
	public static void cargar(int codigoProyecto)
	{
		// almacenado en la base datos
		ResultSet resultado;
		
		// conectano a la base datos
		Proyecto.CONEXION.conectar();
		
		// realizando consulta
		resultado = TABLA.buscar("codigo_proyecto","" + codigoProyecto);
		
		try
		{
			// primer y unico elemento de la consulta
			resultado.first();
			
			numeroPatrones =
				resultado.getInt("numero_patrones_configuracion_rbf");
			numeroEntradas =
				resultado.getInt("numero_entradas_configuracion_rbf");
			numeroCentroides =
				resultado.getInt("numero_centroides_configuracion_rbf");
			numeroSalidas =
				resultado.getInt("numero_salidas_configuracion_rbf");
			errorMinimo =
				resultado.getInt("error_minimo_configuracion_rbf");
			errorMaximo =
				resultado.getInt("error_maximo_configuracion_rbf");
			cicloNoSupervisado =
				resultado.getDouble("ciclo_no_Supervisado_configuracion_rbf");
			cicloSupervisado =
				resultado.getDouble("ciclo_supervisado_configuracion_rbf");
			rataSupervisada =
				resultado.getDouble("rata_supervisada_configuracion_rbf");
			variacionRataSupervisada =
				resultado.getDouble("variacion_rata_supervisada_configuracion_rbf");
			rataINoSupervisada =
				resultado.getDouble("ratai_no_supervisada_configuracion_rbf");
			rataFNoSupervisada =
				resultado.getDouble("rataf_no_supervisada_configuracion_rbf");
			porcentajeCruzada =
				resultado.getDouble("porcentaje_cruzada_configuracion_rbf");
			habilitarCentroidesDinamicos =
				resultado.getBoolean("habilitar_centroides_dinamicos_configuracion_rbf");
		}
		catch(SQLException ex)
		{
			// si hay errores al cargar la base datos
			System.err.println("No se pudo cargar la tabla configuracion_rbf.");
		}	
		
		// desconectando
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Método que guarda las variables que representan a una configuración en la
     * base de datos. Se guarda en la base de datos según el código del
     * proyecto.
     * 
     * @param codigoProyecto Código del proyecto que se encuentra en la base de
     *                       datos.
     */
	public static void guardar(int codigoProyecto)
	{
		int habilitadoCentroides;
		
		// si esta habilitado los centroides dinámicos
		if (habilitarCentroidesDinamicos)
			habilitadoCentroides = 1;
		else
			habilitadoCentroides = 0;
		
		// conectando a la base datos
		Proyecto.CONEXION.conectar();
		
		// Preparando campo para actualizar
		TABLA.actualizarCampo("numero_patrones_configuracion_rbf",
							  "" + numeroPatrones);
		TABLA.actualizarCampo("numero_entradas_configuracion_rbf",
							  "" + numeroEntradas);
		TABLA.actualizarCampo("numero_centroides_configuracion_rbf",
							  "" + numeroCentroides);
		TABLA.actualizarCampo("numero_salidas_configuracion_rbf",
							  "" + numeroSalidas);
		TABLA.actualizarCampo("error_maximo_configuracion_rbf",
							  "" + errorMaximo);
		TABLA.actualizarCampo("error_minimo_configuracion_rbf",
							  "" + errorMinimo);
		TABLA.actualizarCampo("ciclo_supervisado_configuracion_rbf",
							  "" + cicloSupervisado);
		TABLA.actualizarCampo("ciclo_no_supervisado_configuracion_rbf",
							  "" + cicloNoSupervisado);
		TABLA.actualizarCampo("rata_supervisada_configuracion_rbf",
							  "" + rataSupervisada);
		TABLA.actualizarCampo("variacion_rata_supervisada_configuracion_rbf",
							  "" + variacionRataSupervisada);
		TABLA.actualizarCampo("ratai_no_supervisada_configuracion_rbf",
							  "" + rataINoSupervisada);
		TABLA.actualizarCampo("rataf_no_supervisada_configuracion_rbf",
							  "" + rataFNoSupervisada);
		TABLA.actualizarCampo("porcentaje_cruzada_configuracion_rbf",
							  "" + porcentajeCruzada);
		TABLA.actualizarCampo("habilitar_centroides_dinamicos_configuracion_rbf",
							  "" +  habilitadoCentroides);
		
		// agregando a la tabla
		TABLA.actualizar("codigo_proyecto","" + codigoProyecto);
		
		// desconectando de la base datos
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Método que agrega un registro de configuración neuronal RBF en la base 
     * de datos. Este registro se agrega según el proyecto que se creó (por el
     * hecho que la configuración está asociada al proyecto).
     * 
     * @param codigoProyecto Código del proyecto
     */
	public static void agregar(int codigoProyecto)
	{
		int habilitadoCentroides;
		
		// si esta habilitado los centroides dinamicos
		if (habilitarCentroidesDinamicos)
			habilitadoCentroides = 1;
		else
			habilitadoCentroides = 0;
		
		// conectando a la base de datos
		Proyecto.CONEXION.conectar();
		
		// Preparando campo para agregar
		TABLA.agregarCampo("codigo_proyecto",
						   "" + codigoProyecto);
		TABLA.agregarCampo("numero_patrones_configuracion_rbf",
						   "" + numeroPatrones);
		TABLA.agregarCampo("numero_entradas_configuracion_rbf",
						   "" + numeroEntradas);
		TABLA.agregarCampo("numero_centroides_configuracion_rbf",
						   "" + numeroCentroides);
		TABLA.agregarCampo("numero_salidas_configuracion_rbf",
						   "" + numeroSalidas);
		TABLA.agregarCampo("error_maximo_configuracion_rbf",
						   "" + errorMaximo);
		TABLA.agregarCampo("error_minimo_configuracion_rbf",
						   "" + errorMinimo);
		TABLA.agregarCampo("ciclo_supervisado_configuracion_rbf",
						   "" + cicloSupervisado);
		TABLA.agregarCampo("ciclo_no_supervisado_configuracion_rbf",
						   "" + cicloNoSupervisado);
		TABLA.agregarCampo("rata_supervisada_configuracion_rbf",
						   "" + rataSupervisada);
		TABLA.agregarCampo("variacion_rata_supervisada_configuracion_rbf",
						   "" + variacionRataSupervisada);
		TABLA.agregarCampo("ratai_no_supervisada_configuracion_rbf",
						   "" + rataINoSupervisada);
		TABLA.agregarCampo("rataf_no_supervisada_configuracion_rbf",
						   "" + rataFNoSupervisada);
		TABLA.agregarCampo("porcentaje_cruzada_configuracion_rbf",
						   "" + porcentajeCruzada);
		TABLA.agregarCampo("habilitar_centroides_dinamicos_configuracion_rbf",
						   "" + habilitadoCentroides);
		
		// agregando a la tabla
		TABLA.agregar();
		
		// desconectando de la base datos
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Método modifica los valores de la configuración según los valores
     * ingresados. Los valores ingresados deben venir en forma de un arreglo
     * de tipo double. Cual hay poner atención en el orden de en que se
     * ingresan los valores al arreglo. Generalmente usada para la configuración
     * de la herramienta del frame.
     * 
     * @param parametros Arreglo con los valores de la configuración.
     */
	public static void modificarValores(double[] parametros)
	{
		// Si no hay errores
		if (validarValores(parametros) == "")
		{
			numeroCentroides         = (int) parametros[5];
			cicloNoSupervisado       = parametros[7];
			cicloSupervisado         = parametros[8];
			rataSupervisada			 = parametros[12];
			variacionRataSupervisada = parametros[13];
			errorMinimo 			 = (int) parametros[10];
			errorMaximo 			 = (int) parametros[11];
			porcentajeCruzada        = parametros[9];
			rataINoSupervisada       = parametros[14];
			rataFNoSupervisada       = parametros[15];
		}
	}
	
	/**
     * Método que entrega una configuración. Usada generalmente para la
     * configuración de la herramienta del frame configuración de economía.
     * 
     * @return parametros Arreglo con los valores de la configuración.
     */
	public static double[] entregarValores()
	{
		double[] parametros = new double[TOTAL_ELEMENTOS_CONFIGURACION];
		
		parametros[0] = NUMERO_MAXIMO_PATRONES;
		parametros[1] = NUMERO_MAXIMO_ENTRADAS;
		parametros[2] = NUMERO_MAXIMO_CENTROIDES;
		parametros[3] = NUMERO_MAXIMO_SALIDAS;
		parametros[3] = numeroPatrones;
		parametros[4] = numeroEntradas;
		parametros[5] = numeroCentroides;
		parametros[6] = numeroSalidas;
		parametros[7] = cicloNoSupervisado;
		parametros[8] = cicloSupervisado;
		parametros[9] = porcentajeCruzada;
		parametros[10] = errorMinimo;
		parametros[11] = errorMaximo;
		parametros[12] = rataSupervisada;
		parametros[13] = variacionRataSupervisada;
		parametros[14] = rataINoSupervisada;
		parametros[15] = rataFNoSupervisada;
		
		return parametros;
	}
	
	/**
     * Método que valida los parámetros entregados a la configuración. Por cada
     * error comentido agrega una cadena de string con el error. Un error se
     * evalua según los rangos de cada item de la configuración.
     * 
     * @param parametros Arreglo de tipo double con los valores de la 
     *					 configuración.
     *
     * @return error String con los errores cometido por la configuración de
     * 			     parámetros.
	 */
	public static String validarValores(double[] parametros)
	{
		String error = "";
		
		// validando valores que se intentan modificar
		
		// validando ciclos no supervisado
		if (parametros[7] != (int)parametros[7] || 
			parametros[7] <= 0 || parametros[7] > 50000)
			error += "- En el número de ciclos no supervisado.\n";
		
		// validando número centroides
		if (parametros[5] != (int)parametros[5] ||
			parametros[5] <= 0 || parametros[5] > NUMERO_MAXIMO_CENTROIDES)
			error += "- En el número centroides.\n";
		
		// validando rata inicial no supervisada
		if (parametros[14] < 0 || parametros[14] > 0.6)
			error += "- En la rata inicial no supervisada.\n";
		
		// validando rata final no supervisada
		if (parametros[15] < 0 || parametros[15] > 0.6)
			error += "- En la rata final no supervisada.\n";
		
		// validando ciclos supervisado
		if (parametros[8] != (int)parametros[8] || 
			parametros[8] <= 0 || parametros[8] > 200000)
			error += "- En el número de ciclos supervisado.\n";
		
		// validando rata supervisada
		if (parametros[12] < 0 || parametros[12] > 0.1)
			error += "- En la rata supervisada.\n";
		
		// validando variación rata	supervisada	
		if (parametros[13] < 1 || parametros[13] > 3)
			error += "- En la variación de la rata supervisada.\n";
		
		// validando error mínimo
		if (parametros[10] != (int)parametros[10] || 
			parametros[10] <= 0 || parametros[10] > 6)
			error += "- En error mínimo aceptable.\n";
		
		// validando error máximo
		if (parametros[11] != (int)parametros[11] || 
			parametros[11] <= 0 || parametros[11] > 4)
			error += "- En error máximo aceptable.\n";
		
		// validando porcentaje cruzado
		if (parametros[9] < 0 || parametros[9] > 0.5)
			error += "- En el porcentaje de validación cruzada.\n";
		
		return error;
	}
	
	/**
     * Método que establece el número de patrones de configuración RBF.
     * 
     * @param numeroPatrones El número de Patrones.
     */
	public static void cambiarNumeroPatrones(int numeroPatrones)
	{
		ConfiguracionNeuronalRBF.numeroPatrones = numeroPatrones;
	}
	
	/**
     * Método que devuelve el número de entradas que tiene la red Neuronal.
     * 
     * @return numeroEntradas El número de entradas de la red.
     */
	public static int obtenerNumeroEntradas()
	{
		return numeroEntradas;
	}
	
	/**
     * Método que devuelve el número de salidas que tiene la red Neuronal.
     * 
     * @return numeroSalidas El número de entradas de la red.
     */
	public static int obtenerNumeroSalidas()
	{
		return numeroSalidas;
	}
	
	/**
     * Método devuelve verdadero si esta habilitado los centroides dinámicos, si
     * no esta habilitado falso.
     * 
     * @return habilitarCentroidesDinamicos Indica si el crecimientos de los
     *										centroides esta habilitado o no.
     */
	public static boolean obtenerHabilitarCentroidesDinamicos()
	{
		return habilitarCentroidesDinamicos;
	}
	
	/**
     * Método que establece la habilidad de que el número los centroides crecen 
     * en forma dinámica.
     * 
     * @param habilitarCentroidesDinamicos Indica si el crecimientos de los
     *									   centroides esta habilitado o no.
     */
	public static void establecerHabilitarCentroidesDinamicos
					(boolean habilitarCentroidesDinamicos)
	{
		ConfiguracionNeuronalRBF.habilitarCentroidesDinamicos = 
			habilitarCentroidesDinamicos;
	}
}