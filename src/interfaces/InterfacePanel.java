/**
 * @(#)InterfacePanel.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Interface que tiene el prop�sito de unificar los m�todos que se utilizan en
 * todos los paneles que tiene la aplicaci�n. Cada m�todo de esta interface
 * tiene un objetivo en com�n. Sin embargo, la diferencia de posea cada m�todo
 * deber� especificarse en la implementaci�n que se realize en cada una de las
 * clases que implementen a esta interface.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public interface InterfacePanel
{
	/**
	 * En este m�todo se deben configurar todas las propiedades que tienen los
	 * paneles. Las propiedades que se cambian en este m�todo corresponden a los
	 * atributos que se derivan de la clase JPanel.
	 */
	public void configurarPanel();
	
	/**
	 * En este m�todo se deben configurar todos los componentes GUI que tienen
	 * los paneles. Los componentes GUI corresponden a paneles, textos, campos
	 * de texto, botones, combo box, �reas de texto, progress bar, bordes,
	 * t�tulos, fuentes, colores, etc. Estos componentes sirven para que el
	 * usuario pueda interactuar con la aplicaci�n.
	 */
	public void configurarComponentes();
	
	/**
	 * En este m�todo se deben configurar todos los eventos asociados a los
	 * paneles. Los eventos corresponden a las respuestas que debe realizar la
	 * aplicaci�n frente a eventos ocurridos en los componentes GUI que tienen
	 * los paneles.
	 */
	public void configurarEventos();
}