/**
 * @(#)FormatoDecimal.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;

/**
 * Clase que extiende de la clase DefaultCellEditor. En esta clase permite
 * sobrecargar el m�todo getCellEditorValue().
 * 
 * @version 2.0 01/03/05
 * @author H�ctor D�az
 * @see DefaultCellEditor
 * @see JTextField
 */ 
public class FormatoDecimal extends DefaultCellEditor
{
	/** Campo de texto con formato personalizado. */
	private CampoNumerico formatoCampo;
	
	/**
	 * M�todo constructor de la clase.
	 */
	public FormatoDecimal(CampoNumerico textfield)
	{
		super(textfield);
		formatoCampo = textfield;
	}
	
	/**
	 * M�todo que sobreescribe el m�todo getCellEditorValue de la clase
	 * DefaultCellEditor.
	 * 
	 * @return Double Valor con formato double.
	 */	 
	public Object getCellEditorValue()
	{
	    return new Double(formatoCampo.getValueDouble());
	}
}