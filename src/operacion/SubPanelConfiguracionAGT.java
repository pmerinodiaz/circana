/**
 * @(#)SubPanelConfiguracionAGT.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
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
 * el algoritmo gen�tico de transporte. 
 * 
 * @version 2.0 01/03/05
 * @author H�ctor D�az
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
	
	/** Cuadro de texto para el ingreso del tama�o de la poblaci�n. */
	private CampoNumerico campoTamano;
	
	/** Cuadro de texto para el ingreso del n�mero de generaciones. */
	private CampoNumerico campoGeneraciones;
	
	/** Cuadro de texto para el ingreso de la probabilidad de cruce. */
	private CampoNumerico campoProbCruce;
	
	/** Cuadro de texto para el ingreso de la probabilidad de mutaci�n. */
	private CampoNumerico campoProbMutacion;
	
	/** Etiqueta con recomendaci�n de la probabilidad de cruce. */
	private	JLabel recomendacionProbCruce;
	
	/** Etiqueta con recomendaci�n de la probabilidad de mutaci�n. */
	private JLabel recomendacionProbMutacion;;
	
	/** ComboBox para la selecci�n de la funci�n de evaluaci�n. */
	private JComboBox comboFuncion;
	
	/** ComboBox para la selecci�n de la t�cnica de selecci�n de individuos. */
	private JComboBox comboSeleccion;
		
	/**
     * M�todo constructor del panel de configuracion del algoritmo gen�tico de
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
     * M�todo donde se configura el t�tulo y borde del panel de configuraci�n
     * del algoritmo gen�tico.
     */
	public void configurarPanel()
	{		
		// borde del panel
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// titulo
		TitledBorder titulo = new TitledBorder(borde,
			" Configuraci�n del Algoritmo Gen�tico de Transporte ",
			TitledBorder.LEFT, TitledBorder.TOP);
		
		// agrega el t�tulo
		setBorder(titulo);
		setLayout(null);
	}
	
	/**
	 * M�todo en donde se configuran los componentes GUI del panel de
	 * configuraci�n del algoritmo gen�tico de transporte.
	 */
	public void configurarComponentes()
	{
		configurarTexto();
		configurarTextField();
		configurarComboBox();
	}
	
	/**
     * M�todo donde se configura los textos del panel de configuraci�n del
     * algoritmo gen�tico de transporte.
     */
	private void configurarTexto()
	{
		// creando fuente para la recomendaci�n
		Font fuenteRecomendacion = new Font("Arial", Font.PLAIN, 9);
		
		// crear los textos
		JLabel[] textoExplicacion = new JLabel[4];
		textoExplicacion[0] =
			new JLabel(new ImageIcon("../img/operacion_configuracion_agt.gif"));
		textoExplicacion[1] =
			new JLabel("Puede ingresar los principales par�metros de " +
			"configuraci�n para el Algoritmo Gen�tico que resuelve el");
		textoExplicacion[2] =
			new JLabel("problema de transporte. Pruebe el desempe�o de " +
			"la optimizaci�n del algoritmo gen�tico cambiando los");
		textoExplicacion[3] =
			new JLabel("distintos par�metros de configuraci�n.");
		JLabel textoTamano = new JLabel("Tama�o de la Poblaci�n:");
		JLabel textoGeneraciones = new JLabel("N�mero de Generaciones:");
		JLabel textoProbCruce = new JLabel("Probabilidad de Cruce:");
		JLabel textoProbMutacion = new JLabel("Probabilidad de Mutaci�n:");
		JLabel textoFuncion = new JLabel("Funci�n de Evaluaci�n:");
		JLabel textoSeleccion = new JLabel("T�cnica de Selecci�n:");
		
		// crear textos de recomendaci�n
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
		
		// agregar textos al panel de configuraci�n
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
		
		// agregar recomendaciones al panel de configuraci�n
		add(recomendacionTamano);
		add(recomendacionGeneraciones);
		add(recomendacionProbCruce);
		add(recomendacionProbMutacion);
		add(recomendacionFuncion);
		add(recomendacionSeleccion);
	}
	
	/**
     * M�todo donde se configuran las cajas de entrada de datos del algoritmo
     * gen�tico de transporte.
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
			"Ingrese el tama�o de la poblaci�n");
		campoGeneraciones.setToolTipText(
			"Ingrese la cantidad de ciclos de evoluci�n");
		campoProbCruce.setToolTipText(
			"Ingrese la probabilidad de cruce de individuos");
		campoProbMutacion.setToolTipText(
			"Ingrese la probabilidad de mutaci�n de un indiviudo");
		
		// agregar los campos de texto al panel de configuraci�n
		add(campoTamano);
		add(campoGeneraciones);
		add(campoProbCruce);
		add(campoProbMutacion);
	}
	
	/**
     * M�todo donde se configuran los combobox del algoritmo gen�tico de
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
			"Seleccionar la funci�n de costos transporte");
		comboSeleccion.setToolTipText(
			"Seleccionar la t�cnica de selecci�n");
		
		// agregar combos al panel de configuraci�n
		add(comboFuncion);
		add(comboSeleccion);
	}
	
	/**
	 * M�todo que adjunta los escuchadores eventos a los combobox que tiene el 
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
     * M�todo que carga el combo de la funci�n de evaluaci�n.
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
     * M�todo que carga el combo de la t�cnica de selecci�n.
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
     * M�todo que carga los valores de configuraci�n actuales del algoritmo
     * gen�tico de transporte.
     */
	public void cargarConfiguracion()
	{
		// carga los valores de configuraci�n actuales.
		campoTamano.setValue(ConfiguracionAGT.tamanoPoblacion);
		campoGeneraciones.setValue(ConfiguracionAGT.numeroGeneraciones);
		campoProbCruce.setValue(ConfiguracionAGT.probabilidadCruce);
		campoProbMutacion.setValue(ConfiguracionAGT.probabilidadMutacion);
		comboFuncion.setSelectedIndex(ConfiguracionAGT.tipoFuncion);
		comboSeleccion.setSelectedIndex(ConfiguracionAGT.tecnicaSeleccion);
	}
	
	/**
     * M�todo que carga los valores por defecto del algoritmo gen�tico de
     * transporte.
     */
	public void restaurarConfiguracion()
	{
		// cargar valores por defecto.
		ConfiguracionAGT.cargar(Proyecto.CODIGO_NUEVO);
		cargarConfiguracion();
	}
	
	/**
     * M�todo que establece los nuevos valores de configuraci�n ingresados
     * en el panel para los par�metros del algoritmo gen�tico de transporte.
     * Verificando si es correcta la informaci�n ingresada.
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
			
			// Establecer los nuevos par�metros
			ConfiguracionAGT.establecerParametros(parametros);
		}
		else
		{
			error = "Se han cometido los siguientes errores:\n" + error;
				JOptionPane.showMessageDialog(this, error,
				"Error en la configuraci�n",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
     * M�todo que verifica si los datos ingresados en la configuraci�n tienen
     * una sintaxis correcta.
     *
     * @return error Retorna un string con el error cometido.
     */
	public String validarConfiguracion()
	{
		String error = "";
		
		// validando campo tama�o de la poblaci�n
		if (!Servicio.esNumero(campoTamano.getText()) ||
			campoTamano.getValueInt() <= 0 ||
			Servicio.contarCaracter(campoTamano.getText(), '.') > 0)
			error += "- En el tama�o de la poblaci�n del algoritmo gen�tico " +
					 "de transporte.\n";
		
		// validando campo n�mero de generaciones
		if (!Servicio.esNumero(campoGeneraciones.getText()) ||
			campoGeneraciones.getValueInt() <= 0 ||
			Servicio.contarCaracter(campoGeneraciones.getText(), '.') > 0)
			error += "- En el n�mero de generaciones del algoritmo gen�tico " +
					 "de transporte.\n";
		
		// validando campo probabilidad de cruce
		if (!Servicio.esNumero(campoProbCruce.getText()) ||
			campoProbCruce.getValueDouble() < 0 ||
			campoProbCruce.getValueDouble() > 1)
			error += "- En la probabilidad de cruce del algoritmo gen�tico " +
					 "de transporte.\n";
		
		// validando campo probabilidad de mutaci�n
		if (!Servicio.esNumero(campoProbMutacion.getText()) ||
			campoProbMutacion.getValueDouble() < 0 ||
			campoProbMutacion.getValueDouble() > 1)
			error += "- En la probabilidad de mutaci�n del algoritmo gen�tico " +
					 "de transporte.\n";
		
		return error;
	}
	
	/**
     * M�todo en donde se maneja la habilitaci�n o deshabilitaci�n de los
     * botones principales de de la configuraci�n (restaurar y establecer).
     * En particular, este m�todo no se implementa, ya que lo maneja el panel
     * contenedor de este sub-panel.
     *
     * @param habilitado Inidica si los botones se habilitan o deshabilitan.
     */
    public void habilitarBotones(boolean habilitado)
    {
    }
	
	/**
	 * M�todo que asigna dinamicamente el texto de recomendaci�n de probabilidad
	 * de cruce y mutaci�n de acuerdo a la funci�n seleccionada.
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
	 * M�todo que obtiene el objeto comboFuncion.
	 *
	 * @return comboFuncion El Combobox con el tipo de funci�n selecionada.
	 */	
	public JComboBox obtenerComboFuncion()
	{
		return this.comboFuncion;
	}
}