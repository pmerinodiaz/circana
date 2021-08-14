/**
 * @(#)InterfaceMenu.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Interface que tiene el prop�sito de unificar los m�todos que se utilizan en
 * todos los menus que tiene la aplicaci�n. Cada m�todo de esta interface tiene
 * un objetivo en com�n. Sin embargo, la diferencia de posea cada m�todo deber�
 * especificarse en la implementaci�n que se realize en cada una de las clases
 * que implementen a esta interface.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public interface InterfaceMenu
{
	/**
	 * En este m�todo se deben configurar todas las propiedades que tienen los
	 * menus. Las propiedades que se cambian en este m�todo corresponden a los
	 * atributos que se derivan de la clase JMenu.
	 */
	public void configurarMenu();
	
	/**
	 * En este m�todo se deben configurar todos los componentes GUI que tienen
	 * los menus. Los componentes GUI corresponden a items, groups, etc. Estos
	 * componentes sirven para que el usuario pueda interactuar con la aplicaci�n.
	 */
	public void configurarComponentes();
	
	/**
	 * En este m�todo se deben configurar todos los eventos asociados a los
	 * menus. Los eventos corresponden a las respuestas que debe realizar la
	 * aplicaci�n frente a eventos ocurridos en los componentes GUI que tienen
	 * los menus.
	 */
	public void configurarEventos();
	
	/**
	 * En este m�todo se deben configuran las opciones del men�. Espec�ficamente
	 * se deben establecer las propiedades de habilitaci�n de los items de los
	 * menus, dependiendo de si se encuentra abierto o no un proyecto.
	 */
	public void habilitarOpciones();
}