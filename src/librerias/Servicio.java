/**
 * @(#)Servicio.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.util.Calendar;
import java.util.Random;
import java.util.Locale;
import java.math.BigDecimal;
import java.text.NumberFormat;

/** 
 * Clase que contiene solamente m�todos est�ticos que sirven para ofrecer
 * diversos servicios a la aplicaci�n.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @author Paul Leger
 * @author H�ctor D�az
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
	/* El valor para indicar que queremos trabajar con un n�mero. */
	public static final int NUMERO = 0;
	
	/* El valor para indicar que queremos trabajar con un porcentaje. */
	public static final int PORCENTAJE = 1;
	
	/** El valor del radio terrestre (kil�metros) en la zona ecuatorial. */
	private static final double RADIO_ECUATORIAL = 6378.4;
	
	/** El valor del radio terrestre (kil�metros) en la zona polar. */
	private static final double RADIO_POLAR = 6356.4;
	
	/** Objeto que entrega los valores que son aleatorios. */
	private static final Random azar = new Random(System.currentTimeMillis());
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * minutos a grados. La transformaci�n consiste en convertir los minutos a
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
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * un grado, que contiene grados y minutos, a solamente grados. La
	 * transformaci�n consiste en convertir los minutos a grados y luego
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
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * grados a minutos. La transformaci�n consiste en convertir los grados a
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
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * un grado, que contiene grados y minutos, a solamente minutos. La
	 * transformaci�n consiste en convertir los grados a minutos y luego
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
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * a�os a d�as. La transformaci�n consiste en convertir los a�os a d�as y
	 * luego se devuelve este valor.
	 *
	 * @param anio Un valor expresado en a�os.
	 *
	 * @return dia Los a�os expresados en d�as.
	 */
	public static double transformarAnioDia(double anio)
	{
		return anio * 365;
	}
	
	/**
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * meses a d�as. La transformaci�n consiste en convertir los meses a d�as y
	 * luego se devuelve este valor.
	 *
	 * @param mes Un valor esxpresado en meses.
	 *
	 * @return dia Los meses expresados en d�as.
	 */
	public static double transformarMesDia(double mes)
	{
		return mes * (365.0 / 12.0);
	}
	
	/**
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * d�as a horas. La transformaci�n consiste en convertir los d�as a horas y
	 * luego se devuelve este valor.
	 *
	 * @param dia Un valor expresado en d�as.
	 *
	 * @return hora Los d�as expresados en horas.
	 */
	public static double transformarDiaHora(double dia)
	{
		return dia * 24;
	}
	
	/**
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * horas a minutos. La transformaci�n consiste en convertir las horas a
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
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * minutos a segundos. La transformaci�n consiste en convertir los minutos a
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
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * a�os a horas. La transformaci�n consiste en convertir los a�os a d�as,
	 * luego los d�as a horas y luego se devuelve este valor.
	 *
	 * @param anio Un valor expresado en a�os.
	 *
	 * @return hora Los a�os expresados en horas.
	 */
	public static double transformarAnioHora(double anio)
	{
		return transformarDiaHora(transformarAnioDia(anio));
	}
	
	/**
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * a�os a minutos. La transformaci�n consiste en convertir los a�os a d�as,
	 * luego los d�as a horas, luego las horas a minutos y luego se devuelve
	 * este valor.
	 *
	 * @param anio Un valor expresado en a�os.
	 *
	 * @return minuto Los a�os expresados en minutos.
	 */
	public static double transformarAnioMinuto(double anio)
	{
		return transformarHoraMinuto(transformarAnioHora(anio));
	}
	
	/**
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * a�os a segundos. La transformaci�n consiste en convertir los a�os a d�as,
	 * luego los d�as a horas, luego las horas a minutos, luego los minutos a
	 * segundos y luego se devuelve este valor.
	 *
	 * @param anio Un valor expresado en a�os.
	 *
	 * @return segundo Los a�os expresados en segundos.
	 */
	public static double transformarAnioSegundo(double anio)
	{
		return transformarMinutoSegundo(transformarAnioMinuto(anio));
	}
	
	/**
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * d�as a minutos. La transformaci�n consiste en convertir los d�as a horas,
	 * luego convertir las horas a minutos y luego se devuelve este valor.
	 *
	 * @param dia Un valor expresado en d�as.
	 *
	 * @return minuto Los d�as expresados en minutos.
	 */
	public static double transformarDiaMinuto(double dia)
	{
		return transformarHoraMinuto(transformarDiaHora(dia));
	}
	
	/**
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * d�as a segundos. La transformaci�n consiste en convertir los d�as a
	 * horas, luego convertir las horas a minutos, despu�s convertir los minutos
	 * a segundos y luego se devuelve este valor.
	 *
	 * @param dia Un valor expresado en d�as.
	 *
	 * @return segundo Los d�as expresados en segundos.
	 */
	public static double transformarDiaSegundo(double dia)
	{
		return transformarMinutoSegundo(transformarDiaMinuto(dia));
	}
	
	/**
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * horas a segundos. La transformaci�n consiste en convertir las horas a
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
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * segundos a minutos. La transformaci�n consiste en convertir los segundos
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
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * minutos a horas. La transformaci�n consiste en convertir los minutos a
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
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * horas a d�as. La transformaci�n consiste en convertir las horas a d�as y
	 * luego se devuelve este valor.
	 *
	 * @param hora Un valor expresado en horas.
	 *
	 * @return dia Las horas expresadas en d�as.
	 */
	public static double transformarHoraDia(double hora)
	{
		return hora / 24;
	}
	
	/**
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * d�as a a�os. La transformaci�n consiste en convertir los d�as a a�os y
	 * luego se devuelve este valor.
	 *
	 * @param dia Un valor expresado en d�as.
	 *
	 * @return anio Los d�as expresados en a�os.
	 */
	public static double transformarDiaAnio(double dia)
	{
		return dia / 365;
	}
	
	/**
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * segundos a horas. La transformaci�n consiste en convertir los segundos
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
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * segundos a d�as. La transformaci�n consiste en convertir los segundos
	 * a minutos, luegos los minutos a horas, luego las horas a d�as y luego se
	 * devuelve este valor.
	 *
	 * @param segundo Un valor expresado en segundos.
	 *
	 * @return dia Los segundos expresados en d�as.
	 */
	public static double transformarSegundoDia(double segundo)
	{
		return transformarHoraDia(transformarSegundoHora(segundo));
	}
	
	/**
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * segundos a a�os. La transformaci�n consiste en convertir los segundos
	 * a minutos, luegos los minutos a horas, luego las horas a d�as, luego los
	 * d�as a a�os y luego se devuelve este valor.
	 *
	 * @param segundo Un valor expresado en segundos.
	 *
	 * @return anio Los segundos expresados en a�os.
	 */
	public static double transformarSegundoAnio(double segundo)
	{
		return transformarDiaAnio(transformarSegundoDia(segundo));
	}
	
	/**
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * minutos a d�as. La transformaci�n consiste en convertir los minutos a
	 * horas, luegos las horas a d�as y luego se devuelve este valor.
	 *
	 * @param minuto Un valor expresado en minutos.
	 *
	 * @return dia Los minutos expresados en d�as.
	 */
	public static double transformarMinutoDia(double minuto)
	{
		return transformarHoraDia(transformarMinutoHora(minuto));
	}
	
	/**
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * minutos a a�os. La transformaci�n consiste en convertir los minutos a
	 * horas, luegos las horas a d�as, luego los d�as a a�os y luego se devuelve
	 * este valor.
	 *
	 * @param minuto Un valor expresado en minutos.
	 *
	 * @return anio Los minutos expresados en a�os.
	 */
	public static double transformarMinutoAnio(double minuto)
	{
		return transformarDiaAnio(transformarMinutoDia(minuto));
	}
	
	/**
	 * M�todo transformador de unidades de tiempo. Realiza la transformaci�n de
	 * horas a a�os. La transformaci�n consiste en convertir las horas a d�as,
	 * luegos los d�as a a�os y luego se devuelve este valor.
	 *
	 * @param hora Un valor expresado en horas.
	 *
	 * @return anio Las horas expresadas en a�os.
	 */
	public static double transformarHoraAnio(double hora)
	{
		return transformarDiaAnio(transformarHoraDia(hora));
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * grados de longitud a kil�metros. La transformaci�n consiste en convertir
	 * los grados de longitud a kil�metros y luego se devuelve este valor.
	 *
	 * @param grado Un valor expresado en grados de longitud.
	 *
	 * @return kilometro Los grados en longitud expresados en kil�metros.
	 */
	public static double transformarGradoLongitudKilometro(double grado)
	{
		return (grado * RADIO_POLAR) / 90;
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * kil�metros a grados de longitud. La transformaci�n consiste en convertir
	 * los kil�metros a grados de longitud y luego se devuelve este valor.
	 *
	 * @param kilometro Un valor expresado en kilometros.
	 *
	 * @return grado Los kil�metros expresados en grados de longitud.
	 */
	public static double transformarKilometroGradoLongitud(double kilometro)
	{
		return (kilometro * 90) / RADIO_POLAR;
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * grados de latitud a kil�metros. La transformaci�n consiste en convertir
	 * los grados de latitud a kil�metros y luego se devuelve este valor.
	 *
	 * @param grado Un valor expresado en grados de latitud.
	 *
	 * @return kilometro Los grados de latitud expresados en kil�metros.
	 */
	public static double transformarGradoLatitudKilometro(double grado)
	{
		return (grado * RADIO_ECUATORIAL) / 90;
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * kil�metros a grados de latitud. La transformaci�n consiste en convertir
	 * los kil�metros a grados de latitud y luego se devuelve este valor.
	 *
	 * @param kilometro Un valor expresado en kilometros.
	 *
	 * @return grado Los kil�metros expresados en grados de latitud.
	 */
	public static double transformarKilometroGradoLatitud(double kilometro)
	{
		return (kilometro * 90) / RADIO_ECUATORIAL;
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * kil�metros a grados. La transformaci�n consiste en convertir los
	 * kil�metros a grados de longitud, luego convertir los kil�metros a grados
	 * en latitud. Finalmente calcular el promedio de estos grados y luego se
	 * devuelve este valor.
	 *
	 * @param kilometro Un valor expresado en kilometros.
	 *
	 * @return grado Los kil�metros expresados en grados.
	 */
	public static double transformarKilometroGrado(double kilometro)
	{
		return (transformarKilometroGradoLongitud(kilometro) +
				transformarKilometroGradoLatitud(kilometro)) / 2;
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * kil�metros a metros. La transformaci�n consiste en convertir los
	 * kil�metros a metros y luego se devuelve este valor.
	 *
	 * @param kilometro Un valor expresado en kilometros.
	 *
	 * @return metro Los kil�metros expresados en metros.
	 */
	public static double transformarKilometroMetro(double kilometro)
	{
		return kilometro * 1000;
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * cent�metros a mil�metros. La transformaci�n consiste en convertir los
	 * cent�metros a mil�metros y luego se devuelve este valor.
	 *
	 * @param centimetro Un valor expresado en cent�metros.
	 *
	 * @return milimetro Los cent�metros expresados en mil�metros.
	 */
	public static double transformarCentimetroMilimetro(double centimetro)
	{
		return centimetro * 10;
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * metros a kil�metros. La transformaci�n consiste en convertir los metros
	 * a kil�metros y luego se devuelve este valor.
	 *
	 * @param metro Un valor expresado en metros.
	 *
	 * @return kilometro Los metros expresados en kil�metros.
	 */
	public static double transformarMetroKilometro(double metro)
	{
		return metro / 1000;
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * mil�metros a cent�metros. La transformaci�n consiste en convertir los
	 * mil�metros a cent�metros y luego se devuelve este valor.
	 *
	 * @param milimetro Un valor expresado en mil�metros.
	 *
	 * @return centimetro Los mil�metros expresados en cent�metros.
	 */
	public static double transformarMilimetroCentimetro(double milimetro)
	{
		return milimetro / 10;
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * millas n�uticas a metros. La transformaci�n consiste en convertir las
	 * millas n�uticas a metros y luego se devuelve este valor.
	 *
	 * @param millaNautica Un valor expresado en millas n�uticas.
	 *
	 * @return metro Las millas n�uticas expresadas en metros.
	 */
	public static double transformarMillaNauticaMetro(double millaNautica)
	{
		return millaNautica * 1853.25;
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * millas n�uticas a kil�metros. La transformaci�n consiste en convertir las
	 * millas n�uticas a metros, luegos los metros a kil�metros y luego se
	 * devuelve este valor.
	 *
	 * @param millaNautica Un valor expresado en millas n�uticas.
	 *
	 * @return kil�metro Las millas n�uticas expresadas en kil�metros.
	 */
	public static double transformarMillaNauticaKilometro(double millaNautica)
	{
		return transformarMetroKilometro(
			   transformarMillaNauticaMetro(millaNautica));
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * grados de longitud a unidades de celda. La transformaci�n consiste en
	 * convertir los grados de longitud a las unidades de celdas usadas en la
	 * transformaci�n del modelo y devolver este valor.
	 *
	 * @param longitud Un valor expresado en grados de longitud.
	 * @para transformacion La transformaci�n de modelo usada.
	 *
	 * @return celda Los grados de longitud expresados en unidades de celda.
	 */
	public static int transformarGradoLongitudCelda(double longitud,
											TransformacionModelo transformacion)
	{
		// Obtener iDel y xDel de la transformaci�n.
		int iDel = transformacion.obtenerIDel();
		double xDel = transformacion.obtenerXDel();
		
		return (int) Math.abs(iDel * longitud / xDel);
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * grados de latitud a unidades de celda. La transformaci�n consiste en
	 * convertir los grados de latitud a las unidades de celdas usadas en la
	 * transformaci�n del modelo y devolver este valor.
	 *
	 * @param latitud Un valor expresado en grados de latitud.
	 * @para transformacion La transformaci�n de modelo usada.
	 *
	 * @return celda Los grados de latitud expresados en unidades de celda.
	 */
	public static int transformarGradoLatitudCelda(double latitud,
											TransformacionModelo transformacion)
	{
		// Obtener jDel y yDel de la transformaci�n.
		int jDel = transformacion.obtenerJDel();
		double yDel = transformacion.obtenerYDel();
		
		return (int) Math.abs(jDel * latitud / yDel);
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * metros de altitud a unidades de celda. La transformaci�n consiste en
	 * convertir los metros de altitud a las unidades de celdas usadas en la
	 * transformaci�n del modelo y devolver este valor.
	 *
	 * @param altitud Un valor expresado en metros de altitud.
	 * @para transformacion La transformaci�n de modelo usada.
	 *
	 * @return celda Los metros de altitud expresados en unidades de celda.
	 */
	public static int transformarMetroAltitudCelda(double altitud,
											TransformacionModelo transformacion)
	{
		// Obtener jDel y yDel de la transformaci�n.
		int kDel = transformacion.obtenerKDel();
		double zDel = transformacion.obtenerZDel();
		
		return (int) Math.abs(kDel * altitud / zDel);
	}
	
	/**
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * metros a unidades de celda. La transformaci�n consiste en convertir los
	 * metros a las unidades de celdas usadas en la transformaci�n del modelo,
	 * es decir, grados de longitud y latitud, y metros son transformados a
	 * unidades de celda. Se devuelve el promedio entre las celdas en longitud,
	 * latitud y altitud.
	 *
	 * @param metro Un valor expresado en metros.
	 * @para transformacion La transformaci�n de modelo usada.
	 *
	 * @return celda Los metros expresados en unidades de celda.
	 */
	public static int transformarMetroCelda(double metro,
											TransformacionModelo transformacion)
	{
		// Transformar los metros a kil�metros.
		double kilometro = transformarMetroKilometro(metro);
		
		// Transformar los kil�metros a longitud, latitud y altitud.
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
	 * M�todo transformador de unidades de medida. Realiza la transformaci�n de
	 * una celda a una coordenada. La transformaci�n consiste en convertir
	 * las unidades de celdas usadas en la transformaci�n del modelo a
	 * longitudes, latitudes y altitudes. Se devuelve una nueva coordenada con
	 * la longitud, latitud y altitud que corresponden a la transformaci�n de
	 * la celda.
	 *
	 * @param celda Una celda.
	 * @para transformacion La transformaci�n de modelo usada.
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
	 * M�todo transformador de unidades de masa. Realiza la transformaci�n de
	 * toneladas a kil�gramos. La transformaci�n consiste en convertir las
	 * toneladas a kil�gramos y luego se devuelve este valor.
	 *
	 * @param tonelada Un valor expresado en toneladas.
	 *
	 * @return kilogramos Las toneladas expresadas en kil�gramos.
	 */
	public static double transformarToneladaKilogramo(double tonelada)
	{
		return tonelada * 1000;
	}
	
	/**
	 * M�todo transformador de unidades de masa. Realiza la transformaci�n de
	 * kil�gramos a gramos. La transformaci�n consiste en convertir los
	 * kil�gramos a gramos y luego se devuelve este valor.
	 *
	 * @param kilogramos Un valor expresado en kilogramos.
	 *
	 * @return gramos Los kil�gramos expresados en gramos.
	 */
	public static double transformarKilogramoGramo(double kilogramo)
	{
		return kilogramo * 1000;
	}
	
	/**
	 * M�todo transformador de unidades de masa. Realiza la transformaci�n de
	 * toneladas a gramos. La transformaci�n consiste en convertir las
	 * toneladas a kil�gramos, luego convertir los kil�gramos a gramos y luego
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
	 * M�todo transformador de unidades de masa. Realiza la transformaci�n de
	 * gramos a kil�gramos. La transformaci�n consiste en convertir los gramos a
	 * kil�gramos y luego se devuelve este valor.
	 *
	 * @param gramo Un valor expresado en gramos.
	 *
	 * @return kilogramos Los gramos expresados en kil�gramos.
	 */
	public static double transformarGramoKilogramo(double gramo)
	{
		return gramo / 1000;
	}
	
	/**
	 * M�todo transformador de unidades de masa. Realiza la transformaci�n de
	 * kil�gramos a toneladas. La transformaci�n consiste en convertir los
	 * kil�gramos a toneladas y luego se devuelve este valor.
	 *
	 * @param kilogramo Un valor expresado en kil�gramos.
	 *
	 * @return toneladas Los kilogr�mos expresados en toneladas.
	 */
	public static double transformarKilogramoTonelada(double kilogramo)
	{
		return kilogramo / 1000;
	}
	
	/**
	 * M�todo transformador de unidades de masa. Realiza la transformaci�n de
	 * gramos a toneladas. La transformaci�n consiste en convertir los gramos a
	 * kil�gramos, luego convertir los kil�gramos a toneladas y luego se
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
	 * M�todo que obtiene el grado que tiene un n�mero expresado en punto
	 * flotante. Al n�mero se le calculan sus grados y minutos. Luego, en caso
	 * de que los minutos obtenidos sean mayores o iguales que sesenta, entonces
	 * se procede a incrementar los grados en uno y a decrementar los minutos en
	 * sesenta, hasta que los minutos sean menores a sesenta.
	 *
	 * @param n�mero Un valor expresado en grados.
	 *
	 * @return grado El grado del n�mero.
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
	 * M�todo que obtiene el minuto que tiene un n�mero punto flotante. Al
	 * n�mero se le calculan sus grados y minutos. Luego, en caso de que los
	 * minutos obtenidos sean mayores o iguales que sesenta, entonces se procede
	 * a incrementar los grados en uno y a decrementar los minutos en sesenta,
	 * hasta que los minutos sean menores a sesenta.
	 *
	 * @param numero Un valor expresado en grados.
	 *
	 * @return minuto El minuto del n�mero.
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
	 * M�todo que calcula los minutos que hay entre dos grados. El c�lculo es
	 * determinado de la siguiente manera: primeramente los grados inicial y
	 * final se transforman a minutos, luego se restan los minutos, y
	 * finalmente se devuelve este valor. Los grados par�metros del m�todo son
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
	 * M�todo que calcula los minutos que hay entre dos grados. El c�lculo es
	 * determinado de la siguiente manera: primeramente los grados inicial y
	 * final se transforman a minutos, luego se restan los minutos, y
	 * finalmente se devuelve este valor. Los grados par�metros del m�todo son
	 * expresados en grados como valores �num�ricos punto flotante.
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
	 * M�todo que decrementa en ciertos minutos un grado recibido como
	 * par�metro.
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
	 * M�todo que incrementa en ciertos minutos un grado recibido como
	 * par�metro.
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
	 * M�todo que calcula la distancia existente entre dos medidas. La
	 * distancia se calcula usando la siguiente f�rmula:
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
	 * M�todo que obtiene la distancia de forma pitag�rica entre dos coordenadas
	 * de celda. La distancia se calcula usando la siguiente f�rmula:
	 * distancia = ((x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2)^(1/2)
	 *
	 * @param celda1 La celda inicial.
	 * @param celda2 La celda final.
	 *
	 * @return distancia La distancia pitag�rica entre las celdas.
	 */
	public static double obtenerDistancia(Celda celda1, Celda celda2)
	{
		return Math.sqrt(
			Math.pow(celda2.obtenerI() - celda1.obtenerI(), 2)+
			Math.pow(celda2.obtenerJ() - celda1.obtenerJ(), 2)+
			Math.pow(celda2.obtenerK() - celda1.obtenerK(), 2));
	}
	
	/**
	 * M�todo que obtiene la distancia entre dos coordenadas. La distancia que
	 * se devuelve es la distancia pitag�rica entre estas dos coordenadas. La
	 * distancia que se devuelve es expresada en kil�metros.
	 *
	 * @param coordenada1 La coordenada inicial.
	 * @param coordenada2 La coordenada final.
	 *
	 * @return distancia La distancia pitag�rica entre las coordenadas.
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
	 * M�todo que calcula el per�metro de una circunferencia con radio conocido.
	 * El per�metro se calcula usando la siguiente f�rmula:
	 * per�metro = 2 * PI * radio
	 *
	 * @param radio El radio de la circunferencia.
	 *
	 * @return perimetro El per�metro de la circunferencia.
	 */
	public static double obtenerPerimetroCircunferencia(double radio)
	{
		return Math.abs(2 * Math.PI * radio);
	}
	
	/**
	 * M�todo que obtiene el �rea superficial de una circunferencia con radio
	 * especificado como par�metro. El �rea se calcula usando la siguiente
	 * f�rmula: �rea = PI * radio * radio
	 *
	 * @param radio El radio de la circunferencia.
	 *
	 * @return area El �rea superficial de la circunferencia.
	 */
	public static double obtenerAreaCircunferencia(double radio)
	{
		return Math.abs(Math.PI * Math.pow(radio, 2));
	}
	
	/**
	 * M�todo que obtiene el �rea de la Tierra a una cierta latitud. Se toma
	 * la porci�n del radio de la Tierra que corresponde a la latitud
	 * especificada como par�metro. Se asume que el �rea superficial de la
	 * Tierra se calcula como el �rea superficial de una circunferencia
	 * cualquiera.
	 *
	 * @param latitud La latitud en donde se obtiene el �rea terrestre.
	 *
	 * @return area El �rea terrestre (kil�metros^2) en la latitud especificada.
	 */
	public static double obtenerAreaTerrestre(double latitud)
	{
		// Calcular el radio.
		double radio = ((90 - latitud) * RADIO_ECUATORIAL) / 90;
		
		return obtenerAreaCircunferencia(radio);
	}
	
	/**
	 * M�todo que obtiene el �rea superficial (kil�metros cuadrados) terrestre
	 * entre dos longitudes y entre dos latitudes especificadas como par�metros.
	 * Primero se obtienen las �reas superficiales con la latitud inicial y
	 * final. Luego se restan las �reas y se multiplican por la porci�n.
	 *
	 * @param longitudInicial La longitud inicial expresada en grados.
	 * @param longitudFinal La longitud final expresada en grados.
	 * @param latitudInicial La latitud inicial expresada en grados.
	 * @param latitudFinal La latitud final expresada en grados.
	 *
	 * @return area El �rea superficial (kil�metros^2) entre las longitudes y
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
	 * M�todo que obtiene el �rea superficial (kil�metros cuadrados) terrestre
	 * de un n�mero de celdas especificadas como par�metros. Primero se obtiene
	 * la transformaci�n usada en el mapa, luego se obtienen las longitudes y
	 * latitudes que representan los l�mites de las celdas. luego se calcula el
	 * �rea superficial entre estos l�mites y se devuelve este valor.
	 *
	 * @param numeroCeldas El n�mero de celdas a las cuales quiere obtenerse
	 *                     su �rea superficial.
	 * @param transformacion La transformaci�n de modelo usada.
	 *
	 * @return area El �rea superficial (kil�metros^2) del n�mero de celdas
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
     * M�todo que dado un d�a del a�o devuelve el mes del a�o mas su
     * aproximaci�n decimal con respecto al siguiente mes. Ej: 1.2; 1.9; 12.5
     * (mitad Diciembre).
     * 
     * @param dia D�a del a�o.
     *
     * @return mes Mes del a�o con su aproximaci�n decimal al mes siguiente.
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
     * M�todo que dado un mes del a�o devuelve el nombre del mes del a�o. Se
     * crea un arreglo de string con los nombres del a�o y luego se devuelve el
     * nombre del mes que corresponde al par�metro recibido. Se hace notar que
     * los meses son valores entre uno y doce.
     * 
     * @param mes Un mes del a�o.
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
	 * Metodo que traspasa un d�a del mes a un d�a del a�o.
	 *
	 * @param dia Un d�a del mes.
	 * @param mes Un mes del a�o.
	 *
	 * @return dia Un d�a del a�o.
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
	 * Metodo que traspasa arreglo de 2 dimensiones (d�a del mes y mes) a un d�a
	 * del a�o. Formato del arreglo es (0: d�a, 1: mes)	
	 *
	 * @param mesDia El arreglo que contiene el d�a del mes y mes. Formato del
	 *				 arreglo es (0: d�a, 1: mes).
	 *
	 * @return dia Un d�a del a�o.
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
	 * M�todo que obtiene la cantidad de d�as que tiene un mes del a�o. Este
	 * m�todo no considera el a�o bisiesto.
	 *
	 * @param mes Un mes del a�o.
	 *
	 * @return cantidadDias La cantidad de d�as que tiene el mes.
	 */
	public static int obtenerCantidadDias(int mes)
	{
		// Las cantidades de d�as de los meses.
	 	int[] cantidadDias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	 	
	 	// Devolver la cantidad de d�as el mes.
	 	return cantidadDias[mes - 1];
	}
	
	/**
	 * M�todo que calcula los d�as que hay entre dos fechas. El c�lculo es
	 * determinado de la siguiente manera: primeramente la fecha inicial y final
	 * se transforman a d�as, luego se restan los d�as, y finalmente se devuelve
	 * este valor. Las fechas par�metros del m�todo son expresados en formato
	 * dd-mm-aa (0: d�a, 1: mes, 2: a�o).
	 *
	 * @param fechaInicial El valor inicial valor expresado en grados y
	 *                      minutos.
	 * @param fechaFinal El valor final valor expresado en grados y minutos.
	 *
	 * @return dias Los d�as entre la fecha inicial y final.
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
	 * Entrega el mes y d�a correspondiente al d�a especificado. Devuelve -1
	 * si el d�a esta fuera de los rangos.
	 * 
	 * @param dia Un d�a del a�o.
	 *
	 * @return mesDia Un mes del a�o y un d�a del mes entregado antes.
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
	 * M�todo que traspasa un arreglo de 3 dimensiones (d�a, mes y a�o) a un
	 * string con formato de fecha. El formato del arreglo es (0: d�a, 1: mes y
	 * 2: a�o). El formato del string de salida es dd/mm/aa.
	 *
	 * @param diaMesAnio El arreglo que contiene el d�a, mes y a�o en formato
	 *                   de (0: d�a, 1: mes, 2: a�o).
	 *
	 * @return fecha La fecha compuesta como un string en formato dd/mm/aa.
	 */
	public static String obtenerFecha(int[] diaMesAnio)
	{
		return diaMesAnio[0] + "/" + diaMesAnio[1] + "/" + diaMesAnio[2];
	}
	
	/**
	 * M�todo que traspasa un string con formato de fecha a un arreglo de 3 
	 * dimensiones (d�a, mes y a�o). El formato del arreglo es (0: d�a, 1: mes y
	 * 2: a�o). El formato del string dd/mm/aa.
	 *
	 * @param fecha La fecha compuesta como un string en formato dd/mm/aa.
	 *
	 * @return diaMesAnio El arreglo que contiene el d�a, mes y a�o en formato
	 *                   de (0: d�a, 1: mes, 2: a�o).
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
	 * M�todo que traspasa un arreglo de 3 dimensiones (hora, minuto y segundo)
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
     * M�todo que detrmina si un string puede ser traducido a un n�mero para
     * usarlo en c�lculos en futuro. Este m�todo se usa para verificar si el
     * texto ingresado representa un n�mero. No hace distinci�n entre un n�mero
     * entero, real o negativo (sin contar a que grupo matem�tico pertence).
     *
     * @param texto El string con el texto del n�mero que se quiere verificar.
     *
     * @return numero Indica si el string es n�mero o no.
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
     * M�todo que valida si el arreglo de string es una fecha v�lida. La fecha
     * tiene formato de dd/mm/aa (0:d�a, 1:mes, 2:a�o).
     *
     * @param fecha El vector de string con la fecha a validar. El formato del
     *              arreglo es (0:d�a, 1:mes, 2:a�o).
     *
     * @return valida Indica si la fecha es v�lida o no.
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
	 * M�todo que compara el orden de las fechas dadas. Una fecha puede ser
	 * mayor o menor seg�n los d�as, meses y a�o. Las fechas tienen formato de
	 * dd/mm/aa (0:d�a, 1:mes, 2:a�o).
	 * 
	 * @param fecha1 El vector de string con la fecha1 a comparar. El formato
	 *               del arreglo es (0:d�a, 1:mes, 2:a�o).
     * @param fecha2 E vector de string con la fecha2 a comparar. El formato
     *               del arreglo es (0:d�a, 1:mes, 2:a�o).
     *
     * @return comparacion Idica la comparaci�n que hay entre las fechas.
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
     * M�todo que cuenta los n�mero de caracteres que se encuentra en un string
     * dado como par�metro.
     * 
     * @param texto El string ha analizar.
     * @param caracter Caracter que que se quiere ver cuanta veces esta repetido.
     *
     * @return numero El n�mero de veces que se encuentra el caracterer en el
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
	 * M�todo que rellena con espacios un texto que ha recibido por par�metros.
	 * El espaciado consiste en incorporarle el caracter espacio tantas veces
	 * como sea necesaria, para que el texto tenga un tama�o de el total
	 * especificado por par�metros.
	 *
	 * @param texto El texto que debe ser espaciado.
	 * @param total El total del largo que debe tener al final el texto.
	 *
	 * @return texto El texto que ya ha sido espaciado.
	 */
	public static String espaciar(String texto, int total)
	{
		// Obtener el tama�o del texto.
		int tamanio = texto.length();
		
		// Ciclo para rellenar el texto con espacios.
		for (int i=0; i<(total-tamanio); i++)
			texto+= " ";
		
		return texto;
	}
	
	/**
	 * M�todo que devuelve un n�mero convertido en texto bien formateado. Se
	 * utiliza la coma como separador decimal. Se utiliza el punto como
	 * separador de miles. Si tiene m�s decimales que los indicados realiza un
	 * redondeo hacia arriba.
	 *
	 * @param importe El n�mero a ser formateado.
	 * @param nDecimales El n�mero de decimales a mostrar.
	 *
	 * @return numero El n�mero ya formateado.
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
	 * M�todo que devuelve un n�mero convertido en texto bien formateado como
	 * porcentaje. El n�mero entrante debe ser del tipo 0.53 para convertirlo en
	 * 53%. Puntos a tener en cuenta: Utiliza la coma como separador decimal,
	 * utiliza el punto como separador de miles, si tiene m�s decimales que los
	 * indicados realiza un redondeo hacia arriba, a�ade el s�mbolo de
	 * porcentaje al final.
	 *
	 * @param importe El n�mero a ser formateado.
	 * @param nDecimales El n�mero de decimales a mostrar.
	 *
	 * @return numero El n�mero ya formateado.
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
	 * M�todo que devuelve un n�mero convertido en texto bien formateado.
	 * Utiliza la coma como separador decimal. Utiliza el punto como separador
	 * de miles. Si tiene m�s decimales que los indicados realiza un redondeo
	 * hacia arriba. Realiza una llamada al otro m�todo indicando que queremos
	 * formatear un n�mero.
	 *
	 * @param cantidad El n�mero a ser formateado.
	 * @param nDecimales El n�mero de decimales a mostrar.
	 *
	 * @return numero El n�mero ya formateado.
	 */
	public static String obtenerNumeroFormateado(long cantidad, int nDecimales)
	{
		return obtenerNumeroFormateado(new BigDecimal(cantidad), nDecimales,
									   NUMERO, false);
	}
	
	/**
	 * M�todo que devuelve un n�mero convertido en texto bien formateado.
	 * Utiliza la coma como separador decimal. Utiliza el punto como separador
	 * de miles. Si tiene m�s decimales que los indicados realiza un redondeo
	 * hacia arriba. Realiza una llamada al otro m�todo indicando que queremos
	 * formatear un n�mero.
	 *
	 * @param cantidad El n�mero a ser formateado.
	 * @param nDecimales El n�mero de decimales a mostrar.
	 *
	 * @return numero El n�mero ya formateado.
	 */
	public static String obtenerNumeroFormateado(double cantidad,
												 int nDecimales)
	{
		return obtenerNumeroFormateado(new BigDecimal(cantidad), nDecimales,
									   NUMERO, false);
	}
	
	/**
	 * M�todo que devuelve un porcentaje convertido en texto bien formateado.
	 * Utiliza la coma como separador decimal. Utiliza el punto como separador
	 * de miles. Si tiene m�s decimales que los indicados realiza un redondeo
	 * hacia arriba. Realiza una llamada al otro m�todo indicando que queremos
	 * formatear un porcentaje.
	 *
	 * @param cantidad El n�mero a ser formateado.
	 * @param nDecimales El n�mero de decimales a mostrar.
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
	 * M�todo que devuelve un n�mero convertido en texto bien formateado.
	 * Utiliza la coma como separador decimal. Utiliza el punto como separador
	 * de miles. Si tiene m�s decimales que los indicados realiza un redondeo
	 * hacia arriba.
	 *
	 * @param cantidad El n�mero a ser formateado.
	 * @param nDecimales El n�mero de decimales a mostrar.
	 * @param TipoCampo Indicamos si vamos a formatear un n�mero o un
	 *                  porcentaje.
	 *
	 * @return numero El n�mero ya formateado
	 */
	public static String obtenerNumeroFormateado(BigDecimal cantidad,
												 int nDecimales, int TipoCampo,
												 boolean simboloPorcentaje)
	{
		String numero = "";
		
		// Cuando es un n�mero.
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
     * Metodo que aproxima un n�mero double o de larga extensi�n a un n�mero
     * aproximado o redondeado al n�mero de decimales espec�fico. Soluciona
     * los problemas de redondeo.
     *
     * @param cantidad El n�mero a ser aproximado.
	 * @param nDecimales El n�mero de decimales a mostrar.
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
	 * M�todo que crea un arreglo ordenado en forma descendente a partir de un
	 * arreglo que se encuentra desordenado. El algoritmo usado para el
	 * ordenamiento del arreglo es el de la burbuja.
	 *
	 * @param arreglo El arreglo de valores que est� desordenado.
	 *
	 * @return nuevo Un nuevo arreglo ordenado en forma descendente.
	 */
	public static double[] crearArregloOrdenado(double[] arreglo)
	{
		// Obtener el tama�o del arreglo.
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
	 * M�todo que obtiene un n�mero al azar dentro del rango especificado en los
	 * par�metros. La particularidad de este m�todo es que el valor que se
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
	 * M�todo que obtiene un n�mero al azar con cierto porcentaje de tendencia
	 * hacia un valor dentro del rango especificado en los par�metros. La
	 * particularidad de este m�todo es que el valor que se retorna es un n�mero
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
	 * M�todo que obtiene un n�mero al azar dentro del rango especificado en los
	 * par�metros. La particularidad de este m�todo es que el valor que se
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
	 * M�todo que obtiene un n�mero al azar con cierto porcentaje de tendencia
	 * hacia un valor dentro del rango especificado en los par�metros. La
	 * particularidad de este m�todo es que el valor que se retorna es un n�mero
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
	 * Calcula una raz�n que indica que tan cerca se encuentra de valor superior.
	 * y la vez indica que tan lejos se encuentra del valor inferior.
	 * 
	 * @param valor El valor que desea comparar.
	 * @param valorInferior La cuota inferior de valor.
	 * @param valorSuperior La cuota superior de valor.
	 * 
	 * @return razon La cercan�a entre el valor y su valor superior.
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