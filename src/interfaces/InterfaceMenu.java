/**
 * @(#)InterfaceMenu.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Interface que tiene el propósito de unificar los métodos que se utilizan en
 * todos los menus que tiene la aplicación. Cada método de esta interface tiene
 * un objetivo en común. Sin embargo, la diferencia de posea cada método deberá
 * especificarse en la implementación que se realize en cada una de las clases
 * que implementen a esta interface.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public interface InterfaceMenu
{
	/**
	 * En este método se deben configurar todas las propiedades que tienen los
	 * menus. Las propiedades que se cambian en este método corresponden a los
	 * atributos que se derivan de la clase JMenu.
	 */
	public void configurarMenu();
	
	/**
	 * En este método se deben configurar todos los componentes GUI que tienen
	 * los menus. Los componentes GUI corresponden a items, groups, etc. Estos
	 * componentes sirven para que el usuario pueda interactuar con la aplicación.
	 */
	public void configurarComponentes();
	
	/**
	 * En este método se deben configurar todos los eventos asociados a los
	 * menus. Los eventos corresponden a las respuestas que debe realizar la
	 * aplicación frente a eventos ocurridos en los componentes GUI que tienen
	 * los menus.
	 */
	public void configurarEventos();
	
	/**
	 * En este método se deben configuran las opciones del menú. Específicamente
	 * se deben establecer las propiedades de habilitación de los items de los
	 * menus, dependiendo de si se encuentra abierto o no un proyecto.
	 */
	public void habilitarOpciones();
}