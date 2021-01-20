/**
 * @(#)Recurso.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;

/**
 * Clase que contiene solamente atributos con valores est�ticos, que sirven para
 * mantener la informaci�n biol�gica de los recursos existentes en el sistema de
 * estudio. Esta clase se comunica con la base de datos MySQL, por lo tanto, los
 * recursos usados en el ecosistema se comunican con la base de datos. Los
 * valores de los atributos de esta clase son obtenidos desde diferentes tablas
 * de la base de datos. Esta clase sirve como interfaz entre la base de datos
 * y los diferentes m�dulos del ecosistema que utilizan la informaci�n de los
 * recursos.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see ResultSet
 * @see SQLException
 * @see Color
 * @see Proyecto
 * @see Servicio
 * @see Ajuste
 * @see SuperIndividuo
 */
public class Recurso
{
	/** El valor que indica que el recurso es Camar�n Nailon. */
	public static final int CAMARON_NAILON = 0;
	
	/** El valor que indica que el recurso es Langostino Amarillo. */
	public static final int LANGOSTINO_AMARILLO = 1;
	
	/** El valor que indica que el recurso es Langostino Colorado. */
	public static final int LANGOSTINO_COLORADO = 2;
	
	/** El valor que indica que el recurso es Merluza Com�n. */
	public static final int MERLUZA_COMUN = 3;
	
	/** El valor del umbral de predaci�n. */
	public static final double UMBRAL_PREDACION = 1.5;
	
	/**
	 * El valor de la muerte por concepto de decadencia. La relaci�n de esta
	 * variable es la siguiente: A menor valor, m�s tiempo de vida tienen los
	 * super-individuos sin alimento, y a mayor valor, menor tiempo de vida
	 * tienen los super-individuos sin alimento. Este valor fue estimado
	 * emp�ricamente para que los super-individuos vivan como m�ximo 2 meses sin
	 * haber consumido alimento alguno.
	 */
	public static final double MUERTE_DECADENCIA = 0.19875;
	
	/** La cantidad de recursos. */
	private static int cantidadRecursos;
	
	/** Los tipos de animales de los recursos. */
	private static int[] tipoAnimal;
	
	/**
	 * Las distancias al fondo m�nimas (metros) de las zonas sistemas de los
	 * recursos.
	 */
	private static double[] distanciaFondoMinimaZonaSistema;
	
	/**
	 * Las distancias al fondo m�ximas (metros) de las zonas sistemas de los
	 * recursos.
	 */
	private static double[] distanciaFondoMaximaZonaSistema;
	
	/**
	 * Las altitudes iniciales (metros) de las zonas pel�gicas de los recursos.
	 */
	private static double[] altitudInicialZonaPelagica;
	
	/**
	 * Las altitudes finales (metros) de las zonas pel�gicas de los recursos.
	 */
	private static double[] altitudFinalZonaPelagica;
	
	/**
	 * Las altitudes iniciales (metros) de las zonas bent�nicas de los recursos.
	 */
	private static double[] altitudInicialZonaBentonica;
	
	/**
	 * Las altitudes finales (metros) de las zonas bent�nicas de los recursos.
	 */
	private static double[] altitudFinalZonaBentonica;
	
	/**
	 * Las distancias al continente m�nimas (metros) de las zonas regi�n de los
	 * recursos.
	 */
	private static double[] distanciaContinenteMinimaZonaRegion;
	
	/**
	 * Las distancias al continente m�ximas (metros) de las zonas regi�n de los
	 * recursos.
	 */
	private static double[] distanciaContinenteMaximaZonaRegion;
	
	/** Las altitudes iniciales (metros) de las zonas luz de los recursos. */
	private static double[] altitudInicialZonaLuz;
	
	/** Las altitudes finales (metros) de las zonas luz de los recursos. */
	private static double[] altitudFinalZonaLuz;
	
	/** Los tipos de conductas de los recursos. */
	private static int[] conducta;
	
	/** Los colores rojos de los recursos. */
	private static int[] colorRojo;
	
	/** Los colores verdes de los recursos. */
	private static int[] colorVerde;
	
	/** Los colores azules de los recursos. */
	private static int[] colorAzul;
	
	/** Los tipos de latitud inicial en donde viven los recursos. */
	private static int[] tipoLatitudInicial;
	
	/** Los tipos de latitud final en donde viven los recursos. */
	private static int[] tipoLatitudFinal;
	
	/** Los tipos de los recursos. */
	private static int[] tipoRecurso;
	
	/** Los tipos de reproducci�n de los recursos. */
	private static int[] tipoReproduccion;
	
	/** Los nombres comunes de los recursos. */
	private static String[] nombreComun;
	
	/** Las edades m�ximas de los machos (a�os). */
	private static double[] edadMaximaMacho;
	
	/** Las edades m�ximas de las hembras (a�os). */
	private static double[] edadMaximaHembra;
	
	/** Los par�metros Loo de los machos (cent�metros). */
	private static double[] parametroLooMacho;
	
	/** Los par�metros to de los machos (a�os). */
	private static double[] parametroToMacho;
	
	/** Los par�metros K de los machos (1/a�os). */
	private static double[] parametroKMacho;
	
	/** Los par�metros A de los machos. */
	private static double[] parametroAMacho;
	
	/** Los par�metros B de los machos. */
	private static double[] parametroBMacho;
	
	/** Los par�metros Loo de las hembras (cent�metros). */
	private static double[] parametroLooHembra;
	
	/** Los par�metros to de las hembras (a�os). */
	private static double[] parametroToHembra;
	
	/** Los par�metros K de las hembras (1/a�os). */
	private static double[] parametroKHembra;
	
	/** Los par�metros A de las hembras. */
	private static double[] parametroAHembra;
	
	/** Los par�metros B de las hembras. */
	private static double[] parametroBHembra;
	
	/** Las tallas m�nimas de los machos (cent�metros). */
	private static double[] tallaMinimaMacho;
	
	/** Las tallas m�ximas de los machos (cent�metros). */
	private static double[] tallaMaximaMacho;
	
	/** Las tallas m�nimas de las hembras (cent�metros). */
	private static double[] tallaMinimaHembra;
	
	/** Las tallas m�ximas de las hembras (cent�metros). */
	private static double[] tallaMaximaHembra;
	
	/** Los pesos m�nimos de los machos (gramos). */
	private static double[] pesoMinimoMacho;
	
	/** Los pesos m�ximos de los machos (gramos). */
	private static double[] pesoMaximoMacho;
	
	/** Los pesos m�nimos de las hembras (gramos). */
	private static double[] pesoMinimoHembra;
	
	/** Los pesos m�ximos de las hembras (gramos). */
	private static double[] pesoMaximoHembra;
	
	/** Los par�metros S1. */
	private static double[] parametroS1;
	
	/** Los par�metros S2. */
	private static double[] parametroS2;
	
	/** Las probabilidades de la madurez sexual entre [0, 1]. */
	private static double[] madurezSexual;
	
	/** El n�mero de huevos m�nimos (unidades). */
	private static long[] huevosMinimo;
	
	/** El n�mero de huevos m�ximos (unidades). */
	private static long[] huevosMaximo;
	
	/** Los periodos de portaci�n de huevos (d�as). */
	private static int[] periodoPortacion;
	
	/**
	 * Las tasas de natalidad (unidades nacidas/unidades de huevos desovados).
	 */
	private static double[] tasaNatalidad;
	
	/** Las tasas de mortalidad (unidades muertas/d�as). */
	private static long[] tasaMortalidad;
	
	/** Las latitudes iniciales del h�bitat (grados). */
	private static double[] latitudInicial;
	
	/** Las latitudes finales del h�bitat (grados). */
	private static double[] latitudFinal;
	
	/** Las altitudes iniciales del h�bitat (metros). */
	private static double[] altitudInicial;
	
	/** Las altitudes finales del h�bitat (metros). */
	private static double[] altitudFinal;
	
	/** Las temperaturas iniciales del h�bitat (�C). */
	private static double[] temperaturaInicial;
	
	/** Las temperaturas finales del h�bitat (�C). */
	private static double[] temperaturaFinal;
	
	/** Las salinidades iniciales del h�bitat (psu). */
	private static double[] salinidadInicial;
	
	/** Las salinidades finales del h�bitat (psu). */
	private static double[] salinidadFinal;
	
	/** Los ox�genos disueltos iniciales del h�bitat (mil�metros/litros). */
	private static double[] oxigenoDisueltoInicial;
	
	/** Los ox�genos disueltos finales del h�bitat (mil�metros/litros). */
	private static double[] oxigenoDisueltoFinal;
	
	/** Las percepciones (metros/segundos). */
	private static double[] percepcion;
	
	/** Las velocidades (metros/segundos). */
	private static double[] velocidad;
	
	/** Las energ�as iniciales entre [1, 10]. */
	private static double[] energiaInicial;
	
	/** Las energ�as m�nimas entre [1, 10]. */
	private static double[] energiaMinima;
	
	/** Las energ�as m�ximas entre [1, 10]. */
	private static double[] energiaMaxima;
	
	/**
	 * Los factores de energ�as por unidad de tiempo (energ�a/d�as) entre
	 * [1, 10].
	 */
	private static double[] energiaTiempo;
	
	/**
	 * Los factores de energ�as por unidad de cosumo (energ�a/gramos) entre
	 * [1, 10].
	 */
	private static double[] energiaConsumo;
	
	/**
	 * Los factores de energ�as por unidad de desplazamiento
	 * (energ�a/kil�metros) entre [1, 10].
	 */
	private static double[] energiaDesplazamiento;
	
	/** Los factores de descomposici�n entre [0, 1]. */
	private static double[] descomposicion;
	
	/** Las tendencias de arrancar entre [0, 1]. */
	private static double[] tendenciaArrancar;
	
	/** Las tendencias de predar entre [0, 1]. */
	private static double[] tendenciaPredar;
	
	/** Las tendencias de reproducir entre [0, 1]. */
	private static double[] tendenciaReproducir;
	
	/** Las tendencias de desovar entre [0, 1]. */
	private static double[] tendenciaDesovar;
	
	/** Las tendencias de convivir entre [0, 1]. */
	private static double[] tendenciaConvivir;
	
	/** Las tendencias de migrar entre [0, 1]. */
	private static double[] tendenciaMigrar;
	
	/** Las tendencias de divagar entre [0, 1]. */
	private static double[] tendenciaDivagar;
	
	/** Las tendencias de nada entre [0, 1]. */
	private static double[] tendenciaNada;
	
	/** Las alimentaciones de tipos de dietas entre {Si, No}. */
	private static String[][] alimentacionTipoDieta;
	
	/** Las alimentaciones de fauna acompa�ante entre [0, 1]. */
	private static double[][] alimentacionFaunaAcompaniante;
	
	/** Las probabilidades de portaci�n [0, 1]. */
	private static double[][] portacion;
	
	/** Las probabiliades de desove [0, 1]. */
	private static double[][] desove;
	
	/** Las abundancias (unidad). */
	private static long[] abundancia;
	
