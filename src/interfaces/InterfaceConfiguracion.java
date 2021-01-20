/**
 * @(#)InterfaceConfiguracion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Interface que tiene el propósito de unificar los métodos que se utilizan en
 * todos los paneles de configuración que tiene la aplicación. Cada método de
 * esta interface tiene un objetivo en común. Sin embargo, la diferencia que
 * posea cada método deberá especificarse en la implementación que se realize en
 * cada una de las clases que implementen a esta interface.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public interface InterfaceConfiguracion
{
	/**
	 * En este método se debe cargar la configuración desde la clase estática
	 * correspondiente al panel de configuración. Los valores son tomados desde
	 * la clase estática y son llevados a los componentes GUI que tiene el panel
	 * de configuración.
	 */
	public void cargarConfiguracion();
	
	/**
	 * En este método se debe establecer la configuración por defecto. Se eligen
	 * y setean los valores de los componentes GUI que tiene el panel de
	 * configuración con los valores por defecto. Finalmente, se debe notificar
	 * los cambios efectuados en el proyecto.
	 */
	public void restaurarConfiguracion();
	
	/**
	 * En este método se debe establecer los valores actuales de la
	 * configuración, contenidos en la clase estática y que fueron aplicados por
	 * el usuario en el panel de configuración. En este método se cambian los
	 * valores de los atributos de las clases estáticas. Finalmente, se debe
	 * notificar los cambios efectuados en el proyecto.
	 */
	public void establecerConfiguracion();
	
	/**
     * En este método se debe validar la configuración establecida. Por cada
     * error cometido en los datos, se debe agregar un mensaje al string del
     * error. Un error se evalúa según los tipos de datos y los rangos de cada
     * item de la configuración.
     * 
     * @return error String con los errores cometidos en la configuración.
     */
	public String validarConfiguracion();
	
	/**
     * En este método se debe manejar la habilitación o deshabilitación de los
     * botones restaurar y establecer de de la configuración.
     *
     * @param habilitado Inidica si los botones se habilitan o deshabilitan.
     */
    public void habilitarBotones(boolean habilitado);
}