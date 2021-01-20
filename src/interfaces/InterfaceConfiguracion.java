/**
 * @(#)InterfaceConfiguracion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Interface que tiene el prop�sito de unificar los m�todos que se utilizan en
 * todos los paneles de configuraci�n que tiene la aplicaci�n. Cada m�todo de
 * esta interface tiene un objetivo en com�n. Sin embargo, la diferencia que
 * posea cada m�todo deber� especificarse en la implementaci�n que se realize en
 * cada una de las clases que implementen a esta interface.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public interface InterfaceConfiguracion
{
	/**
	 * En este m�todo se debe cargar la configuraci�n desde la clase est�tica
	 * correspondiente al panel de configuraci�n. Los valores son tomados desde
	 * la clase est�tica y son llevados a los componentes GUI que tiene el panel
	 * de configuraci�n.
	 */
	public void cargarConfiguracion();
	
	/**
	 * En este m�todo se debe establecer la configuraci�n por defecto. Se eligen
	 * y setean los valores de los componentes GUI que tiene el panel de
	 * configuraci�n con los valores por defecto. Finalmente, se debe notificar
	 * los cambios efectuados en el proyecto.
	 */
	public void restaurarConfiguracion();
	
	/**
	 * En este m�todo se debe establecer los valores actuales de la
	 * configuraci�n, contenidos en la clase est�tica y que fueron aplicados por
	 * el usuario en el panel de configuraci�n. En este m�todo se cambian los
	 * valores de los atributos de las clases est�ticas. Finalmente, se debe
	 * notificar los cambios efectuados en el proyecto.
	 */
	public void establecerConfiguracion();
	
	/**
     * En este m�todo se debe validar la configuraci�n establecida. Por cada
     * error cometido en los datos, se debe agregar un mensaje al string del
     * error. Un error se eval�a seg�n los tipos de datos y los rangos de cada
     * item de la configuraci�n.
     * 
     * @return error String con los errores cometidos en la configuraci�n.
     */
	public String validarConfiguracion();
	
	/**
     * En este m�todo se debe manejar la habilitaci�n o deshabilitaci�n de los
     * botones restaurar y establecer de de la configuraci�n.
     *
     * @param habilitado Inidica si los botones se habilitan o deshabilitan.
     */
    public void habilitarBotones(boolean habilitado);
}