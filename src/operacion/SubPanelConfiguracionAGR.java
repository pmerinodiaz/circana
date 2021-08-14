/**
 * @(#)SubPanelConfiguracionAGR.java 2.0 01/03/05
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
 * Clase que extiende de la clase JPanel. En esta clase permite configurar
 * el algoritmo gen�tico de ruteo. 
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
public class SubPanelConfiguracionAGR extends JPanel
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
	
	/** ComboBox para la selecci�n de la t�cnica de selecci�n de individuos. */
	private JComboBox comboSeleccion;
	
	/**
     * M�todo constructor del panel configuracion del algoritmo gen�tico de
     * ruteo.
     *
     * @param panelConfiguracionOperacion El panel creador de este sub-panel.
     * @param x Inicio X del sub-panel.
     * @param y Inicio Y del sub-panel.
     * @param ancho Ancho del sub-panel.
     * @param largo Largo del sub-panel.
     */
	public SubPanelConfiguracionAGR(
						PanelConfiguracionOperacion panelConfiguracionOperacion,
						int x, int y, int ancho, int largo)
	{
		// Inicializar el puntero.
		this.panelConfiguracionOperacion = panelConfiguracionOperacion;
		
		setBounds(x,y,ancho,largo);
		
		// Configurar
		configurarPanel();
		configurarComponentes();
	}
	
	/**
     * M�todo donde se configura el t�tulo y borde del panel de configuraci�n 
     * del algoritmo gen�tico de ruteo.
     */
	public void configurarPanel()
	{		
		// borde del panel
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// t�tulo
		TitledBorder titulo = new TitledBorder(borde,
			" Configuraci�n del Algoritmo Gen�tico de Ruteo ",
			TitledBorder.LEFT, TitledBorder.TOP);
		
		// agrega el t�tulo
		setBorder(titulo);
		setLayout(null);
	}
	
	/**
	 * M�todo en donde se configuran los componentes GUI del panel de
	 * configuraci�n del algoritmo gen�tico de ruteo.
	 */
	public void configurarComponentes()
	{
		configurarTexto();
		configurarTextField();
		configurarComboBox();
	}
	
	/**
     * M�todo donde se configura los textos del panel de configuraci�n del
     * algoritmo gen�tico de ruteo.
     */
	private void configurarTexto()
	{
		// creando fuente para la recomendacion
		Font fuenteRecomendacion = new Font("Arial", Font.PLAIN, 9);
		
		// crear los textos
		JLabel[] textoExplicacion = new JLabel[4];
		textoExplicacion[0] =
			new JLabel(new ImageIcon("../img/operacion_configuracion_agr.gif"));
		textoExplicacion[1] =
			new JLabel("Puede ingresar los principales par�metros de " +
			"configuraci�n para el Algoritmo Gen�tico que resuelve el " +
			"problema de ruteo. Pruebe el");
		textoExplicacion[2] =
			new JLabel("desempe�o de la optimizaci�n del algoritmo gen�tico " +
			"cambiando los distintos par�metros de configuraci�n.");
		JLabel textoTamano = new JLabel("Tama�o de la Poblaci�n:");
		JLabel textoGeneraciones = new JLabel("N�mero de Generaciones:");
		JLabel textoProbCruce = new JLabel("Probabilidad de Cruce:");
		JLabel textoProbMutacion = new JLabel("Probabilidad de Mutaci�n:");
		JLabel textoSeleccion = new JLabel("T�cnica de Selecci�n:");
		
		// crear textos de recomendaci�n
		JLabel recomendacionTamano =
			new JLabel("Un valor mayor a 10",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionGeneraciones =
			new JLabel("Un valor mayor a 10",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionProbCruce =
			new JLabel("Un valor entre [0.1, 0.6]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionProbMutacion =
			new JLabel("Un valor menor a 0.1",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionSeleccion =
			new JLabel("Torneo",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		
		// posicionar textos
		textoExplicacion[0].setBounds(660, 50, 95, 140);
		textoExplicacion[1].setBounds(20, 25, 760, 15);
		textoExplicacion[2].setBounds(20, 40, 760, 15);
		textoTamano.setBounds(20,65,190,20);
		textoGeneraciones.setBounds(20,95,190,20);
		textoProbCruce.setBounds(20,125,190,20);
		textoProbMutacion.setBounds(20,155,190,20);
		textoSeleccion.setBounds(20,185,190,20);
		
		// posicionar recomendaciones
		recomendacionTamano.setBounds(350,65,300,20);
		recomendacionGeneraciones.setBounds(350,95,300,20);
		recomendacionProbCruce.setBounds(350,125,300,20);
		recomendacionProbMutacion.setBounds(350,155,300,20);
		recomendacionSeleccion.setBounds(350,185,300,20);
		
		// asignar fuente a recomendaciones
		recomendacionTamano.setFont(fuenteRecomendacion);
		recomendacionGeneraciones.setFont(fuenteRecomendacion);
		recomendacionProbCruce.setFont(fuenteRecomendacion);
		recomendacionProbMutacion.setFont(fuenteRecomendacion);
		recomendacionSeleccion.setFont(fuenteRecomendacion);
		
		// agregar textos al panel de configuraci�n
		add(textoExplicacion[0]);
		add(textoExplicacion[1]);
		add(textoExplicacion[2]);
		add(textoTamano);
		add(textoGeneraciones);
		add(textoProbCruce);
		add(textoProbMutacion);
		add(textoSeleccion);
		
		// agregar recomendaciones al panel de configuraci�n
		add(recomendacionTamano);
		add(recomendacionGeneraciones);
		add(recomendacionProbCruce);
		add(recomendacionProbMutacion);
		add(recomendacionSeleccion);
	}
	
	/**
     * M�todo donde se configuran las cajas de entrada de datos del algoritmo
     * gen�tico de ruteo.
     */
	private void configurarTextField()
	{
		// crear cuadros de texto
		campoTamano = new CampoNumerico(0);
		campoGeneraciones = new CampoNumerico(0);
		campoProbCruce = new CampoNumerico(0.0);
		campoProbMutacion = new CampoNumerico(0.0);
		
		// posicionar textos
		campoTamano.setBounds(200,65,130,20);
		campoGeneraciones.setBounds(200,95,130,20);
		campoProbCruce.setBounds(200,125,130,20);
		campoProbMutacion.setBounds(200,155,130,20);
		
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
     * M�todo donde se configuran los combobox del algoritmo gen�tico de ruteo.
     */
	private void configurarComboBox()
	{				
		// crear combos boxs
		comboSeleccion = new JComboBox();
		
		// agrega los items desde la BD
		cargarComboTecnicaSeleccion();
		
		// posicionar combos
		comboSeleccion.setBounds(200,185,130,20);
		
		// asignar tips
		comboSeleccion.setToolTipText("Seleccionar la t�cnica de selecci�n");
		
		// agregar combos al panel de configuraci�n
		add(comboSeleccion);
	}
	
	/**
	 * M�todo que adjunta los escuchadores eventos a los componentes GUI que
	 * tiene el subpanel de configuracion AGR. En particular, este m�todo no se
	 * implementa, ya que no tiene eventos asociados.
	 */
	public void configurarEventos()
	{
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
			System.out.println("Error al cargar la tecnica de seleccion.");
		}
		conexion.desconectar();	
	}
	
	/**
     * M�todo que carga los valores de configuraci�n actuales del algoritmo 
     * gen�tico de ruteo.
     */
	public void cargarConfiguracion()
	{
		// carga los valores de configuraci�n actuales.
		campoTamano.setText(""+ConfiguracionAGR.tamanoPoblacion);
		campoGeneraciones.setText(""+ConfiguracionAGR.numeroGeneraciones);
		campoProbCruce.setValue(ConfiguracionAGR.probabilidadCruce);
		campoProbMutacion.setValue(ConfiguracionAGR.probabilidadMutacion);
		comboSeleccion.setSelectedIndex(ConfiguracionAGR.tecnicaSeleccion);
	}
	
	/**
     * M�todo que carga los valores por defecto del algoritmo gen�tico de ruteo.
     */
	public void restaurarConfiguracion()
	{
		// cargar valores por defecto.
		ConfiguracionAGR.cargar(Proyecto.CODIGO_NUEVO);
		cargarConfiguracion();
	}
	
	/**
     * M�todo que establece los nuevos valores de configuraci�n ingresados
     * en el panel para los par�metros del algoritmo gen�tico de ruteo.
     */
	public void establecerConfiguracion()
	{		
		String error = validarConfiguracion();
		
		if(error == "")
		{
			double[] parametros = new double[5];
			
			parametros[0] = campoTamano.getValueInt();
			parametros[1] = campoGeneraciones.getValueInt();
			parametros[2] = campoProbCruce.getValueDouble();
			parametros[3] = campoProbMutacion.getValueDouble();
			parametros[4] = comboSeleccion.getSelectedIndex();
			
			// Establecer los nuevos par�metros
			ConfiguracionAGR.establecerParametros(parametros);
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
     * M�todo que verifica si los datos ingresados en la configuraci�n tiene una 
     * sintaxis correcta.
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
					 "de ruteo.\n";
		
		// validando campo n�mero de generaciones
		if (!Servicio.esNumero(campoGeneraciones.getText()) ||
			campoGeneraciones.getValueInt() <= 0 ||
			Servicio.contarCaracter(campoGeneraciones.getText(), '.') > 0)
			error += "- En el n�mero de generaciones del algoritmo gen�tico " +
					 "de ruteo.\n";
		
		// validando campo probabilidad de cruce
		if (!Servicio.esNumero(campoProbCruce.getText()) ||
			campoProbCruce.getValueDouble() < 0 ||
			campoProbCruce.getValueDouble() > 1)
			error += "- En la probabilidad de cruce del algoritmo gen�tico " +
					 "de ruteo.\n";
		
		// validando campo probabilidad de mutaci�n
		if (!Servicio.esNumero(campoProbMutacion.getText()) ||
			campoProbMutacion.getValueDouble() < 0 ||
			campoProbMutacion.getValueDouble() > 1)
			error += "- En la probabilidad de mutaci�n del algoritmo gen�tico " +
					 "de ruteo.\n";
		
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
}