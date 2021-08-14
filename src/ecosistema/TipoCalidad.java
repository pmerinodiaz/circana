/**
 * @(#)TipoCalidad.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa los tipos de calidades de extracción de los recursos
 * pesqueros. Esta clase contiene solamente atributos estáticos.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public class TipoCalidad
{
	/** El valor que indica que el tipo de calidad es muy baja. */
	public static final int MUY_BAJA = 0;
	
	/** El valor que indica que el tipo de calidad es baja. */
	public static final int BAJA = 1;
	
	/** El valor que indica que el tipo de calidad es media. */
	public static final int MEDIA = 2;
	
	/** El valor que indica que el tipo de calidad es alta. */
	public static final int ALTA = 3;
	
	/** El valor que indica que el tipo de calidad es muy alta. */
	public static final int MUY_ALTA = 4;
	
	/** Valor para la calidad considerada como muy baja. */
	public static final double CALIDAD_MUY_BAJA = 0;
	
	/** Valor para la calidad considerada como baja. */
	public static final double CALIDAD_BAJA = 0.25;
	
	/** Valor para la calidad considerada como media. */
	public static final double CALIDAD_MEDIA = 0.5;
	
	/** Valor para la calidad considerada como alta. */
	public static final double CALIDAD_ALTA = 0.75;
	
	/** Valor para la calidad considerada como muy alta. */
	public static final double CALIDAD_MUY_ALTA = 1;
}