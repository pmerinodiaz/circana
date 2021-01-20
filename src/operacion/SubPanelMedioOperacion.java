/**
 * @(#)SubPanelMedioOperacion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

/**
 * Clase que extiende de la clase JPanel. En esta clase se permite configurar
 * las restricciones de medio de transporte.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see Color
 * @see JPanel
 * @see JScrollPane
 * @see JOptionPane
 * @see TablaSwing
 * @see InterfacePanel
 */
public class SubPanelMedioOperacion extends JPanel implements InterfacePanel
{
	/** Panel desplazable. */
	private JScrollPane barraPanel;
	
	/** Tabla para restricciones. */
	private TablaSwing tablaCapacidad;
	
	/**
     * Método constructor del panel de restricciones de medio de transporte.
     */
	public SubPanelMedioOperacion()
	{
		// Configurar.
		configurarPanel();
		configurarComponentes();
	}
	
	/**
	 * Método en donde se configuran varias propiedades que tiene este panel.
	 * Las propiedades que se configuran corresponden a los atributos derivados
	 * de la clase JPanel y que son modificados por este método.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
	 * Método que configura los elementos contenidos en este panel.
	 */
	public void configurarComponentes()
	{
		// titulo para la tabla
		String[] titulo = {"Medio de Transporte",
						   "Capacidad Total (tonelada)",
						   "Incorporar"};
		
		// Modelo personalizado
		ModeloTabla modelo = new ModeloTabla(titulo, obtenerDatosBD());
		
		// crear tabla
		tablaCapacidad = new TablaSwing(modelo);
		tablaCapacidad.configurarColumnasEditable(0,2,false);
		tablaCapacidad.establecerAnchoColumna(0,300);
		tablaCapacidad.establecerAnchoColumna(1,200);
		tablaCapacidad.establecerAnchoColumna(2,100);
		tablaCapacidad.establecerColorColumna(1,Color.WHITE,"");
		
		// validar campos numéricos
		validarNumeros(tablaCapacidad);
		
		// scrollpane
		barraPanel = new JScrollPane(tablaCapacidad);
		
		// asignar posicion
		barraPanel.setBounds(0,0,760,120);
		
		// incorporar al panel
		add(barraPanel);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los componentes GUI que
	 * tiene el subpanel de restricciones de medio de transporte. En particular,
	 * este método no se implementa, ya que no tiene eventos asociados.
	 */
	public void configurarEventos()
	{
	}
	
	/**
	 * Método que obtiene los datos correspondientes a las restricciones de
	 * medio de transporte desde la base de datos.
	 *
	 * @return datos Arreglo de datos.
	 */
	private Object[][] obtenerDatosBD()
	{
		Object datos[][] = new Object[ConfiguracionAGT.l - 1][3];
		int i;
		
		for(i = 0;i < ConfiguracionAGT.l - 1;i++)
		{
			datos[i][0] = new String(ConfiguracionAGT.capacidad.
									 obtenerElemento(i).obtenerDescripcion());
			datos[i][1] = new Double(ConfiguracionAGT.capacidad.
									 obtenerElemento(i).obtenerCantidad());
			datos[i][2] = new Boolean(true);
		}
		
		return datos;
	}
	
	/**
	 * Método que configura la validación de los campos con formato decimal de
	 * una tabla.
	 *
	 * @param tabla La tabla que se quiere validar.
	 */
    private void validarNumeros(TablaSwing tabla)
    {
        // Configura el editor para ingreso de valores reales
        final CampoNumerico formatoCampo = new CampoNumerico(0);
        formatoCampo.setHorizontalAlignment(CampoNumerico.RIGHT);
		
        FormatoDecimal formatoCelda = new FormatoDecimal(formatoCampo);
        
        tabla.setDefaultEditor(Double.class, formatoCelda);
    }
	
	/**
	 * Método que establece las restricciones de medio de transporte.
	 */
	public void establecerRestricciones()
	{
		Boolean casilla = new Boolean(true);
		int i;
		double[] vector = new double[ConfiguracionAGT.l - 1];
		String error;
		String titulo;
		String solucion;
		
		error = "";
		solucion = "{";
		try
		{
			for(i = 0; i < ConfiguracionAGT.l - 1;i++)
			{
				if(tablaCapacidad.getValueAt(i,2).toString() != "true")
					vector[i] = 0;
				else
					vector[i] = Double.parseDouble("0"+
								tablaCapacidad.getValueAt(i,1));
				
				solucion = solucion + vector[i] + ";";
			}
			solucion = solucion + "}";
		}
		catch(NumberFormatException msg)
		{
			error = "Debe ingresar números reales";
			titulo = "Error en Restricciones de Medio de Transporte";
			JOptionPane.showMessageDialog(this,error,titulo,
										  JOptionPane.ERROR_MESSAGE);
		}
		
		if(error == "")
		{
			//System.out.println("Capacidad=" + solucion);
			ConfiguracionAGT.establecerCapacidad(vector);
		}
	}
}