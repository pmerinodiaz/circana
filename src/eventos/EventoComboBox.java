/**
 * @(#)EventoComboBox.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que permite incorporar los eventos a los combo box del tipo JComboBox
 * que se tiene en toda la aplicaci�n. Esta clase implementa la interface
 * ActionListener, con el objetivo de poder realizar diversas acciones frente a
 * los eventos capturados de los combo box que se usan en la aplicaci�n.
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
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
	 * M�todo constructor que sirve para ser llamado desde el
	 * SubPanelRestricciones que tiene el PanelTransportarOperacion. En este
	 * m�todo se inicializan los objetos que se usar�n posteriormente en la
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
	 * M�todo constructor que sirve para ser llamado desde el
	 * SubPanelConfiguracionAGT que tiene el PanelTransportarOperacion. En este
	 * m�todo se inicializan los objetos que se usar�n posteriormente en la
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
	 * M�todo en donde se captura el evento sobre un combo box, cuando �ste ha
	 * sido seleccionado. Se captura el identificador del combo box pulsado y se
	 * implementa la acci�n a seguir. Dependiendo de los objetos creados e
	 * inicializados en el constructor se determina desde donde fue llamado este
	 * m�todo.
	 *
	 * @param evento El evento capturado del combo box pulsado.
	 */	
    public void actionPerformed(ActionEvent evento)
    {
        // Obtener el combo box en el cual se gener� el evento.
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
        	// Evento ocurrido en el combobox "Funci�n de Evaluaci�n".
        	if(combo == subpanelConfiguracionAGT.obtenerComboFuncion())
        		subpanelConfiguracionAGT.cambiarRecomendacion();
        }
    }
}