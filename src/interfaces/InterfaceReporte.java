/**
 * @(#)InterfaceReporte.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Interface que tiene el propósito de unificar los métodos que se utilizan en
 * todos los paneles de reporte que tiene la aplicación. Cada método de esta
 * interface tiene un objetivo en común. Sin embargo, la diferencia de posea
 * cada método deberá especificarse en la implementación que se realize en cada
 * una de las clases que implementen a esta interface.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public interface InterfaceReporte
{
	/**
	 * En este método se debe verificar si hay o no un reporte en el proyecto.
	 * En caso de haberlo, entonces se debe cargar en el área de texto. En caso
	 * contrario, se deben deshabilitan los botones guardar e imprimir.
	 */
	public void cargarReporte();
	
	/**
	 * En este método se debe mostrar el reporte completo que se ha generado. El
	 * reporte ha de ser mostrado en un área de texto.
	 */
	public void mostrarReporte();
	
	/**
	 * En este método se debe limpiar el reporte que se generó antiguamente. El
	 * reporte ha de ser limpiado del área de texto.
	 */
	public void limpiarReporte();
	
	/**
	 * En este método se deben guardar los resultados generados en la base de
	 * datos. Si existen resultados antiguos, entonces deben ser borrados.
	 * Luego, se deben insertan los resultados en las tablas de resultados que
	 * manejan los modelos.
	 */
	public void guardarReporte();
	
	/**
     * En este método se debe imprimir por impresora el reporte generado. Se
     * debe llamar al método del objeto que maneja la impresa y que imprime el
     * contenido del reporte.
     */
	public void imprimirReporte();
	
	/**
	 * En este método se debe manejar la habilitación o deshabilitación de los
	 * botones para guardar e imprimir el reporte.
	 *
	 * @param habilitador Un booleano que indica si se habilita o no los botones
	 *                    guardar e imprimir.
	 */
	public void habilitarBotones(boolean habilitador);
}