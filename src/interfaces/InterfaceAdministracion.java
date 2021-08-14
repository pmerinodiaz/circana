/**
 * @(#)InterfaceAdministracion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Interface que tiene el propósito de unificar los métodos que se utilizan en
 * todos los frames de administración de datos que tiene la aplicación. Cada
 * método de esta interface tiene un objetivo en común. Sin embargo, la
 * diferencia que posea cada método deberá especificarse en la implementación
 * que se realize en cada una de las clases que implementen a esta interface.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 */
public interface InterfaceAdministracion
{
	/**
	 * En este método se debe limpiar el frame de administración de datos. Lo
	 * relevante de este método es que cuando se llama al método show para
	 * mostrar un frame, debe llamarse antes a este método para que limpie los
	 * contenidos ingresados anteriormente por el usuario.
	 */
	public void limpiar();
	
	/**
	 * En este método se debe cerrar el frame de administración de datos. Debe
	 * cerrarse como si se terminara la aplicación principal. Es decir, con un
	 * exit.
	 */
	public void cancelar();
	
	/**
	 * En este método se deben guardar en la base de datos los datos ingresados
	 * en el formulario, previamente habiéndolos validado.
	 */
	public void guardar();
	
	/**
     * En este método se debe validar el ingreso de datos. Por cada error
     * cometido en los datos, se debe agregar un mensaje al string del error. Un
     * error se evalúa según los tipos de datos y los rangos de cada item del
     * formulario de ingreso de datos. Finalmente la lista de errores es
     * devuelta por el método en forma de string.
     * 
     * @return error String con los errores cometidos en el ingreso de datos.
     */
	public String validar();
}