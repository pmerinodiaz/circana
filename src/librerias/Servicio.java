/**
 * @(#)Servicio.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.util.Calendar;
import java.util.Random;
import java.util.Locale;
import java.math.BigDecimal;
import java.text.NumberFormat;

/** 
 * Clase que contiene solamente métodos estáticos que sirven para ofrecer
 * diversos servicios a la aplicación.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @author Paul Leger
 * @author Héctor Díaz
 * @see Calendar
 * @see Random
 * @see Locale
 * @see Math
 * @see BigDecimal
 * @see NumberFormat
 * @see Integer
 * @see String
 */
public class Servicio
{
	/* El valor para indicar que queremos trabajar con un número. */
	public static final int NUMERO = 0;
	
	/* El valor para indicar que queremos trabajar con un porcentaje. */
	public static final int PORCENTAJE = 1;
	
	/** El valor del radio terrestre (kilómetros) en la zona ecuatorial. */
	private static final double RADIO_ECUATORIAL = 6378.4;
	
	/** El valor del radio terrestre (kilómetros) en la zona polar. */
	private static final double RADIO_POLAR = 6356.4;
	
	/** Objeto que entrega los valores que son aleatorios. */
	private static final Random azar = new Random(System.currentTimeMillis());
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * minutos a grados. La transformación consiste en convertir los minutos a
	 * grados y luego se devuelve este valor.
	 *
	 * @param minutos Un valor expresado en minutos.
	 *
	 * @return grados Los minutos expresados en grados.
	 */
	public static double obtenerGrados(double minutos)
	{
		return minutos / 60;
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * un grado, que contiene grados y minutos, a solamente grados. La
	 * transformación consiste en convertir los minutos a grados y luego
	 * sumarlos a los grados. Luego, se devuelve este valor.
	 *
	 * @param grado Un valor expresado en grados y minutos.
	 *
	 * @return grados Los grados y minutos expresados solamenten en grados.
	 */
	public static double obtenerGrados(Grado grado)
	{
		return grado.obtenerGrado() + obtenerGrados(grado.obtenerMinuto());
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * grados a minutos. La transformación consiste en convertir los grados a
	 * minutos y luego se devuelve este valor.
	 *
	 * @param grados Un valor expresado en grados.
	 *
	 * @return minutos Los grados expresados en minutos.
	 */
	public static double obtenerMinutos(double grados)
	{
		return grados * 60;
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * un grado, que contiene grados y minutos, a solamente minutos. La
	 * transformación consiste en convertir los grados a minutos y luego
	 * sumarlos a los minutos. Luego, se devuelve este valor.
	 *
	 * @param grado Un valor expresado en grados y minutos.
	 *
	 * @return minutos Los grados y minutos expresados solamenten en minutos.
	 */
	public static double obtenerMinutos(Grado grado)
	{
		return obtenerMinutos(grado.obtenerGrado()) + grado.obtenerMinuto();
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * años a días. La transformación consiste en convertir los años a días y
	 * luego se devuelve este valor.
	 *
	 * @param anio Un valor expresado en años.
	 *
	 * @return dia Los años expresados en días.
	 */
	public static double transformarAnioDia(double anio)
	{
		return anio * 365;
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * meses a días. La transformación consiste en convertir los meses a días y
	 * luego se devuelve este valor.
	 *
	 * @param mes Un valor esxpresado en meses.
	 *
	 * @return dia Los meses expresados en días.
	 */
	public static double transformarMesDia(double mes)
	{
		return mes * (365.0 / 12.0);
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * días a horas. La transformación consiste en convertir los días a horas y
	 * luego se devuelve este valor.
	 *
	 * @param dia Un valor expresado en días.
	 *
	 * @return hora Los días expresados en horas.
	 */
	public static double transformarDiaHora(double dia)
	{
		return dia * 24;
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * horas a minutos. La transformación consiste en convertir las horas a
	 * minutos y luego se devuelve este valor.
	 *
	 * @param hora Un valor expresado en horas.
	 *
	 * @return minuto Las horas expresadas en minutos.
	 */
	public static double transformarHoraMinuto(double hora)
	{
		return hora * 60;
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * minutos a segundos. La transformación consiste en convertir los minutos a
	 * segundos y luego se devuelve este valor.
	 *
	 * @param minuto Un valor expresado en minutos.
	 *
	 * @return segundo Los minutos expresados en segundos.
	 */
	public static double transformarMinutoSegundo(double minuto)
	{
		return minuto * 60;
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * años a horas. La transformación consiste en convertir los años a días,
	 * luego los días a horas y luego se devuelve este valor.
	 *
	 * @param anio Un valor expresado en años.
	 *
	 * @return hora Los años expresados en horas.
	 */
	public static double transformarAnioHora(double anio)
	{
		return transformarDiaHora(transformarAnioDia(anio));
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * años a minutos. La transformación consiste en convertir los años a días,
	 * luego los días a horas, luego las horas a minutos y luego se devuelve
	 * este valor.
	 *
	 * @param anio Un valor expresado en años.
	 *
	 * @return minuto Los años expresados en minutos.
	 */
	public static double transformarAnioMinuto(double anio)
	{
		return transformarHoraMinuto(transformarAnioHora(anio));
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * años a segundos. La transformación consiste en convertir los años a días,
	 * luego los días a horas, luego las horas a minutos, luego los minutos a
	 * segundos y luego se devuelve este valor.
	 *
	 * @param anio Un valor expresado en años.
	 *
	 * @return segundo Los años expresados en segundos.
	 */
	public static double transformarAnioSegundo(double anio)
	{
		return transformarMinutoSegundo(transformarAnioMinuto(anio));
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * días a minutos. La transformación consiste en convertir los días a horas,
	 * luego convertir las horas a minutos y luego se devuelve este valor.
	 *
	 * @param dia Un valor expresado en días.
	 *
	 * @return minuto Los días expresados en minutos.
	 */
	public static double transformarDiaMinuto(double dia)
	{
		return transformarHoraMinuto(transformarDiaHora(dia));
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * días a segundos. La transformación consiste en convertir los días a
	 * horas, luego convertir las horas a minutos, después convertir los minutos
	 * a segundos y luego se devuelve este valor.
	 *
	 * @param dia Un valor expresado en días.
	 *
	 * @return segundo Los días expresados en segundos.
	 */
	public static double transformarDiaSegundo(double dia)
	{
		return transformarMinutoSegundo(transformarDiaMinuto(dia));
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * horas a segundos. La transformación consiste en convertir las horas a
	 * minutos, luego convertir los minutos a segundos y luego se devuelve este
	 * valor.
	 *
	 * @param hora Un valor expresado en horas.
	 *
	 * @return segundo Las horas expresadas en segundos.
	 */
	public static double transformarHoraSegundo(double hora)
	{
		return transformarMinutoSegundo(transformarHoraMinuto(hora));
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * segundos a minutos. La transformación consiste en convertir los segundos
	 * a minutos y luego se devuelve este valor.
	 *
	 * @param segundo Un valor expresado en segundos.
	 *
	 * @return minuto Los segundos expresados en minutos.
	 */
	public static double transformarSegundoMinuto(double segundo)
	{
		return segundo / 60;
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * minutos a horas. La transformación consiste en convertir los minutos a
	 * horas y luego se devuelve este valor.
	 *
	 * @param minuto Un valor expresado en minutos.
	 *
	 * @return hora Los minutos expresados en horas.
	 */
	public static double transformarMinutoHora(double minuto)
	{
		return minuto / 60;
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * horas a días. La transformación consiste en convertir las horas a días y
	 * luego se devuelve este valor.
	 *
	 * @param hora Un valor expresado en horas.
	 *
	 * @return dia Las horas expresadas en días.
	 */
	public static double transformarHoraDia(double hora)
	{
		return hora / 24;
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * días a años. La transformación consiste en convertir los días a años y
	 * luego se devuelve este valor.
	 *
	 * @param dia Un valor expresado en días.
	 *
	 * @return anio Los días expresados en años.
	 */
	public static double transformarDiaAnio(double dia)
	{
		return dia / 365;
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * segundos a horas. La transformación consiste en convertir los segundos
	 * a minutos, luegos los minutos a horas y luego se devuelve este valor.
	 *
	 * @param segundo Un valor expresado en segundos.
	 *
	 * @return hora Los segundos expresados en horas.
	 */
	public static double transformarSegundoHora(double segundo)
	{
		return transformarMinutoHora(transformarSegundoMinuto(segundo));
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * segundos a días. La transformación consiste en convertir los segundos
	 * a minutos, luegos los minutos a horas, luego las horas a días y luego se
	 * devuelve este valor.
	 *
	 * @param segundo Un valor expresado en segundos.
	 *
	 * @return dia Los segundos expresados en días.
	 */
	public static double transformarSegundoDia(double segundo)
	{
		return transformarHoraDia(transformarSegundoHora(segundo));
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * segundos a años. La transformación consiste en convertir los segundos
	 * a minutos, luegos los minutos a horas, luego las horas a días, luego los
	 * días a años y luego se devuelve este valor.
	 *
	 * @param segundo Un valor expresado en segundos.
	 *
	 * @return anio Los segundos expresados en años.
	 */
	public static double transformarSegundoAnio(double segundo)
	{
		return transformarDiaAnio(transformarSegundoDia(segundo));
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * minutos a días. La transformación consiste en convertir los minutos a
	 * horas, luegos las horas a días y luego se devuelve este valor.
	 *
	 * @param minuto Un valor expresado en minutos.
	 *
	 * @return dia Los minutos expresados en días.
	 */
	public static double transformarMinutoDia(double minuto)
	{
		return transformarHoraDia(transformarMinutoHora(minuto));
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * minutos a años. La transformación consiste en convertir los minutos a
	 * horas, luegos las horas a días, luego los días a años y luego se devuelve
	 * este valor.
	 *
	 * @param minuto Un valor expresado en minutos.
	 *
	 * @return anio Los minutos expresados en años.
	 */
	public static double transformarMinutoAnio(double minuto)
	{
		return transformarDiaAnio(transformarMinutoDia(minuto));
	}
	
	/**
	 * Método transformador de unidades de tiempo. Realiza la transformación de
	 * horas a años. La transformación consiste en convertir las horas a días,
	 * luegos los días a años y luego se devuelve este valor.
	 *
	 * @param hora Un valor expresado en horas.
	 *
	 * @return anio Las horas expresadas en años.
	 */
	public static double transformarHoraAnio(double hora)
	{
		return transformarDiaAnio(transformarHoraDia(hora));
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * grados de longitud a kilómetros. La transformación consiste en convertir
	 * los grados de longitud a kilómetros y luego se devuelve este valor.
	 *
	 * @param grado Un valor expresado en grados de longitud.
	 *
	 * @return kilometro Los grados en longitud expresados en kilómetros.
	 */
	public static double transformarGradoLongitudKilometro(double grado)
	{
		return (grado * RADIO_POLAR) / 90;
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * kilómetros a grados de longitud. La transformación consiste en convertir
	 * los kilómetros a grados de longitud y luego se devuelve este valor.
	 *
	 * @param kilometro Un valor expresado en kilometros.
	 *
	 * @return grado Los kilómetros expresados en grados de longitud.
	 */
	public static double transformarKilometroGradoLongitud(double kilometro)
	{
		return (kilometro * 90) / RADIO_POLAR;
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * grados de latitud a kilómetros. La transformación consiste en convertir
	 * los grados de latitud a kilómetros y luego se devuelve este valor.
	 *
	 * @param grado Un valor expresado en grados de latitud.
	 *
	 * @return kilometro Los grados de latitud expresados en kilómetros.
	 */
	public static double transformarGradoLatitudKilometro(double grado)
	{
		return (grado * RADIO_ECUATORIAL) / 90;
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * kilómetros a grados de latitud. La transformación consiste en convertir
	 * los kilómetros a grados de latitud y luego se devuelve este valor.
	 *
	 * @param kilometro Un valor expresado en kilometros.
	 *
	 * @return grado Los kilómetros expresados en grados de latitud.
	 */
	public static double transformarKilometroGradoLatitud(double kilometro)
	{
		return (kilometro * 90) / RADIO_ECUATORIAL;
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * kilómetros a grados. La transformación consiste en convertir los
	 * kilómetros a grados de longitud, luego convertir los kilómetros a grados
	 * en latitud. Finalmente calcular el promedio de estos grados y luego se
	 * devuelve este valor.
	 *
	 * @param kilometro Un valor expresado en kilometros.
	 *
	 * @return grado Los kilómetros expresados en grados.
	 */
	public static double transformarKilometroGrado(double kilometro)
	{
		return (transformarKilometroGradoLongitud(kilometro) +
				transformarKilometroGradoLatitud(kilometro)) / 2;
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * kilómetros a metros. La transformación consiste en convertir los
	 * kilómetros a metros y luego se devuelve este valor.
	 *
	 * @param kilometro Un valor expresado en kilometros.
	 *
	 * @return metro Los kilómetros expresados en metros.
	 */
	public static double transformarKilometroMetro(double kilometro)
	{
		return kilometro * 1000;
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * centímetros a milímetros. La transformación consiste en convertir los
	 * centímetros a milímetros y luego se devuelve este valor.
	 *
	 * @param centimetro Un valor expresado en centímetros.
	 *
	 * @return milimetro Los centímetros expresados en milímetros.
	 */
	public static double transformarCentimetroMilimetro(double centimetro)
	{
		return centimetro * 10;
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * metros a kilómetros. La transformación consiste en convertir los metros
	 * a kilómetros y luego se devuelve este valor.
	 *
	 * @param metro Un valor expresado en metros.
	 *
	 * @return kilometro Los metros expresados en kilómetros.
	 */
	public static double transformarMetroKilometro(double metro)
	{
		return metro / 1000;
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * milímetros a centímetros. La transformación consiste en convertir los
	 * milímetros a centímetros y luego se devuelve este valor.
	 *
	 * @param milimetro Un valor expresado en milímetros.
	 *
	 * @return centimetro Los milímetros expresados en centímetros.
	 */
	public static double transformarMilimetroCentimetro(double milimetro)
	{
		return milimetro / 10;
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * millas náuticas a metros. La transformación consiste en convertir las
	 * millas náuticas a metros y luego se devuelve este valor.
	 *
	 * @param millaNautica Un valor expresado en millas náuticas.
	 *
	 * @return metro Las millas náuticas expresadas en metros.
	 */
	public static double transformarMillaNauticaMetro(double millaNautica)
	{
		return millaNautica * 1853.25;
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * millas náuticas a kilómetros. La transformación consiste en convertir las
	 * millas náuticas a metros, luegos los metros a kilómetros y luego se
	 * devuelve este valor.
	 *
	 * @param millaNautica Un valor expresado en millas náuticas.
	 *
	 * @return kilómetro Las millas náuticas expresadas en kilómetros.
	 */
	public static double transformarMillaNauticaKilometro(double millaNautica)
	{
		return transformarMetroKilometro(
			   transformarMillaNauticaMetro(millaNautica));
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * grados de longitud a unidades de celda. La transformación consiste en
	 * convertir los grados de longitud a las unidades de celdas usadas en la
	 * transformación del modelo y devolver este valor.
	 *
	 * @param longitud Un valor expresado en grados de longitud.
	 * @para transformacion La transformación de modelo usada.
	 *
	 * @return celda Los grados de longitud expresados en unidades de celda.
	 */
	public static int transformarGradoLongitudCelda(double longitud,
											TransformacionModelo transformacion)
	{
		// Obtener iDel y xDel de la transformación.
		int iDel = transformacion.obtenerIDel();
		double xDel = transformacion.obtenerXDel();
		
		return (int) Math.abs(iDel * longitud / xDel);
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * grados de latitud a unidades de celda. La transformación consiste en
	 * convertir los grados de latitud a las unidades de celdas usadas en la
	 * transformación del modelo y devolver este valor.
	 *
	 * @param latitud Un valor expresado en grados de latitud.
	 * @para transformacion La transformación de modelo usada.
	 *
	 * @return celda Los grados de latitud expresados en unidades de celda.
	 */
	public static int transformarGradoLatitudCelda(double latitud,
											TransformacionModelo transformacion)
	{
		// Obtener jDel y yDel de la transformación.
		int jDel = transformacion.obtenerJDel();
		double yDel = transformacion.obtenerYDel();
		
		return (int) Math.abs(jDel * latitud / yDel);
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * metros de altitud a unidades de celda. La transformación consiste en
	 * convertir los metros de altitud a las unidades de celdas usadas en la
	 * transformación del modelo y devolver este valor.
	 *
	 * @param altitud Un valor expresado en metros de altitud.
	 * @para transformacion La transformación de modelo usada.
	 *
	 * @return celda Los metros de altitud expresados en unidades de celda.
	 */
	public static int transformarMetroAltitudCelda(double altitud,
											TransformacionModelo transformacion)
	{
		// Obtener jDel y yDel de la transformación.
		int kDel = transformacion.obtenerKDel();
		double zDel = transformacion.obtenerZDel();
		
		return (int) Math.abs(kDel * altitud / zDel);
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * metros a unidades de celda. La transformación consiste en convertir los
	 * metros a las unidades de celdas usadas en la transformación del modelo,
	 * es decir, grados de longitud y latitud, y metros son transformados a
	 * unidades de celda. Se devuelve el promedio entre las celdas en longitud,
	 * latitud y altitud.
	 *
	 * @param metro Un valor expresado en metros.
	 * @para transformacion La transformación de modelo usada.
	 *
	 * @return celda Los metros expresados en unidades de celda.
	 */
	public static int transformarMetroCelda(double metro,
											TransformacionModelo transformacion)
	{
		// Transformar los metros a kilómetros.
		double kilometro = transformarMetroKilometro(metro);
		
		// Transformar los kilómetros a longitud, latitud y altitud.
		double longitud = transformarKilometroGradoLongitud(kilometro);
		double latitud = transformarKilometroGradoLatitud(kilometro);
		double altitud = metro;
		
		// Transformar a unidades de celda.
		int celdaI = transformarGradoLongitudCelda(longitud, transformacion);
		int celdaJ = transformarGradoLatitudCelda(latitud, transformacion);
		int celdaK = transformarMetroAltitudCelda(altitud, transformacion);
		
		// Devolver el promedio.
		return (celdaI + celdaJ + celdaK) / 3;
	}
	
	/**
	 * Método transformador de unidades de medida. Realiza la transformación de
	 * una celda a una coordenada. La transformación consiste en convertir
	 * las unidades de celdas usadas en la transformación del modelo a
	 * longitudes, latitudes y altitudes. Se devuelve una nueva coordenada con
	 * la longitud, latitud y altitud que corresponden a la transformación de
	 * la celda.
	 *
	 * @param celda Una celda.
	 * @para transformacion La transformación de modelo usada.
	 *
	 * @return coordenada La nueva coordenada que corresponde a la celda.
	 */
	public static Coordenada transformarCeldaCoordenada(Celda celda,
											TransformacionModelo transformacion)
	{
		double longitud = transformacion.obtenerX(celda.obtenerI());
		double latitud = transformacion.obtenerY(celda.obtenerJ());
		double altitud = transformacion.obtenerZ(celda.obtenerK());
		
		return new Coordenada(longitud, latitud, altitud);
	}
	
	/**
	 * Método transformador de unidades de masa. Realiza la transformación de
	 * toneladas a kilógramos. La transformación consiste en convertir las
	 * toneladas a kilógramos y luego se devuelve este valor.
	 *
	 * @param tonelada Un valor expresado en toneladas.
	 *
	 * @return kilogramos Las toneladas expresadas en kilógramos.
	 */
	public static double transformarToneladaKilogramo(double tonelada)
	{
		return tonelada * 1000;
	}
	
	/**
	 * Método transformador de unidades de masa. Realiza la transformación de
	 * kilógramos a gramos. La transformación consiste en convertir los
	 * kilógramos a gramos y luego se devuelve este valor.
	 *
	 * @param kilogramos Un valor expresado en kilogramos.
	 *
	 * @return gramos Los kilógramos expresados en gramos.
	 */
	public static double transformarKilogramoGramo(double kilogramo)
	{
		return kilogramo * 1000;
	}
	
	/**
	 * Método transformador de unidades de masa. Realiza la transformación de
	 * toneladas a gramos. La transformación consiste en convertir las
	 * toneladas a kilógramos, luego convertir los kilógramos a gramos y luego
	 * se devuelve este valor.
	 *
	 * @param tonelada Un valor expresado en toneladas.
	 *
	 * @return gramos Las toneladas expresadas en gramos.
	 */
	public static double transformarToneladaGramo(double tonelada)
	{
		return transformarKilogramoGramo(
			   transformarToneladaKilogramo(tonelada));
	}
	
	/**
	 * Método transformador de unidades de masa. Realiza la transformación de
	 * gramos a kilógramos. La transformación consiste en convertir los gramos a
	 * kilógramos y luego se devuelve este valor.
	 *
	 * @param gramo Un valor expresado en gramos.
	 *
	 * @return kilogramos Los gramos expresados en kilógramos.
	 */
	public static double transformarGramoKilogramo(double gramo)
	{
		return gramo / 1000;
	}
	
	/**
	 * Método transformador de unidades de masa. Realiza la transformación de
	 * kilógramos a toneladas. La transformación consiste en convertir los
	 * kilógramos a toneladas y luego se devuelve este valor.
	 *
	 * @param kilogramo Un valor expresado en kilógramos.
	 *
	 * @return toneladas Los kilográmos expresados en toneladas.
	 */
	public static double transformarKilogramoTonelada(double kilogramo)
	{
		return kilogramo / 1000;
	}
	
	/**
	 * Método transformador de unidades de masa. Realiza la transformación de
	 * gramos a toneladas. La transformación consiste en convertir los gramos a
	 * kilógramos, luego convertir los kilógramos a toneladas y luego se
	 * devuelve este valor.
	 *
	 * @param gramo Un valor expresado en gramos.
	 *
	 * @return toneladas Los gramos expresados en toneladas.
	 */
	public static double transformarGramoTonelada(double gramo)
	{
		return transformarKilogramoTonelada(
			   transformarGramoKilogramo(gramo));
	}
	
	/**
	 * Método que obtiene el grado que tiene un número expresado en punto
	 * flotante. Al número se le calculan sus grados y minutos. Luego, en caso
	 * de que los minutos obtenidos sean mayores o iguales que sesenta, entonces
	 * se procede a incrementar los grados en uno y a decrementar los minutos en
	 * sesenta, hasta que los minutos sean menores a sesenta.
	 *
	 * @param número Un valor expresado en grados.
	 *
	 * @return grado El grado del número.
	 */
	public static int obtenerGrado(double grados)
	{
		int grado = (int) grados;
		int resto = (int) ((grados * 60.0) % 60);
		
		while (resto >= 60)
		{
			grado++;
			resto -= 60;
		}
		
		return grado;
	}
	
	/**
	 * Método que obtiene el minuto que tiene un número punto flotante. Al
	 * número se le calculan sus grados y minutos. Luego, en caso de que los
	 * minutos obtenidos sean mayores o iguales que sesenta, entonces se procede
	 * a incrementar los grados en uno y a decrementar los minutos en sesenta,
	 * hasta que los minutos sean menores a sesenta.
	 *
	 * @param numero Un valor expresado en grados.
	 *
	 * @return minuto El minuto del número.
	 */
	public static int obtenerMinuto(double grados)
	{
		int resto = (int) grados;
		int minuto = (int) ((grados * 60.0) % 60);
		
		while (minuto >= 60)
		{
			resto++;
			minuto -= 60;
		}
		
		return minuto;
	}
	
	/**
	 * Método que calcula los minutos que hay entre dos grados. El cálculo es
	 * determinado de la siguiente manera: primeramente los grados inicial y
	 * final se transforman a minutos, luego se restan los minutos, y
	 * finalmente se devuelve este valor. Los grados parámetros del método son
	 * expresados en grados y minutos.
	 *
	 * @param gradosInicial El valor inicial valor expresado en grados y
	 *                      minutos.
	 * @param gradosFinal El valor final valor expresado en grados y minutos.
	 *
	 * @return minutos Los minutos entre el grado inicial y final.
	 */
	public static int obtenerMinutos(Grado gradosInicial, Grado gradosFinal)
	{
		int min1 = (int) obtenerMinutos(gradosInicial);
		int min2 = (int) obtenerMinutos(gradosFinal);
		
		return (min2 >= min1) ? Math.abs(min2 - min1) : -1;
	}
	
	/**
	 * Método que calcula los minutos que hay entre dos grados. El cálculo es
	 * determinado de la siguiente manera: primeramente los grados inicial y
	 * final se transforman a minutos, luego se restan los minutos, y
	 * finalmente se devuelve este valor. Los grados parámetros del método son
	 * expresados en grados como valores ´numéricos punto flotante.
	 *
	 * @param gradosInicial El valor inicial valor expresado en grados.
	 * @param gradosFinal El valor final valor expresado en grados.
	 *
	 * @return minutos Los minutos entre el grado inicial y final.
	 */
	public static int obtenerMinutos(double gradosInicial, double gradosFinal)
	{
		return (int) Math.abs(obtenerMinutos(gradosFinal - gradosInicial));
	}
	
	/**
	 * Método que decrementa en ciertos minutos un grado recibido como
	 * parámetro.
	 *
	 * @param grado El valor del grado que se quiere decrementar.
	 * @param decremento El valor del decremento en minutos.
	 *
	 * @return grado El nuevo valor del grado decrementado.
	 */
	public static Grado decrementarMinutos(Grado grado, int decremento)
	{
		int grados = grado.obtenerGrado();
		int minutos = grado.obtenerMinuto();
		
		if (minutos == 0)
		{
			minutos = 60 - decremento;
			grados-=1;
		}
		else minutos-=decremento;
		
		return new Grado(grados, minutos);
	}
	
	/**
	 * Método que incrementa en ciertos minutos un grado recibido como
	 * parámetro.
	 *
	 * @param grado El valor del grado que se quiere incrementar.
	 * @param incremento El valor del incremento en minutos.
	 *
	 * @return grado El nuevo valor del grado incrementado
	 */
	public static Grado incrementarMinutos(Grado grado, int incremento)
	{
		int grados = grado.obtenerGrado();
		int minutos = grado.obtenerMinuto();
		
		if (minutos == (60 - incremento))
		{
			minutos = 0;
			grados += 1;
		}
		else minutos+=incremento;
		
		return new Grado(grados, minutos);
	}
	
	/**
	 * Método que calcula la distancia existente entre dos medidas. La
	 * distancia se calcula usando la siguiente fórmula:
	 * distancia = |medida_final - medida_inicial|
	 *
	 * @param medidaInicial La medida inicial.
	 * @param medidaFinal La medida final.
	 *
	 * @return distancia La distancia entre las medidas.
	 */
	public static double obtenerDistancia(double medidaInicial,
	                                      double medidaFinal)
	{
		return Math.abs(medidaFinal - medidaInicial);
	}
	
	/**
	 * Método que obtiene la distancia de forma pitagórica entre dos coordenadas
	 * de celda. La distancia se calcula usando la siguiente fórmula:
	 * distancia = ((x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2)^(1/2)
	 *
	 * @param celda1 La celda inicial.
	 * @param celda2 La celda final.
	 *
	 * @return distancia La distancia pitagórica entre las celdas.
	 */
	public static double obtenerDistancia(Celda celda1, Celda celda2)
	{
		return Math.sqrt(
			Math.pow(celda2.obtenerI() - celda1.obtenerI(), 2)+
			Math.pow(celda2.obtenerJ() - celda1.obtenerJ(), 2)+
			Math.pow(celda2.obtenerK() - celda1.obtenerK(), 2));
	}
	
	/**
	 * Método que obtiene la distancia entre dos coordenadas. La distancia que
	 * se devuelve es la distancia pitagórica entre estas dos coordenadas. La
	 * distancia que se devuelve es expresada en kilómetros.
	 *
	 * @param coordenada1 La coordenada inicial.
	 * @param coordenada2 La coordenada final.
	 *
	 * @return distancia La distancia pitagórica entre las coordenadas.
	 */
	public static double obtenerDistancia(Coordenada coordenada1,
										  Coordenada coordenada2)
	{
		return Math.sqrt(
			transformarGradoLongitudKilometro(
				Math.pow(coordenada2.obtenerLongitud() -
						 coordenada1.obtenerLongitud(), 2)) +
			transformarGradoLatitudKilometro(
				Math.pow(coordenada2.obtenerLatitud() -
						 coordenada1.obtenerLatitud(), 2)) +
				Math.pow(coordenada2.obtenerAltitud() -
						 coordenada1.obtenerAltitud(), 2));
	}
	
	/**
	 * Método que calcula el perímetro de una circunferencia con radio conocido.
	 * El perímetro se calcula usando la siguiente fórmula:
	 * perímetro = 2 * PI * radio
	 *
	 * @param radio El radio de la circunferencia.
	 *
	 * @return perimetro El perímetro de la circunferencia.
	 */
	public static double obtenerPerimetroCircunferencia(double radio)
	{
		return Math.abs(2 * Math.PI * radio);
	}
	
	/**
	 * Método que obtiene el área superficial de una circunferencia con radio
	 * especificado como parámetro. El área se calcula usando la siguiente
	 * fórmula: área = PI * radio * radio
	 *
	 * @param radio El radio de la circunferencia.
	 *
	 * @return area El área superficial de la circunferencia.
	 */
	public static double obtenerAreaCircunferencia(double radio)
	{
		return Math.abs(Math.PI * Math.pow(radio, 2));
	}
	
	/**
	 * Método que obtiene el área de la Tierra a una cierta latitud. Se toma
	 * la porción del radio de la Tierra que corresponde a la latitud
	 * especificada como parámetro. Se asume que el área superficial de la
	 * Tierra se calcula como el área superficial de una circunferencia
	 * cualquiera.
	 *
	 * @param latitud La latitud en donde se obtiene el área terrestre.
	 *
	 * @return area El área terrestre (kilómetros^2) en la latitud especificada.
	 */
	public static double obtenerAreaTerrestre(double latitud)
	{
		// Calcular el radio.
		double radio = ((90 - latitud) * RADIO_ECUATORIAL) / 90;
		
		return obtenerAreaCircunferencia(radio);
	}
	
	/**
	 * Método que obtiene el área superficial (kilómetros cuadrados) terrestre
	 * entre dos longitudes y entre dos latitudes especificadas como parámetros.
	 * Primero se obtienen las áreas superficiales con la latitud inicial y
	 * final. Luego se restan las áreas y se multiplican por la porción.
	 *
	 * @param longitudInicial La longitud inicial expresada en grados.
	 * @param longitudFinal La longitud final expresada en grados.
	 * @param latitudInicial La latitud inicial expresada en grados.
	 * @param latitudFinal La latitud final expresada en grados.
	 *
	 * @return area El área superficial (kilómetros^2) entre las longitudes y
	 *              latitudes especificadas.
	 */
	public static double obtenerArea(double longitudInicial,
									 double longitudFinal,
									 double latitudInicial,
									 double latitudFinal)
	{
		double areaInicial = obtenerAreaTerrestre(latitudInicial);
		double areaFinal = obtenerAreaTerrestre(latitudFinal);
		
		return
			Math.abs((areaInicial-areaFinal) *
					 (longitudFinal-longitudInicial) / 180);
	}
	
	/**
	 * Método que obtiene el área superficial (kilómetros cuadrados) terrestre
	 * de un número de celdas especificadas como parámetros. Primero se obtiene
	 * la transformación usada en el mapa, luego se obtienen las longitudes y
	 * latitudes que representan los límites de las celdas. luego se calcula el
	 * área superficial entre estos límites y se devuelve este valor.
	 *
	 * @param numeroCeldas El número de celdas a las cuales quiere obtenerse
	 *                     su área superficial.
	 * @param transformacion La transformación de modelo usada.
	 *
	 * @return area El área superficial (kilómetros^2) del número de celdas
	 *              especificadas.
	 */
	public static double obtenerArea(int numeroCeldas,
									 TransformacionModelo transformacion)
	{
		// Obtener las longitudes y latitudes.
		double longitudInicial = transformacion.obtenerX(0);
		double longitudFinal = transformacion.obtenerX(numeroCeldas);
		double latitudInicial = transformacion.obtenerY(0);
		double latitudFinal = transformacion.obtenerY(numeroCeldas);
		
		return Servicio.obtenerArea(longitudInicial, longitudFinal,
									latitudInicial, latitudFinal);
	}
	
	/**
     * Método que dado un día del año devuelve el mes del año mas su
     * aproximación decimal con respecto al siguiente mes. Ej: 1.2; 1.9; 12.5
     * (mitad Diciembre).
     * 
     * @param dia Día del año.
     *
     * @return mes Mes del año con su aproximación decimal al mes siguiente.
     */
	public static double obtenerMes(int dia)
	{
		double mes = 0;
		
	 	// 31 Enero.
	 	if (dia > 0 && dia <= 31) 
	 		mes = 1 + dia / 31.0;
	 	
	 	// 28 Febrero.
	 	else
	 	if (dia > 31 && dia <= 59) 
	 		mes = 2 + (dia - 31) / 28.0;
 		
	 	// 31 Marzo.
	 	else
	 	if (dia > 59 && dia <= 90) 
	 		mes = 3 + (dia - 59) / 31.0;
	 	
	 	// 30 Abril.
	 	else
	 	if (dia > 90 && dia <= 130) 
	 		mes = 4 + (dia - 90) / 30.0;
	 		
	 	// 31 Mayo.
	 	else
	 	if (dia > 120 && dia <= 151) 
	 		mes = 5 + (dia - 120) / 31.0;
	 	
	 	// 30 Junio.
	 	else
	 	if (dia > 151 && dia <= 181)
	 		mes = 6 +(dia - 151) / 30.0;
	 	
	 	// 31 Julio.
	  	else
	  	if (dia > 181 && dia <= 212) 
	 		mes = 7 + (dia - 181) / 31.0;
	 	
	 	// 31 Agosto.
	  	else
	  	if (dia > 212 && dia <= 243) 
	 		mes = 8 + (dia - 212) / 31.0;
		
	 	// 30 Septiembre.
	 	else
	 	if (dia > 243 && dia <= 273) 
	 		mes = 9 + (dia - 243) / 30.0;
	 	
	 	// 31 Octubre.
	  	else
	  	if (dia > 273 && dia <= 304)
	 		mes = 10 + (dia - 273) / 31.0;
	 	
	 	// 30 Noviembre.
	  	else
	  	if (dia > 304 && dia <= 334) 
	 		mes = 11 + (dia - 304) / 30.0;
	 	
	 	// 31 Diciembre.
	  	else
	  	if (dia > 334 && dia <= 365) 
	 		mes = 12 + (dia - 334) / 31.0;
	 	
	 	return mes;
	}
	
	/**
     * Método que dado un mes del año devuelve el nombre del mes del año. Se
     * crea un arreglo de string con los nombres del año y luego se devuelve el
     * nombre del mes que corresponde al parámetro recibido. Se hace notar que
     * los meses son valores entre uno y doce.
     * 
     * @param mes Un mes del año.
     *
     * @return nombre El nombre del mes en formato string.
     */
	public static String obtenerNombreMes(int mes)
	{
		// Los nombres de los meses.
	 	String[] nombre = {"Enero",
	 					   "Febrero",
	 					   "Marzo",
	 					   "Abril",
	 					   "Mayo",
	 					   "Junio",
	 					   "Julio",
	 					   "Agosto",
	 					   "Septiembre",
	 					   "Octubre",
	 					   "Noviembre",
	 					   "Diciembre"};
	 	
	 	// Devolver el mes.
	 	return nombre[mes - 1];
	}
	
	/**
	 * Metodo que traspasa un día del mes a un día del año.
	 *
	 * @param dia Un día del mes.
	 * @param mes Un mes del año.
	 *
	 * @return dia Un día del año.
	 */
	public static int obtenerDia(int dia, int mes)
	{
		// Crear el arreglo de 2 dimensiones.
		int[] diaMes = new int[2];
		
		// Asignar valor al arreglo.
		diaMes[0] = dia;
		diaMes[1] = mes;
		
		return obtenerDia(diaMes);
	}
	
	/**
	 * Metodo que traspasa arreglo de 2 dimensiones (día del mes y mes) a un día
	 * del año. Formato del arreglo es (0: día, 1: mes)	
	 *
	 * @param mesDia El arreglo que contiene el día del mes y mes. Formato del
	 *				 arreglo es (0: día, 1: mes).
	 *
	 * @return dia Un día del año.
	 */
	public static int obtenerDia(int[] diaMes)
	{
		int i;
		int dia = 0;
		
		for (i = 1; i <= diaMes[1] - 1; i++)
	 	{
	 		if (i <= 7)
	 		{
	 			if (i == 2)
	 				dia += 28;
	 			
            	if (i != 2 && i%2 == 0)
            		dia += 30;
            	
            	if (i%2 != 0)
            		dia += 31;
            }
            else
            {
            	if (i%2 != 0)
            		dia += 30;
            	
            	if (i%2 == 0)
            		dia += 31;
           	}
		}
		
		dia += diaMes[0];
		
		return dia;
	}
	
	/**
	 * Método que obtiene la cantidad de días que tiene un mes del año. Este
	 * método no considera el año bisiesto.
	 *
	 * @param mes Un mes del año.
	 *
	 * @return cantidadDias La cantidad de días que tiene el mes.
	 */
	public static int obtenerCantidadDias(int mes)
	{
		// Las cantidades de días de los meses.
	 	int[] cantidadDias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	 	
	 	// Devolver la cantidad de días el mes.
	 	return cantidadDias[mes - 1];
	}
	
	/**
	 * Método que calcula los días que hay entre dos fechas. El cálculo es
	 * determinado de la siguiente manera: primeramente la fecha inicial y final
	 * se transforman a días, luego se restan los días, y finalmente se devuelve
	 * este valor. Las fechas parámetros del método son expresados en formato
	 * dd-mm-aa (0: día, 1: mes, 2: año).
	 *
	 * @param fechaInicial El valor inicial valor expresado en grados y
	 *                      minutos.
	 * @param fechaFinal El valor final valor expresado en grados y minutos.
	 *
	 * @return dias Los días entre la fecha inicial y final.
	 */
	public static int obtenerDias(int[] fechaInicial, int[] fechaFinal)
	{
		double dias1 = fechaInicial[0] +
					   transformarMesDia(fechaInicial[1]) +
					   transformarAnioDia(fechaInicial[2]);
		double dias2 = fechaFinal[0] +
					   transformarMesDia(fechaFinal[1]) +
					   transformarAnioDia(fechaFinal[2]);
		
		return (dias2 >= dias1) ? (int) Math.abs(dias2 - dias1) : -1;
	}
	
	/**
	 * Entrega el mes y día correspondiente al día especificado. Devuelve -1
	 * si el día esta fuera de los rangos.
	 * 
	 * @param dia Un día del año.
	 *
	 * @return mesDia Un mes del año y un día del mes entregado antes.
	 */
	public static int[] obtenerMesDia(int dia)
	{
		int[] mesDia = new int[2];
		
		mesDia[0] = mesDia[1] = -1;
	 	
	 	// 31 Enero.
	 	if (dia > 0 && dia <= 31) 
	 	{
	 		mesDia[0] = 1; 
	 		mesDia[1] = dia;
	 	}
	 	
	 	// 28 Febrero.
	 	else
	 	if (dia > 31 && dia <= 59) 
	 	{	
	 		mesDia[0] = 2; 
	 		mesDia[1] = dia % 31;
	 	}
	 	
	 	// 31 Marzo.
	 	else
	 	if (dia > 59 && dia <= 90) 
	 	{
	 		mesDia[0] = 3; 
	 		mesDia[1] = dia % 59;
	 	}
	 	
	 	// 30 Abril.
	 	else
	 	if (dia > 90 && dia <= 130) 
	 	{
	 		mesDia[0] = 4; 
	 		mesDia[1] = dia % 90;
	 	}
	 	
	 	// 31 Mayo.
	 	else
	 	if (dia > 120 && dia <= 151) 
	 	{
	 		mesDia[0] = 5; 
	 		mesDia[1] = dia % 120;
	 	}
	 	
	 	// 30 Junio.
	 	else
	 	if (dia > 151 && dia <= 181) 
	 	{
	 		mesDia[0] = 6; 
	 		mesDia[1] = dia % 151;
	 	}
	 	
	 	// 31 Julio.
	 	else
	 	if (dia > 181 && dia <= 212) 
	 	{
	 		mesDia[0] = 7; 
	 		mesDia[1] = dia % 181;
	 	}
	 	
	 	// 31 Agosto.
	 	else
	 	if (dia > 212 && dia <= 243) 
	 	{
	 		mesDia[0] = 8; 
	 		mesDia[1] = dia % 212;
	 	}
	 	
	 	// 30 Septiembre.
	 	else
	 	if (dia > 243 && dia <= 273) 
	 	{
	 		mesDia[0] = 9; 
	 		mesDia[1] = dia % 243;
	 	}
	 	
	 	// 31 Octubre.
	 	else
	 	if (dia > 273 && dia <= 304) 
	 	{
	 		mesDia[0] = 10; 
	 		mesDia[1] = dia % 273;
	 	}
	 	
	 	// 30 Noviembre.
	 	else
	 	if (dia > 304 && dia <= 334) 
	 	{
	 		mesDia[0] = 11; 
	 		mesDia[1] = dia % 304;
	 	}
	 	
	 	// 31 Diciembre.
	 	else
	 	if (dia > 334 && dia <= 365) 
	 	{
	 		mesDia[0] = 12; 
	 		mesDia[1] = dia % 334;
	 	}
	 	
	 	return mesDia;
	}
	
	/**
	 * Método que traspasa un arreglo de 3 dimensiones (día, mes y año) a un
	 * string con formato de fecha. El formato del arreglo es (0: día, 1: mes y
	 * 2: año). El formato del string de salida es dd/mm/aa.
	 *
	 * @param diaMesAnio El arreglo que contiene el día, mes y año en formato
	 *                   de (0: día, 1: mes, 2: año).
	 *
	 * @return fecha La fecha compuesta como un string en formato dd/mm/aa.
	 */
	public static String obtenerFecha(int[] diaMesAnio)
	{
		return diaMesAnio[0] + "/" + diaMesAnio[1] + "/" + diaMesAnio[2];
	}
	
	/**
	 * Método que traspasa un string con formato de fecha a un arreglo de 3 
	 * dimensiones (día, mes y año). El formato del arreglo es (0: día, 1: mes y
	 * 2: año). El formato del string dd/mm/aa.
	 *
	 * @param fecha La fecha compuesta como un string en formato dd/mm/aa.
	 *
	 * @return diaMesAnio El arreglo que contiene el día, mes y año en formato
	 *                   de (0: día, 1: mes, 2: año).
	 */
	public static int[] obtenerFecha(String fecha)
	{
		int diaMesAnio[] = new int[3];
		int indice = 0;
		String valor = "";
		
		for(int i = 0; i < fecha.length(); i++)
		{
			if(fecha.charAt(i) == '/')
			{
				diaMesAnio[indice] = Integer.parseInt(valor);
				valor = "";
				indice ++;
			}
			else
			{
				valor = valor + fecha.charAt(i);
			}				
		}
		
		diaMesAnio[indice] = Integer.parseInt(valor);
		
		return diaMesAnio;
	}
	
	/**
	 * Método que traspasa un arreglo de 3 dimensiones (hora, minuto y segundo)
	 * a un string con formato de hora. El formato del arreglo es (0: hora,
	 * 1: minuto y 2: segundo). El formato del string de salida es hh:mm:ss.
	 *
	 * @param horaMinutoSegundo El arreglo que contiene la hora, minuto y
	 *                          segundo en formato de (0: hora, 1: minuto,
	 *                          2: segundo).
	 *
	 * @return hora La hora compuesta como un string en formato hh:mm:ss.
	 */
	public static String obtenerHora(int[] horaMinutoSegundo)
	{
		return horaMinutoSegundo[0] + ":" +
			   horaMinutoSegundo[1] + ":" +
			   horaMinutoSegundo[2];
	}
	
	/**
     * Método que detrmina si un string puede ser traducido a un número para
     * usarlo en cálculos en futuro. Este método se usa para verificar si el
     * texto ingresado representa un número. No hace distinción entre un número
     * entero, real o negativo (sin contar a que grupo matemático pertence).
     *
     * @param texto El string con el texto del número que se quiere verificar.
     *
     * @return numero Indica si el string es número o no.
     */
	public static boolean esNumero(String texto)
	{
		boolean numero = true, punto = false;
		char caracter;
		int i;
		
		if (texto.length() == 0) 
			numero = false;
		
		for (i = 0; i < texto.length(); i++)
	    {
	    	caracter = texto.charAt(i);
	    	
	    	if (i == 0 && (caracter < '0' || caracter > '9') &&
	    		(caracter != '+' && caracter !='-') )
	    			numero = false;
	    	
	    	if (caracter == '.' && punto)
	    		numero = false;
	    	
	    	if (caracter == '.')
	    		punto = true;
	   	    
			if (i != 0 && caracter != '.' &&
				(caracter < '0' || caracter > '9'))
				numero = false;
		}
		
		return numero;
	}
	
	/**
     * Método que valida si el arreglo de string es una fecha válida. La fecha
     * tiene formato de dd/mm/aa (0:día, 1:mes, 2:año).
     *
     * @param fecha El vector de string con la fecha a validar. El formato del
     *              arreglo es (0:día, 1:mes, 2:año).
     *
     * @return valida Indica si la fecha es válida o no.
     */
     public static boolean esFechaValida(String[] fecha)
     {
     	boolean valida = false;
     	int dia  = Integer.parseInt(fecha[0]);
     	int mes  = Integer.parseInt(fecha[1]);
     	int anio = Integer.parseInt(fecha[2]);
     	int bisiesto;
     	
     	if (anio >= 0)
		{
			if (mes > 0 && mes < 13)
			{
				if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 ||
					mes == 10 || mes == 12)
				{
					if (dia > 0 && dia < 32)
						valida = true;
				}
				else
				if (mes == 2)
				{
					bisiesto = anio % 4;
					
					if (bisiesto != 0)
					{
						if (dia > 0 && dia < 29)
							valida = true;
					}
					else
					{
						if (dia > 0 && dia < 30)
							valida = true;
					}
				}
				else
				{
					if (dia > 0 && dia < 31)
						valida = true;
				}
			}
		}
		
		return valida;
	}
	
	/**
	 * Método que compara el orden de las fechas dadas. Una fecha puede ser
	 * mayor o menor según los días, meses y año. Las fechas tienen formato de
	 * dd/mm/aa (0:día, 1:mes, 2:año).
	 * 
	 * @param fecha1 El vector de string con la fecha1 a comparar. El formato
	 *               del arreglo es (0:día, 1:mes, 2:año).
     * @param fecha2 E vector de string con la fecha2 a comparar. El formato
     *               del arreglo es (0:día, 1:mes, 2:año).
     *
     * @return comparacion Idica la comparación que hay entre las fechas.
     *                     Devuelve 1 si la fecha1 es mayor a la fecha2.
     *                     Devuelve 0 si la fecha1 es igual a la fecha2.
     */
	public static int compararFechas(String[] fecha1, String[] fecha2)
	{
		int comparacion; //-1 0 1
		int anio1,mes1,dia1,anio2,mes2,dia2;
		
		dia1  = Integer.parseInt(fecha1[0]);
		mes1  = Integer.parseInt(fecha1[1]);
		anio1 = Integer.parseInt(fecha1[2]);
		
		dia2  = Integer.parseInt(fecha2[0]);
		mes2  = Integer.parseInt(fecha2[1]);
		anio2 = Integer.parseInt(fecha2[2]);
		
		Calendar calendario1= Calendar.getInstance();
		Calendar calendario2 = Calendar.getInstance();
		
		calendario1.set(anio1,mes1,dia1);
		calendario2.set(anio2,mes2,dia2);
		
		if (calendario1.before(calendario2))
			comparacion = -1; // si fecha1 es menor a fecha2
		else
			{
				if (calendario1.after(calendario2))
					comparacion = 1; // si fecha2 es mayor a fecha1
				else
					comparacion = 0;
			}
		
		return comparacion;
	}
	
	/**
     * Método que cuenta los número de caracteres que se encuentra en un string
     * dado como parámetro.
     * 
     * @param texto El string ha analizar.
     * @param caracter Caracter que que se quiere ver cuanta veces esta repetido.
     *
     * @return numero El número de veces que se encuentra el caracterer en el
     *                texto.
     */
	public static int contarCaracter(String texto, char caracter)
	{
		int numero = 0;
		
		for (int i = 0; i < texto.length(); i++)
			if (texto.charAt(i) == caracter)
				numero++;
		
		return numero;
	}
	
	/**
	 * Método que rellena con espacios un texto que ha recibido por parámetros.
	 * El espaciado consiste en incorporarle el caracter espacio tantas veces
	 * como sea necesaria, para que el texto tenga un tamaño de el total
	 * especificado por parámetros.
	 *
	 * @param texto El texto que debe ser espaciado.
	 * @param total El total del largo que debe tener al final el texto.
	 *
	 * @return texto El texto que ya ha sido espaciado.
	 */
	public static String espaciar(String texto, int total)
	{
		// Obtener el tamaño del texto.
		int tamanio = texto.length();
		
		// Ciclo para rellenar el texto con espacios.
		for (int i=0; i<(total-tamanio); i++)
			texto+= " ";
		
		return texto;
	}
	
	/**
	 * Método que devuelve un número convertido en texto bien formateado. Se
	 * utiliza la coma como separador decimal. Se utiliza el punto como
	 * separador de miles. Si tiene más decimales que los indicados realiza un
	 * redondeo hacia arriba.
	 *
	 * @param importe El número a ser formateado.
	 * @param nDecimales El número de decimales a mostrar.
	 *
	 * @return numero El número ya formateado.
	 */
	private static String formatearNumero(BigDecimal importe, int nDecimales)
	{
		// Preparar un objeto para realizar el formateo.
		NumberFormat formateo = NumberFormat.getNumberInstance(Locale.US);
		formateo.setMaximumFractionDigits(nDecimales);
		formateo.setMinimumFractionDigits(nDecimales);
		
		return formateo.format(importe);
	}
	
	/**
	 * Método que devuelve un número convertido en texto bien formateado como
	 * porcentaje. El número entrante debe ser del tipo 0.53 para convertirlo en
	 * 53%. Puntos a tener en cuenta: Utiliza la coma como separador decimal,
	 * utiliza el punto como separador de miles, si tiene más decimales que los
	 * indicados realiza un redondeo hacia arriba, añade el símbolo de
	 * porcentaje al final.
	 *
	 * @param importe El número a ser formateado.
	 * @param nDecimales El número de decimales a mostrar.
	 *
	 * @return numero El número ya formateado.
	 */
	private static String formatearPorcentaje(BigDecimal importe,
											  int nDecimales,
											  boolean conSimbolo)
	{
		double imp = importe.doubleValue();
		
		// Preparar un objeto para realizar el formateo.
		NumberFormat formateo = NumberFormat.getPercentInstance();
		formateo.setMaximumFractionDigits(nDecimales);
		formateo.setMinimumFractionDigits(nDecimales);
		String salida = formateo.format(importe);
		if (!conSimbolo)
			salida = salida.substring(0, salida.length() - 1);
		
		return salida;
	}
	
	/**
	 * Método que devuelve un número convertido en texto bien formateado.
	 * Utiliza la coma como separador decimal. Utiliza el punto como separador
	 * de miles. Si tiene más decimales que los indicados realiza un redondeo
	 * hacia arriba. Realiza una llamada al otro método indicando que queremos
	 * formatear un número.
	 *
	 * @param cantidad El número a ser formateado.
	 * @param nDecimales El número de decimales a mostrar.
	 *
	 * @return numero El número ya formateado.
	 */
	public static String obtenerNumeroFormateado(long cantidad, int nDecimales)
	{
		return obtenerNumeroFormateado(new BigDecimal(cantidad), nDecimales,
									   NUMERO, false);
	}
	
	/**
	 * Método que devuelve un número convertido en texto bien formateado.
	 * Utiliza la coma como separador decimal. Utiliza el punto como separador
	 * de miles. Si tiene más decimales que los indicados realiza un redondeo
	 * hacia arriba. Realiza una llamada al otro método indicando que queremos
	 * formatear un número.
	 *
	 * @param cantidad El número a ser formateado.
	 * @param nDecimales El número de decimales a mostrar.
	 *
	 * @return numero El número ya formateado.
	 */
	public static String obtenerNumeroFormateado(double cantidad,
												 int nDecimales)
	{
		return obtenerNumeroFormateado(new BigDecimal(cantidad), nDecimales,
									   NUMERO, false);
	}
	
	/**
	 * Método que devuelve un porcentaje convertido en texto bien formateado.
	 * Utiliza la coma como separador decimal. Utiliza el punto como separador
	 * de miles. Si tiene más decimales que los indicados realiza un redondeo
	 * hacia arriba. Realiza una llamada al otro método indicando que queremos
	 * formatear un porcentaje.
	 *
	 * @param cantidad El número a ser formateado.
	 * @param nDecimales El número de decimales a mostrar.
	 *
	 * @return porcentaje El porcentaje ya formateado.
	 */
	public static String obtenerPorcentajeFormateado(double cantidad,
													 int nDecimales)
	{
		return obtenerNumeroFormateado(new BigDecimal(cantidad), nDecimales,
									   PORCENTAJE, false);
	}
	
	/**
	 * Método que devuelve un número convertido en texto bien formateado.
	 * Utiliza la coma como separador decimal. Utiliza el punto como separador
	 * de miles. Si tiene más decimales que los indicados realiza un redondeo
	 * hacia arriba.
	 *
	 * @param cantidad El número a ser formateado.
	 * @param nDecimales El número de decimales a mostrar.
	 * @param TipoCampo Indicamos si vamos a formatear un número o un
	 *                  porcentaje.
	 *
	 * @return numero El número ya formateado
	 */
	public static String obtenerNumeroFormateado(BigDecimal cantidad,
												 int nDecimales, int TipoCampo,
												 boolean simboloPorcentaje)
	{
		String numero = "";
		
		// Cuando es un número.
		if (TipoCampo == NUMERO)
			numero = formatearNumero(cantidad, nDecimales);
		else
			// Cuando es un porcentaje.
			if (TipoCampo == PORCENTAJE)
				numero = formatearPorcentaje(cantidad, nDecimales,
											 simboloPorcentaje);
		
		return numero;
	}
	
	/**
     * Metodo que aproxima un número double o de larga extensión a un número
     * aproximado o redondeado al número de decimales específico. Soluciona
     * los problemas de redondeo.
     *
     * @param cantidad El número a ser aproximado.
	 * @param nDecimales El número de decimales a mostrar.
	 * 
     * @return numeroAproximado El numero aproximado.
     */
	public static double obtenerNumeroAproximado(double cantidad,
												 int nDecimales)
	{
		double numeroAproximado =
			Double.parseDouble(obtenerNumeroFormateado(cantidad,nDecimales));
		
		return numeroAproximado;
	}
	
	/**
	 * Método que crea un arreglo ordenado en forma descendente a partir de un
	 * arreglo que se encuentra desordenado. El algoritmo usado para el
	 * ordenamiento del arreglo es el de la burbuja.
	 *
	 * @param arreglo El arreglo de valores que está desordenado.
	 *
	 * @return nuevo Un nuevo arreglo ordenado en forma descendente.
	 */
	public static double[] crearArregloOrdenado(double[] arreglo)
	{
		// Obtener el tamaño del arreglo.
		int tamanio = arreglo.length;
		double temporal;
		
		// Crear el nuevo arreglo ordenado.
		double[] nuevo = new double[tamanio];
		
		// Asignar valores al arreglo nuevo.
		for (int i=0; i<tamanio; i++)
			nuevo[i] = arreglo[i];
		
		// Ordenar el arreglo nuevo.
		for (int i=0; i<tamanio; i++)
			for (int j=0; j<tamanio-1; j++)
				if (nuevo[j] < nuevo[j+1])
				{
					temporal = nuevo[j];
					nuevo[j] = nuevo[j+1];
					nuevo[j+1] = temporal;
				}
		
		return nuevo;
	}
	
	/**
	 * Método que obtiene un número al azar dentro del rango especificado en los
	 * parámetros. La particularidad de este método es que el valor que se
	 * retorna es un entero.
	 *
	 * @param cotaInferior El valor de la cota inferior del intervalo.
	 * @param cotaSuperior El valor de la cota superior del intervalo.
	 *
	 * @return azarEntero Un valor aleatorio entero que se encuentra en el
	 *                    intervalo [cotaInferior, cotaSuperior].
	 */
	public static int obtenerAzar(int cotaInferior, int cotaSuperior)
	{
		int delta = cotaSuperior - cotaInferior + 1;
		int valor = azar.nextInt(delta) + cotaInferior;
		
		return valor;
	}
	
	/**
	 * Método que obtiene un número al azar con cierto porcentaje de tendencia
	 * hacia un valor dentro del rango especificado en los parámetros. La
	 * particularidad de este método es que el valor que se retorna es un número
	 * aleatorio entero con cierto porcentaje de tendencia hacia cierto valor.
	 *
	 * @param cotaInferior El valor de la cota inferior del intervalo.
	 * @param cotaSuperior El valor de la cota superior del intervalo.
	 * @param valor El valor al cual tiende el azar.
	 * @param tendencia El porcentaje de tendencia hacia el valor. Esta
	 *                  tendencia se encuentra en el intervalo [0, 1].
	 *
	 * @return azarTendencia Un valor aleatorio entero con cierta tendencia
	 *                       hacia un valor que se encuentra en el intervalo
	 *                       [cotaInferior, cotaSuperior].
	 */
	public static int obtenerAzar(int cotaInferior, int cotaSuperior,
								  int numero, double tendencia)
	{
		int aleatorio = obtenerAzar(cotaInferior, cotaSuperior);
		int delta = Math.abs(aleatorio - numero);
		double porcentaje = obtenerAzar(0, tendencia);
		delta *= porcentaje;
		
		if (aleatorio > numero)
			return numero + delta;
		
		return numero - delta;
	}
	
	/**
	 * Método que obtiene un número al azar dentro del rango especificado en los
	 * parámetros. La particularidad de este método es que el valor que se
	 * retorna es un double.
	 *
	 * @param cotaInferior El valor de la cota inferior del intervalo.
	 * @param cotaSuperior El valor de la cota superior del intervalo.
	 *
	 * @return azarDouble Un valor aleatorio double que se encuentra en el
	 *                    intervalo [cotaInferior, cotaSuperior].
	 */
	public static double obtenerAzar(double cotaInferior, double cotaSuperior)
	{
		double delta = cotaSuperior - cotaInferior;
		double porcentaje = azar.nextDouble();
		double valor = delta * porcentaje;
		
		return valor + cotaInferior;
	}
	
	/**
	 * Método que obtiene un número al azar con cierto porcentaje de tendencia
	 * hacia un valor dentro del rango especificado en los parámetros. La
	 * particularidad de este método es que el valor que se retorna es un número
	 * aleatorio double con cierto porcentaje de tendencia hacia cierto valor.
	 *
	 * @param cotaInferior El valor de la cota inferior del intervalo.
	 * @param cotaSuperior El valor de la cota superior del intervalo.
	 * @param valor El valor al cual tiende el azar.
	 * @param tendencia El porcentaje de tendencia hacia el valor. Esta
	 *                  tendencia se encuentra en el intervalo [0, 1].
	 *
	 * @return azarTendencia Un valor aleatorio double con cierta tendencia
	 *                       hacia un valor que se encuentra en el intervalo
	 *                       [cotaInferior, cotaSuperior].
	 */
	public static double obtenerAzar(double cotaInferior, double cotaSuperior,
									 double numero, double tendencia)
	{
		double valor = obtenerAzar(cotaInferior,cotaSuperior);
		double delta = Math.abs(valor - numero);
		double porcentaje = obtenerAzar(0.0, tendencia);
		delta = delta * porcentaje;
		double resultadoFinal;
		
		if (valor > numero)
			resultadoFinal = numero + delta;
		else resultadoFinal = numero - delta;
		
		return resultadoFinal;
	}
	
	/**
	 * Calcula una razón que indica que tan cerca se encuentra de valor superior.
	 * y la vez indica que tan lejos se encuentra del valor inferior.
	 * 
	 * @param valor El valor que desea comparar.
	 * @param valorInferior La cuota inferior de valor.
	 * @param valorSuperior La cuota superior de valor.
	 * 
	 * @return razon La cercanía entre el valor y su valor superior.
	 */
	public static double obtenerRazon(double valor, double valorInferior, 
													double valorSuperior)
	{
		double razon = 0;
		double diferencia = valorSuperior - valorInferior;
		
		if (valor > valorSuperior)
			valor = valorSuperior;
		else
		if (valor < valorInferior)
			valor = valorInferior;
		else
		if (diferencia == 0) 
	    	razon = 0;
		else 
			razon = 1 - (diferencia - (valor - valorInferior)) / diferencia;
		
		return razon;
	}
}