/**
 * @(#)ConfiguracionRedNeuronalBP.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que contiene solamente valores y m�todos est�ticos que sirven para
 * mantener o proveer informaci�n especial o de configuraci�n a la red neuronal
 * artificial Back-Propagation. Tambi�n esta clase se comunica con el motor de
 * base de datos MySql. La comunicaci�n se basa en el traspaso de la
 * configuraci�n a la base de datos y visceversa.
 * 
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see ResultSet
 * @see SQLException
 * @see BaseDatoMotor
 * @see BaseDatoTablaBasica
 * @see RedNeuronalBP
 */
public class ConfiguracionNeuronalBP
{
	/** Indica la cuota inferior del intervalo  en que se maneja la red. */
	public final static double INTERVALO_MENOR = -0.3;
	
	/** Indica la cuota superior del intervalo  en que se maneja la red. */
	public final static double INTERVALO_MAYOR = 0.3;
	
	/** Indica que uno de los errores de la red no esta iniciado. */
	public final static int ERROR_NO_INICIADO = -1;
	
	/**
	 * El n�mero total de elementos en la configuraci�n excluyendo este y los
	 * anteriores.
	 */
	public final static int TOTAL_ELEMENTOS_CONFIGURACION = 19;
	
	/** El n�mero m�ximo de patrones.  */
	public final static int NUMERO_MAXIMO_PATRONES = 200;
	
	/** El n�mero m�ximo de entradas que puede tener la red neuronal. */
	public final static int NUMERO_MAXIMO_ENTRADAS = 30;
	
	/** El n�mero m�ximo de neuronas ocultas en capa 1 que puede tener la red. */
	public final static int NUMERO_MAXIMO_NEURONAS_CO1 = 30;
	
	/** El n�mero m�ximo de neuronas ocultas en capa 2 que puede tener la red. */
	public final static int NUMERO_MAXIMO_NEURONAS_CO2 = 30;
	
	/** El n�mero m�ximo de salidas que puede tener la red. */
	public final static int NUMERO_MAXIMO_SALIDAS = 30;
	
	/** Tabla conectada a la tabla de la base de datos. */
	private final static BaseDatoTablaBasica TABLA =
		new BaseDatoTablaBasica(Proyecto.CONEXION,"configuracion_bp");
	
	/** N�mero de patrones. */
	private static int numeroPatrones;
	
	/** N�mero de entradas que tiene la red. */
	private static int numeroEntradas;
	
	/** N�mero de neuronas ocultas de la capa 1 que tiene la red. */
	private static int numeroNeuronasCO1;
	
	/** N�mero de neuronas ocultas de la capa 2 que tiene la red. */
	private static int numeroNeuronasCO2;
	
	/** N�mero de salidas que tiene la red. */
	private static int numeroSalidas;
	
	/**
	 * Tipo de funci�n de transici�n de la capa oculta 1 (0:lineal. 1:sigmoidal
	 * 2: tanHiperbolica).
	 */
	private static int tipoFuncionCO1;
	
	/**
	 * Tipo de funci�n de transici�n de la capa oculta 2 (0:lineal. 1:sigmoidal
	 * 2: tanHiperbolica).
	 */
	private static int tipoFuncionCO2;
	
	/**
	 * Tipo de funci�n de transici�n de la capa de salida (0:lineal. 1:sigmoidal
	 * 2: tanHiperbolica).
	 */
	private static int tipoFuncionS;
	
 	/** Cantidad de ciclos del aprendizaje. */
  	private static double ciclo;
  	
  	/** Porcentaje de validaci�n cruzada. */
  	private static double porcentajeCruzada;
  	
  	/** Error m�nimo aceptable para la red. */
  	private static int errorMinimo;
  	
  	/** Error m�ximo desbordable por la red. */
  	private static int errorMaximo;
  	
  	/** Valor de la rata de aprendizaje. */
  	private static double rata;
	
	/** Valor de la variaci�n de la rata de aprendizaje. */
  	private static double variacionRata;
	
