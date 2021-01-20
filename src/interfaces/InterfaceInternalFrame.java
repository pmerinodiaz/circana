/**
 * @(#)InterfaceInternalFrame.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Interface que tiene el propósito de unificar los métodos que se utilizan en
 * todos los internal frames que tiene la aplicación. Cada método de esta
 * interface tiene un objetivo en común. Sin embargo, la diferencia de posea
 * cada método deberá especificarse en la implementación que se realize en cada
 * una de las clases que implementen a esta interface.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public interface InterfaceInternalFrame
{
	/**
	 * En este método se deben inicializar todos los objetos que se usan en los
	 * internal frames. Estos objetos corresponden a los elementos especiales
	 * que tienen los internal frames y no corresponden a sus componentes GUI.
	 */
	public void configurarElementosEspeciales();
	
	/**
	 * En este método se deben configurar todas las propiedades que tienen los
	 * internal frames. Las propiedades que se cambian en este método
	 * corresponden a los atributos que se derivan de la clase JInternalFrame.
	 */
	public void configurarFrame();
	
	/**
	 * En este método se deben configurar todos los componentes GUI que tienen
	 * los internal frames. Los componentes GUI corresponden a paneles, textos,
	 * campos de texto, botones, combo box, áreas de texto, progress bar,
	 * bordes, títulos, fuentes, colores, etc. Estos componentes sirven para que
	 * el usuario pueda interactuar con la aplicación.
	 */
	public void configurarComponentes();
	
	/**
	 * En este método se debe cargar la información que está disponible en las
	 * diversas configuraciones que se encuentran en el proyecto. Lo importante
	 * en este método es que carga la configuración del proyecto en los internal
	 * frames.
	 */
	public void cargarConfiguracion();
	
	/**
     * En este método se debe cargar la información con respecto al proyecto que
     * se encuentra en la clase estática Proyecto. De esta manera, se actualizan
     * las opciones de los frames que se encuentran relacionadas con el
     * proyecto.
     */
	public void actualizarInformacionProyecto();
}