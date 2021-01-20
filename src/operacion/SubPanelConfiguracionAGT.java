/**
 * @(#)SubPanelConfiguracionAGT.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que extiende de la clase JPanel. En esta clase se permite configurar
 * el algoritmo genético de transporte. 
 * 
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see Color
 * @see Font
 * @see ResultSet
 * @see SQLException
 * @see JPanel
 * @see JLabel
 * @see JComboBox
 * @see JOptionPane
 * @see JButton
 * @see ImageIcon
 * @see SwingConstants
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see CampoNumerico
 * @see PanelConfiguracionOperacion
 * @see InterfacePanel
 * @see InterfaceConfiguracion
 */
public class SubPanelConfiguracionAGT extends JPanel
	implements InterfacePanel, InterfaceConfiguracion
{
	/** Panel que se hace referencia al creador de este objeto. */
	public PanelConfiguracionOperacion panelConfiguracionOperacion;
	
	/** Cuadro de texto para el ingreso del tamaño de la población. */
	private CampoNumerico campoTamano;
	
	/** Cuadro de texto para el ingreso del número de generaciones. */
	private CampoNumerico campoGeneraciones;
	
	/** Cuadro de texto para el ingreso de la probabilidad de cruce. */
	private CampoNumerico campoProbCruce;
	
	/** Cuadro de texto para el ingreso de la probabilidad de mutación. */
	private CampoNumerico campoProbMutacion;
	
	/** Etiqueta con recomendación de la probabilidad de cruce. */
	private	JLabel recomendacionProbCruce;
	
	/** Etiqueta con recomendación de la probabilidad de mutación. */
	private JLabel recomendacionProbMutacion;;
	
	/** ComboBox para la selección de la función de evaluación. */
	private JComboBox comboFuncion;
	
	/** ComboBox para la selección de la técnica de selección de individuos. */
	private JComboBox comboSeleccion;
		
	/**
     * Método constructor del panel de configuracion del algoritmo genético de
     * transporte.
     *
     * @param panelConfiguracionOperacion El panel creador de este sub-panel.
     * @param x Inicio X del sub-panel.
     * @param y Inicio Y del sub-panel.
     * @param ancho Ancho del sub-panel.
     * @param largo Largo del sub-panel.
     */
	public SubPanelConfiguracionAGT(
						PanelConfiguracionOperacion panelConfiguracionOperacion,
						int x, int y, int ancho, int largo)
	{
		// Inicializar el puntero.
		this.panelConfiguracionOperacion = panelConfiguracionOperacion;
		
		setBounds(x,y,ancho,largo);
		
		// Configurar.
		configurarPanel();
		configurarComponentes();
		configurarEventos();
	}
	
	/**
     * Método donde se configura el título y borde del panel de configuración
     * del algoritmo genético.
     */
	public void configurarPanel()
	{		
		// borde del panel
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// titulo
		TitledBorder titulo = new TitledBorder(borde,
			" Configuración del Algoritmo Genético de Transporte ",
			TitledBorder.LEFT, TitledBorder.TOP);
		
		// agrega el título
		setBorder(titulo);
		setLayout(null);
	}
	
	/**
	 * Método en donde se configuran los componentes GUI del panel de
	 * configuración del algoritmo genético de transporte.
	 */
	public void configurarComponentes()
	{
		configurarTexto();
		configurarTextField();
		configurarComboBox();
	}
	
	/**
     * Método donde se configura los textos del panel de configuración del
     * algoritmo genético de transporte.
     */
	private void configurarTexto()
	{
		// creando fuente para la recomendación
		Font fuenteRecomendacion = new Font("Arial", Font.PLAIN, 9);
		
		// crear los textos
		JLabel[] textoExplicacion = new JLabel[4];
		textoExplicacion[0] =
			new JLabel(new ImageIcon("../img/operacion_configuracion_agt.gif"));
		textoExplicacion[1] =
			new JLabel("Puede ingresar los principales parámetros de " +
			"configuración para el Algoritmo Genético que resuelve el");
		textoExplicacion[2] =
			new JLabel("problema de transporte. Pruebe el desempeño de " +
			"la optimización del algoritmo genético cambiando los");
		textoExplicacion[3] =
			new JLabel("distintos parámetros de configuración.");
		JLabel textoTamano = new JLabel("Tamaño de la Población:");
		JLabel textoGeneraciones = new JLabel("Número de Generaciones:");
		JLabel textoProbCruce = new JLabel("Probabilidad de Cruce:");
		JLabel textoProbMutacion = new JLabel("Probabilidad de Mutación:");
		JLabel textoFuncion = new JLabel("Función de Evaluación:");
		JLabel textoSeleccion = new JLabel("Técnica de Selección:");
		
		// crear textos de recomendación
		JLabel recomendacionTamano =
			new JLabel("Un valor mayor a 10",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionGeneraciones =
			new JLabel("Un valor mayor a 100",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		recomendacionProbCruce =
			new JLabel("Un valor menor a 0.1",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		recomendacionProbMutacion =
			new JLabel("Un valor entre [0.2, 0.5]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionFuncion =
			new JLabel("Lineal a Trozos",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionSeleccion =
			new JLabel("Torneo",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		
		// posicionar textos
		textoExplicacion[0].setBounds(20, 25, 135, 50);
		textoExplicacion[1].setBounds(165, 25, 710, 15);
		textoExplicacion[2].setBounds(165, 40, 710, 15);
		textoExplicacion[3].setBounds(165, 55, 710, 15);
		textoTamano.setBounds(20,90,190,20);
		textoGeneraciones.setBounds(20,120,190,20);
		textoProbCruce.setBounds(20,150,190,20);
		textoProbMutacion.setBounds(20,180,190,20);
		textoFuncion.setBounds(20,210,190,20);
		textoSeleccion.setBounds(20,240,190,20);
		
		// posicionar recomendaciones
		recomendacionTamano.setBounds(350,90,300,20);
		recomendacionGeneraciones.setBounds(350,120,300,20);
		recomendacionProbCruce.setBounds(350,150,300,20);
		recomendacionProbMutacion.setBounds(350,180,300,20);
		recomendacionFuncion.setBounds(350,210,300,20);
		recomendacionSeleccion.setBounds(350,240,300,20);
		
		// asignar fuente a recomendaciones
		recomendacionTamano.setFont(fuenteRecomendacion);
		recomendacionGeneraciones.setFont(fuenteRecomendacion);
		recomendacionProbCruce.setFont(fuenteRecomendacion);
		recomendacionProbMutacion.setFont(fuenteRecomendacion);
		recomendacionFuncion.setFont(fuenteRecomendacion);
		recomendacionSeleccion.setFont(fuenteRecomendacion);
		
		// agregar textos al panel de configuración
		add(textoExplicacion[0]);
		add(textoExplicacion[1]);
		add(textoExplicacion[2]);
		add(textoExplicacion[3]);
		add(textoTamano);
		add(textoGeneraciones);
		add(textoProbCruce);
		add(textoProbMutacion);
		add(textoFuncion);
		add(textoSeleccion);
		
		// agregar recomendaciones al panel de configuración
		add(recomendacionTamano);
		add(recomendacionGeneraciones);
		add(recomendacionProbCruce);
		add(recomendacionProbMutacion);
		add(recomendacionFuncion);
		add(recomendacionSeleccion);
	}
	
	/**
     * Método donde se configuran las cajas de entrada de datos del algoritmo
     * genético de transporte.
     */
	private void configurarTextField()
	{
		// crear cuadros de texto
		campoTamano = new CampoNumerico(0);
		campoGeneraciones = new CampoNumerico(0);
		campoProbCruce = new CampoNumerico(0.0);
		campoProbMutacion = new CampoNumerico(0.0);
		
		// posicionar textos
		campoTamano.setBounds(200,90,130,20);
		campoGeneraciones.setBounds(200,120,130,20);
		campoProbCruce.setBounds(200,150,130,20);
		campoProbMutacion.setBounds(200,180,130,20);
		
		// asignar tips
		campoTamano.setToolTipText(
			"Ingrese el tamaño de la población");
		campoGeneraciones.setToolTipText(
			"Ingrese la cantidad de ciclos de evolución");
		campoProbCruce.setToolTipText(
			"Ingrese la probabilidad de cruce de individuos");
		campoProbMutacion.setToolTipText(
			"Ingrese la probabilidad de mutación de un indiviudo");
		
		// agregar los campos de texto al panel de configuración
		add(campoTamano);
		add(campoGeneraciones);
		add(campoProbCruce);
		add(campoProbMutacion);
	}
	
	/**
     * Método donde se configuran los combobox del algoritmo genético de
     * transporte.
     */
	private void configurarComboBox()
	{
		// crear combos boxs
		comboFuncion = new JComboBox();
		comboSeleccion = new JComboBox();
		
		// agrega los items desde la BD
		cargarComboFuncionEvaluacion();
		cargarComboTecnicaSeleccion();
		
		// posicionar combos
		comboFuncion.setBounds(200,210,130,20);
		comboSeleccion.setBounds(200,240,130,20);
		
		// asignar tips
		comboFuncion.setToolTipText(
			"Seleccionar la función de costos transporte");
		comboSeleccion.setToolTipText(
			"Seleccionar la técnica de selección");
		
		// agregar combos al panel de configuración
		add(comboFuncion);
		add(comboSeleccion);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los combobox que tiene el 
	 * subpanel de configuracion AGT. En particular se incorpora el escuchador 
	 * de eventos del tipo EventoComboBox a los JComboBox que tiene el panel.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoComboBox eventoCombo = new EventoComboBox(this);
		
		// Incorporar el escuchador de eventos a los combobox.
		comboFuncion.addActionListener(eventoCombo);
	}
	
	/**
     * Método que carga el combo de la función de evaluación.
     */
	private void cargarComboFuncionEvaluacion()
	{
		String condicion;
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		BaseDatoTablaBasica tablaFuncionEvaluacion =
			new BaseDatoTablaBasica(conexion,"funcion_evaluacion");
		
		comboFuncion.removeAllItems();
		
		conexion.conectar();
		
		condicion = "codigo_funcion_evaluacion >= 0 " +
					"order by codigo_funcion_evaluacion";
		resultado = tablaFuncionEvaluacion.buscarCondicion(condicion);
		
		try
		{
			resultado.first();
			do
			{
				comboFuncion.addItem(
					resultado.getString("descripcion_funcion_evaluacion"));
				resultado.next();
			}
			while(!resultado.isAfterLast());
		}
		catch(SQLException ex)
		{
			System.out.println("Error al cargar la funcion de evaluacion.");
		}
		
		conexion.desconectar();
	}
	
	/**
     * Método que carga el combo de la técnica de selección.
     */
	private void cargarComboTecnicaSeleccion()
	{
		String condicion;
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		BaseDatoTablaBasica tablaTecnicaSeleccion =
			new BaseDatoTablaBasica(conexion,"tecnica_seleccion");
		
		comboSeleccion.removeAllItems();
		
		conexion.conectar();
		
		condicion = "codigo_tecnica_seleccion >= 0 " +
					"order by codigo_tecnica_seleccion";
		resultado = tablaTecnicaSeleccion.buscarCondicion(condicion);
		
		try
		{
			resultado.first();
			do
			{
				comboSeleccion.addItem(
					resultado.getString("descripcion_tecnica_seleccion"));
				resultado.next();
			}
			while(!resultado.isAfterLast());
		}
		catch(SQLException ex)
		{
			System.out.println("Error al cargar la tecnica de seleccion");
		}
		
		conexion.desconectar();
	}
	
	/**
     * Método que carga los valores de configuración actuales del algoritmo
     * genético de transporte.
     */
	public void cargarConfiguracion()
	{
		// carga los valores de configuración actuales.
		campoTamano.setValue(ConfiguracionAGT.tamanoPoblacion);
		campoGeneraciones.setValue(ConfiguracionAGT.numeroGeneraciones);
		campoProbCruce.setValue(ConfiguracionAGT.probabilidadCruce);
		campoProbMutacion.setValue(ConfiguracionAGT.probabilidadMutacion);
		comboFuncion.setSelectedIndex(ConfiguracionAGT.tipoFuncion);
		comboSeleccion.setSelectedIndex(ConfiguracionAGT.tecnicaSeleccion);
	}
	
	/**
     * Método que carga los valores por defecto del algoritmo genético de
     * transporte.
     */
	public void restaurarConfiguracion()
	{
		// cargar valores por defecto.
		ConfiguracionAGT.cargar(Proyecto.CODIGO_NUEVO);
		cargarConfiguracion();
	}
	
	/**
     * Método que establece los nuevos valores de configuración ingresados
     * en el panel para los parámetros del algoritmo genético de transporte.
     * Verificando si es correcta la información ingresada.
     */
	public void establecerConfiguracion()
	{
		String error = validarConfiguracion();
		
		if(error == "")
		{
			double[] parametros = new double[6];
			
			parametros[0] = campoTamano.getValueInt();
			parametros[1] = campoGeneraciones.getValueInt();
			parametros[2] = campoProbCruce.getValueDouble();
			parametros[3] = campoProbMutacion.getValueDouble();
			parametros[4] = comboFuncion.getSelectedIndex();
			parametros[5] = comboSeleccion.getSelectedIndex();
			
			// Establecer los nuevos parámetros
			ConfiguracionAGT.establecerParametros(parametros);
		}
		else
		{
			error = "Se han cometido los siguientes errores:\n" + error;
				JOptionPane.showMessageDialog(this, error,
				"Error en la configuración",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
     * Método que verifica si los datos ingresados en la configuración tienen
     * una sintaxis correcta.
     *
     * @return error Retorna un string con el error cometido.
     */
	public String validarConfiguracion()
	{
		String error = "";
		
		// validando campo tamaño de la población
		if (!Servicio.esNumero(campoTamano.getText()) ||
			campoTamano.getValueInt() <= 0 ||
			Servicio.contarCaracter(campoTamano.getText(), '.') > 0)
			error += "- En el tamaño de la población del algoritmo genético " +
					 "de transporte.\n";
		
		// validando campo número de generaciones
		if (!Servicio.esNumero(campoGeneraciones.getText()) ||
			campoGeneraciones.getValueInt() <= 0 ||
			Servicio.contarCaracter(campoGeneraciones.getText(), '.') > 0)
			error += "- En el número de generaciones del algoritmo genético " +
					 "de transporte.\n";
		
		// validando campo probabilidad de cruce
		if (!Servicio.esNumero(campoProbCruce.getText()) ||
			campoProbCruce.getValueDouble() < 0 ||
			campoProbCruce.getValueDouble() > 1)
			error += "- En la probabilidad de cruce del algoritmo genético " +
					 "de transporte.\n";
		
		// validando campo probabilidad de mutación
		if (!Servicio.esNumero(campoProbMutacion.getText()) ||
			campoProbMutacion.getValueDouble() < 0 ||
			campoProbMutacion.getValueDouble() > 1)
			error += "- En la probabilidad de mutación del algoritmo genético " +
					 "de transporte.\n";
		
		return error;
	}
	
	/**
     * Método en donde se maneja la habilitación o deshabilitación de los
     * botones principales de de la configuración (restaurar y establecer).
     * En particular, este método no se implementa, ya que lo maneja el panel
     * contenedor de este sub-panel.
     *
     * @param habilitado Inidica si los botones se habilitan o deshabilitan.
     */
    public void habilitarBotones(boolean habilitado)
    {
    }
	
	/**
	 * Método que asigna dinamicamente el texto de recomendación de probabilidad
	 * de cruce y mutación de acuerdo a la función seleccionada.
	 */
	public void cambiarRecomendacion()
	{
		String[] textoCruce = {"Un valor menor a 0.1",
							   "Un valor entre [0.2, 0.5]"};
		
		String[] textoMutacion = {"Un valor menor a 0.3",
								  "Un valor entre [0.2, 0.5]"};
		
		if(comboFuncion.getSelectedIndex() == 0)
		{
			recomendacionProbCruce.setText(textoCruce[0]);
			recomendacionProbMutacion.setText(textoMutacion[0]);
		}
		else
		{
			recomendacionProbCruce.setText(textoCruce[1]);
			recomendacionProbMutacion.setText(textoMutacion[1]);
		}
	}
	
	/**
	 * Método que obtiene el objeto comboFuncion.
	 *
	 * @return comboFuncion El Combobox con el tipo de función selecionada.
	 */	
	public JComboBox obtenerComboFuncion()
	{
		return this.comboFuncion;
	}
}