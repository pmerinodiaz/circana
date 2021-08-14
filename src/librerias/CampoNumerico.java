/**
 * @(#)CampoNumerico.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 * Clase que extiende de la clase JTexField. En esta clase se permite configurar
 * el formato num�rico de un campo.
 * 
 * @version 2.0 01/03/05
 * @author H�ctor D�az
 * @see Tollkit
 * @see NumberFormat
 * @see ParseException
 * @see Locale
 * @see JTexField
 * @see Document
 */
public class CampoNumerico extends JTextField
{
    /** Establece el formato de un n�mero aceptable por el campo de texto. */
    private NumberFormat formatoNumerico;
	
	/**
     * M�todo constructor de la clase. Establece el formato num�rico decimal de
     * acuerdo a la localidad por defecto. Este constructor trabaja con formato
     * int.
     *
     * @param valor El valor entero establecido para el cuadro de texto.
     */
    public CampoNumerico(int valor)
    {
		super();
		formatoNumerico = NumberFormat.getIntegerInstance(Locale.US);
		formatoNumerico.setParseIntegerOnly(true);
		setValue(valor);
    }
	
	/**
     * M�todo constructor de la clase. Establece el formato num�rico decimal de
     * acuerdo a la localidad por defecto. Este constructor trabaja con formato
     * double.
     *
     * @param valor El valor real establecido para el cuadro de texto.
     */
    public CampoNumerico(double valor)
    {
		super();
		formatoNumerico = NumberFormat.getNumberInstance(Locale.US);
		formatoNumerico.setParseIntegerOnly(false);
		setValue(valor);
    }
	
	/**
	 * M�todo sobrecargado que establece el valor del campo de texto como un
	 * valor con formato int.
	 *
	 * @param valor Valor entero establecido para el cuadro de texto.
	 */
    public void setValue(int valor)
    {
    	setText(formatoNumerico.format(valor));
    }
	
	/**
	 * M�todo sobrecargado que establece el valor del campo de texto como un
	 * valor con formato double.
	 *
	 * @param valor Valor real establecido para el cuadro de texto.
	 */
    public void setValue(double valor)
    {
    	setText(formatoNumerico.format(valor));
    }
	
	/**
	 * M�todo que devuelve el valor del campo de texto como un valor con formato
	 * int.
	 *
	 * @return valor Valor entero contenido en el cuadro de texto.
	 */
    public int getValueInt()
    {
        int valor = 0;
        
        try
        {
        	valor = formatoNumerico.parse(getText()).intValue();
        }
        catch (ParseException e)
        {
        	Toolkit.getDefaultToolkit().beep();
        }
        
        return valor;
    }
	
	/**
	 * M�todo que devuelve el valor del campo de texto como un valor con formato
	 * double.
	 *
	 * @return valor Valor real contenido en el cuadro de texto.
	 */
    public double getValueDouble()
    {
        double valor = 0;
        
        try
        {
        	valor = formatoNumerico.parse(getText()).doubleValue();
        }
        catch (ParseException e)
        {
        	Toolkit.getDefaultToolkit().beep();
        }
        
        return valor;
    }
	
	/**
	 * M�todo sobrecargado que crea un modelo con formato para el cuadro de
	 * texto.
	 *
	 * @return DocumentoNumerico Instancia de PlainDocument.
	 */
    protected Document createDefaultModel()
    {
        return new DocumentoNumerico();
    }
}