/**
 * @(#)EventoList.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
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
 * @author Héctor Díaz
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
	 * Método constructor que sirve para ser llamado desde el panel de abrir
	 * guardar proyecto que tiene la aplicación. En este método se inicializan
	 * los objetos que se usarán posteriormente en la clase.
	 *
	 * @param frameAbrirGuardarProyecto Es un objeto que hace referencia al
	 *                                  frame de la aplicación de donde es
	 *                                  llamada.
	 */
    public EventoList(FrameAbrirGuardarProyecto frameAbrirGuardarProyecto)
    {
    	this.frameAbrirGuardarProyecto = frameAbrirGuardarProyecto;
    }
    
	/**
	 * Método en donde se captura el evento ocurrido en una fila en la tabla, 
	 * cuando éste a sido pulsado. Se captura la fila seleccionada en la tabla
	 * y se implementa la acción a seguir.
	 *
	 * @param evento El evento en la tabla.
	 */    
    public void valueChanged(ListSelectionEvent evento)
    {
    	// Capturar la lista pulsada.
        ListSelectionModel lsm = (ListSelectionModel) evento.getSource();
        
        // Cuando el evento ocurrió en "frameAbrirGuardarProyecto".
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