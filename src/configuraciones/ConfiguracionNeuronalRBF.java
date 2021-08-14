/**
 * @(#)ConfiguracionRedNeuronalRBF.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que contiene solamente valores est�ticos, que sirven para mantener o
 * proveer informaci�n especial la red neuronal Radial Base Fase(RBF). Esta
 * clase se comunica con la base de datos en MySql. As�, la red a trav�s de esta
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
	 * Indica cual es la distancia m�xima que puede haber entre los centroides y
	 * el patr�n.
	 */
	public final static double FACTOR_DISTANCIA = 0.1;
	
	/** Indica la cuota inferior del intervalo  en que se maneja la red. */
	public final static double INTERVALO_MENOR = -0.4;
	
	/** Indica la cuota superior del intervalo  en que se maneja la red. */
	public final static double INTERVALO_MAYOR = 0.4;
	
	/** Indica que uno de los errores de la red no esta iniciado. */
	public final static int ERROR_NO_INICIADO = -1;
	
	/**
	 * El n�mero total de elementos en la configuraci�n excluyendo este y los
	 * anteriores.
	 */
	public final static int TOTAL_ELEMENTOS_CONFIGURACION = 17;
	
	/** El n�mero m�ximo de patrones. */
	public final static int NUMERO_MAXIMO_PATRONES = 200;
	
	/** El n�mero m�ximo de entradas que puede tener la red neuronal. */
	public final static int NUMERO_MAXIMO_ENTRADAS = 30;
	
	/** El n�mero m�ximo de centroides que puede tener la red. */ 
	public final static int NUMERO_MAXIMO_CENTROIDES = 50;
	
	/** El n�mero m�ximo de salidas que puede tener la red. */
	public final static int NUMERO_MAXIMO_SALIDAS = 30;
	
	/** Tabla conectada a la tabla de la base de datos. */
	private final static BaseDatoTablaBasica TABLA =
		new BaseDatoTablaBasica(Proyecto.CONEXION,"configuracion_rbf");
	
	/** N�mero de patrones. */
	private static int numeroPatrones;
	
	/** N�mero de entradas que tiene la red. */
	private static int numeroEntradas;
	
	/** N�mero de centroides que tiene la red. */
	private static int numeroCentroides;
	
	/** N�mero de salidas que tiene la red. */
	private static int numeroSalidas;
	
	/** Cantidad de ciclos del aprendizaje no supervisado. */
  	private static double cicloNoSupervisado;
  	
  	/** Cantidad de ciclos del aprendizaje supervisado. */
  	private static double cicloSupervisado;
	
  	/** Porcentaje de validaci�n cruzada. */
  	private static double porcentajeCruzada;
  	
  	/** Error m�nimo aceptable para la red. */
  	private static double errorMinimo;
  	
  	/** Error m�ximo desbordable por la red. */
  	private static double errorMaximo;
  	
  	/** Valor de la rata de aprendizaje supervisada. */
  	private static double rataSupervisada;
	
	/** Valor de la variaci�n de la rata de aprendizaje supervisada. */
  	private static double variacionRataSupervisada;
  	
  	/** Valor de la rata inicial del aprendizaje no supervisada. */
  	private static double rataINoSupervisada;
  	
  	/** Valor de la rata final del aprendizaje no supervisada. */
  	private static double rataFNoSupervisada;
  	
  	/** Indica si los centroides crecen en forma din�mica. */
	private static boolean habilitarCentroidesDinamicos;
	
  	/**
     * M�todo que carga una configuraci�n de la base de datos. Al ser cargada
     * todas las variables tomar�n los valores seg�n la configuraci�n. Si el
     * c�digo del proyecto es NUEVO_PROYECTO se carga la configuraci�n por
     * defecto.
     *
     * @param codigoProyecto C�digo del proyecto que se encuentra en la base de
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
     * M�todo que guarda las variables que representan a una configuraci�n en la
     * base de datos. Se guarda en la base de datos seg�n el c�digo del
     * proyecto.
     * 
     * @param codigoProyecto C�digo del proyecto que se encuentra en la base de
     *                       datos.
     */
	public static void guardar(int codigoProyecto)
	{
		int habilitadoCentroides;
		
		// si esta habilitado los centroides din�micos
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
     * M�todo que agrega un registro de configuraci�n neuronal RBF en la base 
     * de datos. Este registro se agrega seg�n el proyecto que se cre� (por el
     * hecho que la configuraci�n est� asociada al proyecto).
     * 
     * @param codigoProyecto C�digo del proyecto
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
     * M�todo modifica los valores de la configuraci�n seg�n los valores
     * ingresados. Los valores ingresados deben venir en forma de un arreglo
     * de tipo double. Cual hay poner atenci�n en el orden de en que se
     * ingresan los valores al arreglo. Generalmente usada para la configuraci�n
     * de la herramienta del frame.
     * 
     * @param parametros Arreglo con los valores de la configuraci�n.
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
     * M�todo que entrega una configuraci�n. Usada generalmente para la
     * configuraci�n de la herramienta del frame configuraci�n de econom�a.
     * 
     * @return parametros Arreglo con los valores de la configuraci�n.
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
     * M�todo que valida los par�metros entregados a la configuraci�n. Por cada
     * error comentido agrega una cadena de string con el error. Un error se
     * evalua seg�n los rangos de cada item de la configuraci�n.
     * 
     * @param parametros Arreglo de tipo double con los valores de la 
     *					 configuraci�n.
     *
     * @return error String con los errores cometido por la configuraci�n de
     * 			     par�metros.
	 */
	public static String validarValores(double[] parametros)
	{
		String error = "";
		
		// validando valores que se intentan modificar
		
		// validando ciclos no supervisado
		if (parametros[7] != (int)parametros[7] || 
			parametros[7] <= 0 || parametros[7] > 50000)
			error += "- En el n�mero de ciclos no supervisado.\n";
		
		// validando n�mero centroides
		if (parametros[5] != (int)parametros[5] ||
			parametros[5] <= 0 || parametros[5] > NUMERO_MAXIMO_CENTROIDES)
			error += "- En el n�mero centroides.\n";
		
		// validando rata inicial no supervisada
		if (parametros[14] < 0 || parametros[14] > 0.6)
			error += "- En la rata inicial no supervisada.\n";
		
		// validando rata final no supervisada
		if (parametros[15] < 0 || parametros[15] > 0.6)
			error += "- En la rata final no supervisada.\n";
		
		// validando ciclos supervisado
		if (parametros[8] != (int)parametros[8] || 
			parametros[8] <= 0 || parametros[8] > 200000)
			error += "- En el n�mero de ciclos supervisado.\n";
		
		// validando rata supervisada
		if (parametros[12] < 0 || parametros[12] > 0.1)
			error += "- En la rata supervisada.\n";
		
		// validando variaci�n rata	supervisada	
		if (parametros[13] < 1 || parametros[13] > 3)
			error += "- En la variaci�n de la rata supervisada.\n";
		
		// validando error m�nimo
		if (parametros[10] != (int)parametros[10] || 
			parametros[10] <= 0 || parametros[10] > 6)
			error += "- En error m�nimo aceptable.\n";
		
		// validando error m�ximo
		if (parametros[11] != (int)parametros[11] || 
			parametros[11] <= 0 || parametros[11] > 4)
			error += "- En error m�ximo aceptable.\n";
		
		// validando porcentaje cruzado
		if (parametros[9] < 0 || parametros[9] > 0.5)
			error += "- En el porcentaje de validaci�n cruzada.\n";
		
		return error;
	}
	
	/**
     * M�todo que establece el n�mero de patrones de configuraci�n RBF.
     * 
     * @param numeroPatrones El n�mero de Patrones.
     */
	public static void cambiarNumeroPatrones(int numeroPatrones)
	{
		ConfiguracionNeuronalRBF.numeroPatrones = numeroPatrones;
	}
	
	/**
     * M�todo que devuelve el n�mero de entradas que tiene la red Neuronal.
     * 
     * @return numeroEntradas El n�mero de entradas de la red.
     */
	public static int obtenerNumeroEntradas()
	{
		return numeroEntradas;
	}
	
	/**
     * M�todo que devuelve el n�mero de salidas que tiene la red Neuronal.
     * 
     * @return numeroSalidas El n�mero de entradas de la red.
     */
	public static int obtenerNumeroSalidas()
	{
		return numeroSalidas;
	}
	
	/**
     * M�todo devuelve verdadero si esta habilitado los centroides din�micos, si
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
     * M�todo que establece la habilidad de que el n�mero los centroides crecen 
     * en forma din�mica.
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