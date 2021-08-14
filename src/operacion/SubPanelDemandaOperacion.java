/**
 * @(#)SubPanelDemandaOperacion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

/**
 * Clase que extiende de la clase JPanel. En esta clase se permite configurar
 * las restricciones de demanda.
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
 * @see Color
 * @see JPanel
 * @see JScrollPane
 * @see JOptionPane
 * @see TablaSwing
 * @see InterfacePanel
 */
public class SubPanelDemandaOperacion extends JPanel implements InterfacePanel
{
	/** Panel desplazable. */
	private JScrollPane barraPanel;
	
	/** Tabla para restricciones. */
	private TablaSwing tablaDemanda;
	
	/**
     * M�todo constructor del panel de restricciones de demanda.
     */
	public SubPanelDemandaOperacion()
	{
		// Configurar.
		configurarPanel();
		configurarComponentes();
	}
	
	/**
	 * M�todo en donde se configuran varias propiedades que tiene este panel.
	 * Las propiedades que se configuran corresponden a los atributos derivados
	 * de la clase JPanel y que son modificados por este m�todo.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
	 * M�todo que configura los elementos contenidos en este panel.
	 */
	public void configurarComponentes()
	{
		// titulo para la tabla
		String[] titulo = {"Punto de Demanda",
						   "Cantidad Demandada (tonelada)",
						   "Incorporar"};
		
		// Modelo personalizado
		ModeloTabla modelo = new ModeloTabla(titulo, obtenerDatosBD());
		
		// crear tabla
		tablaDemanda = new TablaSwing(modelo);
		tablaDemanda.configurarColumnasEditable(0,1,false);
		tablaDemanda.establecerAnchoColumna(0,300);
		tablaDemanda.establecerAnchoColumna(1,200);
		tablaDemanda.establecerAnchoColumna(2,100);
		tablaDemanda.establecerColorColumna(1,Color.WHITE,
									"Ingrese las toneladas demandadas");
		
		// validar campos num�ricos
		validarNumeros(tablaDemanda);
		
		// scrollpane
		barraPanel = new JScrollPane(tablaDemanda);
		
		// asignar posicion
		barraPanel.setBounds(0,0,760,120);
		
		// incorporar al panel
		add(barraPanel);
	}
	
	/**
	 * M�todo que adjunta los escuchadores eventos a los componentes GUI que
	 * tiene el subpanel de restricciones de demanda. En particular, este m�todo
	 * no se implementa, ya que no tiene eventos asociados.
	 */
	public void configurarEventos()
	{
	}
	
	/**
	 * M�todo que obtiene los datos correspondientes a las restricciones de
	 * demanda desde la base de datos.
	 *
	 * @return datos Arreglo de datos.
	 */
	private Object[][] obtenerDatosBD()
	{
		Object datos[][] = new Object[ConfiguracionAGT.n - 1][3];
		int i;
		
		for(i = 0;i < ConfiguracionAGT.n - 1;i++)
		{
			datos[i][0] = new String(ConfiguracionAGT.demanda.
									 obtenerElemento(i).obtenerDescripcion());
			datos[i][1] = new Double(ConfiguracionAGT.demanda.
									 obtenerElemento(i).obtenerCantidad());
			datos[i][2] = new Boolean(true);
		}
		
		return datos;
	}
	
	/**
	 * M�todo que configura la validaci�n de los campos con formato decimal de
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
	 * M�todo que establece las restricciones de demanda.
	 */
	public void establecerRestricciones()
	{
		int i;
		double[] vector = new double[ConfiguracionAGT.n - 1];
		String error;
		String titulo;
		String solucion;
		
		error = "";
		solucion = "{";
		try
		{
			for(i = 0; i < ConfiguracionAGT.n - 1;i++)
			{
				if(tablaDemanda.getValueAt(i,2).toString() != "true")
					vector[i] = 0;
				else
					vector[i] = Double.parseDouble("0"+
								tablaDemanda.getValueAt(i,1));
				
				solucion = solucion + vector[i] + ";";
			}
			solucion = solucion + "}";
		}
		catch(NumberFormatException msg)
		{
			error = "Debe ingresar n�meros reales";
			titulo = "Error en Restricciones de Demanda";
			JOptionPane.showMessageDialog(this,error,titulo,
										  JOptionPane.ERROR_MESSAGE);
		}
		
		if(error == "")
		{
			//System.out.println("Demanda=" + solucion);
			ConfiguracionAGT.establecerDemanda(vector);
		}
	}
}