	/** Los porcentajes de machos entre [0, 1]. */
	private static double[] porcentajeMachos;
	
	/** Los porcentajes de hembras entre [0, 1]. */
	private static double[] porcentajeHembras;
	
	/** Los porcentajes de hembras sin huevos entre [0, 1]. */
	private static double[] porcentajeHembrasSH;
	
	/** Los porcentajes de hembras con huevos entre [0, 1]. */
	private static double[] porcentajeHembrasCH;
	
	/** Las tallas promedios (cent�metro). */
	private static double[] talla;
	
	/** Las tallas promedios de machos (cent�metro). */
	private static double[] tallaMachos;
	
	/** Las tallas promedios de hembras (cent�metro). */
	private static double[] tallaHembras;
	
	/** Las tallas promedios de hembras sin huevos (cent�metro). */
	private static double[] tallaHembrasSH;
	
	/** Las tallas promedios de hembras con huevos (cent�metro). */
	private static double[] tallaHembrasCH;
	
	/** Las biomasas (tonelada). */
	private static double[] biomasa;
	
	/** Los pesos promedios (gramo). */
	private static double[] peso;
	
	/** Los pesos promedios de machos (gramo). */
	private static double[] pesoMachos;
	
	/** Los pesos promedios de hembras (gramo). */
	private static double[] pesoHembras;
	
	/** Los pesos promedios de hembras sin huevos (gramo). */
	private static double[] pesoHembrasSH;
	
	/** Los pesos promedios de hembras con huevos (gramo). */
	private static double[] pesoHembrasCH;
	
	/** Las cantidades de caladeros (unidad). */
	private static int[] cantidadCaladeros;
	
	/** Los tipos de latitudes iniciales de los caladeros de un recurso. */
	private static int[] tipoLatitudInicialCaladero;
	
	/** Los tipos de latitudes finales de los caladeros de un recurso. */
	private static int[] tipoLatitudFinalCaladero;
	
	/** Las latitudes iniciales de los caladeros de un recurso (grado). */
	private static double[] latitudInicialCaladero;
	
	/** Las latitudes finales de los caladeros de un recurso (grado). */
	private static double[] latitudFinalCaladero;
	
	/** Las �reas de los caladeros de un recurso (kil�metros^2). */
	private static double[] areaCaladero;
	
	/** Las abundancias de los caladeros de un recurso (unidad). */
	private static long[] abundanciaCaladero;
	
	/** Las biomasas de los caladeros de un recurso (tonelada). */
	private static double[] biomasaCaladero;
	
	/**
	 * M�todo que carga desde la base de datos los datos biol�gicos de los
	 * recursos. En esta clase se les asigna valor a todos los atributos de la
	 * clase que tienen relaci�n con la tabla recurso, zona_sistema,
	 * zona_pelagica, zona_bentonica, zona_region, zona_luz y color.
	 */
	public static void cargar()
	{
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Realizar la consulta.
		String consulta =
		"select * "+
		"from recurso, zona_sistema, zona_pelagica, zona_bentonica, "+
		"zona_region, zona_luz, color "+
		"where recurso.codigo_zona_sistema = zona_sistema.codigo_zona_sistema "+
		"AND recurso.codigo_zona_pelagica = zona_pelagica.codigo_zona_pelagica "+
		"AND recurso.codigo_zona_bentonica = zona_bentonica.codigo_zona_bentonica "+
		"AND recurso.codigo_zona_region = zona_region.codigo_zona_region "+
		"AND recurso.codigo_zona_luz = zona_luz.codigo_zona_luz "+
		"AND recurso.codigo_color = color.codigo_color "+
		"order by codigo_recurso asc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		try
		{
			// Posicionar en el �ltimo registro.
			resultado.last();
			
			// Establecer el n�mero de registros del resultado.
			cantidadRecursos = resultado.getRow();
			
			// Asignar espacio en memoria a todos los arreglos relacionados a la
			// tabla recurso.
			tipoAnimal = new int[cantidadRecursos];
			distanciaFondoMinimaZonaSistema = new double[cantidadRecursos];
			distanciaFondoMaximaZonaSistema = new double[cantidadRecursos];
			altitudInicialZonaPelagica = new double[cantidadRecursos];
			altitudFinalZonaPelagica = new double[cantidadRecursos];
			altitudInicialZonaBentonica = new double[cantidadRecursos];
			altitudFinalZonaBentonica = new double[cantidadRecursos];
			distanciaContinenteMinimaZonaRegion = new double[cantidadRecursos];
			distanciaContinenteMaximaZonaRegion = new double[cantidadRecursos];
			altitudInicialZonaLuz = new double[cantidadRecursos];
			altitudFinalZonaLuz = new double[cantidadRecursos];
			conducta = new int[cantidadRecursos];
			colorRojo = new int[cantidadRecursos];
			colorVerde = new int[cantidadRecursos];
			colorAzul = new int[cantidadRecursos];
			tipoLatitudInicial = new int[cantidadRecursos];
			tipoLatitudFinal = new int[cantidadRecursos];
			tipoRecurso = new int[cantidadRecursos];
			tipoReproduccion = new int[cantidadRecursos];
			nombreComun = new String[cantidadRecursos];
			edadMaximaMacho = new double[cantidadRecursos];
			edadMaximaHembra = new double[cantidadRecursos];
			parametroLooMacho = new double[cantidadRecursos];
			parametroToMacho = new double[cantidadRecursos];
			parametroKMacho = new double[cantidadRecursos];
			parametroAMacho = new double[cantidadRecursos];
			parametroBMacho = new double[cantidadRecursos];
			parametroLooHembra = new double[cantidadRecursos];
			parametroToHembra = new double[cantidadRecursos];
			parametroKHembra = new double[cantidadRecursos];
			parametroAHembra = new double[cantidadRecursos];
			parametroBHembra = new double[cantidadRecursos];
			tallaMinimaMacho = new double[cantidadRecursos];
			tallaMaximaMacho = new double[cantidadRecursos];
			tallaMinimaHembra = new double[cantidadRecursos];
			tallaMaximaHembra = new double[cantidadRecursos];
			pesoMinimoMacho = new double[cantidadRecursos];
			pesoMaximoMacho = new double[cantidadRecursos];
			pesoMinimoHembra = new double[cantidadRecursos];
			pesoMaximoHembra = new double[cantidadRecursos];
			parametroS1 = new double[cantidadRecursos];
			parametroS2 = new double[cantidadRecursos];
			madurezSexual = new double[cantidadRecursos];
			huevosMinimo = new long[cantidadRecursos];
			huevosMaximo = new long[cantidadRecursos];
			periodoPortacion = new int[cantidadRecursos];
			tasaNatalidad = new double[cantidadRecursos];
			tasaMortalidad = new long[cantidadRecursos];
			latitudInicial = new double[cantidadRecursos];
			latitudFinal = new double[cantidadRecursos];
			altitudInicial = new double[cantidadRecursos];
			altitudFinal = new double[cantidadRecursos];
			temperaturaInicial = new double[cantidadRecursos];
			temperaturaFinal = new double[cantidadRecursos];
			salinidadInicial = new double[cantidadRecursos];
			salinidadFinal = new double[cantidadRecursos];
			oxigenoDisueltoInicial = new double[cantidadRecursos];
			oxigenoDisueltoFinal = new double[cantidadRecursos];
			percepcion = new double[cantidadRecursos];
			velocidad = new double[cantidadRecursos];
			energiaInicial = new double[cantidadRecursos];
			energiaMinima = new double[cantidadRecursos];
			energiaMaxima = new double[cantidadRecursos];
			energiaTiempo = new double[cantidadRecursos];
			energiaConsumo = new double[cantidadRecursos];
			energiaDesplazamiento = new double[cantidadRecursos];
			descomposicion = new double[cantidadRecursos];
			tendenciaArrancar = new double[cantidadRecursos];
			tendenciaPredar = new double[cantidadRecursos];
			tendenciaReproducir = new double[cantidadRecursos];
			tendenciaDesovar = new double[cantidadRecursos];
			tendenciaConvivir = new double[cantidadRecursos];
			tendenciaMigrar = new double[cantidadRecursos];
			tendenciaDivagar = new double[cantidadRecursos];
			tendenciaNada = new double[cantidadRecursos];
			
			// Asignar espacio en memoria a todos los arreglos relacionados a la
			// tabla recurso_tipo_dieta.
			alimentacionTipoDieta = new String[cantidadRecursos][6];
			
			// Asignar espacio en memoria a todos los arreglos relacionados a la
			// tabla fauna_acompaniante.
			alimentacionFaunaAcompaniante =
								 new double[cantidadRecursos][cantidadRecursos];
			
			// Asignar espacio en memoria a todos los arreglos relacionados a la
			// tabla recurso_mes.
			portacion = new double[cantidadRecursos][12];
			desove = new double[cantidadRecursos][12];
			
			// Asignar espacio en memoria a todos los arreglos relacionados a la
			// tabla dato_ecosistemico_real.
			abundancia = new long[cantidadRecursos];
			porcentajeMachos = new double[cantidadRecursos];
			porcentajeHembras = new double[cantidadRecursos];
			porcentajeHembrasSH = new double[cantidadRecursos];
			porcentajeHembrasCH = new double[cantidadRecursos];
			talla = new double[cantidadRecursos];
			tallaMachos = new double[cantidadRecursos];
			tallaHembras = new double[cantidadRecursos];
			tallaHembrasSH = new double[cantidadRecursos];
			tallaHembrasCH = new double[cantidadRecursos];
			biomasa = new double[cantidadRecursos];
			peso = new double[cantidadRecursos];
			pesoMachos = new double[cantidadRecursos];
			pesoHembras = new double[cantidadRecursos];
			pesoHembrasSH = new double[cantidadRecursos];
			pesoHembrasCH = new double[cantidadRecursos];
			
			// Asignar espacio en memoria a los arreglos relacionados a la tabla
			// caladero.
			cantidadCaladeros = new int[cantidadRecursos];
			
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para recorrer los resultados.
			int indice = 0;
			do
			{
				// Cargar los datos de la tabla recurso.
				tipoAnimal[indice] = resultado.getInt("codigo_tipo_animal");
				distanciaFondoMinimaZonaSistema[indice] =
				resultado.getDouble("distancia_fondo_minima_zona_sistema");
				distanciaFondoMaximaZonaSistema[indice] =
				resultado.getDouble("distancia_fondo_maxima_zona_sistema");
				altitudInicialZonaPelagica[indice] =
				resultado.getDouble("altitud_inicial_zona_pelagica");
				altitudFinalZonaPelagica[indice] =
				resultado.getDouble("altitud_final_zona_pelagica");
				altitudInicialZonaBentonica[indice] =
				resultado.getDouble("altitud_inicial_zona_bentonica");
				altitudFinalZonaBentonica[indice] =
				resultado.getDouble("altitud_final_zona_bentonica");
				distanciaContinenteMinimaZonaRegion[indice] =
				resultado.getDouble("distancia_continente_minima_zona_region");
				distanciaContinenteMaximaZonaRegion[indice] =
				resultado.getDouble("distancia_continente_maxima_zona_region");
				altitudInicialZonaLuz[indice] =
				resultado.getDouble("altitud_inicial_zona_luz");
				altitudFinalZonaLuz[indice] =
				resultado.getDouble("altitud_final_zona_luz");
				conducta[indice] = resultado.getInt("codigo_conducta");
				colorRojo[indice] = resultado.getInt("rojo_color");
				colorVerde[indice] = resultado.getInt("verde_color");
				colorAzul[indice] = resultado.getInt("azul_color");
				tipoLatitudInicial[indice] =
				resultado.getInt("codigo_latitud_inicial");
				tipoLatitudFinal[indice] =
				resultado.getInt("codigo_latitud_final");
				tipoRecurso[indice] = resultado.getInt("codigo_tipo_recurso");
				tipoReproduccion[indice] =
				resultado.getInt("codigo_tipo_reproduccion");
				nombreComun[indice] =
				resultado.getString("nombre_comun_recurso");
				edadMaximaMacho[indice] =
				resultado.getDouble("edad_maxima_macho_recurso");
				edadMaximaHembra[indice] =
				resultado.getDouble("edad_maxima_hembra_recurso");
				parametroLooMacho[indice] =
				resultado.getDouble("parametro_loo_macho_recurso");
				parametroToMacho[indice] =
				resultado.getDouble("parametro_to_macho_recurso");
				parametroKMacho[indice] =
				resultado.getDouble("parametro_k_macho_recurso");
				parametroAMacho[indice] =
				resultado.getDouble("parametro_a_macho_recurso");
				parametroBMacho[indice] =
				resultado.getDouble("parametro_b_macho_recurso");
				parametroLooHembra[indice] =
				resultado.getDouble("parametro_loo_hembra_recurso");
				parametroToHembra[indice] =
				resultado.getDouble("parametro_to_hembra_recurso");
				parametroKHembra[indice] =
				resultado.getDouble("parametro_k_hembra_recurso");
				parametroAHembra[indice] =
				resultado.getDouble("parametro_a_hembra_recurso");
				parametroBHembra[indice] =
				resultado.getDouble("parametro_b_hembra_recurso");
				tallaMinimaMacho[indice] =
				resultado.getDouble("talla_minima_macho_recurso");
				tallaMaximaMacho[indice] =
				resultado.getDouble("talla_maxima_macho_recurso");
				tallaMinimaHembra[indice] =
				resultado.getDouble("talla_minima_hembra_recurso");
				tallaMaximaHembra[indice] =
				resultado.getDouble("talla_maxima_hembra_recurso");
				pesoMinimoMacho[indice] =
				resultado.getDouble("peso_minimo_macho_recurso");
				pesoMaximoMacho[indice] =
				resultado.getDouble("peso_maximo_macho_recurso");
				pesoMinimoHembra[indice] =
				resultado.getDouble("peso_minimo_hembra_recurso");
				pesoMaximoHembra[indice] =
				resultado.getDouble("peso_maximo_hembra_recurso");
				parametroS1[indice] =
				resultado.getDouble("parametro_s1_recurso");
				parametroS2[indice] =
				resultado.getDouble("parametro_s2_recurso");
				madurezSexual[indice] =
				resultado.getDouble("madurez_sexual_recurso");
				huevosMinimo[indice] =
				resultado.getLong("huevos_minimo_recurso");
				huevosMaximo[indice] =
				resultado.getLong("huevos_maximo_recurso");
				periodoPortacion[indice] =
				resultado.getInt("periodo_portacion_recurso");
				tasaNatalidad[indice] =
				resultado.getDouble("tasa_natalidad_recurso");
				tasaMortalidad[indice] =
				resultado.getLong("tasa_mortalidad_recurso");
				latitudInicial[indice] =
				resultado.getDouble("latitud_inicial_recurso");
				latitudFinal[indice] =
				resultado.getDouble("latitud_final_recurso");
				altitudInicial[indice] =
				resultado.getDouble("altitud_inicial_recurso");
				altitudFinal[indice] =
				resultado.getDouble("altitud_final_recurso");
				temperaturaInicial[indice] =
				resultado.getDouble("temperatura_inicial_recurso");
				temperaturaFinal[indice] =
				resultado.getDouble("temperatura_final_recurso");
				salinidadInicial[indice] =
				resultado.getDouble("salinidad_inicial_recurso");
				salinidadFinal[indice] =
				resultado.getDouble("salinidad_final_recurso");
				oxigenoDisueltoInicial[indice] =
				resultado.getDouble("oxigeno_disuelto_inicial_recurso");
				oxigenoDisueltoFinal[indice] =
				resultado.getDouble("oxigeno_disuelto_final_recurso");
				percepcion[indice] = resultado.getDouble("percepcion_recurso");
				velocidad[indice] = resultado.getDouble("velocidad_recurso");
				energiaInicial[indice] =
				resultado.getDouble("energia_inicial_recurso");
				energiaMinima[indice] =
				resultado.getDouble("energia_minima_recurso");
				energiaMaxima[indice] =
				resultado.getDouble("energia_maxima_recurso");
				energiaTiempo[indice] =
				resultado.getDouble("energia_tiempo_recurso");
				energiaConsumo[indice] =
				resultado.getDouble("energia_consumo_recurso");
				energiaDesplazamiento[indice] =
				resultado.getDouble("energia_desplazamiento_recurso");
				descomposicion[indice] =
				resultado.getDouble("descomposicion_recurso");
				tendenciaArrancar[indice] =
				resultado.getDouble("tendencia_arrancar_recurso");
				tendenciaPredar[indice] =
				resultado.getDouble("tendencia_predar_recurso");
				tendenciaReproducir[indice] =
				resultado.getDouble("tendencia_reproducir_recurso");
				tendenciaDesovar[indice] =
				resultado.getDouble("tendencia_desovar_recurso");
				tendenciaConvivir[indice] =
				resultado.getDouble("tendencia_convivir_recurso");
				tendenciaMigrar[indice] =
				resultado.getDouble("tendencia_migrar_recurso");
				tendenciaDivagar[indice] =
				resultado.getDouble("tendencia_divagar_recurso");
				tendenciaNada[indice] =
				resultado.getDouble("tendencia_nada_recurso");
				
				// Posicionar en el siguiente registro.
				resultado.next();
				
				// Incrementar indice.
				indice++;
			}
			while (!resultado.isAfterLast());
		}
		
		// Capturar la exepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar las siguientes tablas: "+
							   "recurso, zona_sistema, zona_pelagica, "+
							   "zona_bentonica, zona_region, zona_luz y color.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * M�todo en donde se llama a los m�todos que cargan todos los datos de los
	 * recursos en los arreglos. Para cada recurso, se cargan sus datos en
	 * espec�fico.
	 */
	public static void cargarRecursos()
	{
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Ciclo para cargar los datos de la fauna acompa�ante, los datos
		// ecosist�micos reales y los caladeros de los recursos.
		for (int recurso=0; recurso<cantidadRecursos; recurso++)
		{
			cargarRecursoTipoDieta(recurso);
			cargarFaunaAcompaniante(recurso);
			cargarRecursoMes(recurso);
			cargarDatosReales(recurso);
			cargarCantidadCaladeros(recurso);
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * M�todo que carga desde la base de datos los datos de la alimentaci�n del
	 * tipo de dieta de los recursos. En esta clase se les asigna valor a todos
	 * los atributos de la clase que tienen relaci�n con la tabla
	 * recurso_tipo_dieta.
	 *
	 * @param recurso El c�digo del recurso.
	 */
	private static void cargarRecursoTipoDieta(int recurso)
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Realizar la consulta.
		String consulta =
		"select alimentacion_tipo_dieta "+
		"from recurso_tipo_dieta "+
		"where codigo_recurso = "+recurso+" "+
		"order by codigo_tipo_dieta asc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		try
		{
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para recorrer los resultados.
			int indice = 0;
			do
			{
				// Cargar los datos de la tabla recurso_tipo_dieta.
				alimentacionTipoDieta[recurso][indice] =
				resultado.getString("alimentacion_tipo_dieta");
				
				// Posicionar en el siguiente registro.
				resultado.next();
				
				// Incrementar el �ndice.
				indice++;
			}
			while (!resultado.isAfterLast());
		}
		
		// Capturar la exepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tablas "+
							   "recurso_tipo_dieta.");
		}
	}
	