	/**
     * M�todo que carga una configuracion de la base de datos. Al ser cargadas
     * todas las variables tomar�n su valor seg�n la configuraci�n. Si el c�digo
     * del proyecto es NUEVO_PROYECTO se carga la configuraci�n por defecto.
     *
     * @param codigoProyecto C�digo del proyecto que se encuentra en la base de
     *                       datos.
     */
	public static void cargar(int codigoProyecto)
	{
		// almcendando los resultado de la consulta
		ResultSet resultado;
		
		// conectando a la base de datos
		Proyecto.CONEXION.conectar();
		
		// reslizando consulta
		resultado = TABLA.buscar("codigo_proyecto","" + codigoProyecto);	
		
		try
		{
			// el primer elemento y unico de la consulta
			resultado.first();
			
			numeroPatrones = resultado.getInt("numero_patrones_configuracion_bp");
			numeroEntradas = resultado.getInt("numero_entradas_configuracion_bp");
			numeroNeuronasCO1 = resultado.getInt("numero_neuronasco1_configuracion_bp");
			numeroNeuronasCO2 = resultado.getInt("numero_neuronasco2_configuracion_bp");
			numeroSalidas = resultado.getInt("numero_salidas_configuracion_bp");
			tipoFuncionCO1 = resultado.getInt("codigo_funcion_neuronalco1");
			tipoFuncionCO2 = resultado.getInt("codigo_funcion_neuronalco2");
			tipoFuncionS = resultado.getInt("codigo_funcion_neuronals");
			errorMinimo = resultado.getInt("error_minimo_configuracion_bp");
			errorMaximo = resultado.getInt("error_maximo_configuracion_bp");
			ciclo = resultado.getDouble("ciclo_configuracion_bp");
			rata = resultado.getDouble("rata_configuracion_bp");
			variacionRata = resultado.getDouble("variacion_rata_configuracion_bp");
			porcentajeCruzada = 
				resultado.getDouble("porcentaje_cruzada_configuracion_bp");
		}
		catch(SQLException ex)
		{
			// Si hay problemas al conectar la base datos
			System.err.println("No se pudo cargar la tabla configuracion_bp.");
		}
		
		// descontando de la base de datos
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * M�todo que guarda las variables que representan a una configuraci�n en la
     * base de datos. Se guarda en la base de datos seg�n el proyecto.
     * 
     * @param codigoProyecto C�digo del proyecto que se encuentra en la base de
     *                       datos.
     */
	public static void guardar(int codigoProyecto)
	{
		// conectando a la base de datos
		Proyecto.CONEXION.conectar();
		
		// Preparando campo para agregar
		TABLA.actualizarCampo("numero_patrones_configuracion_bp",
							  "" + numeroPatrones);
		TABLA.actualizarCampo("numero_entradas_configuracion_bp",
							  "" + numeroEntradas);
		TABLA.actualizarCampo("numero_neuronasco1_configuracion_bp",
							  "" + numeroNeuronasCO1);
		TABLA.actualizarCampo("numero_neuronasco2_configuracion_bp",
							  "" + numeroNeuronasCO2);
		TABLA.actualizarCampo("numero_salidas_configuracion_bp",
							  "" + numeroSalidas);
		TABLA.actualizarCampo("codigo_funcion_neuronalco1",
							  "" + tipoFuncionCO1);
		TABLA.actualizarCampo("codigo_funcion_neuronalco2",
							  "" + tipoFuncionCO2);
		TABLA.actualizarCampo("codigo_funcion_neuronals",
							  "" + tipoFuncionS);
		TABLA.actualizarCampo("error_maximo_configuracion_bp",
							  "" + errorMaximo);
		TABLA.actualizarCampo("error_minimo_configuracion_bp",
							  "" + errorMinimo);
		TABLA.actualizarCampo("ciclo_configuracion_bp",
							  "" + ciclo);
		TABLA.actualizarCampo("rata_configuracion_bp",
							  "" + rata);
		TABLA.actualizarCampo("variacion_rata_configuracion_bp",
							  "" + variacionRata);
		TABLA.actualizarCampo("porcentaje_cruzada_configuracion_bp",
							  "" + porcentajeCruzada);
		
		// agregando a la tabla
		TABLA.actualizar("codigo_proyecto","" + codigoProyecto);
		
		// desconectando de la base de datos
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * M�todo que agrega un registro de configuraci�n neuronal BP en la base 
     * de datos. Este registro se agrega segun el proyecto que se cre� (por el 
     * hecho de que la configuraci�n est� asociada al proyecto).
     * 
     * @param codigoProyecto Identificador del Proyecto.
     */
	public static void agregar(int codigoProyecto)
	{
		// conectando a la base de datos			
		Proyecto.CONEXION.conectar();
		
		// Preparando campo para agregar
		TABLA.agregarCampo("codigo_proyecto",
						   "" + codigoProyecto);
		TABLA.agregarCampo("numero_patrones_configuracion_bp",
						   "" + numeroPatrones);
		TABLA.agregarCampo("numero_entradas_configuracion_bp",
						   "" + numeroEntradas);
		TABLA.agregarCampo("numero_neuronasco1_configuracion_bp",
						   "" + numeroNeuronasCO1);
		TABLA.agregarCampo("numero_neuronasco2_configuracion_bp",
						   "" + numeroNeuronasCO2);
		TABLA.agregarCampo("numero_salidas_configuracion_bp",
						   "" + numeroSalidas);
		TABLA.agregarCampo("codigo_funcion_neuronalco1",
						   "" + tipoFuncionCO1);
		TABLA.agregarCampo("codigo_funcion_neuronalco2",
						   "" + tipoFuncionCO2);
		TABLA.agregarCampo("codigo_funcion_neuronals",
						   "" + tipoFuncionS);
		TABLA.agregarCampo("error_maximo_configuracion_bp",
						   "" + errorMaximo);
		TABLA.agregarCampo("error_minimo_configuracion_bp",
						   "" + errorMinimo);
		TABLA.agregarCampo("ciclo_configuracion_bp",
						   "" + ciclo);
		TABLA.agregarCampo("rata_configuracion_bp",
						   "" + rata);
		TABLA.agregarCampo("variacion_rata_configuracion_bp",
						   "" + variacionRata);
		TABLA.agregarCampo("porcentaje_cruzada_configuracion_bp",
						   "" + porcentajeCruzada);
		
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
     * @param parametros Arreglos con los valores de la configuraci�n.
     */
	
	public static void modificarValores(double[] parametros)
	{
		// si no hay errores
		if (validarValores(parametros) == "")
		{
			numeroNeuronasCO1 =(int) parametros[7];
			numeroNeuronasCO2 =(int) parametros[8];
			ciclo 			  = parametros[13];
			rata 			  = parametros[17];
			variacionRata     = parametros[18];
			tipoFuncionCO1    = (int) parametros[10];
			tipoFuncionCO2    = (int) parametros[11];
			tipoFuncionS      = (int) parametros[12];
			errorMinimo		  = (int) parametros[15];
			errorMaximo		  = (int) parametros[16];
			porcentajeCruzada = parametros[14];
		}
	}
	
	/**
     * M�todo que entrega una configuraci�n. Usada generalmente para la 
     * configuraci�n de la herramienta del frame configuraci�n de econom�a.
     * Los valores de configuraci�n vienen expresados en un arreglo de
     * tipo double.
     * 
     * @return parametros Arreglo con los valores de la configuraci�n.
     */
	public static double[] entregarValores()
	{
		double[] parametros = new double[TOTAL_ELEMENTOS_CONFIGURACION];
		
		parametros[0] = NUMERO_MAXIMO_PATRONES;
		parametros[1] = NUMERO_MAXIMO_ENTRADAS;
		parametros[2] = NUMERO_MAXIMO_NEURONAS_CO1;
		parametros[3] = NUMERO_MAXIMO_NEURONAS_CO2;
		parametros[4] = NUMERO_MAXIMO_SALIDAS;
		parametros[5] = numeroPatrones;
		parametros[6] = numeroEntradas;
		parametros[7] = numeroNeuronasCO1;
		parametros[8] = numeroNeuronasCO2;
		parametros[9] = numeroSalidas;
		parametros[10] = tipoFuncionCO1;
		parametros[11] = tipoFuncionCO2;
		parametros[12] = tipoFuncionS;
		parametros[13] = ciclo;
		parametros[14] = porcentajeCruzada;
		parametros[15] = errorMinimo;
		parametros[16] = errorMaximo;
		parametros[17] = rata;
		parametros[18] = variacionRata;
		
		return parametros;
	}
	
	/**
     * M�todo que valida los par�metros entregados a la configuraci�n. Por
     * cada error comentido se agrega una cadena de string con el error. Un
     * error se eval�a seg�n los rangos de cada item de la configuraci�n.
     * 
     * @param parametros Arreglo de itpo double con los valores de la 
     *					 configuraci�n.
     *
     * @return error String con los errores cometido por la configuraci�n de
     *				 par�metros.
     */
	public static String validarValores(double[] parametros)
	{
		String error = "";
		
		// validando valores que se intentan modificar
		
		// validando ciclos
		if (parametros[13] != (int)parametros[13] || 
			parametros[13] <= 0 || parametros[13] > 300000)
			error += "- En el n�mero de ciclos.\n";
		
		// validando numeronaCO1
		if (parametros[7] != (int)parametros[7] ||
			parametros[7] <= 0 || parametros[7] > NUMERO_MAXIMO_NEURONAS_CO1)
			error += "- En el n�mero de neuronas de la capa oculta 1.\n";
		
		// validando numeronaCO2
		if (parametros[8] != (int)parametros[8] ||
			parametros[8] <= 0 || parametros[8] > NUMERO_MAXIMO_NEURONAS_CO2)
			error += "- En el n�mero de neuronas de la capa oculta 2.\n";
		
		// validando rata
		if (parametros[17] <= 0 || parametros[17] > 0.1)
			error += "- En la rata de aprendizaje.\n";
		
		// validando variaci�n rata
		if (parametros[18] < 1 || parametros[18] > 3)
			error += "- En la variaci�n de la rata.\n";
		
		// validando error m�nimo
		if (parametros[15] != (int)parametros[15] || 
			parametros[15] <= 0 || parametros[15] > 6)
			error += "- En el error m�nimo aceptable.\n";
		
		// validando error m�ximo
		if (parametros[16] != (int)parametros[16] || 
			parametros[16] <= 0 || parametros[16] > 4)
			error += "- En el error m�ximo aceptable.\n";
		
		// validando porcentaje cruzado
		if (parametros[14] < 0 || parametros[14] > 0.5)
			error += "- En el porcentaje cruzado.\n";
		
		return error;
	}
	
	/**
     * M�todo que cambia el n�mero de patrones de configuraci�n BP.
     * 
     * @param numeroPatrones El n�mero de Patrones.
     */
	public static void cambiarNumeroPatrones(int numeroPatrones)
	{
		ConfiguracionNeuronalBP.numeroPatrones = numeroPatrones;
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
     * @return numeroSalidas El n�mero de salidas de la red.
     */
	public static int obtenerNumeroSalidas()
	{
		return numeroSalidas;
	}
}