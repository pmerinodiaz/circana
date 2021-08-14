/**
 * @(#)EventoComboBox.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que permite incorporar los eventos a los combo box del tipo JComboBox
 * que se tiene en toda la aplicación. Esta clase implementa la interface
 * ActionListener, con el objetivo de poder realizar diversas acciones frente a
 * los eventos capturados de los combo box que se usan en la aplicación.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see JComboBox 
 * @see ActionListener
 * @see ActionEvent
 * @see SubPanelRestricciones
 * @see SubPanelConfiguracionAGT
 */
public class EventoComboBox implements ActionListener
{
	/** Para cuando es llamada por la clase SubPanelRestricciones. */
	private SubPanelRestricciones subpanelRestricciones;
	
	/** Para cuando es llamada por la clase SubPanelConfiguracionAGT. */
	private SubPanelConfiguracionAGT subpanelConfiguracionAGT;
	
	/**
	 * Método constructor que sirve para ser llamado desde el
	 * SubPanelRestricciones que tiene el PanelTransportarOperacion. En este
	 * método se inicializan los objetos que se usarán posteriormente en la
	 * clase.
	 *
	 * @param subpanelRestricciones Es un objeto de la clase
	 *                              SubPanelRestricciones.
	 */
	public EventoComboBox(SubPanelRestricciones subpanelRestricciones)
	{
		this.subpanelRestricciones = subpanelRestricciones;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el
	 * SubPanelConfiguracionAGT que tiene el PanelTransportarOperacion. En este
	 * método se inicializan los objetos que se usarán posteriormente en la
	 * clase.
	 *
	 * @param subpanelConfiguracionAGT Es un objeto de la clase
	 *                                 SubPanelConfiguracionAGT.
	 */
	public EventoComboBox(SubPanelConfiguracionAGT subpanelConfiguracionAGT)
	{
		this.subpanelConfiguracionAGT = subpanelConfiguracionAGT;
	}
	
	/**
	 * Método en donde se captura el evento sobre un combo box, cuando éste ha
	 * sido seleccionado. Se captura el identificador del combo box pulsado y se
	 * implementa la acción a seguir. Dependiendo de los objetos creados e
	 * inicializados en el constructor se determina desde donde fue llamado este
	 * método.
	 *
	 * @param evento El evento capturado del combo box pulsado.
	 */	
    public void actionPerformed(ActionEvent evento)
    {
        // Obtener el combo box en el cual se generó el evento.
        JComboBox combo = (JComboBox) evento.getSource();
        
        // Evento ocurrido en el "SubPanelRestricciones".
        if (subpanelRestricciones != null)
        {
        	// Evento ocurrido en el combobox "Restricciones".
        	if (combo == subpanelRestricciones.obtenerComboRestricciones())
        		subpanelRestricciones.establecerPaleta();
        	
        	// Evento ocurrido en el combobox "Fecha".
        	else
        	if (combo == subpanelRestricciones.obtenerComboFecha())
        		subpanelRestricciones.panelTransportarOperacion.
        		actualizarPaneles();
        }
        
        // Evento ocurrido en el "SubPanelConfiguracionAGT".
        else
        if(subpanelConfiguracionAGT != null)
        {
        	// Evento ocurrido en el combobox "Función de Evaluación".
        	if(combo == subpanelConfiguracionAGT.obtenerComboFuncion())
        		subpanelConfiguracionAGT.cambiarRecomendacion();
        }
    }
}