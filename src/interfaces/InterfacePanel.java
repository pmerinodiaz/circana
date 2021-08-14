/**
 * @(#)InterfacePanel.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Interface que tiene el propósito de unificar los métodos que se utilizan en
 * todos los paneles que tiene la aplicación. Cada método de esta interface
 * tiene un objetivo en común. Sin embargo, la diferencia de posea cada método
 * deberá especificarse en la implementación que se realize en cada una de las
 * clases que implementen a esta interface.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public interface InterfacePanel
{
	/**
	 * En este método se deben configurar todas las propiedades que tienen los
	 * paneles. Las propiedades que se cambian en este método corresponden a los
	 * atributos que se derivan de la clase JPanel.
	 */
	public void configurarPanel();
	
	/**
	 * En este método se deben configurar todos los componentes GUI que tienen
	 * los paneles. Los componentes GUI corresponden a paneles, textos, campos
	 * de texto, botones, combo box, áreas de texto, progress bar, bordes,
	 * títulos, fuentes, colores, etc. Estos componentes sirven para que el
	 * usuario pueda interactuar con la aplicación.
	 */
	public void configurarComponentes();
	
	/**
	 * En este método se deben configurar todos los eventos asociados a los
	 * paneles. Los eventos corresponden a las respuestas que debe realizar la
	 * aplicación frente a eventos ocurridos en los componentes GUI que tienen
	 * los paneles.
	 */
	public void configurarEventos();
}