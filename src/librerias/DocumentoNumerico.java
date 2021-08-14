/**
 * @(#)DocumentoNumerico.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Toolkit;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 * Clase que extiende de la clase PlainDocument. En esta clase permite
 * sobrecargar el método insertString con el objetivo de dar una estructura
 * personalizada al texto del documento.
 * 
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see Toolkit
 * @see JPlainDocument
 * @see AttributeSet
 * @see BadLocationException
 */ 
public class DocumentoNumerico extends PlainDocument
{
	/**
	 * Método sobrecargado que establece un formato decimal al texto del
	 * documento.
	 */
    public void insertString(int offs, String str, AttributeSet a)
    	throws BadLocationException
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        char[] fuente = str.toCharArray();
        char[] resultado = new char[fuente.length];
        int j = 0;
		
        for (int i = 0; i < resultado.length; i++)
        {
            if (Character.isDigit(fuente[i]) || fuente[i] == '.')
            {
            	resultado[j++] = fuente[i];
            }
            else
            {
                toolkit.beep();
            }
        }
        super.insertString(offs, new String(resultado, 0, j), a);
    }
}