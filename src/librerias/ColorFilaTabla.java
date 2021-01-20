/**
 * @(#)ColorFilaTabla.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Clase que extiende de la clase DefaultTableCellRenderer. En esta clase se
 * permite cambiar el color de fondo de una fila en una tabla.
 * 
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see Color
 * @see Component
 * @see JTable
 * @see DefaultTableCellRenderer
 */
public class ColorFilaTabla extends DefaultTableCellRenderer
{
	/** Indica la posicion de la fila en la tabla. */
    private int filaSeleccionada;
    
    /** Indica el color de fondo de la fila establecida. */
    private Color colorFondo;
	
    /** Indica el color de fondo de la fila establecida. */
    private String tooltips;
    
	/**
	 * Método constructor que realiza una llamada al constructor de la
	 * superclase.
	 */
    public ColorFilaTabla(Color color, String ayuda)
    {
   		super();
   		filaSeleccionada = -1;
   		colorFondo = color;
   		tooltips = ayuda;
    }
	
	/**
	 * Método que indica la fila a la que se le cambiará el color de fondo.
	 *
	 * @param fila Posición dentro de la tabla.
	 */
    public void establecerColorFila(int fila)
    {
    	filaSeleccionada = fila;
    }
	
	/**
	 * Método sobrecargado que retorna el componente utilizado para dibujar la
	 * celda de la tabla. En este método se establece el cambio de color para
	 * la fila establecida con distinto color de fondo. Además se le agrega un
	 * tooltips como ayuda.
	 */
    public Component getTableCellRendererComponent(JTable table, Object value,
    				boolean isSelected, boolean hasFocus, int row, int column)
    {
    	if (row == filaSeleccionada)
        {
        	this.setBackground(colorFondo);
        	this.setToolTipText(tooltips);
        }
        else
        {
         	this.setBackground(table.getBackground());
         	this.setToolTipText("");
        }
        
    	this.setHorizontalAlignment(this.RIGHT);
        Component componente = super.getTableCellRendererComponent(table,value,
        										isSelected,hasFocus,row,column);
        return componente;
    }
}