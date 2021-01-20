/**
 * @(#)ModeloTabla.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import javax.swing.table.DefaultTableModel;
import java.lang.NullPointerException;

/**
 * Clase que extiende de DeafaultTableModel. Su objetivo principal es permitir
 * la construcción de tablas con datos personalizados.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see DefaultTableModel
 * @see NullPointerException
 */
public class ModeloTabla extends DefaultTableModel
{
	/**
     * Método constructor de ModeloTabla, inicializa la tabla con sus
     * componentes.
     *
     * @param titulos Texto que indica el nombre de la columna de la tabla.
     * @param datos Indica los datos que contiene la tabla.
     */	
    public ModeloTabla(String[] titulos, Object[][] datos)
    {
    	super(datos, titulos);
    }
	
	/**
     * Método constructor de ModeloTabla, inicializa la tabla con sus
     * componentes.
     *
     * @param datos Indica los datos que contiene la tabla.
     * @param columnas Indica las columnos que tiene la tabla.
     */	
    public ModeloTabla(Object[][] datos, int columnas)
    {
    	super.setDataVector(datos, new Object[columnas]);
    }
    
    /**
     * Método que indica que tipo de dato contiene la columna indicada.
     * 
     * @param c Indica el número de la columna.
     *
     * @return super.getValue(0,c).getClass() Indica el tipo de dato de la
     *                                        celda.
     */
    public Class getColumnClass(int c)
    {
    	Class valor = null;
    	
    	try
    	{
    		valor = super.getValueAt(0, c).getClass();
    	}
    	catch (NullPointerException ex)
    	{
    		System.out.println("Excepcion " +c+" "+ ex.getMessage());
    	}
    	
        return valor;
    }
	
	/**
	 * Método sobrecargado que cambia el valor de una celda de la tabla.
	 * 
	 * @param value Indica el nuevo valor de la celda.
	 * @param row Indica la fila en la que se ubica la celda en la tabla.
	 * @param col Indica la columna en la que se ubica la celda en la tabla.
	 */
    public void setValueAt(Object value, int row, int col)
    {
    	super.setValueAt(value,row,col);
    }
}