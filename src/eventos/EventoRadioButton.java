/**
 * @(#)EventoRadioButton.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

/**
 * Clase que permite incorporar los eventos a las opciones radio button del tipo
 * JRadioButton que tiene la aplicación. Esta clase implementa a la interface
 * ActionListener, con el objetivo de poder realizar diversas acciones frente a
 * los eventos capturados de las opciones de los JRadioButton de la aplicación.
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see ActionListener
 * @see ActionEvent
 * @see JRadioButton
 * @see PanelEntrenarEconomia
 */
public class EventoRadioButton implements ActionListener
{
	/** Para cuando es llamada por la clase PanelEntrenarEconomia. */
	private PanelEntrenarEconomia panelEntrenarEconomia;
	
	/**
	 * Método constructor que sirve para ser llamado desde el
	 * PanelEntrenarEconomia que tiene el frameEconomia.
	 *
	 * @param panelEntrenarEconomia Es un objeto de la clase
	 *                              PanelEntrenarEconomia.
	 */
	public EventoRadioButton(PanelEntrenarEconomia panelEntrenarEconomia)
	{
		this.panelEntrenarEconomia = panelEntrenarEconomia;
	}
	
	/**
	 * Método en donde se captura el evento sobre una opción de RadioButton, 
	 * cuando ésta a sido elegida. Se capura el identificador de la opción 
	 * del RadioButton elegida y se implementa la acción a seguir.
	 *
	 * @param evento El evento capturado de la opción del RadioButtonElegida.
	 */
	public void actionPerformed(ActionEvent evento)
	{
		// Obtener la opción del radio Button
		JRadioButton opcion = (JRadioButton) evento.getSource();
		
		// Evento ocurrido en el "PanelEntrenarEconomia".
		if (panelEntrenarEconomia != null)
		{
			// Evento ocurrido en el item "BP".
			if (opcion == panelEntrenarEconomia.obtenerItemBP())
				panelEntrenarEconomia.frameEconomia.seleccionarRed(0);
			
			// Evento ocurrido en el item "RBF".
			else
			if (opcion == panelEntrenarEconomia.obtenerItemRBF())
				panelEntrenarEconomia.frameEconomia.seleccionarRed(1);
		}
	}
}