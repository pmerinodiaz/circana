/**
 * @(#)InterfaceAdministracion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Interface que tiene el prop�sito de unificar los m�todos que se utilizan en
 * todos los frames de administraci�n de datos que tiene la aplicaci�n. Cada
 * m�todo de esta interface tiene un objetivo en com�n. Sin embargo, la
 * diferencia que posea cada m�todo deber� especificarse en la implementaci�n
 * que se realize en cada una de las clases que implementen a esta interface.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public interface InterfaceAdministracion
{
	/**
	 * En este m�todo se debe limpiar el frame de administraci�n de datos. Lo
	 * relevante de este m�todo es que cuando se llama al m�todo show para
	 * mostrar un frame, debe llamarse antes a este m�todo para que limpie los
	 * contenidos ingresados anteriormente por el usuario.
	 */
	public void limpiar();
	
	/**
	 * En este m�todo se debe cerrar el frame de administraci�n de datos. Debe
	 * cerrarse como si se terminara la aplicaci�n principal. Es decir, con un
	 * exit.
	 */
	public void cancelar();
	
	/**
	 * En este m�todo se deben guardar en la base de datos los datos ingresados
	 * en el formulario, previamente habi�ndolos validado.
	 */
	public void guardar();
	
	/**
     * En este m�todo se debe validar el ingreso de datos. Por cada error
     * cometido en los datos, se debe agregar un mensaje al string del error. Un
     * error se eval�a seg�n los tipos de datos y los rangos de cada item del
     * formulario de ingreso de datos. Finalmente la lista de errores es
     * devuelta por el m�todo en forma de string.
     * 
     * @return error String con los errores cometidos en el ingreso de datos.
     */
	public String validar();
}