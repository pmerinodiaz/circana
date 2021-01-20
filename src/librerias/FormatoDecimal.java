/**
 * @(#)FormatoDecimal.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;

/**
 * Clase que extiende de la clase DefaultCellEditor. En esta clase permite
 * sobrecargar el método getCellEditorValue().
 * 
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see DefaultCellEditor
 * @see JTextField
 */ 
public class FormatoDecimal extends DefaultCellEditor
{
	/** Campo de texto con formato personalizado. */
	private CampoNumerico formatoCampo;
	
	/**
	 * Método constructor de la clase.
	 */
	public FormatoDecimal(CampoNumerico textfield)
	{
		super(textfield);
		formatoCampo = textfield;
	}
	
	/**
	 * Método que sobreescribe el método getCellEditorValue de la clase
	 * DefaultCellEditor.
	 * 
	 * @return Double Valor con formato double.
	 */	 
	public Object getCellEditorValue()
	{
	    return new Double(formatoCampo.getValueDouble());
	}
}