	/**
	 * M�todo que carga desde la base de datos los datos de la alimentaci�n de
	 * fauna acompa�ante de los recursos. En esta clase se les asigna valor a
	 * todos los atributos de la clase que tienen relaci�n con la tabla
	 * fauna_acompaniante.
	 *
	 * @param recurso El c�digo del recurso.
	 */
	private static void cargarFaunaAcompaniante(int recurso)
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Realizar la consulta.
		String consulta =
		"select alimentacion_fauna_acompaniante "+
		"from fauna_acompaniante "+
		"where codigo_recurso_objetivo = "+recurso+" "+
		"order by codigo_recurso_acompaniante asc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		try
		{
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para recorrer los resultados.
			int indice = 0;
			do
			{
				// Cargar los datos de la tabla fauna_acompaniante.
				alimentacionFaunaAcompaniante[recurso][indice] =
				resultado.getDouble("alimentacion_fauna_acompaniante");
				
				// Posicionar en el siguiente registro.
				resultado.next();
				
				// Incrementar el �ndice.
				indice++;
			}
			while (!resultado.isAfterLast());
		}
		
		// Capturar la exepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla "+
							   "fauna_acompaniante.");
		}
	}
	
	/**
	 * M�todo que carga desde la base de datos los datos de los recurso en los
	 * meses. En esta clase se les asigna valor a todos los atributos de la
	 * clase que tienen relaci�n con la tabla recurso_mes.
	 *
	 * @param recurso El c�digo del recurso.
	 */
	private static void cargarRecursoMes(int recurso)
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Realizar la consulta.
		String consulta =
		"select portacion_recurso_mes, desove_recurso_mes "+
		"from recurso_mes "+
		"where codigo_recurso = "+recurso+" "+
		"order by codigo_mes asc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		try
		{
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para recorrer los resultados.
			int indice = 0;
			do
			{
				// Cargar los datos de la tabla recurso_mes.
				portacion[recurso][indice] =
				resultado.getDouble("portacion_recurso_mes");
				desove[recurso][indice] =
				resultado.getDouble("desove_recurso_mes");
				
				// Posicionar en el siguiente registro.
				resultado.next();
				
				// Incrementar el �ndice.
				indice++;
			}
			while (!resultado.isAfterLast());
		}
		
		// Capturar la exepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla recurso_mes.");
		}
	}
	
	/**
	 * M�todo que carga desde la base de datos los datos ecosist�micos reales de
	 * un recurso con c�digo conocido. En esta clase se les asigna valor a todos
	 * los atributos de la clase que tienen relaci�n con la tabla
	 * dato_ecosistemico_real.
	 *
	 * @param recurso El c�digo del recurso.
	 */
	private static void cargarDatosReales(int recurso)
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Obtener los datos del proyecto.
		int region = Proyecto.obtenerCodigoRegion();
		int fechaInicial[] = new int[3];
		fechaInicial = Proyecto.obtenerFechaInicialFormatoInt();
		int anioInicial = fechaInicial[2];
		int diaInicial = Servicio.obtenerDia(fechaInicial[0], fechaInicial[1]);
		
		// Realizar la consulta.
		String consulta =
		"select * "+
		"from dato_ecosistemico_real "+
		"where codigo_recurso = "+recurso+" and "+
		"codigo_region = "+region+" and "+
		"codigo_anio <= "+anioInicial+" and "+
		"codigo_dia <= "+diaInicial+" "+
		"order by codigo_anio desc, codigo_dia desc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Setear los valores de los atributos.
		try
		{
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Setear los valores.
			abundancia[recurso] =
			resultado.getLong("abundancia_dato_ecosistemico_real");
			porcentajeMachos[recurso] =
			resultado.getDouble("porcentaje_machos_dato_ecosistemico_real");
			porcentajeHembras[recurso] =
			resultado.getDouble("porcentaje_hembras_dato_ecosistemico_real");
			porcentajeHembrasSH[recurso] =
			resultado.getDouble("porcentaje_hembras_sh_dato_ecosistemico_real");
			porcentajeHembrasCH[recurso] =
			resultado.getDouble("porcentaje_hembras_ch_dato_ecosistemico_real");
			talla[recurso] =
			resultado.getDouble("talla_dato_ecosistemico_real");
			tallaMachos[recurso] =
			resultado.getDouble("talla_machos_dato_ecosistemico_real");
			tallaHembras[recurso] =
			resultado.getDouble("talla_hembras_dato_ecosistemico_real");
			tallaHembrasSH[recurso] =
			resultado.getDouble("talla_hembras_sh_dato_ecosistemico_real");
			tallaHembrasCH[recurso] =
			resultado.getDouble("talla_hembras_ch_dato_ecosistemico_real");
			biomasa[recurso] =
			resultado.getDouble("biomasa_dato_ecosistemico_real");
			peso[recurso] =
			resultado.getDouble("peso_dato_ecosistemico_real");
			pesoMachos[recurso] =
			resultado.getDouble("peso_machos_dato_ecosistemico_real");
			pesoHembras[recurso] =
			resultado.getDouble("peso_hembras_dato_ecosistemico_real");
			pesoHembrasSH[recurso] =
			resultado.getDouble("peso_hembras_sh_dato_ecosistemico_real");
			pesoHembrasCH[recurso] =
			resultado.getDouble("peso_hembras_ch_dato_ecosistemico_real");
		}
		
		// Capturar la exepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla "+
							   "dato_ecosistemico_real.");
		}
	}
	
	/**
	 * M�todo que carga desde la base de datos los datos de las cantidades de
	 * caladeros de los recursos. En esta clase se les asigna valor al atributo
	 * que almacena la cantidad de caladeros que tiene un recurso.
	 *
	 * @param recurso El c�digo del recurso.
	 */
	private static void cargarCantidadCaladeros(int recurso)
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Obtener los datos del proyecto.
		int region = Proyecto.obtenerCodigoRegion();
		int fechaInicial[] = new int[3];
		fechaInicial = Proyecto.obtenerFechaInicialFormatoInt();
		int anioInicial = fechaInicial[2];
		int diaInicial = Servicio.obtenerDia(fechaInicial[0], fechaInicial[1]);
		
		// Realizar la consulta.
		String consulta =
		"select * "+
		"from caladero_real "+
		"where codigo_recurso = "+recurso+" and "+
		"codigo_region = "+region+" and "+
		"codigo_anio <= "+anioInicial+" and "+
		"codigo_dia <= "+diaInicial+" "+
		"order by codigo_anio desc, codigo_dia desc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Setear los valores de los atributos.
		try
		{
			// Posicionar en el �ltimo registro.
			resultado.last();
			
			// Establecer el n�mero de registros del resultado.
			cantidadCaladeros[recurso] = resultado.getRow();
		}
		
		// Capturar la exepci�n.
		catch (SQLException exepcion)
		{
			// No es necesario mostrar la excepci�n.
		}
	}
	
	/**
	 * M�todo que carga desde la base de datos los datos de los caladeros de
	 * un recurso con c�digo conocido. En esta clase se inicializan todos los
	 * atributos de la clase que tienen relaci�n con la tabla caladero_real.
	 *
	 * @param recurso El c�digo del recurso.
	 */
	public static void cargarCaladeros(int recurso)
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Obtener los datos del proyecto.
		int region = Proyecto.obtenerCodigoRegion();
		int fechaInicial[] = new int[3];
		fechaInicial = Proyecto.obtenerFechaInicialFormatoInt();
		int anioInicial = fechaInicial[2];
		int diaInicial = Servicio.obtenerDia(fechaInicial[0], fechaInicial[1]);
		
		// Realizar la consulta.
		String consulta =
		"select * "+
		"from caladero_real "+
		"where codigo_recurso = "+recurso+" and "+
		"codigo_region = "+region+" and "+
		"codigo_anio <= "+anioInicial+" and "+
		"codigo_dia <= "+diaInicial+" "+
		"order by codigo_anio desc, codigo_dia desc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Setear los valores de los atributos.
		try
		{
			// Posicionar en el �ltimo registro.
			resultado.last();
			int registros = resultado.getRow();
			
			// Inicializar los arreglos.
			tipoLatitudInicialCaladero = new int[registros];
			tipoLatitudFinalCaladero = new int[registros];
			latitudInicialCaladero = new double[registros];
			latitudFinalCaladero = new double[registros];
			areaCaladero = new double[registros];
			abundanciaCaladero = new long[registros];
			biomasaCaladero = new double[registros];
			
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para recorrer los resultados.
			int indice = 0;
			do
			{
				// Setear la alimentaci�n.
				tipoLatitudInicialCaladero[indice] =
				resultado.getInt("codigo_latitud_inicial");
				tipoLatitudFinalCaladero[indice] =
				resultado.getInt("codigo_latitud_final");
				latitudInicialCaladero[indice] =
				resultado.getDouble("latitud_inicial_caladero_real");
				latitudFinalCaladero[indice] =
				resultado.getDouble("latitud_final_caladero_real");
				areaCaladero[indice] =
				resultado.getDouble("area_caladero_real");
				abundanciaCaladero[indice] =
				resultado.getLong("abundancia_caladero_real");
				biomasaCaladero[indice] =
				resultado.getDouble("biomasa_caladero_real");
				
				// Posicionar en el siguiente registro.
				resultado.next();
				
				// Incrementar el �ndice.
				indice++;
			}
			while (!resultado.isAfterLast());
		}
		
		// Capturar la exepci�n.
		catch (SQLException exepcion)
		{
			// No es necesario mostrar la excepci�n.
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * M�todo que calcula la edad promedio de un recurso (a�os) en funci�n de
	 * su talla promedio (cent�metros) y su peso promedio (gramos). La talla
	 * promedio y el peso promedio son obtenidos de los datos ecosist�micos
	 * reales.
	 *
	 * @param ajuste El ajuste de variables.
	 * @param recurso El c�digo del recurso.
	 *
	 * @return edad La edad promedio en a�os.
	 */
	public static double calcularEdad(Ajuste ajuste, int recurso)
	{
		// Variables de la f�rmula.
		double edadMaxima = ajuste.obtenerEdadMaxima();
		double talla = 0;
		double tallaMinima = ajuste.obtenerTallaMinima();
		double tallaMaxima = ajuste.obtenerTallaMaxima();
		double peso = 0;
		double pesoMinimo = ajuste.obtenerPesoMinimo();
		double pesoMaximo = ajuste.obtenerPesoMaximo();
		
		// Establecer los valores que dependen del sexo.
		switch (ajuste.obtenerSexo())
		{
			// Cuando es Macho.
			case SuperIndividuo.MACHO:
			talla = tallaMachos[recurso];
			peso = pesoMachos[recurso];
			break;
			
			// Cuando es Hembra.
			case SuperIndividuo.HEMBRA:
			talla = tallaHembras[recurso];
			peso = pesoHembras[recurso];
			break;
		}
		
		// Calcular la edad en funci�n de la talla.
		double edadTalla = calcularEdad(edadMaxima, talla, tallaMinima,
										tallaMaxima);
		
		// Calcular la edad en funci�n del peso.
		double edadPeso = calcularEdad(edadMaxima, peso, pesoMinimo,
									   pesoMaximo);
		
		// Devolver la edad promedio.
		return (edadTalla + edadPeso) / 2;
	}
	
	/**
	 * M�todo que calcula la edad de un recurso (a�os) en funci�n del rango de
	 * valores especificados en los par�metros. La f�rmula usada es una funci�n
	 * lineal.
	 *
	 * @param edadMaxima El valor de la edad m�xima del recurso (a�os).
	 * @param valor El valor actual.
	 * @param valorMinimo El valor de la cota inferior.
	 * @param valorMaximo El valor de la cota superior.
	 *
	 * @return edad La edad (a�os) en funci�n del rango de valores.
	 */
	private static double calcularEdad(double edadMaxima, double valor,
									   double valorMinimo, double valorMaximo)
	{
		// Variables de la f�rmula.
		double edad = 0;
		
		// Cuando el valor es mayor o igual que el valor m�nimo y el valor es
		// menor o igual que el valor m�ximo.
		if (valorMinimo <= valor && valor <= valorMaximo)
			edad = (valor - valorMinimo)*edadMaxima/(valorMaximo - valorMinimo);
		
		// Cuando el valor es menor que el valor m�nimo o el valor es mayor que
		// el valor m�ximo.
		else
			// Cuando el valor es mayor que el valor m�ximo.
			if (valor > valorMaximo)
				edad = edadMaxima;
		
		// Devolver la edad.
		return edad;
	}
	
	/**
	 * M�todo que calcula la talla de un recurso (cent�metros) a cierta edad
	 * (a�os). La f�rmula usada es la Ecuaci�n de Von Bertalanffy para la talla.
	 *
	 * @param ajuste El ajuste de variables.
	 * @param edad La edad en a�os.
	 *
	 * @return talla La talla en cent�metros.
	 */
	public static double calcularTalla(Ajuste ajuste, double edad)
	{
		// Variables de la f�rmula.
		double Loo = ajuste.obtenerParametroLoo();
		double K = ajuste.obtenerParametroK();
		double t0 = ajuste.obtenerParametroTo();
		double t = edad;
		
		// Devolver la f�rmula.
		return Loo * (1 - Math.exp(-1 * K * (t - t0)));
	}
	
	/**
	 * M�todo que calcula el peso de un recurso (gramos) a cierta talla
	 * (mil�metros). La f�rmula usada es la relaci�n de talla-peso.
	 *
	 * @param ajuste El ajuste de variables.
	 * @param talla La talla en cent�metros.
	 *
	 * @return peso El peso en gramos.
	 */
	public static double calcularPeso(Ajuste ajuste, double talla)
	{
		// Variables de la f�rmula.
		double a = ajuste.obtenerParametroA();
		double b = ajuste.obtenerParametroB();
		
		// Devolver la f�rmula.
		return a * Math.pow(Servicio.transformarCentimetroMilimetro(talla), b);
	}
	
	/**
	 * M�todo que calcula la probabilidad de que un recurso se encuentre maduro
	 * sexualmente a cierta talla (cent�metros). Para los machos, la
	 * probabilidad de madurez sexual es siempre uno. Para las hembras, se usa
	 * la f�rmula de la Ecuaci�n de probabilidad de madurez sexual en funci�n de
	 * la talla.
	 *
	 * @param ajuste El ajuste de variables.
	 * @param talla La talla en cent�metros.
	 *
	 * @return reproduccion La probabilidad de que un recurso sea maduro sexual.
	 */
	public static double calcularReproduccion(Ajuste ajuste, double talla)
	{
		// Cuando es macho.
		if (ajuste.obtenerSexo() == SuperIndividuo.MACHO)
			return 1;
		
		// Cuando es hembra.
		else
		{
			// Variables de la f�rmula.
			double S1 = ajuste.obtenerParametroS1();
			double S2 = ajuste.obtenerParametroS2();
			double L = talla;
			
			// Devolver la f�rmula.
			return 1 / (1 + Math.exp(S1 + S2 * L));
		}
	}
	
	/**
	 * M�todo que determina si un recurso es maduro sexualmente o no. El
	 * procedimiento es revisar su la probabilidad de madurez sexual es mayor o
	 * igual que la madurez sexual establecida. En caso afirmativo se retorna
	 * MADURO. En caso contrario, se retorna INMADURO.
	 *
	 * @param ajuste El ajuste de variables.
	 * @param reproduccion La probabilidad de madurez sexual.
	 *
	 * @return MADURO Cuando la probabilidad de madurez sexual es mayor o igual
	 *                que la madurez sexual establecida.
	 * @return INMADURO Cuando la probabilidad de madurez sexual es menor que la
	 *                  madurez sexual establecida.
	 */
	public static int calcularMadurez(Ajuste ajuste, double reproduccion)
	{
		// Devolver el estado de madurez sexual.
		return (reproduccion >= ajuste.obtenerMadurezSexual()) ?
				SuperIndividuo.MADURO : SuperIndividuo.INMADURO;
	}
	
	/**
	 * M�todo que calcula el n�mero de huevos (unidades) que tiene un
	 * super-individuo a cierta talla (cent�metros). La relaci�n utilizada para
	 * obtener la fecundiad es: (1) A mayor talla, entonces mayor cantidad de
	 * huevos, y (2) a menor talla, entonces menor cantidad de huevos. La
	 * f�rmula usada es una funci�n lineal.
	 *
	 * @param ajuste El ajuste de variables.
	 * @param talla La talla en cent�metros.
	 *
	 * @return fecundidad La cantidad de huevos (unidades) a cierta talla.
	 */
	public static long calcularFecundidad(Ajuste ajuste, double talla)
	{
		// Variables de la f�rmula.
		long fecundidad = 0;
		
		// Cuando la talla es mayor o igual que la talla m�nima y menor o igual
		// que la talla m�xima.
		if (ajuste.obtenerTallaMinima() <= talla &&
			talla <= ajuste.obtenerTallaMaxima())
			fecundidad = (long) ((talla - ajuste.obtenerTallaMinima()) * 
						 (ajuste.obtenerHuevosMaximo() -
						  ajuste.obtenerHuevosMinimo()) / 
						 (ajuste.obtenerTallaMaxima() -
						  ajuste.obtenerTallaMinima()) +
						  ajuste.obtenerTallaMinima());
		
		// Cuando la talla es menor que la talla m�nima o mayor que la talla
		// m�xima.
		else
			// Cuando la talla es menor que la talla m�nima.
			if (talla < ajuste.obtenerTallaMinima())
				fecundidad = ajuste.obtenerHuevosMinimo();
			
			// Cuando la talla es mayor que la talla m�xima.
			else fecundidad = ajuste.obtenerHuevosMaximo();
		
		// Devolver la fecundidad.
		return fecundidad;
	}
	
	/**
	 * M�todo que calcula la decadencia que tiene un super-individuo, a cierta
	 * edad (a�os) y energ�a (valor entre [1, 10]). La relaci�n utilizada para
	 * obtener la decadencia es: (1) A mayor edad, entonces mayor decadencia,
	 * (2) a menor edad, entonces menor decadencia, (3) a menor energ�a,
	 * entonces mayor decadencia, y (4) a mayor energ�a, entonces menor
	 * decadencia. La decadencia es un valor entre [0, 1]. El valor cero indica
	 * que la decadencia es baja. El valor uno indica que la decadencia es alta.
	 * La f�rmula usada es una funci�n lineal.
	 *
	 * @param ajuste El ajuste de variables.
	 * @param edad La talla en a�os.
	 * @param energia La energ�a.
	 *
	 * @return decadencia La decadencia a cierta edad y energ�a.
	 *                    0: Indica que la decandencia es baja.
	 *                    1: Indica que la decadencia es alta.
	 */
	public static double calcularDecadencia(Ajuste ajuste, double edad,
											double energia)
	{
		// Variables de la f�rmula.
		double factorEdad = 0;
		double factorEnergia = 0;
		
		// Cuando la edad es mayor o igual que la edad m�nima y menor o igual
		// que la edad m�xima.
		if (0 <= edad && edad <= ajuste.obtenerEdadMaxima())
			factorEdad = edad/ajuste.obtenerEdadMaxima();
		
		// Cuando la edad es menor que la edad m�nima o mayor que la edad
		// m�xima.
		else factorEdad = 1;
		
		// Cuando la energia es mayor o igual que la energ�a m�nima y menor o
		// igual que la energ�a m�xima.
		if (ajuste.obtenerEnergiaMinima() <= energia &&
			energia <= ajuste.obtenerEnergiaMaxima())
			factorEnergia = 1 - (energia - ajuste.obtenerEnergiaMinima())/
								(ajuste.obtenerEnergiaMaxima() -
								 ajuste.obtenerEnergiaMinima());
		
		// Cuando la energia es menor que la energ�a m�nima o mayor que la
		// energ�a m�xima.
		else
			// Cuando la energia es menor que la energ�a m�nima.
			if (energia < ajuste.obtenerEnergiaMinima())
				factorEnergia = 1;
		
		// Devolver la decandencia ponderada.
		return 0.2 * factorEdad + 0.8 * factorEnergia;
	}
	
	/**
	 * M�todo que calcula la aptitud que tiene un super-individuo, dependiendo
	 * de su edad (a�os), talla (cent�metros) y peso (gramos). El valor de la
	 * aptitud es un n�mero entre el rango [0, 1]. El valor cero indica que la
	 * aptitud es baja. El valor uno indice que la aptitud es alta. La f�rmula
	 * usada es una funci�n lineal.
	 *
	 * @param ajuste El ajuste de variables.
	 * @param edad La talla en a�os.
	 * @param talla La talla en cent�metros.
	 * @param peso El peso en gramos.
	 *
	 * @return aptitud La aptitud a cierta edad, talla y peso.
	 *                 0: Indica que la aptitud es baja.
	 *                 1: Indica que la aptitud es alta.
	 */
	public static double calcularAptitud(Ajuste ajuste, double edad,
										 double talla, double peso)
	{
		// Variables de la f�rmula.
		double aptitudEdad = 0;
		double aptitudTalla = 0;
		double aptitudPeso = 0;
		
		// Calcular la aptitud con respecto a la edad.
		if (0 <= edad && edad <= ajuste.obtenerEdadMaxima())
			aptitudEdad = 1 - edad / ajuste.obtenerEdadMaxima();
		else
			if (edad > ajuste.obtenerEdadMaxima())
				aptitudEdad = 0;
		
		// Calcular la aptitud con respecto a la talla.
		if (ajuste.obtenerTallaMinima() <= talla &&
			talla <= ajuste.obtenerTallaMaxima())
			aptitudTalla = (talla - ajuste.obtenerTallaMinima()) /
						   (ajuste.obtenerTallaMaxima() -
						    ajuste.obtenerTallaMinima());
		else
			if (talla > ajuste.obtenerTallaMaxima())
				aptitudEdad = 1;
		
		// Calcular la aptitud con respecto al peso.
		if (ajuste.obtenerPesoMinimo() <= peso &&
			peso <= ajuste.obtenerPesoMaximo())
			aptitudPeso = (peso - ajuste.obtenerPesoMinimo()) /
						  (ajuste.obtenerPesoMaximo() -
						   ajuste.obtenerPesoMinimo());
		else
			if (peso > ajuste.obtenerPesoMaximo())
				aptitudPeso = 1;
		
		// Devolver la aptitud ponderada.
		return aptitudEdad * 0.2 + aptitudTalla * 0.5 + aptitudPeso * 0.3;
	}
	
	/**
	 * M�todo que calcula la calidad que tiene un super-individuo, dependiendo
	 * de su estado, edad, talla, peso, reproducci�n, madurez y tiempo de
	 * portaci�n. El valor de la calidad es un n�mero entre el rango [0, 1]. El
	 * valor cero indica que la calidad es baja. El valor uno indice que la
	 * calidad es alta. La f�rmula usada es una funci�n lineal.
	 *
	 * @param ajuste El ajuste de variables.
	 * @param estado El estado entre {1, 2, 3, 4, 5}.
	 * @param edad La talla en a�os.
	 * @param talla La talla en cent�metros.
	 * @param peso El peso en gramos.
	 * @param reproduccion La probabilidad de reproducci�n entre [0, 1].
	 * @param madurez El estado de madurez sexual entre {1, 2}.
	 * @param tiempoPortacion El tiempo que le queda para desovar en d�as.
	 *
	 * @return calidad La calidad extractiva del producto a cierto estado, edad,
	 *                 talla, peso, reproducci�n, madurez y tiempo de portaci�n.
	 *                 0: Indica que la calidad es baja.
	 *                 1: Indica que la calidad es alta.
	 */
	public static double calcularCalidad(Ajuste ajuste, int estado, double edad,
										 double talla, double peso,
										 double reproduccion, int madurez,
										 double tiempoPortacion)
	{
		// Variables de la f�rmula.
		double calidadEstado = 0;
		double calidadAptitud = 0;
		double calidadReproduccion = 0;
		double calidadMadurez = 0;
		double calidadTiempoPortacion = 0;
		
		// Dependiendo del estado, calcular el factor de calidad por estado.
		switch (estado)
		{
			// Cuando est� Desaparecido.
			case SuperIndividuo.DESAPARECIDO:
			calidadEstado = 0;
			break;
			
			// Cuando est� Muerto.
			case SuperIndividuo.MUERTO:
			calidadEstado = 0;
			break;
			
			// Cuando est� Sin Huevos.
			case SuperIndividuo.SIN_HUEVOS:
			calidadEstado = 1;
			break;
			
			// Cuando est� Con Huevos.
			case SuperIndividuo.CON_HUEVOS:
			calidadEstado = 0;
			break;
		}
		
		// Calcular el factor de calidad por aptitud.
		calidadAptitud = calcularAptitud(ajuste, edad, talla, peso);
		
		// Calcular el factor de calidad por reproducci�n.
		calidadReproduccion = 1 - reproduccion;
		
		// Dependiendo de la madurez sexual, calcular el factor de calidad por
		// madurez sexual.
		switch (madurez)
		{
			// Cuando es Maduro.
			case SuperIndividuo.MADURO:
			calidadMadurez = 1;
			break;
			
			// Cuando es Inmaduro.
			case SuperIndividuo.INMADURO:
			calidadMadurez = 0;
			break;
		}
		
		// Calcular el factor de calidad por tiempo de portaci�n.
		if (tiempoPortacion > 0)
			calidadTiempoPortacion = 0;
		else calidadTiempoPortacion = 1;
		
		// Devolver la calidad ponderada.
		return calidadEstado * 0.1 + calidadAptitud * 0.2 +
			   calidadReproduccion * 0.3 + calidadMadurez * 0.1 +
			   calidadTiempoPortacion * 0.3;
	}
	
	/**
	 * M�todo que calcula la migraci�n de longitud que tiene un super-individuo.
	 * La longitud a migrar corresponde a un n�mero aleatorio entre la longitud
	 * inicial y final que tiene el super-individuo. Luego se retorna este
	 * valor.
	 *
	 * @param ajuste Las variables de ajuste del super-individuo.
	 *
	 * @return longitud La longitud a migrar.
	 */
	public static double calcularLongitud(Ajuste ajuste)
	{
		return Servicio.obtenerAzar(Mapa.obtenerLongitudInicial(),
									Mapa.obtenerLongitudFinal());
	}
	
	/**
	 * M�todo que calcula la migraci�n de latitud que tiene un super-individuo.
	 * La latitud a migrar corresponde a un n�mero aleatorio entre la latitud
	 * inicial y final que tiene el super-individuo. Luego se retorna este
	 * valor.
	 *
	 * @param ajuste Las variables de ajuste del super-individuo.
	 *
	 * @return latitud La latitud a migrar.
	 */
	public static double calcularLatitud(Ajuste ajuste)
	{
		return Servicio.obtenerAzar(ajuste.obtenerLatitudInicial(),
									ajuste.obtenerLatitudFinal());
	}
	
	/**
	 * M�todo que calcula la migraci�n de altitud que tiene un super-individuo.
	 * La altitud a migrar corresponde a un n�mero aleatorio entre la altitud
	 * inicial y final que tiene el super-individuo. Luego se retorna este
	 * valor.
	 *
	 * @param ajuste Las variables de ajuste del super-individuo.
	 *
	 * @return altitud La altitud a migrar.
	 */
	public static double calcularAltitud(Ajuste ajuste)
	{
		return Servicio.obtenerAzar(ajuste.obtenerAltitudInicial(),
									ajuste.obtenerAltitudFinal());
	}
	
	/**
	 * M�todo que calcula la longitud de habitat de un recurso (metros) a cierta
	 * edad (a�os). Este concepto se denomina segregaci�n espacial del recurso
	 * por longitud.
	 *
	 * @param ajuste Las variables de ajuste.
	 * @param edad La edad en a�os.
	 *
	 * @return longitud La longitud de h�bitat con respecto a la edad.
	 */
	/*
	 * OBSERVACION: Falta implementar este m�todo.
	 */
	public static double calcularLongitudSegregacion(Ajuste ajuste, double edad)
	{
		return 0;
	}
	
	/**
	 * M�todo que calcula la latitud de habitat de un recurso (metros) a cierta
	 * edad (a�os). Este concepto se denomina segregaci�n espacial del recurso
	 * por latitud.
	 *
	 * @param ajuste Las variables de ajuste.
	 * @param edad La edad en a�os.
	 *
	 * @return latitud La latitud de h�bitat con respecto a la edad.
	 */
	/*
	 * OBSERVACION: Falta implementar este m�todo.
	 */
	public static double calcularLatitudSegregacion(Ajuste ajuste, double edad)
	{
		return 0;
	}
	
	/**
	 * M�todo que calcula la altitud de habitat de un recurso (metros) a cierta
	 * edad (a�os). La regla usada es que los recursos m�s j�venes viven m�s
	 * cerca de la altitud inicial y los recursos m�s viejos viven m�s cerca de
	 * la altitud final. Este concepto se denomina segregaci�n espacial del
	 * recurso por altitud. La f�rmula usada es una funci�n lineal.
	 *
	 * @param ajuste Las variables de ajuste.
	 * @param edad La edad en a�os.
	 *
	 * @return altitud La altitud de h�bitat con respecto a la edad.
	 */
	public static double calcularAltitudSegregacion(Ajuste ajuste, double edad)
	{
		// Variables de la f�rmula.
		double altitud = 0;
		double edadMaxima = ajuste.obtenerEdadMaxima();
		
		// Cuando la edad es mayor o igual que la edad m�nima y menor o igual
		// que la edad m�xima.
		if (0 <= edad && edad <= edadMaxima)
			altitud = ajuste.obtenerAltitudInicial() +
					  (ajuste.obtenerAltitudFinal() -
					  ajuste.obtenerAltitudInicial()) * edad/
			          edadMaxima;
		
		// Cuando la edad es menor que la edad m�nima o mayor que la edad
		// m�xima.
		else
			// Cuando la edad es mayor que la edad m�xima.
			if (edadMaxima < edad)
				altitud = ajuste.obtenerAltitudFinal();
		
		// Devolver la latitud a migrar.
		return altitud;
	}
	
	/**
	 * M�todo que calcula la adecuaci�n del tama�o para la selecci�n de presas.
	 * La f�rmula usada es la raz�n de predaci�n (talla presa/talla predador).
	 *
	 * @param tallaPresa La talla de la presa en cent�metros.
	 * @param tallaPredador La talla del predador en cent�metros.
	 *
	 * @return adecuacionTamanio La raz�n de predaci�n.
	 */
	public static double calcularAdecuacionTamanio(double tallaPresa,
												   double tallaPredador)
	{
		return tallaPresa / tallaPredador;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo cantidadRecursos.
	 *
	 * @return cantidadRecursos El valor del atributo cantidadRecursos.
	 */
	public static int obtenerCantidadRecursos()
	{
		return cantidadRecursos;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tipoAnimal con un �ndice
	 * conocido.
	 *
	 * @return tipoAnimal[i] El valor del atributo tipoAnimal[i].
	 */
	public static int obtenerTipoAnimal(int i)
	{
		return tipoAnimal[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo distanciaFondoMinimaZonaSistema
	 * con un �ndice conocido.
	 *
	 * @return distanciaFondoMinimaZonaSistema[i] El valor del atributo
	 *                                       distanciaFondoMinimaZonaSistema[i].
	 */
	public static double obtenerDistanciaFondoMinimaZonaSistema(int i)
	{
		return distanciaFondoMinimaZonaSistema[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo distanciaFondoMaximaZonaSistema
	 * con un �ndice conocido.
	 *
	 * @return distanciaFondoMaximaZonaSistema[i] El valor del atributo
	 *                                       distanciaFondoMaximaZonaSistema[i].
	 */
	public static double obtenerDistanciaFondoMaximaZonaSistema(int i)
	{
		return distanciaFondoMaximaZonaSistema[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo altitudInicialZonaPelagica con
	 * un �ndice conocido.
	 *
	 * @return altitudInicialZonaPelagica[i] El valor del atributo
	 *                                       altitudInicialZonaPelagica[i].
	 */
	public static double obtenerAltitudInicialZonaPelagica(int i)
	{
		return altitudInicialZonaPelagica[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo altitudFinalZonaPelagica con un
	 * �ndice conocido.
	 *
	 * @return altitudFinalZonaPelagica[i] El valor del atributo
	 *                                     altitudFinalZonaPelagica[i].
	 */
	public static double obtenerAltitudFinalZonaPelagica(int i)
	{
		return altitudFinalZonaPelagica[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo altitudInicialZonaBentonica con
	 * un �ndice conocido.
	 *
	 * @return altitudInicialZonaBentonica[i] El valor del atributo
	 *                                        altitudInicialZonaBentonica[i].
	 */
	public static double obtenerAltitudInicialZonaBentonica(int i)
	{
		return altitudInicialZonaBentonica[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo altitudFinalZonaBentonica con un
	 * �ndice conocido.
	 *
	 * @return altitudFinalZonaBentonica[i] El valor del atributo
	 *                                      altitudFinalZonaBentonica[i].
	 */
	public static double obtenerAltitudFinalZonaBentonica(int i)
	{
		return altitudFinalZonaBentonica[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo
	 * distanciaContinenteMinimaZonaRegion con un �ndice conocido.
	 *
	 * @return distanciaContinenteMinimaZonaRegion[i] El valor del atributo
	 *                                   distanciaContinenteMinimaZonaRegion[i].
	 */
	public static double obtenerDistanciaContinenteMinimaZonaRegion(int i)
	{
		return distanciaContinenteMinimaZonaRegion[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo
	 * distanciaContinenteMaximaZonaRegion con un �ndice conocido.
	 *
	 * @return distanciaContinenteMaximaZonaRegion[i] El valor del atributo
	 *                                   distanciaContinenteMaximaZonaRegion[i].
	 */
	public static double obtenerDistanciaContinenteMaximaZonaRegion(int i)
	{
		return distanciaContinenteMaximaZonaRegion[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo altitudInicialZonaLuz con un
	 * �ndice conocido.
	 *
	 * @return altitudInicialZonaLuz[i] El valor del atributo
	 *                                  altitudInicialZonaLuz[i].
	 */
	public static double obtenerAltitudInicialZonaLuz(int i)
	{
		return altitudInicialZonaLuz[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo altitudFinalZonaLuz con un
	 * �ndice conocido.
	 *
	 * @return altitudFinalZonaLuz[i] El valor del atributo
	 *                                altitudFinalZonaLuz[i].
	 */
	public static double obtenerAltitudFinalZonaLuz(int i)
	{
		return altitudFinalZonaLuz[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo conducta con un �ndice conocido.
	 *
	 * @return conducta[i] El valor del atributo conducta[i].
	 */
	public static int obtenerConducta(int i)
	{
		return conducta[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo colorRojo con un �ndice
	 * conocido.
	 *
	 * @return colorRojo[i] El valor del atributo colorRojo[i].
	 */
	public static int obtenerColorRojo(int i)
	{
		return colorRojo[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo colorVerde con un �ndice
	 * conocido.
	 *
	 * @return colorVerde[i] El valor del atributo colorVerde[i].
	 */
	public static int obtenerColorVerde(int i)
	{
		return colorVerde[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo colorAzul con un �ndice
	 * conocido.
	 *
	 * @return colorAzul[i] El valor del atributo colorAzul[i].
	 */
	public static int obtenerColorAzul(int i)
	{
		return colorAzul[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tipoLatitudInicial con un �ndice
	 * conocido.
	 *
	 * @return tipoLatitudInicial[i] El valor del atributo
	 *                               tipoLatitudInicial[i].
	 */
	public static int obtenerTipoLatitudInicial(int i)
	{
		return tipoLatitudInicial[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tipoLatitudFinal con un �ndice
	 * conocido.
	 *
	 * @return tipoLatitudFinal[i] El valor del atributo tipoLatitudFinal[i].
	 */
	public static int obtenerTipoLatitudFinal(int i)
	{
		return tipoLatitudFinal[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tipoRecurso con un �ndice
	 * conocido.
	 *
	 * @return tipoRecurso[i] El valor del atributo tipoRecurso[i].
	 */
	public static int obtenerTipoRecurso(int i)
	{
		return tipoRecurso[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tipoReproduccion con un �ndice
	 * conocido.
	 *
	 * @return tipoReproduccion[i] El valor del atributo tipoReproduccion[i].
	 */
	public static int obtenerTipoReproduccion(int i)
	{
		return tipoReproduccion[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo nombreComun con un �ndice
	 * conocido.
	 *
	 * @return nombreComun[i] El valor del atributo nombreComun[i].
	 */
	public static String obtenerNombreComun(int i)
	{
		return nombreComun[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo edadMaximaMacho con un �ndice
	 * conocido.
	 *
	 * @return edadMaximaMacho[i] El valor del atributo edadMaximaMacho[i].
	 */
	public static double obtenerEdadMaximaMacho(int i)
	{
		return edadMaximaMacho[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo edadMaximaHembra con un �ndice
	 * conocido.
	 *
	 * @return edadMaximaHembra[i] El valor del atributo edadMaximaHembra[i].
	 */
	public static double obtenerEdadMaximaHembra(int i)
	{
		return edadMaximaHembra[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroLooMacho con un �ndice
	 * conocido.
	 *
	 * @return parametroLooMacho[i] El valor del atributo parametroLooMacho[i].
	 */
	public static double obtenerParametroLooMacho(int i)
	{
		return parametroLooMacho[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroToMacho con un �ndice
	 * conocido.
	 *
	 * @return parametroToMacho[i] El valor del atributo parametroToMacho[i].
	 */
	public static double obtenerParametroToMacho(int i)
	{
		return parametroToMacho[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroKMacho con un �ndice
	 * conocido.
	 *
	 * @return parametroKMacho[i] El valor del atributo parametroKMacho[i].
	 */
	public static double obtenerParametroKMacho(int i)
	{
		return parametroKMacho[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroAMacho con un �ndice
	 * conocido.
	 *
	 * @return parametroAMacho[i] El valor del atributo parametroAMacho[i].
	 */
	public static double obtenerParametroAMacho(int i)
	{
		return parametroAMacho[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroBMacho con un �ndice
	 * conocido.
	 *
	 * @return parametroBMacho[i] El valor del atributo parametroBMacho[i].
	 */
	public static double obtenerParametroBMacho(int i)
	{
		return parametroBMacho[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroLooHembra con un �ndice
	 * conocido.
	 *
	 * @return parametroLooHembra[i] El valor del atributo
	 *                               parametroLooHembra[i].
	 */
	public static double obtenerParametroLooHembra(int i)
	{
		return parametroLooHembra[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroToHembra con un �ndice
	 * conocido.
	 *
	 * @return parametroToHembra[i] El valor del atributo parametroToHembra[i].
	 */
	public static double obtenerParametroToHembra(int i)
	{
		return parametroToHembra[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroKHembra con un �ndice
	 * conocido.
	 *
	 * @return parametroKHembra[i] El valor del atributo parametroKHembra[i].
	 */
	public static double obtenerParametroKHembra(int i)
	{
		return parametroKHembra[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroAHembra con un �ndice
	 * conocido.
	 *
	 * @return parametroAHembra[i] El valor del atributo parametroAHembra[i].
	 */
	public static double obtenerParametroAHembra(int i)
	{
		return parametroAHembra[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroBHembra con un �ndice
	 * conocido.
	 *
	 * @return parametroBHembra[i] El valor del atributo parametroBHembra[i].
	 */
	public static double obtenerParametroBHembra(int i)
	{
		return parametroBHembra[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tallaMinimaMacho con un �ndice
	 * conocido.
	 *
	 * @return tallaMinimaMacho[i] El valor del atributo tallaMinimaMacho[i].
	 */
	public static double obtenerTallaMinimaMacho(int i)
	{
		return tallaMinimaMacho[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tallaMaximaMacho con un �ndice
	 * conocido.
	 *
	 * @return tallaMaximaMacho[i] El valor del atributo tallaMaximaMacho[i].
	 */
	public static double obtenerTallaMaximaMacho(int i)
	{
		return tallaMaximaMacho[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tallaMinimaHembra con un �ndice
	 * conocido.
	 *
	 * @return tallaMinimaHembra[i] El valor del atributo tallaMinimaHembra[i].
	 */
	public static double obtenerTallaMinimaHembra(int i)
	{
		return tallaMinimaHembra[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tallaMaximaHembra con un �ndice
	 * conocido.
	 *
	 * @return tallaMaximaHembra[i] El valor del atributo tallaMaximaHembra[i].
	 */
	public static double obtenerTallaMaximaHembra(int i)
	{
		return tallaMaximaHembra[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo pesoMinimoMacho con un �ndice
	 * conocido.
	 *
	 * @return pesoMinimoMacho[i] El valor del atributo pesoMinimoMacho[i].
	 */
	public static double obtenerPesoMinimoMacho(int i)
	{
		return pesoMinimoMacho[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo pesoMaximoMacho con un �ndice
	 * conocido.
	 *
	 * @return pesoMaximoMacho[i] El valor del atributo pesoMaximoMacho[i].
	 */
	public static double obtenerPesoMaximoMacho(int i)
	{
		return pesoMaximoMacho[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo pesoMinimoHembra con un �ndice
	 * conocido.
	 *
	 * @return pesoMinimoHembra[i] El valor del atributo pesoMinimoHembra[i].
	 */
	public static double obtenerPesoMinimoHembra(int i)
	{
		return pesoMinimoHembra[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo pesoMaximoHembra con un �ndice
	 * conocido.
	 *
	 * @return pesoMaximoHembra[i] El valor del atributo pesoMaximoHembra[i].
	 */
	public static double obtenerPesoMaximoHembra(int i)
	{
		return pesoMaximoHembra[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroS1 con un �ndice
	 * conocido.
	 *
	 * @return parametroS1[i] El valor del atributo parametroS1[i].
	 */
	public static double obtenerParametroS1(int i)
	{
		return parametroS1[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroS2 con un �ndice
	 * conocido.
	 *
	 * @return parametroS2[i] El valor del atributo parametroS2[i].
	 */
	public static double obtenerParametroS2(int i)
	{
		return parametroS2[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo madurezSexual con un �ndice
	 * conocido.
	 *
	 * @return madurezSexual[i] El valor del atributo madurezSexual[i].
	 */
	public static double obtenerMadurezSexual(int i)
	{
		return madurezSexual[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo huevosMinimo con un �ndice
	 * conocido.
	 *
	 * @return huevosMinimo[i] El valor del atributo huevosMinimo[i].
	 */
	public static long obtenerHuevosMinimo(int i)
	{
		return huevosMinimo[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo huevosMaximo con un �ndice
	 * conocido.
	 *
	 * @return huevosMaximo[i] El valor del atributo huevosMaximo[i].
	 */
	public static long obtenerHuevosMaximo(int i)
	{
		return huevosMaximo[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo periodoPortacion con un �ndice
	 * conocido.
	 *
	 * @return periodoPortacion[i] El valor del atributo periodoPortacion[i].
	 */
	public static int obtenerPeriodoPortacion(int i)
	{
		return periodoPortacion[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tasaNatalidad con un �ndice
	 * conocido.
	 *
	 * @return tasaNatalidad[i] El valor del atributo tasaNatalidad[i].
	 */
	public static double obtenerTasaNatalidad(int i)
	{
		return tasaNatalidad[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tasaMortalidad con un �ndice
	 * conocido.
	 *
	 * @return tasaMortalidad[i] El valor del atributo tasaMortalidad[i].
	 */
	public static long obtenerTasaMortalidad(int i)
	{
		return tasaMortalidad[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo latitudInicial con un �ndice
	 * conocido.
	 *
	 * @return latitudInicial[i] El valor del atributo latitudInicial[i].
	 */
	public static double obtenerLatitudInicial(int i)
	{
		return latitudInicial[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo latitudFinal con un �ndice
	 * conocido.
	 *
	 * @return latitudFinal[i] El valor del atributo latitudFinal[i].
	 */
	public static double obtenerLatitudFinal(int i)
	{
		return latitudFinal[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo altitudInicial con un �ndice
	 * conocido.
	 *
	 * @return altitudInicial[i] El valor del atributo altitudInicial[i].
	 */
	public static double obtenerAltitudInicial(int i)
	{
		return altitudInicial[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo altitudFinal con un �ndice
	 * conocido.
	 *
	 * @return altitudFinal[i] El valor del atributo altitudFinal[i].
	 */
	public static double obtenerAltitudFinal(int i)
	{
		return altitudFinal[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo temperaturaInicial con un �ndice
	 * conocido.
	 *
	 * @return temperaturaInicial[i] El valor del atributo
	 *                               temperaturaInicial[i].
	 */
	public static double obtenerTemperaturaInicial(int i)
	{
		return temperaturaInicial[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo temperaturaFinal con un �ndice
	 * conocido.
	 *
	 * @return temperaturaFinal[i] El valor del atributo temperaturaFinal[i].
	 */
	public static double obtenerTemperaturaFinal(int i)
	{
		return temperaturaFinal[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo salinidadInicial con un �ndice
	 * conocido.
	 *
	 * @return salinidadInicial[i] El valor del atributo salinidadInicial[i].
	 */
	public static double obtenerSalinidadInicial(int i)
	{
		return salinidadInicial[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo salinidadFinal con un �ndice
	 * conocido.
	 *
	 * @return salinidadFinal[i] El valor del atributo salinidadFinal[i].
	 */
	public static double obtenerSalinidadFinal(int i)
	{
		return salinidadFinal[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo oxigenoDisueltoInicial con un
	 * �ndice conocido.
	 *
	 * @return oxigenoDisueltoInicial[i] El valor del atributo
	 *                                   oxigenoDisueltoInicial[i].
	 */
	public static double obtenerOxigenoDisueltoInicial(int i)
	{
		return oxigenoDisueltoInicial[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo oxigenoDisueltoFinal con un
	 * �ndice conocido.
	 *
	 * @return oxigenoDisueltoFinal[i] El valor del atributo
	 *                                 oxigenoDisueltoFinal[i].
	 */
	public static double obtenerOxigenoDisueltoFinal(int i)
	{
		return oxigenoDisueltoFinal[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo percepcion con un �ndice
	 * conocido.
	 *
	 * @return percepcion[i] El valor del atributo percepcion[i].
	 */
	public static double obtenerPercepcion(int i)
	{
		return percepcion[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo velocidad con un �ndice
	 * conocido.
	 *
	 * @return velocidad[i] El valor del atributo velocidad[i].
	 */
	public static double obtenerVelocidad(int i)
	{
		return velocidad[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo energiaInicial con un �ndice
	 * conocido.
	 *
	 * @return energiaInicial[i] El valor del atributo energiaInicial[i].
	 */
	public static double obtenerEnergiaInicial(int i)
	{
		return energiaInicial[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo energiaMinima con un �ndice
	 * conocido.
	 *
	 * @return energiaMinima[i] El valor del atributo energiaMinima[i].
	 */
	public static double obtenerEnergiaMinima(int i)
	{
		return energiaMinima[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo energiaMaxima con un �ndice
	 * conocido.
	 *
	 * @return energiaMaxima[i] El valor del atributo energiaMaxima[i].
	 */
	public static double obtenerEnergiaMaxima(int i)
	{
		return energiaMaxima[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo energiaTiempo con un �ndice
	 * conocido.
	 *
	 * @return energiaTiempo[i] El valor del atributo energiaTiempo[i].
	 */
	public static double obtenerEnergiaTiempo(int i)
	{
		return energiaTiempo[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo energiaConsumo con un �ndice
	 * conocido.
	 *
	 * @return energiaConsumo[i] El valor del atributo energiaConsumo[i].
	 */
	public static double obtenerEnergiaConsumo(int i)
	{
		return energiaConsumo[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo energiaDesplazamiento con un
	 * �ndice conocido.
	 *
	 * @return energiaDesplazamiento[i] El valor del atributo
	 *                                  energiaDesplazamiento[i].
	 */
	public static double obtenerEnergiaDesplazamiento(int i)
	{
		return energiaDesplazamiento[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo descomposicion con un �ndice
	 * conocido.
	 *
	 * @return descomposicion[i] El valor del atributo descomposicion[i].
	 */
	public static double obtenerDescomposicion(int i)
	{
		return descomposicion[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaArrancar con un �ndice
	 * conocido.
	 *
	 * @return tendenciaArrancar[i] El valor del atributo tendenciaArrancar[i].
	 */
	public static double obtenerTendenciaArrancar(int i)
	{
		return tendenciaArrancar[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaPredar con un �ndice
	 * conocido.
	 *
	 * @return tendenciaPredar[i] El valor del atributo tendenciaPredar[i].
	 */
	public static double obtenerTendenciaPredar(int i)
	{
		return tendenciaPredar[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaReproducir con un
	 * �ndice conocido.
	 *
	 * @return tendenciaReproducir[i] El valor del atributo
	 *                                tendenciaReproducir[i].
	 */
	public static double obtenerTendenciaReproducir(int i)
	{
		return tendenciaReproducir[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaDesovar con un �ndice
	 * conocido.
	 *
	 * @return tendenciaDesovar[i] El valor del atributo tendenciaDesovar[i].
	 */
	public static double obtenerTendenciaDesovar(int i)
	{
		return tendenciaDesovar[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaConvivir con un �ndice
	 * conocido.
	 *
	 * @return tendenciaConvivir[i] El valor del atributo tendenciaConvivir[i].
	 */
	public static double obtenerTendenciaConvivir(int i)
	{
		return tendenciaConvivir[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaMigrar con un �ndice
	 * conocido.
	 *
	 * @return tendenciaMigrar[i] El valor del atributo tendenciaMigrar[i].
	 */
	public static double obtenerTendenciaMigrar(int i)
	{
		return tendenciaMigrar[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaDivagar con un �ndice
	 * conocido.
	 *
	 * @return tendenciaDivagar[i] El valor del atributo tendenciaDivagar[i].
	 */
	public static double obtenerTendenciaDivagar(int i)
	{
		return tendenciaDivagar[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaNada con un �ndice
	 * conocido.
	 *
	 * @return tendenciaNada[i] El valor del atributo tendenciaNada[i].
	 */
	public static double obtenerTendenciaNada(int i)
	{
		return tendenciaNada[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo abundancia con un �ndice
	 * conocido.
	 *
	 * @return abundancia[i] El valor del atributo abundancia[i].
	 */
	public static long obtenerAbundancia(int i)
	{
		return abundancia[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo porcentajeMachos con un �ndice
	 * conocido.
	 *
	 * @return porcentajeMachos[i] El valor del atributo porcentajeMachos[i].
	 */
	public static double obtenerPorcentajeMachos(int i)
	{
		return porcentajeMachos[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo porcentajeHembras con un �ndice
	 * conocido.
	 *
	 * @return porcentajeHembras[i] El valor del atributo porcentajeHembras[i].
	 */
	public static double obtenerPorcentajeHembras(int i)
	{
		return porcentajeHembras[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo porcentajeHembrasSH con un
	 * �ndice conocido.
	 *
	 * @return porcentajeHembrasSH[i] El valor del atributo
	 *                                porcentajeHembrasSH[i].
	 */
	public static double obtenerPorcentajeHembrasSH(int i)
	{
		return porcentajeHembrasSH[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo porcentajeHembrasCH con un
	 * �ndice conocido.
	 *
	 * @return porcentajeHembrasCH[i] El valor del atributo
	 *                                porcentajeHembrasCH[i].
	 */
	public static double obtenerPorcentajeHembrasCH(int i)
	{
		return porcentajeHembrasCH[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo talla con un �ndice conocido.
	 *
	 * @return talla[i] El valor del atributo talla[i].
	 */
	public static double obtenerTalla(int i)
	{
		return talla[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tallaMachos con un �ndice
	 * conocido.
	 *
	 * @return tallaMachos[i] El valor del atributo tallaMachos[i].
	 */
	public static double obtenerTallaMachos(int i)
	{
		return tallaMachos[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tallaHembras con un �ndice
	 * conocido.
	 *
	 * @return tallaHembras[i] El valor del atributo tallaHembras[i].
	 */
	public static double obtenerTallaHembras(int i)
	{
		return tallaHembras[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tallaHembrasSH con un �ndice
	 * conocido.
	 *
	 * @return tallaHembrasSH[i] El valor del atributo tallaHembrasSH[i].
	 */
	public static double obtenerTallaHembrasSH(int i)
	{
		return tallaHembrasSH[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tallaHembrasCH con un �ndice
	 * conocido.
	 *
	 * @return tallaHembrasCH[i] El valor del atributo tallaHembrasCH[i].
	 */
	public static double obtenerTallaHembrasCH(int i)
	{
		return tallaHembrasCH[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo biomasa con un �ndice conocido.
	 *
	 * @return biomasa[i] El valor del atributo biomasa[i].
	 */
	public static double obtenerBiomasa(int i)
	{
		return biomasa[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo peso con un �ndice conocido.
	 *
	 * @return peso[i] El valor del atributo peso[i].
	 */
	public static double obtenerPeso(int i)
	{
		return peso[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo pesoMachos con un �ndice
	 * conocido.
	 *
	 * @return pesoMachos[i] El valor del atributo pesoMachos[i].
	 */
	public static double obtenerPesoMachos(int i)
	{
		return pesoMachos[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo pesoHembras con un �ndice
	 * conocido.
	 *
	 * @return pesoHembras[i] El valor del atributo pesoHembras[i].
	 */
	public static double obtenerPesoHembras(int i)
	{
		return pesoHembras[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo pesoHembrasSH con un �ndice
	 * conocido.
	 *
	 * @return pesoHembrasSH[i] El valor del atributo pesoHembrasSH[i].
	 */
	public static double obtenerPesoHembrasSH(int i)
	{
		return pesoHembrasSH[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo pesoHembrasCH con un �ndice
	 * conocido.
	 *
	 * @return pesoHembrasCH[i] El valor del atributo pesoHembrasCH[i].
	 */
	public static double obtenerPesoHembrasCH(int i)
	{
		return pesoHembrasCH[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo alimentacionTipoDieta con los
	 * �ndices conocidos.
	 *
	 * @return alimentacionTipoDieta[i][j] El valor del atributo
	 *                                     alimentacionTipoDieta[i][j].
	 */
	public static String obtenerAlimentacionTipoDieta(int i, int j)
	{
		return alimentacionTipoDieta[i][j];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo alimentacionFaunaAcompaniante
	 * con los �ndices conocidos.
	 *
	 * @return alimentacionFaunaAcompaniante[i][j] El valor del atributo
	 *                                      alimentacionFaunaAcompaniante[i][j].
	 */
	public static double obtenerAlimentacionFaunaAcompaniante(int i, int j)
	{
		return alimentacionFaunaAcompaniante[i][j];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo portacion con los �ndices
	 * conocidos.
	 *
	 * @return portacion[i][j] El valor del atributo portacion[i][j].
	 */
	public static double obtenerPortacion(int i, int j)
	{
		return portacion[i][j];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo desove con los �ndices
	 * conocidos.
	 *
	 * @return desove[i][j] El valor del atributo desove[i][j].
	 */
	public static double obtenerDesove(int i, int j)
	{
		return desove[i][j];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo caladero con un �ndice conocido.
	 *
	 * @return caladero[i] El valor del atributo caladero[i].
	 */
	public static int obtenerCantidadCaladeros(int i)
	{
		return cantidadCaladeros[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tipoLatitudInicialCaladero con
	 * un �ndice conocido.
	 *
	 * @return tipoLatitudInicialCaladero[i] El valor del atributo
	 *                                       tipoLatitudInicialCaladero[i].
	 */
	public static int obtenerTipoLatitudInicialCaladero(int i)
	{
		return tipoLatitudInicialCaladero[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tipoLatitudFinalCaladero con
	 * un �ndice conocido.
	 *
	 * @return tipoLatitudFinalCaladero[i] El valor del atributo
	 *                                     tipoLatitudFinalCaladero[i].
	 */
	public static int obtenerTipoLatitudFinalCaladero(int i)
	{
		return tipoLatitudFinalCaladero[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo latitudInicialCaladero con un
	 * �ndice conocido.
	 *
	 * @return latitudInicialCaladero[i] El valor del atributo
	 *                                   latitudInicialCaladero[i].
	 */
	public static double obtenerLatitudInicialCaladero(int i)
	{
		return latitudInicialCaladero[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo latitudFinalCaladero con un
	 * �ndice conocido.
	 *
	 * @return latitudFinalCaladero[i] El valor del atributo
	 *                                 latitudFinalCaladero[i].
	 */
	public static double obtenerLatitudFinalCaladero(int i)
	{
		return latitudFinalCaladero[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo areaCaladero con un �ndice
	 * conocido.
	 *
	 * @return areaCaladero[i] El valor del atributo areaCaladero[i].
	 */
	public static double obtenerAreaCaladero(int i)
	{
		return areaCaladero[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo abundanciaCaladero con
	 * un �ndice conocido.
	 *
	 * @return abundanciaCaladero[i] El valor del atributo abundanciaCaladero[i].
	 */
	public static long obtenerAbundanciaCaladero(int i)
	{
		return abundanciaCaladero[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo biomasaCaladero con un �ndice
	 * conocido.
	 *
	 * @return biomasaCaladero[i] El valor del atributo biomasaCaladero[i].
	 */
	public static double obtenerBiomasaCaladero(int i)
	{
		return biomasaCaladero[i];
	}
}