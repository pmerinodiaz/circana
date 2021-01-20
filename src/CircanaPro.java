/**
 * @(#)CircanaPro.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import javax.swing.JOptionPane;
import javax.swing.JFrame;

/**
 * Clase que contiene el main principal de la aplicaci�n. Esta clase contiene el
 * m�todo en el cual se crea un objeto de la clase FrameCircanaPro y luego se
 * despliega en pantalla el frame principal de la aplicaci�n. En caso de que la
 * aplicaci�n CIRCANA Pro no inicie (Por lo general, no se inicia debido a un
 * problema en la conexi�n a la base de datos), entonces se recurre a una
 * aplicaci�n secundaria que entrega la posibilidad de que el usuario pueda
 * configurar la base de datos.
 *
 * @version 2.0 01/01/05
 * @author Patricio Merino
 * @see JOptionPane
 * @see JFrame
 * @see FrameCircanaPro
 * @see FrameConfiguracionServidor
 */
public class CircanaPro
{
	/**
	 * Main principal en donde se genera la aplicaci�n. Se crea un objeto de la
	 * clase FrameCircanaPro y despu�s se despliega en pantalla el frame. En
	 * caso de no iniciarse la aplicaci�n, entonces se ejecuta una aplicaci�n
	 * secundaria para que el usuario intente solucionar el problema.
	 *
	 * @param args El conjunto de entradas externas (de tipo texto) que tiene la
	 *             aplicaci�n.
	 */
	public static void main(String[] args)
	{
		// Tratar de crear la aplicaci�n y mostrarla.
		try
		{
			FrameCircanaPro frameCircanaPro = new FrameCircanaPro();
			frameCircanaPro.setVisible(true);
		}
		
		// Cuando no se puede crear la aplicaci�n.
		catch (Exception excepcion)
		{
			// Mostrar un mensaje de error.
			JOptionPane.showMessageDialog(new JFrame(),
				"Por favor, configure su servidor de base de datos MySQL.",
				"No se pudo iniciar CIRCANA Pro 2.0",
				JOptionPane.ERROR_MESSAGE);
			
			// Mostrar el frame para configuraci�n del servidor.
			FrameConfiguracionServidor frameConfiguracionServidor =
				new FrameConfiguracionServidor();
			frameConfiguracionServidor.setVisible(true);
		}
	}
}