/**
 * @(#)InterfaceReporte.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Interface que tiene el prop�sito de unificar los m�todos que se utilizan en
 * todos los paneles de reporte que tiene la aplicaci�n. Cada m�todo de esta
 * interface tiene un objetivo en com�n. Sin embargo, la diferencia de posea
 * cada m�todo deber� especificarse en la implementaci�n que se realize en cada
 * una de las clases que implementen a esta interface.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public interface InterfaceReporte
{
	/**
	 * En este m�todo se debe verificar si hay o no un reporte en el proyecto.
	 * En caso de haberlo, entonces se debe cargar en el �rea de texto. En caso
	 * contrario, se deben deshabilitan los botones guardar e imprimir.
	 */
	public void cargarReporte();
	
	/**
	 * En este m�todo se debe mostrar el reporte completo que se ha generado. El
	 * reporte ha de ser mostrado en un �rea de texto.
	 */
	public void mostrarReporte();
	
	/**
	 * En este m�todo se debe limpiar el reporte que se gener� antiguamente. El
	 * reporte ha de ser limpiado del �rea de texto.
	 */
	public void limpiarReporte();
	
	/**
	 * En este m�todo se deben guardar los resultados generados en la base de
	 * datos. Si existen resultados antiguos, entonces deben ser borrados.
	 * Luego, se deben insertan los resultados en las tablas de resultados que
	 * manejan los modelos.
	 */
	public void guardarReporte();
	
	/**
     * En este m�todo se debe imprimir por impresora el reporte generado. Se
     * debe llamar al m�todo del objeto que maneja la impresa y que imprime el
     * contenido del reporte.
     */
	public void imprimirReporte();
	
	/**
	 * En este m�todo se debe manejar la habilitaci�n o deshabilitaci�n de los
	 * botones para guardar e imprimir el reporte.
	 *
	 * @param habilitador Un booleano que indica si se habilita o no los botones
	 *                    guardar e imprimir.
	 */
	public void habilitarBotones(boolean habilitador);
}