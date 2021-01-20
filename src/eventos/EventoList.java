/**
 * @(#)EventoList.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */
 
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**
 * Clase que permite incorporar los eventos ocurridos al seleccionar una fila en 
 * una tabla. Esta clase implementa a la interface ListSelectionListener con el
 * objetivo de manejar los eventos ocurridos en las filas de una tabla.
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
 * @see ListSelectionModel
 * @see ListSelectionListener
 * @see ListSelectionEvent
 * @see FrameAbrirGuardarProyecto
 */
public class EventoList implements ListSelectionListener
{
	/** Para cuando es llamada por la clase PanelAbrirGuardarProyecto. */
	FrameAbrirGuardarProyecto frameAbrirGuardarProyecto;
	
	/**
	 * M�todo constructor que sirve para ser llamado desde el panel de abrir
	 * guardar proyecto que tiene la aplicaci�n. En este m�todo se inicializan
	 * los objetos que se usar�n posteriormente en la clase.
	 *
	 * @param frameAbrirGuardarProyecto Es un objeto que hace referencia al
	 *                                  frame de la aplicaci�n de donde es
	 *                                  llamada.
	 */
    public EventoList(FrameAbrirGuardarProyecto frameAbrirGuardarProyecto)
    {
    	this.frameAbrirGuardarProyecto = frameAbrirGuardarProyecto;
    }
    
	/**
	 * M�todo en donde se captura el evento ocurrido en una fila en la tabla, 
	 * cuando �ste a sido pulsado. Se captura la fila seleccionada en la tabla
	 * y se implementa la acci�n a seguir.
	 *
	 * @param evento El evento en la tabla.
	 */    
    public void valueChanged(ListSelectionEvent evento)
    {
    	// Capturar la lista pulsada.
        ListSelectionModel lsm = (ListSelectionModel) evento.getSource();
        
        // Cuando el evento ocurri� en "frameAbrirGuardarProyecto".
        if (frameAbrirGuardarProyecto != null)
        {
	        if (!lsm.isSelectionEmpty())
	        {
	            int filaSeleccionada = lsm.getMinSelectionIndex();
	            frameAbrirGuardarProyecto.establecerProyecto(filaSeleccionada);
	        }
        }
    }
}